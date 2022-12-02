package com.tiburela.qsercom.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.tiburela.qsercom.models.ColorCintasSemns;
import com.tiburela.qsercom.models.ContenedoresEnAcopio;
import com.tiburela.qsercom.models.ControlCalidad;
import com.tiburela.qsercom.models.CuadroMuestreo;
import com.tiburela.qsercom.models.DatosDeProceso;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.ImagesToPdf;
import com.tiburela.qsercom.models.InformRegister;
import com.tiburela.qsercom.models.PackingListMod;
import com.tiburela.qsercom.models.ProductPostCosecha;
import com.tiburela.qsercom.models.ReportCamionesyCarretas;
import com.tiburela.qsercom.models.SetInformDatsHacienda;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;
import com.tiburela.qsercom.models.UsuarioQsercom;

import java.util.ArrayList;
import java.util.HashMap;

public class Variables {

 public static String  KEY_CONTROL_CALIDAD_ATACHEDS="MICTRNLDLD";
 public static String  KEY_CUADRO_MUETREO_ATACHED="MICUDAROR";

 public static CuadroMuestreo cuadroMuestreoVinculado;

 public static String idCuadroMuestreoToDowload ="";

 public static String idControCalidadToDowload ="";




 public static String  paqueteName="com.tiburela.qsercom";

 public static String currentkEYcuadroMuetreo;

   public static FirebaseUser userGoogle;

    public static  ArrayList<ControlCalidad>listReprsVinculads=new ArrayList<>();


    public static UsuarioQsercom usuarioQsercomGlobal;

    public static InformRegister currentInformRegisterSelected;


    public static String  nombreCUrrentUser="";


    public static String currentKeyShareToDelete="";

    public static float currentPoscion;

    public static  int   activityCurrent;

    public static final  int   PROCESO_FRUT_IN_FINCA=13000;

    public static String nodoDondeEstaHashMapQueReciensubimos;


    public static final int   FormatDatsContAcopi=10067;
    public static final int   FormatDatsContAcopiPREVIEW=10069;
    public static final int   FormCamionesyCarretasActivity=10070;
    public static final int   FormCamionesyCarretasActivityPreview=10071;

    public static final int   FormContenedores =10066;
    public static final int   FormPreviewContenedores =10060;
    public static final int   FormCantrolCalidad=10072;
    public static final int   FormPackingList=10073;
    public static final int   FormMuestreoRechaz=10074;

    public static   ProductPostCosecha productsGlobal;



    public static final int   FOTO_LLEGADA=1;
    public static final int   FOTO_PROD_POSTCOSECHA=2;
    public static final int   FOTO_TRANSPORTISTA=3;
    public static final int   FOTO_SELLO_LLEGADA=4;
    public static final int   FOTO_CONTENEDOR=58;

    public static int typeoFdeleteImg=0;
    public static final  int   MINIMO_FOTOS_ALL_CATEGORY=1;



    public static final int   HOY=600;
    public static final int   AYER=601;
    public static final int   ANTEAYER=602;
    public static final int   ESPECIFIC_DATE=603;

    public static SetInformEmbarque1 CurrenReportPart1;
    public static SetInformEmbarque2 CurrenReportPart2;

    public static SetInformDatsHacienda CurrenReportPart3;
    public static ContenedoresEnAcopio CurrenReportContensEnACp;
    public static PackingListMod currenReportPackinList;
    public static ReportCamionesyCarretas currenReportCamionesyCarretas;
    public static    ColorCintasSemns  currentColorCIntas;
    public static CuadroMuestreo currentcuadroMuestreo;
    public static ControlCalidad currenControlCalReport;


    public static String KEYEXTRAPREVIEW= "estacontent";
    public static String KEYEXTRA_CONTEN_EN_ACP= "estacontentXCC";
    public static String KEY_PACKING_LIST= "MIPAQKICN";
    public static String KEY_PDF_MAKER= "MIPODFMAKER";
    public static String KEY_PDF_MAKER_PDF_NAME= "MIPODFMAKER_PDF_NAME";

    public static String nodoDondeEstaraPesoBruto2y3l;


    public static final int  DOWLOAD_IMAGES= 1500 ;
    public static final int  SELEC_AND_TAKE_iMAGES= 1501 ;

    public static  int  modoRecicler=0 ;

    public static final int MODO_EDICION=414 ;
    public static final int MODO_VISUALIZACION=415 ;

    public static boolean isClickable = true;

    public static boolean VienedePreview = false;
    public static boolean copiamosData = false;


    public static ArrayList<ImagenReport> listImagenDataGlobalCurrentReport;


   public static  HashMap<String, ImagenReport> hashMapImagesStart=new HashMap<>();


    public static ArrayList<String> listImagesToDelete; //alamcenar los unicques id para elimnarlos


    public static final int  CALIFICADOR_CAMPO= 1700 ;
    public static final int  CALIFICADOR_OFICINA= 1701 ;
    public static  int tipoDeUser =CALIFICADOR_OFICINA ;

    public static   int orientacionImagen ;
    public static ProductPostCosecha currenProductPostCosecha;


    //imagenes pdf ubicaciones POR FILAS
    public static final int  TRES_IMGS_VERTCLES= 2500 ;
    public static final int  UNAVERTICAL_Y_OTRA_HORIZONTAL= 2501 ;
    public static final int  DOS_IMGS_VERTICALES= 2502 ;
    public static final int  DOS_HORIZONTALES= 2503 ;
    public static final int  DEFAULNO_ENCONTRO_NADA= 2504 ;
    public static final int  UNA_HORIZONTAL= 2505 ;
    public static final int  UNA_VERTICAL= 2506 ;


    public static boolean   ES_ULTIMA_IMAGEN_Y_PRIMERA_PAGINA= true ;





    public static HashMap<String, DatosDeProceso> mimapaDatosProcesMapCurrent=new HashMap<>();

    public static HashMap<String, ColorCintasSemns> mapColorCintasSemanas=new HashMap<>();


    public static  ArrayList<ImagesToPdf>listImagesSeccion=new ArrayList<ImagesToPdf>();

    public static HashMap<String, String> currentMapPreferences=new HashMap<>();

    public static boolean hayUnFormIncompleto =false;
    public static boolean hayUnFormIcompletoTOrEYCLER =false;


    public static int currentFormSelect=0;


    public static Context contexto;
    public static Activity activity;

}
