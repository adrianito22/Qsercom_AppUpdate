package com.tiburela.qsercom.PdfMaker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import com.tiburela.qsercom.R;
import com.tiburela.qsercom.models.DataToPDF;
import com.tiburela.qsercom.models.ImagesToPdf;
import com.tiburela.qsercom.models.ProductPostCosecha;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;
import com.tiburela.qsercom.utils.HelperImage;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

public class PdfMaker {

     static ArrayList<ImagesToPdf> currentListImagesSeccion;
    static   PdfDocument pdfDocument;
    static Paint mipaintHeader;
    static Paint paintContentText;
    static boolean isAlignmentCenter =false;
    static Paint mipaintLines;
    static Paint paintIzquierda ;
    static Paint paintBacdkground;
    static Paint paintBacdkgroundDurazno;
   static  int indiceDecurrentListImagesSeccion=0;

    static ArrayList<PdfDocument.Page> pagesPdfDocuments=new ArrayList<>();  //aqui guardamos las pages ya con sus finish...
    static Canvas currentCanvasObjec;
    static  PdfDocument.Page  currentPagePdfObjec;


    static int poscYUltImgColoc=210; //vamos a empezar aqui
    static  int posXRIGTHUltImgColoc =30;
    static  int posXLefStartNow =50;

    private static final int START_X_POSICION = 40;
    private static final int END_X_POSICION = 555;
    private static final int START_X_POSICION_TEXT_RIGTH = 200;
    private static final int START_X_POSICION_TEXT_LEFT = 40;


    static ArrayList<DataToPDF> data;


    public static int currentPosicionLastYcanvasElement = 0;




    public static void generatePdfReport1(Context context, SetInformEmbarque1 informe1, SetInformEmbarque2 informe2, ProductPostCosecha productPostC) {
        // creating an object variable
        // for our PDF document.



        Bitmap bmpGlobal, bitMapScaledHeader, bitmapScaledFooter;
        bmpGlobal = BitmapFactory.decodeResource(context.getResources(), R.drawable.headerpdf);
        bitMapScaledHeader = Bitmap.createScaledBitmap(bmpGlobal, 595, 200, false);

        //generamos un bitmap scaled footer
        bmpGlobal = BitmapFactory.decodeResource(context.getResources(), R.drawable.footer_pdf);
        //  bitmapScaledFooter=Bitmap.createScaledBitmap(bmpGlobal, 595, 290, false);

         pdfDocument = new PdfDocument();

        Paint miPaint = new Paint(); //paint es un pincel propiedad,, y contiene todo lo que contiene un pincel(color,ancho..tipo,etc.)

        /****creramos e inicilizamos un objeto page nfo que recibe como parametros un width.heigth y pgae number que ahora es 1 */
        PdfDocument.PageInfo mypageInfo1 = new PdfDocument.PageInfo.Builder(595, 842, 1).create();

        // below line is used for setting
        // start page for our PDF file.
        /***creamos e iniclizamos un objeto   PdfDocument.Page  Y le gramos la confirguracion o el objeto  mypageInfo */
        PdfDocument.Page myPage1 = pdfDocument.startPage(mypageInfo1);

        /*** creating a variable for canvas */
        Canvas canvas = myPage1.getCanvas();

        addImageHeaderFootterPDF( canvas,context);


        initPaintObject();


        /****productos poscosecha utilizados  */

        int posicionYdondeStartDibujamos = 220;

        //AGREGAMOS LAS TABLAS EN EL CANVAS

        addNewTable(canvas, posicionYdondeStartDibujamos, mipaintLines, "ESTE ES EL PRIMERA TABLA y HEADER", Variables.FIRST_HEADER_OF_TABLE, informe1, informe2, productPostC);
        addNewTable(canvas, currentPosicionLastYcanvasElement + 10, mipaintLines, "PRODUCTOS POST-COSECHA UTILIZADOS", Variables.TABLE_PRODUCTOS_POSTCOSECHA, informe1, informe2, productPostC);
        addNewTable(canvas, currentPosicionLastYcanvasElement + 10, mipaintLines, "Datos del contenedor", Variables.DATOS_DEL_CONTENEDOR, informe1, informe2, productPostC);
        addNewTable(canvas, currentPosicionLastYcanvasElement + 10, mipaintLines, "SEllOS LLEGADA", Variables.TABLE_SELLOS_LLEGADA, informe1, informe2, productPostC);
        addNewTableSellosINtalados(canvas, currentPosicionLastYcanvasElement + 10, "SEllOS INSTALADOS",informe2);
        pdfDocument.finishPage(myPage1); //finalziamos la  pagina 1


        /**SEGUNDA HOJA DEL PDF*/
        //cremaosd la pagina 2 del pdf
        PdfDocument.PageInfo mypageInfo2 = new PdfDocument.PageInfo.Builder(595, 842, 1).create();
        PdfDocument.Page myPage2 = pdfDocument.startPage(mypageInfo2);
        Canvas canvas2 = myPage2.getCanvas();

        //agregamos las imagen de header y la de footer
        addImageHeaderFootterPDF( canvas2,context);

        //agregamos data
        addNewTable(canvas2, posicionYdondeStartDibujamos, mipaintLines, "DATOS TRANSPORTISTA", Variables.TABLE_DATOS_TRANSPORTISTA, informe1, informe2, productPostC);


        int posicionYdeDataosProcesotabla = currentPosicionLastYcanvasElement + 10;
        addNewTable(canvas2, currentPosicionLastYcanvasElement + 10, mipaintLines, "DATOS DEl PROCESO", Variables.TABLE_DATOS_PROCESO, informe1, informe2, productPostC);


        /**en esta ultima tabla agregamos demas columnas y contenido usando el siguioente metodo*/
        addMoreDataDatosProceso(posicionYdeDataosProcesotabla, canvas2);


        addNewTable(canvas2, currentPosicionLastYcanvasElement, mipaintLines, "DATOS HACIENDA", Variables.TABLE_DATOS_HACIENDA, informe1, informe2, productPostC);

        addNewTable(canvas2, currentPosicionLastYcanvasElement, mipaintLines, "CONTROL DE GANCHO", Variables.TABLE_CONTROL_DE_GANCHO, informe1, informe2, productPostC);

      //  addTableCalibracionFrutaCaleEnfunde(canvas2, currentPosicionLastYcanvasElement, mipaintLines, "CALIBRACION DE FRUTA (CALENDARIO DE ENFUNDE)", Variables.TABLE_CALIB_FRUTS_CLD_ENFUN, informe1, informe2, productPostC);

        pdfDocument.finishPage(myPage2); //finalziamos la  pagina 2







        /***Agregamos tercera hoja al ducmento DEMO*/

        /**3ra  HOJA DEL PDF*/
        //
        //cremaosd la pagina 2 del pdf
        PdfDocument.PageInfo mypageInfo3 = new PdfDocument.PageInfo.Builder(595, 842, 1).create();
        PdfDocument.Page myPage3 = pdfDocument.startPage(mypageInfo3);
        Canvas canvas3 = myPage3.getCanvas();

        //agregamos las imagen de header y la de footer
        addImageHeaderFootterPDF(canvas3,context);

        //ahora agregamos las imagenes en esa hoja

       Bitmap bmpGlobalx = BitmapFactory.decodeResource(context.getResources(), R.drawable.imagen_horizontal);

        addImagenInPDF(bmpGlobalx,bmpGlobalx,canvas3);


        pdfDocument.finishPage(myPage3); //finalziamos la  pagina 2




        //creamos la primera pgina de aanexos ...
        PdfDocument.PageInfo mypageInfo4 = new PdfDocument.PageInfo.Builder(595, 842, 1).create();
       // PdfDocument.Page myPage4 = pdfDocument.startPage(mypageInfo4);
      //  Canvas canvas4 = myPage4.getCanvas();

        currentPagePdfObjec= pdfDocument.startPage(mypageInfo4);

        currentCanvasObjec= currentPagePdfObjec.getCanvas();

         //  currentPagePdfObjec=myPage4;
        //obtenbemos solo una lista que contenga fotos llegada...
        currentListImagesSeccion= HelperImage.createImagesSet(HelperImage.imAGESpdfSetGlobal,Variables.FOTO_LLEGADA);
             //agregamos fotos llegada
        Log.i("contabur","el SIZE DE ESTA SECCION  ES "+currentListImagesSeccion.size());

        //add header y footer
        addImageHeaderFootterPDF(currentCanvasObjec,context);



       // pdfDocument.finishPage(myPage4) ;

        createPages_addImgs(context,"Anexo : FOTOS LLEGADA");
           //public static void addImageHeaderFootterPDF(Bitmap bitMapScaledHeader, Bitmap bmpGlobal, Canvas canvas, Paint miPaint) {



        exportPdxFZ(pdfDocument, context);


    }



