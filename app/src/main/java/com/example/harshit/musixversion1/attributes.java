package com.example.harshit.musixversion1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class attributes extends AppCompatActivity {

    TextView name,country,id,rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attributes);
        name=(TextView)findViewById(R.id.name);
        country=(TextView)findViewById(R.id.house);
        id=(TextView)findViewById(R.id.pageRank);
        rating=(TextView)findViewById(R.id.books);
       try {
           GOTChar gotchar = (GOTChar) (attributes.this.getIntent().getSerializableExtra("gotchar"));
       name.setText("Name--"+gotchar.name);
        country.setText("Country--"+gotchar.country);
        id.setText("id  "+gotchar.id);
        rating.setText("rating"+gotchar.rating);
       }catch (Exception exp){}


    }
}
