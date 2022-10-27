package com.tiburela.qsercom.PdfMaker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.models.ImagesToPdf;
import com.tiburela.qsercom.utils.HelperImage;
import com.tiburela.qsercom.utils.PdfMakerHelper;
import com.tiburela.qsercom.utils.Variables;

import java.util.ArrayList;

public class HelperAdImgs {
   public static  ArrayList<ImagesToPdf> currentListImagesSeccion;

    static   boolean wehaveAddSpaceyIndescrip =false;
    static   float   posicionLastELEMENTAd =10000f;


    public static void createPages_addImgs(int setFotoCategory ,String anexoNombre, Document midocumentotoAddData,
                                            PageSize pageSize,Context contexto){
        int contador =0;

        Log.i("xamil","la foto selecionada categoria es  "+setFotoCategory);


        Log.i("contaburx","el size de inagesetglobal es "+HelperImage.imAGESpdfSetGlobal.size());

        currentListImagesSeccion= HelperImage.getImagesWhitthisCATEGORY(HelperImage.imAGESpdfSetGlobal,setFotoCategory);///era Variables.FOTO_LLEGADA

        Log.i("xamil","el SIZE DE  current list images seccion es "+currentListImagesSeccion.size());


        //nos asegurammos que ninguna este en el pdf aun ,LAS DESMARCAMOS
        currentListImagesSeccion= HelperImage.marcaQueNoEstaEnPDF(currentListImagesSeccion);
        Log.i("contaburx","la IMAGEN DE LA 1  POSCION esta en el pdf? "+currentListImagesSeccion.get(0).estaENPdf);
        Log.i("contaburx","la IMAGEN DE LA 2 POSCION esta en el pdf? "+currentListImagesSeccion.get(1).estaENPdf);



        while(!allImagesISUsed(currentListImagesSeccion)){ //mientras quedan imagenes si usar////

            if(contador==0  && setFotoCategory==Variables.FOTO_LLEGADA ) { ///significa que es la primera pagina
              ///AGREGMAOS TEXTO
                midocumentotoAddData.add(new Paragraph("ANEXOS").setFontSize(11f).setPaddingLeft(20f));
                midocumentotoAddData.add(new Paragraph("* "+anexoNombre).setFontSize(11f).setPaddingLeft(30f));
            }
            Log.i("homero","el posicion las element en comprobacion es  "+posicionLastELEMENTAd);

            /**chekeamos si cremos otra pagina*/
                if(posicionLastELEMENTAd<150){
                    Log.i("homero","creamos una nueva pagina ");

                    midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
                    HelperPdf pdfHelper= new HelperPdf();
                   Image imglogqSercom=pdfHelper.createInfoImgtoPDF(contexto.getDrawable(R.drawable.headerpdf),1);
                    // imglogqSercom.setHorizontalAlignment(HorizontalAlignment.CENTER);
                    midocumentotoAddData.add(imglogqSercom).setTopMargin(0f);
                }




            contador++;


            Log.i("contaburx","se ejecuto ESto "+contador+" veces");
            int patronEncontrado=HelperImage.buscaPosiblePatronParaOrdenar(currentListImagesSeccion);


            if(patronEncontrado== Variables.TRES_IMGS_VERTCLES){
                wehaveAddSpaceyIndescrip =false;
                Log.i("contaburx","es Variables.TRES_IMGS_VERTCLES");
                addImagenSet(Variables.TRES_IMGS_VERTCLES,midocumentotoAddData,pageSize);
                //ENTONCES ESTOS ENCONTRADOS LOS PONEMOS QUE YA SE USARON.....


            }

            else if(patronEncontrado == Variables.DOS_IMGS_VERTICALES) {
                Log.i("contaburx","es el DOS_IMGS_VERTICALES ");
                wehaveAddSpaceyIndescrip =false;

                addImagenSet(Variables.DOS_IMGS_VERTICALES,midocumentotoAddData,pageSize);


            }

            else if(patronEncontrado == Variables.UNAVERTICAL_Y_OTRA_HORIZONTAL) {
                Log.i("contaburx","es  UNAVERTICAL_Y_OTRA_HORIZONTAL");
                wehaveAddSpaceyIndescrip =false;

                addImagenSet(Variables.UNAVERTICAL_Y_OTRA_HORIZONTAL,midocumentotoAddData,pageSize);


            }


            else if(patronEncontrado == Variables.DOS_HORIZONTALES) {
                Log.i("contaburx","es el DOS_HORIZONTALES ");
                wehaveAddSpaceyIndescrip =false;

                addImagenSet(Variables.DOS_HORIZONTALES,midocumentotoAddData,pageSize);


            }



            else if(patronEncontrado == Variables.DEFAULNO_ENCONTRO_NADA) {
                Log.i("contaburx","es el  DEFAULNO_ENCONTRO_NADA");
                wehaveAddSpaceyIndescrip =false;

                addImagenSet(Variables.DEFAULNO_ENCONTRO_NADA,midocumentotoAddData,pageSize);


            }

        }

    }
    private static boolean allImagesISUsed(ArrayList<ImagesToPdf> list){
        int contadorIMagesUsadas=0;


        for(int indice=0; indice<list.size(); indice++){

            if(list.get(indice).estaENPdf){

                contadorIMagesUsadas++;



            }else{

                break;
            }

            //primero si podemos poner las 3 primeras imagenes....
            //comprobamos que existan al menos 3 imagenes


        }

        Log.i("contabur","hay "+contadorIMagesUsadas +" imagenes usadas");


        if(contadorIMagesUsadas== list.size() || contadorIMagesUsadas== list.size()-1 ){ //o si solo queda 1

            return true;
        }else{
            return false;

        }
    }
    private static void addImagenSet(int tipoOrdenImgs, Document docuemnto,  PageSize pageSize){

        float espacioDisponibleHorizontal=pageSize.getWidth()-100f; //
        //si hay texto o comentario le restamos menos espacio....

        float espacioDisponibleVertical=pageSize.getHeight()-200; //
        //si hay texto o comentario le restamos menos espacio....




        //comprobar en que linea ... comprobar la posicion de la ultima
        if(tipoOrdenImgs==Variables.TRES_IMGS_VERTCLES){ //modo 3 imagenes en una linea...
            Log.i("contabur","hay 3 imagenes verticales hurrazzx");
            float [] tableWidth  = {1,1,1} ;

            Table table = new Table(tableWidth,true);
           // table.setHeight(200);  //Primera tabla ///este ancho...


            Image imagVertical1=HelperPdf.createInfoImgtoPDF(HelperImage.imagesSetToCurrentFila.get(0).miBitmap);
            imagVertical1.setAutoScale(true);

          //  imagVertical1.scaleToFit((pageSize.getWidth()-20f-20f)/3,230);

            Cell cell1 = new Cell().add(imagVertical1);
            table.addCell(cell1);


            Image imagVertical2=HelperPdf.createInfoImgtoPDF(HelperImage.imagesSetToCurrentFila.get(1).miBitmap);
            imagVertical2.setAutoScale(true);
            Cell cell2 = new Cell().add(imagVertical2);
            table.addCell(cell2);


            Image imagVertical3=HelperPdf.createInfoImgtoPDF(HelperImage.imagesSetToCurrentFila.get(0).miBitmap);
            imagVertical3.setAutoScale(true);
            Cell cell3 = new Cell().add(imagVertical3);
            table.addCell(cell3);
            table.setHeight(230);  //Primera tabla ///este ancho...
            configtable(table,pageSize);
            docuemnto.add(table);

            Rectangle remaining = docuemnto.getRenderer().getCurrentArea().getBBox();
            posicionLastELEMENTAd = remaining.getTop();
            Log.i("homero","eldocuemnto.getRenderer().getCurrentArea().getBBox() ES "+posicionLastELEMENTAd);
            markImgComoUsada(HelperImage.imagesSetToCurrentFila); //podem

            //vamos a crear otra pagina ahora....



        }


        else if(tipoOrdenImgs==Variables.UNAVERTICAL_Y_OTRA_HORIZONTAL){ //1 vertical y otro horizontal en la misma linea
                  float [] tableWidth  = {2,1} ;
                 Table table = new Table(tableWidth,true);
                 table.setHeight(espacioDisponibleVertical/2);  //Primera tabla


                 Image imagVertical=HelperPdf.createInfoImgtoPDF(HelperImage.imagesSetToCurrentFila.get(0).miBitmap);
                 imagVertical.setAutoScale(true);
                 Cell cell1 = new Cell().add(imagVertical);
                 table.addCell(cell1);


                 Image imgHorizontal=HelperPdf.createInfoImgtoPDF(HelperImage.imagesSetToCurrentFila.get(1).miBitmap);
                 imgHorizontal.setAutoScale(true);
                 Cell cell2 = new Cell().add(imgHorizontal);
                table.addCell(cell2);
                table.setHeight(230);  //Primera tabla ///este ancho...
            configtable(table,pageSize);


            docuemnto.add(table);

            Rectangle remaining = docuemnto.getRenderer().getCurrentArea().getBBox();
            posicionLastELEMENTAd = remaining.getTop();
            Log.i("homero","eldocuemnto.getRenderer().getCurrentArea().getBBox() ES "+posicionLastELEMENTAd);


            markImgComoUsada(HelperImage.imagesSetToCurrentFila); //podemos pasarle una lista de este 3 imagenes o marca una por una como hicimos arriba



        }


        else if(tipoOrdenImgs==Variables.DOS_IMGS_VERTICALES){ //2 imagenes verticales en una linea



            if(HelperImage.imagesSetToCurrentFila.get(0).descripcionOpcion.length()>1){  //aqui agregamos la descripcion si contiene
                addTexDescripTOiMGSiHay(HelperImage.imagesSetToCurrentFila.get(0).descripcionOpcion,docuemnto);
              //  LARGO_iMG_VERTICAL_=LARGO_iMG_VERTICAL_-10;
                //b
            }

            float [] tableWidth  = {1,1} ;
            Table table = new Table(tableWidth,true);
            table.setHeight(280);  //Primera tabla ///este ancho...




            Image imagVertical=HelperPdf.createInfoImgtoPDF(HelperImage.imagesSetToCurrentFila.get(0).miBitmap);
            imagVertical.setAutoScale(true);
            Cell cell1 = new Cell().add(imagVertical);
            table.addCell(cell1);


            Image imgHorizontal=HelperPdf.createInfoImgtoPDF(HelperImage.imagesSetToCurrentFila.get(1).miBitmap);
            imgHorizontal.setAutoScale(true);
            Cell cell2 = new Cell().add(imgHorizontal);
            table.addCell(cell2);
            table.setHeight(230);  //Primera tabla ///este ancho...
            configtable(table,pageSize);

            docuemnto.add(table);
            Variables.currentPoscion = docuemnto.getRenderer().getCurrentArea().getBBox().getBottom();
           // Log.i("samledtaa","el mi logoqsercom "+ Variables.currentPoscion);

            docuemnto.add(table);
            Rectangle remaining = docuemnto.getRenderer().getCurrentArea().getBBox();
            posicionLastELEMENTAd = remaining.getTop();
            Log.i("homero","eldocuemnto.getRenderer().getCurrentArea().getBBox() ES "+posicionLastELEMENTAd);

            if(HelperImage.imagesSetToCurrentFila.get(1).descripcionOpcion.length()>0){  //aqui agregamos la descripcion

                addTexDescripTOiMGSiHay(HelperImage.imagesSetToCurrentFila.get(1).descripcionOpcion,docuemnto);

            }


            if(HelperImage.imagesSetToCurrentFila.get(1).descripcionOpcion.length()>1){  //aqui agregamos la descripcion si contiene
               // addTexDescripTOiMGSiHay(HelperImage.imagesSetToCurrentFila.get(1).descripcionOpcion,left,docuemnto);
              //  LARGO_iMG_VERTICAL_=LARGO_iMG_VERTICAL_-10;



            }



            markImgComoUsada(HelperImage.imagesSetToCurrentFila); //podemos pasarle una lista de este 3 imagenes o marca una por una como hicimos arriba




        }


        else if(tipoOrdenImgs==Variables.DOS_HORIZONTALES){ //2 imagenes verticales en una linea
            float [] tableWidth  = {1,1} ;
            Table table = new Table(tableWidth,true);
            table.setHeight(280);  //Primera tabla ///este ancho...

            Image imagVertical=HelperPdf.createInfoImgtoPDF(HelperImage.imagesSetToCurrentFila.get(0).miBitmap);
            imagVertical.setAutoScale(true);
            Cell cell1 = new Cell().add(imagVertical);
            table.addCell(cell1);


            Image imgHorizontal=HelperPdf.createInfoImgtoPDF(HelperImage.imagesSetToCurrentFila.get(1).miBitmap);
            imgHorizontal.setAutoScale(true);
            Cell cell2 = new Cell().add(imgHorizontal);
            table.addCell(cell2);
            table.setHeight(230);  //Primera tabla ///este ancho...
            configtable(table,pageSize);

            docuemnto.add(table);
            Variables.currentPoscion = docuemnto.getRenderer().getCurrentArea().getBBox().getBottom();
            // Log.i("samledtaa","el mi logoqsercom "+ Variables.currentPoscion);

            docuemnto.add(table);
            Rectangle remaining = docuemnto.getRenderer().getCurrentArea().getBBox();
            posicionLastELEMENTAd = remaining.getTop();
            Log.i("homero","eldocuemnto.getRenderer().getCurrentArea().getBBox() ES "+posicionLastELEMENTAd);




            Log.i("contabur","es el horizontal true");
          ///  addTexDescripTOiMGSiHay(HelperImage.imagesSetToCurrentFila.get(0).descripcionOpcion,left,docuemnto);


          //  addTexDescripTOiMGSiHay(HelperImage.imagesSetToCurrentFila.get(1).descripcionOpcion,left);


            markImgComoUsada(HelperImage.imagesSetToCurrentFila); //podemos pasarle una lista de este 3 imagenes o marca una por una como hicimos arriba




        }


        else if(tipoOrdenImgs==Variables.DEFAULNO_ENCONTRO_NADA){ //2 imagenes verticales en una linea
            ///en caso que no ecneuntre nigun patron

        }


        //y oior ultimo si hay una imagen sola....tamnto vertical o horizontal ..no la aghregues ,,es una opcion




    }





