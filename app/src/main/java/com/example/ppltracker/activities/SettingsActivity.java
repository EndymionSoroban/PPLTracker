package com.example.ppltracker.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ppltracker.R;
import com.example.ppltracker.data.AppRepository;
import com.example.ppltracker.model.UserSettings;

public class SettingsActivity extends AppCompatActivity {

    private AppRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Set up toolbar
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize repository
        repository = new AppRepository(this);

        setupSettingsUI();
    }

    private void setupSettingsUI() {
        // This would set up all the UI elements for settings
        // Such as weight unit selection, rest timer defaults, etc.

        // For example, weight rounding spinner:
        // Spinner roundingSpinner = findViewById(R.id.spinner_weight_rounding);
        // ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
        //     this, R.array.weight_rounding_options, android.R.layout.simple_spinner_item);
        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // roundingSpinner.setAdapter(adapter);
    }

    // This would be called when the save button is clicked
    public void saveSettings(View view) {
        // Save settings to repository
        Toast.makeText(this, "Settings saved", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}