package com.tiburela.qsercom.models;

import android.graphics.Canvas;
import android.graphics.Paint;

public class TextPdf  extends  Celda{


    private  String texto;


    public TextPdf(int startX, int endX, int startY, int endY,String texto) {
        super(startX, endX, startY, endY);

        this.texto=texto;


    }




    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }




    public void addText(String texto,Canvas canvas, Paint paintTOtext,int AlineacionText ){




    }

}
