package com.generated.app;

public class GrammarItem {
    public int id;
    public String title;
    public String level;
    public String explanation;
    public String example;
    public String question;
    public String opt1, opt2;
    public int correctOpt;

    public GrammarItem(int id, String title, String level, String explanation, String example, String question, String opt1, String opt2, int correctOpt) {
        this.id = id;
        this.title = title;
        this.level = level;
        this.explanation = explanation;
        this.example = example;
        this.question = question;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.correctOpt = correctOpt;
    }
}
