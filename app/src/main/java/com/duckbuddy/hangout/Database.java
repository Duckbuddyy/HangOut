package com.duckbuddy.hangout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    private static final String VERITABANI_ISMI = "sqllite_database";
    private static final int VERITABANI_VERSIYON = 1;

    private static final String TABLO_ISMI = "cafe_listesi";
    private static String KAFE_ID = "id";
    private static String KAFE_ISIM = "isim";
    private static String KAFE_ADRES = "adres";
    private static String KAFE_TELEFON = "telefon";
    private static String KAFE_FOTOGRAF = "fotograf";
    private static String KAFE_FOTOGRAF2 = "fotograf2";
    private static String KAFE_FOTOGRAF3 = "fotograf3";
    private static String KAFE_YILDIZ = "yildiz";
    private static String KAFE_ENLEM = "enlem";
    private static String KAFE_BOYLAM = "boylam";

    public Database(Context context) {
        super(context,VERITABANI_ISMI,null,VERITABANI_VERSIYON);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tabloYarat = "CREATE TABLE " + TABLO_ISMI + "( "
                + KAFE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KAFE_ISIM + " VARCHAR, "
                + KAFE_ADRES + " VARCHAR, "
                + KAFE_TELEFON + " VARCHAR, "
                + KAFE_FOTOGRAF + " INTEGER, "
                + KAFE_FOTOGRAF2 + " INTEGER, "
                + KAFE_FOTOGRAF3 + " INTEGER, "
                + KAFE_YILDIZ + " INTEGER, "
                + KAFE_ENLEM + " DOUBLE, "
                + KAFE_BOYLAM + " DOUBLE )";
        db.execSQL(tabloYarat);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String tabloSil = "DROP TABLE IF EXISTS " + TABLO_ISMI;
        db.execSQL(tabloSil);
        onCreate(db);
    }

    public void cafeEkle(Cafe cafe){
        if(!cafeVar(cafe)) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KAFE_ISIM, cafe.getCafeIsmi());
            values.put(KAFE_ADRES, cafe.getCafeAdresi());
            values.put(KAFE_TELEFON, cafe.getCafeTelefonu());
            values.put(KAFE_FOTOGRAF, cafe.getCafeFotografId());
            values.put(KAFE_FOTOGRAF2, cafe.getCafeFotografId2());
            values.put(KAFE_FOTOGRAF3, cafe.getCafeFotografId3());
            values.put(KAFE_YILDIZ, cafe.getCafeYildizi());
            values.put(KAFE_ENLEM, cafe.getCafeEnlem());
            values.put(KAFE_BOYLAM, cafe.getCafeBoylam());
            db.insert(TABLO_ISMI, null, values);
            System.out.println(cafe.getCafeIsmi() + "eklendi!");
            db.close();
        }
    }

    public void cafeSil(int cafeID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLO_ISMI, KAFE_ID + " = ?",new String[] {String.valueOf(cafeID)});
        db.close();
    }

    public int cafeSayisiAl(){
        String sorguSayisiSQL = "SELECT * FROM " + TABLO_ISMI;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sorguSayisiSQL,null);
        int cafeSayisi = cursor.getCount();
        db.close();
        cursor.close();
        return cafeSayisi;
    }

    public int cafeIdAl(String kafeIsmi){
        String selectQuery = "SELECT * FROM " + TABLO_ISMI + " WHERE isim = \"" + kafeIsmi + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        int kafeIdIndex = cursor.getColumnIndex(KAFE_ID);
        int deneme = -1;
        if(cursor.getCount() > 0)
            deneme = cursor.getInt(kafeIdIndex);
        cursor.close();
        db.close();
        return deneme;
    }

    public Cafe cafeAl(int id){
        String selectQuery = "SELECT * FROM " + TABLO_ISMI + " WHERE id="+id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();

        int kafeIsmiIndex = cursor.getColumnIndex(KAFE_ISIM);
        int kafeAdresiIndex = cursor.getColumnIndex(KAFE_ADRES);
        int kafeTelefonuIndex = cursor.getColumnIndex(KAFE_TELEFON);
        int kafeFotografIndex = cursor.getColumnIndex(KAFE_FOTOGRAF);
        int kafeFotografIndex2 = cursor.getColumnIndex(KAFE_FOTOGRAF2);
        int kafeFotografIndex3 = cursor.getColumnIndex(KAFE_FOTOGRAF3);
        int kafeYildiziIndex = cursor.getColumnIndex(KAFE_YILDIZ);
        int kafeEnlemIndex = cursor.getColumnIndex(KAFE_ENLEM);
        int kafeBoylamIndex = cursor.getColumnIndex(KAFE_BOYLAM);

        if(cursor.getCount() > 0){
            Cafe cafe = new Cafe(cursor.getString(kafeIsmiIndex),
                    cursor.getString(kafeAdresiIndex),
                    cursor.getString(kafeTelefonuIndex),
                    cursor.getInt(kafeFotografIndex),
                    cursor.getInt(kafeFotografIndex2),
                    cursor.getInt(kafeFotografIndex3),
                    cursor.getInt(kafeYildiziIndex),
                    cursor.getDouble(kafeEnlemIndex),
                    cursor.getDouble(kafeBoylamIndex));
            return cafe;
        }
        cursor.close();
        db.close();
        return null;
    }

    public ArrayList<Cafe> tumKafeleriAl(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLO_ISMI, null);
        ArrayList<Cafe> returnCafeArray = new ArrayList<>();

        int kafeIsmiIndex = cursor.getColumnIndex(KAFE_ISIM);
        int kafeAdresiIndex = cursor.getColumnIndex(KAFE_ADRES);
        int kafeTelefonuIndex = cursor.getColumnIndex(KAFE_TELEFON);
        int kafeFotografIndex = cursor.getColumnIndex(KAFE_FOTOGRAF);
        int kafeFotografIndex2 = cursor.getColumnIndex(KAFE_FOTOGRAF2);
        int kafeFotografIndex3 = cursor.getColumnIndex(KAFE_FOTOGRAF3);
        int kafeYildiziIndex = cursor.getColumnIndex(KAFE_YILDIZ);
        int kafeEnlemIndex = cursor.getColumnIndex(KAFE_ENLEM);
        int kafeBoylamIndex = cursor.getColumnIndex(KAFE_BOYLAM);

        cursor.moveToFirst();

        if(cursor.moveToFirst())
            do {
                returnCafeArray.add(new Cafe(cursor.getString(kafeIsmiIndex),
                cursor.getString(kafeAdresiIndex),
                cursor.getString(kafeTelefonuIndex),
                cursor.getInt(kafeFotografIndex),
                cursor.getInt(kafeFotografIndex2),
                cursor.getInt(kafeFotografIndex3),
                cursor.getInt(kafeYildiziIndex),
                cursor.getDouble(kafeEnlemIndex),
                cursor.getDouble(kafeBoylamIndex)));
            }while (cursor.moveToNext());
        cursor.close();
        db.close();
        return returnCafeArray;
    }

    public void tumKafeleriSil(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLO_ISMI, null, null);
        db.close();
    }

    private boolean cafeVar(Cafe cafe){
        String selectQuery = "SELECT * FROM " + TABLO_ISMI + " WHERE isim = \"" + cafe.getCafeIsmi() + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        boolean kafeVar = false;
        cursor.moveToFirst();

        if(cursor.getCount() > 0)
            kafeVar = true;
        cursor.close();
        db.close();
        return kafeVar;
    }

}