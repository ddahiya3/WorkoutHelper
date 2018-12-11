package com.example.dhruvdahiya.workouthelper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {
    private Context mContext;
    private ArrayList<cards> mList;

    public Adapter(Context context, ArrayList<cards> cardList) {
        mContext = context;
        mList = cardList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.cards, viewGroup, false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int position) {
        cards currentItem = mList.get(position);
        ArrayList<Integer> muscleName = currentItem.getMuscle();
        String exerciseName = currentItem.getExercise();

        viewHolder.mTextViewExercise.setText(exerciseName);
        viewHolder.mTextViewMuscle.setText("Muscles: " + toText(muscleName));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewMuscle;
        public TextView mTextViewExercise;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewExercise = itemView.findViewById(R.id.card_exercise);
            mTextViewMuscle = itemView.findViewById(R.id.card_muscle);
        }
    }
    public String toText(ArrayList<Integer> array) {
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
