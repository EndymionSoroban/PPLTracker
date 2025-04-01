package com.example.ppltracker.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ppltracker.R;
import com.example.ppltracker.model.WorkoutExercise;

import java.util.List;
import java.util.Locale;

/**
 * Adapter for the exercise preview RecyclerView in MainActivity
 */
public class ExercisePreviewAdapter extends RecyclerView.Adapter<ExercisePreviewAdapter.ViewHolder> {

    private List<WorkoutExercise> exercises;

    public ExercisePreviewAdapter(List<WorkoutExercise> exercises) {
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exercise_preview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WorkoutExercise workoutExercise = exercises.get(position);

        holder.exerciseName.setText(workoutExercise.getExercise().getName());

        String detailsText = String.format(Locale.getDefault(),
                "%d × %d-%d reps @ %.1f lbs",
                workoutExercise.getExercise().getSets(),
                workoutExercise.getExercise().getMinReps(),
                workoutExercise.getExercise().getMaxReps(),
                workoutExercise.getExercise().getCurrentWeight());
        holder.exerciseDetails.setText(detailsText);

        // Show AMRAP text if the last set is AMRAP
        if (workoutExercise.getExercise().isLastSetAmrap()) {
            holder.exerciseAmrap.setVisibility(View.VISIBLE);
        } else {
            holder.exerciseAmrap.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    /**
     * ViewHolder for exercise preview items
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView exerciseName;
        TextView exerciseDetails;
        TextView exerciseAmrap;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseName = itemView.findViewById(R.id.text_exercise_name);
            exerciseDetails = itemView.findViewById(R.id.text_exercise_details);
            exerciseAmrap = itemView.findViewById(R.id.text_exercise_amrap);
        }
    }
}