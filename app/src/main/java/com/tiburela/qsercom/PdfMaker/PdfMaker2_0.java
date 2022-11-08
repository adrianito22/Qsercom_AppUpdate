package com.tiburela.qsercom.PdfMaker;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.compose.ui.text.Paragraph;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
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
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.kernel.layout.element.Table; //???? der any probllem?

//write here which class not found and wirte whee you want to use i timport com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
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
    int ActivityFormularioDondeVino;
    ArrayList< HashMap <String, String>>ListWhitHashMapsControlCalidad=new ArrayList<>() ;
    ArrayList< HashMap <String, String>> ListWhitHashMapsRechzadosChekeed=new ArrayList<>();
    HashMap <String, String> hasmapMapControlCalid;

    ArrayList<String>listtoTableDescripcion=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_maker20);

        ActivityFormularioDondeVino = getIntent().getIntExtra(Variables.KEY_PDF_MAKER,0);


        Log.i("debbdf","activity donde vino es "+ActivityFormularioDondeVino);

        if(ActivityFormularioDondeVino== Variables.FormPreviewContenedores){
            Log.i("debbdf","es el primer if");

            Log.i("debbdf","el size de listReprsVinculads es: "+Variables.listReprsVinculads.size());

            //obtenemos los hasmaps

                 //TENEMOS UNA LISTA CON LSO REPORTES

                for(int indice=0; indice< Variables.listReprsVinculads.size(); indice++){
                  //decsrgamos hasmap control calidad

                        String currentlOCATIONwHEREisHAMAP = Variables.listReprsVinculads.get(indice).getKeyWhereLocateasHmapFieldsRecha();
                    dowloadRecszCcalidadMapAndCallDowloadRechdz(currentlOCATIONwHEREisHAMAP, indice+1);


                }





                Log.i("debbdf","excelente create");
        }



        else if(ActivityFormularioDondeVino  == Variables.FormPreviewContenedores){  //completar estos

            Log.i("debbdf","es el segundo if");

        }else if (ActivityFormularioDondeVino  == Variables.FormPreviewContenedores){
            Log.i("debbdf","es el tercer if");


        }






    }







    public void createPDFContenedores() throws FileNotFoundException {
        String pdfDirecory=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        //doble chekeo si la current canvas object no fue terminada la finalizamos
        // pdfDocument.finishPage(currentPagePdfObjec) ;
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(directory, UUID.randomUUID().toString() + ".pdf");
        //chekear si existe si es asi sobrescribirlo

        HelperPdf.initFontx();




        OutputStream estrema =new FileOutputStream(file);
        PdfWriter writer = new PdfWriter(file); //le pasmaos el file

        PdfDocument miPFDocumentkernel= new PdfDocument(writer);
        PageSize pageSize= PageSize.A4;  //si no le quitamos el rotate...
        PdfPage pagePdf= miPFDocumentkernel.addNewPage(pageSize);///


        HelperPdf pdfHelper= new HelperPdf();

        Document midocumentotoAddData= new Document(miPFDocumentkernel,pageSize); // le gagregamos data a este...
        midocumentotoAddData.setMargins(0, 0, 0, 0);

        Image imglogqSercom=pdfHelper.createInfoImgtoPDF(getDrawable(R.drawable.headerpdf),1);
        imglogqSercom.scaleToFit(595f, 200f); //ESTA EN 400 DESPUES 3300
        imglogqSercom.setHorizontalAlignment(HorizontalAlignment.CENTER);
          midocumentotoAddData.add(imglogqSercom).setTopMargin(0f);


          /**CONFIGURAMOS OTRA VEZ EL MARGEN*/
        midocumentotoAddData.setMargins(200, 0, 0, 0);


        Image imageHeader=pdfHelper.createInfoImgtoPDF(getDrawable(R.drawable.headerpdf),1);
         imageHeader.setFixedPosition(0, 650); // si no usamos este
         imageHeader.setMarginTop(0f); // de prueba
        ImageEventHandlerHeader handler = new ImageEventHandlerHeader(imageHeader,midocumentotoAddData);

        miPFDocumentkernel.addEventHandler(PdfDocumentEvent.END_PAGE, handler);



        Rectangle remaining = midocumentotoAddData.getRenderer().getCurrentArea().getBBox();

        float y = remaining.getTop();
        float sizeTable= pageSize.getWidth()-120f;

        Log.i("miodatr","el size de table es"+sizeTable);


        Log.i("homero","eldocuemnto.getRenderer().getCurrentArea().getBBox() en logo es ES "+y);


        float position = midocumentotoAddData.getRenderer().getCurrentArea().getBBox().getTop();
        Log.i("miodatr","el mi logoqsercom "+position);


        Image imglogqSercomfooterBacground=pdfHelper.createInfoImgtoPDF(getDrawable(R.drawable.footer_pdf),1);
        imglogqSercomfooterBacground.setFixedPosition(0, 0); // si no usamos este
        BackgroundEventHandler handler2 = new BackgroundEventHandler(imglogqSercomfooterBacground);
        miPFDocumentkernel.addEventHandler(PdfDocumentEvent.END_PAGE, handler2);



        Table tableTitle=  new Table(1);

        /**TABLE TITULO EXPORTADORA SOLICTADA y procesada*/
        Cell cell1= new Cell()  .setBorder(Border.NO_BORDER).add(new Paragraph("REPORTE CALIDAD CONTENEDORES").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f));
        Cell cell2= new Cell().setBorder(Border.NO_BORDER) .add(new Paragraph("EXPORTADORA SOLICITADA: BANDECUA MARCA DEL MONTE").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f));
        Cell cell3= new Cell().setBorder(Border.NO_BORDER)  .add(new Paragraph("EXPORTADORA PROCESADA LAT BIO")).setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f);

        tableTitle.addCell(cell1);
        tableTitle.addCell(cell2);
        tableTitle.addCell(cell3);

        HelperPdf.configTableMaringAndWidth(tableTitle,sizeTable);
        midocumentotoAddData.add(tableTitle);


        Log.i("mitables","el size de colum 1  es "+sizeTable/2);
        Log.i("mitables","el size de colum 2  es "+sizeTable/1.5f);

        /**EMPEZAMOS CON LAS TABLAS*/
      //  float sizeColumns[]= {sizeTable/2,sizeTable/1.5f};
        float sizeColumns[]= {190,285};

        Table table1=  new Table(sizeColumns);
        // table1.

        //DeviceRgb rgbColor= new DeviceRgb(153, 255, 229); //color verde claro


        /**add primer cuadro..*/
        //crea list de celds...y add values...
        ArrayList<NameAndValue>dataTOtable1=HelperPdf.generaDataToTable(Variables.CurrenReportPart1,Variables.CurrenReportPart2,Variables.CurrenReportPart3,1,Variables.currenProductPostCosecha);
        HashMap<String,Cell> listCellsToTabCurrentTab= HelperPdf.generateHasmapFieldnameandValue(dataTOtable1,50,0);



        //editamos la tabla 1
        listCellsToTabCurrentTab.get("0name").setBackgroundColor(HelperPdf.rgbColorVerdeCana);
        listCellsToTabCurrentTab.get("0value").setBackgroundColor(HelperPdf.rgbColorVerdeCana);

        ///productos postcosecha

        addCellsInTable(listCellsToTabCurrentTab,table1);
        HelperPdf.configTableMaringAndWidth(table1,sizeTable);
        midocumentotoAddData.add(table1);




        /**productos postosecha*/

        float sizeColumns2[]= {1,1};
        table1=  new Table(sizeColumns2);

        Cell cell0= new Cell(1,2).add(new Paragraph("PRODUCTOS POSTOCOSECHA UTILIZADOS").setFont(HelperPdf.font).setFontSize(8f)
                .setTextAlignment(TextAlignment.CENTER)) ;

        cell0.setBackgroundColor(HelperPdf.rgbColorAzulClaro); //editamos el color
        table1.addCell(cell0);


        ArrayList<NameAndValue> dataTOtable2=HelperPdf.generaDataToTable(Variables.CurrenReportPart1,Variables.CurrenReportPart2,Variables.CurrenReportPart3,2,Variables.currenProductPostCosecha);


        HashMap<String, Cell> listCellsToTabCurrentTab2= HelperPdf.generateHasmapFieldnameandValue(dataTOtable2,100,0);
        ///productos postcosecha


        addCellsInTable(listCellsToTabCurrentTab2,table1);
        HelperPdf.configTableMaringAndWidth(table1,sizeTable);
        midocumentotoAddData.add(table1);

        // table1.setMarginTop(10f);

        /**DATOS  DE CONTENEDOR***/

        table1=  new Table(sizeColumns2);

        cell0= new Cell(1,2).add(new Paragraph("DATOS DE CONTENEDOR").setTextAlignment(TextAlignment.CENTER).setFont(HelperPdf.font).setFontSize(8f)) ;

        cell0.setBackgroundColor(HelperPdf.rgbColorAzulClaro); //editamos el color
        table1.addCell(cell0);



        dataTOtable2=HelperPdf.generaDataToTable(Variables.CurrenReportPart1,Variables.CurrenReportPart2,Variables.CurrenReportPart3,3,Variables.currenProductPostCosecha);
        listCellsToTabCurrentTab2= HelperPdf.generateHasmapFieldnameandValue(dataTOtable2,50,0);
        ///LA POSICION 5 LA EDITAMOS
        listCellsToTabCurrentTab2.get("2value").setBackgroundColor(HelperPdf.rgbColorAzulClaro); //editamos el color


        addCellsInTable(listCellsToTabCurrentTab2,table1);
        HelperPdf.configTableMaringAndWidth(table1,sizeTable);

        midocumentotoAddData.add(table1);

        /**SELLOS DE LLEGADA*/

        table1=  new Table(sizeColumns2);

        cell0= new Cell(1,2).add(new Paragraph("SELLLOS LLEGADA").setTextAlignment(TextAlignment.CENTER).setFontSize(8f).setFont(HelperPdf.font)) ;

        cell0.setBackgroundColor(HelperPdf.rgbColorAzulClaro); //editamos el color
        table1.addCell(cell0);



        dataTOtable2=HelperPdf.generaDataToTable(Variables.CurrenReportPart1,Variables.CurrenReportPart2,Variables.CurrenReportPart3,4,Variables.currenProductPostCosecha);
        listCellsToTabCurrentTab2= HelperPdf.generateHasmapFieldnameandValue(dataTOtable2,50,0);
        ///LA POSICION 5 LA EDITAMOS
        listCellsToTabCurrentTab2.get("2value").setBackgroundColor(HelperPdf.rgbColorAzulClaro); //editamos el color


        addCellsInTable(listCellsToTabCurrentTab2,table1);
        HelperPdf.configTableMaringAndWidth(table1,sizeTable);
        midocumentotoAddData.add(table1);

        /**SELLLOS INSTALADOS*/


        float sizeColumns5[]= {1,2,1,1};
        table1=  new Table(sizeColumns5);

        cell0= new Cell(1,4).add(new Paragraph("SELLLOS INSTALADOS").setTextAlignment(TextAlignment.CENTER).setFontSize(8f).setFont(HelperPdf.font)) ;

        cell0.setBackgroundColor(HelperPdf.rgbColorAzulClaro); //editamos el color
        table1.addCell(cell0);


        dataTOtable2=HelperPdf.generaDataToTable(Variables.CurrenReportPart1,Variables.CurrenReportPart2,Variables.CurrenReportPart3,5,Variables.currenProductPostCosecha);
        listCellsToTabCurrentTab2= HelperPdf.generateHasmapFieldnameandValue(dataTOtable2,50,600);

        //editamos otras columnas 10
        listCellsToTabCurrentTab2.get("10value"); //editamos el color



        addCellsInTable(listCellsToTabCurrentTab2,table1);
        HelperPdf.configTableMaringAndWidth(table1,sizeTable);
        midocumentotoAddData.add(table1);

        /**TERMINA PRIMERA HOJA DEBERIA CREAR OTROA HOJA*/

        midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

        //agregamos el header



        /**DATOS DE TRANSPORTISTA */

        /**add header imagen*/


        ///AGREGAMOS LA IMAGEN HEADER AQUI


        float sizeColumns6[]= {4,5};
        table1=  new Table(sizeColumns6);

        cell0= new Cell(1,2).add(new Paragraph("DATOS DE TRANSPORTISTA").setTextAlignment(TextAlignment.CENTER).setFontSize(8f).setFont(HelperPdf.font)) ;

        cell0.setBackgroundColor(HelperPdf.rgbColorAzulClaro); //editamos el color
        table1.addCell(cell0);


        dataTOtable2=HelperPdf.generaDataToTable(Variables.CurrenReportPart1,Variables.CurrenReportPart2,Variables.CurrenReportPart3,6,Variables.currenProductPostCosecha);
        listCellsToTabCurrentTab2= HelperPdf.generateHasmapFieldnameandValue(dataTOtable2,50,0);

        //editamos otras columnas 10
        // listCellsToTabCurrentTab2.get("10value"); //editamos el color


        addCellsInTable(listCellsToTabCurrentTab2,table1);
        HelperPdf.configTableMaringAndWidth(table1,sizeTable);
        table1.setMarginTop(5f);
        midocumentotoAddData.add(table1);



        /**DATOS DE PROCESO parte 1 */

        float sizeColumns7[]= {1,2,2,2,2,2,2,2,2};
        table1=  new Table(sizeColumns7);



        cell0= new Cell(1,9).add(new Paragraph("DATOS DE PROCESO").setTextAlignment(TextAlignment.CENTER).setFontSize(8f).setFont(HelperPdf.font)) ;

        cell0.setBackgroundColor(HelperPdf.rgbColorAzulClaro); //editamos el color
        table1.addCell(cell0);


        table1 =    HelperPdf.createTable2(table1,HelperPdf.rgbColorAzulClaro,Variables.CurrenReportPart2) ;
        HelperPdf.configTableMaringAndWidth(table1,sizeTable);
        //table1.setMarginTop(5f);
        midocumentotoAddData.add(table1);



        /**DATOS DE PROCESO parte 2 */

        float sizeColumns8[]= {1,2,2,2,2};
        table1=  new Table(sizeColumns8);


        table1 =    HelperPdf.createTable3(table1,Variables.CurrenReportPart2) ;

        HelperPdf.configTableMaringAndWidth(table1,sizeTable);
        midocumentotoAddData.add(table1);





        /**DATOS DE PROCESO parte 3 */

        float sizeColumns10[]= {1,1,1,1,1,1,1};
        table1=  new Table(sizeColumns10);


        table1 =HelperPdf.createTbale6(table1,Variables.CurrenReportPart3) ;
        HelperPdf.configTableMaringAndWidth(table1,sizeTable);
        midocumentotoAddData.add(table1);

        /**CONTROL DE GANCHOI**/



        float araycolum[]= {1,2};
        table1=  new Table(araycolum);

        Cell cellHeader2= new Cell(1,2).setBackgroundColor(HelperPdf.rgbColorAzulClaro);
        cellHeader2.add(new Paragraph(" CONTROL DE GANCHO ").setTextAlignment(TextAlignment.CENTER).setFontSize(8f).setFont(HelperPdf.font));
        table1.addCell(cellHeader2);

        dataTOtable2=HelperPdf.generaDataToTable(Variables.CurrenReportPart1,Variables.CurrenReportPart2,Variables.CurrenReportPart3,8,Variables.currenProductPostCosecha);


        Log.i("debugtablesss","el size de dataTOtable2 es "+dataTOtable2.size());

        listCellsToTabCurrentTab2= HelperPdf.generateHasmapFieldnameandValue(dataTOtable2,50,0);

        Log.i("debugtablesss","el size de listCellsToTabCurrentTab2 es "+listCellsToTabCurrentTab2.size());


        addCellsInTable(listCellsToTabCurrentTab2,table1);
        HelperPdf.configTableMaringAndWidth(table1,sizeTable);
        midocumentotoAddData.add(table1);



        /**Calibracion de fruta calnedario de enfunde*/

        table1= HelperPdf.createTABLEcalendarioEnfude(table1,Variables.CurrenReportPart3);
        HelperPdf.configTableMaringAndWidth(table1,sizeTable);
        midocumentotoAddData.add(table1);

        midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));



          /**agregamos tables de control calidad*/

            midocumentotoAddData.add(new Paragraph("1.- EVALUACIÓN Y CONDICIÓN DE FRUTA.").
                    setFontSize(7f).
                    setMarginTop(1f).
                    setBold().
                    setPaddingBottom(1f).setMarginLeft(60f));

           int contadorTablas=1;

         for(int i=0; i<ListWhitHashMapsControlCalidad.size(); i++ ){
             
             HashMap<String,String>currentMap=ListWhitHashMapsControlCalidad.get(i);
             HashMap<String,String>currentMapDefectsCheked=ListWhitHashMapsRechzadosChekeed.get(i);
             ControlCalidad currenControCaldRep= Variables.listReprsVinculads.get(i);

             table1=  HelperPdf.createTableEvaluacionYcondcionFruta(currenControCaldRep,currentMap,currentMapDefectsCheked,PdfMaker2_0.this,contadorTablas);


              if( contadorTablas % 2==0){  //y si existen mas tablas y es un numero
                  table1.setMarginTop(40f);

                  if(contadorTablas<ListWhitHashMapsControlCalidad.size()){ //signifca que quedan mas tablas

                      midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

                  }

              }else{ //si es la primera tabla de la pagina

                  table1.setMarginTop(10f);


              }


             table1.setWidth(pageSize.getWidth()/2);
             table1.setMarginLeft(60f);
             midocumentotoAddData.add(table1);

              contadorTablas++;


         }




        /**Agregamos Certificacion texto y tabla*/
        midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

        Paragraph title= new Paragraph("CERTIFICACION").setFontSize(12f).setTextAlignment(TextAlignment.CENTER).setMarginTop(10f).setBold();

        midocumentotoAddData.add(title);
        midocumentotoAddData.add(new Paragraph("Estimados.").setFontSize(9f).setMarginTop(5f).setPaddingLeft(60f));


        /**TEXTO SEGUNDA LINEA*/
        table1=  HelperPdf.generateTexCertificationTable("SEMANA n AQUI");
       // table1.setMarginLeft(60f);
        table1.setWidth(pageSize.getWidth()-147f);  //ESTABA EM 120
        table1.setMarginLeft(60f);

        midocumentotoAddData.add(table1);

        midocumentotoAddData.add(new Paragraph("Acontinuacion describimos lo siguiente:").setFontSize(9f).setMarginTop(5f).setPaddingLeft(60f));
        midocumentotoAddData.add(new Paragraph("Tabla1.- Descripcion de porcentaje de calidadad e productores").setFontSize(9f).setMarginTop(5f).setPaddingLeft(60f).setBold());


           /***TABLA PORCENTAJE DE CALIDAD DE PRODUCTORES*/
        table1 =HelperPdf. createTablePorceCalidProductres();


        /**confi especial*/
        table1.setWidth(sizeTable-50);
        table1.setHorizontalAlignment(HorizontalAlignment.LEFT);
        table1.setMarginLeft(60f);

        table1.setMarginTop(1f);
        midocumentotoAddData.add(table1);



        midocumentotoAddData.add(new Paragraph("Grafico 1.- Demostracion calidad total y danos - estropeos en fruta.").setFontSize(7.5f).setMarginTop(10f).setPaddingLeft(60f));

         /**Agregamos pie  Grafico*/
         PieChart pieChart;
         pieChart=findViewById(R.id.pieChart_view);

        DeviceRgb rgbColor= new DeviceRgb(220,220,220);

         table1=new Table(1);
         cell1= new Cell().setBackgroundColor(rgbColor).setBorder(Border.NO_BORDER);
         cell1.add(new Paragraph("CALIDAD FRUTA").setFontSize(16f).setBold().setTextAlignment(TextAlignment.CENTER).setPaddingTop(10f));
        table1.addCell(cell1);

        Bitmap bitmap=  HelperPdf.createPieCharImgbITMAP(pieChart,PdfMaker2_0.this);
        Image imagen= HelperPdf.createImagebYbitmap(bitmap);
        imagen.setWidth(150);
        imagen.setHeight(150);
        imagen.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //imagen.scaleToFit(1000,100); //estaba en 100


        cell1= new Cell().setBorder(Border.NO_BORDER).setBackgroundColor(rgbColor).setHorizontalAlignment(HorizontalAlignment.CENTER);
        cell1.add(imagen);
        table1.addCell(cell1);

        table1.setWidth(pageSize.getWidth()-200f);
        // table1.setMarginLeft(70f);
        table1.setMarginTop(1f);
        table1.setHorizontalAlignment(HorizontalAlignment.CENTER);

        midocumentotoAddData.add(table1);


        /**Texto como verfiicadora tenemos...*/

        midocumentotoAddData.add(new Paragraph("Como verificadora tenemos la obligacion de corregir estos danos en  la fruta para garantizar la calidad den la exportacion del banano  buscando siempre el bienestar de nuestro cliente").
                setFontSize(7.5f).setMarginTop(10f).setPaddingLeft(60f).setPaddingRight(65f));

        midocumentotoAddData.add(new Paragraph("CLIENTE AQUI").
                setFontSize(8.5f).setMarginTop(1f).setPaddingLeft(60f).setBold());


        midocumentotoAddData.add(new Paragraph("Atentamente,").
                setFontSize(7.5f).setMarginTop(15f).setPaddingLeft(60f));

          /**NOMBRE DE LOS INSPECTORES*/
         table1=  HelperPdf.generaTableInspectores(Variables.CurrenReportPart3,pageSize.getWidth());
         midocumentotoAddData.add(table1);


         /**BAR CHART Sporcentaje de frutas*/
        midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));











        BarChart barChartView;
        barChartView=findViewById(R.id.barChartView);


                int contadorAllGraficos=2;

                int contadorImageChar=1;

        for(int indice=0; indice<ListWhitHashMapsControlCalidad.size(); indice++){  //2 tablas...  4 en total
            ControlCalidad currenControCaldRep= Variables.listReprsVinculads.get(indice);

              //agregamos el texto en cel centro
             Paragraph mipara= new Paragraph("GRAFICO"+contadorAllGraficos +".-DEMOSTRACION DE DEFECTOS EMPAQUE "+currenControCaldRep.getTipoEmpaque()+" "+currenControCaldRep.getMarcaCaja())
                     .setPaddingLeft(60f).setBold();
            mipara.setHorizontalAlignment(HorizontalAlignment.CENTER);

             if(contadorImageChar%2==0){  //es multiplo de 2
                 mipara.setMarginTop(25f);

                 if(contadorImageChar<ListWhitHashMapsControlCalidad.size()){
                     //cremoas nueva pagina siempre y cuando existan mas valores
                     midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
                 }


             }
             else{

                 mipara.setMarginTop(2f);

             }


            midocumentotoAddData.add(mipara);

            bitmap=  HelperPdf.createBarChart(barChartView,PdfMaker2_0.this,indice);
            imagen= HelperPdf.createImagebYbitmap(bitmap);// .setPaddingLeft(70f).setPaddingRight(70f);
            imagen.setWidth(pageSize.getWidth()-145);
            //imagen.setHorizontalAlignment(HorizontalAlignment.CENTER);

            table1= new Table(1);

            cell1 = new Cell().setBorder(new SolidBorder(rgbColor, 1)).setBorderBottom(Border.NO_BORDER).add(new Paragraph("PORCENTAJE POR DEFECTO EN SELECION Y EMPAQUE").setMarginTop(10f).setTextAlignment(TextAlignment.CENTER));;
            table1.addCell(cell1);


            cell1 = new Cell().setBorderTop(Border.NO_BORDER);
            cell1.setBorder(new SolidBorder(rgbColor, 1));



            cell1.add(imagen);
            table1.addCell(cell1);
            HelperPdf.configTableMaringAndWidth(table1,sizeTable);

            midocumentotoAddData.add(table1);
            //setPaddingLeft(70f)
            contadorAllGraficos++;
            contadorImageChar++;
        }







        /**descripcion de defectos de fruta*/

        //agregamos el hedaer
        /**add header imagen*/
        midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

        float araycolumzz[]= {1,1,1,1,1,1};
        table1=  new Table(araycolumzz);

        table1=HelperPdf.descripciondEFECXTOSFRUTA(table1);
        HelperPdf.configTableMaringAndWidth(table1,sizeTable);
        table1.setMarginTop(8f);
        midocumentotoAddData.add(table1);

        float ancho=pageSize.getWidth();   //MAS O MENOS EL DE LA ULTIMA TABLA
        float alto=pageSize.getHeight();


        Log.i("miodatr","el ancho del doc es "+ancho);
        Log.i("miodatr","el alto  del doc es "+alto);

     //   miPFDocumentkernel.addEventHandler(PdfDocumentEvent.END_PAGE, handler);





        /**Agregamos anexos*/
        midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));


        /**FOTOS LLEGADA */
        HelperAdImgs.createPages_addImgs(Variables.FOTO_LLEGADA,"FRUTAS EN FINCA",midocumentotoAddData,pageSize,PdfMaker2_0.this);


        /**AGREGAMOS GRAFICOS ESTADISTICOS...*/
        midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        //agregamaos el header

        //dowloaDinformControlCalidAndGeneratePICsATATICITIS(Variables.CurrenReportPart1.getUniqueIDinforme());


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


        BarChart barChart=findViewById(R.id.barChartView);
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

    private void dowloadRecszCcalidadMapAndCallDowloadRechdz(String nodeLocateHasmapControlC, int iterador){

        Log.i("comnadaer","el NODEKey es : "+nodeLocateHasmapControlC);

        ValueEventListener seenListener = RealtimeDB.rootDatabaseReference.child("Informes").child("ControCalidHasmap").
                child(nodeLocateHasmapControlC).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        hasmapMapControlCalid =new HashMap<>();


                        for (DataSnapshot dss : dataSnapshot.getChildren()) {
                            String key = dss.getKey();

                            String  fieldData =dss.getValue(String.class);

                            //   HashMap packinKey = dss.getValue( String.class);

                            //   Log.i("misadhd","el size del mapa es "+ packingListMap.size());
                            Log.i("hameha","el key es "+key +"y el; field data es "+fieldData );


                            if (fieldData!=null) {///

                                hasmapMapControlCalid.put(key,fieldData);

                            }
                        }


                        ListWhitHashMapsControlCalidad.add(hasmapMapControlCalid);


                        Log.i("comnadaer","el size de hasmap es "+hasmapMapControlCalid.size());


                          if(iterador==Variables.listReprsVinculads.size() ){

                              iterateCallDowldRechzadosDefects();

                              Log.i("comnadaer","llamamos a iterateCallDowldRechzadosDefects "+hasmapMapControlCalid.size());


                          }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.i("misadhd","el error es "+ databaseError.getMessage());



                    }
                });



    }




    //despues decrgamos los defectos...
    private void dowloadAllSelectDefectosPosiciones(String nodeLocateHasmapDefectSelecc, int contador){

        Log.i("comnadaer","el key es "+nodeLocateHasmapDefectSelecc);

        HashMap<String, String> hasmapMapdEFECTOSchekeed= new HashMap<>();


        ValueEventListener seenListener = RealtimeDB.rootDatabaseReference.child("Informes").child("DefectoSelecionadosHashmap").child(nodeLocateHasmapDefectSelecc).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot dss : dataSnapshot.getChildren()) {
                    String key = dss.getKey();

                    String  fieldData =dss.getValue(String.class);

                    Log.i("debsumas","el key es "+key);
                    Log.i("debsumas"," y el fiel data es "+fieldData);


                    if (fieldData!=null && ! fieldData.equals("EMPTY")) {///

                        hasmapMapdEFECTOSchekeed.put(key,fieldData);

                    }
                }


                //agregamos
                ListWhitHashMapsRechzadosChekeed.add(hasmapMapdEFECTOSchekeed);

               // Log.i("debsumas"," el size de  "+hasmapMapdEFECTOSchekeed.size());

                if(contador ==Variables.listReprsVinculads.size()){


                    Log.i("comnadaer","llamaor cel size de ListWhitHashMapsControlCalidad es: "+ListWhitHashMapsControlCalidad.size());
                    Log.i("comnadaer","llamaor el size de ListWhitHashMapsRechzadosChekeed es: "+ListWhitHashMapsRechzadosChekeed.size());

                    Log.i("comnadaer","llamaor crear pdf now"+hasmapMapControlCalid.size());

                    try {
                        createPDFContenedores() ;
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();

                    }


                    //HEMOS TERMINADO DE DESCRGAR TODOS...
                }


                //   setDataInViews(hasmapMapControlCalid,Variables.currenControlCalReport);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("misadhd","el error es "+ databaseError.getMessage());



            }
        });



    }

    private void iterateCallDowldRechzadosDefects() {

        for(int indice=0; indice< Variables.listReprsVinculads.size(); indice++){
            //decsrgamos hasmap control calidad

            String currentlOCATIONwHEREisHAMAP = Variables.listReprsVinculads.get(indice).getKeyDondeEstaraHasmapDefecSelec();
            dowloadAllSelectDefectosPosiciones(currentlOCATIONwHEREisHAMAP,indice+ 1);


        }

    }



}