//
//  Rasterizer.java
//  
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

/**
 * 
 * A simple class for performing rasterization algorithms.
 *
 */

import java.util.*;

public class Rasterizer {

	/**
	 * number of scanlines
	 */
	int n_scanlines;

	/**
	 * Constructor
	 *
	 * @param n
	 *            number of scanlines
	 *
	 */
	Rasterizer(int n) {
		n_scanlines = n;
	}

	/**
	 * Draw a line from (x0,y0) to (x1, y1) on the simpleCanvas C.
	 *
	 * Implementation should be using the Midpoint Method
	 *
	 * You are to add the implementation here using only calls to C.setPixel()
	 *
	 * @param x0
	 *            x coord of first endpoint
	 * @param y0
	 *            y coord of first endpoint
	 * @param x1
	 *            x coord of second endpoint
	 * @param y1
	 *            y coord of second endpoint
	 * @param C
	 *            The canvas on which to apply the draw command.
	 */
	public void drawLine(int x0, int y0, int x1, int y1, simpleCanvas C) {

		int xinit = 0, yinit = 0;
		float dE, dNE, d;
		int x, y;
		// to swap to make the first point as leftmost
		if (x0 > x1) {
			xinit = x1;
			yinit = y1;
			x1 = x0;
			y1 = y0;
			x0 = xinit;
			y0 = yinit;
		}
		// to calculate the slope of the line
		float dy = y1 - y0, dx = x1 - x0;
		// to draw vertical line
		if (dx == 0) {
			// draw line from lower y value to higher y value among the two
			// points
			if (y0 < y1)
				for (x = x0, y = y0; y <= y1; ++y)
					C.setPixel(x, y);
			else
				for (x = x0, y = y1; y <= y0; ++y)
					C.setPixel(x, y);

		}
		// to draw horzontal line
		else if (dy == 0) {
			// draw line from left x value to right x value among the two points
			if (x0 < x1)
				for (x = x0, y = y0; x <= x1; ++x)
					C.setPixel(x, y);
			else
				for (x = x1, y = y0; x <= x0; ++x)
					C.setPixel(x, y);
		}
		// to draw the line with slope 1
		else if (dy / dx == 1) {

			for (x = x0, y = y0; x <= x1; ++x, ++y)
				C.setPixel(x, y);
		}
		// to draw the line with slope -1
		else if (dy / dx == -1) {
			for (x = x0, y = y0; x <= x1; ++x, --y)
				C.setPixel(x, y);
		}
		// to draw a line with slope less than 1 and greater than 0
		else if ((dy / dx) < 1 && (dy / dx) > 0) {
			// intermediate values required to find the pixel to be filled next
			// for pixel east of current point
			dE = 2 * dy;
			// for pixel north east of current point
			dNE = 2 * (dy - dx);
			d = dE - dx; // initial value to start
			for (x = x0, y = y0; x <= x1; ++x) {
				C.setPixel(x, y);
				// finding pixel for next iteration
				if (d <= 0) {
					d += dE;
				} else {
					++y;
					d += dNE;
				}
			}
		}
		// to draw a line with slope greater than -1 and less than zero
		else if ((dy / dx) > -1 && (dy / dx) < 0) {
			// to find the absolute value of dx and dy
			dx = Math.abs(dx);
			dy = Math.abs(dy);
			// intermediate values required to find the pixel to be filled next
			// for pixel east of current point
			dE = 2 * dy;

			// for pixel south east of current point
			dNE = 2 * (dy - dx);
			// initial value to start
			d = dE - dx;
			for (x = Math.min(x0, x1), y = Math.max(y0, y1); x <= Math.max(x0,
					x1); ++x) {
				C.setPixel(x, y);
				// finding pixel for next iteration
				if (d <= 0) {
					d += dE;
				} else {
					--y;
					d += dNE;
				}
			}

		}
		// to draw a line with slope greater than 1 and less than positive
		// infinity
		else if ((dy / dx) > 1) {
			// intermediate values required to find the pixel to be filled next
			// for pixel east of current point
			dE = 2 * dx;

			// for pixel north east of current point
			dNE = 2 * (dx - dy);
			// initial value to start
			d = dE - dy;
			for (y = y0, x = x0; y <= y1; ++y) {
				C.setPixel(x, y);
				// finding pixel for next iteration
				if (d <= 0) {
					d += dE;
				} else {
					++x;
					d += dNE;
				}
			}
		}
		// to draw a line with slope less than -1 and greater than negative
		// infinity
		else {
			// to find the absolute value of dx and dy
			dx = Math.abs(dx);
			dy = Math.abs(dy);
			// intermediate values required to find the pixel to be filled next
			// for pixel east of current point
			dE = 2 * dx;
			// for pixel south east of current point
			dNE = 2 * (dx - dy);
			d = dE - dy;
			for (y = Math.min(y0, y1), x = Math.max(x0, x1); y <= Math.max(y0,
					y1); ++y) {
				C.setPixel(x, y);
				// initial value to start
				if (d <= 0) {
					d += dE;
				} else {
					--x;
					d += dNE;
				}
			}
		}

	}

}
