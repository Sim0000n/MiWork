package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word(R.drawable.color_red,"red","weṭeṭṭi",R.raw.color_red));
        words.add(new Word(R.drawable.color_green,"green","chokokki",R.raw.color_green));
        words.add(new Word(R.drawable.color_brown,"brown","ṭakaakki",R.raw.color_brown));
        words.add(new Word(R.drawable.color_gray,"gray","ṭopoppi",R.raw.color_gray));
        words.add(new Word(R.drawable.color_black,"black","kululli",R.raw.color_black));
        words.add(new Word(R.drawable.color_white,"white","kelelli",R.raw.color_white));
        words.add(new Word(R.drawable.color_dusty_yellow,"dusty yellow","ṭopiisə",R.raw.color_dusty_yellow));
        words.add(new Word(R.drawable.color_mustard_yellow,"mustard yellow","chiwiiṭə",R.raw.color_mustard_yellow));



        WordAdapter wordAdapter = new WordAdapter(this,words);
        wordAdapter.setColorResourceID(R.color.category_colors);
        ListView listView = (ListView)findViewById(R.id.colors_list);
        listView.setAdapter(wordAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = words.get(adapterView.getPositionForView(view));
                MediaPlayer mMediaPlayer = MediaPlayer.create(ColorsActivity.this,word.getMediaResourceID());
                mMediaPlayer.start();
            }
        });
    }
}
