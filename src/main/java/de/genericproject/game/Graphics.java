package de.genericproject.game;

import javax.media.opengl.*;
import javax.media.opengl.glu.*;

public class Graphics implements GLEventListener
{
    private GLU glu;
    private int w, h;
    private float angle = 0.0f;
    
    private float cam_position_x;
    private float cam_position_y;
    private float cam_position_z;
    
    private BlockWorld blockworld;
    private LightSource light1;
      
    public Graphics()
    {  
    	blockworld = new BlockWorld();
    	light1 = new LightSource(GL2.GL_LIGHT1, 3.4f, 14f, 2.2f, 1f);
    	light1.setColorAmbient(0.2f, 0.15f, 0.15f, 0.3f);
    	light1.setColorSpecular(0.8f, 0.8f, 0.8f, 0.8f);
    }
    
    /**
     * init the openGL drawing
     */
    public void init(GLAutoDrawable drawable) 
    {
    	w = drawable.getWidth();
        h = drawable.getHeight();
        
        GL2 gl = drawable.getGL().getGL2();
        glu = new GLU();
        
        gl.glShadeModel(GL.GL_LINE_SMOOTH);
        gl.glClearColor(0.0f,0.0f,0.0f,1.0f);
        gl.glClearDepth(1.0f);												// Depth Buffer Setup
    	gl.glEnable(GL2.GL_DEPTH_TEST);										// Enables Depth Testing
    	gl.glDepthFunc(GL2.GL_LESS);										// The Type Of Depth Test To Do
    	gl.glShadeModel(GL2.GL_SMOOTH);
    	gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);			// Really Nice Perspective Calculations
    	
    	cam_position_x = 0f;
    	cam_position_y = 3f;
    	cam_position_z = 18f;
    }
    
    /**
     * move the camera along the z-axis
     * @param distance 
     */
    public void moveCamera(float distance)
    {
    	cam_position_z += distance;
    }
    
    /** 
     * rotate the camera around the y-axis
     * @param angle
     */
    public void rotateCamera(float angle)
    {
    	this.angle += angle;
    }

    /**
     * clear the view and start drawing
     */
    public void display(GLAutoDrawable drawable) 
    {
        GL2 gl = drawable.getGL().getGL2();
        
        w = drawable.getWidth();
        h = drawable.getHeight();
        
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT|GL2.GL_DEPTH_BUFFER_BIT);			// Clear the colour and depth buffer
          
        gl.glViewport(0, 0, w, h);											// Reset The Current Viewport
        
        gl.glEnable(GL2.GL_LIGHTING);
        light1.render(gl);
        
        gl.glCullFace(GL2.GL_BACK);
        
        gl.glMatrixMode(GL2.GL_PROJECTION);									// Select The Projection Matrix
        gl.glLoadIdentity();												// Reset The Projection Matrix
    	
        glu.gluPerspective(45.0f,(float)w/(float)h,0.1f,100.0f);			// Calculate The Aspect Ratio Of The Window

        gl.glMatrixMode(GL2.GL_MODELVIEW);									// Select The Modelview Matrix
        gl.glLoadIdentity();												// Reset The Modelview Matrix     

        drawScene(drawable);												// Draw the scene
    }

    /**
     * adjust the view to the new window size 
     */
	public void reshape(GLAutoDrawable drawable, int x, int y, int w2, int h2) 
    {
		GL2 gl = drawable.getGL().getGL2();
        
        w2 = drawable.getWidth();
        h2 = drawable.getHeight();
        
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        
        // perspective view
        gl.glViewport(10, 10, w-20, h-20);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f,(float)w/(float)h,0.1f,100.0f);
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) 
    {      
    }
    
    /**
     * draw the scene. this moves the world in the correct position relative to the camera
     * @param drawable
     */
    public void drawScene(GLAutoDrawable drawable)
    {
    	GL2 gl = drawable.getGL().getGL2();
    	
    	gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        
        gl.glTranslatef( -cam_position_x, -cam_position_y, -cam_position_z );									// Move back 6 units
    	gl.glRotatef(angle, 0.0f, 1.0f, 0.0f);								// Rotate by angle

    	drawGraph(drawable);
    }
    
    /**
     * draw all elements
     * @param drawable
     */
    private void drawGraph(GLAutoDrawable drawable)
    {
    	blockworld.render(drawable, cam_position_x, cam_position_y, cam_position_z, angle);
    }
    
	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}
}


