package com.generated.app;

import android.app.Fragment;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class WordsFragment extends Fragment implements TextToSpeech.OnInitListener {

    private DatabaseHelper dbHelper;
    private List<WordItem> wordList;
    private int currentIndex = 0;
    private boolean isEnglishShowing = true;

    private TextToSpeech tts;
    private TextView tvWord, tvSentence, tvProgress;
    private Button btnFlip, btnNext, btnAudio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_words, container, false);

        dbHelper = new DatabaseHelper(getActivity());
        wordList = dbHelper.getAllWords();

        tts = new TextToSpeech(getActivity(), this);

        tvWord = view.findViewById(R.id.tvWord);
        tvSentence = view.findViewById(R.id.tvSentence);
        tvProgress = view.findViewById(R.id.tvProgress);
        btnFlip = view.findViewById(R.id.btnFlip);
        btnNext = view.findViewById(R.id.btnNext);
        btnAudio = view.findViewById(R.id.btnAudio);

        displayWord();

        btnFlip.setOnClickListener(v -> {
            if (wordList.isEmpty()) return;
            WordItem item = wordList.get(currentIndex);
            isEnglishShowing = !isEnglishShowing;
            if (isEnglishShowing) {
                tvWord.setText(item.english);
                btnFlip.setText("הפוך לתרגום");
            } else {
                tvWord.setText(item.translation);
                btnFlip.setText("הפוך לאנגלית");
            }
        });

        btnAudio.setOnClickListener(v -> {
            if (wordList.isEmpty()) return;
            WordItem item = wordList.get(currentIndex);
            if (tts != null) {
                tts.speak(item.english, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnNext.setOnClickListener(v -> {
            if (wordList.isEmpty()) return;
            currentIndex++;
            if (currentIndex >= wordList.size()) {
                Toast.makeText(getActivity(), "סיימת את כל המילים ביחידה!", Toast.LENGTH_SHORT).show();
                currentIndex = 0;
            }
            isEnglishShowing = true;
            btnFlip.setText("הפוך לתרגום");
            displayWord();
        });

        return view;
    }

    private void displayWord() {
        if (wordList.isEmpty()) {
            tvWord.setText("אין מילים זמינות");
            tvSentence.setText("");
            tvProgress.setText("0/0");
            return;
        }
        WordItem item = wordList.get(currentIndex);
        tvWord.setText(item.english);
        tvSentence.setText("דוגמה: " + item.sentence);
        tvProgress.setText((currentIndex + 1) + " / " + wordList.size());
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            tts.setLanguage(Locale.US);
        }
    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
