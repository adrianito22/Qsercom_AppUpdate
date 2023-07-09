package com.tiburela.qsercom.PdfMaker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.utils.HelperImage;
import com.tiburela.qsercom.utils.Variables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ExecutionException;

public class HelperAdImgs implements LifecycleOwner {
   public static  ArrayList<ImagenReport> currentListImagesSeccion;
   static Matrix matrix;
   static Thread thread;
   static   Rectangle remaining ;
    static Cell cell;
    static      Bitmap dstBmp;
  static   BitmapCreatorBackG foo;

//   static   Cell    cell;
private static     Image imagVertical;
  static  Image imgHorizontal;

   static Table table;
   
    static   boolean wehaveAddSpaceyIndescrip =false;
    static   float   posicionLastELEMENTAd =10000f;
     static boolean isPosicion0AndNewAnexo=false;
    static float yPosicion=150;
    static float yPosicionSuper=0;
   public static PdfDocument pdfDocumentx;
   static  Bitmap myBitmap;




    public static void  initpdfDocument( PdfDocument pdfDocument) {

       pdfDocumentx =pdfDocument;

    }


    public static void createPages_addImgs(int setFotoCategory ,String anexoNombre, Document midocumentotoAddData,
                                            PageSize pageSize,Context contexto) throws Exception {
        int contador =0;

        Log.i("xamil","la foto selecionada categoria es  "+setFotoCategory);

        currentListImagesSeccion= HelperImage.getImagesWhitthisCATEGORYz(ImagenReport.hashMapImagesData,setFotoCategory);///era Variables.FOTO_LLEGADA

        Collections.sort(currentListImagesSeccion, new Comparator<ImagenReport>()
        {
            @Override
            public int compare(ImagenReport lhs, ImagenReport rhs) {
                return lhs.getSortPositionImage() - rhs.getSortPositionImage();

                //  return Integer.compare(lhs.getSortPositionImage(), rhs.getSortPositionImage());
            }
        });




        Log.i("xamil","el SIZE DE  current list images seccion es "+currentListImagesSeccion.size()+" y la categoria es "+setFotoCategory);

        //nos asegurammos que ninguna este en el pdf aun ,LAS DESMARCAMOS
        currentListImagesSeccion= HelperImage.marcaQueNoEstaEnPDF(currentListImagesSeccion);
       // Log.i("contaburx","la IMAGEN DE LA 1  POSCION esta en el pdf? "+currentListImagesSeccion.get(0).estaENPdf);
       // Log.i("contaburx","la IMAGEN DE LA 2 POSCION esta en el pdf? "+currentListImagesSeccion.get(1).estaENPdf);

        Log.i("xamil","el size de imagenes de estacategoria es  "+currentListImagesSeccion.size());


        while(!allImagesISUsed(currentListImagesSeccion)){ //mientras quedan imagenes si usar////

            if(contador==0  && setFotoCategory==Variables.FOTO_PROCESO_FRUTA_FINCA ) { ///significa que es la primera pagina
              ///AGREGMAOS TEXTO
                midocumentotoAddData.add(new Paragraph("ANEXOS").setFontSize(10f).setPaddingLeft(50f).setBold().setMarginTop(2f));
                midocumentotoAddData.add(new Paragraph("*  "+anexoNombre).setFontSize(10f).
                        setPaddingLeft(60f).setMarginTop(1f).setBold());
                isPosicion0AndNewAnexo=true;

            }


            else if (contador==0  && setFotoCategory==Variables.FOTO_LLEGADA_CONTENEDOR ) { //FOTO_LLEGADA_CONTENEDOR //ese
                ///AGREGMAOS TEXTO
                midocumentotoAddData.add(new Paragraph(anexoNombre).setFontSize(10f).setBold().setMarginTop(2f).setTextAlignment(TextAlignment.CENTER));

                Log.i("sajsdfgfsd","es foto contenedor ");

                isPosicion0AndNewAnexo=true;

            }




            else if (contador==0  && setFotoCategory==Variables.FOTO_DOCUMENTACION ) { ///significa que es la primera pagina
                ///AGREGMAOS TEXTO
                midocumentotoAddData.add(new Paragraph(anexoNombre).setFontSize(10f).setBold().setMarginTop(2f).setTextAlignment(TextAlignment.CENTER));
                Log.i("sajsdfgfsd","es foto contenedor ");

                isPosicion0AndNewAnexo=true;

            }



            Log.i("homero","el posicion las element en comprobacion es  "+posicionLastELEMENTAd);


            contador++;



           // buscar ek patron quiero uno de proceso de fruta en finca

            Log.i("contaburx","se ejecuto ESto "+contador+" veces");
            int patronEncontrado=HelperImage.buscaPosiblePatronParaOrdenar(currentListImagesSeccion);



            if(patronEncontrado== Variables.TRES_IMGS_VERTCLES){
                wehaveAddSpaceyIndescrip =false;
                Log.i("PATRONX","es Variables.TRES_IMGS_VERTCLES");

                addImagenSetAndCreateNewPage(Variables.TRES_IMGS_VERTCLES,midocumentotoAddData,pageSize,contexto);
                //ENTONCES ESTOS ENCONTRADOS LOS PONEMOS QUE YA SE USARON.....
            }

            else if(patronEncontrado == Variables.DOS_IMGS_VERTICALES) {
                Log.i("PATRONX","es el DOS_IMGS_VERTICALES ");
                wehaveAddSpaceyIndescrip =false;

                addImagenSetAndCreateNewPage(Variables.DOS_IMGS_VERTICALES,midocumentotoAddData,pageSize,contexto);
            }

            else if(patronEncontrado == Variables.DOS_HORIZONTALES) {
                Log.i("PATRONX","es el DOS_HORIZONTALES ");
                wehaveAddSpaceyIndescrip =false;

                addImagenSetAndCreateNewPage(Variables.DOS_HORIZONTALES,midocumentotoAddData,pageSize,contexto);
            }


            else if(patronEncontrado == Variables.UNAVERTICAL_Y_OTRA_HORIZONTAL) {
                Log.i("PATRONX","es  UNAVERTICAL_Y_OTRA_HORIZONTAL");
                wehaveAddSpaceyIndescrip =false;

                addImagenSetAndCreateNewPage(Variables.UNAVERTICAL_Y_OTRA_HORIZONTAL,midocumentotoAddData,pageSize,contexto);
            }


            else if(patronEncontrado == Variables.UNAHORIZONTAL_Y_OTRA_VERTICAL) {
                Log.i("PATRONX","es  UNAHORIZONTAL_Y_OTRA_VERTICAL");
                wehaveAddSpaceyIndescrip =false;

                addImagenSetAndCreateNewPage(Variables.UNAHORIZONTAL_Y_OTRA_VERTICAL,midocumentotoAddData,pageSize,contexto);
            }


            else if(patronEncontrado == Variables.UNA_HORIZONTAL) {
                Log.i("PATRONX","es el UNA_HORIZONTAL ");
                wehaveAddSpaceyIndescrip =false;

                addImagenSetAndCreateNewPage(Variables.UNA_HORIZONTAL,midocumentotoAddData,pageSize,contexto);
            }


            else if(patronEncontrado == Variables.UNA_VERTICAL) {
                Log.i("PATRONX","es el UNA_HORIZONTAL ");
                wehaveAddSpaceyIndescrip =false;

                addImagenSetAndCreateNewPage(Variables.UNA_VERTICAL,midocumentotoAddData,pageSize,contexto);
            }


            else if(patronEncontrado == Variables.DEFAULNO_ENCONTRO_NADA) {
                Log.i("PATRONX","es el  DEFAULNO_ENCONTRO_NADA");
                wehaveAddSpaceyIndescrip =false;

                addImagenSetAndCreateNewPage(Variables.DEFAULNO_ENCONTRO_NADA,midocumentotoAddData,pageSize,contexto);
            }



        }

    }
    private static boolean allImagesISUsed(ArrayList<ImagenReport> list){
        int contadorIMagesUsadas=0;


        for(int indice=0; indice<list.size(); indice++){

            if(list.get(indice).isEstaENPdf()){

                contadorIMagesUsadas++;


            }

            //primero si podemos poner las 3 primeras imagenes....
            //comprobamos que existan al menos 3 imagenes

        }

        Log.i("contabur","hay "+contadorIMagesUsadas +" imagenes usadas");

        /*
        if(contadorIMagesUsadas== list.size() || contadorIMagesUsadas== list.size()-1 ){ //o si solo queda 1

        return true;
        }

        else{return false;

        }
*/








        if(contadorIMagesUsadas== list.size() || contadorIMagesUsadas== list.size()-1 && posicionLastELEMENTAd<150 ){ //o si solo queda 1

            Variables.ES_ULTIMA_IMAGEN_Y_PRIMERA_PAGINA=true;

        }




        if(contadorIMagesUsadas ==list.size()){

            return true;

        }else{

            return false;

        }




    }




