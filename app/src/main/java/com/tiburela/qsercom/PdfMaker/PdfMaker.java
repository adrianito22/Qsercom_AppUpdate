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
import java.util.Locale;
import java.util.UUID;

import com.tiburela.qsercom.R;
import com.tiburela.qsercom.models.DataToPDF;
import com.tiburela.qsercom.models.InformEmbarque;
import com.tiburela.qsercom.utils.Variables;

public class PdfMaker {

 static   Paint mipaintHeader;
    static   Paint paintContentText;

    static    Paint mipaintLines;

    static  Paint paintBacdkground;

   private static final int START_X_POSICION=20;
    private  static final int END_X_POSICION= 575;
    private static  final int START_X_POSICION_TEXT_RIGTH= 280;
    private static final int START_X_POSICION_TEXT_LEFT=30;



    static ArrayList<DataToPDF>data;


   public static  int currentPosicionLastYcanvasElement = 0;


    public static void generatePdfReport1(Context context, String codeInforme, int ediNhojaEvaluacion, String zona, String productor, String codigo, String pemarque, String nguiaRemision, String hacienda, String _nguia_transporte, String ntargetaEmbarque, String inscirpMagap, String horaInicio, String horaTermino, String semana, String empacadora, String contenedor, String cbservacion) {
        // creating an object variable
        // for our PDF document.
        PdfDocument pdfDocument = new PdfDocument();

        // two variables for paint "paint" is used
        // for drawing shapes and we will use "title"
        // for adding text in our PDF file.
        // Paint paint = new Paint();
        Paint miPaint = new Paint(); //paint es un pincel propiedad,, y contiene todo lo que contiene un pincel(color,ancho..tipo,etc.)

        /****creramos e inicilizamos un objeto page nfo que recibe como parametros un width.heigth y pgae number que ahora es 1 */
        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(400, 600, 1).create();

        // below line is used for setting
        // start page for our PDF file.
        /***creamos e iniclizamos un objeto   PdfDocument.Page  Y le gramos la confirguracion o el objeto  mypageInfo */
        PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);

        /*** creating a variable for canvas */
        // from our page of PDF.
        Canvas canvas = myPage.getCanvas();

        // below line is used to draw our image on our PDF file.
        // the first parameter of our drawbitmap method is
        // our bitmap
        // second parameter is position from left
        // third parameter is position from top and last
        // one is our variable for paint.
        //  canvas.drawBitmap(scaledbmp, 56, 40, paint);

        // below line is used for adding typeface for
        // our text which we will be adding in our PDF file.

        //Configur5amos el paintobjeto

        miPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        // below line is used for setting text size
        // which we will be displaying in our PDF file.
        miPaint.setTextSize(15);

        // below line is sued for setting color
        // of our text inside our PDF file.
        miPaint.setColor(ContextCompat.getColor(context, R.color.purple_200));

        // below line is used to draw text in our PDF file.
        // the first parameter is our text, second parameter
        // is position from start, third parameter is position from top
        // and then we are passing our variable of paint which is title.
        canvas.drawText("A portal for IT professionals.", 209, 100, miPaint);
        canvas.drawText("Geeks for Geeks", 209, 80, miPaint);

        // similarly we are creating another text and in this
        // we are aligning this text to center of our PDF file.
        miPaint.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        miPaint.setColor(ContextCompat.getColor(context, R.color.purple_200));
        miPaint.setTextSize(15);

        // below line is used for setting
        // our text to center of PDF.
        miPaint.setTextAlign(Paint.Align.CENTER);
      //  canvas.drawText("This is sample document which we have created.", 396, 560, miPaint);

        // after adding all attributes to our
        // PDF file we will be finishing our page.
        pdfDocument.finishPage(myPage);


