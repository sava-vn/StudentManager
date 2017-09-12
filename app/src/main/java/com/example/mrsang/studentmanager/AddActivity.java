package com.example.mrsang.studentmanager;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {
    public static int FULL = 11;
    public static String BUNDLE = "BUNDLE";
    private EditText edtName;
    private EditText edtNumber;
    private EditText edtEmail;
    private EditText edtAdd;
    private Button btnAdd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initWidget();
        setFont();
        MainActivity.arrEdit = new ArrayList<>();
        MainActivity.listDel = new ArrayList<>();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result();
            }
        });
    }

    public void initWidget() {
        edtName = (EditText) findViewById(R.id.edt_name);
        edtNumber = (EditText) findViewById(R.id.edt_number);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtAdd = (EditText) findViewById(R.id.edt_address);
        btnAdd = (Button) findViewById(R.id.btn_add);
    }

    public void setFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Geogrotesque-Light.otf");
        edtName.setTypeface(typeface);
        edtNumber.setTypeface(typeface);
        edtEmail.setTypeface(typeface);
        edtAdd.setTypeface(typeface);
    }

    public void result() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString(Database.NAME, edtName.getText().toString());
        bundle.putString(Database.NUMBER, edtNumber.getText().toString());
        bundle.putString(Database.EMAIL, edtEmail.getText().toString());
        bundle.putString(Database.ADDRESS, edtAdd.getText().toString());
        intent.putExtra(BUNDLE, bundle);
        setResult(FULL, intent);
        finish();
    }
}