package com.example.dhruvdahiya.workouthelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainScreen extends AppCompatActivity {
    private static final String LOG_TAG = MainScreen.class.getSimpleName();
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchDisplayPage();
            }
        });
    }

    /** Called when the user taps the ENTER button */
    public void openSearchDisplayPage() {
        Intent intent = new Intent(this, searchresultpage.class);
        startActivity(intent);
        Log.d(LOG_TAG, "Button clicked!");
    }

}
