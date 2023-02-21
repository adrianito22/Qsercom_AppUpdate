package com.tiburela.qsercom.PdfMaker;


import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.provider.Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.util.Util;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
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
import com.itextpdf.layout.property.UnitValue;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.activities.formulariosPrev.ActivityContenedoresPrev;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.models.CheckedAndAtatch;
import com.tiburela.qsercom.models.ControlCalidad;
import com.tiburela.qsercom.models.CuadroMuestreo;
import com.tiburela.qsercom.models.NameAndValue;
import com.tiburela.qsercom.models.PromedioLibriado;
import com.tiburela.qsercom.utils.HelperImage;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PdfMaker2_0 extends AppCompatActivity {
    int ActivityFormularioDondeVino;
    ArrayList< HashMap <String, String>>ListWhitHashMapsControlCalidad=new ArrayList<>() ;

    static  float posicionyTablasLibriado=0;

    ArrayList< HashMap <String, String>> ListWhitHashMapsRechzadosChekeed=new ArrayList<>();
    HashMap <String, String> hasmapMapControlCalid;


    Button btnIrAARCHIVOpdf;

    String nameOFPDFrEPORTfile;
    int numProductsPostcosecha=0;

    Uri uriThiSfile;

    CountDownTimer cTimer;

     ProgressBar progressBar2;
    TextView txtTareaAqui;

    Context contexto;

    int numRacimosRechzados = 0;
    final int TWO_PERMISION_REQUEST = 131;
    final int CODE_WRITE_EXTERNAL_STORAGE = 132;
    final int CODE_READ_EXTERNAL_STORAGE = 133;


    LinearLayout layoutDown;
    LinearLayout layoutGraficos;
    Button btnDescargar ;

    ArrayList<String>listtoTableDescripcion=new ArrayList<>();


     boolean hayFILE = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_maker20);

        ActivityFormularioDondeVino = getIntent().getIntExtra(Variables.KEY_PDF_MAKER,0);

        nameOFPDFrEPORTfile=getIntent().getStringExtra(Variables.KEY_PDF_MAKER_PDF_NAME);
        numProductsPostcosecha=getIntent().getIntExtra(Variables.KEY_PDF_MAKER_PDF_NUM_PR_POST,0);

        Log.i("sermasd","namepdf es "+nameOFPDFrEPORTfile);


        contexto=getApplicationContext();
         progressBar2=findViewById(R.id.progressBar2);
         txtTareaAqui=findViewById(R.id.txtTareaAqui);
         layoutDown=findViewById(R.id.layoutDown);
         btnDescargar=findViewById(R.id.btnDescargar) ;
        layoutGraficos=findViewById(R.id.layoutGraficos) ;
        btnIrAARCHIVOpdf=findViewById(R.id.btnIrAARCHIVOpdf);
        btnIrAARCHIVOpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uriFile=uriThiSfile.toString();


                Log.i("searchFilex","el uri es : "+uriFile);


                try {
                    InputStream inputStream = PdfMaker2_0.this.getContentResolver().openInputStream(uriThiSfile);
                    inputStream.close();
                    hayFILE=true;
                    ///ecnontroFiLE[0] = true;
                } catch (Exception e) {
                    hayFILE=false;

                    // Log.w(MY_TAG, "File corresponding to the uri does not exist " + uri.toString());
                }


