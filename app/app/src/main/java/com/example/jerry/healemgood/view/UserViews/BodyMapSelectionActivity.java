/*
 *  Class Name: BodyMapSelectionActivity
 *
 *  Version: Version 1.0
 *
 *  Date: November 14, 2018
 *
 *  Copyright (c) Team 12, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at the University of Alberta
 */

package com.example.jerry.healemgood.view.UserViews;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jerry.healemgood.R;
import com.example.jerry.healemgood.utils.BodyColor;
import com.example.jerry.healemgood.utils.BodyPart;

/**
 * Represents a BodyMapSelectionActivity
 * This activity handles everything relating to selecting a body part on the map for use in records/problems
 *
 * @author TianqiCS
 * @version 1.0
 * @see AppCompatActivity
 * @since 1.0
 */

public class BodyMapSelectionActivity extends AppCompatActivity{
    private float lastTouchX;  // position x
    private float lastTouchY;  // position y
    private BodyColor bodyColor = new BodyColor();  // color under the hood

    /**
     * This function will load a previously used instance of the activity
     *
     * @param savedInstanceState
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_body_selection);

        ImageView imageView = findViewById (R.id.bodyMap);

        imageView.setOnTouchListener(touchListener);
        imageView.setOnClickListener(clickListener);
    }

    /**
     * This is a listener that deals with the body part map
     *
     */
    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent e) {

            // save the X,Y coordinates
            if (e.getActionMasked() == MotionEvent.ACTION_UP) {
                int color = getHotspotColor(R.id.colorMap,(int) e.getX(),(int) e.getY());  // an integer represents the color

                Log.i("Click", "X = "+ e.getX() + " Y = " + e.getY());

                if (bodyColor.getBodyPart(color) == BodyPart.NULL) {  // the position is invalid
                    return false;
                }

                // position is valid save it into variables
                lastTouchX = (int) e.getX();
                lastTouchY = (int) e.getY();
                Toast toast = Toast.makeText(getApplicationContext(), ""+bodyColor.getBodyPart(color), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                return true;
            }

            // let the touch event pass on to whoever needs it
            return false;
        }
    };

    /**
     * This is a listener that records the values entered.
     */
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // retrieve the stored coordinates
            // use the coordinates for whatever
        }
    };


    /**
     * This gets the color of the pixel clicked on
     *
     * @param hotspotId
     * @param x
     * @param y
     * @return x,y
     */

    public int getHotspotColor (int hotspotId, int x, int y) {
        ImageView img = findViewById (hotspotId);
        if (img == null) {
            Log.d ("BodyMapSelection", "Hot spot image not found");
            return 0;
        } else {
            img.setDrawingCacheEnabled(true);
            Bitmap hotspot = Bitmap.createBitmap(img.getDrawingCache());
            if (hotspot == null) {
                Log.d ("BodyMapSelection", "Hot spot bitmap was not created");
                return 0;
            } else {
                img.setDrawingCacheEnabled(false);
                return hotspot.getPixel(x, y);
            }
        }
    }
}
