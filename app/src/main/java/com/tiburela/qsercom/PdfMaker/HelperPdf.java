package com.tiburela.qsercom.PdfMaker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.models.CalibrFrutCalEnf;
import com.tiburela.qsercom.models.ControlCalidad;
import com.tiburela.qsercom.models.DefectsCantdad;
import com.tiburela.qsercom.models.NameAndValue;
import com.tiburela.qsercom.models.ProductPostCosecha;
import com.tiburela.qsercom.models.PromedioLibriado;
import com.tiburela.qsercom.models.ReportCamionesyCarretas;
import com.tiburela.qsercom.models.SetInformDatsHacienda;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;
import com.tiburela.qsercom.models.TableCalidProdc;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class HelperPdf {
public static int percentvalue=0;
   public static  PdfFont font=null;

   public static boolean hayMasDe10products;
    public static DeviceRgb rgbColorVerdeCanaFuerte= new DeviceRgb(56, 86, 35);

   public static DeviceRgb rgbColorVerdeCana= new DeviceRgb(197, 224, 179);
    public static DeviceRgb rgbColorAzulClaro= new DeviceRgb(189, 214, 238);
    public static DeviceRgb rgbColorNaranja= new DeviceRgb(255, 217, 102);
    public static DeviceRgb rgbColorDurazno= new DeviceRgb(247, 202, 172);

    public static DeviceRgb rgbColorPlomoBlanco= new DeviceRgb(242, 242, 242);

   // private static  ArrayList<String>listNamesGlobal= new ArrayList<>();


    public static ArrayList<Integer>listNumsCustomDefects= new ArrayList<>();

    public static HashMap<Integer,  ArrayList<String>> hasmapOfListwhitNamesFefectsCurrentControlCalidadFOmr = new HashMap<>();



    public static double promedioPOrcetajeQS;

    public static float CALIDAD_TOTAL;
    public static  double PORCENTAJE_DE_DEFECTOS;
    public static  ArrayList<TableCalidProdc> TableCalidProdc;


    public static HashMap<String,  ArrayList<DefectsCantdad>> defectsSelecionHahashMaps = new HashMap<>();
    public static HashMap< String, ArrayList<DefectsCantdad>> defectsEmpaqueHashMapOfLists = new HashMap<>();
      HashMap<String, ArrayList<String>>hola= new HashMap<>();


       public static void initFontx(){
           try{
               font = PdfFontFactory.createFont(StandardFonts.HELVETICA);

           } catch (IOException e) {
               e.printStackTrace();
           }
           
       }


    public  Image  createInfoImgtoPDF(Drawable mIDrawable, Context conetxt,int i){
        // mIDrawable= conetxt.getDrawable(R.drawable.logopdf);
        Bitmap miBitmap= ((BitmapDrawable)mIDrawable). getBitmap();
        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
        miBitmap.compress(Bitmap.CompressFormat.PNG,100,stream1);
        byte[] bitmapData1= stream1.toByteArray();
        ImageData imageData= ImageDataFactory.create(bitmapData1);

        Image image =new Image(imageData);
        image.setWidth(100f);
        return  image;
    }



    public static void configTableMaringAndWidth(Table table1, float sizeTable){

        table1.setWidth(sizeTable);
        table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table1.setMarginLeft(60f);
        table1.setMarginRight(20f);
        Log.i("mitables","el table es  here es es "+sizeTable);


    }


    public static Image  createImagebYbitmap( Bitmap miBitmap){

        // mIDrawable= conetxt.getDrawable(R.drawable.logopdf);
       // Bitmap miBitmap= ((BitmapDrawable)mIDrawable). getBitmap();
        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
        miBitmap.compress(Bitmap.CompressFormat.PNG,100,stream1);
        byte[] bitmapData1= stream1.toByteArray();
        ImageData imageData= ImageDataFactory.create(bitmapData1);

        Image image =new Image(imageData);
        image.setWidth(100f);
        return  image;


    }


    public  Image  createInfoImgtoPDF(Drawable mIDrawable,int qualkity){

        // mIDrawable= conetxt.getDrawable(R.drawable.logopdf);
        Bitmap miBitmap= ((BitmapDrawable)mIDrawable).getBitmap();
        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
        miBitmap.compress(Bitmap.CompressFormat.PNG,qualkity,stream1);
        byte[] bitmapData1= stream1.toByteArray();
        ImageData imageData= ImageDataFactory.create(bitmapData1);

        Image image =new Image(imageData);
        //image.setWidth(826f);

        return  image;

    }


    public static   Image  createInfoImgtoPDF(Bitmap miBitmap){

        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
        miBitmap.compress(Bitmap.CompressFormat.JPEG,75,stream1);
        byte[] bitmapData1= stream1.toByteArray();
        ImageData imageData= ImageDataFactory.create(bitmapData1);

        Image image =new Image(imageData);
       // image.setWidth(100f);

        return  image;



       }






    public static HashMap<String,Cell> generateHasmapFieldnameandValue (ArrayList<NameAndValue>list,int alineacion, int TableID){

        Log.i("mismussndo","el lineacion es "+alineacion);
        HashMap<String,Cell> hasmpaDevolver= new HashMap<>();

        if(alineacion==50){ //alinea a la izquiera

            for(int i=0;i<list.size();i++){

                Cell cell1;

                Log.i("mismussndo","el name es "+list.get(i).getNameFields());
                Log.i("mismussndo","el value es "+list.get(i).getValueContent());
                Paragraph paragraph1 = new Paragraph(list.get(i).getNameFields()).setFontSize(7.3f).setPaddingLeft(10f).setFont(font).
                setPaddingTop(0f).setPaddingBottom(0f);
                cell1= new Cell().setHeight(11f).setPadding(0).add(paragraph1);  //estaba en 8 aqui ver

                /**arreglar el size aqui quiero menos size buscar */
                Log.i("mundiosert","se ejecuto este codee"+list.get(i).getNameFields());


                Paragraph paragraph2;
                if(list.get(i).getValueContent()==null){
                    paragraph2=new Paragraph("").setFontSize(7.3f).setFont(font);

                }else{

                    paragraph2= new Paragraph(list.get(i).getValueContent()).setFontSize(7.3f).setFont(font);

                }


                Cell cell2;

                if(TableID==600 && i==4 || i== 5 || i==6 || i==7|| i==8 ||i==9 ||i==10||i==11||i==12){//si es ;a tabla sellos instalados
                    cell2= new Cell(1,4).setHeight(11f).setPadding(0).add(paragraph2).setPaddingLeft(10f); ///le agragmos un span

                }


                else{

                    cell2= new Cell().setHeight(11f).setPadding(0).add(paragraph2).setPaddingLeft(10f); //normal

                }




                Log.i("mismussndo","estamo en el indice "+i);

                hasmpaDevolver.put(i+"name",cell1);
                hasmpaDevolver.put(i+"value",cell2);
            }
        }

        else if(alineacion==100){  //alinea en el centro...
            Log.i("mismussndo","el lineacion es "+alineacion);

            for(int i=0;i<list.size();i++){
                Paragraph paragraph1 = new Paragraph(list.get(i).getNameFields()).setFontSize(7.2f).setFont(font)// 7.2
                        .setPaddingTop(0f).setPaddingBottom(0f);

                Cell cell1= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);

                Paragraph paragraph2;
                if(list.get(i).getValueContent()==null){

                    Log.i("mismussndo","paragrap es nulo ");

                    paragraph2=new Paragraph("");

                }else{

                    paragraph2=new Paragraph(list.get(i).getValueContent()).setFontSize(7.2f).setFont(font);


                }


                //queremos que celda no tenga paddin leponemosen cero
                Cell cell2= new Cell().add(paragraph2).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(1f); //estab en 9,7 parece buena

                hasmpaDevolver.put(i+"name",cell1);
                hasmpaDevolver.put(i+"value",cell2);
            }

        }



        return hasmpaDevolver;

    }

    public static HashMap<String, Cell> generateHasmapFieldnameandValueCamionesYcarretas (ArrayList<NameAndValue> list, int alineacion, int TableID){

        Log.i("mismussndo","el lineacion es "+alineacion);
        HashMap<String,Cell> hasmpaDevolver= new HashMap<>();

        if(alineacion==50){ //alinea a la izquiera

            for(int i=0;i<list.size();i++){

                Cell cell1;

                Log.i("mismussndo","el name es "+list.get(i).getNameFields());
                Log.i("mismussndo","el value es "+list.get(i).getValueContent());
                Paragraph paragraph1 = new Paragraph(list.get(i).getNameFields()).setFontSize(8.5f).setPaddingLeft(10f).setFont(font).
                        setPaddingTop(0f).setPaddingBottom(0f);
                cell1= new Cell().setPadding(0.1f).add(paragraph1);  //estaba en 8 aqui ver

                /**arreglar el size aqui quiero menos size buscar */
                Log.i("mundiosert","se ejecuto este codee"+list.get(i).getNameFields());


                Paragraph paragraph2;
                if(list.get(i).getValueContent()==null){
                    paragraph2=new Paragraph("").setFontSize(8.5f).setFont(font).setPaddingTop(0f).setPaddingBottom(0f);

                }else{

                    paragraph2= new Paragraph(list.get(i).getValueContent()).setFontSize(8.5f).setFont(font).setPaddingTop(0f).setPaddingBottom(0f);

                }


                Cell cell2;

                if(TableID==600 && i==4 || i== 5 || i==6 || i==7|| i==8 ||i==9 ||i==10||i==11||i==12){//si es ;a tabla sellos instalados
                    cell2= new Cell(1,4).setPadding(0.1f).add(paragraph2).setPaddingLeft(10f).setPaddingTop(0f).setPaddingBottom(0f); ///le agragmos un span

                }


                else{

                    cell2= new Cell().setPadding(0.1f).add(paragraph2).setPaddingLeft(10f); //normal

                }




                Log.i("mismussndo","estamo en el indice "+i);

                hasmpaDevolver.put(i+"name",cell1);
                hasmpaDevolver.put(i+"value",cell2);
            }
        }

        else if(alineacion==100){  //alinea en el centro...
            Log.i("mismussndo","el lineacion es "+alineacion);

            for(int i=0;i<list.size();i++){
                Paragraph paragraph1 = new Paragraph(list.get(i).getNameFields()).setFontSize(8.2f).setFont(font)
                        .setPaddingTop(0f).setPaddingBottom(0f);

                Cell cell1= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setPadding(0.1f);

                Paragraph paragraph2;
                if(list.get(i).getValueContent()==null){

                    Log.i("prodcutrrr","se jeucto el if ll  ");


                    paragraph2=new Paragraph("");

                }else{

                    Log.i("prodcutrrr","se jeucto el else ll  ");

                    paragraph2=new Paragraph(list.get(i).getValueContent()).setFontSize(8.2f).setFont(font).setPaddingTop(0f).setPaddingBottom(0f);


                }


                //queremos que celda no tenga paddin leponemosen cero
                Cell cell2= new Cell().add(paragraph2).setTextAlignment(TextAlignment.CENTER).setPadding(0.1f); //estab en 9,7 parece buena

                hasmpaDevolver.put(i+"name",cell1);
                hasmpaDevolver.put(i+"value",cell2);
            }

        }



        return hasmpaDevolver;

    }


    public static Table generateTablePRODUCTSPOSTO (ProductPostCosecha product){

      Table table= new Table(4);
      Cell cell;
      Paragraph paragraph1;

      int contadorProductsPostCosecha =0;


        if(!product.bromorux.trim().isEmpty()){

            paragraph1 = new Paragraph("BROMORUX").setFontSize(6.8f).setFont(font).setBold() //producto
                    .setPaddingTop(0f).setPaddingBottom(0f);
            cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
            table.addCell(cell);


            paragraph1 = new Paragraph(product.bromorux).setFontSize(6.8f).setFont(font).setBold() //cantidad
                    .setPaddingTop(0f).setPaddingBottom(0f);
            cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
            table.addCell(cell);

            contadorProductsPostCosecha++;
        }


        ///si contienen data
            if(!product.acido_citrico.trim().isEmpty()){

                 paragraph1 = new Paragraph("ACIDO CITRICO").setFontSize(6.8f).setFont(font).setBold() //producto
                        .setPaddingTop(0f).setPaddingBottom(0f);
                  cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
                  table.addCell(cell);


                paragraph1 = new Paragraph(product.acido_citrico).setFontSize(6.8f).setFont(font).setBold()  //cantidad
                        .setPaddingTop(0f).setPaddingBottom(0f);
                cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
                table.addCell(cell);
                contadorProductsPostCosecha++;

            }

            if(!product.alumbre.trim().isEmpty()){
                paragraph1 = new Paragraph("ALUMBRE").setFontSize(6.8f).setFont(font).setBold()  //prodcuto
                        .setPaddingTop(0f).setPaddingBottom(0f);
                cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
                table.addCell(cell);


                paragraph1 = new Paragraph(product.alumbre).setFontSize(6.8f).setFont(font).setBold()  //cantidad
                        .setPaddingTop(0f).setPaddingBottom(0f);
                cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
                table.addCell(cell);

                contadorProductsPostCosecha++;

            }

            if(!product.bc100.trim().isEmpty())
            {

                paragraph1 = new Paragraph("BC100").setFontSize(6.8f).setFont(font).setBold()  //prodcuto
                        .setPaddingTop(0f).setPaddingBottom(0f);
                cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
                table.addCell(cell);


                paragraph1 = new Paragraph(product.bc100).setFontSize(6.8f).setFont(font).setBold()  //cantidad
                        .setPaddingTop(0f).setPaddingBottom(0f);
                cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
                table.addCell(cell);

                contadorProductsPostCosecha++;


            }


            if(!product.biottol.trim().isEmpty()){
                paragraph1 = new Paragraph("BIOTTOL").setFontSize(6.8f).setFont(font).setBold()  //prodcuto
                        .setPaddingTop(0f).setPaddingBottom(0f);
                cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
                table.addCell(cell);


                paragraph1 = new Paragraph(product.biottol).setFontSize(6.8f).setFont(font).setBold()  //cantidad
                        .setPaddingTop(0f).setPaddingBottom(0f);
                cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
                table.addCell(cell);

                contadorProductsPostCosecha++;

            }



            if(!product.eclipse.trim().isEmpty()){
                paragraph1 = new Paragraph("ECLIPSE").setFontSize(6.8f).setFont(font).setBold()  //prodcuto
                        .setPaddingTop(0f).setPaddingBottom(0f);
                cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
                table.addCell(cell);


                paragraph1 = new Paragraph(product.eclipse).setFontSize(6.8f).setFont(font).setBold()  //cantidad
                        .setPaddingTop(0f).setPaddingBottom(0f);
                cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
                table.addCell(cell);

                contadorProductsPostCosecha++;


            }

            if(!product.gib_bex.trim().isEmpty()){
                paragraph1 = new Paragraph("GIB_BEX").setFontSize(6.8f).setFont(font).setBold()  //prodcuto
                        .setPaddingTop(0f).setPaddingBottom(0f);
                cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
                table.addCell(cell);


                paragraph1 = new Paragraph(product.gib_bex).setFontSize(6.8f).setFont(font).setBold()  //cantidad
                        .setPaddingTop(0f).setPaddingBottom(0f);
                cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
                table.addCell(cell);
                contadorProductsPostCosecha++;

            }

            if(!product.mertec.trim().isEmpty()){
                paragraph1 = new Paragraph("MERTEC").setFontSize(6.8f).setFont(font).setBold()  //prodcuto
                        .setPaddingTop(0f).setPaddingBottom(0f);
                cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
                table.addCell(cell);


                paragraph1 = new Paragraph(product.mertec).setFontSize(6.8f).setFont(font).setBold()  //cantidad
                        .setPaddingTop(0f).setPaddingBottom(0f);
                cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
                table.addCell(cell);
                contadorProductsPostCosecha++;

            }

            if(!product.nlarge.trim().isEmpty()){
                paragraph1 = new Paragraph("NLARGE").setFontSize(6.8f).setFont(font).setBold()  //prodcuto
                        .setPaddingTop(0f).setPaddingBottom(0f);
                cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
                table.addCell(cell);


                paragraph1 = new Paragraph(product.nlarge).setFontSize(6.8f).setFont(font).setBold()  //cantidad
                        .setPaddingTop(0f).setPaddingBottom(0f);
                cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
                table.addCell(cell);
                contadorProductsPostCosecha++;

            }

            if(!product.otro_especifique.trim().isEmpty()){
                paragraph1 = new Paragraph(product.otro_especifique).setFontSize(6.8f).setFont(font).setBold()  //prodcuto
                        .setPaddingTop(0f).setPaddingBottom(0f);
                cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
                table.addCell(cell);

                Log.i("numproducts","el otro cantidad es  "+product.cantidadOtro);
                Log.i("numproducts","el otro nomnbre  es  "+product.cantidadOtro);

                paragraph1 = new Paragraph(product.cantidadOtro).setFontSize(6.8f).setFont(font).setBold()  //cantidad
                        .setPaddingTop(0f).setPaddingBottom(0f);
                cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
                table.addCell(cell);


                contadorProductsPostCosecha++;

            }

            if(!product.ryzuc.trim().isEmpty()){
                paragraph1 = new Paragraph("RYZUX").setFontSize(6.8f).setFont(font).setBold()  //prodcuto
                        .setPaddingTop(0f).setPaddingBottom(0f);
                cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
                table.addCell(cell);


                paragraph1 = new Paragraph(product.ryzuc).setFontSize(6.8f).setFont(font).setBold()  //cantidad
                        .setPaddingTop(0f).setPaddingBottom(0f);
                cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
                table.addCell(cell);

                contadorProductsPostCosecha++;

            }

            if(!product.sastifar.trim().isEmpty()){
                paragraph1 = new Paragraph("SASTISFARR").setFontSize(6.8f).setFont(font).setBold()  //prodcuto
                        .setPaddingTop(0f).setPaddingBottom(0f);
                cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
                table.addCell(cell);


                paragraph1 = new Paragraph(product.sastifar).setFontSize(6.8f).setFont(font).setBold()  //cantidad
                        .setPaddingTop(0f).setPaddingBottom(0f);
                cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
                table.addCell(cell);

                contadorProductsPostCosecha++;

            }

            if(!product.xtrata.trim().isEmpty()){
                paragraph1 = new Paragraph("XTRATA").setFontSize(6.8f).setFont(font).setBold()  //prodcuto
                        .setPaddingTop(0f).setPaddingBottom(0f);
                cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
                table.addCell(cell);


                paragraph1 = new Paragraph(product.xtrata).setFontSize(6.8f).setFont(font).setBold()  //cantidad
                        .setPaddingTop(0f).setPaddingBottom(0f);
                cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
                table.addCell(cell);

                contadorProductsPostCosecha++;

            }

            if(!product.sb100.trim().isEmpty()){
                paragraph1 = new Paragraph("SB100").setFontSize(6.8f).setFont(font).setBold()  //prodcuto
                        .setPaddingTop(0f).setPaddingBottom(0f);
                cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
                table.addCell(cell);


                paragraph1 = new Paragraph(product.sb100).setFontSize(6.8f).setFont(font).setBold()  //cantidad
                        .setPaddingTop(0f).setPaddingBottom(0f);
                cell= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(9f).setPadding(0);
                table.addCell(cell);
                contadorProductsPostCosecha++;

            }

if(contadorProductsPostCosecha>10){
    hayMasDe10products=true;
   // Toast.makeText(context, "Hay mas de 10 productos, SE ADMITE MAXIMO 10", Toast.LENGTH_LONG).show();
}else{

    hayMasDe10products=false;


}
        return table;

    }

    public static Table generateTablePRODUCTSPOSTOMas4pRODUCTS(ProductPostCosecha product){

        Table table= new Table(1);
        Cell cell;
        Paragraph paragraph1;

        int contadorProductsPostCosecha =0;

        String productosPostcosecha="";

        if(!product.bromorux.trim().isEmpty()){


            contadorProductsPostCosecha++;


            productosPostcosecha=productosPostcosecha+product.bromorux+" BROMORUX"+" - ";


        }


        ///si contienen data
        if(!product.acido_citrico.trim().isEmpty()){


            contadorProductsPostCosecha++;

            productosPostcosecha=productosPostcosecha+product.acido_citrico+" ACIDO CITRICO"+" - ";

        }

        if(!product.alumbre.trim().isEmpty()){

            contadorProductsPostCosecha++;

            productosPostcosecha=productosPostcosecha+product.alumbre+" ALUMBRE"+" - ";


        }

        if(!product.bc100.trim().isEmpty())
        {


            contadorProductsPostCosecha++;

            productosPostcosecha=productosPostcosecha+product.bc100+" BC100"+" - ";



        }


        if(!product.biottol.trim().isEmpty()){


            contadorProductsPostCosecha++;


            productosPostcosecha=productosPostcosecha+product.biottol+" BIOTTOL"+" - ";


        }



        if(!product.eclipse.trim().isEmpty()){


            contadorProductsPostCosecha++;

            productosPostcosecha=productosPostcosecha+product.eclipse+" ECLIPSE"+" - ";


        }

        if(!product.gib_bex.trim().isEmpty()){

            contadorProductsPostCosecha++;


            productosPostcosecha=productosPostcosecha+product.gib_bex+" GIB_BEX"+" - ";


        }

        if(!product.mertec.trim().isEmpty()){

            contadorProductsPostCosecha++;

            productosPostcosecha=productosPostcosecha+product.mertec+" MERTEC"+" - ";


        }

        if(!product.nlarge.trim().isEmpty()){

            contadorProductsPostCosecha++;

            productosPostcosecha=productosPostcosecha+product.nlarge+" NLARGE"+" - ";

        }

        if(!product.otro_especifique.trim().isEmpty()){

            contadorProductsPostCosecha++;

            productosPostcosecha=productosPostcosecha+product.cantidadOtro+product.otro_especifique+" - ";


        }

        if(!product.ryzuc.trim().isEmpty()){

            contadorProductsPostCosecha++;

            productosPostcosecha=productosPostcosecha+product.ryzuc+" RYZUX"+" - ";


        }

        if(!product.sastifar.trim().isEmpty()){

            contadorProductsPostCosecha++;


            productosPostcosecha=productosPostcosecha+product.sastifar+" SASTISFARR"+" - ";


        }

        if(!product.xtrata.trim().isEmpty()){

            contadorProductsPostCosecha++;

            productosPostcosecha=productosPostcosecha+product.xtrata+" XTRATA"+" - ";


        }

        if(!product.sb100.trim().isEmpty()){

            contadorProductsPostCosecha++;


            productosPostcosecha=productosPostcosecha+product.sb100+" SB100";


        }

        if(contadorProductsPostCosecha>10){
            hayMasDe10products=true;
          //  Toast.makeText(context, "Hay mas de 10 productos, SE ADMITE MAXIMO 10", Toast.LENGTH_LONG).show();
        }else{

            hayMasDe10products=false;


        }


        paragraph1= new Paragraph(productosPostcosecha).setFontSize(8f).setFont(font).setPaddingTop(0f).setPaddingBottom(0f);

        cell= new Cell().setPadding(0.3f).add(paragraph1).setTextAlignment(TextAlignment.CENTER);

        table.addCell(cell);

        return table;

    }




    /**para el cuadrto 1*/
    public static  ArrayList<NameAndValue> generaDataToTable(SetInformEmbarque1 Object1,
                                                             SetInformEmbarque2 Object2, SetInformDatsHacienda Object3,
                                                             int tableInfo, ProductPostCosecha product){
        ArrayList<NameAndValue> listTOrETURN1 = new ArrayList<>();

        if(tableInfo==1){

            listTOrETURN1.add(new NameAndValue("SEMANA "+Object1.getSemana() ,"FECHA: "+Object1.getSimpleDataFormat()));
            listTOrETURN1.add(new NameAndValue("PRODUCTOR ",Object1.getProductor()));
            listTOrETURN1.add(new NameAndValue("HACIENDA ",Object1.getHacienda()));
            listTOrETURN1.add(new NameAndValue("CÓDIGO PRODUCTOR ",Object1.getCodigo()));
            listTOrETURN1.add(new NameAndValue("CÓDIGO MAGAP ",Object1.getInscirpMagap()));
            listTOrETURN1.add(new NameAndValue("PUERTO EMBARQUE ",Object1.getPemarque()));
            listTOrETURN1.add(new NameAndValue("ZONA",Object1.getZona()));



            String [] arrayTime=Object1.getHoraInicio().split(":");

            if(arrayTime[0].length()==1){
                listTOrETURN1.add(new NameAndValue("HORA INICIO","0"+Object1.getHoraInicio()));
            }

            else{
                listTOrETURN1.add(new NameAndValue("HORA INICIO",Object1.getHoraInicio()));
            }




             arrayTime=Object1.getHoraTermino().split(":");

            if(arrayTime[0].length()==1){
                listTOrETURN1.add(new NameAndValue("HORA TERMINO","0"+Object1.getHoraTermino()));
            }

            else{
                listTOrETURN1.add(new NameAndValue("HORA TERMINO",Object1.getHoraTermino()));
            }







            listTOrETURN1.add(new NameAndValue("GUÍA REMISIÓN",Object1.getNguiaRemision()));
            listTOrETURN1.add(new NameAndValue("GUÍA DE TRANSPORTE",Object1.get_nguia_transporte()));
            listTOrETURN1.add(new NameAndValue("TARJA DE EMBARQUE",Object1.getNtargetaEmbarque()));

            listTOrETURN1.add(new NameAndValue("HOJA DE EVALUACIÓN",String.valueOf(Object1.getEdiNhojaEvaluacion())));

        }


        else   if(tableInfo==2){ /**la segunda tabla productos postocosecha*/
            ///si contienen data

            if(!product.bromorux.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("BROMORUX",product.bromorux));
            }

            if(!product.acido_citrico.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("ACIDO CITRICO",product.acido_citrico));
            }

            if(!product.alumbre.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("ALUMBRE",product.alumbre));
            }

            if(!product.bc100.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("BC100",product.bc100));
            }

            if(!product.biottol.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("BIOTTOL",product.biottol));
            }

            if(!product.eclipse.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("ECLIPSE",product.eclipse));
            }

            if(!product.gib_bex.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("GIB_BEX",product.gib_bex));
            }

            if(!product.mertec.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("MERTEC",product.mertec));
            }

            if(!product.nlarge.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("NLARGE",product.nlarge));

            }

            if(!product.otro_especifique.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue(product.otro_especifique,product.cantidadOtro));
            }

            if(!product.ryzuc.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("RYZUX",product.ryzuc));
            }

            if(!product.sastifar.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("SASTISFARR",product.sastifar));
            }

            if(!product.xtrata.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("XTRATA",product.xtrata));
            }

            if(!product.sb100.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("SB100",product.sb100));
            }

        }


        else   if(tableInfo==3){ /**la 3ra tabla datos contenedor*/////vamos con la tercera tabla...
            listTOrETURN1.add(new NameAndValue("DESTINO",Object1.getDestinoContenedor()));
            listTOrETURN1.add(new NameAndValue("VAPOR",Object1.getVapor()));
            listTOrETURN1.add(new NameAndValue("NUMERACIÓN DE CONTENEDOR",Object1.getNumcionContenedor()));



            String [] arrayTime=Object1.getHoraLlegadaContenedor().split(":");

            if(arrayTime[0].length()==1){
                listTOrETURN1.add(new NameAndValue("HORA DE LLEGADA","0"+Object1.getHoraLlegadaContenedor()));
            }

            else{
                listTOrETURN1.add(new NameAndValue("HORA DE LLEGADA",Object1.getHoraLlegadaContenedor()));
            }



             arrayTime=Object1.getHoraSalidadContenedor().split(":");

            if(arrayTime[0].length()==1){
                listTOrETURN1.add(new NameAndValue("HORA SALIDA","0"+Object1.getHoraSalidadContenedor()));
            }

            else{
            listTOrETURN1.add(new NameAndValue("HORA SALIDA",Object1.getHoraSalidadContenedor()));
            }


        }else if(tableInfo==4){/**sellos llegada*/

            listTOrETURN1.add(new NameAndValue("SELLO PLÁSTICO NAVIERA",Object1.getSelloPlasticoNaviera()));
            listTOrETURN1.add(new NameAndValue("STICKER DE PATIO VENTOLERA EXTERNA",Object1.getStickerVentoExtern()));
            listTOrETURN1.add(new NameAndValue("NÚMERO DE SERIE DE FUNDA",Object1.getnSerieFunda()));
            listTOrETURN1.add(new NameAndValue("CABLE DE RASTREO LLEGADA",Object1.getCableRastreoLlegada()));
            listTOrETURN1.add(new NameAndValue("BOOKING",Object1.getBooking()));
            listTOrETURN1.add(new NameAndValue("MAX GROSS",Object1.getMaxGross()+"KG"));
            listTOrETURN1.add(new NameAndValue("TARE",Object1.getTare()+"KG"));


        }else if(tableInfo==5){ //SELLOS INSTALADOS

            if(!Object2.getTermografo1().trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("TERMÓGRAFO 1",Object2.getTermografo1()));

                //
                if(!Object2.getUbicacionPalletN1().trim().isEmpty()){

                    listTOrETURN1.add(new NameAndValue("HORA DE ENCEND:" +Object2.getTermografo1HoraEncendido(),"UBICACIÓN PALLET: "+Object2.getUbicacionPalletN1()));
                }

                else if(!Object2.getRumaPalletN1().trim().isEmpty()){

                    listTOrETURN1.add(new NameAndValue("HORA DE ENCEND:" +Object2.getTermografo1HoraEncendido(),"RUMA/PALLET: "+Object2.getRumaPalletN1()));
                }
                else{

                    listTOrETURN1.add(new NameAndValue("HORA DE ENCEND:" +Object2.getTermografo1HoraEncendido(),"UBICACIÓN PALLET: "+Object2.getUbicacionPalletN1()));


                }


            }



            if(!Object2.getTermografo2().trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("TERMÓGRAFO 2",Object2.getTermografo2()));

                if(!Object2.getUbicacionPalletN2().trim().isEmpty()){

                    listTOrETURN1.add(new NameAndValue("HORA DE ENCEND:" +Object2.getTermografo2HoraEncendido(),"UBICACIÓN PALLET: "+Object2.getUbicacionPalletN2()));
                }

                else if(!Object2.getRumaPalletN2().trim().isEmpty()){

                    listTOrETURN1.add(new NameAndValue("HORA DE ENCEND:" +Object2.getTermografo2HoraEncendido(),"RUMA/PALLET: "+Object2.getRumaPalletN2()));
                }
                else{

                    listTOrETURN1.add(new NameAndValue("HORA DE ENCEND:" +Object2.getTermografo2HoraEncendido(),"UBICACIÓN PALLET: "+Object2.getUbicacionPalletN2()));

                }


            }

            listTOrETURN1.add(new NameAndValue("CANDADO QSERCON",Object2.getCandadoQsercom()));
            listTOrETURN1.add(new NameAndValue("CABLE NAVIERA",Object2.getCableNaviera()));

            listTOrETURN1.add(new NameAndValue("SELLO PLÁSTICO",Object2.getSelloPlastico()));
            listTOrETURN1.add(new NameAndValue("SELLO BOTELLA",Object2.getCandadoBotella()));
            listTOrETURN1.add(new NameAndValue("CABLE EXPORTADORA",Object2.getCableExportadora()));
            listTOrETURN1.add(new NameAndValue("SELLO ADHESIVO EXPORTADORA",Object2.getSelloAdhesivoExportadora()));

            listTOrETURN1.add(new NameAndValue("SELLO ADHESIVO NAVIERA",Object2.getSelloAdhesivoNaviera()));
            listTOrETURN1.add(new NameAndValue("OTROS SELLOS ",Object2.getOtrosSellosEspecif()));


        }else if(tableInfo==6){/**datos transportista */

            listTOrETURN1.add(new NameAndValue("COMPAÑIA TRANSPORTISTA ",Object2.getCompaniaTranporte()));
            listTOrETURN1.add(new NameAndValue("NOMBRE CHOFER ",Object2.getNombreChofer()));
            listTOrETURN1.add(new NameAndValue("CÉDULA  ",Object2.getCedulaChofer()));
            listTOrETURN1.add(new NameAndValue("CELULAR ",Object2.getCelularChofer()));
            listTOrETURN1.add(new NameAndValue("PLACA ",Object2.getPlacaChofer()));
            listTOrETURN1.add(new NameAndValue("COLOR CABEZAL ",Object2.getColorCAbezal()));



        }  ///falta datos de proceso yPosicion otro



        else if(tableInfo==7){/**datos De proceso */



            // listTOrETURN1.add(new NameAndValue("",Object2.));



        }
        else if(tableInfo==8){/**CONTROL DE GANCHO*/

       // por aquiracimos rechzados


            listTOrETURN1.add(new NameAndValue("CAJAS PROCESADAS DESPACHADAS",Object3.getEdiCajasProcDesp()));
            listTOrETURN1.add(new NameAndValue("RACIMOS COSECHADOS ",Object3.getEdiRacimosCosech()));
            listTOrETURN1.add(new NameAndValue("RACIMOS RECHAZADOS ",Object3.getEdiRacimosRecha()));
            listTOrETURN1.add(new NameAndValue("RACIMOS PROCESADOS ",Object3.getEdiRacimProces()));

        }







        return listTOrETURN1;

    }


    public static  ArrayList<NameAndValue> generaDataToTable(ReportCamionesyCarretas Object1,
                                                             int tableInfo, ProductPostCosecha product){
        ArrayList<NameAndValue> listTOrETURN1 = new ArrayList<>();

        if(tableInfo==1){

            listTOrETURN1.add(new NameAndValue("SEMANA "+Object1.getSemana() ,"FECHA: "+Object1.getSimpleDataFormat()));
            listTOrETURN1.add(new NameAndValue("PRODUCTOR ",Object1.getProductor()));
            listTOrETURN1.add(new NameAndValue("HACIENDA ",Object1.getHacienda()));
            listTOrETURN1.add(new NameAndValue("CÓDIGO PRODUCTOR ",Object1.getCodigo()));
            listTOrETURN1.add(new NameAndValue("CÓDIGO MAGAP ",Object1.getInscirpMagap()));
            listTOrETURN1.add(new NameAndValue("PUERTO EMBARQUE ",Object1.getPemarque()));
            listTOrETURN1.add(new NameAndValue("ZONA",Object1.getZona()));



            String [] arrayTime=Object1.getHoraInicio().split(":");

            if(arrayTime[0].length()==1){
                listTOrETURN1.add(new NameAndValue("HORA INICIO","0"+Object1.getHoraInicio()));
            }

            else{
                listTOrETURN1.add(new NameAndValue("HORA INICIO",Object1.getHoraInicio()));
            }




            arrayTime=Object1.getHoraTermino().split(":");

            if(arrayTime[0].length()==1){
                listTOrETURN1.add(new NameAndValue("HORA TERMINO","0"+Object1.getHoraTermino()));
            }

            else{
                listTOrETURN1.add(new NameAndValue("HORA TERMINO",Object1.getHoraTermino()));
            }







            listTOrETURN1.add(new NameAndValue("GUÍA REMISIÓN",Object1.getNguiaRemision()));
            listTOrETURN1.add(new NameAndValue("GUÍA DE TRANSPORTE",Object1.get_nguia_transporte()));
            listTOrETURN1.add(new NameAndValue("TARJA DE EMBARQUE",Object1.getNtargetaEmbarque()));

            listTOrETURN1.add(new NameAndValue("HOJA DE EVALUACIÓN",String.valueOf(Object1.getEdiNhojaEvaluacion())));

        }


        else   if(tableInfo==2){ /**la segunda tabla productos postocosecha*/
            ///si contienen data

            if(!product.bromorux.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("BROMORUX",product.bromorux));
            }

            if(!product.acido_citrico.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("ACIDO CITRICO",product.acido_citrico));
            }

            if(!product.alumbre.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("ALUMBRE",product.alumbre));
            }

            if(!product.bc100.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("BC100",product.bc100));
            }

            if(!product.biottol.trim().isEmpty()){

                Log.i("prodcutrrr","el biotol es "+product.biottol);

                listTOrETURN1.add(new NameAndValue("BIOTTOL",product.biottol));
            }

            if(!product.eclipse.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("ECLIPSE",product.eclipse));
            }

            if(!product.gib_bex.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("GIB_BEX",product.gib_bex));
            }

            if(!product.mertec.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("MERTEC",product.mertec));
            }

            if(!product.nlarge.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("NLARGE",product.nlarge));

            }

            if(!product.otro_especifique.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue(product.otro_especifique,product.cantidadOtro));
            }

            if(!product.ryzuc.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("RYZUX",product.ryzuc));
            }

            if(!product.sastifar.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("SASTISFARR",product.sastifar));
            }

            if(!product.xtrata.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("XTRATA",product.xtrata));
            }

            if(!product.sb100.trim().isEmpty()){
                listTOrETURN1.add(new NameAndValue("SB100",product.sb100));
            }

        }


        else if(tableInfo==6){/**datos transportista */

         //   listTOrETURN1.add(new NameAndValue("COMPAÑIA TRANSPORTISTA ",Object1.getce()));
            listTOrETURN1.add(new NameAndValue("NOMBRE CHOFER ",Object1.getNombredeChofer()));
            listTOrETURN1.add(new NameAndValue("CÉDULA  ",Object1.getCedula()));
            listTOrETURN1.add(new NameAndValue("CELULAR ",Object1.getCelular()));
            listTOrETURN1.add(new NameAndValue("PLACA ",Object1.getPlaca()));

            listTOrETURN1.add(new NameAndValue("CANDADO/S QSERCON ",generaCandadosQsercon(Object1)));



        }  ///falta datos de proceso yPosicion otro


        else if(tableInfo==8){/**CONTROL DE GANCHO*/

            // por aquiracimos rechzados


            listTOrETURN1.add(new NameAndValue("CAJAS PROCESADAS DESPACHADAS",String.valueOf(Object1.getCajasProcesadasDespachadas() )));
            listTOrETURN1.add(new NameAndValue("RACIMOS COSECHADOS ",String.valueOf(Object1.getRacimosCosechados())));
            listTOrETURN1.add(new NameAndValue("RACIMOS RECHAZADOS ",String.valueOf(Object1.getRacimosRechazados())));
            listTOrETURN1.add(new NameAndValue("RACIMOS PROCESADOS ",String.valueOf(Object1.getRacimosProcesados())));

        }




        return listTOrETURN1;

    }




    public static Table createTable2(Table miTable, DeviceRgb rgbColor,SetInformEmbarque2 object2) {
        Cell cellTipPlastic= new Cell().setPaddingLeft(10f);
        Cell cellPolitubo= new Cell().setPaddingLeft(10f);
        Cell cellPolituboX= new Cell().setPaddingLeft(10f); //x

        Cell cellPolipack= new Cell().setPaddingLeft(10f);
        Cell cellPolipackX= new Cell().setPaddingLeft(10f);

        Cell cellbanavac= new Cell().setPaddingLeft(10f);
        Cell cellbanavacX= new Cell().setPaddingLeft(10f);

        Cell cellTipPlasbagS= new Cell().setPaddingLeft(10f);
        Cell cellTipPlasbagX= new Cell().setPaddingLeft(10f);

        Cell celltipodEcAJA= new Cell().setPaddingLeft(10f).setBorderBottom(Border.NO_BORDER);

        Cell cell22Xu= new Cell().setPaddingLeft(10f).setBorderBottom(Border.NO_BORDER);
        Cell cell22XuX= new Cell().setPaddingLeft(10f).setBorderBottom(Border.NO_BORDER);

        Cell cellDisplay= new Cell().setPaddingLeft(10f).setBorderBottom(Border.NO_BORDER);
        Cell cellDisplayX= new Cell().setPaddingLeft(10f).setBorderBottom(Border.NO_BORDER);

        Cell cell13kg= new Cell().setPaddingLeft(10f).setBorderBottom(Border.NO_BORDER);
        Cell cell13kgX= new Cell().setPaddingLeft(10f).setBorderBottom(Border.NO_BORDER);

        Cell cell208= new Cell().setPaddingLeft(10f).setBorderBottom(Border.NO_BORDER);
        Cell cell208X= new Cell().setPaddingLeft(10f).setBorderBottom(Border.NO_BORDER);



        cellPolituboX.setBackgroundColor(rgbColor) ; //MNARCAR LA X CON BACVGROUND VERDE
        cellPolipackX.setBackgroundColor(rgbColor);
        cellbanavacX.setBackgroundColor(rgbColor);
        cellTipPlasbagX.setBackgroundColor(rgbColor);


        //FALTA AGREGAR A LAS TABLAS
        miTable.addCell(cellTipPlastic.add(new Paragraph("TIPO DE PLÁSTICO").setFontSize(7.3f).setFont(font)).setBold()) ;
        miTable.addCell(cellPolitubo.add(new Paragraph("POLITUBO").setFontSize(7.3f).setFont(font))) ;
        //tipo de caja


        if(object2.getTipoPlastico().equalsIgnoreCase("Politubo")) {
            cellPolituboX.add(new Paragraph(" X ").setFontSize(6.5f).setFont(font));

        }else{

            cellPolituboX.add(new Paragraph("   ").setWidth(10));


        }
        miTable.addCell(cellPolituboX);



        miTable.addCell(cellPolipack.add(new Paragraph("POLIPACK").setFont(font).setFontSize(7.3f))) ;

        if(object2.getTipoPlastico().equalsIgnoreCase("Polipack")) {
            cellPolipackX.add(new Paragraph(" X ").setFontSize(6.5f));

        }else{
            cellPolipackX.add(new Paragraph("  ").setWidth(10));

        }
        miTable.addCell(cellPolipackX);



        miTable.addCell(cellbanavac.add(new Paragraph("BANAVAC").setFontSize(7.3f).setFont(font))) ;

        if(object2.getTipoPlastico().equalsIgnoreCase("Banavac")) {
            cellbanavacX.add(new Paragraph(" X ").setFontSize(6.5f));

        }

        else{

            cellbanavacX.add(new Paragraph("  ").setWidth(10));

        }

        miTable.addCell(cellbanavacX);


        // miTable.addCell(cellbanavac.add(new Paragraph("BANAVAC").setFontSize(6.5f))) ;


        miTable.addCell(cellTipPlasbagS.add(new Paragraph("BAG").setFontSize(7.3f).setFont(font))) ;

        if(object2.getTipoPlastico().equalsIgnoreCase("BAG")) {
            cellTipPlasbagX.add(new Paragraph("  X ").setFontSize(6.5f));

        }else{

            cellTipPlasbagX.add(new Paragraph("  ").setWidth(10));

        }
        miTable.addCell(cellTipPlasbagX);



        /**tipo de cja */

        miTable.addCell(celltipodEcAJA.add(new Paragraph("TIPO DE CAJA").setFontSize(7.3f).setFont(font).setBold())) ;
        miTable.addCell(cell22Xu.add(new Paragraph("22xU").setFontSize(6.5f).setFont(font))) ;


        cell22XuX.setBackgroundColor(rgbColor) ; //MNARCAR LA X CON BACVGROUND VERDE
        cellDisplayX.setBackgroundColor(rgbColor);
        cell13kgX.setBackgroundColor(rgbColor);
        cell208X.setBackgroundColor(rgbColor);


        if(object2.getTipoCaja().equalsIgnoreCase("22xu")) {
            cell22XuX.add(new Paragraph(" X ").setFontSize(7.3f));
        }else{
            cell22XuX.add(new Paragraph("  ").setWidth(10));

        }


        miTable.addCell(cell22XuX) ;


        miTable.addCell(cellDisplay.add(new Paragraph("DISPLAY").setFontSize(7.3f).setFont(font))) ;


        if(object2.getTipoCaja().equalsIgnoreCase("Display")) {
            cellDisplayX.add(new Paragraph(" X ").setFontSize(6.5f));


        }else{
            cellDisplayX.add(new Paragraph("  ").setWidth(10));

        }


        miTable.addCell(cellDisplayX) ;



        miTable.addCell(cell13kg.add(new Paragraph("13KG").setFontSize(7.3f).setFont(font))) ;

        if(object2.getTipoCaja().equalsIgnoreCase("13KG")) {
            cell13kgX.add(new Paragraph(" X ").setFontSize(6.5f));

        }else{

            cell13kgX.add(new Paragraph("  ").setWidth(10));

        }
        miTable.addCell(cell13kgX) ;



        //aqui agregamos 3 opciones incluida la

        if(object2.getTipoCaja().equalsIgnoreCase("9,5kg")){
            miTable.addCell(cell13kg.add(new Paragraph("9,5KG").setFontSize(7.3f).setFont(font))) ;
            cell13kgX.add(new Paragraph(" X ").setFontSize(6.5f));


        }

        else{

            miTable.addCell(cell208.add(new Paragraph("208").setFontSize(7.3f).setFont(font))) ;

            if(object2.getTipoCaja().equalsIgnoreCase("208")) {
                cell208X.add(new Paragraph(" X ").setFontSize(6.5f));

            }else{
                cell208X.add(new Paragraph("  ").setWidth(10));


            }
        }


        miTable.setBorderBottom(Border.NO_BORDER);

        miTable.addCell(cell208X) ;







        return miTable;

    }

    public static Table createTable2(Table miTable, DeviceRgb rgbColor, ReportCamionesyCarretas object2) {
        /*    Paragraph paragraph1 = new Paragraph(list.get(i).getNameFields()).setFontSize(8.5f).setPaddingLeft(10f).setFont(font).
                        .setPaddingTop(0f).setPaddingBottom(0f);
                cell1= new Cell().setPadding(0.1f).add(paragraph1);*/


        Cell cellTipPlastic= new Cell().setPaddingLeft(10f).setBackgroundColor(rgbColor).setPadding(0.1f);
        Cell cellPolitubo= new Cell().setPaddingLeft(10f).setBackgroundColor(rgbColor).setPadding(0.1f);
        Cell cellPolituboX= new Cell().setPaddingLeft(10f).setPadding(0.1f); //x

        Cell cellPolipack= new Cell().setPaddingLeft(10f).setBackgroundColor(rgbColor).setPadding(0.1f);
        Cell cellPolipackX= new Cell().setPaddingLeft(10f).setPadding(0.1f);

        Cell cellbanavac= new Cell().setPaddingLeft(10f).setBackgroundColor(rgbColor).setPadding(0.1f);
        Cell cellbanavacX= new Cell().setPaddingLeft(10f).setPadding(0.1f);

        Cell cellTipPlasbagS= new Cell().setPaddingLeft(10f).setBackgroundColor(rgbColor).setPadding(0.1f);
        Cell cellTipPlasbagX= new Cell().setPaddingLeft(10f).setPadding(0.1f);

        Cell celltipodEcAJA= new Cell().setPaddingLeft(10f).setBorderBottom(Border.NO_BORDER).setBackgroundColor(rgbColor).setPadding(0.1f);

        Cell cell22Xu= new Cell().setPaddingLeft(10f).setBorderBottom(Border.NO_BORDER).setBackgroundColor(rgbColor).setPadding(0.1f);
        Cell cell22XuX= new Cell().setPaddingLeft(10f).setBorderBottom(Border.NO_BORDER).setPadding(0.1f);

        Cell cellDisplay= new Cell().setPaddingLeft(10f).setBorderBottom(Border.NO_BORDER).setBackgroundColor(rgbColor).setPadding(0.1f);
        Cell cellDisplayX= new Cell().setPaddingLeft(10f).setBorderBottom(Border.NO_BORDER).setPadding(0.1f);

        Cell cell13kg= new Cell().setPaddingLeft(10f).setBorderBottom(Border.NO_BORDER).setBackgroundColor(rgbColor).setPadding(0.1f);
        Cell cell13kgX= new Cell().setPaddingLeft(10f).setBorderBottom(Border.NO_BORDER).setPadding(0.1f);

        Cell cell208= new Cell().setPaddingLeft(10f).setBorderBottom(Border.NO_BORDER).setBackgroundColor(rgbColor).setPadding(0.1f);
        Cell cell208X= new Cell().setPaddingLeft(10f).setBorderBottom(Border.NO_BORDER).setPadding(0.1f);

        //FALTA AGREGAR A LAS TABLAS
        miTable.addCell(cellTipPlastic.add(new Paragraph("TIPO DE PLÁSTICO").setPaddingLeft(10f).setPaddingTop(0f).setPaddingBottom(0f).setFontSize(8.5f).setFont(font)).setBold()) ;
        miTable.addCell(cellPolitubo.add(new Paragraph("POLITUBO").setPaddingLeft(10f).setPaddingTop(0f).setPaddingBottom(0f).setFontSize(8.5f).setFont(font))) ;
        //tipo de caja


        Log.i("tipoplastico","el tipo pladtico es "+object2.getTipoDePlastico());

        if(object2.getTipoDePlastico().equalsIgnoreCase("Politubo")) {
            cellPolituboX.add(new Paragraph(" X ").setFontSize(7.6f).setFont(font));

        }else{

            cellPolituboX.add(new Paragraph("   ").setWidth(10));


        }
        miTable.addCell(cellPolituboX);



        miTable.addCell(cellPolipack.add(new Paragraph("POLIPACK").setPaddingLeft(10f).setPaddingTop(0f).setPaddingBottom(0f).setFont(font).setFontSize(8.5f))) ;

        if(object2.getTipoDePlastico().equalsIgnoreCase("Polipack")) {
            cellPolipackX.add(new Paragraph(" X ").setFontSize(7.6f));

        }else{
            cellPolipackX.add(new Paragraph("  ").setWidth(10));

        }
        miTable.addCell(cellPolipackX);



        miTable.addCell(cellbanavac.add(new Paragraph("BANAVAC").setPaddingLeft(10f).setPaddingTop(0f).setPaddingBottom(0f).setFontSize(8.5f).setFont(font))) ;

        if(object2.getTipoDePlastico().equalsIgnoreCase("Banavac")) {
            cellbanavacX.add(new Paragraph(" X ").setFontSize(7.6f));

        }

        else{

            cellbanavacX.add(new Paragraph("  ").setWidth(10));

        }

        miTable.addCell(cellbanavacX);


        // miTable.addCell(cellbanavac.add(new Paragraph("BANAVAC").setFontSize(6.5f))) ;


        miTable.addCell(cellTipPlasbagS.add(new Paragraph("BAG").setPaddingLeft(10f).setPaddingTop(0f).setPaddingBottom(0f).setFontSize(8.5f).setFont(font))) ;

        if(object2.getTipoDePlastico().equalsIgnoreCase("BAG")) {
            cellTipPlasbagX.add(new Paragraph("  X ").setFontSize(7.6f));

        }else{

            cellTipPlasbagX.add(new Paragraph("  ").setWidth(10));

        }
        miTable.addCell(cellTipPlasbagX);



        /**tipo de cja */

        miTable.addCell(celltipodEcAJA.add(new Paragraph("TIPO DE CAJA").setPaddingLeft(10f).setPaddingTop(0f).setPaddingBottom(0f).setFontSize(8.5f).setFont(font).setBold())) ;
        miTable.addCell(cell22Xu.add(new Paragraph("22xU").setPaddingLeft(10f).setFontSize(8.5f).setFont(font))) ;


        // cell22XuX.setBackgroundColor(rgbColor) ; //MNARCAR LA X CON BACVGROUND VERDE
        //  cellDisplayX.setBackgroundColor(rgbColor);
        //  cell13kgX.setBackgroundColor(rgbColor);
        //  cell208X.setBackgroundColor(rgbColor);


        if(object2.getTipoDeCaja().equalsIgnoreCase("22xu")) {
            cell22XuX.add(new Paragraph(" X ").setFontSize(7.6f));
        }else{
            cell22XuX.add(new Paragraph("  ").setWidth(10));

        }


        miTable.addCell(cell22XuX) ;


        miTable.addCell(cellDisplay.add(new Paragraph("DISPLAY").setPaddingLeft(10f).setPaddingTop(0f).setPaddingBottom(0f).setFontSize(8.5f).setFont(font))) ;


        if(object2.getTipoDeCaja().equalsIgnoreCase("Display")) {
            cellDisplayX.add(new Paragraph(" X ").setFontSize(7.6f));


        }else{
            cellDisplayX.add(new Paragraph("  ").setWidth(10));

        }


        miTable.addCell(cellDisplayX) ;



        miTable.addCell(cell13kg.add(new Paragraph("13KG").setPaddingLeft(10f).setPaddingTop(0f).setPaddingBottom(0f).setFontSize(8.5f).setFont(font))) ;

        if(object2.getTipoDeCaja().equalsIgnoreCase("13KG")) {
            cell13kgX.add(new Paragraph(" X ").setFontSize(7.6f));

        }else{

            cell13kgX.add(new Paragraph("  ").setWidth(10));

        }
        miTable.addCell(cell13kgX) ;



        //aqui agregamos 3 opciones incluida la

        if(object2.getTipoDeCaja().equalsIgnoreCase("9,5kg")){
            miTable.addCell(cell13kg.add(new Paragraph("9,5KG").setPaddingLeft(10f).setPaddingTop(0f).setPaddingBottom(0f).setFontSize(8.5f).setFont(font))) ;
            cell13kgX.add(new Paragraph(" X ").setFontSize(7.6f));


        }

        else{

            miTable.addCell(cell208.add(new Paragraph("208").setPaddingLeft(10f).setPaddingTop(0f).setPaddingBottom(0f).setFontSize(8.5f).setFont(font))) ;

            if(object2.getTipoDeCaja().equalsIgnoreCase("208")) {
                cell208X.add(new Paragraph(" X ").setFontSize(7.6f));

            }else{
                cell208X.add(new Paragraph("  ").setWidth(10));


            }
        }


        miTable.setBorderBottom(Border.NO_BORDER);

        miTable.addCell(cell208X) ;





        return miTable;

    }


    public static Table createTbale6(Table table1,SetInformDatsHacienda object){
        Cell cellDatosHaciend = new Cell(1,7).setBackgroundColor(rgbColorAzulClaro).add(new Paragraph("DATOS DE HACIENDA").setTextAlignment(TextAlignment.CENTER).setFontSize(8f).setFont(font).setBold());

        Cell micelda;
        Paragraph   paragrapMarcado = new Paragraph(" X ").setFontSize(7f).setFont(font).setTextAlignment(TextAlignment.CENTER);;
        Paragraph   paragragSinMarcar = new Paragraph("  ");;

        table1.addCell(cellDatosHaciend);

        Cell cellx1 = new Cell().add(new Paragraph("FUENTE DE AGUA").setFontSize(7.3f).setFont(font).setPaddingLeft(10f).setBold());
        Cell cellx2 = new Cell().add(new Paragraph("AGUA POTABLE").setFontSize(7.3f).setFont(font).setPaddingLeft(10f));
        table1.addCell(cellx1);
        table1.addCell(cellx2);

        if(object.getFuenteAgua().equalsIgnoreCase("AGUA POTABLE")) {
            micelda = new Cell(1,1).setBackgroundColor(rgbColorVerdeCana).setFontSize(7.3f).add(paragrapMarcado);
            micelda .setWidth(10f);
            table1.addCell(micelda);


        }else{

            micelda = new Cell(1,1).setBackgroundColor(rgbColorVerdeCana).setFontSize(7.3f).add(paragragSinMarcar);
            micelda .setWidth(10f);

            table1.addCell(micelda);


        }

        table1.addCell(new Paragraph("POZO").setFontSize(7.3f).setFont(font).setPaddingLeft(10f));

        Log.i("elaguaes","la fuente de agua es "+object.getFuenteAgua());


        if(object.getFuenteAgua().equalsIgnoreCase("pozo")) {

            Log.i("elaguaes","la fuente de agua es pozo");

            micelda = new Cell(1,1).setBackgroundColor(rgbColorVerdeCana).setFontSize(6.5f).add(paragrapMarcado);
            micelda .setWidth(10f);

            table1.addCell(micelda);
        }
        else{
            Log.i("elaguaes","la fuente de agua no es pozo ");


            micelda = new Cell(1,1).setBackgroundColor(rgbColorVerdeCana).setFontSize(6.5f).add(paragragSinMarcar);
            micelda .setWidth(10f);

            table1.addCell(micelda);
        }

        table1.addCell(new Paragraph("BIDÓN").setFontSize(7.5f).setFont(font).setPaddingLeft(10f));

        if(object.getFuenteAgua().equalsIgnoreCase("BIDÓN")) {

            micelda = new Cell(1,1).setBackgroundColor(rgbColorVerdeCana).setFontSize(6.5f).add(paragrapMarcado);
            micelda .setWidth(10f);

            table1.addCell(micelda);
        }else{

            micelda = new Cell(1,1).setBackgroundColor(rgbColorVerdeCana).setFontSize(6.5f).add(paragragSinMarcar);
            micelda .setWidth(10f);

            table1.addCell(micelda);
        }


        //2DA FILA DE DATOS DE HACIENDA...

        table1.addCell(new Paragraph("AGUA CORRIDA").setFontSize(7.3f).setFont(font).setPaddingLeft(10f).setBold());

        table1.addCell(new Paragraph("SI").setFontSize(7f).setFont(font).setPaddingLeft(10f));


        if(object.isHayAguaCorrida()) {
            //  table1.addCell(new Paragraph(" X").setBackgroundColor(rgbColor).setFontSize(7.5f));

            micelda = new Cell(1,1).setBackgroundColor(rgbColorVerdeCana).add(paragrapMarcado);
            micelda .setWidth(10f);
            table1.addCell(micelda);

        }else{

            micelda = new Cell(1,1).setBackgroundColor(rgbColorVerdeCana).setFontSize(7.5f).add(paragragSinMarcar);
            micelda .setWidth(10f);
            table1.addCell(micelda);

        }

        micelda = new Cell(1,2).add(new Paragraph("NO").setFontSize(7.5f).setFont(font).setPaddingLeft(10f));
       table1.addCell(micelda);

        if(!object.isHayAguaCorrida()) {

            micelda = new Cell(1,2).setBackgroundColor(rgbColorVerdeCana).setFontSize(7.5f).add(paragrapMarcado);
            micelda .setWidth(10f);

            table1.addCell(micelda);
            ///





        }else{

            micelda = new Cell(1,2).setBackgroundColor(rgbColorVerdeCana).setFontSize(7.5f).add(paragragSinMarcar);
            //micelda .setWidth(10f);

            table1.addCell(micelda);
        }

//

        table1.addCell(new Paragraph("LAVADO DE RACIMOS").setFontSize(7.5f).setFont(font).setPaddingLeft(10f).setBold());


        table1.addCell(new Paragraph("SI").setFontSize(7.5f).setFont(font).setPaddingLeft(10f));


        /**tiene 7 columnas*/

        if(object.isHayLavadoRacimos()) {
            //  table1.addCell(new Paragraph(" X").setBackgroundColor(rgbColor));
            micelda = new Cell(1,1).setBackgroundColor(rgbColorVerdeCana).setFontSize(7.5f).add(paragrapMarcado);
            micelda .setWidth(10f);

            table1.addCell(micelda);

        }else{

            micelda = new Cell(1,1).setBackgroundColor(rgbColorVerdeCana).setFontSize(7.5f).add(paragragSinMarcar);
            micelda .setWidth(10f);

            table1.addCell(micelda);
        }

        table1.addCell(new Paragraph("NO").setFontSize(7.5f).setFont(font).setPaddingLeft(10f));

        if(!object.isHayLavadoRacimos()) {
            micelda = new Cell(1,3).setBackgroundColor(rgbColorVerdeCana).setFontSize(7.5f).add(paragrapMarcado);
           // micelda .setWidth(10f);

            table1.addCell(micelda);
        }else{

            micelda = new Cell(1,3).setBackgroundColor(rgbColorVerdeCana).setFontSize(7.5f).add(paragragSinMarcar);
          //  micelda .setWidth(10f);

            table1.addCell(micelda);
        }


        /**fumigacion corona linea `1*/
        table1.addCell(new Paragraph("FUMIGACIÓN CORONA LINEA 1").setFontSize(7.5f).setFont(font).setPaddingLeft(10f).setBold());

        table1.addCell(new Paragraph("FOGGING").setFontSize(7.5f).setFont(font).setPaddingLeft(10f));


        if(object.getFumigacionClin1().equalsIgnoreCase("FOGGING")) {
            micelda = new Cell(1,1).setBackgroundColor(rgbColorVerdeCana).setFontSize(7.5f).add(paragrapMarcado);
            micelda .setWidth(10f);

            table1.addCell(micelda);

        }else{

            micelda = new Cell(1,1).setBackgroundColor(rgbColorVerdeCana).setFontSize(7.5f).add(paragragSinMarcar);
            micelda .setWidth(10f);

            table1.addCell(micelda);

        }


        ////////
        table1.addCell(new Paragraph("BOMBA CP3").setFontSize(7.5f).setFont(font).setPaddingLeft(10f));

/*
        if(object.getFumigacionClin1().equalsIgnoreCase("BOMBA CP3 MANUAL") ) {

            micelda = new Cell(1,1).setBackgroundColor(rgbColorVerdeCana).setFontSize(7.5f).add(paragrapMarcado);
            micelda .setWidth(10f);

            table1.addCell(micelda);


        }else{

            micelda = new Cell(1,1).setBackgroundColor(rgbColorVerdeCana).setFontSize(7.5f).add(paragragSinMarcar);
            micelda .setWidth(10f);

            table1.addCell(micelda);
        }
*/
////////////4



     //   table1.addCell();

        if(object.getFumigacionClin1().equalsIgnoreCase("BOMBA CP3 ELÉCTRICA")) {

            micelda = new Cell(1,3).setBackgroundColor(rgbColorVerdeCana).
                    setFontSize(7.5f).add(new Paragraph("ELÉCTRICA").setFontSize(7.5f).setFont(font).setPaddingLeft(10f));

            micelda .setWidth(10f);
            table1.addCell(micelda);

        }else if(object.getFumigacionClin1().equalsIgnoreCase("BOMBA CP3 MANUAL")) {

            micelda = new Cell(1,3).setBackgroundColor(rgbColorVerdeCana).
                    setFontSize(7.5f).add(new Paragraph("MANUAL").setFontSize(7.5f).setFont(font).setPaddingLeft(10f));

         //   micelda = new Cell(1,1).setBackgroundColor(rgbColorVerdeCana).setFontSize(7.5f).add(paragragSinMarcar);
            micelda .setWidth(10f);

            table1.addCell(micelda);
        }else{
            micelda = new Cell(1,3).setBackgroundColor(rgbColorVerdeCana).setFontSize(7.5f).add(paragragSinMarcar);
            table1.addCell(micelda);


        }



        return table1;
    }

    public static Table createTbale6(ReportCamionesyCarretas object){

        float sizeColumns10[]= {190,1,1,1,1,1,1};
        Table table1=  new Table(sizeColumns10);

/*
        Cell cellDatosHaciend = new Cell(1,7).setBackgroundColor(rgbColorAzulClaro).add(new Paragraph("DATOS DE HACIENDA").
                setTextAlignment(TextAlignment.CENTER).setFontSize(8f).setFont(font).setBold());

        */

        Cell micelda;
        Paragraph   paragrapMarcado = new Paragraph(" X ").setPaddingBottom(0f).setPaddingTop(0f).setFontSize(6.5f).setFont(font).setTextAlignment(TextAlignment.CENTER);;
        Paragraph   paragragSinMarcar = new Paragraph("  ");

        Paragraph paragraph;

        //   table1.addCell(cellDatosHaciend);

        Cell cellx1 = new Cell().setPadding(0.1f).add(new Paragraph("FUENTE DE AGUA").setPaddingBottom(0f).setPaddingTop(0f).setFontSize(8.5f).setFont(font).setPaddingLeft(10f).setBold()).setBackgroundColor(rgbColorPlomoBlanco);
        Cell cellx2 = new Cell().setPadding(0.1f).add(new Paragraph("AGUA POTABLE").setPaddingBottom(0f).setPaddingTop(0f).setFontSize(8.5f).setFont(font).setPaddingLeft(10f).setBackgroundColor(rgbColorPlomoBlanco));
        table1.addCell(cellx1);
        table1.addCell(cellx2);

        if(object.getFuenteAgua().equalsIgnoreCase("AGUA POTABLE")) {
            micelda = new Cell(1,1).setFontSize(8.5f).add(paragrapMarcado);
            micelda .setWidth(10f);
            table1.addCell(micelda);


        }else{

            micelda = new Cell(1,1).setPadding(0.1f).setFontSize(8.5f).add(paragragSinMarcar);
            micelda .setWidth(10f);

            table1.addCell(micelda);


        }


        cellx1= new Cell().setPadding(0.1f);
        paragraph=new Paragraph("POZO").setPaddingBottom(0f).setPaddingTop(0f).setFontSize(8.5f).setFont(font).setPaddingLeft(10f);
        cellx1.add(paragraph).setBackgroundColor(rgbColorPlomoBlanco);


        table1.addCell(cellx1);

        Log.i("elaguaes","la fuente de agua es "+object.getFuenteAgua());


        if(object.getFuenteAgua().equalsIgnoreCase("pozo")) {

            Log.i("elaguaes","la fuente de agua es pozo");

            micelda = new Cell(1,1).setPadding(0.1f).add(paragrapMarcado);
            micelda .setWidth(10f);

            table1.addCell(micelda);
        }
        else{
            Log.i("elaguaes","la fuente de agua no es pozo ");


            micelda = new Cell(1,1).setPadding(0.1f).add(paragragSinMarcar);
            micelda .setWidth(10f);

            table1.addCell(micelda);
        }


        cellx1= new Cell().setPadding(0.1f);
        paragraph=new Paragraph("BIDÓN").setPaddingBottom(0f).setPaddingTop(0f).setFontSize(8.5f).setFont(font).setPaddingLeft(10f);
        cellx1.add(paragraph).setBackgroundColor(rgbColorPlomoBlanco);




        table1.addCell(cellx1);

        if(object.getFuenteAgua().equalsIgnoreCase("BIDÓN")) {

            micelda = new Cell(1,1).setPadding(0.1f).add(paragrapMarcado);
            micelda .setWidth(10f);

            table1.addCell(micelda);
        }else{

            micelda = new Cell(1,1).setPadding(0.1f).add(paragragSinMarcar);
            micelda .setWidth(10f);

            table1.addCell(micelda);
        }


        //2DA FILA DE DATOS DE HACIENDA...
        cellx1= new Cell().setPadding(0.1f);
        paragraph=new Paragraph("AGUA CORRIDA").setPaddingBottom(0f).setPaddingTop(0f).setFontSize(8.5f).setFont(font).setPaddingLeft(10f).setBold();
        cellx1.add(paragraph).setBackgroundColor(rgbColorPlomoBlanco);

        table1.addCell(cellx1);


        cellx1= new Cell().setPadding(0.1f);
        paragraph=new Paragraph("SI").setPaddingBottom(0f).setPaddingTop(0f).setFontSize(8.5f).setFont(font).setPaddingLeft(10f);
        cellx1.add(paragraph).setBackgroundColor(rgbColorPlomoBlanco);


        table1.addCell(cellx1);


        if(object.isHayAguaCorrida()) {
            //  table1.addCell(new Paragraph(" X").setBackgroundColor(rgbColor).setFontSize(8.5f));

            micelda = new Cell(1,1).setPadding(0.1f).add(paragrapMarcado);
            micelda .setWidth(10f);
            table1.addCell(micelda);

        }else{

            micelda = new Cell(1,1).setPadding(0.1f).setFontSize(8.5f).add(paragragSinMarcar);
            micelda .setWidth(10f);
            table1.addCell(micelda);

        }

        micelda = new Cell(1,2).setPadding(0.1f).add(new Paragraph("NO").setPaddingBottom(0f).setPaddingTop(0f).setFontSize(8.5f).setFont(font).setPaddingLeft(10f)).setBackgroundColor(rgbColorPlomoBlanco);
        table1.addCell(micelda);

        if(!object.isHayAguaCorrida()) {

            micelda = new Cell(1,2).setPadding(0.1f).setFontSize(8.5f).add(paragrapMarcado);
            micelda .setWidth(10f);

            table1.addCell(micelda);
            ///





        }else{

            micelda = new Cell(1,2).setPadding(0.1f).setFontSize(8.5f).add(paragragSinMarcar);
            //micelda .setWidth(10f);

            table1.addCell(micelda);
        }

//
        cellx1= new Cell().setPadding(0.1f);
        paragraph=new Paragraph("LAVADO DE RACIMOS").setPadding(0.1f).setPaddingBottom(0f).setPaddingTop(0f).setFontSize(8.5f).setFont(font).setPaddingLeft(10f).setBold();
        cellx1.add(paragraph).setBackgroundColor(rgbColorPlomoBlanco);

        table1.addCell(cellx1);





        cellx1= new Cell().setPadding(0.1f);
        paragraph=new Paragraph("SI").setPaddingBottom(0f).setPaddingTop(0f).setFontSize(8.5f).setFont(font).setPaddingLeft(10f);
        cellx1.add(paragraph).setBackgroundColor(rgbColorPlomoBlanco);

        table1.addCell(cellx1);
        // table1.addCell(new Paragraph("SI").setFontSize(8.5f).setFont(font).setPaddingLeft(10f)).setBackgroundColor(rgbColorPlomoBlanco);


        /**tiene 7 columnas*/

        if(object.isLavadoRacimos()) {
            //  table1.addCell(new Paragraph(" X").setBackgroundColor(rgbColor));
            micelda = new Cell(1,1).setPadding(0.1f).setFontSize(8.5f).add(paragrapMarcado);
            micelda .setWidth(10f);

            table1.addCell(micelda);

        }else{

            micelda = new Cell(1,1).setPadding(0.1f).setFontSize(8.5f).add(paragragSinMarcar);
            micelda .setWidth(10f);

            table1.addCell(micelda);
        }

        cellx1= new Cell().setPadding(0.1f);
        paragraph=new Paragraph("NO").setPaddingBottom(0f).setPaddingTop(0f).setFontSize(8.5f).setFont(font).setPaddingLeft(10f);
        cellx1.add(paragraph).setBackgroundColor(rgbColorPlomoBlanco);

        table1.addCell(cellx1);

        if(!object.isLavadoRacimos()) {
            micelda = new Cell(1,3).setPadding(0.1f).setFontSize(8.5f).add(paragrapMarcado);
            // micelda .setWidth(10f);

            table1.addCell(micelda);
        }else{

            micelda = new Cell(1,3).setPadding(0.1f).setFontSize(8.5f).add(paragragSinMarcar);
            //  micelda .setWidth(10f);

            table1.addCell(micelda);
        }


        /**fumigacion corona linea `1*/

        cellx1= new Cell().setPadding(0.1f);
        paragraph=new Paragraph("FUMIGACIÓN CORONA LINEA 1").setPaddingBottom(0f).setPaddingTop(0f).setFontSize(8.5f).setFont(font).setPaddingLeft(10f).setBold();
        cellx1.add(paragraph).setBackgroundColor(rgbColorPlomoBlanco);


        table1.addCell(cellx1);


        cellx1= new Cell().setPadding(0.1f);
        paragraph=new Paragraph("FOGGING").setPaddingBottom(0f).setPaddingTop(0f).setFontSize(8.5f).setFont(font).setPaddingLeft(10f);
        cellx1.add(paragraph).setBackgroundColor(rgbColorPlomoBlanco);
        table1.addCell(cellx1);


        if(object.getFumigacionClin1().equalsIgnoreCase("FOGGING")) {
            micelda = new Cell(1,1).setPadding(0.1f).setFontSize(8.5f).add(paragrapMarcado);
            micelda .setWidth(10f);

            table1.addCell(micelda);

        }else{

            micelda = new Cell(1,1).setPadding(0.1f).setFontSize(8.5f).add(paragragSinMarcar);
            micelda .setWidth(10f);

            table1.addCell(micelda);

        }


        ////////

        cellx1= new Cell().setPadding(0.1f);
        paragraph=new Paragraph("BOMBA CP3").setPaddingBottom(0f).setPaddingTop(0f).setFontSize(8.5f).setFont(font).setPaddingLeft(10f);
        cellx1.add(paragraph).setBackgroundColor(rgbColorPlomoBlanco);
        table1.addCell(cellx1);




/*
        if(object.getFumigacionClin1().equalsIgnoreCase("BOMBA CP3 MANUAL") ) {

            micelda = new Cell(1,1).setBackgroundColor(rgbColorVerdeCana).setFontSize(8.5f).add(paragrapMarcado);
            micelda .setWidth(10f);

            table1.addCell(micelda);


        }else{

            micelda = new Cell(1,1).setBackgroundColor(rgbColorVerdeCana).setFontSize(8.5f).add(paragragSinMarcar);
            micelda .setWidth(10f);

            table1.addCell(micelda);
        }
*/
////////////4



        //   table1.addCell();

        if(object.getFumigacionClin1().equalsIgnoreCase("BOMBA CP3 ELÉCTRICA")) {

            micelda = new Cell(1,3).setPadding(0.1f).
                    setFontSize(8.5f).add(new Paragraph("ELÉCTRICA").setPaddingBottom(0f).setPaddingTop(0f).setFontSize(8.5f).setFont(font).setPaddingLeft(10f)).setBackgroundColor(rgbColorPlomoBlanco);

            micelda .setWidth(10f);
            table1.addCell(micelda);

        }else if(object.getFumigacionClin1().equalsIgnoreCase("BOMBA CP3 MANUAL")) {

            micelda = new Cell(1,3).setPadding(0.1f).setPadding(0.1f).
                    setFontSize(8.5f).add(new Paragraph("MANUAL").setPaddingBottom(0f).setPaddingTop(0f).setFontSize(8.5f).setFont(font).setPaddingLeft(10f)).setBackgroundColor(rgbColorPlomoBlanco);

            //   micelda = new Cell(1,1).setBackgroundColor(rgbColorVerdeCana).setFontSize(8.5f).add(paragragSinMarcar);
            micelda .setWidth(10f);

            table1.addCell(micelda);
        }else{
            micelda = new Cell(1,3).setPadding(0.1f).setFontSize(8.5f).add(paragragSinMarcar);
            table1.addCell(micelda);


        }



        return table1;
    }


    public static Table createTable3(Table miTable,SetInformEmbarque2 object2) {


        ///Cell cell208X= new Cell().setPaddingLeft(10f);


        ///cellPolituboX.setBackgroundColor(rgbColor) ; //MNARCAR LA X CON BACVGROUND VERDE

        //FALTA AGREGAR A LAS TABLAS
        //  miTable.addCell(new Cell().add(new Paragraph("TIPO DE PLASTICO").setFontSize(7.5f))) ;


        miTable.addCell(new Cell().add(new Paragraph("ENZUCHADO").setFontSize(7.5f).setPaddingLeft(10f).setFont(font)).setBold()) ;
        miTable.addCell(new Cell().add(new Paragraph("SI").setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;

        if(object2.isHayExcelnsuchado()) {
                miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" X ").setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;
        }

        else {
            miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;
        }

        miTable.addCell(new Cell().add(new Paragraph("NO").setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;


        if(!object2.isHayExcelnsuchado()) {
            miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" X ").setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;
        }

        else {
            miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;
        }


        /**BALANZA*///*/

        miTable.addCell(new Cell().add(new Paragraph("BALANZA").setFont(font).setFontSize(7.5f).setPaddingLeft(10f)).setBold()) ;
        miTable.addCell(new Cell().add(new Paragraph("SI").setFont(font).setFontSize(7.5f).setPaddingLeft(10f))) ;

        if(object2.isHayBalanza()) {
            miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" X ").setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;
        }

        else {
            miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f)).setFont(font)) ;
        }


        miTable.addCell(new Cell().add(new Paragraph("NO").setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;


        if(!object2.isHayBalanza()) {
            miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" X ").setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;
        }

        else {
            miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;
        }



        /** CONDICION DE BALANZA*///*/


        /**condicion de  BALANZA*///*/

        miTable.addCell(new Cell().add(new Paragraph("CONDICIÓN DE BALANZA").setFont(font).setFontSize(7.5f).setPaddingLeft(10f)).setBold()) ;

        //BUENO ,ALO Y REGUKLAR

        if(object2.getCondicionBalanza().equalsIgnoreCase("ACEPTABLE")) {


            miTable.addCell(new Cell().add(new Paragraph("ACEPTABLE").setFont(font).setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;

            miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" X ").setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;


            miTable.addCell(new Cell().add(new Paragraph("MALA").setFont(font).setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;
            miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;


        }






        if(object2.getCondicionBalanza().equalsIgnoreCase("BUENA")) {

            Log.i("condicoon","es buena se ejecuto esto ") ;

            miTable.addCell(new Cell().add(new Paragraph("BUENA").setFont(font).setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;

            miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" X ").setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;


            miTable.addCell(new Cell().add(new Paragraph("MALA").setFont(font).setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;
            miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;


        }

        if(object2.getCondicionBalanza().equalsIgnoreCase("MALA")) {
            miTable.addCell(new Cell().add(new Paragraph("MALA ").setFontSize(7.5f).setPaddingLeft(10f))) ;
            miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph("X").setFontSize(7.5f).setPaddingLeft(10f)).setFont(font)) ;

            miTable.addCell(new Cell().add(new Paragraph("BUENA").setFontSize(7.5f).setPaddingLeft(10f))) ;
            miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f)).setFont(font)) ;
        }

        if(object2.getCondicionBalanza().equalsIgnoreCase("REGULAR")) {
            miTable.addCell(new Cell().add(new Paragraph("REGULAR").setFont(font).setFontSize(7.5f).setPaddingLeft(10f))) ;
            miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" X ").setFont(font).setFontSize(7.5f).setPaddingLeft(10f))) ;


            miTable.addCell(new Cell().add(new Paragraph("BUENA").setFont(font).setFontSize(7.5f).setPaddingLeft(10f))) ;
            miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" ").setFont(font).setFontSize(7.5f).setPaddingLeft(10f))) ;

        }


