package com.example.lakshmikanta.sampleui.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lakshmi Kanta on 3/28/2016.
 */
public class Item implements Parcelable {
    public String title="", range="", siRange="";
    public boolean groupHeader;
    public Item(){}

    protected Item(Parcel in) {
        title = in.readString();
        range = in.readString();
        siRange = in.readString();
        groupHeader = in.readByte() != 0;
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(range);
        dest.writeString(siRange);
        dest.writeByte((byte) (groupHeader ? 1 : 0));
    }
}
