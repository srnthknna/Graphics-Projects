//
// finalMain.java
//
// Main class for lighting / shading assignment.
//
// Students should not be modifying this file.
//

import java.awt.*;
import java.io.IOException;
import java.nio.*;
import java.awt.event.*;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.*;

import com.jogamp.opengl.util.Animator;

public class finalMain implements GLEventListener, KeyListener {

	/**
	 * We need four vertex buffers and four element buffers: two for the torus
	 * (flat shading and non-flat shading) and two for the teapot (flat shading
	 * and non-flat shading).
	 *
	 * Array layout: column 0 column 1 row 0: torus flat torus non-flat row 1:
	 * teapot flat teapot non-flat
	 */
	private int vbuffer[][];
	private int ebuffer[][];
	private int numVerts[][];

	/**
	 * Animation control
	 */
	Animator anime;
	boolean animating;

	/**
	 * Initial animation rotation angles
	 */
	float angles[];

	// texture values
	public viewParams assignObj;

	/**
	 * Current shader type: flat vs. non-flat
	 */
	int currentShader;

	/**
	 * Program IDs - current, and all variants
	 */
	public int program;
	public int flat;
	public int phong;
	public int gouraud;

	/**
	 * Shape info
	 */
	shapes myShape;

	/**
	 * Lighting information
	 */
	lightingParams myPhong;

	/**
	 * Viewing information
	 */
	viewParams myView;

	/**
	 * My canvas
	 */
	GLCanvas myCanvas;

	/**
	 * Constructor
	 */
	public finalMain(GLCanvas G) {
		vbuffer = new int[4][2];
		ebuffer = new int[4][2];
		numVerts = new int[4][2];

		angles = new float[4];

		animating = false;
		currentShader = shapes.SHADE_FLAT;
		// assigning rotation angles for all four shapes
		angles[0] = 0.0f;
		angles[1] = 0.0f;
		angles[2] = 0.0f;
		angles[3] = 0.0f;

		myCanvas = G;
		assignObj = new viewParams();
		// Initialize lighting and view
		myPhong = new lightingParams();
		myView = new viewParams();

		// Set up event listeners
		G.addGLEventListener(this);
		G.addKeyListener(this);
	}

	/**
	 * method for error checking in code
	 * 
	 * @param gl2
	 */
	private void errorCheck(GL2 gl2) {
		int code = gl2.glGetError();
		// if there is a error then report the user
		if (code == GL.GL_NO_ERROR)
			System.err.println("All is well");
		else
			System.err.println("Problem - error code : " + code);

	}

	/**
	 * Simple animate function
	 */
	public void animate() {
		angles[shapes.sphereNumber] += 2;
		angles[shapes.cylinderNumber] += 1;
		angles[shapes.cubeNumber] += 2;
		angles[shapes.coneNumber] += 1;
	}

	/**
	 * Called by the drawable to initiate OpenGL rendering by the client.
	 */
	public void display(GLAutoDrawable drawable) {
		// get GL
		GL2 gl2 = (drawable.getGL()).getGL2();

		// clear and draw params..
		gl2.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		// use the correct program
		gl2.glUseProgram(program);
		// For sphere shape
		// set up texture for sphere
		assignObj.setUpTextures(program, gl2);

		// set up viewing and projection parameters
		myView.setUpFrustum(program, gl2);

		// set up the camera
		myView.setUpCamera(program, gl2, 0.5f, 3.5f, 6.5f, 0.0f, 1.0f, 0.0f,
				0.0f, 1.0f, 0.0f);

		// set up transformations for the torus
		myView.setUpTransforms(program, gl2, 0.7f, 0.7f, 0.7f,
				angles[shapes.sphereNumber], angles[shapes.sphereNumber],
				angles[shapes.sphereNumber], 0f, 2.3f, 0f);
		// set up Phong illumination
		myPhong.setUpPhong(program, gl2, 0);

		// draw it
		selectBuffers(gl2, shapes.sphereNumber, currentShader);
		gl2.glDrawElements(GL.GL_TRIANGLES,
				numVerts[shapes.sphereNumber][currentShader],
				GL.GL_UNSIGNED_SHORT, 0l);
		// For cylinder shape

		// set up transformations for the teapot
		myView.setUpTransforms(program, gl2, 0.6f, 0.6f, 0.7f,
				angles[shapes.cylinderNumber] - 20f,
				angles[shapes.cylinderNumber], angles[shapes.cylinderNumber],
				-0.85f, -1.2f, 0f);
		// set up Phong illumination
		myPhong.setUpPhong(program, gl2, 1);

		// draw it
		selectBuffers(gl2, shapes.cylinderNumber, currentShader);
		gl2.glDrawElements(GL.GL_TRIANGLES,
				numVerts[shapes.cylinderNumber][currentShader],
				GL.GL_UNSIGNED_SHORT, 0l);
		// For Cone shape
		// set up transformations for the teapot
		myView.setUpTransforms(program, gl2, .52f, .42f, .5f,
				angles[shapes.coneNumber] - 20f, angles[shapes.coneNumber],
				angles[shapes.coneNumber], -0.1f, -0.2f, 1.2f);
		// set up Phong illumination
		myPhong.setUpPhong(program, gl2, 2);

		// draw it
		selectBuffers(gl2, shapes.coneNumber, currentShader);
		gl2.glDrawElements(GL.GL_TRIANGLES,
				numVerts[shapes.coneNumber][currentShader],
				GL.GL_UNSIGNED_SHORT, 0l);
		// For Cube shape
		// set up transformations for the torus
		myView.setUpTransforms(program, gl2, 1f, .2f, 1f,
				angles[shapes.cubeNumber] - 16f,
				angles[shapes.cubeNumber] + 50f, angles[shapes.cubeNumber], 0f,
				1f, 0f);
		// set up Phong illumination
		myPhong.setUpPhong(program, gl2, 3);

		// draw it
		selectBuffers(gl2, shapes.cubeNumber, currentShader);
		gl2.glDrawElements(GL.GL_TRIANGLES,
				numVerts[shapes.cubeNumber][currentShader],
				GL.GL_UNSIGNED_SHORT, 0l);
		// perform any required animation for the next time
		if (animating) {
			animate();
		}
	}

