package com.example.amirael_ewady.gucmapapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;

public class MapActivity extends AppCompatActivity {

    private String JSONString;
    private JSONObject Object;
    private JSONArray Array;
    private String TAG="JSON";
    MyCustomView myView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        myView = (MyCustomView)findViewById(R.id.view);
        myView.invalidate();
        myView.requestLayout();

       /* JSONString = getIntent().getExtras().getString("JsonString");

        try {
            Object= new JSONObject(JSONString.toString());
            Array = Object.getJSONArray("LocationsCount");




            for(int i=0; i<Array.length();i++){
                JSONObject post = Array.getJSONObject(i);
                Log.d(TAG, post.getString("Location") + ","+ post.getInt("count"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

*/

    }
}