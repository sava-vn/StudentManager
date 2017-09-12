package com.example.mrsang.studentmanager;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mr.Sang on 9/10/2017.
 */

public class Student implements Parcelable {
    private int id;
    private String name;
    private String number;
    private String email;
    private String address;

    public Student() {
    }

    protected Student(Parcel in) {
        id = in.readInt();
        name = in.readString();
        number = in.readString();
        email = in.readString();
        address = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student(String name, String number, String email, String address) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
    }

    public Student(int id, String name, String number, String email, String address) {
        this.id = id;

        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(number);
        parcel.writeString(email);
        parcel.writeString(address);
    }
}
