#version 120

// Phong shading fragment shader
varying vec3 L;
varying vec3 V;
varying vec3 N;
varying vec3 R;

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
	
	//	Compute	terms	in	the	illumination	equation	
	vec4 ambient = ambientLight  * ambientReflectionCoefficient * ambientColor;
	vec4 diffuse = lightColor * diffuseColor * diffuseReflectionCoefficient * max(0,dot(L,N));
	vec4 specular = lightColor * specularReflectionCoefficient * specularColor * pow(max(0,dot(V,R)),specularExponent);
	
	
	
	if(dot(L,N)<=0||dot(V,R)<=0)
	specular=vec4(0,0,0,0);
	
	//returning all three shading types
    gl_FragColor = ambient+diffuse+specular;
}
