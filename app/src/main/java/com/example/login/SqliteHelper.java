package com.example.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteHelper extends SQLiteOpenHelper {

    //DATABASE NAME
    public static final String DATABASE_NAME = "akademik";

    //DATABASE VERSION
    public static final int DATABASE_VERSION = 1;

    //TABLE NAME
    public static final String TABLE_USERS = "users";
    public static final String TABLE_SISWA = "siswa";
    public static final String TABLE_GURU = "guru";
    public static final String TABLE_MAPEL = "mapel";
    public static final String TABLE_NILAI = "nilai";

    //TABLE USERS COLUMNS
    //ID COLUMN @primaryKey
    public static final String KEY_ID = "id";
    //COLUMN user name
    public static final String KEY_USER_NAME = "username";
    //COLUMN email
    public static final String KEY_EMAIL = "email";
    //COLUMN password
    public static final String KEY_PASSWORD = "password";



    public static final String KEY_IDSISWA = "nis";
    public static final String KEY_NAMES = "nama";
    public static final String KEY_TGLLAHIR = "tgllahir";
    public static final String KEY_JENKEL = "jeniskelamin";
    public static final String KEY_ALAMAT = "alamat";
    public static final String KEY_NOHP = "nohp";

    public static final String KEY_IDGURU = "NIP";
    public static final String KEY_NAMAGURU = "nama";
    public static final String KEY_TGL_LAHIR = "tgllahir";
    public static final String KEY_JENKEL_GR = "jeniskelamin";
    public static final String KEY_ALAMAT_GR = "alamat";

    public static final String KEY_IDMAPEL = "idmapel";
    public static final String KEY_MAPELl = "mapel";

    public static final String KEY_NIS = "nis";
    public static final String KEY_NAME = "nama";
    public static final String KEY_MAPEL = "mapel";
    public static final String KEY_UTS = "uts";
    public static final String KEY_UAS = "uas";
    public static final String KEY_TUGAS = "tugas";
    public static final String KEY_TOTAL = "total";
    public static final String KEY_GRADE = "grade";



    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_USER_NAME + " TEXT, "
            + KEY_EMAIL + " TEXT, "
            + KEY_PASSWORD + " TEXT"
            + " ) ";

    public static final String SQL_TABLE_SISWA = " CREATE TABLE " + TABLE_SISWA
            + " ( "
            + KEY_IDSISWA + " INTEGER PRIMARY KEY, "
            + KEY_NAMES + " TEXT, "
            + KEY_TGLLAHIR + " TEXT, "
            + KEY_JENKEL + " TEXT, "
            + KEY_ALAMAT + " TEXT, "
            + KEY_NOHP + " TEXT"
            + " ) ";

    public static final String SQL_TABLE_GURU = " CREATE TABLE " + TABLE_GURU
            + " ( "
            + KEY_IDGURU + " INTEGER PRIMARY KEY, "
            + KEY_NAMAGURU + " TEXT, "
            + KEY_TGL_LAHIR + " TEXT, "
            + KEY_JENKEL_GR + " TEXT, "
            + KEY_ALAMAT_GR + " TEXT"
            + " ) ";

    public static final String SQL_TABLE_MAPEL = " CREATE TABLE " + TABLE_MAPEL
            + " ( "
            + KEY_IDMAPEL + " INTEGER PRIMARY KEY, "
            + KEY_MAPELl + " TEXT"
            + " ) ";

    public static final String SQL_TABLE_NILAI = " CREATE TABLE " + TABLE_NILAI
            + " ( "
            + KEY_NIS + " INTEGER PRIMARY KEY, "
            + KEY_NAME + " TEXT, "
            + KEY_MAPEL + " TEXT, "
            + KEY_UTS + " TEXT, "
            + KEY_UAS + " TEXT, "
            + KEY_TUGAS + " TEXT, "
            + KEY_TOTAL + " TEXT, "
            + KEY_GRADE + " TEXT"
            + " ) ";


    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);
        sqLiteDatabase.execSQL(SQL_TABLE_SISWA);
        sqLiteDatabase.execSQL(SQL_TABLE_GURU);
        sqLiteDatabase.execSQL(SQL_TABLE_MAPEL);
        sqLiteDatabase.execSQL(SQL_TABLE_NILAI);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_SISWA);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_GURU);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_MAPEL);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NILAI);
    }

    //using this method we can add users to user table
    public void addUser(User user) {

        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();

        //Put username in  @values
        values.put(KEY_USER_NAME, user.userName);

        //Put email in  @values
        values.put(KEY_EMAIL, user.email);

        //Put password in  @values
        values.put(KEY_PASSWORD, user.password);

        // insert row
        long todo_id = db.insert(TABLE_USERS, null, values);
    }

    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{user.email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

            //Match both passwords check they are same or not
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }
}