/***tipo de balanza/
 *
 */


        miTable.addCell(new Cell().add(new Paragraph("TIPO BALANZA").setFont(font).setFontSize(7.5f).setPaddingLeft(10f)).setBold()) ;
        miTable.addCell(new Cell().add(new Paragraph("BASCULA").setFont(font).setFontSize(7.5f).setPaddingLeft(10f))) ;

        if(object2.getTipoDeBalanza().equalsIgnoreCase("BASCULA")) {
            miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" X ").setFont(font).setFontSize(7.5f).setPaddingLeft(10f))) ;
        }

        else {
            miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" ").setFont(font).setFontSize(7.5f).setPaddingLeft(10f))) ;
        }



        miTable.addCell(new Cell().add(new Paragraph("DIGITAL").setFont(font).setFontSize(7.5f).setPaddingLeft(10f))) ;

        if(object2.getTipoDeBalanza().equalsIgnoreCase("DIGITAL")) {
            miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" X ").setFont(font).setFontSize(7.5f).setPaddingLeft(10f))) ;
        }

        else {
            miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" ").setFont(font).setFontSize(7.5f).setPaddingLeft(10f))) ;
        }





/***BALANZA DE REPESA/
 */


        miTable.addCell(new Cell().add(new Paragraph("BALANZA DE REPESA").setFont(font).setFontSize(7.5f).setPaddingLeft(10f)).setBold()) ;
        miTable.addCell(new Cell().add(new Paragraph("SI").setFont(font).setFontSize(7.5f).setPaddingLeft(10f))) ;

        if(object2.getHayBalanzaRepeso()) {
            miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" X ").setFontSize(7f).setPaddingLeft(10f))) ;
        }

        else {
            miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f))) ;
        }



        miTable.addCell(new Cell().add(new Paragraph("NO").setFont(font).setFontSize(7.5f).setPaddingLeft(10f))) ;

        if(!object2.getHayBalanzaRepeso()) {
            miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" X ").setFontSize(7f).setPaddingLeft(10f))) ;
        }

        else {
            miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f))) ;
        }



        //////// //si hay balanza de repeso
        if(object2.getHayBalanzaRepeso()){
            miTable.addCell(new Cell().add(new Paragraph("TIPO DE BALANZA DE REPESA").setFont(font).setFontSize(7.5f).setPaddingLeft(10f)).setBold()) ;

            miTable.addCell(new Cell().add(new Paragraph("BASCULA").setFont(font).setFontSize(7.5f).setPaddingLeft(10f))) ;


            if(object2.getTipoDeBalanzaRepeso().equalsIgnoreCase("bascula")) {
                miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" X ").setFont(font).setFontSize(7f).setPaddingLeft(10f))) ;
            }

            else {
                miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" ").setFont(font).setFontSize(7.5f).setPaddingLeft(10f))) ;
            }



            miTable.addCell(new Cell().add(new Paragraph("DIGITAL").setFont(font).setFontSize(7.5f).setPaddingLeft(10f))) ;


            if(!object2.getTipoDeBalanzaRepeso().equalsIgnoreCase("DIGITAL")) {
                miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" X ").setFontSize(7f).setPaddingLeft(10f))) ;
            }

            else {
                miTable.addCell(new Cell().setBackgroundColor(rgbColorVerdeCana).add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f))) ;
            }


        }



        ///QUEDAMOS EN DATOS DE HACIENDA..

        return miTable;

    }


    public static Table createTable3(Table miTable, ReportCamionesyCarretas object2) {


        //si no sale bien revisar create table 3



        miTable.addCell(new Cell().setPadding(0.1f).setBackgroundColor(rgbColorPlomoBlanco).add(new Paragraph("ENZUCHADO").setPaddingTop(0f).setPaddingBottom(0f).setFontSize(8.5f).setPaddingLeft(10f).setFont(font)).setBold()) ;
        miTable.addCell(new Cell().setPadding(0.1f).setBackgroundColor(rgbColorPlomoBlanco).add(new Paragraph("SI").setPaddingTop(0f).setPaddingBottom(0f).setFontSize(8.5f).setPaddingLeft(10f).setFont(font))) ;

        if(object2.isHayEnsunchado()) {
            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" X ").setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;
        }

        else {
            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;
        }

        miTable.addCell(new Cell().setPadding(0.1f).setBackgroundColor(rgbColorPlomoBlanco).add(new Paragraph("NO").setPaddingTop(0f).setPaddingBottom(0f).setFontSize(8.5f).setPaddingLeft(10f).setFont(font).setBackgroundColor(rgbColorPlomoBlanco))) ;


        if(!object2.isHayEnsunchado()) {
            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" X ").setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;
        }

        else {
            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;
        }


        /**BALANZA*///*/

        miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph("BALANZA").setPaddingBottom(0f).setPaddingTop(0f).setFont(font).setFontSize(8.5f).setPaddingLeft(10f)).setBold().setBackgroundColor(rgbColorPlomoBlanco)) ;
        miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph("SI").setPaddingBottom(0f).setPaddingTop(0f).setFont(font).setFontSize(8.5f).setPaddingLeft(10f)).setBackgroundColor(rgbColorPlomoBlanco)) ;

        if(object2.isHayBalanza()) {
            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" X ").setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;
        }

        else {
            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f)).setFont(font)) ;
        }


        miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph("NO").setPaddingBottom(0f).setPaddingTop(0f).setFontSize(8.5f).setPaddingLeft(10f).setFont(font)).setBackgroundColor(rgbColorPlomoBlanco)) ;


        if(!object2.isHayBalanza()) {
            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" X ").setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;
        }

        else {
            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;
        }



        /** CONDICION DE BALANZA*///*/


        /**condicion de  BALANZA*///*/

        miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph("CONDICIÓN DE BALANZA").setPaddingBottom(0f).setPaddingTop(0f).setFont(font).setFontSize(8.5f).setPaddingLeft(10f)).setBold().setBackgroundColor(rgbColorPlomoBlanco)) ;

        //BUENO ,ALO Y REGUKLAR

        if(object2.getCondicionBalanza().equalsIgnoreCase("ACEPTABLE")) {


            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph("ACEPTABLE").setPaddingBottom(0f).setPaddingTop(0f).setFont(font).setFontSize(8.5f).setPaddingLeft(10f).setFont(font)).setBackgroundColor(rgbColorPlomoBlanco)) ;

            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" X ").setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;


            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph("MALA").setPaddingBottom(0f).setPaddingTop(0f).setFont(font).setFontSize(8.5f).setPaddingLeft(10f).setFont(font)).setBackgroundColor(rgbColorPlomoBlanco)) ;
            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;


        }






        if(object2.getCondicionBalanza().equalsIgnoreCase("BUENA")) {

            Log.i("condicoon","es buena se ejecuto esto ") ;

            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph("BUENA").setPaddingBottom(0f).setPaddingTop(0f).setFont(font).setFontSize(8.5f).setPaddingLeft(10f).setFont(font)).setBackgroundColor(rgbColorPlomoBlanco)) ;

            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" X ").setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;


            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph("MALA").setPaddingBottom(0f).setPaddingTop(0f).setFont(font).setFontSize(8.5f).setPaddingLeft(10f).setFont(font)).setBackgroundColor(rgbColorPlomoBlanco)) ;
            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f).setFont(font))) ;


        }

        if(object2.getCondicionBalanza().equalsIgnoreCase("MALA")) {
            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph("MALA ").setPaddingBottom(0f).setPaddingTop(0f).setFontSize(8.5f).setPaddingLeft(10f)).setBackgroundColor(rgbColorPlomoBlanco)) ;
            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph("X").setFontSize(7.5f).setPaddingLeft(10f)).setFont(font)) ;

            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph("BUENA").setPaddingBottom(0f).setPaddingTop(0f).setFontSize(8.5f).setPaddingLeft(10f)).setBackgroundColor(rgbColorPlomoBlanco)) ;
            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f)).setFont(font)) ;
        }

        if(object2.getCondicionBalanza().equalsIgnoreCase("REGULAR")) {
            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph("REGULAR").setPaddingBottom(0f).setPaddingTop(0f).setFont(font).setFontSize(8.5f).setPaddingLeft(10f)).setBackgroundColor(rgbColorPlomoBlanco)) ;
            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" X ").setFont(font).setFontSize(7.5f).setPaddingLeft(10f))) ;


            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph("BUENA").setPaddingBottom(0f).setPaddingTop(0f).setFont(font).setFontSize(8.5f).setPaddingLeft(10f)).setBackgroundColor(rgbColorPlomoBlanco)) ;
            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" ").setFont(font).setFontSize(7.5f).setPaddingLeft(10f))) ;

        }


