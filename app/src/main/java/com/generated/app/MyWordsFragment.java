package com.generated.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MyWordsFragment extends Fragment {

    private DatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_words, container, false);

        dbHelper = new DatabaseHelper(getActivity());
        ListView lvWords = view.findViewById(R.id.lvWords);

        List<WordItem> words = dbHelper.getAllWords();
        List<String> displayList = new ArrayList<>();
        for (WordItem w : words) {
            displayList.add(w.english + " - " + w.translation + " (" + w.level + ")");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, displayList);
        lvWords.setAdapter(adapter);

        return view;
    }
}
