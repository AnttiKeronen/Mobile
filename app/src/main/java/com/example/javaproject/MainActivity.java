package com.example.javaproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity implements WorkoutAdapter.OnChanged {
    private ArrayList<Workout> workouts;
    private WorkoutAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnAdd = findViewById(R.id.btnAdd);
        ListView list = findViewById(R.id.listWorkouts);
        workouts = WorkoutStore.load(this);
        adapter = new WorkoutAdapter(this, workouts, this);
        list.setAdapter(adapter);
        btnAdd.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, AddWorkoutActivity.class))
        );
    }
    @Override
    protected void onResume() {
        super.onResume();
        workouts.clear();
        workouts.addAll(WorkoutStore.load(this));
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onChanged() {
        WorkoutStore.save(this, workouts);
    }
}
