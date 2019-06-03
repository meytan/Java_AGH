package com.example.lab13;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity implements SensorEventListener {

    private static final String TAG = "AnimationStarter";
    private Sensor sensor;
    private SensorManager sm;
    private Ball ball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);



        final ImageView ballImg = (ImageView) findViewById(R.id.bounceBallImage);
        final ImageView bg = (ImageView) findViewById(R.id.bg);



        ball = new Ball(ballImg);
        ball.setSize(dpTpPx(80));
        ball.setAreaH(dpTpPx(600));
        ball.setAreaW(dpTpPx(300));



        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ball.move();
                if(ball.isGameOver()) {
                    Log.i(TAG, "LOSTTTTTTTTTTTTTTTTT!!!!!!!!!!!!!!!!");
//                    showAlert();
                }
            }
        }, 0, 1000/70);


    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        ball.setX(event.values[0]);
        ball.setY(event.values[1]);


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public float dpTpPx(float dp){
        Resources r = getResources();
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                r.getDisplayMetrics()
        );
    }

//    public void showAlert(){
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//                this);
//
//        // set title
//        alertDialogBuilder.setTitle("Your Title");
//
//        // set dialog message
//        alertDialogBuilder
//                .setMessage("Click yes to exit!")
//                .setCancelable(false)
//                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,int id) {
//                        // if this button is clicked, close
//                        // current activity
//                        MainActivity.this.finish();
//                    }
//                })
//                .setNegativeButton("No",new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,int id) {
//                        // if this button is clicked, just close
//                        // the dialog box and do nothing
//                        dialog.cancel();
//                    }
//                });
//
//        // create alert dialog
//        AlertDialog alertDialog = alertDialogBuilder.create();
//
//        // show it
//        alertDialog.show();
//    }

}