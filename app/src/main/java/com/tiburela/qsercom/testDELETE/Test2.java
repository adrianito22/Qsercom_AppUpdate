package com.tiburela.qsercom.testDELETE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Test2 extends AppCompatActivity {
BarChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.char_layout);

        chart=findViewById(R.id.chart);


        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 30f));
        entries.add(new BarEntry(1f, 80f));
        entries.add(new BarEntry(2f, 60f));
        entries.add(new BarEntry(3f, 50f));
        // gap of 2f
        entries.add(new BarEntry(5f, 70f));
        entries.add(new BarEntry(6f, 60f));
        BarDataSet set = new BarDataSet(entries, "BarDataSet");

        BarData data = new BarData(set);
        data.addDataSet("dfg",1);
        data.setBarWidth(0.9f); // set custom bar width
        chart.setData(data);
        chart.setFitBars(true); // make the x-axis fit exactly all bars
        chart.invalidate();


/*
        YourData[] dataObjects = ...;
        List<Entry> entries = new ArrayList<Entry>();
        for (YourData data : dataObjects) {
            // turn your data into Entry objects
            entries.add(new Entry(data.getValueX(), data.getValueY()));
        }
    */




/*
        YourData[] group1 = ...;
        YourData[] group2 = ...;
        List<BarEntry> entriesGroup1 = new ArrayList<>();
        List<BarEntry> entriesGroup2 = new ArrayList<>();
// fill the lists
        for(int i = 0; i < group1.length; i++) {
            entriesGroup1.add(new BarEntry(i, group1.getValue()));
            entriesGroup2.add(new BarEntry(i, group2.getValue()));
        }
        BarDataSet set1 = new BarDataSet(entriesGroup1, "Group 1");
        BarDataSet set2 = new BarDataSet(entriesGroup2, "Group 2");

        */



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
