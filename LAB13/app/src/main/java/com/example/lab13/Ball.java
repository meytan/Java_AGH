package com.example.lab13;

import android.util.Log;

import android.widget.ImageView;

import java.util.Random;

public class Ball {

    double x =0, y=0;
    private float  posX, posY;
    private float size, areaH, areaW;
    private float screenX, screenY;
    private int howManyFrameRandom;
    private int randomDirection;
    Random generator = new Random();

    private ImageView ballImg;

    public Ball(ImageView ballImg) {
        this.ballImg = ballImg;
    }

    public void move(){


        posX = ballImg.getX() - 3 * (float)x;
        posY = ballImg.getY() + 3 * (float)y;
        float step = 5;
        if(howManyFrameRandom > 0){
            switch (randomDirection){
                case 0:
                    posX += step;
                    break;
                case 1:
                    posX -= step;
                    break;
                case 2:
                    posY += step;
                    break;
                case 3:
                    posY -= step;
                    break;

            }
            howManyFrameRandom--;
        }

        ballImg.setX(posX);
        ballImg.setY(posY);
        Log.i("Position: ", ballImg.getX() + ", " + ballImg.getY());

    }

    public boolean isGameOver(){
        float startY = screenY / 2 - size * 0.5f;
        float startX = screenX / 2 - size * 0.5f;

        if(posX > 0) {
            if (posY < startY - areaH * 0.5 + size * 0.5) {
                return true;
            }
            if (posY > startY + areaH * 0.5 - size * 0.5) {
                return true;
            }
            if (posX < startX - areaW * 0.5 + size * 0.5) {
                return true;
            }
            if (posX > startX + areaW * 0.5 - size * 0.5) {
                return true;
            }
        }

        return false;



    }

    public void reset(){
        ballImg.setX(screenX / 2 - size * 0.5f);
        ballImg.setY(screenY / 2 - size * 0.5f);
    }

   public void randomMove(){
         randomDirection = generator.nextInt(4);
         howManyFrameRandom = 70;
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
