package genericproject;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

public class Block {
	
	final static float BLOCK_SIZE = 1f; 
	
	float pos_x;
	float pos_y;
	float pos_z;
	
	int type;
	final static int TYPE_DIRT = 0;
	final static int TYPE_ROCK = 1;
	final static int TYPE_SAND = 2;
	final static int TYPE_WOOD = 3;
	final static int TYPE_LEAF = 4;
	final static int TYPE_GRASS = 5;
	
	final static float[] COLOR_DIRT = {0.82f, 0.48f, 0.18f};
	final static float[] COLOR_ROCK = {0.67f, 0.63f, 0.60f};
	final static float[] COLOR_SAND = {0.96f, 0.93f, 0.22f};
	final static float[] COLOR_WOOD = {0.69f, 0.41f, 0.01f};
	final static float[] COLOR_LEAF = {0.53f, 0.91f, 0.28f};
	final static float[] COLOR_GRASS = {0.43f, 0.81f, 0.28f};
	
	final static float[][] COLOR_MAP = {COLOR_DIRT, COLOR_ROCK, COLOR_SAND, COLOR_WOOD, COLOR_LEAF, COLOR_GRASS};
	
	public Block(float x, float y, float z, int type)
	{
		pos_x = x;
		pos_y = y;
		pos_z = z;
		this.type = type;
	}
	
	
	public void render(GLAutoDrawable drawable)
	{
		GL2 gl = drawable.getGL().getGL2();;
    	
    	gl.glPushMatrix();
    	gl.glTranslatef(pos_x, pos_y, pos_z);
    	gl.glBegin(GL2.GL_QUADS);										// Begin drawing square bottom
		
		gl.glColor3f( COLOR_MAP[type][0], COLOR_MAP[type][1], COLOR_MAP[type][2]);
		
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
