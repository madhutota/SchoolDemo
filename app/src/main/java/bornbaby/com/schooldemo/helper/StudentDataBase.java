package bornbaby.com.schooldemo.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import bornbaby.com.schooldemo.model.Student;

/**
 * Created by madhu on 28-Dec-17.
 */

public class StudentDataBase extends SQLiteOpenHelper {

    // Database version
    private static final int DATABASE_VERSION = 1;

    // Database name
    private static final String DATABASE_NAME = "studetsinfo";
    // table name
    private static final String TABLE_NAME = "student";
    // student table columns name
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE_NUMBER = "phonenumber";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_USER_PIC = "userpic";
    private static final String KEY_USER_PASSWORD = "password";


    private static final String CREATE_TABLE_NAME = "CREATE TABLE "
            + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_NAME + " TEXT,"
            + KEY_PHONE_NUMBER + " TEXT ,"
            + KEY_EMAIL + " TEXT ,"
            + KEY_USER_PIC + " TEXT ,"
            + KEY_USER_PASSWORD + " TEXT " +
            ")";


    public StudentDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_NAME);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void addStudent(Student student) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, student.getName());
        contentValues.put(KEY_PHONE_NUMBER, student.getPhonenumber());
        contentValues.put(KEY_EMAIL, student.getEmail());
        contentValues.put(KEY_USER_PIC, student.getUserPic());
        contentValues.put(KEY_USER_PASSWORD, student.getPassword());
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();


    }

    public Student getStudent(int id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String[] columns = new String[]{KEY_ID, KEY_NAME, KEY_PHONE_NUMBER, KEY_EMAIL, KEY_USER_PIC, KEY_USER_PASSWORD};
        String selection = KEY_ID + "=?";
        String[] args = new String[]{String.valueOf(id)};

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, columns, selection, args, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();


        Student contact = new Student();
        contact.setId(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(1));
        contact.setPhonenumber(cursor.getString(2));
        contact.setUserPic(cursor.getString(3));

        return contact;


    }

    public List<Student> getAllStudents() {
        List<Student> shopsList = new ArrayList<>();

        // Select Query
        String selectQuery = " SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(Integer.parseInt(cursor.getString(0)));
                student.setName(cursor.getString(1));
                student.setPhonenumber(cursor.getString(2));
                student.setEmail(cursor.getString(3));
                student.setUserPic(cursor.getString(4));

                shopsList.add(student);
            } while (cursor.moveToNext());
        }
        return shopsList;
    }

    public int getStudentsCount() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String selectQuery = " SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        cursor.close();

        return cursor.getCount();


    }

    public boolean chekStudent(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                KEY_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = KEY_EMAIL + " = ?" + " AND " + KEY_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        Cursor cursor = db.query(TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }


}
