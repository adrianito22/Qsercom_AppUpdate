package com.tiburela.qsercom.PdfMaker;

import static com.itextpdf.kernel.pdf.PdfName.Column;
import static com.itextpdf.kernel.pdf.PdfName.Columns;
import static com.itextpdf.kernel.pdf.PdfName.Image;
import static com.itextpdf.kernel.pdf.PdfName.Table;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.compose.ui.text.Paragraph;

import android.content.Context;
import android.graphics.fonts.Font;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import com.google.android.material.tabs.TabLayout;


import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.kernel.layout.element.Table; //???? der any probllem?

//write here which class not found and wirte whee you want to use i timport com.itextpdf.layout.element.Cell;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.tagutils.AccessibilityProperties;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.GrooveBorder;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.BlockElement;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.BlockElement;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

//issue resolved edear

import com.itextpdf.layout.renderer.IRenderer;
import com.tiburela.qsercom.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class PdfMaker2_0 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_maker20);

        try {
            createPDF();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


//Table   issue resolved?
//Table
    }


    public void createPDF() throws FileNotFoundException {

         String pdfDirecory=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();

        //doble chekeo si la current canvas object no fue terminada la finalizamos
        // pdfDocument.finishPage(currentPagePdfObjec) ;


        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(directory, UUID.randomUUID().toString() + ".pdf");

           //chekear si existe si es asi sobrescribirlo



             OutputStream estrema =new FileOutputStream(file);
             PdfWriter writer = new PdfWriter(file); //le pasmaos el file

               PdfDocument miPFDocumentkernel= new PdfDocument(writer);
               PageSize pageSize= PageSize.A4;  //si no le quitamos el rotate...

              PdfPage pagePdf= miPFDocumentkernel.addNewPage(pageSize);///creo que a este le agregamos la data

             Document midocumento= new Document(miPFDocumentkernel);



              float []array={3,1,6};

             Table table = new Table(array);

           Paragraph fgf = new Paragraph("HOLA");


           Cell celdcolor=new Cell();


          // celdcolor.setBackgroundColor(dvolor);

        DeviceRgb dvolor= new DeviceRgb(255, 255, 255);
        table.setStrokeColor(dvolor);

      //  SolidBorder ADDD= new SolidBorder(dvolor, 3);

        Border boder2= new GrooveBorder(dvolor, 3);


        Cell cell= new Cell();


        HelperPdf pdfHelper= new HelperPdf();
      //  table.addCell(new Cell().add(fgf).setBorderTop(ADDD));


       // table.addCell(new Cell().add(fgf).setBorderTop(ADDD));
              //table.addCell(new Cell().add(fgf));

              table.addHeaderCell(pdfHelper.createInfoImgtoPDF(getDrawable(R.drawable.logopdf),PdfMaker2_0.this));


            //  table.addCell(new Cell().add(fgf));
          //  table.addCell(new Cell().add(pdfHelper.createInfoImgtoPDF(getDrawable(R.drawable.logopdf),PdfMaker2_0.this)));

            table.setBorder(boder2);

        // table.setBackgroundColor(dvolor);

        Paragraph mipara= new Paragraph("sdfsdsd");



      //  table.setBorder(boder2);

        //midocumento.add(table);


        ///pagar....

        //gift card.........
        ////


        //////
        /////.....................


        //20qr ...........

        /////
        ///claro .............  .........
        ///////////


        midocumento.close();


            Log.i("holbaba", "pdf generado vacano");






    }


}