package com.example.lakshmikanta.sampleui.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Lakshmi Kanta on 3/28/2016.
 */
public class LabvaluesModel implements Parcelable {
    public String id, title, notes;
    public ArrayList<String> headers;
    public ArrayList<Item> items = new ArrayList<Item>();

    public LabvaluesModel(){}

    protected LabvaluesModel(Parcel in) {
        id = in.readString();
        title = in.readString();
        notes = in.readString();
        headers = in.createStringArrayList();
        items = in.createTypedArrayList(Item.CREATOR);
    }

    public static final Creator<LabvaluesModel> CREATOR = new Creator<LabvaluesModel>() {
        @Override
        public LabvaluesModel createFromParcel(Parcel in) {
            return new LabvaluesModel(in);
        }

        @Override
        public LabvaluesModel[] newArray(int size) {
            return new LabvaluesModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(notes);
        dest.writeStringList(headers);
        dest.writeTypedList(items);
    }
}



