package com.example.mrsang.studentmanager;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static int ADDACTIVITY = 1;
    private Database database;
    private TextView tvTitle;
    private Button btnAddActivity;
    private Button btnShowActivity;
    public static ArrayList<Integer> listDel;
    public static ArrayList<Student> arrEdit;
    private  ArrayList<Student> arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        setFonts();
        btnAddActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, ADDACTIVITY);
            }
        });
        btnShowActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arr = database.getAllStudent();
                Intent intent = new Intent(MainActivity.this,ShowActivity.class);
                intent.putExtra("Key",arr);
                startActivity(intent);
            }
        });
    }

    public void initWidget() {
        arrEdit = new ArrayList<>();
        listDel = new ArrayList<>();
        database = new Database(this);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        btnAddActivity = (Button) findViewById(R.id.btn_AddActivity);
        btnShowActivity = (Button) findViewById(R.id.btn_showActivity);
    }

    public void setFonts() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FS Siruca.ttf");
        btnAddActivity.setTypeface(typeface);
        btnShowActivity.setTypeface(typeface);
        typeface = Typeface.createFromAsset(getAssets(), "fonts/Geogrotesque-Light.otf");
        tvTitle.setTypeface(typeface, Typeface.BOLD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADDACTIVITY) {
            if (resultCode == AddActivity.FULL) {
                Bundle bundle = data.getBundleExtra(AddActivity.BUNDLE);
                String name = bundle.getString(Database.NAME);
                String number = bundle.getString(Database.NUMBER);
                String email = bundle.getString(Database.EMAIL);
                String address = bundle.getString(Database.ADDRESS);
                Student student = new Student(name, number, email, address);
                database.addStudent(student);
            }
        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        for(int i : listDel){
            database.deleteStudent(arr.get(i).getId());
            arr.remove(i);
        }
        for(Student student : arrEdit){
            database.updateStudent(student);
        }

    }
}
