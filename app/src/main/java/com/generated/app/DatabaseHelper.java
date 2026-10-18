package com.generated.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "english_app.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE words (id INTEGER PRIMARY KEY AUTOINCREMENT, english TEXT, translation TEXT, sentence TEXT, level TEXT, category TEXT, mastery INTEGER)");
        db.execSQL("CREATE TABLE progress (key TEXT PRIMARY KEY, value TEXT)");
        db.execSQL("CREATE TABLE reading (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, level TEXT, text TEXT, question TEXT, opt1 TEXT, opt2 TEXT, opt3 TEXT, correct INTEGER)");
        db.execSQL("CREATE TABLE grammar (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, level TEXT, explanation TEXT, example TEXT, question TEXT, opt1 TEXT, opt2 TEXT, correct INTEGER)");
        
        insertInitialData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS words");
        db.execSQL("DROP TABLE IF EXISTS progress");
        db.execSQL("DROP TABLE IF EXISTS reading");
        db.execSQL("DROP TABLE IF EXISTS grammar");
        onCreate(db);
    }

    private void insertInitialData(SQLiteDatabase db) {
        // Sample words
        String[][] words = {
            {"Apple", "תפוח", "I eat an apple every day.", "מתחילים", "אוכל"},
            {"Book", "ספר", "This is a very good book.", "מתחילים", "יומיום"},
            {"Car", "רכב", "He drives a new car.", "מתחילים", "נסיעות"},
            {"Dog", "כלב", "The dog is barking.", "מתחילים", "בעלי חיים"},
            {"House", "בית", "They live in a big house.", "מתחילים", "יומיום"},
            {"Water", "מים", "Please give me some water.", "מתחילים", "אוכל"},
            {"Friend", "חבר", "She is my best friend.", "בסיסי", "חברה"},
            {"Work", "עבודה", "I start work at 8 AM.", "בסיסי", "עבודה"},
            {"Money", "כסף", "How much does this cost?", "בסיסי", "קניות"},
            {"Travel", "לנסיע / לטיול", "I love to travel abroad.", "בסיסי", "נסיעות"},
            {"Airport", "שדה תעופה", "We are heading to the airport.", "בינוני", "נסיעות"},
            {"Restaurant", "מסעדה", "Let us eat at that restaurant.", "בינוני", "מסעדות"},
            {"Meeting", "פגישה", "The meeting is at noon.", "בינוני", "עבודה"},
            {"Customer", "לקוח", "Customer service is available.", "גבוה", "שירות לקוחות"},
            {"Technology", "טכנולוגיה", "New technology changes our lives.", "מתקדם מאוד", "טכנולוגיה"}
        };

        for (String[] w : words) {
            ContentValues cv = new ContentValues();
            cv.put("english", w[0]);
            cv.put("translation", w[1]);
            cv.put("sentence", w[2]);
            cv.put("level", w[3]);
            cv.put("category", w[4]);
            cv.put("mastery", 0);
            db.insert("words", null, cv);
        }

        // Reading texts
        db.execSQL("INSERT INTO reading (title, level, text, question, opt1, opt2, opt3, correct) VALUES (" +
                "'My Morning Routine', 'מתחילים', 'Every morning I wake up at 7 AM. I drink a cup of coffee and eat breakfast. Then I go to work by bus.', 'What time does the author wake up?', '6 AM', '7 AM', '8 AM', 2)");
        db.execSQL("INSERT INTO reading (title, level, text, question, opt1, opt2, opt3, correct) VALUES (" +
                "'A Trip to London', 'בסיסי', 'Last summer I visited London. I saw Big Ben and rode the London Eye. The weather was rainy but wonderful.', 'What was the weather like?', 'Sunny', 'Rainy', 'Snowy', 2)");

        // Grammar
        db.execSQL("INSERT INTO grammar (title, level, explanation, example, question, opt1, opt2, correct) VALUES (" +
                "'Present Simple', 'מתחילים', 'משמש לתיאור פעולות קבועות או הרגלים.', 'I work every day.', 'Choose the correct form: She ___ to school.', 'go', 'goes', 2)");
        db.execSQL("INSERT INTO grammar (title, level, explanation, example, question, opt1, opt2, correct) VALUES (" +
                "'Past Simple', 'בסיסי', 'משמש לתיאור פעולות שהתרחשו בעבר הסתיים.', 'I visited Paris last year.', 'Choose the correct form: Yesterday, I ___ a book.', 'read', 'reads', 1)");
    }

    public List<WordItem> getAllWords() {
        List<WordItem> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, english, translation, sentence, level, category, mastery FROM words", null);
        if (c.moveToFirst()) {
            do {
                list.add(new WordItem(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getInt(6)));
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    public void updateWordMastery(int id, int mastery) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("mastery", mastery);
        db.update("words", cv, "id = ?", new String[]{String.valueOf(id)});
    }

    public List<ReadingItem> getAllReadings() {
        List<ReadingItem> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, title, level, text, question, opt1, opt2, opt3, correct FROM reading", null);
        if (c.moveToFirst()) {
            do {
                list.add(new ReadingItem(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6), c.getString(7), c.getInt(8)));
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    public List<GrammarItem> getAllGrammar() {
        List<GrammarItem> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, title, level, explanation, example, question, opt1, opt2, correct FROM grammar", null);
        if (c.moveToFirst()) {
            do {
                list.add(new GrammarItem(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6), c.getString(7), c.getInt(8)));
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    public String getProgress(String key, String defaultValue) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT value FROM progress WHERE key = ?", new String[]{key});
        String val = defaultValue;
        if (c.moveToFirst()) {
            val = c.getString(0);
        }
        c.close();
        return val;
    }

    public void setProgress(String key, String value) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("key", key);
        cv.put("value", value);
        db.replace("progress", null, cv);
    }
}
