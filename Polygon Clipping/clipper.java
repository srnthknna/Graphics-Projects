//
//  Clipper.java
//
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

/**
 * Object for performing clipping
 *
 */

public class clipper {

	// output x y list to store values after left clipping
	float out1x[] = new float[50];
	float out1y[] = new float[50];
	// output x y list to store values after right clipping
	float out2x[] = new float[50];
	float out2y[] = new float[50];
	// output x y list to store values after top clipping
	float out3x[] = new float[50];
	float out3y[] = new float[50];
	// variable to store x and y intersect values
	float intersectx = 0;
	float intersecty = 0;

	/**
	 * clipPolygon
	 *
	 * Clip the polygon with vertex count in and vertices inx/iny against the
	 * rectangular clipping region specified by lower-left corner (x0,y0) and
	 * upper-right corner (x1,y1). The resulting vertices are placed in
	 * outx/outy.
	 *
	 * The routine should return the the vertex count of the polygon resulting
	 * from the clipping.
	 *
	 * @param in
	 *            the number of vertices in the polygon to be clipped
	 * @param inx
	 *            - x coords of vertices of polygon to be clipped.
	 * @param iny
	 *            - y coords of vertices of polygon to be clipped.
	 * @param outx
	 *            - x coords of vertices of polygon resulting after clipping.
	 * @param outy
	 *            - y coords of vertices of polygon resulting after clipping.
	 * @param x0
	 *            - x coord of lower left of clipping rectangle.
	 * @param y0
	 *            - y coord of lower left of clipping rectangle.
	 * @param x1
	 *            - x coord of upper right of clipping rectangle.
	 * @param y1
	 *            - y coord of upper right of clipping rectangle.
	 *
	 * @return number of vertices in the polygon resulting after clipping
	 *
	 */

	public int clipPolygon(int in, float inx[], float iny[], float outx[],
			float outy[], float x0, float y0, float x1, float y1) {
		// variables to store vertex count after clipping
		int out1n, out2n, out3n, out4n;
		// left clipping
		out1n = SHPC(in, inx, iny, out1x, out1y, x0, y0, x0, y1, 1);
		// reight clipping
		out2n = SHPC(out1n, out1x, out1y, out2x, out2y, x1, y0, x1, y1, 2);
		// top clipping
		out3n = SHPC(out2n, out2x, out2y, out3x, out3y, x0, y1, x1, y1, 3);
		// bottom clipping
		out4n = SHPC(out3n, out3x, out3y, outx, outy, x0, y0, x1, y0, 4);
		return out4n; // should return number of vertices in clipped poly.
	}

	/**
	 * method to set the clipping value for checking points
	 * 
	 * @param x0
	 *            -x coordinate of clipping edge
	 * @param y0
	 *            -y coordinate of clipping edge
	 * @param x1
	 *            -x coordinate of clipping edge
	 * @param y1
	 *            -y coordinate of clipping edge
	 * @param side
	 *            -flag for boundary edge
	 * @return
	 */
	public float setchecker(float x0, float y0, float x1, float y1, int side) {
		float checker = 0;
		// for left boundary
		if (side == 1) {
			checker = x0;
		}
		// for right boundary
		if (side == 2) {
			checker = x1;
		}
		// for top boundary
		if (side == 3) {
			checker = y1;
		}
		// for bottom boundary
		if (side == 4) {
			checker = y0;
		}
		// checker for clipping
		return checker;
	}

	/**
	 * method to check if the points are inside or outside the clipping edge
	 * 
	 * @param x0
	 *            -x coordinate of the point to be checked
	 * @param y0
	 *            -y coordinate of the point to be checked
	 * @param checker
	 *            -checker value for deciding
	 * @param side
	 *            -flag of boundary that is clipped
	 * @return-true if point is inside else false
	 */
	public boolean isinside(float x0, float y0, float checker, int side) {
		// for left clipping
		if (side == 1) {
			if (x0 > checker)
				return true;
			else
				return false;
		}
		// for right clipping
		else if (side == 2) {
			if (x0 < checker)
				return true;
			else
				return false;
		}
		// for top clipping
		else if (side == 3) {
			if (y0 < checker)
				return true;
			else
				return false;
		}
		// for bottom clipping
		else if (side == 4) {
			if (y0 > checker)
				return true;
			else
				return false;
		}

		return false;
	}

