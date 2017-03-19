package com.example.amirael_ewady.gucmapapp2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Amira El-Ewady on 5/18/2016.
 */
public class MyCustomView extends View {

    public Context context;
    private String JSONString;
    private JSONObject Object;
    private JSONArray Array;
    private String TAG="JSON";
    private static int count1=0;
    private static int count2=0;
    private static int count3=0;




    public MyCustomView(Context context)
    {
        super(context);
        this.context = context;


    }

    public MyCustomView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;

    }

    public MyCustomView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        this.context = context;

    }



    public void setContext(Context context) {
        this.context = context;
    }

    protected void onDraw(Canvas canvas){

        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inScaled = false;
        // bitmap = BitmapFactory.decodeResource(mContext, R.drawable.map, opts);

        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.map,opts);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawBitmap(bm, 0, 0, paint);

        JSONString = BackgroundTask.json;


        try {
            Object= new JSONObject(JSONString.toString());
            Array = Object.getJSONArray("LocationsCount");
            count1 = Array.getJSONObject(0).getInt("count");
            count2 =Array.getJSONObject(1).getInt("count");
            count3 =Array.getJSONObject(2).getInt("count");






           /* for(int i=0; i<Array.length();i++){
                JSONObject post = Array.getJSONObject(i);
                Log.d(TAG, post.getString("Location") + "," + post.getInt("count"));

            }*/
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //RECTANGLE1
        paint.setARGB(170, 0, 0, 255);
        canvas.drawRect(642F, 29, 682F, 145F, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(15);
        paint.setAntiAlias(true);
        canvas.drawText("0", 650, 70, paint);


        //RECTANGLE2
        if(count1 >= 1 && count1 <10) {
            //color Green
            paint.setARGB(200, 0,128,0);
        }else{
            //color Blue
            paint.setARGB(170, 0, 0, 255);

        }
        canvas.drawRect(542F, 29, 642F, 145F, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(15);
        paint.setAntiAlias(true);
        canvas.drawText(count1 + "", 585, 70, paint);

        //RECTANGLE3
        if(count2 >=1 && count2 <10) {
            paint.setARGB(200, 0,128,0);
        }else {
            paint.setARGB(170, 0, 0, 255);

        }
        canvas.drawRect(314F, 29, 414F, 145F, paint);


        paint.setColor(Color.BLACK);
        paint.setTextSize(15);
        paint.setAntiAlias(true);
        canvas.drawText(count2 + "", 360, 70, paint);

        //RECTANGLE4
        if(count3 >=1 && count3 <10 ){
            paint.setARGB(200, 0,128,0);

        }else {

            paint.setARGB(170, 0, 0, 255);
        }
        canvas.drawRect(217F, 29, 314F, 145F, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(15);
        paint.setAntiAlias(true);
        canvas.drawText(count3 + "", 250, 70, paint);


        //RECTANGLE5
        paint.setARGB(170, 0, 0, 255);
        canvas.drawRect(127F, 29, 218F, 145F, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(15);
        paint.setAntiAlias(true);
        canvas.drawText("0", 170, 70, paint);

        //RECTANGLE6
        paint.setARGB(170, 0, 0, 255);
        canvas.drawRect(0F, 29, 126F, 266F, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(15);
        paint.setAntiAlias(true);
        canvas.drawText("0",60, 70, paint);

        ///////LOWER RECTANGLES

        //RECTANGLE1
        paint.setARGB(170, 0, 0, 255);
        canvas.drawRect(642F, 167, 690F, 215F, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(15);
        paint.setAntiAlias(true);
        canvas.drawText("0", 650, 200, paint);


        //RECTANGLE2
        paint.setARGB(170, 0, 0, 255);
        canvas.drawRect(595F, 167, 643F, 215F, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(15);
        paint.setAntiAlias(true);
        canvas.drawText("0", 625, 200, paint);

        //RECTANGLE3
        paint.setARGB(170, 0, 0, 255);
        canvas.drawRect(537F, 167, 585F, 215F, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(15);
        paint.setAntiAlias(true);
        canvas.drawText("0", 550, 200, paint);


        //RECTANGLE4
        paint.setARGB(170, 0, 0, 255);
        canvas.drawRect(490F, 167, 537F, 215F, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(15);
        paint.setAntiAlias(true);
        canvas.drawText("0", 520, 200, paint);


        //RECTANGLE5
        paint.setARGB(170, 0, 0, 255);
        canvas.drawRect(415F, 167, 462F, 215F, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(15);
        paint.setAntiAlias(true);
        canvas.drawText("0", 427, 200, paint);


        //RECTANGLE6
        paint.setARGB(170, 0, 0, 255);
        canvas.drawRect(368F, 167, 415F, 215F, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(15);
        paint.setAntiAlias(true);
        canvas.drawText("0", 397, 200, paint);



        //RECTANGLE7
        paint.setARGB(170, 0, 0, 255);
        canvas.drawRect(313F, 167, 360F, 215F, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(15);
        paint.setAntiAlias(true);
        canvas.drawText("0", 320, 200, paint);

        //RECTANGLE8
        paint.setARGB(170, 0, 0, 255);
        canvas.drawRect(265F, 167, 313F, 215F, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(15);
        paint.setAntiAlias(true);
        canvas.drawText("0", 295, 200, paint);

        //RECTANGLE9
        paint.setARGB(170, 0, 0, 255);
        canvas.drawRect(215F, 167, 261F, 215F, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(15);
        paint.setAntiAlias(true);
        canvas.drawText("0", 223, 200, paint);

        //RECTANGLE10 , under upper rectangle6
        paint.setARGB(170, 0, 0, 255);
        canvas.drawRect(0F, 267, 72F, 379F, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(15);
        paint.setAntiAlias(true);
        canvas.drawText("0", 30, 300, paint);



        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        paint.setAntiAlias(true);
        canvas.drawText("Number of connected devices", 400, 295, paint);

        paint.setARGB(170,255,0,0);
        canvas.drawRect(400F, 317, 440F, 360F, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        paint.setAntiAlias(true);
        canvas.drawText("> 30", 450, 345, paint);


        paint.setARGB(170,255,165,0);
        canvas.drawRect(400F, 367, 440F, 410F, paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        paint.setAntiAlias(true);
        canvas.drawText("20-30", 450, 395, paint);


        paint.setARGB(150,255,255,0);
        canvas.drawRect(400F, 417, 440F, 460F, paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        paint.setAntiAlias(true);
        canvas.drawText("10-20", 450, 445, paint);

        paint.setARGB(200, 0,128,0);
        canvas.drawRect(400F, 467, 440F, 510F, paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        paint.setAntiAlias(true);
        canvas.drawText("1-10", 450, 495, paint);

        paint.setARGB(170, 0, 0, 255);
        canvas.drawRect(400F, 517, 440F, 560F, paint);


        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        paint.setAntiAlias(true);
        canvas.drawText("0- empty", 450, 545, paint);

    }
}

