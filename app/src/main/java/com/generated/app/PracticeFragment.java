package com.generated.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PracticeFragment extends Fragment {

    private DatabaseHelper dbHelper;
    private List<WordItem> wordList;
    private int currentIndex = 0;

    private TextView tvQuestionTitle, tvQuestionWord;
    private EditText etAnswer;
    private Button btnSubmit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_practice, container, false);

        dbHelper = new DatabaseHelper(getActivity());
        wordList = dbHelper.getAllWords();

        tvQuestionTitle = view.findViewById(R.id.tvQuestionTitle);
        tvQuestionWord = view.findViewById(R.id.tvQuestionWord);
        etAnswer = view.findViewById(R.id.etAnswer);
        btnSubmit = view.findViewById(R.id.btnSubmit);

        loadQuestion();

        btnSubmit.setOnClickListener(v -> {
            if (wordList.isEmpty()) return;
            WordItem item = wordList.get(currentIndex);
            String userAnswer = etAnswer.getText().toString().trim();

            if (userAnswer.equalsIgnoreCase(item.english)) {
                Toast.makeText(getActivity(), "כל הכבוד! תשובה נכונה", Toast.LENGTH_SHORT).show();
                currentIndex++;
                if (currentIndex >= wordList.size()) {
                    currentIndex = 0;
                    Toast.makeText(getActivity(), "סיימת את כל שאלות התרגול!", Toast.LENGTH_LONG).show();
                }
                etAnswer.setText("");
                loadQuestion();
            } else {
                Toast.makeText(getActivity(), "שגוי. נסה שוב. התשובה הנכונה היא: " + item.english, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    private void loadQuestion() {
        if (wordList.isEmpty()) {
            tvQuestionWord.setText("אין שאלות זמינות");
            return;
        }
        WordItem item = wordList.get(currentIndex);
        tvQuestionWord.setText(item.translation);
    }
}
