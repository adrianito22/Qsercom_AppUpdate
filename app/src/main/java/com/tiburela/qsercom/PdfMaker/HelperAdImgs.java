package com.tiburela.qsercom.PdfMaker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.text.Layout;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
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
import com.tiburela.qsercom.storage.StorageData;
import com.tiburela.qsercom.utils.HelperImage;
import com.tiburela.qsercom.utils.Variables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ExecutionException;

public class HelperAdImgs implements LifecycleOwner {
   public static  ArrayList<ImagenReport> currentListImagesSeccion;

    static   boolean wehaveAddSpaceyIndescrip =false;
    static   float   posicionLastELEMENTAd =10000f;
     static boolean isPosicion0AndNewAnexo=false;
    static float yPosicion=150;
    static float yPosicionSuper=0;
    StorageReference storageRef;
   public static PdfDocument pdfDocumentx;
   static  Bitmap bitmapDevolver=null;
   static  Bitmap myBitmap;

    private static  MutableLiveData<Bitmap> userNameMutableLiveData = new MutableLiveData<>();
   static Bitmap mapa;


   // static float yPosicion=150;


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


            else if (contador==0  && setFotoCategory==Variables.FOTO_LLEGADA_CONTENEDOR ) {
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

        Rectangle remaining ;

        Log.i("posicuon","el posicon del ultimo elemento es "+ yPosicion);

        if(isPosicion0AndNewAnexo){
            heigthImg=heigthImg+10;
            isPosicion0AndNewAnexo=false;
        }

        remaining=  docuemnto.getRenderer().getCurrentArea().getBBox();
        yPosicionSuper= remaining.getTop();


        Cell cell;

        float espcioCuparaThisItem;


        if(tipoOrdenImgs==Variables.TRES_IMGS_VERTCLES){


            Log.i("salertty","hay TRES_IMGS_VERTCLES");

            float [] tableWidth  = {1,1,1} ;

            Table table = new Table(tableWidth,true);

            Image imagVertical1=HelperPdf.createInfoImgtoPDF( retornaBitmaPhere(contexta,HelperImage.imagesSetToCurrentFila.get(0).getUrlStoragePic(),HelperImage.imagesSetToCurrentFila.get(0)));


            imagVertical1.setAutoScale(true);
            imagVertical1.scaleAbsolute(widthImg,heigthImg);


            if(HelperImage.imagesSetToCurrentFila.get(0).getDescripcionImagen().length()>1){

                //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imagVertical1,HelperImage.imagesSetToCurrentFila.get(0).getDescripcionImagen());

            }


            else {

                cell = new Cell().setBorder(Border.NO_BORDER).add(imagVertical1); //estaba asi
            }


            table.addCell(cell);



            imagVertical1=HelperPdf.createInfoImgtoPDF( retornaBitmaPhere(contexta,HelperImage.imagesSetToCurrentFila.get(1).getUrlStoragePic(),HelperImage.imagesSetToCurrentFila.get(1)));
            imagVertical1.setAutoScale(true);
            imagVertical1.scaleAbsolute(widthImg,heigthImg);



            if(HelperImage.imagesSetToCurrentFila.get(1).getDescripcionImagen().length()>1){  //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imagVertical1,HelperImage.imagesSetToCurrentFila.get(1).getDescripcionImagen());
            }
            else {

                cell = new Cell().setBorder(Border.NO_BORDER).add(imagVertical1); //estaba asi
            }



            table.addCell(cell);


             imagVertical1=HelperPdf.createInfoImgtoPDF( retornaBitmaPhere(contexta,HelperImage.imagesSetToCurrentFila.get(2).getUrlStoragePic(),HelperImage.imagesSetToCurrentFila.get(2)));
            imagVertical1.setAutoScale(true);
            imagVertical1.scaleAbsolute(widthImg,heigthImg);



            if(HelperImage.imagesSetToCurrentFila.get(2).getDescripcionImagen().length()>1){  //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imagVertical1,HelperImage.imagesSetToCurrentFila.get(2).getDescripcionImagen());
            }
            else {

                cell = new Cell().setBorder(Border.NO_BORDER).add(imagVertical1); //estaba asi
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

            Image imagVertical=HelperPdf.createInfoImgtoPDF( retornaBitmaPhere(contexta,HelperImage.imagesSetToCurrentFila.get(0).getUrlStoragePic(),HelperImage.imagesSetToCurrentFila.get(0)));
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


            float [] tableWidth  = {190f,400} ; //estaba en 250.. le ponemos
           //  float [] tableWidth  = {155f,200} ; //estaba en 250.. le ponemos

            Table table = new Table(tableWidth,true);

                 Image imagVertical=HelperPdf.createInfoImgtoPDF( retornaBitmaPhere(contexta,HelperImage.imagesSetToCurrentFila.get(0).getUrlStoragePic(),HelperImage.imagesSetToCurrentFila.get(0)));
                 imagVertical.setAutoScale(true);


           // imagVertical.scaleAbsolute(190,heigthImg);

            imagVertical.scaleToFit(190,heigthImg);


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




               Image imgHorizontal=HelperPdf.createInfoImgtoPDF(retornaBitmaPhere(contexta,HelperImage.imagesSetToCurrentFila.get(1).getUrlStoragePic(),HelperImage.imagesSetToCurrentFila.get(1)));
              imgHorizontal.setAutoScale(true);
            //imagVertical.setHorizontalAlignment(HorizontalAlignment.RIGHT);

            //imgHorizontal.scaleAbsolute(335-6,heigthImg);
            imgHorizontal.scaleToFit(335-6,heigthImg);



          //  imgHorizontal.scaleToFit((pageSize.getWidth()-70f)/1.8f,heigthImg); //estaba en 1.9
            imgHorizontal.setHorizontalAlignment(HorizontalAlignment.LEFT);
            imgHorizontal.setMarginLeft(15f);
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


            float [] tableWidth  = {200,155f} ;// 1.5 estaba
            Table table = new Table(tableWidth,true);


            Image imgHorizontal=HelperPdf.createInfoImgtoPDF(retornaBitmaPhere(contexta,HelperImage.imagesSetToCurrentFila.get(0).getUrlStoragePic(),HelperImage.imagesSetToCurrentFila.get(0)));
            imgHorizontal.setAutoScale(true);

             Log.i("cuandoexecuta","se ejecduto esta");
          // imgHorizontal.scaleAbsolute((pageSize.getWidth()-70f)/2,heigthImg);
            imgHorizontal.scaleAbsolute(335,heigthImg);
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


            Image imagVertical=HelperPdf.createInfoImgtoPDF( retornaBitmaPhere(contexta,HelperImage.imagesSetToCurrentFila.get(1).getUrlStoragePic(),HelperImage.imagesSetToCurrentFila.get(1)));
            imagVertical.setAutoScale(true);

           // imagVertical.scaleAbsolute(widthImg,heigthImg);
            imagVertical.scaleAbsolute(190,heigthImg);


          //  imagVertical.sca
            imagVertical.setHorizontalAlignment(HorizontalAlignment.LEFT);
            //imagVertical.setMarginRight(10f);
            imagVertical.setMarginLeft(10f); //estaba en 10


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

            Image imagHorizontal = HelperPdf.createInfoImgtoPDF(retornaBitmaPhere(contexta, HelperImage.imagesSetToCurrentFila.get(0).getUrlStoragePic(), HelperImage.imagesSetToCurrentFila.get(0)));
            imagHorizontal.setAutoScale(true);

            imagHorizontal.scaleAbsolute(((pageSize.getWidth()-70)/2   ),heigthImg-60);


            imagHorizontal.setHorizontalAlignment(HorizontalAlignment.LEFT);
            imagHorizontal.setMarginRight(3f);


            if(HelperImage.imagesSetToCurrentFila.get(0).getDescripcionImagen().length()>1){  //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imagHorizontal,HelperImage.imagesSetToCurrentFila.get(0).getDescripcionImagen());
            }
            else {

                cell = new Cell().setBorder(Border.NO_BORDER).add(imagHorizontal); //estaba asi
            }

            table.addCell(cell);


            imagHorizontal=HelperPdf.createInfoImgtoPDF(retornaBitmaPhere(contexta,HelperImage.imagesSetToCurrentFila.get(1).getUrlStoragePic(),HelperImage.imagesSetToCurrentFila.get(1)));
            imagHorizontal.setAutoScale(true);

            imagHorizontal.scaleAbsolute(((pageSize.getWidth()-70)/2   ),heigthImg-60);


            imagHorizontal.setHorizontalAlignment(HorizontalAlignment.LEFT);
            imagHorizontal.setMarginLeft(3f);


            if(HelperImage.imagesSetToCurrentFila.get(1).getDescripcionImagen().length()>1){  //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imagHorizontal,HelperImage.imagesSetToCurrentFila.get(1).getDescripcionImagen());
            }
            else {

                cell = new Cell().setBorder(Border.NO_BORDER).add(imagHorizontal); //estaba asi
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




            Table table = new Table(1,true);

            Image imagHorizontal=HelperPdf.createInfoImgtoPDF( retornaBitmaPhere(contexta,HelperImage.imagesSetToCurrentFila.get(0).getUrlStoragePic(),HelperImage.imagesSetToCurrentFila.get(0)));
            imagHorizontal.setAutoScale(true);
            //imagHorizontal.scaleToFit()

            imagHorizontal.setHorizontalAlignment(HorizontalAlignment.CENTER);


            if(HelperImage.imagesSetToCurrentFila.get(0).getDescripcionImagen().length()>1){  //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imagHorizontal,HelperImage.imagesSetToCurrentFila.get(0).getDescripcionImagen());
            }
            else {

                cell = new Cell().setBorder(Border.NO_BORDER).add(imagHorizontal); //estaba asi
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



            Table table = new Table(1,true);



            Log.i("urlptint","la url es "+HelperImage.imagesSetToCurrentFila.get(0).getUrlStoragePic());
            Log.i("urlptint","la url es "+HelperImage.imagesSetToCurrentFila.get(0).getUniqueIdNamePic());



            Image imagVertical=HelperPdf.createInfoImgtoPDF( retornaBitmaPhere(contexta,HelperImage.imagesSetToCurrentFila.get(0).getUrlStoragePic(),HelperImage.imagesSetToCurrentFila.get(0)));
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
     Cell    cell= new Cell();
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


        public static  Bitmap generaBitmap(Context contexto,String urlImage)  {


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




                                Log.i("haisdssdsd","es reay listo..");

                                // imageView.setImageBitmap(resource);
                            }

                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder) {
                            }
                        });





        return  bitmapDevolver;
    }


    public static Bitmap generaBitmapMutable(Context contexto,String urlImage,int inte) {

        Glide.with(contexto)
                .asBitmap()
                .load(urlImage)    //.onlyRetrieveFromCache(false)
                .into(new CustomTarget<Bitmap>() {

                    //  Observer<Bitmap> observer= new Observer<Bitmap>() {

                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        //

                        userNameMutableLiveData.setValue(bitmapDevolver);

                           mapa=userNameMutableLiveData.getValue();

                        Log.i("haisdssdsd","es reay listo..");

                        // imageView.setImageBitmap(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });

        if(mapa==null) {
            Log.i("haisdssdsd", "es mpbit es nulo..");

        } else{
                Log.i("haisdssdsd","el bitmap no es nulo");


            }


        Log.i("haisdssdsd","es reay listo.");


        return mapa;



    }



public static  Bitmap retornaBitmaPhere(Context contexto, String urlImage,ImagenReport image) throws ExecutionException, InterruptedException {

    Log.i("ladtastor","llamos reotnabima");

    BitmapCreatorBackG foo = new BitmapCreatorBackG();
    foo.iniValuesParams(contexto,urlImage,image);

    Thread thread = new Thread(foo);
    thread.start();
    thread.join();
    myBitmap = foo.getValueBitmap();

      Bitmap dstBmp ;

    //si el alto de la image es el doble del ancho
    if (myBitmap.getHeight() > myBitmap.getWidth() + myBitmap.getWidth()   ){

       // int dimension = Math.min(myBitmap.getWidth(), myBitmap.getHeight());
     //   return ThumbnailUtils.extractThumbnail(myBitmap, dimension, dimension);
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


/*
    private void generaBitmap2(Context contexto, String urlImage,int TypeImagenn)  {

            //aqui btenemos una lista de imagenes y hacemos el calucalo

      //  userNameMutableLiveData.observe(this,observer):



        generaBitmapMutable(contexto,urlImage,4).observe(this, new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap userName) {

                //aqui hacesmo

                   //aqui tenemos el bitmappp creo;
                //here, do whatever you want on `userName`


            }
        });


    }


*/


   /*
   private void genratebitmapNew(Context contexto,String urlImage){

       Glide.with(contexto)
               .load("your url")
               .asBitmap()
               .into(new BitmapImageViewTarget(imgView) {
                   @Override
                   protected void setResource(Bitmap resource) {
                       //Play with bitmap
                       super.setResource(resource);
                   }
               });
   }

*/

    private void dowloadAndSetImg(String imgPath, ImageView holder, Context context){

         storageRef  = StorageData.rootStorageReference.child("imagenes_all_reports/"+imgPath);

        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Glide.with(context)
                        .load(uri)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)         //ALL or NONE as your requirement
                        //.thumbnail(Glide.with(OfertsAdminActivity.context).load(R.drawable.enviado_icon))
                        // .error(R.drawable.)
                        .into(holder);

                String uriX = uri.toString();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Log.i("ladtastor","es un fallo y es "+exception.getMessage());

                try{

                    //   Glide.with(ActivitySeeReports.context)
                    //.load(R.drawable.acea2)
                    // .fitCenter()
                    // .into(holder.imgViewLogoGIFTc);


                } catch (Exception e) {
                    e.printStackTrace();
                }



            }
        });






    }
public static Bitmap generabitmnaprotado(Bitmap bitmap, String filePath) {

       // Bitmap bitmap = BitmapFactory.decodeFile(filePath);

        try
        {
            // Determine Orientation
            ExifInterface exif = new ExifInterface(filePath);
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);

            // Determine Rotation
            int rotation = 0;
            if      (orientation == 6)      rotation = 90;
            else if (orientation == 3)      rotation = 180;
            else if (orientation == 8)      rotation = 270;

            // Rotate Image if Necessary
            if (rotation != 0)
            {
                // Create Matrix
                Matrix matrix = new Matrix();
                matrix.postRotate(rotation);

                // Rotate Bitmap
                Bitmap rotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

                // Pretend none of this ever happened!
                bitmap.recycle();
                bitmap = rotated;
                rotated = null;
            }
        }
        catch (Exception e)
        {
            // TODO: Log Error Messages Here
        }

// TODO: Use Result Here
       // xxx.setBitmap(bitmap);

      return  bitmap;
    }


    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return null;
    }
}
