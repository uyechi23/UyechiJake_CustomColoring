package com.example.uyechijake_customcoloring;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
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

        // obtain instances of the three SeekBars that adjust color
        SeekBar red_bar = findViewById(R.id.red_bar);
        SeekBar green_bar = findViewById(R.id.green_bar);
        SeekBar blue_bar = findViewById(R.id.blue_bar);

        // create the model class of the scene
        SceneModel sceneModel = new SceneModel(text, red_bar, green_bar, blue_bar);
        scene.setModel(sceneModel);

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
        /*
        External Citation
            Date:       08 February 2022
            Problem:    Didn't know where the heck planets and stars were located in the night sky
            Resources:  https://www.nj.com/inside-jersey/2018/02/nj_night_skysaturn_returns.html
                        https://www.vectorstock.com/royalty-free-vector/libra-constellation-space-stars-background-vector-6358490
            Solution:   Used these two sites to determine the names of the stars in the constellation Libra.
                        This is in the timeframe of the Southern sky on January 30, 2019, at around 3-6 AM,
                        when the Moon and Jupiter were both visibly within the constellation Libra in certain parts of the Earth.
         */
        sceneModel.addElement(new CustomStar("Theta Librae", 0xFFD79D32, 236,717,50, 50));
        sceneModel.addElement(new CustomStar("Gamma Librae (Zubenelakrab)", 0xFFD79D32, 564,531,50, 50));
        sceneModel.addElement(new CustomStar("Beta Librae (Zubeneschamali)", 0xFF45B2F1, 784,148,75, 100));
        sceneModel.addElement(new CustomStar("Alpha Librae (Zubenelgenubi)", 0xFF45B2F1, 1292,375,75, 100));
        sceneModel.addElement(new CustomStar("Sigma Librae (Brachium)", 0xFFD79D32, 1184,1031,80, 110));
        sceneModel.addElement(new CustomStar("Upsilon Librae", 0xFFD79D32, 629,1175,50, 50));
        sceneModel.addElement(new CustomStar("Tau Librae", 0xFF45B2F1, 571,1304,50, 50));
        sceneModel.addElement(new CustomCircle("Moon", 0xFF94928E, 1069, 164, 75));
        sceneModel.addElement(new CustomPlanet("Jupiter", 0xFFE5CA97, 870, 538, 40, 30));
        sceneModel.addElement(new CustomPlanet("Mars", 0xFFE66161, 145, 998, 30, -30));
    }

}