    public static  void configtable(Table table1,PageSize pageSize){

        table1.setWidth(pageSize.getWidth()-20f);
        table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table1.setMarginLeft(10f);
        table1.setMarginRight(10f);
        table1.setMarginTop(8f);
    }

    public static void addTexDescripTOiMGSiHay(String textoDescripcion,Document document){
        if(textoDescripcion.length()>1){

            //agegamos la descripcion de la primer imagen....
             //agregamos

            document.add(new Paragraph(textoDescripcion));

            Rectangle remaining = document.getRenderer().getCurrentArea().getBBox();
            posicionLastELEMENTAd = remaining.getTop();
            Log.i("homero","eldocuemnto.getRenderer().getCurrentArea().getBBox() ES "+posicionLastELEMENTAd);
          //  Paragraph p = new Paragraph("Hello World");
            //doc.add(p);


           // currentCanvasObjec.drawText(textoDescripcion, lef, poscYUltImgColoc, paintDescripcionImg);

            if(!wehaveAddSpaceyIndescrip){

             ///   poscYUltImgColoc=poscYUltImgColoc+10; //actualizamos la posicion de la y a mas 10 por ahora
                wehaveAddSpaceyIndescrip =true;


            }

        }


    }
    private static void markImgComoUsada(ArrayList<ImagesToPdf> list){

        //buscamos esos ids,, y marcamos como usado...
        //vamos atenber dos listas.... una lista que es la litsa del conjunto actual y una lista que dice


        for(int i= 0; i<list.size(); i++){ ///
            String uniqueId=list.get(i).uniQueIdimgPertenece;

            for(int indice=0; indice<currentListImagesSeccion.size(); indice++){

                if(uniqueId.equals(currentListImagesSeccion.get(indice).uniQueIdimgPertenece)) {
                    currentListImagesSeccion.get(indice).estaENPdf=true;

                  //  Log.i("contabur","el size de marcads como usadas es   "+currentListImagesSeccion.size());


                }



            }

        }




    }

}
