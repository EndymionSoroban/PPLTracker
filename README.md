# PPL Tracker - Linear Progression Workout App

A mobile application for tracking your Push-Pull-Legs (PPL) workout program with linear progression, based on the Metallicadpa PPL template.

## Features

- **Linear Progression Tracking**: Automatically increases weights when target reps are achieved
- **PPL Template Support**: Built specifically for the Metallicadpa PPL beginner program
- **Rest Timer**: Built-in timer for tracking rest periods between sets
- **Workout History**: Track and review all your past workouts
- **Progress Charts**: Visualize your progression for each exercise
- **1RM Calculator**: Calculate and track your one-rep max
  

## Building the Project

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/PPTracker.git
   ```

2. Open the project in Android Studio.

3. Sync the Gradle files.

4. Build the project:
   ```
   ./gradlew assembleDebug
   ```

5. Install the APK on your device:
   ```
   adb install app/build/outputs/apk/debug/app-debug.apk
   ```

## Architecture

The app follows the MVVM (Model-View-ViewModel) architecture pattern and uses the following components:

- **Room Database**: For data persistence
- **LiveData**: For observable data
- **ViewModel**: For managing UI-related data
- **Repository Pattern**: For centralized data management
- **Data Binding**: For binding UI components to data sources

## Libraries Used

- AndroidX Core and AppCompat
- Material Design Components
- Room Persistence Library
- MPAndroidChart for data visualization
- LiveData and ViewModel
- Navigation Components

## How to Use

1. **Set Your 1RMs**: On first launch, enter your one-rep max values for main lifts.
2. **Start a Workout**: Select the workout type (Push, Pull, or Legs).
3. **Track Sets**: Record your sets, reps, and weights.
4. **Complete Workout**: Mark the workout as complete to apply progression.
5. **Review Progress**: Check your progress over time with detailed charts.

## Data Model

- **Exercise**: Represents an exercise with properties like name, 1RM, sets, reps, etc.
- **Workout**: Represents a single workout session with exercises.
- **WorkoutExercise**: Tracks performance for an exercise within a workout.
- **ProgressEntry**: Tracks the progress of an exercise over time.
- **UserSettings**: Stores user preferences like units (lbs/kg).

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgements

- Based on the Metallicadpa PPL program: [Reddit Post](https://www.reddit.com/r/Fitness/comments/37ylk5/a_linear_progression_based_ppl_program_for/)
- Inspired by various workout tracking apps and spreadsheets