    private static void addImagenSetAndCreateNewPage(int tipoOrdenImgs, Document docuemnto, PageSize pageSize, Context contexta) throws Exception {

        float heigthImg=244; ///232 antes               ///237 +22....
        float widthImg=(pageSize.getWidth()-70f)/3;

        //Rectangle remaining ;
        // Cell cell;

        Log.i("posicuon","el posicon del ultimo elemento es "+ yPosicion);

        if(isPosicion0AndNewAnexo){
            heigthImg=heigthImg+10;
            isPosicion0AndNewAnexo=false;
        }

        remaining=  docuemnto.getRenderer().getCurrentArea().getBBox();
        yPosicionSuper= remaining.getTop();


       // Cell cell;

        float espcioCuparaThisItem;


        if(tipoOrdenImgs==Variables.TRES_IMGS_VERTCLES){


            Log.i("salertty","hay TRES_IMGS_VERTCLES");

            float [] tableWidth  = {1,1,1} ;

            Table table = new Table(tableWidth,true);

             imagVertical=HelperPdf.createInfoImgtoPDF( retornaBitmaPhere(contexta,HelperImage.imagesSetToCurrentFila.get(0).getUrlStoragePic(),HelperImage.imagesSetToCurrentFila.get(0)));


            imagVertical.setAutoScale(true);
            imagVertical.scaleAbsolute(widthImg,heigthImg);


            if(HelperImage.imagesSetToCurrentFila.get(0).getDescripcionImagen().length()>1){

                //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imagVertical,HelperImage.imagesSetToCurrentFila.get(0).getDescripcionImagen());

            }


            else {

                cell = new Cell().setBorder(Border.NO_BORDER).add(imagVertical); //estaba asi
            }


            table.addCell(cell);



            imagVertical=HelperPdf.createInfoImgtoPDF( retornaBitmaPhere(contexta,HelperImage.imagesSetToCurrentFila.get(1).getUrlStoragePic(),HelperImage.imagesSetToCurrentFila.get(1)));
            imagVertical.setAutoScale(true);
            imagVertical.scaleAbsolute(widthImg,heigthImg);



            if(HelperImage.imagesSetToCurrentFila.get(1).getDescripcionImagen().length()>1){  //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imagVertical,HelperImage.imagesSetToCurrentFila.get(1).getDescripcionImagen());
            }
            else {

                cell = new Cell().setBorder(Border.NO_BORDER).add(imagVertical); //estaba asi
            }



            table.addCell(cell);


             imagVertical=HelperPdf.createInfoImgtoPDF( retornaBitmaPhere(contexta,HelperImage.imagesSetToCurrentFila.get(2).getUrlStoragePic(),HelperImage.imagesSetToCurrentFila.get(2)));
            imagVertical.setAutoScale(true);
            imagVertical.scaleAbsolute(widthImg,heigthImg);



            if(HelperImage.imagesSetToCurrentFila.get(2).getDescripcionImagen().length()>1){  //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imagVertical,HelperImage.imagesSetToCurrentFila.get(2).getDescripcionImagen());
            }
            else {

                cell = new Cell().setBorder(Border.NO_BORDER).add(imagVertical); //estaba asi
            }




            table.addCell(cell);
            table.setHeight(heigthImg);  //Primera tabla ///este ancho...

            configtable(table,pageSize);

           // espcioCuparaThisItem= yPosicion +8+ heigthImg ; //8 es margin
          //  Log.i("posicuon","el TRES_IMGS_VERTCLES es "+espcioCuparaThisItem);


              ///vamos a comprobar...


            if(heigthImg+8 >yPosicionSuper) {  // +8 de margin

                docuemnto.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            }



            docuemnto.add(table);



            //posicionLastELEMENTAd = remaining.getTop();
            Log.i("homero","eldocuemnto.getRenderer().getCurrentArea().getBBox() ES "+posicionLastELEMENTAd);
            markImgComoUsada(HelperImage.imagesSetToCurrentFila); //podem



        }

        else if(tipoOrdenImgs==Variables.DOS_IMGS_VERTICALES){ //2 imagenes verticales en una linea
            Log.i("salertty","hay DOS_IMGS_VERTICALES");


            float [] tableWidth  = {1,1} ;
            Table table = new Table(tableWidth,true);
            //  table.setHeight(280);  //Primera tabla ///este ancho...

             imagVertical=HelperPdf.createInfoImgtoPDF( retornaBitmaPhere(contexta,HelperImage.imagesSetToCurrentFila.get(0).getUrlStoragePic(),HelperImage.imagesSetToCurrentFila.get(0)));
            imagVertical.setAutoScale(true);
            imagVertical.scaleAbsolute(widthImg,heigthImg);
            imagVertical.setHorizontalAlignment(HorizontalAlignment.RIGHT);
            imagVertical.setMarginRight(10f);
            //imagVertical.scaleAbsolute(widthImg,heigthImg);



            if(HelperImage.imagesSetToCurrentFila.get(0).getDescripcionImagen().length()>1){  //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imagVertical,HelperImage.imagesSetToCurrentFila.get(0).getDescripcionImagen());
            }



            else {

                cell = new Cell().setBorder(Border.NO_BORDER).add(imagVertical); //estaba asi
            }

            table.addCell(cell);


            imagVertical=HelperPdf.createInfoImgtoPDF( retornaBitmaPhere(contexta,HelperImage.imagesSetToCurrentFila.get(1).getUrlStoragePic(),HelperImage.imagesSetToCurrentFila.get(1)));
            imagVertical.setAutoScale(true);
            imagVertical.scaleAbsolute(widthImg,heigthImg);
            imagVertical.setMarginLeft(10f);
            // imagVertical.scaleAbsolute(widthImg,heigthImg);


            if(HelperImage.imagesSetToCurrentFila.get(1).getDescripcionImagen().length()>1){  //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imagVertical,HelperImage.imagesSetToCurrentFila.get(1).getDescripcionImagen());
            }
            else {

                cell = new Cell().setBorder(Border.NO_BORDER).add(imagVertical); //estaba asi
            }


            table.addCell(cell);

            table.setHeight(heigthImg);  //Primera tabla ///este ancho...
            table.setWidth((widthImg*2)+50f);
            table.setHorizontalAlignment(HorizontalAlignment.CENTER);
            table.setMarginTop(8f);



            if(heigthImg+8 >yPosicionSuper) {  // +8 de margin

                docuemnto.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            }

            docuemnto.add(table);



            Variables.currentPoscion = docuemnto.getRenderer().getCurrentArea().getBBox().getBottom();
            // Log.i("samledtaa","el mi logoqsercom "+ Variables.currentPoscion);

            // docuemnto.add(table);
            remaining = docuemnto.getRenderer().getCurrentArea().getBBox();
            posicionLastELEMENTAd = remaining.getTop();
            Log.i("homero","eldocuemnto.getRenderer().getCurrentArea().getBBox() ES "+posicionLastELEMENTAd);



            Log.i("PATRONX","el size de image HelperImage.imagesSetToCurrentFila es : "+HelperImage.imagesSetToCurrentFila.size());

            markImgComoUsada(HelperImage.imagesSetToCurrentFila); //podemos pasarle una lista de este 3 imagenes o marca una por una como hicimos arriba

        }


        else if(tipoOrdenImgs==Variables.UNAVERTICAL_Y_OTRA_HORIZONTAL){ //1 vertical yPosicion otro horizontal en la misma linea

            Log.i("salertty","hay UNAVERTICAL_Y_OTRA_HORIZONTAL");


            float [] tableWidth  = {190f , 390 } ; //estaba en 250.. le ponemos
           //  float [] tableWidth  = {155f,200} ; //estaba en 250.. le ponemos

            Table table = new Table(tableWidth,true);

             imagVertical=HelperPdf.createInfoImgtoPDF( retornaBitmaPhere(contexta,HelperImage.imagesSetToCurrentFila.get(0).getUrlStoragePic(),HelperImage.imagesSetToCurrentFila.get(0)));
            imagVertical.setAutoScale(true);


           // imagVertical.scaleAbsolute(190,heigthImg);

           // imagVertical.scaleAbsolute(190,heigthImg);
            imagVertical.scaleAbsolute(190,heigthImg);


            //imagVertical.setHorizontalAlignment(HorizontalAlignment.LEFT);
          //  imagVertical.setMarginRight(10f); //borramos

            imagVertical.setHorizontalAlignment(HorizontalAlignment.LEFT);
           // imagVertical.setMarginLeft(10f);
            //imagVertical.setMarginRight(10f);

            if(HelperImage.imagesSetToCurrentFila.get(0).getDescripcionImagen().length()>1){  //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imagVertical,HelperImage.imagesSetToCurrentFila.get(0).getDescripcionImagen());
            }
            else {
                cell = new Cell().setBorder(Border.NO_BORDER).add(imagVertical); //estaba asi
            }

            table.addCell(cell);




                imgHorizontal=HelperPdf.createInfoImgtoPDF(retornaBitmaPhere(contexta,HelperImage.imagesSetToCurrentFila.get(1).getUrlStoragePic(),HelperImage.imagesSetToCurrentFila.get(1)));
              imgHorizontal.setAutoScale(true);
            //imagVertical.setHorizontalAlignment(HorizontalAlignment.RIGHT);


            imgHorizontal.scaleAbsolute(363,heigthImg); //360 estaba



          //  imgHorizontal.scaleToFit((pageSize.getWidth()-70f)/1.8f,heigthImg); //estaba en 1.9
            imgHorizontal.setHorizontalAlignment(HorizontalAlignment.LEFT);
            imgHorizontal.setMarginLeft(5f);
          //  imgHorizontal.setMarginRight(30f);




            if(HelperImage.imagesSetToCurrentFila.get(1).getDescripcionImagen().length()>1){

                //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imgHorizontal,HelperImage.imagesSetToCurrentFila.get(1).getDescripcionImagen());

            }

            else {

                cell = new Cell().setBorder(Border.NO_BORDER).add(imgHorizontal); //estaba asi

            }


            table.addCell(cell);
            //table.setWidth((imagVertical.getImageWidth())+(imgHorizontal.getImageWidth()+60));
            table.setHorizontalAlignment(HorizontalAlignment.CENTER);
            table.setMarginTop(8f);
            table.setMarginLeft(15f);

            table.setHeight(heigthImg);

           // configtable(table,pageSize);

            if(heigthImg+8 >yPosicionSuper) {  // +8 de margin

                docuemnto.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

            }



            Log.i("homero","eldocuemnto.getRenderer().getCurrentArea().getBBox() ES "+posicionLastELEMENTAd);

            docuemnto.add(table);

            markImgComoUsada(HelperImage.imagesSetToCurrentFila); //podemos pasarle una lista de este 3 imagenes o marca una por una como hicimos arriba



        }

        else if(tipoOrdenImgs==Variables.UNAHORIZONTAL_Y_OTRA_VERTICAL){ //1 vertical yPosicion otro horizontal en la misma linea

            //  Log.i("salertty","hay UNAHORIZONTAL_Y_OTRA_VERTICAL");
            //  float [] tableWidth  = {190,300} ; //estaba en 250.. le ponemos
            float widthImgxxx=(pageSize.getWidth()-70f);
            Log.i("cuandoexecuta","el ancho es"+widthImgxxx);


            float [] tableWidth  = {360f,230f} ;// 1.5 estaba 200  ///probamos con 220 despues
            Table table = new Table(tableWidth,true);


             imgHorizontal=HelperPdf.createInfoImgtoPDF(retornaBitmaPhere(contexta,HelperImage.imagesSetToCurrentFila.get(0).getUrlStoragePic(),HelperImage.imagesSetToCurrentFila.get(0)));
            imgHorizontal.setAutoScale(true);

             Log.i("cuandoexecuta","se ejecduto esta");
          // imgHorizontal.scaleAbsolute((pageSize.getWidth()-70f)/2,heigthImg);
            imgHorizontal.scaleAbsolute(360,heigthImg); //estaba en 350

            imgHorizontal.setHorizontalAlignment(HorizontalAlignment.LEFT);
            //imgHorizontal.setMarginRight(20);

           //imgHorizontal.setMarginL(20);


            // imgHorizontal.scaleToFit(150,heigthImg);
            //imagVertical.setHorizontalAlignment(HorizontalAlignment.RIGHT);


            if(HelperImage.imagesSetToCurrentFila.get(1).getDescripcionImagen().length()>1){  //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imgHorizontal,HelperImage.imagesSetToCurrentFila.get(0).getDescripcionImagen());
            }
            else {
                cell = new Cell().setBorder(Border.NO_BORDER).add(imgHorizontal); //estaba asi
            }

            table.addCell(cell);


             imagVertical=HelperPdf.createInfoImgtoPDF( retornaBitmaPhere(contexta,HelperImage.imagesSetToCurrentFila.get(1).getUrlStoragePic(),HelperImage.imagesSetToCurrentFila.get(1)));
            imagVertical.setAutoScale(true);

           // imagVertical.scaleAbsolute(widthImg,heigthImg);
            imagVertical.scaleAbsolute(190,heigthImg);


          //  imagVertical.sca
            imagVertical.setHorizontalAlignment(HorizontalAlignment.LEFT);
            //imagVertical.setMarginRight(10f);
            imagVertical.setMarginLeft(5f); //estaba en 10


            if(HelperImage.imagesSetToCurrentFila.get(1).getDescripcionImagen().length()>1){  //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imagVertical,HelperImage.imagesSetToCurrentFila.get(1).getDescripcionImagen());
            }
            else {
                cell = new Cell().setBorder(Border.NO_BORDER).add(imagVertical); //estaba asi
            }

            table.addCell(cell);


            //table.setWidth(300); //*//SI NO COBFURAR SIZE TABLA //


            table.setHorizontalAlignment(HorizontalAlignment.CENTER);
            table.setMarginTop(8f);
            table.setMarginLeft(15f);

           // table.setMarginRight(25f);
            table.setHeight(heigthImg);

            //  configtable(table,pageSize);
            //  espcioCuparaThisItem= yPosicion +8+ heigthImg ;
            //margin 10


            if(heigthImg+8 >yPosicionSuper) {  // +8 de margin

                docuemnto.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            }

            //    Rectangle remaining = docuemnto.getRenderer().getCurrentArea().getBBox();
            //  posicionLastELEMENTAd = remaining.getTop();
            Log.i("homero","eldocuemnto.getRenderer().getCurrentArea().getBBox() ES "+posicionLastELEMENTAd);

            docuemnto.add(table);

            markImgComoUsada(HelperImage.imagesSetToCurrentFila); //podemos pasarle una lista de este 3 imagenes o marca una por una como hicimos arriba


        }


        else if(tipoOrdenImgs==Variables.DOS_HORIZONTALES) { //2 imagenes verticales en una linea

            Log.i("salertty", "hay DOS_HORIZONTALES erl nacho es "+pageSize.getWidth());

            float[] tableWidth = {1, 1};
            Table table = new Table(tableWidth, true);

             imgHorizontal = HelperPdf.createInfoImgtoPDF(retornaBitmaPhere(contexta, HelperImage.imagesSetToCurrentFila.get(0).getUrlStoragePic(), HelperImage.imagesSetToCurrentFila.get(0)));
            imgHorizontal.setAutoScale(true);

            imgHorizontal.scaleAbsolute(((pageSize.getWidth()-70)/2   ),heigthImg-60);


            imgHorizontal.setHorizontalAlignment(HorizontalAlignment.LEFT);
            imgHorizontal.setMarginRight(3f);


            if(HelperImage.imagesSetToCurrentFila.get(0).getDescripcionImagen().length()>1){  //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imgHorizontal,HelperImage.imagesSetToCurrentFila.get(0).getDescripcionImagen());
            }
            else {

                cell = new Cell().setBorder(Border.NO_BORDER).add(imgHorizontal); //estaba asi
            }

            table.addCell(cell);


            imgHorizontal=HelperPdf.createInfoImgtoPDF(retornaBitmaPhere(contexta,HelperImage.imagesSetToCurrentFila.get(1).getUrlStoragePic(),HelperImage.imagesSetToCurrentFila.get(1)));
            imgHorizontal.setAutoScale(true);

            imgHorizontal.scaleAbsolute(((pageSize.getWidth()-70)/2   ),heigthImg-60);


            imgHorizontal.setHorizontalAlignment(HorizontalAlignment.LEFT);
            imgHorizontal.setMarginLeft(3f);


            if(HelperImage.imagesSetToCurrentFila.get(1).getDescripcionImagen().length()>1){  //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imgHorizontal,HelperImage.imagesSetToCurrentFila.get(1).getDescripcionImagen());
            }
            else {

                cell = new Cell().setBorder(Border.NO_BORDER).add(imgHorizontal); //estaba asi
            }


            table.addCell(cell);




            table.setHeight(heigthImg);  //Primera tabla ///este ancho...
            table.setWidth(pageSize.getWidth()-14);
            table.setHorizontalAlignment(HorizontalAlignment.CENTER);

            //table.setMarginLeft(25f);
            //table.setMarginRight(25f);

            //configtable(table,pageSize);


            if(heigthImg+8 >yPosicionSuper) {  // +8 de margin

                docuemnto.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            }

            docuemnto.add(table);

            Variables.currentPoscion = docuemnto.getRenderer().getCurrentArea().getBBox().getBottom();
            // Log.i("samledtaa","el mi logoqsercom "+ Variables.currentPoscion);
          //  posicionLastELEMENTAd = remaining.getTop();
            Log.i("homero","eldocuemnto.getRenderer().getCurrentArea().getBBox() ES "+posicionLastELEMENTAd);

            markImgComoUsada(HelperImage.imagesSetToCurrentFila); //podemos pasarle una lista de este 3 imagenes o marca una por una como hicimos arriba



        }



        else if(tipoOrdenImgs==Variables.UNA_HORIZONTAL){

            Log.i("salertty","hay UNA_HORIZONTAL");



            if(Variables.ES_ULTIMA_IMAGEN_Y_PRIMERA_PAGINA) {
                heigthImg= heigthImg+50;

                Variables.ES_ULTIMA_IMAGEN_Y_PRIMERA_PAGINA =false;
            }




             table = new Table(1,true);

             imgHorizontal=HelperPdf.createInfoImgtoPDF( retornaBitmaPhere(contexta,HelperImage.imagesSetToCurrentFila.get(0).getUrlStoragePic(),HelperImage.imagesSetToCurrentFila.get(0)));
            imgHorizontal.setAutoScale(true);
            //imgHorizontal.scaleToFit()

            imgHorizontal.setHorizontalAlignment(HorizontalAlignment.CENTER);


            if(HelperImage.imagesSetToCurrentFila.get(0).getDescripcionImagen().length()>1){  //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imgHorizontal,HelperImage.imagesSetToCurrentFila.get(0).getDescripcionImagen());
            }
            else {

                cell = new Cell().setBorder(Border.NO_BORDER).add(imgHorizontal); //estaba asi
            }

            table.addCell(cell);



            table.setHeight(heigthImg);  //Primera tabla ///este ancho...
            table.setWidth(pageSize.getWidth()-80);

            table.setHorizontalAlignment(HorizontalAlignment.CENTER);


            //configtable(table,pageSize);
            espcioCuparaThisItem= yPosicion +8+ heigthImg ; //8 es margin
            Log.i("posicuon","el espcioCuparaThisItem en UNA_HORIZONTAL es "+espcioCuparaThisItem);




            if(heigthImg+8 >yPosicionSuper) {  // +8 de margin
                docuemnto.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            }

            docuemnto.add(table);





            Variables.currentPoscion = docuemnto.getRenderer().getCurrentArea().getBBox().getBottom();
            // Log.i("samledtaa","el mi logoqsercom "+ Variables.currentPoscion);
           // posicionLastELEMENTAd = remaining.getTop();

            markImgComoUsada(HelperImage.imagesSetToCurrentFila); //podemos pasarle una lista de este 3 imagenes o marca una por una como hicimos arriba




        }


        else if(tipoOrdenImgs==Variables.UNA_VERTICAL){


            Log.i("salertty","hay UNA_VERTICAL");


            if(Variables.ES_ULTIMA_IMAGEN_Y_PRIMERA_PAGINA) {
                heigthImg= heigthImg+50;

                Variables.ES_ULTIMA_IMAGEN_Y_PRIMERA_PAGINA =false;
            }



             table = new Table(1,true);



            Log.i("urlptint","la url es "+HelperImage.imagesSetToCurrentFila.get(0).getUrlStoragePic());
            Log.i("urlptint","la url es "+HelperImage.imagesSetToCurrentFila.get(0).getUniqueIdNamePic());



             imagVertical=HelperPdf.createInfoImgtoPDF( retornaBitmaPhere(contexta,HelperImage.imagesSetToCurrentFila.get(0).getUrlStoragePic(),HelperImage.imagesSetToCurrentFila.get(0)));
            imagVertical.setAutoScale(true);
            imagVertical.setHorizontalAlignment(HorizontalAlignment.CENTER);


            if(HelperImage.imagesSetToCurrentFila.get(0).getDescripcionImagen().length()>1){  //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imagVertical,HelperImage.imagesSetToCurrentFila.get(0).getDescripcionImagen());
                cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
            }
            else {

                cell = new Cell().setBorder(Border.NO_BORDER).add(imagVertical); //estaba asi
                cell.setHorizontalAlignment(HorizontalAlignment.CENTER);

            }


            table.addCell(cell);

            table.setHeight(heigthImg);  //Primera tabla ///este ancho...
           // table.setWidth(pageSize.getWidth()-100);
            table.setHorizontalAlignment(HorizontalAlignment.CENTER);

            //table.setMarginLeft(40f);
            //table.setMarginRight(40f);

            //configtable(table,pageSize);
            espcioCuparaThisItem= yPosicion +8+ heigthImg ; //8 es margin

            Log.i("posicuon","el espcioCuparaThisItem en UNA_VERTICAL es "+espcioCuparaThisItem);



            if(heigthImg+8 >yPosicionSuper) {  // +8 de margin
                docuemnto.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            }

            docuemnto.add(table);



            Variables.currentPoscion = docuemnto.getRenderer().getCurrentArea().getBBox().getBottom();
            // Log.i("samledtaa","el mi logoqsercom "+ Variables.currentPoscion);

             remaining = docuemnto.getRenderer().getCurrentArea().getBBox();
            posicionLastELEMENTAd = remaining.getTop();

            markImgComoUsada(HelperImage.imagesSetToCurrentFila); //podemos pasarle una lista de este 3 imagenes o marca una por una como hicimos arriba




        }

        else if(tipoOrdenImgs==Variables.DEFAULNO_ENCONTRO_NADA){ //2 imagenes verticales en una linea
            ///en caso que no ecneuntre nigun patron
            Log.i("salertty","hay DEFAULNO_ENCONTRO_NADA");

        }



        //yPosicion oior ultimo si hay una imagen sola....tamnto vertical o horizontal ..no la aghregues ,,es una opcion

    }













