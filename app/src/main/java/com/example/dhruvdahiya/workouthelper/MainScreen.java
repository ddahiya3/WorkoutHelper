package com.example.dhruvdahiya.workouthelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;


public class MainScreen extends AppCompatActivity {
    private static final String LOG_TAG = MainScreen.class.getSimpleName();

    private RequestQueue mQueue;
    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private ArrayList<cards> mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_display_page);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mList = new ArrayList<>();

        mQueue = Volley.newRequestQueue(this);

        jsonParse();
        /**
        Button enterButton = (Button) findViewById(R.id.enter);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openSearchDisplayPage();
            }
        });
         **/
    }

    /** Called when the user taps the ENTER button */
    public void openSearchDisplayPage() {
        Intent intent = new Intent(this, searchresultpage.class);
        startActivity(intent);
        Log.d(LOG_TAG, "Button clicked!");
    }
    public void jsonParse() {
        String url = "https://wger.de/api/v2/exercise/?language=2&status=2";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject results = jsonArray.getJSONObject(i);

                                JSONArray muscles = results.getJSONArray("muscles");
                                String exerciseName = results.getString("name");

                                mList.add(new cards(exerciseName, convertToList(muscles)));
                            }

                            mAdapter = new Adapter(MainScreen.this, mList);
                            mRecyclerView.setAdapter(mAdapter);

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
    public ArrayList<Integer> convertToList(JSONArray array) throws JSONException {
        ArrayList<Integer> list = new ArrayList<>();

        if (array != null) {

            for (int i = 0;i < array.length();i++){
                Integer thing = (Integer) array.get(i);
                list.add(thing);
            }
        }
        return list;
    }
}
