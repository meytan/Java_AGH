package com.example.lab13;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;

import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity implements SensorEventListener {

    private static final String TAG = "AnimationStarter";
    private Sensor sensor;
    private SensorManager sm;
    private Ball ball;
    private Timer timer;
    private Context context = this;

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
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        ball.setScreenY(displayMetrics.heightPixels);
        ball.setScreenX(displayMetrics.widthPixels);
        startGame();


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

    private void startGame(){
        timer= new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ball.move();
                if(ball.isGameOver()) {
                    Log.i(TAG, "LOSTTTTTTTTTTTTTTTTT!!!!!!!!!!!!!!!!");
                    resetGame();
                }
            }
        }, 0, 1000/70);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ball.randomMove();
            }
        }, 0, 3000);

    }

    private void resetGame(){
        timer.cancel();
        timer.purge();
        ball.reset();
        this.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(context, "GAME OVER! Try again", Toast.LENGTH_SHORT).show();
            }
        });

        startGame();

    }


}