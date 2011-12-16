package de.genericproject.game;

import de.genericproject.game.fileimport.ModelReader;
import de.genericproject.game.fileimport.VoxelData;
import de.genericproject.game.fileimport.binvox.BinvoxReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import java.util.Vector;

public class BlockWorld {
	final static Logger log = LoggerFactory.getLogger(BlockWorld.class);

	Vector<Block> blocks;
	Vector<BoundingBox> boundingBoxes;
	
	final static int DIM_X_LOW = -199;
	final static int DIM_X_HIGH = 200;
	final static int DIM_Z_LOW = -199;
	final static int DIM_Z_HIGH = 200;
	
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
		log.debug("model loaded. containing "+blocks.size()+" blocks");
		
		for(int x = DIM_X_LOW; x < DIM_X_HIGH; x++)
		{
			for(int z = DIM_Z_LOW; z < DIM_Z_HIGH; z++)
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
		
		log.debug("blockworld created. containing "+blocks.size()+" blocks");
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
		for(int x=DIM_X_LOW; x < DIM_X_HIGH; x+=100)
		{
			for(int z=DIM_Z_LOW; z < DIM_Z_HIGH; z+=100)
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
	public void render(GLAutoDrawable drawable, Camera camera, int rendermode)
	{	
		GL2 gl = drawable.getGL().getGL2();
		framecounter++;
		if(framecounter > 40)
		{
			Block.debug_counter = 0;
			boxesDrawn = 0;
		}
		if(rendermode == RenderModes.VERTEYARRAY)
		{
			//	Enable and specificy pointers to vertex arrays
			gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
			gl.glEnableClientState(GL2.GL_INDEX_ARRAY);
			//gl.glEnableClientState(GL2.GL_NORMAL_ARRAY);
		}
		for(int i=0; i<boundingBoxes.size(); i++)
		{
			
				
			if(camera.isBoundingBoxVisible(boundingBoxes.get(i)) )
			{
				boundingBoxes.get(i).render(drawable, rendermode);
				boxesDrawn++;
			}
		}
		if(rendermode == RenderModes.VERTEYARRAY)
		{
			gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
			gl.glDisableClientState(GL2.GL_INDEX_ARRAY);
			//gl.glDisableClientState(GL2.GL_NORMAL_ARRAY);
		}
		else if(rendermode == RenderModes.DISPLAYLIST)
		{
			gl.glFlush();
		}
		if(framecounter > 40)
		{
			log.debug("rendered "+Block.debug_counter+" blocks in "+boxesDrawn+"/"+boundingBoxes.size());
			framecounter = 0;
		}
	}
	
	int framecounter;
	int boxesDrawn;
}
