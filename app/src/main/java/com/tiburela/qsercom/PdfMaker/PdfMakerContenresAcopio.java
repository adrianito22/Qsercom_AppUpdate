package com.tiburela.qsercom.PdfMaker;


import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.provider.Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION;

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
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
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
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.models.ControlCalidad;
import com.tiburela.qsercom.models.CuadroMuestreo;
import com.tiburela.qsercom.models.DatosDeProceso;
import com.tiburela.qsercom.models.ImagenReport;
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

public class PdfMakerContenresAcopio extends AppCompatActivity {
    int ActivityFormularioDondeVino;
      File file;




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


     String [] dateCreate;

    LinearLayout layoutDown;
    Button btnDescargar ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_makert_con_acop);

        ActivityFormularioDondeVino = getIntent().getIntExtra(Variables.KEY_PDF_MAKER,0);

        nameOFPDFrEPORTfile=getIntent().getStringExtra(Variables.KEY_PDF_MAKER_PDF_NAME);
        numProductsPostcosecha=getIntent().getIntExtra(Variables.KEY_PDF_MAKER_PDF_NUM_PR_POST,0);

        Log.i("sermasd","namepdf es "+nameOFPDFrEPORTfile);


        contexto=getApplicationContext();
         progressBar2=findViewById(R.id.progressBar2);
         txtTareaAqui=findViewById(R.id.txtTareaAqui);
         layoutDown=findViewById(R.id.layoutDown);
         btnDescargar=findViewById(R.id.btnDescargar) ;
        btnIrAARCHIVOpdf=findViewById(R.id.btnIrAARCHIVOpdf);
        layoutDown.setVisibility(View.VISIBLE);

        btnIrAARCHIVOpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(file!=null){


                    try{

                        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                        StrictMode.setVmPolicy(builder.build());


                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(file), "application/pdf");
                        // intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        Intent intent1 = Intent.createChooser(intent, "Open With");

                        startActivity(intent1);



                    } catch (Exception e) {
                        Log.i("searchFilex","la expecion es "+e.getMessage());

                        throw new RuntimeException(e);
                    }


                }


            }
        });

        btnDescargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //DESCTIVAMSO EL BOTON SOLO SI TENEMOS LOS 2  PERMISOS CONCEDIDOS
                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED &&

                        ActivityCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE)
                                == PackageManager.PERMISSION_GRANTED) { //si tiene permisos

                    btnDescargar.setEnabled(false);

                }



                try {


                    if (android.os.Build.VERSION.SDK_INT >Build.VERSION_CODES.R) {//adnroid 11


                        Log.i("permisodd","tiene ya el permiso READ_EXTERNAL_STORAGE  && WRITE_EXTERNAL_STORAGE ");

                        Toast.makeText(PdfMakerContenresAcopio.this, "Descargando Pdf", Toast.LENGTH_SHORT).show();

                        //03-03 TEMU 838382-8

                        dateCreate=Variables.CurrenReportContensEnACp.getFechaInicio().split("/");

                        //String date=Variable
                        //  String name=+""+  Variables.CurrenReportContensEnACp.getNumContenedor();
                        // createPdfContenrAcopio2("holaas");
                        createPdfContenrAcopio2(""+dateCreate[0]+"_"+dateCreate[1]+" "+Variables.CurrenReportContensEnACp.getNumContenedor()); ;


                    }else{


                        if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                                == PackageManager.PERMISSION_GRANTED &&
                                ActivityCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE)
                                        == PackageManager.PERMISSION_GRANTED

                        ){ //si tiene permisos
                            Log.i("permisodd","tiene ya el permiso READ_EXTERNAL_STORAGE  && WRITE_EXTERNAL_STORAGE ");

                            Toast.makeText(PdfMakerContenresAcopio.this, "Descargando Pdf", Toast.LENGTH_SHORT).show();

                            //03-03 TEMU 838382-8

                            dateCreate=Variables.CurrenReportContensEnACp.getFechaInicio().split("/");

                            //String date=Variable
                            //  String name=+""+  Variables.CurrenReportContensEnACp.getNumContenedor();
                            // createPdfContenrAcopio2("holaas");
                            createPdfContenrAcopio2(""+dateCreate[0]+"_"+dateCreate[1]+" "+Variables.CurrenReportContensEnACp.getNumContenedor()); ;


                        }else{
                            Log.i("permisodd","no tiene ambos permisos ");



                            requestPermision(PdfMakerContenresAcopio.this);


                        }



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


         if(ActivityFormularioDondeVino  == Variables.FormatDatsContAcopiPREVIEW){  //completar estos




            Log.i("debbdf","es el segundo if");

        }






    }





  private  void UpdateProgressAndText(String texto,int progressPercent) {

      progressBar2.setProgress(progressPercent);
      txtTareaAqui.setText(texto);

  }


    public void createPdfContenrAcopio2(String nameDoc) throws Exception {




        resetListGlobales();



        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
         file = new File(directory, nameDoc+".pdf");
        uriThiSfile=Uri.fromFile(file);
        HelperContenedoresAcopio.initFontx();


        PdfWriter writer = new PdfWriter(file); //le pasmaos el file



        PdfDocument miPFDocumentkernel= new PdfDocument(writer);
        PageSize pageSize= PageSize.A4;  //si no le quitamos el rotate...
        PdfPage pagePdf= miPFDocumentkernel.addNewPage(pageSize);///

     //   miPFDocumentkernel.addNewPage(pageSize);///


        HelperPdf pdfHelper= new HelperPdf();

        Document midocumentotoAddData= new Document(miPFDocumentkernel,pageSize); // le gagregamos data a este...
        midocumentotoAddData.setMargins(0, 0, 0, 0);

        Image imglogqSercom=pdfHelper.createInfoImgtoPDF(getDrawable(R.drawable.headerpdf),1);
        imglogqSercom.scaleToFit(595f, 200f); //ESTA EN 400 DESPUES 3300
        imglogqSercom.setHorizontalAlignment(HorizontalAlignment.CENTER);
        midocumentotoAddData.add(imglogqSercom).setTopMargin(0f);


        /**CONFIGURAMOS OTRA VEZ EL MARGEN*/
        midocumentotoAddData.setMargins(190, 0, 105, 0);//MIRAR MRAGENES ESTAB EN 105


        Image imageHeader=pdfHelper.createInfoImgtoPDF(getDrawable(R.drawable.headerpdf),1);
        imageHeader.setFixedPosition(0, 650); // si no usamos este
        imageHeader.setMarginTop(0f); // de prueba


        /**el background y logo
         */


        Image imagBack=pdfHelper.createInfoImgtoPDF(getDrawable(R.drawable.logo_qsercon_baclgg),1);
        imagBack.setFixedPosition(100, 200); //probando posotion //estabe en 250
        imagBack.setMarginTop(0f); // de prueba


        ImageEventHandlerHeader handler = new ImageEventHandlerHeader(imageHeader,midocumentotoAddData,imagBack);

        miPFDocumentkernel.addEventHandler(PdfDocumentEvent.END_PAGE, handler);

        Rectangle remaining = midocumentotoAddData.getRenderer().getCurrentArea().getBBox();

        float y = remaining.getTop();
        float sizeTableANCHO= pageSize.getWidth()-60f;




        float position = midocumentotoAddData.getRenderer().getCurrentArea().getBBox().getTop();


        Image imglogqSercomfooterBacground=pdfHelper.createInfoImgtoPDF(getDrawable(R.drawable.footer_pdf),1);
        imglogqSercomfooterBacground.setFixedPosition(0, 0); // si no usamos este
        BackgroundEventHandler handler2 = new BackgroundEventHandler(imglogqSercomfooterBacground);
        miPFDocumentkernel.addEventHandler(PdfDocumentEvent.END_PAGE, handler2);




        /**TABLE 1 */

        Cell cellGlobal= new Cell()  .setBorder(Border.NO_BORDER).add(new Paragraph("DATOS CONTENEDORES DE ACOPIO SEMANA "+Variables.CurrenReportContensEnACp.getSemanaNum()+"   - "+Variables.CurrenReportContensEnACp.getUniqueIDinforme()).setPadding(0.2f).
                setTextAlignment(TextAlignment.CENTER).setFontSize(8.6f).setBold().setBackgroundColor(new DeviceRgb(255, 242, 204)));

        cellGlobal.setPaddingBottom(0f); //para no dejar margen abajo
        cellGlobal.setPaddingLeft(30f); //estaba en 20
        cellGlobal.setPaddingRight(30f);
        midocumentotoAddData.add(cellGlobal);


      /**creamos la primera tabla*/
        ArrayList<NameAndValue>listNameAndValue=HelperContenedoresAcopio.generateParValorList(HelperContenedoresAcopio.TABLE_DATOS_CONTENEDORES_DE_ACOPIO);
        Table tableGlobal=HelperContenedoresAcopio.generaTableByID(listNameAndValue,HelperContenedoresAcopio.TABLE_DATOS_CONTENEDORES_DE_ACOPIO);
        HelperContenedoresAcopio.configTableMaringAndWidth(tableGlobal,sizeTableANCHO);
       // tableGlobal.setMarginTop(0f);
       // tableGlobal.setPaddingTop(0f);
        midocumentotoAddData.add(tableGlobal);



        //
        /**TABLE 2 */

         cellGlobal= new Cell()  .setBorder(Border.NO_BORDER).add(new Paragraph("DATOS DE CONTENEDOR").
                setTextAlignment(TextAlignment.CENTER).setFontSize(8.6f).setBold().setBackgroundColor(new DeviceRgb(156, 194, 229)));

        cellGlobal.setPaddingBottom(0f); //para no dejar margen abajo
        cellGlobal.setPaddingTop(0f); //para no dejar margen abajo
        cellGlobal.setPaddingLeft(30f); //estaba en 20
        cellGlobal.setPaddingRight(30f);
        midocumentotoAddData.add(cellGlobal);


        /**creamos la 2 tabla*/
        listNameAndValue=HelperContenedoresAcopio.generateParValorList(HelperContenedoresAcopio.TABLE_DATOS_DEL_CONTENEDOR);
         tableGlobal=HelperContenedoresAcopio.generaTableByID(listNameAndValue,HelperContenedoresAcopio.TABLE_DATOS_DEL_CONTENEDOR);
        HelperContenedoresAcopio.configTableMaringAndWidth(tableGlobal,sizeTableANCHO);
        midocumentotoAddData.add(tableGlobal);



        /**TABLE 3 */

        cellGlobal= new Cell()  .setBorder(Border.NO_BORDER).add(new Paragraph("SELLOS LLEGADA").
                setTextAlignment(TextAlignment.CENTER).setFontSize(8.6f).setBold().setBackgroundColor(new DeviceRgb(156, 194, 229)));

        cellGlobal.setPaddingBottom(0f); //para no dejar margen abajo
        cellGlobal.setPaddingTop(0f); //para no dejar margen abajo
        cellGlobal.setPaddingLeft(30f); //estaba en 20
        cellGlobal.setPaddingRight(30f);
        midocumentotoAddData.add(cellGlobal);


        /**creamos la 3 tabla*/
        listNameAndValue=HelperContenedoresAcopio.generateParValorList(HelperContenedoresAcopio.TABLE_SELLOS_LLEGADA);
        tableGlobal=HelperContenedoresAcopio.generaTableByID(listNameAndValue,HelperContenedoresAcopio.TABLE_SELLOS_LLEGADA);
        HelperContenedoresAcopio.configTableMaringAndWidth(tableGlobal,sizeTableANCHO);
        midocumentotoAddData.add(tableGlobal);




        /**TABLE 4 */

        cellGlobal= new Cell()  .setBorder(Border.NO_BORDER).add(new Paragraph("SELLOS INSTALADOS").
                setTextAlignment(TextAlignment.CENTER).setFontSize(8.6f).setBold().setBackgroundColor(new DeviceRgb(156, 194, 229)));

        cellGlobal.setPaddingBottom(0f); //para no dejar margen abajo
        cellGlobal.setPaddingTop(0f); //para no dejar margen abajo
        cellGlobal.setPaddingLeft(30f); //estaba en 20
        cellGlobal.setPaddingRight(30f);
        midocumentotoAddData.add(cellGlobal);



        //table termografo
        tableGlobal=HelperContenedoresAcopio.generateTermografoTable();
        HelperContenedoresAcopio.configTableMaringAndWidth(tableGlobal,sizeTableANCHO);
        midocumentotoAddData.add(tableGlobal);


        listNameAndValue=HelperContenedoresAcopio.generateParValorList(HelperContenedoresAcopio.TABLE_SELLOS_INSTALADOS);
        tableGlobal=HelperContenedoresAcopio.generaTableByID(listNameAndValue,HelperContenedoresAcopio.TABLE_SELLOS_INSTALADOS);
        HelperContenedoresAcopio.configTableMaringAndWidth(tableGlobal,sizeTableANCHO);
        midocumentotoAddData.add(tableGlobal);



        /**TABLE 5 */

        cellGlobal= new Cell()  .setBorder(Border.NO_BORDER).add(new Paragraph("DATOS TRANSPORTISTA").
                setTextAlignment(TextAlignment.CENTER).setFontSize(8.6f).setBold().setBackgroundColor(new DeviceRgb(156, 194, 229)));

       // cellGlobal.setPaddingBottom(0f); //para no dejar margen abajo
        cellGlobal.setPaddingTop(0f); //para no dejar margen abajo
        cellGlobal.setPaddingLeft(30f); //estaba en 20
        cellGlobal.setPaddingRight(30f);
        midocumentotoAddData.add(cellGlobal);


        /**creamos la 5 tabla*/
        listNameAndValue=HelperContenedoresAcopio.generateParValorList(HelperContenedoresAcopio.TABLE_DATOS_TRANSPORTISTA);
        tableGlobal=HelperContenedoresAcopio.generaTableByID(listNameAndValue,HelperContenedoresAcopio.TABLE_DATOS_TRANSPORTISTA);
        HelperContenedoresAcopio.configTableMaringAndWidth(tableGlobal,sizeTableANCHO);
        midocumentotoAddData.add(tableGlobal);


        midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE)); //nuvea pagina




        cellGlobal= new Cell()  .setBorder(Border.NO_BORDER).add(new Paragraph("DATOS DE PROCESO").
                setTextAlignment(TextAlignment.CENTER).setFontSize(8.6f).setBold().setBackgroundColor(new DeviceRgb(156, 194, 229)));

        // cellGlobal.setPaddingBottom(0f); //para no dejar margen abajo
        cellGlobal.setPaddingTop(15f); //para no dejar margen abajo
        cellGlobal.setPaddingLeft(30f); //estaba en 20
        cellGlobal.setPaddingRight(30f);
        cellGlobal.setPaddingBottom(0f);
        midocumentotoAddData.add(cellGlobal);

        Log.i("mispaps","el size de map xxc es "+Variables.mimapaDatosProcesMapCurrent.size());
        /**creamos la 6 tabla*/
        tableGlobal=HelperContenedoresAcopio.generaTableDatosProceso(Variables.mimapaDatosProcesMapCurrent);
        HelperContenedoresAcopio.configTableMaringAndWidth(tableGlobal,sizeTableANCHO);
        midocumentotoAddData.add(tableGlobal);




        tableGlobal= new Table(2);
        cellGlobal= new Cell().setPaddingLeft(10f).setBorder(Border.NO_BORDER).add(new Paragraph("NOMBRE INSPECTOR DE ACOPIO: "+Variables.CurrenReportContensEnACp.getInspectorAcopio()).
                setTextAlignment(TextAlignment.LEFT).setFontSize(8.6f).setBold().setBackgroundColor(new DeviceRgb(255, 242, 204)));
        tableGlobal.addCell(cellGlobal);


        cellGlobal= new Cell()  .setBorder(Border.NO_BORDER).add(new Paragraph("CEDULA: "+Variables.CurrenReportContensEnACp.getCedulaIdenti()).
                setTextAlignment(TextAlignment.LEFT).setFontSize(8.6f).setBold().setBackgroundColor(new DeviceRgb(255, 242, 204)));


        tableGlobal.setBackgroundColor(new DeviceRgb(255, 242, 204));
        tableGlobal.addCell(cellGlobal);

        cellGlobal.setPaddingTop(0f); //para no dejar margen abajo
       // cellGlobal.setPaddingLeft(30f); //estaba en 20
       // cellGlobal.setPaddingRight(30f);
        HelperContenedoresAcopio.configTableMaringAndWidth(tableGlobal,sizeTableANCHO);

        midocumentotoAddData.add(tableGlobal);




        /***ANEXOS TOCA POR AQUI.. VAMOS HABER...**/
        midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE)); //nuvea pagina

        Paragraph mipargrap =  new Paragraph("ANEXO FOTOS DE APERTURA, INSPECCIÓN Y CONSOLIDACIÓN DE CONTENEDOR.").
                setTextAlignment(TextAlignment.CENTER).setFontSize(8.6f).setBold().setPaddingTop(15f);

        midocumentotoAddData.add(mipargrap);


        //aqui aregmaos

        HelperImage.indiceValues=0;
        HelperAdImgs.createPages_addImgs(Variables.FOTO_LLEGADA_CONTENEDOR," ",midocumentotoAddData,pageSize,contexto);
        Log.i("foticoss","terminamos foto llegada");




        /**FOTO_SELLOS LLEGADA...*/
        HelperImage.indiceValues=0;

        HelperAdImgs.createPages_addImgs(Variables.FOTO_SELLO_LLEGADA,"",midocumentotoAddData,pageSize,contexto);

        Log.i("foticoss","terminamos sellos llegada");

        HelperImage.indiceValues=0;

        /**FOTO_PUERTA_ABIERTA_DEL_CONTENENEDOR...*/
        HelperAdImgs.createPages_addImgs(Variables.FOTO_PUERTA_ABIERTA_DEL_CONTENENEDOR," ",midocumentotoAddData,pageSize,contexto);
        Log.i("foticoss","terminamos puerta bauiertya contenedor");



        /**FOTO_PALLETS ...*/
        HelperImage.indiceValues=0;

        // midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        HelperAdImgs.createPages_addImgs(Variables.FOTO_PALLETS,"",midocumentotoAddData,pageSize,contexto);

        Log.i("foticoss","terminamos foto pallets");

        HelperImage.indiceValues=0;

        //   midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        HelperAdImgs.createPages_addImgs(Variables.FOTO_CIERRE_CONTENEDOR,"",midocumentotoAddData,pageSize,contexto);

        Log.i("foticoss","terminamos foto cierre contenedor");

        HelperImage.indiceValues=0;

        midocumentotoAddData.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        HelperAdImgs.createPages_addImgs(Variables.FOTO_DOCUMENTACION,"* DOCUMENTACIÓN",midocumentotoAddData,pageSize,contexto);


        Log.i("foticoss","terminamos documentacion");

        Paragraph paragraph =HelperPdf.generateTexRevisadoPorFormatAndPosition(Variables.CurrenReportContensEnACp.getNombreRevisa(),Variables.CurrenReportContensEnACp.getCodigonRevisa());
        midocumentotoAddData.add(paragraph);


        /**PRXIMOS PASOS AVERGIAR COMO SE COMPORRTA ESTE CODIGO IMAGENES
         * PASARLE EL TIPO DE OBJETO AL QUE CREA PARA VALOR Y LA DEMAS DATA...
         * BTN PARA GENERAR PDF EN ESE REPORTE PREVIEW....
         * BTN GUARDAR LOCALE Y GENERAR PDF ,INTENTAR GENMERAR EL PDF Y VER QUE PASA.....
         * */


        midocumentotoAddData.close();
        btnIrAARCHIVOpdf.setEnabled(true);


    }


    private void resetListGlobales(){

        HelperAdImgs.currentListImagesSeccion= new ArrayList<>();
        HelperImage.imagesSetToCurrentFila= new ArrayList<>();



    }



    private HashMap<String,DatosDeProceso>generaExampleDatosProces(){
     HashMap<String, DatosDeProceso>miMap= new HashMap<>();


        for(int indice=0; indice<10; indice++){

            miMap.put(""+indice, new DatosDeProceso("Adrian ","EMPAQ","000"+indice,indice*3,"jjj","jkj"));

        }


        return miMap;

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

                    Toast.makeText(PdfMakerContenresAcopio.this, "Permiso concedido, puedes descargar", Toast.LENGTH_SHORT).show();




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
            break;

            case CODE_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if(ActivityCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED

                    ){


                        if(ActivityCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE)
                                == PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(PdfMakerContenresAcopio.this, "Permiso concedido, puedes descargar", Toast.LENGTH_SHORT).show();
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


            break;

            case CODE_READ_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.i("permisodd","es permiso concedido READ_EXTERNAL_STORAGE ");


                    if(ActivityCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED){


                        if(ActivityCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE)
                                == PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(PdfMakerContenresAcopio.this, "Permiso concedido, puedes descargar", Toast.LENGTH_SHORT).show();
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



                    ActivityCompat.requestPermissions(PdfMakerContenresAcopio.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        TWO_PERMISION_REQUEST);


            }

               else if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_DENIED){
                    Log.i("permisodd","SE EJECUTO ES TEELSE  IF 1 ");

                    ActivityCompat.requestPermissions(PdfMakerContenresAcopio.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            CODE_READ_EXTERNAL_STORAGE);


                }



               else if(ActivityCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_DENIED ){
                    Log.i("permisodd","SE EJECUTO ES TEELSE  IF 2 ");

                    ActivityCompat.requestPermissions(PdfMakerContenresAcopio.this, new String[]{WRITE_EXTERNAL_STORAGE},
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


}