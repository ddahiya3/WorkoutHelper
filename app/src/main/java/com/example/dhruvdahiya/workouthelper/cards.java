package com.example.dhruvdahiya.workouthelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class cards {
    private ArrayList<Integer> mMuscle;
    private String mExercise;
    private String mDescription;
    private String mImageUrl;

    public cards(String exercise, ArrayList<Integer> muscle, String description) {
       mMuscle = muscle;
       mExercise = exercise;
       mDescription = description;
    }
    public ArrayList<Integer> getMuscle() {
        return mMuscle;
    }
    public String getExercise() {
        return mExercise;
    }
    public String getDescription() {
        return mDescription;
    }
}
