package de.genericproject.game;

import de.genericproject.game.graphic_utils.Vector3f;

import javax.media.opengl.GL2;
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
	
	public Vector3f getBottomLeftPoint()
	{
		return bottomLeftPoint;
	}
	
	public Vector3f getTopRightPoint()
	{
		return topRightPoint;
	}
}
