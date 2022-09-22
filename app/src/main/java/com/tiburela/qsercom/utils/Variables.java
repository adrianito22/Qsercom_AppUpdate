package com.tiburela.qsercom.utils;

import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;

import java.util.ArrayList;
import java.util.HashMap;

public class Variables {

public static String  paqueteName="com.tiburela.qsercom";

    public static  int   tipoDeFoto;

    public static final int   FOTO_LLEGADA=1;
    public static final int   FOTO_PROD_POSTCOSECHA=2;
    public static final int   FOTO_TRANSPORTISTA=3;
    public static final int   FOTO_SELLO_LLEGADA=4;
    public static final int   FOTO_CONTENEDOR=58;

    public static int typeoFdeleteImg=0;
    public static final  int   MINIMO_FOTOS_ALL_CATEGORY=1;


    public static final int   FIRST_HEADER_OF_TABLE=5018;
    public static final int   TABLE_PRODUCTOS_POSTCOSECHA=5019;
    public static final int   TABLE_DATOS_PROCESO=5020;
    public static final int   TABLE_DATOS_TRANSPORTISTA=5021;
    public static final int   TABLE_SELLOS_LLEGADA=5022;
    public static final int   DATOS_DEL_CONTENEDOR=5023;
    public static final int   TABLE_CALIB_FRUTS_CLD_ENFUN=5024;
    public static final int   TABLE_CONTROL_DE_GANCHO=5025;
    public static final int   TABLE_DATOS_HACIENDA=5026;


    public static final int   HOY=600;
    public static final int   AYER=601;
    public static final int   ANTEAYER=602;
    public static final int   ESPECIFIC_DATE=603;

    public static SetInformEmbarque1 CurrenReportPart1;

    public static SetInformEmbarque2 CurrenReportPart2;


    public static String KEYEXTRAPREVIEW= "estacontent";



    public static final int  DOWLOAD_IMAGES= 1500 ;
    public static final int  SELEC_AND_TAKE_iMAGES= 1501 ;

    public static  int  modoRecicler=0 ;

    public static final int MODO_EDICION=414 ;
    public static final int MODO_VISUALIZACION=415 ;

    public static boolean isClickable = true;

    public static boolean VienedePreview = false;
    public static boolean copiamosData = false;


    public static ArrayList<ImagenReport> listImagenData;


   public static  HashMap<String, ImagenReport> hashMapImagesStart;


    public static ArrayList<String> listImagesToDelete; //alamcenar los unicques id para elimnarlos


    public static final int  CALIFICADOR_CAMPO= 1700 ;
    public static final int  CALIFICADOR_OFICINA= 1701 ;
    public static  int  tipOuSER =CALIFICADOR_CAMPO ;

    public static  final int VERTICAL =1200 ;
    public static  final int HORIZONTAL =12001 ;
    public static   int orientacionImagen ;

}
