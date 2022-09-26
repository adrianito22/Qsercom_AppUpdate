package com.tiburela.qsercom.utils;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.tiburela.qsercom.PdfMaker.PdfMaker;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;

public class PdfMakerHelper {

public static void addXINpdf(Canvas canvas, Paint paint, SetInformEmbarque1 informPart1, SetInformEmbarque2 informPart2,int yPosicion){

    if(informPart2.getTipoPlastico().equals("POLITUBO")) { //le pasmaos la posicioon y
        canvas.drawText("X",285,yPosicion,paint);

    }

    if(informPart2.getTipoPlastico().equals("POLIPACK")) {
        canvas.drawText("X",382-20,yPosicion,paint);

    }

    if(informPart2.getTipoPlastico().equals("BANAVAC")) {
        canvas.drawText("X",457-20,yPosicion,paint);

    }



    if(informPart2.getTipoPlastico().equals("BAGS")) {

        canvas.drawText("X",555-20,yPosicion,paint);

    }


    if(informPart2.getTipoCaja().equals("22XU")) {

        canvas.drawText("X",285,yPosicion+9,paint);

    }



    if(informPart2.getTipoCaja().equals("DISPLAY")) {

      //  canvas.drawText("X",382-20,yPosicion+9,paint);
        canvas.drawText("X",285,yPosicion+9,paint);

    }


    if(informPart2.getTipoCaja().equals("13 KG")) {

        canvas.drawText("X",457-20,yPosicion+9,paint);

    }


    if(informPart2.getTipoCaja().equals("208")) {

        canvas.drawText("X", PdfMaker.END_X_POSICION-20,yPosicion+9,paint);

    }



    if(informPart2.isHayExcelnsuchado()) {

        canvas.drawText("X",345,yPosicion+9,paint);

    }else{

        canvas.drawText("X",PdfMaker.END_X_POSICION-37,yPosicion+9,paint);


    }



    if(informPart2.isHayBalanza()) {

        canvas.drawText("X",345,yPosicion+18,paint);

    }else{

        canvas.drawText("X",PdfMaker.END_X_POSICION-37,yPosicion+18,paint);


    }


    if(informPart2.getTipoDeBalanza().equals("BASCULA")) {
//        canvas.drawText("X",345,yPosicion+7+3+9+9,paint);
        canvas.drawText("X",345,yPosicion+36,paint);

    }


    if(informPart2.getTipoDeBalanza().equals("DIGITAL")) {
        canvas.drawText("X",PdfMaker.END_X_POSICION-37,yPosicion+36,paint);


    }


}


}
