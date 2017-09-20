package edu.orangecoastcollege.cs273.pdavis11.paintestimator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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

    private void initializeViews()
    {
        mLengthEditText = (EditText) findViewById(R.id.lengthEditText);
        mWidthEditText = (EditText) findViewById(R.id.widthEditText);
        mHeightEditText = (EditText) findViewById(R.id.heightEditText);
        mDoorsEditText = (EditText) findViewById(R.id.doorsEditText);
        mWindowsEditText = (EditText) findViewById(R.id.windowsEditText);
        mGallonsTextView = (TextView) findViewById(R.id.gallonsTextView);

    }

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        loadSharedPreference();
    }

    protected void computeGallons()
    {
        // Update the model first, then calculate
        mRoom.setLength(Float.parseFloat(mLengthEditText.getText().toString()));
        mRoom.setWidth(Float.parseFloat(mWidthEditText.getText().toString()));
        mRoom.setHeight(Float.parseFloat(mHeightEditText.getText().toString()));
        mRoom.setDoors(Integer.parseInt(mDoorsEditText.getText().toString()));
        mRoom.setWindows(Integer.parseInt(mWindowsEditText.getText().toString()));

        mGallonsTextView.setText(getString(R.string.interior_surface_area_text) + mRoom.totalSurfaceArea()
        + "\n" + getString(R.string.gallons_needed_text) + mRoom.gallonsOfPaintRequired());
        saveSharedPreferences();
    }

    protected void goToHelp(View v)
    {
        // Construct an EXPLICIT Intent to go to HelpActivity
        // Intent: specify where to start (context) and where we're going (next Activity)
        Intent helpIntent = new Intent(this, HelpActivity.class);
        helpIntent.putExtra("gallons", mRoom.gallonsOfPaintRequired());
        startActivity(helpIntent);

    }
}
