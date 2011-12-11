package de.genericproject.game;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Block {

	static int debug_counter;

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


	final static float BOX_SIZE = 0.5f;

	/**
	 * the points of the standard box
	 *    F -- G
	 *   /    /|
	 *  E -- H |
	 *  | B  | C
	 *  |/   |/
	 *  A -- D
	 */

	// vertex coords array
	float vertices[];

	int indices[] = {	0,1,2,3,
                     	4,5,6,7,
                     	8,9,10,11,
                     	12,13,14,15,
						16,17,18,19,
                     	20,21,22,23
					};

			// the colors that match with each vertex
	float[] colors = new float[]
					{1,1,1,  1,1,0,  1,0,0,  1,0,1,              // h-e-a-d
                    1,1,1,  1,0,1,  0,0,1,  0,1,1,              // h-d-c-g
                    1,1,1,  0,1,1,  0,1,0,  1,1,0,            // h-g-f-e
                    1,1,0,  0,1,0,  0,0,0,  1,0,0,              // e-f-b-a
                    0,0,0,  0,0,1,  1,0,1,  1,0,0,               // b-c-d-a
                    0,0,1,  0,0,0,  0,1,0,  0,1,1};            // c-b-f-g

	private FloatBuffer vertexBuffer;
	private FloatBuffer colorBuffer;
	private FloatBuffer normalsBuffer;
	private IntBuffer indicesBuffer;
	/**
	 * map to retrieve the correct color for each block type
	 */

	/**
	 * create a new block
	 * @param x Position x
	 * @param y Position y
	 * @param z Position z
	 * @param type Block type. See different block types
	 */
	public Block(float x, float y, float z, int type)
	{
		vertices = new float[]
		{
			BOX_SIZE + x, 	BOX_SIZE + y , 	0f + z , 			0f + x , 		BOX_SIZE + y, 	0f + z,  			0f + x, 		0f + y, 		0f + z,  		BOX_SIZE + x, 	0f + y, 		0f + z,        	// h-e-a-d
			BOX_SIZE + x, 	BOX_SIZE + y , 	0f + z, 			BOX_SIZE + x , 	0f + y, 		0f + z, 			BOX_SIZE + x, 	0f + y, 		BOX_SIZE + z, 	BOX_SIZE + x, 	BOX_SIZE + y, 	BOX_SIZE + z, 	// h-d-c-g
			BOX_SIZE + x,	BOX_SIZE + y , 	0f + z, 			BOX_SIZE + x , 	BOX_SIZE + y, 	BOX_SIZE + z,  		0f + x, 		BOX_SIZE + y, 	BOX_SIZE + z,  	0f + x, 		BOX_SIZE + y, 	0f + z,        	// h-g-f-e
			0f + x, 		BOX_SIZE + y , 	0f + z,  			0f + x , 		BOX_SIZE + y, 	BOX_SIZE + z,  		0f + x, 		0f + y, 		BOX_SIZE + z,  	0f + x, 		0f + y, 		0f + z,    		// e-f-b-a
			0f + x, 		0f + y, 		BOX_SIZE + z ,  	BOX_SIZE + x , 	0f + y, 		BOX_SIZE + z,  		BOX_SIZE + x, 	0f + y, 		0f + z,  		0f + x, 		0f + y, 		0f + z,    		// b-c-d-a
			BOX_SIZE + x, 	0f + y , 		BOX_SIZE + z ,  	0f + x , 		0f + y, 		BOX_SIZE + z,  		0f + x , 		BOX_SIZE + y, 	BOX_SIZE + z,  	BOX_SIZE + x, 	BOX_SIZE + y, 	BOX_SIZE + z  	// c-b-f-g
		};

		pos_x = x;
		pos_y = y;
		pos_z = z;
		this.type = type;

		vertexBuffer =
			ByteBuffer
				.allocateDirect(vertices.length * 4)
				.order(ByteOrder.nativeOrder())
				.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.rewind();

		colorBuffer =
			ByteBuffer
				.allocateDirect(colors.length * 4)
				.order(ByteOrder.nativeOrder())
				.asFloatBuffer();
		colorBuffer.put(colors);
		colorBuffer.rewind();

		indicesBuffer =
			(ByteBuffer.allocateDirect(indices.length * 4))
				.order(ByteOrder.nativeOrder())
				.asIntBuffer();
		indicesBuffer.put(indices);
		indicesBuffer.rewind();

		normalsBuffer =
			ByteBuffer
				.allocateDirect(vertices.length * 4)
				.order(ByteOrder.nativeOrder())
				.asFloatBuffer();
		normalsBuffer.put(vertices);
		normalsBuffer.rewind();
	}

	/**
	 * render method. render this single block
	 * @param drawable
	 */
	public void render(GLAutoDrawable drawable)
	{
		GL2 gl = drawable.getGL().getGL2();

		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, new float[]{0.82f, 0.48f, 0.18f, 1f}, 0);
    	gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, new float[]{0.82f, 0.48f, 0.18f, 1f}, 0);
    	gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, 0.2f);

		// Enable and specificy pointers to vertex arrays
		gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL2.GL_INDEX_ARRAY);
		gl.glEnableClientState(GL2.GL_COLOR_ARRAY);

		gl.glVertexPointer(3, GL2.GL_FLOAT, 0, vertexBuffer);
		gl.glIndexPointer(GL2.GL_INT, 0, indicesBuffer);
		gl.glColorPointer(3, GL2.GL_FLOAT, 0, colorBuffer);
		gl.glDrawElements(GL2.GL_QUADS, 24, GL2.GL_UNSIGNED_INT, indicesBuffer);

		gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL2.GL_INDEX_ARRAY);
		gl.glDisableClientState(GL2.GL_COLOR_ARRAY);

/*		debug_counter++;
		GL2 gl = drawable.getGL().getGL2();;

    	gl.glPushMatrix();
    	gl.glTranslatef(pos_x * BOX_SIZE, pos_y * BOX_SIZE, pos_z * BOX_SIZE);
    	gl.glBegin(GL2.GL_QUADS);										// Begin drawing square bottom

		//gl.glColor4f( COLOR_MAP[type][0], COLOR_MAP[type][1], COLOR_MAP[type][2], COLOR_MAP[type][3]);

    	gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, COLOR_MAP[type], 0);
    	gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, COLOR_MAP[type], 0);
    	gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, 0.2f);

		float[] vertexArray = {BOX_POINT_A, BOX_POINT_B};

    	// bottom ABCD
    	gl.glNormal3f(0f, -1f, 0f);
		gl.glVertex3fv(BOX_POINT_A, 0);
		gl.glVertex3fv(BOX_POINT_B, 0);
		gl.glVertex3fv(BOX_POINT_C, 0);
		gl.glVertex3fv(BOX_POINT_D, 0);

		// front ADHE
		gl.glNormal3f(0f, 0f, -1f);
		gl.glVertex3fv(BOX_POINT_A, 0);
		gl.glVertex3fv(BOX_POINT_D, 0);
		gl.glVertex3fv(BOX_POINT_H, 0);
		gl.glVertex3fv(BOX_POINT_E, 0);
*/
	}
}
