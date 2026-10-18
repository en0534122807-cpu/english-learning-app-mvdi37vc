package com.generated.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

public class HomeFragment extends Fragment {

    private DatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        dbHelper = new DatabaseHelper(getActivity());

        TextView tvGreeting = view.findViewById(R.id.tvGreeting);
        TextView tvXp = view.findViewById(R.id.tvXp);
        TextView tvStreak = view.findViewById(R.id.tvStreak);
        TextView tvLevel = view.findViewById(R.id.tvLevel);
        Button btnStart = view.findViewById(R.id.btnStart);
        Button btnLevelTest = view.findViewById(R.id.btnLevelTest);
        Button btnMyWords = view.findViewById(R.id.btnMyWords);

        // Greeting
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        String greeting = getString(R.string.greeting_morning);
        if (hour >= 12 && hour < 17) {
            greeting = getString(R.string.greeting_afternoon);
        } else if (hour >= 17) {
            greeting = getString(R.string.greeting_evening);
        }
        tvGreeting.setText(greeting);

        // Stats
        String xp = dbHelper.getProgress("xp", "120");
        String streak = dbHelper.getProgress("streak", "3");
        String level = dbHelper.getProgress("level", "בסיסי");

        tvXp.setText(xp + " XP");
        tvStreak.setText(streak + " " + getString(R.string.streak_label));
        tvLevel.setText("רמה: " + level);

        btnStart.setOnClickListener(v -> {
            ((MainActivity) getActivity()).loadFragment(new WordsFragment());
        });

        btnLevelTest.setOnClickListener(v -> {
            ((MainActivity) getActivity()).loadFragment(new LevelTestFragment());
        });

        btnMyWords.setOnClickListener(v -> {
            ((MainActivity) getActivity()).loadFragment(new MyWordsFragment());
        });

        return view;
    }
}