        exportPdxFZ(pdfDocument,context);




    }




    public static void generatePdfReport1(Context context, InformEmbarque informe) {
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
        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(595, 842, 1).create();

        // below line is used for setting
        // start page for our PDF file.
        /***creamos e iniclizamos un objeto   PdfDocument.Page  Y le gramos la confirguracion o el objeto  mypageInfo */
        PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);

        /*** creating a variable for canvas */
        Canvas canvas = myPage.getCanvas();


        //Cagregmoa el header bitmap
        canvas.drawBitmap(bitMapScaledHeader, 0, 0, miPaint);
        canvas.drawBitmap(bitMapScaledHeader, 0, 0, miPaint);
        canvas.drawBitmap( resize(bmpGlobal,505,280), 0, 535, miPaint);

        //cremoa sun objketo
     InformEmbarque   informeObjct = new InformEmbarque("aaad01",12,"Sur","Horlando Mendez","01dssd","Adrtinañ","021121","Florestilla","45654","5454","ADER INCRIPCION","8:00","16:23","12","La Florencia","Contenedor 01","falto mas cola y pan");
       //iniiclizamoas paints
        initPaintObject();





     //creamos el header




        /****productos poscosecha utilizados  */

        int posicionYdondeStartDibujamos=220;

        //AGREGAMOS LAS TABLAS EN EL CANVAS

        addNewTable( canvas,  posicionYdondeStartDibujamos,mipaintLines,"ESTE ES EL PRIMERA TABLA y HEADER",Variables.FIRST_HEADER_OF_TABLE,informeObjct);
        addNewTable( canvas,  currentPosicionLastYcanvasElement+10,mipaintLines,"PRODUCTOS POST-COSECHA UTILIZADOS",Variables.TABLE_PRODUCTOS_POSTCOSECHA,informeObjct);
        addNewTable( canvas,  currentPosicionLastYcanvasElement+10,mipaintLines,"Datos del contenedor",Variables.TABLE_PRODUCTOS_POSTCOSECHA,informeObjct);
        addNewTable( canvas,  currentPosicionLastYcanvasElement+10,mipaintLines,"SEllOS LLEGADA",Variables.TABLE_PRODUCTOS_POSTCOSECHA,informeObjct);
        addNewTableSellosINtalados( canvas,  currentPosicionLastYcanvasElement+10,mipaintLines,"SEllOS instalados");
        addNewTable( canvas,  currentPosicionLastYcanvasElement+10,mipaintLines,"SEllOS LLEGADA",Variables.TABLE_PRODUCTOS_POSTCOSECHA,informeObjct);



          //ahora agregamos mas data en otra pagina
        //agregamos otra pagina
        //creamos primera linea horizontal
        //  canvas.drawLine(startXposicion,320,endXposicion,320,mipaintLines);



        int value=mypageInfo.getPageWidth()-20;

        Log.i("searcladatas","misdataes "+value);


        // after adding all attributes to our
        // PDF file we will be finishing our page.
        pdfDocument.finishPage(myPage);

       // exportPd(pdfDocument,context);

        exportPdxFZ(pdfDocument,context);

        //saveFile("mipdf01",pdfDocument);

        //   exportPd(pdfDocument,context);

//    private static void createPdfFromView(Context context, String fileName, int pageWidth, int pageHeight, int pageNumber,PdfDocument pdfDocument) {
    //   createPdfFromView( context,"cawaike", 400,600  ,  1,pdfDocument);


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

public static  void createpdfhiwRSOLUT(Context context)  {

    Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.footer_pdf);

//boolean img1_SetImage - used to check Img1 is available or not
//img1_Uri - Uri of Img1
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inScaled = false;
       // Bitmap bmp = BitmapFactory.decodeFile(img1_Uri.getPath(), opt);

        PdfDocument.PageInfo myPageInfo2 = new PdfDocument.PageInfo.Builder(595, 842, 1).create();

  //  PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(595, 842, 1).create();
    PdfDocument myPDFDoc = new PdfDocument();


    PdfDocument.Page myPage2 = myPDFDoc.startPage(myPageInfo2);

    Canvas myCanvas2 = myPage2.getCanvas();

