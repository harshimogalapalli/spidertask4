package com.example.harshit.musixversion1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.nio.file.Paths;

public class trackattributes extends AppCompatActivity {

    TextView  trackname,trackartist,album,year,tracklyrics;
    ImageView imagelink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trackattributes);
        trackname=(TextView)findViewById(R.id.trackname);
        trackartist=(TextView)findViewById(R.id.trackartist);
        tracklyrics=(TextView)findViewById(R.id.tracklyrics);
        year=(TextView)findViewById(R.id.year);
        album=(TextView)findViewById(R.id.album);
        imagelink=(ImageView)findViewById(R.id.imagelink);
   final tra hi=(tra) (trackattributes.this.getIntent().getSerializableExtra("hi"));

        Picasso.with(this).load(
            hi.imagelink).into(imagelink);
      tracklyrics.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent browser=new Intent(    Intent.ACTION_VIEW, Uri.parse(hi.tracklyrics));
          }
      });
        trackname.setText(hi.trackname);
        trackartist.setText("ARTIST---"+hi.trackartist);
        tracklyrics.setText("LYRICS--"+hi.tracklyrics);
        year.setText("YEAR---"+hi.year);
        album.setText("ALBUM---"+hi.album);




    }
}
