package com.tiburela.qsercom.models;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Celda {

    public static final int DEFAUALT=100;
    public static Canvas micanvasBitmp;
    private int startX;
    private int endX;
    private int startY;
    private int endY;

    private int anchoSize;
    private int altoSize;


public static Bitmap miBitmap;


    public Celda(int startX, int endX , int startY, int endY){
        this.startX=startX;
        this.endX=endX;
        this.startY=startY;
        this.endY=endY;
        anchoSize=endX-startX;
         altoSize=endY-startY;
    }

}