/***tipo de balanza/
 *
 */


        miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph("TIPO BALANZA").setPaddingBottom(0f).setPaddingTop(0f).setFont(font).setFontSize(8.5f).setPaddingLeft(10f)).setBold().setBackgroundColor(rgbColorPlomoBlanco)) ;
        miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph("BASCULA").setPaddingBottom(0f).setPaddingTop(0f).setFont(font).setFontSize(8.5f).setPaddingLeft(10f)).setBackgroundColor(rgbColorPlomoBlanco)) ;

        if(object2.getTipoBalanza().equalsIgnoreCase("BASCULA")) {
            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" X ").setFont(font).setFontSize(7.5f).setPaddingLeft(10f))) ;
        }

        else {
            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" ").setFont(font).setFontSize(7.5f).setPaddingLeft(10f))) ;
        }



        miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph("DIGITAL").setPaddingBottom(0f).setPaddingTop(0f).setFont(font).setFontSize(8.5f).setPaddingLeft(10f)).setBackgroundColor(rgbColorPlomoBlanco)) ;

        if(object2.getTipoBalanza().equalsIgnoreCase("DIGITAL")) {
            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" X ").setFont(font).setFontSize(7.5f).setPaddingLeft(10f))) ;
        }

        else {
            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" ").setFont(font).setFontSize(7.5f).setPaddingLeft(10f))) ;
        }





