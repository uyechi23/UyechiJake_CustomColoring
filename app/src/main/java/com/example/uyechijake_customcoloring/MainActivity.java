package com.example.uyechijake_customcoloring;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // obtain instances of the scene and text Views from the activity_main.xml layout
        SceneView scene = findViewById(R.id.scene);
        TextView text = findViewById(R.id.element_id_field);

        // create the model class of the scene
        SceneModel sceneModel = new SceneModel(text);
        scene.setModel(sceneModel);

        // obtain instances of the three SeekBars that adjust color
        SeekBar red_bar = findViewById(R.id.red_bar);
        SeekBar green_bar = findViewById(R.id.green_bar);
        SeekBar blue_bar = findViewById(R.id.blue_bar);

        // the textView attribute of the SceneView to be the TextView obtained above
        sceneModel.setTextView(text);

        // create a scene controller
        SceneController sceneController = new SceneController(scene, sceneModel, text, red_bar, green_bar, blue_bar);

        // link the scene to the scene controller created above
        scene.setOnTouchListener(sceneController);

        // link the three SeekBars to the OnSeekBarChangeListener created above
        red_bar.setOnSeekBarChangeListener(sceneController);
        green_bar.setOnSeekBarChangeListener(sceneController);
        blue_bar.setOnSeekBarChangeListener(sceneController);

        // draw the scene using the current model
        drawScene(sceneModel);
    }

    /*
    This method draws a scene, given a scene model
     */
    public void drawScene(SceneModel sceneModel){
        sceneModel.addElement(new CustomCircle("circle_one", 0xFF98FF98, 600, 200, 100));
        sceneModel.addElement(new CustomCircle("circle_two", 0xFF98FF98, 200, 200, 100));
        sceneModel.addElement(new CustomCircle("circle_three", 0xFF98FF98, 200, 600, 100));
        sceneModel.addElement(new CustomStar("test_star", 0xFF98FF98, 1000, 1000, 500, 800));
    }

}