    public static  void configtable(Table table1,PageSize pageSize){

        table1.setWidth(pageSize.getWidth()-20f);
        table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table1.setMarginTop(10f);  //estaba 8 enn....

        table1.setMarginLeft(15f);
        table1.setMarginRight(10f);

    }

    public static void addTexDescripTOiMGSiHay(String textoDescripcion,Document document){
        if(textoDescripcion.length()>1){

            //agegamos la descripcion de la primer imagen....
             //agregamos

            document.add(new Paragraph(textoDescripcion));

            Rectangle remaining = document.getRenderer().getCurrentArea().getBBox();
            posicionLastELEMENTAd = remaining.getTop();
            Log.i("homero","eldocuemnto.getRenderer().getCurrentArea().getBBox() ES "+posicionLastELEMENTAd);
          //  Paragraph p = new Paragraph("Hello World");
            //doc.add(p);


           // currentCanvasObjec.drawText(textoDescripcion, lef, poscYUltImgColoc, paintDescripcionImg);

            if(!wehaveAddSpaceyIndescrip){

             ///   poscYUltImgColoc=poscYUltImgColoc+10; //actualizamos la posicion de la yPosicion a mas 10 por ahora
                wehaveAddSpaceyIndescrip =true;


            }

        }



        ///este codigo marcael area restante despues de agregar un elemento al pdf
       // PdfCanvas canvas = new PdfCanvas(miPFDocumentkernel.getPage(5));
     //   canvas.setStrokeColor(ColorConstants.RED).rectangle(remaining).stroke();
     //   canvas.addImage(1,243,3,5,4,4,4);







    }


