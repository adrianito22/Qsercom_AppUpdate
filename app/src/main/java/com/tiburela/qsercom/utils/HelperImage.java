package com.tiburela.qsercom.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import com.tiburela.qsercom.models.ImagenReport;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class HelperImage {
   public static  ArrayList<ImagenReport> imAGESpdfSetGlobal=new ArrayList<ImagenReport>();

     public static int indiceValues=0;
   public static  ArrayList<ImagenReport> imagesSetToCurrentFila;

   public static  HashMap<String, ImagenReport> ImagenReportMap;




    public static ArrayList<ImagenReport> getImagesWhitthisCATEGORYz( HashMap<String, ImagenReport >allImagesData, int categoriaBuscar) {

        ArrayList<ImagenReport> listImagesReport = new ArrayList<>();


        for (ImagenReport imageObject: allImagesData.values()) {

            int  categoryCurrentImg=imageObject.getTipoImagenCategory();
            Log.i("xamil","categoria current imagen es"+categoryCurrentImg);

            Log.i("xamil","el uri es "+imageObject.getUrlStoragePic());



            if (categoryCurrentImg == categoriaBuscar) {
                listImagesReport.add(imageObject);
            }

        }





        return  listImagesReport;


    }
    



    public static String  devuelveHorientacionImg(Bitmap bitmap){

        //comprobar en que linea ... comprobar la posicion de la ultima

        int ancho = bitmap.getWidth();
        int alto = bitmap.getHeight();

        Log.i("cuandoexecuta","el ancho es "+ancho);
        Log.i("cuandoexecuta","el alto es "+alto);


        if(ancho > alto) { //modo 3 imagenes en una linea...
            return "horizontal";
        }else{
            return "vertical";


        }


    }
    public static Bitmap rotate(Bitmap in, int angle) {
        Matrix mat = new Matrix();
        mat.postRotate(angle);
        return Bitmap.createBitmap(in, 0, 0, in.getWidth(), in.getHeight(), mat, true);
    }



    public static int getOrientation(Uri selectedImage, Context context) {
        int orientation = 0;
        final String[] projection = new String[]{MediaStore.Images.Media.ORIENTATION};
        final Cursor cursor = context.getContentResolver().query(selectedImage, projection, null, null, null);
        if(cursor != null) {
            final int orientationColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.ORIENTATION);
            if(cursor.moveToFirst()) {
                orientation = cursor.isNull(orientationColumnIndex) ? 0 : cursor.getInt(orientationColumnIndex);
            }
            cursor.close();
        }
        return orientation;
    }



    /*
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
            contadorImgHorizontal=0;
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

            for(int i=0; i<list.size(); i++){  ////una vertical
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
*/

