package com.generated.app;

public class ReadingItem {
    public int id;
    public String title;
    public String level;
    public String text;
    public String question;
    public String option1;
    public String option2;
    public String option3;
    public int correctOption; // 1, 2, or 3

    public ReadingItem(int id, String title, String level, String text, String question, String option1, String option2, String option3, int correctOption) {
        this.id = id;
        this.title = title;
        this.level = level;
        this.text = text;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.correctOption = correctOption;
    }
}
