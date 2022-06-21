package com.example.cityguide;

import android.content.Context;

import java.util.ArrayList;

public class Tour {

    private String tourName;
    private String description;

    private ArrayList itemList;

    public String getTourName() {
        return tourName;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList itemList) {
        this.itemList = itemList;
    }

    public Tour(Context context, String itemID) {

        int tourNameID = context.getResources().getIdentifier(itemID + "Name", "string" , context.getPackageName());
        int itemDescriptionID = context.getResources().getIdentifier(itemID + "Description", "string" , context.getPackageName());
        tourName = context.getString(tourNameID);
        description = context.getString(itemDescriptionID);

        itemList = new ArrayList();
    }
}