static boolean yaLlamo=false;



    public static Bitmap ensureCorrectRotation( Uri uri, Bitmap bitmap) {

        int degrees = getExifRotation(uri);
        Log.i("cuandoexecuta","la  oriemntacioion deheres es sss "+degrees);

        if (degrees != 0) {
            Matrix matrix = new Matrix();
            matrix.preRotate(degrees);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
            return bitmap;
          //  return transformBitmap(bitmap, matrix);
        }


        return bitmap;
    }





    public static int getExifRotation(Uri imgPath)
    {
        try
        {
            ExifInterface exif = new ExifInterface(imgPath.getPath());
            String rotationAmount = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
            if (!TextUtils.isEmpty(rotationAmount))
            {
                int rotationParam = Integer.parseInt(rotationAmount);
                switch (rotationParam)
                {
                    case ExifInterface.ORIENTATION_NORMAL:
                        return 0;
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        return 90;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        return 180;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        return 270;
                    default:
                        return 0;
                }
            }
            else
            {
                return 0;
            }
        }
        catch (Exception ex)
        {
            return 0;
        }
    }




    public static int buscaPosiblePatronParaOrdenar(ArrayList<ImagenReport>list){

        imagesSetToCurrentFila= new ArrayList<>();

       //  indiceValues++;

        if(!yaLlamo){

            for(ImagenReport incm: list){
                Log.i("misdtaurl ","num value es "+incm.getSortPositionImage()+" el url es "+incm.getUrlStoragePic());

            }
            yaLlamo=true;

        }


        Log.i("PATRONX","el indice es : "+indiceValues);


        ///no aseguramos que exista....
        if(indiceValues+2 <list.size() && list.get(indiceValues).getHorientacionImage().equals("vertical") &&  //imagem,imagen2,imagen3,imagen4,imagen5,imagen6 //chekeamos que exista este indice
            list.get(indiceValues+1).getHorientacionImage().equals("vertical")&& list.get(indiceValues+2).getHorientacionImage().equals("vertical")){
            //imagen

            imagesSetToCurrentFila.add(list.get(indiceValues));
            imagesSetToCurrentFila.add(list.get(indiceValues+1));
            imagesSetToCurrentFila.add(list.get(indiceValues+2));
            indiceValues=indiceValues+3;

              return  Variables.TRES_IMGS_VERTCLES;
        }


         /**chekeamos si no son dos verticales*/

        if(indiceValues+1 <list.size() && list.get(indiceValues).getHorientacionImage().equals("vertical")
                &&  list.get(indiceValues+1).getHorientacionImage().equals("vertical")){
            //imagen

            imagesSetToCurrentFila.add(list.get(indiceValues));
            imagesSetToCurrentFila.add(list.get(indiceValues+1));
            indiceValues=indiceValues+2;

            return  Variables.DOS_IMGS_VERTICALES;
        }




        /**chekeamos si no son dos HORIOZONTALES*/

        if(indiceValues+1 <list.size() && list.get(indiceValues).getHorientacionImage().equals("horizontal") &&  //imagem,imagen2,imagen3,imagen4,imagen5,imagen6 //chekeamos que exista este indice
                list.get(indiceValues+1).getHorientacionImage().equals("horizontal")){
            //imagen




                    Log.i("PATRONX","lña primera imagen ur es "+list.get(indiceValues).getUrlStoragePic());
            Log.i("PATRONX","lña primera imagen ur es "+list.get(indiceValues+1).getUrlStoragePic());


            imagesSetToCurrentFila.add(list.get(indiceValues));
            imagesSetToCurrentFila.add(list.get(indiceValues+1));
            indiceValues=indiceValues+2;

            return  Variables.DOS_HORIZONTALES;
        }



        /**chekeamos si la primera es vertical y la otroa horizontal*/

        if(indiceValues+1 <list.size() && list.get(indiceValues).getHorientacionImage().equals("vertical") &&  //imagem,imagen2,imagen3,imagen4,imagen5,imagen6 //chekeamos que exista este indice
                list.get(indiceValues+1).getHorientacionImage().equals("horizontal")){
            //imagen

            imagesSetToCurrentFila.add(list.get(indiceValues));
            imagesSetToCurrentFila.add(list.get(indiceValues+1));
            indiceValues=indiceValues+2;

            return  Variables.UNAVERTICAL_Y_OTRA_HORIZONTAL;
        }



        /**chekeamos si la primera es HORIZONTAL y la otroa VERTICAL*/

        if(indiceValues+1  <list.size()&& list.get(indiceValues).getHorientacionImage().equals("horizontal") &&  //imagem,imagen2,imagen3,imagen4,imagen5,imagen6 //chekeamos que exista este indice
                list.get(indiceValues+1).getHorientacionImage().equals("vertical")){
            //imagen

            imagesSetToCurrentFila.add(list.get(indiceValues));
            imagesSetToCurrentFila.add(list.get(indiceValues+1));
            indiceValues=indiceValues+2;

            return  Variables.UNAHORIZONTAL_Y_OTRA_VERTICAL;
        }



        /**UNA VERTICAL*/

        if(indiceValues <list.size() && list.get(indiceValues).getHorientacionImage().equals("vertical")   //imagem,imagen2,imagen3,imagen4,imagen5,imagen6 //chekeamos que exista este indice
               ){
            //imagen

            imagesSetToCurrentFila.add(list.get(indiceValues));
            indiceValues=indiceValues+1;



            return  Variables.UNA_VERTICAL;
        }



        /**UNA HORIZONTAL*/

        if(indiceValues <list.size()&& list.get(indiceValues).getHorientacionImage().equals("horizontal")   //imagem,imagen2,imagen3,imagen4,imagen5,imagen6 //chekeamos que exista este indice
                ){
            //imagen

            imagesSetToCurrentFila.add(list.get(indiceValues));
            indiceValues=indiceValues+1;

            return  Variables.UNA_HORIZONTAL;
        }



        //SI NO ENCONTRA,MOPS NADA LE DAMOS ESTE..
        imagesSetToCurrentFila=new ArrayList<ImagenReport>();
        Log.i("PATRONX","NO HEMOS ENCONTRADO NADA ");
        indiceValues=indiceValues+1;

        return  Variables.DEFAULNO_ENCONTRO_NADA;





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

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix,
                true);
    }

    public static Bitmap handleSamplingAndRotationBitmap(Context context, Uri selectedImage)
            throws IOException {
        int MAX_HEIGHT = 1024;
        int MAX_WIDTH = 1024;

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        InputStream imageStream = context.getContentResolver().openInputStream(selectedImage);
        BitmapFactory.decodeStream(imageStream, null, options);
        imageStream.close();

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, MAX_WIDTH, MAX_HEIGHT);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        imageStream = context.getContentResolver().openInputStream(selectedImage);
        Bitmap img = BitmapFactory.decodeStream(imageStream, null, options);

        img = rotateImageIfRequired(context, img, selectedImage);
        return img;
    }

    private static Bitmap rotateImageIfRequired(Context context, Bitmap img, Uri selectedImage) throws IOException {

        InputStream input = context.getContentResolver().openInputStream(selectedImage);
        ExifInterface ei;
            ei = new ExifInterface(input);

        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotateImage(img, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateImage(img, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateImage(img, 270);
            default:
                return img;
        }
    }


}
