package com.tiburela.qsercom.PdfMaker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.fonts.Font;
import android.util.Log;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.models.NameAndValue;
import com.tiburela.qsercom.models.ProductPostCosecha;
import com.tiburela.qsercom.models.SetInformDatsHacienda;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
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



    public static HashMap<String,Cell> generateHasmapFieldnameandValue(ArrayList<NameAndValue>list,int alineacion, int TableID){



        Log.i("mismussndo","el lineacion es "+alineacion);
         HashMap<String,Cell> hasmpaDevolver= new HashMap<>();

         if(alineacion==50){ //alinea a la izquiera

             for(int i=0;i<list.size();i++){

                 Cell cell1;


                 Log.i("mismussndo","el name es "+list.get(i).getNameFields());
                 Log.i("mismussndo","el value es "+list.get(i).getValueContent());
                 Paragraph paragraph1 = new Paragraph(list.get(i).getNameFields()).setFontSize(7.5f);
                         cell1= new Cell().add(paragraph1).setPaddingLeft(10f)
                                 .setHeight(10f);  //era 10.5





                 Paragraph paragraph2;
                 if(list.get(i).getValueContent()==null){
                     paragraph2=new Paragraph("");

                 }else{

                     paragraph2= new Paragraph(list.get(i).getValueContent());

                 }


                 Cell cell2;

                 if(TableID==600 && i==4 || i== 5 || i==6 || i==7|| i==8 ||i==9 ||i==10||i==11||i==12){//si es ;a tabla sellos instalados
                         cell2= new Cell(1,4).add(paragraph2).setPaddingLeft(10f).setFontSize(7.5f).setHeight(10f); ///le agragmos un span



                 }else{

                     cell2= new Cell().add(paragraph2).setPaddingLeft(10f).setFontSize(7.5f).setHeight(10f); //normal

                 }




                 Log.i("mismussndo","estamo en el indice "+i);

                 hasmpaDevolver.put(i+"name",cell1);
                 hasmpaDevolver.put(i+"value",cell2);
             }
         }

         else if(alineacion==100){  //alinea en el centro...
             Log.i("mismussndo","el lineacion es "+alineacion);

             for(int i=0;i<list.size();i++){

                 Paragraph paragraph1 = new Paragraph(list.get(i).getNameFields()).setFontSize(7.5f);
                 Cell cell1= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(10f);


                 Paragraph paragraph2;
                 if(list.get(i).getValueContent()==null){
                     paragraph2=new Paragraph("");

                 }else{

                     paragraph2=new Paragraph(list.get(i).getValueContent()).setFontSize(7.5f);


                 }


                 Cell cell2= new Cell().add(paragraph2).setTextAlignment(TextAlignment.CENTER).setHeight(10f);

                 hasmpaDevolver.put(i+"name",cell1);
                 hasmpaDevolver.put(i+"value",cell2);
             }

         }



          return hasmpaDevolver;

    }





    /**para el cuadrto 1*/
    public static  ArrayList<NameAndValue> generaDataToTable(SetInformEmbarque1 Object1,
                                                             SetInformEmbarque2 Object2, SetInformDatsHacienda Object3,
                                                             int tableInfo, ProductPostCosecha product){
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
              ///si contienen data
            if(!product.acido_citrico.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("ACIDO CITRICO",product.acido_citrico));
            }

            if(!product.alumbre.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("ALUMBRE",product.alumbre));
            }

            if(!product.bc100.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("BC100",product.bc100));
            }

            if(!product.biottol.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("BIOTTOL",product.biottol));
            }

            if(!product.eclipse.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("ECLIPSE",product.eclipse));
            }

            if(!product.gib_bex.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("GIB_BEX",product.gib_bex));
            }

            if(!product.mertec.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("MERTEC",product.mertec));
            }

            if(!product.nlarge.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("NLARGE",product.nlarge));

            }

            if(!product.otro_especifique.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue(product.otro_especifique,product.otroCantidad));
            }

            if(!product.ryzuc.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("RYZUX",product.ryzuc));
            }

            if(!product.sastifar.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("SASTISFARR",product.sastifar));
            }

            if(!product.xtrata.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("XTRATA",product.xtrata));
            }

            if(!product.sb100.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("SB100",product.sb100));
            }

        }


        else   if(tableInfo==3){ /**la 3ra tabla datos contenedor*/////vamos con la tercera tabla...
            listTOrETURN1.add(new NameAndValue("DESTINO",Object1.getDestinoContenedor()));
            listTOrETURN1.add(new NameAndValue("VAPOR",Object1.getVapor()));
            listTOrETURN1.add(new NameAndValue("NUMERACION DE CONTENEDOR",Object1.getNumcionContenedor()));
            listTOrETURN1.add(new NameAndValue("HORA DE LLEGADA",Object1.getHoraLlegadaContenedor()));
            listTOrETURN1.add(new NameAndValue("HORA SALIDA",Object1.getHoraSalidadContenedor()));
            listTOrETURN1.add(new NameAndValue("DESTINO",Object1.getDestinoContenedor()));



        }else if(tableInfo==4){/**sellos llegada*/

            listTOrETURN1.add(new NameAndValue("SELLO PLASTICO NAVIERA",Object1.getSelloPlasticoNaviera()));
            listTOrETURN1.add(new NameAndValue("STICKER DE PATIO VENTOLERA EXTERNA",Object1.getStickerVentoExtern()));
            listTOrETURN1.add(new NameAndValue("NUMERO DE SERIE DE FUNDA",Object1.getnSerieFunda()));
            listTOrETURN1.add(new NameAndValue("CABLE DE RASTREO LLEGADA",Object1.getCableRastreoLlegada()));
            listTOrETURN1.add(new NameAndValue("BOOKING",Object1.getBooking()));
            listTOrETURN1.add(new NameAndValue("MAX GROSS",Object1.getMaxGross()));
            listTOrETURN1.add(new NameAndValue("TARE",Object1.getTare()));



        }else if(tableInfo==5){ //SELLOS INSTALADOS

            listTOrETURN1.add(new NameAndValue("TERMOGRAFO 1",Object2.getTermografo1()));
            listTOrETURN1.add(new NameAndValue("HORA DE ENCEND:" +Object2.getTermografo1HoraEncendido(),"UBICACION PALLET "+Object2.getUbicacionPalletN1()));
            listTOrETURN1.add(new NameAndValue("TERMOGRAFO 2",Object2.getTermografo2()));
            listTOrETURN1.add(new NameAndValue("HORA DE ENCEND:" +Object2.getTermografo2HoraEncendido(),"UBICACION PALLET "+Object2.getUbicacionPalletN2()));


            listTOrETURN1.add(new NameAndValue("CANDADO QSERCOM",Object2.getCandadoQsercom()));
            listTOrETURN1.add(new NameAndValue("CABLE NAVIERA",Object2.getCableNaviera()));


            listTOrETURN1.add(new NameAndValue("SELLO PLASTICO",Object2.getSelloPlastico()));
            listTOrETURN1.add(new NameAndValue("SELLO BOTELLA",Object2.getCandadoBotella()));
            listTOrETURN1.add(new NameAndValue("CABLE EXPORTADORA",Object2.getCableExportadora()));
            listTOrETURN1.add(new NameAndValue("SELLO ADHESIVO EXPORTADORA",Object2.getSelloAdhesivoExportadora()));

            listTOrETURN1.add(new NameAndValue("SELLO ADHESIVO NAVIERA",Object2.getSelloAdhesivoNaviera()));
            listTOrETURN1.add(new NameAndValue("OTROS SELLOS ",Object2.getOtrosSellosEspecif()));


        }else if(tableInfo==6){/**datos transportista */

            listTOrETURN1.add(new NameAndValue("COMPANIA TRANSPORTISTA ",Object2.getCompaniaTranporte()));
            listTOrETURN1.add(new NameAndValue("NOMBRE CHOFER ",Object2.getNombreChofer()));
            listTOrETURN1.add(new NameAndValue("CEDULA ",Object2.getCedulaChofer()));
            listTOrETURN1.add(new NameAndValue("CELULAR ",Object2.getCelularChofer()));
            listTOrETURN1.add(new NameAndValue("PLACA ",Object2.getPlacaChofer()));
            listTOrETURN1.add(new NameAndValue("COLOR CABEZAL ",Object2.getColorCAbezal()));



        }  ///falta datos de proceso y otro



        else if(tableInfo==7){/**datos De proceso */

            listTOrETURN1.add(new NameAndValue("",Object2.));



        }





        return listTOrETURN1;

    }


}
