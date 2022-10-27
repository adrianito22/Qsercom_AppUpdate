package com.tiburela.qsercom.PdfMaker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.fonts.Font;
import android.util.Log;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.models.NameAndValue;
import com.tiburela.qsercom.models.ProductPostCosecha;
import com.tiburela.qsercom.models.SetInformDatsHacienda;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;
import com.tiburela.qsercom.utils.HelperImage;
import com.tiburela.qsercom.utils.Variables;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class HelperPdf {

    public static  ArrayList<ArrayList<String>> listOFlist = new ArrayList<>();




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


}