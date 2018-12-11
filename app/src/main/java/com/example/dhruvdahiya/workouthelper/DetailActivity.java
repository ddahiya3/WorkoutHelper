package com.example.dhruvdahiya.workouthelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static com.example.dhruvdahiya.workouthelper.MainScreen.EXTRA_DESCRIPTION;
import static com.example.dhruvdahiya.workouthelper.MainScreen.EXTRA_EXERCISES;
import static com.example.dhruvdahiya.workouthelper.MainScreen.EXTRA_MUSCLES;

public class DetailActivity extends AppCompatActivity {

    private RequestQueue mQueue;
    private ImageView mImageViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mImageViewResult = findViewById(R.id.image_view_detail);

        mQueue = Volley.newRequestQueue(this);

        jsonParse();

        Intent intent = getIntent();
        String exercises = intent.getStringExtra(EXTRA_EXERCISES);
        ArrayList<Integer> muscles = intent.getIntegerArrayListExtra(EXTRA_MUSCLES);
        String description = intent.getStringExtra(EXTRA_DESCRIPTION);


        TextView textViewExercises = findViewById(R.id.text_view_exercise_detail);
        TextView textViewMuscles = findViewById(R.id.text_view_muscles_detail);
        TextView textDescription = findViewById(R.id.text_view_description_detail);

        //Picasso.with(this).load(imageUrl).fit().centerInside().into(imageView);
        textViewExercises.setText(exercises);
        textViewMuscles.setText("Muscles: " + toText(muscles));
        textDescription.setText("About the Exercise:" + '\n' + description.replace("<p>", "").replace("</p>", "."));


    }
    public void jsonParse() {
            String url = "https://wger.de/api/v2/exercise/?language=2&page=" + i + "&status=2";

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("results");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject results = jsonArray.getJSONObject(i);

                                    String imageUrl = results.getString("image");


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

    public static String toText(ArrayList<Integer> array) {
        if (array == null) {
            return "Error";
        }
        if (array.size() == 0) {
            return "No muscles listed";
        }
        StringBuilder toReturn = new StringBuilder();
        for (int i = 0; i < array.size(); i++) {
            int toText = array.get(i);
            switch(toText) {
                case 1: toReturn.insert(0, "Biceps, ");
                    break;
                case 2: toReturn.insert(0, "Anterior Deltoid, ");
                    break;
                case 3: toReturn.insert(0, "Serratus anterior, ");
                    break;
                case 4: toReturn.insert(0, "Chest, ");
                    break;
                case 5: toReturn.insert(0, "Triceps, ");
                    break;
                case 6: toReturn.insert(0, "Abs, ");
                    break;
                case 7: toReturn.insert(0, "Calves, ");
                    break;
                case 8: toReturn.insert(0, "Gluteus maximus, ");
                    break;
                case 9: toReturn.insert(0, "Trapezius, ");
                    break;
                case 10: toReturn.insert(0, "Quadriceps femoris, ");
                    break;
                case 11: toReturn.insert(0, "Biceps Femoris, ");
                    break;
                case 12: toReturn.insert(0, "Lats, ");
                    break;
                case 13: toReturn.insert(0, "Brachialis, ");
                    break;
                case 14: toReturn.insert(0, "Obliques, ");
                    break;
                case 15: toReturn.insert(0, "Soleus, ");
                    break;
                default: toReturn.insert(0, "invalid muscle");
                    break;
            }
        }
        return toReturn.toString();
    }
}
