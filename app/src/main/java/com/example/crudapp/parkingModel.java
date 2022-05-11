package com.example.crudapp;

import android.os.Parcel;
import android.os.Parcelable;

public class parkingModel implements Parcelable{
    private String pname;
    private String avail;
    private String parkingid;
    private String contact;
    private String fees;

    public parkingModel() {

    }

    protected parkingModel(Parcel in) {
        pname = in.readString();
        avail = in.readString();
        parkingid = in.readString();
        contact = in.readString();
        fees = in.readString();
    }

    public static final Parcelable.Creator<parkingModel> CREATOR = new Parcelable.Creator<parkingModel>() {
        @Override
        public parkingModel createFromParcel(Parcel in) {
            return new parkingModel(in);
        }

        @Override
        public parkingModel[] newArray(int size) {
            return new parkingModel[size];
        }
    };

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public static Creator<parkingModel> getCREATOR() {
        return CREATOR;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getAvail() {
        return avail;
    }

    public void setAvail(String avail) {
        this.avail = avail;
    }

    public String getParkingid() {
        return parkingid;
    }

    public void setParkingid(String parkingid) {
        this.parkingid = parkingid;
    }

    public parkingModel(String pname, String avail, String parkingid, String contact, String fees) {
        this.pname = pname;
        this.avail = avail;
        this.parkingid = parkingid;
        this.contact = contact;
        this.fees = fees;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(pname);
        parcel.writeString(avail);
        parcel.writeString(parkingid);
        parcel.writeString(contact);
        parcel.writeString(fees);
    }
}
