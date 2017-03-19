package com.example.amirael_ewady.gucmapapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        con =(Button)findViewById(R.id.button);
    }


    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(getApplicationContext(), "The GUC Indoor Density Map..", Toast.LENGTH_LONG).show();

        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent mainIntent = new Intent(MainActivity.this, scan.class);
                MainActivity.this.startActivity(mainIntent);
            }
        });


       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 4000);*/

    }
}