	/**
	 * method to find the intersect point of line with clipping edge
	 * 
	 * @param x0
	 *            -x coordinate of line
	 * @param y0
	 *            -y coordinate of line
	 * @param x1
	 *            -x coordinate of line
	 * @param y1
	 *            -y coordinate of line
	 * @param checker
	 *            -clipping value for the boundary
	 * @param side
	 *            -flag for clipping edge
	 */
	public void intersect(float x0, float y0, float x1, float y1,
			float checker, int side) {
		// for left and right clipping
		if (side == 1 || side == 2) {
			// x and y coordinates of point formed with intersection
			intersectx = checker;
			intersecty = y1 + ((y1 - y0) * (checker - x1) / (x1 - x0));
		}
		// for top and bottom clipping
		else if (side == 3 || side == 4) {
			// x and y coordinates of point formed with intersection
			intersectx = x1 + ((checker - y1) * (x1 - x0) / (y1 - y0));
			intersecty = checker;
		}
	}

	/**
	 * method to implement Sutherland-Hodgman polygon clipping
	 * 
	 * @param in
	 *            -number of input vertices
	 * @param inx
	 *            - input x coordinates of vertices
	 * @param iny
	 *            - input y coordinates of vertices
	 * @param outx
	 *            - output x coordinates of vertices
	 * @param outy
	 *            - output y coordinates of vertices
	 * @param x0
	 *            - x coordinate of clipping boundary
	 * @param y0
	 *            - y coordinate of clipping boundary
	 * @param x1
	 *            - x coordinate of clipping boundary
	 * @param y1
	 *            - y coordinate of clipping boundary
	 * @param side
	 *            -flag for clipping coundary
	 * @return-number of output vertices after clipping
	 */
	public int SHPC(int in, float inx[], float iny[], float outx[],
			float outy[], float x0, float y0, float x1, float y1, int side) {
		// variable to store output variables after clipping
		int vertexcount = 0;
		// variable to set index for next point
		int y = 0;
		// to get the clipping value for the clipping edge
		float checker = setchecker(x0, y0, x1, y1, side);
		// for in input points
		for (int i = 0; i < in; i++) {
			// set index of next point
			if (i != in - 1) {
				y = i + 1;
			} else {
				y = 0;
			}
			// if the first point is inside the clipping edge
			if (isinside(inx[i], iny[i], checker, side)) {
				// if the second point is inside the clipping edge
				if (isinside(inx[y], iny[y], checker, side)) {
					// add second point to the output point list
					outx[vertexcount] = inx[y];
					outy[vertexcount] = iny[y];
					vertexcount++;
				}
				// if the second point is outside the clipping edge
				else {
					// add intersection of points with clipping edge
					intersect(inx[i], iny[i], inx[y], iny[y], checker, side);
					outx[vertexcount] = intersectx;
					outy[vertexcount] = intersecty;
					vertexcount++;
				}
			}
			// if first point is outside the clipping edge
			else {
				if (isinside(inx[y], iny[y], checker, side)) {
					intersect(inx[i], iny[i], inx[y], iny[y], checker, side);
					// add the intersection of the points with clipping edge and
					// second point
					outx[vertexcount] = intersectx;
					outy[vertexcount] = intersecty;
					vertexcount++;
					outx[vertexcount] = inx[y];
					outy[vertexcount] = iny[y];
					vertexcount++;
				}
			}
		}
		// return number of output vertices
		return vertexcount;
	}
}
