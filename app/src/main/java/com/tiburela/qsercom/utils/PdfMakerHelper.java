package com.tiburela.qsercom.utils;

import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.tiburela.qsercom.PdfMaker.PdfMaker;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;

public class PdfMakerHelper {

public static void addXINpdf(Canvas canvas, Paint paint, SetInformEmbarque1 informPart1, SetInformEmbarque2 informPart2,int yPosicion){

    if(informPart2.getTipoPlastico().equals("POLITUBO")) { //le pasmaos la posicioon y
        canvas.drawText("X",282,yPosicion,paint);

    }

    if(informPart2.getTipoPlastico().equals("POLIPACK")) {
        canvas.drawText("X",355,yPosicion,paint);

    }

    if(informPart2.getTipoPlastico().equals("BANAVAC")) {
        canvas.drawText("X",457-20,yPosicion,paint);

    }


    if(informPart2.getTipoPlastico().equals("BAGS")) {

        canvas.drawText("X",555-10,yPosicion,paint);

    }


    if(informPart2.getTipoCaja().equals("22XU")) {

        canvas.drawText("X",282,yPosicion+9,paint);

    }



    if(informPart2.getTipoCaja().equals("DISPLAY")) {
        canvas.drawText("X",355,yPosicion+9,paint);


    }


    if(informPart2.getTipoCaja().equals("13 KG")) {

        canvas.drawText("13",457-25,yPosicion+9,paint);

    }


    if(informPart2.getTipoCaja().equals("208")) {

        canvas.drawText("X", PdfMaker.END_X_POSICION-20,yPosicion+9,paint);

    }



    if(informPart2.isHayExcelnsuchado()) {

      // canvas.drawText("ens",345,yPosicion+9,paint);
        canvas.drawText("X",345,yPosicion+19,paint);


    }else{

        canvas.drawText("X",PdfMaker.END_X_POSICION-50,yPosicion+19,paint);



    }



    if(informPart2.isHayBalanza()) {

      //  canvas.drawText("sb",345,yPosicion+2,paint);
        canvas.drawText("X",345,yPosicion+29,paint);


    }else{

        canvas.drawText("X",PdfMaker.END_X_POSICION-50,yPosicion+29,paint);


    }


    if(informPart2.getTipoDeBalanza().equals("BASCULA")) {
//        canvas.drawText("X",345,yPosicion+7+3+9+9,paint);
        canvas.drawText("X",345,yPosicion+49,paint);

    }


    if(informPart2.getTipoDeBalanza().equals("DIGITAL")) {
        canvas.drawText("X",PdfMaker.END_X_POSICION-50,yPosicion+49,paint);


    }


    if(informPart2.getHayBalanzaRepeso()){
        //        canvas.drawText("sb",345,yPosicion+29,paint);
        canvas.drawText("X",345,yPosicion+59,paint);

    }else{

        canvas.drawText("X",PdfMaker.END_X_POSICION-50,yPosicion+59,paint);

    }


    if(informPart2.getTipoDeBalanzaRepeso().equalsIgnoreCase("BASCULA")){
        //        canvas.drawText("sb",345,yPosicion+29,paint);
        canvas.drawText("X",345,yPosicion+69,paint);

    }


    if(informPart2.getTipoDeBalanzaRepeso().equalsIgnoreCase("DIGITAL")){
        //        canvas.drawText("sb",345,yPosicion+29,paint);
        canvas.drawText("X",PdfMaker.END_X_POSICION-50,yPosicion+69,paint);

    }


    //MARCAMOS CONDICION DE BALANZA


    if(Variables.CurrenReportPart2.getCondicionBalanza().equals("BUENA")){ //otra opcion balanza
        canvas.drawText("X",345,yPosicion+39,paint);
 //        canvas.drawText("sbr",345,yPosicion+59,paint);
       // canvas.drawText("MALA",382+3,posicionYstart+7+48,paintContentText); //ESTACON LA LETRA NORMAL

    }else{

        canvas.drawText("X",PdfMaker.END_X_POSICION-50,yPosicion+39,paint);

    }


}


}
