package com.example.amirael_ewady.gucmapapp2;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Handler;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;

public class scan extends AppCompatActivity implements BeaconConsumer {


    private BeaconManager beaconManager;
    public static final String TAG = "beacons everywhere";
    private TextView location;
    private String mac;
    public ArrayList<String> BeaconAddress = new ArrayList<String>();//ArrayList with all scanned MacAddresses.
    public String Add; //Address that will be added to ArrayList having all scanned macAddresses to compare RSSI values.
    public int RSSI; //The RSSI value for each scanned MacAddress.
    public ArrayList<String> Array = new ArrayList<String>();
    //El Array el fiha kol [MacAddress,RSSI]
    public static String yourLocation; //current MacAddress that will be sent to the database,its count will be incremented.

    public BluetoothAdapter ble;
    public String userAddress;  //user's smartPhone bluetooth Address

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser()
                .setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
        beaconManager.bind(this);

       // Toast.makeText(getApplicationContext(), " Loading Your Information..", Toast.LENGTH_LONG).show();


        ble = BluetoothAdapter.getDefaultAdapter();
        userAddress = ble.getAddress(); //save the smartPhone's bluetooth address


    }


    protected void onStart(){
        super.onStart();

        spinner = (Spinner)findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.places, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

                //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();


                switch (position) {
                    case 0:
                        break;

                    case 1:
                        Toast.makeText(parent.getContext(), "No Map", Toast.LENGTH_LONG).show();

                        //getLocationCount();
                        // intent = new Intent(scanner.this, MapActivity.class);
                        // startActivity(intent);
                        break;
                    case 2:
                        getLocationCount();
                        //intent = new Intent(scanner.this, MapActivity.class);
                        //startActivity(intent);

                        break;
                    case 3:
                        Toast.makeText(parent.getContext(), "No Map", Toast.LENGTH_LONG).show();

                        break;

                }
                //startActivity(intent);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        if(ble.isEnabled()) {
            //1-Add to Database
            int secondsDelayed = 5;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    DeviceConnected();
                    Log.d(TAG, "DeviceConnected is executed");
                }
            }, secondsDelayed * 1000);

            //2-Show me my location
            int secondsDelayed4 = 8;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    location();
                }
            }, secondsDelayed4 * 1000);

            //3-remove from Database and update.
            int secondsDelayed2 = 30;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    disconnect();
                    Toast.makeText(getApplicationContext(), "Reupdating Your Information", Toast.LENGTH_LONG).show();

                    Intent i2 = new Intent(scan.this,scan.class);
                    startActivity(i2);

                }
            }, secondsDelayed2 * 1000);

        }else {
            Toast.makeText(getApplicationContext(), "Cannot proceed unless Bluetooth is turned on", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }

    @Override
    public void onBeaconServiceConnect() {


        final Region region = new Region("myBeacons",null,null,null);


        beaconManager.setMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {

                try {
                    Log.d(TAG, "Did Enter Region");
                    beaconManager.startRangingBeaconsInRegion(region);

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void didExitRegion(Region region) {

                try {
                    Log.d(TAG, "Did Exit Region");

                    beaconManager.stopRangingBeaconsInRegion(region);

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void didDetermineStateForRegion(int i, Region region) {

            }
        });



        beaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                for (Beacon oneBeacon : beacons) {
                    yourLocation = "";
                    Add = oneBeacon.getBluetoothAddress();
                    RSSI = oneBeacon.getRssi();

                    Log.d(TAG, "MacAddress: " + oneBeacon.getBluetoothAddress() + "RSSI: " + oneBeacon.getRssi() + "Distance: " + oneBeacon.getDistance());

                    if (!BeaconAddress.contains(Add)) {
                        BeaconAddress.add(Add);
                    }

                    for (int i = 0; i < BeaconAddress.size(); i++) {

                        Log.d(TAG, "Elements inside the ArrayList are:" + BeaconAddress.get(i));
                    }

                    if (!Array.contains(Add)) {
                        Array.add(Add);
                        Array.add(RSSI + "");
                    }

                    yourLocation = getLocation(Array);
                    Log.d(TAG, "You're near Beacon with Mac = " + yourLocation);


                }

            }
        });

        try {
            beaconManager.startRangingBeaconsInRegion(region);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    //Method that compares between RSSI values and returns the MacAddress with highest RSSI.
    public String getLocation(ArrayList<String> array){
        ArrayList<String> Mac  = new ArrayList<>();
        ArrayList<Integer> Rssi   = new ArrayList<>();
        int max;
        int index;
        String MacLocation;


        for(int i=0; i<array.size(); i=i+2){
            Mac.add(array.get(i));
        }
        for(int j=1; j<array.size(); j=j+2){
            Rssi.add(Integer.parseInt(array.get(j)));
        }
        max= Rssi.get(0);
        for(int k=0; k<Rssi.size();k++){

            if(Rssi.get(k)>max){
                max = Rssi.get(k);
                index=k;
                MacLocation = Mac.get(index);
                return MacLocation;
            } else{
                MacLocation =Mac.get(0);
                return MacLocation;
            }

        }


        return null;
    }

    public void DeviceConnected() {

        String method = "update";

        BackgroundTask backgroudTask = new BackgroundTask(this);
        String Address = yourLocation;
        backgroudTask.execute(method, Address, userAddress);


    }
    public void location(){
        String method = "locate";
        BackgroundTask backgroudTask = new BackgroundTask(this);

        String LocLoc = yourLocation;

        backgroudTask.execute(method, LocLoc);
    }

    public void disconnect(){

        String method = "decrement";
        BackgroundTask backgroudTask = new BackgroundTask(this);
        String Address = yourLocation;
        backgroudTask.execute(method, Address, userAddress);


    }

    public void getLocationCount(){
        String method = "getCount";
        BackgroundTask backgroudTask = new BackgroundTask(this);
        // String Address = currentMac;

        backgroudTask.execute(method);
    }
}
