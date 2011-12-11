package de.genericproject.game;

import javax.media.opengl.GLAutoDrawable;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.genericproject.game.fileimport.ModelReader;
import de.genericproject.game.fileimport.VoxelData;
import de.genericproject.game.fileimport.binvox.BinvoxReader;

public class BlockWorld {
	final static Logger log = LoggerFactory.getLogger(BlockWorld.class);

	Vector<Block> blocks;
	Vector<BoundingBox> boundingBoxes;
	
	public BlockWorld()
	{
		blocks = new Vector<Block>();
		boundingBoxes = new Vector<BoundingBox>();
		generateRandomMap();
		divideIntoBoudingBoxes();
		framecounter = 0;
	}
	
	
	
	private void generateRandomMap()
	{
		blocks.removeAllElements();
		
		VoxelData v = loadModelFromFile("chevalier.binvox");
		if(v != null) {
			addModel(v, -30, -50, 2);
		}
		
		for(int x = -99; x < 100; x++)
		{
			for(int z = - 99; z < 100; z++)
			{
				blocks.add(new Block(x, 0, z, (int)(Math.random()*3)));
				if(Math.random() < 0.002f)
				{				
					generateTree(x, 0, z);
				}
			}
		}
		blocks.add(new Block(0, 1, 0, Block.TYPE_ROCK));
		blocks.add(new Block(0, 2, 0, Block.TYPE_ROCK));
		blocks.add(new Block(-1, 1, 0, Block.TYPE_ROCK));
		blocks.add(new Block(1, 1, 0, Block.TYPE_ROCK));
		blocks.add(new Block(0, 1, -1, Block.TYPE_ROCK));
		blocks.add(new Block(0, 1, 1, Block.TYPE_ROCK));
		
		System.out.println("blockworld created. containing "+blocks.size()+" blocks");
	}
	
	/**
	 * place a tree on top of the block at x, y, z
	 * @param x
	 * @param y
	 * @param z
	 */
	private void generateTree(float x, float y, float z)
	{
		blocks.add(new Block(x, y+1, z, Block.TYPE_WOOD));
		blocks.add(new Block(x, y+2, z, Block.TYPE_WOOD));
		blocks.add(new Block(x, y+3, z, Block.TYPE_WOOD));
		blocks.add(new Block(x, y+4, z, Block.TYPE_WOOD));
		blocks.add(new Block(x, y+5, z, Block.TYPE_WOOD));
		blocks.add(new Block(x, y+6, z, Block.TYPE_LEAF));
		blocks.add(new Block(x+1, y+4, z, Block.TYPE_LEAF));
		blocks.add(new Block(x-1, y+4, z, Block.TYPE_LEAF));
		blocks.add(new Block(x, y+4, z+1, Block.TYPE_LEAF));
		blocks.add(new Block(x, y+4, z-1, Block.TYPE_LEAF));
		blocks.add(new Block(x-1, y+5, z-1, Block.TYPE_LEAF));
		blocks.add(new Block(x-1, y+5, z, Block.TYPE_LEAF));
		blocks.add(new Block(x-1, y+5, z+1, Block.TYPE_LEAF));
		blocks.add(new Block(x, y+5, z-1, Block.TYPE_LEAF));
		blocks.add(new Block(x, y+5, z+1, Block.TYPE_LEAF));
		blocks.add(new Block(x+1, y+5, z-1, Block.TYPE_LEAF));
		blocks.add(new Block(x+1, y+5, z, Block.TYPE_LEAF));
		blocks.add(new Block(x+1, y+5, z+1, Block.TYPE_LEAF));
		blocks.add(new Block(x+1, y+6, z, Block.TYPE_LEAF));
		blocks.add(new Block(x-1, y+6, z, Block.TYPE_LEAF));
		blocks.add(new Block(x, y+6, z+1, Block.TYPE_LEAF));
		blocks.add(new Block(x, y+6, z-1, Block.TYPE_LEAF));
		
	}
	
	private VoxelData loadModelFromFile(String filename) {
		ModelReader modelReader = new BinvoxReader();
		VoxelData voxelData = null;
		voxelData = modelReader.read(filename);
		return voxelData;
	}
	
	private void addModel(VoxelData data, float x, float y, float z) {	
		for(int m_x = 0; m_x < data.getDepth(); m_x++) {
			for(int m_y = 0; m_y < data.getHeight(); m_y++) {
				 for(int m_z = 0; m_z < data.getWidth(); m_z++) {
					if(data.getVoxel(m_x, m_y, m_z) != 0) {
						blocks.add(new Block(x + m_x, z + m_z, y + m_y, Block.TYPE_ROCK));
					}
				}
			}
		}
	}
	
	/**
	 * put the blocks from the root node into different bounding boxes
	 * this method should be put into it's own helper class (maybe factory)
	 */
	private void divideIntoBoudingBoxes()
	{
		for(int x=-99; x < 100; x+=100)
		{
			for(int z=-99; z < 100; z+=100)
			{
				boundingBoxes.add(new BoundingBox(x, 0, z, 100));
			}
		}
		
		// now sort the boxes into the bounding boxes
		while(blocks.size() > 0)
		{
			Block block = blocks.remove(0);
			for(int i=0; i<boundingBoxes.size(); i++)
			{
				if( boundingBoxes.get(i).containsBlock(block) )
				{
					boundingBoxes.get(i).add(block);
					i = boundingBoxes.size();
				}
			}
		}
	}
	
	/**
	 * loading the block world given by the filename. this will replace the existing world
	 * @param filename
	 */
	public void loadMapFromFile(String filename)
	{
		blocks.removeAllElements();
	}
	
	/**
	 * render method. render this block world
	 * @param drawable
	 */
	public void render(GLAutoDrawable drawable, float x, float y, float z, float angle)
	{	
		framecounter++;
		if(framecounter > 40)
		{
			Block.debug_counter = 0;
		}
		for(int i=0; i<boundingBoxes.size(); i++)
		{
			if(boundingBoxes.get(i).isVisible(x, y, z, angle) )
				boundingBoxes.get(i).render(drawable, x, y, z);
		}
		if(framecounter > 40)
		{
			System.out.println("rendered "+Block.debug_counter+" blocks");
			framecounter = 0;
		}
	}
	
	int framecounter;
}
