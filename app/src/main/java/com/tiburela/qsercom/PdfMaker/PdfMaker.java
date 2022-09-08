package com.tiburela.qsercom.PdfMaker;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.tiburela.qsercom.activities.MainActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import com.tiburela.qsercom.R;

public class PdfMaker {

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
        canvas.drawText("This is sample document which we have created.", 396, 560, miPaint);

        // after adding all attributes to our
        // PDF file we will be finishing our page.
        pdfDocument.finishPage(myPage);


        exportPd(pdfDocument,context);




    }




    public static void generatePdfReport1(Context context) {
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
        canvas.drawText("This is sample document which we have created.", 396, 560, miPaint);

        // after adding all attributes to our
        // PDF file we will be finishing our page.
        pdfDocument.finishPage(myPage);

       // exportPd(pdfDocument,context);

        saveFile("adriniato",pdfDocument);

        //   exportPd(pdfDocument,context);

//    private static void createPdfFromView(Context context, String fileName, int pageWidth, int pageHeight, int pageNumber,PdfDocument pdfDocument) {
    //   createPdfFromView( context,"cawaike", 400,600  ,  1,pdfDocument);


        }






    public static void exportPd(PdfDocument pdfDocument , Context context){

        // below line is used to set the name of
        // our PDF file and its path.
      //  ContextWrapper cw = new ContextWrapper(context);
     //   File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);

       // File file = new File(directory, "UniqueFileName" + ".pdf");

        ContextWrapper cw = new ContextWrapper(context);


       // String fullPath =cw.getExternal(Environment.DIRECTORY_DOWNLOADS).toString();
///
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
      //  File directory = cw.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

      //  File file = new File(Environment.getExternalStorageDirectory(), "/holhga.pdf");


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
        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/mypdf/";

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
}
