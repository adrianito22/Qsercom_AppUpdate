package com.tiburela.qsercom.PdfMaker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.fonts.Font;
import android.util.Log;

import androidx.compose.ui.graphics.colorspace.Connector;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.models.ControlCalidad;
import com.tiburela.qsercom.models.DefectsCantdad;
import com.tiburela.qsercom.models.NameAndValue;
import com.tiburela.qsercom.models.ProductPostCosecha;
import com.tiburela.qsercom.models.SetInformDatsHacienda;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;
import com.tiburela.qsercom.models.TableCalidProdc;
import com.tiburela.qsercom.utils.HelperImage;
import com.tiburela.qsercom.utils.Variables;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HelperPdf {

    public static  ArrayList<ArrayList<String>> listOFlist = new ArrayList<>();


    public static  ArrayList<TableCalidProdc> TableCalidProdc = new ArrayList<>();


    public  Image  createInfoImgtoPDF(Drawable mIDrawable, Context conetxt,int i){
        // mIDrawable= conetxt.getDrawable(R.drawable.logopdf);
        Bitmap miBitmap= ((BitmapDrawable)mIDrawable).getBitmap();
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
        miBitmap.compress(Bitmap.CompressFormat.PNG,100,stream1);
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
                Paragraph paragraph1 = new Paragraph(list.get(i).getNameFields()).setFontSize(7.5f);
                cell1= new Cell().add(paragraph1).setPaddingLeft(10f)
                        .setHeight(10f);  //era 10.5





                Paragraph paragraph2;
                if(list.get(i).getValueContent()==null){
                    paragraph2=new Paragraph("");

                }else{

                    paragraph2= new Paragraph(list.get(i).getValueContent());

                }


                Cell cell2;

                if(TableID==600 && i==4 || i== 5 || i==6 || i==7|| i==8 ||i==9 ||i==10||i==11||i==12){//si es ;a tabla sellos instalados
                    cell2= new Cell(1,4).add(paragraph2).setPaddingLeft(10f).setFontSize(7.5f).setHeight(10f); ///le agragmos un span



                }else{

                    cell2= new Cell().add(paragraph2).setPaddingLeft(10f).setFontSize(7.5f).setHeight(10f); //normal

                }




                Log.i("mismussndo","estamo en el indice "+i);

                hasmpaDevolver.put(i+"name",cell1);
                hasmpaDevolver.put(i+"value",cell2);
            }
        }

        else if(alineacion==100){  //alinea en el centro...
            Log.i("mismussndo","el lineacion es "+alineacion);

            for(int i=0;i<list.size();i++){

                Paragraph paragraph1 = new Paragraph(list.get(i).getNameFields()).setFontSize(7.5f);
                Cell cell1= new Cell().add(paragraph1).setTextAlignment(TextAlignment.CENTER).setHeight(10f);


                Paragraph paragraph2;
                if(list.get(i).getValueContent()==null){
                    paragraph2=new Paragraph("");

                }else{

                    paragraph2=new Paragraph(list.get(i).getValueContent()).setFontSize(7.5f);


                }


                Cell cell2= new Cell().add(paragraph2).setTextAlignment(TextAlignment.CENTER).setHeight(10f);

                hasmpaDevolver.put(i+"name",cell1);
                hasmpaDevolver.put(i+"value",cell2);
            }

        }



        return hasmpaDevolver;

    }





    /**para el cuadrto 1*/
    public static  ArrayList<NameAndValue> generaDataToTable(SetInformEmbarque1 Object1,
                                                             SetInformEmbarque2 Object2, SetInformDatsHacienda Object3,
                                                             int tableInfo, ProductPostCosecha product){
        ArrayList<NameAndValue> listTOrETURN1 = new ArrayList<>();

        if(tableInfo==1){

            listTOrETURN1.add(new NameAndValue("SEMANA "+Object1.getSemana(),"FECHA: "+Object1.getSimpleDataFormat()));
            listTOrETURN1.add(new NameAndValue("PRODUCTOR ",Object1.getProductor()));
            listTOrETURN1.add(new NameAndValue("CODIGO PRODUCTOR ",Object1.getCodigo()));
            listTOrETURN1.add(new NameAndValue("CODIGO MAGAP ",Object1.getInscirpMagap()));
            listTOrETURN1.add(new NameAndValue("PUERTO EMBARQUE ",Object1.getPemarque()));
            listTOrETURN1.add(new NameAndValue("ZONA",Object1.getZona()));
            listTOrETURN1.add(new NameAndValue("HORA INICIO",Object1.getHoraInicio()));
            listTOrETURN1.add(new NameAndValue("HORA TERMINO",Object1.getHoraTermino()));
            listTOrETURN1.add(new NameAndValue("GUIA REMISION",Object1.getNguiaRemision()));
            listTOrETURN1.add(new NameAndValue("GUIA DE TRASNPORTE",Object1.get_nguia_transporte()));
            listTOrETURN1.add(new NameAndValue("TARJA DE EMBARQUE",Object1.getNtargetaEmbarque()));
            listTOrETURN1.add(new NameAndValue("HOJA DE EVALUACION",String.valueOf(Object1.getEdiNhojaEvaluacion())));

        }


        else   if(tableInfo==2){ /**la segunda tabla productos postocosecha*/
            ///si contienen data
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
                listTOrETURN1.add(new NameAndValue(product.otro_especifique,product.otroCantidad));
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
            listTOrETURN1.add(new NameAndValue("NUMERACION DE CONTENEDOR",Object1.getNumcionContenedor()));
            listTOrETURN1.add(new NameAndValue("HORA DE LLEGADA",Object1.getHoraLlegadaContenedor()));
            listTOrETURN1.add(new NameAndValue("HORA SALIDA",Object1.getHoraSalidadContenedor()));
            listTOrETURN1.add(new NameAndValue("DESTINO",Object1.getDestinoContenedor()));



        }else if(tableInfo==4){/**sellos llegada*/

            listTOrETURN1.add(new NameAndValue("SELLO PLASTICO NAVIERA",Object1.getSelloPlasticoNaviera()));
            listTOrETURN1.add(new NameAndValue("STICKER DE PATIO VENTOLERA EXTERNA",Object1.getStickerVentoExtern()));
            listTOrETURN1.add(new NameAndValue("NUMERO DE SERIE DE FUNDA",Object1.getnSerieFunda()));
            listTOrETURN1.add(new NameAndValue("CABLE DE RASTREO LLEGADA",Object1.getCableRastreoLlegada()));
            listTOrETURN1.add(new NameAndValue("BOOKING",Object1.getBooking()));
            listTOrETURN1.add(new NameAndValue("MAX GROSS",Object1.getMaxGross()));
            listTOrETURN1.add(new NameAndValue("TARE",Object1.getTare()));



        }else if(tableInfo==5){ //SELLOS INSTALADOS

            listTOrETURN1.add(new NameAndValue("TERMOGRAFO 1",Object2.getTermografo1()));
            listTOrETURN1.add(new NameAndValue("HORA DE ENCEND:" +Object2.getTermografo1HoraEncendido(),"UBICACION PALLET "+Object2.getUbicacionPalletN1()));
            listTOrETURN1.add(new NameAndValue("TERMOGRAFO 2",Object2.getTermografo2()));
            listTOrETURN1.add(new NameAndValue("HORA DE ENCEND:" +Object2.getTermografo2HoraEncendido(),"UBICACION PALLET "+Object2.getUbicacionPalletN2()));


            listTOrETURN1.add(new NameAndValue("CANDADO QSERCOM",Object2.getCandadoQsercom()));
            listTOrETURN1.add(new NameAndValue("CABLE NAVIERA",Object2.getCableNaviera()));


            listTOrETURN1.add(new NameAndValue("SELLO PLASTICO",Object2.getSelloPlastico()));
            listTOrETURN1.add(new NameAndValue("SELLO BOTELLA",Object2.getCandadoBotella()));
            listTOrETURN1.add(new NameAndValue("CABLE EXPORTADORA",Object2.getCableExportadora()));
            listTOrETURN1.add(new NameAndValue("SELLO ADHESIVO EXPORTADORA",Object2.getSelloAdhesivoExportadora()));

            listTOrETURN1.add(new NameAndValue("SELLO ADHESIVO NAVIERA",Object2.getSelloAdhesivoNaviera()));
            listTOrETURN1.add(new NameAndValue("OTROS SELLOS ",Object2.getOtrosSellosEspecif()));


        }else if(tableInfo==6){/**datos transportista */

            listTOrETURN1.add(new NameAndValue("COMPANIA TRANSPORTISTA ",Object2.getCompaniaTranporte()));
            listTOrETURN1.add(new NameAndValue("NOMBRE CHOFER ",Object2.getNombreChofer()));
            listTOrETURN1.add(new NameAndValue("CEDULA ",Object2.getCedulaChofer()));
            listTOrETURN1.add(new NameAndValue("CELULAR ",Object2.getCelularChofer()));
            listTOrETURN1.add(new NameAndValue("PLACA ",Object2.getPlacaChofer()));
            listTOrETURN1.add(new NameAndValue("COLOR CABEZAL ",Object2.getColorCAbezal()));



        }  ///falta datos de proceso y otro



        else if(tableInfo==7){/**datos De proceso */



            // listTOrETURN1.add(new NameAndValue("",Object2.));



        }
        else if(tableInfo==8){/**CONTROL DE GANCHO*/

            listTOrETURN1.add(new NameAndValue("CAJAS PROCESADAS DESPACHADAS",Object3.getEdiCajasProcDesp()));
            listTOrETURN1.add(new NameAndValue("RACIMOS COSECHADOS ",Object3.getEdiRacimosCosech()));
            listTOrETURN1.add(new NameAndValue("RACIMOS RECHAZADOS ",Object3.getEdiRacimosRecha()));
            listTOrETURN1.add(new NameAndValue("RACIMOS PROCESADOS ",Object3.getEdiRacimProces()));

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

        Cell celltipodEcAJA= new Cell().setPaddingLeft(10f);

        Cell cell22Xu= new Cell().setPaddingLeft(10f);
        Cell cell22XuX= new Cell().setPaddingLeft(10f);

        Cell cellDisplay= new Cell().setPaddingLeft(10f);
        Cell cellDisplayX= new Cell().setPaddingLeft(10f);

        Cell cell13kg= new Cell().setPaddingLeft(10f);
        Cell cell13kgX= new Cell().setPaddingLeft(10f);

        Cell cell208= new Cell().setPaddingLeft(10f);
        Cell cell208X= new Cell().setPaddingLeft(10f);



        cellPolituboX.setBackgroundColor(rgbColor) ; //MNARCAR LA X CON BACVGROUND VERDE
        cellPolipackX.setBackgroundColor(rgbColor);
        cellbanavacX.setBackgroundColor(rgbColor);
        cellTipPlasbagX.setBackgroundColor(rgbColor);


        //FALTA AGREGAR A LAS TABLAS
        miTable.addCell(cellTipPlastic.add(new Paragraph("TIPO DE PLASTICO").setFontSize(7.5f))) ;
        miTable.addCell(cellPolitubo.add(new Paragraph("POLITUBO").setFontSize(6.5f))) ;
        //tipo de caja


        if(object2.getTipoPlastico().equalsIgnoreCase("Politubo")) {
            cellPolituboX.add(new Paragraph(" X "));

        }else{

            cellPolituboX.add(new Paragraph("   ").setWidth(10));


        }
        miTable.addCell(cellPolituboX);



        miTable.addCell(cellPolipack.add(new Paragraph("POLIPACK").setFontSize(6.5f))) ;

        if(object2.getTipoPlastico().equalsIgnoreCase("Polipack")) {
            cellPolipackX.add(new Paragraph(" X "));

        }else{
            cellPolipackX.add(new Paragraph("  ").setWidth(10));

        }
        miTable.addCell(cellPolipackX);



        miTable.addCell(cellbanavac.add(new Paragraph("BANAVAC").setFontSize(6.5f))) ;

        if(object2.getTipoPlastico().equalsIgnoreCase("Banavac")) {
            cellbanavacX.add(new Paragraph(" X "));

        }

        else{

            cellbanavacX.add(new Paragraph("  ").setWidth(10));

        }

        miTable.addCell(cellbanavacX);


        // miTable.addCell(cellbanavac.add(new Paragraph("BANAVAC").setFontSize(6.5f))) ;


        miTable.addCell(cellTipPlasbagS.add(new Paragraph("BAGS").setFontSize(6.5f))) ;

        if(object2.getTipoPlastico().equalsIgnoreCase("BAGS")) {
            cellTipPlasbagX.add(new Paragraph("  X "));

        }else{

            cellTipPlasbagX.add(new Paragraph("  ").setWidth(10));

        }
        miTable.addCell(cellTipPlasbagX);



        /**tipo de cja */

        miTable.addCell(celltipodEcAJA.add(new Paragraph("TIPO DE CAJA").setFontSize(7.5f))) ;
        miTable.addCell(cell22Xu.add(new Paragraph("22xU").setFontSize(6.5f))) ;


        cell22XuX.setBackgroundColor(rgbColor) ; //MNARCAR LA X CON BACVGROUND VERDE
        cellDisplayX.setBackgroundColor(rgbColor);
        cell13kgX.setBackgroundColor(rgbColor);
        cell208X.setBackgroundColor(rgbColor);


        if(object2.getTipoPlastico().equalsIgnoreCase("22xu")) {
            cell22XuX.add(new Paragraph(" X "));
        }else{
            cell22XuX.add(new Paragraph("  ").setWidth(10));

        }
        miTable.addCell(cell22XuX) ;


        miTable.addCell(cellDisplay.add(new Paragraph("DISPLAY").setFontSize(6.5f))) ;


        if(object2.getTipoPlastico().equalsIgnoreCase("Display")) {
            cellDisplayX.add(new Paragraph(" X "));


        }else{
            cellDisplayX.add(new Paragraph("  ").setWidth(10));

        }
        miTable.addCell(cellDisplayX) ;



        miTable.addCell(cell13kg.add(new Paragraph("13 KG").setFontSize(6.5f))) ;

        if(object2.getTipoPlastico().equalsIgnoreCase("13 KG")) {
            cell13kgX.add(new Paragraph(" X "));

        }else{

            cell13kgX.add(new Paragraph("  ").setWidth(10));

        }
        miTable.addCell(cell13kgX) ;



        miTable.addCell(cell208.add(new Paragraph("208").setFontSize(6.5f))) ;

        if(object2.getTipoPlastico().equalsIgnoreCase("208")) {
            cell208X.add(new Paragraph(" X "));

        }else{
            cell208X.add(new Paragraph("  ").setWidth(10));


        }
        miTable.addCell(cell208X) ;







        return miTable;

    }

    public static Table createTbale6(Table table1,SetInformDatsHacienda object,DeviceRgb rgbColor){
        Cell cellDatosHaciend = new Cell(1,7).add(new Paragraph("DATOS DE HACIENDA").setFontSize(7.5f).setBackgroundColor(rgbColor));
        Cell micelda;
        Paragraph   paragrapMarcado = new Paragraph(" X ").setFontSize(7.5f);;
        Paragraph   paragragSinMarcar = new Paragraph(" ");;

        table1.addCell(cellDatosHaciend);
        Cell cellx1 = new Cell().add(new Paragraph("FUENTE DE AGUA").setFontSize(7.5f));
        Cell cellx2 = new Cell().add(new Paragraph("AGUA POTABLE").setFontSize(7.5f));
        table1.addCell(cellx1);
        table1.addCell(cellx2);

        if(object.getFuenteAgua().equalsIgnoreCase("AGUA POTABLE")) {
            micelda = new Cell(1,1).setBackgroundColor(rgbColor).setFontSize(7.5f).add(paragrapMarcado);
            micelda .setWidth(10f);
            table1.addCell(micelda);


        }else{

            micelda = new Cell(1,1).setBackgroundColor(rgbColor).setFontSize(7.5f).add(paragragSinMarcar);
            micelda .setWidth(10f);

            table1.addCell(micelda);


        }

        table1.addCell(new Paragraph("POZO").setFontSize(7.5f));

        if(object.getFuenteAgua().equalsIgnoreCase("POZO")) {
            micelda = new Cell(1,1).setBackgroundColor(rgbColor).setFontSize(7.5f).add(paragrapMarcado);
            micelda .setWidth(10f);

            table1.addCell(micelda);
        }
        else{

            micelda = new Cell(1,1).setBackgroundColor(rgbColor).setFontSize(7.5f).add(paragragSinMarcar);
            micelda .setWidth(10f);

            table1.addCell(micelda);
        }

        table1.addCell(new Paragraph("BIDON").setFontSize(7.5f));

        if(object.getFuenteAgua().equalsIgnoreCase("BIDON")) {

            micelda = new Cell(1,1).setBackgroundColor(rgbColor).setFontSize(7.5f).add(paragrapMarcado);
            micelda .setWidth(10f);

            table1.addCell(micelda);
        }else{

            micelda = new Cell(1,1).setBackgroundColor(rgbColor).setFontSize(7.5f).add(paragragSinMarcar);
            micelda .setWidth(10f);

            table1.addCell(micelda);
        }


        //2DA FILA DE DATOS DE HACIENDA...

        table1.addCell(new Paragraph("AGUA CORRIDA").setFontSize(7.5f));

        table1.addCell(new Paragraph("SI").setFontSize(7.5f));


        if(object.isHayAguaCorrida()) {
            //  table1.addCell(new Paragraph(" X").setBackgroundColor(rgbColor).setFontSize(7.5f));

            micelda = new Cell(1,1).setBackgroundColor(rgbColor).add(paragrapMarcado);
            table1.addCell(micelda);

        }else{

            micelda = new Cell(1,1).setBackgroundColor(rgbColor).setFontSize(7.5f).add(paragragSinMarcar);
            micelda .setWidth(10f);
            table1.addCell(micelda);

        }

        table1.addCell(new Paragraph("NO").setFontSize(7.5f));

        if(!object.isHayAguaCorrida()) {

            micelda = new Cell(1,2).setBackgroundColor(rgbColor).setFontSize(7.5f).add(paragrapMarcado);
            micelda .setWidth(10f);

            table1.addCell(micelda);

        }else{

            micelda = new Cell(1,3).setBackgroundColor(rgbColor).setFontSize(7.5f).add(paragragSinMarcar);
            micelda .setWidth(10f);

            table1.addCell(micelda);
        }

        table1.addCell(new Paragraph("LAVADO DE RACIMOS").setFontSize(7.5f));


        table1.addCell(new Paragraph("SI").setFontSize(7.5f));

        if(object.isHayLavadoRacimos()) {
            //  table1.addCell(new Paragraph(" X").setBackgroundColor(rgbColor));
            micelda = new Cell(1,1).setBackgroundColor(rgbColor).setFontSize(7.5f).add(paragrapMarcado);
            micelda .setWidth(10f);

            table1.addCell(micelda);

        }else{

            micelda = new Cell(1,1).setBackgroundColor(rgbColor).setFontSize(7.5f).add(paragragSinMarcar);
            micelda .setWidth(10f);

            table1.addCell(micelda);
        }

        table1.addCell(new Paragraph("NO").setFontSize(7.5f));

        if(!object.isHayLavadoRacimos()) {
            micelda = new Cell(1,3).setBackgroundColor(rgbColor).setFontSize(7.5f).add(paragrapMarcado);
            micelda .setWidth(10f);

            table1.addCell(micelda);
        }else{

            micelda = new Cell(1,3).setBackgroundColor(rgbColor).setFontSize(7.5f).add(paragragSinMarcar);
            micelda .setWidth(10f);

            table1.addCell(micelda);
        }


        /**fumigacion corona linea `1*/
        table1.addCell(new Paragraph("FUMIGACION CORONA LINEA 1").setFontSize(7.5f));

        table1.addCell(new Paragraph("FOGGING").setFontSize(7.5f));


        if(object.getFumigacionClin1().equalsIgnoreCase("FOGGING")) {
            micelda = new Cell(1,1).setBackgroundColor(rgbColor).setFontSize(7.5f).add(paragrapMarcado);
            micelda .setWidth(10f);

            table1.addCell(micelda);

        }else{

            micelda = new Cell(1,1).setBackgroundColor(rgbColor).setFontSize(7.5f).add(paragragSinMarcar);
            micelda .setWidth(10f);

            table1.addCell(micelda);

        }


        ////////
        table1.addCell(new Paragraph("BOMBA CP3").setFontSize(7.5f));


        if(object.getFumigacionClin1().equalsIgnoreCase("BOMBA CP3")) {

            micelda = new Cell(1,1).setBackgroundColor(rgbColor).setFontSize(7.5f).add(paragrapMarcado);
            micelda .setWidth(10f);

            table1.addCell(micelda);


        }else{

            micelda = new Cell(1,1).setBackgroundColor(rgbColor).setFontSize(7.5f).add(paragragSinMarcar);
            micelda .setWidth(10f);

            table1.addCell(micelda);
        }

////////////4

        table1.addCell(new Paragraph("ELECTRICA").setFontSize(7.5f));

        if(object.getFumigacionClin1().equalsIgnoreCase("ELECTRICA")) {
            micelda = new Cell(1,1).setBackgroundColor(rgbColor).setFontSize(7.5f).add(paragrapMarcado);
            micelda .setWidth(10f);
            table1.addCell(micelda);
        }else{
            micelda = new Cell(1,1).setBackgroundColor(rgbColor).setFontSize(7.5f).add(paragragSinMarcar);
            micelda .setWidth(10f);

            table1.addCell(micelda);
        }



        return table1;
    }


    public static Table createTable3(Table miTable, DeviceRgb rgbColor,SetInformEmbarque2 object2) {


        Cell cell208X= new Cell().setPaddingLeft(10f);


        ///cellPolituboX.setBackgroundColor(rgbColor) ; //MNARCAR LA X CON BACVGROUND VERDE

        //FALTA AGREGAR A LAS TABLAS
        //  miTable.addCell(new Cell().add(new Paragraph("TIPO DE PLASTICO").setFontSize(7.5f))) ;


        miTable.addCell(new Cell().add(new Paragraph("ENSUCHADO").setFontSize(7.5f).setPaddingLeft(10f))) ;
        miTable.addCell(new Cell().add(new Paragraph("SI").setFontSize(7.5f).setPaddingLeft(10f))) ;

        if(object2.isHayExcelnsuchado()) {
            miTable.addCell(new Cell().add(new Paragraph(" X ").setFontSize(7.5f).setPaddingLeft(10f).setBackgroundColor(rgbColor))) ;
        }

        else {
            miTable.addCell(new Cell().add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f).setBackgroundColor(rgbColor))) ;
        }

        miTable.addCell(new Cell().add(new Paragraph("NO").setFontSize(7.5f).setPaddingLeft(10f))) ;


        if(!object2.isHayExcelnsuchado()) {
            miTable.addCell(new Cell().add(new Paragraph(" X ").setFontSize(7.5f).setPaddingLeft(10f).setBackgroundColor(rgbColor))) ;
        }

        else {
            miTable.addCell(new Cell().add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f).setBackgroundColor(rgbColor))) ;
        }


        /**BALANZA*///*/

        miTable.addCell(new Cell().add(new Paragraph("BALANZA").setFontSize(7.5f).setPaddingLeft(10f))) ;
        miTable.addCell(new Cell().add(new Paragraph("SI").setFontSize(7.5f).setPaddingLeft(10f))) ;

        if(object2.isHayBalanza()) {
            miTable.addCell(new Cell().add(new Paragraph(" X ").setFontSize(7.5f).setPaddingLeft(10f).setBackgroundColor(rgbColor))) ;
        }

        else {
            miTable.addCell(new Cell().add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f).setBackgroundColor(rgbColor))) ;
        }


        miTable.addCell(new Cell().add(new Paragraph("NO").setFontSize(7.5f).setPaddingLeft(10f))) ;


        if(!object2.isHayBalanza()) {
            miTable.addCell(new Cell().add(new Paragraph(" X ").setFontSize(7.5f).setPaddingLeft(10f).setBackgroundColor(rgbColor))) ;
        }

        else {
            miTable.addCell(new Cell().add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f).setBackgroundColor(rgbColor))) ;
        }



        /** CONDICION DE BALANZA*///*/


        /**condicion de  BALANZA*///*/

        miTable.addCell(new Cell().add(new Paragraph("CONDICION DE BALANZA").setFontSize(7.5f).setPaddingLeft(10f))) ;

        //BUENO ,ALO Y REGUKLAR

        if(object2.getCondicionBalanza().equalsIgnoreCase("BUENA")) {

            Log.i("condicoon","es buena se ejecuto esto ") ;

            miTable.addCell(new Cell().add(new Paragraph("BUENA").setFontSize(7.5f).setPaddingLeft(10f))) ;

            miTable.addCell(new Cell().add(new Paragraph(" X ").setFontSize(7.5f).setPaddingLeft(10f).setBackgroundColor(rgbColor))) ;


            miTable.addCell(new Cell().add(new Paragraph("MALA").setFontSize(7.5f).setPaddingLeft(10f))) ;
            miTable.addCell(new Cell().add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f).setBackgroundColor(rgbColor))) ;


        }

        if(object2.getCondicionBalanza().equalsIgnoreCase("MALA")) {
            miTable.addCell(new Cell().add(new Paragraph("MALA ").setFontSize(7.5f).setPaddingLeft(10f))) ;
            miTable.addCell(new Cell().add(new Paragraph("X").setFontSize(7.5f).setPaddingLeft(10f).setBackgroundColor(rgbColor))) ;

            miTable.addCell(new Cell().add(new Paragraph("BUENA").setFontSize(7.5f).setPaddingLeft(10f))) ;
            miTable.addCell(new Cell().add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f).setBackgroundColor(rgbColor))) ;
        }

        if(object2.getCondicionBalanza().equalsIgnoreCase("REGULAR")) {
            miTable.addCell(new Cell().add(new Paragraph("REGULAR").setFontSize(7.5f).setPaddingLeft(10f))) ;
            miTable.addCell(new Cell().add(new Paragraph(" X ").setFontSize(7.5f).setPaddingLeft(10f).setBackgroundColor(rgbColor))) ;


            miTable.addCell(new Cell().add(new Paragraph("BUENA").setFontSize(7.5f).setPaddingLeft(10f))) ;
            miTable.addCell(new Cell().add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f).setBackgroundColor(rgbColor))) ;

        }


