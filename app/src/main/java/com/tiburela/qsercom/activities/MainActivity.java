package com.tiburela.qsercom.activities;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.tiburela.qsercom.R;

public class MainActivity extends AppCompatActivity {

    Button btngGoDriveActivity;



    private static final int PERMISSION_REQUEST_CODE=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btngGoDriveActivity=findViewById(R.id.btngGoDriveActivity);
        btngGoDriveActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, ActivityGoogleDrive.class));

            }
        });

        if(!checkPermission()){

            requestPermission();
         //   Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();


        }



    }



    public void generatePdf(View vista){
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
        miPaint.setColor(ContextCompat.getColor(this, R.color.purple_200));

        // below line is used to draw text in our PDF file.
        // the first parameter is our text, second parameter
        // is position from start, third parameter is position from top
        // and then we are passing our variable of paint which is title.
        canvas.drawText("A portal for IT professionals.", 209, 100, miPaint);
        canvas.drawText("Geeks for Geeks", 209, 80, miPaint);

        // similarly we are creating another text and in this
        // we are aligning this text to center of our PDF file.
        miPaint.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        miPaint.setColor(ContextCompat.getColor(this, R.color.purple_200));
        miPaint.setTextSize(15);

        // below line is used for setting
        // our text to center of PDF.
        miPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("This is sample document which we have created.", 396, 560, miPaint);

        // after adding all attributes to our
        // PDF file we will be finishing our page.
        pdfDocument.finishPage(myPage);


        exportPd(pdfDocument);




    }

    private void exportPd(PdfDocument pdfDocument ){

        // below line is used to set the name of
        // our PDF file and its path.
        File file = new File(Environment.getExternalStorageDirectory(), "GFG.pdf");

        try {
            // after creating a file name we will
            // write our PDF file to that location.
            pdfDocument.writeTo(new FileOutputStream(file));

            // below line is to print toast message
            // on completion of PDF generation.
            Toast.makeText(MainActivity.this, "PDF file generated successfully.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            // below line is used
            // to handle error
            e.printStackTrace();
        }
        // after storing our pdf to that
        // location we are closing our PDF file.
        pdfDocument.close();


    }




    private boolean checkPermission() {
        // checking of permissions.
        int permission1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }



    private void requestPermission() {
        // requesting permissions if not provided.
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {

                // after requesting permissions we are showing
                // users a toast message of permission granted.
                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (writeStorage && readStorage) {
                    Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denied.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }


}