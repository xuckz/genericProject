package de.genericproject.game.fileimport;

public interface ModelReader {
	public String getFormat();
	public VoxelData read(String filename);
}