/***tipo de balanza/
 *
 */


        miTable.addCell(new Cell().add(new Paragraph("TIPO BALANZA").setFontSize(7.5f).setPaddingLeft(10f))) ;
        miTable.addCell(new Cell().add(new Paragraph("BASCULA").setFontSize(7.5f).setPaddingLeft(10f))) ;

        if(object2.getTipoDeBalanza().equalsIgnoreCase("BASCULA")) {
            miTable.addCell(new Cell().add(new Paragraph(" X ").setFontSize(7.5f).setPaddingLeft(10f).setBackgroundColor(rgbColor))) ;
        }

        else {
            miTable.addCell(new Cell().add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f).setBackgroundColor(rgbColor))) ;
        }



        miTable.addCell(new Cell().add(new Paragraph("DIGITAL").setFontSize(7.5f).setPaddingLeft(10f))) ;

        if(object2.getTipoDeBalanza().equalsIgnoreCase("DIGITAL")) {
            miTable.addCell(new Cell().add(new Paragraph(" X ").setFontSize(7.5f).setPaddingLeft(10f).setBackgroundColor(rgbColor))) ;
        }

        else {
            miTable.addCell(new Cell().add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f).setBackgroundColor(rgbColor))) ;
        }





/***BALANZA DE REPESA/
 */


        miTable.addCell(new Cell().add(new Paragraph("BALANZA DE REPESA").setFontSize(7.5f).setPaddingLeft(10f))) ;
        miTable.addCell(new Cell().add(new Paragraph("SI").setFontSize(7.5f).setPaddingLeft(10f))) ;

        if(object2.getHayBalanzaRepeso()) {
            miTable.addCell(new Cell().add(new Paragraph(" X ").setFontSize(7.5f).setPaddingLeft(10f).setBackgroundColor(rgbColor))) ;
        }

        else {
            miTable.addCell(new Cell().add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f).setBackgroundColor(rgbColor))) ;
        }



        miTable.addCell(new Cell().add(new Paragraph("NO").setFontSize(7.5f).setPaddingLeft(10f))) ;

        if(!object2.getHayBalanzaRepeso()) {
            miTable.addCell(new Cell().add(new Paragraph(" X ").setFontSize(7.5f).setPaddingLeft(10f).setBackgroundColor(rgbColor))) ;
        }

        else {
            miTable.addCell(new Cell().add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f).setBackgroundColor(rgbColor))) ;
        }



        //////// //si hay balanza de repeso
        if(object2.getHayBalanzaRepeso()){
            miTable.addCell(new Cell().add(new Paragraph("TIPO DE BALANZA DE REPESA").setFontSize(7.5f).setPaddingLeft(10f))) ;

            miTable.addCell(new Cell().add(new Paragraph("BASCULA").setFontSize(7.5f).setPaddingLeft(10f))) ;


            if(object2.getTipoDeBalanzaRepeso().equalsIgnoreCase("bascula")) {
                miTable.addCell(new Cell().add(new Paragraph(" X ").setFontSize(7.5f).setPaddingLeft(10f).setBackgroundColor(rgbColor))) ;
            }

            else {
                miTable.addCell(new Cell().add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f).setBackgroundColor(rgbColor))) ;
            }



            miTable.addCell(new Cell().add(new Paragraph("DIGITAL").setFontSize(7.5f).setPaddingLeft(10f))) ;


            if(!object2.getTipoDeBalanzaRepeso().equalsIgnoreCase("DIGITAL")) {
                miTable.addCell(new Cell().add(new Paragraph(" X ").setFontSize(7.5f).setPaddingLeft(10f).setBackgroundColor(rgbColor))) ;
            }

            else {
                miTable.addCell(new Cell().add(new Paragraph(" ").setFontSize(7.5f).setPaddingLeft(10f).setBackgroundColor(rgbColor))) ;
            }


        }



        ///QUEDAMOS EN DATOS DE HACIENDA..

        return miTable;

    }



    /**Calibracion de fruta clanedario de enfunde*/


    public static  Table createTABLEcalendarioEnfude(Table table1X,DeviceRgb rgbColor ,SetInformDatsHacienda inform){


        float araycolumccc[]= {1,1,1,1};
        table1X=  new Table(araycolumccc);


        Cell cellHeader2= new Cell(1,4).setBackgroundColor(rgbColor);
        cellHeader2.add(new Paragraph(" CALIBRACION DE FRUTA( CALENDARIO DE ENFUNDE) ").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f));
        table1X.addCell(cellHeader2);


        Cell cellSemana= new Cell(1,1);
        cellSemana.add(new Paragraph(" SEMANA ").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f));

        table1X.addCell(cellSemana);



        Cell cellColor= new Cell(1,1);
        cellColor.add(new Paragraph(" COLOR ").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f));

        table1X.addCell(cellColor);


        Cell cellNUMrAC= new Cell(1,1);
        cellNUMrAC.add(new Paragraph("NUMERACION RACIMOS ").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f));
        table1X.addCell(cellNUMrAC);


        Cell cellPorcent= new Cell(1,1);
        cellPorcent.add(new Paragraph(" PORCENTAJE ").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f));
        table1X.addCell(cellPorcent);


        ArrayList<String> values= new ArrayList<>();

        values.add(" 14 ");
        values.add(inform.getColortSem14());
        values.add(inform.getNumRcim14());
        values.add(inform.getPorc14());

        values.add(" 13 ");
        values.add(inform.getColortSem13());
        values.add(inform.getNumRcim13());
        values.add(inform.getPorc13());


        values.add(" 12 ");
        values.add(inform.getColortSem12());
        values.add(inform.getNumRcim12());
        values.add(inform.getPorc12());


        values.add(" 11 ");
        values.add(inform.getColortSem11());
        values.add(inform.getNumRcim11());
        values.add(inform.getPorc11());

        values.add(" 10 ");
        values.add(inform.getColortSem10());
        values.add(inform.getNumRcim10());
        values.add(inform.getPorc10());

        values.add(" 9 ");
        values.add(inform.getColortSem9()); //CORREGIR
        values.add(inform.getNumRcim9());
        values.add(inform.getPorc9());


        int  [] arranumsAddOtherFontSize  = {0,4,8,12,16,20} ;


        for (int i = 0; i < values.size(); i++) {

            Cell cellNUMrACx= new Cell(1,1);

            if(Arrays.asList(arranumsAddOtherFontSize).contains(i)){

                cellNUMrACx.add(new Paragraph(values.get(i)).
                        setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());

            }else{

                cellNUMrACx.add(new Paragraph(values.get(i)).
                        setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f));


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
        list.add("lg,latex gelatinoso");
        list.add("p,pequeña");
        list.add("ml,mal formado");
        list.add("is,manchas de latex");
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
        list.add("re,residuos quimicos");
        list.add("rr,mancha roja");
        list.add("ps,daño de punta");
        list.add("ab,pulpa crema");
        list.add("wl,cochinilla");
        list.add("lg,latex gelatinoso");
        list.add("mf,mutilado");
        list.add("sm,fumagina");
        list.add("ps,punto jhonson");
        list.add("ls,latex gelatinoso");
        list.add("wl,cochinilla");
        list.add("sk,speckling");
        list.add("sc,rasguño");
        list.add("dt,fruta sucia");  //34

        // defectos de empaque //////////

        list.add("sre,estropeo empaque");
        list.add("bre,daño empaque");
        list.add("nie,cullo roto empaque");
        list.add("pse,daño de punta empaque");
        list.add("srfe,friccion empaque");
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

                Cell cellbold = new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(array[0].toUpperCase()).setFontSize(12f).setBold().setPaddingLeft(5f));
                Cell cellSign = new Cell(1,3).setBorder(Border.NO_BORDER).add(new Paragraph(array[1].toUpperCase()).setFontSize(12f).setPaddingLeft(5f));

                table.addCell(cellbold);
                table.addCell(cellSign);


            }

            if(i ==35){
                Cell cellbold = new Cell(1,6).setBorder(Border.NO_BORDER).setPaddingTop(7f).setPaddingBottom(7f).setBorder(Border.NO_BORDER).add(new Paragraph( "DEFECTOS EN EMPAQUE").
                        setTextAlignment(TextAlignment.LEFT).setFontSize(13f).setBold().setPaddingLeft(5f));
                table.addCell(cellbold);


            }else{
                if(i !=34) {
                    String [] array =list.get(i).split(",");

                    Cell cellbold = new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(array[0].toUpperCase()).setFontSize(12f).setBold().setPaddingLeft(5f));
                    Cell cellSign = new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(array[1].toUpperCase()).setFontSize(12f).setPaddingLeft(5f));

                    table.addCell(cellbold);
                    table.addCell(cellSign);
                }


            }



        }

        return table;

    }



    /**ESTO CREARA UN TABLE CADA VEZ QUE SE LLAMA**/
    public static  Table createTableEvaluacionYcondcionFruta(ControlCalidad objecControlCald,HashMap <String, String> hashMapControlCald,HashMap <String, String> hashMapDefctChecked,Context contexto){
        //aqui creamos la info con esta data.....

        ArrayList<Double>listPh = new ArrayList<>();
        ArrayList<DefectsCantdad>defectsSeleccion= new ArrayList<>();
        ArrayList<DefectsCantdad>defectsEmpaque= new ArrayList<>();


        //DATOS QUE NECESITAMOS OBTENERdoub
        double PROMEDIO_PESO=0;
        double CALIDAD_TOTAL;
        double PORCENTAJE_DE_DEFECTOS;
        int NUMERO_DEFECTS;
        String MAYOR_DEFECTO_SELECCION="NO";
        String MAYOR_DEFECTO_EMPAQUE="NO";
        int NUMERO_DE_CLUSTERS_iNSPECCIONADOS=0;
        int NUMERO_DE_CLUSTERS_POR_CAJA;

        int NUMERO_DE_DEDOS;
        double GRADO_CALIBRE_PROMEDIO;
        double LARGO_DEDOS_PROMEDIO;
        double PH_PROMEDIO=0;




        int  []keyDatPh  ={R.id.ediPH1,R.id.ediPH2,R.id.ediPH3,R.id.ediPH4,R.id.ediPH5,R.id.ediPH6,R.id.ediPH7,
                R.id.ediPH8,R.id.ediPH9,R.id.ediPH10};

        int  [] keyDatsPeso={R.id.ediPesoL1,R.id.ediPesoL2,R.id.ediPesoL3,R.id.ediPesoL4,R.id.ediPesoL5,R.id.ediPesoL6,R.id.ediPesoL7,
                R.id.ediPesoL8,R.id.ediPesoL9,R.id.ediPesoL10};


        int  [] keyDatsNumClusters={R.id.ediNumClusInsp1,R.id.ediNumClusInsp2,R.id.ediNumClusInsp3,R.id.ediNumClusInsp4,
                R.id.ediNumClusInsp5,R.id.ediNumClusInsp6,R.id.ediNumClusInsp7, R.id.ediNumClusInsp8,R.id.ediNumClusInsp9,R.id.ediNumClusInsp10};


        int  [] keyDatsNumDefectSelecion={R.id.imgSelecDefc1,R.id.imgSelecDefc2,R.id.imgSelecDefc3,R.id.imgSelecDefc4,
                R.id.imgSelecDefc5,R.id.imgSelecDefc6,R.id.imgSelecDefc7, R.id.imgSelecDefc8,R.id.imgSelecDefc9,R.id.imgSelecDefc10};



        int  [] keyDatsNumDefectSempqs={R.id.imvEmpaque1,R.id.imvEmpaque2,R.id.imvEmpaque3,R.id.imvEmpaque4,
                R.id.imvEmpaque5,R.id.imvEmpaque6,R.id.imvEmpaque7, R.id.imvEmpaque8,R.id.imvEmpaque9,R.id.imvEmpaque10};





        int  [] keyaRRAYnumClustXcajaLine1={R.id.edif2NdedoXclustxC1,R.id.edif2NdedoXclustxC2,R.id.edif2NdedoXclustxC3,R.id.edif2NdedoXclustxC4,
                R.id.edif2NdedoXclustxC5,R.id.edif2NdedoXclustxC6,R.id.edif2NdedoXclustxC7, R.id.edif2NdedoXclustxC8,R.id.edif2NdedoXclustxC9,R.id.edif2NdedoXclustxC10
                ,R.id.edif2NdedoXclustxC11,R.id.edif2NdedoXclustxC12,R.id.edif2NdedoXclustxC13,R.id.edif2NdedoXclustxC14,R.id.edif2NdedoXclustxC15,R.id.edif2NdedoXclustxC16,
                R.id.edif2NdedoXclustxC17,R.id.edif2NdedoXclustxC18};

        int  [] keyaRRAYnumClustXcajaLine2={R.id.ediNdedoXclustXc1,R.id.ediNdedoXclustXc2,R.id.ediNdedoXclustXc3,R.id.ediNdedoXclustXc4,
                R.id.ediNdedoXclustXc5,R.id.ediNdedoXclustXc6,R.id.ediNdedoXclustXc7, R.id.ediNdedoXclustXc8,R.id.ediNdedoXclustXc9,R.id.ediNdedoXclustXc10
                ,R.id.ediNdedoXclustXc11,R.id.ediNdedoXclustXc12,R.id.ediNdedoXclustXc13,R.id.ediNdedoXclustXc14,R.id.ediNdedoXclustXc15,R.id.ediNdedoXclustXc16
                ,R.id.ediNdedoXclustXc17,R.id.ediNdedoXclustXc18};


        int  [] keyaRRAYnumnUMdedosFil1={R.id.ediNdedoXclust1,R.id.ediNdedoXclust2,R.id.ediNdedoXclust3,R.id.ediNdedoXclust4,
                R.id.ediNdedoXclust5,R.id.ediNdedoXclust6,R.id.ediNdedoXclust7, R.id.ediNdedoXclust8,R.id.ediNdedoXclust9,R.id.ediNdedoXclust10
                ,R.id.ediNdedoXclust11,R.id.ediNdedoXclust12,R.id.ediNdedoXclust13,R.id.ediNdedoXclust14,R.id.ediNdedoXclust15,R.id.ediNdedoXclust16,
                R.id.ediNdedoXclust17,R.id.ediNdedoXclust18
                ,R.id.ediNdedoXclust19,R.id.ediNdedoXclust20,R.id.ediNdedoXclust21,R.id.ediNdedoXclust22,R.id.ediNdedoXclust23,R.id.ediNdedoXclust24
                ,R.id.ediNdedoXclust25,R.id.ediNdedoXclust26,R.id.ediNdedoXclust27,R.id.ediNdedoXclust28

        };


        int  [] keyaRRAYnumnUMdedosFil2={R.id.edif2NdedoXclust1,R.id.edif2NdedoXclust2,R.id.edif2NdedoXclust3,R.id.edif2NdedoXclust4,
                R.id.edif2NdedoXclust5,R.id.edif2NdedoXclust6,R.id.edif2NdedoXclust7, R.id.edif2NdedoXclust8,R.id.edif2NdedoXclust9,R.id.edif2NdedoXclust10
                ,R.id.edif2NdedoXclust11,R.id.edif2NdedoXclust12,R.id.edif2NdedoXclust13,R.id.edif2NdedoXclust14,R.id.edif2NdedoXclust15,R.id.edif2NdedoXclust16,
                R.id.edif2NdedoXclust17,R.id.edif2NdedoXclust18
                ,R.id.edif2NdedoXclust19,R.id.edif2NdedoXclust20,R.id.edif2NdedoXclust21,R.id.edif2NdedoXclust22,R.id.edif2NdedoXclust23
                ,R.id.edif2NdedoXclust24,R.id.edif2NdedoXclust25,R.id.edif2NdedoXclust26,R.id.edif2NdedoXclust27,R.id.edif2NdedoXclust28
        };


        int  [] keyaRRAYcalibracionesFil1={R.id.ediCalByA1,R.id.ediCalByA2,R.id.ediCalByA3,R.id.ediCalByA4,
                R.id.ediCalByA5,R.id.ediCalByA6,R.id.ediCalByA7, R.id.ediCalByA8,R.id.ediCalByA9,R.id.ediCalByA10
                ,R.id.ediCalByA11,R.id.ediCalByA12,R.id.ediCalByA13,R.id.ediCalByA14,R.id.ediCalByA15,R.id.ediCalByA16,
                R.id.ediCalByA17,R.id.ediCalByA18};


        int  [] keyaRRAYcalibracionesFil2={R.id.edif2Calib1,R.id.edif2Calib2,R.id.edif2Calib3,R.id.edif2Calib4,
                R.id.edif2Calib5,R.id.edif2Calib6,R.id.edif2Calib7, R.id.edif2Calib8,R.id.edif2Calib9,R.id.edif2Calib10
                ,R.id.edif2Calib11,R.id.edif2Calib12,R.id.edif2Calib13,R.id.edif2Calib14,R.id.edif2Calib15,R.id.edif2Calib16,
                R.id.edif2Calib17,R.id.edif2Calib18};


        int  [] keyaRRAYlargoFil1={R.id.ediLargDeds1,R.id.ediLargDeds2,R.id.ediLargDeds3,R.id.ediLargDeds4,
                R.id.ediLargDeds5,R.id.ediLargDeds6,R.id.ediLargDeds7, R.id.ediLargDeds8,R.id.ediLargDeds9,R.id.ediLargDeds10
                ,R.id.ediLargDeds11,R.id.ediLargDeds12,R.id.ediLargDeds13,R.id.ediLargDeds14,R.id.ediLargDeds15,R.id.ediLargDeds16,
                R.id.ediLargDeds17,R.id.ediLargDeds18,R.id.ediLargDeds19,R.id.ediLargDeds20,R.id.ediLargDeds21,R.id.ediLargDeds22
                ,R.id.ediLargDeds23,R.id.ediLargDeds24,R.id.ediLargDeds25,R.id.ediLargDeds26,R.id.ediLargDeds27,R.id.ediLargDeds28
                ,R.id.ediLargDeds29,R.id.ediLargDeds30};


        int  [] keyaRRAYlargoFil2={R.id.edif2LrgD1,R.id.edif2LrgD2,R.id.edif2LrgD3,R.id.edif2LrgD4,
                R.id.edif2LrgD5,R.id.edif2LrgD6,R.id.edif2LrgD7, R.id.edif2LrgD8,R.id.edif2LrgD9,R.id.edif2LrgD10
                ,R.id.edif2LrgD11,R.id.edif2LrgD12,R.id.edif2LrgD13,R.id.edif2LrgD14,R.id.edif2LrgD15,R.id.edif2LrgD16,
                R.id.edif2LrgD17,R.id.edif2LrgD18 ,R.id.edif2LrgD19,R.id.edif2LrgD20,R.id.edif2LrgD21,R.id.edif2LrgD22
                ,R.id.edif2LrgD23,R.id.edif2LrgD24,R.id.edif2LrgD25,R.id.edif2LrgD26,R.id.edif2LrgD27,R.id.edif2LrgD28
                ,R.id.edif2LrgD29,R.id.edif2LrgD30};



        int resultLine1=0;
        int resultLine2=0;

        double resultLine1Double=0;
        double resultLine2Double=0;

        for(int i=0; i<keyaRRAYlargoFil1.length ;i++) {
            //keyDatsPesoarray
            //itremoas
            String keyCurrent1= String.valueOf(keyaRRAYlargoFil1[i])  ;
            if(hashMapControlCald.containsKey(keyCurrent1)){
                resultLine1Double=resultLine1Double +Double.parseDouble(hashMapControlCald.get(keyCurrent1));
            }

            String keyCurrent2= String.valueOf(keyaRRAYlargoFil2[i])  ;
            if(hashMapControlCald.containsKey(keyCurrent2)){
                resultLine2Double=resultLine2Double +Double.parseDouble(hashMapControlCald.get(keyCurrent2));

            }


        }

        /**gheneramos numero de LARGO_DEDOS_PROMEDIO*/
        LARGO_DEDOS_PROMEDIO=resultLine2Double/resultLine1Double;

        Log.i("ELWEIGTH","EL LARGO_DEDOS_PROMEDIO  ES "+LARGO_DEDOS_PROMEDIO);

         resultLine1Double=0;
         resultLine2Double=0;

        for(int i=0; i<keyaRRAYcalibracionesFil1.length ;i++) {
            //keyDatsPesoarray
            //itremoas
            String keyCurrent1= String.valueOf(keyaRRAYcalibracionesFil1[i])  ;
            if(hashMapControlCald.containsKey(keyCurrent1)){
                resultLine1Double=resultLine1Double +Double.parseDouble(hashMapControlCald.get(keyCurrent1));
            }

            String keyCurrent2= String.valueOf(keyaRRAYcalibracionesFil2[i])  ;
            if(hashMapControlCald.containsKey(keyCurrent2)){
                resultLine2Double=resultLine2Double +Double.parseDouble(hashMapControlCald.get(keyCurrent2));

            }


        }

        /**gheneramos numero de GRADO_CALIBRE_PROMEDIO*/
        GRADO_CALIBRE_PROMEDIO=resultLine2Double/resultLine1Double;

        Log.i("ELWEIGTH","EL GRADO_CALIBRE_PROMEDIO  PROMEDIO ES "+GRADO_CALIBRE_PROMEDIO);







         resultLine1=0;
         resultLine2=0;

        for(int i=0; i<keyaRRAYnumnUMdedosFil1.length ;i++) {
            //keyDatsPesoarray
            //itremoas
            String keyCurrent1= String.valueOf(keyaRRAYnumnUMdedosFil1[i])  ;
            if(hashMapControlCald.containsKey(keyCurrent1)){
                resultLine1=resultLine1 +Integer.parseInt(hashMapControlCald.get(keyCurrent1));
            }

            String keyCurrent2= String.valueOf(keyaRRAYnumnUMdedosFil2[i])  ;
            if(hashMapControlCald.containsKey(keyCurrent2)){
                resultLine2=resultLine2 +Integer.parseInt(hashMapControlCald.get(keyCurrent2));

            }


        }

        /**gheneramos numero de NUMERO_DE_DEDOS*/
        NUMERO_DE_DEDOS=resultLine2/resultLine1;

        Log.i("ELWEIGTH","EL NUMERO_DE_DEDOS  PROMEDIO ES "+NUMERO_DE_DEDOS);




        resultLine1=0;
         resultLine2=0;

        for(int i=0; i<keyaRRAYnumClustXcajaLine1.length ;i++) {
            //keyDatsPesoarray
            //itremoas
            String keyCurrent1= String.valueOf(keyaRRAYnumClustXcajaLine1[i])  ;
            if(hashMapControlCald.containsKey(keyCurrent1)){
                resultLine1=resultLine1 +Integer.parseInt(hashMapControlCald.get(keyCurrent1));
            }

            String keyCurrent2= String.valueOf(keyaRRAYnumClustXcajaLine2[i])  ;
            if(hashMapControlCald.containsKey(keyCurrent2)){
                resultLine2=resultLine2 +Integer.parseInt(hashMapControlCald.get(keyCurrent2));

            }


        }

        /**gheneramos numero de CLUSTERS*/
        NUMERO_DE_CLUSTERS_POR_CAJA=resultLine2/resultLine1;

        Log.i("ELWEIGTH","EN NUMERO_DE_CLUSTERS_POR_CAJA  PROMEDIO ES "+NUMERO_DE_CLUSTERS_POR_CAJA);



        ///            listTOrETURN1.add(new NameAndValue("DESTINO",Object1.getDestinoContenedor()));

       /**GENERAMNOS PROMOEDIO PESO*/
           int numeroItemsEcontrados =0;

        for(int i=0; i<keyDatsPeso.length ;i++) {
              //keyDatsPesoarray
              //itremoas
              String keyCurrent= String.valueOf(keyDatsPeso[i])  ;

              if(hashMapControlCald.containsKey(keyCurrent)){

                  PROMEDIO_PESO=PROMEDIO_PESO + Double.parseDouble(hashMapControlCald.get(keyCurrent));
                  numeroItemsEcontrados++;

              }

          }
        //tenemos que redondiar el peso lñibras
        PROMEDIO_PESO =  PROMEDIO_PESO/numeroItemsEcontrados;

        Log.i("ELWEIGTH","EN PESO PROMEDIO ES "+PROMEDIO_PESO);




        //agregamos el peso libras
       // dataTotable.add(new NameAndValue( "PROMEDIO PESO",pesoLibrastext));
       // Log.i("ELWEIGTH","EL PESO PROMEDIO  ES "+pesoLibrastext);
            /**PH_PROMEDIO*/
        /**PH PROMEDIO */  //SI NO TIENE PH AGREGAMOS 0

        for(int indice =0; indice <keyDatPh.length; indice++){
            String keyValue = String.valueOf(keyDatPh[indice]);
            if(hashMapControlCald.containsKey(keyValue)) {
                listPh.add(Double.parseDouble(hashMapControlCald.get(keyValue)));

                PH_PROMEDIO=PH_PROMEDIO + Double.parseDouble(hashMapControlCald.get(keyValue));

            }

        }

        PH_PROMEDIO =  PH_PROMEDIO/numeroItemsEcontrados;
        Log.i("ELWEIGTH","EN PH  PROMEDIO ES "+PH_PROMEDIO);

       // PROMEDIO_PESO=PROMEDIO_PESO + Double.parseDouble(hashMapControlCald.get(keyCurrent));




        /**DEFECTOS SELECION*/
          String  [] arrayuDefectsSeleccNames =contexto.getResources().getStringArray(R.array.array_defectos_fruta);


        for(int i=0; i<keyDatsNumDefectSelecion.length ;i++) {
            //iteramos arry con las keys
            String keyCurrent= String.valueOf(keyDatsNumDefectSelecion[i])  ;

            if(hashMapDefctChecked.containsKey(keyCurrent)){

                String value =hashMapDefctChecked.get(keyCurrent);
                //cremoas un array con el valor de ese string..
                String [] posicionDefectoEncontrados=value.split(",");

                for(int indice2=0; indice2<posicionDefectoEncontrados.length ;indice2++){
                         int posicionDefecto=Integer.parseInt(posicionDefectoEncontrados [indice2]);
                         //posicion 0 y 1 example//en caos qu queramos la fila podemos usar el nuemro defe4ctos por ahora esta en 0
                    defectsSeleccion.add(new DefectsCantdad(0,arrayuDefectsSeleccNames[posicionDefecto]));

                        }
            }

        }
        Log.i("ELWEIGTH","EN TOTAL DEFECTOS SELECION HAY "+defectsSeleccion.size());




        /**DEFECTOS EMPAQUE*/

        String   [] arrayuDefectSeMPAQUENames =contexto.getResources().getStringArray(R.array.array_defectos_empaque);

        for(int i=0; i<keyDatsNumDefectSempqs.length ;i++) {
            //iteramos arry con las keys
            String keyCurrent= String.valueOf(keyDatsNumDefectSempqs[i])  ;

            if(hashMapDefctChecked.containsKey(keyCurrent)){

                String value =hashMapDefctChecked.get(keyCurrent);
                //cremoas un array con el valor de ese string..
                String [] posicionDefectoEncontrados=value.split(",");

                for(int indice2=0; indice2<posicionDefectoEncontrados.length ;indice2++){

                    int posicionDefecto=Integer.parseInt(posicionDefectoEncontrados [indice2]);
                    //posicion 0 y 1 example//en caos qu queramos la fila podemos usar el nuemro defe4ctos por ahora esta en 0
                    defectsEmpaque.add(new DefectsCantdad(0,arrayuDefectSeMPAQUENames[posicionDefecto]));

                }
            }

        }


        NUMERO_DEFECTS=defectsSeleccion.size()+defectsEmpaque.size();

                Log.i("ELWEIGTH","EN TOTAL TODOS LOS DEFECTOE ES "+NUMERO_DEFECTS);


                //los nombre de los defectos que selecionaron
        ArrayList<String>defectsSelecNames = new ArrayList<>();

        for(int indice2=0; indice2<defectsSeleccion.size() ;indice2++){
          //obtenemos la lista de defectos..

               if(!defectsSelecNames.contains(defectsSeleccion.get(indice2).getNombreDefect())){

                   defectsSelecNames.add(defectsSeleccion.get(indice2).getNombreDefect());

               }


        }


                 /***MAYOR DEFECTO SELECION*/



        int numeroDfectosCurrentDefect;
        int numeroDefectosMayor=0;

        for(int indice2=0; indice2<defectsSelecNames.size() ;indice2++){

            String currentNombreDefecto =defectsSelecNames.get(indice2);

            numeroDfectosCurrentDefect=0;


            for(int indice3=0; indice3<defectsSeleccion.size() ;indice3++){
                            DefectsCantdad currnetdefc= defectsSeleccion.get(indice2);

                            if(currnetdefc.getNombreDefect().equals(currentNombreDefecto)){
                                numeroDfectosCurrentDefect++;
                            }
             }
          //  DefectsCantdad defctCantidad =  new DefectsCantdad(numeroDfectosCurrentDefect,currentNombreDefecto);
           // numeroDefectosCada1.add(defctCantidad);

            if(numeroDfectosCurrentDefect>=numeroDefectosMayor){
                numeroDefectosMayor =numeroDfectosCurrentDefect;
                MAYOR_DEFECTO_SELECCION=currentNombreDefecto;



            }

        }

        Log.i("ELWEIGTH","el defecto mayor en selecion es  es "+MAYOR_DEFECTO_SELECCION);


        /**numero de CLUSTERS INSPECCIONADOS */
        for(int indice =0; indice <keyDatsNumClusters.length; indice++){

            String keyValue = String.valueOf(keyDatsNumClusters[indice]);

            if(hashMapControlCald.containsKey(keyValue)) {
                NUMERO_DE_CLUSTERS_iNSPECCIONADOS=NUMERO_DE_CLUSTERS_iNSPECCIONADOS+Integer.parseInt(hashMapControlCald.get(keyValue));
            }


        }

        Log.i("ELWEIGTH","EN TOTAL DE numClustersInspeccinads  ES "+NUMERO_DE_CLUSTERS_iNSPECCIONADOS);


        Log.i("ELWEIGTH","A TEXT1 ES "+NUMERO_DEFECTS);
        Log.i("ELWEIGTH","A TEXT2 ES "+NUMERO_DE_CLUSTERS_iNSPECCIONADOS);

        PORCENTAJE_DE_DEFECTOS=((double)NUMERO_DEFECTS/100) * NUMERO_DE_CLUSTERS_iNSPECCIONADOS;
        Log.i("ELWEIGTH","el PORCENTAJE_DE_DEFECTOS es "+PORCENTAJE_DE_DEFECTOS);

        CALIDAD_TOTAL=100-PORCENTAJE_DE_DEFECTOS;
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
      numeroDefectosMayor=0;

        for(int indice2=0; indice2<defectsSelecNames.size() ;indice2++){

            String currentNombreDefecto =defectsSelecNames.get(indice2);

            numeroDfectosCurrentDefect=0;


            for(int indice3=0; indice3<defectsEmpaque.size() ;indice3++){
                DefectsCantdad currnetdefc= defectsEmpaque.get(indice2);

                if(currnetdefc.getNombreDefect().equals(currentNombreDefecto)){
                    numeroDfectosCurrentDefect++;
                }
            }


            if(numeroDfectosCurrentDefect>=numeroDefectosMayor){

                numeroDefectosMayor =numeroDfectosCurrentDefect;
                MAYOR_DEFECTO_EMPAQUE=currentNombreDefecto;
            }
        }

        Log.i("ELWEIGTH","el defecto MAYOR_DEFECTO_EMPAQUE ES "+MAYOR_DEFECTO_EMPAQUE);


        ///entonces vamos a crear la tabla
      DecimalFormat df = new DecimalFormat("#.##");
        // String pesoLibrastext= df.format(pesoLibras);


         if(MAYOR_DEFECTO_SELECCION.contains(":")){
            String array[]=MAYOR_DEFECTO_SELECCION.split(":");


            MAYOR_DEFECTO_SELECCION=array[0];
         }


        if(MAYOR_DEFECTO_EMPAQUE.contains(":")){
            String array[]=MAYOR_DEFECTO_EMPAQUE.split(":");


            MAYOR_DEFECTO_EMPAQUE=array[0];
        }


        TableCalidProdc.add(new TableCalidProdc(objecControlCald.getTipoEmpaque()+" "+objecControlCald.getMarcaCaja(),objecControlCald.getTotalCajas(),CALIDAD_TOTAL));




       Table table1 =  createTableWhitDateEvaluacionFrura(objecControlCald.getTipoEmpaque()+" "+objecControlCald.getMarcaCaja(),df.format(PROMEDIO_PESO),
               df.format(CALIDAD_TOTAL)+"%",df.format(PORCENTAJE_DE_DEFECTOS)+"%",String.valueOf(NUMERO_DEFECTS),String.valueOf(MAYOR_DEFECTO_SELECCION),
               String.valueOf(MAYOR_DEFECTO_EMPAQUE),String.valueOf(NUMERO_DE_CLUSTERS_POR_CAJA),String.valueOf(NUMERO_DE_DEDOS),df.format(GRADO_CALIBRE_PROMEDIO)+"%",
               df.format(LARGO_DEDOS_PROMEDIO)+"%",String.valueOf(PH_PROMEDIO)
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
        cellGlobal.add(new Paragraph(promedioPeso).setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
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


        rgbColor= new DeviceRgb(198, 224, 180); //color
        cellGlobal= new Cell(1,1).setBackgroundColor(rgbColor);
        cellGlobal.add(new Paragraph("NUMERO DE CLUSTER").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        table1X.addCell(cellGlobal);


        cellGlobal= new Cell();
        cellGlobal.add(new Paragraph(numClusters).setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        table1X.addCell(cellGlobal);


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




    private Table createTablePorceCalidProductres(){

        DeviceRgb rgbColor= new DeviceRgb(146, 208, 80); //color

        float  [] array={2,1,2,2};
         Table table= new Table(array);
         Cell celdaGlobal= new Cell(2,1).setBackgroundColor(rgbColor);
         celdaGlobal.add(new Paragraph("CODIGO").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
         table.addCell(celdaGlobal);

        celdaGlobal.add(new Paragraph("TIPO DE EMPAQUE").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        table.addCell(celdaGlobal);


        celdaGlobal.add(new Paragraph("TOTAL EMBARCADO").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        table.addCell(celdaGlobal);

        celdaGlobal.add(new Paragraph("POCERCENTAJE QS %").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());
        table.addCell(celdaGlobal);



          /***codigo el numero de rowspan sera del tamaano  TableCalidProdc .size*/
         rgbColor= new DeviceRgb(146, 208, 80); //color
        celdaGlobal= new Cell(TableCalidProdc.size(),1).setBackgroundColor(rgbColor);
        celdaGlobal.add(new Paragraph("CODIGO Aqui").setTextAlignment(TextAlignment.CENTER).setFontSize(7.5f).setBold());

        table.addCell(celdaGlobal);

        DecimalFormat df = new DecimalFormat("#.##");

               int totalEMbracado=0;
               double sumaPorcentajes=0;
        for(TableCalidProdc itemCurrent :TableCalidProdc){

                celdaGlobal= new Cell().setBackgroundColor(rgbColor);
                celdaGlobal.add(new Paragraph(itemCurrent.getTipoEmpaque()).setFontSize(7.5f).setPaddingLeft(5f));
                table.addCell(celdaGlobal);



                celdaGlobal= new Cell().setBackgroundColor(rgbColor);
                celdaGlobal.add(new Paragraph(String.valueOf(itemCurrent.getTotalEmbacado())).setFontSize(7.5f).setTextAlignment(TextAlignment.CENTER));
                table.addCell(celdaGlobal);


                celdaGlobal= new Cell().setBackgroundColor(rgbColor);
                celdaGlobal.add(new Paragraph(df.format(String.valueOf(itemCurrent.getPorcentajeQS()))+"%").setFontSize(7.5f).setTextAlignment(TextAlignment.CENTER));
                table.addCell(celdaGlobal);


            totalEMbracado=totalEMbracado+itemCurrent.getTotalEmbacado();
            sumaPorcentajes=sumaPorcentajes+itemCurrent.getPorcentajeQS();

            }



        double porcentajeFinal=sumaPorcentajes/TableCalidProdc.size();
        //Tabla total
        rgbColor= new DeviceRgb(146, 208, 80); //color

        celdaGlobal= new Cell(1,2).setBackgroundColor(rgbColor);
        celdaGlobal.add(new Paragraph("TOTAL").setTextAlignment(TextAlignment.CENTER).setBold());
        table.addCell(celdaGlobal);



        //TOTAL EMBRACADO
        rgbColor= new DeviceRgb(146, 208, 80); //color
        celdaGlobal= new Cell(1,2).setBackgroundColor(rgbColor);
        celdaGlobal.add(new Paragraph(totalEMbracado+" cajas").setTextAlignment(TextAlignment.CENTER).setBold());
        table.addCell(celdaGlobal);


        //porcentaje
        rgbColor= new DeviceRgb(146, 208, 80); //color
        celdaGlobal= new Cell(1,2).setBackgroundColor(rgbColor);
        celdaGlobal.add(new Paragraph(df.format(porcentajeFinal)+"%").setTextAlignment(TextAlignment.CENTER).setBold());
        table.addCell(celdaGlobal);

        return table;
    }
}