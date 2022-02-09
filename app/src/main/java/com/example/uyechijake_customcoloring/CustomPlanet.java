package com.example.uyechijake_customcoloring;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class CustomPlanet extends CustomElement{

    // define the properties of the planet
    private final int x; // center of planet x-coordinate
    private final int y; // center of planet y-coordinate
    private final int radius; // radius of the planet's body
    private final int angle; // angle to draw the ring at (0 = horizontal)
    private final Paint ringColor = new Paint(); // color of the ring
    private final Path body = new Path(); // Path object storing path of body
    private final Path ring = new Path(); // Path object storing path of ring

    public CustomPlanet(String name, int mainColor, int x, int y, int radius, int angle){
        // call super constructor
        super(name, mainColor);

        // create a random color for the ring; given more time, I would've made it possible
        // to modify both the ring and body color, but I wasn't sure how to go about doing that
        // with the way I implemented the ring of the CustomPlanet object
        int randColor = Color.rgb(myRand.nextInt(256), myRand.nextInt(256),
                myRand.nextInt(256));
        this.ringColor.setColor(randColor);

        // set positional/size data for the planet
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.angle = angle;
    }

    // draw the planet to the canvas
    @Override
    public void drawMe(Canvas canvas) {
        // set the FillType to WINDING
        body.setFillType(Path.FillType.WINDING);
        ring.setFillType(Path.FillType.WINDING);

        // update the planet shape
        updateShape();

        // draw the body in the main color of the planet object (modifiable)
        canvas.drawPath(body, myPaint);

        // rotate the canvas at the ring's rotational angle, centered at the planet's center
        canvas.rotate(angle, x, y);

        // draw the ring in the ring color of the planet object (non-modifiable)
        canvas.drawPath(ring, ringColor);

        // rotate the canvas back, centered at the planet's center
        canvas.rotate(-angle, x, y);
    }

    /*
    External Citation:
        Date:       08 February 2022
        Problem:    Didn't want to redo the calculation methods in the CustomCircle class
        Resource:   Professor Nux's CustomCircle class
        Solution:   Ctrl-c Ctrl-v (since the body of the planet is a circle, similar to CustomCircle)
     */
    @Override
    public boolean containsPoint(int x, int y) {
        //Calculate the distance between this point and the center
        int xDist = Math.abs(x - this.x);
        int yDist = Math.abs(y - this.y);
        int dist = (int)Math.sqrt(xDist*xDist + yDist*yDist);  //Thanks, Pythagoras :)

        return (dist < this.radius + TAP_MARGIN);
    }

    @Override
    public int getSize() {return (int)(Math.PI * this.radius * this.radius);}

    @Override
    public void drawHighlight(Canvas canvas) {
        // draw the circle in highlight paint and outline paint, similar to CustomCircle
        canvas.drawCircle(x, y, radius, highlightPaint);
        canvas.drawCircle(x, y, radius, outlinePaint);  //keep outline so it stands out

        // draw the ring on top of the highlight, since the ring color is not modifiable
        canvas.rotate(angle, x, y);
        ring.addArc((int) (x-radius*1.5), (int) (y-radius*0.1), (int) (x+radius*1.5), (int) (y+radius*0.35), -55, 280);
        canvas.drawPath(ring, ringColor);
        canvas.rotate(-angle, x, y);
    }

    // update the shape of the planet
    public void updateShape(){
        // empty the paths for the body and ring
        body.reset();
        ring.reset();

        // add the circle to the body path and close the path
        body.addCircle(x, y, radius, Path.Direction.CW);
        body.close();

        // add a lopsided arc to the ring path and close it
        ring.addArc((int) (x-radius*1.5), (int) (y-radius*0.1), (int) (x+radius*1.5), (int) (y+radius*0.35), -55, 280);
        ring.close();
    }
}
