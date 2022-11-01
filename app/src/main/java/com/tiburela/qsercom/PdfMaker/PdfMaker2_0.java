package com.tiburela.qsercom.PdfMaker;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.compose.ui.text.Paragraph;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.kernel.layout.element.Table; //???? der any probllem?

//write here which class not found and wirte whee you want to use i timport com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

//issue resolved edear


import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.models.ControlCalidad;
import com.tiburela.qsercom.models.NameAndValue;
import com.tiburela.qsercom.utils.Variables;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PdfMaker2_0 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_maker20);

        try {
            createPDF();
            Log.i("debbdf","exclente create");

        } catch (FileNotFoundException e) {
            e.printStackTrace();

            Log.i("debbdf","la excepcion es "+e.getMessage());
        }

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
        PdfPage pagePdf= miPFDocumentkernel.addNewPage(pageSize);///
        Document midocumentotoAddData= new Document(miPFDocumentkernel,pageSize); // le gagregamos data a este...
        midocumentotoAddData.setMargins(0, 0, 0, 0);

        HelperPdf pdfHelper= new HelperPdf();



        /**add header imagen*/
        Image imglogqSercom=pdfHelper.createInfoImgtoPDF(getDrawable(R.drawable.headerpdf),1);
        // imglogqSercom.setHorizontalAlignment(HorizontalAlignment.CENTER)
        midocumentotoAddData.add(imglogqSercom).setTopMargin(0f);
        Rectangle remaining = midocumentotoAddData.getRenderer().getCurrentArea().getBBox();
        float y = remaining.getTop();
        Log.i("homero","eldocuemnto.getRenderer().getCurrentArea().getBBox() en logo es ES "+y);


        float position = midocumentotoAddData.getRenderer().getCurrentArea().getBBox().getTop();
        Log.i("miodatr","el mi logoqsercom "+position);


        //float sizeColumnsTableTitle[]= {1};
        Table tableTitle=  new Table(1);

        /**TABLE TITULO EXPORTADORA SOLICTADA y procesada*/
        Cell cell1= new Cell().add(new Paragraph("REPORTE CALIDAD CONTENEDORES").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f));
        Cell cell2= new Cell().add(new Paragraph("EXPORTADORA SOLICITADA: BANDECUA MARCA DEL MONTE").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f));
        Cell cell3= new Cell().add(new Paragraph("EXPORTADORA PROCESADA LAT BIO")).setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f);


        tableTitle.addCell(cell1.setHeight(10f)).setBorder(Border.NO_BORDER);
        tableTitle.addCell(cell2.setHeight(10f).setBorder(Border.NO_BORDER));
        tableTitle.addCell(cell3.setHeight(10f)).setBorder(Border.NO_BORDER);


        tableTitle.setWidth(pageSize.getWidth()-100f);
        tableTitle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        tableTitle.setMarginLeft(40f);
        tableTitle.setMarginRight(20f);
        midocumentotoAddData.add(tableTitle);


        /**EMPEZAMOS CON LAS TABLAS*/
        float sizeColumns[]= {1,3};
        Table table1=  new Table(sizeColumns);
        // table1.

        DeviceRgb rgbColor= new DeviceRgb(153, 255, 229); //color verde claro


        /**add primer cuadro..*/
        //crea list de celds...y add values...
        ArrayList<NameAndValue>dataTOtable1=HelperPdf.generaDataToTable(Variables.CurrenReportPart1,Variables.CurrenReportPart2,Variables.CurrenReportPart3,1,Variables.currenProductPostCosecha);
        HashMap<String,Cell> listCellsToTabCurrentTab= HelperPdf.generateHasmapFieldnameandValue(dataTOtable1,50,0);



        //editamos la tabla 1
        listCellsToTabCurrentTab.get("0name").setBackgroundColor(rgbColor);
        listCellsToTabCurrentTab.get("0value").setBackgroundColor(rgbColor);

        ///productos postcosecha

        addCellsInTable(listCellsToTabCurrentTab,table1);
        table1.setWidth(pageSize.getWidth()-100f);
        table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table1.setMarginLeft(40f);
        table1.setMarginRight(20f);
        midocumentotoAddData.add(table1);




        /**productos postosecha*/

        float sizeColumns2[]= {1,1};
        table1=  new Table(sizeColumns2);

        Cell cell0= new Cell(1,2).add(new Paragraph("PRODUCTOS POSTOCOSECHA UTILIZADOS").setTextAlignment(TextAlignment.CENTER).setFontSize(8f)) ;

        cell0.setBackgroundColor(rgbColor); //editamos el color
        table1.addCell(cell0);


        ArrayList<NameAndValue> dataTOtable2=HelperPdf.generaDataToTable(Variables.CurrenReportPart1,Variables.CurrenReportPart2,Variables.CurrenReportPart3,2,Variables.currenProductPostCosecha);


        HashMap<String, Cell> listCellsToTabCurrentTab2= HelperPdf.generateHasmapFieldnameandValue(dataTOtable2,100,0);
        ///productos postcosecha


        addCellsInTable(listCellsToTabCurrentTab2,table1);
        table1.setWidth(pageSize.getWidth()-100f);
        table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table1.setMarginLeft(40f);
        table1.setMarginRight(20f);
        midocumentotoAddData.add(table1);

        // table1.setMarginTop(10f);

        /**DATOS  DE CONTENEDOR***/

        float sizeColumns3[]= {1,1};
        table1=  new Table(sizeColumns2);

        cell0= new Cell(1,2).add(new Paragraph("DATOS DE CONTENEDOR").setTextAlignment(TextAlignment.CENTER).setFontSize(8f)) ;

        cell0.setBackgroundColor(rgbColor); //editamos el color
        table1.addCell(cell0);



        dataTOtable2=HelperPdf.generaDataToTable(Variables.CurrenReportPart1,Variables.CurrenReportPart2,Variables.CurrenReportPart3,3,Variables.currenProductPostCosecha);
        listCellsToTabCurrentTab2= HelperPdf.generateHasmapFieldnameandValue(dataTOtable2,50,0);
        ///LA POSICION 5 LA EDITAMOS
        listCellsToTabCurrentTab2.get("2value").setBackgroundColor(rgbColor); //editamos el color


        addCellsInTable(listCellsToTabCurrentTab2,table1);
        table1.setWidth(pageSize.getWidth()-100f);
        table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table1.setMarginLeft(40f);
        table1.setMarginRight(20f);
        midocumentotoAddData.add(table1);

        /**SELLOS DE LLEGADA*/

        float sizeColumns4[]= {1,1};
        table1=  new Table(sizeColumns2);

        cell0= new Cell(1,2).add(new Paragraph("SELLLOS LLEGADA").setTextAlignment(TextAlignment.CENTER).setFontSize(8f)) ;

        cell0.setBackgroundColor(rgbColor); //editamos el color
        table1.addCell(cell0);



        dataTOtable2=HelperPdf.generaDataToTable(Variables.CurrenReportPart1,Variables.CurrenReportPart2,Variables.CurrenReportPart3,4,Variables.currenProductPostCosecha);
        listCellsToTabCurrentTab2= HelperPdf.generateHasmapFieldnameandValue(dataTOtable2,50,0);
        ///LA POSICION 5 LA EDITAMOS
        listCellsToTabCurrentTab2.get("2value").setBackgroundColor(rgbColor); //editamos el color


        addCellsInTable(listCellsToTabCurrentTab2,table1);
        table1.setWidth(pageSize.getWidth()-120f);
        table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table1.setMarginLeft(60f);
        table1.setMarginRight(20f);
        midocumentotoAddData.add(table1);

        /**SELLLOS INSTALADOS*/


        float sizeColumns5[]= {1,2,1,1};
        table1=  new Table(sizeColumns5);

        cell0= new Cell(1,4).add(new Paragraph("SELLLOS INSTALADOS").setTextAlignment(TextAlignment.CENTER).setFontSize(8f)) ;

        cell0.setBackgroundColor(rgbColor); //editamos el color
        table1.addCell(cell0);


        dataTOtable2=HelperPdf.generaDataToTable(Variables.CurrenReportPart1,Variables.CurrenReportPart2,Variables.CurrenReportPart3,5,Variables.currenProductPostCosecha);
        listCellsToTabCurrentTab2= HelperPdf.generateHasmapFieldnameandValue(dataTOtable2,50,600);

        //editamos otras columnas 10
        listCellsToTabCurrentTab2.get("10value"); //editamos el color



        addCellsInTable(listCellsToTabCurrentTab2,table1);
        table1.setWidth(pageSize.getWidth()-120f);
        table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table1.setMarginLeft(60f);
        table1.setMarginRight(20f);
        midocumentotoAddData.add(table1);

        /**TERMINA PRIMERA HOJA DEBERIA CREAR OTROA HOJA*/

        midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

        //agregamos el header



        /**DATOS DE TRANSPORTISTA */

        /**add header imagen*/

         imglogqSercom=pdfHelper.createInfoImgtoPDF(getDrawable(R.drawable.headerpdf),1);
         midocumentotoAddData.add(imglogqSercom).setTopMargin(0f);

        ///AGREGAMOS LA IMAGEN HEADER AQUI


        float sizeColumns6[]= {4,5};
        table1=  new Table(sizeColumns6);

        cell0= new Cell(1,2).add(new Paragraph("DATOS DE TRANSPORTISTA").setTextAlignment(TextAlignment.CENTER).setFontSize(8f)) ;

        cell0.setBackgroundColor(rgbColor); //editamos el color
        table1.addCell(cell0);


        dataTOtable2=HelperPdf.generaDataToTable(Variables.CurrenReportPart1,Variables.CurrenReportPart2,Variables.CurrenReportPart3,6,Variables.currenProductPostCosecha);
        listCellsToTabCurrentTab2= HelperPdf.generateHasmapFieldnameandValue(dataTOtable2,50,0);

        //editamos otras columnas 10
        // listCellsToTabCurrentTab2.get("10value"); //editamos el color


        addCellsInTable(listCellsToTabCurrentTab2,table1);
        table1.setWidth(pageSize.getWidth()-120f);
        table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table1.setMarginLeft(60f);
        table1.setMarginRight(20f);
        table1.setMarginTop(5f);
        midocumentotoAddData.add(table1);



        /**DATOS DE PROCESO parte 1 */

        float sizeColumns7[]= {1,2,2,2,2,2,2,2,2};
        table1=  new Table(sizeColumns7);



        cell0= new Cell(1,9).add(new Paragraph("DATOS DE PROCESO").setTextAlignment(TextAlignment.CENTER).setFontSize(8f)) ;

        cell0.setBackgroundColor(rgbColor); //editamos el color
        table1.addCell(cell0);


        table1 =    HelperPdf.createTable2(table1,rgbColor,Variables.CurrenReportPart2) ;


        table1.setWidth(pageSize.getWidth()-120f);
        table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table1.setMarginLeft(60f);
        table1.setMarginRight(20f);
        table1.setMarginTop(5f);
        midocumentotoAddData.add(table1);



        /**DATOS DE PROCESO parte 2 */

        float sizeColumns8[]= {1,2,2,2,2};
        table1=  new Table(sizeColumns8);


        table1 =    HelperPdf.createTable3(table1,rgbColor,Variables.CurrenReportPart2) ;
        table1.setWidth(pageSize.getWidth()-120f);
        table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table1.setMarginLeft(60f);
        table1.setMarginRight(20f);
        table1.setMarginTop(0f);
        midocumentotoAddData.add(table1);



        //CREMOASPRIMERATABLA tipo plastricoy tipo de caja     ,

        /**DATOS DE PROCESO parte 1 */

        float sizeColumns9[]= {1,1,1,1,1,1,1,1,1};
        table1=  new Table(sizeColumns9);



        cell0= new Cell(1,9).add(new Paragraph("DATOS DE PROCESO").setTextAlignment(TextAlignment.CENTER).setFontSize(8f)) ;

        cell0.setBackgroundColor(rgbColor); //editamos el color
        table1.addCell(cell0);


        table1 =    HelperPdf.createTable2(table1,rgbColor,Variables.CurrenReportPart2) ;


        table1.setWidth(pageSize.getWidth()-120f);
        table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table1.setMarginLeft(60f);
        table1.setMarginRight(20f);
        table1.setMarginTop(5f);
        midocumentotoAddData.add(table1);



        /**DATOS DE PROCESO parte 3 */

        float sizeColumns10[]= {1,1,1,1,1,1,1};
        table1=  new Table(sizeColumns10);


        table1 =HelperPdf.createTbale6(table1,Variables.CurrenReportPart3,rgbColor) ;
        table1.setWidth(pageSize.getWidth()-120f);
        table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table1.setMarginLeft(60f);
        table1.setMarginRight(20f);
        table1.setMarginTop(0f);
        midocumentotoAddData.add(table1);

        /**CONTROL DE GANCHOI**/



        float araycolum[]= {1,2};
        table1=  new Table(araycolum);

        Cell cellHeader2= new Cell(1,2).setBackgroundColor(rgbColor);
        cellHeader2.add(new Paragraph(" CONTROL DE GANCHO ").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f));
        table1.addCell(cellHeader2);

        dataTOtable2=HelperPdf.generaDataToTable(Variables.CurrenReportPart1,Variables.CurrenReportPart2,Variables.CurrenReportPart3,8,Variables.currenProductPostCosecha);


        Log.i("debugtablesss","el size de dataTOtable2 es "+dataTOtable2.size());

        listCellsToTabCurrentTab2= HelperPdf.generateHasmapFieldnameandValue(dataTOtable2,50,0);

        Log.i("debugtablesss","el size de listCellsToTabCurrentTab2 es "+listCellsToTabCurrentTab2.size());


        addCellsInTable(listCellsToTabCurrentTab2,table1);


        table1.setWidth(pageSize.getWidth()-120f);
        table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table1.setMarginLeft(60f);
        table1.setMarginRight(20f);
        table1.setMarginTop(0f);
        midocumentotoAddData.add(table1);



        /**Calibracion de fruta calnedario de enfunde*/

        table1= HelperPdf.createTABLEcalendarioEnfude(table1,rgbColor,Variables.CurrenReportPart3);

        table1.setWidth(pageSize.getWidth()-120f);
        table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table1.setMarginLeft(60f);
        table1.setMarginRight(20f);
        table1.setMarginTop(0f);
        midocumentotoAddData.add(table1);


        midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));


        /**descripcion de defectos de fruta*/

        //agregamos el hedaer
        /**add header imagen*/

        imglogqSercom=pdfHelper.createInfoImgtoPDF(getDrawable(R.drawable.headerpdf),1);
        // imglogqSercom.setHorizontalAlignment(HorizontalAlignment.CENTER);
        midocumentotoAddData.add(imglogqSercom).setTopMargin(0f);

        float araycolumzz[]= {1,1,1,1,1,1};
        table1=  new Table(araycolumzz);

        table1=HelperPdf.descripciondEFECXTOSFRUTA(table1);

        table1.setWidth(pageSize.getWidth()-120f);
        table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table1.setMarginLeft(60f);
        table1.setMarginRight(20f);
        table1.setMarginTop(8f);
        midocumentotoAddData.add(table1);

        float ancho=pageSize.getWidth();   //MAS O MENOS EL DE LA ULTIMA TABLA
        float alto=pageSize.getHeight();


        Log.i("miodatr","el ancho del doc es "+ancho);
        Log.i("miodatr","el alto  del doc es "+alto);




        /**Agregamos Certificacion texto*/

        Paragraph title= new Paragraph("CERTIFICACION").setFontSize(12f);

        midocumentotoAddData.add(title);
        midocumentotoAddData.add(new Paragraph("Estimados").setFontSize(9f).setMarginTop(5f));
        midocumentotoAddData.add(new Paragraph("Certifico la calidsd y porcentaje de calidad semana 35, marca del monte").setFontSize(9f).setMarginTop(5f));
        midocumentotoAddData.add(new Paragraph("Acontinuacion describimos lo siguiente:").setFontSize(9f).setMarginTop(5f));
        midocumentotoAddData.add(new Paragraph("Tabla1.-Descripcion de porcentaje de calidadad e productores").setFontSize(9f).setMarginTop(5f));

        float sizeColumnsx[]= {2,1,2,2};
        table1=  new Table(sizeColumnsx);
        //aqyi agrega la data y despues agrega esto...


        ///algoritimo crea


        midocumentotoAddData.close();
        midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));


         /////



        /**Agregamos anexos*/
        midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        //agregamaos el header
        imglogqSercom=pdfHelper.createInfoImgtoPDF(getDrawable(R.drawable.headerpdf),1);
        // imglogqSercom.setHorizontalAlignment(HorizontalAlignment.CENTER);
        midocumentotoAddData.add(imglogqSercom).setTopMargin(0f);


        /**FOTOS LLEGADA */
        HelperAdImgs.createPages_addImgs(Variables.FOTO_LLEGADA,"FRUTAS EN FINCA",midocumentotoAddData,pageSize,PdfMaker2_0.this);




        /**AGREGAMOS GRAFICOS ESTADISTICOS...*/
        midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        //agregamaos el header
        imglogqSercom=pdfHelper.createInfoImgtoPDF(getDrawable(R.drawable.headerpdf),1);
        // imglogqSercom.setHorizontalAlignment(HorizontalAlignment.CENTER);
        midocumentotoAddData.add(imglogqSercom).setTopMargin(0f);

        dowloaDinformControlCalidAndGeneratePICsATATICITIS(Variables.CurrenReportPart1.getUniqueIDinforme());



        midocumentotoAddData.close();


    }


    private void addCellsInTable(HashMap<String,Cell >hasmpa, Table table){
        for(int indice=0; indice<hasmpa.size(); indice++) {

            if(hasmpa.containsKey(indice+"name")){
                Log.i("mismundo","si hay  name");

                table.addCell(hasmpa.get(indice+"name"));
            }else{

                Log.i("mismundo","no contiene name");
            }

            if(hasmpa.containsKey(indice+"value")){
                table.addCell(hasmpa.get(indice+"value"));


            }
            else{
                Log.i("mismundo","no contiene value");


            }




        }


    }



    ///tratando de dibujar en can vas


    /*
    private void addmarkAgua(  ImageData image) {

        PdfCanvas canvas = new PdfCanvas(pdfDoc.addNewPage());
        canvas.saveState();
        PdfExtGState state = new PdfExtGState();
        state.setFillOpacity(0.6f);
        canvas.setExtGState(state);
        canvas.addImage(image, 0, 0, pageSize.getWidth(), false);
        canvas.restoreState();

    }
*/


    void dowloaDinformControlCalidAndGeneratePICsATATICITIS(String idReporActivityContenedores){
        /*   DatabaseReference usersdRef = rootRef.child("Informes").child("listControCalidad");

        Query query = usersdRef.orderByChild("uniqueId").equalTo(uniqueId);*/
      //  Log.i("sliexsa","el date selecionado es l es  "+dateSelecionado);
      //  Log.i("sliexsa","el size de lista here call es  "+allReportFiltB.size());

        RealtimeDB.initDatabasesRootOnly();

        Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("listControCalidad").orderByChild("idDelInformePeretenece").equalTo(idReporActivityContenedores);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ControlCalidad controlcalidad=null;

                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                     controlcalidad=ds.getValue(ControlCalidad.class);


                }

                if(controlcalidad!=null){
                    createChar(controlcalidad);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }



    private void createChar(ControlCalidad controlCalidad){


        BarChart barChart=findViewById(R.id.chart);
        barChart.getXAxis().setDrawGridLines(false);  //ocultamos algunas lineas

        final String [] quarters=  getResources().getStringArray(R.array.array_defectos_frutax);
        ///
        // barChart.setBackground(getDrawable(R.drawable.bacgroundsercom));

        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return quarters[(int) value];
            }
        };


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


        } , PdfMaker2_0.this);




        //  barChart.setDescription("hola");

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(formatter);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(23);
        // Bitmap b = getChartBitmap();


        xAxis.setTextSize(7/*textSize*/);


    }



    ////aqui recibimos un intent con el id int del tipo de formulario a crear....
    //una array list con los items contor calidad..
    //el formulario object o la data con el que craemos el reporte unclyendo las imagenes...
    //para ahorara memoria destruimos el activity anterior....
    ///

}