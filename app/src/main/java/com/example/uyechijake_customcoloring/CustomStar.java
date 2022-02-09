package com.example.uyechijake_customcoloring;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;

/**
 * This class defines a custom drawing element that is a star.
 *
 * @author Jake Uyechi
 * @version Spring 2022
 * @see CustomElement
 */

public class CustomStar extends CustomElement{

    // define the properties of the star
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final Path shape = new Path();

    // constructor for Star object
    public CustomStar(String name, int color, int x, int y, int width, int height){
        super(name, color); // call super() constructor with name and color

        // set center coordinates and width/height of the star
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void drawMe(Canvas canvas) {
        /*
        External Citation
            Date:           07 February 2022
            Problem:        Needed to figure out how to draw a star, which doesn't have a primitive shape
                            to base it off of (like oval/circle/rectangle)
            Resource(s):    https://developer.android.com/reference/android/graphics/Canvas
                            https://developer.android.com/reference/android/graphics/Path
                            https://developer.android.com/reference/android/graphics/Path.FillType
            Solution:       Used arithmetic to construct a piecewise ellipse made up of four arcs bounded
                            by four rectangles that all meet at the center point of the star by implementing
                            methods used in the documentation for Canvas and Path above
         */


        // set the fill type to be Path.FillType.WINDING (specified in documentation)
        shape.setFillType(Path.FillType.WINDING);

        // update the shape
        updateShape(5);

        // draw the path on the canvas with the given path, forming the outline
        canvas.drawPath(shape, outlinePaint);

        // reset the path
        shape.reset();

        // update the shape with no offset (so no outline prints)
        updateShape(0);

        // draw the path on the canvas with the given path, filling it with the paint
        canvas.drawPath(shape, myPaint);
    }


    /*
    External Citation
        Date:           07 February 2022
        Problem:        Needed to figure out how to determine if the click is within the bounds of the star
        Resource:       https://stuff.mit.edu/afs/sipb/project/android/docs/reference/android/graphics/Path.html
        Solution:       Incomplete; if the user clicks in the entire rectangle that binds the star, even if it isn't
                        directly on the star object painted on the canvas, it will register as a click.
     */
    @Override
    public boolean containsPoint(int x, int y) {
        // create a new rectangle object to store the bounds of the star in
        RectF bounds = new RectF();

        // compute the bounds
        shape.computeBounds(bounds, true);

        // return the boolean returned by the .contains() method
        return bounds.contains(x, y);
    }

    /*
    Area of ellipse: pi * A * B, where A and B are the major and minor axes of the ellipse (reversible).
    Since this is basically a rectangle with ellipses punched out of the corners, the total area
    of this shape will be equal to the area of the rectangle - area of the ellipse bounded by
    the rectangle.
     */
    @Override
    public int getSize() {
        // return the size of the rectangle binding the star minus the size of the ellipse, casted to an int
        return (int) ((width * height) - (Math.PI * width/2 * height/2));
    }

    @Override
    public void drawHighlight(Canvas canvas) {
        // update shape with the given outline offset (5 is enough to make the outline show around star)
        updateShape(5);
        canvas.drawPath(shape, outlinePaint);

        // update shape with the highlight color
        updateShape(0);
        canvas.drawPath(shape, highlightPaint);
    }

    public void updateShape(int offset){
        // create four rectangles to bind ovals to
        // this is for the outline, so the bounds of the oval-binding rectangles will be offset
        RectF topLeft = new RectF(x-width-offset, y-height-offset, x, y);
        RectF topRight = new RectF(x, y-height-offset, x+width+offset, y);
        RectF bottomRight = new RectF(x, y, x+width+offset, y+height+offset);
        RectF bottomLeft = new RectF(x-width-offset, y, x, y+height+offset);

        // create arcs to form the outlines of the edges of the star
        shape.arcTo(topLeft, 90, -90);
        shape.arcTo(topRight, 180, -90);
        shape.arcTo(bottomRight, 270, -90);
        shape.arcTo(bottomLeft, 0, -90);

        // close the path (this shouldn't be necessary, but just to be safe, close)
        // if the start and end points of the path aren't the same, it will draw a line segment
        // so filling the path doesn't cause errors
        shape.close();
    }
}