	/**
	 * Notifies the listener to perform the release of all OpenGL resources per
	 * GLContext, such as memory buffers and GLSL programs.
	 */
	public void dispose(GLAutoDrawable drawable) {
	}

	/**
	 * verify shader creation
	 * 
	 * @param myShaders
	 * @param program
	 * @param which
	 */
	private void checkShaderError(shaderSetup myShaders, int program,
			String which) {
		if (program == 0) {
			System.err.println("Error setting " + which + " shader - "
					+ myShaders.errorString(myShaders.shaderErrorCode));
			System.exit(1);
		}
	}

	/**
	 * Called by the drawable immediately after the OpenGL context is
	 * initialized.
	 */
	public void init(GLAutoDrawable drawable) {
		// get the gl object
		GL2 gl2 = drawable.getGL().getGL2();

		// create the Animator now that we have the drawable
		anime = new Animator(drawable);

		// Load shaders, verifying each
		shaderSetup myShaders = new shaderSetup();

		flat = myShaders.readAndCompile(gl2, "flat.vert", "flat.frag");
		checkShaderError(myShaders, flat, "flat");

		gouraud = myShaders.readAndCompile(gl2, "gouraud.vert", "gouraud.frag");
		checkShaderError(myShaders, gouraud, "gouraud");

		phong = myShaders.readAndCompile(gl2, "phong.vert", "phong.frag");
		checkShaderError(myShaders, phong, "phong");

		// Default shader program
		program = flat;

		// Create all four shapes
		createShapes(gl2, shapes.sphereNumber, shapes.SHADE_FLAT);
		createShapes(gl2, shapes.sphereNumber, shapes.SHADE_NOT_FLAT);
		createShape(gl2, shapes.cylinderNumber, shapes.SHADE_FLAT);
		createShape(gl2, shapes.cylinderNumber, shapes.SHADE_NOT_FLAT);
		createShape(gl2, shapes.cubeNumber, shapes.SHADE_FLAT);
		createShape(gl2, shapes.cubeNumber, shapes.SHADE_NOT_FLAT);
		createShape(gl2, shapes.coneNumber, shapes.SHADE_FLAT);
		createShape(gl2, shapes.coneNumber, shapes.SHADE_NOT_FLAT);

		// Other GL initialization
		gl2.glEnable(GL.GL_DEPTH_TEST);
		gl2.glEnable(GL.GL_CULL_FACE);
		gl2.glCullFace(GL.GL_BACK);
		gl2.glFrontFace(GL.GL_CCW);
		gl2.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl2.glDepthFunc(GL.GL_LEQUAL);
		gl2.glClearDepth(1.0f);
		// load texture for sphere
		try {
			assignObj.loadTexture("triangletiling.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Called by the drawable during the first repaint after the component has
	 * been resized.
	 */
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
	}

	/**
	 * creates a new shape with texture
	 */
	public void createShapes(GL2 gl2, int i, int j) {
		// clear the old shape
		myShape = new shapes();

		// clear the old shape
		myShape.clear();

		// make a shape
		myShape.makesphere(0);

		// get your verticies and elements
		Buffer points = myShape.getVertices();

		Buffer elements = myShape.getElements();

		Buffer texCoords = myShape.getUV();

		// set up the vertex buffer
		int bf[] = new int[4];
		gl2.glGenBuffers(4, bf, 0);
		vbuffer[i][j] = bf[0];

		long vertBsize = myShape.getNVerts() * 4l * 4l;
		long tdataSize = myShape.getNVerts() * 2l * 4l;
		gl2.glBindBuffer(GL.GL_ARRAY_BUFFER, vbuffer[i][j]);
		gl2.glBufferData(GL.GL_ARRAY_BUFFER, vertBsize + tdataSize, null,
				GL.GL_STATIC_DRAW);
		gl2.glBufferSubData(GL.GL_ARRAY_BUFFER, 0, vertBsize, points);
		gl2.glBufferSubData(GL.GL_ARRAY_BUFFER, vertBsize, tdataSize, texCoords);

		gl2.glGenBuffers(4, bf, 0);
		ebuffer[i][j] = bf[0];

		long eBuffSize = myShape.getNVerts() * 2l;
		gl2.glBindBuffer(GL.GL_ELEMENT_ARRAY_BUFFER, ebuffer[i][j]);
		gl2.glBufferData(GL.GL_ELEMENT_ARRAY_BUFFER, eBuffSize, elements,
				GL.GL_STATIC_DRAW);

		numVerts[i][j] = myShape.getNVerts();

	}

	/**
	 * Create vertex and element buffers for a shape
	 */
	public void createShape(GL2 gl2, int obj, int flat) {
		// clear the old shape
		myShape = new shapes();

		// make the shape
		myShape.makeShape(obj, flat);

		// save the vertex count
		numVerts[obj][flat] = myShape.nVertices();

		// get the vertices
		Buffer points = myShape.getVertices();
		long dataSize = myShape.nVertices() * 4l * 4l;

		// get the normals
		Buffer normals = myShape.getNormals();
		long ndataSize = myShape.nVertices() * 3l * 4l;

		// get the element data
		Buffer elements = myShape.getElements();
		long edataSize = myShape.nVertices() * 2l;

		// generate the vertex buffer
		int bf[] = new int[1];

		gl2.glGenBuffers(1, bf, 0);
		vbuffer[obj][flat] = bf[0];
		gl2.glBindBuffer(GL.GL_ARRAY_BUFFER, vbuffer[obj][flat]);
		gl2.glBufferData(GL.GL_ARRAY_BUFFER, dataSize + ndataSize, null,
				GL.GL_STATIC_DRAW);
		gl2.glBufferSubData(GL.GL_ARRAY_BUFFER, 0, dataSize, points);
		gl2.glBufferSubData(GL.GL_ARRAY_BUFFER, dataSize, ndataSize, normals);

		// generate the element buffer
		gl2.glGenBuffers(1, bf, 0);
		ebuffer[obj][flat] = bf[0];
		gl2.glBindBuffer(GL.GL_ELEMENT_ARRAY_BUFFER, ebuffer[obj][flat]);
		gl2.glBufferData(GL.GL_ELEMENT_ARRAY_BUFFER, edataSize, elements,
				GL.GL_STATIC_DRAW);

	}

	/**
	 * Bind the correct vertex and element buffers
	 *
	 * Assumes the correct shader program has already been enabled
	 */
	private void selectBuffers(GL2 gl2, int obj, int flat) {
		// bind the buffers
		gl2.glBindBuffer(GL.GL_ARRAY_BUFFER, vbuffer[obj][flat]);
		gl2.glBindBuffer(GL.GL_ELEMENT_ARRAY_BUFFER, ebuffer[obj][flat]);

		// calculate the number of bytes of vertex data
		long dataSize = numVerts[obj][flat] * 4l * 4l;

		// set up the vertex attribute variables
		int vPosition = gl2.glGetAttribLocation(program, "vPosition");
		gl2.glEnableVertexAttribArray(vPosition);
		gl2.glVertexAttribPointer(vPosition, 4, GL.GL_FLOAT, false, 0, 0l);
		int vNormal = gl2.glGetAttribLocation(program, "vNormal");
		gl2.glEnableVertexAttribArray(vNormal);
		gl2.glVertexAttribPointer(vNormal, 3, GL.GL_FLOAT, false, 0, dataSize);
		int vTex = gl2.glGetAttribLocation(program, "uvvvariable");
		gl2.glEnableVertexAttribArray(vTex);
		gl2.glVertexAttribPointer(vTex, 2, GL.GL_FLOAT, false, 0, dataSize);

	}

	/**
	 * Because I am a Key Listener...we'll only respond to key presses
	 */
	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	/**
	 * Invoked when a key has been pressed.
	 */
	public void keyPressed(KeyEvent e) {
		// Get the key that was pressed
		char key = e.getKeyChar();

		// Respond appropriately
		switch (key) {

		case '1': // flat shading
			program = flat;
			currentShader = shapes.SHADE_FLAT;
			break;

		case '2': // Gouraud shading
			program = gouraud;
			currentShader = shapes.SHADE_NOT_FLAT;
			break;

		case '3': // phong shading
			program = phong;
			currentShader = shapes.SHADE_NOT_FLAT;
			break;

		case 'a': // animate
			animating = true;
			anime.start();
			break;

		case 's': // stop animating
			animating = false;
			anime.stop();
			break;

		case 'q':
		case 'Q':
			System.exit(0);
			break;
		}

		// do a redraw
		myCanvas.display();
	}

	/**
	 * main program
	 */
	public static void main(String[] args) {
		// GL setup
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		GLCanvas canvas = new GLCanvas(caps);

		// create your tessMain
		finalMain myMain = new finalMain(canvas);

		Frame frame = new Frame("CG - Shading Assignment");
		frame.setSize(600, 700);
		frame.add(canvas);
		frame.setVisible(true);

		// by default, an AWT Frame doesn't do anything when you click
		// the close button; this bit of code will terminate the program when
		// the window is asked to close
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}
