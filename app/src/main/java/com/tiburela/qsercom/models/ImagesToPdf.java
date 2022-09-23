package com.tiburela.qsercom.models;

import android.graphics.Bitmap;

import java.util.UUID;

public class ImagesToPdf {
    
    
    //una propieda de espacion a la derecha...que falta..
    //clacular el espacio que hay desde la poscion de la ultima imagen a la derecha...
    //si no cabe una imagen entonces anda a la siguiente ........
    
    //si ya termino entonces crea otra pagina...
    
    //fila propiedad


    //digamos que contendra 3 espacios
  public int numPaginaAlQUEPertenece;
   public int filaAlaQueSeubica ; //tenemos solo dos filas 1 y 2
 public   int WidthQueOcupa ;   ///el width es de 3 ....
 public   boolean esFinalDeFila; //si es final signifca que vamos a la linea de abajo...
 public   boolean estaENPdf; //si es final signifca que vamos a la linea de abajo...

    public String uniQueId;
    public  String horientacionImagen ;   ///el width es de 3 ....
    public  int posicionPdImageEnd;
    public int tipoImagenCategory;

    public Bitmap miBitmap ;

    public ImagesToPdf(String horientacionImagen,Bitmap miBitmap,int tipoImagenCategory){
        numPaginaAlQUEPertenece=0;
        filaAlaQueSeubica=0;
        WidthQueOcupa=0;
        posicionPdImageEnd=0;
        esFinalDeFila=false;
        estaENPdf=false;
        this. horientacionImagen=horientacionImagen;
        uniQueId= UUID.randomUUID().toString();
        this.miBitmap=miBitmap;

        this.tipoImagenCategory=tipoImagenCategory;


    }


    //vamos a tener 3 medidas especiales




}
