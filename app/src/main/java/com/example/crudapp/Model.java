package com.example.crudapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Model implements Parcelable {
    private String name;
    private String contact;
    private String vnumber;
    private String inTime;
    private String outTime;
    private String location;
    private String date;

    public Model(){

    }

    public Model(String name, String contact, String vnumber, String inTime, String outTime, String location, String date) {
        this.name = name;
        this.contact = contact;
        this.vnumber = vnumber;
        this.inTime = inTime;
        this.outTime = outTime;
        this.location = location;
        this.date = date;
    }

    protected Model(Parcel in) {
        name = in.readString();
        contact = in.readString();
        vnumber = in.readString();
        inTime = in.readString();
        outTime = in.readString();
        location = in.readString();
        date = in.readString();
    }

    public static final Creator<Model> CREATOR = new Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel in) {
            return new Model(in);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public static Creator<Model> getCREATOR() {
        return CREATOR;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getVnumber() {
        return vnumber;
    }

    public void setVnumber(String vnumber) {
        this.vnumber = vnumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(contact);
        parcel.writeString(vnumber);
        parcel.writeString(inTime);
        parcel.writeString(outTime);
        parcel.writeString(location);
        parcel.writeString(date);
    }
}


