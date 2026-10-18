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

public class GrammarFragment extends Fragment {

    private DatabaseHelper dbHelper;
    private List<GrammarItem> grammarList;
    private int currentIndex = 0;

    private TextView tvTitle, tvExplanation, tvExample, tvQuestion;
    private RadioGroup rgGrammar;
    private RadioButton rbOpt1, rbOpt2;
    private Button btnCheckGrammar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grammar, container, false);

        dbHelper = new DatabaseHelper(getActivity());
        grammarList = dbHelper.getAllGrammar();

        tvTitle = view.findViewById(R.id.tvTitle);
        tvExplanation = view.findViewById(R.id.tvExplanation);
        tvExample = view.findViewById(R.id.tvExample);
        tvQuestion = view.findViewById(R.id.tvQuestion);
        rgGrammar = view.findViewById(R.id.rgGrammar);
        rbOpt1 = view.findViewById(R.id.rbOpt1);
        rbOpt2 = view.findViewById(R.id.rbOpt2);
        btnCheckGrammar = view.findViewById(R.id.btnCheckGrammar);

        loadGrammar();

        btnCheckGrammar.setOnClickListener(v -> {
            if (grammarList.isEmpty()) return;
            GrammarItem item = grammarList.get(currentIndex);
            int selectedId = rgGrammar.getCheckedRadioButtonId();
            int selectedIndex = -1;
            if (selectedId == R.id.rbOpt1) selectedIndex = 1;
            else if (selectedId == R.id.rbOpt2) selectedIndex = 2;

            if (selectedIndex == item.correctOpt) {
                Toast.makeText(getActivity(), "נכון מאוד!", Toast.LENGTH_SHORT).show();
                currentIndex++;
                if (currentIndex >= grammarList.size()) {
                    currentIndex = 0;
                    Toast.makeText(getActivity(), "סיימת את כל שיעורי הדקדוק!", Toast.LENGTH_LONG).show();
                }
                rgGrammar.clearCheck();
                loadGrammar();
            } else {
                Toast.makeText(getActivity(), "שגוי. נסה שוב.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void loadGrammar() {
        if (grammarList.isEmpty()) {
            tvTitle.setText("אין שיעורי דקדוק זמינים");
            return;
        }
        GrammarItem item = grammarList.get(currentIndex);
        tvTitle.setText(item.title);
        tvExplanation.setText(item.explanation);
        tvExample.setText("דוגמה: " + item.example);
        tvQuestion.setText(item.question);
        rbOpt1.setText(item.opt1);
        rbOpt2.setText(item.opt2);
    }
}
