package com.example.cityguide;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class TourActivity extends AppCompatActivity {

    Tour tour;
    String tourID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);

        tourID = getIntent().getStringExtra("id");
        tour = new Tour(this, tourID);
        setDummyItemList(tourID);
    }

    @Override
    protected void onStart() {
        super.onStart();

        TextView tourNameTV = findViewById(R.id.itemNameTV);
        TextView descriptionTV = findViewById(R.id.descriptionTV);
        tourNameTV.setText(tour.getTourName());
        descriptionTV.setText(tour.getDescription());

        ListView tourListView = findViewById(R.id.tourListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_view, (List)tour.getItemList());
        tourListView.setAdapter(adapter);
    }

    public void setDummyItemList(String tourID){

        ArrayList itemList = new ArrayList<Item>();

        int tourNum = Character.getNumericValue(tourID.charAt(tourID.length() - 1));

        switch (tourNum){
            case 1:
                itemList.add(getString(R.string.place1Name));
                itemList.add(getString(R.string.place4Name));
                break;
            case 2:
                itemList.add(getString(R.string.place1Name));
                itemList.add(getString(R.string.place2Name));
                itemList.add(getString(R.string.place3Name));
                itemList.add(getString(R.string.place4Name));
                itemList.add(getString(R.string.place5Name));
                break;
            case 3:
                itemList.add(getString(R.string.place2Name));
                itemList.add(getString(R.string.place5Name));
                break;

            default:
                ;
        }

        tour.setItemList(itemList);
    }
}