if(hayFILE){


    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setDataAndType(uriThiSfile, "application/pdf");
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    try {/*from   ww w  .  java  2s . c  om*/
        PdfMaker2_0.this.startActivity(intent);

        Log.i("searchFilex","vaomos activity");

        //  return true;
    } catch (ActivityNotFoundException e) {


        Log.i("searchFilex","error ehjejhr");

    }

  //  viewpdf(uriThiSfile);


  //  verifyStoragePermission();


   // openPdf(uriThiSfile);




    Log.i("searchFilex","se se encontro file");

}else {

    Toast.makeText(PdfMaker2_0.this, "No existe el archivo", Toast.LENGTH_SHORT).show();
    Log.i("searchFilex","NO se encontro file");



}




            }
        });

      //  btnDescargar.setEnabled(false);


        btnDescargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /// Toast.makeText(PdfMaker2_0.this, "Iniciando Descarga", Toast.LENGTH_SHORT).show();

                //

                try {
                    HelperPdf.TableCalidProdc=new ArrayList<>();//le agregamos aqui



                    if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE)
                                    == PackageManager.PERMISSION_GRANTED

                    ){ //si tiene permisos
                        Log.i("permisodd","tiene ya el permiso READ_EXTERNAL_STORAGE  && WRITE_EXTERNAL_STORAGE ");


                        createPDFContenedores() ;


                    }else{
                        Log.i("permisodd","no tiene ambos permisos ");



                        requestPermision(PdfMaker2_0.this);


                       /*
                        ActivityCompat.requestPermissions(PdfMaker2_0.this, new String[]{WRITE_EXTERNAL_STORAGE},
                                2);
*/
                    }






                }

                catch (FileNotFoundException e) {

                    e.printStackTrace();

                } catch (Exception e) {
                    e.printStackTrace();
                }

               // generateUniqueIDtOForm();

            }
        });


        Log.i("debbdf","activity donde vino es "+ActivityFormularioDondeVino);

        if(ActivityFormularioDondeVino== Variables.FormPreviewContenedores){
            Log.i("debbdf","es el primer if");

            Log.i("debbdf","el size de listReprsVinculads es: "+Variables.listReprsVinculads.size());

            //obtenemos los hasmaps

                 //TENEMOS UNA LISTA CON LSO REPORTES


            UpdateProgressAndText("Descargando Data",10);


            Log.i("hameha","empezamos a crear data" );


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





  private  void UpdateProgressAndText(String texto,int progressPercent) {

      progressBar2.setProgress(progressPercent);
      txtTareaAqui.setText(texto);

  }

    public void createPDFContenedores() throws Exception {


        //prueba now

       int sizedd= Variables.listPromedioLibriado.size();

        Log.i("superman","el size de libriado es  "+sizedd);


       HelperPdf. hasmapOfListwhitNamesFefectsCurrentControlCalidadFOmr= new HashMap<>();//reseteamos este
       HelperPdf.listNumsCustomDefects= new ArrayList<>();
        HelperPdf.TableCalidProdc=new ArrayList<>();


        String pdfDirecory=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        //doble chekeo si la current canvas object no fue terminada la finalizamos
        // pdfDocument.finishPage(currentPagePdfObjec) ;
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(directory, nameOFPDFrEPORTfile+".pdf");

        uriThiSfile=Uri.fromFile(file);

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
        midocumentotoAddData.setMargins(190, 0, 105, 0);


        Image imageHeader=pdfHelper.createInfoImgtoPDF(getDrawable(R.drawable.headerpdf),1);
         imageHeader.setFixedPosition(0, 650); // si no usamos este
         imageHeader.setMarginTop(0f); // de prueba
        ImageEventHandlerHeader handler = new ImageEventHandlerHeader(imageHeader,midocumentotoAddData);

        miPFDocumentkernel.addEventHandler(PdfDocumentEvent.END_PAGE, handler);



        Rectangle remaining = midocumentotoAddData.getRenderer().getCurrentArea().getBBox();

        float y = remaining.getTop();
        float sizeTable= pageSize.getWidth()-120f;

        Log.i("miodatr","el size de table es"+sizeTable);

        Log.i("debian","sellamoaqui tambien ");

        Log.i("homero","eldocuemnto.getRenderer().getCurrentArea().getBBox() en logo es ES "+y);


        float position = midocumentotoAddData.getRenderer().getCurrentArea().getBBox().getTop();
        Log.i("miodatr","el mi logoqsercom "+position);


        Image imglogqSercomfooterBacground=pdfHelper.createInfoImgtoPDF(getDrawable(R.drawable.footer_pdf),1);
        imglogqSercomfooterBacground.setFixedPosition(0, 0); // si no usamos este
        BackgroundEventHandler handler2 = new BackgroundEventHandler(imglogqSercomfooterBacground);
        miPFDocumentkernel.addEventHandler(PdfDocumentEvent.END_PAGE, handler2);



        Table tableTitle=  new Table(1);

        /**TABLE TITULO EXPORTADORA SOLICTADA yPosicion procesada*/
        Cell cell1= new Cell()  .setBorder(Border.NO_BORDER).add(new Paragraph("REPORTE CALIDAD CONTENEDORES").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        Cell cell2= new Cell().setBorder(Border.NO_BORDER) .add(new Paragraph("EXPORTADORA SOLICITANTE "+Variables.CurrenReportPart1.getExportadoraSolicitante().toUpperCase()+" MARCA "+" "+Variables.CurrenReportPart1.getMarrca().toUpperCase())
                .setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        Cell cell3= new Cell().setBorder(Border.NO_BORDER)
                .add(new Paragraph("EXPORTADORA PROCESADA "+Variables.CurrenReportPart1.getExportadoraProcesada()+" "+Variables.CurrenReportPart1.getUniqueIDinforme().toUpperCase()).
                        setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());

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
        //crea list de celds...yPosicion add values...
        ArrayList<NameAndValue>dataTOtable1=HelperPdf.generaDataToTable(Variables.CurrenReportPart1,Variables.CurrenReportPart2,Variables.CurrenReportPart3,1,Variables.currenProductPostCosecha);
        HashMap<String,Cell> mapCellsToTabCurrentTab= HelperPdf.generateHasmapFieldnameandValue(dataTOtable1,50,0);



        //editamos la tabla 1
        mapCellsToTabCurrentTab.get("0name").setBackgroundColor(HelperPdf.rgbColorVerdeCana);
        mapCellsToTabCurrentTab.get("0value").setBackgroundColor(HelperPdf.rgbColorVerdeCana);

        mapCellsToTabCurrentTab.get("0name").setBold();
        mapCellsToTabCurrentTab.get("0value").setBold();

        //
        mapCellsToTabCurrentTab.get("1name").setBold();
        mapCellsToTabCurrentTab.get("2name").setBold();
        mapCellsToTabCurrentTab.get("3name").setBold();
        mapCellsToTabCurrentTab.get("4name").setBold();
        mapCellsToTabCurrentTab.get("5name").setBold();
        mapCellsToTabCurrentTab.get("6name").setBold();
        mapCellsToTabCurrentTab.get("7name").setBold();
        mapCellsToTabCurrentTab.get("8name").setBold();
        mapCellsToTabCurrentTab.get("9name").setBold();
        mapCellsToTabCurrentTab.get("10name").setBold();
        mapCellsToTabCurrentTab.get("11name").setBold();
        mapCellsToTabCurrentTab.get("12name").setBold();




        ///productos postcosecha

        addCellsInTable(mapCellsToTabCurrentTab,table1);
        HelperPdf.configTableMaringAndWidth(table1,sizeTable);
        midocumentotoAddData.add(table1);




        /**productos postosecha*/

        float sizeColumns2[]= {190,1};
        table1=  new Table(sizeColumns2);

        Cell cell0= new Cell(1,2).add(new Paragraph("PRODUCTOS POSTOCOSECHA UTILIZADOS").setFont(HelperPdf.font).setFontSize(8f).setBold()
                .setTextAlignment(TextAlignment.CENTER)) ;

        cell0.setBackgroundColor(HelperPdf.rgbColorAzulClaro); //editamos el color
        table1.addCell(cell0);


        /**devulve productos postcosecha table inf0 = 2 */


        if(numProductsPostcosecha<=4){
            ArrayList<NameAndValue> dataTOtable2=HelperPdf.generaDataToTable(Variables.CurrenReportPart1,Variables.CurrenReportPart2,Variables.CurrenReportPart3,2,Variables.currenProductPostCosecha);
            mapCellsToTabCurrentTab= HelperPdf.generateHasmapFieldnameandValue(dataTOtable2,100,0);
            ///productos postcosecha
            ///

            for(int indice=0; indice<mapCellsToTabCurrentTab.size()/2; indice++){
              try {

                  mapCellsToTabCurrentTab.get(indice+"name").setBold();
                  mapCellsToTabCurrentTab.get(indice+"value").setBold();
              } catch (Exception e) {
                  throw new RuntimeException(e);
              }


            }


            addCellsInTable(mapCellsToTabCurrentTab,table1);

        }else{

            HelperPdf.configTableMaringAndWidth(table1,sizeTable);
            midocumentotoAddData.add(table1);

            table1=HelperPdf.generateTablePRODUCTSPOSTO(Variables.currenProductPostCosecha,contexto);

        }




        HelperPdf.configTableMaringAndWidth(table1,sizeTable);
        midocumentotoAddData.add(table1);

        // table1.setMarginTop(10f);

        /**DATOS  DE CONTENEDOR***/

        table1=  new Table(sizeColumns2);

        cell0= new Cell(1,2).add(new Paragraph("DATOS DE CONTENEDOR").setTextAlignment(TextAlignment.CENTER).setFont(HelperPdf.font).setFontSize(8f).setBold()) ;

        cell0.setBackgroundColor(HelperPdf.rgbColorAzulClaro); //editamos el color
        table1.addCell(cell0);

        ArrayList<NameAndValue>dataTOtable2;

        dataTOtable2=HelperPdf.generaDataToTable(Variables.CurrenReportPart1,Variables.CurrenReportPart2,Variables.CurrenReportPart3,3,Variables.currenProductPostCosecha);
        mapCellsToTabCurrentTab= HelperPdf.generateHasmapFieldnameandValue(dataTOtable2,50,0);
        ///LA POSICION 5 LA EDITAMOS

        mapCellsToTabCurrentTab.get("0name").setBold(); //editamos el color
        mapCellsToTabCurrentTab.get("1name").setBold(); //editamos el color
        mapCellsToTabCurrentTab.get("2name").setBold(); //editamos el color
        mapCellsToTabCurrentTab.get("3name").setBold(); //editamos el color
        mapCellsToTabCurrentTab.get("4name").setBold(); //editamos el color

        mapCellsToTabCurrentTab.get("2value").setBackgroundColor(HelperPdf.rgbColorNaranja); //editamos el color


        addCellsInTable(mapCellsToTabCurrentTab,table1);
        HelperPdf.configTableMaringAndWidth(table1,sizeTable);

        midocumentotoAddData.add(table1);

        /**SELLOS DE LLEGADA*/

        table1=  new Table(sizeColumns2);

        cell0= new Cell(1,2).add(new Paragraph("SELLOS LLEGADA").setTextAlignment(TextAlignment.CENTER).setFontSize(8f).setFont(HelperPdf.font).setBold()) ;

        cell0.setBackgroundColor(HelperPdf.rgbColorAzulClaro); //editamos el color
        table1.addCell(cell0);



        dataTOtable2=HelperPdf.generaDataToTable(Variables.CurrenReportPart1,Variables.CurrenReportPart2,Variables.CurrenReportPart3,4,Variables.currenProductPostCosecha);
        mapCellsToTabCurrentTab= HelperPdf.generateHasmapFieldnameandValue(dataTOtable2,50,0);
        ///LA POSICION 5 LA EDITAMOS
        mapCellsToTabCurrentTab.get("0name").setBold(); //editamos el color
        mapCellsToTabCurrentTab.get("1name").setBold(); //editamos el color
        mapCellsToTabCurrentTab.get("2name").setBold(); //editamos el color
        mapCellsToTabCurrentTab.get("3name").setBold(); //editamos el color
        mapCellsToTabCurrentTab.get("4name").setBold(); //editamos el color
        mapCellsToTabCurrentTab.get("5name").setBold(); //editamos el color
        mapCellsToTabCurrentTab.get("6name").setBold(); //editamos el color



        addCellsInTable(mapCellsToTabCurrentTab,table1);
        HelperPdf.configTableMaringAndWidth(table1,sizeTable);
        midocumentotoAddData.add(table1);

        /**SELLOS INSTALADOS*/


        float sizeColumns5[]= {190,2,1,1};
        table1=  new Table(sizeColumns5);

        cell0= new Cell(1,4).add(new Paragraph("SELLOS INSTALADOS").setTextAlignment(TextAlignment.CENTER).setFontSize(8f).setFont(HelperPdf.font).setBold()) ;

        cell0.setBackgroundColor(HelperPdf.rgbColorAzulClaro); //editamos el color
        table1.addCell(cell0);


        dataTOtable2=HelperPdf.generaDataToTable(Variables.CurrenReportPart1,Variables.CurrenReportPart2,Variables.CurrenReportPart3,5,Variables.currenProductPostCosecha);
        mapCellsToTabCurrentTab= HelperPdf.generateHasmapFieldnameandValue(dataTOtable2,50,600);

        //editamos otras columnas 10

        for(int indice=0; indice<mapCellsToTabCurrentTab.size()/2; indice++){


            try {
                mapCellsToTabCurrentTab.get(indice+"name").setBold(); //editamos el color


            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }








        addCellsInTable(mapCellsToTabCurrentTab,table1);
        HelperPdf.configTableMaringAndWidth(table1,sizeTable);
        midocumentotoAddData.add(table1);

        /**TERMINA PRIMERA HOJA DEBERIA CREAR OTROA HOJA*/

        midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

        //agregamos el header


        Log.i("debian","sellamoaqui tambien xxx ");



        /**DATOS DE TRANSPORTISTA */

        /**add header imagen*/


        ///AGREGAMOS LA IMAGEN HEADER AQUI


        float sizeColumns6[]= {190,5};
        table1=  new Table(sizeColumns6);

        cell0= new Cell(1,2).add(new Paragraph("DATOS DE TRANSPORTISTA").
                setTextAlignment(TextAlignment.CENTER).setFontSize(8f).
                setFont(HelperPdf.font).setBold()) ;

        cell0.setBackgroundColor(HelperPdf.rgbColorAzulClaro); //editamos el color
        table1.addCell(cell0);


        dataTOtable2=HelperPdf.generaDataToTable(Variables.CurrenReportPart1,Variables.CurrenReportPart2,Variables.CurrenReportPart3,6,Variables.currenProductPostCosecha);
        mapCellsToTabCurrentTab= HelperPdf.generateHasmapFieldnameandValue(dataTOtable2,50,0);

        //editamos otras columnas 10
        mapCellsToTabCurrentTab.get("0name").setBold(); //editamos el color
        mapCellsToTabCurrentTab.get("1name").setBold(); //editamos el color
        mapCellsToTabCurrentTab.get("2name").setBold(); //editamos el color
        mapCellsToTabCurrentTab.get("3name").setBold(); //editamos el color
        mapCellsToTabCurrentTab.get("4name").setBold(); //editamos el color
        mapCellsToTabCurrentTab.get("5name").setBold(); //editamos el color


        addCellsInTable(mapCellsToTabCurrentTab,table1);
        HelperPdf.configTableMaringAndWidth(table1,sizeTable);
        table1.setMarginTop(5f);
        midocumentotoAddData.add(table1);



        /**DATOS DE PROCESO parte 1 */

        float sizeColumns7[]= {190,2,2,2,2,2,2,2,2};
        table1=  new Table(sizeColumns7);



        cell0= new Cell(1,9).add(new Paragraph("DATOS DE PROCESO").setTextAlignment(TextAlignment.CENTER).setFontSize(8f).setFont(HelperPdf.font).setBold()) ;

        cell0.setBackgroundColor(HelperPdf.rgbColorAzulClaro); //editamos el color
        table1.addCell(cell0);


        table1 =    HelperPdf.createTable2(table1,HelperPdf.rgbColorAzulClaro,Variables.CurrenReportPart2) ;
        HelperPdf.configTableMaringAndWidth(table1,sizeTable);
        //table1.setMarginTop(5f);
        midocumentotoAddData.add(table1);



        /**DATOS DE PROCESO parte 2 */

        float sizeColumns8[]= {190,2,2,2,2};
        table1=  new Table(sizeColumns8);


        table1 =    HelperPdf.createTable3(table1,Variables.CurrenReportPart2) ;

        HelperPdf.configTableMaringAndWidth(table1,sizeTable);
        midocumentotoAddData.add(table1);



        /**DATOS DE PROCESO parte 3 */

        float sizeColumns10[]= {190,1,1,1,1,1,1};
        table1=  new Table(sizeColumns10);


        table1 =HelperPdf.createTbale6(table1,Variables.CurrenReportPart3) ;



        HelperPdf.configTableMaringAndWidth(table1,sizeTable);
        midocumentotoAddData.add(table1);

        /**CONTROL DE GANCHOI**/



        float araycolum[]= {190,2};
        table1=  new Table(araycolum);

        Cell cellHeader2= new Cell(1,2).setBackgroundColor(HelperPdf.rgbColorAzulClaro);
        cellHeader2.add(new Paragraph(" CONTROL DE GANCHO ").setTextAlignment(TextAlignment.CENTER).setFontSize(8f).setFont(HelperPdf.font).setBold());
        table1.addCell(cellHeader2);

        dataTOtable2=HelperPdf.generaDataToTable(Variables.CurrenReportPart1,Variables.CurrenReportPart2,Variables.CurrenReportPart3,8,Variables.currenProductPostCosecha);


        Log.i("debugtablesss","el size de dataTOtable2 es "+dataTOtable2.size());

        mapCellsToTabCurrentTab= HelperPdf.generateHasmapFieldnameandValue(dataTOtable2,50,0);
        mapCellsToTabCurrentTab.get("0name").setBold();
        mapCellsToTabCurrentTab.get("1name").setBold();
        mapCellsToTabCurrentTab.get("2name").setBold();
        mapCellsToTabCurrentTab.get("3name").setBold();



        Log.i("debugtablesss","el size de listCellsToTabCurrentTab2 es "+mapCellsToTabCurrentTab.size());


        addCellsInTable(mapCellsToTabCurrentTab,table1);
        HelperPdf.configTableMaringAndWidth(table1,sizeTable);
        midocumentotoAddData.add(table1);



        /**Calibracion de fruta calnedario de enfunde*/

        table1= HelperPdf.createTABLEcalendarioEnfude(table1,Variables.CurrenReportPart3);
        HelperPdf.configTableMaringAndWidth(table1,sizeTable);
        midocumentotoAddData.add(table1);

        midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));



          /**agregamos tables de control calidad*/

            midocumentotoAddData.add(new Paragraph("1.- EVALUACIÓN Y CONDICIÓN DE FRUTA.").
                    setFontSize(7.3f).
                    setMarginTop(1f).
                    setBold().
                    setPaddingBottom(1f).setMarginLeft(60f));

           int contadorTablas=1;

         for(int i=0; i<ListWhitHashMapsControlCalidad.size(); i++ ){
             
             HashMap<String,String>currentMap=ListWhitHashMapsControlCalidad.get(i);
             HashMap<String,String>currentMapDefectsCheked=ListWhitHashMapsRechzadosChekeed.get(i);
             ControlCalidad currenControCaldRep= Variables.listReprsVinculads.get(i);

             table1=  HelperPdf.createTableEvaluacionYcondcionFruta(currenControCaldRep,currentMap,currentMapDefectsCheked,PdfMaker2_0.this,contadorTablas);


              if( contadorTablas % 2==0){  //yPosicion si existen mas tablas yPosicion es un numero
                  table1.setMarginTop(40f);

                  if(contadorTablas<ListWhitHashMapsControlCalidad.size()){ //signifca que quedan mas tablas

                      midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

                  }
              }

              else{ //si es la primera tabla de la pagina

                  table1.setMarginTop(10f);


              }


             table1.setWidth(pageSize.getWidth()/2);
             table1.setMarginLeft(60f);
             midocumentotoAddData.add(table1);

              contadorTablas++;


         }




        /**Agregamos Certificacion texto yPosicion tabla*/
        midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

        Paragraph title= new Paragraph("CERTIFICACIÓN").setFontSize(12f).setTextAlignment(TextAlignment.CENTER).setMarginTop(10f).setBold();

        midocumentotoAddData.add(title);
        midocumentotoAddData.add(new Paragraph("Estimados.").setFontSize(9f).setMarginTop(5f).setPaddingLeft(60f));


        /**TEXTO SEGUNDA LINEA*/
        //table1=  HelperPdf.generateTexCertificationTable("MARCA AQUI");
        title=HelperPdf.generateTexCertificoLaCALIDAD(Variables.CurrenReportPart1.getMarrca());
        title.setMarginLeft(60f);

        midocumentotoAddData.add(title);


        midocumentotoAddData.add(new Paragraph("A continuación describimos lo siguiente:").setFontSize(9f).setMarginTop(5f).setPaddingLeft(60f));
        midocumentotoAddData.add(new Paragraph("Tabla1.-  Descripción de porcentaje de calidad de productores y tipos de empaque").setFontSize(9f).setMarginTop(5f).setPaddingLeft(60f).setBold());


           /***TABLA PORCENTAJE DE CALIDAD DE PRODUCTORES*/
        table1 =HelperPdf. createTablePorceCalidProductres();


        /**confi especial*/
        table1.setWidth(sizeTable-50);
        table1.setHorizontalAlignment(HorizontalAlignment.LEFT);
        table1.setMarginLeft(60f);

        table1.setMarginTop(1f);
        midocumentotoAddData.add(table1);



        midocumentotoAddData.add(new Paragraph("Gráfico 1.- Demostración de calidad total y daños- estropeos en fruta.").setFontSize(7.5f).setMarginTop(10f).setPaddingLeft(60f));

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


        imagen.setWidth(120);
        imagen.setHeight(120);
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

        midocumentotoAddData.add(new Paragraph("Como verificadora tenemos la obligación de corregir estos daños en  la fruta para garantizar la calidad en la exportación del banano  buscando siempre el bienestar de nuestro cliente.").
                setFontSize(7.5f).setMarginTop(9f).setPaddingLeft(60f).setPaddingRight(65f));
/*
        midocumentotoAddData.add(new Paragraph(Variables.CurrenReportPart1.getClienteReporte()).
                setFontSize(8.5f).setMarginTop(1f).setPaddingLeft(60f).setBold());
*/

        midocumentotoAddData.add(new Paragraph("Atentamente,").
                setFontSize(7.5f).setMarginTop(10f).setPaddingLeft(60f));

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
             Paragraph mipara= new Paragraph("GRÁFICO "+contadorAllGraficos +".-DEMOSTRACIÓN DE DEFECTOS EMPAQUE "+currenControCaldRep.getMarcaCaja())
                     .setPaddingLeft(60f).setBold();
            mipara.setHorizontalAlignment(HorizontalAlignment.CENTER);


          //  midocumentotoAddData.add(new Paragraph("textaqui").setFontSize(5f));


            remaining = midocumentotoAddData.getRenderer().getCurrentArea().getBBox();
            float y2 = remaining.getTop();


             ///este codigo marcael area restante despues de agregar un elemento al pdf
            //PdfCanvas canvas = new PdfCanvas(miPFDocumentkernel.getPage(5));
            //canvas.setStrokeColor(ColorConstants.RED).rectangle(remaining).stroke();


            Log.i("posicuon","el posicon  es "+y2);



            if(contadorImageChar%2==0){  //es multiplo de 2
                 mipara.setMarginTop(25f);

                 if(contadorImageChar<ListWhitHashMapsControlCalidad.size()){
                     //cremoas nueva pagina siempre yPosicion cuando existan mas valores
                     midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
                 }


             }
             else{

                 mipara.setMarginTop(2f);

             }


            midocumentotoAddData.add(mipara);

            bitmap=  HelperPdf.createBarChart(barChartView,PdfMaker2_0.this,indice);



            imagen= HelperPdf.createImagebYbitmap(bitmap);// .setPaddingLeft(70f).setPaddingRight(70f);
            imagen.setHeight(185f); //SI NO QUITAMOS 5

            //////////////////

            imagen.setWidth(pageSize.getWidth()-145);
            //imagen.setHorizontalAlignment(HorizontalAlignment.CENTER);

            table1= new Table(1);

            cell1 = new Cell().setBorder(new SolidBorder(rgbColor, 1)).setBorderBottom(Border.NO_BORDER).add(new Paragraph("PORCENTAJE POR DEFECTO EN SELECCIÓN Y EMPAQUE").setMarginTop(10f).setTextAlignment(TextAlignment.CENTER));;
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



        /**libriado**/


        if(Utils.hashMappromedioLibriado.size()>0){ //si hay libriado
             boolean isPrimeraTablaLibriado=true;
            midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

            midocumentotoAddData.add(new Paragraph("2.PESO PROMEDIO CLÚSTER,").
                    setFontSize(7.5f).setMarginTop(10f).setPaddingLeft(60f));

            Log.i("miodatr","se eejcuto el pdd");


            Rectangle remainingZZ ;

            String [] keyandanme;


            for (Map.Entry<String,ArrayList<PromedioLibriado>> entry : Utils.hashMappromedioLibriado.entrySet()) {
                String key = entry.getKey();
                ArrayList<PromedioLibriado> arrayList = entry.getValue();

                keyandanme = key.split("-");


                /**obtenemos tabla by array list item*/
                table1 = HelperPdf.devulveTablaToLibriado(arrayList, keyandanme[0]);
                HelperPdf.configTableMaringAndWidth(table1, sizeTable - 200);


                if (isPrimeraTablaLibriado) {

                    table1.setMarginTop(10f);
                    table1.setMarginBottom(10f);
                    midocumentotoAddData.add(table1);
                    isPrimeraTablaLibriado = false;

                } else { /**aqui havemos el calculo...*/

                    Log.i("simpredert", "esta table tiene de size" +arrayList.size());


                    /***tenemos un valor contsante que es el heihgt del title y el promedio abajo serian unos 70*/
                    float estimacionPosicionOcuparaTable=posicionyTablasLibriado-(arrayList.size()*22)-90;

                    Log.i("simpredert", "la posicion estmada seria " + estimacionPosicionOcuparaTable);



                    if(estimacionPosicionOcuparaTable<210){
                        midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

                    }



                     table1.setMarginTop(10f);
                    table1.setMarginBottom(10f);

                    midocumentotoAddData.add(table1);


                }

                remainingZZ = midocumentotoAddData.getRenderer().getCurrentArea().getBBox();
                posicionyTablasLibriado = remainingZZ.getTop();



                Log.i("simpredert", "la posicionyTablasLibriado table real DESPUES DE ADD es  " + posicionyTablasLibriado);


            }
            Log.i("miodataxx","es mayor a cero");


        }



        /**Agregamos anexos*/

       // UpdateProgressAndText("Agregando Fotos al Reporte",90);

           HelperAdImgs.initpdfDocument(miPFDocumentkernel);



        midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

        HelperAdImgs.createPages_addImgs(Variables.FOTO_PROCESO_FRUTA_FINCA,"PROCESO DE FRUTA EN FINCA",midocumentotoAddData,pageSize,contexto);






        /**FOTO_LLEGADA_CONTENEDOR...*/
        midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        HelperAdImgs.createPages_addImgs(Variables.FOTO_LLEGADA_CONTENEDOR,"*  APERTURA, INSPECCIÓN Y CIERRE DE  CONTENEDOR",midocumentotoAddData,pageSize,contexto);


        HelperAdImgs.createPages_addImgs(Variables.FOTO_SELLO_LLEGADA,"",midocumentotoAddData,pageSize,contexto);



        /**FOTO_PUERTA_ABIERTA_DEL_CONTENENEDOR...*/
        midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        HelperAdImgs.createPages_addImgs(Variables.FOTO_PUERTA_ABIERTA_DEL_CONTENENEDOR," ",midocumentotoAddData,pageSize,contexto);






        /**FOTO_PALLETS ...*/
       // midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        HelperAdImgs.createPages_addImgs(Variables.FOTO_PALLETS,"",midocumentotoAddData,pageSize,contexto);



     //   midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        HelperAdImgs.createPages_addImgs(Variables.FOTO_CIERRE_CONTENEDOR,"",midocumentotoAddData,pageSize,contexto);


        midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        HelperAdImgs.createPages_addImgs(Variables.FOTO_DOCUMENTACION,"*  DOCUMENTACIÓN",midocumentotoAddData,pageSize,contexto);






        /**DEBUG borrar*/

        int aray[]= {
                Variables.FOTO_PROCESO_FRUTA_FINCA,Variables.FOTO_LLEGADA_CONTENEDOR,
                Variables.FOTO_SELLO_LLEGADA,Variables.FOTO_PUERTA_ABIERTA_DEL_CONTENENEDOR,Variables.FOTO_PALLETS,
        Variables.FOTO_CIERRE_CONTENEDOR,Variables.FOTO_DOCUMENTACION}
                ;


        for(int indice=0; indice<aray.length; indice++){
              int categoiria=aray [indice];

              Log.i("categoriasxx","estos son los ids de la categoria : "+categoiria);

            for(int indice2 = 0; indice2<HelperImage.imAGESpdfSetGlobal.size(); indice2++){

                if(HelperImage.imAGESpdfSetGlobal.get(indice2).getTipoImagenCategory()==categoiria){

                  //  Log.i("categoriasxx","el id  de esta imagen es : "+HelperImage.imAGESpdfSetGlobal.get(indice2).uniQueIdimgPertenece);


                }


            }


        }


        Toast.makeText(PdfMaker2_0.this, "Se GUARDÓ  el Pdf", Toast.LENGTH_SHORT).show();


        midocumentotoAddData.close();
       // UpdateProgressAndText("Terminado",100);


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
                            Log.i("hameha","el key es "+key +"yPosicion el; field data es "+fieldData );


                            if (fieldData!=null) {///

                                hasmapMapControlCalid.put(key,fieldData);

                            }
                        }


                        ListWhitHashMapsControlCalidad.add(hasmapMapControlCalid);


                        Log.i("comnadaer","el size de hasmap es "+hasmapMapControlCalid.size());


                          if(iterador==Variables.listReprsVinculads.size() ){

                              UpdateProgressAndText("Creando pdf",30);

                              Log.i("hameha","recin terminamos de descrgar data" );


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
                    Log.i("debsumas"," yPosicion el fiel data es "+fieldData);


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

                    UpdateProgressAndText("Pdf esta listo",100);


                    layoutDown.setVisibility(LinearLayout.VISIBLE);
                    layoutGraficos.setVisibility(LinearLayout.GONE);

                    Log.i("enablebtn","hemos llmado enable ");


                    //


                    Log.i("searchFile","se llamomethod");



                 //   checkIfFileisSaveINsRORAGE(uriThiSfile);




                    /*
                    if(Variables.currentkEYcuadroMuetreo!=null){

                       // DowloadUniqeuRechazadosoObject(Variables.currentkEYcuadroMuetreo);

                    }


*/


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



    private void generateUniqueIDtOForm(){

        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[6];
        random.nextBytes(bytes);

       // String key = DatatypeConverter.printBase64Binary(random);

        String key = new String(Base64.encodeBase64(bytes));

        Log.i("misuperid","el id es "+key);

    }



    private void checkIFeXISTfILE(){
        //PODEMOS USAR UN BUCLE POR 3 SEGUNDOS...

        //Y USAR UN WHILE.... Y SI DENTRO DE ESTE TIMPO NO ENCONTRO FILE,,,
        //SIGNIFCA QUE NO EXISTE EL FILE...


    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case TWO_PERMISION_REQUEST: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED

                        && grantResults[1] == PackageManager.PERMISSION_GRANTED ) {

                    Log.i("permisodd","es permiso concedido READ_EXTERNAL_STORAGE ");

                    //descragamos el file..

                      ///aqui descargamos el pdf.....y lo screamos

                    try {
                        createPDFContenedores() ;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    Log.i("permisodd","es permiso denegado READ_EXTERNAL_STORAGE ");

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    //   Toast.makeText(ActivityContenedoresPrev.this, "P" , Toast.LENGTH_SHORT).show();
                }
            }




                // final int CODE_WRITE_EXTERNAL_STORAGE = 132;
            //    final int CODE_READ_EXTERNAL_STORAGE = 133;

            case CODE_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED

                    ){


                        try {
                            createPDFContenedores() ;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }




                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    Log.i("permisodd","es permiso denegado CODE_WRITE_EXTERNAL_STORAGE  ");

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    //   Toast.makeText(ActivityContenedoresPrev.this, "P" , Toast.LENGTH_SHORT).show();
                }


            }



            case CODE_READ_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.i("permisodd","es permiso concedido READ_EXTERNAL_STORAGE ");


                    if(ActivityCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED){


                        try {
                            createPDFContenedores() ;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    Log.i("permisodd","es permiso denegado CODE_READ_EXTERNAL_STORAGE ");

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    //   Toast.makeText(ActivityContenedoresPrev.this, "P" , Toast.LENGTH_SHORT).show();
                }

            }


            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void requestPermision( Context contex){

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(contex);
        bottomSheetDialog.setContentView(R.layout.bottom_sheetx);

        bottomSheetDialog.setCancelable(false);
        Button btnConceder =bottomSheetDialog.findViewById(R.id.btnConceder);
        Button btnCerrar =bottomSheetDialog.findViewById(R.id.btnCerrar);

        btnConceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_DENIED &&
                        ActivityCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE)
                                == PackageManager.PERMISSION_DENIED){

                    Log.i("permisodd","SE EJECUTO ESTE IF ");



                    ActivityCompat.requestPermissions(PdfMaker2_0.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        TWO_PERMISION_REQUEST);


            }

               else if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_DENIED){
                    Log.i("permisodd","SE EJECUTO ES TEELSE  IF 1 ");

                    ActivityCompat.requestPermissions(PdfMaker2_0.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            CODE_READ_EXTERNAL_STORAGE);


                }



               else if(ActivityCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_DENIED ){
                    Log.i("permisodd","SE EJECUTO ES TEELSE  IF 2 ");

                    ActivityCompat.requestPermissions(PdfMaker2_0.this, new String[]{WRITE_EXTERNAL_STORAGE},
                            CODE_WRITE_EXTERNAL_STORAGE);


                }






                bottomSheetDialog.dismiss();

            }
        });



        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomSheetDialog.dismiss();


            }
        });
        bottomSheetDialog.show();




    }


    /****AGREGAR LAS NUEVAS PROPIEDADES AL OBJETOS IMAGEREPORT ALGO ASI... VERIFICAR LAS PROPIEDADES Y AGREGARLES
     ***DESPUES CEHKEAR SI EN REALIDAD TODO FUNCIONA CON REPSECTO A IMAGENES
     * **CUANTA MEMORIA CONSUME ,AHORA ESTA EN 850MB CASI UN GIGA...
     * ***CHEKEAR PDF SI SALEN LAS IMAGENES...
     *
     * OAGREGAR UN PUNTO DE QUIEBRE Y OBTENER LAS IMAGENES ID Y DESPUES AGREGARLES UNO POR UNO ESA PROPIEDAD..
     * //SUBIR MAS IMAGENES CON ESA NUEVA PROPIEDAD...//UNAS 3 IMAGENES POR SET COMOMAXI, ELIMNAR ALGUNAS..
     * EN EACTIVITY PREVIW PAR ASI CAMBAIR A POCAS...
     *


     ****/



    private void DowloadUniqeuRechazadosoObject (String currentIDoBJECTvinuc ){
///PODEMOS OBTER

        //to fetch all the users of firebase Auth app
        //  DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersdRef = RealtimeDB.rootDatabaseReference.child("Informes").child("CuadrosMuestreo");

        Query query = usersdRef.orderByChild("uniqueIdObject").equalTo(currentIDoBJECTvinuc);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {

                    CuadroMuestreo informe=ds.getValue(CuadroMuestreo.class);
                    Log.i("holerd","aqui se encontro un cuadro muestreo......");

                    if(informe!=null){
                        numRacimosRechzados=informe.getTotalRechazadosAll();
                          //activamos btn generar

                        btnDescargar.setEnabled(true);

                    break;
                    }


                }


             //aqui podemos revisar
             //   checkIfFileisSaveINsRORAGE(uriThiSfile);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("misdata","el error es  "+ error.getMessage());

            }
        } );

    }



    private void checkIfFileisSaveINsRORAGE (Uri uriSearchFile){

        final boolean[] ecnontroFiLE = {false};
        final boolean[] pasaron3Seconds = {false};


        cTimer =new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {


                if(null != uriSearchFile) {
                    try {
                        InputStream inputStream = PdfMaker2_0.this.getContentResolver().openInputStream(uriSearchFile);
                        inputStream.close();
                        ecnontroFiLE[0] = true;
                    } catch (Exception e) {
                        // Log.w(MY_TAG, "File corresponding to the uri does not exist " + uri.toString());
                    }
                }

                /*
                while(!pasaron3Seconds[0]){



                }

*/



                Log.i("searchFile","se llama ontcikc");

                //aqui chekeamo habver si encontro


               // mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                // logic to set the EditText could go here
            }

            public void onFinish() {

                try {
                    InputStream inputStream = PdfMaker2_0.this.getContentResolver().openInputStream(uriSearchFile);
                    inputStream.close();
                    ecnontroFiLE[0] = true;
                } catch (Exception e) {
                    // Log.w(MY_TAG, "File corresponding to the uri does not exist " + uri.toString());
                }




                pasaron3Seconds[0]=false;


                if(ecnontroFiLE[0]){ ///si existe activamos btn intent open pdf
                    Log.i("searchFile","si se encontro file");

                }else {

                    Log.i("searchFile","NO se encontro file");


                    //le decimos que no se descargo

                }

              //  mTextField.setText("done!");
            }

        }.start();


        //CERREMOAS UN BUCLE I CHEKEAMO SI YA FUE DESCARGADIO



    }



    void cancelTimer() {
        //si se destruye la actividad ...lo cancelamos....


        if(cTimer!=null)
            cTimer.cancel();
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();

        cancelTimer();

    }