    public static Bitmap resize(Bitmap imaged, int maxWidth, int maxHeight) {
        Bitmap image = imaged;

        if (maxHeight > 0 && maxWidth > 0) {
            int width = image.getWidth();
            int height = image.getHeight();
            float ratioBitmap = (float) width / (float) height;
            float ratioMax = (float) maxWidth / (float) maxHeight;
            int finalWidth = maxWidth;
            int finalHeight = maxHeight;
            if (ratioMax > 1) {
                finalWidth = Math.round(((float) maxHeight * ratioBitmap));
            } else {
                finalHeight = Math.round(((float) maxWidth / ratioBitmap));
            }
            return image = Bitmap.createScaledBitmap(image, finalWidth, finalHeight, false);
        }
        return image;
    }





    public static void exportPdxFZ(PdfDocument pdfDocument, Context context) {

        // below line is used to set the name of
        // our PDF file and its path.
        //  ContextWrapper cw = new ContextWrapper(context);
        //   File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);

        // File file = new File(directory, "UniqueFileName" + ".pdf");

        ContextWrapper cw = new ContextWrapper(context);


        // String fullPath =cw.getExternal(Environment.DIRECTORY_DOWNLOADS).toString();
///
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        //  File directory = cw.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        //  File file = new File(Environment.getExternalStorageDirectory(), "/holhga.pdf");


        //  File file = new File(directory, UUID.randomUUID().toString() +".pdf");


        File file = new File(directory, UUID.randomUUID().toString() + ".pdf");


        try {
            // after creating a file name we will
            // write our PDF file to that location.

            pdfDocument.writeTo(new FileOutputStream(file));

            // below line is to print toast message
            // on completion of PDF generation.
            Toast.makeText(context, "PDF file generated successfully.", Toast.LENGTH_SHORT).show();

            Log.i("holbaba", "pdf generado vacano");

            directory.delete();


        } catch (IOException e) {
            // below line is used
            // to handle error
            e.printStackTrace();

            Log.i("holbaba", "hay una excepcion " + e.getMessage());

        }
        // after storing our pdf to that
        // location we are closing our PDF file.
        // pdfDocument.close();

        //  createPdfFromView(context,"sdfsdf",pdfDocument);

        //    private static void createPdfFromView(Context context, String fileName,PdfDocument pdfDocument) {


    }
    public static void addImagenInPDF( Bitmap imagen,Bitmap imagen2, Canvas canvas) {

        RectF dst = new RectF(50, 225, 50 + 500, 50 + 350);
        canvas.drawBitmap(imagen, null, dst, null);


        RectF dstx = new RectF(50, 450, 50 + 500, 50 + 600);
        canvas.drawBitmap(imagen2, null, dstx, null);

    }



/*
    public static void addImageHeaderFootterPDF(Bitmap bitMapScaledHeader, Bitmap bmpGlobal, Canvas canvas) {
        // bmpGlobal creo que es footer

        //Cagregmoa el header bitmap
        canvas.drawBitmap(bitMapScaledHeader, 0, 0, mipaintLines);
        canvas.drawBitmap(bitMapScaledHeader, 0, 0, mipaintLines);
        canvas.drawBitmap(resize(bmpGlobal, 505, 280), 0, 535, mipaintLines);

    }

*/
    public static void addImageHeaderFootterPDF(Canvas canvas,Context context) {
        // bmpGlobal creo que es footer
        Bitmap  bitMapScaledHeader, bitmapScaledFooter;
        bitMapScaledHeader = BitmapFactory.decodeResource(context.getResources(), R.drawable.headerpdf);
        bitmapScaledFooter = BitmapFactory.decodeResource(context.getResources(), R.drawable.footer_pdf);

        RectF rectFtObj3=new RectF(0,570, 595,842);

        //Cagregmoa el header bitmap
      //  canvas.drawBitmap(bitMapScaledHeader, 0, 0, miPaint);
       // canvas.drawBitmap(resize(bitmapScaledFooter, 505, 280), 0, 535, miPaint);
        HelperImage.addImagenInPDF(bitmapScaledFooter, canvas,rectFtObj3);

       /////
         rectFtObj3=new RectF(0,0, 595,200);
        HelperImage.addImagenInPDF(bitMapScaledHeader, canvas,rectFtObj3);


        //        bitMapScaledHeader = Bitmap.createScaledBitmap(bmpGlobal, 595, 200, false);

    }



