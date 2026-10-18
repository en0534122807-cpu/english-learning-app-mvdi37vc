package com.generated.app;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }

        Button btnHome = findViewById(R.id.btnNavHome);
        Button btnWords = findViewById(R.id.btnNavWords);
        Button btnPractice = findViewById(R.id.btnNavPractice);
        Button btnReading = findViewById(R.id.btnNavReading);
        Button btnGrammar = findViewById(R.id.btnNavGrammar);
        Button btnSettings = findViewById(R.id.btnNavSettings);

        btnHome.setOnClickListener(v -> loadFragment(new HomeFragment()));
        btnWords.setOnClickListener(v -> loadFragment(new WordsFragment()));
        btnPractice.setOnClickListener(v -> loadFragment(new PracticeFragment()));
        btnReading.setOnClickListener(v -> loadFragment(new ReadingFragment()));
        btnGrammar.setOnClickListener(v -> loadFragment(new GrammarFragment()));
        btnSettings.setOnClickListener(v -> loadFragment(new SettingsFragment()));
    }

    public void loadFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentContainer, fragment);
        ft.commit();
    }
}
