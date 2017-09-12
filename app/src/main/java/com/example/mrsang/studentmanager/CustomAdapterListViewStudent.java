package com.example.mrsang.studentmanager;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mr.Sang on 9/10/2017.
 */

public class CustomAdapterListViewStudent extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Student> arrayList;

    public CustomAdapterListViewStudent(Context context, int layout, ArrayList<Student> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public ArrayList<Student> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Student> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
       return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder ;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder.imgStudent = (ImageView) view.findViewById(R.id.img_student);
            viewHolder.tvName = (TextView) view.findViewById(R.id.tv_name);
            Typeface typeface = Typeface.createFromAsset(context.getAssets(),"fonts/Geogrotesque-Light.otf");
            viewHolder.tvName.setTypeface(typeface);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Student student = arrayList.get(i);
        viewHolder.imgStudent.setImageResource(R.drawable.icon2);
        viewHolder.tvName.setText(student.getName());
        return view;
    }
    private class ViewHolder{
        ImageView imgStudent;
        TextView tvName;
    }
}