// Work out scaleFactor to get all the image on the page
        float wScale, hScale, scaleFactor;
        wScale = (float) 595 / bmp.getWidth(); // If you don't cast Int/Int = Int so you loose any decimal places.
        hScale = (float) 842 / bmp.getHeight();  // Alternative is to define the size as float e.g. 842.0f
        if (wScale >= hScale) {
            scaleFactor = hScale;
        } else {
            scaleFactor = wScale;
        }



        Paint paint = new Paint();
        paint.setAntiAlias(true);
        myCanvas2.scale(scaleFactor, scaleFactor);
        myCanvas2.drawBitmap(bmp,20,880,paint);


    myPDFDoc.finishPage(myPage2);


    exportPdxFZ(myPDFDoc,context);



}



    public static void exportPdxFZ(PdfDocument pdfDocument , Context context){

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


        File file = new File(directory, UUID.randomUUID().toString() +".pdf");


        try {
            // after creating a file name we will
            // write our PDF file to that location.
            pdfDocument.writeTo(new FileOutputStream(file));

            // below line is to print toast message
            // on completion of PDF generation.
            Toast.makeText(context, "PDF file generated successfully.", Toast.LENGTH_SHORT).show();

            Log.i("holbaba","pdf generado vacano");

            directory.delete();


        } catch (IOException e) {
            // below line is used
            // to handle error
            e.printStackTrace();

            Log.i("holbaba","hay una excepcion "+e.getMessage());

        }
        // after storing our pdf to that
        // location we are closing our PDF file.
       // pdfDocument.close();

      //  createPdfFromView(context,"sdfsdf",pdfDocument);

        //    private static void createPdfFromView(Context context, String fileName,PdfDocument pdfDocument) {


    }


    public  static void  xportPdf2(PdfDocument pdfDocument , Context context) {


        // write the document content
        String directory_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getPath() + "/mypdf/";

        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String targetPdf = directory_path+"test-2.pdf";
        File filePath = new File(targetPdf);
        try {
            pdfDocument.writeTo(new FileOutputStream(filePath));
            Toast.makeText(context, "Done", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Log.e("main", "error "+e.toString());
            Toast.makeText(context, "Something wrong: " + e.toString(),  Toast.LENGTH_LONG).show();
        }
    }

    private static void createPdfFromView(Context context, String fileName,PdfDocument pdfDocument) {

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path, fileName.concat(".pdf"));

        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (file.exists()) {



          //  pdfDocument.finishPage(page);

            try {
                Toast.makeText(context, "Saving...", Toast.LENGTH_SHORT).show();
                pdfDocument.writeTo(fOut);

            } catch (IOException e) {
                Toast.makeText(context, "Failed...", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            pdfDocument.close();

            /*Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);*/

        } else

        {
            //..


        }




    }

    public static  void saveFile( String fileName, PdfDocument document) {

        try {

            File mypath=new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),fileName+".pdf");
            document.writeTo(new FileOutputStream(mypath));

            document.close();

            mypath.delete();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }


    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize, boolean filter) {
        float ratio = Math.min( maxImageSize / realImage.getWidth(), maxImageSize / realImage.getHeight());
        if (ratio >= 1.0) {
            return realImage;
        }

        int height = Math.round(ratio * realImage.getHeight());

        return Bitmap.createScaledBitmap(realImage, 595, height, filter);
    }




    private static void createFIRSTTable(Canvas canvas , int endXposicion, InformEmbarque informeObjct ) { //en el primer hay 43

        int startXposicion = 10;
        int initStartYposicion = 205;

        canvas.drawLine(startXposicion, initStartYposicion, endXposicion, initStartYposicion, mipaintLines);

        canvas.drawText("REPORTE DE CALIDAD DE CONTENEDORES", Math.round(595/2), 215, mipaintHeader);

        canvas.drawText("EXPORTADORA SOLICITANTE  " + informeObjct.getHacienda(), Math.round(595/2), 230, mipaintHeader);

        canvas.drawText("EXPORTADORA PROCESADA " + informeObjct.getEmpacadora(), Math.round(595/2), 245, mipaintHeader);

      //  canvas.drawLine(10, 255, endXposicion, 255, mipaintLines);


        int starYposicion = 255; //ERA 330

        addDataList(1);

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


    private static void addNewTable(Canvas canvas, int starYposicion, Paint mipaintLines, String textHEader,int tipoIdTabla,InformEmbarque informeObjct) {

        addDataList(tipoIdTabla);




        int starYposicionDelPrincipio=starYposicion;

           if(tipoIdTabla  == Variables.FIRST_HEADER_OF_TABLE){
               canvas.drawRect(START_X_POSICION, starYposicion-15, END_X_POSICION, starYposicion+25, paintBacdkground);

               // canvas.drawLine(startXposicion, initStartYposicion, endXposicion, initStartYposicion, mipaintLines);
               canvas.drawText("REPORTE DE CALIDAD DE CONTENEDORES", Math.round(595/2), starYposicion, mipaintHeader);
               canvas.drawText("EXPORTADORA SOLICITANTE  " + informeObjct.getHacienda(), Math.round(595/2), starYposicion+10, mipaintHeader);
               canvas.drawText("EXPORTADORA PROCESADA " + informeObjct.getEmpacadora(), Math.round(595/2), starYposicion+20, mipaintHeader);



               starYposicion= starYposicion+27; //actualizamos la posicion de donde termina el la posicino y del ultimo elemento

           }else {


               /***Creamos el background del header usando un drawrect*/
               canvas.drawRect(START_X_POSICION, starYposicion-10, END_X_POSICION, starYposicion+7, paintBacdkground);

               /***Creamos la primera LINEA DEL HEADER*/
               canvas.drawLine(START_X_POSICION,starYposicion-10,END_X_POSICION,starYposicion-10,mipaintLines);

               /***Creamos el texto header y lo posicinamos en el centro*/
               canvas.drawText(textHEader.toUpperCase(Locale.ROOT),  Math.round(595/2), starYposicion+2, mipaintHeader);

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

        //linea vertical en la mita de la tabla
        canvas.drawLine(278,starYposicionDelPrincipio+10,278,starYposicion ,mipaintLines);

        //linea vertical al finalizar la derecha
        canvas.drawLine(END_X_POSICION,starYposicionDelPrincipio-10,END_X_POSICION,starYposicion ,mipaintLines);


        currentPosicionLastYcanvasElement=starYposicion;


    }


    private static void addNewTableSellosINtalados(Canvas canvas, int starYposicion, Paint paintContentText, String textHEader) {

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
            canvas.drawText(data.get(i).dataContent ,START_X_POSICION_TEXT_RIGTH+10 , starYposicion+8, paintContentText);

            //creamos otra linea horizontal
            canvas.drawLine(START_X_POSICION,starYposicion+10,END_X_POSICION,starYposicion+10,mipaintLines);
            starYposicion= starYposicion+10;



        }





        //linea vertical al empezar la izquiera starYposicionDelPrincipio+10 despue sprobamos  //porbar starYposicion-20
        canvas.drawLine(START_X_POSICION,starYposicionDelPrincipio-10,START_X_POSICION,starYposicion ,mipaintLines);

        //linea vertical en la mita de la tabla
        canvas.drawLine(278,starYposicionDelPrincipio+10,278,starYposicion ,mipaintLines);

        //linea vertical al finalizar la derecha
        canvas.drawLine(END_X_POSICION,starYposicionDelPrincipio-10,END_X_POSICION,starYposicion ,mipaintLines);



        //agregamos otras 2 columnas mas datos como hora encendido

        //primera columna
      //  canvas.drawLine(300,starYposicionDelPrincipio+10,300,starYposicion-10 ,mipaintLines);
        canvas.drawLine(370,starYposicionDelPrincipio+10,370,starYposicion-10 ,mipaintLines);
        canvas.drawLine(460,starYposicionDelPrincipio+10,460,starYposicion-10 ,mipaintLines);


         //agregamos el texto


        //columna3 texto
        canvas.drawText("Horaenendido 10:20" ,370+10 , starYposicionDelPrincipio+15, mipaintLines);
        canvas.drawText("Horaenendido 9:20" ,370+10 , starYposicionDelPrincipio+25, mipaintLines);

        //columna4 texto
        canvas.drawText("Hubicacion pallet ezq" ,460+10 , starYposicionDelPrincipio+15, mipaintLines);
        canvas.drawText("Hubicacion pallet de" ,460+10 , starYposicionDelPrincipio+25, mipaintLines);


        currentPosicionLastYcanvasElement=starYposicion;


    }






    private int getLastPosicionElementY(int ultimaPosicionY){


        return   currentPosicionLastYcanvasElement+ultimaPosicionY;
    }


    private static void  addDataList(int Seccion){
        InformEmbarque   informeObjct = new InformEmbarque("aaad01",12,"Sur","Horlando Mendez","01dssd","Adrtinañ","021121","Florestilla","45654","5454","ADER INCRIPCION","8:00","16:23","12","La Florencia","Contenedor 01","falto mas cola y pan");

        data=new ArrayList<>();

     switch(Seccion){
                       //el data field es el nombre del dato
         case Variables.FIRST_HEADER_OF_TABLE:
             data.add(new DataToPDF(informeObjct.getSemana(),"Semana"));
             data.add(new DataToPDF(informeObjct.getProductor(),"Productor"));
             data.add(new DataToPDF(informeObjct.getHacienda(),"Hacienda"));
             data.add(new DataToPDF(informeObjct.getCodeInforme(),"Codigo informe"));
             data.add(new DataToPDF(informeObjct.getZona(),"Zona"));
             data.add(new DataToPDF(informeObjct.getHoraInicio(),"Hora Inicio"));
             data.add(new DataToPDF(informeObjct.getHoraInicio(),"Hora inicio2"));
             data.add(new DataToPDF(informeObjct.getHoraInicio(),"Hora inicio3" ));
             data.add(new DataToPDF(informeObjct.getHoraInicio(),"Hora inicio4"));




             break;


         case Variables.TABLE_PRODUCTOS_POSTCOSECHA:

             //PRODUCTOS POSTCOSECHA UTILIZADFOS
             //si el producto postosecha...  obtenemops los productos postosecha a partir del objeto

             //EL PRODUCTO Y SU CANTIDADD
             data.add(new DataToPDF("BSC100","4500CC"));
             data.add(new DataToPDF("ZXD","45MM"));
             data.add(new DataToPDF("SOLFER","1L"));
             data.add(new DataToPDF("samero1","4MM"));
             data.add(new DataToPDF("Acido citrico","56MM"));

             break;

         case 3:
             break;


         case 4:
             break;


         case 5:
             break;


         case 6:
             break;




     }


    }



    private static void initPaintObject() {
        mipaintHeader= new Paint();
         mipaintLines = new Paint();
        paintBacdkground=new Paint();
        paintContentText=new Paint();

        mipaintHeader.setTextAlign(Paint.Align.CENTER);
        mipaintHeader.setColor(Color.parseColor("#4CAF50"));
        mipaintHeader.setTextSize(9);

        paintContentText.setTextSize(8);

        mipaintLines.setTextAlign(Paint.Align.LEFT);
        // mipaintLines.setTextAlign(Paint.Align.CENTE);
        mipaintLines.setColor(Color.parseColor("#3A3A3A"));


        paintBacdkground.setTextSize(8);
        // paint.setStyle(Paint.Color.);
        paintBacdkground.setColor(Color.parseColor("#FFBDD6EE"));
    }



    private void addImagesInpdfPage(){

        //descraghamos las imagenes

    //comporbamos que sea horixintales o verticales.....
    //si hay vertticales ponemos hasta 3.
    // si hay horixontales 2 ... horizontaqles o una horizontal y otras verticall...

    ///tendsra como parametro la pagina...



    }


}
