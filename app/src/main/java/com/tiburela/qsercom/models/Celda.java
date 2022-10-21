package com.tiburela.qsercom.models;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Celda {

    private int startX;

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public int getAnchoSize() {
        return anchoSize;
    }

    public void setAnchoSize(int anchoSize) {
        this.anchoSize = anchoSize;
    }

    public int getAltoSize() {
        return altoSize;
    }

    public void setAltoSize(int altoSize) {
        this.altoSize = altoSize;
    }

    private int endX;
    private int startY;
    private int endY;
    private int anchoSize;
    private int altoSize;




    public Celda(int startX, int endX , int startY, int endY){
        this.startX=startX;
        this.endX=endX;
        this.startY=startY;
        this.endY=endY;
        anchoSize=endX-startX;
        altoSize=endY-startY;
    }

}
