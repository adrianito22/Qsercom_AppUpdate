package com.tiburela.qsercom.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.StorageReference;
import com.tiburela.qsercom.activities.formulariosPrev.ActivityContenedoresPrev;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.storage.StorageData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class HelperImage {
   public static  ArrayList<ImagenReport> imAGESpdfSetGlobal=new ArrayList<ImagenReport>();


   public static  ArrayList<ImagenReport> imagesSetToCurrentFila;

   public static  HashMap<String, ImagenReport> ImagenReportMap;




    public static void addImagenInPDF(Bitmap imagen2, Canvas canvas,RectF dst) {

        //  RectF dst = new RectF(50, 225, 50 + 500, 50 + 350);
       // canvas.drawBitmap(imagen, null, dst, null);
        RectF dstx = new RectF(50, 450, 50 + 500, 50 + 600);


      //  RectF dstx = new RectF(50, 450, 50 + 500, 50 + 600);
        canvas.drawBitmap(imagen2, null, dst, null);
       // canvas.drawBitmap(imagen2, null, dstx, null);


    }


    private void addImagenSet(int posicionUltimaImagenColoacadaY, ArrayList<ImagenReport> list, int tipoOrdenImgs, Canvas canvas){

        //comprobar en que linea ... comprobar la posicion de la ultima

        if(tipoOrdenImgs==Variables.TRES_IMGS_VERTCLES){ //modo 3 imagenes en una linea...

            //LE SUMAMOS MAS 10 A LA POSICION  posicionUltimaImagenColoacadaY
            posicionUltimaImagenColoacadaY= posicionUltimaImagenColoacadaY+10;

          //  HelperImage.addImagenInPDF();

            //e verticales serian




            //**colocamos las 3 imagenes en una linea....
            // ad imagen set i pdf....
            //AQUI USAMOS EL RECTS Y GENERAMOS EL BITMAP PARA AGREGARLO AL CANVAS O PDF PAGE QUE RECIMOS POR PARAEMTRO....

            ///1ERA imagen

            //2DA imagen

            //3ERA IMAGEN



            posicionUltimaImagenColoacadaY=    posicionUltimaImagenColoacadaY+500 ; //+EL VALOR QUE OCUPA LA FILA IMAGENES  EN VERTICALMENTE
            //EL SIZE DEL REC PUEDE SER 500 LA VARIABLE
            //ASI EN LA SIGUIENTE SOLO LE SUMAMOS +10


            //agregamos en la linea 2...


        }

        else if(tipoOrdenImgs==Variables.UNAVERTICAL_Y_OTRA_HORIZONTAL){ //1 vertical y otro horizontal en la misma linea



        }


        else if(tipoOrdenImgs==Variables.DOS_IMGS_VERTICALES){ //2 imagenes verticales en una linea



        }


        else if(tipoOrdenImgs==Variables.DOS_HORIZONTALES){ //2 imagenes verticales en una linea



        }

        //y oior ultimo si hay una imagen sola....tamnto vertical o horizontal ..no la aghregues ,,es una opcion




    }



    public static ArrayList<ImagenReport> getImagesWhitthisCATEGORY( ArrayList <ImagenReport>allImagesData, int categoriaBuscar) {
        ArrayList<ImagenReport> ImagenReport = new ArrayList<>();




        for (ImagenReport imageObject: allImagesData) {

            int  categoryCurrentImg=imageObject.getTipoImagenCategory();
            Log.i("xamil","categoria current imagen es"+categoryCurrentImg);

            if (categoryCurrentImg == categoriaBuscar) {
                ImagenReport.add(imageObject);
            }

        }

  
        return  ImagenReport;


    }


    public static ArrayList<ImagenReport> getImagesWhitthisCATEGORYz( HashMap<String, ImagenReport >allImagesData, int categoriaBuscar) {
        ArrayList<ImagenReport> ImagenReport = new ArrayList<>();




        for (ImagenReport imageObject: allImagesData.values()) {

            int  categoryCurrentImg=imageObject.getTipoImagenCategory();
            Log.i("xamil","categoria current imagen es"+categoryCurrentImg);

            Log.i("xamil","el uri es "+imageObject.getUrlStoragePic());



            if (categoryCurrentImg == categoriaBuscar) {
                ImagenReport.add(imageObject);
            }

        }




/*
        for (ImagenReport imageObject: allImagesData) {

            int  categoryCurrentImg=imageObject.getTipoImagenCategory();
            Log.i("xamil","categoria current imagen es"+categoryCurrentImg);

            if (categoryCurrentImg == categoriaBuscar) {
                ImagenReport.add(imageObject);
            }

        }
*/

        return  ImagenReport;


    }
    

    public static ArrayList<ImagenReport> getImagesWhitthisCATEGORY( HashMap<String, ImagenReport>allImagesData, int categoriaBuscar) {
        ArrayList<ImagenReport> ImagenReport = new ArrayList<>();




        for (ImagenReport imageObject: allImagesData.values()) {

            int  categoryCurrentImg=imageObject.getTipoImagenCategory();
            Log.i("xamil","categoria current imagen es"+categoryCurrentImg);

            if (categoryCurrentImg == categoriaBuscar) {
                ImagenReport.add(imageObject);
            }

        }

        /*


        for (int i = 0; i < allImagesData.size(); i++) {

           int  categoryCurrentImg=allImagesData.get(i).tipoImagenCategory;
            Log.i("xamil","categoria current imagen es"+categoryCurrentImg);

            if (categoryCurrentImg == categoriaBuscar) {
                imAGESpdfSet.add(allImagesData.get(i));
            }

        }

        Log.i("xamil","el SIZE DE createImagesSet es "+imAGESpdfSet.size());
*/

        return  ImagenReport;


    }



    public static String  devuelveHorientacionImg(Bitmap bitmap){

        //comprobar en que linea ... comprobar la posicion de la ultima

        int ancho = bitmap.getWidth();
        int alto = bitmap.getHeight();

        if(ancho > alto) { //modo 3 imagenes en una linea...
            return "horizontal";
        }else{
            return "vertical";


        }


    }




    public static  void dowloadAllImages(ArrayList<ImagenReport>miLisAllImages){
        //lllamos a este metodo unicamente si la lista es 0....si no

        for(int i = 0; i <miLisAllImages.size() ;i++ ){

             String pathImage =miLisAllImages.get(i).getUniqueIdNamePic();
             int categoYCurrentImg=miLisAllImages.get(i).getTipoImagenCategory();

            StorageReference storageRef = StorageData.rootStorageReference.child("imagenes_all_reports/"+pathImage);

            storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    try {

                        Bitmap  bitmap = Glide.with(ActivityContenedoresPrev.context).asBitmap().load(uri).submit().get();
                        String horientacionImg=devuelveHorientacionImg(bitmap);


                     //   imAGESpdfSetGlobal.add(new ImagenReport(horientacionImg,bitmap,categoYCurrentImg));

                      // Log.i("hamiso","el size de esta lista es "+imAGESpdfSetGlobal.size());

                          ///llamamos a este otro metodo .......



                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                    Log.i("hamiso","es un fallo y es "+exception.getMessage());

                }
            });



        }





    }


    public static  void dowloadAllImages2(ArrayList<ImagenReport>miLisAllImages){
        //lllamos a este metodo unicamente si la lista es 0....si no
        ImagenReportMap=new HashMap<>();

        for(int i = 0; i <miLisAllImages.size() ;i++ ){

            String pathImage =miLisAllImages.get(i).getUniqueIdNamePic();
            int categoYCurrentImg=miLisAllImages.get(i).getTipoImagenCategory();
             String uniqueId=miLisAllImages.get(i).getUniqueIdNamePic();
            String descripcionImage=miLisAllImages.get(i).getDescripcionImagen();

            StorageReference storageRef = StorageData.rootStorageReference.child("imagenes_all_reports/"+pathImage);


            try {
                final File localFile = File.createTempFile("Images", "bmp");
                storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener< FileDownloadTask.TaskSnapshot >() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                        Bitmap  bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        String horientacionImg=devuelveHorientacionImg(bitmap);

                       // ImagenReport imgsObect=new ImagenReport(horientacionImg,bitmap,categoYCurrentImg,uniqueId);
                       // imAGESpdfSetGlobal.add(imgsObect);
                      //  ImagenReportMap.put(uniqueId,imgsObect);



                     //   Log.i("hamiso","el size de esta lista es "+imAGESpdfSetGlobal.size());

                        ///llamamos a este otro metodo .......



                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                       // Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();

        }





    }

    }

    public static  RectF creATErec(int left, int top, int right, int bottom,Paint paint) {
        right = left + right; // width is the distance from left to right
        bottom = top + bottom; // height is the distance from top to bottom
       // canvas.drawRect(left, top, right, bottom, paint);


        // canvas.drawBitmap(imagen, null, dst, null);
        RectF dstx = new RectF(left, top, right , bottom);


        return  dstx;
        //  RectF dstx = new RectF(50, 450, 50 + 500, 50 + 600);
      ///  canvas.drawBitmap(imagen2, null, dst, null);
        // canvas.drawBitmap(imagen2, null, dstx, null);

    }



    public static int buscaPosiblePatronParaOrdenar(ArrayList<ImagenReport> list){

        imagesSetToCurrentFila=new ArrayList<>();


        int contadorImgVertical= 0;
        int contadorImgHorizontal= 0;
        int valorDevolver=0;
        boolean encontramos=false;


       /// int debugFOTOScONTENEDOR =0;

        for(int i=0; i<list.size(); i++){ //primero buscamos tres imagenes  verticales....

                  if(list.get(i).getHorientacionImage().equals("vertical") && ! list.get(i).isEstaENPdf() ){
                      contadorImgVertical++;
                      imagesSetToCurrentFila.add(list.get(i));

                      if(contadorImgVertical==3){
                          valorDevolver= Variables.TRES_IMGS_VERTCLES ;
                          encontramos=true;
                          break;
                      }
                  }

              }


        if(!encontramos) {
            imagesSetToCurrentFila=new ArrayList<ImagenReport>();

            contadorImgVertical= 0;
            for(int i=0; i<list.size(); i++){ ///si no comprobamos que exista una imagen vertical y otra horizontal ...

                if(list.get(i).getHorientacionImage().equals("vertical") && ! list.get(i).isEstaENPdf() ){
                    imagesSetToCurrentFila.add(list.get(i));

                    contadorImgVertical++;

                    if(contadorImgVertical==1 &&  contadorImgHorizontal==1  ){
                           //le cambiamos la ubicacion en caso que lña imagen horizontal este en la posicioon 1
                          //primero la vertical y depues la horizontal
                        if(imagesSetToCurrentFila.get(0).getHorientacionImage().equals("horizontal")){ ///si la primera imagen es horizontal
                             //invertir ubicacion
                            Collections.reverse(imagesSetToCurrentFila);

                        }


                        valorDevolver= Variables.UNAVERTICAL_Y_OTRA_HORIZONTAL ;
                        encontramos=true;

                        break;
                    }
                }





                if(list.get(i).getHorientacionImage().equals("horizontal") && ! list.get(i).isEstaENPdf() ){

                    imagesSetToCurrentFila.add(list.get(i));

                    contadorImgHorizontal++;

                    if(contadorImgVertical==1 &&  contadorImgHorizontal==1  ){

                        if(imagesSetToCurrentFila.get(0).getHorientacionImage().equals("horizontal")){ ///si la primera imagen es horizontal
                            //invertir ubicacion
                            Collections.reverse(imagesSetToCurrentFila);

                        }


                        valorDevolver=Variables.UNAVERTICAL_Y_OTRA_HORIZONTAL ;
                        encontramos=true;

                        break;



                    }
                }


            }

        }


        if(!encontramos) {
            imagesSetToCurrentFila=new ArrayList<ImagenReport>();

            contadorImgVertical= 0;

            for(int i=0; i<list.size(); i++){  ////si no vemos que halla solo dos imagenes verticales  PARA PÓNERFLAS  en el centro

                if(list.get(i).getHorientacionImage().equals("vertical") && ! list.get(i).isEstaENPdf() ){
                    imagesSetToCurrentFila.add(list.get(i));

                    contadorImgVertical++;

                    if(contadorImgVertical==2 ){
                        valorDevolver=Variables.DOS_IMGS_VERTICALES ;
                        encontramos=true;

                        break;

                    }
                }


            }


        }


        if(!encontramos) {
            imagesSetToCurrentFila=new ArrayList<ImagenReport>();

            contadorImgHorizontal= 0;

            for(int i=0; i<list.size(); i++){  ////si no vemos que halla dos horizontales
                if(list.get(i).getHorientacionImage().equals("horizontal") && ! list.get(i).isEstaENPdf() ){
                    imagesSetToCurrentFila.add(list.get(i));

                    contadorImgHorizontal++;

                    if(contadorImgHorizontal==2 ){

                        encontramos=true;

                        valorDevolver= Variables.DOS_HORIZONTALES ;

                        break;
                    }


                }

            }


        }


        if(!encontramos) {
            imagesSetToCurrentFila= new ArrayList<>();

            for(int i=0; i<list.size(); i++){  ////si no vemos que halla dos horizontales
                if(list.get(i).getHorientacionImage().equals("horizontal") && ! list.get(i).isEstaENPdf() ){
                    imagesSetToCurrentFila.add(list.get(i));

                    Log.i("helloheree","ecnonctramos una por aqui ");

                    encontramos=true;

                    valorDevolver= Variables.UNA_HORIZONTAL ;

                    break;

                }

            }


        }


        if(!encontramos) {
            imagesSetToCurrentFila= new ArrayList<>();

            for(int i=0; i<list.size(); i++){  ////si no vemos que halla dos horizontales
                if(list.get(i).getHorientacionImage().equals("vertical") && ! list.get(i).isEstaENPdf() ){
                    imagesSetToCurrentFila.add(list.get(i));

                    Log.i("helloheree","ecnonctramos una por aqui ");

                    encontramos=true;

                    valorDevolver= Variables.UNA_VERTICAL ;

                    break;

                }

            }


        }


        if(!encontramos) {
            //SI NO ENCONTRA,MOPS NADA LE DAMOS ESTE..
            imagesSetToCurrentFila=new ArrayList<ImagenReport>();
            Log.i("PATRONX","NO HEMOS ENCONTRADO NADA ");

            valorDevolver =Variables.DEFAULNO_ENCONTRO_NADA;

        }
            //si no


        return  valorDevolver;
    }


    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }





    public  static Bitmap generateBitmapTumbail(Bitmap bitmap ) {
      //int heigth = bitmap.getWidth();
      //int width= bitmap.getHeight();
         bitmap = Bitmap.createScaledBitmap(bitmap,(int)(bitmap.getWidth()*0.2), (int)(bitmap.getHeight()*0.2), true);



     return      bitmap;
    }




    public static  ArrayList<ImagenReport>   marcaQueNoEstaEnPDF (ArrayList<ImagenReport> list){
        ArrayList<ImagenReport> listx=new ArrayList<>();
        //buscamos esos ids,, y marcamos como usado...
        //vamos atenber dos listas.... una lista que es la litsa del conjunto actual y una lista que dice


        for(int i= 0; i<list.size(); i++){ ///
            listx.add(list.get(i));
            listx.get(i).setEstaENPdf(false);
        }



       return listx;

    }

/*
    BitmapFactory.Options Options = new BitmapFactory.Options();
    Options.inSampleSize = 4;
    Options.inJustDecodeBounds = false;
    action_bitmap = BitmapFactory.decodeFile(Glob.savedImage, Options);


 */



    private void comprimeImg(Bitmap bitmap) {

        BitmapFactory.Options Options = new BitmapFactory.Options();
        Options.inSampleSize = 4;
        Options.inJustDecodeBounds = false;
        //   bitmap = BitmapFactory.decodeFile( , Options);

    }
}
