package com.example.javaproject;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
public class WorkoutStore {
    private static final String PREFS = "workout_prefs";
    private static final String KEY = "workouts";
    public static ArrayList<Workout> load(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        String raw = sp.getString(KEY, "");
        ArrayList<Workout> list = new ArrayList<>();
        if (raw == null || raw.trim().isEmpty()) return list;
        String[] lines = raw.split("\n");
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split("\\|", -1);
            if (parts.length != 3) continue;
            String name = parts[0];
            int minutes;
            try {
                minutes = Integer.parseInt(parts[1]);
            } catch (Exception e) {
                minutes = 0;
            }
            boolean done = "1".equals(parts[2]);
            list.add(new Workout(name, minutes, done));
        }
        return list;
    }
    public static void save(Context ctx, ArrayList<Workout> workouts) {
        StringBuilder sb = new StringBuilder();
        for (Workout w : workouts) {
            sb.append(w.name).append("|")
                    .append(w.minutes).append("|")
                    .append(w.done ? "1" : "0")
                    .append("\n");
        }
        SharedPreferences sp = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        sp.edit().putString(KEY, sb.toString()).apply();
    }
}
