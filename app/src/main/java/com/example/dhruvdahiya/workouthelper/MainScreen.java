package com.example.dhruvdahiya.workouthelper;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.concurrent.TimeUnit;


public class MainScreen extends AppCompatActivity implements Adapter.OnItemClickListener {
    private static int SPLASH_TIMEOUT = 2000;
    private static final String LOG_TAG = MainScreen.class.getSimpleName();

    public static final String EXTRA_URL = "image";
    public static final String EXTRA_EXERCISES = "name";
    public static final String EXTRA_MUSCLES = "muscles";
    public static final String EXTRA_DESCRIPTION = "description";
    public static final String EXTRA_ID = "id";

    private RequestQueue mQueue;
    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private ArrayList<cards> mList;


    /**
     * new Handler().postDelayed(new Runnable() {
     *             @Override
     *             public void run() {
     *                 Intent homeIntent = new Intent(MainScreen.this,HomeScreen.class);
     *                 startActivity(homeIntent);
     *                 finish();
     *             }
     *         } ,SPLASH_TIMEOUT);
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.search_display_page);
        /**new Handler().postDelayed(new Runnable() {
                @Override
                  public void run() {
                      Intent homeIntent = new Intent(MainScreen.this,HomeScreen.class);
                      startActivity(homeIntent);
                      finish();
                  }
              } ,SPLASH_TIMEOUT);
         */
        EditText editText = findViewById(R.id.editTexts);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mList = new ArrayList<>();

        mQueue = Volley.newRequestQueue(this);

        jsonParse();

    }
    private void filter(String text) {
        ArrayList<cards> filteredList = new ArrayList<>();

        for (cards item : mList) {
            if (item.getExercise().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        mAdapter.filterList(filteredList);
    }
    public void jsonParse() {
        for (int i = 1; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String url = "https://wger.de/api/v2/exercise/?language=2&page=" + i + "&status=2";

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
                                    String description = results.getString("description");
                                    int exerciseId = results.getInt("id");

                                    mList.add(new cards(exerciseName, convertToList(muscles), description, exerciseId));
                                }

                                mAdapter = new Adapter(MainScreen.this, mList);
                                mRecyclerView.setAdapter(mAdapter);
                                mAdapter.setOnItemClickListener(MainScreen.this);

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

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        cards clickedItem = mList.get(position);

        detailIntent.putExtra(EXTRA_EXERCISES, clickedItem.getExercise());
        detailIntent.putExtra(EXTRA_MUSCLES, clickedItem.getMuscle());
        detailIntent.putExtra(EXTRA_DESCRIPTION, clickedItem.getDescription());
        detailIntent.putExtra(EXTRA_ID, clickedItem.getId());

        startActivity(detailIntent);
    }
}
