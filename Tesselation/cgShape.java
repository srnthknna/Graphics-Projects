//
//  cgShape.java
//
//  Class that includes routines for tessellating a number of basic shapes.
//
//  Students are to supply their implementations for the functions in
//  this file using the function "addTriangle()" to do the tessellation.
//

import java.awt.*;
import java.lang.Character.Subset;
import java.nio.*;
import java.awt.event.*;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;

import java.io.*;


public class cgShape extends simpleShape
{
    /**
     * constructor
     */
    public cgShape()
    {
    }

    /**
	 * makeCube - Create a unit cube, centered at the origin, with a given
	 * number of subdivisions in each direction on each face.
	 *
	 * @param subdivision
	 *            - number of equal subdivisons to be made in each direction
	 *            along each face
	 *
	 *            Can only use calls to addTriangle()
	 */
	public void makeCube (int subdivisions)
    {
        if( subdivisions < 1 )
            subdivisions = 1;
        //variable for increment of the vertices with subdivisions
		float addons=(float)(1.0/subdivisions);
		//variable constant for negative and positive ends
		float a=(float) -0.5;
		float b=(float) 0.5;
		float sidea=-0.5f;
		float sideb=-0.5f;
		//loop to draw 6 phases two each for x-y,y-z and x-z plane.
		for(int k=0;k<subdivisions;k+=1)
        {
			float i=sidea+((float)k/subdivisions);
			for(int l=0;l<subdivisions;l+=1)
            {
        		
        		float j=sideb+((float)l/subdivisions);
        		//x-y plane negative z
        		addTriangle(i, j, a,i, j+addons,a,  i+addons, j, a);
        		addTriangle( i+addons, j, a,i, j+addons,a,i+addons,j+addons, a);
        		//x-y plane positive z
        		addTriangle( i, j+addons,b,i, j, b, i+addons, j, b);
        		addTriangle( i, j+addons,b,i+addons, j, b,i+addons,j+addons, b);
        		//y-z plane negative x
        		addTriangle(a,i, j, a, i, j+addons, a, i+addons, j);
        		addTriangle(a,i+addons, j,a, i, j+addons,a,i+addons,j+addons);
        		//y-z plane positive x
        		addTriangle(b,i, j,  b, i+addons, j,b, i, j+addons);
        		addTriangle(b,i+addons,j+addons,b, i, j+addons,b,i+addons, j);
        		//x-z plane negative y
        		addTriangle(i,a, j,   i+addons,a, j,i,a, j+addons);
        		addTriangle(i+addons,a,j+addons, i,a, j+addons,i+addons,a, j);
        		//x-z plane positive y
        		addTriangle(i,b, j,  i,b, j+addons,  i+addons,b, j);
        		addTriangle(i+addons,b, j, i,b, j+addons,i+addons,b,j+addons);
        		
        	
        		
            }
        }

        
    }

   
    /**
     * makeCylinder - Create polygons for a cylinder with unit height, centered
     * at the origin, with separate number of radial subdivisions and height
     * subdivisions.
     *
     * @param radius - Radius of the base of the cylinder
     * @param radialDivision - number of subdivisions on the radial base
     * @param heightDivisions - number of subdivisions along the height
     *
     * Can only use calls to addTriangle()
     */
    public void makeCylinder (float radius, int radialDivisions, int heightDivisions)
    {
        if( radialDivisions < 3 )
            radialDivisions = 3;

        if( heightDivisions < 1 )
            heightDivisions = 1;
        //degrees of radial increment for each subdivision
        float radincr=(float) (360/radialDivisions);
        //increment factor for all the divisions along height
        float hincr=(float) (1.0f/heightDivisions);
        float height=-0.5f;
        //loop to draw the cylinder top and bottom circle and the curved surface area
        for(int k=0;k<radialDivisions;k+=1)
    	{
    		float i=(float) (((float)k/radialDivisions)*(360));
        	//first points x and z coordinate on the top circle 
        	float pointx=(float) (radius*Math.cos(Math.toRadians(i)));
        	float pointz=(float) (radius*Math.sin(Math.toRadians(i)));
        	//second points x and z coordinate on the top circle
        	float pointx1=(float) (radius*Math.cos(Math.toRadians(i+radincr)));
        	float pointz1=(float) (radius*Math.sin(Math.toRadians(i+radincr)));
        	//loop to print all the triangles with two points on the top circle to respective two points at the bottom
        	for(int l=0;l<heightDivisions;l+=1)
            {
        		
        		float j=height+((float)l/heightDivisions);
        		addTriangle(pointx,j,pointz,pointx,j+hincr,pointz,pointx1,j,pointz1);
        		addTriangle(pointx1,j,pointz1,pointx,j+hincr,pointz,pointx1,j+hincr,pointz1);
        	}
        	//top circle in the cylinder
        	addTriangle(pointx,0.5f,pointz,0.0f,0.5f,0.0f,pointx1,0.5f,pointz1);
        	//bottom circle in the cylinder
        	addTriangle(0.0f,-0.5f,0.0f,pointx,-0.5f,pointz,pointx1,-0.5f,pointz1);       	
        }
      
    }

