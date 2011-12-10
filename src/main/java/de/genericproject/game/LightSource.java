package de.genericproject.game;

import javax.media.opengl.GL2;

public class LightSource {

	float position[] = new float[4]; 
	float colorAmbient[] = new float[4];
	float colorSpecular[] = new float[4];
	float colorDiffuse[] = new float[4];
	
	/**
	 * open gl light id. open gl can only handle 8 light sources
	 */
	int lightID;
	
	public LightSource(int id, float x, float y, float z, float w)
	{
		lightID = id;
		position[0] = x;
		position[1] = y;
		position[2] = z;
		position[3] = w;
	}
	
	public void setColorAmbient(float r, float g, float b, float a)
	{
		colorAmbient[0] = r;
		colorAmbient[1] = g;
		colorAmbient[2] = b;
		colorAmbient[3] = a;
	}
	
	public void setColorSpecular(float r, float g, float b, float a)
	{
		colorSpecular[0] = r;
		colorSpecular[1] = g;
		colorSpecular[2] = b;
		colorSpecular[3] = a;
	}
	
	public void setColorDiffuse(float r, float g, float b, float a)
	{
		colorDiffuse[0] = r;
		colorDiffuse[1] = g;
		colorDiffuse[2] = b;
		colorDiffuse[3] = a;
	}
	
	public void render(GL2 gl)
	{
		 // Set light parameters.
        gl.glLightfv(lightID, GL2.GL_POSITION, position, 0);
        gl.glLightfv(lightID, GL2.GL_AMBIENT, colorAmbient, 0);
        gl.glLightfv(lightID, GL2.GL_SPECULAR, colorSpecular, 0);
        gl.glLightfv(lightID, GL2.GL_DIFFUSE, colorDiffuse, 0);
        
        gl.glLightf(lightID, GL2.GL_CONSTANT_ATTENUATION, 1.0f);
        gl.glLightf(lightID, GL2.GL_LINEAR_ATTENUATION, 0.01f);
        gl.glLightf(lightID, GL2.GL_QUADRATIC_ATTENUATION, 0.001f);
        
        gl.glEnable(lightID);
	}
	
}
