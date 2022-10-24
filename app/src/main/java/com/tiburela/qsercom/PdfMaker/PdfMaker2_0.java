package com.tiburela.qsercom.PdfMaker;


import androidx.appcompat.app.AppCompatActivity;
//import androidx.compose.ui.text.Paragraph;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;


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
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;

//issue resolved edear


import com.itextpdf.layout.element.Table;
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

        HelperPdf pdfHelper= new HelperPdf();

        /**add header imagen*/
        com.itextpdf.layout.element.Image imglogqSercom=pdfHelper.createInfoImgtoPDF(getDrawable(R.drawable.headerpdf),PdfMaker2_0.this,5);
       // imglogqSercom.setHorizontalAlignment(HorizontalAlignment.CENTER);
        midocumentotoAddData.add(imglogqSercom);

        float sizeColumns[]= {1,3};
        Table table1=  new Table(sizeColumns);
       // table1.

        DeviceRgb rgbColor= new DeviceRgb(153, 255, 229); //color verde claro

        try {
            /**add primer cuadro..*/
            //crea list de celds...y add values...
            ArrayList<NameAndValue>dataTOtable1=HelperPdf.generaDataToTable(Variables.CurrenReportPart1,Variables.CurrenReportPart2,Variables.CurrenReportPart3,1);
            HashMap<String,Cell> listCellsToTab1= HelperPdf.generateHasmapFieldnameandValue(dataTOtable1);


            //editamos la tabla 1
            listCellsToTab1.get("0name").setBackgroundColor(rgbColor);
            listCellsToTab1.get("0value").setBackgroundColor(rgbColor);

            ///productos postcosecha

            addCellsInTable(listCellsToTab1,table1);
            midocumentotoAddData.add(table1);


        } catch (Exception e) {
            e.printStackTrace();
        }

       /**DATA FICITICA EDIYTAR*/
        Cell cell0= new Cell(1,2).add(new Paragraph("PRODUCTOS POSTOCOSECHAUTILIZADOS")) ;

        Cell cell1= new Cell().add(new Paragraph("bsz100")) ;
        Cell cell2= new Cell().add(new Paragraph("jl200"))  ;
        Cell cell3= new Cell().add(new Paragraph("lñ20"))  ;
        Cell cell4= new Cell().add(new Paragraph("der08"))  ;
        Cell cell5= new Cell().add(new Paragraph("der09"))  ;
        Cell cell6= new Cell().add(new Paragraph("der02"))  ;
        Cell cell7= new Cell().add(new Paragraph("der05"))  ;
        Cell cell8= new Cell().add(new Paragraph("der02"))  ;

        cell0.setBackgroundColor(rgbColor);
       // cell2.setBackgroundColor(rgbColor);

        table1.addCell(cell0);
        table1.addCell(cell1.setTextAlignment(TextAlignment.LEFT));
        table1.addCell(cell2.setTextAlignment(TextAlignment.CENTER));
        table1.addCell(cell3.setMarginLeft(10f)); //margin no parece funciona
        table1.addCell(cell4.setPaddingLeft(10f));  //padng funciona
        table1.addCell(cell5.setMargin(5));
        table1.addCell(cell6);
        table1.addCell(cell7);
        table1.addCell(cell8);

        table1.setMarginTop(10f);

        table1.setWidth(pageSize.getWidth()-75f);

        midocumentotoAddData.add(table1);

        //segunda postocosechja

        //  float []array={3,1,6};

           //  Table table = new Table(array);
           Paragraph fgf = new Paragraph("HOLA");

          // celdcolor.setBackgroundColor(dvolor);
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

            table.addCell(hasmpa.get(indice+"name"));
            table.addCell(hasmpa.get(indice+"value"));


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