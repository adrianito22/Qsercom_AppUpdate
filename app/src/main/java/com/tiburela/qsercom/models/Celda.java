package com.tiburela.qsercom.models;

import static android.view.Gravity.CENTER;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import java.lang.annotation.Inherited;

public class Celda {


    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getEndX() {
        return endX;
    }

    public void setEndX(float endX) {
        this.endX = endX;
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public float getEndY() {
        return endY;
    }

    public void setEndY(float endY) {
        this.endY = endY;
    }

    public float getAnchoSize() {
        return anchoSize;
    }

    public void setAnchoSize(float anchoSize) {
        this.anchoSize = anchoSize;
    }

    public float getAltoSize() {
        return altoSize;
    }

    public void setAltoSize(float altoSize) {
        this.altoSize = altoSize;
    }
    private float startX;

    private float endX;
    private float startY;
    private float endY;

    private float anchoSize;
    private float altoSize;


    public boolean isEsCeldaContrucDefault() {
        return esCeldaContrucDefault;
    }

    public void setEsCeldaContrucDefault(boolean esCeldaContrucDefault) {
        this.esCeldaContrucDefault = esCeldaContrucDefault;
    }

    private boolean esCeldaContrucDefault;


    public int getAnchoTable() {
        return anchoTable;
    }

    public void setAnchoTable(int anchoTable) {
        this.anchoTable = anchoTable;
    }

    public int getMarginRigth() {
        return marginRigth;
    }

    public void setMarginRigth(int marginRigth) {
        this.marginRigth = marginRigth;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
    }

    public int getAling() {
        return aling;
    }

    public void setAling(int aling) {
        this.aling = aling;
    }

    private int anchoTable;
   private int marginRigth;
   private int marginLeft;
   private int aling;

    public int getAltoCelda() {
        return altoCelda;
    }

    public void setAltoCelda(int altoCelda) {
        this.altoCelda = altoCelda;
    }

    private int altoCelda;


    public Celda(int anchoTable,int marginRigth, int marginLeft,
                 int altoCelda,int aling){
        this.anchoTable = anchoTable;
        this.marginRigth=marginRigth;
        this.marginLeft=marginLeft;
        this.altoCelda=altoCelda;
        startX=0;
        endX=0;
        startY=0;
        endY=0;
        anchoSize=endX-startX; //CON ESTO OBTENEMOS EL ANCHO DE CADA DIBUJO MAS NO BIEN SITUAMOS...para situar debemos suma end +start..
        altoSize=endY -startY;
        esCeldaContrucDefault =false;
        this.aling=aling;
        Log.i("debuginCewlda","el altosize es "+altoSize);

    }



    public Celda(float startX, float endX , float startY, float endY){ //para pasarle un objeto celda
        this.startX=startX;
        this.endX=endX;
        this.startY=startY;
        this.endY=endY;
        anchoSize=endX-startX; //CON ESTO OBTENEMOS EL ANCHO DE CADA DIBUJO MAS NO BIEN SITUAMOS...para situar debemos suma end +start..
        altoSize=endY -startY;
        esCeldaContrucDefault =false;
        Log.i("debuginCewlda","el altosize es "+altoSize);
    }




    public Celda(){
        startX=0;
        endX=0;
        startY=0;
         endY=0;
        anchoSize=0; //=endX-startX
        altoSize=0;       //  altoSize=endY -startY;
        esCeldaContrucDefault =true;
    }

    //tambien necesitamos una funcion para alinear celda dentro de otro objeto



    //el celda object contendra el ancho
    public void addCelda (Celda celdaObjec, Canvas canvas, int marginLeft, int marginRigth){/// ///EL largo puede ser default...
                float anchoCelda=0;
                 TableFifi.ultimaPOsicionXEnd= TableFifi.ultimaPOsicionXEnd+marginLeft;

        if(celdaObjec.anchoSize==TableFifi.ANCHO_DOCUEMENTO){ // ES 1 ///la tabla ucpara todo el ancho..
            anchoCelda=TableFifi.ANCHO_DOCUEMENTO-marginRigth-marginLeft;
        }



        else if(celdaObjec.anchoSize ==TableFifi.SIZE_2_TERCIO) { //si es dos ocupara solo la mita

            anchoCelda=TableFifi.ANCHO_DOCUEMENTO/2;

        }

        else if(celdaObjec.anchoSize ==TableFifi.SIZE_1_TERCIO) {

            anchoCelda=TableFifi.ANCHO_DOCUEMENTO/3;



        }else{   //es custom size

            anchoCelda=celdaObjec.anchoSize;

        }

        celdaObjec.setAnchoSize(anchoCelda);

        alinearCelda(celdaObjec,celdaObjec.aling); //alineamos la tabla

        //actualizzamos los valores de esta celda...

      //  Celda celdabackgroundContainer=new Celda(ultimaPOsicionXEnd,ultimaPOsicionXEnd+ anchoCelda ,ultimaPOsicionYEnd,ultimaPOsicionYEnd+ (numeroFilas*tablaObject.altoCelda));

        celdaObjec.setStartX(TableFifi.ultimaPOsicionXEnd);
        celdaObjec.setEndX(TableFifi.ultimaPOsicionXEnd+anchoCelda);

        celdaObjec.setStartY(TableFifi.ultimaPOsicionYEnd);
        celdaObjec.setEndY(TableFifi.ultimaPOsicionYEnd+celdaObjec.altoCelda);

    }


    public  void alinearCelda(Celda celdaObject,int alineacion){

        if(alineacion==TableFifi.ALIGN_TABLE_CENTER){

            float  anchoTotal_menos_anchoTabla= TableFifi.ANCHO_DOCUEMENTO-celdaObject.getAnchoSize();

            TableFifi.ultimaPOsicionXEnd= anchoTotal_menos_anchoTabla/2;
            Log.i("alineamos","elanchoTotal_menos_anchoTabla es : "+anchoTotal_menos_anchoTabla);


            Log.i("alineamos","el x posicion es  : "+TableFifi.ultimaPOsicionXEnd);

            //  ultimaPOsicionYEnd=ANCHO_DOCUEMENTO-tablaObject.anchoTable/2;



        }
        /*
        else if(alineacion==ALIGN_TABLE_LEFT)  { //esta ya esta por defecto

            ultimaPOsicionXEnd=ANCHO_DOCUEMENTO-tablaObject.anchoTable;


        }
*/
        else if (alineacion==TableFifi.ALIGN_TABLE_RIGTH) {



            TableFifi.ultimaPOsicionXEnd=TableFifi.ANCHO_DOCUEMENTO-celdaObject.anchoSize-celdaObject.marginRigth;

        }


        // ultimaPOsicionYEnd=tablaObject.backgroundCeldFather.getEndY()+marginTop;

    }






    private void addBacgroundINCelda(Celda celdaObject,String colorToParseBacground,Canvas canvas){

        Paint paintBacground= new Paint();
        paintBacground.setColor(Color.parseColor(colorToParseBacground));
        canvas.drawRect(celdaObject.getStartX(),celdaObject.getStartY(),celdaObject.getEndX(),celdaObject.getEndY(),paintBacground);


    }

    private void addTextInCelda(String text, Celda celdaWhereEstaraText, Canvas canvas, Paint paintToText , int tipoAlineacionText){

        Rect bounds = new Rect();

        float sizeAltoCelda =celdaWhereEstaraText.getAltoSize();
        int text_height = 0;
        int text_width = 0;

        // paint.setTypeface(Typeface.DEFAULT);// your preference here
        // paint.setTextSize(25);// have this the same as your text size

        paintToText.getTextBounds(text, 0, text.length(), bounds);

        text_height =  bounds.height();
       // text_width =  bounds.width();

      //  Paint painCentterna = new Paint();
        paintToText.setTextAlign(Paint.Align.LEFT);

       // int xPos = (canvas.getWidth() / 2);
       // int yPos = (int) ((canvas.getHeight() / 2) - ((painCentterna.descent() + painCentterna.ascent()) / 2)) ;
        //((textPaint.descent() + textPaint.ascent()) / 2) is the distance from the baseline to the center.


        if(tipoAlineacionText==TableFifi.CENTER_ALIGN) {

            float xPosZ =celdaWhereEstaraText.getAnchoSize()/2 + celdaWhereEstaraText.getStartX() - (int)(paintToText.measureText(text)/2);

            Log.i("sabado","la poscion deonde se ubicara es "+xPosZ);

            float yPosZ =celdaWhereEstaraText.getAltoSize()/2 + celdaWhereEstaraText.getStartY()+3;

            canvas.drawText(text, xPosZ, yPosZ, paintToText);

        }



        else if(tipoAlineacionText==CENTER) {

            canvas.drawText(text,celdaWhereEstaraText.getStartX()+10,celdaWhereEstaraText.getStartY()+text_height+(celdaWhereEstaraText.getAltoSize()/4),paintToText);

        }


    }





    private void addBordesInCelda(){


    }





}
