package de.genericproject.game;

import java.util.Vector;

import javax.media.opengl.GLAutoDrawable;

public class BoundingBox {

	int size;
	int lower_x;
	int lower_y;
	int lower_z;
	
	Vector<Block> blocks;
	
	public BoundingBox(int x, int y, int z, int size)
	{
		lower_x = x;
		lower_y = y;
		lower_z = z;
		this.size = size;
		blocks = new Vector<Block>();
	}
	
	public void add(Block block)
	{
		blocks.add(block);
	}
	
	public boolean containsBlock(Block block)
	{
		return containsBlock((int)block.pos_x, (int)block.pos_y, (int)block.pos_z);
	}
	
	public boolean containsBlock(int x, int y, int z)
	{
		return (withinRangeX(x) && withinRangeY(y) && withinRangeZ(z));
	}
	
	private boolean withinRangeX(int x)
	{
		return (x >= lower_x && x <= lower_x+size);
	}
	
	private boolean withinRangeY(int y)
	{
		return (y >= lower_y && y <= lower_y+size);
	}
	
	private boolean withinRangeZ(int z)
	{
		return (z >= lower_z && z <= lower_z+size);
	}
	
	public void clearAll()
	{
		blocks.removeAllElements();
	}
	
	public boolean isVisible(float x, float y, float z, float angle)
	{
		return true;
	}
	
	/**
	 * render everything inside this bounding box
	 */
	public void render(GLAutoDrawable drawable)
	{
		for(int i=0; i<blocks.size(); i++)
			blocks.get(i).render(drawable);
	}
}
