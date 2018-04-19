package com.example.android.miwok;

import android.annotation.TargetApi;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);
        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Where are you going?","minto wuksus",R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?","tinnə oyaase'nə",R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...","oyaaset...",R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?","michəksəs?",R.raw.phrase_are_you_coming));
        words.add(new Word("I’m feeling good.","kuchi achit?",R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?","əənəs'aa?",R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.","həə’ əənəm",R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.","əənəm",R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.","yoowutis",R.raw.phrase_lets_go));
        words.add(new Word("Come here.","ənni'nem",R.raw.phrase_come_here));

        WordAdapter wordAdapter = new WordAdapter(this,words);
        wordAdapter.setColorResourceID(R.color.category_phrases);
        ListView listView = (ListView)findViewById(R.id.phrases_list);
        listView.setAdapter(wordAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = words.get(adapterView.getPositionForView(view));
                final MediaPlayer mMediaPlayer = MediaPlayer.create(PhrasesActivity.this,word.getMediaResourceID());
                mMediaPlayer.start();
//                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
//                    public void onCompletion(MediaPlayer mediaPlayer){
//                        Toast.makeText(PhrasesActivity.this,"End",Toast.LENGTH_SHORT).show();
//                        if(mMediaPlayer != null)
//                            mMediaPlayer.release();
//                    }
//
//                });
            }
        });

    }

//    private void releaseMediaPlayer(MediaPlayer){
//        if()
//    }
}
