package com.tiburela.qsercom.activities.formulariosPrev;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;
import static android.view.View.GONE;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.icu.text.DecimalFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.activities.formularios.ActivityReporteCalidadCamionesyCarretas;
import com.tiburela.qsercom.adapters.RecyclerViewAdapter;
import com.tiburela.qsercom.auth.Auth;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.dialog_fragment.DialogConfirmChanges;
import com.tiburela.qsercom.models.CalibrFrutCalEnf;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.ProductPostCosecha;
import com.tiburela.qsercom.models.PromedioLibriado;
import com.tiburela.qsercom.models.ReportCamionesyCarretas;
import com.tiburela.qsercom.storage.StorageData;
import com.tiburela.qsercom.utils.FieldOpcional;
import com.tiburela.qsercom.utils.HelperEditAndPreviewmode;
import com.tiburela.qsercom.utils.HelperImage;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class PreviewCalidadCamionesyCarretas extends AppCompatActivity implements View.OnClickListener  {

    TextInputEditText ediNombreRevisa;
    TextInputEditText ediCodigoRevisa;

    ImageView imgVAtachProcesoFrutaFinca;
    ImageView imbTakePicProcesoFrutaFinca;
  //  ImageView imgVAtachLlegadaContenedor;
   // ImageView imbTakePicLllegadaContenedor;
  //  ImageView imgVAtachSellosLlegada;
  //  ImageView imbTakePicSellosLlegada;
    // ImageView imgVAtachPuertaAbiertaContenedor;
   // ImageView imbTakePicPuertaAbiertaContenedor;
 //   ImageView imgVAtachFotosPallet;
  //  ImageView imbTakePicPallet;
    ImageView imgVAtachCierreContenedor;
    ImageView imbTakePicCierreContenedor;

    ImageView imgVAtachDocumentacionss;
    ImageView imbTakePicDocuementacionxx;

    private TextInputEditText ediExportadoraProcesada;
    private TextInputEditText ediExportadoraSolicitante;
   private TextInputEditText ediMarca;
    private TextInputEditText ediClienteNombreReporte;

    private static final int PERMISSION_REQUEST_CODE=100;
    private String UNIQUE_ID_iNFORME;
    boolean hayUnformularioIcompleto ;
    public static Context context;
    int contadorIterador=0;
    ProductPostCosecha productxGlobal;
    CalibrFrutCalEnf calEnfundeGLOB;
    ImageView imgUpdatecAlfrutaEnfunde;

    FloatingActionButton fab2;
    private final int CODE_TWO_PERMISIONS = 12;
    LinearLayout layoutPesobrutoPorClusterSolo;

    boolean isModEdicionFields;
    HashMap<String,Float> hasmapPesoBrutoClosters2y3L;
    private int currentTypeImage=0;
    ProgressBar progressBarFormulario;

    TextInputEditText ediSemana;
    TextInputEditText ediFecha;
    TextInputEditText ediProductor;
    TextInputEditText ediHacienda;
    TextInputEditText ediCodigo;
    TextInputEditText ediInscirpMagap;
    TextInputEditText ediPemarque;
    TextInputEditText ediZona;
    TextInputEditText ediHoraInicio;
    TextInputEditText ediHoraTermino;
    TextInputEditText ediNguiaRemision;
    TextInputEditText edi_nguia_transporte;
    TextInputEditText ediNtargetaEmbarque;
    TextInputEditText ediNhojaEvaluacion;
    TextInputEditText ediObservacion;
    TextInputEditText ediEmpacadora;
    TextInputEditText ediContenedor;
    TextInputEditText ediPPC01;
    TextInputEditText ediPPC02;
    TextInputEditText ediPPC03;
    TextInputEditText ediPPC04;
    TextInputEditText ediPPC05;
    TextInputEditText ediPPC06;
    TextInputEditText ediPPC07;
    TextInputEditText ediPPC08;
    TextInputEditText ediPPC09;
    TextInputEditText ediPPC010;
    TextInputEditText ediPPC011;
    TextInputEditText ediPPC012;
    TextInputEditText ediPPC013;
    TextInputEditText ediPPC014;
    TextInputEditText ediPPC015;
    TextInputEditText ediPPC016;
    TextInputEditText ediNViaje;
    TextInputEditText ediEnsunchado;
    TextInputEditText ediBalanzaRepeso;


    TextInputEditText ediCandadoQsercom;
    TextInputEditText ediBalanza;
    TextInputEditText ediFuenteAgua;
    TextInputEditText ediAguaCorrida;
    TextInputEditText ediLavadoRacimos;
    TextInputEditText ediFumigacionClin1;
    TextInputEditText ediTipoBoquilla;
    TextInputEditText ediCajasProcDesp;
    TextInputEditText ediRacimosCosech;
    TextInputEditText ediRacimosRecha;
    TextInputEditText ediRacimProces;


    TextInputEditText ediNombreChofer;
    TextInputEditText ediCedula;
    TextInputEditText ediCelular;
    TextInputEditText ediPLaca;

    TextInputEditText ediCondicionBalanza;
    TextInputEditText ediTipodeCaja;
    TextInputEditText ediTipoPlastico;
    TextInputEditText ediTipoBalanza;
    TextInputEditText editipbalanzaRepeso;
    TextInputEditText ediUbicacionBalanza;

    TextInputEditText ediUbicacion1;
    TextInputEditText ediRuma1;
    TextInputEditText ediTermofrafo2;
    TextInputEditText ediHoraEncendido2;
    TextInputEditText ediUbicacion2;


    TextInputEditText ediExtCalid;
    TextInputEditText ediExtRodillo;
    TextInputEditText ediExtGancho;

    TextInputEditText ediExtCalidCi;
    TextInputEditText ediExtRodilloCi;
    TextInputEditText ediExtGanchoCi;

    TextInputEditText p2pbCluster01;


    LinearLayout linLayoutHeader1;
    LinearLayout linLayoutHeader2;
    LinearLayout linLayoutHeader3;
    LinearLayout linLayoutHeader4;
    LinearLayout linLayoutHeader5;
    LinearLayout linLayoutHeader6;
    LinearLayout linLayoutHeader7;
    LinearLayout linLayoutHeader8;
    LinearLayout lyEscontenedor;


    Spinner spinnerSelectZona;
    Spinner spinnerCondicionBalanza;
    Spinner spinnertipoCaja;
    Spinner spinnertipodePlastico;
    Spinner spinnertipodeBlanza ;
    Spinner spinnertipodeBlanzaRepeso ;
   Spinner spinnerubicacionBalanza ;

    Spinner spFuenteAgua ;
    Spinner spFumigaCorL1 ;
    Spinner spTipoBoquilla ;
    Spinner spinnerCandadoQsercon;


    Switch switchHaybalanza;
    Switch switchHayEnsunchado;
    Switch switchBalanzaRep;
    Switch switchLavdoRacimos;
    Switch swAguaCorrida;




    ActivityResultLauncher activityResultLauncher;
    Uri cam_uri;
    ArrayList<View> listViewsClickedUser;



    @Override
    protected void onStart() {
        super.onStart();

        Auth.initAuth(PreviewCalidadCamionesyCarretas.this);
        Auth.signInAnonymously(PreviewCalidadCamionesyCarretas.this);

        RealtimeDB.initDatabasesRootOnly();



        if(hayUnformularioIcompleto){

            TextInputEditText [] arrayEditex =creaArryOfTextInputEditText();

           // Utils.addDataOfPrefrencesInView(arrayEditex);

            Map<String, ImagenReport> mapImagesReport = Utils.loadMapiMAGEData(PreviewCalidadCamionesyCarretas.this);


            ArrayList<ImagenReport> listImagesToSaVE = new ArrayList<ImagenReport>(mapImagesReport.values());


            //if el formulario no es nulo

            if(listImagesToSaVE!=null ) {

                addInfotomap(listImagesToSaVE);
                createlistsForReciclerviewsImages(listImagesToSaVE);

            }





        }



        // Check if user is signed in (non-null) and update UI accordingly.
        // FirebaseUser currentUser = Auth.mAuth.getCurrentUser();
        //  updateUI(currentUs bver)

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_calidad_camio_carret_pewv);
        context=getApplicationContext();

        Variables.copiamosData=false;

        Variables.activityCurrent=Variables.FormCamionesyCarretasActivityPreview;

        ImagenReport.hashMapImagesData=new HashMap<>();

       // hideSomeElemtosAnexosAndChangeValues();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            hayUnformularioIcompleto = extras.getBoolean("ActivitymenuKey");

            //The key argument here must match that used in the other activity
        }


        UNIQUE_ID_iNFORME=Variables.currenReportCamionesyCarretas.getUniqueIDinforme(); /// AQUI OBTENEMOS EL ID



        Log.i("latypeimage","es succces bien "+UNIQUE_ID_iNFORME);


        Auth.initAuth(this);

      //  StorageData. initStorageReference();


        findViewsIds();
        ocultaoTherVIEWs();
        configCertainSomeViewsAliniciar();
        listViewsClickedUser=new ArrayList<>();

        addClickListeners();
        resultatachImages();
        listennersSpinners();

        eventCheckdata();
        //creaFotos();
        checkModeVisualitY();

    }





    void showingTimePicker( View vista){


        final Calendar cldr = Calendar.getInstance();
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        // time picker dialog
        TimePickerDialog  picker = new TimePickerDialog(PreviewCalidadCamionesyCarretas.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int sHour, int sMinute) {

                        String minutes=String.valueOf(sMinute);

                        String AM_PM ;

                        if(sHour < 12) {
                            AM_PM = "AM";
                        } else {
                            AM_PM = "PM";
                        }

                        if(minutes.equals("0")){
                            minutes="00";
                        }



                        if(vista.getId()==R.id.ediHoraInicio) {
                            ediHoraInicio.setText(sHour + ":" + minutes+" "+ AM_PM);


                        }


                        else if (vista.getId()== R.id.ediHoraTermino) {
                            ediHoraTermino.setText(sHour + ":" + minutes+" "+ AM_PM);


                        }






                        else if (vista.getId()== R.id.ediHoraEncendido2) {
                            ediHoraEncendido2.setText(sHour + ":" + minutes+" "+ AM_PM);


                        }



                    }
                }, hour, minutes, true);

        picker.setButton(DialogInterface.BUTTON_POSITIVE, "OK", picker);
        picker.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", picker);

        picker.show();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    void selecionaFecha(){


        final Calendar cldr = Calendar.getInstance();
        int year = cldr.get(Calendar.YEAR);
        int daySemana = cldr.get(Calendar.DAY_OF_WEEK);
        int mes = cldr.get(Calendar.MONTH);

        // time picker dialog
        DatePickerDialog  picker = new DatePickerDialog(PreviewCalidadCamionesyCarretas.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        String dateSelec=i2+"/"+(i1+1)+"/"+i;


                        ediFecha.setText(dateSelec);

                    }
                }, year,  mes, daySemana);

        picker.setButton(DialogInterface.BUTTON_POSITIVE, "OK", picker);
        picker.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", picker);


        picker.show();
    }


    private void disableEditText(EditText editText) {

        // editText.setFocusable(false);
        // editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
        //  editText.setBackgroundColor(Color.TRANSPARENT);
    }



    private void configCertainSomeViewsAliniciar( ) { //configuraremos algos views al iniciar
          ///ocutamos otro views
        lyEscontenedor.setVisibility(LinearLayout.GONE);


      //   LinearLayout lyUbicacionBalanza=findViewById(R.id.lyUbicacionBalanza);
       // lyUbicacionBalanza.setVisibility(LinearLayout.GONE);

        disableEditText(ediFecha);
        disableEditText(ediHoraInicio);
        disableEditText(ediHoraTermino);


        disableEditText(ediContenedor);

        disableEditText(ediZona);
        disableEditText(ediEnsunchado);
        disableEditText(ediBalanzaRepeso);



    }

    public void scroollElementoFaltante(View vistFocus){

        // View targetView = findViewById(R.id.DESIRED_VIEW_ID);
        vistFocus.getParent().requestChildFocus(vistFocus,vistFocus);



    }
    private void  showToast(){


        Toast.makeText(PreviewCalidadCamionesyCarretas.this, "Falta Imagen", Toast.LENGTH_SHORT).show();

    }

    boolean cehckFaltanImagenes() {


        if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_PROCESO_FRUTA_FINCA)){

            TextView ediFotoProcesoEnFruta=findViewById(R.id.ediFotoProcesoEnFruta);
            ediFotoProcesoEnFruta.requestFocus();
            scroollElementoFaltante(ediFotoProcesoEnFruta);
            showToast();

            return false;
        }else{
            TextView ediFotosSellosInstalados=findViewById(R.id.ediFotoProcesoEnFruta);
            ediFotosSellosInstalados.clearFocus();
            ediFotosSellosInstalados.setError(null);
        }




        if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_CIERRE_CONTENEDOR)){

            TextView ediFotoProcesoEnFruta=findViewById(R.id.txtCierreContenedor);
            ediFotoProcesoEnFruta.requestFocus();
            scroollElementoFaltante(ediFotoProcesoEnFruta);
            showToast();

            return false;
        }else{

            TextView ediFotosSellosInstalados=findViewById(R.id.txtCierreContenedor);
            ediFotosSellosInstalados.clearFocus();
            ediFotosSellosInstalados.setError(null);

        }




        if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_DOCUMENTACION)){
            TextView ediFotoProcesoEnFruta=findViewById(R.id.txtFotosDocumentacion);
            ediFotoProcesoEnFruta.requestFocus();
            scroollElementoFaltante(ediFotoProcesoEnFruta);
            showToast();

            return false;
        }else{

            TextView ediFotosSellosInstalados=findViewById(R.id.txtFotosDocumentacion);
            ediFotosSellosInstalados.clearFocus();
            ediFotosSellosInstalados.setError(null);

        }

        return true;


    }



    private void findViewsIds( ) { //configuraremos algos views al iniciar

        ediNombreRevisa=findViewById(R.id.ediNombreRevisa);
        ediCodigoRevisa=findViewById(R.id.ediCodigoRevisa);


        ediExportadoraProcesada=findViewById(R.id.ediExportadoraProcesada);
        ediExportadoraSolicitante=findViewById(R.id.ediExportadoraSolicitante);
        ediMarca=findViewById(R.id.ediMarca);
        ediClienteNombreReporte=findViewById(R.id.ediClienteNombreReporte);

        imgVAtachProcesoFrutaFinca=findViewById(R.id.imgVAtachProcesoFrutaFinca);
        imbTakePicProcesoFrutaFinca=findViewById(R.id.imbTakePicProcesoFrutaFinca);

       /*
        imgVAtachLlegadaContenedor = findViewById(R.id.imgVAtachLlegadaContenedor);
        imbTakePicLllegadaContenedor= findViewById(R.id.imbTakePicLllegadaContenedor);
        imgVAtachSellosLlegada= findViewById(R.id.imgVAtachSellosLlegada);
        imbTakePicSellosLlegada= findViewById(R.id.imbTakePicSellosLlegada);
        imgVAtachPuertaAbiertaContenedor= findViewById(R.id.imgVAtachPuertaAbiertaContenedor);
        imbTakePicPuertaAbiertaContenedor= findViewById(R.id.imbTakePicPuertaAbiertaContenedor);
        imgVAtachFotosPallet= findViewById(R.id.imgVAtachFotosPallet);
        imbTakePicPallet= findViewById(R.id.imbTakePicPallet);
       */

        imgVAtachCierreContenedor= findViewById(R.id.imgVAtachCierreContenedor);
        imgVAtachDocumentacionss = findViewById(R.id.imgVAtachDocumentacionss);
        imbTakePicCierreContenedor= findViewById(R.id.imbTakePicCierreContenedor);
        imbTakePicDocuementacionxx = findViewById(R.id.imbTakePicDocuementacionxx);


        fab2=findViewById(R.id.fab2);

        imgUpdatecAlfrutaEnfunde=findViewById(R.id.imgUpdatecAlfrutaEnfunde);
        layoutPesobrutoPorClusterSolo=findViewById(R.id.layoutPesobrutoPorClusterSolo);

        ediEmpacadora=findViewById(R.id.ediEmpacadora);
        ediCandadoQsercom=findViewById(R.id.ediCandadoQsercom);
        lyEscontenedor=findViewById(R.id.lyEscontenedor);
        ediSemana=findViewById(R.id.ediSemana);
        ediFecha=findViewById(R.id.ediFecha);
        ediProductor=findViewById(R.id.ediProductor);
        ediHacienda=findViewById(R.id.ediHacienda);
        ediCodigo=findViewById(R.id.ediCodigo);
        ediInscirpMagap=findViewById(R.id.ediInscirpMagap);
        ediPemarque=findViewById(R.id.ediPemarque);
        ediNtargetaEmbarque=findViewById(R.id.ediNtargetaEmbarque);
        ediZona=findViewById(R.id.ediZona);
        ediHoraInicio=findViewById(R.id.ediHoraInicio);
        ediHoraTermino=findViewById(R.id.ediHoraTermino);
        ediNguiaRemision=findViewById(R.id.ediNguiaRemision);
        edi_nguia_transporte=findViewById(R.id.edi_nguia_transporte);
        edi_nguia_transporte=findViewById(R.id.edi_nguia_transporte);
        ediNhojaEvaluacion=findViewById(R.id.ediNhojaEvaluacion);
        spinnerSelectZona = findViewById(R.id.spinnerZona);
        ediObservacion = findViewById(R.id.ediObservacion);


        ediEnsunchado=findViewById(R.id.ediEnsunchado);
        ediBalanzaRepeso=findViewById(R.id.ediBalanzaRepeso);


        ediBalanza=findViewById(R.id.ediBalanza);
        ediFuenteAgua=findViewById(R.id.ediFuenteAgua);
        ediAguaCorrida=findViewById(R.id.ediAguaCorrida);
        ediLavadoRacimos=findViewById(R.id.ediLavadoRacimos);
        ediFumigacionClin1=findViewById(R.id.ediFumigacionClin1);
        ediTipoBoquilla=findViewById(R.id.ediTipoBoquilla);
        ediCajasProcDesp=findViewById(R.id.ediCajasProcDesp);
        ediRacimosCosech=findViewById(R.id.ediRacimosCosech);

        ediRacimosRecha=findViewById(R.id.ediRacimosRecha);
        ediRacimProces=findViewById(R.id.ediRacimProces);


         ediExtCalid=findViewById(R.id.ediExtCalid);
         ediExtRodillo=findViewById(R.id.ediExtRodillo);
         ediExtGancho= findViewById(R.id.ediExtGancho);

         ediExtCalidCi=findViewById(R.id.ediExtCalidCi);
         ediExtRodilloCi=findViewById(R.id.ediExtRodilloCi);
         ediExtGanchoCi =findViewById(R.id.ediExtGanchoCi);

        spinnerCandadoQsercon=findViewById(R.id.spinnerCandadoQsercon);


        linLayoutHeader1 =findViewById(R.id.linLayoutHeader1);
        linLayoutHeader2 =findViewById(R.id.linLayoutHeader2);
        linLayoutHeader3 =findViewById(R.id.linLayoutHeader3);
        linLayoutHeader4 =findViewById(R.id.linLayoutHeader4);
        linLayoutHeader5 =findViewById(R.id.linLayoutHeader5);
        linLayoutHeader6 =findViewById(R.id.linLayoutHeader6);
        linLayoutHeader7 =findViewById(R.id.linLayoutHeader7);

        linLayoutHeader8 =findViewById(R.id.linLayoutHeader8);

        spFuenteAgua =findViewById(R.id.spFuenteAgua);
        spFumigaCorL1=findViewById(R.id.spFumigaCorL1) ;
        spTipoBoquilla=findViewById(R.id.spTipoBoquilla) ;
        switchLavdoRacimos =findViewById(R.id.switchLavdoRacimos);
        swAguaCorrida=findViewById(R.id.swAguaCorrida);

        ediContenedor=findViewById(R.id.ediContenedor);


        ediPPC01=findViewById(R.id.ediPPC01);
        ediPPC02=findViewById(R.id.ediPPC02);
        ediPPC03=findViewById(R.id.ediPPC03);
        ediPPC04=findViewById(R.id.ediPPC04);
        ediPPC05=findViewById(R.id.ediPPC05);
        ediPPC06=findViewById(R.id.ediPPC06);
        ediPPC07=findViewById(R.id.ediPPC07);
        ediPPC08=findViewById(R.id.ediPPC08);
        ediPPC09=findViewById(R.id.ediPPC09);
        ediPPC010=findViewById(R.id.ediPPC010);
        ediPPC011=findViewById(R.id.ediPPC011);
        ediPPC012=findViewById(R.id.ediPPC012);
        ediPPC013=findViewById(R.id.ediPPC013);
        ediPPC014=findViewById(R.id.ediPPC014);
        ediPPC015=findViewById(R.id.ediPPC015);
        ediPPC016=findViewById(R.id.ediPPC016);

       // p2pbCluster01=findViewById(R.id.p2pbCluster01);

        ediNViaje=findViewById(R.id.ediNViaje);

        progressBarFormulario=findViewById(R.id.progressBarFormulario);

        ediCondicionBalanza=findViewById(R.id.ediCondicionBalanza);
        ediTipodeCaja=findViewById(R.id.ediTipodeCaja);
        ediTipoPlastico=findViewById(R.id.ediTipoPlastico);
        ediTipoBalanza=findViewById(R.id.ediTipoBalanza);
        editipbalanzaRepeso=findViewById(R.id.editipbalanzaRepeso);
        ediUbicacionBalanza=findViewById(R.id.ediUbicacionBalanza);



        ediUbicacion1=findViewById(R.id.ediCod2);
        ediRuma1=findViewById(R.id.edinCajas3);
        ediTermofrafo2=findViewById(R.id.ediTermofrafo2);
        ediUbicacion2=findViewById(R.id.ediUbicacion2);
        ediCandadoQsercom =findViewById(R.id.ediCandadoQsercom);


        ediNombreChofer=findViewById(R.id.ediNombreChofer);
        ediCedula=findViewById(R.id.ediCedula);
        ediCelular=findViewById(R.id.ediCelular);
        ediPLaca=findViewById(R.id.ediPLaca);






        spinnerCondicionBalanza=  findViewById(R.id.spinnerCondicionBalanza);
        spinnertipoCaja =  findViewById(R.id.spinnertipoCaja);
        spinnertipodePlastico = findViewById(R.id.spinnertipodePlastico);
        spinnertipodeBlanza =  findViewById(R.id.spinnertipodeBlanza);
        spinnertipodeBlanzaRepeso =  findViewById(R.id.spinnertipodeBlanzaRepeso);
        spinnerubicacionBalanza =  findViewById(R.id.spinnerubicacionBalanza);

        switchHaybalanza=findViewById(R.id.switchHaybalanza);
        switchHayEnsunchado=findViewById(R.id.switchHayEnsunchado);
        switchBalanzaRep=findViewById(R.id.switchBalanzaRep);



    }


    private void addClickListeners( ) {

        /**todos add a todos clicklistener de la implemntacion*/
        imgVAtachDocumentacionss.setOnClickListener(this);//ultimo
        imgVAtachProcesoFrutaFinca.setOnClickListener(this);
        imbTakePicProcesoFrutaFinca.setOnClickListener(this);

        imgVAtachCierreContenedor.setOnClickListener(this);
        imbTakePicCierreContenedor.setOnClickListener(this);
        imbTakePicDocuementacionxx.setOnClickListener(this);



    //    p2pbCluster01.setOnClickListener(this);

        layoutPesobrutoPorClusterSolo.setOnClickListener(this);

        imgUpdatecAlfrutaEnfunde.setOnClickListener(this);




        fab2.setOnClickListener(this);




        linLayoutHeader2.setOnClickListener(this);
        linLayoutHeader1.setOnClickListener(this);
      //  linLayoutHeader3.setOnClickListener(this);
     //   linLayoutHeader4.setOnClickListener(this);
     //   linLayoutHeader5.setOnClickListener(this);
        linLayoutHeader6.setOnClickListener(this);
        linLayoutHeader7.setOnClickListener(this);
        linLayoutHeader8.setOnClickListener(this);

        ediFecha.setOnClickListener(this);
        ediHoraInicio.setOnClickListener(this);
        ediHoraTermino.setOnClickListener(this);




    }

    private void oucultaLinearLayout(LinearLayout linearLayout) { //configuraremos algos views al iniciar
        linearLayout.setVisibility(GONE);


    }


    private void muestraLinearLayout( LinearLayout linearLayout) { //configuraremos algos views al iniciar

        linearLayout.setVisibility(LinearLayout.VISIBLE);



    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {


        if(view.getId() ==R.id.p2pbCluster01 || view.getId() ==R.id.p2pbCluster02 || view.getId() ==R.id.p2pbCluster03|| view.getId() ==R.id.p2pbCluster04) {

            String keyCurrent=Integer.toString(view.getId());
            showBottomSheetDialog(keyCurrent);

            return ;

        }


        if(!checkPermission()){

            requestPermission();
            //   Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            // checkPermission2();

            /****por aqui pedir permisos antes **/

        }

        String data[]={"image/*"};
        Log.i("miclickimg","hemos hecho click");

        int idCurrent= view.getId();


        if(idCurrent==R.id.imgVAtachProcesoFrutaFinca || idCurrent==R.id.imgVAtachCierreContenedor ||
                idCurrent == R.id.imgVAtachDocumentacionss){ //si es atach//si es atach


            currentTypeImage=Integer.parseInt(view.getTag().toString());


            activityResultLauncher.launch("image/*");

            Log.i("miclickimg","es foto es type selected es "+currentTypeImage);

        }

        else if(idCurrent==R.id.imbTakePicProcesoFrutaFinca
                || idCurrent==R.id.imbTakePicCierreContenedor ||
                idCurrent==R.id.imbTakePicDocuementacionxx ){  //si es tajke pic con camara

            currentTypeImage=Integer.parseInt(view.getTag().toString());


            takepickNow();

            Log.i("miclickimg","es foto es type selected es "+currentTypeImage);


        }



          else{

            switch (view.getId()) {


                case R.id.imgUpdatecAlfrutaEnfunde:
                    Log.i("miclickimg","es foto es type Variables.FOTO_PROD_POSTCOSECHA");
                    getResultDatCalibCalEnfundes();
                    break;



                case  R.id.ediFecha:
                    selecionaFecha();
                    break;



                case R.id.fab2: //si pulas en btn chekear en que modo esta ...si el modo cambia...
                    TextView txtModeAdviser=findViewById(R.id.txtModeAdviser);

                    if(isModEdicionFields){ //si es modo edicion..
                        fab2.setImageResource(R.drawable.ic_baseline_edit_24aa);

                        txtModeAdviser.setText("Modo Visualizacion ");



                        //cambiamos al modo visualizacion
                        isModEdicionFields=false;
                        activateModePreview();


                    }else{ //SI NO ES MODO VISUZALIZACION
                        fab2.setImageResource(R.drawable.ic_baseline_preview_24jhj);
                        txtModeAdviser.setText("Modo Edicion ");

                        isModEdicionFields=true;
                        activateModeEdit();


                        //CAMABIAMOS EL MODO

                    }



                    break; //



                case R.id.layoutPesobrutoPorClusterSolo:

                    LinearLayout layPesoBruto1=findViewById(R.id.layPesoBruto1);

                    if(layPesoBruto1.getVisibility() == GONE) {
                        muestraLinearLayout(layPesoBruto1);
                    }
                    else{

                        oucultaLinearLayout(layPesoBruto1);
                    }
                    break; //



                case R.id.linLayoutHeader1:
                    LinearLayout layoutContainerSeccion1=findViewById(R.id.layoutContainerSeccion1);

                    if(layoutContainerSeccion1.getVisibility() == GONE) {
                        muestraLinearLayout(layoutContainerSeccion1);
                    }
                    else{

                        oucultaLinearLayout(layoutContainerSeccion1);
                    }
                    break; //



                case R.id.linLayoutHeader2:
                    LinearLayout layoutContainerSeccion2=findViewById(R.id.layoutContainerSeccion2);

                    if(layoutContainerSeccion2.getVisibility() == GONE) {
                        muestraLinearLayout(layoutContainerSeccion2);
                    }
                    else{

                        oucultaLinearLayout(layoutContainerSeccion2);
                    }
                    break; //

                case R.id.linLayoutHeader3:
                    LinearLayout layoutContainerSeccion3=findViewById(R.id.layoutContainerSeccion3);

                    if(layoutContainerSeccion3.getVisibility() == GONE) {
                        muestraLinearLayout(layoutContainerSeccion3);
                    }
                    else{

                        oucultaLinearLayout(layoutContainerSeccion3);
                    }
                    break; //


                case R.id.linLayoutHeader4:
                    LinearLayout layoutContainerSeccion4=findViewById(R.id.layoutContainerSeccion4);

                    if(layoutContainerSeccion4.getVisibility() == GONE) {
                        muestraLinearLayout(layoutContainerSeccion4);
                    }
                    else{

                        oucultaLinearLayout(layoutContainerSeccion4);
                    }
                    break; //



                case R.id.linLayoutHeader5:
                    LinearLayout layoutContainerSeccion5=findViewById(R.id.layoutContainerSeccion5);

                    if(layoutContainerSeccion5.getVisibility() == GONE) {
                        muestraLinearLayout(layoutContainerSeccion5);
                    }
                    else{

                        oucultaLinearLayout(layoutContainerSeccion5);
                    }
                    break; //

                case R.id.linLayoutHeader6:
                    LinearLayout layoutContainerSeccion6=findViewById(R.id.layoutContainerSeccion6);

                    if(layoutContainerSeccion6.getVisibility() == GONE) {
                        muestraLinearLayout(layoutContainerSeccion6);
                    }
                    else{

                        oucultaLinearLayout(layoutContainerSeccion6);
                    }
                    break; //


                case R.id.linLayoutHeader7:
                    LinearLayout layoutContainerSeccion7=findViewById(R.id.layoutContainerDatsProceso);

                    if(layoutContainerSeccion7.getVisibility() == GONE) {
                        muestraLinearLayout(layoutContainerSeccion7);
                    }
                    else{

                        oucultaLinearLayout(layoutContainerSeccion7);
                    }
                    break; //
                case R.id.linLayoutHeader8:
                    LinearLayout layoutContainerSeccion8=findViewById(R.id.layoutContainerSeccion8);

                    if(layoutContainerSeccion8.getVisibility() == GONE) {
                        muestraLinearLayout(layoutContainerSeccion8);
                    }
                    else{

                        oucultaLinearLayout(layoutContainerSeccion8);
                    }
                    break; //

                case R.id.ediCajas7:
                    // Utils.closeKeyboard(ActivityReporteCalidadCamionesyCarretas.this);

                    selecionaFecha();

                    break; //



                case R.id.ediHoraInicio:
                    // Utils.closeKeyboard(ActivityReporteCalidadCamionesyCarretas.this);

                    showingTimePicker(view);

                    break; //

                case R.id.ediHoraTermino:
                    // Utils.closeKeyboard(ActivityReporteCalidadCamionesyCarretas.this);
                    showingTimePicker(view);

                    break; //




                case R.id.ediTipoEmp2:
                    // Utils.closeKeyboard(ActivityReporteCalidadCamionesyCarretas.this);
                    showingTimePicker(view);

                    break; //

                case R.id.ediHoraEncendido2:
                    // Utils.closeKeyboard(ActivityReporteCalidadCamionesyCarretas.this);
                    showingTimePicker(view);

                    break; //




            }

        }




    }

    private void takepickNow() {


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

        ){
            takePickCamera();

            Log.i("codereister","permiso CONDEIDOIOTOMAMOS FOTO ES IF") ;
        }

        else

        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},
                    CODE_TWO_PERMISIONS);

            Log.i("codereister","permiso DENEGADO SOLICTAMOS PERMISO") ;

        }
    }

    void takePickCamera() {


        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "AppQsercom");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");

        cam_uri = PreviewCalidadCamionesyCarretas.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cam_uri);

        //startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE); // OLD WAY
        startCamera.launch(cameraIntent);                // VERY NEW WAY

    }


    ActivityResultLauncher<Intent> startCamera = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        // There are no request codes


                        try {

                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(PreviewCalidadCamionesyCarretas.this.getContentResolver(),cam_uri);

                            //Bitmap bitmap= Glide.with(context).asBitmap().load(cam_uri).submit().get();
                            String horientacionImg= HelperImage.devuelveHorientacionImg(bitmap);

                            //creamos un nuevo objet de tipo ImagenReport
                            ImagenReport obcjImagenReport =new ImagenReport("",cam_uri.toString(),currentTypeImage, Utils.getFileNameByUri(PreviewCalidadCamionesyCarretas.this,cam_uri),horientacionImg);
                            obcjImagenReport.setIdReportePerteence(UNIQUE_ID_iNFORME);

                            //agregamos este objeto a la lista
                            ImagenReport.hashMapImagesData.put(obcjImagenReport.getUniqueIdNamePic(), obcjImagenReport);


                            showImagesPicShotOrSelectUpdateView(false);

                        }


                      catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        Utils.saveMapImagesDataPreferences(ImagenReport.hashMapImagesData, PreviewCalidadCamionesyCarretas.this);

                        showImagesPicShotOrSelectUpdateView(false);

                    }
                }
            });



    private View getVistaAnteriorClick() { //el estado puede ser lleno o vacio isEstaLleno


        if(listViewsClickedUser.size() ==3) { //SOLO GUARDAMOS DOS NUMEROS para ahorra memoria
            listViewsClickedUser.remove(0);   //ya no queremoes el primer objeto de la lista siempre y cuando la lista contnega 3 objetos

        }
        Log.i("casnasd","el size aqui en metodo es "+listViewsClickedUser.size());




        View vistAnterior = listViewsClickedUser.get(0);
        //  Log.i("soeobjetc","el objeto anterioR TAG ES "+vistAnterior.getTag().toString());



        return   vistAnterior;

    }


    private void resultatachImages() {
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.GetMultipleContents(), new ActivityResultCallback<List<Uri>>() {
                    @Override
                    public void onActivityResult(List<Uri> result) {
                        if (result != null) {

                            //creamos un objeto

                            for(int indice=0; indice<result.size(); indice++){


                                try {

                                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(PreviewCalidadCamionesyCarretas.this.getContentResolver(),result.get(indice));

                                    String horientacionImg=HelperImage.devuelveHorientacionImg(bitmap);

                                    Log.i("latypeimage","la imagen tipo es "+currentTypeImage);

                                    //creamos un nuevo objet de tipo ImagenReport
                                    ImagenReport obcjImagenReport =new ImagenReport("",result.get(indice).toString(),currentTypeImage, UUID.randomUUID().toString()+Utils.getFormate2(Utils.getFileNameByUri(PreviewCalidadCamionesyCarretas.this,result.get(indice))),horientacionImg);
                                    obcjImagenReport.setIdReportePerteence(UNIQUE_ID_iNFORME);

                                    //agregamos este objeto a la lista
                                    ImagenReport.hashMapImagesData.put(obcjImagenReport.getUniqueIdNamePic(), obcjImagenReport);


                                    Log.i("latypeimage","el size de map es  "+ImagenReport.hashMapImagesData.size());


                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Utils.saveMapImagesDataPreferences(ImagenReport.hashMapImagesData, PreviewCalidadCamionesyCarretas.this);

                            }


                            Log.i("latypeimage","el size de map cc es  "+ImagenReport.hashMapImagesData.size());

                            showImagesPicShotOrSelectUpdateView(false);



                        }
                    }
                });
    }

    void showImageByUri(Uri uri )  {
        try {

            // Setting image on image view using Bitmap
            Bitmap bitmap = MediaStore
                    .Images
                    .Media
                    .getBitmap(
                            getContentResolver(),
                            uri);



            //escalamos el bitmap
            Bitmap bitmap2=Bitmap.createScaledBitmap(bitmap, 420, 400, false);
            Log.i("registrand","los encontrado");


            ImageView imageView= new ImageView(this);


            imageView.setImageBitmap(bitmap2);






        }

        catch (IOException e) {
            // Log the exception
            e.printStackTrace();
        }
    }


    private void listennersSpinners() {


        spTipoBoquilla .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String textSelect= spTipoBoquilla.getSelectedItem().toString();
                ediTipoBoquilla.setText(textSelect);


                /*
                if(textSelect.equals("Ninguna")){
                    //actualizamos
                    Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                   // ediTipoBoquilla.setText("");
                  //  actualizaListStateView("spTipoBoquilla",false) ;
                }else {
                  //  actualizaListStateView("spTipoBoquilla",true) ;
                }
*/

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spFumigaCorL1 .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String textSelect= spFumigaCorL1.getSelectedItem().toString();
                ediFumigacionClin1.setText(textSelect);
                if(textSelect.equals("Ninguna")){
                    //actualizamos
                    Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                    ediFumigacionClin1.setText("");
                  //  actualizaListStateView("spFumigaCorL1",false) ;
                }else {
                  //  actualizaListStateView("spFumigaCorL1",true) ;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        spFuenteAgua .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String textSelect= spFuenteAgua.getSelectedItem().toString();
                ediFuenteAgua.setText(textSelect);
                if(textSelect.equals("Ninguna")){
                    //actualizamos
                    Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                    ediFuenteAgua.setText("");
                 //   actualizaListStateView("spFuenteAgua",false) ;
                }else {
                  //  actualizaListStateView("spFuenteAgua",true) ;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });









        spinnerSelectZona .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String zonaEelejida= spinnerSelectZona.getSelectedItem().toString();
                ediZona.setText(zonaEelejida);


                if(zonaEelejida.equals("Ninguna")){
                    //actualizamos
                    Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                    ediZona.setText("");
                 //   actualizaListStateView("spinnerZona",false) ;
                }else {
                 //   actualizaListStateView("spinnerZona",true) ;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        spinnerCondicionBalanza .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String condicion= spinnerCondicionBalanza.getSelectedItem().toString();
                ediCondicionBalanza.setText(condicion);
                if(condicion.equals("Ninguna")){
                    //actualizamos
                    Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                    ediCondicionBalanza.setText("");
                  ///  actualizaListStateView("ediCondicionBalanza",false) ;
                }else {
                 //   actualizaListStateView("ediCondicionBalanza",true) ;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        spinnerCandadoQsercon .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String candadoQsercom= spinnerCandadoQsercon.getSelectedItem().toString();
                ediCandadoQsercom.setText(candadoQsercom);




              /*
                if(condicion.equals("Ninguna")){
                    //actualizamos
                    Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                    ediCondicionBalanza.setText("");
                    actualizaListStateView("ediCondicionBalanza",false) ;
                }else {
                    actualizaListStateView("ediCondicionBalanza",true) ;
                }
*/
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnertipoCaja .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String zonaEelejida= spinnertipoCaja.getSelectedItem().toString();
                ediTipodeCaja.setText(zonaEelejida);
                if(zonaEelejida.equals("Ninguna")){
                    //actualizamos
                    Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                    ediTipodeCaja.setText("");
                  //  actualizaListStateView("ediTipodeCaja",false) ;
                }else {
                  //  actualizaListStateView("ediTipodeCaja",true) ;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        spinnertipodePlastico .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String zonaEelejida= spinnertipodePlastico.getSelectedItem().toString();
                ediTipoPlastico.setText(zonaEelejida);
                if(zonaEelejida.equals("Ninguna")){
                    //actualizamos
                    Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                    ediTipoPlastico.setText("");
                  //  actualizaListStateView("ediTipoPlastico",false) ;
                }else {
                 //  actualizaListStateView("ediTipoPlastico",true) ;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnertipodeBlanza .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String zonaEelejida= spinnertipodeBlanza.getSelectedItem().toString();
                ediTipoBalanza.setText(zonaEelejida);

                if(zonaEelejida.equals("Ninguna")){
                    //actualizamos
                    Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                    ediTipoBalanza.setText("");
                  //  actualizaListStateView("ediTipoBalanza",false) ;
                }else {
                //    actualizaListStateView("ediTipoBalanza",true) ;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnertipodeBlanzaRepeso .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String zonaEelejida= spinnertipodeBlanzaRepeso.getSelectedItem().toString();
                editipbalanzaRepeso.setText(zonaEelejida);
                if(zonaEelejida.equals("Ninguna")){
                    //actualizamos
                    Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                    editipbalanzaRepeso.setText("");
                    // actualizaListStateView("addetiquetaaqui",false) ;
                }else {
                    //  actualizaListStateView("addetiquetaaqui",true) ;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


/*
        spinnerubicacionBalanza .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String zonaEelejida= spinnerubicacionBalanza.getSelectedItem().toString();

                ediUbicacionBalanza.setText(zonaEelejida);

                if(zonaEelejida.equals("Ninguna")){
                    //actualizamos
                    Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                    ediUbicacionBalanza.setText("");
                  //  actualizaListStateView("ediUbicacionBalanza",false) ;
                }else {
                 //   actualizaListStateView("ediUbicacionBalanza",true) ;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


*/

        switchLavdoRacimos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(switchLavdoRacimos.isChecked()){

                    ediLavadoRacimos.setText(" SI ") ;

                }else {
                    ediLavadoRacimos.setText(" NO ") ;

                }
            }
        });




        swAguaCorrida.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(swAguaCorrida.isChecked()){

                    ediAguaCorrida.setText(" SI ") ;

                }else {
                    ediAguaCorrida.setText(" NO ") ;

                }
            }
        });

    }


    private void showImagesPicShotOrSelectUpdateView(boolean isDeleteImg){

        //si es eliminar comprobar aqui

        if(isDeleteImg){

            currentTypeImage=Variables.typeoFdeleteImg;
        }


        Log.i("latypeimage","el currenttupe imagen es xxc "+currentTypeImage);


        ArrayList<ImagenReport> filterListImagesData=new ArrayList<ImagenReport>(); //LISTA FILTRADA QUE REPRESENTARA EL RECICLERVIEW

        RecyclerView recyclerView=null;


        for (Map.Entry<String, ImagenReport> set : ImagenReport.hashMapImagesData.entrySet()) {

            String key = set.getKey();
            ImagenReport value = set.getValue();

            if(value.getTipoImagenCategory()==currentTypeImage){

                Log.i("latypeimage","encontramos una imagen con este tipo");


                filterListImagesData.add(ImagenReport.hashMapImagesData.get(key));
            }

        }


        //buscamos este


        switch(currentTypeImage){

            case Variables.FOTO_PROCESO_FRUTA_FINCA:
                recyclerView= findViewById(R.id.recyclerFotoProcesoFrEnFinca);
                break;



            case Variables.FOTO_CIERRE_CONTENEDOR:
                recyclerView= findViewById(R.id.recyclerFotoCierreCtendr);
                break;

            case Variables.FOTO_DOCUMENTACION:
                recyclerView= findViewById(R.id.recyclerFotoDocumentacion);
                break;
        }



        RecyclerViewAdapter adapter=new RecyclerViewAdapter(filterListImagesData,this);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);


        // at last set adapter to recycler view.
        assert recyclerView != null;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        eventoBtnclicklistenerDelete(adapter);



    }


    private void eventCheckdata(){// verificamos que halla llenado toda la info necesaria..

        Button btnCheck;
        btnCheck=findViewById(R.id.btnCheck);


        btnCheck.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                // generatePDFandImport();

                checkDataFields();




            }
        });





    }

    void checkDataFields(){ //

        if(! checkDatosGeneralesIsLleno()){

            Log.i("test001","no esta lleno  checkDatosGeneralesIsLleno");
            return;
        }


        else{
            Log.i("test001","si esta lleno checkDatosGeneralesIsLleno ");

        }

        if(! checkcantidadPostcosechaIsLleno()){

            Log.i("test001","no esta lleno  checkcantidadPostcosechaIsLleno");
            return;
        }else{
            Log.i("test001","si esta lleno checkcantidadPostcosechaIsLleno ");

        }



        /*
        if(! checkDatosContenedorIsLleno()){
            Log.i("test001","no esta lleno  checkDatosContenedorIsLleno");

            return;
        }else{

            Log.i("test001","si  esta lleno  checkDatosContenedorIsLleno");
        }
       */


        if(! checkDatosTransportistaIsLleno()){
            Log.i("test001","no esta lleno  checkDatosTransportistaIsLleno");

            return;
        }else{

            Log.i("test001","si  esta lleno  checkDatosTransportistaIsLleno");
        }


        if(! checkDatosProcesoIsLleno()){
            Log.i("test001","no esta lleno  checkDatosProcesoIsLleno");

            return;
        }else{

            Log.i("test001","si  esta lleno  checkDatosProcesoIsLleno");

        }



        if(! checkDatosHaciendaIsLleno()){
            Log.i("test001","no esta lleno  checkDatosHaciendaIsLleno");

            return;
        }else{

            Log.i("test001","si  esta lleno  checkDatosHaciendaIsLleno");
        }


        if(! checkDataCalibFrutaCalEnfn()){
            Log.i("test001","no esta lleno  checkDataCalibFrutaCalEnfn");

            return;
        }else{

            Log.i("test001","si  esta lleno  checkDataCalibFrutaCalEnfn");


        }



        if(! cehckFaltanImagenes()){
            return;

        }


        if(! chekDaTaEvaluador()){
            Log.i("test001","no esta lleno  chekDaTaEvaluador");

            return;
        }else{

            Log.i("test001","si  esta lleno  chekDaTaEvaluador");


        }




        openBottomSheetConfirmCreateNew(Variables.FormCamionesyCarretasActivity);






    }
    private void openBottomSheetConfirmCreateNew(int tipoFormulario){

        DialogConfirmChanges addPhotoBottomDialogFragment = DialogConfirmChanges.newInstance(tipoFormulario);
        addPhotoBottomDialogFragment.show(getSupportFragmentManager(), DialogConfirmChanges.TAG);
    }




    private void createObjcInformeAndUpdate(){

        ReportCamionesyCarretas informe = new ReportCamionesyCarretas(UNIQUE_ID_iNFORME,ediCodigo.getText().toString(),
                ediNhojaEvaluacion.getText().toString(),ediZona.getText().toString(),ediProductor.getText().toString(),
                ediCodigo.getText().toString(), ediPemarque.getText().toString(),
                 ediNguiaRemision.getText().toString(),ediHacienda.getText().toString(),edi_nguia_transporte.getText().toString(),ediNtargetaEmbarque.getText().toString(),
                ediInscirpMagap.getText().toString(),ediHoraInicio.getText().toString(),ediHoraTermino.getText().toString(),ediSemana.getText().toString(),ediEmpacadora.getText().toString(),
                ediNombreChofer.getText().toString(),ediCedula.getText().toString(),ediCelular.getText().toString(),ediPLaca.getText().toString(),ediCandadoQsercom.getText().toString(),
                   ediTipoPlastico.getText().toString(),ediTipodeCaja.getText().toString(),switchHayEnsunchado.isChecked(),switchHaybalanza.isChecked(),
                   ediCondicionBalanza.getText().toString(),ediTipoBalanza.getText().toString(),switchBalanzaRep.isChecked(),editipbalanzaRepeso.getText().toString(),
                ediFuenteAgua.getText().toString(),swAguaCorrida.isChecked(),switchLavdoRacimos.isChecked(),ediFumigacionClin1.getText().toString(),
                Integer.parseInt(ediRacimosCosech.getText().toString()) ,Integer.parseInt(ediRacimosRecha.getText().toString()),Integer.parseInt(ediRacimProces.getText().toString()),
                Integer .parseInt(ediCajasProcDesp.getText().toString()), ediExtCalid.getText().toString(),ediExtRodillo.getText().toString(),
                ediExtGancho.getText().toString(), ediExtCalidCi.getText().toString(),ediExtRodilloCi.getText().toString(),ediExtGanchoCi.getText().toString()
                ,FieldOpcional.observacionOpcional,Variables.currenReportCamionesyCarretas.getNodoQueContieneMapPesoBrutoCloster2y3l()

                ,ediClienteNombreReporte.getText().toString(),ediTipoBoquilla.getText().toString(),  ediExportadoraProcesada.getText().toString(),ediExportadoraSolicitante.getText().toString()

        ) ;




        HashMap<String, Float> miMapLbriado = generateMapLibriadoIfExistAndUpload(false);


        Log.i("simener","el size de lista es "+miMapLbriado.size());


        String keyWhereLocaleHashMapLibriado = "";
        if (miMapLbriado.size() > 0) {
            if(!Variables.currenReportCamionesyCarretas.getKeyOrNodeLibriadoSiEs().trim().isEmpty() &&
                    Variables.currenReportCamionesyCarretas.getKeyOrNodeLibriadoSiEs().length()>1){

                keyWhereLocaleHashMapLibriado = Variables.currenReportCamionesyCarretas.getKeyOrNodeLibriadoSiEs();

                Log.i("simener","el keyWhereLocaleHashMapLibriado ES  "+keyWhereLocaleHashMapLibriado);

            }
            else  { //pero si esta vacio
                keyWhereLocaleHashMapLibriado=RealtimeDB.rootDatabaseReference.push().getKey();
                Log.i("simener","ESTA VACIO Y AHORA ES "+keyWhereLocaleHashMapLibriado);

            }


            RealtimeDB.addNewhasmapPesoBrutoClosters2y3L(miMapLbriado, keyWhereLocaleHashMapLibriado);
        }


        Log.i("simener","el keyWhereLocaleHashMapLibriado ES AQUI ES  "+keyWhereLocaleHashMapLibriado);

        informe.setKeyOrNodeLibriadoSiEs(keyWhereLocaleHashMapLibriado);




        //agregamos algunas propiedades unicas que ya tenia este informe omo faceha,etc
        informe.setSimpleDataFormat(Variables.currenReportCamionesyCarretas.getSimpleDataFormat());
        informe.setFechaCreacionInf(Variables.currenReportCamionesyCarretas.getFechaCreacionInf());

        RealtimeDB.updateCalidaCamionCarrretas(informe,Variables.currenReportCamionesyCarretas);


        addCalibracionFutaC_enfAndUpload();



        // updateOrUploadNewHashmapPesoBrutoCloster2y3l(Variables.currenReportCamionesyCarretas.getNodoQueContieneMapPesoBrutoCloster2y3l());

        addProdcutsPostCosechaAndUpload(); //agregamos y subimos los productos postcosecha..



    }

    HashMap<String, Float> generateMapLibriadoIfExistAndUpload(boolean isGeneratePdf) {


        EditText pbCluster01 = findViewById(R.id.pbCluster01);
        EditText pbCluster05 = findViewById(R.id.pbCluster05);
        EditText pbCluster03 = findViewById(R.id.pbCluster03);
        EditText pbCluster02 = findViewById(R.id.pbCluster02);
        EditText pbCluster04 = findViewById(R.id.pbCluster04);
        EditText pbCluster010 = findViewById(R.id.pbCluster010);
        EditText pbCluster09 = findViewById(R.id.pbCluster09);
        EditText pbCluster07 = findViewById(R.id.pbCluster07);
        EditText pbCluster08 = findViewById(R.id.pbCluster08);
        EditText pbCluster06 = findViewById(R.id.pbCluster06);
        EditText pbCluster011 = findViewById(R.id.pbCluster011);
        EditText pbCluster015 = findViewById(R.id.pbCluster015);
        EditText pbCluster012 = findViewById(R.id.pbCluster012);
        EditText pbCluster013 = findViewById(R.id.pbCluster013);
        EditText pbCluster014 = findViewById(R.id.pbCluster014);
        EditText pbCluster016 = findViewById(R.id.pbCluster016);
        EditText pbCluster019 = findViewById(R.id.pbCluster019);
        EditText pbCluster018 = findViewById(R.id.pbCluster018);
        EditText pbCluster020 = findViewById(R.id.pbCluster020);
        EditText pbCluster017 = findViewById(R.id.pbCluster017);
        EditText pbCluster025 = findViewById(R.id.pbCluster025);
        EditText pbCluster024 = findViewById(R.id.pbCluster024);
        EditText pbCluster023 = findViewById(R.id.pbCluster023);
        EditText pbCluster022 = findViewById(R.id.pbCluster022);
        EditText pbCluster021 = findViewById(R.id.pbCluster021);
        EditText pbCluster028 = findViewById(R.id.pbCluster028);
        EditText pbCluster027 = findViewById(R.id.pbCluster027);
        EditText pbCluster029 = findViewById(R.id.pbCluster029);
        EditText pbCluster026 = findViewById(R.id.pbCluster026);
        EditText pbCluster030 = findViewById(R.id.pbCluster030);
        EditText pbCluster034 = findViewById(R.id.pbCluster034);
        EditText pbCluster031 = findViewById(R.id.pbCluster031);
        EditText pbCluster035 = findViewById(R.id.pbCluster035);
        EditText pbCluster033 = findViewById(R.id.pbCluster033);
        EditText pbCluster032 = findViewById(R.id.pbCluster032);
        EditText pbCluster039 = findViewById(R.id.pbCluster039);
        EditText pbCluster040 = findViewById(R.id.pbCluster040);
        EditText pbCluster037 = findViewById(R.id.pbCluster037);
        EditText pbCluster038 = findViewById(R.id.pbCluster038);
        EditText pbCluster036 = findViewById(R.id.pbCluster036);
        EditText pbCluster043 = findViewById(R.id.pbCluster043);
        EditText pbCluster045 = findViewById(R.id.pbCluster045);
        EditText pbCluster042 = findViewById(R.id.pbCluster042);
        EditText pbCluster041 = findViewById(R.id.pbCluster041);
        EditText pbCluster044 = findViewById(R.id.pbCluster044);
        EditText pbCluster048 = findViewById(R.id.pbCluster048);
        EditText pbCluster046 = findViewById(R.id.pbCluster046);
        EditText pbCluster050 = findViewById(R.id.pbCluster050);
        EditText pbCluster047 = findViewById(R.id.pbCluster047);
        EditText pbCluster049 = findViewById(R.id.pbCluster049);
        EditText p2pbCluster01 = findViewById(R.id.p2pbCluster01);
        EditText p2pbCluster05 = findViewById(R.id.p2pbCluster05);
        EditText p2pbCluster03 = findViewById(R.id.p2pbCluster03);
        EditText p2pbCluster02 = findViewById(R.id.p2pbCluster02);
        EditText p2pbCluster04 = findViewById(R.id.p2pbCluster04);
        EditText p2pbCluster010 = findViewById(R.id.p2pbCluster010);
        EditText p2pbCluster09 = findViewById(R.id.p2pbCluster09);
        EditText p2pbCluster07 = findViewById(R.id.p2pbCluster07);
        EditText p2pbCluster08 = findViewById(R.id.p2pbCluster08);
        EditText p2pbCluster06 = findViewById(R.id.p2pbCluster06);
        EditText p2pbCluster011 = findViewById(R.id.p2pbCluster011);
        EditText p2pbCluster015 = findViewById(R.id.p2pbCluster015);
        EditText p2pbCluster012 = findViewById(R.id.p2pbCluster012);
        EditText p2pbCluster013 = findViewById(R.id.p2pbCluster013);
        EditText p2pbCluster014 = findViewById(R.id.p2pbCluster014);
        EditText p2pbCluster016 = findViewById(R.id.p2pbCluster016);
        EditText p2pbCluster019 = findViewById(R.id.p2pbCluster019);
        EditText p2pbCluster018 = findViewById(R.id.p2pbCluster018);
        EditText p2pbCluster020 = findViewById(R.id.p2pbCluster020);
        EditText p2pbCluster017 = findViewById(R.id.p2pbCluster017);
        EditText p2pbCluster025 = findViewById(R.id.p2pbCluster025);
        EditText p2pbCluster024 = findViewById(R.id.p2pbCluster024);
        EditText p2pbCluster023 = findViewById(R.id.p2pbCluster023);
        EditText p2pbCluster022 = findViewById(R.id.p2pbCluster022);
        EditText p2pbCluster021 = findViewById(R.id.p2pbCluster021);
        EditText p2pbCluster028 = findViewById(R.id.p2pbCluster028);
        EditText p2pbCluster027 = findViewById(R.id.p2pbCluster027);
        EditText p2pbCluster029 = findViewById(R.id.p2pbCluster029);
        EditText p2pbCluster026 = findViewById(R.id.p2pbCluster026);
        EditText p2pbCluster030 = findViewById(R.id.p2pbCluster030);
        EditText p2pbCluster034 = findViewById(R.id.p2pbCluster034);
        EditText p2pbCluster031 = findViewById(R.id.p2pbCluster031);
        EditText p2pbCluster035 = findViewById(R.id.p2pbCluster035);
        EditText p2pbCluster033 = findViewById(R.id.p2pbCluster033);
        EditText p2pbCluster032 = findViewById(R.id.p2pbCluster032);
        EditText p2pbCluster039 = findViewById(R.id.p2pbCluster039);
        EditText p2pbCluster040 = findViewById(R.id.p2pbCluster040);
        EditText p2pbCluster037 = findViewById(R.id.p2pbCluster037);
        EditText p2pbCluster038 = findViewById(R.id.p2pbCluster038);
        EditText p2pbCluster036 = findViewById(R.id.p2pbCluster036);


        EditText[] miArray = {
                pbCluster01, pbCluster05, pbCluster03, pbCluster02, pbCluster04, pbCluster010, pbCluster09, pbCluster07, pbCluster08, pbCluster06, pbCluster011,
                pbCluster015, pbCluster012, pbCluster013, pbCluster014, pbCluster016, pbCluster019, pbCluster018, pbCluster020, pbCluster017, pbCluster025,
                pbCluster024, pbCluster023, pbCluster022, pbCluster021, pbCluster028, pbCluster027, pbCluster029, pbCluster026, pbCluster030, pbCluster034,
                pbCluster031, pbCluster035, pbCluster033, pbCluster032, pbCluster039, pbCluster040, pbCluster037, pbCluster038, pbCluster036, pbCluster043,
                pbCluster045, pbCluster042, pbCluster041, pbCluster044, pbCluster048, pbCluster046, pbCluster050, pbCluster047, pbCluster049, p2pbCluster01,
                p2pbCluster05, p2pbCluster03, p2pbCluster02, p2pbCluster04, p2pbCluster010, p2pbCluster09, p2pbCluster07, p2pbCluster08, p2pbCluster06,
                p2pbCluster011, p2pbCluster015, p2pbCluster012, p2pbCluster013, p2pbCluster014, p2pbCluster016, p2pbCluster019, p2pbCluster018,
                p2pbCluster020, p2pbCluster017, p2pbCluster025, p2pbCluster024, p2pbCluster023, p2pbCluster022, p2pbCluster021, p2pbCluster028,
                p2pbCluster027, p2pbCluster029, p2pbCluster026, p2pbCluster030, p2pbCluster034, p2pbCluster031, p2pbCluster035, p2pbCluster033,
                p2pbCluster032, p2pbCluster039, p2pbCluster040, p2pbCluster037, p2pbCluster038, p2pbCluster036

        };

        HashMap<String, Float> miMapData = new HashMap<>();


        for (EditText currentEdit : miArray) {

            if (!currentEdit.getText().toString().trim().isEmpty()) {

                //le agregamos un slash al id key mas o menos este fomrato idddd/fil1

                miMapData.put(currentEdit.getId() + "-" + currentEdit.getTag(), Float.parseFloat(currentEdit.getText().toString()));

                Log.i("miodataxx","hay texto aqui");

            }


        }


        if (isGeneratePdf) {
            //obtenemos el p

            //fil1

            Variables.listPromedioLibriado= new ArrayList<>();

            float sumFilas;
            int contadorItemsConTag;


            for (int indice = 0; indice < 18; indice++) {
                //                String keyOFeditextCurrent = edi.getId() + "-" + edi.getTag();

                sumFilas=0;
                contadorItemsConTag=0;

                // String keyActualToSearch=


                //buscamos solo los que contengan esta key


                for (HashMap.Entry<String, Float> entry : miMapData.entrySet()) {

                    String keyHashMap = entry.getKey();
                    Float value = entry.getValue();


                    Log.i("miodataxx","hay texto aqui"+keyHashMap+" el value es "+"fil"+(indice+1));


                    if(keyHashMap.contains("fil"+(indice+1)) ){
                        contadorItemsConTag++;

                        sumFilas=sumFilas+value;


                    }


                }




                //ahora creamos un nuevo objet[

                if(sumFilas>0){

                    Variables.listPromedioLibriado.add(new PromedioLibriado(indice+1,sumFilas/contadorItemsConTag));

                }




            }

            Log.i("miodataxx","el size es: "+Variables.listPromedioLibriado.size());


        }

        return miMapData;

    }


    private void eventoBtnclicklistenerDelete(RecyclerViewAdapter adapter) {

        adapter.setOnItemClickListener(new RecyclerViewAdapter.ClickListener() {


            @Override
            public void onItemClick(int position, View v) {  //este para eminar
                //  Variables.currentCuponObjectGlob =listGiftCards.get(position);

                Log.i("midaclick","el click es here, posicion es "+position);

                ///elimnar el hasmap
                //vamos a ver el tipo del objeto removivo
                Variables.typeoFdeleteImg=  ImagenReport.hashMapImagesData.get(v.getTag()).getTipoImagenCategory();

                Log.i("camisax","el size antes de eliminar es "+ ImagenReport.hashMapImagesData.size());


                ImagenReport.hashMapImagesData.remove(v.getTag().toString());
                Utils.saveMapImagesDataPreferences(ImagenReport.hashMapImagesData, PreviewCalidadCamionesyCarretas.this);


                Log.i("camisax","el size despues de eliminar es "+ ImagenReport.hashMapImagesData.size());

                showImagesPicShotOrSelectUpdateView(true);





                //   Log.i("dtaas","switch a" + "ctivate is "+Variables.currentCuponObjectGlob.isEsActivateCupon());
                //  Log.i("dtaas","switch destacado  is "+Variables.currentCuponObjectGlob.isEsDestacadoCupon());


            }

        });
    }



    void uploadImagesInStorageAndInfoPICS() {
        //una lista de Uris


        if(ImagenReport.hashMapImagesData.size() ==0 ){

            Toast.makeText(this, "esta vacia ", Toast.LENGTH_SHORT).show();
            return;
        }
        //boorara desee aqui
        if(  !Variables.hashMapImagesStart.keySet().equals(ImagenReport.hashMapImagesData.keySet())){ //si no son iguales

            Log.i("elfile","alguno o toos son diferentes images llamaos metodo filtra");

            StorageData.counTbucle = 0; //resetemoa esta variable que sera indice en la reflexion

            ArrayList<ImagenReport> list2 = Utils.mapToArrayList(Utils.creaHahmapNoDuplicado());


            /**debugborrar*/
            for(ImagenReport imagenReport: list2){

                Log.i("latypeimage","el value es "+imagenReport.getTipoImagenCategory());
            }

            /**debugborrar*/


            StorageData.uploaddata(list2);

           // StorageData.uploadImage(PreviewCalidadCamionesyCarretas.this, Utils.creaHahmapNoDuplicado());



        }else{
            Log.i("debugasd","el size de hashMapImagesStart es  "+ Variables.hashMapImagesStart.size()+" y el size de hashMapImagesData es" +ImagenReport.hashMapImagesData.size());


            Log.i("elfile","son iguales las imagenes");

        }



        //    public static void uploadImage(Context context, ArrayList<ImagenReport> listImagesData) {

        //aqui subimos

    }





    @RequiresApi(api = Build.VERSION_CODES.M)
    private void generatePDFandImport(){
        //generate pdf


        if(!checkPermission()){

            requestPermission();
            //   Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            // checkPermission2();

            /****por aqui pedir permisos antes **/

        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && !Environment.isExternalStorageManager()) {
            // Uri uri = Uri.parse("package:" + BuildConfig.APPLICATION_ID);
            // startActivity(new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, uri));
        }







        //  startActivity(new Intent(ActivityReporteCalidadCamionesyCarretas.this,PdfPreviewActivity.class));

        //generamos un pdf con la data que tenemos()

        /*

        PdfMaker.generatePdfReport1(ActivityReporteCalidadCamionesyCarretas.this,ediCodigo.getText().toString(),Integer.parseInt(ediNhojaEvaluacion.getText().toString()),
                ediZona.getText().toString(),ediProductor.getText().toString(),ediCodigo.getText().toString()
                ,ediPemarque.getText().toString(),ediNguiaRemision.getText().toString(),ediHacienda.getText().toString()
                ,edi_nguia_transporte.getText().toString(),ediNtargetaEmbarque.getText().toString(),
                ediInscirpMagap.getText().toString(),ediHoraInicio.getText().toString(),ediHoraTermino.getText().toString()
                ,ediSemana.getText().toString(),ediEmpacadora.getText().toString(),ediContenedor.getText().toString(),ediObservacion.getText().toString()
                );

*/




    }


    private boolean checkPermission() {
        // checking of permissions.
        int permission1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestPermission() {
        // requesting permissions if not provided.

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Explain to the user why we need to read the contacts
            }

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    12);

            // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
            // app-defined int constant that should be quite unique

            return;
        }


        try {
            ActivityCompat.requestPermissions(this, new String[]{ READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {

                // after requesting permissions we are showing
                // users a toast message of permission granted.

               /*

                boolean writeStorage = grantResults[0] == PackageManager.MANAGE;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if ( readStorage) {
                    Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denied.", Toast.LENGTH_SHORT).show();
                    finish();
                }

                */

            }
        }
    }


    private boolean checkPermission2() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else {
            int result = ContextCompat.checkSelfPermission(PreviewCalidadCamionesyCarretas.this, READ_EXTERNAL_STORAGE);
            int result1 = ContextCompat.checkSelfPermission(PreviewCalidadCamionesyCarretas.this, WRITE_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
        }
    }







    ///vamos a cehekar que exista al menos una imagen en cada categoria...
    //comprbar que exista un objeto imagen.....

    //primero chekeamos el el uri exista...


    private boolean existminiumImage(int numImagenNMinimo, int categoriaImagenToSearch){

        int numImagesEcontradas=0;


        for (Map.Entry<String, ImagenReport> set : ImagenReport.hashMapImagesData.entrySet()) { //revismao en todo el map

            //   String key = set.getKey();

            ImagenReport value = set.getValue();

            if(value.getTipoImagenCategory()==categoriaImagenToSearch){

                numImagesEcontradas++;

                if(numImagesEcontradas >=numImagenNMinimo){
                    break;

                }

            }


        }


        if(numImagesEcontradas>=numImagenNMinimo){
            return true;
        }else{
            return false;


        }



    }



    private boolean checkDatosGeneralesIsLleno(){

        LinearLayout layoutContainerSeccion1=findViewById(R.id.layoutContainerSeccion1);

        if(layoutContainerSeccion1.getVisibility()== GONE){
            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
        }



        if(ediExportadoraProcesada.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediExportadoraProcesada.requestFocus();
            ediExportadoraProcesada.setError("Este espacio es obligatorio");
            return false;
            //obtiene el padre del padre

        }
        if(ediExportadoraSolicitante.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediExportadoraProcesada.requestFocus();
            ediExportadoraProcesada.setError("Este espacio es obligatorio");
            return false;
            //obtiene el padre del padre

        }
        if(ediClienteNombreReporte.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediClienteNombreReporte.requestFocus();
            ediClienteNombreReporte.setError("Este espacio es obligatorio");
            return false;
            //obtiene el padre del padre

        }




        if(ediSemana.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediSemana.requestFocus();
            ediSemana.setError("Este espacio es obligatorio");
            return false;
            //obtiene el padre del padre

        }

        if (ediFecha.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediFecha.requestFocus();
            ediFecha.setError("Debe selecionar una fecha");

            return false;

        }







        if(ediProductor.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediProductor.requestFocus();
            ediProductor.setError("Este espacio es obligatorio");

            return false;

        }




        if(ediHacienda.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediHacienda.requestFocus();
            ediHacienda.setError("Este espacio es obligatorio");

            return false;

        }






        if(ediCodigo.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCodigo.requestFocus();
            ediCodigo.setError("Este espacio es obligatorio");

            return false;

        }


        if(ediInscirpMagap.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediInscirpMagap.requestFocus();
            ediInscirpMagap.setError("Este espacio es obligatorio");
            ediInscirpMagap.setText("_");
            return false;

        }

        if(ediPemarque.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediPemarque.requestFocus();
            ediPemarque.setError("Este espacio es obligatorio");

            return false;

        }


        if(ediZona.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediZona.requestFocus();
            ediZona.setError("Debe selecionar una zona");
            return false;

        }


        if(ediHoraInicio.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediHoraInicio.requestFocus();
            ediHoraInicio.setError("Este espacio es obligatorio");

            return false;

        }


        if(ediHoraTermino.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediHoraTermino.requestFocus();
            ediHoraTermino.setError("Este espacio es obligatorio");

            return false;

        }



        if(ediNguiaRemision.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediNguiaRemision.requestFocus();
            ediNguiaRemision.setError("Este espacio es obligatorio");

            return false;

        }

        if(edi_nguia_transporte.getText().toString().isEmpty()){ //chekamos que no este vacia
            edi_nguia_transporte.requestFocus();
            edi_nguia_transporte.setError("Este espacio es obligatorio");

            return false;

        }


        if(ediNtargetaEmbarque.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediNtargetaEmbarque.requestFocus();
            ediNtargetaEmbarque.setError("Este espacio es obligatorio");

            return false;

        }




        if(ediNhojaEvaluacion.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediNhojaEvaluacion.requestFocus();
            ediNhojaEvaluacion.setError("Este espacio es obligatorio");
            return false;
            //obtiene el padre del padre

        }





        if(ediEmpacadora.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediEmpacadora.requestFocus();
            ediEmpacadora.setError("Este espacio es obligatorio");

            return false;

        }


        if(!ediObservacion.getText().toString().isEmpty()){ //si esta lleno

            FieldOpcional.observacionOpcional=ediObservacion.getText().toString();

        }

        //


        return true;
    }



    private boolean  checkcantidadPostcosechaIsLleno(){
        LinearLayout layoutContainerSeccion2=findViewById(R.id.layoutContainerSeccion2);
        Log.i("camisad" ,"se llamo este ");


        if(layoutContainerSeccion2.getVisibility()== GONE){
            layoutContainerSeccion2.setVisibility(LinearLayout.VISIBLE);
        }

        if(ediPPC01.getText().toString().isEmpty() && ediPPC02.getText().toString().isEmpty()&& ediPPC03.getText().toString().isEmpty()
                && ediPPC04.getText().toString().isEmpty()&& ediPPC05.getText().toString().isEmpty()&& ediPPC06.getText().toString().isEmpty()
                && ediPPC07.getText().toString().isEmpty()&& ediPPC08.getText().toString().isEmpty() && ediPPC09.getText().toString().isEmpty()
                && ediPPC010.getText().toString().isEmpty() && ediPPC011.getText().toString().isEmpty() && ediPPC012.getText().toString().isEmpty()
                && ediPPC013.getText().toString().isEmpty() && ediPPC014.getText().toString().isEmpty() && ediPPC015.getText().toString().isEmpty()
                && ediPPC016.getText().toString().isEmpty()
        ){ //chekamos que no este vacia
            ediPPC01.requestFocus();
            ediPPC01.setError("Inserte al menos 1 producto");

            Log.i("camisad" ,"se eejcuto este");

            return false;

        }



        if(! ediPPC015.getText().toString().isEmpty() && ediPPC016.getText().toString().isEmpty() ){ //chekamos que no este vacia
            ediPPC016.requestFocus();
            ediPPC016.setError("Inserte cantidad");

            return false ;

        }


        if(! ediPPC016.getText().toString().isEmpty() && ediPPC015.getText().toString().isEmpty() ){ //chekamos que no este vacia
            ediPPC015.requestFocus();
            ediPPC015.setError("inserte nombre producto");

            return false;

        }




        return  true;

    }







    private boolean checkDatosTransportistaIsLleno(){

        LinearLayout layoutContainerSeccion6=findViewById(R.id.layoutContainerSeccion6);
        ///CHEKEAMOS DATA seccion CONTENEDOR
        if(layoutContainerSeccion6.getVisibility()== GONE){
            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
        }


        if(ediNombreChofer.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediNombreChofer.requestFocus();
            ediNombreChofer.setError("Este espacio es obligatorio");

            return false;

        }



        if(ediCedula.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCedula.requestFocus();
            ediCedula.setError("Este espacio es obligatorio");

            return false;

        }

        if(ediCelular.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCelular.requestFocus();
            ediCelular.setError("Este espacio es obligatorio");

            return false;

        }

        if(ediPLaca.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediPLaca.requestFocus();
            ediPLaca.setError("Este espacio es obligatorio");
            return false;

        }






        return true;

    }




    private boolean checkDatosHaciendaIsLleno(){
        LinearLayout layoutContainerSeccion8=findViewById(R.id.layoutContainerSeccion8);

        if(layoutContainerSeccion8.getVisibility()== GONE){
            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
        }


        if(ediFuenteAgua.getText().toString().equalsIgnoreCase("ninguna")){ //chekamos que no este vacia
            ediFuenteAgua.requestFocus();
            ediFuenteAgua.setError("Selecione una opcion");

            return false;

        }




        if(ediLavadoRacimos.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediLavadoRacimos.requestFocus();
            ediLavadoRacimos.setError("Este espacio es obligatorio");

            return false;

        }

        if(ediFumigacionClin1.getText().toString().equalsIgnoreCase("ninguna")){ //chekamos que no este vacia
            ediFumigacionClin1.requestFocus();
            ediFumigacionClin1.setError("Selecione una opcion");

            return false;

        }



        if(ediTipoBoquilla.getText().toString().equalsIgnoreCase("ninguna")){ //chekamos que no este vacia
            ediTipoBoquilla.requestFocus();
            ediTipoBoquilla.setError("Selecione una opcion");

            return false;

        }


        if(ediCajasProcDesp.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCajasProcDesp.requestFocus();
            ediCajasProcDesp.setError("Este espacio es obligatorio");
            return false;

        }



        if(ediRacimosCosech.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediRacimosCosech.requestFocus();
            ediRacimosCosech.setError("Este espacio es obligatorio");

            return false;

        }

        if(ediRacimosRecha.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediRacimosRecha.requestFocus();
            ediRacimosRecha.setError("Este espacio es obligatorio");

            return false;
        }

        if(ediRacimProces.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediRacimProces.requestFocus();
            ediRacimProces.setError("Este espacio es obligatorio");

            return false;

        }

        ///vamos con los datos de semananas y eso




        return true;

    }

    private boolean checkDataCalibFrutaCalEnfn(){

        //le decimos que esta todo bien y omitiremos estos datos....
        return true;

    }


    private boolean chekDaTaEvaluador(){


        if(ediExtCalid.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediExtCalid.requestFocus();
            ediExtCalid.setError("Este espacio es obligatorio");

          //  layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediExtCalidCi.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediExtCalidCi.requestFocus();
            ediExtCalidCi.setError("Este espacio es obligatorio");

            //  layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

/*
        if(ediExtRodillo.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediExtRodillo.requestFocus();
            ediExtRodillo.setError("Este espacio es obligatorio");

            //  layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediExtRodilloCi.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediExtRodilloCi.requestFocus();
            ediExtRodilloCi.setError("Este espacio es obligatorio");

            //  layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediExtGancho.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediExtGancho.requestFocus();
            ediExtGancho.setError("Este espacio es obligatorio");

            //  layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(ediExtGanchoCi.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediExtGanchoCi.requestFocus();
            ediExtGanchoCi.setError("Este espacio es obligatorio");

            //  layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

*/


        return true;
    }



    private boolean checkDatosProcesoIsLleno(){
        LinearLayout layoutContainerSeccion7=findViewById(R.id.layoutContainerDatsProceso);
        ///CHEKEAMOS DATA seccion CONTENEDOR

        if(layoutContainerSeccion7.getVisibility()== GONE){
            layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
        }


        if(ediCondicionBalanza.getText().toString().equalsIgnoreCase("ninguna")){ //chekamos que no este vacia
            ediCondicionBalanza.requestFocus();
            ediCondicionBalanza.setError("Selecione una opcion");

            return false;

        }

        if(ediTipodeCaja.getText().toString().equalsIgnoreCase("ninguna")){ //chekamos que no este vacia
            ediTipodeCaja.requestFocus();
            ediTipodeCaja.setError("Selecione una opcion");

            return false;

        }


        if(ediTipoPlastico.getText().toString().equalsIgnoreCase("ninguna")){ //chekamos que no este vacia
            ediTipoPlastico.requestFocus();
            ediTipoPlastico.setError("Selecione una opcion");

            return false;

        }



        if(ediTipoBalanza.getText().toString().equalsIgnoreCase("ninguna")){ //chekamos que no este vacia
            ediTipoBalanza.requestFocus();
            ediTipoBalanza.setError("Selecione una opcion");

            return false;

        }


/*
        if(editipbalanzaRepeso.getText().toString().isEmpty()){ //chekamos que no este vacia
            editipbalanzaRepeso.requestFocus();
            editipbalanzaRepeso.setError("Este espacio es obligatorio");

            layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediUbicacionBalanza.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediUbicacionBalanza.requestFocus();
            ediUbicacionBalanza.setError("Este espacio es obligatorio");

            layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }
*/

        return true;

    }


    private void  addProdcutsPostCosechaAndUpload(){

        ProductPostCosecha producto=new ProductPostCosecha(UNIQUE_ID_iNFORME);
        //creamos un array de editext

        EditText [] editextArray = {ediPPC01,ediPPC02,ediPPC03,ediPPC04,ediPPC05,ediPPC06,ediPPC07,
                ediPPC08,ediPPC09, ediPPC010,ediPPC011,ediPPC012,ediPPC013,ediPPC014,ediPPC015,ediPPC016} ;


        for (int indice =0; indice<editextArray.length; indice++) {
            EditText currentEditext=editextArray[indice];
            if (!currentEditext.getText().toString().isEmpty()){ //si no esta vacioo
                if (!currentEditext.getText().toString().trim().isEmpty())  //si no es un espacio vacio
                {

                    switch (currentEditext.getId()){

                        case R.id.ediPPC01:
                            producto.alumbre=currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC02:
                            producto.bc100=currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC03:
                            producto.sb100=currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC04:
                            producto.eclipse=currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC05:
                            producto.acido_citrico=currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC06:
                            producto.biottol=currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC07:
                            producto.bromorux=currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC08:
                            producto.ryzuc=currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC09:
                            producto.mertec=currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC010:
                            producto.sastifar=currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC011:
                            producto.xtrata=currentEditext.getText().toString();
                            break;


                        case R.id.ediPPC012:
                            producto.nlarge=currentEditext.getText().toString();
                            break;


                        case R.id.ediPPC013:
                            producto.gib_bex=currentEditext.getText().toString();
                            break;



                        case R.id.ediPPC014:
                            producto.cloro=currentEditext.getText().toString();
                            break;


                        case R.id.ediPPC015:
                            producto.otro_especifique=currentEditext.getText().toString();
                            break;


                        case R.id.ediPPC016:
                            producto.cantidadOtro=currentEditext.getText().toString();
                            break;


                    }

                }


            }

            //si el editext tiene data lo corregimos usando la propiedad hint


        }



        RealtimeDB.UploadProductosPostCosecha(producto);


    }

    private void  addCalibracionFutaC_enfAndUpload(){

        //recorremos un array de editext y creamos un objeto de tipo CalibrFrutCalEnf..
        //si no tiene data agregamos cero u comillas...

        CalibrFrutCalEnf calibrFrutCalEnf=new CalibrFrutCalEnf(UNIQUE_ID_iNFORME);
        //creamos un array de editext
        //editext here
        EditText ediColorSem14,ediColortSem13,ediColortSem12,ediColortSem11,ediColortSem10,ediColortSem9;
        EditText  ediNumRcim14,ediNumRcim13,ediNumRcim12,ediNumRcim11,ediNumRcim10,ediNumRac9;


        //findviewsid
        ediColorSem14=findViewById(R.id.ediColortSem14);
        ediColortSem13 =findViewById(R.id.ediColortSem13);
        ediColortSem12=findViewById(R.id.ediColortSem12);
        ediColortSem11=findViewById(R.id.ediColortSem11);
        ediColortSem10=findViewById(R.id.ediColortSem10);
        ediColortSem9=findViewById(R.id.ediColortSem9);

        ediNumRcim14=findViewById(R.id.ediNumRcim14);
        ediNumRcim13=findViewById(R.id.ediNumRcim13);
        ediNumRcim12=findViewById(R.id.ediNumRcim12);
        ediNumRcim11=findViewById(R.id.ediNumRcim11);
        ediNumRcim10=findViewById(R.id.ediNumRcim10);
        ediNumRac9=findViewById(R.id.ediNumRac9);


        EditText [] editextArrayColorSeman = {ediColorSem14,ediColortSem13,ediColortSem12,ediColortSem11,ediColortSem10,ediColortSem9} ;
        EditText [] editextNumRacimsArray =           {ediNumRcim14,ediNumRcim13,ediNumRcim12,ediNumRcim11,ediNumRcim10,ediNumRac9} ;


        for (int indice =0; indice<editextArrayColorSeman.length; indice++) {
            EditText currentEditextColorSem=editextArrayColorSeman[indice];
            EditText currentEditextNumRacims=editextNumRacimsArray[indice];

            if (!currentEditextColorSem.getText().toString().isEmpty()){ //si no esta vacioo
                if (!currentEditextColorSem.getText().toString().trim().isEmpty())  //si no es un espacio vacio
                {

                    switch (currentEditextColorSem.getId()){


                        case R.id.ediColortSem14:
                            calibrFrutCalEnf.setColorSemana14(currentEditextColorSem.getText().toString());

                            //AQUI EL PARSE

                            calibrFrutCalEnf.setNumeracionRacimosSem14(Integer.parseInt(currentEditextNumRacims.getText().toString()));

                            break;
                        case R.id.ediColortSem13:
                            calibrFrutCalEnf.setColorSemana13(currentEditextColorSem.getText().toString());


                            calibrFrutCalEnf.setNumeracionRacimosSem13(Integer.parseInt(currentEditextNumRacims.getText().toString()));

                            break;

                        case R.id.ediColortSem12:
                            calibrFrutCalEnf.setColorSemana12(currentEditextColorSem.getText().toString());
                            calibrFrutCalEnf.setNumeracionRacimosSem12(Integer.parseInt(currentEditextNumRacims.getText().toString()));

                            break;

                        case R.id.ediColortSem11:
                            calibrFrutCalEnf.setColorSemana11(currentEditextColorSem.getText().toString());
                            calibrFrutCalEnf.setNumeracionRacimosSem11(Integer.parseInt(currentEditextNumRacims.getText().toString()));

                            break;
                        case R.id.ediColortSem10:
                            calibrFrutCalEnf.setColorSemana10(currentEditextColorSem.getText().toString());
                            calibrFrutCalEnf.setNumeracionRacimosSem10(Integer.parseInt(currentEditextNumRacims.getText().toString()));

                            break;
                        case R.id.ediColortSem9:
                            calibrFrutCalEnf.setColorSemana9(currentEditextColorSem.getText().toString());
                            calibrFrutCalEnf.setNumeracionRacimosSem9(Integer.parseInt(currentEditextNumRacims.getText().toString()));

                            break;

                    }

                }


            }

            //si el editext tiene data lo corregimos usando la propiedad hint


        }



        //actualizamos solo si ya existe un objeto descargado calEnfundeGLOB.. si es nulo ,signica que no existe


        if(calEnfundeGLOB!=null){

            RealtimeDB.UpdateCalibracionFrutCal(calibrFrutCalEnf,calEnfundeGLOB.getKeyFirebase());

               Log.i("somewrlals","es difrente de nulo  lo actualizamos");

        } else{
            Log.i("somewrlals","es nulo crearemos now ");

            RealtimeDB.UploadCalibracionFrutCal(calibrFrutCalEnf);


        }



    }





//upload data...

    //descragamos el ultimo
//Si hay un formulario ... que no se envio aun.....estado subido..
//si hay un formulario obtenerlo..
    //una propiedad que diga si ya lo subio...
    ///el primer valor del map conttendra esa propiedad...
    private TextInputEditText[] creaArryOfTextInputEditText() {

        TextInputEditText [] arrayEditex = {

                ediSemana,
                ediFecha,
                ediProductor,
                ediHacienda,
                ediCodigo,
                ediInscirpMagap,
                ediPemarque,
                ediZona,
                ediHoraInicio,
                ediHoraTermino,
                ediNguiaRemision,
                edi_nguia_transporte,
                ediNtargetaEmbarque,
                ediNhojaEvaluacion,
                ediObservacion,
                ediEmpacadora,
                ediContenedor,
                ediPPC01,
                ediPPC02,
                ediPPC03,
                ediPPC04,
                ediPPC05,
                ediPPC06,
                ediPPC07,
                ediPPC08,
                ediPPC09,
                ediPPC010,
                ediPPC011,
                ediPPC012,
                ediPPC013,
                ediPPC014,
                ediPPC015,
                ediPPC016,
                ediNViaje,
                ediEnsunchado,
                ediBalanzaRepeso,


                ediBalanza,
                ediFuenteAgua,
                ediAguaCorrida,
                ediLavadoRacimos,
                ediFumigacionClin1,
                ediTipoBoquilla,
                ediCajasProcDesp,
                ediRacimosCosech,
                ediRacimosRecha,
                ediRacimProces,


                ediNombreChofer,
                ediCedula,
                ediCelular,
                ediPLaca,
                ediCondicionBalanza,
                ediTipodeCaja,
                ediTipoPlastico,
                ediTipoBalanza,
                editipbalanzaRepeso,
              //  ediUbicacionBalanza,
                ediUbicacion1,
                ediRuma1,
                ediTermofrafo2,
                ediHoraEncendido2,
                ediUbicacion2,
                ediCandadoQsercom,






        } ;


        return arrayEditex;
    }


    void addInfotomap(ArrayList<ImagenReport>listImagenReports){
        ImagenReport.hashMapImagesData= new HashMap<>();

        //agregamos adata al mapusnado un bucle

        for(int indice2=0; indice2<listImagenReports.size(); indice2++){

            ImagenReport currentImareportObj=listImagenReports.get(indice2);

            ImagenReport.hashMapImagesData.put(currentImareportObj.getUniqueIdNamePic(),currentImareportObj);

        }



        Variables.hashMapImagesStart=new HashMap<String, ImagenReport>();



        if (!Variables.copiamosData) {
            // Variables.hashMapImagesStart =ImagenReport.hashMapImagesData;

            //CREAMOS UNA COPIA USANDO UN BUCLE

            Variables.hashMapImagesStart = new HashMap<>();


            for (Map.Entry<String, ImagenReport> entry : ImagenReport.hashMapImagesData.entrySet()) {
                String key = entry.getKey();
                ImagenReport value = entry.getValue();

                Variables.hashMapImagesStart.put(key, value);
                // ...
            }

            Variables.copiamosData = true;


        }



    }

    void addImagesInRecyclerviews(ArrayList<ImagenReport>listImagenReports){

        //agregamos data al map


        RecyclerView recyclerView=null;


        switch(currentTypeImage){
            case Variables.FOTO_PROCESO_FRUTA_FINCA:
                recyclerView= findViewById(R.id.recyclerFotoProcesoFrEnFinca);
                break;


            case Variables.FOTO_CIERRE_CONTENEDOR:
                recyclerView= findViewById(R.id.recyclerFotoCierreCtendr);
                break;

            case Variables.FOTO_DOCUMENTACION:
                recyclerView= findViewById(R.id.recyclerFotoDocumentacion);
                break;


        }

        RecyclerViewAdapter adapter=new RecyclerViewAdapter(listImagenReports,this);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);


        // at last set adapter to recycler view.


        if(recyclerView!=null){

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
            eventoBtnclicklistenerDelete(adapter);

        }


    }



    void createlistsForReciclerviewsImages(ArrayList<ImagenReport>listImagenReports){

        ArrayList<ImagenReport>lisFiltrada;

        int [] arrayTiposImagenes = {Variables.FOTO_LLEGADA_CONTENEDOR,Variables.FOTO_SELLO_LLEGADA,
                Variables.FOTO_PUERTA_ABIERTA_DEL_CONTENENEDOR,Variables.FOTO_PALLETS,Variables.FOTO_CIERRE_CONTENEDOR,
                Variables.FOTO_DOCUMENTACION};


        for(int indice=0; indice<arrayTiposImagenes.length; indice++){

            lisFiltrada=new ArrayList<>();

            for(int indice2=0; indice2<listImagenReports.size(); indice2++){

                if(listImagenReports.get(indice2).getTipoImagenCategory()==arrayTiposImagenes[indice]){ //entonces usamos este

                    lisFiltrada.add(listImagenReports.get(indice2));


                }

            }

            currentTypeImage=arrayTiposImagenes[indice];
            //lalamos el recicler que
            addImagesInRecyclerviews(lisFiltrada);


        }


        Variables.modoRecicler=Variables.SELEC_AND_TAKE_iMAGES;

        //  addInfotomap(listImagenReports);


    }




    private void calibracionFutaCalendarioEnfunde(){

        TextInputEditText ediCandadoqsercon;


    }



    /*
    private void updateOrUploadNewHashmapPesoBrutoCloster2y3l(String keyNodoToUpload){

        boolean sixisteUnHasmapDescargado=true;


        TextInputEditText mPbCluster01 = findViewById(R.id.pbCluster01);
        TextInputEditText mPbCluster02 = findViewById(R.id.pbCluster02);
        TextInputEditText mPbCluster03 = findViewById(R.id.pbCluster03);
        TextInputEditText mPbCluster04 = findViewById(R.id.pbCluster04);
        TextInputEditText mPbCluster05 = findViewById(R.id.pbCluster05);
        TextInputEditText mPbCluster06 = findViewById(R.id.pbCluster06);
        TextInputEditText mPbCluster07 = findViewById(R.id.pbCluster07);
        TextInputEditText mPbCluster08 = findViewById(R.id.pbCluster08);
        TextInputEditText mPbCluster09 = findViewById(R.id.pbCluster09);
        TextInputEditText mPbCluster010 = findViewById(R.id.pbCluster010);
        TextInputEditText mPbCluster011 = findViewById(R.id.pbCluster011);
        TextInputEditText mPbCluster012 = findViewById(R.id.pbCluster012);
        TextInputEditText mPbCluster013 = findViewById(R.id.pbCluster013);
        TextInputEditText mPbCluster014 = findViewById(R.id.pbCluster014);
        TextInputEditText mPbCluster015 = findViewById(R.id.pbCluster015);
        TextInputEditText mPbCluster016 = findViewById(R.id.pbCluster016);
        TextInputEditText mPbCluster017 = findViewById(R.id.pbCluster017);
        TextInputEditText mPbCluster018 = findViewById(R.id.pbCluster018);
        TextInputEditText mPbCluster019 = findViewById(R.id.pbCluster019);
        TextInputEditText mPbCluster020 = findViewById(R.id.pbCluster020);
        TextInputEditText mPbCluster021 = findViewById(R.id.pbCluster021);
        TextInputEditText mPbCluster022 = findViewById(R.id.pbCluster022);
        TextInputEditText mPbCluster023 = findViewById(R.id.pbCluster023);
        TextInputEditText mPbCluster024 = findViewById(R.id.pbCluster024);
        TextInputEditText mPbCluster025 = findViewById(R.id.pbCluster025);
        TextInputEditText mPbCluster026 = findViewById(R.id.pbCluster026);
        TextInputEditText mPbCluster027 = findViewById(R.id.pbCluster027);
        TextInputEditText mPbCluster028 = findViewById(R.id.pbCluster028);
        TextInputEditText mPbCluster029 = findViewById(R.id.pbCluster029);
        TextInputEditText mPbCluster030 = findViewById(R.id.pbCluster030);
        TextInputEditText mPbCluster031 = findViewById(R.id.pbCluster031);
        TextInputEditText mPbCluster032 = findViewById(R.id.pbCluster032);
        TextInputEditText mPbCluster033 = findViewById(R.id.pbCluster033);
        TextInputEditText mPbCluster034 = findViewById(R.id.pbCluster034);
        TextInputEditText mPbCluster035 = findViewById(R.id.pbCluster035);
        TextInputEditText mPbCluster036 = findViewById(R.id.pbCluster036);
        TextInputEditText mPbCluster037 = findViewById(R.id.pbCluster037);
        TextInputEditText mPbCluster038 = findViewById(R.id.pbCluster038);
        TextInputEditText mPbCluster039 = findViewById(R.id.pbCluster039);
        TextInputEditText mPbCluster040 = findViewById(R.id.pbCluster040);
        TextInputEditText mPbCluster041 = findViewById(R.id.pbCluster041);
        TextInputEditText mPbCluster042 = findViewById(R.id.pbCluster042);
        TextInputEditText mPbCluster043 = findViewById(R.id.pbCluster043);
        TextInputEditText mPbCluster044 = findViewById(R.id.pbCluster044);
        TextInputEditText mPbCluster045 = findViewById(R.id.pbCluster045);
        TextInputEditText mPbCluster046 = findViewById(R.id.pbCluster046);
        TextInputEditText mPbCluster047 = findViewById(R.id.pbCluster047);
        TextInputEditText mPbCluster048 = findViewById(R.id.pbCluster048);
        TextInputEditText mPbCluster049 = findViewById(R.id.pbCluster049);
        TextInputEditText mPbCluster050 = findViewById(R.id.pbCluster050);


        TextInputEditText mP2pbCluster01 = findViewById(R.id.p2pbCluster01);
        TextInputEditText mP2pbCluster02 = findViewById(R.id.p2pbCluster02);
        TextInputEditText mP2pbCluster03 = findViewById(R.id.p2pbCluster03);
        TextInputEditText mP2pbCluster04 = findViewById(R.id.p2pbCluster04);
        TextInputEditText mP2pbCluster05 = findViewById(R.id.p2pbCluster05);
        TextInputEditText mP2pbCluster06 = findViewById(R.id.p2pbCluster06);
        TextInputEditText mP2pbCluster07 = findViewById(R.id.p2pbCluster07);
        TextInputEditText mP2pbCluster08 = findViewById(R.id.p2pbCluster08);
        TextInputEditText mP2pbCluster09 = findViewById(R.id.p2pbCluster09);
        TextInputEditText mP2pbCluster010 = findViewById(R.id.p2pbCluster010);
        TextInputEditText mP2pbCluster011 = findViewById(R.id.p2pbCluster011);
        TextInputEditText mP2pbCluster012 = findViewById(R.id.p2pbCluster012);
        TextInputEditText mP2pbCluster013 = findViewById(R.id.p2pbCluster013);
        TextInputEditText mP2pbCluster014 = findViewById(R.id.p2pbCluster014);
        TextInputEditText mP2pbCluster015 = findViewById(R.id.p2pbCluster015);
        TextInputEditText mP2pbCluster016 = findViewById(R.id.p2pbCluster016);
        TextInputEditText mP2pbCluster017 = findViewById(R.id.p2pbCluster017);
        TextInputEditText mP2pbCluster018 = findViewById(R.id.p2pbCluster018);
        TextInputEditText mP2pbCluster019 = findViewById(R.id.p2pbCluster019);
        TextInputEditText mP2pbCluster020 = findViewById(R.id.p2pbCluster020);
        TextInputEditText mP2pbCluster021 = findViewById(R.id.p2pbCluster021);
        TextInputEditText mP2pbCluster022 = findViewById(R.id.p2pbCluster022);
        TextInputEditText mP2pbCluster023 = findViewById(R.id.p2pbCluster023);
        TextInputEditText mP2pbCluster024 = findViewById(R.id.p2pbCluster024);
        TextInputEditText mP2pbCluster025 = findViewById(R.id.p2pbCluster025);
        TextInputEditText mP2pbCluster026 = findViewById(R.id.p2pbCluster026);
        TextInputEditText mP2pbCluster027 = findViewById(R.id.p2pbCluster027);
        TextInputEditText mP2pbCluster028 = findViewById(R.id.p2pbCluster028);
        TextInputEditText mP2pbCluster029 = findViewById(R.id.p2pbCluster029);
        TextInputEditText mP2pbCluster030 = findViewById(R.id.p2pbCluster030);
        TextInputEditText mP2pbCluster031 = findViewById(R.id.p2pbCluster031);
        TextInputEditText mP2pbCluster032 = findViewById(R.id.p2pbCluster032);
        TextInputEditText mP2pbCluster033 = findViewById(R.id.p2pbCluster033);
        TextInputEditText mP2pbCluster034 = findViewById(R.id.p2pbCluster034);
        TextInputEditText mP2pbCluster035 = findViewById(R.id.p2pbCluster035);
        TextInputEditText mP2pbCluster036 = findViewById(R.id.p2pbCluster036);
        TextInputEditText mP2pbCluster037 = findViewById(R.id.p2pbCluster037);
        TextInputEditText mP2pbCluster038 = findViewById(R.id.p2pbCluster038);
        TextInputEditText mP2pbCluster039 = findViewById(R.id.p2pbCluster039);
        TextInputEditText mP2pbCluster040 = findViewById(R.id.p2pbCluster040);
        TextInputEditText mP2pbCluster041 = findViewById(R.id.p2pbCluster041);
        TextInputEditText mP2pbCluster042 = findViewById(R.id.p2pbCluster042);
        TextInputEditText mP2pbCluster043 = findViewById(R.id.p2pbCluster043);
        TextInputEditText mP2pbCluster044 = findViewById(R.id.p2pbCluster044);
        TextInputEditText mP2pbCluster045 = findViewById(R.id.p2pbCluster045);
        TextInputEditText mP2pbCluster046 = findViewById(R.id.p2pbCluster046);
        TextInputEditText mP2pbCluster047 = findViewById(R.id.p2pbCluster047);
        TextInputEditText mP2pbCluster048 = findViewById(R.id.p2pbCluster048);
        TextInputEditText mP2pbCluster049 = findViewById(R.id.p2pbCluster049);
        TextInputEditText mP2pbCluster050 = findViewById(R.id.p2pbCluster050);



          TextInputEditText[]arraYedistClusters={
         mPbCluster01, mPbCluster02, mPbCluster03, mPbCluster04, mPbCluster05, mPbCluster06, mPbCluster07, mPbCluster08, mPbCluster09, mPbCluster010,
          mPbCluster011, mPbCluster012, mPbCluster013, mPbCluster014, mPbCluster015, mPbCluster016, mPbCluster017, mPbCluster018, mPbCluster019,
                  mPbCluster020, mPbCluster021, mPbCluster022, mPbCluster023, mPbCluster024, mPbCluster025, mPbCluster026, mPbCluster027,
                  mPbCluster028, mPbCluster029, mPbCluster030, mPbCluster031, mPbCluster032, mPbCluster033, mPbCluster034, mPbCluster035,
                  mPbCluster036, mPbCluster037, mPbCluster038, mPbCluster039, mPbCluster040, mPbCluster041, mPbCluster042, mPbCluster043,
                  mPbCluster044, mPbCluster045, mPbCluster046, mPbCluster047, mPbCluster048, mPbCluster049, mPbCluster050,

                  mP2pbCluster01, mP2pbCluster02, mP2pbCluster03, mP2pbCluster04, mP2pbCluster05, mP2pbCluster06, mP2pbCluster07,
                  mP2pbCluster08, mP2pbCluster09, mP2pbCluster010, mP2pbCluster011, mP2pbCluster012, mP2pbCluster013,
                  mP2pbCluster014, mP2pbCluster015, mP2pbCluster016, mP2pbCluster017, mP2pbCluster018, mP2pbCluster019,
                  mP2pbCluster020, mP2pbCluster021, mP2pbCluster022, mP2pbCluster023, mP2pbCluster024, mP2pbCluster025,
                  mP2pbCluster026, mP2pbCluster027, mP2pbCluster028, mP2pbCluster029, mP2pbCluster030, mP2pbCluster031,
                  mP2pbCluster032, mP2pbCluster033, mP2pbCluster034, mP2pbCluster035, mP2pbCluster036, mP2pbCluster037,
                  mP2pbCluster038, mP2pbCluster039, mP2pbCluster040, mP2pbCluster041, mP2pbCluster042, mP2pbCluster043,
                  mP2pbCluster044, mP2pbCluster045, mP2pbCluster046, mP2pbCluster047, mP2pbCluster048, mP2pbCluster049, mP2pbCluster050,

          };


          if(hasmapPesoBrutoClosters2y3L == null){
              sixisteUnHasmapDescargado=false;
              hasmapPesoBrutoClosters2y3L=new HashMap<>();

          }


          for(int indice=0; indice< arraYedistClusters.length; indice++){
              String keyOfView=String.valueOf(arraYedistClusters[indice].getId());
              String valor=arraYedistClusters[indice].getText().toString();

              //chekea que haya contenido
              if(!valor.trim().isEmpty()){
                  float valorFlotante=Float.parseFloat(valor);
                  hasmapPesoBrutoClosters2y3L.put(keyOfView,valorFlotante);


              }


          }



        if(sixisteUnHasmapDescargado){


            RealtimeDB.UpdateHasmapPesoBrutoClosters2y3L(hasmapPesoBrutoClosters2y3L,keyNodoToUpload);



        }

        else{

            // RealtimeDB.initDatabasesRootOnly();
            // String nodoDeb=RealtimeDB.rootDatabaseReference.push().getKey().toString();
            RealtimeDB.addNewhasmapPesoBrutoClosters2y3L(hasmapPesoBrutoClosters2y3L,keyNodoToUpload);

            Log.i("damsddas","llamoas this metod upload");

          //  RealtimeDB.addNewhasmapPesoBrutoClosters2y3L(hasmapPesoBrutoClosters2y3L,nodoDeb);

        }




    }

*/



    void dowLoadProducsPostC(String idAlquePERTENECE){

        // DatabaseReference midatabase=rootDatabaseReference.child("Informes").child("listInformes");
        Query query = RealtimeDB.rootDatabaseReference.child("Informes").
                child("listProductosPostCosecha").
                orderByChild("idpertenece").equalTo(idAlquePERTENECE);


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Map<String, Object> map = null;
                //  Map<String, String> map = dataSnapshot.getValue(Map.class);
                //  Log.i("sliexsa","el size de map es "+map.size());


                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    productxGlobal=ds.getValue(ProductPostCosecha.class);
                }


                if (productxGlobal != null) {


                    Variables.currenProductPostCosecha = productxGlobal;
                    setProductosPostcosecha(productxGlobal);


                }



                // createlistsForReciclerviewsImages(listImagenData);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }



    void setProductosPostcosecha( ProductPostCosecha objProducto) {


       // Log.i("dowloadxs","el producto postocescha yea "+objProducto.);

        ediPPC01.setText(objProducto.alumbre);
        ediPPC02.setText(objProducto.bc100);
        ediPPC03.setText(objProducto.sb100);
        ediPPC04.setText(objProducto.eclipse);
        ediPPC05.setText(objProducto.acido_citrico);
        ediPPC06.setText(objProducto.biottol);
        ediPPC07.setText(objProducto.bromorux);
        ediPPC08.setText(objProducto.ryzuc);
        ediPPC09.setText(objProducto.mertec);
        ediPPC010.setText(objProducto.sastifar);
        ediPPC011.setText(objProducto.xtrata);
        ediPPC012.setText(objProducto.nlarge);
        ediPPC013.setText(objProducto.gib_bex);
        ediPPC014.setText(objProducto.cloro);
        ediPPC015.setText(objProducto.otro_especifique);
        ediPPC016.setText(objProducto.cantidadOtro);



    }



    void dowLoadMapPesoBrutoCloster2y3l(String nodeLocationMap) {
            ///Log.i("hameha","el NODEKey es : "+nodeKePackinGList);

            ValueEventListener seenListener = RealtimeDB.rootDatabaseReference.child("Informes").child("MapsPesoBrutoCloster2y3l").child(nodeLocationMap).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    hasmapPesoBrutoClosters2y3L =new HashMap<>();


                    for (DataSnapshot dss : dataSnapshot.getChildren()) {
                        String key = dss.getKey();

                        Float  fieldData =dss.getValue(Float.class);


                        if (fieldData!=null) {///

                            hasmapPesoBrutoClosters2y3L.put(key,fieldData);



                        }

                        Log.i("simener","el size de lis now es "+hasmapPesoBrutoClosters2y3L.size());

                        setDataLibriado(hasmapPesoBrutoClosters2y3L);

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.i("misadhd","el error es "+ databaseError.getMessage());



                }
            });




        }



    void  setDataLibriado(HashMap<String,Float>miMap){


        EditText        pbCluster01 = findViewById(R.id.pbCluster01);
        EditText        pbCluster05 = findViewById(R.id.pbCluster05);
        EditText        pbCluster03 = findViewById(R.id.pbCluster03);
        EditText        pbCluster02 = findViewById(R.id.pbCluster02);
        EditText        pbCluster04 = findViewById(R.id.pbCluster04);
        EditText        pbCluster010 = findViewById(R.id.pbCluster010);
        EditText        pbCluster09 = findViewById(R.id.pbCluster09);
        EditText        pbCluster07 = findViewById(R.id.pbCluster07);
        EditText        pbCluster08 = findViewById(R.id.pbCluster08);
        EditText        pbCluster06 = findViewById(R.id.pbCluster06);
        EditText        pbCluster011 = findViewById(R.id.pbCluster011);
        EditText        pbCluster015 = findViewById(R.id.pbCluster015);
        EditText        pbCluster012 = findViewById(R.id.pbCluster012);
        EditText        pbCluster013 = findViewById(R.id.pbCluster013);
        EditText        pbCluster014 = findViewById(R.id.pbCluster014);
        EditText        pbCluster016 = findViewById(R.id.pbCluster016);
        EditText        pbCluster019 = findViewById(R.id.pbCluster019);
        EditText        pbCluster018 = findViewById(R.id.pbCluster018);
        EditText        pbCluster020 = findViewById(R.id.pbCluster020);
        EditText        pbCluster017 = findViewById(R.id.pbCluster017);
        EditText        pbCluster025 = findViewById(R.id.pbCluster025);
        EditText        pbCluster024 = findViewById(R.id.pbCluster024);
        EditText        pbCluster023 = findViewById(R.id.pbCluster023);
        EditText        pbCluster022 = findViewById(R.id.pbCluster022);
        EditText        pbCluster021 = findViewById(R.id.pbCluster021);
        EditText        pbCluster028 = findViewById(R.id.pbCluster028);
        EditText        pbCluster027 = findViewById(R.id.pbCluster027);
        EditText        pbCluster029 = findViewById(R.id.pbCluster029);
        EditText        pbCluster026 = findViewById(R.id.pbCluster026);
        EditText        pbCluster030 = findViewById(R.id.pbCluster030);
        EditText        pbCluster034 = findViewById(R.id.pbCluster034);
        EditText        pbCluster031 = findViewById(R.id.pbCluster031);
        EditText        pbCluster035 = findViewById(R.id.pbCluster035);
        EditText        pbCluster033 = findViewById(R.id.pbCluster033);
        EditText        pbCluster032 = findViewById(R.id.pbCluster032);
        EditText        pbCluster039 = findViewById(R.id.pbCluster039);
        EditText        pbCluster040 = findViewById(R.id.pbCluster040);
        EditText        pbCluster037 = findViewById(R.id.pbCluster037);
        EditText        pbCluster038 = findViewById(R.id.pbCluster038);
        EditText        pbCluster036 = findViewById(R.id.pbCluster036);
        EditText        pbCluster043 = findViewById(R.id.pbCluster043);
        EditText        pbCluster045 = findViewById(R.id.pbCluster045);
        EditText        pbCluster042 = findViewById(R.id.pbCluster042);
        EditText        pbCluster041 = findViewById(R.id.pbCluster041);
        EditText        pbCluster044 = findViewById(R.id.pbCluster044);
        EditText        pbCluster048 = findViewById(R.id.pbCluster048);
        EditText        pbCluster046 = findViewById(R.id.pbCluster046);
        EditText        pbCluster050 = findViewById(R.id.pbCluster050);
        EditText        pbCluster047 = findViewById(R.id.pbCluster047);
        EditText        pbCluster049 = findViewById(R.id.pbCluster049);
        EditText        p2pbCluster01 = findViewById(R.id.p2pbCluster01);
        EditText        p2pbCluster05 = findViewById(R.id.p2pbCluster05);
        EditText        p2pbCluster03 = findViewById(R.id.p2pbCluster03);
        EditText        p2pbCluster02 = findViewById(R.id.p2pbCluster02);
        EditText        p2pbCluster04 = findViewById(R.id.p2pbCluster04);
        EditText        p2pbCluster010 = findViewById(R.id.p2pbCluster010);
        EditText        p2pbCluster09 = findViewById(R.id.p2pbCluster09);
        EditText        p2pbCluster07 = findViewById(R.id.p2pbCluster07);
        EditText        p2pbCluster08 = findViewById(R.id.p2pbCluster08);
        EditText        p2pbCluster06 = findViewById(R.id.p2pbCluster06);
        EditText        p2pbCluster011 = findViewById(R.id.p2pbCluster011);
        EditText        p2pbCluster015 = findViewById(R.id.p2pbCluster015);
        EditText        p2pbCluster012 = findViewById(R.id.p2pbCluster012);
        EditText        p2pbCluster013 = findViewById(R.id.p2pbCluster013);
        EditText        p2pbCluster014 = findViewById(R.id.p2pbCluster014);
        EditText        p2pbCluster016 = findViewById(R.id.p2pbCluster016);
        EditText        p2pbCluster019 = findViewById(R.id.p2pbCluster019);
        EditText        p2pbCluster018 = findViewById(R.id.p2pbCluster018);
        EditText        p2pbCluster020 = findViewById(R.id.p2pbCluster020);
        EditText        p2pbCluster017 = findViewById(R.id.p2pbCluster017);
        EditText        p2pbCluster025 = findViewById(R.id.p2pbCluster025);
        EditText        p2pbCluster024 = findViewById(R.id.p2pbCluster024);
        EditText        p2pbCluster023 = findViewById(R.id.p2pbCluster023);
        EditText        p2pbCluster022 = findViewById(R.id.p2pbCluster022);
        EditText        p2pbCluster021 = findViewById(R.id.p2pbCluster021);
        EditText        p2pbCluster028 = findViewById(R.id.p2pbCluster028);
        EditText        p2pbCluster027 = findViewById(R.id.p2pbCluster027);
        EditText        p2pbCluster029 = findViewById(R.id.p2pbCluster029);
        EditText        p2pbCluster026 = findViewById(R.id.p2pbCluster026);
        EditText        p2pbCluster030 = findViewById(R.id.p2pbCluster030);
        EditText        p2pbCluster034 = findViewById(R.id.p2pbCluster034);
        EditText        p2pbCluster031 = findViewById(R.id.p2pbCluster031);
        EditText        p2pbCluster035 = findViewById(R.id.p2pbCluster035);
        EditText        p2pbCluster033 = findViewById(R.id.p2pbCluster033);
        EditText        p2pbCluster032 = findViewById(R.id.p2pbCluster032);
        EditText        p2pbCluster039 = findViewById(R.id.p2pbCluster039);
        EditText        p2pbCluster040 = findViewById(R.id.p2pbCluster040);
        EditText        p2pbCluster037 = findViewById(R.id.p2pbCluster037);
        EditText        p2pbCluster038 = findViewById(R.id.p2pbCluster038);
        EditText        p2pbCluster036 = findViewById(R.id.p2pbCluster036);


        EditText [] miArray= {
                pbCluster01, pbCluster05, pbCluster03, pbCluster02, pbCluster04, pbCluster010, pbCluster09, pbCluster07, pbCluster08, pbCluster06, pbCluster011,
                pbCluster015, pbCluster012, pbCluster013, pbCluster014, pbCluster016, pbCluster019, pbCluster018, pbCluster020, pbCluster017, pbCluster025,
                pbCluster024 ,pbCluster023, pbCluster022, pbCluster021, pbCluster028, pbCluster027, pbCluster029, pbCluster026, pbCluster030, pbCluster034,
                pbCluster031, pbCluster035, pbCluster033, pbCluster032, pbCluster039, pbCluster040, pbCluster037, pbCluster038, pbCluster036, pbCluster043,
                pbCluster045, pbCluster042, pbCluster041, pbCluster044, pbCluster048, pbCluster046, pbCluster050, pbCluster047, pbCluster049, p2pbCluster01,
                p2pbCluster05, p2pbCluster03, p2pbCluster02, p2pbCluster04, p2pbCluster010, p2pbCluster09, p2pbCluster07, p2pbCluster08, p2pbCluster06,
                p2pbCluster011, p2pbCluster015, p2pbCluster012, p2pbCluster013, p2pbCluster014, p2pbCluster016, p2pbCluster019, p2pbCluster018,
                p2pbCluster020, p2pbCluster017, p2pbCluster025, p2pbCluster024, p2pbCluster023, p2pbCluster022, p2pbCluster021, p2pbCluster028,
                p2pbCluster027, p2pbCluster029, p2pbCluster026, p2pbCluster030, p2pbCluster034, p2pbCluster031, p2pbCluster035, p2pbCluster033,
                p2pbCluster032, p2pbCluster039, p2pbCluster040, p2pbCluster037, p2pbCluster038, p2pbCluster036

        };





        for (HashMap.Entry<String, Float> entry : miMap.entrySet()) {
            String keyHashMap = entry.getKey();
            Float value = entry.getValue();


            //buscamos en el attay de editext el que contega este id
            for(EditText edi: miArray){
                //buscamos el que contenga este

                String keyOFeditextCurrent=edi.getId()+"-"+edi.getTag();


                if(keyHashMap.equals(keyOFeditextCurrent)){

                    edi.setText(String.valueOf(value));

                    break;
                }





            }


            ///entonces buscamos el que contenga este


        }


    }

    private TextInputEditText getTexImputEditextByidORkey( TextInputEditText[] allArrayViewsTextIMPUTe,int idViewSearch ){

        TextInputEditText textInputEditText=null;
        Log.i("midata","hay en totak "+allArrayViewsTextIMPUTe.length + "textimput editext");


        for(int indice=0; indice<allArrayViewsTextIMPUTe.length ; indice++){  //iteramos el mapa

            Log.i("midata","el id de este view es es "+allArrayViewsTextIMPUTe[indice].getId());

            if(allArrayViewsTextIMPUTe[indice].getId()==idViewSearch){


                textInputEditText= allArrayViewsTextIMPUTe[indice];
                break;
            }

        }

        return textInputEditText;
    }


    void dowloadImagesDataReport(String reportUNIQUEidtoSEARCH){ //DESCRAGAMOS EL SEGUNDO

        Log.i("dowloadxs","el reporunique id es : "+reportUNIQUEidtoSEARCH);

        RealtimeDB.initDatabasesRootOnly();
        RealtimeDB.initDatabasesReferenceImagesData();

        Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("ImagesData").orderByChild("idReportePerteence").equalTo(reportUNIQUEidtoSEARCH);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<ImagenReport>listImagenData=new ArrayList<>();

                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    ImagenReport imagenReport=ds.getValue(ImagenReport.class);
                    listImagenData.add(imagenReport);

                }


                Variables.listImagenDataGlobalCurrentReport =listImagenData;

              //  dowloadAllImages2AddCallRecicler(Variables.listImagenData);

                Log.i("mispiggi","se llamo a: addInfotomap");

                Log.i("dowloadxs","el size de lista es "+Variables.listImagenDataGlobalCurrentReport.size());


                addInfotomap(Variables.listImagenDataGlobalCurrentReport);

                createlistsForReciclerviewsImages(Variables.listImagenDataGlobalCurrentReport);



                //este metodo lo llamaremos ahora
                //al objeto imagen report le agregaremos una propiedad llamada bitmap...o crearemos un map de bitmaps que usraemos para cargarlos desde el
                //el adpater del recicler view y asiu no alteramos el objeto imagereport...solo que ya no descragremos la imagen nuevamente...


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }

