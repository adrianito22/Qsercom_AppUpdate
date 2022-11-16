package com.tiburela.qsercom.PdfMaker;

import android.content.Context;
import android.util.Log;

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
import com.itextpdf.layout.property.VerticalAlignment;
import com.tiburela.qsercom.models.ImagesToPdf;
import com.tiburela.qsercom.utils.HelperImage;
import com.tiburela.qsercom.utils.Variables;

import java.util.ArrayList;

public class HelperAdImgs {
   public static  ArrayList<ImagesToPdf> currentListImagesSeccion;

    static   boolean wehaveAddSpaceyIndescrip =false;
    static   float   posicionLastELEMENTAd =10000f;
     static boolean isPosicion0AndNewAnexo=false;
    static float yPosicion=150;
    static float yPosicionSuper=0;

   public static PdfDocument pdfDocumentx;


   // static float yPosicion=150;


    public static void  initpdfDocument( PdfDocument pdfDocument) {

       pdfDocumentx =pdfDocument;

    }


    public static void createPages_addImgs(int setFotoCategory ,String anexoNombre, Document midocumentotoAddData,
                                            PageSize pageSize,Context contexto) throws Exception {
        int contador =0;

        Log.i("xamil","la foto selecionada categoria es  "+setFotoCategory);


        Log.i("contaburx","el size de inagesetglobal es "+HelperImage.imAGESpdfSetGlobal.size());

        currentListImagesSeccion= HelperImage.getImagesWhitthisCATEGORY(HelperImage.ImagesToPdfMap,setFotoCategory);///era Variables.FOTO_LLEGADA

        Log.i("xamil","el SIZE DE  current list images seccion es "+currentListImagesSeccion.size());


        //nos asegurammos que ninguna este en el pdf aun ,LAS DESMARCAMOS
        currentListImagesSeccion= HelperImage.marcaQueNoEstaEnPDF(currentListImagesSeccion);
       // Log.i("contaburx","la IMAGEN DE LA 1  POSCION esta en el pdf? "+currentListImagesSeccion.get(0).estaENPdf);
       // Log.i("contaburx","la IMAGEN DE LA 2 POSCION esta en el pdf? "+currentListImagesSeccion.get(1).estaENPdf);

        Log.i("PATRONX","el size de imagenes de estacategoria es  "+currentListImagesSeccion.size());


        while(!allImagesISUsed(currentListImagesSeccion)){ //mientras quedan imagenes si usar////

            if(contador==0  && setFotoCategory==Variables.FOTO_LLEGADA ) { ///significa que es la primera pagina
              ///AGREGMAOS TEXTO
                midocumentotoAddData.add(new Paragraph("ANEXOS").setFontSize(10f).setPaddingLeft(50f).setBold().setMarginTop(2f));
                midocumentotoAddData.add(new Paragraph("*  "+anexoNombre).setFontSize(10f).
                        setPaddingLeft(60f).setMarginTop(1f).setBold());
                isPosicion0AndNewAnexo=true;



            }


            else if (contador==0  && setFotoCategory==Variables.FOTO_CONTENEDOR ) { ///significa que es la primera pagina
                ///AGREGMAOS TEXTO
                midocumentotoAddData.add(new Paragraph(anexoNombre).setFontSize(10f).setBold().setMarginTop(2f).setTextAlignment(TextAlignment.CENTER));

                Log.i("sajsdfgfsd","es foto contenedor ");

                isPosicion0AndNewAnexo=true;

            }


            else if (contador==0  && setFotoCategory==Variables.FOTO_SELLO_LLEGADA ) { ///significa que es la primera pagina
                ///AGREGMAOS TEXTO
                midocumentotoAddData.add(new Paragraph(anexoNombre).setFontSize(10f).setBold().setMarginTop(2f).setTextAlignment(TextAlignment.CENTER));
                Log.i("sajsdfgfsd","es foto contenedor ");

                isPosicion0AndNewAnexo=true;

            }









            Log.i("homero","el posicion las element en comprobacion es  "+posicionLastELEMENTAd);

            /**chekeamos si cremos otra pagina*/
                if(posicionLastELEMENTAd<145){ //estaba en 150

                  //  Log.i("sajsdfgfsd","creamos una nueva pagina yPosicion el id es: "+setFotoCategory);

                    Log.i("homera","laposicion es  "+posicionLastELEMENTAd);

                    Log.i("homera","creamos una nueva pagina ");

                   // HelperPdf pdfHelper= new HelperPdf();
                  // Image imglogqSercom=pdfHelper.createInfoImgtoPDF(contexto.getDrawable(R.drawable.headerpdf),1);
                    // imglogqSercom.setHorizontalAlignment(HorizontalAlignment.CENTER);
                   // midocumentotoAddData.add(imglogqSercom).setTopMargin(0f);

                }




            contador++;


            Log.i("contaburx","se ejecuto ESto "+contador+" veces");
            int patronEncontrado=HelperImage.buscaPosiblePatronParaOrdenar(currentListImagesSeccion);


            if(patronEncontrado== Variables.TRES_IMGS_VERTCLES){
                wehaveAddSpaceyIndescrip =false;
                Log.i("PATRONX","es Variables.TRES_IMGS_VERTCLES");

                addImagenSetAndCreateNewPage(Variables.TRES_IMGS_VERTCLES,midocumentotoAddData,pageSize);
                //ENTONCES ESTOS ENCONTRADOS LOS PONEMOS QUE YA SE USARON.....
            }

            else if(patronEncontrado == Variables.DOS_IMGS_VERTICALES) {
                Log.i("PATRONX","es el DOS_IMGS_VERTICALES ");
                wehaveAddSpaceyIndescrip =false;

                addImagenSetAndCreateNewPage(Variables.DOS_IMGS_VERTICALES,midocumentotoAddData,pageSize);
            }

            else if(patronEncontrado == Variables.UNAVERTICAL_Y_OTRA_HORIZONTAL) {
                Log.i("PATRONX","es  UNAVERTICAL_Y_OTRA_HORIZONTAL");
                wehaveAddSpaceyIndescrip =false;

                addImagenSetAndCreateNewPage(Variables.UNAVERTICAL_Y_OTRA_HORIZONTAL,midocumentotoAddData,pageSize);
            }

            else if(patronEncontrado == Variables.DOS_HORIZONTALES) {
                Log.i("PATRONX","es el DOS_HORIZONTALES ");
                wehaveAddSpaceyIndescrip =false;

                addImagenSetAndCreateNewPage(Variables.DOS_HORIZONTALES,midocumentotoAddData,pageSize);
            }

            else if(patronEncontrado == Variables.UNA_HORIZONTAL) {
                Log.i("PATRONX","es el UNA_HORIZONTAL ");
                wehaveAddSpaceyIndescrip =false;

                addImagenSetAndCreateNewPage(Variables.UNA_HORIZONTAL,midocumentotoAddData,pageSize);
            }


            else if(patronEncontrado == Variables.UNA_VERTICAL) {
                Log.i("PATRONX","es el UNA_HORIZONTAL ");
                wehaveAddSpaceyIndescrip =false;

                addImagenSetAndCreateNewPage(Variables.UNA_VERTICAL,midocumentotoAddData,pageSize);
            }


            else if(patronEncontrado == Variables.DEFAULNO_ENCONTRO_NADA) {
                Log.i("PATRONX","es el  DEFAULNO_ENCONTRO_NADA");
                wehaveAddSpaceyIndescrip =false;

                addImagenSetAndCreateNewPage(Variables.DEFAULNO_ENCONTRO_NADA,midocumentotoAddData,pageSize);
            }

        }

    }
    private static boolean allImagesISUsed(ArrayList<ImagesToPdf> list){
        int contadorIMagesUsadas=0;


        for(int indice=0; indice<list.size(); indice++){

            if(list.get(indice).estaENPdf){

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




    private static void addImagenSetAndCreateNewPage(int tipoOrdenImgs, Document docuemnto, PageSize pageSize) throws Exception {

        float espacioDisponibleHorizontal=pageSize.getWidth()-100f; //
        //si hay texto o comentario le restamos menos espacio....

        float heigthImg=232;
        float widthImg=(pageSize.getWidth()-70f)/3;


        Rectangle remaining ;
///
       // Rectangle remaining = docuemnto.getRenderer().getCurrentArea().getBBox();
        Log.i("posicuon","el posicon del ultimo elemento es "+ yPosicion);


        if(isPosicion0AndNewAnexo){
            heigthImg=heigthImg+10;
            isPosicion0AndNewAnexo=false;
        }

        remaining=  docuemnto.getRenderer().getCurrentArea().getBBox();
        yPosicionSuper= remaining.getTop();


        Cell cell;

        float espcioCuparaThisItem;


        //comprobar en que linea ... comprobar la posicion de la ultima
        if(tipoOrdenImgs==Variables.TRES_IMGS_VERTCLES){ //modo 3 imagenes en una linea...
            Log.i("contabur","hay 3 imagenes verticales hurrazzx");
          //  float [] tableWidth  = {1,1,1} ;
            float [] tableWidth  = {1,1,1} ;

            Table table = new Table(tableWidth,true);
            // table.setHeight(200);  //Primera tabla ///este ancho...

            Image imagVertical1=HelperPdf.createInfoImgtoPDF(HelperImage.imagesSetToCurrentFila.get(0).miBitmap);
            imagVertical1.setAutoScale(true);
           //   imagVertical1.scaleToFit((pageSize.getWidth()-20f)/3,230);
            imagVertical1.scaleAbsolute(widthImg,heigthImg);



            /*revismaos imagens**///
          ///  imagVertical1.setProperty(Property.BORDER_BOTTOM, FloatPropertyValue.NONE);



            //test a la primer imagen le agregamos texto

            if(HelperImage.imagesSetToCurrentFila.get(0).descripcionOpcion.length()>1){

                //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imagVertical1,HelperImage.imagesSetToCurrentFila.get(0).descripcionOpcion);

            }


            else {

                cell = new Cell().setBorder(Border.NO_BORDER).add(imagVertical1); //estaba asi
            }


            table.addCell(cell);




             imagVertical1=HelperPdf.createInfoImgtoPDF(HelperImage.imagesSetToCurrentFila.get(1).miBitmap);
            imagVertical1.setAutoScale(true);
            imagVertical1.scaleAbsolute(widthImg,heigthImg);




            if(HelperImage.imagesSetToCurrentFila.get(1).descripcionOpcion.length()>1){  //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imagVertical1,HelperImage.imagesSetToCurrentFila.get(1).descripcionOpcion);
            }
            else {

                cell = new Cell().setBorder(Border.NO_BORDER).add(imagVertical1); //estaba asi
            }



            table.addCell(cell);


             imagVertical1=HelperPdf.createInfoImgtoPDF(HelperImage.imagesSetToCurrentFila.get(0).miBitmap);
            imagVertical1.setAutoScale(true);
            imagVertical1.scaleAbsolute(widthImg,heigthImg);



            if(HelperImage.imagesSetToCurrentFila.get(2).descripcionOpcion.length()>1){  //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imagVertical1,HelperImage.imagesSetToCurrentFila.get(2).descripcionOpcion);
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


/*

            if(espcioCuparaThisItem < 131  ) { //debe de quedas mas del tamano del alto de laimagen
                Log.i("posicuon","el espcioCuparaThisItem es "+espcioCuparaThisItem);

                docuemnto.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
                yPosicion=150;
                docuemnto.add(table);

            }else {
                docuemnto.add(table);
                remaining = docuemnto.getRenderer().getCurrentArea().getBBox();
                yPosicion= remaining.getTop();

            }

*/


            //posicionLastELEMENTAd = remaining.getTop();
            Log.i("homero","eldocuemnto.getRenderer().getCurrentArea().getBBox() ES "+posicionLastELEMENTAd);
            markImgComoUsada(HelperImage.imagesSetToCurrentFila); //podem



        }


        else if(tipoOrdenImgs==Variables.UNAVERTICAL_Y_OTRA_HORIZONTAL){ //1 vertical yPosicion otro horizontal en la misma linea
                  float [] tableWidth  = {1,1} ;
                 Table table = new Table(tableWidth,true);

                 Image imagVertical=HelperPdf.createInfoImgtoPDF(HelperImage.imagesSetToCurrentFila.get(0).miBitmap);
                 imagVertical.setAutoScale(true);
                 imagVertical.setHorizontalAlignment(HorizontalAlignment.RIGHT);
                 imagVertical.setMarginRight(10f);


            if(HelperImage.imagesSetToCurrentFila.get(0).descripcionOpcion.length()>1){  //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imagVertical,HelperImage.imagesSetToCurrentFila.get(0).descripcionOpcion);
            }
            else {
                cell = new Cell().setBorder(Border.NO_BORDER).add(imagVertical); //estaba asi
            }

            table.addCell(cell);




                 Image imgHorizontal=HelperPdf.createInfoImgtoPDF(HelperImage.imagesSetToCurrentFila.get(1).miBitmap);
                 imgHorizontal.setAutoScale(true);
            //imagVertical.setHorizontalAlignment(HorizontalAlignment.RIGHT);
                 imagVertical.setMarginLeft(10f);


            if(HelperImage.imagesSetToCurrentFila.get(1).descripcionOpcion.length()>1){  //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imgHorizontal,HelperImage.imagesSetToCurrentFila.get(1).descripcionOpcion);
            }
            else {
                cell = new Cell().setBorder(Border.NO_BORDER).add(imgHorizontal); //estaba asi
            }

            table.addCell(cell);




            table.setWidth((widthImg*2)+80f);
            table.setHorizontalAlignment(HorizontalAlignment.CENTER);
            table.setMarginTop(8f);
            table.setHeight(heigthImg);

          //  configtable(table,pageSize);

            espcioCuparaThisItem= yPosicion +8+ heigthImg ; //8 es margin

            if(heigthImg+8 >yPosicionSuper) {  // +8 de margin

                docuemnto.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            }





        //    Rectangle remaining = docuemnto.getRenderer().getCurrentArea().getBBox();
          //  posicionLastELEMENTAd = remaining.getTop();
            Log.i("homero","eldocuemnto.getRenderer().getCurrentArea().getBBox() ES "+posicionLastELEMENTAd);

            docuemnto.add(table);

            markImgComoUsada(HelperImage.imagesSetToCurrentFila); //podemos pasarle una lista de este 3 imagenes o marca una por una como hicimos arriba



        }


        else if(tipoOrdenImgs==Variables.DOS_IMGS_VERTICALES){ //2 imagenes verticales en una linea


            float [] tableWidth  = {1,1} ;
            Table table = new Table(tableWidth,true);
          //  table.setHeight(280);  //Primera tabla ///este ancho...

            Image imagVertical=HelperPdf.createInfoImgtoPDF(HelperImage.imagesSetToCurrentFila.get(0).miBitmap);
            imagVertical.setAutoScale(true);
            imagVertical.setHorizontalAlignment(HorizontalAlignment.RIGHT);
            imagVertical.setMarginRight(10f);
            //imagVertical.scaleAbsolute(widthImg,heigthImg);



            if(HelperImage.imagesSetToCurrentFila.get(0).descripcionOpcion.length()>1){  //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imagVertical,HelperImage.imagesSetToCurrentFila.get(0).descripcionOpcion);
            }
            else {

                cell = new Cell().setBorder(Border.NO_BORDER).add(imagVertical); //estaba asi
            }

            table.addCell(cell);


             imagVertical=HelperPdf.createInfoImgtoPDF(HelperImage.imagesSetToCurrentFila.get(1).miBitmap);
            imagVertical.setAutoScale(true);
            imagVertical.setMarginLeft(10f);
           // imagVertical.scaleAbsolute(widthImg,heigthImg);


            if(HelperImage.imagesSetToCurrentFila.get(1).descripcionOpcion.length()>1){  //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imagVertical,HelperImage.imagesSetToCurrentFila.get(1).descripcionOpcion);
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


        else if(tipoOrdenImgs==Variables.DOS_HORIZONTALES){ //2 imagenes verticales en una linea
            float [] tableWidth  = {1,1} ;
            Table table = new Table(tableWidth,true);

            Image imagHorizontal=HelperPdf.createInfoImgtoPDF(HelperImage.imagesSetToCurrentFila.get(0).miBitmap);
            imagHorizontal.setAutoScale(true);
            imagHorizontal.setHorizontalAlignment(HorizontalAlignment.RIGHT);
            imagHorizontal.setMarginRight(6f);


            if(HelperImage.imagesSetToCurrentFila.get(0).descripcionOpcion.length()>1){  //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imagHorizontal,HelperImage.imagesSetToCurrentFila.get(0).descripcionOpcion);
            }
            else {

                cell = new Cell().setBorder(Border.NO_BORDER).add(imagHorizontal); //estaba asi
            }

            table.addCell(cell);


            imagHorizontal=HelperPdf.createInfoImgtoPDF(HelperImage.imagesSetToCurrentFila.get(1).miBitmap);
            imagHorizontal.setAutoScale(true);
            imagHorizontal.setHorizontalAlignment(HorizontalAlignment.LEFT);
            imagHorizontal.setMarginLeft(6f);


            if(HelperImage.imagesSetToCurrentFila.get(1).descripcionOpcion.length()>1){  //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imagHorizontal,HelperImage.imagesSetToCurrentFila.get(1).descripcionOpcion);
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

            if(Variables.ES_ULTIMA_IMAGEN_Y_PRIMERA_PAGINA) {
                heigthImg= heigthImg+50;

                Variables.ES_ULTIMA_IMAGEN_Y_PRIMERA_PAGINA =false;
            }




            Table table = new Table(1,true);

            Image imagHorizontal=HelperPdf.createInfoImgtoPDF(HelperImage.imagesSetToCurrentFila.get(0).miBitmap);
            imagHorizontal.setAutoScale(true);
            imagHorizontal.setHorizontalAlignment(HorizontalAlignment.CENTER);


            if(HelperImage.imagesSetToCurrentFila.get(0).descripcionOpcion.length()>1){  //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imagHorizontal,HelperImage.imagesSetToCurrentFila.get(0).descripcionOpcion);
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

            if(Variables.ES_ULTIMA_IMAGEN_Y_PRIMERA_PAGINA) {
                heigthImg= heigthImg+50;

                Variables.ES_ULTIMA_IMAGEN_Y_PRIMERA_PAGINA =false;
            }




            Table table = new Table(1,true);

            Image imagVertical=HelperPdf.createInfoImgtoPDF(HelperImage.imagesSetToCurrentFila.get(0).miBitmap);
            imagVertical.setAutoScale(true);


            if(HelperImage.imagesSetToCurrentFila.get(0).descripcionOpcion.length()>1){  //aqui agregamos la descripcion si contiene
                cell= addImgAndTextDescriptionInCell(pdfDocumentx,imagVertical,HelperImage.imagesSetToCurrentFila.get(0).descripcionOpcion);
            }
            else {

                cell = new Cell().setBorder(Border.NO_BORDER).add(imagVertical); //estaba asi
            }


            table.addCell(cell);

            table.setHeight(heigthImg);  //Primera tabla ///este ancho...
            table.setWidth(pageSize.getWidth()-100);
            table.setMarginLeft(40f);
            table.setMarginRight(40f);

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

        }


        //yPosicion oior ultimo si hay una imagen sola....tamnto vertical o horizontal ..no la aghregues ,,es una opcion




    }





    public static  void configtable(Table table1,PageSize pageSize){

        table1.setWidth(pageSize.getWidth()-20f);
        table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table1.setMarginTop(8f);

        table1.setMarginLeft(10f);
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


    private static void markImgComoUsada(ArrayList<ImagesToPdf> list){

        //buscamos esos ids,, yPosicion marcamos como usado...
        //vamos atenber dos listas.... una lista que es la litsa del conjunto actual yPosicion una lista que dice


        for(int i= 0; i<list.size(); i++){ ///
            String uniqueId=list.get(i).uniQueIdimgPertenece;

            for(int indice=0; indice<currentListImagesSeccion.size(); indice++){

                if(uniqueId.equals(currentListImagesSeccion.get(indice).uniQueIdimgPertenece)) {
                    currentListImagesSeccion.get(indice).estaENPdf=true;

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



}
