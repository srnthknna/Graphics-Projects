#version 120
// Gouraud shading fragment shader
//Variables for texture mapping
varying vec2 uvvariable;
uniform float flag;
uniform sampler2D texturevar;


varying	vec4	color;	
void main()
{
	//check to perform shading or texture mapping
    if(flag==0) {
		gl_FragColor = color;
	}
	else {
    	gl_FragColor =  texture2D(texturevar,uvvariable);
    }
}
