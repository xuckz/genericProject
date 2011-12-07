package genericproject;

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
      
    public Graphics()
    {  
    	blockworld = new BlockWorld();
    }
    
    public void init(GLAutoDrawable drawable) 
    {
    	w = drawable.getWidth();
        h = drawable.getHeight();
        
        GL2 gl = drawable.getGL().getGL2();
        glu = new GLU();
        
        //gl.glShadeModel(GL.GL_LINE_SMOOTH);
        gl.glClearColor(0.0f,0.0f,0.0f,0.0f);
        gl.glClearDepth(1.0f);												// Depth Buffer Setup
    	gl.glEnable(GL2.GL_DEPTH_TEST);										// Enables Depth Testing
    	gl.glDepthFunc(GL2.GL_LEQUAL);										// The Type Of Depth Test To Do
    	gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);			// Really Nice Perspective Calculations
    	
    	cam_position_x = 0f;
    	cam_position_y = 3f;
    	cam_position_z = 18f;
    }

    public void display(GLAutoDrawable drawable) 
    {
        GL2 gl = drawable.getGL().getGL2();
        
        w = drawable.getWidth();
        h = drawable.getHeight();
        
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT|GL2.GL_DEPTH_BUFFER_BIT);			// Clear the colour and depth buffer
          
        gl.glViewport(0, 0, w, h);											// Reset The Current Viewport
        
        gl.glMatrixMode(GL2.GL_PROJECTION);									// Select The Projection Matrix
        gl.glLoadIdentity();												// Reset The Projection Matrix
    	
        glu.gluPerspective(45.0f,(float)w/(float)h,0.1f,100.0f);			// Calculate The Aspect Ratio Of The Window

        gl.glMatrixMode(GL2.GL_MODELVIEW);									// Select The Modelview Matrix
        gl.glLoadIdentity();												// Reset The Modelview Matrix     

        drawScene(drawable);												// Draw the scene
    }

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
    
    public void drawScene(GLAutoDrawable drawable)
    {
    	angle++;
    	GL2 gl = drawable.getGL().getGL2();
    	
    	gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        
        gl.glTranslatef( -cam_position_x, -cam_position_y, -cam_position_z );									// Move back 6 units
    	gl.glRotatef(angle, 0.0f, 1.0f, 0.0f);								// Rotate by angle

    	drawGraph(drawable);
    }
    
    private void drawGraph(GLAutoDrawable drawable)
    {
    	blockworld.render(drawable);
    }
    
    private void drawBox(GLAutoDrawable drawable)
    {
    	GL2 gl = drawable.getGL().getGL2();;
    	
    	gl.glPushMatrix();
    	gl.glBegin(GL2.GL_QUADS);										// Begin drawing square bottom
		
		gl.glColor3f( 0.0f, 0.0f, 1.0f);
		gl.glVertex3f(0.0f,0.0f, 0.0f);								
		gl.glVertex3f(-1.0f,0.0f, 0.0f);
		gl.glVertex3f(-1.0f,1.0f, 0.0f);
		gl.glVertex3f( 0.0f,1.0f, 0.0f);	
		
		gl.glColor3f( 0.0f, 1.0f, 0.0f);
		gl.glVertex3f(0.0f,0.0f, 0.0f);								
		gl.glVertex3f(0.0f,0.0f, 1.0f);
		gl.glVertex3f(0.0f,1.0f, 1.0f);
		gl.glVertex3f( 0.0f,1.0f, 0.0f);	
		
		gl.glColor3f( 0.0f, 1.0f, 1.0f);
		gl.glVertex3f(0.0f,0.0f, 0.0f);								
		gl.glVertex3f(-1.0f,0.0f, 0.0f);
		gl.glVertex3f(-1.0f,0.0f, 1.0f);
		gl.glVertex3f( 0.0f,0.0f, 1.0f);
		
		gl.glColor3f( 1.0f, 0.0f, 0.0f);
		gl.glVertex3f(-1.0f,0.0f, 1.0f);								
		gl.glVertex3f(-1.0f,0.0f, 0.0f);
		gl.glVertex3f(-1.0f,1.0f, 0.0f);
		gl.glVertex3f(-1.0f,1.0f, 1.0f);
		
		gl.glColor3f( 1.0f, 0.0f, 1.0f);
		gl.glVertex3f(-1.0f,0.0f, 1.0f);								
		gl.glVertex3f(0.0f,0.0f, 1.0f);
		gl.glVertex3f(0.0f,1.0f, 1.0f);
		gl.glVertex3f(-1.0f,1.0f, 1.0f);
		
		gl.glColor3f( 1.0f, 1.0f, 0.0f);
		gl.glVertex3f(0.0f,1.0f, 0.0f);								
		gl.glVertex3f(0.0f,1.0f, 1.0f);
		gl.glVertex3f(-1.0f,1.0f, 1.0f);
		gl.glVertex3f(-1.0f,1.0f, 0.0f);
		
		gl.glEnd();														// Finish drawing square bottom
		gl.glPopMatrix();
    }
    
    public void drawPyramid(GLAutoDrawable drawable)
    {
GL2 gl = drawable.getGL().getGL2();;
    	
    	gl.glPushMatrix();
			gl.glBegin(GL2.GL_TRIANGLES);									// Begin drawing triangle sides
			
			gl.glColor3f( 1.0f, 0.0f, 0.0f);								// Set colour to red
			gl.glVertex3f( 0.0f, 1.0f, 1.0f);								// Top vertex
			gl.glVertex3f(-1.0f,-1.0f, 0.0f);								// Bottom left vertex
			gl.glVertex3f( 1.0f,-1.0f, 0.0f);								// Bottom right vertex
			
			gl.glColor3f( 0.0f, 1.0f, 0.0f);								// Set colour to green
			gl.glVertex3f( 0.0f, 1.0f, 1.0f);								// Top vertex
			gl.glVertex3f(-1.0f,-1.0f, 2.0f);								// Bottom left vertex
			gl.glVertex3f( -1.0f,-1.0f, 0.0f);								// Bottom right vertex
			
			gl.glColor3f( 0.0f, 0.0f, 1.0f);								// Set colour to blue
			gl.glVertex3f( 0.0f, 1.0f, 1.0f);								// Top vertex
			gl.glVertex3f(-1.0f,-1.0f, 2.0f);								// Bottom left vertex
			gl.glVertex3f( 1.0f,-1.0f, 2.0f);								// Bottom right vertex
			
			gl.glColor3f( 0.5f, 0.0f, 0.5f);								// Set colour to purple
			gl.glVertex3f( 0.0f, 1.0f, 1.0f);								// Top vertex
			gl.glVertex3f( 1.0f,-1.0f, 0.0f);								// Bottom left vertex
			gl.glVertex3f( 1.0f,-1.0f, 2.0f);								// Bottom right vertex
			gl.glEnd();														// Finish drawing triangle sides
		gl.glPopMatrix();
	
		gl.glPushMatrix();
			gl.glBegin(GL2.GL_QUADS);										// Begin drawing square bottom
			
			gl.glColor3f( 1.0f, 1.0f, 0.0f);								// Set colour to yellow
			gl.glVertex3f(-1.0f,-1.0f, 0.0f);								// Bottom left vertex
			gl.glVertex3f(-1.0f,-1.0f, 2.0f);								// Top left vertex
			gl.glVertex3f( 1.0f,-1.0f, 2.0f);								// Bottom right vertex
			gl.glVertex3f( 1.0f,-1.0f, 0.0f);								// Top right vertex
			
			gl.glEnd();														// Finish drawing square bottom
		gl.glPopMatrix();
    }

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}
}