    /*
    private Image  addWathermark(PdfDocument pdfDoc, Image img, String watermark) {
            float width = img.getImageScaledWidth();
            float height = img.getImageScaledHeight();
            PdfFormXObject template = new PdfFormXObject(new Rectangle(width, height));
            new Canvas(template, pdfDoc).
                    add(img).
                    setFontColor(DeviceGray.WHITE).
                    showTextAligned(watermark, width / 2, height / 2, TextAlignment.CENTER, (float) Math.PI * 30f / 180f);
            return new Image(template);
        }


*/


    private static void markImgComoUsada(ArrayList<ImagenReport> list){

        //buscamos esos ids,, yPosicion marcamos como usado...
        //vamos atenber dos listas.... una lista que es la litsa del conjunto actual yPosicion una lista que dice


        for(int i= 0; i<list.size(); i++){ ///
            String uniqueId=list.get(i).getUniqueIdNamePic();

            for(int indice=0; indice<currentListImagesSeccion.size(); indice++){

                if(uniqueId.equals(currentListImagesSeccion.get(indice).getUniqueIdNamePic())) {
                    currentListImagesSeccion.get(indice).setEstaENPdf(true);

                  //  Log.i("contabur","el size de marcads como usadas es   "+currentListImagesSeccion.size());


                }



            }

        }




    }


