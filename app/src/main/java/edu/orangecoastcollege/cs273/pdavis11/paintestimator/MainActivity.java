package edu.orangecoastcollege.cs273.pdavis11.paintestimator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * The controller. Initializes the views of and calls methods to send to the modal for
 * processing. Returns calculations of painted rooms and sends the info the view to display
 * the calculated return for the user to read.
 *
 * @author Phillip Davis
 * @version 1
 */
public class MainActivity extends AppCompatActivity {

    private static final DecimalFormat df = new DecimalFormat(".#");

    // member variable for the Views
    private EditText mLengthEditText;
    private EditText mWidthEditText;
    private EditText mHeightEditText;

    private EditText mDoorsEditText;
    private EditText mWindowsEditText;

    private TextView mGallonsTextView;

    // Member variable for the model
    private InteriorRoom mRoom = new InteriorRoom();

    // Member variable for the SharedPreferences
    private SharedPreferences mPrefs;

    /**
     * Initializes the views to write and read values for the user.
     */
    private void initializeViews()
    {
        mLengthEditText = (EditText) findViewById(R.id.lengthEditText);
        mWidthEditText = (EditText) findViewById(R.id.widthEditText);
        mHeightEditText = (EditText) findViewById(R.id.heightEditText);
        mDoorsEditText = (EditText) findViewById(R.id.doorsEditText);
        mWindowsEditText = (EditText) findViewById(R.id.windowsEditText);
        mGallonsTextView = (TextView) findViewById(R.id.gallonsTextView);

    }

    /**
     * Loads the saved preferences of the user. Loads 0 or 0.0 for all fields
     */
    private void loadSharedPreference()
    {
        mPrefs = getSharedPreferences("edu.orangecoastcollege.cs273.pdavis11.PaintEstimator", MODE_PRIVATE);
        if (mPrefs != null)
        {
            // Load all the room information
            mRoom.setLength(mPrefs.getFloat("length", 0.0f));
            mLengthEditText.setText(String.valueOf((mRoom.getLength())));

            mRoom.setWidth(mPrefs.getFloat("width", 0.0f));
            mWidthEditText.setText(String.valueOf(mRoom.getWidth()));

            mRoom.setHeight(mPrefs.getFloat("height", 0.0f));
            mHeightEditText.setText(String.valueOf(mRoom.getHeight()));

            mRoom.setDoors(mPrefs.getInt("doors",0));
            mDoorsEditText.setText(String.valueOf(mRoom.getDoors()));

            mRoom.setDoors(mPrefs.getInt("windows",0));
            mWindowsEditText.setText(String.valueOf(mRoom.getWindows()));
        }
    }

    /**
     * Saves new preferences that the user has selected for the fields.
     */
    private void saveSharedPreferences()
    {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.clear();
        editor.putFloat("length", mRoom.getLength());
        editor.putFloat("width", mRoom.getWidth());
        editor.putFloat("height", mRoom.getHeight());
        editor.putInt("doors", mRoom.getDoors());
        editor.putInt("windows", mRoom.getWindows());

        // Save the changes to the SharedPreferences
        editor.commit();
    }

    /**
     * Starts the TextViews, EditFields, and Buttons for the user to interact with.
     * @param savedInstanceState the saved instance that user has selected
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        loadSharedPreference();
    }

    /**
     * Computes the gallons needed to be able to paint a bed room. Sends mLength, mHeight, mWidth,
     * mDoors, and mWindows to the model and returns the value needed.
     * @param view The computeButton button click that starts the activity.
     */
    public void computeGallons(View view)
    {
        // Update the model first, then calculate
        mRoom.setLength(Float.parseFloat(mLengthEditText.getText().toString()));
        mRoom.setWidth(Float.parseFloat(mWidthEditText.getText().toString()));
        mRoom.setHeight(Float.parseFloat(mHeightEditText.getText().toString()));
        mRoom.setDoors(Integer.parseInt(mDoorsEditText.getText().toString()));
        mRoom.setWindows(Integer.parseInt(mWindowsEditText.getText().toString()));

        mGallonsTextView.setText(getString(R.string.interior_surface_area_text)
                + mRoom.totalSurfaceArea() + getString(R.string.feet) + "\n"
                + getString(R.string.gallons_needed_text) + df.format(mRoom.gallonsOfPaintRequired()));
        saveSharedPreferences();
    }

    /**
     * Sends the user to a help dialogue activity to further the users knowledge about painting
     * rooms. Sends a string the amount of paint required.
     * @param v The helpButton button click that starts the method.
     */
    public void goToHelp(View v)
    {
        String estimatedGallons = getString(R.string.estimated_paint) + " "
                + df.format(mRoom.gallonsOfPaintRequired()) + " " + getString(R.string.gallons_lower);

        // Construct an EXPLICIT Intent to go to HelpActivity
        // Intent: specify where to start (context) and where we're going (next Activity)
        Intent helpIntent = new Intent(this, HelpActivity.class);
        helpIntent.putExtra("estimatedGallons", estimatedGallons);
        startActivity(helpIntent);

    }
}
