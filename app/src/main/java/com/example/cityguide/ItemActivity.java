package com.example.cityguide;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.Locale;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class ItemActivity extends AppCompatActivity {//implements OnMapReadyCallback {

    private String itemName;
    private String description;
    private MapView mMapView;
    private TextToSpeech textToSpeech;
    private VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event);
        TextView eventNameTV = findViewById(R.id.eventNameTV);
        TextView descriptionTV = findViewById(R.id.descriptionTV);
        ImageView imageView = findViewById(R.id.imageView);
        ImageView imageView2 = findViewById(R.id.imageView2);
        ImageView imageView3 = findViewById(R.id.imageView3);

        // Get proper data from resources
        String itemID = getIntent().getStringExtra("id");
        int itemNameID = getResources().getIdentifier(itemID + "Name", "string" , getPackageName());
        int itemDescriptionID = getResources().getIdentifier(itemID + "Description", "string" , getPackageName());
        int ivID = getResources().getIdentifier(itemID + "a", "drawable", getPackageName());
        int iv2ID = getResources().getIdentifier(itemID + "b", "drawable", getPackageName());
        int iv3ID = getResources().getIdentifier(itemID + "c", "drawable", getPackageName());

        itemName = getString(itemNameID);
        description = getString(itemDescriptionID);

        // Set views with data
        eventNameTV.setText(itemName);
        descriptionTV.setText(description);
        imageView.setImageResource(ivID);
        imageView2.setImageResource(iv2ID);
        imageView3.setImageResource(iv3ID);

        //mMapView = (MapView) findViewById(R.id.mapView);
        //mMapView.onCreate(savedInstanceState);
        //mMapView.getMapAsync(this);

        if (itemID.contains("place")){

            // Voice description
            Button audioButton = findViewById(R.id.audioButton);
            audioButton.setVisibility(View.VISIBLE);

            textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int i) {
                    textToSpeech.setLanguage(Locale.US);
                }
            });

            // Video
            videoView = findViewById(R.id.videoView);
            MediaController mediaController= new MediaController(this);
            mediaController.setAnchorView(videoView);

            int vidID = getResources().getIdentifier(itemID + "_vid", "raw", getPackageName());
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + vidID);

            System.out.println(uri.toString());

            videoView.setMediaController(mediaController);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();


        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void TextToSpeechButton(View view){
        textToSpeech.speak(description, TextToSpeech.QUEUE_FLUSH, null,null);
    }

    @Override
    public void onPause() {
        super.onPause();

        textToSpeech.shutdown();
        videoView.pause();
    }

    @Override
    public void onStop() {
        super.onStop();

        textToSpeech.shutdown();
        if (videoView!=null) {
            videoView.stopPlayback();
        }
    }

/*    @Override
    public void onMapReady(GoogleMap map) {

        // 51.109126, 17.032759
        map.addMarker(new MarkerOptions().position(new LatLng(51.109126, 17.032759)).title("Marker"));
    }*/
}
