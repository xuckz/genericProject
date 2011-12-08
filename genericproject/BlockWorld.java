package genericproject;

import java.util.Vector;

import javax.media.opengl.GLAutoDrawable;

public class BlockWorld {

	Vector<Block> blocks;
	
	public BlockWorld()
	{
		blocks = new Vector<Block>();
		buildTestWorld();
	}
	
	/**
	 * This is just for simple testing methods and will be removed later. 
	 */
	public void buildTestWorld()
	{
		blocks.removeAllElements();
		blocks.add(new Block(0, 0, 0, Block.TYPE_DIRT));
		blocks.add(new Block(0, 1, 0, Block.TYPE_WOOD));
		blocks.add(new Block(0, 2, 0, Block.TYPE_WOOD));
		blocks.add(new Block(0, 3, 0, Block.TYPE_LEAF));
		blocks.add(new Block(0, 4, 0, Block.TYPE_LEAF));
		blocks.add(new Block(1, 3, 0, Block.TYPE_LEAF));
		blocks.add(new Block(-1, 3, 0, Block.TYPE_LEAF));
		blocks.add(new Block(0, 3, 1, Block.TYPE_LEAF));
		blocks.add(new Block(0, 3, -1, Block.TYPE_LEAF));
		blocks.add(new Block(3, 0, 2, Block.TYPE_ROCK));
		blocks.add(new Block(2, 0, 2, Block.TYPE_ROCK));
		blocks.add(new Block(2, 1, 2, Block.TYPE_ROCK));
		blocks.add(new Block(1, 0, 2, Block.TYPE_SAND));
		blocks.add(new Block(1, 0, 1, Block.TYPE_SAND));
		blocks.add(new Block(1, 0, 0, Block.TYPE_DIRT));
		blocks.add(new Block(-1, 0, 0, Block.TYPE_GRASS));
		blocks.add(new Block(-1, 0, 1, Block.TYPE_GRASS));
		blocks.add(new Block(0, 0, 1, Block.TYPE_WATER));	
		blocks.add(new Block(3, 0, 0, Block.TYPE_DIRT));
		
		for(int x=-32; x<33;x++)
		{
			for(int z=-32; z<33; z++)
			{
				blocks.add(new Block(x, -2, z, (int)(Math.random()*3)));
			}
		}
		System.out.println("blockworld created. containing "+blocks.size()+" blocks");
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
