package com.example.ppltracker.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ppltracker.R;
import com.example.ppltracker.data.AppRepository;
import com.example.ppltracker.databinding.ActivityProgressBinding;
import com.example.ppltracker.model.Exercise;
import com.example.ppltracker.model.ProgressEntry;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ProgressActivity extends AppCompatActivity {

    private ActivityProgressBinding binding;
    private AppRepository repository;
    private List<String> exerciseNames;
    private String selectedExerciseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProgressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set up toolbar
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize repository
        repository = new AppRepository(this);

        setupExerciseSelector();
        setupInitialData();
    }

    private void setupExerciseSelector() {
        // In a real app, this would come from the repository
        // For demo purposes, we'll use a hardcoded list
        exerciseNames = Arrays.asList(
                "Bench Press", "Squat", "Deadlift", "Overhead Press",
                "Barbell Row", "Incline DB Press", "Romanian Deadlift"
        );

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, exerciseNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerExercise.setAdapter(adapter);

        binding.spinnerExercise.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedExerciseName = exerciseNames.get(position);
                loadExerciseData(selectedExerciseName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    private void setupInitialData() {
        // Load first exercise by default
        if (!exerciseNames.isEmpty()) {
            selectedExerciseName = exerciseNames.get(0);
            binding.spinnerExercise.setSelection(0);
            loadExerciseData(selectedExerciseName);
        }
    }

    private void loadExerciseData(String exerciseName) {
        // In a real app, this would load data from the repository
        // For demo purposes, we'll use hardcoded sample data
        List<ProgressEntry> progressHistory = getSampleProgressHistory(exerciseName);

        updateWeightChart(progressHistory);
        updateVolumeChart(progressHistory);
        update1RMChart(progressHistory);
        updateHistoryTable(progressHistory);
    }

    private void updateWeightChart(List<ProgressEntry> progressHistory) {
        // For demo purposes, we'll add this chart to the FrameLayout
        LineChart chart = new LineChart(this);
        binding.chartWeightProgress.removeAllViews();
        binding.chartWeightProgress.addView(chart);

        List<Entry> entries = new ArrayList<>();
        List<String> dates = new ArrayList<>();

        for (int i = 0; i < progressHistory.size(); i++) {
            ProgressEntry entry = progressHistory.get(i);
            entries.add(new Entry(i, (float) entry.getWeight()));
            dates.add(entry.getDate().format(DateTimeFormatter.ofPattern("MM/dd")));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Weight (lbs)");
        dataSet.setColor(Color.BLUE);
        dataSet.setLineWidth(2f);
        dataSet.setCircleColor(Color.BLUE);
        dataSet.setCircleRadius(4f);
        dataSet.setDrawValues(false);

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(dates));
        xAxis.setGranularity(1f);

        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);
        chart.invalidate();

        // Update stats text
        if (progressHistory.size() > 0) {
            double startWeight = progressHistory.get(0).getWeight();
            double currentWeight = progressHistory.get(progressHistory.size() - 1).getWeight();
            double gain = currentWeight - startWeight;

            binding.textWeightStats.setText(String.format(Locale.getDefault(),
                    "Starting: %.1f lbs | Current: %.1f lbs | Total Gain: %.1f lbs",
                    startWeight, currentWeight, gain));
        }
    }

    private void updateVolumeChart(List<ProgressEntry> progressHistory) {
        // Similar to weight chart implementation
        // For brevity, this would use a BarChart from MPAndroidChart
        // This is a simplified placeholder
    }

    private void update1RMChart(List<ProgressEntry> progressHistory) {
        // Similar to weight chart implementation
        // For brevity, this would calculate and display estimated 1RM values
        // This is a simplified placeholder
    }

    private void updateHistoryTable(List<ProgressEntry> progressHistory) {
        TableLayout tableLayout = binding.tableProgressHistory;

        // Clear existing rows (except header)
        int childCount = tableLayout.getChildCount();
        if (childCount > 1) {
            tableLayout.removeViews(1, childCount - 1);
        }

        // Add rows for each progress entry
        for (ProgressEntry entry : progressHistory) {
            TableRow row = new TableRow(this);
            row.setPadding(8, 8, 8, 8);

            // Date column
            TextView dateView = new TextView(this);
            dateView.setText(entry.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            dateView.setPadding(4, 4, 4, 4);
            dateView.setGravity(View.TEXT_ALIGNMENT_CENTER);
            row.addView(dateView);

            // Weight column
            TextView weightView = new TextView(this);
            weightView.setText(String.format(Locale.getDefault(), "%.1f lbs", entry.getWeight()));
            weightView.setPadding(4, 4, 4, 4);
            weightView.setGravity(View.TEXT_ALIGNMENT_CENTER);
            row.addView(weightView);

            // Reps column
            TextView repsView = new TextView(this);
            repsView.setText(String.valueOf(entry.getReps()));
            repsView.setPadding(4, 4, 4, 4);
            repsView.setGravity(View.TEXT_ALIGNMENT_CENTER);
            row.addView(repsView);

            // Estimated 1RM column
            TextView oneRMView = new TextView(this);
            double estimatedOneRM = calculateOneRM(entry.getWeight(), entry.getReps());
            oneRMView.setText(String.format(Locale.getDefault(), "%.1f lbs", estimatedOneRM));
            oneRMView.setPadding(4, 4, 4, 4);
            oneRMView.setGravity(View.TEXT_ALIGNMENT_CENTER);
            row.addView(oneRMView);

            tableLayout.addView(row);
        }
    }

    private double calculateOneRM(double weight, int reps) {
        // Epley formula: 1RM = w * (1 + r/30)
        return weight * (1 + reps / 30.0);
    }

    // Sample data for demonstration
    private List<ProgressEntry> getSampleProgressHistory(String exerciseName) {
        List<ProgressEntry> progress = new ArrayList<>();

        if (exerciseName.equals("Bench Press")) {
            progress.add(new ProgressEntry(LocalDate.now().minusDays(28), 185.0, 5));
            progress.add(new ProgressEntry(LocalDate.now().minusDays(21), 190.0, 5));
            progress.add(new ProgressEntry(LocalDate.now().minusDays(14), 195.0, 5));
            progress.add(new ProgressEntry(LocalDate.now().minusDays(7), 200.0, 5));
            progress.add(new ProgressEntry(LocalDate.now(), 205.0, 6));
        } else if (exerciseName.equals("Squat")) {
            progress.add(new ProgressEntry(LocalDate.now().minusDays(26), 205.0, 5));
            progress.add(new ProgressEntry(LocalDate.now().minusDays(19), 210.0, 5));
            progress.add(new ProgressEntry(LocalDate.now().minusDays(12), 215.0, 5));
            progress.add(new ProgressEntry(LocalDate.now().minusDays(5), 220.0, 5));
            progress.add(new ProgressEntry(LocalDate.now().minusDays(2), 225.0, 5));
        } else if (exerciseName.equals("Deadlift")) {
            progress.add(new ProgressEntry(LocalDate.now().minusDays(24), 245.0, 5));
            progress.add(new ProgressEntry(LocalDate.now().minusDays(17), 255.0, 5));
            progress.add(new ProgressEntry(LocalDate.now().minusDays(10), 265.0, 5));
            progress.add(new ProgressEntry(LocalDate.now().minusDays(3), 275.0, 5));
        } else {
            // Generic progress for other exercises
            progress.add(new ProgressEntry(LocalDate.now().minusDays(28), 100.0, 8));
            progress.add(new ProgressEntry(LocalDate.now().minusDays(21), 105.0, 8));
            progress.add(new ProgressEntry(LocalDate.now().minusDays(14), 110.0, 8));
            progress.add(new ProgressEntry(LocalDate.now().minusDays(7), 115.0, 8));
            progress.add(new ProgressEntry(LocalDate.now(), 120.0, 9));
        }

        return progress;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}