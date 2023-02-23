package com.tiburela.qsercom.activities.othersActivits;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.widget.ImageView;
import android.widget.Toast;

import com.tiburela.qsercom.R;

import java.io.File;
import java.io.IOException;

public class PdfActivity extends AppCompatActivity {

    String filePath = "";
    ImageView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        pdfView = (ImageView) findViewById(R.id.pdfview);
        filePath = getIntent().getStringExtra("filePath");
        File file = new File(filePath);
        try{
            openPDF(file);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

        //File file = new File(filePath);
        //Uri uri = Uri.fromFile(file);
        //pdfView.fromUri(uri).load();


    }


    public void openPDF(File file) throws IOException {
        // File file = new File(filePath);

        ParcelFileDescriptor fileDescriptor = null;
        fileDescriptor = ParcelFileDescriptor.open(
                file, ParcelFileDescriptor.MODE_READ_ONLY);

        //min. API Level 21
        PdfRenderer pdfRenderer = null;
        pdfRenderer = new PdfRenderer(fileDescriptor);

        final int pageCount = pdfRenderer.getPageCount();
        Toast.makeText(this,
                "pageCount = " + pageCount,
                Toast.LENGTH_LONG).show();

        //Display page 0
        PdfRenderer.Page rendererPage = pdfRenderer.openPage(0);
        int rendererPageWidth = rendererPage.getWidth();
        int rendererPageHeight = rendererPage.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(
                rendererPageWidth,
                rendererPageHeight,
                Bitmap.Config.ARGB_8888);
        rendererPage.render(bitmap, null, null,
                PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

        pdfView.setImageBitmap(bitmap);
        rendererPage.close();

        pdfRenderer.close();
        fileDescriptor.close();
    }



}