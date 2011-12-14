package de.genericproject.game;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Graphics implements GLEventListener, KeyListener
{
    private GLU glu;
    private int w, h;

    private BlockWorld blockworld;
    private LightSource light1;

	private boolean[] keys;

	private float zoom = 10.0f;
    private float cameraXPosition = 0.0f;
    private float cameraYPosition = 0.0f;
    private float cameraZPosition = 10.0f;

    private float cameraLXPosition = cameraXPosition;
    private float cameraLYPosition = cameraYPosition;
    private float cameraLZPosition = cameraZPosition - zoom;

	private int rendermode = 0;

	Camera camera;
      
    public Graphics()
    {  
    	// boolean array for keyboard input
        keys = new boolean[256];

		camera = new Camera();
        camera.yawLeft(0);
        camera.pitchDown(0.0);
        camera.moveForward(-10);
        camera.look(10);

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


		gl.glViewport(0, 0, w, h);											// Reset The Current Viewport
	}

    /**
     * clear the view and start drawing
     */
    public void display(GLAutoDrawable drawable) 
    {
        GL2 gl = drawable.getGL().getGL2();
        
        w = drawable.getWidth();
        h = drawable.getHeight();

	   	keyboardChecks();
        
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT|GL2.GL_DEPTH_BUFFER_BIT);			// Clear the colour and depth buffer
        
        gl.glEnable(GL2.GL_LIGHTING);
        light1.render(gl);
        
        gl.glCullFace(GL2.GL_BACK);

        gl.glMatrixMode(GL2.GL_PROJECTION);					// Select The Projection Matrix
        gl.glLoadIdentity();							// Reset The Projection Matrix

        glu.gluPerspective(70.0, (float)w/(float)h, 1, 50);
        glu.gluLookAt(camera.getXPos(), camera.getYPos(), camera.getZPos(), camera.getXLPos(), camera.getYLPos(), camera.getZLPos(), 0.0, 1.0, 0.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);					// Select The Modelview Matrix
        gl.glLoadIdentity();

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

    	drawGraph(drawable);
    }
    
    /**
     * draw all elements
     * @param drawable
     */
    private void drawGraph(GLAutoDrawable drawable)
    {
    	blockworld.render(drawable, (float)camera.getXPos(), (float)camera.getYPos(), (float)camera.getZPos(), (float)camera.getPitch(), rendermode);
    }
    
	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent key)
    {
    	try
    	{
        	char i = key.getKeyChar();
			keys[(int)i] = true;
    	}
    	catch(Exception e){};


    }


	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
    public void keyReleased(KeyEvent key)
    {
    	try
    	{
        	char i = key.getKeyChar();
        	keys[(int)i] = false;
    	}
    	catch(Exception e){};
    }

	public void keyboardChecks()
    {
        if(keys['w'])
        {
            cameraZPosition -= 0.5;
            cameraLZPosition -= 0.5;

            camera.moveForward(0.5);
            camera.look(10);

        }

        if(keys['s'])
        {
            cameraZPosition += 0.5;
            cameraLZPosition += 0.5;

            camera.moveForward(-0.5);
            camera.look(10);
        }

        if(keys['j'])
        {
            camera.pitchUp(0.1);
            camera.look(10);
        }

        if(keys['k'])
        {
            camera.pitchDown(0.1);
            camera.look(10);
        }

        if(keys['q'])
        {
            camera.yawLeft(0.05);
            camera.look(10);
        }

        if(keys['e'])
        {
            camera.yawRight(0.05);
            camera.look(10);
        }

        if(keys['a'])
        {
            camera.strafeLeft(0.5);
            camera.look(10);
        }

        if(keys['d'])
        {
            camera.strafeRight(0.5);
            camera.look(10);
        }

		if(keys['r'])
        {
			if(rendermode == 0)
			{
				rendermode = 1;
				System.out.print("changed rendermode to vertex array \n");
			}

			else if(rendermode == 1)
			{
				rendermode = 0;
				System.out.print("changed rendermode to vertex3f \n");
			}
        }
	}
}


