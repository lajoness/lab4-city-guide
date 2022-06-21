package com.example.cityguide;

import android.content.Context;

public class Item {

    private String itemName;
    private String description;
    private int imageID1;
    private int imageID2;
    private int imageID3;
    private int videoID;

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public int getImageID1() {
        return imageID1;
    }

    public int getImageID2() {
        return imageID2;
    }

    public int getImageID3() {
        return imageID3;
    }

    public int getVideoID() {
        return videoID;
    }

    public Item(Context context, String itemID) {

        int itemNameID = context.getResources().getIdentifier(itemID + "Name", "string" , context.getPackageName());
        int itemDescriptionID = context.getResources().getIdentifier(itemID + "Description", "string" , context.getPackageName());
        itemName = context.getString(itemNameID);
        description = context.getString(itemDescriptionID);

        imageID1 = context.getResources().getIdentifier(itemID + "a", "drawable", context.getPackageName());
        imageID2 = context.getResources().getIdentifier(itemID + "b", "drawable", context.getPackageName());
        imageID3 = context.getResources().getIdentifier(itemID + "c", "drawable", context.getPackageName());

        if (itemID.contains("place")) {
            videoID = context.getResources().getIdentifier(itemID + "_vid", "raw", context.getPackageName());
        }
    }
}
