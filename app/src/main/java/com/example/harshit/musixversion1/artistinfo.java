


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

public class artistinfo extends AppCompatActivity {

    // List view
    private ListView lva;
    // Listview Adapter
    ArrayAdapter<GOTChar> adapter1;

    // Search EditText
    EditText inputSearch1;
Button but2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artistinfo);
        String url = "http://api.musixmatch.com/ws/1.1/chart.artists.get?page=1&page_size=100&country=US&apikey=e6d9758391fb7f2959f2a4158969f134";
        lva=(ListView) findViewById(R.id.lva2);
        but2=(Button)findViewById(R.id.back);
        inputSearch1=(EditText)findViewById(R.id.inputSearch1);
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //mTextView.setText("Response: " + response.toString());
try {
    JSONObject message = response.getJSONObject("message");
    JSONObject body = message.getJSONObject("body");
    JSONArray artistlist = body.getJSONArray("artist_list");

     ArrayList<GOTChar> artistnames = new ArrayList<GOTChar>();

    for (int i = 0; i < artistlist.length(); i++) {
        JSONObject a1 = artistlist.getJSONObject(i);
        JSONObject artists = a1.getJSONObject("artist");
        GOTChar gotchar=new GOTChar();
        gotchar.name = artists.getString("artist_name");
        gotchar.country = artists.getString("artist_country");
        gotchar.rating = artists.getString("artist_rating");
        gotchar.id=  artists.getString("artist_id");

        artistnames.add(gotchar);
    }
    adapter1 = new ArrayAdapter<GOTChar>(artistinfo.this,
            android.R.layout.simple_list_item_1, artistnames);

   lva.setAdapter(adapter1);
    lva.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            Intent i2=new Intent(artistinfo.this,attributes.class);
            Bundle extras=new Bundle();
            //extras.putString("name",name);

            extras.putSerializable("gotchar", (GOTChar)lva.getItemAtPosition(position) );

            i2.putExtras(extras);
            startActivity(i2);


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
                });
        queue.add(jsonObjectRequest);


        inputSearch1 = (EditText) findViewById(R.id.inputSearch1);

        inputSearch1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                if (artistinfo.this.adapter1 != null) {
                  artistinfo.this.adapter1.getFilter().filter(cs);
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
    public void gototracks(View view) {
        Intent i=new Intent(artistinfo.this,MainActivity.class);
        startActivity(i);
        finish();
    }



}