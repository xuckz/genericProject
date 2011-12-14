package de.genericproject.game;

import de.genericproject.game.graphic_utils.Vector3f;

import javax.media.opengl.GLAutoDrawable;
import java.util.Vector;

public class BoundingBox {

	int size;
	Vector3f bottomLeftPoint;
	Vector3f topRightPoint;
	
	Vector<Block> blocks;
	
	public BoundingBox(int x, int y, int z, int size)
	{
		bottomLeftPoint = new Vector3f(x, y, z);
		topRightPoint = new Vector3f(x+size, y+size, z+size);
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
	
	public boolean containsPoint(float x, float y, float z)
	{
		return containsBlock((int)x, (int)y, (int)z);
	}
	
	private boolean withinRangeX(int x)
	{
		return (x >= bottomLeftPoint.getX() && x <= topRightPoint.getX());
	}
	
	private boolean withinRangeY(int y)
	{
		return (y >= bottomLeftPoint.getY() && y <= topRightPoint.getY());
	}
	
	private boolean withinRangeZ(int z)
	{
		return (z >= bottomLeftPoint.getZ() && z <= topRightPoint.getZ());
	}
	
	public void clearAll()
	{
		blocks.removeAllElements();
	}
	
	/**
	 * render everything inside this bounding box
	 */
	public void render(GLAutoDrawable drawable, int rendermode)
	{
		for(int i=0; i<blocks.size(); i++)
		{
			if(rendermode == 0)
				blocks.get(i).renderVertexArray(drawable);

			else if(rendermode == 1)
				blocks.get(i).renderVertex3f(drawable);
		}
	}
	
	public Vector3f getBottomLeftPoint()
	{
		return bottomLeftPoint;
	}
	
	public Vector3f getTopRightPoint()
	{
		return topRightPoint;
	}
}
