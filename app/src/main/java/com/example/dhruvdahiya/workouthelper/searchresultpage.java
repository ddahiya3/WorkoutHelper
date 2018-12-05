package com.example.dhruvdahiya.workouthelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class searchresultpage extends AppCompatActivity {
    private static final String LOG_TAG = searchresultpage.class.getSimpleName();
    private Button searchresultpageGoBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresultpage);
        searchresultpageGoBack = (Button) findViewById(R.id.srpGoBack);
        searchresultpageGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackToMain();
            }
        });
    }

    /**
     * This is the Go Back button's function.
     */
    public void goBackToMain() {
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
        Log.d(LOG_TAG, "Button clicked!");
    }
}