    /**
     * makeCone - Create polygons for a cone with unit height, centered at the
     * origin, with separate number of radial subdivisions and height
     * subdivisions.
     *
     * @param radius - Radius of the base of the cone
     * @param radialDivision - number of subdivisions on the radial base
     * @param heightDivisions - number of subdivisions along the height
     *
     * Can only use calls to addTriangle()
     */
    public void makeCone (float radius, int radialDivisions, int heightDivisions)
    {
        if( radialDivisions < 3 )
            radialDivisions = 3;

        if( heightDivisions < 1 )
            heightDivisions = 1;
        //increment factor along the radius 
        float radincr=(float) (360/radialDivisions);
        //increment factor of  the radius along height
        float radiusincr=(float)(radius/heightDivisions);
        //increment factor for height
        float hincr=(float) (1.0f/heightDivisions);  
        //loop to draw bottom circle
      
       for(int j=0;j<radialDivisions;j+=1)
        {
        	float i=(float) (((float)j/radialDivisions)*(360));
        	//first points x and z coordinate on the bottom circle
        	float pointx=(float) (radius*Math.cos(Math.toRadians(i)));
        	float pointz=(float) (radius*Math.sin(Math.toRadians(i))); 
        	//second points x and z coordinate on the  bottom circle
        	float pointx1=(float) (radius*Math.cos(Math.toRadians(i+radincr)));
        	float pointz1=(float) (radius*Math.sin(Math.toRadians(i+radincr)));
        	//to join the points with known points
        	addTriangle(0.0f,-0.5f,0.0f,pointx,-0.5f,pointz,pointx1,-0.5f,pointz1);
       	
        }
        //variable to keep track of radius of the circles parallel to base
        float rad=radius;
    	float rad1=radius;
    	//to draw all the triangle on the curved surface area
    	float height=-0.5f;
    	  float j=0;
    	for(int l=0;l<heightDivisions-1;l+=1)
        {
    		
    		j=height+((float)l/heightDivisions);
    		
    		//swapping the radius for next iterations
        	rad=rad1;
        	rad1=rad1-radiusincr;
        	//if radius is 0 then stop
        	if(rad1 < 0f) {
        		break;
        	}
        	//loop to draw curved surface long height
        	for(int k=0;k<radialDivisions;k+=1)
        	{
        		float i=(float) (((float)k/radialDivisions)*(360));
        	//two points on the circle above the other parallel to base
        	float pointx4=(float) (rad1*Math.cos(Math.toRadians(i)));
        	float pointz4=(float) (rad1*Math.sin(Math.toRadians(i)));
        	
        	float pointx3=(float) (rad1*Math.cos(Math.toRadians(i+radincr)));
        	float pointz3=(float) (rad1*Math.sin(Math.toRadians(i+radincr)));
        	//two points on the circle below the other parallel to base
        	float pointx2=(float) (rad*Math.cos(Math.toRadians(i)));
        	float pointz2=(float) (rad*Math.sin(Math.toRadians(i)));
        	
        	float pointx1=(float) (rad*Math.cos(Math.toRadians(i+radincr)));
        	float pointz1=(float) (rad*Math.sin(Math.toRadians(i+radincr)));
        	//add triangles to draw triangles over the curved surface area.
        	addTriangle(pointx3,j+hincr,pointz3,pointx1,j,pointz1,pointx2,j,pointz2);
        	addTriangle(pointx3,j+hincr,pointz3,pointx2,j,pointz2,pointx4,j+hincr,pointz4);
        	
        	
        }
        }
    	//to draw the upper cone
        for(int k=0;k<radialDivisions;k+=1)
        {
        	float i=(float) (((float)k/radialDivisions)*(360));
        	float radius1=rad;
        	//first points x and z coordinate on the bottom circle
        	float pointx=(float) (radius1*Math.cos(Math.toRadians(i)));
        	float pointz=(float) (radius1*Math.sin(Math.toRadians(i))); 
        	//second points x and z coordinate on the  bottom circle
        	float pointx1=(float) (radius1*Math.cos(Math.toRadians(i+radincr)));
        	float pointz1=(float) (radius1*Math.sin(Math.toRadians(i+radincr)));
        	//to join the points with known points
        	addTriangle(0.0f,0.5f,0.0f,pointx,j,pointz,pointx1,j,pointz1);
       	
        }
        
    }

