package com.example.android.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.content.Context.AUDIO_SERVICE;

public class FamilyMembersFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_family_members,container,false);

        mAudioManager = (AudioManager)getActivity().getSystemService(AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word(R.drawable.family_father,"father","əpə",R.raw.family_father));
        words.add(new Word(R.drawable.family_mother,"mother","əṭa",R.raw.family_mother));
        words.add(new Word(R.drawable.family_son,"son","angsi",R.raw.family_son));
        words.add(new Word(R.drawable.family_daughter,"daughter","tune",R.raw.family_daughter));
        words.add(new Word(R.drawable.family_older_brother,"older brother","taachi",R.raw.family_older_brother));
        words.add(new Word(R.drawable.family_younger_brother,"younger brother","chalitti",R.raw.family_younger_brother));
        words.add(new Word(R.drawable.family_older_sister,"older sister","teṭe",R.raw.family_older_sister));
        words.add(new Word(R.drawable.family_younger_sister,"younger sister","kolliti",R.raw.family_younger_sister));
        words.add(new Word(R.drawable.family_grandmother,"grandmother","ama",R.raw.family_grandmother));
        words.add(new Word(R.drawable.family_grandfather,"grandfather","paapa",R.raw.family_grandfather));

        WordAdapter wordAdapter = new WordAdapter(getActivity(),words);
        wordAdapter.setColorResourceID(R.color.category_family);
        ListView listView = (ListView)rootView.findViewById(R.id.family_list);
        listView.setAdapter(wordAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                Word word = words.get(adapterView.getPositionForView(view));
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mMediaPlayer = MediaPlayer.create(getActivity(),word.getMediaResourceID());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mOnCompetionListener);
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

    private MediaPlayer.OnCompletionListener mOnCompetionListener = new MediaPlayer.OnCompletionListener() {
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

    private void releaseMediaPlayer(){
        if(mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        releaseMediaPlayer();
    }
}
