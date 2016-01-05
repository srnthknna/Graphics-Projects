//
// lightingParams.java
//
// Simple class for setting up the viewing and projection transforms
// for the Shading Assignment.
//
// Students are to complete this class.
//

import javax.media.opengl.*;
import javax.media.opengl.fixedfunc.*; 

public class lightingParams
{
    // Add any global class variables you need here.

    /**
     * constructor
     */
    public lightingParams()
    {
      
    }
    /**
     * This functions sets up the lighting, material, and shading parameters
     * for the Phong shader.
     *
     * You will need to write this function, and maintain all of the values
     * needed to be sent to the vertex shader.
     *
     * @param program - The ID of an OpenGL (GLSL) shader program to which
     * parameter values are to be sent
     *
     * @param gl2 - GL2 object on which all OpenGL calls are to be made
     *
     */
    public void setUpPhong (int program, GL2 gl2)
    {
    	//To initialize the variables in the vertex and fragment shader 
    	gl2.glUniform4f(gl2.glGetUniformLocation( program, "ambientColor" ),0.5f,0.1f,0.9f,1.0f);
    	gl2.glUniform4f(gl2.glGetUniformLocation( program, "diffuseColor" ),0.89f,0.0f,0.0f,1.0f);
    	gl2.glUniform4f(gl2.glGetUniformLocation( program, "specularColor" ),1.0f,1.0f,1.0f,1.0f);
    	gl2.glUniform4f(gl2.glGetUniformLocation( program, "lightColor" ),1.0f,1.0f,0.0f,1.0f);
    	gl2.glUniform4f(gl2.glGetUniformLocation( program, "lightPosition" ),0.0f,5.0f,2.0f,1.0f);
    	gl2.glUniform4f(gl2.glGetUniformLocation( program, "ambientLight" ),0.5f,0.5f,0.5f,1.0f);	
    	gl2.glUniform1f(gl2.glGetUniformLocation( program, "ambientReflectionCoefficient" ),0.5f);
    	gl2.glUniform1f(gl2.glGetUniformLocation( program, "diffuseReflectionCoefficient" ),0.7f);
    	gl2.glUniform1f(gl2.glGetUniformLocation( program, "specularReflectionCoefficient" ),1.0f);
    	gl2.glUniform1f(gl2.glGetUniformLocation( program, "specularExponent" ),10.0f);
    }
}
