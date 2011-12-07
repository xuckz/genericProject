package genericproject;

import java.util.Vector;

import javax.media.opengl.GLAutoDrawable;

public class BlockWorld {

	Vector<Block> blocks;
	
	public BlockWorld()
	{
		blocks = new Vector<Block>();
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
		
	}
	
	public void render(GLAutoDrawable drawable)
	{
		for(int i=0; i<blocks.size(); i++)
			blocks.get(i).render(drawable);
	}
}