/***BALANZA DE REPESA/
 */


        miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph("BALANZA DE REPESA").setPaddingBottom(0f).setPaddingTop(0f).setFont(font).setFontSize(8.5f).setPaddingLeft(10f)).setBold().setBackgroundColor(rgbColorPlomoBlanco)) ;
        miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph("SI").setPaddingBottom(0f).setPaddingTop(0f).setFont(font).setFontSize(7.5f).setPaddingLeft(10f)).setBackgroundColor(rgbColorPlomoBlanco)) ;

        if(object2.isHayBalanzaRepesa()) {
            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" X ").setFontSize(7.5f).setPaddingLeft(10f))) ;
        }

        else {
            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f))) ;
        }



        miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph("NO").setPaddingBottom(0f).setPaddingTop(0f).setFont(font).setFontSize(8.5f).setPaddingLeft(10f)).setBackgroundColor(rgbColorPlomoBlanco)) ;

        if(!object2.isHayBalanzaRepesa()) {
            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" X ").setFontSize(7f).setPaddingLeft(10f))) ;
        }

        else {
            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" ").setFontSize(8.5f).setPaddingLeft(10f))) ;
        }



        //////// //si hay balanza de repeso
        if(object2.isHayBalanzaRepesa()){
            miTable.addCell(new Cell().setPadding(0.1f).setPadding(0.1f).add(new Paragraph("TIPO DE BALANZA DE REPESA").setPaddingBottom(0f).setPaddingTop(0f).setFont(font).setFontSize(8.5f).setPaddingLeft(10f)).setBold().setBackgroundColor(rgbColorPlomoBlanco)) ;

            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph("BASCULA").setPaddingBottom(0f).setPaddingTop(0f).setFont(font).setFontSize(8.5f).setPaddingLeft(10f)).setBackgroundColor(rgbColorPlomoBlanco)) ;


            if(object2.getTipoBalanzaRepesa().equalsIgnoreCase("bascula")) {
                miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" X ").setFont(font).setFontSize(7f).setPaddingLeft(10f))) ;
            }

            else {
                miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" ").setFont(font).setFontSize(7.5f).setPaddingLeft(10f))) ;
            }



            miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph("DIGITAL").setPaddingBottom(0f).setPaddingTop(0f).setFont(font).setFontSize(8.5f).setPaddingLeft(10f)).setBackgroundColor(rgbColorPlomoBlanco)) ;


            if(!object2.getTipoBalanzaRepesa().equalsIgnoreCase("DIGITAL")) {
                miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" X ").setFontSize(7.5f).setPaddingLeft(10f))) ;
            }

            else {
                miTable.addCell(new Cell().setPadding(0.1f).add(new Paragraph(" ").setFontSize(8.5f).setPaddingLeft(10f))) ;
            }


        }



        ///QUEDAMOS EN DATOS DE HACIENDA..

        return miTable;

    }



    /**Calibracion de fruta clanedario de enfunde*/


    public static  Table createTABLEcalendarioEnfude(Table table1X ,SetInformDatsHacienda inform){


        float araycolumccc[]= {1,1,1,1};
        table1X=  new Table(araycolumccc);


        Cell cellHeader2= new Cell(1,4).setBackgroundColor(rgbColorAzulClaro);
        cellHeader2.add(new Paragraph("CALIBRACIÓN DE FRUTA(CALENDARIO DE ENFUNDE)").setFont(font).setTextAlignment(TextAlignment.CENTER).setFontSize(8f).setBold());
        table1X.addCell(cellHeader2);


        Cell cellSemana= new Cell(1,1).setBackgroundColor(rgbColorDurazno);
        cellSemana.add(new Paragraph(" SEMANA ").setFont(font).setTextAlignment(TextAlignment.CENTER).setFontSize(8f).setBold());

        table1X.addCell(cellSemana);



        Cell cellColor= new Cell(1,1).setBackgroundColor(rgbColorDurazno);
        cellColor.add(new Paragraph(" COLOR ").setBold().setFont(font).setTextAlignment(TextAlignment.CENTER).setFontSize(8f));

        table1X.addCell(cellColor);


        Cell cellNUMrAC= new Cell(1,1).setBackgroundColor(rgbColorDurazno);
        cellNUMrAC.add(new Paragraph("NUMERACIÓN RACIMOS ").setBold().setFont(font).setTextAlignment(TextAlignment.CENTER).setFontSize(8f));
        table1X.addCell(cellNUMrAC);


        Cell cellPorcent= new Cell(1,1).setBackgroundColor(rgbColorDurazno);
        cellPorcent.add(new Paragraph(" PORCENTAJE ").setBold().setFont(font).setTextAlignment(TextAlignment.CENTER).setFontSize(8f));
        table1X.addCell(cellPorcent);


        ArrayList<String> values= new ArrayList<>();

         String [] arrayporcentaje;

         String remplace;

        values.add(" 14 ");
        values.add(inform.getColortSem14());
        values.add(inform.getNumRcim14());
       // values.add(inform.getPorc14());

        if(!inform.getPorc14().trim().isEmpty()){
            remplace=inform.getPorc14().replace(",", ".");
            inform.setPorc14(remplace);

            if(inform.getPorc14().contains(".")) { ///40
                arrayporcentaje=inform.getPorc14().split("\\.");

                if (arrayporcentaje[1].length() == 1) { //SI TIene
                    inform.setPorc14(inform.getPorc14()+"0");
                }
               }else{
                inform.setPorc14(inform.getPorc14()+".00");


            }

                values.add(inform.getPorc14()+"%");



        }else{
            values.add(inform.getPorc14());

            Log.i("samerr","14 ES EMPTY");

        }

        values.add(" 13 ");
        values.add(inform.getColortSem13());
        values.add(inform.getNumRcim13());
        //values.add(inform.getPorc13());

        if(!inform.getPorc13().trim().isEmpty()){
            remplace=inform.getPorc13().replace(",", ".");
            inform.setPorc13(remplace);

            arrayporcentaje=inform.getPorc13().split("\\.");

            if(inform.getPorc13().contains(".")) {

                if (arrayporcentaje[1].length() == 1) { //SI TIene
                    inform.setPorc13(inform.getPorc13()+"0");
                }
            }

            else{
                inform.setPorc13(inform.getPorc13()+".00");



            }

            values.add(inform.getPorc13()+"%");


        }else
            values.add(inform.getPorc13());

        Log.i("samerr","13 ES EMPTY");


        values.add(" 12 ");
        values.add(inform.getColortSem12());
        values.add(inform.getNumRcim12());
       // values.add(inform.getPorc12());


        if(!inform.getPorc12().trim().isEmpty()){

            remplace=inform.getPorc12().replace(",", ".");
            inform.setPorc12(remplace);
            Log.i("samerr","el informe es "+inform.getPorc12());

            arrayporcentaje=inform.getPorc12().split("\\.");

            if(inform.getPorc12().contains(".")) {

                if (arrayporcentaje[1].length() == 1) { //SI TIene
                    inform.setPorc12(inform.getPorc12()+"0");
                }
            }

            else{

                inform.setPorc12(inform.getPorc12()+".00");

            }

            values.add(inform.getPorc12()+"%");

        }else
            values.add(inform.getPorc12());

        Log.i("samerr","12 ES EMPTY");


        values.add(" 11 ");
        values.add(inform.getColortSem11());
        values.add(inform.getNumRcim11());


        if(!inform.getPorc11().trim().isEmpty()){
            remplace=inform.getPorc11().replace(",", ".");
            inform.setPorc11(remplace);


            arrayporcentaje=inform.getPorc11().split("\\.");


            if(inform.getPorc11().contains(".")) {


                if (arrayporcentaje[1].length() == 1) { //SI TIene
                    inform.setPorc11(inform.getPorc11()+"0");
                }

            }else{
                inform.setPorc11(inform.getPorc11()+".00");


            }

            values.add(inform.getPorc11()+"%");




        }else
            values.add(inform.getPorc11());


        values.add(" 10 ");
        values.add(inform.getColortSem10());
        values.add(inform.getNumRcim10());

        if(!inform.getPorc10().trim().isEmpty()){
            remplace=inform.getPorc10().replace(",", ".");
            inform.setPorc10(remplace);

            arrayporcentaje=inform.getPorc10().split("\\.");

            if(inform.getPorc10().contains(".")) {
                if(arrayporcentaje[1].length()==1) { //SI TIene
                    inform.setPorc10("0"+inform.getPorc10());
                }
            }
            else{
                inform.setPorc10(inform.getPorc10()+".00");



            }




            values.add(inform.getPorc10()+"%");

        }else
            values.add(inform.getPorc10());





        values.add(" 9 ");
        values.add(inform.getColortSem9()); //CORREGIR
        values.add(inform.getNumRcim9());



        if(!inform.getPorc9().trim().isEmpty()){
            remplace=inform.getPorc9().replace(",", ".");
            inform.setPorc9(remplace);

            arrayporcentaje=inform.getPorc9().split("\\.");

            if(inform.getPorc9().contains(".")){
                if(arrayporcentaje[1].length()==1) { //SI TIene
                    inform.setPorc9("0"+inform.getPorc9());
                }

            }
            else{
                inform.setPorc9(inform.getPorc9()+".00");



            }





            values.add(inform.getPorc9()+"%");

        }

        else
            values.add(inform.getPorc9());






        int  [] arranumsAddOtherFontSize  = {0,4,8,12,16,20} ;


        for (int i = 0; i < values.size(); i++) {

            Cell cellNUMrACx= new Cell(1,1);

            if(Arrays.asList(arranumsAddOtherFontSize).contains(i)){

                cellNUMrACx.add(new Paragraph(values.get(i)).
                        setTextAlignment(TextAlignment.CENTER).setFontSize(8f).setBold());

            }else{

                cellNUMrACx.add(new Paragraph(values.get(i)).
                        setTextAlignment(TextAlignment.CENTER).setFontSize(8f));
            }

            table1X.addCell(cellNUMrACx);


        }

        return table1X;
    }


    public static  Table createTABLEcalendarioEnfude(CalibrFrutCalEnf inform){


        float araycolumccc[]= {1,1,1,1};
       Table table1X=  new Table(araycolumccc);


        Cell cellHeader2= new Cell(1,4).setBackgroundColor(rgbColorAzulClaro);
        cellHeader2.add(new Paragraph("CALIBRACIÓN DE FRUTA(CALENDARIO DE ENFUNDE)").setFont(font).setTextAlignment(TextAlignment.CENTER).setFontSize(8f).setBold());
        table1X.addCell(cellHeader2);


        Cell cellSemana= new Cell(1,1).setBackgroundColor(rgbColorDurazno);
        cellSemana.add(new Paragraph(" SEMANA ").setFont(font).setTextAlignment(TextAlignment.CENTER).setFontSize(8f).setBold());

        table1X.addCell(cellSemana);



        Cell cellColor= new Cell(1,1).setBackgroundColor(rgbColorDurazno);
        cellColor.add(new Paragraph(" COLOR ").setBold().setFont(font).setTextAlignment(TextAlignment.CENTER).setFontSize(8f));

        table1X.addCell(cellColor);


        Cell cellNUMrAC= new Cell(1,1).setBackgroundColor(rgbColorDurazno);
        cellNUMrAC.add(new Paragraph("NUMERACIÓN RACIMOS ").setBold().setFont(font).setTextAlignment(TextAlignment.CENTER).setFontSize(8f));
        table1X.addCell(cellNUMrAC);


        Cell cellPorcent= new Cell(1,1).setBackgroundColor(rgbColorDurazno);
        cellPorcent.add(new Paragraph(" PORCENTAJE ").setBold().setFont(font).setTextAlignment(TextAlignment.CENTER).setFontSize(8f));
        table1X.addCell(cellPorcent);


        ArrayList<String> values= new ArrayList<>();

        String [] arrayporcentaje;

        String remplace;

        values.add(" 14 ");
        values.add(inform.getColorSemana14());
        values.add(String.valueOf(inform.getNumeracionRacimosSem14()));
        // values.add(inform.getPorc14());

        if(!inform.getPorc14().trim().isEmpty()){
            remplace=inform.getPorc14().replace(",", ".");
            inform.setPorc14(remplace);

            if(inform.getPorc14().contains(".")) { ///40
                arrayporcentaje=inform.getPorc14().split("\\.");

                if (arrayporcentaje[1].length() == 1) { //SI TIene
                    inform.setPorc14(inform.getPorc14()+"0");
                }
            }else{
                inform.setPorc14(inform.getPorc14()+".00");


            }

            values.add(inform.getPorc14()+"%");



        }else{
            values.add(inform.getPorc14());

            Log.i("samerr","14 ES EMPTY");

        }

        values.add(" 13 ");
        values.add(inform.getColorSemana13());
        values.add(String.valueOf(inform.getNumeracionRacimosSem13()));
        //values.add(inform.getPorc13());

        if(!inform.getPorc13().trim().isEmpty()){
            remplace=inform.getPorc13().replace(",", ".");
            inform.setPorc13(remplace);

            arrayporcentaje=inform.getPorc13().split("\\.");

            if(inform.getPorc13().contains(".")) {

                if (arrayporcentaje[1].length() == 1) { //SI TIene
                    inform.setPorc13(inform.getPorc13()+"0");
                }
            }

            else{
                inform.setPorc13(inform.getPorc13()+".00");



            }

            values.add(inform.getPorc13()+"%");


        }else
            values.add(inform.getPorc13());

        Log.i("samerr","13 ES EMPTY");


        values.add(" 12 ");
        values.add(inform.getColorSemana12());
        values.add(String.valueOf(inform.getNumeracionRacimosSem12()));
        // values.add(inform.getPorc12());


        if(!inform.getPorc12().trim().isEmpty()){

            remplace=inform.getPorc12().replace(",", ".");
            inform.setPorc12(remplace);
            Log.i("samerr","el informe es "+inform.getPorc12());

            arrayporcentaje=inform.getPorc12().split("\\.");

            if(inform.getPorc12().contains(".")) {

                if (arrayporcentaje[1].length() == 1) { //SI TIene
                    inform.setPorc12(inform.getPorc12()+"0");
                }
            }

            else{

                inform.setPorc12(inform.getPorc12()+".00");

            }

            values.add(inform.getPorc12()+"%");

        }else
            values.add(inform.getPorc12());

        Log.i("samerr","12 ES EMPTY");


        values.add(" 11 ");
        values.add(inform.getColorSemana11());
        values.add(String.valueOf(inform.getNumeracionRacimosSem11()));


        if(!inform.getPorc11().trim().isEmpty()){
            remplace=inform.getPorc11().replace(",", ".");
            inform.setPorc11(remplace);


            arrayporcentaje=inform.getPorc11().split("\\.");


            if(inform.getPorc11().contains(".")) {


                if (arrayporcentaje[1].length() == 1) { //SI TIene
                    inform.setPorc11(inform.getPorc11()+"0");
                }

            }else{
                inform.setPorc11(inform.getPorc11()+".00");


            }

            values.add(inform.getPorc11()+"%");




        }else
            values.add(inform.getPorc11());


        values.add(" 10 ");
        values.add(inform.getColorSemana10());
        values.add(String.valueOf(inform.getNumeracionRacimosSem10()));

        if(!inform.getPorc10().trim().isEmpty()){
            remplace=inform.getPorc10().replace(",", ".");
            inform.setPorc10(remplace);

            arrayporcentaje=inform.getPorc10().split("\\.");

            if(inform.getPorc10().contains(".")) {
                if(arrayporcentaje[1].length()==1) { //SI TIene
                    inform.setPorc10("0"+inform.getPorc10());
                }
            }
            else{
                inform.setPorc10(inform.getPorc10()+".00");



            }




            values.add(inform.getPorc10()+"%");

        }else
            values.add(inform.getPorc10());





        values.add(" 9 ");
        values.add(inform.getColorSemana9()); //CORREGIR
        values.add(String.valueOf(inform.getNumeracionRacimosSem9()));



        if(!inform.getPorc9().trim().isEmpty()){
            remplace=inform.getPorc9().replace(",", ".");
            inform.setPorc9(remplace);

            arrayporcentaje=inform.getPorc9().split("\\.");

            if(inform.getPorc9().contains(".")){
                if(arrayporcentaje[1].length()==1) { //SI TIene
                    inform.setPorc9("0"+inform.getPorc9());
                }

            }
            else{
                inform.setPorc9(inform.getPorc9()+".00");



            }





            values.add(inform.getPorc9()+"%");

        }

        else
            values.add(inform.getPorc9());






        int  [] arranumsAddOtherFontSize  = {0,4,8,12,16,20} ;


        for (int i = 0; i < values.size(); i++) {

            Cell cellNUMrACx= new Cell(1,1);

            if(Arrays.asList(arranumsAddOtherFontSize).contains(i)){

                cellNUMrACx.add(new Paragraph(values.get(i)).
                        setTextAlignment(TextAlignment.CENTER).setFontSize(8f).setBold());

            }else{

                cellNUMrACx.add(new Paragraph(values.get(i)).
                        setTextAlignment(TextAlignment.CENTER).setFontSize(8f));
            }

            table1X.addCell(cellNUMrACx);


        }

        return table1X;
    }


    public static  Table createTABLEcalendarioEnfudeToCamionesYcarretas(CalibrFrutCalEnf inform){
        Log.i("sellamo","se llamo esto first ");


        float araycolumccc[]= {1,1,1,1};
        Table table1X=  new Table(araycolumccc);


        Cell cellHeader2= new Cell(1,4).setBackgroundColor(rgbColorDurazno);
        cellHeader2.add(new Paragraph("CALIBRACIÓN DE FRUTA(CALENDARIO DE ENFUNDE)").setFont(font).setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f).setBold());
        table1X.addCell(cellHeader2);


        Cell cellSemana= new Cell(1,1).setBackgroundColor(rgbColorVerdeCana);
        cellSemana.add(new Paragraph(" SEMANA ").setFont(font).setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f).setBold());

        table1X.addCell(cellSemana);



        Cell cellColor= new Cell(1,1).setBackgroundColor(rgbColorVerdeCana);
        cellColor.add(new Paragraph(" COLOR ").setBold().setFont(font).setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f));

        table1X.addCell(cellColor);


        Cell cellNUMrAC= new Cell(1,1).setBackgroundColor(rgbColorVerdeCana);
        cellNUMrAC.add(new Paragraph("NUMERACIÓN RACIMOS ").setBold().setFont(font).setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f));
        table1X.addCell(cellNUMrAC);


        Cell cellPorcent= new Cell(1,1).setBackgroundColor(rgbColorVerdeCana);
        cellPorcent.add(new Paragraph(" PORCENTAJE ").setBold().setFont(font).setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f));
        table1X.addCell(cellPorcent);


        ArrayList<String> values= new ArrayList<>();

        String [] arrayporcentaje;

        String remplace;

        values.add(" 14 ");
        values.add(inform.getColorSemana14());
        values.add(String.valueOf(inform.getNumeracionRacimosSem14()));
        // values.add(inform.getPorc14());

        if(!inform.getPorc14().trim().isEmpty()){
            remplace=inform.getPorc14().replace(",", ".");
            inform.setPorc14(remplace);

            if(inform.getPorc14().contains(".")) { ///40
                arrayporcentaje=inform.getPorc14().split("\\.");

                if (arrayporcentaje[1].length() == 1) { //SI TIene
                    inform.setPorc14(inform.getPorc14()+"0");
                }
            }else{
                inform.setPorc14(inform.getPorc14()+".00");


            }

            values.add(inform.getPorc14()+"%");



        }else{
            values.add(inform.getPorc14());

            Log.i("samerr","14 ES EMPTY");

        }

        values.add(" 13 ");
        values.add(inform.getColorSemana13());
        values.add(String.valueOf(inform.getNumeracionRacimosSem13()));
        //values.add(inform.getPorc13());

        if(!inform.getPorc13().trim().isEmpty()){
            remplace=inform.getPorc13().replace(",", ".");
            inform.setPorc13(remplace);

            arrayporcentaje=inform.getPorc13().split("\\.");

            if(inform.getPorc13().contains(".")) {

                if (arrayporcentaje[1].length() == 1) { //SI TIene
                    inform.setPorc13(inform.getPorc13()+"0");
                }
            }

            else{
                inform.setPorc13(inform.getPorc13()+".00");



            }

            values.add(inform.getPorc13()+"%");


        }else
            values.add(inform.getPorc13());

        Log.i("samerr","13 ES EMPTY");


        values.add(" 12 ");
        values.add(inform.getColorSemana12());
        values.add(String.valueOf(inform.getNumeracionRacimosSem12()));
        // values.add(inform.getPorc12());


        if(!inform.getPorc12().trim().isEmpty()){

            remplace=inform.getPorc12().replace(",", ".");
            inform.setPorc12(remplace);
            Log.i("samerr","el informe es "+inform.getPorc12());

            arrayporcentaje=inform.getPorc12().split("\\.");

            if(inform.getPorc12().contains(".")) {

                if (arrayporcentaje[1].length() == 1) { //SI TIene
                    inform.setPorc12(inform.getPorc12()+"0");
                }
            }

            else{

                inform.setPorc12(inform.getPorc12()+".00");

            }

            values.add(inform.getPorc12()+"%");

        }else
            values.add(inform.getPorc12());

        Log.i("samerr","12 ES EMPTY");


        values.add(" 11 ");
        values.add(inform.getColorSemana11());
        values.add(String.valueOf(inform.getNumeracionRacimosSem11()));


        if(!inform.getPorc11().trim().isEmpty()){
            remplace=inform.getPorc11().replace(",", ".");
            inform.setPorc11(remplace);


            arrayporcentaje=inform.getPorc11().split("\\.");


            if(inform.getPorc11().contains(".")) {


                if (arrayporcentaje[1].length() == 1) { //SI TIene
                    inform.setPorc11(inform.getPorc11()+"0");
                }

            }else{
                inform.setPorc11(inform.getPorc11()+".00");


            }

            values.add(inform.getPorc11()+"%");




        }else
            values.add(inform.getPorc11());


        values.add(" 10 ");
        values.add(inform.getColorSemana10());
        values.add(String.valueOf(inform.getNumeracionRacimosSem10()));

        if(!inform.getPorc10().trim().isEmpty()){
            remplace=inform.getPorc10().replace(",", ".");
            inform.setPorc10(remplace);

            arrayporcentaje=inform.getPorc10().split("\\.");

            if(inform.getPorc10().contains(".")) {
                if(arrayporcentaje[1].length()==1) { //SI TIene
                    inform.setPorc10("0"+inform.getPorc10());
                }
            }
            else{
                inform.setPorc10(inform.getPorc10()+".00");



            }




            values.add(inform.getPorc10()+"%");

        }else
            values.add(inform.getPorc10());





        values.add(" 9 ");
        values.add(inform.getColorSemana9()); //CORREGIR
        values.add(String.valueOf(inform.getNumeracionRacimosSem9()));



        if(!inform.getPorc9().trim().isEmpty()){
            remplace=inform.getPorc9().replace(",", ".");
            inform.setPorc9(remplace);

            arrayporcentaje=inform.getPorc9().split("\\.");

            if(inform.getPorc9().contains(".")){
                if(arrayporcentaje[1].length()==1) { //SI TIene
                    inform.setPorc9("0"+inform.getPorc9());
                }

            }
            else{
                inform.setPorc9(inform.getPorc9()+".00");



            }





            values.add(inform.getPorc9()+"%");

        }

        else
            values.add(inform.getPorc9());






        int  [] arranumsAddOtherFontSize  = {0,4,8,12,16,20} ;


        for (int i = 0; i < values.size(); i++) {

            Cell cellNUMrACx= new Cell(1,1);

            if(Arrays.asList(arranumsAddOtherFontSize).contains(i)){
                cellNUMrACx.add(new Paragraph(values.get(i)).
                        setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f).setBold());

                //   cellNUMrACx.setBackgroundColor(rgbColorPlomoBlanco);


                Log.i("sellamo","se llamo esto ");

            }else{

                cellNUMrACx.add(new Paragraph(values.get(i)).
                        setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f));
            }

            if(values.get(i).equals(" 14 ") ||values.get(i).equals(" 13 ")|| values.get(i).equals(" 12 ")||values.get(i).equals(" 11 ")
                    ||values.get(i).equals(" 10 ")|| values.get(i).equals(" 9 ")){
                cellNUMrACx.setBackgroundColor(rgbColorPlomoBlanco);

            }


            table1X.addCell(cellNUMrACx);


        }

        return table1X;
    }


    public static Table descripciondEFECXTOSFRUTA(Table table){
        ArrayList<String> list = new ArrayList<String>();
        list.add("sr,estropeo");
        list.add("tc,moquillo");
        list.add("lp,larga plana");
        list.add("br,daño de pulpa");
        list.add("ff,dedos mellizos");
        list.add("lc,larga curva");
        list.add("ct,corte de cuchillo");
        list.add("lg,LÁTEX  gelatinoso");
        list.add("p,pequeña");
        list.add("ml,mal formado");
        list.add("is,manchas de LÁTEX");
        list.add("f,flojo");
        list.add("ni,cuello roto");
        list.add("ug,bajo grado");
        list.add("m,montada");
        list.add("bm,daño de colapsis");
        list.add("og,sobre grado");
        list.add("v,virado");
        list.add("ts,dedo corto");
        list.add("fl,flores");
        list.add("ea,empaque alto");
        list.add("re,residuos QUÍMICOS");
        list.add("rr,mancha roja");
        list.add("ps,daño de punta");
        list.add("ab,pulpa crema");
        list.add("wi,cochinilla");
        list.add("lg,latex gelatinoso");
        list.add("mf,mutilado");
        list.add("sm,fumagina");
        list.add("ps,punto jhonson");
        list.add("ls,latex seco");
        list.add("wi,cochinilla");
        list.add("sk,speckling");
        list.add("sc,rasguño");
        list.add("dt,fruta sucia");  //34

        // defectos de empaque //////////

        list.add("sre,estropeo empaque");
        list.add("bre,daño empaque");
        list.add("nie,cuello roto empaque");
        list.add("pse,daño de punta empaque");
        list.add("srfe,FRICCIÓN empaque");
        list.add("mfe,mal formado empaque");

        //cremoas la primer CELDA



        //aGREGMOS LA IMGEN HEADER...




        Cell cellHeader2 = new Cell(1,6).setBorder(Border.NO_BORDER).setPaddingBottom(10f);
        table.addCell(cellHeader2.add(new Paragraph("DESCRIPCIÓN DE DEFECTOS DE FRUTA – DEFECTOS EN EMPAQUE").
                setBold().setTextAlignment(TextAlignment.CENTER)));


        //itreamos la lista
        for(int i = 0; i < list.size(); i++){


            if(i ==34){
                String [] array =list.get(i).split(",");

                Cell cellbold = new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(array[0].toUpperCase()).setFontSize(9f).setBold().setPaddingLeft(5f));
                Cell cellSign = new Cell(1,3).setBorder(Border.NO_BORDER).add(new Paragraph(array[1].toUpperCase()).setFontSize(9f).setPaddingLeft(5f));

                table.addCell(cellbold);
                table.addCell(cellSign);


            }

            if(i ==35){
                Cell cellbold = new Cell(1,6).setBorder(Border.NO_BORDER).setPaddingTop(7f).setPaddingBottom(7f).setBorder(Border.NO_BORDER).add(new Paragraph( "DEFECTOS EN EMPAQUE").
                        setTextAlignment(TextAlignment.LEFT).setFontSize(10f).setBold().setPaddingLeft(5f));
                table.addCell(cellbold);


            }else{
                if(i !=34) {
                    String [] array =list.get(i).split(",");

                    Cell cellbold = new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(array[0].toUpperCase()).setFontSize(9f).setBold().setPaddingLeft(5f));
                    Cell cellSign = new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(array[1].toUpperCase()).setFontSize(9f).setPaddingLeft(5f));

                    table.addCell(cellbold);
                    table.addCell(cellSign);
                }


            }



        }

        return table;

    }



    /**ESTO CREARA UN TABLE CADA VEZ QUE SE LLAMA**/
    public static  Table createTableEvaluacionYcondcionFruta(ControlCalidad objecControlCald,HashMap <String, String> hashMapControlCald,HashMap <String, String> hashMapDefctChecked,Context contexto,int contador) {
        //aqui creamos la info con esta data.....

        ArrayList<Double> listPh = new ArrayList<>();
        ArrayList<DefectsCantdad> defectsSeleccionList = new ArrayList<>();
        ArrayList<DefectsCantdad> defectsEmpaque = new ArrayList<>();

        //  TableCalidProdc=new ArrayList<>();//le agergue

        //DATOS QUE NECESITAMOS OBTENERdoub
        double PROMEDIO_PESO = 0;

        int NUMERO_DEFECTS;
        String MAYOR_DEFECTO_SELECCION = "NO";
        String MAYOR_DEFECTO_EMPAQUE = "NO";
        int NUMERO_DE_CLUSTERS_iNSPECCIONADOS = 0;
        int NUMERO_DE_CLUSTERS_POR_CAJA=0;

        int NUMERO_DE_DEDOS=0;
        double GRADO_CALIBRE_PROMEDIO;
        double LARGO_DEDOS_PROMEDIO;



        int[] keyDatsPeso = {R.id.ediPesoL1, R.id.ediPesoL2, R.id.ediPesoL3, R.id.ediPesoL4, R.id.ediPesoL5, R.id.ediPesoL6, R.id.ediPesoL7,
                R.id.ediPesoL8, R.id.ediPesoL9, R.id.ediPesoL10};


        int[] keyDatsNumClusters = {R.id.ediNumClusInsp1, R.id.ediNumClusInsp2, R.id.ediNumClusInsp3, R.id.ediNumClusInsp4,
                R.id.ediNumClusInsp5, R.id.ediNumClusInsp6, R.id.ediNumClusInsp7, R.id.ediNumClusInsp8, R.id.ediNumClusInsp9, R.id.ediNumClusInsp10};


        int[] keyDatsNumDefectSelecion = {R.id.imgSelecDefc1, R.id.imgSelecDefc2, R.id.imgSelecDefc3, R.id.imgSelecDefc4,
                R.id.imgSelecDefc5, R.id.imgSelecDefc6, R.id.imgSelecDefc7, R.id.imgSelecDefc8, R.id.imgSelecDefc9, R.id.imgSelecDefc10};


        int[] arrayWhitKeysDefectsEmmpaque = {R.id.imvEmpaque1, R.id.imvEmpaque2, R.id.imvEmpaque3, R.id.imvEmpaque4,
                R.id.imvEmpaque5, R.id.imvEmpaque6, R.id.imvEmpaque7, R.id.imvEmpaque8, R.id.imvEmpaque9, R.id.imvEmpaque10};


        int[] keyaRRAYnumClustXcajaLine1 = {R.id.edif2NdedoXclustxC1, R.id.edif2NdedoXclustxC2, R.id.edif2NdedoXclustxC3, R.id.edif2NdedoXclustxC4,
                R.id.edif2NdedoXclustxC5, R.id.edif2NdedoXclustxC6, R.id.edif2NdedoXclustxC7, R.id.edif2NdedoXclustxC8, R.id.edif2NdedoXclustxC9, R.id.edif2NdedoXclustxC10
                , R.id.edif2NdedoXclustxC11, R.id.edif2NdedoXclustxC12, R.id.edif2NdedoXclustxC13, R.id.edif2NdedoXclustxC14, R.id.edif2NdedoXclustxC15, R.id.edif2NdedoXclustxC16,
                R.id.edif2NdedoXclustxC17, R.id.edif2NdedoXclustxC18};





        int[] keyaRRAYnumClustXcajaLine2 = {R.id.ediNdedoXclustXc1, R.id.ediNdedoXclustXc2, R.id.ediNdedoXclustXc3, R.id.ediNdedoXclustXc4,
                R.id.ediNdedoXclustXc5, R.id.ediNdedoXclustXc6, R.id.ediNdedoXclustXc7, R.id.ediNdedoXclustXc8, R.id.ediNdedoXclustXc9, R.id.ediNdedoXclustXc10
                , R.id.ediNdedoXclustXc11, R.id.ediNdedoXclustXc12, R.id.ediNdedoXclustXc13, R.id.ediNdedoXclustXc14, R.id.ediNdedoXclustXc15, R.id.ediNdedoXclustXc16
                , R.id.ediNdedoXclustXc17, R.id.ediNdedoXclustXc18};




        int[] keyaRRAYcalibracionesFil1 = {

                R.id.ediCalByA1, R.id.ediCalByA2, R.id.ediCalByA3, R.id.ediCalByA4,
                R.id.ediCalByA5, R.id.ediCalByA6, R.id.ediCalByA7, R.id.ediCalByA8, R.id.ediCalByA9, R.id.ediCalByA10
                , R.id.ediCalByA11, R.id.ediCalByA12, R.id.ediCalByA13, R.id.ediCalByA14, R.id.ediCalByA15, R.id.ediCalByA16,
                R.id.ediCalByA17, R.id.ediCalByA18

        };


        int[] keyaRRAYcalibracionesFil2 = {
                R.id.edif2Calib1, R.id.edif2Calib2, R.id.edif2Calib3, R.id.edif2Calib4,
                R.id.edif2Calib5, R.id.edif2Calib6, R.id.edif2Calib7, R.id.edif2Calib8, R.id.edif2Calib9, R.id.edif2Calib10
                , R.id.edif2Calib11, R.id.edif2Calib12, R.id.edif2Calib13, R.id.edif2Calib14, R.id.edif2Calib15, R.id.edif2Calib16,
                R.id.edif2Calib17, R.id.edif2Calib18
        };


        int[] keyaRRAYlargoFil1 = {
                R.id.ediLargDeds1, R.id.ediLargDeds2, R.id.ediLargDeds3, R.id.ediLargDeds4,
                R.id.ediLargDeds5, R.id.ediLargDeds6, R.id.ediLargDeds7, R.id.ediLargDeds8, R.id.ediLargDeds9, R.id.ediLargDeds10
                , R.id.ediLargDeds11, R.id.ediLargDeds12, R.id.ediLargDeds13, R.id.ediLargDeds14, R.id.ediLargDeds15, R.id.ediLargDeds16,
                R.id.ediLargDeds17, R.id.ediLargDeds18, R.id.ediLargDeds19, R.id.ediLargDeds20, R.id.ediLargDeds21, R.id.ediLargDeds22
                , R.id.ediLargDeds23, R.id.ediLargDeds24, R.id.ediLargDeds25, R.id.ediLargDeds26, R.id.ediLargDeds27, R.id.ediLargDeds28
                , R.id.ediLargDeds29, R.id.ediLargDeds30};


        int[] keyaRRAYlargoFil2 = {R.id.edif2LrgD1, R.id.edif2LrgD2, R.id.edif2LrgD3, R.id.edif2LrgD4,
                R.id.edif2LrgD5, R.id.edif2LrgD6, R.id.edif2LrgD7, R.id.edif2LrgD8, R.id.edif2LrgD9, R.id.edif2LrgD10
                , R.id.edif2LrgD11, R.id.edif2LrgD12, R.id.edif2LrgD13, R.id.edif2LrgD14, R.id.edif2LrgD15, R.id.edif2LrgD16,
                R.id.edif2LrgD17, R.id.edif2LrgD18, R.id.edif2LrgD19, R.id.edif2LrgD20, R.id.edif2LrgD21, R.id.edif2LrgD22
                , R.id.edif2LrgD23, R.id.edif2LrgD24, R.id.edif2LrgD25, R.id.edif2LrgD26, R.id.edif2LrgD27, R.id.edif2LrgD28
                , R.id.edif2LrgD29, R.id.edif2LrgD30};


        int resultLine1;
        int resultLine2;

        double resultLine1Double = 0;
        double resultLine2Double = 0;

        for (int i = 0; i < keyaRRAYlargoFil1.length; i++) {
            //keyDatsPesoarray
            //itremoas
            String keyCurrent1 = String.valueOf(keyaRRAYlargoFil1[i]);
            if (hashMapControlCald.containsKey(keyCurrent1)) {
                resultLine1Double = resultLine1Double + Double.parseDouble(hashMapControlCald.get(keyCurrent1));
            }

            String keyCurrent2 = String.valueOf(keyaRRAYlargoFil2[i]);
            if (hashMapControlCald.containsKey(keyCurrent2)) {
                resultLine2Double = resultLine2Double + Double.parseDouble(hashMapControlCald.get(keyCurrent2));

            }


        }

        /**gheneramos numero de LARGO_DEDOS_PROMEDIO*/
        LARGO_DEDOS_PROMEDIO = resultLine2Double / resultLine1Double;

        Log.i("ELWEIGTH", "EL LARGO_DEDOS_PROMEDIO  ES " + LARGO_DEDOS_PROMEDIO);

        resultLine1Double = 0;
        resultLine2Double = 0;

        for (int i = 0; i < keyaRRAYcalibracionesFil1.length; i++) {
            //keyDatsPesoarray
            //itremoas
            String keyCurrent1 = String.valueOf(keyaRRAYcalibracionesFil1[i]);
            if (hashMapControlCald.containsKey(keyCurrent1)) {
                resultLine1Double = resultLine1Double + Double.parseDouble(hashMapControlCald.get(keyCurrent1));
            }

            String keyCurrent2 = String.valueOf(keyaRRAYcalibracionesFil2[i]);
            if (hashMapControlCald.containsKey(keyCurrent2)) {
                resultLine2Double = resultLine2Double + Double.parseDouble(hashMapControlCald.get(keyCurrent2));

            }


        }

        /**gheneramos numero de GRADO_CALIBRE_PROMEDIO*/
        GRADO_CALIBRE_PROMEDIO = resultLine2Double / resultLine1Double;

        Log.i("ELWEIGTH", "EL GRADO_CALIBRE_PROMEDIO  PROMEDIO ES " + GRADO_CALIBRE_PROMEDIO);


        resultLine1 = 0;
        resultLine2 = 0;


        /*
        for (int i = 0; i < keyaRRAYnumnUMdedosFil1.length; i++) {
            //keyDatsPesoarray
            //itremoas
            String keyCurrent1 = String.valueOf(keyaRRAYnumnUMdedosFil1[i]);
            if (hashMapControlCald.containsKey(keyCurrent1)) {

                Log.i("derddssd"," el value es "+hashMapControlCald.get(keyCurrent1));

                resultLine1 = resultLine1 + Integer.parseInt(hashMapControlCald.get(keyCurrent1));
            }

            String keyCurrent2 = String.valueOf(keyaRRAYnumnUMdedosFil2[i]);
            if (hashMapControlCald.containsKey(keyCurrent2)) {

                resultLine2 = resultLine2 + Integer.parseInt(hashMapControlCald.get(keyCurrent2));

            }

        }
        /*


        /**gheneramos numero de NUMERO_DE_DEDOS*/


        if(!objecControlCald.getNumeroDedosXclusterOmano().equals("")){


            //10:03:22.277  W  java.lang.NumberFormatException: For input string: "10,1"


            String flotanteX=objecControlCald.getNumeroDedosXclusterOmano();
            Log.i("flotantedd", "EL flotante String es " + flotanteX);


            NUMERO_DE_DEDOS=(int)(Float.parseFloat(objecControlCald.getNumeroDedosXclusterOmano()));



        }

       // NUMERO_DE_DEDOS = resultLine2 / resultLine1; //ediNdedoXclustXc20 //here aqui....


        Log.i("ELWEIGTH", "EL NUMERO_DE_DEDOS  PROMEDIO ES " + NUMERO_DE_DEDOS);


        resultLine1 = 0;
        resultLine2 = 0;

        for (int i = 0; i < keyaRRAYnumClustXcajaLine1.length; i++) {
            //keyDatsPesoarray
            //itremoas
            String keyCurrent1 = String.valueOf(keyaRRAYnumClustXcajaLine1[i]);
            if (hashMapControlCald.containsKey(keyCurrent1)) {
                resultLine1 = resultLine1 + Integer.parseInt(hashMapControlCald.get(keyCurrent1));
            }



            String keyCurrent2 = String.valueOf(keyaRRAYnumClustXcajaLine2[i]);
            if (hashMapControlCald.containsKey(keyCurrent2)) {
                resultLine2 = resultLine2 + Integer.parseInt(hashMapControlCald.get(keyCurrent2));

            }


        }

        /**gheneramos numero de CLUSTERS*/


        if(resultLine1>0 || resultLine2>0){
            NUMERO_DE_CLUSTERS_POR_CAJA = resultLine2 / resultLine1;

        }


       //  Log.i("ELWEIGTH", "EN NUMERO_DE_CLUSTERS_POR_CAJA  PROMEDIO ES " + NUMERO_DE_CLUSTERS_POR_CAJA);


        ///            listTOrETURN1.add(new NameAndValue("DESTINO",Object1.getDestinoContenedor()));

        /**GENERAMNOS PROMOEDIO PESO*/
        int numeroItemsEcontrados = 0;

        for (int i = 0; i < keyDatsPeso.length; i++) {
            //keyDatsPesoarray
            //itremoas
            String keyCurrent = String.valueOf(keyDatsPeso[i]);

            if (hashMapControlCald.containsKey(keyCurrent)) {

                PROMEDIO_PESO = PROMEDIO_PESO + Double.parseDouble(hashMapControlCald.get(keyCurrent));
                numeroItemsEcontrados++;

            }

        }
        //tenemos que redondiar el peso lñibras
        PROMEDIO_PESO = PROMEDIO_PESO / numeroItemsEcontrados;

        Log.i("ELWEIGTH", "EN PESO PROMEDIO ES " + PROMEDIO_PESO);

        /**DEFECTOS SELECION*/
        String[] arrayuDefectsSeleccNames = contexto.getResources().getStringArray(R.array.array_defectos_frutselecion); //estaba en ararydefectx

        ArrayList<String> defectosNamesSelecion = new ArrayList<>(); //contendra todos los nombres de defectos selecion


        int contadorCustomDefects=0;
        for (String valuex : hashMapDefctChecked.values()) { //el custom defecto contiene hasta 3 length size..

            Log.i("hasmpadefectchekeed", "la data es " + valuex);

            /**buscamos otros defcetos names y si hay los agregamos a la lista de names **/


            String[] arrayDefectsAnCantidadx = valuex.split("-"); // puede contener esto  1-4, o esto  5-2, 5-8,9-9 , 5-6-adriano


            if (valuex.contains(",")) { //si contiene una comma

                String[] arrayDefectsAnCantidad = valuex.split(","); // puede contener esto  1-4, o esto  5-2, 5-8,9-9 , 5-6-adriano

                for(int indicec=0; indicec<arrayDefectsAnCantidad.length; indicec++){ //itreamos el array qu contiene  valores asi: 2-5 o 9-8 o 8,8,adriano

                    Log.i("howartsxx", "el string es  "+ arrayDefectsAnCantidad[indicec]);

                    String[] arrayIndiceAndNum = arrayDefectsAnCantidad[indicec].split("-");


                    if(arrayIndiceAndNum.length==3){ //si tiene 3 es custom name
                        Log.i("howartsxx", "encontramos 1 aqui y es  "+arrayIndiceAndNum[2]);

                        if(!defectosNamesSelecion.contains(arrayIndiceAndNum[2])){

                            if(!defectosNamesSelecion.contains(arrayIndiceAndNum[2])){

                                defectosNamesSelecion.add(contadorCustomDefects,arrayIndiceAndNum[2]);

                                contadorCustomDefects++;


                                Log.i("howartsxx", "agregamos y el size es  "+defectosNamesSelecion.size());
                                Log.i("howartsxx", "EL SIZSE DE LA LISTA AQUI ES  "+defectosNamesSelecion.size());

                            }


                          //  Log.i("howartsxx", "lo agregamos");
                        }

                    }


                }

            }

            else if(arrayDefectsAnCantidadx.length==3){

                for(int indicec=0; indicec<arrayDefectsAnCantidadx.length; indicec++){ //itreamos el array qu contiene  valores asi: 2-5 o 9-8 o 8,8,adriano

                    Log.i("howartsxx", "el string es  "+ arrayDefectsAnCantidadx[indicec]);
                        Log.i("howartsxx", "encontramos 1 aqui y es  "+arrayDefectsAnCantidadx[2]);

                        if(!defectosNamesSelecion.contains(arrayDefectsAnCantidadx[2])){

                            if(!defectosNamesSelecion.contains(arrayDefectsAnCantidadx[2])){

                                defectosNamesSelecion.add(contadorCustomDefects,arrayDefectsAnCantidadx[2]);

                                contadorCustomDefects++;

                                Log.i("howartsxx", "agregamos y el size es  "+defectosNamesSelecion.size());
                                Log.i("howartsxx", "EL SIZSE DE LA LISTA AQUI ES  "+defectosNamesSelecion.size());

                            }


                            //  Log.i("howartsxx", "lo agregamos");
                        }




                }


            }



        }


        listNumsCustomDefects.add(contadorCustomDefects);

        defectosNamesSelecion.addAll(Arrays.asList(arrayuDefectsSeleccNames));

        hasmapOfListwhitNamesFefectsCurrentControlCalidadFOmr.put( contador ,defectosNamesSelecion);
        Log.i("suoerdefect", "el dince contador es  "+(contador-1));


        int numRestaR=0;


        if(listNumsCustomDefects.get(contador-1)==0){

            numRestaR=numRestaR+3;

        }

        else if(listNumsCustomDefects.get(contador-1)==1){

            numRestaR=numRestaR+2;

        }


        else if(listNumsCustomDefects.get(contador-1)==2){

            numRestaR=numRestaR+1;

        }


        Log.i("eomcnezamos ", "aqui le vamos a restar  "+numRestaR);

        // Log.i("suoerdefect", "vamos a sumar: "+numSumarDefecto+" el num defectos defecto es "+);



        Log.i("misdataxx", "EL SIZSE DEl array es "+defectosNamesSelecion.size());

        //copiamos los defectos


        Log.i("misdataxx", "EL SIZSE DE list now es "+defectosNamesSelecion.size());


        for(String value: defectosNamesSelecion){
            Log.i("eomcnezamos ", "EL value es  "+value);
        }

        Log.i("suertexxx ", "el defect hasmpa size es  "+hashMapDefctChecked.size());


        /**cremoas los objetos defectcantidad y los agregamos a la lista**/

        //ITERAMOS EL HASMPA E IMPRIMOS KEYS AND VALUES..
        for (HashMap.Entry<String, String> entry : hashMapDefctChecked.entrySet()) {

            String key = entry.getKey();
            String value = entry.getValue();

            Log.i("simpson", "el key es  "+key);

            Log.i("simpson", "el value es  "+value);

        }



        for(int i=0; i<keyDatsNumDefectSelecion.length ;i++) {

            //iteramos arry con las keys
            String keyCurrent= String.valueOf(keyDatsNumDefectSelecion[i])  ;

            if(hashMapDefctChecked.containsKey(keyCurrent)){

                String value =hashMapDefctChecked.get(keyCurrent);

                Log.i("simpson", "el value es "+value);

                if(value.contains(",")){

                    String [] posicionDefectoEncontrados=value.split(",");

                    for(int indice2=0; indice2<posicionDefectoEncontrados.length ;indice2++){
                        String   [] arrayIndiceAndNum =posicionDefectoEncontrados[indice2].split("-");
                        int posicionDefecto=Integer.parseInt(arrayIndiceAndNum[0]);//

                         if(arrayIndiceAndNum.length==3){

                             defectsSeleccionList.add(new DefectsCantdad(Integer.parseInt(arrayIndiceAndNum[1]),arrayIndiceAndNum[2]));
                             Log.i("suoerdefect", "se contro : "+arrayIndiceAndNum[1]+" y e; edfecto es "+arrayIndiceAndNum[2]);

                         }

                         else

                         {

                             //posicion 0 yPosicion 1 example//en caos qu queramos la fila podemos usar el nuemro defe4ctos por ahora esta en 0
                             defectsSeleccionList.add(new DefectsCantdad(Integer.parseInt(arrayIndiceAndNum[1]),defectosNamesSelecion.get(posicionDefecto-numRestaR)));

                             Log.i("suoerdefect", "se contro : "+arrayIndiceAndNum[1]+" numero defectos en la posicion "+(posicionDefecto-numRestaR)+
                                     "y el nombre es "+ defectosNamesSelecion.get(posicionDefecto-numRestaR));


                         }



                    }

                }else {
                    String   [] arrayIndiceAndNum =value.split("-");

                    int posicionDefecto=Integer.parseInt(arrayIndiceAndNum[0]);



                    if(arrayIndiceAndNum.length==3){

                        defectsSeleccionList.add(new DefectsCantdad(Integer.parseInt(arrayIndiceAndNum[1]),arrayIndiceAndNum[2]));
                        Log.i("simpson", "el defcet name selecion es herees  "+arrayIndiceAndNum[2]);


                    }else{
                        //posicion 0 yPosicion 1 example//en caos qu queramos la fila podemos usar el nuemro defe4ctos por ahora esta en 0
                        defectsSeleccionList.add(new DefectsCantdad(Integer.parseInt(arrayIndiceAndNum[1]),defectosNamesSelecion.get(posicionDefecto-numRestaR)));


                        Log.i("suoerdefect", "se contro : "+arrayIndiceAndNum[1]+" numero defectos en la posicion "+(posicionDefecto-numRestaR)+ "y el nombre es "+
                                defectosNamesSelecion.get(posicionDefecto-numRestaR));
                    }


                }

                //cremoas un array con el valor de ese string..

            }

        }


       /*** el erro esta aqui pilaxxx*/
        Log.i("suertexxx ", "el lista con defectos es   "+defectsSeleccionList.size());


        defectsSelecionHahashMaps.put(String.valueOf(contador-1),defectsSeleccionList);


       // Log.i("SUEPREME","El size de defect selecion es "+defectsSelecionHahashMaps.size()+"el key es  "+(contador-1));


        /**DEFECTOS EMPAQUE*/



        String   [] arrayuDefectSeMPAQUENames =contexto.getResources().getStringArray(R.array.array_defectos_empaque2);



        for(int i=0; i<arrayWhitKeysDefectsEmmpaque.length ;i++) { ///recorremos el numero de keys que hay para defects empaque
            //iteramos arry con las keys
            String keyCurrent= String.valueOf(arrayWhitKeysDefectsEmmpaque[i])  ;

            if(hashMapDefctChecked.containsKey(keyCurrent)){

                String value =hashMapDefctChecked.get(keyCurrent);

                String [] defectsPosicionSeparateByCommma=value.split(",");

                //iteramos este array


                for(int indice2=0; indice2<defectsPosicionSeparateByCommma.length ;indice2++){


                    String   [] arrayIndiceAndNum =defectsPosicionSeparateByCommma[indice2].split("-");

                    int posicionDefecto=Integer.parseInt(arrayIndiceAndNum[0]);

                    //obtenemos la poscion de este dfecto,, recuerda que gaurdamos un set de defectos  en un string con commas..
                  //  int posicionDefecto=Integer.parseInt(defectsPosicionSeparateByCommma [indice2]);
                    //posicion 0 yPosicion 1 example//en caos qu queramos la fila podemos usar el nuemro defe4ctos por ahora esta en 0

                        //guardamos el nombre de este defecto

                    Log.i("eldefecta","el defecto selecioando es "+arrayuDefectSeMPAQUENames[posicionDefecto]);
                    defectsEmpaque.add(new DefectsCantdad(Integer.parseInt(arrayIndiceAndNum[1]),arrayuDefectSeMPAQUENames[posicionDefecto]));

                }


            }

        }






        defectsEmpaqueHashMapOfLists.put(String.valueOf(contador-1),defectsEmpaque);

        NUMERO_DEFECTS=getNumDefectsAll(defectsSeleccionList, defectsEmpaque);

        /***obtenemos el mayor defecto empaque ahora*/



                //obtenemos los nombre de los defectos que selecionaron
        ArrayList<String>defectsSelecNames = new ArrayList<>();

        for(int indice2=0; indice2<defectsSeleccionList.size() ;indice2++){
          //obtenemos la lista de defectos..

               if(!defectsSelecNames.contains(defectsSeleccionList.get(indice2).getNombreDefect())){

                   defectsSelecNames.add(defectsSeleccionList.get(indice2).getNombreDefect());

               }


        }




        /**OBTIENE MAYOR DEFECTO selecion pilas cuando sean defectos iguales*/

        String nombreMayorDefectoSelecion="NO";
        int numeroMayorDefectoselecion=0;
        int numeroDfectosCurrentDefectSelecion;

        for(int indice2=0; indice2<defectsSelecNames.size() ;indice2++){
            numeroDfectosCurrentDefectSelecion=0;

            //buscamos cada uno de  los nombre selecionados como defectos
            String currenTnameDefectToContar=defectsSelecNames.get(indice2);

            Log.i("elmayirr","buscamos este defecto "+currenTnameDefectToContar);


            for(int indice3=0; indice3<defectsSeleccionList.size() ;indice3++){
                Log.i("elmayirr","buscamos este defecto "+defectsSeleccionList.get(indice3).getNombreDefect());

                if(defectsSeleccionList.get(indice3).getNombreDefect().equals(currenTnameDefectToContar)){
                    numeroDfectosCurrentDefectSelecion=numeroDfectosCurrentDefectSelecion+defectsSeleccionList.get(indice3).getNumeroDefectos();
                }
            }


            if(numeroDfectosCurrentDefectSelecion>numeroMayorDefectoselecion){
                numeroMayorDefectoselecion=numeroDfectosCurrentDefectSelecion;
                nombreMayorDefectoSelecion=currenTnameDefectToContar;

            }

        }



        MAYOR_DEFECTO_SELECCION=nombreMayorDefectoSelecion;

        if(MAYOR_DEFECTO_SELECCION.contains(":")){

            String [] array=MAYOR_DEFECTO_SELECCION.split(":");
            MAYOR_DEFECTO_SELECCION=array[0];

        }
        Log.i("elmayirr","el mayor defecto SELECION NOW es "+MAYOR_DEFECTO_SELECCION);





        /**numero de CLUSTERS INSPECCIONADOS */

        NUMERO_DE_CLUSTERS_iNSPECCIONADOS= objecControlCald.getNumeroClustersInspeccioandos();

        Log.i("ELWEIGTH","EN TOTAL DE numClustersInspeccinads  ES "+NUMERO_DE_CLUSTERS_iNSPECCIONADOS);


        Log.i("ELWEIGTH","A TEXT1 ES "+NUMERO_DEFECTS);
        Log.i("ELWEIGTH","A TEXT2 ES "+NUMERO_DE_CLUSTERS_iNSPECCIONADOS);




       // Log.i("cality es ","el CALIDAD_TOTAL es "+objecControlCald.getCalidaCamp());

        CALIDAD_TOTAL=objecControlCald.getCalidaCamp();
        Log.i("cality es ","el CALIDAD_TOTAL  down es es "+CALIDAD_TOTAL);

        PORCENTAJE_DE_DEFECTOS=100-CALIDAD_TOTAL;

       // PORCENTAJE_DE_DEFECTOS=((double)NUMERO_DEFECTS/100) * NUMERO_DE_CLUSTERS_iNSPECCIONADOS;


        //SDFDSHJ

        Log.i("ELWEIGTH","el CALIDAD_TOTAL es "+CALIDAD_TOTAL);


        /***MAYOR DEFECTO empaque OBTENIENDO NOMBRES */

        //los nombre de los defectos que selecionaron
     defectsSelecNames = new ArrayList<>();

        for(int indice2=0; indice2<defectsEmpaque.size() ;indice2++){
            //obtenemos la lista de defectos..

            if(!defectsSelecNames.contains(defectsEmpaque.get(indice2).getNombreDefect())){

                defectsSelecNames.add(defectsEmpaque.get(indice2).getNombreDefect());
            }

        }


        /**OBTIENE MAYOR DEFECTO EMPAQUE*/

         String nombreMayorDefecto="NO";
        int numeroMayorDefecto=0;
        int numeroDfectosCurrentDefectx;


        for(int indice2=0; indice2<defectsSelecNames.size() ;indice2++){
            numeroDfectosCurrentDefectx=0;

             //buscamos cada uno de  los nombre selecionados como defectos
            String currenTnameDefectToContar=defectsSelecNames.get(indice2);

            Log.i("elmayirr","buscamos este defecto "+currenTnameDefectToContar);


            for(int indice3=0; indice3<defectsEmpaque.size() ;indice3++){
                Log.i("elmayirr","buscamos este defecto "+defectsEmpaque.get(indice3).getNombreDefect());

                if(defectsEmpaque.get(indice3).getNombreDefect().equals(currenTnameDefectToContar)){
                    numeroDfectosCurrentDefectx=numeroDfectosCurrentDefectx+defectsEmpaque.get(indice3).getNumeroDefectos();
                }
            }

            if(numeroDfectosCurrentDefectx>numeroMayorDefecto){
                numeroMayorDefecto=numeroDfectosCurrentDefectx;
                nombreMayorDefecto=currenTnameDefectToContar;

            }



        }


        MAYOR_DEFECTO_EMPAQUE=nombreMayorDefecto;
        Log.i("elmayirr","el mayor defecto es "+nombreMayorDefecto);






        ///entonces vamos a crear la tabla
      DecimalFormat df = new DecimalFormat("#.##");





        Log.i("debugderor","el calidad total es "+CALIDAD_TOTAL);

        TableCalidProdc.add(new TableCalidProdc(objecControlCald.getTipoEmpaque()+" "+objecControlCald.getMarcaCaja(),objecControlCald.getTotalCajas(),CALIDAD_TOTAL, objecControlCald.getCodigo(),
                objecControlCald.getMarcaCaja()));



       Table table1 =  createTableWhitDateEvaluacionFrura(objecControlCald.getMarcaCaja(),df.format(PROMEDIO_PESO),
               df.format(CALIDAD_TOTAL)+"%",df.format(PORCENTAJE_DE_DEFECTOS)+"%",String.valueOf(NUMERO_DEFECTS),String.valueOf(MAYOR_DEFECTO_SELECCION),
               String.valueOf(MAYOR_DEFECTO_EMPAQUE),String.valueOf(NUMERO_DE_CLUSTERS_POR_CAJA),String.valueOf(NUMERO_DE_DEDOS),df.format(GRADO_CALIBRE_PROMEDIO)+" grados",
               df.format(LARGO_DEDOS_PROMEDIO)+" pulgadas","3.0" //estaba en PH_PROMEDIO
               );

        return table1;



    }


    public static  Table createTableEvaluacionYcondcionFrutaCmnsyCarretas(ControlCalidad objecControlCald,HashMap <String, String> hashMapControlCald,HashMap <String, String> hashMapDefctChecked,Context contexto,int contador) {
        //aqui creamos la info con esta data.....

        ArrayList<Double> listPh = new ArrayList<>();
        ArrayList<DefectsCantdad> defectsSeleccionList = new ArrayList<>();
        ArrayList<DefectsCantdad> defectsEmpaque = new ArrayList<>();

        //DATOS QUE NECESITAMOS OBTENERdoub
        double PROMEDIO_PESO = 0;

        int NUMERO_DEFECTS;
        String MAYOR_DEFECTO_SELECCION = "NO";
        String MAYOR_DEFECTO_EMPAQUE = "NO";
        int NUMERO_DE_CLUSTERS_iNSPECCIONADOS = 0;
        int NUMERO_DE_CLUSTERS_POR_CAJA=0;

        int NUMERO_DE_DEDOS=0;
        double GRADO_CALIBRE_PROMEDIO;
        double LARGO_DEDOS_PROMEDIO;



        int[] keyDatsPeso = {R.id.ediPesoL1, R.id.ediPesoL2, R.id.ediPesoL3, R.id.ediPesoL4, R.id.ediPesoL5, R.id.ediPesoL6, R.id.ediPesoL7,
                R.id.ediPesoL8, R.id.ediPesoL9, R.id.ediPesoL10};


        int[] keyDatsNumClusters = {R.id.ediNumClusInsp1, R.id.ediNumClusInsp2, R.id.ediNumClusInsp3, R.id.ediNumClusInsp4,
                R.id.ediNumClusInsp5, R.id.ediNumClusInsp6, R.id.ediNumClusInsp7, R.id.ediNumClusInsp8, R.id.ediNumClusInsp9, R.id.ediNumClusInsp10};


        int[] keyDatsNumDefectSelecion = {R.id.imgSelecDefc1, R.id.imgSelecDefc2, R.id.imgSelecDefc3, R.id.imgSelecDefc4,
                R.id.imgSelecDefc5, R.id.imgSelecDefc6, R.id.imgSelecDefc7, R.id.imgSelecDefc8, R.id.imgSelecDefc9, R.id.imgSelecDefc10};


        int[] arrayWhitKeysDefectsEmmpaque = {R.id.imvEmpaque1, R.id.imvEmpaque2, R.id.imvEmpaque3, R.id.imvEmpaque4,
                R.id.imvEmpaque5, R.id.imvEmpaque6, R.id.imvEmpaque7, R.id.imvEmpaque8, R.id.imvEmpaque9, R.id.imvEmpaque10};


        int[] keyaRRAYnumClustXcajaLine1 = {R.id.edif2NdedoXclustxC1, R.id.edif2NdedoXclustxC2, R.id.edif2NdedoXclustxC3, R.id.edif2NdedoXclustxC4,
                R.id.edif2NdedoXclustxC5, R.id.edif2NdedoXclustxC6, R.id.edif2NdedoXclustxC7, R.id.edif2NdedoXclustxC8, R.id.edif2NdedoXclustxC9, R.id.edif2NdedoXclustxC10
                , R.id.edif2NdedoXclustxC11, R.id.edif2NdedoXclustxC12, R.id.edif2NdedoXclustxC13, R.id.edif2NdedoXclustxC14, R.id.edif2NdedoXclustxC15, R.id.edif2NdedoXclustxC16,
                R.id.edif2NdedoXclustxC17, R.id.edif2NdedoXclustxC18};





        int[] keyaRRAYnumClustXcajaLine2 = {R.id.ediNdedoXclustXc1, R.id.ediNdedoXclustXc2, R.id.ediNdedoXclustXc3, R.id.ediNdedoXclustXc4,
                R.id.ediNdedoXclustXc5, R.id.ediNdedoXclustXc6, R.id.ediNdedoXclustXc7, R.id.ediNdedoXclustXc8, R.id.ediNdedoXclustXc9, R.id.ediNdedoXclustXc10
                , R.id.ediNdedoXclustXc11, R.id.ediNdedoXclustXc12, R.id.ediNdedoXclustXc13, R.id.ediNdedoXclustXc14, R.id.ediNdedoXclustXc15, R.id.ediNdedoXclustXc16
                , R.id.ediNdedoXclustXc17, R.id.ediNdedoXclustXc18};




        int[] keyaRRAYcalibracionesFil1 = {

                R.id.ediCalByA1, R.id.ediCalByA2, R.id.ediCalByA3, R.id.ediCalByA4,
                R.id.ediCalByA5, R.id.ediCalByA6, R.id.ediCalByA7, R.id.ediCalByA8, R.id.ediCalByA9, R.id.ediCalByA10
                , R.id.ediCalByA11, R.id.ediCalByA12, R.id.ediCalByA13, R.id.ediCalByA14, R.id.ediCalByA15, R.id.ediCalByA16,
                R.id.ediCalByA17, R.id.ediCalByA18

        };


        int[] keyaRRAYcalibracionesFil2 = {
                R.id.edif2Calib1, R.id.edif2Calib2, R.id.edif2Calib3, R.id.edif2Calib4,
                R.id.edif2Calib5, R.id.edif2Calib6, R.id.edif2Calib7, R.id.edif2Calib8, R.id.edif2Calib9, R.id.edif2Calib10
                , R.id.edif2Calib11, R.id.edif2Calib12, R.id.edif2Calib13, R.id.edif2Calib14, R.id.edif2Calib15, R.id.edif2Calib16,
                R.id.edif2Calib17, R.id.edif2Calib18
        };


        int[] keyaRRAYlargoFil1 = {
                R.id.ediLargDeds1, R.id.ediLargDeds2, R.id.ediLargDeds3, R.id.ediLargDeds4,
                R.id.ediLargDeds5, R.id.ediLargDeds6, R.id.ediLargDeds7, R.id.ediLargDeds8, R.id.ediLargDeds9, R.id.ediLargDeds10
                , R.id.ediLargDeds11, R.id.ediLargDeds12, R.id.ediLargDeds13, R.id.ediLargDeds14, R.id.ediLargDeds15, R.id.ediLargDeds16,
                R.id.ediLargDeds17, R.id.ediLargDeds18, R.id.ediLargDeds19, R.id.ediLargDeds20, R.id.ediLargDeds21, R.id.ediLargDeds22
                , R.id.ediLargDeds23, R.id.ediLargDeds24, R.id.ediLargDeds25, R.id.ediLargDeds26, R.id.ediLargDeds27, R.id.ediLargDeds28
                , R.id.ediLargDeds29, R.id.ediLargDeds30};


        int[] keyaRRAYlargoFil2 = {R.id.edif2LrgD1, R.id.edif2LrgD2, R.id.edif2LrgD3, R.id.edif2LrgD4,
                R.id.edif2LrgD5, R.id.edif2LrgD6, R.id.edif2LrgD7, R.id.edif2LrgD8, R.id.edif2LrgD9, R.id.edif2LrgD10
                , R.id.edif2LrgD11, R.id.edif2LrgD12, R.id.edif2LrgD13, R.id.edif2LrgD14, R.id.edif2LrgD15, R.id.edif2LrgD16,
                R.id.edif2LrgD17, R.id.edif2LrgD18, R.id.edif2LrgD19, R.id.edif2LrgD20, R.id.edif2LrgD21, R.id.edif2LrgD22
                , R.id.edif2LrgD23, R.id.edif2LrgD24, R.id.edif2LrgD25, R.id.edif2LrgD26, R.id.edif2LrgD27, R.id.edif2LrgD28
                , R.id.edif2LrgD29, R.id.edif2LrgD30};


        int resultLine1;
        int resultLine2;

        double resultLine1Double = 0;
        double resultLine2Double = 0;

        for (int i = 0; i < keyaRRAYlargoFil1.length; i++) {
            //keyDatsPesoarray
            //itremoas
            String keyCurrent1 = String.valueOf(keyaRRAYlargoFil1[i]);
            if (hashMapControlCald.containsKey(keyCurrent1)) {
                resultLine1Double = resultLine1Double + Double.parseDouble(hashMapControlCald.get(keyCurrent1));
            }

            String keyCurrent2 = String.valueOf(keyaRRAYlargoFil2[i]);
            if (hashMapControlCald.containsKey(keyCurrent2)) {
                resultLine2Double = resultLine2Double + Double.parseDouble(hashMapControlCald.get(keyCurrent2));

            }


        }

        /**gheneramos numero de LARGO_DEDOS_PROMEDIO*/
        LARGO_DEDOS_PROMEDIO = resultLine2Double / resultLine1Double;

        Log.i("ELWEIGTH", "EL LARGO_DEDOS_PROMEDIO  ES " + LARGO_DEDOS_PROMEDIO);

        resultLine1Double = 0;
        resultLine2Double = 0;

        for (int i = 0; i < keyaRRAYcalibracionesFil1.length; i++) {
            //keyDatsPesoarray
            //itremoas
            String keyCurrent1 = String.valueOf(keyaRRAYcalibracionesFil1[i]);
            if (hashMapControlCald.containsKey(keyCurrent1)) {
                resultLine1Double = resultLine1Double + Double.parseDouble(hashMapControlCald.get(keyCurrent1));
            }

            String keyCurrent2 = String.valueOf(keyaRRAYcalibracionesFil2[i]);
            if (hashMapControlCald.containsKey(keyCurrent2)) {
                resultLine2Double = resultLine2Double + Double.parseDouble(hashMapControlCald.get(keyCurrent2));

            }


        }

        /**gheneramos numero de GRADO_CALIBRE_PROMEDIO*/
        GRADO_CALIBRE_PROMEDIO = resultLine2Double / resultLine1Double;

        Log.i("ELWEIGTH", "EL GRADO_CALIBRE_PROMEDIO  PROMEDIO ES " + GRADO_CALIBRE_PROMEDIO);


        resultLine1 = 0;
        resultLine2 = 0;


        /*
        for (int i = 0; i < keyaRRAYnumnUMdedosFil1.length; i++) {
            //keyDatsPesoarray
            //itremoas
            String keyCurrent1 = String.valueOf(keyaRRAYnumnUMdedosFil1[i]);
            if (hashMapControlCald.containsKey(keyCurrent1)) {

                Log.i("derddssd"," el value es "+hashMapControlCald.get(keyCurrent1));

                resultLine1 = resultLine1 + Integer.parseInt(hashMapControlCald.get(keyCurrent1));
            }

            String keyCurrent2 = String.valueOf(keyaRRAYnumnUMdedosFil2[i]);
            if (hashMapControlCald.containsKey(keyCurrent2)) {

                resultLine2 = resultLine2 + Integer.parseInt(hashMapControlCald.get(keyCurrent2));

            }

        }
        /*


        /**gheneramos numero de NUMERO_DE_DEDOS*/


        if(!objecControlCald.getNumeroDedosXclusterOmano().equals("")){


            //10:03:22.277  W  java.lang.NumberFormatException: For input string: "10,1"


            String flotanteX=objecControlCald.getNumeroDedosXclusterOmano();
            Log.i("flotantedd", "EL flotante String es " + flotanteX);


            NUMERO_DE_DEDOS=(int)(Float.parseFloat(objecControlCald.getNumeroDedosXclusterOmano()));



        }

        // NUMERO_DE_DEDOS = resultLine2 / resultLine1; //ediNdedoXclustXc20 //here aqui....


        Log.i("ELWEIGTH", "EL NUMERO_DE_DEDOS  PROMEDIO ES " + NUMERO_DE_DEDOS);


        resultLine1 = 0;
        resultLine2 = 0;

        for (int i = 0; i < keyaRRAYnumClustXcajaLine1.length; i++) {
            //keyDatsPesoarray
            //itremoas
            String keyCurrent1 = String.valueOf(keyaRRAYnumClustXcajaLine1[i]);
            if (hashMapControlCald.containsKey(keyCurrent1)) {
                resultLine1 = resultLine1 + Integer.parseInt(hashMapControlCald.get(keyCurrent1));
            }



            String keyCurrent2 = String.valueOf(keyaRRAYnumClustXcajaLine2[i]);
            if (hashMapControlCald.containsKey(keyCurrent2)) {
                resultLine2 = resultLine2 + Integer.parseInt(hashMapControlCald.get(keyCurrent2));

            }


        }

        /**gheneramos numero de CLUSTERS*/


        if(resultLine1>0 || resultLine2>0){
            NUMERO_DE_CLUSTERS_POR_CAJA = resultLine2 / resultLine1;

        }


        //  Log.i("ELWEIGTH", "EN NUMERO_DE_CLUSTERS_POR_CAJA  PROMEDIO ES " + NUMERO_DE_CLUSTERS_POR_CAJA);


        ///            listTOrETURN1.add(new NameAndValue("DESTINO",Object1.getDestinoContenedor()));

        /**GENERAMNOS PROMOEDIO PESO*/
        int numeroItemsEcontrados = 0;

        for (int i = 0; i < keyDatsPeso.length; i++) {
            //keyDatsPesoarray
            //itremoas
            String keyCurrent = String.valueOf(keyDatsPeso[i]);

            if (hashMapControlCald.containsKey(keyCurrent)) {

                PROMEDIO_PESO = PROMEDIO_PESO + Double.parseDouble(hashMapControlCald.get(keyCurrent));
                numeroItemsEcontrados++;

            }

        }
        //tenemos que redondiar el peso lñibras
        PROMEDIO_PESO = PROMEDIO_PESO / numeroItemsEcontrados;

        Log.i("ELWEIGTH", "EN PESO PROMEDIO ES " + PROMEDIO_PESO);

        /**DEFECTOS SELECION*/
        String[] arrayuDefectsSeleccNames = contexto.getResources().getStringArray(R.array.array_defectos_frutselecion); //estaba en ararydefectx

        ArrayList<String> defectosNamesSelecion = new ArrayList<>(); //contendra todos los nombres de defectos selecion


        int contadorCustomDefects=0;
        for (String valuex : hashMapDefctChecked.values()) { //el custom defecto contiene hasta 3 length size..

            Log.i("hasmpadefectchekeed", "la data es " + valuex);

            /**buscamos otros defcetos names y si hay los agregamos a la lista de names **/


            String[] arrayDefectsAnCantidadx = valuex.split("-"); // puede contener esto  1-4, o esto  5-2, 5-8,9-9 , 5-6-adriano


            if (valuex.contains(",")) { //si contiene una comma

                String[] arrayDefectsAnCantidad = valuex.split(","); // puede contener esto  1-4, o esto  5-2, 5-8,9-9 , 5-6-adriano

                for(int indicec=0; indicec<arrayDefectsAnCantidad.length; indicec++){ //itreamos el array qu contiene  valores asi: 2-5 o 9-8 o 8,8,adriano

                    Log.i("howartsxx", "el string es  "+ arrayDefectsAnCantidad[indicec]);

                    String[] arrayIndiceAndNum = arrayDefectsAnCantidad[indicec].split("-");


                    if(arrayIndiceAndNum.length==3){ //si tiene 3 es custom name
                        Log.i("howartsxx", "encontramos 1 aqui y es  "+arrayIndiceAndNum[2]);

                        if(!defectosNamesSelecion.contains(arrayIndiceAndNum[2])){

                            if(!defectosNamesSelecion.contains(arrayIndiceAndNum[2])){

                                defectosNamesSelecion.add(contadorCustomDefects,arrayIndiceAndNum[2]);

                                contadorCustomDefects++;


                                Log.i("howartsxx", "agregamos y el size es  "+defectosNamesSelecion.size());
                                Log.i("howartsxx", "EL SIZSE DE LA LISTA AQUI ES  "+defectosNamesSelecion.size());

                            }


                            //  Log.i("howartsxx", "lo agregamos");
                        }

                    }


                }

            }

            else if(arrayDefectsAnCantidadx.length==3){

                for(int indicec=0; indicec<arrayDefectsAnCantidadx.length; indicec++){ //itreamos el array qu contiene  valores asi: 2-5 o 9-8 o 8,8,adriano

                    Log.i("howartsxx", "el string es  "+ arrayDefectsAnCantidadx[indicec]);
                    Log.i("howartsxx", "encontramos 1 aqui y es  "+arrayDefectsAnCantidadx[2]);

                    if(!defectosNamesSelecion.contains(arrayDefectsAnCantidadx[2])){

                        if(!defectosNamesSelecion.contains(arrayDefectsAnCantidadx[2])){

                            defectosNamesSelecion.add(contadorCustomDefects,arrayDefectsAnCantidadx[2]);

                            contadorCustomDefects++;

                            Log.i("howartsxx", "agregamos y el size es  "+defectosNamesSelecion.size());
                            Log.i("howartsxx", "EL SIZSE DE LA LISTA AQUI ES  "+defectosNamesSelecion.size());

                        }


                        //  Log.i("howartsxx", "lo agregamos");
                    }




                }


            }



        }


        listNumsCustomDefects.add(contadorCustomDefects);

        defectosNamesSelecion.addAll(Arrays.asList(arrayuDefectsSeleccNames));

        hasmapOfListwhitNamesFefectsCurrentControlCalidadFOmr.put( contador ,defectosNamesSelecion);
        Log.i("suoerdefect", "el dince contador es  "+(contador-1));


        int numRestaR=0;


        if(listNumsCustomDefects.get(contador-1)==0){

            numRestaR=numRestaR+3;

        }

        else if(listNumsCustomDefects.get(contador-1)==1){

            numRestaR=numRestaR+2;

        }


        else if(listNumsCustomDefects.get(contador-1)==2){

            numRestaR=numRestaR+1;

        }


        Log.i("eomcnezamos ", "aqui le vamos a restar  "+numRestaR);

        // Log.i("suoerdefect", "vamos a sumar: "+numSumarDefecto+" el num defectos defecto es "+);



        Log.i("misdataxx", "EL SIZSE DEl array es "+defectosNamesSelecion.size());

        //copiamos los defectos


        Log.i("misdataxx", "EL SIZSE DE list now es "+defectosNamesSelecion.size());


        for(String value: defectosNamesSelecion){
            Log.i("eomcnezamos ", "EL value es  "+value);
        }

        Log.i("suertexxx ", "el defect hasmpa size es  "+hashMapDefctChecked.size());


        /**cremoas los objetos defectcantidad y los agregamos a la lista**/

        //ITERAMOS EL HASMPA E IMPRIMOS KEYS AND VALUES..
        for (HashMap.Entry<String, String> entry : hashMapDefctChecked.entrySet()) {

            String key = entry.getKey();
            String value = entry.getValue();

            Log.i("simpson", "el key es  "+key);

            Log.i("simpson", "el value es  "+value);

        }



        for(int i=0; i<keyDatsNumDefectSelecion.length ;i++) {

            //iteramos arry con las keys
            String keyCurrent= String.valueOf(keyDatsNumDefectSelecion[i])  ;

            if(hashMapDefctChecked.containsKey(keyCurrent)){

                String value =hashMapDefctChecked.get(keyCurrent);

                Log.i("simpson", "el value es "+value);

                if(value.contains(",")){

                    String [] posicionDefectoEncontrados=value.split(",");

                    for(int indice2=0; indice2<posicionDefectoEncontrados.length ;indice2++){
                        String   [] arrayIndiceAndNum =posicionDefectoEncontrados[indice2].split("-");
                        int posicionDefecto=Integer.parseInt(arrayIndiceAndNum[0]);//

                        if(arrayIndiceAndNum.length==3){

                            defectsSeleccionList.add(new DefectsCantdad(Integer.parseInt(arrayIndiceAndNum[1]),arrayIndiceAndNum[2]));
                            Log.i("suoerdefect", "se contro : "+arrayIndiceAndNum[1]+" y e; edfecto es "+arrayIndiceAndNum[2]);

                        }

                        else

                        {

                            //posicion 0 yPosicion 1 example//en caos qu queramos la fila podemos usar el nuemro defe4ctos por ahora esta en 0
                            defectsSeleccionList.add(new DefectsCantdad(Integer.parseInt(arrayIndiceAndNum[1]),defectosNamesSelecion.get(posicionDefecto-numRestaR)));

                            Log.i("suoerdefect", "se contro : "+arrayIndiceAndNum[1]+" numero defectos en la posicion "+(posicionDefecto-numRestaR)+
                                    "y el nombre es "+ defectosNamesSelecion.get(posicionDefecto-numRestaR));


                        }



                    }

                }else {
                    String   [] arrayIndiceAndNum =value.split("-");

                    int posicionDefecto=Integer.parseInt(arrayIndiceAndNum[0]);



                    if(arrayIndiceAndNum.length==3){

                        defectsSeleccionList.add(new DefectsCantdad(Integer.parseInt(arrayIndiceAndNum[1]),arrayIndiceAndNum[2]));
                        Log.i("simpson", "el defcet name selecion es herees  "+arrayIndiceAndNum[2]);


                    }else{
                        //posicion 0 yPosicion 1 example//en caos qu queramos la fila podemos usar el nuemro defe4ctos por ahora esta en 0
                        defectsSeleccionList.add(new DefectsCantdad(Integer.parseInt(arrayIndiceAndNum[1]),defectosNamesSelecion.get(posicionDefecto-numRestaR)));


                        Log.i("suoerdefect", "se contro : "+arrayIndiceAndNum[1]+" numero defectos en la posicion "+(posicionDefecto-numRestaR)+ "y el nombre es "+
                                defectosNamesSelecion.get(posicionDefecto-numRestaR));
                    }


                }

                //cremoas un array con el valor de ese string..

            }

        }


        /*** el erro esta aqui pilaxxx*/
        Log.i("suertexxx ", "el lista con defectos es   "+defectsSeleccionList.size());


        defectsSelecionHahashMaps.put(String.valueOf(contador-1),defectsSeleccionList);


        // Log.i("SUEPREME","El size de defect selecion es "+defectsSelecionHahashMaps.size()+"el key es  "+(contador-1));


        /**DEFECTOS EMPAQUE*/



        String   [] arrayuDefectSeMPAQUENames =contexto.getResources().getStringArray(R.array.array_defectos_empaque2);



        for(int i=0; i<arrayWhitKeysDefectsEmmpaque.length ;i++) { ///recorremos el numero de keys que hay para defects empaque
            //iteramos arry con las keys
            String keyCurrent= String.valueOf(arrayWhitKeysDefectsEmmpaque[i])  ;

            if(hashMapDefctChecked.containsKey(keyCurrent)){

                String value =hashMapDefctChecked.get(keyCurrent);

                String [] defectsPosicionSeparateByCommma=value.split(",");

                //iteramos este array


                for(int indice2=0; indice2<defectsPosicionSeparateByCommma.length ;indice2++){


                    String   [] arrayIndiceAndNum =defectsPosicionSeparateByCommma[indice2].split("-");

                    int posicionDefecto=Integer.parseInt(arrayIndiceAndNum[0]);

                    //obtenemos la poscion de este dfecto,, recuerda que gaurdamos un set de defectos  en un string con commas..
                    //  int posicionDefecto=Integer.parseInt(defectsPosicionSeparateByCommma [indice2]);
                    //posicion 0 yPosicion 1 example//en caos qu queramos la fila podemos usar el nuemro defe4ctos por ahora esta en 0

                    //guardamos el nombre de este defecto

                    Log.i("eldefecta","el defecto selecioando es "+arrayuDefectSeMPAQUENames[posicionDefecto]);
                    defectsEmpaque.add(new DefectsCantdad(Integer.parseInt(arrayIndiceAndNum[1]),arrayuDefectSeMPAQUENames[posicionDefecto]));

                }


            }

        }






        defectsEmpaqueHashMapOfLists.put(String.valueOf(contador-1),defectsEmpaque);

        NUMERO_DEFECTS=getNumDefectsAll(defectsSeleccionList, defectsEmpaque);

        /***obtenemos el mayor defecto empaque ahora*/



        //obtenemos los nombre de los defectos que selecionaron
        ArrayList<String>defectsSelecNames = new ArrayList<>();

        for(int indice2=0; indice2<defectsSeleccionList.size() ;indice2++){
            //obtenemos la lista de defectos..

            if(!defectsSelecNames.contains(defectsSeleccionList.get(indice2).getNombreDefect())){

                defectsSelecNames.add(defectsSeleccionList.get(indice2).getNombreDefect());

            }


        }




        /**OBTIENE MAYOR DEFECTO selecion pilas cuando sean defectos iguales*/

        String nombreMayorDefectoSelecion="NO";
        int numeroMayorDefectoselecion=0;
        int numeroDfectosCurrentDefectSelecion;

        for(int indice2=0; indice2<defectsSelecNames.size() ;indice2++){
            numeroDfectosCurrentDefectSelecion=0;

            //buscamos cada uno de  los nombre selecionados como defectos
            String currenTnameDefectToContar=defectsSelecNames.get(indice2);

            Log.i("elmayirr","buscamos este defecto "+currenTnameDefectToContar);


            for(int indice3=0; indice3<defectsSeleccionList.size() ;indice3++){
                Log.i("elmayirr","buscamos este defecto "+defectsSeleccionList.get(indice3).getNombreDefect());

                if(defectsSeleccionList.get(indice3).getNombreDefect().equals(currenTnameDefectToContar)){
                    numeroDfectosCurrentDefectSelecion=numeroDfectosCurrentDefectSelecion+defectsSeleccionList.get(indice3).getNumeroDefectos();
                }
            }


            if(numeroDfectosCurrentDefectSelecion>numeroMayorDefectoselecion){
                numeroMayorDefectoselecion=numeroDfectosCurrentDefectSelecion;
                nombreMayorDefectoSelecion=currenTnameDefectToContar;

            }

        }



        MAYOR_DEFECTO_SELECCION=nombreMayorDefectoSelecion;

        if(MAYOR_DEFECTO_SELECCION.contains(":")){

            String [] array=MAYOR_DEFECTO_SELECCION.split(":");
            MAYOR_DEFECTO_SELECCION=array[0];

        }
        Log.i("elmayirr","el mayor defecto SELECION NOW es "+MAYOR_DEFECTO_SELECCION);





        /**numero de CLUSTERS INSPECCIONADOS */

        NUMERO_DE_CLUSTERS_iNSPECCIONADOS= objecControlCald.getNumeroClustersInspeccioandos();

        Log.i("ELWEIGTH","EN TOTAL DE numClustersInspeccinads  ES "+NUMERO_DE_CLUSTERS_iNSPECCIONADOS);


        Log.i("ELWEIGTH","A TEXT1 ES "+NUMERO_DEFECTS);
        Log.i("ELWEIGTH","A TEXT2 ES "+NUMERO_DE_CLUSTERS_iNSPECCIONADOS);




        // Log.i("cality es ","el CALIDAD_TOTAL es "+objecControlCald.getCalidaCamp());

        CALIDAD_TOTAL=objecControlCald.getCalidaCamp();
        Log.i("cality es ","el CALIDAD_TOTAL  down es es "+CALIDAD_TOTAL);

        PORCENTAJE_DE_DEFECTOS=100-CALIDAD_TOTAL;

        // PORCENTAJE_DE_DEFECTOS=((double)NUMERO_DEFECTS/100) * NUMERO_DE_CLUSTERS_iNSPECCIONADOS;


        //SDFDSHJ

        Log.i("ELWEIGTH","el CALIDAD_TOTAL es "+CALIDAD_TOTAL);


        /***MAYOR DEFECTO empaque OBTENIENDO NOMBRES */

        //los nombre de los defectos que selecionaron
        defectsSelecNames = new ArrayList<>();

        for(int indice2=0; indice2<defectsEmpaque.size() ;indice2++){
            //obtenemos la lista de defectos..

            if(!defectsSelecNames.contains(defectsEmpaque.get(indice2).getNombreDefect())){

                defectsSelecNames.add(defectsEmpaque.get(indice2).getNombreDefect());
            }

        }


        /**OBTIENE MAYOR DEFECTO EMPAQUE*/

        String nombreMayorDefecto="NO";
        int numeroMayorDefecto=0;
        int numeroDfectosCurrentDefectx;


        for(int indice2=0; indice2<defectsSelecNames.size() ;indice2++){
            numeroDfectosCurrentDefectx=0;

            //buscamos cada uno de  los nombre selecionados como defectos
            String currenTnameDefectToContar=defectsSelecNames.get(indice2);

            Log.i("elmayirr","buscamos este defecto "+currenTnameDefectToContar);


            for(int indice3=0; indice3<defectsEmpaque.size() ;indice3++){
                Log.i("elmayirr","buscamos este defecto "+defectsEmpaque.get(indice3).getNombreDefect());

                if(defectsEmpaque.get(indice3).getNombreDefect().equals(currenTnameDefectToContar)){
                    numeroDfectosCurrentDefectx=numeroDfectosCurrentDefectx+defectsEmpaque.get(indice3).getNumeroDefectos();
                }
            }

            if(numeroDfectosCurrentDefectx>numeroMayorDefecto){
                numeroMayorDefecto=numeroDfectosCurrentDefectx;
                nombreMayorDefecto=currenTnameDefectToContar;

            }



        }


        MAYOR_DEFECTO_EMPAQUE=nombreMayorDefecto;
        Log.i("elmayirr","el mayor defecto es "+nombreMayorDefecto);






        ///entonces vamos a crear la tabla
        DecimalFormat df = new DecimalFormat("#.##");





        Log.i("debugderor","el calidad total es "+CALIDAD_TOTAL);

        TableCalidProdc.add(new TableCalidProdc(objecControlCald.getTipoEmpaque()+" "+objecControlCald.getMarcaCaja(),objecControlCald.getTotalCajas(),CALIDAD_TOTAL, objecControlCald.getCodigo(),
                objecControlCald.getMarcaCaja()));


        Table table1 =  createTableWhitDateEvaluacionFrutaPdfCamionesYcarretas(objecControlCald.getMarcaCaja(),df.format(PROMEDIO_PESO),
                df.format(CALIDAD_TOTAL)+"%",df.format(PORCENTAJE_DE_DEFECTOS)+"%",String.valueOf(NUMERO_DEFECTS),String.valueOf(MAYOR_DEFECTO_SELECCION),
                String.valueOf(MAYOR_DEFECTO_EMPAQUE),String.valueOf(NUMERO_DE_CLUSTERS_POR_CAJA),String.valueOf(NUMERO_DE_DEDOS),df.format(GRADO_CALIBRE_PROMEDIO)+" grados",
                df.format(LARGO_DEDOS_PROMEDIO)+" pulgadas","3.0" //estaba en PH_PROMEDIO
        );

        return table1;



    }

    private static Table createTableWhitDateEvaluacionFrura(String empaqueNombre,String promedioPeso,String calidadTotal,String porcenjeDefects,
                                      String numeroDefectos,String mayorDefectSelecion,String mayorDefectEmq,String numClusters,String numDedos,
                                                     String gradoCalibrePromedio,String largoDedoPromedio,String phPromedio){
        float araycolumccc[]= {1,2};
         Table table1X=  new Table(araycolumccc);


        DeviceRgb rgbColor= new DeviceRgb(219, 219, 219); //color verde claro
        Cell cellGlobal= new Cell(1,2).setBackgroundColor(rgbColor);
        cellGlobal.add(new Paragraph("EMPAQUE : "+empaqueNombre).setFontSize(7.5f).setPaddingLeft(10f).setBold());
        table1X.addCell(cellGlobal);



        rgbColor= new DeviceRgb(155, 194, 230); //color
         cellGlobal= new Cell(1,1).setBackgroundColor(rgbColor);
        cellGlobal.add(new Paragraph("PROMEDIO PESO").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        table1X.addCell(cellGlobal);


        cellGlobal= new Cell();
        cellGlobal.add(new Paragraph(promedioPeso+"lbs").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        table1X.addCell(cellGlobal);


            /***hasta por aqui en bacground*/
        rgbColor= new DeviceRgb(169, 208, 142); //color
        cellGlobal= new Cell(1,1).setBackgroundColor(rgbColor);
        cellGlobal.add(new Paragraph("CALIDAD TOTAL").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        table1X.addCell(cellGlobal);


        cellGlobal= new Cell();
        cellGlobal.add(new Paragraph(calidadTotal).setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        table1X.addCell(cellGlobal);

        rgbColor= new DeviceRgb(255, 217, 102); //color
        cellGlobal= new Cell(1,1).setBackgroundColor(rgbColor);
        cellGlobal.add(new Paragraph("PORCENTAJE DE DEFECTOS").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        table1X.addCell(cellGlobal);



        cellGlobal= new Cell();
        cellGlobal.add(new Paragraph(porcenjeDefects).setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        table1X.addCell(cellGlobal);


        rgbColor= new DeviceRgb(244, 176, 132); //color
        cellGlobal= new Cell(1,1).setBackgroundColor(rgbColor);
        cellGlobal.add(new Paragraph("NUMERO DE DEFECTOS").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        table1X.addCell(cellGlobal);


        cellGlobal= new Cell();
        cellGlobal.add(new Paragraph(numeroDefectos).setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        table1X.addCell(cellGlobal);



        rgbColor= new DeviceRgb(255, 255, 0); //color
        cellGlobal= new Cell(1,1).setBackgroundColor(rgbColor);
        cellGlobal.add(new Paragraph("MAYOR DEFECTO SELECION").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        table1X.addCell(cellGlobal);

        cellGlobal= new Cell();
        cellGlobal.add(new Paragraph(mayorDefectSelecion).setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        table1X.addCell(cellGlobal);


        rgbColor= new DeviceRgb(255, 255, 0); //color
        cellGlobal= new Cell(1,1).setBackgroundColor(rgbColor);
        cellGlobal.add(new Paragraph("MAYOR DEFECTO EMPAQUE").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        table1X.addCell(cellGlobal);


        cellGlobal= new Cell();
        cellGlobal.add(new Paragraph(mayorDefectEmq).setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        table1X.addCell(cellGlobal);



        try{
            if(Integer.parseInt(numClusters)>0){
                rgbColor= new DeviceRgb(198, 224, 180); //color
                cellGlobal= new Cell(1,1).setBackgroundColor(rgbColor);
                cellGlobal.add(new Paragraph("NÚMERO DE CLUSTER").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
                table1X.addCell(cellGlobal);


                cellGlobal= new Cell();
                cellGlobal.add(new Paragraph(numClusters).setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
                table1X.addCell(cellGlobal);
            }
        }

        catch (NumberFormatException e) {
            e.printStackTrace();

        }




        rgbColor= new DeviceRgb(255, 192, 0); //color
        cellGlobal= new Cell(1,1).setBackgroundColor(rgbColor);
        cellGlobal.add(new Paragraph("NUMERO DE DEDOS").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        table1X.addCell(cellGlobal);

        cellGlobal= new Cell();
        cellGlobal.add(new Paragraph(numDedos).setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        table1X.addCell(cellGlobal);

        rgbColor= new DeviceRgb(153, 153, 255); //color
        cellGlobal= new Cell(1,1).setBackgroundColor(rgbColor);
        cellGlobal.add(new Paragraph("GRADO DE CALIBRE PROMEDIO").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        table1X.addCell(cellGlobal);


        cellGlobal= new Cell();
        cellGlobal.add(new Paragraph(gradoCalibrePromedio).setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        table1X.addCell(cellGlobal);


        rgbColor= new DeviceRgb(0, 153, 204); //color
        cellGlobal= new Cell(1,1).setBackgroundColor(rgbColor);
        cellGlobal.add(new Paragraph("LARGO DE DEDOS PROMEDIO").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        table1X.addCell(cellGlobal);

        cellGlobal= new Cell();
        cellGlobal.add(new Paragraph(largoDedoPromedio).setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        table1X.addCell(cellGlobal);


        rgbColor= new DeviceRgb(146, 208, 80); //color
        cellGlobal= new Cell(1,1).setBackgroundColor(rgbColor);
        cellGlobal.add(new Paragraph("PH").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        table1X.addCell(cellGlobal);


        cellGlobal= new Cell();
        cellGlobal.add(new Paragraph(phPromedio).setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        table1X.addCell(cellGlobal);





        return  table1X;

    }


    private static Table createTableWhitDateEvaluacionFrutaPdfCamionesYcarretas(String empaqueNombre,String promedioPeso,String calidadTotal,String porcenjeDefects,
                                                                                String numeroDefectos,String mayorDefectSelecion,String mayorDefectEmq,String numClusters,String numDedos,
                                                                                String gradoCalibrePromedio,String largoDedoPromedio,String phPromedio){
        float araycolumccc[]= {1,2};
        Table table1X=  new Table(araycolumccc);


        DeviceRgb rgbColor= new DeviceRgb(219, 219, 219); //color verde claro
        Cell cellGlobal= new Cell(1,2).setBackgroundColor(rgbColor);
        cellGlobal.add(new Paragraph("EMPAQUE : "+empaqueNombre).setFontSize(8.5f).setPaddingLeft(10f).setBold());
        table1X.addCell(cellGlobal);



        rgbColor= new DeviceRgb(155, 194, 230); //color
        cellGlobal= new Cell(1,1).setBackgroundColor(rgbColor);
        cellGlobal.add(new Paragraph("PROMEDIO PESO").setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f).setBold());
        table1X.addCell(cellGlobal);


        cellGlobal= new Cell();
        cellGlobal.add(new Paragraph(promedioPeso+"lbs").setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f).setBold());
        table1X.addCell(cellGlobal);


        /***hasta por aqui en bacground*/
        rgbColor= new DeviceRgb(169, 208, 142); //color
        cellGlobal= new Cell(1,1).setBackgroundColor(rgbColor);
        cellGlobal.add(new Paragraph("CALIDAD TOTAL").setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f).setBold());
        table1X.addCell(cellGlobal);


        cellGlobal= new Cell();
        cellGlobal.add(new Paragraph(calidadTotal).setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f).setBold());
        table1X.addCell(cellGlobal);

        rgbColor= new DeviceRgb(255, 217, 102); //color
        cellGlobal= new Cell(1,1).setBackgroundColor(rgbColor);
        cellGlobal.add(new Paragraph("PORCENTAJE DE DEFECTOS").setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f).setBold());
        table1X.addCell(cellGlobal);



        cellGlobal= new Cell();
        cellGlobal.add(new Paragraph(porcenjeDefects).setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f).setBold());
        table1X.addCell(cellGlobal);


        rgbColor= new DeviceRgb(244, 176, 132); //color
        cellGlobal= new Cell(1,1).setBackgroundColor(rgbColor);
        cellGlobal.add(new Paragraph("NUMERO DE DEFECTOS").setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f).setBold());
        table1X.addCell(cellGlobal);


        cellGlobal= new Cell();
        cellGlobal.add(new Paragraph(numeroDefectos).setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f).setBold());
        table1X.addCell(cellGlobal);



        rgbColor= new DeviceRgb(255, 255, 0); //color
        cellGlobal= new Cell(1,1).setBackgroundColor(rgbColor);
        cellGlobal.add(new Paragraph("MAYOR DEFECTO SELECION").setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f).setBold());
        table1X.addCell(cellGlobal);

        cellGlobal= new Cell();
        cellGlobal.add(new Paragraph(mayorDefectSelecion).setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f).setBold());
        table1X.addCell(cellGlobal);


        rgbColor= new DeviceRgb(255, 255, 0); //color
        cellGlobal= new Cell(1,1).setBackgroundColor(rgbColor);
        cellGlobal.add(new Paragraph("MAYOR DEFECTO EMPAQUE").setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f).setBold());
        table1X.addCell(cellGlobal);


        cellGlobal= new Cell();
        cellGlobal.add(new Paragraph(mayorDefectEmq).setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f).setBold());
        table1X.addCell(cellGlobal);



        try{
            if(Integer.parseInt(numClusters)>0){
                rgbColor= new DeviceRgb(198, 224, 180); //color
                cellGlobal= new Cell(1,1).setBackgroundColor(rgbColor);
                cellGlobal.add(new Paragraph("NÚMERO DE CLUSTER").setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f).setBold());
                table1X.addCell(cellGlobal);


                cellGlobal= new Cell();
                cellGlobal.add(new Paragraph(numClusters).setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f).setBold());
                table1X.addCell(cellGlobal);
            }
        }

        catch (NumberFormatException e) {
            e.printStackTrace();

        }




        rgbColor= new DeviceRgb(255, 192, 0); //color
        cellGlobal= new Cell(1,1).setBackgroundColor(rgbColor);
        cellGlobal.add(new Paragraph("NUMERO DE DEDOS").setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f).setBold());
        table1X.addCell(cellGlobal);

        cellGlobal= new Cell();
        cellGlobal.add(new Paragraph(numDedos).setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f).setBold());
        table1X.addCell(cellGlobal);

        rgbColor= new DeviceRgb(153, 153, 255); //color
        cellGlobal= new Cell(1,1).setBackgroundColor(rgbColor);
        cellGlobal.add(new Paragraph("GRADO DE CALIBRE PROMEDIO").setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f).setBold());
        table1X.addCell(cellGlobal);


        cellGlobal= new Cell();
        cellGlobal.add(new Paragraph(gradoCalibrePromedio).setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f).setBold());
        table1X.addCell(cellGlobal);


        rgbColor= new DeviceRgb(0, 153, 204); //color
        cellGlobal= new Cell(1,1).setBackgroundColor(rgbColor);
        cellGlobal.add(new Paragraph("LARGO DE DEDOS PROMEDIO").setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f).setBold());
        table1X.addCell(cellGlobal);

        cellGlobal= new Cell();
        cellGlobal.add(new Paragraph(largoDedoPromedio).setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f).setBold());
        table1X.addCell(cellGlobal);


        rgbColor= new DeviceRgb(146, 208, 80); //color
        cellGlobal= new Cell(1,1).setBackgroundColor(rgbColor);
        cellGlobal.add(new Paragraph("PH").setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f).setBold());
        table1X.addCell(cellGlobal);


        cellGlobal= new Cell();
        cellGlobal.add(new Paragraph(phPromedio).setTextAlignment(TextAlignment.CENTER).setFontSize(8.5f).setBold());
        table1X.addCell(cellGlobal);





        return  table1X;

    }



    public static Table createTablePorceCalidProductres(){

        DeviceRgb rgbColor= new DeviceRgb(231, 230, 230); //color

        float  [] array={1,2,1, 1.5f};
         Table table= new Table(array);
         Cell celdaGlobal= new Cell(2,1).setBackgroundColor(rgbColor);
         celdaGlobal.add(new Paragraph("CODIGO").setTextAlignment(TextAlignment.CENTER).setFontSize(8f).setBold());
         table.addCell(celdaGlobal);

         celdaGlobal= new Cell(2,1).setBackgroundColor(rgbColor);
        celdaGlobal.add(new Paragraph("TIPO DE EMPAQUE").setTextAlignment(TextAlignment.CENTER).setFontSize(8f).setBold());
        table.addCell(celdaGlobal);

        celdaGlobal= new Cell(2,1).setBackgroundColor(rgbColor);
        celdaGlobal.add(new Paragraph("TOTAL EMBARCADO").setTextAlignment(TextAlignment.CENTER).setFontSize(8f).setBold());
        table.addCell(celdaGlobal);

        celdaGlobal= new Cell(2,1).setBackgroundColor(rgbColor);
        celdaGlobal.add(new Paragraph("PORCENTAJE QS %").setTextAlignment(TextAlignment.CENTER).setFontSize(8f).setBold());
        table.addCell(celdaGlobal);



          /***codigo el numero de rowspan sera del tamaano  TableCalidProdc .size*/
         rgbColor= new DeviceRgb(242, 242, 242); //color


        DecimalFormat df = new DecimalFormat("#.##");

               int totalEMbracado=0;
               double sumaPorcentajes=0;
                int numsEmpaques=0;

        for(TableCalidProdc itemCurrent :TableCalidProdc){

              celdaGlobal= new Cell( ).setBackgroundColor(rgbColor);
              celdaGlobal.add(new Paragraph(itemCurrent.getCodigo()).setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f));
              table.addCell(celdaGlobal);


                celdaGlobal= new Cell().setBackgroundColor(rgbColor);
                celdaGlobal.add(new Paragraph(itemCurrent.getNombreMarcaDeCaja()).setFontSize(7.5f).setPaddingLeft(5f));
                table.addCell(celdaGlobal);



                celdaGlobal= new Cell().setBackgroundColor(rgbColor);
                celdaGlobal.add(new Paragraph(String.valueOf(itemCurrent.getTotalEmbacado()+" cajas")).setFontSize(7.5f).setTextAlignment(TextAlignment.CENTER));
                table.addCell(celdaGlobal);

                celdaGlobal= new Cell().setBackgroundColor(rgbColor);
                Log.i("debugderor","el  "+itemCurrent.getPorcentajeQS());

                //String caliddTotal=df.format(itemCurrent.getPorcentajeQS());


                if(df.format(itemCurrent.getPorcentajeQS()).contains(".")){
                    String [] arrayxc=df.format(itemCurrent.getPorcentajeQS()).split("\\.");
                    if(arrayxc[1].length()==1){
                        celdaGlobal.add(new Paragraph(df.format(itemCurrent.getPorcentajeQS())+"0%")).setFontSize(7.5f).setTextAlignment(TextAlignment.CENTER);
                    }else{
                        celdaGlobal.add(new Paragraph(df.format(itemCurrent.getPorcentajeQS())+"%")).setFontSize(7.5f).setTextAlignment(TextAlignment.CENTER);
                    }
                }



                else if(df.format(itemCurrent.getPorcentajeQS()).contains(",")){
                String [] arrayxc=df.format(itemCurrent.getPorcentajeQS()).split(",");
                if(arrayxc[1].length()==1){
                    celdaGlobal.add(new Paragraph(df.format(itemCurrent.getPorcentajeQS())+"0%")).setFontSize(7.5f).setTextAlignment(TextAlignment.CENTER);
                }else{
                    celdaGlobal.add(new Paragraph(df.format(itemCurrent.getPorcentajeQS())+"%")).setFontSize(7.5f).setTextAlignment(TextAlignment.CENTER);

                }

            }

                else  //si es entero

                {

                    celdaGlobal.add(new Paragraph(df.format(itemCurrent.getPorcentajeQS())+".00%")).setFontSize(7.5f).setTextAlignment(TextAlignment.CENTER);

                }


                table.addCell(celdaGlobal);


            totalEMbracado=totalEMbracado+itemCurrent.getTotalEmbacado();
            sumaPorcentajes=sumaPorcentajes+itemCurrent.getPorcentajeQS();

            numsEmpaques++;

            }



        double porcentajeFinal=sumaPorcentajes/TableCalidProdc.size();

         promedioPOrcetajeQS=  porcentajeFinal;
        //Tabla total
         rgbColor= new DeviceRgb(231, 230, 230); //color

        celdaGlobal= new Cell(1,2).setBackgroundColor(rgbColor);
        celdaGlobal.add(new Paragraph("TOTAL").setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(8f));
        table.addCell(celdaGlobal);



        //TOTAL EMBRACADO
        rgbColor= new DeviceRgb(255, 242, 204); //color
        celdaGlobal= new Cell().setBackgroundColor(rgbColor);
        celdaGlobal.add(new Paragraph(totalEMbracado+" cajas").setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(7.5f));
        table.addCell(celdaGlobal);


        //porcentaje
        rgbColor= new DeviceRgb(226, 239, 218); //color
        celdaGlobal= new Cell().setBackgroundColor(rgbColor);

        Log.i("holaddd","el porcentaje final double es "+porcentajeFinal);


        String porcentajeFinalxString=df.format(porcentajeFinal);



      if(porcentajeFinalxString.matches("-?\\d+")){ //si es un integer

          celdaGlobal.add(new Paragraph(porcentajeFinalxString+".00 %").setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(7.5f));

      }

       else  if(df.format(porcentajeFinal).contains(".")){
            String [] arrayxc=df.format(porcentajeFinal).split("\\.");
            if(arrayxc[1].length()==1){

                celdaGlobal.add(new Paragraph(porcentajeFinalxString+"0 %").setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(7.5f));

            }else{

                celdaGlobal.add(new Paragraph(porcentajeFinalxString+"%").setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(7.5f));

            }
        }




        else{
          celdaGlobal.add(new Paragraph(porcentajeFinalxString+"%").setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(7.5f));

      }


        Log.i("holaddd","el porcentaje final string es  "+porcentajeFinalxString);

        table.addCell(celdaGlobal);




        return table;

    }



    public static Table generateTexCertificationTable(String marcaCurrent){

        Cell cell= new Cell().setBorder(Border.NO_BORDER);
        float sizeColumnsx[]= {1,1,1,1};
       Table table1=  new Table(sizeColumnsx);

       cell.add(new Paragraph("Certifico la calidsd yPosicion porcentaje de calidad "));
       table1.addCell(cell).setBorder(Border.NO_BORDER);


         cell= new Cell().setBorder(Border.NO_BORDER);
        cell.add(new Paragraph("Semana "+Variables.CurrenReportPart1.getSemana()).setBold());
        table1.addCell(cell);

        cell= new Cell().setBorder(Border.NO_BORDER);
        cell.add(new Paragraph(",marca "));
        table1.addCell(cell);


        cell= new Cell().setBorder(Border.NO_BORDER);
        cell.add(new Paragraph(marcaCurrent).setBold());
        table1.addCell(cell);

        return table1;
    }



    public static Paragraph generateTexCertificoLaCALIDAD(String marcaCurrent,String semana){

        Text certico = new Text("Certifico la calidad y porcentaje de calidad ").setFontSize(10);
        Text semanaNum = new Text("Semana "+semana).setBold().setFontSize(10);
        Text marcax = new Text(", marca ").setFontSize(10);
        Text marca = new Text(marcaCurrent.toUpperCase()).setFontSize(10).setBold();


        Paragraph p = new Paragraph().setFontSize(10)
                .add(certico).add(semanaNum).add(marcax).add(marca);

        return p;
    }



    public static Paragraph generateTexRevisadoPorFormatAndPosition(String revisadoPorName, String codigoRevisado){

        Text  revisadopor = new Text("  Revisado por : ").setFontSize(9).setBold();

        Text  nameRevisado = new Text(revisadoPorName).setFontSize(9);

        Text codigo = new Text(", codigo ").setBold().setFontSize(8);
        Text codigoAqui = new Text(codigoRevisado+"  ").setFontSize(9);


        Paragraph p = new Paragraph().setFontSize(9)
                .add(revisadopor).add(nameRevisado).add(codigo).add(codigoAqui);


        p.setBackgroundColor(com.itextpdf.kernel.colors.Color.convertRgbToCmyk(new DeviceRgb(56,56,56)));
        p.setFontColor(new DeviceRgb(255,255,255));
       // p.setFixedPosition(78, 107,200); //ESTABA EN 108
        p.setFixedPosition(78, 107,p.getWidth()); //ESTABA EN 108


        return p;
    }



    public static  Bitmap createPieCharImgbITMAP(PieChart pieChart ,Context context){

        String label = "";
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#5c9cd6")); //azul el porcentaje que sirve
        colors.add(Color.parseColor("#ed7d31"));  //narnja el rechzado

        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry((float) promedioPOrcetajeQS,"Calidad"));
        pieEntries.add(new PieEntry((float) ((float) 100-promedioPOrcetajeQS),"Defectos"));

        PieDataSet pieDataSet = new PieDataSet(pieEntries,label);
        pieDataSet.setValueTextSize(23f);

        Typeface tf = Typeface.createFromAsset(context.getAssets(),"m_bold.ttf");
        // pieChart.getDescription().setTypeface(tf);
        pieDataSet.setValueTypeface(tf);
        //providing color list for coloring different entries
        pieDataSet.setColors(colors);
        pieDataSet.setValueTextColor(Color.parseColor("#ffffff"));
        pieDataSet.setDrawIcons(true);
        // pieDataSet.setFormSize(100f);
        //grouping the data set from entry to chart
        PieData pieData = new PieData(pieDataSet);
        ;
        //showing the value of the entries, default true if not set
        pieData.setDrawValues(true);

        pieChart.setHoleRadius(0f);
        pieChart.setTransparentCircleRadius(0f);


        pieChart.getDescription().setEnabled(false);

        pieChart.getLegend().setEnabled(false);


        // pieChart.setDrawCenterText(false);
        // https://medium.com/@clyeung0714/using-mpandroidchart-for-android-application-piechart-123d62d4ddc0
        // pieChart.setDrawEntryLabels(false);
        // pieChart.getDescription().setEnabled(false);

        pieData.setValueFormatter(new PercentFormatterPie(pieChart));
        pieChart.setUsePercentValues(true);
        pieChart.setData(pieData);

        pieChart.setDrawEntryLabels(false); // To remove labels from piece of pie
        pieChart.setBackgroundColor(Color.parseColor("#dcdcdc"));


        // pieChart.getDescription().;
        // pieChart.setHoleColor(Color.parseColor("#000000"));
        // pieChart.setEntryLabelTextSize(19f);  // You can increase or decrease value as per your need in argument


        pieChart.invalidate();
        Bitmap chartBitmap = pieChart.getChartBitmap();

         return chartBitmap;

    }





    public static  Bitmap createBarChart(BarChart barChart , Context context, int contadorIterador,int numerCLUSTERSiNSPECCIONAD){

        barChart.getXAxis().setDrawGridLines(false);

       // final String [] arrayAllDefects = context. getResources().getStringArray(R.array.array_defectos_all);

        Log.i("sonammmer","el size de hashmap "+hasmapOfListwhitNamesFefectsCurrentControlCalidadFOmr.size());

        /*
        for (int key : hasmapOfListwhitNamesFefectsCurrentControlCalidadFOmr.keySet()) {
            Log.i("sonammmer","el current key es: "+key);

            // ...
        }

*/

        /**asi le agregamos los defectos defectos selecion*/
        ArrayList<String> allDefectsNames = hasmapOfListwhitNamesFefectsCurrentControlCalidadFOmr.get(contadorIterador+1); //


        Log.i("sonammmer","el contador iterador es  "+contadorIterador);
        Log.i("sonammmer","el size list es "+allDefectsNames.size());


        /**asi le agregamos los defectos empaque */
        allDefectsNames.add("Empty");
        allDefectsNames.add("BU");
        allDefectsNames.add("SR");
        allDefectsNames.add("BR");
        allDefectsNames.add("NI");


        ArrayList<DefectsCantdad>currentArraylistx=defectsSelecionHahashMaps.get(String.valueOf(contadorIterador));

        Log.i("sonammmer","el hasmpa size es  "+defectsSelecionHahashMaps.size());




        Log.i("sonammmer","el size de curren array list es "+currentArraylistx.size());


        for(    DefectsCantdad objc: currentArraylistx){ //debug imprimos los defcetos actuales
            Log.i("sonammmer","el name actual defectos es: "+objc.getNombreDefect());
        }

        Log.i("sonammmer","FINISH XXX");

        /**si tiene empty le agragamos pensar si podemos agregarle commas mejor es mas rapido*/
        for(int indice=0; indice<allDefectsNames.size(); indice++){

           if(allDefectsNames.get(indice).equalsIgnoreCase("empty")){
               allDefectsNames.set(indice,"");

           }

        }



        ArrayList<BarEntry> barEntries = new ArrayList<>();


        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {

              // final String [] arrayAllDefectsAll = context. getResources().getStringArray(R.array.array_defectos_all23);
               Log.i("camasd","el size de names es "+allDefectsNames.size());
                Log.i("camasd","el size de entreies es  "+barEntries.size());



               // String[] arr = allDefectsNames.toArray(new String[0]);

                if((int) value< allDefectsNames.size()){

                    return allDefectsNames.get((int) value);


                }else{

                    return allDefectsNames.get((int) 0);

                }



              //  return arr[(int) value];

                //return arrayAllDefects(int) value);
                //return arrayAllDefects (value);

            }
        };



        int contadorCurrrentDefect=0;

        String defectNamen="";

