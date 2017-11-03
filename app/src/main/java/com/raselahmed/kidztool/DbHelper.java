package com.raselahmed.kidztool;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

class DbHelper extends SQLiteAssetHelper{

    private static final String DB_NAME = "kidztool.sqlite";
    private static final String[] TABLE_NAME = {"question"};
    private static final int DB_VERSION = 1;

    DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    ArrayList<Question> getQuestion(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Question> questionList = new ArrayList<>();
        String query = "SELECT * FROM "+TABLE_NAME[0];

        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null){
            while (cursor.moveToNext()){
                Question question = new Question(cursor.getInt(cursor.getColumnIndex("qno")),
                        cursor.getString(cursor.getColumnIndex("question")),
                        cursor.getString(cursor.getColumnIndex("option1")),
                        cursor.getString(cursor.getColumnIndex("option2")),
                        cursor.getString(cursor.getColumnIndex("option3")),
                        cursor.getString(cursor.getColumnIndex("option4")),
                        cursor.getString(cursor.getColumnIndex("answer")));
                questionList.add(question);
            }
            cursor.close();
        }
        db.close();
        return questionList;
    }
}
