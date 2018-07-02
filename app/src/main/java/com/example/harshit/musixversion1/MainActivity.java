package com.example.harshit.musixversion1;
import java.io.Serializable;
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    private ListView lv;

    ArrayAdapter<tra> adapter;
    Button but;

    EditText inputSearch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         String url = "http://api.musixmatch.com/ws/1.1/chart.tracks.get?apikey=e6d9758391fb7f2959f2a4158969f134&page=1&page_size=100";
        RequestQueue queue = Volley.newRequestQueue(this);
        but=(Button)findViewById(R.id.go);
        lv=(ListView)findViewById(R.id.lv);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //mTextView.setText("Response: " + response.toString());
                        try {
                            JSONObject message = response.getJSONObject("message");
                            JSONObject body = message.getJSONObject("body");
                            JSONArray tracklist = body.getJSONArray("track_list");

                            ArrayList<tra> tracknames = new ArrayList<tra>();

                            for (int i = 0; i < tracklist.length(); i++) {
                                JSONObject a1 = tracklist.getJSONObject(i);
                                JSONObject tracks = a1.getJSONObject("track");
                             tra hi=new tra();
                               hi.trackartist =tracks.getString("artist_name");
                               hi.trackname = tracks.getString("track_name");
                               hi.album = tracks.getString("album_name");
                                hi.year=  tracks.getString("first_release_date");
                               hi.tracklyrics = tracks.getString("track_share_url");
                               try{  hi.imagelink=tracks.getString("album_coverart_100x100");
                                } catch (Exception exp) {
                                }
                                tracknames.add(hi);
                                   }
                            adapter = new ArrayAdapter<tra>(MainActivity.this,
                                    android.R.layout.simple_list_item_1, tracknames);

                            lv.setAdapter(adapter);
                            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                                    Intent i3=new Intent(MainActivity.this,trackattributes.class);
                                    Bundle extras=new Bundle();
                                    //extras.putString("name",name);

                                    extras.putSerializable("hi", (tra)lv.getItemAtPosition(position) );

                                    i3.putExtras(extras);
                                    startActivity(i3);


                                }
                            });
                        }
                        catch(Exception exp){}

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });queue.add(jsonObjectRequest);

        inputSearch = (EditText)findViewById(R.id.inputSearch);

                        inputSearch.addTextChangedListener(new TextWatcher() {

                            @Override
                            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                                // When user changed the Text
                                if (MainActivity.this.adapter != null) {
                                    MainActivity.this.adapter.getFilter().filter(cs);
                                }
                            }

                            @Override
                            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                                          int arg3) {
                                // TODO Auto-generated method stub

                            }

                            @Override
                            public void afterTextChanged(Editable arg0) {
                                // TODO Auto-generated method stub
                            }
                        });

                    }

    public void gotoartists(View view) {
        Intent i=new Intent(MainActivity.this,artistinfo.class);
        startActivity(i);
        finish();
    }


}