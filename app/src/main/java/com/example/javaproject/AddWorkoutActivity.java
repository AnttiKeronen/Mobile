package com.example.javaproject;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class AddWorkoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);
        EditText WorkoutName = findViewById(R.id.WorkoutName);
        EditText WorkoutMinutes = findViewById(R.id.WorkoutMinutes);
        Switch DoneSwitch = findViewById(R.id.DoneSwitch);
        Button Savebutton = findViewById(R.id.Savebutton);
        Savebutton.setOnClickListener(v -> {
            String name = WorkoutName.getText().toString().trim();
            String minutesRaw = WorkoutMinutes.getText().toString().trim();
            boolean done = DoneSwitch.isChecked();
            if (TextUtils.isEmpty(name)) {
                WorkoutName.setError("Required");
                return;
            }
            int minutes;
            try {
                minutes = Integer.parseInt(minutesRaw);
            } catch (Exception e) {
                WorkoutMinutes.setError("Enter a number");
                return;
            }
            ArrayList<Workout> workouts = WorkoutStore.load(this);
            workouts.add(0, new Workout(name, minutes, done));
            WorkoutStore.save(this, workouts);
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
