package com.example.android.miwok;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.content.Context.AUDIO_SERVICE;

public class NumbersFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_numbers,container,false);

        mAudioManager = (AudioManager)getActivity().getSystemService(AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(R.drawable.number_one,"one","lutti",R.raw.number_one));
        words.add(new Word(R.drawable.number_two,"two","otiiko",R.raw.number_two));
        words.add(new Word(R.drawable.number_three,"three","tolookosu",R.raw.number_three));
        words.add(new Word(R.drawable.number_four,"four","oyyisa",R.raw.number_four));
        words.add(new Word(R.drawable.number_five,"five","massokka",R.raw.number_five));
        words.add(new Word(R.drawable.number_six,"six","temmokka",R.raw.number_six));
        words.add(new Word(R.drawable.number_seven,"seven","kenekaku",R.raw.number_seven));
        words.add(new Word(R.drawable.number_eight,"eight","kawinta",R.raw.number_eight));
        words.add(new Word(R.drawable.number_nine,"nine","wo’e",R.raw.number_nine));
        words.add(new Word(R.drawable.number_ten,"ten","na’aacha",R.raw.number_ten));

        final WordAdapter wordAdapter = new WordAdapter(getActivity(), words);
        wordAdapter.setColorResourceID(R.color.category_numbers);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(wordAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = words.get(adapterView.getPositionForView(view));
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mMediaPlayer = MediaPlayer.create(getActivity(),word.getMediaResourceID());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
                }
            }
        });
        return rootView;
    }

    private AudioManager mAudioManager;
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
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        if(mMediaPlayer!=null)
            mMediaPlayer.release();
        mMediaPlayer = null;
        mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
    }



}
