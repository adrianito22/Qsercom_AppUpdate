package com.tiburela.qsercom.testDELETE;

import static com.tiburela.qsercom.activities.formularios.ActivityReporteCalidadCamionesyCarretas.context;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
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
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.opencensus.resource.Resource;

public class Test2 extends AppCompatActivity {
BarChart barChart;

Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.char_layout);

        barChart=findViewById(R.id.chart);

         ImageView imageView3 =findViewById(R.id.imageView3);
        imageView3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                File file = new File(path, "new Line chart.png");

                if( file.exists()){
                    // File myFile = new File("/scard/myfolder");
                    Uri myUri = Uri.fromFile(file);

                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), myUri);
                    } catch (IOException e) {
                        e.printStackTrace();

                        Log.i("hagyaua","el error es es "+e.getMessage());
                    }
                    imageView3.setImageBitmap(bitmap);

                }else{
                    Log.i("hagyaua","no existe ");


                }


            }
        });


        button =findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adrianoi(barChart);

            }
        });

       // final String[] quarters = new String[] { "Q1", "Q2", "Q3", "Q4" ,"bd","sdf"};
        barChart.getXAxis().setDrawGridLines(false);

        final String [] quarters=  getResources().getStringArray(R.array.array_defectos_frutax);
        ///
       // barChart.setBackground(getDrawable(R.drawable.bacgroundsercom));

        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return quarters[(int) value];
            }
        };
        /*

        ArrayList NoOfEmp = new ArrayList();

        NoOfEmp.add(new BarEntry(945f, 0));
        NoOfEmp.add(new BarEntry(1040f, 1));
        NoOfEmp.add(new BarEntry(1133f, 2));
        NoOfEmp.add(new BarEntry(1240f, 3));
        NoOfEmp.add(new BarEntry(1369f, 4));
        NoOfEmp.add(new BarEntry(1487f, 5));
        NoOfEmp.add(new BarEntry(1501f, 6));
        NoOfEmp.add(new BarEntry(1645f, 7));
        NoOfEmp.add(new BarEntry(1578f, 8));
        NoOfEmp.add(new BarEntry(1695f, 9));

        ArrayList year = new ArrayList();

        year.add("2008");
        year.add("2009");
        year.add("2010");
        year.add("2011");
        year.add("2012");
        year.add("2013");
        year.add("2014");
        year.add("2015");
        year.add("2016");
        year.add("2017");

       // IBarDataSet HOLA= new BarDataSet( new BarEntry(),"");

        BarDataSet bardataset = new BarDataSet(NoOfEmp, "No Of Employee");
        chart.animateY(5000);
        BarData data = new BarData(bardataset, bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(data);




*/


/*
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0f,30f));
        barEntries.add(new BarEntry(1f,80f));
        barEntries.add(new BarEntry(2f,60f));
        barEntries.add(new BarEntry(3f,50f));
        barEntries.add(new BarEntry(4f,70f));
        barEntries.add(new BarEntry(5f,60f));

    */


        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0f,30f));
        barEntries.add(new BarEntry(1f,80f));
        barEntries.add(new BarEntry(2f,60f));
        barEntries.add(new BarEntry(3f,50f));
        barEntries.add(new BarEntry(4f,70f));

        barEntries.add(new BarEntry(5f,35f));
        barEntries.add(new BarEntry(6f,85f));
        barEntries.add(new BarEntry(7f,65f));
        barEntries.add(new BarEntry(8f,55f));
        barEntries.add(new BarEntry(9f,75f));


        barEntries.add(new BarEntry(10f,40f));
        barEntries.add(new BarEntry(11f,90f));
        barEntries.add(new BarEntry(12f,70f));
        barEntries.add(new BarEntry(13f,60f));
        barEntries.add(new BarEntry(14f,75f));


        barEntries.add(new BarEntry(15f,30f));
        barEntries.add(new BarEntry(16f,80f));
        barEntries.add(new BarEntry(17f,60f));
        barEntries.add(new BarEntry(18f,50f));
        barEntries.add(new BarEntry(19f,70f));


        barEntries.add(new BarEntry(20f,30f));
        barEntries.add(new BarEntry(21f,80f));
        barEntries.add(new BarEntry(22f,60f));





        BarDataSet barDataSet = new BarDataSet(barEntries,"Defectos");
        barDataSet.setValueTextSize(10f);
        barDataSet.setFormSize(9f);
        //barDataSet.setDrawIcons(true);


        ArrayList<String> theDates = new ArrayList<>();
        theDates.add("April");
        theDates.add("MAy");
        theDates.add("June");
        theDates.add("July");
        theDates.add("August");
        theDates.add("September");

        BarData theData = new BarData(barDataSet);
        theData.setBarWidth(0.9f);
        barChart.setData(theData);
        //barChart.setTouchEnabled(true);
       // barChart.set
        //barChart.setDragEnabled(true);
       // barChart.setScaleEnabled(true);
        barChart.setFitBars(true);
       // barChart
        barChart.setDrawGridBackground(false);

        barDataSet.setColors(new int[]{
                R.color.durazon , R.color.durazon


        } , Test2.this);




        //  barChart.setDescription("hola");

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(formatter);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(23);
       // Bitmap b = getChartBitmap();


        xAxis.setTextSize(7/*textSize*/);

       // barChart.getDraw
        //Bitmap bitmap = Bitmap.createBitmap(barChart.getWidth(), barChart.getHeight(), Bitmap.Config.ARGB_8888);
      //  barChart.saveToGallery("test.png", 50);
       /// barChart


    }

/*
    public static Bitmap getBitmapFromView(View graph) {
        Bitmap returnedBitmap = Bitmap.createBitmap(graph.getWidth(), graph.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = graph.getBackground();

        if (bgDrawable != null)
            bgDrawable.draw(canvas);

        else
            canvas.drawColor(Color.WHITE);
            view.draw(canvas);
        return returnedBitmap;

    }


 */
   private void adrianoi(BarChart barChart) {

       barChart.saveToGallery("new Line chart", 85);
       barChart.setSaveEnabled(true);
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
