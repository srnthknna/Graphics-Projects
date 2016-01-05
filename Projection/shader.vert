#version 120

uniform vec3 scalefactor;
uniform vec3 translatefactor;
uniform vec3 rotationfactor;

uniform vec3 lookat;
uniform vec3 eyepoint;
uniform vec3 up;

uniform float left;
uniform float right;
uniform float top;
uniform float bottom;
uniform float near;
uniform float far;

uniform float flag;

attribute vec4 vPosition;


void main()
{
//angle for finding cos and sin angle
float anglex=radians(rotationfactor.x);
float angley=radians(rotationfactor.y);
float anglez=radians(rotationfactor.z);

//rotation matrix for x axis
mat4 xrotation = mat4(1,0,0,0,
					  0,cos(anglex),-sin(anglex),0,
					  0,sin(anglex),cos(anglex),0,
					  0,0,0,1);				  
xrotation=transpose(xrotation);
//rotation matrix for y axis	
mat4 yrotation = mat4(cos(angley),0,sin(angley),0,
					  0,1,0,0,
					  -sin(angley),0,cos(angley),0,
					  0,0,0,1);
yrotation=transpose(yrotation);
//rotation matrix for z axis
mat4 zrotation = mat4(cos(anglez),-sin(anglez),0,0,
					  sin(anglez),cos(anglez),0,0,
					  0,0,1,0,
					  0,0,0,1);
zrotation=transpose(zrotation);
//scale matrix
mat4 scale = mat4(scalefactor.x,0,0,0,
				  0,scalefactor.y,0,0,
				  0,0,scalefactor.z,0,
				  0,0,0,1);
scale =transpose(scale);
//translation matrix
mat4 translate = mat4(1,0,0,translatefactor.x,
					  0,1,0,translatefactor.y,
					  0,0,1,translatefactor.z,
					  0,0,0,1);
translate = transpose(translate);
//variables for view transformation matrix
vec3 n=normalize((eyepoint-lookat));	
vec3 u=normalize(cross(up,n));
vec3 v=cross(n,u);				  
					  
mat4 viewtransformation = mat4(u.x,u.y,u.z,-dot(u,eyepoint),
							   v.x,v.y,v.z,-dot(v,eyepoint),
							   n.x,n.y,n.z,-dot(n,eyepoint),
							   0,0,0,1);
viewtransformation = transpose(viewtransformation);	
//matrix for orthographic projection					   
mat4 orthoprojection=mat4(2/(right-left),0,0,-(right+left)/(right-left),
						  0,2/(top-bottom),0,-(top+bottom)/(top-bottom),
						  0,0,-2/(far-near),-(far+near)/(far-near),
						  0,0,0,1);
orthoprojection = transpose(orthoprojection);
//matrix for frustum projection						  
mat4 frustumprojection=mat4((2*near/(right-left)),0,(right+left)/(right-left),0,
								0,(2*near/(top-bottom)),(top+bottom)/(top-bottom),0,
								0,0,-(far+near)/(far-near),-(2*far*near/(far-near)),
								0,0,-1,0);
frustumprojection =transpose(frustumprojection);			  

//transformation matrix handling rotation scaling and translation
mat4 transformation = translate*xrotation*yrotation*zrotation*scale;
//if orthographic projection
if(flag==2)
{
gl_Position =orthoprojection *viewtransformation*transformation*vPosition;
}
//if frustum projection
else
{
gl_Position = frustumprojection*viewtransformation*transformation*vPosition;
}

    
}
