package com.example.uyechijake_customcoloring;

import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

/*
MODEL CLASS -
    Contains data that controls the View object
    Gets modified by actions that occur in the Controller object
 */
public class SceneModel {
    // elementArray - the array of CustomElements that contain the scene
    public ArrayList<CustomElement> elementArray;

    // textView - the TextView tied to the current element displayed on the SceneView
    public TextView textView;

    // seekBars - the SeekBars tied to the colors of the current element
    public SeekBar red_bar;
    public SeekBar green_bar;
    public SeekBar blue_bar;

    // initialize SceneModel with the TextView that will be modified
    public SceneModel(TextView initText, SeekBar red, SeekBar green, SeekBar blue){
        elementArray = new ArrayList<>();
        this.textView = initText;
        this.red_bar = red;
        this.green_bar = green;
        this.blue_bar = blue;
    }

    // set TextView
    public void setTextView(TextView text) {this.textView = text;}

    // add CustomElement
    public void addElement(CustomElement e){elementArray.add(e);}
}
