package com.tiburela.qsercom.utils;

import com.tiburela.qsercom.models.ContenedoresEnAcopio;
import com.tiburela.qsercom.models.DatosDeProceso;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.ImagesToPdf;
import com.tiburela.qsercom.models.PackingListMod;
import com.tiburela.qsercom.models.SetInformDatsHacienda;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;

import java.util.ArrayList;
import java.util.HashMap;

public class Variables {

public static String  paqueteName="com.tiburela.qsercom";


    public static  int   activityCurrent;


    public static String nodoDondeEstaHashMapQueReciensubimos;
    public static final int   FormatDatsContAcopi=10067;
    public static final int   FormaFormularyActivity=10066;
    public static final int   FormPreviewFormularioActivity=10060;
    public static final int   FormatDatsContAcopiPREVIEW=10069;




    public static  int   tipoDeFoto;
    public static final int CREATE_NEW_FORM =1005;
    public static final int   EDIT_FORM=1006;
    public static  int   modoCurren;

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

    public static SetInformDatsHacienda CurrenReportPart3;
    public static ContenedoresEnAcopio CurrenReportContensEnACp;
    public static PackingListMod currenReportPackinList;



    public static String KEYEXTRAPREVIEW= "estacontent";
    public static String KEYEXTRA_CONTEN_EN_ACP= "estacontentXCC";
    public static String KEY_PACKING_LIST= "MIPAQKICN";



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
    public static  int tipoDeUser =CALIFICADOR_OFICINA ;

    public static  final int VERTICAL =1200 ;
    public static  final int HORIZONTAL =12001 ;
    public static   int orientacionImagen ;



    //imagenes pdf ubicaciones POR FILAS
    public static final int  TRES_IMGS_VERTCLES= 2500 ;
    public static final int  UNAVERTICAL_Y_OTRA_HORIZONTAL= 2501 ;
    public static final int  DOS_IMGS_VERTICALES= 2502 ;
    public static final int  DOS_HORIZONTALES= 2503 ;
    public static final int  DEFAULNO_ENCONTRO_NADA= 2504 ;

    public static HashMap<String, DatosDeProceso> mimapaDatosProcesMapCurrent=new HashMap<>();



    public static  ArrayList<ImagesToPdf>listImagesSeccion=new ArrayList<ImagesToPdf>();

}