    public static Image getWatermarkedImage(PdfDocument pdfDoc, Image img, String watermark) {
        float width = img.getImageScaledWidth();
        float height = img.getImageScaledHeight();
        Log.i("heifkg","el heigth de imagen es "+height);

        PdfFormXObject template = new PdfFormXObject(new Rectangle(width, height));
        new Canvas(template, pdfDoc).
                add(img).
                setFontColor(DeviceGray.WHITE).
               // add(new Paragraph("")).
              //  setBackgroundColor(DeviceGray.BLACK).
                setBackgroundColor(DeviceGray.BLACK,4,4,4,4).

       // showTextAligned(new Paragraph(watermark).setBackgroundColor(new DeviceRgb(0,0,0)), width / 2, height /18, TextAlignment.CENTER); //estaba en 9
        showTextAligned(new Paragraph(watermark).setBackgroundColor(new DeviceRgb(0,0,0)), width / 2, height-238 //estab en 235
               , TextAlignment.CENTER ); //estaba en 9


        //  showTextAligned(new Paragraph(watermark).setBackgroundColor(new DeviceRgb(0,0,0)), width / 2, height-10, TextAlignment.CENTER); //estaba en 9

               //  showTextAligned(watermark, width / 2, height / 8, TextAlignment.CENTER); //estaba en 7

        // showTextAligned(watermark, width / 2, height / 2, TextAlignment.CENTER, (float) Math.PI * 30f / 180f);


        return new Image(template);


    }




