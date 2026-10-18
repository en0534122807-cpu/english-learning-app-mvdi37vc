package com.generated.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class SettingsFragment extends Fragment {

    private DatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        dbHelper = new DatabaseHelper(getActivity());

        Button btnReset = view.findViewById(R.id.btnReset);
        btnReset.setOnClickListener(v -> {
            dbHelper.setProgress("xp", "0");
            dbHelper.setProgress("streak", "0");
            dbHelper.setProgress("level", "מתחילים");
            Toast.makeText(getActivity(), "ההתקדמות אופסה בהצלחה", Toast.LENGTH_SHORT).show();
            ((MainActivity) getActivity()).loadFragment(new HomeFragment());
        });

        return view;
    }
}
