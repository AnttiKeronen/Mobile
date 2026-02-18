package com.example.javaproject;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import java.util.ArrayList;
public class WorkoutAdapter extends BaseAdapter {
    public interface OnChanged {
        void onChanged();
    }
    private final LayoutInflater inflater;
    private final ArrayList<Workout> workouts;
    private final OnChanged listener;
    public WorkoutAdapter(Context ctx, ArrayList<Workout> workouts, OnChanged listener) {
        this.inflater = LayoutInflater.from(ctx);
        this.workouts = workouts;
        this.listener = listener;
    }
    @Override public int getCount() { return workouts.size(); }
    @Override public Object getItem(int position) { return workouts.get(position); }
    @Override public long getItemId(int position) { return position; }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = inflater.inflate(R.layout.item_workout, parent, false);
        }
        Workout w = workouts.get(position);
        ImageView img = v.findViewById(R.id.ImageIcon);
        TextView txt = v.findViewById(R.id.TextWorkout);
        Switch sw = v.findViewById(R.id.switchDone);
        Button buttonDelete = v.findViewById(R.id.buttonDelete);
        img.setImageResource(R.drawable.ic_workout);
        txt.setText(w.name + " - " + w.minutes + " min");
        sw.setOnCheckedChangeListener(null);
        sw.setChecked(w.done);
        sw.setOnCheckedChangeListener((buttonView, isChecked) -> {
            w.done = isChecked;
            if (listener != null) listener.onChanged();
        });
        buttonDelete.setOnClickListener(view -> {
            workouts.remove(position);
            notifyDataSetChanged();
            if (listener != null) listener.onChanged();
        });
        return v;
    }
}