int contadorAlldefectos=0;

/**el probelma creo que esta en los entries */


        ArrayList<DefectsCantdad>arrayListItemDefectAndCantidad;

        arrayListItemDefectAndCantidad =defectsSelecionHahashMaps.get(String.valueOf(contadorIterador));
        Log.i("butters","el szie de este array list es "+arrayListItemDefectAndCantidad.size());
        contadorAlldefectos=cuentaDeFECTOS(arrayListItemDefectAndCantidad,defectsEmpaqueHashMapOfLists.get(String.valueOf(contadorIterador)));
        Log.i("butters","esta lista contiene en total "+contadorAlldefectos+" defectos ");

        Log.i("butters","indice item  lista current "+contadorIterador);




        /**defectos seleccion*/  //y tambien los espacios
        for(int indice=0; indice<allDefectsNames.size()-4; indice++){ //buscamos cada unos de los defectos
                 contadorCurrrentDefect=0;

                 String defectoActualToSearch=allDefectsNames.get(indice);


            //recorremos esta lista y buscamos el defecto actual...
            for(int indice2 = 0; indice2< arrayListItemDefectAndCantidad.size(); indice2++){ //buscamos este defecto

                     defectNamen=arrayListItemDefectAndCantidad.get(indice2).getNombreDefect();

                     if(defectNamen.equalsIgnoreCase(defectoActualToSearch)){
                         contadorCurrrentDefect=contadorCurrrentDefect+arrayListItemDefectAndCantidad.get(indice2).getNumeroDefectos();


                     }

                 }




                 if(contadorCurrrentDefect==0){
                     Log.i("howasr","El defect es cero "); //no usamos value first pero si no podemos usarlo despues

                     barEntries.add(new BarEntry((float)indice ,roundTwoDecimals((float)0)));
                 }

                 else {

                   /**aqui tenemos un numero de defectos de un especifico defecto que buscamos , y ahora
                    * queremos saber cuanto representa este defecto en porcentaje del total de clusters inspeccionados**/


                     Log.i("butters","El defect "+defectoActualToSearch+" tiene "+contadorCurrrentDefect+" defectos"); //no usamos value first pero si no podemos usarlo despues


                    // float valuefist= ((float)contadorCurrrentDefect/numClustersInspeccionados);

                     Log.i("butters","el NUM CLUSTERS INSPECIONADOS ES "+numerCLUSTERSiNSPECCIONAD);


                     float porcentatajeYsinreddondear= ((float) contadorCurrrentDefect /numerCLUSTERSiNSPECCIONAD)*100;

                     Log.i("butters","el porcentajex es  "+porcentatajeYsinreddondear);

                     float porcentajeYredondeado = (float) ((float) Math.round(porcentatajeYsinreddondear * 100.0) / 100.0);

                     Log.i("butters","el roundedY es  "+porcentajeYredondeado);

                     barEntries.add(new BarEntry((float)indice, porcentajeYredondeado));


                 }

        }

        Log.i("sabumafu","el numero de entries aqui es  "+barEntries.size());


        /**si no funciona vamos cone indice -2 ,eso deberia crear un espacio defectos empaque */

        Log.i("primervalue","Es primer value de defcetos empaque es "+allDefectsNames.get((allDefectsNames.size()-4)));
