package de.genericproject.game.fileimport.binvox;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.genericproject.game.fileimport.ModelReader;
import de.genericproject.game.fileimport.VoxelData;

public class BinvoxReader implements ModelReader {
	final static Logger log = LoggerFactory.getLogger(BinvoxReader.class);

	BinvoxHeader header;

	@Override
	public String getFormat() {
		return "binvox";
	}

	private DataInputStream createDataInputStream(String filename)
			throws FileNotFoundException {
		return new DataInputStream(new BufferedInputStream(new FileInputStream(
				filename)));
	}

	@Override
	public VoxelData read(String filename) {
		DataInputStream dataInputStream;
		VoxelData voxelData;

		try {
			dataInputStream = createDataInputStream(filename);
		} catch (FileNotFoundException e) {
			log.error("The specified file could not be found!", e);
			return null;
		}
		try {
			header = readHeader(dataInputStream);
			voxelData = readData(dataInputStream);
		} catch (IOException e) {
			log.error("Error while reading from InputStream!", e);
			return null;
		}

		return voxelData;
	}

	private BinvoxHeader readHeader(DataInputStream dataInputStream)
			throws IOException {
		header = new BinvoxHeader();

		String line = dataInputStream.readLine();
		String lineParts[] = line.split(" ");
		if (!lineParts[0].equals("#binvox") && !lineParts[1].contentEquals("1")) {
			log.error("File is not a binvox file!");
			return null;
		}

		while (!line.contains("data") || line == null) {
			lineParts = line.split(" ");
			try {
				if (lineParts[0].equals("dim")) {
					setDimensions(lineParts);
				} 
				else
				if (lineParts[0].equals("translate")) {
					setTranslations(lineParts);
				} 
				else
				if (lineParts[0].equals("scale")) {
					setScale(lineParts);
				}
			} catch (NumberFormatException e) {
				log.info("File format is not correct or file is damaged!");
				return null;
			}

			line = dataInputStream.readLine();
		}
		return header;
	}

	private void setDimensions(String[] lineParts) {

		header.setDepth(Integer.parseInt(lineParts[1]));
		header.setHeight(Integer.parseInt(lineParts[2]));
		header.setWidth(Integer.parseInt(lineParts[3]));
	}

	private void setTranslations(String[] lineParts) {

		header.setTranslationX(Double.parseDouble(lineParts[1]));
		header.setTranslationY(Double.parseDouble(lineParts[2]));
		header.setTranslationZ(Double.parseDouble(lineParts[3]));
	}

	private void setScale(String[] lineParts) {

		header.setScale(Double.parseDouble(lineParts[1]));
	}

	public VoxelData readData(DataInputStream dataInputStream)
			throws IOException {
		int depth = header.getDepth();
		int height = header.getHeight();
		int width = header.getWidth();
		int size = depth * height * width;

		VoxelData voxelData = new VoxelData(depth, height, width);
		byte[] voxelArray = new byte[size];

		byte value;
		int count;

		int index = 0;
		int end_index = 0;

		int nr_voxels = 0;

		while (end_index < size) {
			try {
				value = dataInputStream.readByte();
				count = dataInputStream.readByte() & 0xff;

				end_index = index + count;
				if (end_index > size)
					return null;
				for (int i = index; i < end_index; i++) {
					voxelArray[i] = value;
				}
				if (value > 0)
					nr_voxels += count;
				index = end_index;

			} catch (EOFException endOfFileException) {
				log.error("File unexpectedly ended!", endOfFileException);
				return null;
			}
		}
		voxelData.setData(voxelArray);
		voxelData.setVoxelCount(nr_voxels);
		return voxelData;
	}

}
