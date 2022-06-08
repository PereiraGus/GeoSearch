package com.example.geosearch;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbCountry.db";

    public static final String COUNTRY_TABLE_NAME = "tbCountry";
    public static final String COUNTRY_COLUMN_ID = "idCount";
    public static final String COUNTRY_COLUMN_NAME = "nameCount";
    public static final String COUNTRY_COLUMN_INIT = "init";
    public static final String COUNTRY_COLUMN_CAPITAL = "capital";
    public static final String COUNTRY_COLUMN_POP = "pop";
    public static final String COUNTRY_COLUMN_DENSPOP = "dens";
    public static final String COUNTRY_COLUMN_HDI = "hdi";
    public static final String COUNTRY_COLUMN_RURALPOP = "ruralpop";
    public static final String COUNTRY_COLUMN_URBANPOP = "urbanpop";
    public static final String COUNTRY_COLUMN_LIFEEXPEC = "lifeexpec";
    public static final String COUNTRY_COLUMN_TOTAREA = "totarea";
    public static final String COUNTRY_COLUMN_BRUTEGDP = "brutegdp";
    public static final String COUNTRY_COLUMN_CAPITAGDP = "capitagdp";
    public static final String COUNTRY_COLUMN_HIST = "hist";

    public static final String COUNTRY_COLUMN_CURRENCY = "crrc";
    public static final String COUNTRY_COLUMN_REGION = "region";
    public static final String COUNTRY_COLUMN_LANG = "lang";

    public static final String USER_TABLE_NAME = "tbUser";
    public static final String USER_COLUMN_ID = "idUser";
    public static final String USER_COLUMN_NAME = "nameUser";
    public static final String USER_COLUMN_COUNTRY = "countUser";
    public static final String USER_COLUMN_PASSWORD = "passw";
    public static final String USER_COLUMN_ISLOGGED = "islogged";

    public static final String SAVECOUNTS_TABLE_NAME = "tbSaveCounts";
    public static final String SAVECOUNTS_COLUMN_IDUSU = "idUser";
    public static final String SAVECOUNTS_COLUMN_IDCOUNT = "idCount";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(
          "create table tbCountry" +
            "(idCount integer primary key, nameCount text, init text, capital text, pop text," +
                  "dens text, hdi text, ruralpop text, urbanpop text, lifeexpec text," +
                  "totalarea text, brutegdp text, capitagdp text, hist text, crrc text," +
                  "region text, lang text);");
        db.execSQL(
          "create table tbUser" +
            "(idUser integer primary key, nameUser text, countUser text, passw text," +
                  "islogged text);");
        db.execSQL(
          "create table tbSaveCounts" +
                  "(idUser integer, idCount integer," +
                  "FOREIGN KEY(idUser) references tbUser (idUser)," +
                  "FOREIGN KEY(idCount) references tbCountry (idCount));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldDB, int newDB){
        db.execSQL(
                "drop table if exists tbCountry;");
        db.execSQL(
                "drop table if exists tbUser;");
        db.execSQL(
                "drop table if exists tbSaveCounts;");
        onCreate(db);
    }

    /*public boolean insertCountry (country c)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cont = new ContentValues();
        cont.put(COUNTRY_COLUMN_NAME, c.)
    }*/
}