///defectos empque
        for(int indice=(allDefectsNames.size()-4); indice<allDefectsNames.size(); indice++){
            contadorCurrrentDefect=0;
            String defectoActualToSearch=allDefectsNames.get(indice);


            ArrayList<DefectsCantdad>currentArraylist=defectsEmpaqueHashMapOfLists.get(String.valueOf(contadorIterador));


            for(int indice2 = 0; indice2< currentArraylist.size(); indice2++){

                  defectNamen=currentArraylist.get(indice2).getNombreDefect();


                if(defectNamen.contains(":")){
                    String[] defect =defectNamen.split(":");
                    defectNamen=defect[0];
                }


                if(defectNamen.equals(defectoActualToSearch)){
                    //contadorDefectoEnonctrado++;
                    contadorCurrrentDefect=contadorCurrrentDefect+currentArraylist.get(indice2).getNumeroDefectos();

                }
            }



            if(contadorCurrrentDefect==0){

                barEntries.add(new BarEntry((float)indice,roundTwoDecimals((float)0)));

            }else{
                Log.i("entriesd","Es mayor a cero");

             //   float porcentaje= contadorCurrrentDefect * ((float)listNumClustersInspec.get(contadorIterador)/100);

                float porcentajeSinRedondeaer= ((float) contadorCurrrentDefect /numerCLUSTERSiNSPECCIONAD)*100;


                //float porcentajeNewRepresenta= contadorCurrrentDefect*contadorAlldefectos /100;
               // Log.i("howasr","El porcentaje es new ws  "+porcentajeNewRepresenta);


                Log.i("howasr","El numero de defecto empaque : "+contadorCurrrentDefect+" es "+defectoActualToSearch);
                Log.i("sumaerr","en psquqete defect es "+porcentajeSinRedondeaer);
                Log.i("salerod","el porcentaje 2  es  "+ porcentajeSinRedondeaer);


                float porcentajeRedondeado = (float) ((float) Math.round(porcentajeSinRedondeaer * 100.0) / 100.0);

                barEntries.add(new BarEntry((float)indice,porcentajeRedondeado));

                Log.i("salerod","con contador defecto encontrado es "+ contadorCurrrentDefect);

            }
        }





        /***vemos el valor de los entries*/


        Log.i("entriesd","el size de entries gggg es  "+barEntries.size());
        Log.i("entriesd","el size de lis all defects  es  "+allDefectsNames.size());

        Log.i("salerod","con contador defecto encontrado es "+ contadorCurrrentDefect);



        for(BarEntry entry: barEntries){
            Log.i("entriesd","el value de entrie X ES: "+entry.getX()+" Y DE Y ES "+entry.getY());
        }



        //obtenemos la lista usando el contador del parametro contadorIterador ..
