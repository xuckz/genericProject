package genericproject;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

public class Block {
	
	float pos_x;
	float pos_y;
	float pos_z;
	
	int type;
	/**
	 * defining the different block types
	 */
	final static int TYPE_DIRT = 0;
	final static int TYPE_ROCK = 1;
	final static int TYPE_SAND = 2;
	final static int TYPE_WOOD = 3;
	final static int TYPE_LEAF = 4;
	final static int TYPE_GRASS = 5;
	final static int TYPE_WATER = 6;
	
	/**
	 * defining the different colors for each block type
	 */
	final static float[] COLOR_DIRT = {0.82f, 0.48f, 0.18f, 1f};
	final static float[] COLOR_ROCK = {0.67f, 0.63f, 0.60f, 1f};
	final static float[] COLOR_SAND = {0.96f, 0.93f, 0.22f, 1f};
	final static float[] COLOR_WOOD = {0.69f, 0.41f, 0.01f, 1f};
	final static float[] COLOR_LEAF = {0.53f, 0.91f, 0.28f, 1f};
	final static float[] COLOR_GRASS = {0.43f, 0.81f, 0.28f, 1f};
	final static float[] COLOR_WATER = {0.13f, 0.34f, 0.84f, 0.7f};
	
	/**
	 * map to retrieve the correct color for each block type
	 */
	final static float[][] COLOR_MAP = {COLOR_DIRT, COLOR_ROCK, COLOR_SAND, COLOR_WOOD, COLOR_LEAF, COLOR_GRASS, COLOR_WATER};
	
	/**
	 * create a new block
	 * @param x Position x
	 * @param y Position y
	 * @param z Position z
	 * @param type Block type. See different block types
	 */
	public Block(float x, float y, float z, int type)
	{
		pos_x = x;
		pos_y = y;
		pos_z = z;
		this.type = type;
	}
	
	/**
	 * render method. render this single block
	 * @param drawable
	 */
	public void render(GLAutoDrawable drawable)
	{
		GL2 gl = drawable.getGL().getGL2();;
    	
    	gl.glPushMatrix();
    	gl.glTranslatef(pos_x, pos_y, pos_z);
    	gl.glBegin(GL2.GL_QUADS);										// Begin drawing square bottom
		
		gl.glColor4f( COLOR_MAP[type][0], COLOR_MAP[type][1], COLOR_MAP[type][2], COLOR_MAP[type][3]);
		
		//TODO: make sure that the sides are facing in the correct direction
		gl.glVertex3f(0.0f,0.0f, 0.0f);								
		gl.glVertex3f(1.0f,0.0f, 0.0f);
		gl.glVertex3f(1.0f,1.0f, 0.0f);
		gl.glVertex3f( 0.0f,1.0f, 0.0f);	
		
		gl.glVertex3f(0.0f,0.0f, 0.0f);								
		gl.glVertex3f(0.0f,0.0f, 1.0f);
		gl.glVertex3f(0.0f,1.0f, 1.0f);
		gl.glVertex3f( 0.0f,1.0f, 0.0f);	
		
		gl.glVertex3f(0.0f,0.0f, 0.0f);								
		gl.glVertex3f(1.0f,0.0f, 0.0f);
		gl.glVertex3f(1.0f,0.0f, 1.0f);
		gl.glVertex3f( 0.0f,0.0f, 1.0f);
		
		gl.glVertex3f(1.0f,0.0f, 1.0f);								
		gl.glVertex3f(1.0f,0.0f, 0.0f);
		gl.glVertex3f(1.0f,1.0f, 0.0f);
		gl.glVertex3f(1.0f,1.0f, 1.0f);
		
		gl.glVertex3f(1.0f,0.0f, 1.0f);								
		gl.glVertex3f(0.0f,0.0f, 1.0f);
		gl.glVertex3f(0.0f,1.0f, 1.0f);
		gl.glVertex3f(1.0f,1.0f, 1.0f);
		
		gl.glVertex3f(0.0f,1.0f, 0.0f);								
		gl.glVertex3f(0.0f,1.0f, 1.0f);
		gl.glVertex3f(1.0f,1.0f, 1.0f);
		gl.glVertex3f(1.0f,1.0f, 0.0f);
		
		gl.glEnd();														// Finish drawing square bottom
		gl.glPopMatrix();
	}
}
