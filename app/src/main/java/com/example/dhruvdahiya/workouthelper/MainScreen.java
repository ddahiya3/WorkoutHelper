package com.example.dhruvdahiya.workouthelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainScreen extends AppCompatActivity {
    private static final String LOG_TAG = MainScreen.class.getSimpleName();

    private TextView mTextViewResult;
    private RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        mTextViewResult = findViewById(R.id.simpleTextView);
        Button enterButton = (Button) findViewById(R.id.enter);
        mQueue = Volley.newRequestQueue(this);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openSearchDisplayPage();
                jsonParse();
            }
        });
    }

    /** Called when the user taps the ENTER button */
    public void openSearchDisplayPage() {
        Intent intent = new Intent(this, searchresultpage.class);
        startActivity(intent);
        Log.d(LOG_TAG, "Button clicked!");
    }
    public void jsonParse() {
        String url = "https://api.myjson.com/bins/u2gbi";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("result");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject result = jsonArray.getJSONObject(i);

                                int id = result.getInt("id");

                                mTextViewResult.append(String.valueOf(id) + "\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);

    }
}