/*
        if(MAYOR_DEFECTO_SELECCION.contains(":")){
            String array[]=MAYOR_DEFECTO_SELECCION.split(":");


            MAYOR_DEFECTO_SELECCION=array[0];
        }

*/



        BarDataSet barDataSet = new BarDataSet(barEntries,"");
        barDataSet.setValueTextSize(9f);
        barDataSet.setFormSize(9f);
        barDataSet.setDrawIcons(false);  //es este

        /////// barDataSet.setDrawValues(false); //si no es este



        //barDataSet.setDrawIcons(true);


        BarData theData = new BarData(barDataSet);

        theData.setBarWidth(0.9f); //esta en 0.9
        theData.getGroupWidth(1f, 1f);//space between bars

        barChart.setData(theData);
        //barChart.setTouchEnabled(true);
        // barChart.set
        //barChart.setDragEnabled(true);
        // barChart.setScaleEnabled(true);
        barChart.setFitBars(true);  //esta activa
        // barChart
        barChart.setDrawGridBackground(false);
        //barChart.getXAxis().setSpaceMax(3);
       barChart.getDescription().setEnabled(false); ///vanos ahber lo activamos
        barChart.getLegend().setEnabled(false);

        barDataSet.setColors(new int[]{
                R.color.durazon , R.color.durazon


        } , context);

        barDataSet.setFormSize(4f);
        barDataSet.setValueTextSize(10f);

        /**aqui le pasamos este color*/
        barDataSet.setColor(context.getResources().getColor(R.color.durazon, context.getTheme())); //resolved color


        barDataSet.setValueFormatter(new MyValueFormatter());



        //  barChart.setDescription("hola");

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(formatter);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        Log.i("elentriesNum","el numero entries es "+barEntries.size());

        xAxis.setLabelCount(allDefectsNames.size()-5); //vamos haber
        // Bitmap b = getChartBitmap();



       // YAxis valAxisy = barChart.getY();
        //barChart.setValueFormatter(new MyAxisFormatter());
        //YAxis hola=new MyValueFormatter();

        DecimalFormat format = new DecimalFormat("#,##");
       // format.setMinimumFractionDigits(2);


        YAxis yAxis = barChart.getAxisRight();
