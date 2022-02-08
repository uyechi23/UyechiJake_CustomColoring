package com.example.uyechijake_customcoloring;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceView;

/*
VIEW CLASS -
    Displays the Model object's data to the user
 */
public class SceneView extends SurfaceView {

    // Model class that controls this View class
    public SceneModel sceneModel;

    public SceneView(Context context) {
        super(context);
        sharedInit();
    }

    public SceneView(Context context, AttributeSet attrs) {
        super(context, attrs);
        sharedInit();
    }

    public SceneView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        sharedInit();
    }

    // shared init across all constructors
    public void sharedInit(){
        setWillNotDraw(false);
    }

    @Override
    public void onDraw(Canvas canvas){
        // call the parent class method
        super.onDraw(canvas);

        // for-each loop to iterate through the array of elements
        // retrieve the text from the TextView and construct a similar String using the name of the current element
        // compare the two to determine which element is the currently selected element
        // (ASSUMING NO NAMES ARE THE SAME)
        for(CustomElement e : sceneModel.elementArray){
            String textField = (String) sceneModel.textView.getText();
            String elementName = String.format("Current Element: %s", e.getName());
            if (textField.equals(elementName)) {
                e.drawHighlight(canvas);
            } else {
                e.drawMe(canvas);
            }
        }
    }

    // set the Model for the View class
    public void setModel(SceneModel initModel){
        this.sceneModel = initModel;
    }
}