/*
    public   void dowloadAllImages2AddCallRecicler(ArrayList<ImagenReport>miLisAllImages){
        //lllamos a este metodo unicamente si la lista es 0....si no
        HelperImage.ImagesToPdfMap=new HashMap<>();

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
                        String horientacionImg=HelperImage.devuelveHorientacionImg(bitmap);

                        ImagesToPdf imgsObect=new ImagesToPdf(horientacionImg,bitmap,categoYCurrentImg,uniqueId,descripcionImage);
                        HelperImage.imAGESpdfSetGlobal.add(imgsObect);
                        HelperImage.ImagesToPdfMap.put(uniqueId,imgsObect);

                        Log.i("hamiso","el size de la lista de Variables.listImagenData es "+Variables.listImagenData.size());
                        Log.i("hamiso","el size del map es "+HelperImage.ImagesToPdfMap.size());

                        ///llamamos a este otro metodo .......
                        contadorIterador++;
                        Log.i("hamiso","el contador iteradopr "+ contadorIterador);




                        if(contadorIterador == miLisAllImages.size() ) {

                            createlistsForReciclerviewsImages(Variables.listImagenData);
                            Log.i("hamiso","se llamokkk");
                            Utils.objsIdsDecripcionImgsMOreDescripc =new ArrayList<>();
                          //  btnGENERARpdf.setEnabled(true);

                        }



                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("hamiso","se produjo un error");

                        // Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();

            }





        }


        Log.i("hamiso","llamos a recicler create y el size de map es  "+HelperImage.ImagesToPdfMap.size());



    }
*/

    private void checkModeVisualitY(){

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            isModEdicionFields = extras.getBoolean(Variables.KEYEXTRAPREVIEW);

            Log.i("extra","el modo es "+isModEdicionFields);
            //The key argument here must match that used in the other activity
        }


        isModEdicionFields=false;

        if(isModEdicionFields){
            TextView txtModeAdviser=findViewById(R.id.txtModeAdviser);
            activateModeEdit();
            txtModeAdviser.setText("Modo Edicion ");

            Log.i("isclkiel","es clickeable es "+ Variables.isClickable);

        }

        else

        {
            fab2.setImageResource(R.drawable.ic_baseline_edit_24aa);
            activateModePreview();
            Log.i("isclkiel","es clickeable es "+ Variables.isClickable);

        }


        Variables.modoRecicler=Variables.DOWLOAD_IMAGES;


        addDataEnFields(Variables.currenReportCamionesyCarretas);

        UNIQUE_ID_iNFORME=Variables.currenReportCamionesyCarretas.getUniqueIDinforme();

        addDataENfiledsoTHERviews(Variables.currenReportCamionesyCarretas);


        StorageData.initStorageReference();


        dowloadImagesDataReport(Variables.currenReportCamionesyCarretas.getUniqueIDinforme());

        dowLoadProducsPostC(Variables.currenReportCamionesyCarretas.getUniqueIDinforme());

        dowloadCalibracionCalendario(Variables.currenReportCamionesyCarretas.getUniqueIDinforme());



        Log.i("simener","el nodo que contiene es: "+Variables.currenReportCamionesyCarretas.getKeyOrNodeLibriadoSiEs());



        if(!Variables.currenReportCamionesyCarretas.getKeyOrNodeLibriadoSiEs().trim().isEmpty()){

            dowLoadMapPesoBrutoCloster2y3l((Variables.currenReportCamionesyCarretas.getKeyOrNodeLibriadoSiEs()));

        }



    }

    private void addDataEnFields(ReportCamionesyCarretas currenReport) {

        ediExportadoraProcesada.setText(currenReport.getExportadoraProcesada());
        ediExportadoraSolicitante.setText(currenReport.getExportadoraSolicitante());

        Log.i("jamisama","la semana es "+currenReport.getSemana());
        ediClienteNombreReporte.setText(currenReport.getClienteReporte());
        ediSemana.setText(currenReport.getSemana());
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        String fechaString = formatter.format(currenReport.getFechaCreacionInf());
        ediFecha.setText(fechaString);

        ediProductor.setText(currenReport.getProductor());
        ediHacienda.setText(currenReport.getHacienda());
        ediCodigo.setText(currenReport.getCodigo());
        ediInscirpMagap.setText(currenReport.getInscirpMagap());
        ediPemarque.setText(currenReport.getPemarque());
        ediZona.setText(currenReport.getZona());

        ediTipoBoquilla.setText(currenReport.getTipoBoquilla());

        ediHoraInicio.setText(currenReport.getHoraInicio());

        ediHoraTermino.setText(currenReport.getHoraTermino());

        ediNguiaRemision.setText(currenReport.getNguiaRemision());
        edi_nguia_transporte.setText(currenReport.get_nguia_transporte());
        ediNtargetaEmbarque.setText(currenReport.getNtargetaEmbarque());
        ediNhojaEvaluacion.setText(currenReport.getEdiNhojaEvaluacion());
        ediObservacion.setText(currenReport.getObservacionOpc());
        ediEmpacadora.setText(currenReport.getEmpacadora());
        ediContenedor.setText(currenReport.getContenedor());

        /*faltan los prouctos postcosecha agregamos usando un for**/

          ediPLaca.setText(currenReport.getPlaca());




        ediNombreChofer.setText(currenReport.getNombredeChofer());
        ediCedula.setText(String.valueOf(currenReport.getCedula()));
        ediCelular.setText(String.valueOf(currenReport.getCelular()));

        ediCondicionBalanza.setText(currenReport.getCondicionBalanza());
        ediTipodeCaja.setText(currenReport.getTipoDeCaja());
        ediTipoPlastico.setText(currenReport.getTipoDePlastico());
        ediTipoBalanza.setText(currenReport.getTipoBalanza());
        editipbalanzaRepeso.setText(currenReport.getTipoBalanzaRepesa());

        ediCandadoQsercom.setText(currenReport.getCandadoQsercom());


        //configuramos los ultimos datos
        ediExtCalid.setText(currenReport.getExtensionistaEnCalidad());
        ediExtGancho.setText(currenReport.getExtensionistaEnGancho());
        ediExtRodillo.setText(currenReport.getExtensionistaEnRodillo());

        ediExtCalidCi.setText(currenReport.getCalidadCi());

      //  Log.i("misganas","el extensionista ci es "+//cure);

        ediExtGanchoCi.setText(currenReport.getGanchoCi());
        ediExtRodilloCi.setText(currenReport.getExtRodilloCi());


        //control de gancho

        ediCajasProcDesp.setText(String.valueOf(currenReport.getCajasProcesadasDespachadas()));
        ediRacimosCosech.setText(String.valueOf(currenReport.getRacimosCosechados()));
        ediRacimosRecha.setText(String.valueOf(currenReport.getRacimosRechazados()));
        ediRacimProces.setText(String.valueOf(currenReport.getRacimosProcesados()));


    }


    private  void addDataENfiledsoTHERviews(ReportCamionesyCarretas info1Object) {



        Log.i("mizona","la zona obtenida en addDataENfiledsoTHERviews (data descargada ) es  "+info1Object.getZona());

        selectValue(spinnerSelectZona,info1Object.getZona()) ;

        selectValue(spTipoBoquilla,info1Object.getTipoBoquilla()) ;

      //  Log.i("mizona","la zona obtenida en addDataENfiledsoTHERviews (data descargada ) es  "+info1Object.ge);


        selectValue(spinnerCondicionBalanza,info1Object.getCondicionBalanza()) ;
        selectValue(spinnertipoCaja,info1Object.getTipoDeCaja()) ;
        selectValue(spinnertipodePlastico,info1Object.getTipoDePlastico()) ;
        selectValue(spinnertipodeBlanza,info1Object.getTipoBalanza()) ;
        selectValue(spinnertipodeBlanzaRepeso,info1Object.getTipoBalanzaRepesa()) ;
        //selectValue(spinnerubicacionBalanza,info1Object.getUbicacionBalanza()) ;
        selectValue(spFuenteAgua,info1Object.getFuenteAgua()) ;

        selectValue(spFuenteAgua,info1Object.getFuenteAgua()) ;
        selectValue(spFumigaCorL1,info1Object.getFumigacionClin1()) ;
      //  selectValue(spTipoBoquilla,info1Object.getT()) ;


        switchHaybalanza.setChecked(info1Object.isHayBalanza());
        switchHayEnsunchado.setChecked(info1Object.isHayEnsunchado());
        switchBalanzaRep.setChecked(info1Object.isHayBalanzaRepesa());

        swAguaCorrida.setChecked(info1Object.isHayAguaCorrida());
        switchLavdoRacimos.setChecked(info1Object.isLavadoRacimos());


    }

    private void selectValue(Spinner spinner, String value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(value)) {
                spinner.setSelection(i);
                Log.i("mizona","existe hurra"+value);
                break;

            }else

            {

                Log.i("mizona","no exiwste "+value);

            }
        }

    }


    private void activateModePreview() {
        //Cremoas un super array list de views...

        //cremoas un super array...de todo tipos de Views

        View [] arrayAllFields={ediExportadoraSolicitante,ediExportadoraProcesada,ediClienteNombreReporte,
                ediSemana, ediFecha, ediProductor, ediHacienda, ediCodigo, ediInscirpMagap, ediPemarque, ediZona, ediHoraInicio, ediHoraTermino,
                ediNguiaRemision, edi_nguia_transporte, ediNtargetaEmbarque, ediNhojaEvaluacion, ediObservacion, ediEmpacadora,
                ediContenedor, ediPPC01, ediPPC02, ediPPC03, ediPPC04, ediPPC05, ediPPC06, ediPPC07, ediPPC08, ediPPC09, ediPPC010, ediPPC011,
                ediPPC012, ediPPC013, ediPPC014, ediPPC015, ediPPC016, ediEnsunchado, ediBalanzaRepeso, ediCandadoQsercom,
                ediBalanza, ediFuenteAgua, ediAguaCorrida, ediLavadoRacimos, ediFumigacionClin1, ediTipoBoquilla, ediCajasProcDesp, ediRacimosCosech,
                ediRacimosRecha, ediRacimProces, ediNombreChofer, ediCedula, ediCelular, ediPLaca, ediCondicionBalanza,
                ediTipodeCaja, ediTipoPlastico, ediTipoBalanza, editipbalanzaRepeso, ediExtCalid, ediExtRodillo,
                ediExtGancho, ediExtCalidCi, ediExtRodilloCi, ediExtGanchoCi, spinnerSelectZona, spinnerCondicionBalanza, spinnertipoCaja,
                spinnertipodePlastico, spinnertipodeBlanza , spinnertipodeBlanzaRepeso  , spFuenteAgua ,
                spFumigaCorL1 , spTipoBoquilla , spinnerCandadoQsercon, switchHaybalanza, switchHayEnsunchado, switchBalanzaRep,
                switchLavdoRacimos, swAguaCorrida

        };

    HelperEditAndPreviewmode.diseableViewsByTipe(arrayAllFields);




    }

    private void activateModeEdit() {

        View [] arrayAllFields={

                ediExportadoraSolicitante,ediExportadoraProcesada,ediClienteNombreReporte,

                ediSemana, ediFecha, ediProductor, ediHacienda, ediCodigo, ediInscirpMagap, ediPemarque, ediZona, ediHoraInicio, ediHoraTermino,
                ediNguiaRemision, edi_nguia_transporte, ediNtargetaEmbarque, ediNhojaEvaluacion, ediObservacion, ediEmpacadora,
                ediContenedor, ediPPC01, ediPPC02, ediPPC03, ediPPC04, ediPPC05, ediPPC06, ediPPC07, ediPPC08, ediPPC09, ediPPC010, ediPPC011,
                ediPPC012, ediPPC013, ediPPC014, ediPPC015, ediPPC016, ediEnsunchado, ediBalanzaRepeso, ediCandadoQsercom,
                ediBalanza, ediFuenteAgua, ediAguaCorrida, ediLavadoRacimos, ediFumigacionClin1, ediTipoBoquilla, ediCajasProcDesp, ediRacimosCosech,
                ediRacimosRecha, ediRacimProces, ediNombreChofer, ediCedula, ediCelular, ediPLaca, ediCondicionBalanza,
                ediTipodeCaja, ediTipoPlastico, ediTipoBalanza, editipbalanzaRepeso, ediExtCalid, ediExtRodillo,
                ediExtGancho, ediExtCalidCi, ediExtRodilloCi, ediExtGanchoCi, spinnerSelectZona, spinnerCondicionBalanza, spinnertipoCaja,
                spinnertipodePlastico, spinnertipodeBlanza , spinnertipodeBlanzaRepeso  , spFuenteAgua ,
                spFumigaCorL1 , spTipoBoquilla , spinnerCandadoQsercon, switchHaybalanza, switchHayEnsunchado, switchBalanzaRep,
                switchLavdoRacimos, swAguaCorrida

        };

        HelperEditAndPreviewmode.activateViewsByTypeView(arrayAllFields);


        //igual usamos un super array list de views....
    }

    private void showBottomSheetDialog(String  keyCurrentView) {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(PreviewCalidadCamionesyCarretas.this);

        bottomSheetDialog.setContentView(R.layout.bottom_sheet_selector);

        //NumberPicker numberPicker1=bottomSheetDialog.findViewById(R.id.numberPicker1);
       // NumberPicker numberPicker2=bottomSheetDialog.findViewById(R.id.numberPicker2);
      //  numberPicker1.setMaxValue(5);

      //  numberPicker2.setMaxValue(10);

       //crenado number poicker



        Button  buttonSelecionar=bottomSheetDialog.findViewById(R.id.buttonSelecionar);



        buttonSelecionar.setOnClickListener(new View.OnClickListener() { //editar
            @Override
            public void onClick(View v) {

                //aqui ya obtendremos el peso..

                bottomSheetDialog.dismiss();


            }
        });

        bottomSheetDialog.show();
    }

    ///peso bruto por clsters