/*
        yAxis.setValueFormatter(new MyValueFormatter(){

            @Override
            public String getFormattedValue(float value) {
                // return super.getAxisLabel(value, axis);

                Log.i("sangano","el value es "+value);
                Log.i("sangano","el value con format es "+ format.format(value));

                return "";


    }
});



*/


        barChart.getAxisRight().setDrawLabels(false);




        xAxis.setTextSize(4/*textSize*/);

        // barChart.getDraw
        //Bitmap bitmap = Bitmap.createBitmap(barChart.getWidth(), barChart.getHeight(), Bitmap.Config.ARGB_8888);
        //  barChart.saveToGallery("test.png", 50);
        /// barChart

        barChart.invalidate();
        Bitmap chartBitmap = barChart.getChartBitmap();

        return chartBitmap;

    }


   static float roundTwoDecimals(float d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Float.parseFloat(twoDForm.format(d));
    }

    public static Table generaTableInspectores(SetInformDatsHacienda objectDatosHaCIENDA, float sizeDocument){
        /**NOMBRE DE LOS INSPECTORES*/
        int numClumnas=0;

        Log.i("extensionista","el getExtensionistCalid es "+objectDatosHaCIENDA.getExtensionistCalid());
        Log.i("extensionista","el getCI_extensionistCalid  es "+objectDatosHaCIENDA.getCI_extensionistCalid());


        ArrayList<String>listDatsEvaluadores= new ArrayList<>();

        if(objectDatosHaCIENDA.getExtensionistCalid().trim().length()>1){
            numClumnas++;
            listDatsEvaluadores.add("Sr. "+objectDatosHaCIENDA.getExtensionistCalid().toUpperCase());
        }

        if(objectDatosHaCIENDA.getExtensionistDeRodillo().trim().length()>1){
            numClumnas++;
            listDatsEvaluadores.add("Sr. "+objectDatosHaCIENDA.getExtensionistDeRodillo().toUpperCase());
        }

        if(objectDatosHaCIENDA.getExtensionistEnGancho().trim().length()>1){
            numClumnas++;
            listDatsEvaluadores.add("Sr. "+objectDatosHaCIENDA.getExtensionistEnGancho().toUpperCase());
        }


        //ahora los titulos
        if(objectDatosHaCIENDA.getExtensionistCalid().trim().length()>1){
            listDatsEvaluadores.add("Inspector de calidad");

        }

        if(objectDatosHaCIENDA.getExtensionistDeRodillo().trim().length()>1){
            listDatsEvaluadores.add("Inspector de rodillo");

        }

        if(objectDatosHaCIENDA.getExtensionistEnGancho().trim().length()>1){
            listDatsEvaluadores.add("Inspector de Gancho");

        }







        Table table1= new Table(numClumnas);

        for(String value:listDatsEvaluadores){
            Paragraph paragraph= new Paragraph(value).setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f);
            Cell cell1 = new Cell().setBorder(Border.NO_BORDER);
            cell1.add(paragraph);
            table1.addCell(cell1);


        }

        table1.setWidth(sizeDocument-200f);
        // table1.setMarginLeft(70f);
        table1.setMarginTop(10f);
        table1.setHorizontalAlignment(HorizontalAlignment.CENTER);

        return table1;
    }


    public static Table generaTableInspectores(ReportCamionesyCarretas objectDatosHaCIENDA, float sizeDocument){
        /**NOMBRE DE LOS INSPECTORES*/
        int numClumnas=0;

      //  Log.i("extensionista","el getExtensionistCalid es "+objectDatosHaCIENDA.getExtensionistCalid());
      //  Log.i("extensionista","el getCI_extensionistCalid  es "+objectDatosHaCIENDA.getCI_extensionistCalid());


        ArrayList<String>listDatsEvaluadores= new ArrayList<>();

        if(objectDatosHaCIENDA.getExtensionistaEnCalidad().trim().length()>1){
           numClumnas++;
            listDatsEvaluadores.add("Sr. "+objectDatosHaCIENDA.getExtensionistaEnCalidad().toUpperCase());
        }

        if(objectDatosHaCIENDA.getExtensionistaEnRodillo().trim().length()>1){
            numClumnas++;
            listDatsEvaluadores.add("Sr. "+objectDatosHaCIENDA.getExtensionistaEnRodillo().toUpperCase());
        }

        if(objectDatosHaCIENDA.getExtensionistaEnGancho().trim().length()>1){
            numClumnas++;
            listDatsEvaluadores.add("Sr. "+objectDatosHaCIENDA.getExtensionistaEnGancho().toUpperCase());
        }


              //ahora los titulos
        if(objectDatosHaCIENDA.getExtensionistaEnCalidad().trim().length()>1){
            listDatsEvaluadores.add("Inspector de calidad");

        }

        if(objectDatosHaCIENDA.getExtensionistaEnRodillo().trim().length()>1){
            listDatsEvaluadores.add("Inspector de rodillo");

        }

        if(objectDatosHaCIENDA.getExtensionistaEnGancho().trim().length()>1){
            listDatsEvaluadores.add("Inspector de Gancho");

        }







        Table table1= new Table(numClumnas);

        for(String value:listDatsEvaluadores){
             Paragraph paragraph= new Paragraph(value).setTextAlignment(TextAlignment.CENTER).setFontSize(8f);
             Cell cell1 = new Cell().setBorder(Border.NO_BORDER);
              cell1.add(paragraph);
              table1.addCell(cell1);


        }

        table1.setWidth(sizeDocument-200f);
        // table1.setMarginLeft(70f);
        table1.setMarginTop(10f);
        table1.setHorizontalAlignment(HorizontalAlignment.CENTER);

       return table1;
    }




    public static Table devulveTablaToLibriado(ArrayList<PromedioLibriado> listLibriado,String nameMarcaOtherHere){
      //reoredenamos lis libriado aqui desde el 1

/*
            ArrayList<PromedioLibriado> sortedUsers = (ArrayList<PromedioLibriado>) listLibriado.stream()
                    .sorted(Comparator.comparing(PromedioLibriado::clusterNum))
                    .collect(Collectors.toList());

*/





        Collections.sort(listLibriado, new Comparator<PromedioLibriado>()
        {
            @Override
            public int compare(PromedioLibriado lhs, PromedioLibriado rhs) {//reordenamos

                return Integer.valueOf(lhs.clusterNum).compareTo(rhs.clusterNum);
            }
        });



      Table mitab= new Table(2);
      Cell miCelda;
      Paragraph miParagraph;



        miParagraph= new Paragraph(nameMarcaOtherHere.toUpperCase()).setTextAlignment(TextAlignment.CENTER).setBold();
        miCelda= new Cell(1,2);
        miCelda.add(miParagraph);
        mitab.addCell(miCelda);

        miParagraph= new Paragraph("NÚMERO DE CLUSTERS").setTextAlignment(TextAlignment.CENTER).setBold();
        miCelda= new Cell(1,1);
        miCelda.add(miParagraph);
        mitab.addCell(miCelda);


        miParagraph= new Paragraph("PESO").setTextAlignment(TextAlignment.CENTER).setBold();
        miCelda= new Cell(1,1);
        miCelda.add(miParagraph);
        mitab.addCell(miCelda);

        DecimalFormat dcx= new DecimalFormat("#.##");


          float totalPeso=0;
          String [] arrayValue;

        Log.i("pesoclci","for start here");

        for(PromedioLibriado num: listLibriado){

            Log.i("pesoclci","el value peso es "+num.numPesoCluster);


          miParagraph= new Paragraph(String.valueOf(num.clusterNum)).setTextAlignment(TextAlignment.CENTER);
          miCelda= new Cell(1,1);
          miCelda.add(miParagraph);
          mitab.addCell(miCelda);

          miCelda= new Cell(1,1);

          String valueFormart=dcx.format(num.numPesoCluster);
            valueFormart=valueFormart.replace(",", ".");
           // inform.setPorc14(remplace);

          if(valueFormart.contains(".")){
              arrayValue= valueFormart.split("\\.");

                  if(arrayValue[1].length()==1){ ///3.5

                      miParagraph= new Paragraph(valueFormart+"0").setTextAlignment(TextAlignment.CENTER);

                  }else{

                      miParagraph= new Paragraph(valueFormart).setTextAlignment(TextAlignment.CENTER);

                      Log.i("pesoclci","se eejcuto el else ");

                  }
                 }

               else{

                   miParagraph= new Paragraph(valueFormart+".00").setTextAlignment(TextAlignment.CENTER);

               }





            miCelda.add(miParagraph);
          mitab.addCell(miCelda);

          totalPeso=totalPeso+num.numPesoCluster;

      }


        miParagraph= new Paragraph("TOTAL").setTextAlignment(TextAlignment.CENTER).setBold();
        miCelda= new Cell(1,1);
        miCelda.add(miParagraph);
        mitab.addCell(miCelda);

     //   miParagraph= new Paragraph(dc.format(totalPeso)).setTextAlignment(TextAlignment.CENTER).setBold();

        String totalPesoString=dcx.format(totalPeso);
        totalPesoString=totalPesoString.replace(",", ".");


        if(totalPesoString.contains(".")){

            arrayValue= totalPesoString.split("\\.");

            if(arrayValue[1].length()==1){ ///3.5

                miParagraph= new Paragraph(totalPesoString+"0").setTextAlignment(TextAlignment.CENTER);

            }else{

                miParagraph= new Paragraph(totalPesoString).setTextAlignment(TextAlignment.CENTER);

                Log.i("pesoclci","se eejcuto el else ");

            }
        }

        else{

            miParagraph= new Paragraph(totalPesoString+".00").setTextAlignment(TextAlignment.CENTER);

        }





        miCelda= new Cell(1,1);
        miCelda.add(miParagraph);
        mitab.addCell(miCelda);


        miParagraph= new Paragraph("PROM. PESO (LIBRAS)").setTextAlignment(TextAlignment.CENTER).setBold();
        miCelda= new Cell(1,1);
        miCelda.add(miParagraph);
        mitab.addCell(miCelda);


         ///Y AQUI SACAMOS EL PROMEDIO
        float promedio=totalPeso/listLibriado.size();

        String promedioString=dcx.format(promedio);
        promedioString=promedioString.replace(",", ".");


        if(promedioString.contains(".")){
            arrayValue= promedioString.split("\\.");

            if(arrayValue[1].length()==1){ ///3.5
                miParagraph= new Paragraph(promedioString+"0").setTextAlignment(TextAlignment.CENTER).setBold();

            }else{
                miParagraph= new Paragraph(promedioString).setTextAlignment(TextAlignment.CENTER);
                Log.i("pesoclci","se eejcuto el else ");
            }
        }
        else{
            miParagraph= new Paragraph(promedioString+".00").setTextAlignment(TextAlignment.CENTER);
        }



        DeviceRgb  rgbColor= new DeviceRgb(239, 252, 30); //color
        miCelda= new Cell(1,1).setBackgroundColor(rgbColor);
        miCelda.add(miParagraph);
        mitab.addCell(miCelda);


        return mitab;

    }


    static int getNumDefectsAll(ArrayList<DefectsCantdad> defectsSeleccion, ArrayList<DefectsCantdad> defectsEmpq){
       int numDefectosAll=0;

       for(DefectsCantdad defec : defectsSeleccion){ //defectos selecion

           numDefectosAll=numDefectosAll+defec.getNumeroDefectos();

       }


        for(DefectsCantdad defec : defectsEmpq){ //defectos selecion

            numDefectosAll=numDefectosAll+defec.getNumeroDefectos();

        }

return  numDefectosAll;

    }



    private static int cuentaDeFECTOS(ArrayList<DefectsCantdad>ARRAlIST1,ArrayList<DefectsCantdad>arrayListEmpaque ) {

        int contadorDefectosinArrayList=0;

        for(DefectsCantdad objec: ARRAlIST1){
            if(objec.getNumeroDefectos()>0){

                contadorDefectosinArrayList=contadorDefectosinArrayList+ objec.getNumeroDefectos();

            }

        }

        for(DefectsCantdad objec: arrayListEmpaque){
            if(objec.getNumeroDefectos()>0){

                contadorDefectosinArrayList=contadorDefectosinArrayList+ objec.getNumeroDefectos();

            }

        }



        return  contadorDefectosinArrayList;

    }



    private static int cuentaDefcetosNuevos(){


        return 1;
    }


  private static  String generaCandadosQsercon(ReportCamionesyCarretas object){
        String candados="";

        //            //candado 1: ,candado2: ,candado3:

        if(!object.getCandadoName1().trim().isEmpty()){
            candados="Candado 1: "+object.getCandadoName1();
        }
        //candado 1:name

        if(!object.getCandadoName2().trim().isEmpty()){
            candados=candados+", Candado 2: "+object.getCandadoName2();
        }
        ////candado 1:name, candad 2:sdffsdf

        if(!object.getCandadoName3().trim().isEmpty()){
            candados=candados+", Candado 3: "+object.getCandadoName3();
        }

return  candados.toUpperCase();

    }

}