package com.tiburela.qsercom.testDELETE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.GrooveBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.tiburela.qsercom.PdfMaker.HelperPdf;
import com.tiburela.qsercom.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

public class Test2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_racimos_recha);

    }




    public void createPDF() throws FileNotFoundException {

        String pdfDirecory= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        //doble chekeo si la current canvas object no fue terminada la finalizamos
        // pdfDocument.finishPage(currentPagePdfObjec) ;
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(directory, UUID.randomUUID().toString() + ".pdf");
        //chekear si existe si es asi sobrescribirlo
        OutputStream estrema =new FileOutputStream(file);
        PdfWriter writer = new PdfWriter(file); //le pasmaos el file

        PdfDocument miPFDocumentkernel= new PdfDocument(writer);
        PageSize pageSize= PageSize.A4;  //si no le quitamos el rotate...
        PdfPage pagePdf= miPFDocumentkernel.addNewPage(pageSize);///
        Document midocumento= new Document(miPFDocumentkernel); // le gagregamos data a este...




        //  float []array={3,1,6};

       // Table table = new Table(array);
        Paragraph fgf = new Paragraph("HOLA");
        Cell celdcolor=new Cell();

        // celdcolor.setBackgroundColor(dvolor);
        DeviceRgb dvolor= new DeviceRgb(255, 255, 255);
      //  table.setStrokeColor(dvolor);
        //  SolidBorder ADDD= new SolidBorder(dvolor, 3);
        Border boder2= new GrooveBorder(dvolor, 3);
        Cell cell= new Cell();


        HelperPdf pdfHelper= new HelperPdf();
        //  table.addCell(new Cell().add(fgf).setBorderTop(ADDD));
        // table.addCell(new Cell().add(fgf).setBorderTop(ADDD));
        //table.addCell(new Cell().add(fgf));
        //      table.addHeaderCell(pdfHelper.createInfoImgtoPDF(getDrawable(R.drawable.logopdf),PdfMaker2_0.this));
        //  table.addCell(new Cell().add(fgf));
        //  table.addCell(new Cell().add(pdfHelper.createInfoImgtoPDF(getDrawable(R.drawable.logopdf),PdfMaker2_0.this)));
        //  table.setBorder(boder2);
        // table.setBackgroundColor(dvolor);
        // Paragraph mipara= new Paragraph("sdfsdsd");
        //  table.setBorder(boder2);
        //  midocumento.add(table);









        midocumento.close();

    }

}