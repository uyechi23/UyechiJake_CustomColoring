package com.example.uyechijake_customcoloring;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class SceneView extends SurfaceView {

    // set palette colors
    Paint accent_0 = new Paint();
    Paint accent_1 = new Paint();
    Paint accent_2 = new Paint();
    Paint accent_3 = new Paint();
    Paint accent_4 = new Paint();
    Paint accent_5 = new Paint();


    public SceneView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // allow onDraw() to draw
        setWillNotDraw(false);

        // create scene palette
        accent_0.setColor(0xFFFFFFFF);
        accent_1.setColor(0xFFFFFFFF);
        accent_2.setColor(0xFFFFFFFF);
        accent_3.setColor(0xFFFFFFFF);
        accent_4.setColor(0xFFFFFFFF);
        accent_5.setColor(0xFFFFFFFF);

        // set background color
        setBackgroundColor(0xFFFFFFFF);
    }
}
