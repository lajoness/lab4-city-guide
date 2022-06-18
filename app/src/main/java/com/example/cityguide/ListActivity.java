package com.example.cityguide;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class ListActivity extends AppCompatActivity {

    ArrayList<String> itemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
            }

    @Override
    protected void onStart() {
        super.onStart();

        TextView listNameTV = findViewById(R.id.listNameTV);
        ListView listView = findViewById(R.id.listView);

        String type = getIntent().getStringExtra("type");

        // Wypelnic liste

        listNameTV.setText(type);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_view, itemList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            Map<String, String> classMap = new HashMap<String, String>() {{
                put(String.valueOf(R.string.events), "event");
                put(String.valueOf(R.string.places), "place");
                put(String.valueOf(R.string.acc), "acc");
                put(String.valueOf(R.string.tours), "tour");
            }};

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent itemIntent = new Intent(getApplicationContext(), ItemActivity.class);
                itemIntent.putExtra("id", classMap.get(type) + (position + 1));
                startActivity(itemIntent);
            }
        });

    }



}
