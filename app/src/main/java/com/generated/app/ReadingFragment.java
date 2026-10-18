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

import java.util.List;

public class ReadingFragment extends Fragment {

    private DatabaseHelper dbHelper;
    private List<ReadingItem> readingList;
    private int currentIndex = 0;

    private TextView tvTitle, tvLevel, tvStoryText, tvQuestion;
    private RadioGroup rgOptions;
    private RadioButton rb1, rb2, rb3;
    private Button btnCheckReading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reading, container, false);

        dbHelper = new DatabaseHelper(getActivity());
        readingList = dbHelper.getAllReadings();

        tvTitle = view.findViewById(R.id.tvTitle);
        tvLevel = view.findViewById(R.id.tvLevel);
        tvStoryText = view.findViewById(R.id.tvStoryText);
        tvQuestion = view.findViewById(R.id.tvQuestion);
        rgOptions = view.findViewById(R.id.rgOptions);
        rb1 = view.findViewById(R.id.rb1);
        rb2 = view.findViewById(R.id.rb2);
        rb3 = view.findViewById(R.id.rb3);
        btnCheckReading = view.findViewById(R.id.btnCheckReading);

        loadReading();

        btnCheckReading.setOnClickListener(v -> {
            if (readingList.isEmpty()) return;
            ReadingItem item = readingList.get(currentIndex);
            int selectedId = rgOptions.getCheckedRadioButtonId();
            int selectedIndex = -1;
            if (selectedId == R.id.rb1) selectedIndex = 1;
            else if (selectedId == R.id.rb2) selectedIndex = 2;
            else if (selectedId == R.id.rb3) selectedIndex = 3;

            if (selectedIndex == item.correctOption) {
                Toast.makeText(getActivity(), "תשובה נכונה!", Toast.LENGTH_SHORT).show();
                currentIndex++;
                if (currentIndex >= readingList.size()) {
                    currentIndex = 0;
                    Toast.makeText(getActivity(), "סיימת את כל טקסטי הקריאה!", Toast.LENGTH_LONG).show();
                }
                rgOptions.clearCheck();
                loadReading();
            } else {
                Toast.makeText(getActivity(), "תשובה שגויה, נסה שוב.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void loadReading() {
        if (readingList.isEmpty()) {
            tvTitle.setText("אין טקסטים זמינים");
            return;
        }
        ReadingItem item = readingList.get(currentIndex);
        tvTitle.setText(item.title);
        tvLevel.setText("רמה: " + item.level);
        tvStoryText.setText(item.text);
        tvQuestion.setText(item.question);
        rb1.setText(item.option1);
        rb2.setText(item.option2);
        rb3.setText(item.option3);
    }
}
