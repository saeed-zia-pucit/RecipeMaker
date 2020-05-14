package com.example.myrecipemaker;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBhelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "RecipeMaker.db";

    private HashMap hp;

    public DBhelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table recipe " +
                        "(title text primary key, description text,instruction text,type text,prep_time text,ing text)"
        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS recipe");
        onCreate(db);
    }

    public boolean insertRecipe (RecipeModel recipeModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", recipeModel.getTitle());
        contentValues.put("description", recipeModel.getDescrption());
        contentValues.put("instruction", recipeModel.getInstruction());
        contentValues.put("type", recipeModel.getType());
        contentValues.put("prep_time",recipeModel.getPrepare_time());
        contentValues.put("ing",recipeModel.getIngrediens());
        long k= db.insert("recipe", null, contentValues);
        return true;
    }


    public ArrayList<RecipeModel> getList() {
        ArrayList<RecipeModel> array_list = new ArrayList<RecipeModel>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur =  db.rawQuery( "select * from recipe", null );
        cur.moveToFirst();

        while(cur.isAfterLast() == false){
         array_list.add(new RecipeModel(cur.getString(0),cur.getString(1),cur.getString(2),
                 cur.getString(3),cur.getString(4),cur.getString(5)));
            cur.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getTitleList() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur =  db.rawQuery( "select * from recipe", null );
        cur.moveToFirst();

        while(cur.isAfterLast() == false){
            array_list.add(cur.getString(0));
            cur.moveToNext();
        }
        return array_list;
    }

    public RecipeModel  getDetail(String title) {

        //hp = new HashMap();
        RecipeModel recipeModel=new RecipeModel();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur =  db.rawQuery( "select * from recipe", null );
        cur.moveToFirst();

        while(cur.isAfterLast() == false){
            if(title.equals(cur.getString(0))){
                recipeModel=new RecipeModel(cur.getString(0),cur.getString(1),cur.getString(2),
                        cur.getString(3),cur.getString(4),cur.getString(5));

            }

            cur.moveToNext();
        }
        return recipeModel;
    }
}