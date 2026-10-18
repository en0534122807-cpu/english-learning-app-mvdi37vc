package com.generated.app;

public class WordItem {
    public int id;
    public String english;
    public String translation;
    public String sentence;
    public String level;
    public String category;
    public int masteryLevel; // 0 to 3

    public WordItem(int id, String english, String translation, String sentence, String level, String category, int masteryLevel) {
        this.id = id;
        this.english = english;
        this.translation = translation;
        this.sentence = sentence;
        this.level = level;
        this.category = category;
        this.masteryLevel = masteryLevel;
    }
}
