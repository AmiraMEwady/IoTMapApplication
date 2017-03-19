package com.example.amirael_ewady.gucmapapp2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask<String, Void, String> {

    public String JsonString;
    AlertDialog alertDialog;
    Context context;
    public static String json;

    private String TAG="try";
    BackgroundTask(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Getting Your Location");
        //super.onPreExecute();
    }
    protected String doInBackground(String... params){

        String updateURL = "http://192.168.1.107/webapp/tryWithoutLoop.php";
        // String updateURL = "http://192.168.43.252/webapp/tryWithoutLoop.php";

        //String LocationURL = "http://192.168.43.252/webapp/getLocation.php";
        String LocationURL = "http://192.168.1.107/webapp/getLocation.php";

        // String decrementURL = "http://192.168.43.252/webapp/decrementCount.php";
        String decrementURL = "http://192.168.1.107/webapp/decrementCount.php";

        String countURL = "http://192.168.1.107/webapp/retrieveAll.php";
        // String countURL = "http://192.168.43.252/webapp/retrieveAll.php";

        String weightURL ="http://192.168.1.107/webapp/updateWeight.php";


        String method = params[0];
        if(method.equals("update")){

            String MacAddress  = params[1];
            String userAddress =params[2];

            if(MacAddress != null && userAddress != null) {
                try {


                    URL url = new URL(updateURL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("Address", "UTF-8") + "=" + URLEncoder.encode(MacAddress, "UTF-8") + "&" + URLEncoder.encode("userAddress", "UTF-8") + "=" + URLEncoder.encode(userAddress, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream IS = httpURLConnection.getInputStream();
                    IS.close();
                    return "Successfully scanned, You can view the Map now";


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                //Toast.makeText(context,"Rescannig,please wait", Toast.LENGTH_LONG).show();
                return "rescan";

            }

        }else if(method.equals("locate")){
            String MacAddress = params[1];



            try {
                URL url = new URL(LocationURL);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("Address", "UTF-8") +"="+URLEncoder.encode(MacAddress,"UTF-8") ;
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response ="";
                String line = "";
                while ((line = bufferedReader.readLine())!= null){
                    response += line;

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return "Your Location is "+response;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(method.equals("decrement")){


            String MacAddress  = params[1];
            String userAddress =params[2];


            try {
                URL url = new URL(decrementURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data = URLEncoder.encode("Address", "UTF-8") +"="+URLEncoder.encode(MacAddress,"UTF-8")+"&"+ URLEncoder.encode("userAddress", "UTF-8") +"="+URLEncoder.encode(userAddress,"UTF-8") ;
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                return "updating";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(method.equals("getCount")){
            //String MacAddress = params[1];

            try {
                URL url = new URL(countURL);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
               /* OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("Address", "UTF-8") +"="+URLEncoder.encode(MacAddress,"UTF-8") ;
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();*/

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response ="";
                String line = "";
                while ((line = bufferedReader.readLine())!= null){
                    response += line;

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return response;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return null;
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if(result.equals("Successfully scanned, You can view the Map now") || result.equals("updating") || result.contains("Your Location is ")) {
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }else if(result.equals("rescan"))
        {
            Intent i= new Intent(context,scan.class);
            context.startActivity(i);

        }


        else{
            JsonString = result;
            json =result;

            Intent intent = new Intent(context, MapActivity.class);
            intent.putExtra("JsonString", JsonString);
            //Log.d(TAG, "put extra is executed");
            context.startActivity(intent);

            //Intent i = new Intent();
            // i.setClass(MyCustomView.getContext(), MapActivity.class);
            //startActivity(i);


        }
    }
}