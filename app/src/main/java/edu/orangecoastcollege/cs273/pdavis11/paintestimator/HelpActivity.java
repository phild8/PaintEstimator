package edu.orangecoastcollege.cs273.pdavis11.paintestimator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * The second activities Controller. Displays a help dialogue for the user to read. Displays
 * the estimated paint required. Lets the user select a return button home.
 * @author Phillip Davis
 * @version 1.0
 */
public class HelpActivity extends AppCompatActivity {

    /**
     * Creates a new activity interface for the user to use.
     * @param savedInstanceState The saved instnace if there is one.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);


        // Receive intent from main activity
        Intent intentFromMainAct = getIntent();
        String estimatedPaint = intentFromMainAct.getStringExtra("estimatedGallons");

        // References to TextView
        TextView mEstimatedPaintTextView = (TextView) findViewById(R.id.estimatedPaintTextView);

        // Set TextViews
        mEstimatedPaintTextView.setText(estimatedPaint);

    }

    /**
     * Returns the user to the previous activity - MainActivity.java
     * @param v The button click that starts the method.
     */
    public void goToMainActivity(View v) {finish();}
}
