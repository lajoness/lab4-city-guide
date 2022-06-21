package com.example.cityguide;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.Locale;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class ItemActivity extends AppCompatActivity {

    private Item item;
    private TextToSpeech textToSpeech;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_item);
        TextView itemNameTV = findViewById(R.id.itemNameTV);
        TextView descriptionTV = findViewById(R.id.descriptionTV);
        ImageView imageView = findViewById(R.id.imageView);
        ImageView imageView2 = findViewById(R.id.imageView2);
        ImageView imageView3 = findViewById(R.id.imageView3);

        String itemID = getIntent().getStringExtra("id");
        item = new Item(this, itemID);

        // Set views with data
        itemNameTV.setText(item.getItemName());
        descriptionTV.setText(item.getDescription());
        imageView.setImageResource(item.getImageID1());
        imageView2.setImageResource(item.getImageID2());
        imageView3.setImageResource(item.getImageID3());

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

            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + item.getVideoID());

            videoView.setVisibility(View.VISIBLE);
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void TextToSpeechButton(View view){
        textToSpeech.speak(item.getDescription(), TextToSpeech.QUEUE_FLUSH, null,null);
    }

    @Override
    public void onPause() {
        super.onPause();

        if (textToSpeech!=null) {
            textToSpeech.shutdown();
        }
        if (videoView!=null) {
            videoView.stopPlayback();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if (textToSpeech!=null) {
            textToSpeech.shutdown();
        }
        if (videoView!=null) {
            videoView.stopPlayback();
        }
    }
}