    static Cell addImgAndTextDescriptionInCell(PdfDocument miPFDocumentkernel, Image image, String textoDescripcion ) throws Exception {
         cell= new Cell();
    cell.setBorder(Border.NO_BORDER);
       // Table table = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth().setWidth(UnitValue.createPercentValue(80));
        cell.add(getWatermarkedImage(miPFDocumentkernel, image,textoDescripcion).setAutoScale(true).setWidth(UnitValue.createPercentValue(100)));
       // doc.add(table);
        ///doc.showTextAligned("Bruno knows best", 260, 400, TextAlignment.CENTER, 45f * (float) Math.PI / 180f);
      //  doc.close();


        return cell;
    }

/*
   private static Bitmap generaBitmap(Context contexto,String urlImage)  {



       MutableLiveData<Bitmap> liveData;

       //then initialize this variable in oncreate or where you want just like this
       liveData= new MutableLiveData<>();

       //then set value to LiveData just like this
       Observer  observer2;
       liveData.observe(this,observer2):

//then Create observer for livedata. whenever data change in Livedata  onChanged method will call
       Observer  observer= new Observer() {
           @Override
           public void update(Observable observable, Object o) {



               Glide.with(contexto)
                       .asBitmap()
                       .load(urlImage)    //.onlyRetrieveFromCache(false)
                       .into(new CustomTarget<Bitmap>() {

                           //  Observer<Bitmap> observer= new Observer<Bitmap>() {

                           @Override
                           public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                               //


                               //then set value to LiveData just like this
                               // liveData.setValue(resource);
                               bitmapDevolver=resource;
                               //AQUI CREAMOS LA IMAGEN...

                               liveData.setValue(bitmapDevolver);



                               Log.i("haisdssdsd","es reay listo..");

                           }

                           @Override
                           public void onLoadCleared(@Nullable Drawable placeholder) {
                           }
                       });

           }
       };








       while (bitmapDevolver ==null) {

           Log.i("haisdssdsd","aun es nulo");

       }


       Log.i("haisdssdsd","ya no es nulo hurra ");




   return  bitmapDevolver;
   }

*/







public static  Bitmap retornaBitmaPhere(Context contexto, String urlImage,ImagenReport image) throws InterruptedException {

    Log.i("ladtastor","llamos reotnabima");

     foo = new BitmapCreatorBackG();

    foo.iniValuesParams(contexto,urlImage,image);

     thread = new Thread(foo);
    thread.start();
    thread.join();
    myBitmap = foo.getValueBitmap();

    //  Bitmap dstBmp ;

      //asi giramos nuestra imagen bitmap

    if(image.getGiroGradosImagen()!=360 && image.getGiroGradosImagen()!=0){
        myBitmap  = RotateBitmap(myBitmap,image.getGiroGradosImagen());

    }


    Log.i("comenzar","el heigth es "+myBitmap.getHeight()+" y el ancho es "+myBitmap.getWidth());


     if(image.getHorientacionImage().equals("vertical")  && myBitmap.getWidth() > myBitmap.getHeight()  ) { //si la imagen esta girada ,,USAMOS

          matrix = new Matrix();

         matrix.postRotate(90);

         myBitmap = Bitmap.createBitmap(myBitmap, 0, 0, myBitmap.getWidth(), myBitmap.getHeight(), matrix, true);

     }


   else if(image.getHorientacionImage().equals("horizontal")  && myBitmap.getHeight() > myBitmap.getWidth()  ) { //si la imagen esta girada ,,USAMOS

         matrix = new Matrix();
        matrix.postRotate(90);  ///git,oas 90 grados a la derecha

        myBitmap = Bitmap.createBitmap(myBitmap, 0, 0, myBitmap.getWidth(), myBitmap.getHeight(), matrix, true);

    }


    if (myBitmap.getHeight() > myBitmap.getWidth() + myBitmap.getWidth()   ){

            Log.i("comenzar","se ejecuto esto here");

             int tercio=myBitmap.getHeight()/3;

              int y=myBitmap.getHeight()/2  - myBitmap.getWidth()/2;
              int heigth=myBitmap.getHeight()/2;


        Log.i("comenzar","el tercio es  "+ tercio) ;
        Log.i("comenzar","el y es  "+ y) ;
        Log.i("comenzar","el heigth es"+heigth);


        dstBmp = Bitmap.createBitmap(
                myBitmap,
                0,
                (myBitmap.getHeight()/2  - myBitmap.getWidth()/2) - myBitmap.getWidth()/3  , //estaba en dividio para 4
                 myBitmap.getWidth(),
                (myBitmap.getHeight()/2) + myBitmap.getWidth()/3
              //  myBitmap.getWidth()
        );

        return dstBmp;

    }






return  myBitmap;
}




    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return null;
    }



    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
         matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }


}
