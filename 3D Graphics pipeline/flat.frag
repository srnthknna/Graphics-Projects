#version 120
// Flat shading fragment shader
//Variables for texture mapping
varying vec2 uvvariable;
uniform float flag;
uniform sampler2D texturevar;


varying vec3 newLightPosition;
varying vec3 newVPos;
varying vec3 newNormal;

uniform vec4 ambientColor;
uniform vec4 diffuseColor;
uniform vec4 specularColor;
uniform vec4 lightColor;
uniform vec4 ambientLight;

uniform float ambientReflectionCoefficient;
uniform float diffuseReflectionCoefficient;
uniform float specularReflectionCoefficient;
uniform float specularExponent;

void main()
{
	//	Transform	vertex		position	into	eye	coordinates	
	vec3 L = normalize(newLightPosition-newVPos);
	vec3 V = normalize(-newVPos);
	vec3 N = normalize(newNormal);
	vec3 R = normalize(dot(L,2*N)*N-L);
	
	//Compute	terms	in	the	illumination	equation	
	vec4 ambient = ambientLight  * ambientReflectionCoefficient * ambientColor;
	vec4 diffuse = lightColor * diffuseColor * diffuseReflectionCoefficient * max(0,dot(L,N));
	vec4 specular= lightColor * specularReflectionCoefficient * specularColor * pow(max(0,dot(V,R)),specularExponent);

	
	if(dot(L,N)<=0||dot(V,R)<=0)
	specular=vec4(0,0,0,0);
	//check to perform shading or texture mapping
	if(flag==0) {
		gl_FragColor = (ambient+diffuse+specular);
	}
	else {
    	gl_FragColor =  texture2D(texturevar,uvvariable);
    }
}
