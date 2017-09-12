package com.example.mrsang.studentmanager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.icu.text.DisplayContext;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {
    private static final int CONTEXT_MENU_EDIT =91847;
    private static final int CONTEXT_MENU_DEL =91458;
    private int position;
    private ListView lvStudent;
    private CustomAdapterListViewStudent customAdapterListViewStudent;
    private ArrayList<Student> arrayList=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainActivity.listDel = new ArrayList<>();
        MainActivity.arrEdit = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        lvStudent = (ListView) findViewById(R.id.lv_student);
        Intent intent = getIntent();
        arrayList = intent.getParcelableArrayListExtra("Key");
        if(arrayList==null){
            Toast.makeText(this, "KHÔNG CÓ SINH VIÊN NÀO !", Toast.LENGTH_SHORT).show();
        }else{
            customAdapterListViewStudent = new CustomAdapterListViewStudent(ShowActivity.this,R.layout.custom_lv_student,arrayList);
            lvStudent.setAdapter(customAdapterListViewStudent);
        }
        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dialog dialog = new Dialog(ShowActivity.this);
                dialog.setContentView(R.layout.custom_dialog_student);
                TextView tvId = (TextView) dialog.findViewById(R.id.tv_dal_id);
                TextView tvName = (TextView) dialog.findViewById(R.id.tv_dal_name);
                TextView tvNumber = (TextView) dialog.findViewById(R.id.tv_dal_number);
                TextView tvEmail = (TextView) dialog.findViewById(R.id.tv_dal_email);
                TextView tvAddress = (TextView) dialog.findViewById(R.id.tv_dal_address);
                tvId.setText("0"+arrayList.get(i).getId());
                tvName.setText(arrayList.get(i).getName());
                tvNumber.setText(arrayList.get(i).getNumber());
                tvEmail.setText(arrayList.get(i).getEmail());
                tvAddress.setText(arrayList.get(i).getAddress());
                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Geogrotesque-Light.otf");
                tvName.setTypeface(typeface);
                tvNumber.setTypeface(typeface);
                tvEmail.setTypeface(typeface);
                tvAddress.setTypeface(typeface);
                typeface = Typeface.createFromAsset(getAssets(), "fonts/FS Siruca.ttf");
                tvId.setTypeface(typeface,Typeface.BOLD);
                dialog.show();

            }
        });
        registerForContextMenu(lvStudent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId()==R.id.lv_student){
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
            position= acmi.position;
            menu.add(0,CONTEXT_MENU_EDIT,0,"Edit");
            menu.add(0,CONTEXT_MENU_DEL ,0,"Delete");
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            int dot = 200;      // Length of a Morse Code "dot" in milliseconds
            int dash = 500;     // Length of a Morse Code "dash" in milliseconds
            int short_gap = 2000;    // Length of Gap Between dots/dashes
            int medium_gap = 5000;   // Length of Gap Between Letters
            int long_gap = 10000;    // Length of Gap Between Words

            vibrator.vibrate(100);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId()==CONTEXT_MENU_EDIT) {
            Toast.makeText(this, "Bạn muốn sủa sinh viên " + position, Toast.LENGTH_SHORT).show();
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.custom_dialog_edit);
            TextView tv_id = (TextView) dialog.findViewById(R.id.tv_dal_id2);
            Button btn_cancle = (Button) dialog.findViewById(R.id.btn_cancel);
            Button btn_save = (Button) dialog.findViewById(R.id.btn_save);
            final EditText edt_name = (EditText) dialog.findViewById(R.id.edt_dal_name);
            final EditText edt_number = (EditText) dialog.findViewById(R.id.edt_dal_number);
            final EditText edt_email = (EditText) dialog.findViewById(R.id.edt_dal_email);
            final EditText edt_address = (EditText) dialog.findViewById(R.id.edt_dal_address);
            Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/Geogrotesque-Light.otf");
            edt_name.setTypeface(typeface);
            edt_number.setTypeface(typeface);
            edt_email.setTypeface(typeface);
            edt_address.setTypeface(typeface);
            typeface = Typeface.createFromAsset(getAssets(),"fonts/FS Siruca.ttf");
            tv_id.setTypeface(typeface);
            btn_save.setTypeface(typeface);
            btn_cancle.setTypeface(typeface);
            final Student student = arrayList.get(position);
            edt_name.setText(student.getName());
            edt_number.setText(student.getNumber());
            edt_email.setText(student.getEmail());
            edt_address.setText(student.getAddress());
            dialog.show();
            btn_cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            btn_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    arrayList.get(position).setName(edt_name.getText().toString());
                    arrayList.get(position).setNumber(edt_number.getText().toString());
                    arrayList.get(position).setEmail(edt_email.getText().toString());
                    arrayList.get(position).setAddress(edt_address.getText().toString());
                    customAdapterListViewStudent.notifyDataSetChanged();
                    MainActivity.arrEdit.add(arrayList.get(position));
                    dialog.dismiss();
                }
            });
            return true;
        }
        if(item.getItemId()==CONTEXT_MENU_DEL){
            MainActivity.listDel.add(position);
            arrayList.remove(position);
            customAdapterListViewStudent.notifyDataSetChanged();
            return true;
        }
        return false;
    }
}
