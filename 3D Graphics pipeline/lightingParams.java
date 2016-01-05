//
// lightingParams.java
//
// Simple class for setting up the viewing and projection transforms
// for the Shading Assignment.
//
// Students are to complete this class.
//

import java.io.IOException;
import java.io.InputStream;

import javax.media.opengl.*;
import javax.media.opengl.fixedfunc.*;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;

public class lightingParams {

	/**
	 * constructor
	 */
	public lightingParams() {

	}

	/**
	 * This functions sets up the lighting, material, and shading parameters for
	 * the Phong shader.
	 *
	 * You will need to write this function, and maintain all of the values
	 * needed to be sent to the vertex shader.
	 *
	 * @param program
	 *            - The ID of an OpenGL (GLSL) shader program to which parameter
	 *            values are to be sent
	 *
	 * @param gl2
	 *            - GL2 object on which all OpenGL calls are to be made
	 * @param num
	 *            - object code that is to be created
	 *
	 */
	public void setUpPhong(int program, GL2 gl2, int num) {
		// To initialize the variables in the vertex and fragment shader
		gl2.glUniform4f(gl2.glGetUniformLocation(program, "ambientColor"),
				0.5f, 0.1f, 0.9f, 1.0f);
		gl2.glUniform4f(gl2.glGetUniformLocation(program, "diffuseColor"), 1f,
				1f, 1f, 1f);
		gl2.glUniform4f(gl2.glGetUniformLocation(program, "specularColor"),
				1.0f, 1.0f, 1.0f, 1.0f);
		gl2.glUniform4f(gl2.glGetUniformLocation(program, "lightColor"), 1.0f,
				1.0f, 1.0f, 1.0f);
		gl2.glUniform4f(gl2.glGetUniformLocation(program, "lightPosition"),
				0.0f, 5.0f, 2.0f, 1.0f);
		gl2.glUniform4f(gl2.glGetUniformLocation(program, "ambientLight"), 1f,
				1f, 1f, 1.0f);
		gl2.glUniform1f(gl2.glGetUniformLocation(program,
				"ambientReflectionCoefficient"), 0.2f);
		gl2.glUniform1f(gl2.glGetUniformLocation(program,
				"diffuseReflectionCoefficient"), 0.7f);
		gl2.glUniform1f(gl2.glGetUniformLocation(program,
				"specularReflectionCoefficient"), 1.0f);
		gl2.glUniform1f(gl2.glGetUniformLocation(program, "specularExponent"),
				10.0f);
		// for sphere
		if (num == 0) {
			gl2.glUniform1f(gl2.glGetUniformLocation(program, "flag"), 1f);
			gl2.glUniform4f(gl2.glGetUniformLocation(program, "ambientColor"),
					1f, 1f, 1f, 1.0f);
		}
		// cyclinder
		else if (num == 1) {
			gl2.glUniform1f(gl2.glGetUniformLocation(program, "flag"), 0f);
			gl2.glUniform4f(gl2.glGetUniformLocation(program, "ambientColor"),
					0f, 1f, 0f, 1.0f);
		}
		// cone
		else if (num == 2) {
			gl2.glUniform1f(gl2.glGetUniformLocation(program, "flag"), 0f);
			gl2.glUniform4f(gl2.glGetUniformLocation(program, "ambientColor"),
					0f, 1f, 0f, 1.0f);
		}
		// cube
		else if (num == 3) {
			gl2.glUniform1f(gl2.glGetUniformLocation(program, "flag"), 0f);
			gl2.glUniform4f(gl2.glGetUniformLocation(program, "ambientColor"),
					0.4f, 0.2f, 0f, 1.0f);
		}
	}

}
