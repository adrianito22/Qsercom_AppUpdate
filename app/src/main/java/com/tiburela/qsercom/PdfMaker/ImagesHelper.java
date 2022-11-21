package com.tiburela.qsercom.PdfMaker;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.ImagesToPdf;
import com.tiburela.qsercom.utils.HelperImage;
import com.tiburela.qsercom.utils.Variables;

import java.util.ArrayList;

public class ImagesHelper {

   public  static ArrayList<ImagenReport> currentListImagesSeccion;





    //usamos un bucle while mientras esta imagen no este


    private static void createPages_addImgs(Context context, String anexoNombre){
        //obteine las imagenes por secciones....
        //primero llegada... add imagenes llegada....
        //add imagenes otras.. le agregamos el tipo.....de imagen....
        ///yPosicion creamos un array con las imagenes de ahora del la seccion a la que pertenece...
        //yPosicion mientras no esten en el pdf seguimos iterando...
        ////no vamos a ota pagina..


        int debuGcONTADOR =0;
        Log.i("contaburx","el size de la lista es "+currentListImagesSeccion.size());

        //nos asegurammos que ninguna este en el pdf aun ,LAS DESMARCAMOS
        currentListImagesSeccion= HelperImage.marcaQueNoEstaEnPDF(currentListImagesSeccion);
       // Log.i("contaburx","la IMAGEN DE LA 1  POSCION esta en el pdf? "+currentListImagesSeccion.get(0).estaENPdf);
        //Log.i("contaburx","la IMAGEN DE LA 2 POSCION esta en el pdf? "+currentListImagesSeccion.get(1).estaENPdf);


        /*
        while(!allImagesISUsed(currentListImagesSeccion)){ //mientras quedan imagenes si usar////

            if(debuGcONTADOR==0) { ///significa que es la primera pagina

                Paint paintIzquierda2= new Paint();
                paintIzquierda2.setTextAlign(Paint.Align.LEFT);
                paintIzquierda2.setColor(Color.parseColor("#4CAF50"));
                paintIzquierda2.setTextSize(20);
                //le agregamos el titulo al anexo
                currentCanvasObjec.drawText(anexoNombre, 210, 170, paintIzquierda2);
            }

            debuGcONTADOR++;
            //si es la primera pagina

            Log.i("contaburx","se ejecuto ESto "+debuGcONTADOR+" veces");
            int patronEncontrado=HelperImage.buscaPosiblePatronParaOrdenar(currentListImagesSeccion);


            if(patronEncontrado== Variables.TRES_IMGS_VERTCLES){
                wehaveAddSpaceyIndescrip =false;
                Log.i("contaburx","es Variables.TRES_IMGS_VERTCLES");
                addImagenSet(Variables.TRES_IMGS_VERTCLES,context);
                //ENTONCES ESTOS ENCONTRADOS LOS PONEMOS QUE YA SE USARON.....


            }

            else if(patronEncontrado == Variables.DOS_IMGS_VERTICALES) {
                Log.i("contaburx","es el DOS_IMGS_VERTICALES ");
                wehaveAddSpaceyIndescrip =false;

                addImagenSet(Variables.DOS_IMGS_VERTICALES,context);


            }

            else if(patronEncontrado == Variables.UNAVERTICAL_Y_OTRA_HORIZONTAL) {
                Log.i("contaburx","es  UNAVERTICAL_Y_OTRA_HORIZONTAL");
                wehaveAddSpaceyIndescrip =false;

                addImagenSet(Variables.UNAVERTICAL_Y_OTRA_HORIZONTAL,context);


            }


            else if(patronEncontrado == Variables.DOS_HORIZONTALES) {
                Log.i("contaburx","es el DOS_HORIZONTALES ");
                wehaveAddSpaceyIndescrip =false;

                addImagenSet(Variables.DOS_HORIZONTALES,context);


            }



            else if(patronEncontrado == Variables.DEFAULNO_ENCONTRO_NADA) {
                Log.i("contaburx","es el  DEFAULNO_ENCONTRO_NADA");
                wehaveAddSpaceyIndescrip =false;

                addImagenSet(Variables.DEFAULNO_ENCONTRO_NADA,context);


            }







        }

*/





    }
    private static boolean allImagesISUsed(ArrayList<ImagesToPdf> list){
        int contadorIMagesUsadas=0;


        for(int indice=0; indice<list.size(); indice++){

            if(list.get(indice).estaENPdf){

                contadorIMagesUsadas++;



            }else{

                break;
            }

            //primero si podemos poner las 3 primeras imagenes....
            //comprobamos que existan al menos 3 imagenes


        }

        Log.i("contabur","hay "+contadorIMagesUsadas +" imagenes usadas");


        if(contadorIMagesUsadas== list.size() || contadorIMagesUsadas== list.size()-1 ){ //o si solo queda 1

            return true;
        }else{
            return false;

        }
    }

}
