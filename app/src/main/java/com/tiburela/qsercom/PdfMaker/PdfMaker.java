package com.tiburela.qsercom.PdfMaker;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import com.tiburela.qsercom.R;
import com.tiburela.qsercom.models.DataToPDF;
import com.tiburela.qsercom.models.ProductPostCosecha;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

public class PdfMaker {

    static Paint mipaintHeader;
    static Paint paintContentText;
    static boolean isAlignmentCenter =false;
    static Paint mipaintLines;
    static Paint paintIzquierda ;
    static Paint paintBacdkground;
    static Paint paintBacdkgroundDurazno;


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

        PdfDocument pdfDocument = new PdfDocument();

        Paint miPaint = new Paint(); //paint es un pincel propiedad,, y contiene todo lo que contiene un pincel(color,ancho..tipo,etc.)

        /****creramos e inicilizamos un objeto page nfo que recibe como parametros un width.heigth y pgae number que ahora es 1 */
        PdfDocument.PageInfo mypageInfo1 = new PdfDocument.PageInfo.Builder(595, 842, 1).create();

        // below line is used for setting
        // start page for our PDF file.
        /***creamos e iniclizamos un objeto   PdfDocument.Page  Y le gramos la confirguracion o el objeto  mypageInfo */
        PdfDocument.Page myPage1 = pdfDocument.startPage(mypageInfo1);

        /*** creating a variable for canvas */
        Canvas canvas = myPage1.getCanvas();

        addImageHeaderFootterPDF(bitMapScaledHeader, bmpGlobal, canvas, miPaint);


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
        addImageHeaderFootterPDF(bitMapScaledHeader, bmpGlobal, canvas2, miPaint);


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


    public static void addImageHeaderFootterPDF(Bitmap bitMapScaledHeader, Bitmap bmpGlobal, Canvas canvas, Paint miPaint) {
        // bmpGlobal creo que es footer

        //Cagregmoa el header bitmap
        canvas.drawBitmap(bitMapScaledHeader, 0, 0, miPaint);
        canvas.drawBitmap(bitMapScaledHeader, 0, 0, miPaint);
        canvas.drawBitmap(resize(bmpGlobal, 505, 280), 0, 535, miPaint);

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





}
