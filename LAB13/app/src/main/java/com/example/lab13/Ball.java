package com.example.lab13;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class Ball {

    double x =0, y=0;
    float startX, startY, posX, posY;
    float size, areaH, areaW;
    float screenX, screenY;

    ImageView ballImg;

    boolean moved = false;
    public Ball(ImageView ballImg) {
        this.ballImg = ballImg;
    }

    public void move(){


        posX = ballImg.getX() - 3 * (float)x;
        posY = ballImg.getY() + 3 * (float)y;
        ballImg.setX(posX);
        ballImg.setY(posY);
        Log.i("Position: ", ballImg.getX() + ", " + ballImg.getY());
        Log.i("Start: ", startX + ", " + startY);

    }

    public boolean isGameOver(){
        if(posY > startY - areaH *0.5 + size* 0.5){
            return true;
        }
        if(posY < startY + areaH *0.5){
            return true;
        }
        if(posX > startX - areaW *0.5 + size* 0.5){
            return true;
        }
        if(posX < startX + areaW *0.5 ){
            return true;
        }

        return false;



    }


    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void setAreaH(float areaH) {
        this.areaH = areaH;
    }

    public void setAreaW(float areaW) {
        this.areaW = areaW;
    }

    public void setScreenX(float screenX) {
        this.screenX = screenX;
    }

    public void setScreenY(float screenY) {
        this.screenY = screenY;
    }
}
