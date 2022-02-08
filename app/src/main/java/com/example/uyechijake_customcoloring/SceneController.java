package com.example.uyechijake_customcoloring;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

/*
   External Citation:
       Date:       06 February 2022
       Problem:    Wasn't sure how to create a listener that would tie two separate elements in the layout
       Resource:   (Lecture code posted on Moodle)
       Solution:   Override the default OnTouchListener's onTouch() method to invalidate the display,
                   detect a touch, and determine which element was selected in a for-each loop
 */

/*
   External Citation:
       Date:       06 February 2022
       Problem:    Wasn't sure how to create a listener that would tie two separate elements in the layout
       Resource:   (Lecture code posted on Moodle)
       Solution:   Override the default OnSeekBarChangeListener's onProgressChanged() method to invalidate the
                    display, detect new progress, and update the color of the selected object accordingly
 */

/*
CONTROLLER CLASS -
    Contains Listener objects that modify the Model object, which then modifies the View object
 */
public class SceneController implements View.OnTouchListener, SeekBar.OnSeekBarChangeListener{

    private SceneView scene = null;
    private SceneModel sceneModel = null;
    private TextView text = null;
    private SeekBar red_bar = null;
    private SeekBar green_bar = null;
    private SeekBar blue_bar = null;

    public SceneController(SceneView initScene,
                           SceneModel initModel,
                           TextView initText,
                           SeekBar red_bar,
                           SeekBar green_bar,
                           SeekBar blue_bar){
        this.scene = initScene;
        this.sceneModel = initModel;
        this.text = initText;
        this.red_bar = red_bar;
        this.green_bar = green_bar;
        this.blue_bar = blue_bar;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        // invalidate
        scene.invalidate();

        // get position coordinates from motionEvent
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();

        // sweep through array of CustomElements and find the most recently tapped item
        // also set the progress of the seekBars equal to the values of R, G, and B
        for(CustomElement e : sceneModel.elementArray){
            if(e.containsPoint(x, y)){
                text.setText(String.format("Current Element: %s",e.getName()));
                Log.d("Size", "" + e.getSize());
                int[] rgb = hexToRGB(e.getColor());
                red_bar.setProgress(rgb[0]);
                green_bar.setProgress(rgb[1]);
                blue_bar.setProgress(rgb[2]);
            }
        }

        return false;
    }

    // when the SeekBar changes progress...
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        // invalidate
        scene.invalidate();

        // retrieve color from SeekBars
        int color = Color.rgb(red_bar.getProgress(), green_bar.getProgress(), blue_bar.getProgress());

        // determine the current selected object
        for(CustomElement e : sceneModel.elementArray){
            String textField = (String) text.getText();
            String elementName = String.format("Current Element: %s", e.getName());
            if (textField.equals(elementName)) e.setColor(color);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // irrelevant
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // irrelevant
    }

    public int[] hexToRGB(int hex){
        // create return array to hold three values - red, green, and blue
        int[] rgb = new int[3];

        // mask components individually
        rgb[0] = (0x00FF0000 & hex) >> 16;
        rgb[1] = (0x0000FF00 & hex) >> 8;
        rgb[2] = (0x000000FF & hex);

        // return the rgb array
        return rgb;
    }
}
