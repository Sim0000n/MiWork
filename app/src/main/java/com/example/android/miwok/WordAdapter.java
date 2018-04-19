package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {

    Word currentWord;

    private int colorResourceID;
    public WordAdapter(Activity context, ArrayList<Word> word) {
        super(context,0,word);
    }

    public void setColorResourceID(int colorResourceID) {
        this.colorResourceID = colorResourceID;
    }

    public Word getCurrentWord() {
        return currentWord;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView==null)
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        currentWord = getItem(position);
        View ll = listItemView.findViewById(R.id.list_layout);
        int color = ContextCompat.getColor(getContext(),colorResourceID);
        ll.setBackgroundColor(color);
        if(currentWord.getPicture()!=0){
            ImageView miworkPicture = (ImageView)listItemView.findViewById(R.id.miwork_picture);
            miworkPicture.setImageResource(currentWord.getPicture());
        }
        TextView miworkText = (TextView)listItemView.findViewById(R.id.miwork_text);
        miworkText.setText(currentWord.getMiWork());
        TextView englishText = (TextView)listItemView.findViewById(R.id.english_text);
        englishText.setText(currentWord.getEnglish());
        return listItemView;
    }
}
