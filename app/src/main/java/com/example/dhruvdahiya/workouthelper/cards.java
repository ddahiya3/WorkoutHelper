package com.example.dhruvdahiya.workouthelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class cards {
    private ArrayList<Integer> mMuscle;
    private String mExercise;
    private String mDescription;
    private String mImageUrl;
    private int mId;

    public cards(String exercise, ArrayList<Integer> muscle, String description, int id) {
       mMuscle = muscle;
       mExercise = exercise;
       mDescription = description;
       mId = id;
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
    public int getId() {
        return mId;
    }
}
