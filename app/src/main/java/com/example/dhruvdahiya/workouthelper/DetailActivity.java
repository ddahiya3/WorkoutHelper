package com.example.dhruvdahiya.workouthelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.example.dhruvdahiya.workouthelper.MainScreen.EXTRA_DESCRIPTION;
import static com.example.dhruvdahiya.workouthelper.MainScreen.EXTRA_EXERCISES;
import static com.example.dhruvdahiya.workouthelper.MainScreen.EXTRA_MUSCLES;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String exercises = intent.getStringExtra(EXTRA_EXERCISES);
        ArrayList<Integer> muscles = intent.getIntegerArrayListExtra(EXTRA_MUSCLES);
        String description = intent.getStringExtra(EXTRA_DESCRIPTION);

        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textViewExercises = findViewById(R.id.text_view_exercise_detail);
        TextView textViewMuscles = findViewById(R.id.text_view_muscles_detail);
        TextView textDescription = findViewById(R.id.text_view_description_detail);

        //Picasso.with(this).load(imageUrl).fit().centerInside().into(imageView);
        textViewExercises.setText(exercises);
        textViewMuscles.setText("Muscles: " + toText(muscles));
        textDescription.setText(description);


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