private void setCalibrCalEndInViews(CalibrFrutCalEnf currentObject){


    EditText ediColorSem14=findViewById(R.id.ediColortSem14);
    EditText ediColorSem13=findViewById(R.id.ediColortSem13);
    EditText ediColorSem12=findViewById(R.id.ediColortSem12);
    EditText ediColorSem11=findViewById(R.id.ediColortSem11);
    EditText ediColorSem10=findViewById(R.id.ediColortSem10);
    EditText ediColorSem9=findViewById(R.id.ediColortSem9);

    EditText ediNumRcim14=findViewById(R.id.ediNumRcim14);
    EditText ediNumRcim13=findViewById(R.id.ediNumRcim13);
    EditText ediNumRcim12=findViewById(R.id.ediNumRcim12);
    EditText ediNumRcim11=findViewById(R.id.ediNumRcim11);
    EditText ediNumRcim10=findViewById(R.id.ediNumRcim10);
    EditText ediNumRac9=findViewById(R.id.ediNumRac9);



    ediColorSem14.setText(currentObject.getColorSemana14());
    ediColorSem13.setText(currentObject.getColorSemana13());
    ediColorSem12.setText(currentObject.getColorSemana12());
    ediColorSem11.setText(currentObject.getColorSemana11());
    ediColorSem10.setText(currentObject.getColorSemana10());
    ediColorSem9.setText(currentObject.getColorSemana9());



    if(currentObject.getNumeracionRacimosSem14()>0){

        ediNumRcim14.setText(String.valueOf( currentObject.getNumeracionRacimosSem14()));

    }

    if(currentObject.getNumeracionRacimosSem13()>0){

        ediNumRcim13.setText(String.valueOf( currentObject.getNumeracionRacimosSem13()));

    }


    if(currentObject.getNumeracionRacimosSem12()>0){

        ediNumRcim12.setText(String.valueOf( currentObject.getNumeracionRacimosSem12()));

    }

    if(currentObject.getNumeracionRacimosSem11()>0){

        ediNumRcim11.setText(String.valueOf( currentObject.getNumeracionRacimosSem11()));

    }


    if(currentObject.getNumeracionRacimosSem10()>0){

        ediNumRcim10.setText(String.valueOf(currentObject.  getNumeracionRacimosSem10()) );

    }


    if(currentObject.getNumeracionRacimosSem9()>0){

        ediNumRac9.setText(String.valueOf(currentObject.  getNumeracionRacimosSem9()) );

    }


/*
    ediNumRcim13.setText(String.valueOf(currentObject.getNumeracionRacimosSem13()) );
    ediNumRcim12.setText(String.valueOf(currentObject.getNumeracionRacimosSem12()) );
    ediNumRcim11.setText(String.valueOf( currentObject.getNumeracionRacimosSem11()));
    ediNumRcim10.setText(String.valueOf(currentObject.getNumeracionRacimosSem10()) );
*/

}



    void dowloadCalibracionCalendario(String idAlquePERTENECE){
        Query query = RealtimeDB.rootDatabaseReference.child("Informes").
                child("listCalibracionFtutsCal").
                orderByChild("idPertenece").equalTo(idAlquePERTENECE);


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Map<String, Object> map = null;
                //  Map<String, String> map = dataSnapshot.getValue(Map.class);
                //  Log.i("sliexsa","el size de map es "+map.size());


                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    calEnfundeGLOB=ds.getValue(CalibrFrutCalEnf.class);
                }



                if(calEnfundeGLOB!=null){

                    Log.i("somewrlals","es difrente de nulo");

                    setCalibrCalEndInViews(calEnfundeGLOB);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }



    public void saveInfo() {

        RealtimeDB.initDatabasesReferenceImagesData(); //inicilizamos la base de datos

        uploadImagesInStorageAndInfoPICS(); //subimos laS IMAGENES EN STORAGE Y LA  data de las imagenes EN R_TDBASE

        createObjcInformeAndUpdate(); //CREAMOS LOS IN


    }


    void ocultaoTherVIEWs(){

        ediMarca.setVisibility(View.GONE);
        ediUbicacionBalanza.setVisibility(View.GONE);
        spinnerubicacionBalanza.setVisibility(View.GONE);


    }
    private boolean getResultDatCalibCalEnfundes(){



        if(ediRacimosCosech.getText().toString().trim().isEmpty()){
            ediRacimosCosech.requestFocus();
            ediRacimosCosech.setError("Este valor es necesesario");

            return false;

        }


        EditText ediNumRcim14 = findViewById(R.id.ediNumRcim14);
        EditText ediNumRcim13 = findViewById(R.id.ediNumRcim13);
        EditText ediNumRcim12 = findViewById(R.id.ediNumRcim12);
        EditText ediNumRcim11 = findViewById(R.id.ediNumRcim11);
        EditText ediNumRcim10 = findViewById(R.id.ediNumRcim10);
        EditText ediNumRac9 = findViewById(R.id.ediNumRac9);


        EditText ediPorc14=findViewById(R.id.ediPorc14);
        EditText ediPorc13=findViewById(R.id.ediPorc13);
        EditText ediPorc12=findViewById(R.id.ediPorc12);
        EditText ediPorc11=findViewById(R.id.ediPorc11);
        EditText ediPorc10=findViewById(R.id.ediPorc10);
        EditText ediPorc9 =findViewById(R.id.ediPorc9);




        int numRacimosCosechados=Integer.parseInt(ediRacimosCosech.getText().toString());
        float resultpercente;
        DecimalFormat format = new DecimalFormat("#.##");

        int numeroRacimosContador=0;

        //numero de raCimos
        EditText [] miArrayNUmrACIMOS ={ediNumRcim14,ediNumRcim13,ediNumRcim12,ediNumRcim11,ediNumRcim10,ediNumRac9};

        EditText [] miArraypORCENTAHJES ={ediPorc14,ediPorc13,ediPorc12,ediPorc11,ediPorc10,ediPorc9};

        for(int i=0; i<miArrayNUmrACIMOS.length; i++){

            if(!miArrayNUmrACIMOS[i].getText().toString().trim().isEmpty())
            {        ///tiene que ser mayor a cero
                if(Integer.parseInt(miArrayNUmrACIMOS[i].getText().toString())>0)
                {  //operamoss
                    resultpercente= (Float.parseFloat(miArrayNUmrACIMOS[i].getText().toString())/numRacimosCosechados)*100;

                    String promDecim=format.format(resultpercente)   ;

                    if(promDecim.contains(".")){
                        String [] array=promDecim.split("\\."); //tendremos un valor asi 58.50 normal
                        if(array[1].length()==1){ //tiene solo un valor.
                            promDecim=promDecim+"0";
                        }
                    }


                    miArraypORCENTAHJES[i].setText(promDecim);

                    //sumaoslos racimos totale
                    numeroRacimosContador=numeroRacimosContador+Integer.parseInt(miArrayNUmrACIMOS[i].getText().toString());



                }

            }


        }



        //calculo aqwui

        if(numeroRacimosContador!=numRacimosCosechados){

            Snackbar.make(ediRacimosCosech, "El numero de racimos no concuerda con el numero de racimos cosechados", Snackbar.LENGTH_LONG)
                    .show();

            Log.i("dataracimos","no coincide");

            return false;




        }

        else {
            Log.i("dataracimos","SI coincide");

            return true;



        }



    }

    void hideSomeElemtosAnexosAndChangeValues(){



    }



}
