package com.raselahmed.kidztool.data_access;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.raselahmed.kidztool.models.BioDict;
import com.raselahmed.kidztool.models.Question;
import com.raselahmed.kidztool.models.User;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class DbHelper extends SQLiteAssetHelper{

    private static final String DB_NAME = "kidztool.sqlite";
    private static final String[] TABLE_NAME = {"question", "dictionary", "favorite", "user"};
    private static final int DB_VERSION = 1;


    private static DbHelper mInstance = null;

    public static synchronized DbHelper getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new DbHelper(context);
        }
        return mInstance;
    }


    private DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public ArrayList<Question> getQuestion(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Question> questionList = new ArrayList<>();
        //String query = "SELECT * FROM "+TABLE_NAME[0]+" LIMIT '"+num+"'";

        //Cursor cursor = db.rawQuery(query, null);
        Cursor cursor = db.query(TABLE_NAME[0], new String[]{"*"}, null, null,
                null, null, null);
        if (cursor != null){
            while (cursor.moveToNext()){
                Question question = new Question(cursor.getInt(cursor.getColumnIndex("qno")),
                        cursor.getString(cursor.getColumnIndex("question")),
                        cursor.getString(cursor.getColumnIndex("option1")),
                        cursor.getString(cursor.getColumnIndex("option2")),
                        cursor.getString(cursor.getColumnIndex("option3")),
                        cursor.getString(cursor.getColumnIndex("option4")),
                        cursor.getString(cursor.getColumnIndex("answer")),
                        false);
                questionList.add(question);
            }
            cursor.close();
        }
        //Log.e("DB", ""+questionList.size());
        db.close();
        return questionList;
    }

    public ArrayList<BioDict> getDataOrderByGN(){
        ArrayList<BioDict> nameList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        //String query = "SELECT * FROM "+TABLE_NAME[0];

        Cursor cursor = db.query(TABLE_NAME[1], new String[]{"*"}, null,
                null, null, null, "generalname");
        if(cursor!=null){
            while (cursor.moveToNext()){
                String generalName = cursor.getString(cursor.getColumnIndex("generalname"));
                String scientificName = cursor.getString(cursor.getColumnIndex("scientificname"));

                BioDict Name = new BioDict(generalName, scientificName);
                nameList.add(Name);
            }
            cursor.close();
        }
        db.close();;
        return nameList;
    }

    public ArrayList<BioDict> getDataOrderBySN(){
        ArrayList<BioDict> nameList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        //String query = "SELECT * FROM "+TABLE_NAME[0];

        Cursor cursor = db.query(TABLE_NAME[1], new String[]{"*"}, null,
                null, null, null, "scientificname");
        if(cursor!=null){
            while (cursor.moveToNext()){
                String generalName = cursor.getString(cursor.getColumnIndex("generalname"));
                String scientificName = cursor.getString(cursor.getColumnIndex("scientificname"));

                BioDict Name = new BioDict(generalName, scientificName);
                nameList.add(Name);
            }
            cursor.close();
        }
        db.close();;
        return nameList;
    }

    public boolean addToFavorite(BioDict bioDict){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put("favgen", bioDict.getGeneralName());
            contentValues.put("favsci", bioDict.getScientificName());

            db.insert(TABLE_NAME[2], null, contentValues);
            db.close();
        } catch (Exception e) {
            return  false;
        }
        return true;
    }

    public ArrayList<BioDict> getFavouriteOrderByGN(){
        ArrayList<BioDict> NameList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        //String query = "SELECT * FROM "+TABLE_NAME[0];

        Cursor cursor = db.query(TABLE_NAME[2], new String[]{"*"}, null,
                null, null, null, "favgen");
        if(cursor!=null){
            while (cursor.moveToNext()){
                String generalName = cursor.getString(cursor.getColumnIndex("favgen"));
                String scientificName = cursor.getString(cursor.getColumnIndex("favsci"));

                BioDict Name = new BioDict(generalName, scientificName);
                NameList.add(Name);
            }
            cursor.close();
        }
        db.close();
        return NameList;
    }

    public int deleteFavourite(String generalName){

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME[2], "favgen = ?", new String[]{generalName});
    }

    public boolean insertData(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put("uid", user.getUid());
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("institute", user.getInstitution());
        long inserted = db.insert(TABLE_NAME[3], null, values);

        db.close();
        return inserted > 0;
    }

    public User getUser(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;
        String query = "SELECT * FROM "+TABLE_NAME[3]+" WHERE email = '"+email+"'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null){
            while (cursor.moveToNext()){
                user = new User(cursor.getString(cursor.getColumnIndex("uid")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("email")),
                        cursor.getString(cursor.getColumnIndex("institute")));
            }
            cursor.close();
        }
        db.close();

        return user;
    }
}