//onDestroy()/onDestroyView()



    private void viewpdf(Uri uriPdf) {
        // add the link of pdf
        //String value="https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf";
        Intent intent=new Intent(Intent.ACTION_VIEW, uriPdf);

        // start activity
        startActivity(intent);

    }



    public void verifyStoragePermission() {
        int permission = ActivityCompat.checkSelfPermission(PdfMaker2_0.this, WRITE_EXTERNAL_STORAGE);

        // Surrounded with if statement for Android R to get access of complete file.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager() && permission != PackageManager.PERMISSION_GRANTED) {

                Log.i("permosod","tiene el permiso concedido");

                ActivityCompat.requestPermissions(PdfMaker2_0 .this, new String[]{Manifest.permission.MANAGE_EXTERNAL_STORAGE},7);

                // Abruptly we will ask for permission once the application is launched for sake demo.
                Intent intent = new Intent();
                intent.setAction(ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                Uri uri = Uri.fromParts("package", this.getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);

            }else{

                Log.i("permosod","el permiso esta   denegado");


            }
        }
    }


    void openPdf(Uri uriPdfPath){

         // Start Intent to View PDF from the Installed Applications.
        Intent pdfOpenIntent = new Intent(Intent.ACTION_VIEW);
        pdfOpenIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pdfOpenIntent.setClipData(ClipData.newRawUri("", uriPdfPath));
        pdfOpenIntent.setDataAndType(uriPdfPath, "application/pdf");
        pdfOpenIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION |  Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        try {
            startActivity(pdfOpenIntent);
        } catch (ActivityNotFoundException activityNotFoundException) {
            Toast.makeText(this,"No existe ninguna app para ver pdf",Toast.LENGTH_LONG).show();

        }
    }

}