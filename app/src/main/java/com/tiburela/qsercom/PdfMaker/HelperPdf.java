package com.tiburela.qsercom.PdfMaker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.models.NameAndValue;
import com.tiburela.qsercom.models.SetInformDatsHacienda;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class HelperPdf {

   public static  ArrayList<ArrayList<String>> listOFlist = new ArrayList<>();




    public  Image  createInfoImgtoPDF(Drawable mIDrawable, Context conetxt){
       // mIDrawable= conetxt.getDrawable(R.drawable.logopdf);
          Bitmap miBitmap= ((BitmapDrawable)mIDrawable).getBitmap();
        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
         miBitmap.compress(Bitmap.CompressFormat.PNG,100,stream1);
         byte[] bitmapData1= stream1.toByteArray();
        ImageData imageData= ImageDataFactory.create(bitmapData1);

       Image image =new Image(imageData);
        image.setWidth(100f);


return  image;


    }


    public  Image  createInfoImgtoPDF(Drawable mIDrawable, Context conetxt,int qualkity){
        // mIDrawable= conetxt.getDrawable(R.drawable.logopdf);
        Bitmap miBitmap= ((BitmapDrawable)mIDrawable).getBitmap();
        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
        miBitmap.compress(Bitmap.CompressFormat.PNG,qualkity,stream1);
        byte[] bitmapData1= stream1.toByteArray();
        ImageData imageData= ImageDataFactory.create(bitmapData1);

        Image image =new Image(imageData);
       //image.setWidth(826f);


        return  image;


    }


    public  Image  createInfoImgtoPDF(Bitmap miBitmap){

        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
        miBitmap.compress(Bitmap.CompressFormat.PNG,100,stream1);
        byte[] bitmapData1= stream1.toByteArray();
        ImageData imageData= ImageDataFactory.create(bitmapData1);

        Image image =new Image(imageData);
        image.setWidth(100f);

        return  image;
    }



    public static HashMap<String,Cell> generateHasmapFieldnameandValue(ArrayList<NameAndValue>list){

         HashMap<String,Cell> hasmpaDevolver= new HashMap<>();



          for(int i=0;i<list.size();i++){
              Paragraph paragraph1 = new Paragraph(list.get(i).getNameFields());
              Cell cell1= new Cell().add(paragraph1);

              Paragraph paragraph2 = new Paragraph(list.get(i).getValueContent());
              Cell cell2= new Cell().add(paragraph2);

              hasmpaDevolver.put(String.valueOf(i+"name"),cell1);
              hasmpaDevolver.put(String.valueOf(i+"value"),cell2);



          }



          return hasmpaDevolver;

    }




    /**para el cuadrto 1*/
    public static  ArrayList<NameAndValue> generaDataToTable(SetInformEmbarque1 Object1,
                                                             SetInformEmbarque2 Object2, SetInformDatsHacienda Object3, int tableInfo){
        ArrayList<NameAndValue> listTOrETURN1 = new ArrayList<>();

        if(tableInfo==1){

            listTOrETURN1.add(new NameAndValue("SEMANA "+Object1.getSemana(),"FECHA: "+Object1.getSimpleDataFormat()));
            listTOrETURN1.add(new NameAndValue("PRODUCTOR ",Object1.getProductor()));
            listTOrETURN1.add(new NameAndValue("CODIGO PRODUCTOR ",Object1.getCodigo()));
            listTOrETURN1.add(new NameAndValue("CODIGO MAGAP ",Object1.getInscirpMagap()));
            listTOrETURN1.add(new NameAndValue("PUERTO EMBARQUE ",Object1.getPemarque()));
            listTOrETURN1.add(new NameAndValue("ZONA",Object1.getZona()));
            listTOrETURN1.add(new NameAndValue("HORA INICIO",Object1.getHoraInicio()));
            listTOrETURN1.add(new NameAndValue("HORA TERMINO",Object1.getHoraTermino()));
            listTOrETURN1.add(new NameAndValue("GUIA REMISION",Object1.getNguiaRemision()));
            listTOrETURN1.add(new NameAndValue("GUIA DE TRASNPORTE",Object1.get_nguia_transporte()));
            listTOrETURN1.add(new NameAndValue("TARJA DE EMBARQUE",Object1.getNtargetaEmbarque()));
            listTOrETURN1.add(new NameAndValue("HOJA DE EVALUACION",String.valueOf(Object1.getEdiNhojaEvaluacion())));
        }


     else   if(tableInfo==2){ /**la segunda tabla productos postocosecha*/

                 //PRODUCTOS POSCOSEWZHA CFON UN BUCLE FOR

            listTOrETURN1.add(new NameAndValue("BC100 "+Object1.getSemana(),"FECHA: "+Object1.getSimpleDataFormat()));
            listTOrETURN1.add(new NameAndValue("SB100 ",Object1.getProductor()));
            listTOrETURN1.add(new NameAndValue("ACIDO CITRICO ",Object1.getCodigo()));
            listTOrETURN1.add(new NameAndValue("AGUA ",Object1.getInscirpMagap()));


        }


        else   if(tableInfo==3){ /**la 3ra tabla*/

            listTOrETURN1.add(new NameAndValue("AQUI TERCERA tabla  "+Object1.getSemana(),"FECHA: "+Object1.getSimpleDataFormat()));

        }







        return listTOrETURN1;

    }


}
