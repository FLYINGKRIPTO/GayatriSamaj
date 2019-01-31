package com.example.dell.jaapactivity;

import android.os.Parcel;
import android.os.Parcelable;

public class parsedData implements Parcelable {
    public String Address;
    public String id;
    public String name;
    public parsedData(String Address,String id,String name){
        this.Address=Address;
        this.id=id;
        this.name=name;
    }

    protected parsedData(Parcel in) {
        Address = in.readString();
        id = in.readString();
        name = in.readString();
    }

    public String getAddress() {
        return Address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static final Creator<parsedData> CREATOR = new Creator<parsedData>() {
        @Override
        public parsedData createFromParcel(Parcel in) {
            return new parsedData(in);
        }

        @Override
        public parsedData[] newArray(int size) {
            return new parsedData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Address);
        dest.writeString(id);
        dest.writeString(name);
    }
}
