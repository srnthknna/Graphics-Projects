//
//  cgCanvas.java
//
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 Rochester Institute of Technology. All rights reserved.
//

/**
 * This is a simple canvas class for adding functionality for the
 * 2D portion of Computer Graphics.
 *
 */

import Jama.*;

import java.util.*;

//class to store the elements of buckets of each edge
class bucket {

	int ymax; // y maximum of the two points of the edge
	int xofymin; // x value of the point with minimum y value among the two
	int dx; // difference of x values of the two points
	int dy; // difference of y values of the two points
	int sum; // variable to handle the fractional part while adding slope

};

// class bucketlist to store list of buckets in edgetable
class bucketlist implements Comparator<bucket> {
	// list of buckets to store edges in edgetable
	List<bucket> b;

	/**
	 * constructor to allocate memory for the list
	 */
	bucketlist() {
		b = new ArrayList<bucket>();
	}

	/**
	 * method to remove a bucket from the list
	 * 
	 * @param c
	 *            - the bucket to be removed from the list
	 */
	public void remove(bucket c) {
		b.remove(c);
	}

	/**
	 * method to add a bucket to the list
	 * 
	 * @param c
	 *            - the bucket to be added to the list
	 */
	public void add(bucket c) {
		b.add(c);
	}

	/**
	 * method to addall the buckets of a list to another bucket list
	 * 
	 * @param h
	 *            - bucket list that is to be added
	 */
	public void addall(bucketlist h) {
		b.addAll(h.b);
	}

	/**
	 * comparator to sort the buckets according to x values in them and their
	 * slope
	 * 
	 * @param o1
	 *            - first bucket that is to be compared
	 * @param o2
	 *            - second bucket that is to be compared
	 */
	public int compare(bucket o1, bucket o2) {
		// if the first buckets x value is greater than the second bucket
		if (o1.xofymin > o2.xofymin) {
			return 1;
		}
		// if the first buckets x value is equal to that of second bucket
		else if (o1.xofymin == o2.xofymin) {
			// if the slope of the first bucket is greater than second bucket
			if (((double) o1.dy / o1.dx) > ((double) o2.dy / o2.dx)) {
				return 1;
			}
			// if the slope of the second bucket is less than second bucket
			else
				return -1;
		}
		// if the first buckets x value is less than the second bucket
		else {
			return -1;
		}
	}

};

/**
 * To store the polygons vertices
 * 
 * @author Srnthknna
 *
 */
class polygon {
	// variables to store x y coordinates
	float x[], y[];
	// variable to store number of vertices
	int n;

	/**
	 * constructor to initialize the data members
	 */
	polygon() {
		x = new float[20];
		y = new float[20];
		n = 0;

	}
};

