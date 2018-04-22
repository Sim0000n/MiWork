package com.example.android.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private AudioManager mAudioManger;
    private MediaPlayer mMediaPlayer;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            if(i==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK ||
                    i==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT){
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(i);
            }else if(i==AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }else if(i==AudioManager.AUDIOFOCUS_GAIN){
                mMediaPlayer.start();
            }

        }
    };

    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        mAudioManger = (AudioManager)this.getSystemService(AUDIO_SERVICE);

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
                int result = mAudioManger.requestAudioFocus(mOnAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mMediaPlayer = MediaPlayer.create(ColorsActivity.this,word.getMediaResourceID());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        if(mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer=null;
        }
        mAudioManger.abandonAudioFocus(mOnAudioFocusChangeListener);
    }
}
