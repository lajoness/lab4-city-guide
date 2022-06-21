package com.example.cityguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;

public class ListActivity extends AppCompatActivity {

    ArrayList<String> itemList = new ArrayList<String>();
    Map<String, String> classMap;
    String itemType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        this.itemType = getIntent().getStringExtra("type");
        setClassMap();
        setItemList(this.itemType);
    }

    @Override
    protected void onStart() {
        super.onStart();

        TextView listNameTV = findViewById(R.id.listNameTV);
        ListView listView = findViewById(R.id.listView);

        listNameTV.setText(this.itemType);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_view, itemList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent;

                if (classMap.get(itemType) == "tour"){

                    intent = new Intent(getApplicationContext(), TourActivity.class);
                }
                else {

                    intent = new Intent(getApplicationContext(), ItemActivity.class);
                }

                intent.putExtra("id", classMap.get(itemType) + (position + 1));
                startActivity(intent);
            }
        });

    }

    private void setItemList(String type) {

        String item = classMap.get(type);

        Field[] fields = R.string.class.getFields();
        for(final Field field : fields) {

            Pattern pattern = Pattern.compile(item + "\\dName");
            Matcher matcher = pattern.matcher(field.getName());

            if (matcher.find()) {
                try {
                    int itemID = field.getInt(R.string.class);
                    itemList.add(getString(itemID));
                }
                catch(IllegalAccessException ex){}
            }
        }
    }

    private void setClassMap() {

        classMap = new HashMap<String, String>() {{
            put(getString(R.string.events), "event");
            put(getString(R.string.places), "place");
            put(getString(R.string.acc), "acc");
            put(getString(R.string.tours), "tour");
        }};
    }
}
