package genericproject;

import java.util.Vector;

import javax.media.opengl.GLAutoDrawable;

public class BlockWorld {

	Vector<Block> blocks;
	
	public BlockWorld()
	{
		blocks = new Vector<Block>();
		generateRandomMap();
	}
	
	
	
	private void generateRandomMap()
	{
		blocks.removeAllElements();
		
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
	public void render(GLAutoDrawable drawable)
	{
		for(int i=0; i<blocks.size(); i++)
			blocks.get(i).render(drawable);
	}
}
