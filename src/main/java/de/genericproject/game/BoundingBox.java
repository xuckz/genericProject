package de.genericproject.game;

import de.genericproject.game.graphic_utils.Vector3f;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import java.util.Vector;

public class BoundingBox {

	int size;
	Vector3f bottomLeftFrontPoint;
	Vector3f bottomLeftBackPoint;
	Vector3f bottomRightFrontPoint;
	Vector3f bottomRightBackPoint;
	Vector3f topLeftFrontPoint;
	Vector3f topLeftBackPoint;
	Vector3f topRightFrontPoint;
	Vector3f topRightBackPoint;
	
	Vector<Block> blocks;
	
	public BoundingBox(int x, int y, int z, int size)
	{
		bottomLeftFrontPoint = new Vector3f(x, y, z);
		bottomLeftBackPoint = new Vector3f(x, y, z+size);
		bottomRightFrontPoint = new Vector3f(x+size, y, z);
		bottomRightBackPoint = new Vector3f(x+size, y, z+size);
		topLeftFrontPoint = new Vector3f(x, y+size, z);
		topLeftBackPoint = new Vector3f(x, y+size, z+size);
		topRightFrontPoint = new Vector3f(x+size, y+size, z);
		topRightBackPoint = new Vector3f(x+size, y+size, z+size);
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
		return (x >= bottomLeftFrontPoint.getX() && x <= topRightBackPoint.getX());
	}
	
	private boolean withinRangeY(int y)
	{
		return (y >= bottomLeftFrontPoint.getY() && y <= topRightBackPoint.getY());
	}
	
	private boolean withinRangeZ(int z)
	{
		return (z >= bottomLeftFrontPoint.getZ() && z <= topRightBackPoint.getZ());
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
		if(rendermode == RenderModes.VERTEYARRAY)
		{
			for(int i=0; i<blocks.size(); i++)
				blocks.get(i).renderVertexArray(drawable);
		}
		else if(rendermode == RenderModes.VERTEX3F)
		{
			for(int i=0; i<blocks.size(); i++)
				blocks.get(i).renderVertex3f(drawable);
		}
		else if(rendermode == RenderModes.DISPLAYLIST)
		{
			for(int i=0; i<blocks.size(); i++)
				blocks.get(i).renderDisplayList(drawable);
		}
	}
	
	public Vector3f getBottomLeftFrontPoint()
	{
		return bottomLeftFrontPoint;
	}
	
	public Vector3f getBottomLeftBackPoint()
	{
		return bottomLeftBackPoint;
	}
	
	public Vector3f getBottomRightFrontPoint()
	{
		return bottomRightFrontPoint;
	}
	
	public Vector3f getBottomRightBackPoint()
	{
		return bottomRightBackPoint;
	}
	
	public Vector3f getTopLeftFrontPoint()
	{
		return topLeftFrontPoint;
	}
	
	public Vector3f getTopLeftBackPoint()
	{
		return topLeftBackPoint;
	}
	
	public Vector3f getTopRightFrontPoint()
	{
		return topRightFrontPoint;
	}
	
	public Vector3f getTopRightBackPoint()
	{
		return topRightBackPoint;
	}
}
