package com.example.dhruvdahiya.workouthelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class cards {
    private ArrayList<Integer> mMuscle;
    private String mExercise;

    public cards(String exercise, ArrayList<Integer> muscle) {
       mMuscle = muscle;
       mExercise = exercise;
    }
    public ArrayList<Integer> getMuscle() {
        return mMuscle;
    }
    public String getExercise() {
        return mExercise;
    }
}
