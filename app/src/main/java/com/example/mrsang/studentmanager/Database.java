package com.example.mrsang.studentmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Mr.Sang on 9/11/2017.
 */

public class Database extends SQLiteOpenHelper {
    private static final String TAG = "DBS";
    public static final String DATABASE_NAME = "student_manager";
    public static final String TABLE_NAME = "student";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String NUMBER = "number";
    public static final String EMAIL = "email";
    public static final String ADDRESS = "address";
    private static int VERSION = 1;
    private Context context;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
        Log.d(TAG, "Database: ");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlQuery = "CREATE TABLE " + TABLE_NAME + "(" +
                ID + " INTEGER PRIMARY KEY, " +
                NAME + " NVARCHAR, " +
                NUMBER + " CHAR, " +
                EMAIL + " CHAR," +
                ADDRESS + " NVARCHAR )";
        sqLiteDatabase.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addStudent(Student student) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, student.getName());
        values.put(NUMBER, student.getNumber());
        values.put(EMAIL, student.getEmail());
        values.put(ADDRESS, student.getAddress());
        database.insert(TABLE_NAME, null, values);
        database.close();
    }

    public ArrayList<Student> getAllStudent() {
        ArrayList<Student> arrayList = null;
        SQLiteDatabase database = this.getReadableDatabase();
        Log.d("LOAD : "," Tìm thấy db");
        String sql = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            arrayList = new ArrayList<>();
            do {
                Student student = new Student();
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setNumber(cursor.getString(2));
                student.setEmail(cursor.getString(3));
                student.setAddress(cursor.getString(4));
                arrayList.add(student);
            } while (cursor.moveToNext());
        }
        return arrayList;
    }

    public void deleteStudent(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME,ID +"=?",new String[]{String.valueOf(id)});
    }
    public void clearAllStudent(){
        SQLiteDatabase database = this.getWritableDatabase();
       database.delete(TABLE_NAME,null,null);
    }
    public void updateStudent(Student student){
        ContentValues values = new ContentValues();
        values.put(NAME,student.getName());
        values.put(NUMBER,student.getNumber());
        values.put(EMAIL,student.getEmail());
        values.put(ADDRESS,student.getAddress());
        SQLiteDatabase database = this.getWritableDatabase();
        database.update(TABLE_NAME,values,ID + "=?",new String[]{String.valueOf(student.getId())});
    }
}