    /**
     * makeSphere - Create sphere of a given radius, centered at the origin,
     * using spherical coordinates with separate number of thetha and
     * phi subdivisions.
     *
     * @param radius - Radius of the sphere
     * @param slides - number of subdivisions in the theta direction
     * @param stacks - Number of subdivisions in the phi direction.
     *
     * Can only use calls to addTriangle
     */
    public void makeSphere (float radius, int slices, int stacks)
    {
        if( slices < 3 )
            slices = 3;

        if( stacks < 3 )
            stacks = 3;
        //loops to draw sphere
        for(int i=0;i<stacks;i++)
        {
        	//angle from center to the longitudes
        	float angle = (float) Math.toRadians(((float)i/stacks)*180);
        	float angle1 = (float)Math.toRadians(((float)(i+1)/stacks)*180);
        	for(int j=0;j<slices;j++)
        	{
        		//angle from center to the latitudes
        		float angle2 = (float)Math.toRadians(((float)j/slices)*(360));
            	float angle3 = (float)Math.toRadians(((float)(j+1)/slices)*(360));
            	//two points on the first loop along the sphere 
            	float pointx=(float) (radius*(Math.sin(angle))*(Math.cos(angle2)));
            	float pointy=(float) (radius*(Math.cos(angle)));
            	float pointz=(float) (radius*(Math.sin(angle))*(Math.sin(angle2)));
            	
            	float pointx1=(float) (radius*(Math.sin(angle))*(Math.cos(angle3)));
            	float pointy1=(float) (radius*(Math.cos(angle)));
            	float pointz1=(float) (radius*(Math.sin(angle))*(Math.sin(angle3)));
            	//two points on the second loop along the sphere
            	float pointx2=(float) (radius*(Math.sin(angle1))*(Math.cos(angle2)));
            	float pointy2=(float) (radius*(Math.cos(angle1)));
            	float pointz2=(float) (radius*(Math.sin(angle1))*(Math.sin(angle2)));
            	
            	float pointx3=(float) (radius*(Math.sin(angle1))*(Math.cos(angle3)));
            	float pointy3=(float) (radius*(Math.cos(angle1)));
            	float pointz3=(float) (radius*(Math.sin(angle1))*(Math.sin(angle3)));
            	//add triangles to draw the sphere
            	addTriangle(pointx,pointy,pointz,pointx2,pointy2,pointz2,pointx1,pointy1,pointz1);
            	addTriangle(pointx1,pointy1,pointz1,pointx2,pointy2,pointz2,pointx3,pointy3,pointz3);
        	}
        }
        	
        
    }

}