public class cgCanvas extends simpleCanvas {
	// polygon id initialization
	int id = 0;
	// hasmap to represent polygon table
	HashMap<Integer, polygon> polygon = new HashMap<Integer, polygon>();
	// hashmap to represent the edgetable
	HashMap edgetable = new HashMap<Integer, bucketlist>();
	// list of bukets to represent active edges list
	List<bucket> activeedges = new ArrayList<bucket>();
	// matrix array to store identity matrix
	double[][] transmatrix = new double[3][3];
	Matrix a;
	Matrix identity;
	// variables to store clip and view port coordinates
	float clipwidth, clipheight, viewheight, viewwidth, viewx, viewy;
	float x = 0, y = 0, x1 = 500, y1 = 500;
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
	 * method to set identity matrix
	 */
	public void settransmatrix() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				a.set(i, j, 0);
		a.set(0, 0, 1);
		a.set(1, 1, 1);
		a.set(2, 2, 1);
	}

	/**
	 * Constructor
	 *
	 * @param w
	 *            width of canvas
	 * @param h
	 *            height of canvas
	 */
	cgCanvas(int w, int h) {
		super(w + 1, h + 1);

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				transmatrix[i][j] = 0;

		a = new Matrix(transmatrix);
		identity = new Matrix(transmatrix);
		identity.set(0, 0, 1);
		identity.set(1, 1, 1);
		identity.set(2, 2, 1);
		settransmatrix();
	}

	/**
	 * addPoly - Adds and stores a polygon to the canvas. Note that this method
	 * does not draw the polygon, but merely stores it for later draw. Drawing
	 * is initiated by the draw() method.
	 *
	 * Returns a unique integer id for the polygon.
	 *
	 * @param x
	 *            - Array of x coords of the vertices of the polygon to be added
	 * @param y
	 *            - Array of y coords of the vertices of the polygin to be added
	 * @param n
	 *            - Number of verticies in polygon
	 *
	 * @return a unique integer identifier for the polygon
	 */
	public int addPoly(float x[], float y[], int n) {
		polygon p = new polygon();
		// create id for the polygon
		int polygonid = id;
		// assign vertices and number of vertices
		for (int i = 0; i < n; i++) {
			p.x[i] = (int) x[i];
			p.y[i] = (int) y[i];
			p.n = n;

		}
		// add polygon to hashmap
		polygon.put(polygonid, p);
		id++;
		// return polygon id
		return polygonid;
	}

	/**
	 * method to change the coordinates from world to screen coordinates
	 * 
	 * @param n
	 *            -number of input vertices
	 * @param inx
	 *            -input array for x
	 * @param iny
	 *            -input array for y
	 * @param outx
	 *            -output array for x
	 * @param outy
	 *            -output array for y
	 */
	public void translation(int n, float[] inx, float[] iny, float[] outx,
			float[] outy) {
		for (int i = 0; i < n; i++) {
			// change world coordinates with transformation
			outx[i] = ((float) (a.get(0, 0)) * inx[i])
					+ ((float) (a.get(0, 1)) * iny[i]) + (float) (a.get(0, 2));
			outy[i] = ((float) (a.get(1, 0)) * inx[i])
					+ ((float) (a.get(1, 1)) * iny[i]) + (float) (a.get(1, 2));
			// change world coordinates to screen coordinates
			outx[i] = viewx + (((outx[i] - x) / (x1 - x)) * (viewwidth));
			outy[i] = viewy + (((outy[i] - y) / (y1 - y)) * (viewheight));
		}

	}

	/**
	 * drawPoly - Draw the polygon with the given id. Should draw the polygon
	 * after applying the current transformation on the vertices of the polygon.
	 *
	 * @param polyID
	 *            - the ID of the polygon to be drawn
	 */
	public void drawPoly(int polyID) {

		polygon p = new polygon();
		p = polygon.get(polyID);
		float outx[] = new float[20];
		float outy[] = new float[20];
		float out1x[] = new float[20];
		float out1y[] = new float[20];
		float out2x[] = new float[20];
		float out2y[] = new float[20];
		int n = 0;
		int n1 = 0;
		// clip polygon to world coordinates
		n = clipPolygon(p.n, p.x, p.y, outx, outy, x, y, x1, y1);

		this.activeedges.clear();
		this.edgetable.clear();
		// translate the polygon to screen coordinates
		translation(n, outx, outy, out1x, out1y);
		// to create the edgetable
		inithashtable(n, out1x, out1y);

		// to get the value of greatest and smallest y value in the polygon
		// coordinates
		int ymax = getymax(n, out1y);
		int ymin = getymin(n, out1y);

		// to fill all the scanlines from smallest to greatest y value in
		// polygon
		for (int i = ymin; i <= ymax; i++) {
			bucketlist b = new bucketlist();
			// discard the unwanted edges from the active edge list
			this.discard(i);
			// update the x values of the buckets in active edge list
			this.updatex();
			// add new edges to the active edge list
			this.addedges(i);
			// sort the buckets in the active edgelist
			this.activeedges.sort(b);
			// draw scanline between the edges
			this.fillscanline(i);
		}
		// clear the activeedgelist and edgetable after completion
		this.activeedges.clear();
		this.edgetable.clear();
		settransmatrix();
	}

	/**
	 * clearTransform - Set the current transformation to the identity matrix.
	 */
	public void clearTransform() {
		settransmatrix();
	}

	/**
	 * translate - Add a translation to the current transformation by
	 * pre-multiplying the appropriate translation matrix to the current
	 * transformation matrix.
	 *
	 * @param x
	 *            - Amount of translation in x
	 * @param y
	 *            - Amount of translation in y
	 */
	public void translate(float x, float y) {
		double[][] temptranslate = new double[3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				temptranslate[i][j] = 0;
		temptranslate[0][0] = temptranslate[1][1] = temptranslate[2][2] = 1;
		temptranslate[0][2] = x;
		temptranslate[1][2] = y;
		Matrix b = new Matrix(temptranslate);
		a = b.times(a);

	}

	/**
	 * rotate - Add a rotation to the current transformation by pre-multiplying
	 * the appropriate rotation matrix to the current transformation matrix.
	 *
	 * @param degrees
	 *            - Amount of rotation in degrees
	 */
	public void rotate(float degrees) {
		degrees = (float) (Math.toRadians(degrees));
		double[][] temptranslate = new double[3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				temptranslate[i][j] = 0;
		temptranslate[0][0] = temptranslate[1][1] = Math.cos(degrees);
		temptranslate[0][1] = (-1) * Math.sin(degrees);
		temptranslate[1][0] = Math.sin(degrees);
		temptranslate[2][2] = 1;
		Matrix b = new Matrix(temptranslate);
		a = b.times(a);

	}

	/**
	 * scale - Add a scale to the current transformation by pre-multiplying the
	 * appropriate scaling matrix to the current transformation matrix.
	 *
	 * @param x
	 *            - Amount of scaling in x
	 * @param y
	 *            - Amount of scaling in y
	 */
	public void scale(float x, float y) {
		double[][] temptranslate = new double[3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				temptranslate[i][j] = 0;
		temptranslate[2][2] = 1;
		temptranslate[0][0] = x;
		temptranslate[1][1] = y;
		Matrix b = new Matrix(temptranslate);
		a = b.times(a);
	}

	/**
	 * setClipWindow - defines the clip window
	 *
	 * @param bottom
	 *            - y coord of bottom edge of clip window (in world coords)
	 * @param top
	 *            - y coord of top edge of clip window (in world coords)
	 * @param left
	 *            - x coord of left edge of clip window (in world coords)
	 * @param right
	 *            - x coord of right edge of clip window (in world coords)
	 */
	public void setClipWindow(float bottom, float top, float left, float right) {
		x = left;
		y = bottom;
		x1 = right;
		y1 = top;
		clipheight = top - bottom;
		clipwidth = right - left;
	}

	/**
	 * setViewport - defines the viewport
	 *
	 * @param xmin
	 *            - x coord of lower left of view window (in screen coords)
	 * @param ymin
	 *            - y coord of lower left of view window (in screen coords)
	 * @param width
	 *            - width of view window (in world coords)
	 * @param height
	 *            - width of view window (in world coords)
	 */
	public void setViewport(int x, int y, int width, int height) {
		viewx = x;
		viewy = y;
		viewwidth = width;
		viewheight = height;
		this.activeedges.clear();
		this.edgetable.clear();
	}

	/**
	 * method to remove a edge from the active edges list
	 * 
	 * @param i
	 */
	public void discard(float i) {
		Iterator iterator = activeedges.iterator();
		int flag = 0;
		bucket s = new bucket();
		// while the list contains something
		while (iterator.hasNext()) {

			s = (bucket) iterator.next();
			// if the ymax value of the bucket in the list is less than or equal
			// to scanline value
			if (s.ymax <= i) {
				// set the flag
				flag = 1;
				break;
			}

		}
		// if such element is found then remove from list
		if (flag == 1)
			activeedges.remove(s);
		if (flag != 0)
			discard(i);
	}

	/**
	 * method to find the lowest y value among the points in polygon
	 * 
	 * @param n
	 *            - number of vertices
	 * @param y2
	 *            - array of y values
	 * @return- lowest y value
	 */
	public int getymin(int n, float[] y2) {
		float ymin = y2[0];
		for (int i = 1; i < n; i++) {
			if (y2[i] < ymin)
				ymin = y2[i];
		}
		return (int) ymin;
	}

	/**
	 * method to find the greatest y value among the points in polygon
	 * 
	 * @param n
	 *            -number of vertices
	 * @param y2
	 *            -array of y values
	 * @return-greatest y value
	 */
	public int getymax(int n, float[] y2) {
		float ymax = y2[0];
		for (int i = 1; i < n; i++) {
			if (y2[i] > ymax)
				ymax = y2[i];
		}
		return (int) ymax;
	}

	/**
	 * method to update the x values in the active edges list according to
	 * scanline and slope
	 */
	public void updatex() {
		Iterator iterator = activeedges.iterator();
		bucket s = new bucket();
		// till there is some element in the list
		while (iterator.hasNext()) {
			s = (bucket) iterator.next();
			// add dx to sum
			s.sum += Math.abs(s.dx);
			// till the sum is greater than dy then
			while (s.sum >= Math.abs(s.dy)) {
				// subtract dy from the sum and add or subtract x by 1
				s.xofymin += Math.abs(s.dy) / s.dy;
				s.sum -= Math.abs(s.dy);
			}

		}

	}

	/**
	 * method to add edges to the active edge list
	 * 
	 * @param i
	 *            -value to scanline for which the edges from edgetable has to
	 *            be added to active edge list
	 */
	public void addedges(int i) {
		bucketlist b = new bucketlist();
		// if there is a edge in the edgetable for the scanline value
		if (edgetable.containsKey(i)) {
			b = (bucketlist) edgetable.get(i);
			// if the bucket list from edgetable is not null
			if (b != null) {
				// add all the buckets to the active edge list
				activeedges.addAll(b.b);
			}
		}
	}

	/**
	 * method to fill the scanline from one edge to another
	 * 
	 * @param C
	 *            - object of simpleCanvas class
	 * @param y
	 *            - value of the scanline to be filled
	 */
	public void fillscanline(int y) {
		Iterator iterator = activeedges.iterator();
		bucket p1 = new bucket();
		bucket p2 = new bucket();
		bucket prev = new bucket();
		bucket forcount = new bucket();

		while (iterator.hasNext()) {

			// store the first two buckets in p1 and p2
			p1 = (bucket) iterator.next();
			if (iterator.hasNext()) {
				p2 = (bucket) iterator.next();
				prev = p2;
				// if the x value of first point is large then swap
				if (p1.xofymin > p2.xofymin) {
					bucket temp = new bucket();
					temp = p1;
					p2 = p1;
					p1 = temp;
				}
				// if the x value of both the points are same
				if (p1.xofymin == p2.xofymin) {
					setPixel(p1.xofymin, y);
				}
				// if the line is drawn from one integer to another integer edge
				// coordinate
				else if (p1.sum == 0 && p2.sum == 0) {
					for (int i = p1.xofymin; i <= p2.xofymin - 1; i++)
						setPixel(i, y);
				}
				// if the line is drawn from one fraction to integer edge
				// coordinate
				else if (p1.sum != 0 && p2.sum == 0) {
					for (int i = p1.xofymin; i <= p2.xofymin; i++)
						setPixel(i, y);
				}
				// if the line is drawn form one integer edge to another faction
				// edge coordinate
				else if (p1.sum == 0 && p2.sum != 0) {
					for (int i = p1.xofymin; i <= p2.xofymin; i++)
						setPixel(i, y);
				}
				// if the line is drawn from one fraction edge to another
				// fraction edge coordinate
				else if (p1.sum != 0 && p2.sum != 0) {
					for (int i = p1.xofymin; i <= p2.xofymin; i++)
						setPixel(i, y);
				}

			}

		}

	}

	/**
	 * method to create the edgetable
	 * 
	 * @param n
	 *            -number of edges in the polygon
	 * @param x2
	 *            - array of x coordinates
	 * @param y2
	 *            - array of y coordinates
	 */
	public void inithashtable(int n, float[] x2, float[] y2) {
		// index of y maximum and y minimum value in the coordinates of polygon
		int iymax = 0;
		int iymin = 0;
		// for all the coordinates create edgestable
		for (int i = 0; i < n; i++) {
			bucket buc = new bucket();
			// if it is not the last edge
			if (i != (n - 1)) {
				// find the maximum and minimum y of the two x,y coordinates
				if (y2[i] > y2[i + 1]) {
					iymax = i;
					iymin = i + 1;
				} else {
					iymin = i;
					iymax = i + 1;
				}
				// find the corresponding bucet values for the edge
				buc.ymax = (int) y2[iymax];
				buc.xofymin = (int) x2[iymin];
				buc.dx = (int) (x2[i + 1] - x2[i]);
				buc.dy = (int) (y2[i + 1] - y2[i]);
				// if dy is positive and dx is negative then swap
				if (buc.dx < 0 && buc.dy > 0) {
					buc.dx = Math.abs(buc.dx);
					buc.dy = -1 * (buc.dy);
				}
				// if both dx and dy are negative then take modulus
				if (buc.dx < 0 && buc.dy < 0) {
					buc.dx = Math.abs(buc.dx);
					buc.dy = Math.abs(buc.dy);
				}
				// if the slope of the edge is 0 then skip
				if (buc.dy == 0)
					continue;
				buc.sum = 0;
			}
			// for last edge
			else {
				// find the maximum and minimum y index of the two points
				if (y2[i] > y2[0]) {
					iymax = i;
					iymin = 0;
				} else {
					iymin = i;
					iymax = 0;
				}
				// find the corresponding bucket values of the edge
				buc.ymax = (int) y2[iymax];
				buc.xofymin = (int) x2[iymin];
				buc.dx = (int) (-x2[0] + x2[i]);
				buc.dy = (int) (-y2[0] + y2[i]);
				// if dy is positive and dx is negative then swap
				if (buc.dx < 0 && buc.dy > 0) {
					buc.dx = Math.abs(buc.dx);
					buc.dy = -1 * (buc.dy);
				}
				// if both dx and dy are negative then take modulus
				if (buc.dx < 0 && buc.dy < 0) {
					buc.dx = Math.abs(buc.dx);
					buc.dy = Math.abs(buc.dy);
				}
				// if the slope of the line is 0 then skip
				if (buc.dy == 0)
					continue;
				buc.sum = 0;

			}
			bucketlist b = new bucketlist();
			// if there is no entry for the ymin value then create new
			if (!edgetable.containsKey((int) y2[iymin])) {
				bucketlist b1 = new bucketlist();
				b1.add(buc);
				edgetable.put((int) y2[iymin], b1);
			}
			// if there is an entry in the edgetable for the ymin then append to
			// that list
			else {

				b = (bucketlist) edgetable.get((int) y2[iymin]);
				if (b != null) {
					bucketlist b2 = new bucketlist();
					b2.addall(b);
					b2.add(buc);
					edgetable.put((int) y2[iymin], b2);
				}
			}
		}

	}

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
	 * @param x2
	 *            - x coords of vertices of polygon to be clipped.
	 * @param y2
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

	public int clipPolygon(int in, float[] x2, float[] y2, float outx[],
			float outy[], float x0, float y0, float x1, float y1) {
		// variables to store vertex count after clipping
		int out1n, out2n, out3n, out4n;
		// left clipping
		out1n = SHPC(in, x2, y2, out1x, out1y, x0, y0, x0, y1, 1);
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
	 * @param x2
	 *            - input x coordinates of vertices
	 * @param y2
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
	public int SHPC(int in, float[] x2, float[] y2, float outx[], float outy[],
			float x0, float y0, float x1, float y1, int side) {
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
			if (isinside(x2[i], y2[i], checker, side)) {

				// if the second point is inside the clipping edge
				if (isinside(x2[y], y2[y], checker, side)) {

					// add second point to the output point list
					outx[vertexcount] = x2[y];
					outy[vertexcount] = y2[y];
					vertexcount++;
				}
				// if the second point is outside the clipping edge
				else {
					// add intersection of points with clipping edge
					intersect(x2[i], y2[i], x2[y], y2[y], checker, side);
					outx[vertexcount] = intersectx;
					outy[vertexcount] = intersecty;
					vertexcount++;
				}
			}
			// if first point is outside the clipping edge
			else {
				if (isinside(x2[y], y2[y], checker, side)) {
					intersect(x2[i], y2[i], x2[y], y2[y], checker, side);
					// add the intersection of the points with clipping edge and
					// second point
					outx[vertexcount] = intersectx;
					outy[vertexcount] = intersecty;
					vertexcount++;
					outx[vertexcount] = x2[y];
					outy[vertexcount] = y2[y];
					vertexcount++;
				}
			}
		}
		// return number of output vertices
		return vertexcount;
	}

}
