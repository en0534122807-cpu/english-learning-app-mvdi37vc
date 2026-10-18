package com.generated.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class LevelTestFragment extends Fragment {

    private DatabaseHelper dbHelper;
    private RadioGroup rgTest;
    private RadioButton rb1, rb2, rb3;
    private Button btnSubmitTest;
    private TextView tvTestTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_level_test, container, false);

        dbHelper = new DatabaseHelper(getActivity());
        rgTest = view.findViewById(R.id.rgTest);
        rb1 = view.findViewById(R.id.rb1);
        rb2 = view.findViewById(R.id.rb2);
        rb3 = view.findViewById(R.id.rb3);
        btnSubmitTest = view.findViewById(R.id.btnSubmitTest);
        tvTestTitle = view.findViewById(R.id.tvTestTitle);

        btnSubmitTest.setOnClickListener(v -> {
            int selectedId = rgTest.getCheckedRadioButtonId();
            if (selectedId == R.id.rb2) {
                dbHelper.setProgress("level", "בינוני");
                Toast.makeText(getActivity(), "כל הכבוד! רמתך נקבעה כ: בינוני", Toast.LENGTH_LONG).show();
            } else {
                dbHelper.setProgress("level", "בסיסי");
                Toast.makeText(getActivity(), "רמתך נקבעה כ: בסיסי", Toast.LENGTH_LONG).show();
            }
            ((MainActivity) getActivity()).loadFragment(new HomeFragment());
        });

        return view;
    }
}