    @SuppressLint("Range")
    private static void checOrientation(Context context, Uri imageUri){

        String[] orientationColumn = {MediaStore.Images.Media.ORIENTATION};
        Cursor cur =context.getContentResolver().query(imageUri, orientationColumn, null, null, null);
        int orientation = -1;
        if (cur != null && cur.moveToFirst()) {
            orientation = cur.getInt(cur.getColumnIndex(orientationColumn[0]));
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(orientation);


    }



    private static void createFIRSTTable(Canvas canvas , int endXposicion, SetInformEmbarque1 informeObjct ) { //en el primer hay 43

        int startXposicion = 10;
        int initStartYposicion = 205;

        canvas.drawLine(startXposicion, initStartYposicion, endXposicion, initStartYposicion, mipaintLines);

        canvas.drawText("REPORTE DE CALIDAD DE CONTENEDORES", Math.round(595/2), 215, mipaintHeader);

        canvas.drawText("EXPORTADORA SOLICITANTE  " + informeObjct.getHacienda(), Math.round(595/2), 230, mipaintHeader);

        canvas.drawText("EXPORTADORA PROCESADA " + informeObjct.getEmpacadora(), Math.round(595/2), 245, mipaintHeader);

      //  canvas.drawLine(10, 255, endXposicion, 255, mipaintLines);


        int starYposicion = 255; //ERA 330

       // addDataList(1);

        //creamos primera linea horizontal
          canvas.drawLine(startXposicion,254,endXposicion,254,mipaintLines);

        Paint paintx= new Paint();
        // paint.setStyle(Paint.Color.);
        paintx.setColor(Color.parseColor("#4CAF50"));

        /***Creamos el background del PRIMER DATO , usando un drawrect*/
        canvas.drawRect(startXposicion, 254, endXposicion, starYposicion+10, paintx);


          for(int i = 0; i <data.size(); i++)  { //fecha DATA

              //primer texto a la izquiera
              canvas.drawText(data.get(i).dataFieldName.toUpperCase(Locale.ROOT), START_X_POSICION_TEXT_LEFT, starYposicion+8, mipaintLines);

              //segundo texto a la derecha
              canvas.drawText(data.get(i).dataContent.toUpperCase(Locale.ROOT) ,START_X_POSICION_TEXT_RIGTH+10 , starYposicion+8, mipaintLines);

              //creamos  linea horizontal
              canvas.drawLine(startXposicion,starYposicion+10,endXposicion,starYposicion+10,mipaintLines);

              starYposicion= starYposicion+10;

          }

        //linea vertical al empezar la izquiera
        canvas.drawLine(startXposicion,247,startXposicion,starYposicion-10 ,mipaintLines);

            //linea vertical en la mita de la tabla
        canvas.drawLine(278,247,278,starYposicion-10 ,mipaintLines);

        //linea vertical al finalizar la derecha
        canvas.drawLine(endXposicion,247,endXposicion,starYposicion-10 ,mipaintLines);





    }





    private static void addNewTable(Canvas canvas, int starYposicion, Paint mipaintLines, String textHEader, int tipoIdTabla, SetInformEmbarque1 informeObjct1, SetInformEmbarque2 informeObjct2, ProductPostCosecha productPostC) {
      //  tipoIdTabla,informeObjct1,informeObjct2,productPostC

        addDataList(tipoIdTabla,informeObjct1,informeObjct2,productPostC);

        int starYposicionDelPrincipio=starYposicion;

           if(tipoIdTabla  == Variables.FIRST_HEADER_OF_TABLE){
               canvas.drawRect(START_X_POSICION, starYposicion-15, END_X_POSICION, starYposicion+25, paintBacdkground);

               // canvas.drawLine(startXposicion, initStartYposicion, endXposicion, initStartYposicion, mipaintLines);
               canvas.drawText("REPORTE DE CALIDAD DE CONTENEDORES", Math.round(595/2), starYposicion, mipaintHeader);
               canvas.drawText("EXPORTADORA SOLICITANTE  " + informeObjct1.getHacienda(), Math.round(595/2), starYposicion+10, mipaintHeader);
               canvas.drawText("EXPORTADORA PROCESADA " + informeObjct1.getEmpacadora().toUpperCase(Locale.ROOT), Math.round(595/2), starYposicion+20, mipaintHeader);



               starYposicion= starYposicion+25; //actualizamos la posicion de donde termina el la posicino y del ultimo elemento

           }else {


               /***Creamos el background del header usando un drawrect*/
               canvas.drawRect(START_X_POSICION, starYposicion-10, END_X_POSICION, starYposicion+7, paintBacdkground);

               /***Creamos la primera LINEA DEL HEADER*/
               canvas.drawLine(START_X_POSICION,starYposicion-10,END_X_POSICION,starYposicion-10,mipaintLines);

               /***Creamos el texto header y lo posicinamos dpenediendo*/

                if(isAlignmentCenter) {
                    canvas.drawText(textHEader.toUpperCase(Locale.ROOT),  Math.round(595/2), starYposicion+2, mipaintHeader);


                }else{

                    canvas.drawText(textHEader.toUpperCase(Locale.ROOT), START_X_POSICION_TEXT_RIGTH+10 , starYposicion+2, paintIzquierda);


                }


               /***Creamos la segunda linea del header*/
               canvas.drawLine(START_X_POSICION,starYposicion+7,END_X_POSICION,starYposicion+7,mipaintLines);

               starYposicion= starYposicion+7;


           }



      //creamos el header de la tabla




        for(int i = 0; i <data.size(); i++)  {

            /**       canvas.drawText(data.get(i).dataContent.toUpperCase(Locale.ROOT) ,START_X_POSICION_TEXT_RIGTH+10 , starYposicion+8, mipaintLines);

             //creamos  linea horizontal
             canvas.drawLine(startXposicion,starYposicion+10,endXposicion,starYposicion+10,mipaintLines);

             starYposicion= starYposicion+10;*/

            canvas.drawText(data.get(i).dataFieldName.toUpperCase(Locale.ROOT), START_X_POSICION_TEXT_LEFT+10, starYposicion+8, paintContentText);

            //segundo texto a la derecha
            canvas.drawText(data.get(i).dataContent.toUpperCase(Locale.ROOT) ,START_X_POSICION_TEXT_RIGTH+10 , starYposicion+8, paintContentText);

            //creamos otra linea horizontal
            canvas.drawLine(START_X_POSICION,starYposicion+10,END_X_POSICION,starYposicion+10,mipaintLines);
            starYposicion= starYposicion+10;

        }

        //linea vertical al empezar la izquiera starYposicionDelPrincipio+10 despue sprobamos  //porbar starYposicion-20
        canvas.drawLine(START_X_POSICION,starYposicionDelPrincipio-10,START_X_POSICION,starYposicion ,mipaintLines);


        if(tipoIdTabla  == Variables.FIRST_HEADER_OF_TABLE){

            //linea vertical en la mita de la tabla
            canvas.drawLine(200,starYposicionDelPrincipio+25,200,starYposicion+25 ,mipaintLines);
        }else{

            //linea vertical en la mita de la tabla
            canvas.drawLine(200,starYposicionDelPrincipio+10,200,starYposicion+10 ,mipaintLines);
        }



        //linea vertical al finalizar la derecha
        canvas.drawLine(END_X_POSICION,starYposicionDelPrincipio-10,END_X_POSICION,starYposicion ,mipaintLines);


        ///SI ES TABLE QUEREMPOS QUE SE EJECUTE ESTO...

        currentPosicionLastYcanvasElement=starYposicion;


    }


    private static void addTableCalibracionFrutaCaleEnfunde(Canvas canvas, int starYposicion, Paint mipaintLines, String textHEader, int tipoIdTabla, SetInformEmbarque1 informeObjct1, SetInformEmbarque2 informeObjct2,ProductPostCosecha productPostC) {

        addDataList(tipoIdTabla,informeObjct1,informeObjct2,productPostC);

        int starYposicionDelPrincipio=starYposicion;

            canvas.drawRect(START_X_POSICION, starYposicion, END_X_POSICION, starYposicion+25, paintBacdkground);
            //paintBacdkgroundDurazno
            canvas.drawRect(START_X_POSICION, starYposicion+15, END_X_POSICION, starYposicion+25, paintBacdkgroundDurazno);

             //EL TITULO O HEADER
              canvas.drawText(textHEader,  Math.round(595/2), starYposicion+11, mipaintHeader);// ESTABA EN 12


        // canvas.drawLine(startXposicion, initStartYposicion, endXposicion, initStartYposicion, mipaintLines);
            canvas.drawText("SEMANA", START_X_POSICION+55, starYposicion+23, paintContentText);
            canvas.drawText("COLOR  " , START_X_POSICION+155, starYposicion+23, paintContentText);
            canvas.drawText("NUMERACION DE RACIMOS " , START_X_POSICION+250, starYposicion+23, paintContentText);
            canvas.drawText("PORCENTAJE" , START_X_POSICION+410, starYposicion+23, paintContentText);

            starYposicion= starYposicion+27; //actualizamos la posicion de donde termina el la posicino y del ultimo elemento




        for(int i = 0; i <data.size(); i++)  { //ITERAMOS LAS SEMANAS....

            //CENTRAR ESE TEXTO...
            canvas.drawText(data.get(i).dataFieldName.toUpperCase(Locale.ROOT), START_X_POSICION_TEXT_LEFT+10, starYposicion+8, paintContentText);

            //creamos otra linea horizontal
            canvas.drawLine(START_X_POSICION,starYposicion+10,END_X_POSICION,starYposicion+10,mipaintLines);
            starYposicion= starYposicion+10;

        }

        //linea vertical al empezar la izquiera starYposicionDelPrincipio+10 despue sprobamos  //porbar starYposicion-20
        canvas.drawLine(START_X_POSICION,starYposicionDelPrincipio-10,START_X_POSICION,starYposicion ,mipaintLines);

        //SEGUNDA LINEA VERTICAL
        canvas.drawLine(160,starYposicionDelPrincipio+10,160,starYposicion ,mipaintLines);

        //3RA LINEA VERTICAL
        canvas.drawLine(270,starYposicionDelPrincipio+10,270,starYposicion ,mipaintLines);

        //4TA LINEA VERTICAL
        canvas.drawLine(420,starYposicionDelPrincipio+10,420,starYposicion ,mipaintLines);


        //linea vertical al finalizar la derecha
        canvas.drawLine(END_X_POSICION,starYposicionDelPrincipio-10,END_X_POSICION,starYposicion ,mipaintLines);


        ///SI ES TABLE QUEREMPOS QUE SE EJECUTE ESTO...

        currentPosicionLastYcanvasElement=starYposicion;


    }

    // imagens....

    private static void addMoreDataDatosProceso(int posicionYstart,Canvas canvas) {
      //le agregamos 3 lineas mas... vertcales

        //segundo texto a la derecha
       /// canvas.drawText(data.get(i).dataContent.toUpperCase(Locale.ROOT) ,START_X_POSICION_TEXT_RIGTH+10 , starYposicion+8, paintContentText);

        //creamos la 3 linea vertical
        //3era linea
        canvas.drawLine(307,posicionYstart+10,307,currentPosicionLastYcanvasElement ,mipaintLines);

        //4arta linea
        canvas.drawLine(382,posicionYstart+10,382,currentPosicionLastYcanvasElement ,mipaintLines);

        //5era linea
        canvas.drawLine(457,posicionYstart+10,457,currentPosicionLastYcanvasElement ,mipaintLines);


    }


    private static void addMoreDataDatosHacienda(int posicionYstart,Canvas canvas) {
        //le agregamos 3 lineas mas... vertcales

        //segundo texto a la derecha
        /// canvas.drawText(data.get(i).dataContent.toUpperCase(Locale.ROOT) ,START_X_POSICION_TEXT_RIGTH+10 , starYposicion+8, paintContentText);

        //creamos la 3 linea vertical
        //3era linea
        canvas.drawLine(307,posicionYstart+10,307,currentPosicionLastYcanvasElement ,mipaintLines);

        //4arta linea
        canvas.drawLine(382,posicionYstart+10,382,currentPosicionLastYcanvasElement ,mipaintLines);

        //5era linea
        canvas.drawLine(457,posicionYstart+10,457,currentPosicionLastYcanvasElement ,mipaintLines);


    }




    private static void addNewTableSellosINtalados(Canvas canvas, int starYposicion, String textHEader,SetInformEmbarque2 infoem2) {

        //este podemos agregar algo mas al metodo adnewtable y elimnar este metodo ,despues

        int starYposicionDelPrincipio=starYposicion;
        //creamos el header de la tabla

        Paint paintHeader= new Paint();
        paintHeader.setTextSize(9);//el paint del texto del header sera un poco mas grande..
        paintHeader.setTextAlign(Paint.Align.CENTER);

        Paint paintx= new Paint();
        paintx.setTextSize(8);
        // paint.setStyle(Paint.Color.);
        paintx.setColor(Color.parseColor("#FFBDD6EE"));

        /***Creamos el background del header usando un drawrect*/
        canvas.drawRect(START_X_POSICION, starYposicion-10, END_X_POSICION, starYposicion+7, paintx);


        /***Creamos la primera LINEA DEL HEADER*/
        canvas.drawLine(START_X_POSICION,starYposicion-10,END_X_POSICION,starYposicion-10,mipaintLines);


        /***Creamos el texto header y lo posicinamos en el centro*/
        canvas.drawText(textHEader.toUpperCase(Locale.ROOT),  Math.round(595/2), starYposicion+2, paintHeader);


        /***Creamos la segunda linea del header*/
        canvas.drawLine(START_X_POSICION,starYposicion+7,END_X_POSICION,starYposicion+7,mipaintLines);


        starYposicion= starYposicion+7;


        for(int i = 0; i <data.size(); i++)  {

            /**       canvas.drawText(data.get(i).dataContent.toUpperCase(Locale.ROOT) ,START_X_POSICION_TEXT_RIGTH+10 , starYposicion+8, mipaintLines);
             //creamos  linea horizontal
             canvas.drawLine(startXposicion,starYposicion+10,endXposicion,starYposicion+10,mipaintLines);

             starYposicion= starYposicion+10;*/

            canvas.drawText(data.get(i).dataFieldName, START_X_POSICION_TEXT_LEFT+10, starYposicion+8, paintContentText);

            //segundo texto a la derecha
            canvas.drawText(data.get(i).dataContent ,START_X_POSICION_TEXT_RIGTH , starYposicion+8, paintContentText);

            //creamos otra linea horizontal
            canvas.drawLine(START_X_POSICION,starYposicion+10,END_X_POSICION,starYposicion+10,mipaintLines);
            starYposicion= starYposicion+10;



        }





        //linea vertical al empezar la izquiera starYposicionDelPrincipio+10 despue sprobamos  //porbar starYposicion-20
        canvas.drawLine(START_X_POSICION,starYposicionDelPrincipio-10,START_X_POSICION,starYposicion ,mipaintLines);

        //2da linea vertical
        canvas.drawLine(190,starYposicionDelPrincipio+10,190,starYposicion ,mipaintLines);

        //linea vertical al finalizar la derecha
        canvas.drawLine(END_X_POSICION,starYposicionDelPrincipio-10,END_X_POSICION,starYposicion ,mipaintLines);



        //agregamos otras 2 columnas mas datos como hora encendido

        //3era linea vertical
        canvas.drawLine(300,starYposicionDelPrincipio+10,300,starYposicion-10 ,mipaintLines);

        //4rta linea
        canvas.drawLine(430,starYposicionDelPrincipio+10,430,starYposicion-10 ,mipaintLines);


         //agregamos el texto


        //columna3 texto
        canvas.drawText("HORA DE ENCE: "+ infoem2.getTermografo1HoraEncendido(),310, starYposicionDelPrincipio+15, paintContentText);
        canvas.drawText("HORA DE ENCE: "+ infoem2.getTermografo2HoraEncendido() ,310 , starYposicionDelPrincipio+25, paintContentText);

        //columna4 texto
        canvas.drawText("HUBICACION PALLET: "+infoem2.getUbicacionPalletN1() ,440 , starYposicionDelPrincipio+15, paintContentText);
        canvas.drawText("HUBICACION PALLET: "+infoem2.getUbicacionPalletN2(),440 , starYposicionDelPrincipio+25, paintContentText);


        currentPosicionLastYcanvasElement=starYposicion;


    }








    private static void  addDataList(int Seccion, SetInformEmbarque1 informeObjct1 ,  SetInformEmbarque2 informeObjct2,ProductPostCosecha productPostC){

        data=new ArrayList<>();

     switch(Seccion){
                       //el data field es el nombre del dato
         case Variables.FIRST_HEADER_OF_TABLE:
             isAlignmentCenter=true;

             data.add(new DataToPDF(informeObjct1.getSimpleDataFormat(),"SEMANA "+informeObjct1.getSemana()));
             data.add(new DataToPDF(informeObjct1.getProductor(),"PRODUCTOR"));
             data.add(new DataToPDF(informeObjct1.getHacienda(),"HACIENDA"));
             data.add(new DataToPDF(informeObjct1.getCodeInforme(),"CODIGO PRODUCTOR"));
             data.add(new DataToPDF(informeObjct1.getInscirpMagap(),"CODIGO MAGAP"));
             data.add(new DataToPDF(informeObjct1.getPemarque(),"PUERTO EMBARQUE"));
             data.add(new DataToPDF(informeObjct1.getZona(),"ZONA"));
             data.add(new DataToPDF(informeObjct1.getHoraInicio(),"HORA INICIO"));
             data.add(new DataToPDF(informeObjct1.getHoraTermino(),"HORA TERMINO"));
             data.add(new DataToPDF(informeObjct1.getNguiaRemision(),"GUIA REMISION"));
             data.add(new DataToPDF(informeObjct1.get_nguia_transporte(),"GUIA DE TRANSPORTE"));
             data.add(new DataToPDF(informeObjct1.getNtargetaEmbarque(),"TARJA DE EMBARQUE"));
             data.add(new DataToPDF(String.valueOf(informeObjct1.getEdiNhojaEvaluacion()),"HOJA DE EVALUACION"));

             break;


         case Variables.TABLE_PRODUCTOS_POSTCOSECHA: //usamos un objetoproducto postcosecha
             isAlignmentCenter=true;
             //solo iteramos el mapa  //cremoas el mapa usando el objeto productos postcosecha
             HashMap<String, String> mapProductPostcos = Utils.creMapByObject(productPostC);

             for (Map.Entry<String, String> entry : mapProductPostcos.entrySet()) {
                 String key = entry.getKey();
                 String cantidadProducto = entry.getValue();
                  data.add(new DataToPDF(cantidadProducto,key));
             }



             break;



         case Variables.DATOS_DEL_CONTENEDOR:
             isAlignmentCenter=false;

             data.add(new DataToPDF(informeObjct1.getDestinoContenedor(),"DESTINO"));
             data.add(new DataToPDF(informeObjct1.getVapor(),"VAPOR"));
             data.add(new DataToPDF(informeObjct1.getNumeroViajeContenedor(),"NUMERACION DE CONTENEDOR"));
             data.add(new DataToPDF(informeObjct1.getHoraLlegadaContenedor(),"HORA LLEGADA"));
             data.add(new DataToPDF(informeObjct1.getHoraSalidadContenedor(),"HORA SALIDA"));


             break;


         case Variables.TABLE_SELLOS_LLEGADA:
             isAlignmentCenter=false;

             data.add(new DataToPDF(informeObjct1.getSelloPlasticoNaviera(),"SEllO PLASTICO NAVIERA"));
             data.add(new DataToPDF(informeObjct1.getStickerVentoExtern(),"STICKER VENTOLERA EXTERNA N1"));
             data.add(new DataToPDF(informeObjct1.getnSerieFunda(),"NUMERO DE SERIE DE FUNDA"));
             data.add(new DataToPDF(informeObjct1.getCableRastreoLlegada(),"CABLE DE RASTERO LLEGADA"));
             data.add(new DataToPDF(informeObjct1.getBooking(),"BOOKING"));
             data.add(new DataToPDF(informeObjct1.getMaxGross(),"MAXGROSS"));
             data.add(new DataToPDF(informeObjct1.getTare(),"TARE"));


             break;


         case Variables.TABLE_DATOS_TRANSPORTISTA:
             isAlignmentCenter=false;

             data.add(new DataToPDF(informeObjct2.getCompaniaTranporte(),"COMPAÑIA TRANSPORTISTA"));
             data.add(new DataToPDF(informeObjct2.getNombreChofer(),"NOMBRE CHOFER"));
             data.add(new DataToPDF(informeObjct2.getCedulaChofer(),"CEDULA"));
             data.add(new DataToPDF(informeObjct2.getCelularChofer(),"CELULAR"));
             data.add(new DataToPDF(informeObjct2.getPlacaChofer(),"PLACA"));
             data.add(new DataToPDF(informeObjct2.getColorCAbezal(),"COLOR CABEZAL"));

             break;


         case Variables.TABLE_DATOS_PROCESO :
             isAlignmentCenter=false;

             data.add(new DataToPDF(informeObjct2.getTipoPlastico(),"PLASTICO"));
             data.add(new DataToPDF(informeObjct2.getTipoCaja(),"TIPO DE CAJA"));

             if(informeObjct2.isHayExcelnsuchado()) {
                 data.add(new DataToPDF("SI","ENSUNCHADO"));

             }else{
                 data.add(new DataToPDF("NO","ENSUNCHADO"));


             }

             if(informeObjct2.isHayBalanza()) {
                 data.add(new DataToPDF("SI","BALANZA"));

             }else{
                 data.add(new DataToPDF("NO","BALANZA"));


             }



             data.add(new DataToPDF(informeObjct2.getCondicionBalanza(),"CONDICION DE BALANZA"));

             data.add(new DataToPDF(informeObjct2.getTipoDeBalanza(),"TIPÓ DE BALANZA"));

                 if(informeObjct2.getTipoDeBalanzaRepeso().equals("Ninguna") || informeObjct2.getTipoDeBalanzaRepeso().equals("") ||
                         informeObjct2.getTipoDeBalanzaRepeso().equals(" ") )  {
                     data.add(new DataToPDF("NO","BALANZA DE REPESA"));


                 }else{

                     data.add(new DataToPDF("SI","BALANZA DE REPESA"));
                     data.add(new DataToPDF(informeObjct2.getTipoDeBalanzaRepeso(),"TIPO DE BALANZA DE REPESA"));


                 }




             break;

         case Variables.TABLE_DATOS_HACIENDA:
             //data.add(new DataToPDF(informeObjct2.,"FUENTE DE AGUA"));
             data.add(new DataToPDF(informeObjct2.getCompaniaTranporte(),"AGUA CORRIDA"));
             data.add(new DataToPDF(informeObjct2.getCompaniaTranporte(),"LAVADO DE RACIMOS"));
             data.add(new DataToPDF(informeObjct2.getCompaniaTranporte(),"FUMIGACION DE CORONA LINEA 1"));


             break;


     }


    }



    private static void initPaintObject() {
        mipaintHeader= new Paint();
         mipaintLines = new Paint();
        paintBacdkground=new Paint();
        paintBacdkgroundDurazno =new Paint();


        paintBacdkgroundDurazno.setColor(Color.parseColor("#FFA865"));

        paintContentText=new Paint();

        mipaintHeader.setTextAlign(Paint.Align.CENTER);
        mipaintHeader.setColor(Color.parseColor("#4CAF50"));
        mipaintHeader.setTextSize(9);


         paintIzquierda= new Paint();
        paintIzquierda.setTextAlign(Paint.Align.LEFT);
        paintIzquierda.setColor(Color.parseColor("#4CAF50"));
        paintIzquierda.setTextSize(9);





        paintContentText.setTextSize(8);

        mipaintLines.setTextAlign(Paint.Align.LEFT);
        // mipaintLines.setTextAlign(Paint.Align.CENTE);
        mipaintLines.setColor(Color.parseColor("#3A3A3A"));


        paintBacdkground.setTextSize(8);
        // paint.setStyle(Paint.Color.);
        paintBacdkground.setColor(Color.parseColor("#FFBDD6EE"));
    }




    private static void createPages_addImgs( Context context, String anexoNombre){
         boolean esPrimeraPagina=true;
        int debuGcONTADOR =0;
        Log.i("contaburx","el size de la lista es "+currentListImagesSeccion.size());

        while(!allImagesISUsed(currentListImagesSeccion)){ //mientras quedan imagenes si usar////

           if(debuGcONTADOR==0) { ///significa que es la primera pagina

               Paint paintIzquierda2= new Paint();
               paintIzquierda2.setTextAlign(Paint.Align.LEFT);
               paintIzquierda2.setColor(Color.parseColor("#4CAF50"));
               paintIzquierda2.setTextSize(20);
               //le agregamos el titulo al anexo
               currentCanvasObjec.drawText(anexoNombre, 210, 170, paintIzquierda2);
           }

            debuGcONTADOR++;
               //si es la primera pagina



            Log.i("contaburx","se ejecuto ESto "+debuGcONTADOR+" veces");

            int patronEncontrado=HelperImage.buscaPosiblePatronParaOrdenar(currentListImagesSeccion);



            if(patronEncontrado==Variables.TRES_IMGS_VERTCLES){

                Log.i("contaburx","es Variables.TRES_IMGS_VERTCLES");
                addImagenSet(Variables.TRES_IMGS_VERTCLES,context);
                //ENTONCES ESTOS ENCONTRADOS LOS PONEMOS QUE YA SE USARON.....


            }

            else if(patronEncontrado == Variables.DOS_IMGS_VERTICALES) {
                Log.i("contaburx","es el DOS_IMGS_VERTICALES ");

                addImagenSet(Variables.DOS_IMGS_VERTICALES,context);


            }

            else if(patronEncontrado == Variables.UNAVERTICAL_Y_OTRA_HORIZONTAL) {
                Log.i("contaburx","es  UNAVERTICAL_Y_OTRA_HORIZONTAL");

                addImagenSet(Variables.UNAVERTICAL_Y_OTRA_HORIZONTAL,context);


            }


            else if(patronEncontrado == Variables.DOS_HORIZONTALES) {
                Log.i("contaburx","es el DOS_HORIZONTALES ");

                addImagenSet(Variables.DOS_HORIZONTALES,context);


            }



            else if(patronEncontrado == Variables.DEFAULNO_ENCONTRO_NADA) {
                Log.i("contaburx","es el  DEFAULNO_ENCONTRO_NADA");

                addImagenSet(Variables.DEFAULNO_ENCONTRO_NADA,context);


            }







        }







    }




    private static void CreateNewPageAndFinishAnterior(Context context, PdfDocument.Page myPageAnterior){
        //finalizamos el pdf anterior si lo hay
        pdfDocument.finishPage(myPageAnterior);

        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(595, 842, 1).create();
        PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);
        Canvas canvas3 = myPage.getCanvas();
        addImageHeaderFootterPDF(canvas3,context);

        currentPagePdfObjec = myPage;
        currentCanvasObjec =canvas3;




    }




private static ArrayList<ImagesToPdf> devuleveList3verticalesSIhay(ArrayList<ImagesToPdf> lisT, String propiedad){
int encontradosPropiedad=0;

    ArrayList<ImagesToPdf>lisTx=new ArrayList<>();

    for(int indice=0; indice<lisT.size(); indice++){

if(lisT.get(indice).horientacionImagen.equals(propiedad) && ! lisT.get(indice).estaENPdf ){
    encontradosPropiedad++;
    lisTx.add(lisT.get(indice));

    if(lisTx.size()==3){
        break;

    }
}

        ///agrega


        // si existen al menos 3 para usar

        //crea una lista de estos 3...y ponlos....


        //primero si podemos poner las 3 primeras imagenes....
        //comprobamos que existan al menos 3 imagenes


    }
      return  lisTx;
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




    private static void markImgComoUsada(ArrayList<ImagesToPdf> list){

        //buscamos esos ids,, y marcamos como usado...
        //vamos atenber dos listas.... una lista que es la litsa del conjunto actual y una lista que dice


          for(int i= 0; i<list.size(); i++){ ///
              String uniqueId=list.get(i).uniQueId;

              for(int indice=0; indice<currentListImagesSeccion.size(); indice++){

                  if(uniqueId.equals(currentListImagesSeccion.get(indice).uniQueId)) {
                      currentListImagesSeccion.get(indice).estaENPdf=true;
                      Log.i("contabur","el size de marcads como usadas es   "+currentListImagesSeccion.size());


                  }



              }

          }




    }


    private static void addNameAnexo(String nameAnexo){

        //UJSAMOS EL LcurrentCanvasObjec

    }




    private static void addImagenSet(int tipoOrdenImgs, Context context){
        Bitmap bitMap ;

        //comprobar en que linea ... comprobar la posicion de la ultima

        final  int ANCHO_IMG_VERTICAL =180;
        final  int LARGO_iMG_VERTICAL_ =250;


        int lef= 12;
        final int SPACE=10;



        if(tipoOrdenImgs==Variables.TRES_IMGS_VERTCLES){ //modo 3 imagenes en una linea...
            Log.i("contabur","hay 3 imagenes verticales hurrazzx");

            for(int indice=0; indice<HelperImage.imagesSetToCurrentFila.size(); indice++){


                          RectF rectFtObj3=new RectF(lef,poscYUltImgColoc, lef +ANCHO_IMG_VERTICAL,poscYUltImgColoc+LARGO_iMG_VERTICAL_);
                          bitMap =HelperImage.imagesSetToCurrentFila.get(indice).miBitmap;
                          HelperImage.addImagenInPDF(bitMap, currentCanvasObjec,rectFtObj3);

                          lef= lef+ ANCHO_IMG_VERTICAL +SPACE;


                          markImgComoUsada(HelperImage.imagesSetToCurrentFila); //podemos pasarle una lista de este 3 imagenes o marca una por una como hicimos arriba

            }



            poscYUltImgColoc=poscYUltImgColoc+LARGO_iMG_VERTICAL_+10;
            checkTopCreateNewPageORfinishCurrent(LARGO_iMG_VERTICAL_,context);


            //vamos a crear otra pagina ahora....

        }


        else if(tipoOrdenImgs==Variables.UNAVERTICAL_Y_OTRA_HORIZONTAL){ //1 vertical y otro horizontal en la misma linea

                 RectF rectFtObj3=new RectF(lef,poscYUltImgColoc, lef +ANCHO_IMG_VERTICAL,poscYUltImgColoc+LARGO_iMG_VERTICAL_);
                  bitMap =HelperImage.imagesSetToCurrentFila.get(0).miBitmap; //la primera ubicaion debe contener la imagen vertical
                  HelperImage.addImagenInPDF(bitMap, currentCanvasObjec,rectFtObj3);
                  lef= lef+ ANCHO_IMG_VERTICAL + SPACE+SPACE;

                      //la imagen horizontal
                     rectFtObj3=new RectF(lef,poscYUltImgColoc, lef +ANCHO_IMG_VERTICAL+172,poscYUltImgColoc+LARGO_iMG_VERTICAL_);
                    bitMap =HelperImage.imagesSetToCurrentFila.get(1).miBitmap;
                    HelperImage.addImagenInPDF(bitMap, currentCanvasObjec,rectFtObj3);



                      markImgComoUsada(HelperImage.imagesSetToCurrentFila); //podemos pasarle una lista de este 3 imagenes o marca una por una como hicimos arriba


            poscYUltImgColoc=poscYUltImgColoc+LARGO_iMG_VERTICAL_+10;
            checkTopCreateNewPageORfinishCurrent(LARGO_iMG_VERTICAL_,context);


        }


        else if(tipoOrdenImgs==Variables.DOS_IMGS_VERTICALES){ //2 imagenes verticales en una linea

                lef =100;

                RectF rectFtObj3=new RectF(lef,poscYUltImgColoc, lef +ANCHO_IMG_VERTICAL,poscYUltImgColoc+LARGO_iMG_VERTICAL_);
                bitMap =HelperImage.imagesSetToCurrentFila.get(0).miBitmap;
                HelperImage.addImagenInPDF(bitMap, currentCanvasObjec,rectFtObj3);
                lef= lef+ ANCHO_IMG_VERTICAL +SPACE+10;


               rectFtObj3=new RectF(lef,poscYUltImgColoc, lef +ANCHO_IMG_VERTICAL,poscYUltImgColoc+LARGO_iMG_VERTICAL_);
               bitMap =HelperImage.imagesSetToCurrentFila.get(1).miBitmap;
               HelperImage.addImagenInPDF(bitMap, currentCanvasObjec,rectFtObj3);


                markImgComoUsada(HelperImage.imagesSetToCurrentFila); //podemos pasarle una lista de este 3 imagenes o marca una por una como hicimos arriba


            poscYUltImgColoc=poscYUltImgColoc+LARGO_iMG_VERTICAL_+10;
            checkTopCreateNewPageORfinishCurrent(LARGO_iMG_VERTICAL_,context);


        }


        else if(tipoOrdenImgs==Variables.DOS_HORIZONTALES){ //2 imagenes verticales en una linea


            if(poscYUltImgColoc >210) {
                Log.i("contabur","se ejecutyo trapita");

                poscYUltImgColoc =poscYUltImgColoc+20;

            }


                Log.i("contabur","es el horizontal true");

                RectF rectFtObj3=new RectF(lef,poscYUltImgColoc, lef +ANCHO_IMG_VERTICAL+100,poscYUltImgColoc+LARGO_iMG_VERTICAL_-50);
                bitMap =HelperImage.imagesSetToCurrentFila.get(0).miBitmap;
                HelperImage.addImagenInPDF(bitMap, currentCanvasObjec,rectFtObj3);
                lef= lef+ ANCHO_IMG_VERTICAL+100 +SPACE;

                rectFtObj3=new RectF(lef,poscYUltImgColoc, lef +ANCHO_IMG_VERTICAL+100,poscYUltImgColoc+LARGO_iMG_VERTICAL_-50);
                bitMap =HelperImage.imagesSetToCurrentFila.get(1).miBitmap;
                HelperImage.addImagenInPDF(bitMap, currentCanvasObjec,rectFtObj3);

                markImgComoUsada(HelperImage.imagesSetToCurrentFila); //podemos pasarle una lista de este 3 imagenes o marca una por una como hicimos arriba

            poscYUltImgColoc=poscYUltImgColoc+LARGO_iMG_VERTICAL_+10;



            checkTopCreateNewPageORfinishCurrent(LARGO_iMG_VERTICAL_,context);


        }


        else if(tipoOrdenImgs==Variables.DEFAULNO_ENCONTRO_NADA){ //2 imagenes verticales en una linea
             ///en caso que no ecneuntre nigun patron

        }


        //y oior ultimo si hay una imagen sola....tamnto vertical o horizontal ..no la aghregues ,,es una opcion




    }


    private static  void checkTopCreateNewPageORfinishCurrent(int LARGO_iMG_VERTICAL_,Context context) {

        if(allImagesISUsed(currentListImagesSeccion)) { //no queda ninguna en esta seccion o solo queda una imagen

            pdfDocument.finishPage(currentPagePdfObjec);
            Log.i("contabur","ya se usaron todas o solo sobro una ,finalizamos ");

        }else {


            if(poscYUltImgColoc >LARGO_iMG_VERTICAL_+LARGO_iMG_VERTICAL_ ) { // si es mayor a dos veces el largo de la imagen creamos otra pagina..
                poscYUltImgColoc=210;                                                        //o si es mayor o igual a un nuemro especifico que es mano o menos la posicion de la segunda fila
                CreateNewPageAndFinishAnterior(context,currentPagePdfObjec);
                Log.i("contabur","vamos a crear otra pagina ");


            }


        } //si quedan mas


        //vamos a crear otra pagina ahora....


    }

}
