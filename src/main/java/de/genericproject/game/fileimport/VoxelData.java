package de.genericproject.game.fileimport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VoxelData {
	final static Logger log = LoggerFactory.getLogger(VoxelData.class);

	private int height, width, depth;
	private int voxelCount;
	private byte[][][] data;
	
	public VoxelData(int depth, int height, int width) {
		this.depth = depth;
		this.height = height;
		this.width = width;
	}
	
	public void setData(byte[] inputArray) {
		data = new byte[depth][height][width];
		for(int x = 0; x < depth; x++) {
			for(int y = 0; y < height; y++) {
				for(int z = 0; z < width; z++) {
					data[x][y][z] = inputArray[x * depth * height + y * width + z]; 
				}
			}
		}
	}
	
	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public byte[][][] getData() {
		return data;
	}
		
	public byte getVoxel(int x, int y, int z) {
		return data[x][y][z];
	}

	public int getSize() {
		return depth * height * width;
	}

	public int getVoxelCount() {
		return voxelCount;
	}

	public void setVoxelCount(int voxels) {
		this.voxelCount = voxels;
	}
}
