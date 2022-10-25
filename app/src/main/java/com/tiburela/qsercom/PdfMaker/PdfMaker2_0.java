package com.tiburela.qsercom.PdfMaker;


import androidx.appcompat.app.AppCompatActivity;
//import androidx.compose.ui.text.Paragraph;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;


import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.kernel.layout.element.Table; //???? der any probllem?

//write here which class not found and wirte whee you want to use i timport com.itextpdf.layout.element.Cell;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.GrooveBorder;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

//issue resolved edear


import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.tiburela.qsercom.R;
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
        Image imglogqSercom=pdfHelper.createInfoImgtoPDF(getDrawable(R.drawable.headerpdf),PdfMaker2_0.this,5);
       // imglogqSercom.setHorizontalAlignment(HorizontalAlignment.CENTER);

        midocumentotoAddData.add(imglogqSercom).setTopMargin(0f);



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
         imglogqSercom=pdfHelper.createInfoImgtoPDF(getDrawable(R.drawable.headerpdf),PdfMaker2_0.this,5);
        // imglogqSercom.setHorizontalAlignment(HorizontalAlignment.CENTER);
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



          /**DATOS DE PROCESO*/

          //Tendra 9 values..


        DeviceRgb dvolor= new DeviceRgb(255, 255, 255);
     //   table.setStrokeColor(dvolor);
      //  SolidBorder ADDD= new SolidBorder(dvolor, 3);
        Border boder2= new GrooveBorder(dvolor, 3);
        Cell cell= new Cell();

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


}