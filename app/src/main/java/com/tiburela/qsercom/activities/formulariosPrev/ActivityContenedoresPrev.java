package com.tiburela.qsercom.activities.formulariosPrev;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
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
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tiburela.qsercom.PdfMaker.PdfMaker2_0;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.activities.othersActivits.ActivitySeeReports;
import com.tiburela.qsercom.adapters.CustomAdapter;
import com.tiburela.qsercom.adapters.RecyclerViewAdapter;
import com.tiburela.qsercom.auth.Auth;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.dialog_fragment.DialogConfirmChanges;
import com.tiburela.qsercom.models.CheckedAndAtatch;
import com.tiburela.qsercom.models.ControlCalidad;
import com.tiburela.qsercom.models.CuadroMuestreo;
import com.tiburela.qsercom.models.EstateFieldView;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.ProductPostCosecha;
import com.tiburela.qsercom.models.SetInformDatsHacienda;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;
import com.tiburela.qsercom.storage.StorageData;
import com.tiburela.qsercom.utils.FieldOpcional;
import com.tiburela.qsercom.utils.HelperImage;
import com.tiburela.qsercom.utils.Permisionx;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class ActivityContenedoresPrev extends AppCompatActivity implements View.OnClickListener , View.OnTouchListener {

String currentIDcUDORmUESTREO;


    ProgressDialog progress;

        ArrayList <String>listImagesToDelete= new ArrayList<>();


        Button btnGuardarCambiosmARKrREVISADO;

    TextInputEditText ediExportadoraProcesada ;
    TextInputEditText ediExportadoraSolicitante;
    TextInputEditText ediMarca ;

    private static final int PERMISSION_REQUEST_CODE=100;
    ProductPostCosecha products;
    private String UNIQUE_ID_iNFORME;
    ProductPostCosecha productxGlobal=null;
    ProgressDialog pdialogff;
    public static Context context;
    private int contadorIterador;
    private boolean isModEdicionFields=false;
    private boolean esFirstCharge=true;
   private Switch swAguaCorrida,switchLavdoRacimos;
   private Spinner spFuenteAgua;
   private Spinner spFumigaCorL1;
    ImageView imgAtachVinculacion;
    RecyclerView reciclerViewBottomSheet;
    TextView    txtAdviseer;
    TextView txtAdviserDesvicunlar;
    ArrayList<ControlCalidad> listFormsControlCalidad =new ArrayList<>();
    ArrayList<CuadroMuestreo> listCuadrosMuestreos =new ArrayList<>();

    HashMap<String,CuadroMuestreo> mapCudroMuestreo =new HashMap<>();
    HashMap<String,ControlCalidad> mapControlCalidad =new HashMap<>();



    TextView txtNumReportsVinclds;


    ArrayList<CheckedAndAtatch> checkedListForms =new ArrayList<>();
   private     long millisDateSelect=0;
    BottomSheetDialog bottomSheetDialog;

   private Spinner spTipoBoquilla;
    private static int currentTypeImage=0;
    ProgressBar progressBarFormulario;
    private Context mContext;


   Button btnGENERARpdf;
    FloatingActionButton fab ;

    TextInputEditText ediCjasProcesDespacha;
    TextInputEditText  ediInspectorAcopio;
    TextInputEditText ediExtCalid;
    TextInputEditText  ediExtRodillo;
    TextInputEditText ediExtGancho;
    TextInputEditText ediExtCalidCi;
    TextInputEditText ediExtRodilloCi;
    TextInputEditText ediExtGanchoCi;




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
    TextInputEditText ediHoraLLegadaContenedor;
    TextInputEditText ediHoraSalidaContenedor;
    TextInputEditText ediNguiaRemision;
    TextInputEditText edi_nguia_transporte;
    TextInputEditText ediNtargetaEmbarque;
    TextInputEditText ediNhojaEvaluacion;
    TextInputEditText ediObservacion;
    TextInputEditText ediEmpacadora;
    TextInputEditText ediFotosLlegada;
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
    TextInputEditText ediDestino;
    TextInputEditText ediNViaje;
    TextInputEditText ediTipoContenedor;
    TextInputEditText ediVapor;
    TextInputEditText ediFotoContenedor;
    TextInputEditText ediFotosPposcosecha;
    TextInputEditText ediCompaniaTransporte;
    TextInputEditText ediNombreChofer;
    TextInputEditText ediCedula;
    TextInputEditText ediCelular;
    TextInputEditText ediPLaca;
    TextInputEditText ediMarcaCabezal;
    TextInputEditText ediColorCabezal;
    TextInputEditText ediFotosLlegadaTransport;
    TextInputEditText ediTare;
    TextInputEditText ediBooking;
    TextInputEditText ediMaxGross;
    TextInputEditText ediNumSerieFunda;
    TextInputEditText stikVentolerExterna;
    TextInputEditText ediCableRastreoLlegada;
    TextInputEditText ediSelloPlasticoNaviera;
    TextInputEditText ediOtroSellosLlegada;
    TextInputEditText ediFotosSellosLLegada;
    TextInputEditText ediCondicionBalanza;
    TextInputEditText ediTipodeCaja;
    TextInputEditText ediTipoPlastico;
    TextInputEditText ediTipoBalanza;
    TextInputEditText editipbalanzaRepeso;
    TextInputEditText ediUbicacionBalanza;
    TextInputEditText ediTermofrafo1;
    TextInputEditText ediHoraEncendido1;
    TextInputEditText ediUbicacion1;
    TextInputEditText ediRuma1;
    TextInputEditText ediTermofrafo2;
    TextInputEditText ediHoraEncendido2;
    TextInputEditText ediUbicacion2;
    TextInputEditText ediRuma2;
    TextInputEditText ediCandadoqsercon;
    TextInputEditText ediSelloNaviera;
    TextInputEditText ediCableNaviera;
    TextInputEditText ediSelloPlastico;
    TextInputEditText ediCandadoBotella;
    TextInputEditText ediCableExportadora;
    TextInputEditText ediSelloAdesivoexpor;
    TextInputEditText esiSelloAdhNaviera;
    TextInputEditText ediOtherSellos;
    TextInputEditText ediEnsunchado;
    TextInputEditText ediBalanzaRepeso;
     TextInputEditText ediNumContenedor;

    TextInputEditText ediFuenteAgua;
    TextInputEditText ediAguaCorrida;
    TextInputEditText ediLavadoRacimos;
    TextInputEditText ediFumigacionClin1;
    TextInputEditText ediTipoBoquilla;
    TextInputEditText ediCajasProcDesp;
    TextInputEditText ediRacimosCosech;
    TextInputEditText ediRacimosRecha;
    TextInputEditText ediRacimProces;


    LinearLayout linLayoutHeader1;
    LinearLayout linLayoutHeader2;
    LinearLayout linLayoutHeader3;
    LinearLayout linLayoutHeader4;
    LinearLayout linLayoutHeader5;
    LinearLayout linLayoutHeader6;
    LinearLayout linLayoutHeader7;
    LinearLayout linLayoutHeader8;

           Spinner spinnerSelectZona;
    Spinner spinnerCondicionBalanza;
    Spinner spinnertipoCaja;
    Spinner spinnertipodePlastico;
    Spinner spinnertipodeBlanza ;
    Spinner spinnertipodeBlanzaRepeso ;
    Spinner spinnerubicacionBalanza ;


    Switch switchContenedor;
    Switch switchHaybalanza;
    Switch switchHayEnsunchado;
    Switch switchBalanzaRep;

    ArrayList<View> listViewsClickedUser;

    ActivityResultLauncher activityResultLauncher;
    Uri cam_uri;

    ImageView imBtakePic;
    ImageView imBatach;

    ////////
    ImageView imbAtach_transportista;
    ImageView imbTakePicTransportista;
    ImageView imbAtachSellosLlegada;
    ImageView imbTakePicSellosLLegada;
    ImageView imbAtachDatosContenedor;
    ImageView imbTakePicDatosContenedor;
    ImageView imbAtachPrPostcosecha;
    ImageView imbTakePicPrPostcosecha;



    @Override
    protected void onStart() {
        super.onStart();

        Log.i("lifeclicel","se llamo metoo onstart ");

        Variables.VienedePreview=true;

        Auth.initAuth(ActivityContenedoresPrev.this);
        Auth.signInAnonymously(ActivityContenedoresPrev.this);

        try {
            progress.dismiss();

        } catch (Exception e) {
            e.printStackTrace();
        }


        if(esFirstCharge){
                   findViewsIds();

                   context = getApplicationContext();


                   UNIQUE_ID_iNFORME= Variables.CurrenReportPart1.getUniqueIDinforme();


                   // FirebaseApp.initializeApp(this);
                   //  DatabaseReference rootDatabaseReference = FirebaseDatabase.getInstance().getReference(); //anterior

                   Auth.initAuth(this);


                   RealtimeDB.initDatabasesRootOnly();
                   StorageData. initStorageReference();


                   listViewsClickedUser=new ArrayList<>();


                   addClickListeners();
                   resultatachImages();
                   listennersSpinners();

                  // EstateFieldView.adddataListsStateFields();
                   addOnTouchaMayoriaDeViews();
                   eventCheckdata();
                   //creaFotos();
                   listennersSpinners();
                   checkModeVisualitY();

                   configCertainSomeViewsAliniciar();




                   esFirstCharge=false;
}


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // progressDialog=progressDialog
        setContentView(R.layout.activity_preview);
        CustomAdapter.idsFormsVinucladosControlCalidadString =null;//reseteamos

        CustomAdapter.idsFormsVinucladosCudorMuestreoString=null;


        mContext=this;

//        CustomAdapter.idsFormsVinucladosCntres=Variables.CurrenReportPart1.getAtachControCalidadInfrms();
        Variables.activityCurrent=Variables.FormPreviewContenedores;


        /*
        findViewsIds();

        context = getApplicationContext();


        UNIQUE_ID_iNFORME= Variables.CurrenReportPart1.getUniqueIDinformePart2();


      // FirebaseApp.initializeApp(this);
      //  DatabaseReference rootDatabaseReference = FirebaseDatabase.getInstance().getReference(); //anterior

        Auth.initAuth(this);

        StorageData. initStorageReference();


        listViewsClickedUser=new ArrayList<>();


        addClickListeners();
        resultatachImages();
        listennersSpinners();

        EstateFieldView.adddataListsStateFields();
        addOnTouchaMayoriaDeViews();
        eventCheckdata();
        //creaFotos();
        listennersSpinners();
        checkModeVisualitY();

        configCertainSomeViewsAliniciar();

*/


    }





    void showingTimePicker( View vista){


        final Calendar cldr = Calendar.getInstance();
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        // time picker dialog
        TimePickerDialog  picker = new TimePickerDialog(ActivityContenedoresPrev.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int sHour, int sMinute) {

                        if(vista.getId()==R.id.ediHoraInicio) {
                            ediHoraInicio.setText(sHour + ":" + sMinute);


                        }


                        else if (vista.getId()== R.id.ediHoraTermino) {
                            ediHoraTermino.setText(sHour + ":" + sMinute);


                        }



                        else if (vista.getId()== R.id.ediHoraLLegadaContenedor) {
                            ediHoraLLegadaContenedor.setText(sHour + ":" + sMinute);


                        }



                        else if (vista.getId()== R.id.ediHoraSalidaContenedor) {
                            ediHoraSalidaContenedor.setText(sHour + ":" + sMinute);


                        }


                        else if (vista.getId()== R.id.ediHoraEncendido1) {
                            ediHoraEncendido1.setText(sHour + ":" + sMinute);


                        }



                        else if (vista.getId()== R.id.ediHoraEncendido2) {
                            ediHoraEncendido2.setText(sHour + ":" + sMinute);


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
        DatePickerDialog  picker = new DatePickerDialog(ActivityContenedoresPrev.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                          String dateSelec=i2+"/"+i1+"/"+i;

                        ediFecha.setText(dateSelec);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


                        Date date = null;
                        try {
                            date = sdf.parse(dateSelec);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        millisDateSelect = date.getTime();


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

        disableEditText(ediFecha);
        disableEditText(ediHoraInicio);
        disableEditText(ediHoraTermino);

        disableEditText(ediHoraLLegadaContenedor);//here
        disableEditText(ediHoraSalidaContenedor);

        disableEditText(ediContenedor);
        disableEditText(ediFotosLlegada);
        disableEditText(ediZona);
        disableEditText(ediEnsunchado);
        disableEditText(ediBalanzaRepeso);

        disableEditText(ediHoraEncendido1);
        disableEditText(ediHoraEncendido2);

        //descativamos este boton
        btnGENERARpdf.setEnabled(false);

    }

    private void findViewsIds( ) { //configuraremos algos views al iniciar

        ediExportadoraProcesada=findViewById(R.id.ediExportadoraProcesada);
        ediExportadoraSolicitante =findViewById(R.id.ediExportadoraSolicitante);
        ediMarca=findViewById(R.id.ediMarca);

        btnGuardarCambiosmARKrREVISADO=findViewById(R.id.btnGuardarCambiosmARKrREVISADO);


        ediExtCalid=findViewById(R.id.ediExtCalid);
        ediExtRodillo=findViewById(R.id.ediExtRodillo);
        ediExtGancho=findViewById(R.id.ediExtGancho);

        ediExtCalidCi=findViewById(R.id.ediExtCalidCi);
        ediExtRodilloCi=findViewById(R.id.ediExtRodilloCi);
        ediExtGanchoCi=findViewById(R.id.ediExtGanchoCi);


        txtNumReportsVinclds=findViewById(R.id.txtNumReportsVinclds);

        imgAtachVinculacion=findViewById(R.id.imgAtachVinculacion);

         fab = (FloatingActionButton) findViewById(R.id.fab);
        ediEmpacadora=findViewById(R.id.ediEmpacadora);
        btnGENERARpdf =findViewById(R.id.btnGENERARpdf);

        spFumigaCorL1=findViewById(R.id.spFumigaCorL1);

          ediCjasProcesDespacha=findViewById(R.id.ediCjasProcesDespacha);
          ediInspectorAcopio=findViewById(R.id.ediInspectorAcopio);
        spTipoBoquilla=findViewById(R.id.spTipoBoquilla);

        spFuenteAgua =findViewById(R.id.spFuenteAgua);
         ediSemana=findViewById(R.id.ediCajas3);
         ediFecha=findViewById(R.id.ediCajas7);
         ediProductor=findViewById(R.id.ediCodigoN2);
         ediHacienda=findViewById(R.id.ediCodigoN8);
         ediCodigo=findViewById(R.id.ediCodigoN7);
         ediInscirpMagap=findViewById(R.id.ediCajas10);
         ediPemarque=findViewById(R.id.ediProductor10);
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
        ediFotosLlegada=findViewById(R.id.ediFotosLlegada);

        ediNumContenedor=findViewById(R.id.ediNumContenedor);
        swAguaCorrida=findViewById(R.id.swAguaCorrida);
        switchLavdoRacimos=findViewById(R.id.switchLavdoRacimos);

        ediTare=findViewById(R.id.ediTare);
        ediBooking=findViewById(R.id.ediBooking);
        ediMaxGross=findViewById(R.id.ediMaxGross);
        ediNumSerieFunda=findViewById(R.id.ediNumSerieFunda);
        stikVentolerExterna=findViewById(R.id.stikVentolerExterna);
        ediCableRastreoLlegada=findViewById(R.id.ediCableRastreoLlegada);
        ediSelloPlasticoNaviera=findViewById(R.id.ediSelloPlasticoNaviera);
        ediOtroSellosLlegada=findViewById(R.id.ediOtroSellosLlegada);
        ediFotosSellosLLegada=findViewById(R.id.ediFotosSellosLLegada);

        ediEnsunchado=findViewById(R.id.ediEnsunchado);
        ediBalanzaRepeso=findViewById(R.id.ediBalanzaRepeso);

        ediFuenteAgua=findViewById(R.id.ediFuenteAgua);
        ediAguaCorrida=findViewById(R.id.ediAguaCorrida);
        ediLavadoRacimos=findViewById(R.id.ediLavadoRacimos);
        ediFumigacionClin1=findViewById(R.id.ediFumigacionClin1);
        ediTipoBoquilla=findViewById(R.id.ediTipoBoquilla);
        ediCajasProcDesp=findViewById(R.id.ediCajasProcDesp);
        ediRacimosCosech=findViewById(R.id.ediRacimosCosech);

        ediRacimosRecha=findViewById(R.id.ediRacimosRecha);
        ediRacimProces=findViewById(R.id.ediRacimProces);


        linLayoutHeader1 =findViewById(R.id.linLayoutHeader1);
        linLayoutHeader2 =findViewById(R.id.linLayoutHeader2);
        linLayoutHeader3 =findViewById(R.id.linLayoutHeader3);
        linLayoutHeader4 =findViewById(R.id.linLayoutHeader4);
        linLayoutHeader5 =findViewById(R.id.linLayoutHeader5);
        linLayoutHeader6 =findViewById(R.id.linLayoutHeader6);
        linLayoutHeader7 =findViewById(R.id.linLayoutHeader7);
        linLayoutHeader8  = findViewById(R.id.linLayoutHeader8) ;




        switchContenedor=findViewById(R.id.switchContenedor);
        ediContenedor=findViewById(R.id.ediContenedor);

         imBatach=findViewById(R.id.imbAtach);
         imBtakePic=findViewById(R.id.imbTakePic);

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


          ediDestino=findViewById(R.id.ediDestino);
          ediNViaje=findViewById(R.id.ediNViaje);
          ediVapor=findViewById(R.id.ediVapor);

         // ediHOraLllegada=findViewById(R.id.ediHoraLLegadaContenedor);
          //ediHoraSalida=findViewById(R.id.ediHoraSalida);

        ediHoraLLegadaContenedor=findViewById(R.id.ediHoraLLegadaContenedor);
        ediHoraSalidaContenedor=findViewById(R.id.ediHoraSalidaContenedor);


        ediTipoContenedor=findViewById(R.id.ediTipoContenedor);

        ediFotoContenedor=findViewById(R.id.ediFotoContenedor);

        progressBarFormulario=findViewById(R.id.progressBarFormulario);

        ediFotosPposcosecha=findViewById(R.id.ediFotosPposcosecha);

        ediCondicionBalanza=findViewById(R.id.ediCondicionBalanza);
        ediTipodeCaja=findViewById(R.id.ediTipodeCaja);
        ediTipoPlastico=findViewById(R.id.ediTipoPlastico);
        ediTipoBalanza=findViewById(R.id.ediTipoBalanza);
        editipbalanzaRepeso=findViewById(R.id.editipbalanzaRepeso);
        ediUbicacionBalanza=findViewById(R.id.ediUbicacionBalanza);



        ediTermofrafo1=findViewById(R.id.ediTermofrafo1);
        ediHoraEncendido1=findViewById(R.id.ediHoraEncendido1);
        ediUbicacion1=findViewById(R.id.ediUbicacion1);
        ediRuma1=findViewById(R.id.ediRuma1);
        ediTermofrafo2=findViewById(R.id.ediTermofrafo2);
        ediHoraEncendido2=findViewById(R.id.ediHoraEncendido2);
        ediUbicacion2=findViewById(R.id.ediUbicacion2);
        ediRuma2=findViewById(R.id.ediRuma2);
        ediCandadoqsercon=findViewById(R.id.ediCandadoqsercon);

        ediSelloNaviera=findViewById(R.id.ediSelloNaviera);
        ediCableNaviera=findViewById(R.id.ediCableNaviera);
        ediSelloPlastico=findViewById(R.id.ediSelloPlastico);
        ediCandadoBotella=findViewById(R.id.ediCandadoBotella);
        ediCableExportadora=findViewById(R.id.ediCableExportadora);
        ediSelloAdesivoexpor=findViewById(R.id.ediSelloAdesivoexpor);
        esiSelloAdhNaviera=findViewById(R.id.esiSelloAdhNaviera);
        ediOtherSellos=findViewById(R.id.ediOtherSellos);


        ediCompaniaTransporte=findViewById(R.id.ediCompaniaTransporte);
        ediNombreChofer=findViewById(R.id.ediNombreChofer);
        ediCedula=findViewById(R.id.ediCedula);
        ediCelular=findViewById(R.id.ediCelular);
        ediPLaca=findViewById(R.id.ediPLaca);
        ediMarcaCabezal=findViewById(R.id.ediMarcaCabezal);
        ediColorCabezal=findViewById(R.id.ediColorCabezal);
        ediFotosLlegadaTransport=findViewById(R.id.ediFotosLlegadaTransport);




        //las pics
         imbAtach_transportista=findViewById(R.id.imbAtach_transportista);
         imbTakePicTransportista=findViewById(R.id.imbTakePicTransportista);
         imbAtachSellosLlegada=findViewById(R.id.imbAtachSellosLlegada);
         imbTakePicSellosLLegada=findViewById(R.id.imbTakePicSellosLLegada);
         imbAtachDatosContenedor=findViewById(R.id.imbAtachDatosContenedor);
        imbAtachDatosContenedor=findViewById(R.id.imbAtachDatosContenedor);
         imbAtachPrPostcosecha=findViewById(R.id.imbAtachPrPostcosecha);
         imbTakePicPrPostcosecha=findViewById(R.id.imbTakePicPrPostcosecha);
        imbTakePicDatosContenedor=findViewById(R.id.imbTakePicDatosContenedor);


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
        imgAtachVinculacion.setOnClickListener(this);


        btnGENERARpdf.setOnClickListener(this);

        btnGuardarCambiosmARKrREVISADO.setOnClickListener(this);


        fab.setOnClickListener(this);

        imBtakePic.setOnClickListener(this);
        imBatach.setOnClickListener(this);

         imbAtach_transportista.setOnClickListener(this);
         imbTakePicTransportista.setOnClickListener(this);
         imbAtachSellosLlegada.setOnClickListener(this);
         imbTakePicSellosLLegada.setOnClickListener(this);
         imbAtachDatosContenedor.setOnClickListener(this);
         imbTakePicDatosContenedor.setOnClickListener(this);
         imbAtachPrPostcosecha.setOnClickListener(this);
         imbTakePicPrPostcosecha.setOnClickListener(this);

        ediHoraEncendido1.setOnClickListener(this);
        ediHoraEncendido2.setOnClickListener(this);



        linLayoutHeader2.setOnClickListener(this);
        linLayoutHeader1.setOnClickListener(this);
        linLayoutHeader3.setOnClickListener(this);
        linLayoutHeader4.setOnClickListener(this);
        linLayoutHeader5.setOnClickListener(this);
        linLayoutHeader6.setOnClickListener(this);
        linLayoutHeader7.setOnClickListener(this);
        linLayoutHeader8.setOnClickListener(this);


        ediFecha.setOnClickListener(this);
        ediHoraInicio.setOnClickListener(this);
        ediHoraTermino.setOnClickListener(this);

        ediHoraLLegadaContenedor.setOnClickListener(this);
        ediHoraSalidaContenedor.setOnClickListener(this);



    }

    private void oucultaLinearLayout(LinearLayout linearLayout) { //configuraremos algos views al iniciar
     linearLayout.setVisibility(LinearLayout.GONE);


    }


    private void muestraLinearLayout( LinearLayout linearLayout) { //configuraremos algos views al iniciar

        linearLayout.setVisibility(LinearLayout.VISIBLE);



    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {

         Log.i("darterlo","is selñecieo,ages");

       switch (view.getId()) {




           case R.id.btnGuardarCambiosmARKrREVISADO:

               ///cuandole da en genear obtenmos nuevamente la data

               RealtimeDB.marckComoRevisadoInformRegister(ActivityContenedoresPrev.this,Variables.currentInformRegisterSelected.getKeyLoactionThisForm());

               finish();

               //creamosel pdf con la data actual... excepto las imagenes...

               break;



           case R.id.btnGENERARpdf:

               ///cuandole da en genear obtenmos nuevamente la data

               checkDataToCreatePdf();

               //creamosel pdf con la data actual... excepto las imagenes...

               break;


           case R.id.fab: //si pulas en btn chekear en que modo esta ...si el modo cambia...
               TextView txtModeAdviser=findViewById(R.id.txtModeAdviser);

                if(isModEdicionFields){ //si es modo edicion..
                    fab.setImageResource(R.drawable.ic_baseline_edit_24aa);

                    txtModeAdviser.setText("Modo Visualizacion ");



                    //cambiamos al modo visualizacion
                    isModEdicionFields=false;
                    activateModePreview();


                }else{ //SI NO ES MODO VISUZALIZACION
                    fab.setImageResource(R.drawable.ic_baseline_preview_24jhj);
                    txtModeAdviser.setText("Modo Edicion ");

                    isModEdicionFields=true;
                    activateModeEdit();


                  //CAMABIAMOS EL MODO

                }



               break; //



           case R.id.linLayoutHeader1:
               LinearLayout layoutContainerSeccion1=findViewById(R.id.layoutContainerSeccion1);

               if(layoutContainerSeccion1.getVisibility() == View.GONE) {
                   muestraLinearLayout(layoutContainerSeccion1);
               }
               else{

                   oucultaLinearLayout(layoutContainerSeccion1);
               }
               break; //



           case R.id.linLayoutHeader2:
               LinearLayout layoutContainerSeccion2=findViewById(R.id.layoutContainerSeccion2);

               if(layoutContainerSeccion2.getVisibility() == View.GONE) {
                   muestraLinearLayout(layoutContainerSeccion2);
               }
               else{

                   oucultaLinearLayout(layoutContainerSeccion2);
               }
               break; //

           case R.id.linLayoutHeader3:
               LinearLayout layoutContainerSeccion3=findViewById(R.id.layoutContainerSeccion3);

               if(layoutContainerSeccion3.getVisibility() == View.GONE) {
                   muestraLinearLayout(layoutContainerSeccion3);
               }
               else{

                   oucultaLinearLayout(layoutContainerSeccion3);
               }
               break; //


           case R.id.linLayoutHeader4:
               LinearLayout layoutContainerSeccion4=findViewById(R.id.layoutContainerSeccion4);

               if(layoutContainerSeccion4.getVisibility() == View.GONE) {
                   muestraLinearLayout(layoutContainerSeccion4);
               }
               else{

                   oucultaLinearLayout(layoutContainerSeccion4);
               }
               break; //



           case R.id.linLayoutHeader5:
               LinearLayout layoutContainerSeccion5=findViewById(R.id.layoutContainerSeccion5);

               if(layoutContainerSeccion5.getVisibility() == View.GONE) {
                   muestraLinearLayout(layoutContainerSeccion5);
               }
               else{

                   oucultaLinearLayout(layoutContainerSeccion5);
               }
               break; //

           case R.id.linLayoutHeader6:
               LinearLayout layoutContainerSeccion6=findViewById(R.id.layoutContainerSeccion6);

               if(layoutContainerSeccion6.getVisibility() == View.GONE) {
                   muestraLinearLayout(layoutContainerSeccion6);
               }
               else{

                   oucultaLinearLayout(layoutContainerSeccion6);
               }
               break; //


           case R.id.linLayoutHeader7:
               LinearLayout layoutContainerSeccion7=findViewById(R.id.layoutContainerSeccion7);

               if(layoutContainerSeccion7.getVisibility() == View.GONE) {
                   muestraLinearLayout(layoutContainerSeccion7);
               }
               else{

                   oucultaLinearLayout(layoutContainerSeccion7);
               }
               break; //

           case R.id.linLayoutHeader8:
               LinearLayout layoutContainerSeccion8=findViewById(R.id.layoutContainerSeccion8);

               if(layoutContainerSeccion8.getVisibility() == View.GONE) {
                   muestraLinearLayout(layoutContainerSeccion8);
               }
               else{

                   oucultaLinearLayout(layoutContainerSeccion8);
               }
               break; //


           case R.id.ediCajas7:
              // Utils.closeKeyboard(FormularioActivity.this);

               selecionaFecha();

               break; //



           case R.id.ediHoraInicio:
              // Utils.closeKeyboard(FormularioActivity.this);

               showingTimePicker(view);

               break; //

           case R.id.ediHoraTermino:
             // Utils.closeKeyboard(FormularioActivity.this);
               showingTimePicker(view);

               break; //
           case R.id.ediHoraSalidaContenedor:
               // Utils.closeKeyboard(FormularioActivity.this);
               showingTimePicker(view);

               break; //


           case R.id.ediHoraLLegadaContenedor:
               // Utils.closeKeyboard(FormularioActivity.this);
               showingTimePicker(view);

               break; //

           case R.id.ediHoraEncendido1:
               // Utils.closeKeyboard(FormularioActivity.this);
               showingTimePicker(view);

               break; //

           case R.id.ediHoraEncendido2:
               // Utils.closeKeyboard(FormularioActivity.this);
               showingTimePicker(view);

               break;

           case R.id.imbAtach:

               currentTypeImage=Variables.FOTO_LLEGADA;

               Log.i("miclickimg","es foto es type Variables.FOTO_LLEGADA");

               activityResultLauncher.launch("image/*");
               break;


           case R.id.imbTakePic:
               Log.i("miclickimg","es foto es type Variables.FOTO_LLEGADA");

               currentTypeImage=Variables.FOTO_LLEGADA;

               takepickNow();
               break;



           case R.id.imbAtach_transportista:
               currentTypeImage=Variables.FOTO_TRANSPORTISTA;
               Log.i("miclickimg","es foto es type Variables.FOTO_TRANSPORTISTA");

               activityResultLauncher.launch("image/*");
               break;



           case R.id.imbTakePicTransportista:
               currentTypeImage=Variables.FOTO_TRANSPORTISTA;
               Log.i("miclickimg","es foto es type Variables.FOTO_TRANSPORTISTA");


               takepickNow();
               break;



           case R.id.imbAtachSellosLlegada:
               currentTypeImage=Variables.FOTO_SELLO_LLEGADA;
               Log.i("miclickimg","es foto es type Variables.FOTO_SELLO_LLEGADA");

               activityResultLauncher.launch("image/*");
               break;



           case R.id.imbTakePicSellosLLegada:
               Log.i("miclickimg","es foto es type Variables.FOTO_SELLO_LLEGADA");

               currentTypeImage=Variables.FOTO_TRANSPORTISTA;

               takepickNow();
               break;



           case R.id.imbAtachDatosContenedor:
               currentTypeImage=Variables.FOTO_CONTENEDOR;
               Log.i("miclickimg","es foto es type Variables.FOTO_CONTENEDOR");
               activityResultLauncher.launch("image/*");


               break;


           case R.id.imbTakePicDatosContenedor:
               Log.i("miclickimg","es foto es type Variables.FOTO_CONTENEDOR");

               currentTypeImage=Variables.FOTO_CONTENEDOR;

               takepickNow();
               break;



           case R.id.imbAtachPrPostcosecha:
               currentTypeImage=Variables.FOTO_PROD_POSTCOSECHA;
               Log.i("miclickimg","es foto es type Variables.FOTO_PROD_POSTCOSECHA");

               activityResultLauncher.launch("image/*");
               break;


           case R.id.imbTakePicPrPostcosecha:
               Log.i("miclickimg","es foto es type Variables.FOTO_PROD_POSTCOSECHA");

               currentTypeImage=Variables.FOTO_PROD_POSTCOSECHA;

               takepickNow();
               break;


           case R.id.imgAtachVinculacion:

               ArrayList<String>listIdSvINCULADOS;
               listIdSvINCULADOS=generateLISTbyStringVinculados(CustomAdapter.idsFormsVinucladosControlCalidadString);
               listFormsControlCalidad = new ArrayList<>();
               mapControlCalidad= new HashMap<>();


               checkedListForms = new ArrayList<>();

               if(listIdSvINCULADOS.size()>0 ){  //si existen vinuclados DESCRAGAMOS los informes viculados usando los ids uniqe id
                   showReportsAndSelectOrDeleteVinuclados(ActivityContenedoresPrev.this,true);
                   for(String value: listIdSvINCULADOS){
                       Log.i("salero","se ejecuto esto veces");
                       dowloadReportsVinucladosAndSetinRecycler(value, listIdSvINCULADOS);
                   }

                  }

               else
               { //si esta lista esta vacia inteta con otras......

                   calltoDowloadReportsVinculadoCudroMuestreo();
               }


               break;



       }

        //aqui o


    }


    private void calltoDowloadReportsVinculadoCudroMuestreo(){
        ArrayList<String>listVinculadoCalidadAndRechazads;

        listVinculadoCalidadAndRechazads=generateLISTbyStringVinculados(CustomAdapter.idsFormsVinucladosCudorMuestreoString);
       // listFormsControlCalidad = new ArrayList<>();

       ///agregamos estos valores a esta lista...

       // checkedListForms = new ArrayList<>();

        if(listVinculadoCalidadAndRechazads.size()>0 ){  //si existen vinuclados DESCRAGAMOS los informes viculados usando los ids uniqe id
           // showReportsAndSelectOrDeleteVinuclados(ActivityContenedoresPrev.this,true);
             //CALCULAMOS CUANTOS REPORTES DEBEN HABER EN TOTAL


            Log.i("hanoaia","se llamo aqui ");


            int reportesTOtalquedebenVinculares=listVinculadoCalidadAndRechazads.size();


            Log.i("hanoaia","el numreportesdeven vincularese es  "+reportesTOtalquedebenVinculares);


            for(String value: listVinculadoCalidadAndRechazads){
                Log.i("hanoaia","se ejecuto esto veces");
                DowloadCalbAndRechazadosAndSetRecicler(reciclerViewBottomSheet,true,value,reportesTOtalquedebenVinculares);
            }


        }else{



            Log.i("hanoaia","se ejecuto el else ");

            showReportsAndSelectOrDeleteVinuclados(ActivityContenedoresPrev.this,false);


        }

    }


    private ArrayList<String> generateLISTbyStringVinculados(String controlCalidStrin,String cuadroReportString ){

        ArrayList<String>listIdSvINCULADOS= new ArrayList<>();


        if(controlCalidStrin!=null){
            String [] miarrayiNFORMESvinc = controlCalidStrin.split(",");
            Log.i("datamapitkka","el size de aara es "+miarrayiNFORMESvinc.length);

            if(miarrayiNFORMESvinc.length >1) {

                for(String value : miarrayiNFORMESvinc){
                    listIdSvINCULADOS.add(value);
                }

                Log.i("datamapitkka","es mayor a 1"+listIdSvINCULADOS.size());

            }
            else{

                listIdSvINCULADOS.add(controlCalidStrin);
                Log.i("datamapitkka","no es mayor a 1 y el value es "+listIdSvINCULADOS.size());

            }

        }




        if(cuadroReportString!=null){
            String [] miarrayiNFORMESvincx = cuadroReportString.split(",");
            Log.i("datamapitkka","el size de aara es "+miarrayiNFORMESvincx.length);

            if(miarrayiNFORMESvincx.length >1) {

                for(String value : miarrayiNFORMESvincx){
                    listIdSvINCULADOS.add(value);
                }

                Log.i("datamapitkka","es mayor a 1"+listIdSvINCULADOS.size());

            }
            else{

                listIdSvINCULADOS.add(cuadroReportString);
                Log.i("datamapitkka","no es mayor a 1 y el value es "+listIdSvINCULADOS.size());

            }

        }







        return listIdSvINCULADOS;
    }



    private ArrayList<String> generateLISTbyStringVinculados(String ValueLineViculados ){

        ArrayList<String>listIdSvINCULADOS= new ArrayList<>();



        if(ValueLineViculados!=null){
            String [] miarrayiNFORMESvinc = ValueLineViculados.split(",");
            Log.i("datamapitkka","el size de aara es "+miarrayiNFORMESvinc.length);

            if(miarrayiNFORMESvinc.length >1) {

                for(String value : miarrayiNFORMESvinc){
                    listIdSvINCULADOS.add(value);
                }

                Log.i("datamapitkka","es mayor a 1"+listIdSvINCULADOS.size());

            }
            else{

                listIdSvINCULADOS.add(ValueLineViculados);
                Log.i("datamapitkka","no es mayor a 1 y el value es "+listIdSvINCULADOS.size());

            }

        }




        return listIdSvINCULADOS;
    }

    private void generateAnDowloadPdf(SetInformEmbarque1 objPrimeraParte, SetInformEmbarque2 objSegundaParte, ProductPostCosecha productPost) {
        //generamos el pdf usnado el objeto 1 y 2

       // PdfMaker.generatePdfReport1(ActivityContenedoresPrev.this,objPrimeraParte,objSegundaParte,productPost);




    }


    private void showReportsAndSelectOrDeleteVinuclados(Context context,boolean existeValues) {

        bottomSheetDialog = new BottomSheetDialog(context);

        bottomSheetDialog.setContentView(R.layout.bottom_sheet_ver_atachx);

        bottomSheetDialog.setCancelable(false);

//        CheckBox checkBx1 = bottomSheetDialog.findViewById(R.id.checkBx1);
        reciclerViewBottomSheet =bottomSheetDialog.findViewById(R.id.mirecyclerViewAtach);
        Spinner spinner=bottomSheetDialog.findViewById(R.id.spinnerSelectrodate);
        // spinner.setSelection(posicionSelectedSpinnerx);
        ImageView imgClose=bottomSheetDialog.findViewById(R.id.imgClose);


        // reciclerView.setHasFixedSize(true);
        txtAdviseer=bottomSheetDialog.findViewById(R.id.txtAdviseer);
        txtAdviserDesvicunlar=bottomSheetDialog.findViewById(R.id.txtAdviserDesvicunlar);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //aqui actualizamos...

                txtNumReportsVinclds.setText(String.valueOf(Utils.numReportsVinculadsAll));

                if(CustomAdapter.idsFormsVinucladosCudorMuestreoString!=null){
                    if(CustomAdapter.idsFormsVinucladosCudorMuestreoString.trim().length()>1){
                        DowloadUniqeuRechazadosObjectCUADROMuestreoAndSetNumRechzados(CustomAdapter.idsFormsVinucladosCudorMuestreoString);


                    }
                }


                bottomSheetDialog.dismiss();




            }
        });



        if(existeValues){
            txtAdviseer.setVisibility(TextView.GONE);

        }




        bottomSheetDialog.show();


        /////
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selecionado=spinner.getSelectedItem().toString();
                Calendar cal = Calendar.getInstance();

                Calendar cald2 = Calendar.getInstance();


                //   idsFormsControlCalidVinculados=generateLISTbyStringVinculados(CustomAdapter.idsFormsVinucladosCntres);

                if(selecionado.equals("Hoy")) {

                    // long timeCurrent = new Date().getTime();
                    cal.add(Calendar.DATE, -0);
                    cald2.add(Calendar.DATE,0);

                    //  dowloadEspecificReprtControCalidadVinculados();

                    /**tambien chekamos que si estan chekeeds...*/

                    dowloadinformesby_RangeDateAndCallAndCALLdOWLODCuadroMuestreo(cal.getTimeInMillis(),cald2.getTimeInMillis(),generateLISTbyStringVinculados(CustomAdapter.idsFormsVinucladosControlCalidadString),generateLISTbyStringVinculados(CustomAdapter.idsFormsVinucladosCudorMuestreoString));




                    //  System.out.println("Date = "+ cal.getTimeInMillis());

                }

                else if(selecionado.equals("Ayer")) {

                    cal.add(Calendar.DATE, -1);
                    cald2.add(Calendar.DATE,0);

                    dowloadinformesby_RangeDateAndCallAndCALLdOWLODCuadroMuestreo(cal.getTimeInMillis(),cald2.getTimeInMillis(),generateLISTbyStringVinculados(CustomAdapter.idsFormsVinucladosControlCalidadString),generateLISTbyStringVinculados(CustomAdapter.idsFormsVinucladosCudorMuestreoString));

                    //  System.out.println("Date = "+ cal.getTimeInMillis());

                }





                else if(selecionado.equals("Ultimos 7 dias")) {

                    cal.add(Calendar.DATE, -7);
                    cald2.add(Calendar.DATE,0);


                    dowloadinformesby_RangeDateAndCallAndCALLdOWLODCuadroMuestreo(cal.getTimeInMillis(),cald2.getTimeInMillis(),generateLISTbyStringVinculados(CustomAdapter.idsFormsVinucladosControlCalidadString),generateLISTbyStringVinculados(CustomAdapter.idsFormsVinucladosCudorMuestreoString));



                    Log.i("sabemosd","la data "+cal.getTimeInMillis());

                    //  System.out.println("Date = "+ cal.getTimeInMillis());

                }


                else if(selecionado.equals("Ultimos 15 dias")) {

                    cal.add(Calendar.DATE, -15);
                    cald2.add(Calendar.DATE,0);

                    dowloadinformesby_RangeDateAndCallAndCALLdOWLODCuadroMuestreo(cal.getTimeInMillis(),cald2.getTimeInMillis(),generateLISTbyStringVinculados(CustomAdapter.idsFormsVinucladosControlCalidadString),generateLISTbyStringVinculados(CustomAdapter.idsFormsVinucladosCudorMuestreoString));

                    Log.i("sabemosd","la data "+cal.getTimeInMillis());

                }

                else if(selecionado.equals("Ultimos 30 dias")) {

                    cal.add(Calendar.DATE, -30);
                    cald2.add(Calendar.DATE,0);

                    dowloadinformesby_RangeDateAndCallAndCALLdOWLODCuadroMuestreo(cal.getTimeInMillis(),cald2.getTimeInMillis(),generateLISTbyStringVinculados(CustomAdapter.idsFormsVinucladosControlCalidadString),generateLISTbyStringVinculados(CustomAdapter.idsFormsVinucladosCudorMuestreoString));

                    Log.i("sabemosd","la data "+cal.getTimeInMillis());
                }


                else if(selecionado.equals("Reportes Vinculados")) {


                    if(CustomAdapter.idsFormsVinucladosControlCalidadString !=null){  //si existen vinuclados DESCRAGAMOS ESTOS Y OTTRO BY DATE
                        if(CustomAdapter.idsFormsVinucladosControlCalidadString.length()>0){


                            dowloadinformesby_RangeDateAndCallAndCALLdOWLODCuadroMuestreo(cal.getTimeInMillis(),cald2.getTimeInMillis(),generateLISTbyStringVinculados(CustomAdapter.idsFormsVinucladosControlCalidadString),generateLISTbyStringVinculados(CustomAdapter.idsFormsVinucladosCudorMuestreoString));


                        }
                    }else{
                        txtAdviseer.setText("No existe Ningun Reporte Vinculado");
                        txtAdviseer.setVisibility(TextView.VISIBLE);
                    }


                    Log.i("sabemosd","la data "+cal.getTimeInMillis());
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });






    }

    void dowloadinformesby_RangeDateAndCallAndCALLdOWLODCuadroMuestreo(long desdeFecha, long hastFecha, ArrayList<String>idsFormsControlCalidVinculadosOmit,ArrayList<String>idsFormsCudroOmits){

        listFormsControlCalidad = new ArrayList<>();
        mapControlCalidad= new HashMap<>();

        checkedListForms = new ArrayList<>();

        RealtimeDB.initDatabasesRootOnly();
        // Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("listControCalidad").orderByChild("simpleDate").equalTo(dateSelecionado);
        Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("listControCalidad").orderByChild("timeDateMillis").startAt(desdeFecha).endAt(hastFecha);


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    ControlCalidad controlcalidad=ds.getValue(ControlCalidad.class);


                    //agregamos solo los que no esten en esta lista..
                    if(controlcalidad!=null){
                        listFormsControlCalidad.add(controlcalidad);
                        mapControlCalidad.put(controlcalidad.getUniqueId(),controlcalidad);


                        if(idsFormsControlCalidVinculadosOmit !=null){

                            if(idsFormsControlCalidVinculadosOmit.contains(controlcalidad.getUniqueId())){

                                checkedListForms.add(new CheckedAndAtatch(controlcalidad.getSimpleDate(),controlcalidad.getHacienda(),"Control calidad",true,String.valueOf(controlcalidad.getUniqueId())));


                            }else {

                                checkedListForms.add(new CheckedAndAtatch(controlcalidad.getSimpleDate(),controlcalidad.getHacienda(),"Control calidad",false,String.valueOf(controlcalidad.getUniqueId())));

                            }




                        }else{
                            checkedListForms.add(new CheckedAndAtatch(controlcalidad.getSimpleDate(),controlcalidad.getHacienda(),"Control calidad",false,String.valueOf(controlcalidad.getUniqueId())));


                        }


                    }


                }

                //ceramos el anterior ..mostramos este...

                Log.i("samerr","se llamo sedatauncrecicler en dowloadinformesby_RangeDateAndCallShowSheetB ");
                Log.i("samerr","y el size es  "+ checkedListForms.size());


                dowloadinformesby_RangeDateCuadroMuestreoAndCallShowSheet(desdeFecha,hastFecha,idsFormsCudroOmits);


                //   showReportsAndSelectOrDeleteVinuclados(ActivityContenedores.this,existValues);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }



    void dowloadinformesby_RangeDateCuadroMuestreoAndCallShowSheet(long desdeFecha, long hastFecha, ArrayList<String>idsFormsCuadroMuestreoVinculadosOmit){

        //listFormsControlCalidad = new ArrayList<>();

      //  checkedListForms = new ArrayList<>();

      //  RealtimeDB.initDatabasesRootOnly();
        // Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("listControCalidad").orderByChild("simpleDate").equalTo(dateSelecionado);
        Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("CuadrosMuestreo").orderByChild("dateInMillisecond").startAt(desdeFecha).endAt(hastFecha);


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    CuadroMuestreo cuadroMuestreo=ds.getValue(CuadroMuestreo.class);


                    //agregamos solo los que no esten en esta lista..
                    if(cuadroMuestreo!=null){

                        listCuadrosMuestreos.add(cuadroMuestreo);
                        mapCudroMuestreo.put(cuadroMuestreo.getUniqueIdObject(),cuadroMuestreo);


                        if(idsFormsCuadroMuestreoVinculadosOmit !=null){

                            if(idsFormsCuadroMuestreoVinculadosOmit.contains(cuadroMuestreo.getUniqueIdObject())){

                                checkedListForms.add(new CheckedAndAtatch(cuadroMuestreo.getSimpleDateFormat(),cuadroMuestreo.getProductor(),"Cuadro Muestreo",true,String.valueOf(cuadroMuestreo.getUniqueIdObject())));


                            }else {

                                checkedListForms.add(new CheckedAndAtatch(cuadroMuestreo.getSimpleDateFormat(),cuadroMuestreo.getProductor(),"Cuadro Muestreo",false,String.valueOf(cuadroMuestreo.getUniqueIdObject())));

                            }




                        }else{
                            checkedListForms.add(new CheckedAndAtatch(cuadroMuestreo.getSimpleDateFormat(),cuadroMuestreo.getProductor(),"Cuadro Muestreo",false,String.valueOf(cuadroMuestreo.getUniqueIdObject())));


                        }


                    }


                }

                //ceramos el anterior ..mostramos este...

                Log.i("samerr","se llamo sedatauncrecicler en dowloadinformesby_RangeDateAndCallShowSheetB ");
                Log.i("samerr","y el size es  "+ checkedListForms.size());


                setDataInRecyclerOfBottomSheet(reciclerViewBottomSheet, checkedListForms,false);



                //   showReportsAndSelectOrDeleteVinuclados(ActivityContenedores.this,existValues);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }





    private void takepickNow() {

        Permisionx.checkPermission(Manifest.permission.CAMERA,1,this, ActivityContenedoresPrev.this);


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "AppQsercom");
            values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");


             cam_uri = ActivityContenedoresPrev.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cam_uri);


            //startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE); // OLD WAY
            startCamera.launch(cameraIntent);                // VERY NEW WAY


        }




    }

        ActivityResultLauncher<Intent> startCamera = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            // There are no request codes


                            //  mImageView.setImageURI(cam_uri);

                           // showImageByUri(cam_uri);
                          //  Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.onActivityResult();, imageUri);


                            try {

                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(ActivityContenedoresPrev.this.getContentResolver(),cam_uri);


                              //  Bitmap bitmap=Glide.with(context).asBitmap().load(cam_uri).submit().get();
                                String horientacionImg=HelperImage.devuelveHorientacionImg(bitmap);

                                //creamos un nuevo objet de tipo ImagenReport
                                ImagenReport obcjImagenReport =new ImagenReport("",cam_uri.toString(),currentTypeImage,UNIQUE_ID_iNFORME, Utils.getFileNameByUri(ActivityContenedoresPrev.this,cam_uri),horientacionImg);

                                //agregamos este objeto a la lista
                                ImagenReport.hashMapImagesData.put(obcjImagenReport.getUniqueIdNamePic(), obcjImagenReport);


                                showImagesPicShotOrSelectUpdateView(false);

                            }

                          catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                        }
                    }
                });



    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {


        if(motionEvent.getAction()==MotionEvent.ACTION_DOWN ){

            //agregamos esta vista clickada a la lista

            Log.i("casnasd","se llamo on touch ");

            listViewsClickedUser.add(view);


            Log.i("casnasd","el size de la lista es "+listViewsClickedUser.size());

            if(listViewsClickedUser.size()>1) {
                //obtenemos la lista anterior y verficamos si esta completada;
                View vistFieldAnterior = getVistaAnteriorClick();
                checkeamosSiFieldViewIScompleted(vistFieldAnterior);
                //actualizamos


            }



        }
        return false;
    }



    @SuppressLint("ClickableViewAccessibility")
    private void addOnTouchaMayoriaDeViews(){
        ediObservacion.setOnTouchListener(this);
        ediSemana.setOnTouchListener(this);
        ediFecha.setOnTouchListener(this);
        ediProductor.setOnTouchListener(this);
        ediHacienda.setOnTouchListener(this);
       // ediCodigo.setOnTouchListener(this);
        ediInscirpMagap.setOnTouchListener(this);
        ediPemarque.setOnTouchListener(this);
        ediHoraInicio.setOnTouchListener(this);
        ediHoraTermino.setOnTouchListener(this);
        ediNguiaRemision.setOnTouchListener(this);
        edi_nguia_transporte.setOnTouchListener(this);
        ediNtargetaEmbarque.setOnTouchListener(this);
        ediNhojaEvaluacion.setOnTouchListener(this);
        spinnerSelectZona.setOnTouchListener(this);

         spinnerCondicionBalanza.setOnTouchListener(this);
         spinnertipoCaja.setOnTouchListener(this);
         spinnertipodePlastico.setOnTouchListener(this);
         spinnertipodeBlanza.setOnTouchListener(this);
         spinnertipodeBlanzaRepeso .setOnTouchListener(this);
         spinnerubicacionBalanza.setOnTouchListener(this);






        switchContenedor.setOnTouchListener(this);
        ediEmpacadora.setOnTouchListener(this);
          imBatach.setOnTouchListener(this);

        imBtakePic.setOnTouchListener(this);




        ediPPC01.setOnTouchListener(this);
        ediPPC02.setOnTouchListener(this);
        ediPPC03.setOnTouchListener(this);
        ediPPC04.setOnTouchListener(this);
        ediPPC05.setOnTouchListener(this);
        ediPPC06.setOnTouchListener(this);
        ediPPC07.setOnTouchListener(this);
        ediPPC08.setOnTouchListener(this);
        ediPPC09.setOnTouchListener(this);
        ediPPC010.setOnTouchListener(this);
        ediPPC011.setOnTouchListener(this);
        ediPPC012.setOnTouchListener(this);
        ediPPC013.setOnTouchListener(this);
        ediPPC014.setOnTouchListener(this);
        ediPPC015.setOnTouchListener(this);
        ediPPC016.setOnTouchListener(this);


    }


    private View getVistaAnteriorClick() { //el estado puede ser lleno o vacio isEstaLleno


        if(listViewsClickedUser.size() ==3) { //SOLO GUARDAMOS DOS NUMEROS para ahorra memoria
            listViewsClickedUser.remove(0);   //ya no queremoes el primer objeto de la lista siempre y cuando la lista contnega 3 objetos

        }
        Log.i("casnasd","el size aqui en metodo es "+listViewsClickedUser.size());




        View vistAnterior = listViewsClickedUser.get(0);
      //  Log.i("soeobjetc","el objeto anterioR TAG ES "+vistAnterior.getTag().toString());



        return   vistAnterior;

    }


    private void checkeamosSiFieldViewIScompleted(View view ) {

        //revismaos si el usuario lleno el file o completo la tarea solictada

        Log.i("miodata","el id del selecionado anterior es "+view.getResources().getResourceName(view.getId()));


        if (view instanceof EditText) { //asi es un editex compobamos si esta lleno
            EditText editText = (EditText) view; //asi lo convertimos
             Log.i("miodata","el id es "+view.getResources().getResourceName(view.getId()));

                if ( view.getResources().getResourceName(view.getId()).contains("ediPPC0")){ //asi comprobamos que es un fiel opcional
                    if (editText.getText().toString().length() > 0) {
                        if (!editText.getText().toString().equals("0")) {

                            Log.i("miodata","el state ediPPC/someProductPostCosecha esta lleno ");


                            actualizaListStateView("ediPPC/someProductPostCosecha",true) ;



                        }
                    }

                }

                else if(editText.getText().toString().isEmpty()) {


                    Log.i("idCheck","la data del editext anterior : "+view.getResources().getResourceName(view.getId() )+" esta vacio");


                    actualizaListStateView(view.getResources().getResourceName(view.getId()),false) ;

                }




                ////si existe lo cambiamos a tru



                else if(! editText.getText().toString().isEmpty()) { //si esta lleno

                    Log.i("idCheck","la data del editext anterior : "+view.getResources().getResourceName(view.getId() )+" esta lleno");

                    actualizaListStateView(view.getResources().getResourceName(view.getId()),true) ;



                }



        }




        else if (view.getResources().getResourceName(view.getId()).contains("imbAtach")  ||  view.getResources().getResourceName(view.getId()).contains("imbTakePic")){ //imBtakePic

             //COMPORBAQMOS SI EXISTE AL ME4NOS UN IMAGEN URI LIST..

            if(ImagenReport.hashMapImagesData.size()> 0 ) {
                actualizaListStateView("imbAtach/imbTakePic",true) ;

               Log.i("miodata","el slecionado anteruior es imbAtach/imbTakePic y contiene al menos una foto");


            }else {

                actualizaListStateView("imbAtach/imbTakePic",false) ;
                Log.i("miodata","el slecionado anteruior es imbAtach/imbTakePic y no contiene fotos");



            }


        }






        //seran mas comprobacion para verificar si imagenes por ejemplo fiueron completadas..
        //otra para radiobutton y otr para otro tipo de view..tec


        actualizaProgressBar();

            }



    private void actualizaListStateView(String idSearch,boolean isEstaLleno){
///


        final String  idview = idSearch.replace(Variables.paqueteName+":id/","");

       //com.tiburela.qsercom:id/ediCodigo") ;

        Log.i("camisila","el id to search es "+idview) ;

        for(int i=0; i<EstateFieldView.listEstateViewField.size(); i++){

            if(EstateFieldView.listEstateViewField.get(i).getIdOfView().equals(idview)){

                EstateFieldView.listEstateViewField.get(i).setEstaLleno(isEstaLleno);


            }else  {



            }

        }







    }


    private void actualizaProgressBar(){

             int numero_itemsCompletados=0;

            final int NUMERO_FIELDS_TOTAL=EstateFieldView.listEstateViewField.size(); // 19  ahora items emn total de completar 19,, algunos son opcionales...pero siempre deben haber 19 para que todos esten llenos


            for(int i=0; i<EstateFieldView.listEstateViewField.size(); i++){

                if(EstateFieldView.listEstateViewField.get(i).isEstaLleno()){

                    numero_itemsCompletados =numero_itemsCompletados+1;


                }
            }

        Log.i("idCheck","el NUMERO ITEMScOMPLETADOS ES "+numero_itemsCompletados);


            //buscamos el porecntaje

        //int porcentajeDeProgreso= numero_itemsCompletados*NUMERO_FIELDS_TOTAL/100;

        int porcentajeDeProgreso= numero_itemsCompletados*100/NUMERO_FIELDS_TOTAL;

        progressBarFormulario.setProgress(porcentajeDeProgreso);



           Log.i("maswiso","el porciento es "+porcentajeDeProgreso);
            //un item opcional vale



        }







    private void selecImages(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);

        resultatachImages();



    }


    private void resultatachImages() {
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.GetMultipleContents(), new ActivityResultCallback<List<Uri>>() {
                    @Override
                    public void onActivityResult(List<Uri> result) {
                        if (result != null) {

                            //creamos un objeto

                            Log.i("mispiggi","el current type es "+currentTypeImage);
                            Log.i("mispiggi","el size de la list uris es "+result.size());
                            Log.i("mispiggi","el size de la  lista antes del for es  hashMapImagesData es "+ ImagenReport.hashMapImagesData.size());


                            for(int indice=0; indice<result.size(); indice++){



                                try {

                                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(ActivityContenedoresPrev.this.getContentResolver(),result.get(indice));

                                   // Bitmap bitmap=Glide.with(context).asBitmap().load(result.get(indice)).submit().get();
                                    String horientacionImg=HelperImage.devuelveHorientacionImg(bitmap);

                                    //creamos un nuevo objet de tipo ImagenReport
                                    ImagenReport obcjImagenReport = new ImagenReport("",result.get(indice).toString(),currentTypeImage,UNIQUE_ID_iNFORME, Utils.getFileNameByUri(ActivityContenedoresPrev.this,result.get(indice)), horientacionImg);

                                    //agregamos este objeto a la lista
                                    ImagenReport.hashMapImagesData.put(obcjImagenReport.getUniqueIdNamePic(), obcjImagenReport);


                                    showImagesPicShotOrSelectUpdateView(false);

                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                                showImagesPicShotOrSelectUpdateView(false);



                           ///     ImagenReport imagenReportObjc =new ImagenReport("",result.get(indice).toString(),currentTypeImage,UNIQUE_ID_iNFORME,Utils.getFileNameByUri(ActivityContenedoresPrev.this,result.get(indice)));

                             //   ImagenReport.hashMapImagesData.put(imagenReportObjc.getUniqueIdNamePic(), imagenReportObjc);
                              //  Log.i("mispiggi","el size de la  lists  el key del value es "+imagenReportObjc.getUniqueIdNamePic());


                            }

                            Log.i("mispiggi","el size de la  lists  hashMapImagesData ahora es  es "+ ImagenReport.hashMapImagesData.size());

                           // showImagesPicShotOrSelectUpdateView(false);



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

        spinnerSelectZona .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String zonaEelejida= spinnerSelectZona.getSelectedItem().toString();
                ediZona.setText(zonaEelejida);


                Log.i("mizona","la zona aen listenner spinner es"+zonaEelejida);

                if(zonaEelejida.equals("Ninguna")){
                    //actualizamos
                    Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                    ediZona.setText("");
                    actualizaListStateView("spinnerZona",false) ;
                }else {
                    actualizaListStateView("spinnerZona",true) ;
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
                ediZona.setText("");
                actualizaListStateView("addetiquetaaqui",false) ;
            }else {
                actualizaListStateView("addetiquetaaqui",true) ;
            }

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
                actualizaListStateView("addetiquetaaqui",false) ;
            }else {
                actualizaListStateView("addetiquetaaqui",true) ;
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
                actualizaListStateView("addetiquetaaqui",false) ;
            }else {
                actualizaListStateView("addetiquetaaqui",true) ;
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
                actualizaListStateView("addetiquetaaqui",false) ;
            }else {
                actualizaListStateView("addetiquetaaqui",true) ;
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
                actualizaListStateView("addetiquetaaqui",false) ;
            }else {
                actualizaListStateView("addetiquetaaqui",true) ;
            }

        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });



    spinnerubicacionBalanza .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String zonaEelejida= spinnerubicacionBalanza.getSelectedItem().toString();

            ediUbicacionBalanza.setText(zonaEelejida);

            if(zonaEelejida.equals("Ninguna")){
                //actualizamos
                Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                ediUbicacionBalanza.setText("");
                actualizaListStateView("addetiquetaaqui",false) ;
            }else {
                actualizaListStateView("addetiquetaaqui",true) ;
            }

        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });


    switchContenedor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            if(switchContenedor.isChecked()){

                ediContenedor.setText(" SI ") ;

            }else {
                ediContenedor.setText(" NO ") ;

            }
        }
    });




}


private void showImagesPicShotOrSelectUpdateView(boolean isDeleteImg){

        //si es eliminar comprobar aqui
    if(isDeleteImg){

        Log.i("mispiggi","si es ly_defparte3.xml imgVERGA Y EL SIZE ES  "+ ImagenReport.hashMapImagesData.size());

        currentTypeImage=Variables.typeoFdeleteImg;
    }


     ArrayList<ImagenReport> filterListImagesData=new ArrayList<ImagenReport>(); //LISTA FILTRADA QUE REPRESENTARA EL RECICLERVIEW

    RecyclerView recyclerView= findViewById(R.id.recyclerView);


    Log.i("mispiggi","el size de la MAPA AHORAXXC ES  "+ ImagenReport.hashMapImagesData.size());


    for (Map.Entry<String, ImagenReport> set : ImagenReport.hashMapImagesData.entrySet()) {

        String key = set.getKey();

        ImagenReport value = set.getValue();

        if(value.getTipoImagenCategory()==currentTypeImage){

            filterListImagesData.add(ImagenReport.hashMapImagesData.get(key));

        }


    }


    //buscamos este

    Log.i("mispiggi","el size de la  lists  hashMapImagesData HERE  es  es "+ ImagenReport.hashMapImagesData.size());


    //si la imagen es la imagen de fotos llegada INICLIZAMOS ASI
    if(currentTypeImage== Variables.FOTO_LLEGADA)  {
         recyclerView= findViewById(R.id.recyclerView);


    }
    else if (currentTypeImage==Variables.FOTO_PROD_POSTCOSECHA){
         recyclerView= findViewById(R.id.recyclerViewPostcosecha);
        // at last set adapter to recycler view.

    }

    else if (currentTypeImage==Variables.FOTO_TRANSPORTISTA){
        recyclerView = findViewById(R.id.recyclerVieDatsTransport);

    }

    else if (currentTypeImage==Variables.FOTO_CONTENEDOR){
        recyclerView = findViewById(R.id.recyclerViewDatosContenedor);
    }


    else if (currentTypeImage==Variables.FOTO_SELLO_LLEGADA){
        recyclerView = findViewById(R.id.recyclerViewSellosLlegada);

    }



    Log.i("mispiggi","el size de la  lists  hashMapImagesData HERE AA  es  es "+ ImagenReport.hashMapImagesData.size());


    RecyclerViewAdapter adapter=new RecyclerViewAdapter(filterListImagesData,this);
    GridLayoutManager layoutManager=new GridLayoutManager(this,2);


    // at last set adapter to recycler view.
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);
    Log.i("mispiggi","el size de la  lists  hashMapImagesData HERE EE  es  es "+ ImagenReport.hashMapImagesData.size());


    eventoBtnclicklistenerDelete(adapter);
    Log.i("mispiggi","el size de la  lists  hashMapImagesData HERE SA  es  es "+ ImagenReport.hashMapImagesData.size());



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

void checkDataFields(){

  //  checkDatosGeneralesIsLleno();


    //ES ETEST


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


    if(! checkDatosContenedorIsLleno()){
        Log.i("test001","no esta lleno  checkDatosContenedorIsLleno");

        return;
    }else{

        Log.i("test001","si  esta lleno  checkDatosContenedorIsLleno");
    }


    if(! checkDataSellosLlegadaIsLleno()){
        Log.i("test001","no esta lleno  checkDataSellosLlegadaIsLleno");

        return;
    }else{

        Log.i("test001","si  esta lleno  checkDataSellosLlegadaIsLleno");


    }


    if(! checkSellosInstaladosIsLleno()){
        Log.i("test001","no esta lleno  checkSellosInstaladosIsLleno");

        return;
    }else{

        Log.i("test001","si  esta lleno  checkSellosInstaladosIsLleno");


    }


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


    if(! checkQueexistminim()){
        Log.i("test001","no esta lleno  checkDataCalibFrutaCalEnfn");

        return;
    }else

    {

        Log.i("test001","si  esta lleno  checkDataCalibFrutaCalEnfn");


    }
    if(getResultDatCalibCalEnfundes()){
        Log.i("test001","no esta lleno  getResultDatCalibCalEnfundes");

        return;
    }else

    {

        Log.i("test001","si  esta lleno  getResultDatCalibCalEnfundes");


    }




    openBottomSheet();

}


    private boolean getResultDatCalibCalEnfundes(){



        if(ediRacimosCosech.getText().toString().trim().isEmpty()){
            ediRacimosCosech.requestFocus();
            ediRacimosCosech.setError("Este valor es necesesario");

            return false;

        }



        TextInputEditText ediNumRcim14 = findViewById(R.id.ediNumRcim14);
        TextInputEditText ediNumRcim13 = findViewById(R.id.ediNumRcim13);
        TextInputEditText ediNumRcim12 = findViewById(R.id.ediNumRcim12);
        TextInputEditText ediNumRcim11 = findViewById(R.id.ediNumRcim11);
        TextInputEditText ediNumRcim10 = findViewById(R.id.ediNumRcim10);
        TextInputEditText ediNumRac9 = findViewById(R.id.ediNumRac9);


        TextInputEditText ediPorc14=findViewById(R.id.ediPorc14);
        TextInputEditText ediPorc13=findViewById(R.id.ediPorc13);
        TextInputEditText ediPorc12=findViewById(R.id.ediPorc12);
        TextInputEditText ediPorc11=findViewById(R.id.ediPorc11);
        TextInputEditText ediPorc10=findViewById(R.id.ediPorc10);
        TextInputEditText ediPorc9 =findViewById(R.id.ediPorc9);



        int numRacimosCosechados=Integer.parseInt(ediRacimosCosech.getText().toString());
        float resultpercente;
        DecimalFormat format = new DecimalFormat("#.##");

        int numeroRacimosContador=0;

        //numero de raCimos
        TextInputEditText [] miArrayNUmrACIMOS ={ediNumRcim14,ediNumRcim13,ediNumRcim12,ediNumRcim11,ediNumRcim10,ediNumRac9};

        TextInputEditText [] miArraypORCENTAHJES ={ediPorc14,ediPorc13,ediPorc12,ediPorc11,ediPorc10,ediPorc9};

        for(int i=0; i<miArrayNUmrACIMOS.length; i++){

            if(!miArrayNUmrACIMOS[i].getText().toString().trim().isEmpty())
            {        ///tiene que ser mayor a cero
                if(Integer.parseInt(miArrayNUmrACIMOS[i].getText().toString())>0)
                {  //operamoss
                    resultpercente= (Float.parseFloat(miArrayNUmrACIMOS[i].getText().toString())/numRacimosCosechados)*100;

                    String promDecim=format.format(resultpercente)   ;
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








    void checkDataToCreatePdf(){

        //  checkDatosGeneralesIsLleno();



        if(! checkDatosGeneralesIsLleno()){

            Log.i("test001","no esta lleno  checkDatosGeneralesIsLleno");
            return;
        }



        if(! checkcantidadPostcosechaIsLleno()){
            Log.i("test001","no esta lleno  checkcantidadPostcosechaIsLleno");
            return;
        }


        if(! checkDatosContenedorIsLleno()){
            Log.i("test001","no esta lleno  checkDatosContenedorIsLleno");
            return;
        }


        if(! checkDataSellosLlegadaIsLleno()){
            Log.i("test001","no esta lleno  checkDataSellosLlegadaIsLleno");
            return;
        }


        if(! checkSellosInstaladosIsLleno()){
            Log.i("test001","no esta lleno  checkSellosInstaladosIsLleno");
            return;
        }



        if(! checkDatosTransportistaIsLleno()){
            Log.i("test001","no esta lleno  checkDatosTransportistaIsLleno");
            return;
        }



        if(! checkDatosProcesoIsLleno()){
            Log.i("test001","no esta lleno  checkDatosProcesoIsLleno");
            return;
        }




        if (! checkDatosHaciendaIsLleno()) {
            Log.i("test001","no esta lleno  checkDatosHaciendaIsLleno");
            return;
        }




        if  (!checkQueexistminim()) {
            Log.i("test001","no esta lleno  checkDataCalibFrutaCalEnfn");
            return;
        }


        if  (!cehckExisteMiumReportsVINCULADOS()) {
            Log.i("test001","no esta lleno  cehckExisteMiumReportsVINCULADOS");
            return;
        }




//all reportsdfgdf
        // exieste al menos un reporte generado vinuculado///







        updateInformeWhitCurrentDataOfViews();

        updaTeProductsPostCosecha(); //actualizamos estetambien

        DowloadControlcalidadVinculadosandDecideIRpdfMAKER(Variables.CurrenReportPart1.getAtachControCalidadInfrms());


    }



    private boolean  cehckExisteMiumReportsVINCULADOS(){
          //   int contadroInformsControCalidad=0;
      //  int contadroInformsCuadroMuetreo=0;


      //  String [] allInformCuadroMuetreo =CustomAdapter.idsFormsVinucladosCudorMuestreoString.split(",");
       /// String [] allInformcONTROLcALIDA=CustomAdapter.idsFormsVinucladosControlCalidadString.split(",");


        if(CustomAdapter.idsFormsVinucladosCudorMuestreoString.trim().isEmpty()){
            Toast.makeText(ActivityContenedoresPrev.this, "Agrega al menos un reporte Cuadro de muestreo", Toast.LENGTH_LONG).show();
            return false;

        }


        if(CustomAdapter.idsFormsVinucladosControlCalidadString.trim().isEmpty()){
            Toast.makeText(ActivityContenedoresPrev.this, "Agrega al menos un reporte Control calia", Toast.LENGTH_LONG).show();

            return false;

        }



        /*

        if(allInformCuadroMuetreo.length>=1 && allInformcONTROLcALIDA.length>=1){

            return true;
        }

        else{

            return false;

        }

    */


        return true;
    }


private void openBottomSheet(){

    DialogConfirmChanges addPhotoBottomDialogFragment = DialogConfirmChanges.newInstance(Variables.FormPreviewContenedores);
    addPhotoBottomDialogFragment.show(getSupportFragmentManager(), DialogConfirmChanges.TAG);
}

    private void updateInformeWhitCurrentDataOfViews(){



        //aplicamos la logica PARA CREAR UN NUEVO INFORME
//SI LA DATA ES OPCIONAL EN EL FIELD LE AGREGAMOS UN "";en editex comprobacion le agragmos para que el texto no sea nulo
        Variables.CurrenReportPart1  = new SetInformEmbarque1(
                ediExportadoraProcesada.getText().toString(),ediExportadoraSolicitante.getText().toString(),
                ediMarca.getText().toString(),


                UNIQUE_ID_iNFORME,ediCodigo.getText().toString(),
                Integer.parseInt(ediNhojaEvaluacion.getText().toString()), ediZona.getText().toString()
                ,ediProductor.getText().toString(),ediCodigo.getText().toString()
                ,ediPemarque.getText().toString(),ediNguiaRemision.getText().toString(),ediHacienda.getText().toString()
                ,edi_nguia_transporte.getText().toString(),ediNtargetaEmbarque.getText().toString(),
                ediInscirpMagap.getText().toString(),ediHoraInicio.getText().toString(),ediHoraTermino.getText().toString()
                ,ediSemana.getText().toString(),ediEmpacadora.getText().toString(),ediContenedor.getText().toString(),
                FieldOpcional.observacionOpcional,ediHoraLLegadaContenedor.getText().toString(),ediHoraSalidaContenedor.getText().toString()
                ,ediDestino.getText().toString(),ediNViaje.getText().toString(),ediNumContenedor.getText().toString(),ediVapor.getText().toString(),
                ediTipoContenedor.getText().toString(),ediTare.getText().toString(),ediBooking.getText().toString(),ediMaxGross.getText().toString(),
                ediNumSerieFunda.getText().toString(),stikVentolerExterna.getText().toString(),
                ediCableRastreoLlegada.getText().toString(),ediSelloPlasticoNaviera.getText().toString(),FieldOpcional.otrosSellosLLegaEspecif);
        Variables.CurrenReportPart1.setKeyFirebase( Variables.CurrenReportPart1.getKeyFirebase()); //agregamos el mismo key qe tenia este objeto




        if( CustomAdapter.idsFormsVinucladosControlCalidadString!=null){
            Variables.CurrenReportPart1.setAtachControCalidadInfrms(CustomAdapter.idsFormsVinucladosControlCalidadString);
        }


        if( CustomAdapter.idsFormsVinucladosCudorMuestreoString!=null){
            Variables.CurrenReportPart1.setAtachControCuadroMuestreo(CustomAdapter.idsFormsVinucladosCudorMuestreoString);

        }



        Log.i("eldtatashd","el string atch es "+CustomAdapter.idsFormsVinucladosControlCalidadString);


        if(millisDateSelect >0){

            ////CONVERTIMOS A SIMPLE DATE FORMAT
            Format formatter = new SimpleDateFormat("dd-MM-yyyy");
            String fechaString = formatter.format(Variables.CurrenReportPart1.getFechaCreacionInf());
            Variables.CurrenReportPart1.setSimpleDataFormat(fechaString);
            Variables.CurrenReportPart1.setFechaCreacionInf(millisDateSelect);


        }




        Variables.CurrenReportPart2 = new SetInformEmbarque2(UNIQUE_ID_iNFORME,ediTermofrafo1.getText().toString(),ediTermofrafo2.getText().toString()
                ,ediHoraEncendido1.getText().toString(),ediHoraEncendido2.getText().toString(),
                ediUbicacion1.getText().toString(),ediUbicacion2.getText().toString(),ediRuma1.getText().toString(),ediRuma2.getText().toString()
                ,ediCandadoqsercon.getText().toString(),ediSelloNaviera.getText().toString(),ediCableNaviera.getText().toString(),
                ediSelloPlasticoNaviera.getText().toString(),ediCandadoBotella.getText().toString(),ediCableExportadora.getText().toString(),
                ediSelloAdesivoexpor.getText().toString(),esiSelloAdhNaviera.getText().toString(),FieldOpcional.otrosSellosInstalaEsp,
                ediCompaniaTransporte.getText().toString(), ediNombreChofer.getText().toString(),ediCedula.getText().toString(),
                ediCedula.getText().toString(),ediPLaca.getText().toString(),ediMarcaCabezal.getText().toString(),
                ediColorCabezal.getText().toString(),ediCondicionBalanza.getText().toString(),ediTipodeCaja.getText().toString()
                ,switchHaybalanza.isChecked(),switchHayEnsunchado.isChecked(),spinnertipodePlastico.getSelectedItem().toString(),
                switchBalanzaRep.isChecked(),spinnerubicacionBalanza.getSelectedItem().toString(),ediTipoBalanza.getText().toString(),FieldOpcional.tipoDeBalanzaRepesoOpcnal);

        Variables.CurrenReportPart2.setKeyFirebase( Variables.CurrenReportPart2.getKeyFirebase()); //agregamos el mismo key qe tenia este objeto



        Variables.CurrenReportPart3= new SetInformDatsHacienda(spFuenteAgua.getSelectedItem().toString(),swAguaCorrida.isChecked(),switchLavdoRacimos.isChecked(),

                spFumigaCorL1.getSelectedItem().toString(),
                spTipoBoquilla.getSelectedItem().toString()
                // ediFumigacionClin1.getText().toString()

                //ediTipoBoquilla.getText().toString()

                ,ediCajasProcDesp.getText().toString(), ediRacimosCosech.getText().toString(),ediRacimosRecha.getText().toString(),ediRacimProces.getText().toString()
                ,UNIQUE_ID_iNFORME,ediExtCalid.getText().toString(),ediExtCalidCi.getText().toString());



        updateDatosEvaludoresOFinforme3(Variables.CurrenReportPart3);

        Variables.CurrenReportPart3.setKeyFirebase( Variables.CurrenReportPart3.getKeyFirebase()); //agregamos el mismo key qe tenia este objeto


        updateCaledarioEnfunde(Variables.CurrenReportPart3);


    }



private void createObjcInformeAndUpload() {

    //aplicamos la logica PARA CREAR UN NUEVO INFORME
//SI LA DATA ES OPCIONAL EN EL FIELD LE AGREGAMOS UN "";en editex comprobacion le agragmos para que el texto no sea nulo
    SetInformEmbarque1 informe = new SetInformEmbarque1(ediExportadoraProcesada.getText().toString(),ediExportadoraSolicitante.getText().toString(),
            ediMarca.getText().toString(),

            UNIQUE_ID_iNFORME, ediCodigo.getText().toString(),
            Integer.parseInt(ediNhojaEvaluacion.getText().toString()), ediZona.getText().toString()
            , ediProductor.getText().toString(), ediCodigo.getText().toString()
            , ediPemarque.getText().toString(), ediNguiaRemision.getText().toString(), ediHacienda.getText().toString()
            , edi_nguia_transporte.getText().toString(), ediNtargetaEmbarque.getText().toString(),
            ediInscirpMagap.getText().toString(), ediHoraInicio.getText().toString(), ediHoraTermino.getText().toString()
            , ediSemana.getText().toString(), ediEmpacadora.getText().toString(), ediContenedor.getText().toString(),
            FieldOpcional.observacionOpcional, ediHoraLLegadaContenedor.getText().toString(), ediHoraSalidaContenedor.getText().toString()
            , ediDestino.getText().toString(), ediNViaje.getText().toString(), ediNumContenedor.getText().toString(), ediVapor.getText().toString(),
            ediTipoContenedor.getText().toString(), ediTare.getText().toString(), ediBooking.getText().toString(), ediMaxGross.getText().toString(),
            ediNumSerieFunda.getText().toString(), stikVentolerExterna.getText().toString(),
            ediCableRastreoLlegada.getText().toString(), ediSelloPlasticoNaviera.getText().toString(), FieldOpcional.otrosSellosLLegaEspecif);
    informe.setKeyFirebase(Variables.CurrenReportPart1.getKeyFirebase()); //agregamos el mismo key qe tenia este objeto

    informe.setAtachControCalidadInfrms(CustomAdapter.idsFormsVinucladosControlCalidadString);

    informe.setAtachControCuadroMuestreo(CustomAdapter.idsFormsVinucladosCudorMuestreoString); //LE BORRAMOS MASS

    Log.i("HOMERAS", "el string atch CUADRO MUESTREO ESes " + CustomAdapter.idsFormsVinucladosCudorMuestreoString);

    if (millisDateSelect > 0) {

        ////CONVERTIMOS A SIMPLE DATE FORMAT
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        String fechaString = formatter.format(Variables.CurrenReportPart1.getFechaCreacionInf());
        informe.setSimpleDataFormat(fechaString);
        informe.setFechaCreacionInf(millisDateSelect);


    }


    SetInformEmbarque2 informe2 = new SetInformEmbarque2(UNIQUE_ID_iNFORME, ediTermofrafo1.getText().toString(), ediTermofrafo2.getText().toString()
            , ediHoraEncendido1.getText().toString(), ediHoraEncendido2.getText().toString(),
            ediUbicacion1.getText().toString(), ediUbicacion2.getText().toString(), ediRuma1.getText().toString(), ediRuma2.getText().toString()
            , ediCandadoqsercon.getText().toString(), ediSelloNaviera.getText().toString(), ediCableNaviera.getText().toString(),
            ediSelloPlasticoNaviera.getText().toString(), ediCandadoBotella.getText().toString(), ediCableExportadora.getText().toString(),
            ediSelloAdesivoexpor.getText().toString(), esiSelloAdhNaviera.getText().toString(), FieldOpcional.otrosSellosInstalaEsp,
            ediCompaniaTransporte.getText().toString(), ediNombreChofer.getText().toString(), ediCedula.getText().toString(),
            ediCedula.getText().toString(), ediPLaca.getText().toString(), ediMarcaCabezal.getText().toString(),
            ediColorCabezal.getText().toString(), ediCondicionBalanza.getText().toString(), ediTipodeCaja.getText().toString()
            , switchHaybalanza.isChecked(), switchHayEnsunchado.isChecked(), spinnertipodePlastico.getSelectedItem().toString(),
            switchBalanzaRep.isChecked(), spinnerubicacionBalanza.getSelectedItem().toString(), ediTipoBalanza.getText().toString(), FieldOpcional.tipoDeBalanzaRepesoOpcnal);

    informe2.setKeyFirebase(Variables.CurrenReportPart2.getKeyFirebase()); //agregamos el mismo key qe tenia este objeto


    SetInformDatsHacienda informe3 = new SetInformDatsHacienda(spFuenteAgua.getSelectedItem().toString(), swAguaCorrida.isChecked(), switchLavdoRacimos.isChecked(),

            spFumigaCorL1.getSelectedItem().toString(),
            spTipoBoquilla.getSelectedItem().toString()
            // ediFumigacionClin1.getText().toString()

            //ediTipoBoquilla.getText().toString()

            , ediCajasProcDesp.getText().toString(), ediRacimosCosech.getText().toString(), ediRacimosRecha.getText().toString(), ediRacimProces.getText().toString()
            , UNIQUE_ID_iNFORME, ediExtCalid.getText().toString(), ediExtCalidCi.getText().toString());


             updateDatosEvaludoresOFinforme3(informe3);



    informe3.setKeyFirebase( Variables.CurrenReportPart3.getKeyFirebase()); //agregamos el mismo key qe tenia este objeto


    RealtimeDB.initDatabasesReferenceImagesData(); //inicilizamos la base de datos

    //agr5egamos la data finalemente


    updateCaledarioEnfunde(informe3);

    //checkQueexistminim();

    RealtimeDB.actualizaInformePart1(informe);
    RealtimeDB.actualizaInformePart2(informe2);

    RealtimeDB.actualizaInformePart3(informe3); //vamos a subir este informe...

    // RealtimeDB.actualizaInformePart3(informe3);


    addProdcutsPostCosechaAndUpload(); //agregamos y subimos los productos postcosecha..


}

    private void updateDatosEvaludoresOFinforme3(SetInformDatsHacienda informe3) {

        if(!ediExtRodillo.getText().toString().trim().isEmpty()){

            informe3.setExtensionistDeRodillo(ediExtRodillo.getText().toString());
            informe3.setCI_extensionistDeRodillo(ediExtRodilloCi.getText().toString());

        }


        if(!ediExtGancho.getText().toString().trim().isEmpty()){
            informe3.setExtensionistEnGancho(ediExtGancho.getText().toString());
            informe3.setCI_extensionistEnGancho(ediExtGanchoCi.getText().toString());

        }


    }




    private void eventoBtnclicklistenerDelete(RecyclerViewAdapter adapter) {

        adapter.setOnItemClickListener(new RecyclerViewAdapter.ClickListener() {


            @Override
            public void onItemClick(int position, View v) {  //este para eminar
                Variables.typeoFdeleteImg=  ImagenReport.hashMapImagesData.get(v.getTag().toString()).getTipoImagenCategory();
                Log.i("mispiggi","el size antes de eliminar es "+ ImagenReport.hashMapImagesData.size());

                listImagesToDelete.add(v.getTag().toString());//agregamos ea imagen para borrarla


                try {

                    ImagenReport.hashMapImagesData.remove(v.getTag().toString());


                } catch (Exception e) {
                    e.printStackTrace();
                }

                Log.i("mispiggi","el size despues de eliminar es "+ ImagenReport.hashMapImagesData.size());

                showImagesPicShotOrSelectUpdateView(true);


            }

        });
    }



    void uploadImagesInStorageAndInfoPICS() {
   //una lista de Uris

        if(ImagenReport.hashMapImagesData.size() ==0 ){

             return;
        }

        //si introdujo texto en el recicler actualizar los objetos


          //boorara desee aqui
        if(  !Variables.hashMapImagesStart.keySet().equals(ImagenReport.hashMapImagesData.keySet())){ //si no son iguales

            Log.i("elfile","alguno o toos son diferentes images llamaos metodo filtra");

            StorageData.uploadImage(ActivityContenedoresPrev.this, Utils.creaHahmapNoDuplicado());



        }else{
            Log.i("debugasd","el size de hashMapImagesStart es  "+ Variables.hashMapImagesStart.size()+" y el size de hashMapImagesData es" +ImagenReport.hashMapImagesData.size());


           Log.i("elfile","son iguales las imagenes");

        }

            if(Utils.objsIdsDecripcionImgsMOreDescripc.size()>0) {

                RealtimeDB.initDatabasesReferenceImagesData();
                RealtimeDB.actualizaDescripcionIms(Utils.objsIdsDecripcionImgsMOreDescripc);

            }
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
         //   Uri uri = Uri.parse("package:" + BuildConfig.APPLICATION_ID);
         //  startActivity(new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, uri));
        }







      //  startActivity(new Intent(FormularioActivity.this,PdfPreviewActivity.class));

        //generamos un pdf con la data que tenemos()

        /*

        PdfMaker.generatePdfReport1(FormularioActivity.this,ediCodigo.getText().toString(),Integer.parseInt(ediNhojaEvaluacion.getText().toString()),
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
            int result = ContextCompat.checkSelfPermission(ActivityContenedoresPrev.this, READ_EXTERNAL_STORAGE);
            int result1 = ContextCompat.checkSelfPermission(ActivityContenedoresPrev.this, WRITE_EXTERNAL_STORAGE);
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

        if(Objects.requireNonNull(ediSemana.getText()).toString().isEmpty()){ //chekamos que no este vacia
            ediSemana.requestFocus();
            ediSemana.setError("Este espacio es obligatorio");
            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;
            //obtiene el padre del padre

        }

         if (ediFecha.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediFecha.requestFocus();
            ediFecha.setError("Debe selecionar una fecha");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }







         if(ediProductor.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediProductor.requestFocus();
            ediProductor.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }




         if(ediHacienda.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediHacienda.requestFocus();
            ediHacienda.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }





/*
         if(ediCodigo.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCodigo.requestFocus();
            ediCodigo.setError("Este espacio es obligatorio");
            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }
*/





        if(ediInscirpMagap.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediInscirpMagap.requestFocus();
            ediInscirpMagap.setError("Este espacio es obligatorio");
            ediInscirpMagap.setText("_");
            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediPemarque.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediPemarque.requestFocus();
            ediPemarque.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediZona.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediZona.requestFocus();
            ediZona.setError("Debe selecionar una zona");
            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediHoraInicio.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediHoraInicio.requestFocus();
            ediHoraInicio.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediHoraTermino.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediHoraTermino.requestFocus();
            ediHoraTermino.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



         if(ediNguiaRemision.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediNguiaRemision.requestFocus();
            ediNguiaRemision.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

         if(edi_nguia_transporte.getText().toString().isEmpty()){ //chekamos que no este vacia
            edi_nguia_transporte.requestFocus();
            edi_nguia_transporte.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


         if(ediNtargetaEmbarque.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediNtargetaEmbarque.requestFocus();
            ediNtargetaEmbarque.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }




           if(ediNhojaEvaluacion.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediNhojaEvaluacion.requestFocus();
            ediNhojaEvaluacion.setError("Este espacio es obligatorio");
            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;
            //obtiene el padre del padre

        }



        if(ediEmpacadora.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediEmpacadora.requestFocus();
            ediEmpacadora.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


          if(!ediObservacion.getText().toString().isEmpty()){ //si esta lleno

              FieldOpcional.observacionOpcional=ediObservacion.getText().toString();

        }


        if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_LLEGADA)){
            ediFotosLlegada.requestFocus();

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            ediFotosLlegada.setError("Agregue al menos "+Variables.MINIMO_FOTOS_ALL_CATEGORY+" foto");
            return false;
        }else{

            ediFotosLlegada.clearFocus();
            ediFotosLlegada.setError(null);

        }

        //


        return true;
    }



    private boolean  checkcantidadPostcosechaIsLleno(){
        LinearLayout layoutContainerSeccion2=findViewById(R.id.layoutContainerSeccion2);
        Log.i("camisad" ,"se llamo este ");

        if(ediPPC01.getText().toString().isEmpty() && ediPPC02.getText().toString().isEmpty()&& ediPPC03.getText().toString().isEmpty()
                && ediPPC04.getText().toString().isEmpty()&& ediPPC05.getText().toString().isEmpty()&& ediPPC06.getText().toString().isEmpty()
                && ediPPC07.getText().toString().isEmpty()&& ediPPC08.getText().toString().isEmpty() && ediPPC09.getText().toString().isEmpty()
                && ediPPC010.getText().toString().isEmpty() && ediPPC011.getText().toString().isEmpty() && ediPPC012.getText().toString().isEmpty()
                && ediPPC013.getText().toString().isEmpty() && ediPPC014.getText().toString().isEmpty() && ediPPC015.getText().toString().isEmpty()
                && ediPPC016.getText().toString().isEmpty()
        ){ //chekamos que no este vacia
            ediPPC01.requestFocus();
            ediPPC01.setError("Inserte al menos 1 producto");
            layoutContainerSeccion2.setVisibility(LinearLayout.VISIBLE);

            Log.i("camisad" ,"se eejcuto este");

            return false;

        }



        if(! ediPPC015.getText().toString().isEmpty() && ediPPC016.getText().toString().isEmpty() ){ //chekamos que no este vacia
            ediPPC016.requestFocus();
            ediPPC016.setError("Inserte cantidad");

            layoutContainerSeccion2.setVisibility(LinearLayout.VISIBLE);
            return false ;

        }


        if(! ediPPC016.getText().toString().isEmpty() && ediPPC015.getText().toString().isEmpty() ){ //chekamos que no este vacia
            ediPPC015.requestFocus();
            ediPPC015.setError("inserte nombre producto");

            layoutContainerSeccion2.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        ///y chekeamos al menos una imagen del pridcutp
        if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_PROD_POSTCOSECHA)){
            ediFotosPposcosecha.requestFocus();

            layoutContainerSeccion2.setVisibility(LinearLayout.VISIBLE);
            ediFotosPposcosecha.setError("Agregue al menos "+Variables.MINIMO_FOTOS_ALL_CATEGORY+" foto");
            return false;

        }else{

            ediFotosPposcosecha.clearFocus();
            ediFotosPposcosecha.setError(null);

        }


return  true;

    }



    private boolean checkDatosContenedorIsLleno(){

        LinearLayout layoutContainerSeccion3=findViewById(R.id.layoutContainerSeccion3);


        ///CHEKEAMOS DATA seccion CONTENEDOR

        if(ediDestino.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediDestino.requestFocus();
            ediDestino.setError("Este espacio es obligatorio");

            layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediNViaje.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediNViaje.requestFocus();
            ediNViaje.setError("Este espacio es obligatorio");

            layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediVapor.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediVapor.requestFocus();
            ediVapor.setError("Este espacio es obligatorio");

            layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(ediTipoContenedor.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediTipoContenedor.requestFocus();
            ediTipoContenedor.setError("Este espacio es obligatorio");

            layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediHoraLLegadaContenedor.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediHoraLLegadaContenedor.requestFocus();
            ediHoraLLegadaContenedor.setError("Este espacio es obligatorio");

            layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediHoraSalidaContenedor.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediHoraSalidaContenedor.requestFocus();
            ediHoraSalidaContenedor.setError("Este espacio es obligatorio");

            layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        //chekamos que al menos exista una imagen...


        if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_CONTENEDOR)){
            ediFotoContenedor.requestFocus();

            layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
            ediFotoContenedor.setError("Agregue al menos "+Variables.MINIMO_FOTOS_ALL_CATEGORY+" foto");
            return false;
        }else{

            ediFotoContenedor.clearFocus();
            ediFotoContenedor.setError(null);

        }




return  true;

    }




    private boolean checkDataSellosLlegadaIsLleno(){
        LinearLayout layoutContainerSeccion4=findViewById(R.id.layoutContainerSeccion4);


        if(ediTare.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediTare.requestFocus();
            ediTare.setError("Este espacio es obligatorio");

            layoutContainerSeccion4.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        ///CHEKEAMOS DATA seccion CONTENEDOR

        if(ediBooking.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediBooking.requestFocus();
            ediBooking.setError("Este espacio es obligatorio");

            layoutContainerSeccion4.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(ediMaxGross.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediMaxGross.requestFocus();
            ediMaxGross.setError("Este espacio es obligatorio");

            layoutContainerSeccion4.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediNumSerieFunda.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediNumSerieFunda.requestFocus();
            ediNumSerieFunda.setError("Este espacio es obligatorio");

            layoutContainerSeccion4.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(stikVentolerExterna.getText().toString().isEmpty()){ //chekamos que no este vacia
            stikVentolerExterna.requestFocus();
            stikVentolerExterna.setError("Este espacio es obligatorio");

            layoutContainerSeccion4.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediCableRastreoLlegada.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCableRastreoLlegada.requestFocus();
            ediCableRastreoLlegada.setError("Este espacio es obligatorio");

            layoutContainerSeccion4.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediSelloPlasticoNaviera.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediSelloPlasticoNaviera.requestFocus();
            ediSelloPlasticoNaviera.setError("Este espacio es obligatorio");

            layoutContainerSeccion4.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(!ediOtroSellosLlegada.getText().toString().isEmpty()){ //este es opcional... si esta vacio

            FieldOpcional.otrosSellosLLegaEspecif =ediOtroSellosLlegada.getText().toString();
        }





        if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_SELLO_LLEGADA)){
            ediFotosSellosLLegada.requestFocus();

            layoutContainerSeccion4.setVisibility(LinearLayout.VISIBLE);
            ediFotosSellosLLegada.setError("Agregue al menos "+Variables.MINIMO_FOTOS_ALL_CATEGORY+" foto");
            return false;
        }else{

            ediFotosSellosLLegada.clearFocus();
            ediFotosSellosLLegada.setError(null);

        }




        return true;


    }


    private boolean checkSellosInstaladosIsLleno(){

        LinearLayout layoutContainerSeccion5=findViewById(R.id.layoutContainerSeccion5);


        if(ediTermofrafo1.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediTermofrafo1.requestFocus();
            ediTermofrafo1.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }
        if(ediHoraEncendido1.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediHoraEncendido1.requestFocus();
            ediHoraEncendido1.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediUbicacion1.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediUbicacion1.requestFocus();
            ediUbicacion1.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }
        if(ediRuma1.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediRuma1.requestFocus();
            ediRuma1.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediTermofrafo2.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediTermofrafo2.requestFocus();
            ediTermofrafo2.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediHoraEncendido2.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediHoraEncendido2.requestFocus();
            ediHoraEncendido2.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediUbicacion2.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediUbicacion2.requestFocus();
            ediUbicacion2.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediRuma2.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediRuma2.requestFocus();
            ediRuma2.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediCandadoqsercon.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCandadoqsercon.requestFocus();
            ediCandadoqsercon.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediSelloNaviera.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediSelloNaviera.requestFocus();
            ediSelloNaviera.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }
        if(ediCableNaviera.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCableNaviera.requestFocus();
            ediCableNaviera.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediSelloPlastico.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediSelloPlastico.requestFocus();
            ediSelloPlastico.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediCandadoBotella.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCandadoBotella.requestFocus();
            ediCandadoBotella.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }
        if(ediCableExportadora.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCableExportadora.requestFocus();
            ediCableExportadora.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }
        if(ediSelloAdesivoexpor.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediSelloAdesivoexpor.requestFocus();
            ediSelloAdesivoexpor.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(esiSelloAdhNaviera.getText().toString().isEmpty()){ //chekamos que no este vacia
            esiSelloAdhNaviera.requestFocus();
            esiSelloAdhNaviera.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(! ediOtherSellos.getText().toString().isEmpty()){ //si esta lleno
        FieldOpcional.otrosSellosInstalaEsp =ediOtherSellos.getText().toString();


        }




return true;
    }



    private boolean checkDatosTransportistaIsLleno(){

        LinearLayout layoutContainerSeccion6=findViewById(R.id.layoutContainerSeccion6);
        ///CHEKEAMOS DATA seccion CONTENEDOR

        if(ediCompaniaTransporte.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCompaniaTransporte.requestFocus();
            ediCompaniaTransporte.setError("Este espacio es obligatorio");

            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediNombreChofer.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediNombreChofer.requestFocus();
            ediNombreChofer.setError("Este espacio es obligatorio");

            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(ediCedula.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCedula.requestFocus();
            ediCedula.setError("Este espacio es obligatorio");

            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediCelular.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCelular.requestFocus();
            ediCelular.setError("Este espacio es obligatorio");

            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediPLaca.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediPLaca.requestFocus();
            ediPLaca.setError("Este espacio es obligatorio");
            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(ediMarcaCabezal.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediMarcaCabezal.requestFocus();
            ediMarcaCabezal.setError("Este espacio es obligatorio");
            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediColorCabezal.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediColorCabezal.requestFocus();
            ediColorCabezal.setError("Este espacio es obligatorio");

            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
            return false;

        }




        else if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_TRANSPORTISTA)){
            ediFotosLlegadaTransport.requestFocus();

            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
            ediFotosLlegadaTransport.setError("Agregue al menos "+Variables.MINIMO_FOTOS_ALL_CATEGORY+" foto");
            return false;
        }else{

            ediFotosLlegadaTransport.clearFocus();
            ediFotosLlegadaTransport.setError(null);

        }



      return true;

    }





    private boolean checkDatosProcesoIsLleno(){
        LinearLayout layoutContainerSeccion7=findViewById(R.id.layoutContainerSeccion7);
        ///CHEKEAMOS DATA seccion CONTENEDOR

        if(ediCondicionBalanza.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCondicionBalanza.requestFocus();
            ediCondicionBalanza.setError("Este espacio es obligatorio");

            layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediTipodeCaja.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediTipodeCaja.requestFocus();
            ediTipodeCaja.setError("Este espacio es obligatorio");

            layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(ediCondicionBalanza.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCondicionBalanza.requestFocus();
            ediCondicionBalanza.setError("Este espacio es obligatorio");

            layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediTipoPlastico.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediTipoPlastico.requestFocus();
            ediTipoPlastico.setError("Este espacio es obligatorio");

            layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(ediTipoBalanza.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediTipoBalanza.requestFocus();
            ediTipoBalanza.setError("Este espacio es obligatorio");

            layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(editipbalanzaRepeso.getText().toString().isEmpty()){ //si no esta vacia

          // FieldOpcional.tipoDeBalanzaRepesoOpcnal AQUI GAURDAMOS EL VALOR DEL EDITEXT

            editipbalanzaRepeso.requestFocus();
            editipbalanzaRepeso.setError("Este espacio es obligatorio");

            layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        else {

            FieldOpcional.tipoDeBalanzaRepesoOpcnal =editipbalanzaRepeso.getText().toString();

        }


        if(ediUbicacionBalanza.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediUbicacionBalanza.requestFocus();
            ediUbicacionBalanza.setError("Este espacio es obligatorio");

            layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


return true;

    }




    private void  updaTeProductsPostCosecha(){

        products=new ProductPostCosecha(UNIQUE_ID_iNFORME);
        //creamos un array de editext
        products.keyFirebase=productxGlobal.keyFirebase;

        EditText [] editextArray = {ediPPC01,ediPPC02,ediPPC03,ediPPC04,ediPPC05,ediPPC06,ediPPC07,
                ediPPC08,ediPPC09, ediPPC010,ediPPC011,ediPPC012,ediPPC013,ediPPC014,ediPPC015,ediPPC016} ;


        for (int indice =0; indice<editextArray.length; indice++) {
            EditText currentEditext=editextArray[indice];
            if (!currentEditext.getText().toString().isEmpty()){ //si no esta vacioo
                if (!currentEditext.getText().toString().trim().isEmpty())  //si no es un espacio vacio
                {

                    switch (currentEditext.getId()){

                        case R.id.ediPPC01:
                            products.alumbre=currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC02:
                            products.bc100=currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC03:
                            products.sb100=currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC04:
                            products.eclipse=currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC05:
                            products.acido_citrico=currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC06:
                            products.biottol=currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC07:
                            products.bromorux=currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC08:
                            products.ryzuc=currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC09:
                            products.mertec=currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC010:
                            products.sastifar=currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC011:
                            products.xtrata=currentEditext.getText().toString();
                            break;


                        case R.id.ediPPC012:
                            products.nlarge=currentEditext.getText().toString();
                            break;


                        case R.id.ediPPC013:
                            products.gib_bex=currentEditext.getText().toString();
                            break;



                        case R.id.ediPPC014:
                            products.cloro=currentEditext.getText().toString();
                            break;


                        case R.id.ediPPC015:
                            products.otro_especifique=currentEditext.getText().toString();
                            break;


                        case R.id.ediPPC016:
                            products.cantidadOtro=currentEditext.getText().toString();
                            break;


                    }

                }


            }

            //si el editext tiene data lo corregimos usando la propiedad hint


        }



    }




    private void  addProdcutsPostCosechaAndUpload(){

        ProductPostCosecha producto=new ProductPostCosecha(UNIQUE_ID_iNFORME);
        //creamos un array de editext
        producto.keyFirebase=productxGlobal.keyFirebase;

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



        RealtimeDB.UpdateProductosPostCosecha(producto);


        pdialogff.dismiss();

        Toast.makeText(this, "Informe Actualizado", Toast.LENGTH_SHORT).show();


        startActivity(new Intent(ActivityContenedoresPrev.this, ActivitySeeReports.class));

    }

    private ProductPostCosecha  onlYCreatrePrudcPostCosecha(){
        ProductPostCosecha producto=new ProductPostCosecha(UNIQUE_ID_iNFORME);
        //creamos un array de editext
        producto.keyFirebase=productxGlobal.keyFirebase;

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


       return producto;

    }

//upload data...

    private void diseableViewsByTipe(View view) {

        if (view instanceof TextInputEditText ) { //asi es un editex compobamos si esta lleno

            TextInputEditText editText = (TextInputEditText) view; //asi lo convertimos

            // editText.setFocusable(false);
             editText.setEnabled(false);
           // editText.setCursorVisible(false);
           // editText.setKeyListener(null);
            //  editText.setBackgroundColor(Color.TRANSPARENT);

        }

        else if(view instanceof Spinner){
            Spinner spinner = (Spinner) view; //asi lo convertimos
            spinner.setEnabled(false);
            spinner.setClickable(false);

        }



        else if(view instanceof Switch) {
            @SuppressLint("UseSwitchCompatOrMaterialCode") Switch swichtView = (Switch) view; //asi lo convertimos
            swichtView.setEnabled(false);
            swichtView.setClickable(false);

        }
        else if(view instanceof ImageView) {
            ImageView imagev = (ImageView) view; //asi lo convertimos
            imagev.setEnabled(false);
            imagev.setClickable(false);


        }
        else if(view instanceof Button) {
            Button btn = (Button) view; //asi lo convertimos
            btn.setEnabled(false);
            btn.setClickable(false);


        }


    }


    private void activateViewsByTypeView(View view) {

        // ediProductor.setCursorVisible(true);

        //ediProductor.setFocusable(true);
        ediProductor.setEnabled(true);

        if (view instanceof TextInputEditText) { //asi es un editex compobamos si esta lleno

           TextInputEditText editText = (TextInputEditText) view; //asi lo convertimos

            editText.setEnabled(true);


            //  editText.requestFocus();

           // editText.setFocusable(true);
          //  editText.setEnabled(true);
           // editText.setCursorVisible(true);
           // editText.setKeyListener(false);
            //  editText.setBackgroundColor(Color.TRANSPARENT);

        }

        else if(view instanceof Spinner){
            Spinner spinner = (Spinner) view; //asi lo convertimos
            spinner.setEnabled(true);
            spinner.setClickable(true);

        }



        else if(view instanceof Switch) {
            @SuppressLint("UseSwitchCompatOrMaterialCode") Switch swichtView = (Switch) view; //asi lo convertimos
            swichtView.setEnabled(true);
            swichtView.setClickable(true);

        }


        else if(view instanceof Button) {
            Button btn = (Button) view; //asi lo convertimos
            btn.setEnabled(true);
            btn.setClickable(true);


        }

        else if(view instanceof ImageView) {
            ImageView btn = (ImageView) view; //asi lo convertimos
            btn.setEnabled(true);
            btn.setClickable(true);


        }

    }


    private void activateModePreview( ) {

        Log.i("extra","se llamo activateModePreview descativamos ");
          Variables.isClickable=false;


        diseableViewsByTipe(    ediExportadoraProcesada);
        diseableViewsByTipe(    ediExportadoraSolicitante);
        diseableViewsByTipe(    ediMarca);


        diseableViewsByTipe(    ediSemana);
        diseableViewsByTipe(    ediFecha);
        diseableViewsByTipe(    ediProductor);
        diseableViewsByTipe(    ediHacienda);
        diseableViewsByTipe(    ediCodigo);
        diseableViewsByTipe(    ediInscirpMagap);
        diseableViewsByTipe(    ediPemarque);
        diseableViewsByTipe(    ediZona);
        diseableViewsByTipe(    ediHoraInicio);
        diseableViewsByTipe(    ediHoraTermino);
        diseableViewsByTipe(    ediHoraLLegadaContenedor);
        diseableViewsByTipe(    ediHoraSalidaContenedor);
        diseableViewsByTipe(    ediNguiaRemision);
        diseableViewsByTipe(    edi_nguia_transporte);
        diseableViewsByTipe(    ediNtargetaEmbarque);
        diseableViewsByTipe(    ediNhojaEvaluacion);
        diseableViewsByTipe(    ediObservacion);
        diseableViewsByTipe(    ediEmpacadora);
        diseableViewsByTipe(    ediFotosLlegada);
        diseableViewsByTipe(    ediContenedor);
        diseableViewsByTipe(    ediPPC01);
        diseableViewsByTipe(    ediPPC02);
        diseableViewsByTipe(    ediPPC03);
        diseableViewsByTipe(    ediPPC04);
        diseableViewsByTipe(    ediPPC05);
        diseableViewsByTipe(    ediPPC06);
        diseableViewsByTipe(    ediPPC07);
        diseableViewsByTipe(    ediPPC08);
        diseableViewsByTipe(    ediPPC09);
        diseableViewsByTipe(    ediPPC010);
        diseableViewsByTipe(    ediPPC011);
        diseableViewsByTipe(    ediPPC012);
        diseableViewsByTipe(    ediPPC013);
        diseableViewsByTipe(    ediPPC014);
        diseableViewsByTipe(    ediPPC015);
        diseableViewsByTipe(    ediPPC016);
        diseableViewsByTipe(    ediDestino);
        diseableViewsByTipe(    ediNViaje);
        diseableViewsByTipe(    ediTipoContenedor);
        diseableViewsByTipe(    ediVapor);
        diseableViewsByTipe(    ediFotoContenedor);
        diseableViewsByTipe(    ediFotosPposcosecha);
        diseableViewsByTipe(    ediCompaniaTransporte);
        diseableViewsByTipe(    ediNombreChofer);
        diseableViewsByTipe(    ediCedula);
        diseableViewsByTipe(    ediCelular);
        diseableViewsByTipe(    ediPLaca);
        diseableViewsByTipe(    ediMarcaCabezal);
        diseableViewsByTipe(    ediColorCabezal);
        diseableViewsByTipe(    ediFotosLlegadaTransport);
        diseableViewsByTipe(    ediTare);
        diseableViewsByTipe(    ediBooking);
        diseableViewsByTipe(    ediMaxGross);
        diseableViewsByTipe(    ediNumSerieFunda);
        diseableViewsByTipe(    stikVentolerExterna);
        diseableViewsByTipe(    ediCableRastreoLlegada);
        diseableViewsByTipe(    ediSelloPlasticoNaviera);
        diseableViewsByTipe(    ediOtroSellosLlegada);
        diseableViewsByTipe(    ediFotosSellosLLegada);
        diseableViewsByTipe(    ediCondicionBalanza);
        diseableViewsByTipe(    ediTipodeCaja);
        diseableViewsByTipe(    ediTipoPlastico);
        diseableViewsByTipe(    ediTipoBalanza);
        diseableViewsByTipe(    editipbalanzaRepeso);
        diseableViewsByTipe(    ediUbicacionBalanza);
        diseableViewsByTipe(    ediTermofrafo1);
        diseableViewsByTipe(    ediHoraEncendido1);
        diseableViewsByTipe(    ediUbicacion1);
        diseableViewsByTipe(    ediRuma1);
        diseableViewsByTipe(    ediTermofrafo2);
        diseableViewsByTipe(    ediHoraEncendido2);
        diseableViewsByTipe(    ediUbicacion2);
        diseableViewsByTipe(    ediRuma2);
        diseableViewsByTipe(    ediCandadoqsercon);
        diseableViewsByTipe(    ediSelloNaviera);
        diseableViewsByTipe(    ediCableNaviera);
        diseableViewsByTipe(    ediSelloPlastico);
        diseableViewsByTipe(    ediCandadoBotella);
        diseableViewsByTipe(    ediCableExportadora);
        diseableViewsByTipe(    ediSelloAdesivoexpor);
        diseableViewsByTipe(    esiSelloAdhNaviera);
        diseableViewsByTipe(    ediOtherSellos);
        diseableViewsByTipe(ediEnsunchado);
        diseableViewsByTipe(    ediBalanzaRepeso);

        diseableViewsByTipe(ediCajasProcDesp);
        diseableViewsByTipe(ediRacimosCosech);
        diseableViewsByTipe(ediRacimosRecha);
        diseableViewsByTipe(ediRacimProces);

        diseableViewsByTipe(ediExtCalid);
        diseableViewsByTipe(ediExtCalidCi);
        diseableViewsByTipe(ediExtRodillo);
        diseableViewsByTipe(ediExtRodilloCi);
        diseableViewsByTipe(ediExtGancho);
        diseableViewsByTipe(ediExtGanchoCi);




        //SPINNERS
        diseableViewsByTipe(  spinnerSelectZona);
        diseableViewsByTipe(  spinnerCondicionBalanza);
        diseableViewsByTipe( spinnertipoCaja);
        diseableViewsByTipe(  spinnertipodePlastico);
        diseableViewsByTipe( spinnertipodeBlanza) ;
        diseableViewsByTipe( spinnertipodeBlanzaRepeso) ;
        diseableViewsByTipe( spinnerubicacionBalanza) ;


        diseableViewsByTipe( spFuenteAgua) ;
        diseableViewsByTipe( swAguaCorrida) ;
        diseableViewsByTipe( switchLavdoRacimos) ;
        diseableViewsByTipe( spFumigaCorL1) ;
        diseableViewsByTipe( spTipoBoquilla) ;

        //SWITCHSÇ
        diseableViewsByTipe( switchContenedor);
        diseableViewsByTipe( switchHaybalanza);
        diseableViewsByTipe( switchHayEnsunchado);
        diseableViewsByTipe( switchBalanzaRep);


//iMAGEVIEWS
        diseableViewsByTipe( imBtakePic);
        diseableViewsByTipe( imBatach);
        diseableViewsByTipe( imbAtach_transportista);
        diseableViewsByTipe( imbTakePicTransportista);
        diseableViewsByTipe( imbAtachSellosLlegada);
        diseableViewsByTipe( imbTakePicSellosLLegada);
        diseableViewsByTipe( imbAtachDatosContenedor);
        diseableViewsByTipe( imbTakePicDatosContenedor);
        diseableViewsByTipe( imbAtachPrPostcosecha);
        diseableViewsByTipe( imbTakePicPrPostcosecha);


        //Buttons
        Button  btnCheck=findViewById(R.id.btnCheck);
        diseableViewsByTipe( btnCheck);


    }

    private void activateModeEdit() {
        Variables.isClickable=true;

        activateViewsByTypeView(    ediExportadoraProcesada);
        activateViewsByTypeView(    ediExportadoraSolicitante);
        activateViewsByTypeView(    ediMarca);


        activateViewsByTypeView(    ediSemana);
        activateViewsByTypeView(    ediFecha);
        activateViewsByTypeView(    ediProductor);
        activateViewsByTypeView(    ediHacienda);
       // activateViewsByTypeView(    ediCodigo);
        activateViewsByTypeView(    ediInscirpMagap);
        activateViewsByTypeView(    ediPemarque);
        activateViewsByTypeView(    ediZona);
        activateViewsByTypeView(    ediHoraInicio);
        activateViewsByTypeView(    ediHoraTermino);
        activateViewsByTypeView(    ediHoraLLegadaContenedor);
        activateViewsByTypeView(    ediHoraSalidaContenedor);
        activateViewsByTypeView(    ediNguiaRemision);
        activateViewsByTypeView(    edi_nguia_transporte);
        activateViewsByTypeView(    ediNtargetaEmbarque);
        activateViewsByTypeView(    ediNhojaEvaluacion);
        activateViewsByTypeView(    ediObservacion);
        activateViewsByTypeView(    ediEmpacadora);
        activateViewsByTypeView(    ediFotosLlegada);
        activateViewsByTypeView(    ediContenedor);
        activateViewsByTypeView(    ediPPC01);
        activateViewsByTypeView(    ediPPC02);
        activateViewsByTypeView(    ediPPC03);
        activateViewsByTypeView(    ediPPC04);
        activateViewsByTypeView(    ediPPC05);
        activateViewsByTypeView(    ediPPC06);
        activateViewsByTypeView(    ediPPC07);
        activateViewsByTypeView(    ediPPC08);
        activateViewsByTypeView(    ediPPC09);
        activateViewsByTypeView(    ediPPC010);
        activateViewsByTypeView(    ediPPC011);
        activateViewsByTypeView(    ediPPC012);
        activateViewsByTypeView(    ediPPC013);
        activateViewsByTypeView(    ediPPC014);
        activateViewsByTypeView(    ediPPC015);
        activateViewsByTypeView(    ediPPC016);
        activateViewsByTypeView(    ediDestino);
        activateViewsByTypeView(    ediNViaje);
        activateViewsByTypeView(    ediTipoContenedor);
        activateViewsByTypeView(    ediVapor);
        activateViewsByTypeView(    ediFotoContenedor);
        activateViewsByTypeView(    ediFotosPposcosecha);
        activateViewsByTypeView(    ediCompaniaTransporte);
        activateViewsByTypeView(    ediNombreChofer);
        activateViewsByTypeView(    ediCedula);
        activateViewsByTypeView(    ediCelular);
        activateViewsByTypeView(    ediPLaca);
        activateViewsByTypeView(    ediMarcaCabezal);
        activateViewsByTypeView(    ediColorCabezal);
        activateViewsByTypeView(    ediFotosLlegadaTransport);
        activateViewsByTypeView(    ediTare);
        activateViewsByTypeView(    ediBooking);
        activateViewsByTypeView(    ediMaxGross);
        activateViewsByTypeView(    ediNumSerieFunda);
        activateViewsByTypeView(    stikVentolerExterna);
        activateViewsByTypeView(    ediCableRastreoLlegada);
        activateViewsByTypeView(    ediSelloPlasticoNaviera);
        activateViewsByTypeView(    ediOtroSellosLlegada);
        activateViewsByTypeView(    ediFotosSellosLLegada);
        activateViewsByTypeView(    ediCondicionBalanza);
        activateViewsByTypeView(    ediTipodeCaja);
        activateViewsByTypeView(    ediTipoPlastico);
        activateViewsByTypeView(    ediTipoBalanza);
        activateViewsByTypeView(    editipbalanzaRepeso);
        activateViewsByTypeView(    ediUbicacionBalanza);
        activateViewsByTypeView(    ediTermofrafo1);
        activateViewsByTypeView(    ediHoraEncendido1);
        activateViewsByTypeView(    ediUbicacion1);
        activateViewsByTypeView(    ediRuma1);
        activateViewsByTypeView(    ediTermofrafo2);
        activateViewsByTypeView(    ediHoraEncendido2);
        activateViewsByTypeView(    ediUbicacion2);
        activateViewsByTypeView(    ediRuma2);
        activateViewsByTypeView(    ediCandadoqsercon);
        activateViewsByTypeView(    ediSelloNaviera);
        activateViewsByTypeView(    ediCableNaviera);
        activateViewsByTypeView(    ediSelloPlastico);
        activateViewsByTypeView(    ediCandadoBotella);
        activateViewsByTypeView(    ediCableExportadora);
        activateViewsByTypeView(    ediSelloAdesivoexpor);
        activateViewsByTypeView(    esiSelloAdhNaviera);
        activateViewsByTypeView(    ediOtherSellos);

        activateViewsByTypeView(ediCajasProcDesp);
        activateViewsByTypeView(ediRacimosCosech);
       // activateViewsByTypeView(ediRacimosRecha);
        activateViewsByTypeView(ediRacimProces);

        activateViewsByTypeView( spFuenteAgua) ;
        activateViewsByTypeView( swAguaCorrida) ;
        activateViewsByTypeView( switchLavdoRacimos) ;
        activateViewsByTypeView( spFumigaCorL1) ;
        activateViewsByTypeView( spTipoBoquilla) ;


        activateViewsByTypeView(ediExtCalid);
        activateViewsByTypeView(ediExtCalidCi);
        activateViewsByTypeView(ediExtRodillo);
        activateViewsByTypeView(ediExtRodilloCi);
        activateViewsByTypeView(ediExtGancho);
        activateViewsByTypeView(ediExtGanchoCi);


        //SPINNERS
        activateViewsByTypeView(  spinnerSelectZona);
        activateViewsByTypeView(  spinnerCondicionBalanza);
        activateViewsByTypeView( spinnertipoCaja);
        activateViewsByTypeView(  spinnertipodePlastico);
        activateViewsByTypeView( spinnertipodeBlanza) ;
        activateViewsByTypeView( spinnertipodeBlanzaRepeso) ;
        activateViewsByTypeView( spinnerubicacionBalanza) ;

        //SWITCHSÇ
        activateViewsByTypeView( switchContenedor);
        activateViewsByTypeView( switchHaybalanza);
        activateViewsByTypeView( switchHayEnsunchado);
        activateViewsByTypeView( switchBalanzaRep);


//iMAGEVIEWS
        activateViewsByTypeView( imBtakePic);
        activateViewsByTypeView( imBatach);
        activateViewsByTypeView( imbAtach_transportista);
        activateViewsByTypeView( imbTakePicTransportista);
        activateViewsByTypeView( imbAtachSellosLlegada);
        activateViewsByTypeView( imbTakePicSellosLLegada);
        activateViewsByTypeView( imbAtachDatosContenedor);
        activateViewsByTypeView( imbTakePicDatosContenedor);
        activateViewsByTypeView( imbAtachPrPostcosecha);
        activateViewsByTypeView( imbTakePicPrPostcosecha);


        //Buttons
        Button  btnCheck=findViewById(R.id.btnCheck);
        activateViewsByTypeView( btnCheck);

    }



    private  void setDtaInOthersViews(SetInformEmbarque1 info1Object, SetInformEmbarque2 info2Object
            , SetInformDatsHacienda info3Object) {



        Log.i("mizona","la zona obtenida en addDataENfiledsoTHERviews (data descargada ) es  "+info1Object.getZona());

        selectValue(spinnerSelectZona,info1Object.getZona()) ;

        selectValue(spinnerCondicionBalanza,info2Object.getCondicionBalanza()) ;
        selectValue(spinnertipoCaja,info2Object.getTipoCaja()) ;
        selectValue(spinnertipodePlastico,info2Object.getTipoPlastico()) ;
        selectValue(spinnertipodeBlanza,info2Object.getTipoDeBalanza()) ;
        selectValue(spinnertipodeBlanzaRepeso,info2Object.getTipoDeBalanzaRepeso()) ;
        selectValue(spinnerubicacionBalanza,info2Object.getUbicacionBalanza()) ;

        selectValue(spFuenteAgua,info3Object.getFuenteAgua()) ;
        selectValue(spFumigaCorL1,info3Object.getFumigacionClin1()) ;
        selectValue(spTipoBoquilla,info3Object.getEdiTipoBoquilla()) ;


        swAguaCorrida.setChecked(info3Object.isHayAguaCorrida());
        switchLavdoRacimos.setChecked(info3Object.isHayLavadoRacimos());



        if(info1Object.getContenedor().equals(" SI ")) {

            switchContenedor.setChecked(true);


        }else{
            switchContenedor.setChecked(false);


        }

         switchHaybalanza.setChecked(info2Object.isHayBalanza());
         switchHayEnsunchado.setChecked(info2Object.isHayExcelnsuchado());
         switchBalanzaRep.setChecked(info2Object.getHayBalanzaRepeso());

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


    private  void setDataInfields(SetInformEmbarque1 info1Object, SetInformEmbarque2 info2Object, SetInformDatsHacienda info3Object)  {
        //usamos los 2 objetos para establecer esta data..

        Log.i("jamisama","la semana es "+info1Object.getSemana());

        ediSemana.setText(info1Object.getSemana());
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        String fechaString = formatter.format(info1Object.getFechaCreacionInf());

        Log.i("holabaser","el fecha string ers "+fechaString);


         ediExportadoraProcesada.setText(info1Object.getExportadoraProcesada()); ;
         ediExportadoraSolicitante.setText(info1Object.getExportadoraSolicitante());
         ediMarca.setText(info1Object.getMarrca());



        ediFecha.setText(fechaString);

        ediProductor.setText(info1Object.getProductor());
                ediHacienda.setText(info1Object.getHacienda());
        ediCodigo.setText(info1Object.getUniqueIDinforme());
                ediInscirpMagap.setText(info1Object.getInscirpMagap());
        ediPemarque.setText(info1Object.getPemarque());
                ediZona.setText(info1Object.getZona());
        ediHoraInicio.setText(info1Object.getHoraInicio());
        ediNumContenedor.setText(info1Object.getNumcionContenedor());
                ediHoraTermino.setText(info1Object.getHoraTermino());
        ediHoraLLegadaContenedor.setText(info1Object.getHoraLlegadaContenedor());

                ediHoraSalidaContenedor.setText(info1Object.getHoraSalidadContenedor());
        ediNguiaRemision.setText(info1Object.getNguiaRemision());
                edi_nguia_transporte.setText(info1Object.get_nguia_transporte());
        ediNtargetaEmbarque.setText(info1Object.getNtargetaEmbarque());
                ediNhojaEvaluacion.setText(String.valueOf(info1Object.getEdiNhojaEvaluacion()));
        ediObservacion.setText(info1Object.getObservacion());
                ediEmpacadora.setText(info1Object.getEmpacadora());
                ediContenedor.setText(info1Object.getContenedor());

                /*faltan los prouctos postcosecha agregamos usando un for**/


        ediDestino.setText(info1Object.getDestinoContenedor());
                ediNViaje.setText(info1Object.getNumeroViajeContenedor());
        ediTipoContenedor.setText(info1Object.getTipoContenedor());
                ediVapor.setText(info1Object.getVapor());

        ediCompaniaTransporte.setText(info2Object.getCompaniaTranporte());
        ediNombreChofer.setText(info2Object.getNombreChofer());
        ediCedula.setText(String.valueOf(info2Object.getCedulaChofer()));
        ediCelular.setText(String.valueOf(info2Object.getCelularChofer()));
        ediPLaca.setText(info2Object.getPlacaChofer());
        ediMarcaCabezal.setText(info2Object.getMarcaCaebzalChofer());
        ediColorCabezal.setText(info2Object.getColorCAbezal());
        ediTare.setText(info1Object.getTare());
        ediBooking.setText(info1Object.getBooking());
        ediMaxGross.setText(info1Object.getMaxGross());
        ediNumSerieFunda.setText(info1Object.getnSerieFunda());
        stikVentolerExterna.setText(info1Object.getStickerVentoExtern());
        ediCableRastreoLlegada.setText(info1Object.getCableRastreoLlegada());
        ediSelloPlasticoNaviera.setText(info1Object.getSelloPlasticoNaviera());
        ediCondicionBalanza.setText(info2Object.getCondicionBalanza());
        ediTipodeCaja.setText(info2Object.getTipoCaja());
        ediTipoPlastico.setText(info2Object.getTipoPlastico());
        ediTipoBalanza.setText(info2Object.getTipoDeBalanza());
        editipbalanzaRepeso.setText(info2Object.getTipoDeBalanzaRepeso());
        ediUbicacionBalanza.setText(info2Object.getUbicacionBalanza());
        ediTermofrafo1.setText(info2Object.getTermografo1());
        ediHoraEncendido1.setText(info2Object.getTermografo1HoraEncendido());
        ediUbicacion1.setText(info2Object.getUbicacionPalletN1());
        ediRuma1.setText(info2Object.getRumaPalletN1());
        ediTermofrafo2.setText(info2Object.getRumaPalletN2());
        ediHoraEncendido2.setText(info2Object.getTermografo2HoraEncendido());
        ediUbicacion2.setText(info2Object.getUbicacionPalletN2());
        ediRuma2.setText(info2Object.getRumaPalletN2());

        Log.i("objec2searcg","el value es "+info2Object.getCandadoQsercom());


        ediCandadoqsercon.setText(info2Object.getCandadoQsercom());
        ediSelloNaviera.setText(info2Object.getSelloNaviera());
        ediCableNaviera.setText(info2Object.getCableNaviera());
        ediSelloPlastico.setText(info2Object.getSelloPlastico());
        ediCandadoBotella.setText(info2Object.getCandadoBotella());
        ediCableExportadora.setText(info2Object.getCableExportadora());
        ediSelloAdesivoexpor.setText(info2Object.getSelloAdhesivoExportadora());
        esiSelloAdhNaviera.setText(info2Object.getSelloNaviera());
        ediOtherSellos.setText(info2Object.getOtrosSellosEspecif());
        ediCajasProcDesp.setText(info3Object.getEdiCajasProcDesp());
        ediRacimosCosech.setText(info3Object.getEdiRacimosCosech());

       // ediRacimosRecha.setText(info3Object.getEdiRacimosRecha());

        ediRacimProces.setText(info3Object.getEdiRacimProces());
        ediExtCalid.setText(info3Object.getExtensionistCalid());
        ediExtCalidCi.setText(info3Object.getCI_extensionistCalid());
        ediExtRodillo.setText(info3Object.getExtensionistDeRodillo());
        ediExtRodilloCi.setText(info3Object.getCI_extensionistDeRodillo());
        ediExtGancho.setText(info3Object.getExtensionistEnGancho());
        ediExtGanchoCi .setText(info3Object.getCI_extensionistEnGancho());

        txtNumReportsVinclds.setText(String.valueOf(Utils.generaNumsInformsAtach(info1Object.getAtachControCalidadInfrms(),info1Object.getAtachControCuadroMuestreo())));


        CustomAdapter.mapWhitIDScontrolCaldVinclds = new HashMap<>();
        CustomAdapter.mapWhitIdsCuadroMuestreo = new HashMap<>();

        if(info1Object.getAtachControCalidadInfrms()!=null){
            String [] miarrayiNFORMESvinc = info1Object.getAtachControCalidadInfrms().split(",");
            Log.i("picacins","el size de aara es "+miarrayiNFORMESvinc.length);

            if(miarrayiNFORMESvinc.length >0) {
                for(String value : miarrayiNFORMESvinc){
                    Log.i("picacins","el key sera "+value);
                    CustomAdapter.mapWhitIDScontrolCaldVinclds.put(value,value);
                }
            }


            Log.i("picacins","el numero de reposrtes vincualdos es: "+ CustomAdapter.mapWhitIDScontrolCaldVinclds.size());


        }



           ///AGERGAMOS LOS INFORMES DE CUADRO MUESTREO A LA VARIABLE A LA AVRIABLE GLOBAL
        if(info1Object.getAtachControCuadroMuestreo()!=null){
            String [] miarrayiNFORMESvinc = info1Object.getAtachControCuadroMuestreo().split(",");
            Log.i("picacins","el size de aara es "+miarrayiNFORMESvinc.length);

            if(miarrayiNFORMESvinc.length >0) {
                for(String value : miarrayiNFORMESvinc){



                    if(!value.trim().isEmpty()){

                        CustomAdapter.mapWhitIdsCuadroMuestreo.put(value,value);


                    }
                    Log.i("picacins","el key sera "+value);
                }
            }


            //Log.i("picacins","el numero de reposrtes vincualdos es: "+ CustomAdapter.idOFfORMScontrolCaldVinclds.size());


        }



    }



private void checkModeVisualitY(){


    Bundle extras = getIntent().getExtras();
    if (extras != null) {
        isModEdicionFields = extras.getBoolean(Variables.KEYEXTRAPREVIEW);

        Log.i("extra","el modo es "+isModEdicionFields);
        //The key argument here must match that used in the other activity
    }


    if(isModEdicionFields){
        TextView txtModeAdviser=findViewById(R.id.txtModeAdviser);
        activateModeEdit();
        txtModeAdviser.setText("Modo Edicion ");

        Log.i("isclkiel","es clickeable es "+ Variables.isClickable);

    }else{
        fab.setImageResource(R.drawable.ic_baseline_edit_24aa);
        activateModePreview();
        Log.i("isclkiel","es clickeable es "+ Variables.isClickable);

    }


     Variables.modoRecicler=Variables.DOWLOAD_IMAGES;


    //aqui llamadmosasg
    dowloadSecondPART_Report(Variables.CurrenReportPart1.getUniqueIDinforme(),1);
    //inicializamos STORAGE..
    StorageData.initStorageReference();
    dowloadImagesDataReport(Variables.CurrenReportPart1.getUniqueIDinforme());

    dowLoadProducsPostC(Variables.CurrenReportPart1.getUniqueIDinforme());



}


//descargamos las imagenes path....



    //descargamos prodcutos postcosecha...

     void createlistsForReciclerviewsImages(ArrayList<ImagenReport> listImagenReports){



        ArrayList<ImagenReport>lisFiltrada;

        int []arrayTiposImagenes={Variables.FOTO_LLEGADA,Variables.FOTO_PROD_POSTCOSECHA,Variables.FOTO_TRANSPORTISTA,Variables.FOTO_SELLO_LLEGADA,Variables.FOTO_CONTENEDOR};

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



    }


    void addInfotomap(ArrayList<ImagenReport>listImagenReports){
       //


        ImagenReport.hashMapImagesData= new HashMap<>();

        //agregamos adata al mapusnado un bucle

        for(int indice2=0; indice2<listImagenReports.size(); indice2++){

          ImagenReport currentImareportObj=listImagenReports.get(indice2);

          ImagenReport.hashMapImagesData.put(currentImareportObj.getUniqueIdNamePic(),currentImareportObj);

        }



        if(!Variables.copiamosData) {
           // Variables.hashMapImagesStart =ImagenReport.hashMapImagesData;

            //CREAMOS UNA COPIA USANDO UN BUCLE

            Variables.hashMapImagesStart=new HashMap<>();


            for (Map.Entry<String, ImagenReport> entry : ImagenReport.hashMapImagesData.entrySet()) {
                String key = entry.getKey();
                ImagenReport value = entry.getValue();

                Variables.hashMapImagesStart.put(key,value);
                // ...
            }

            Variables.copiamosData =true;




        }


    }

     void addImagesInRecyclerviews(ArrayList<ImagenReport> listImagenReports){

            //agregamos data al map


          RecyclerView recyclerView= findViewById(R.id.recyclerView);

           //si la imagen es la imagen de fotos llegada INICLIZAMOS ASI
           if(currentTypeImage== Variables.FOTO_LLEGADA)  {
               recyclerView= findViewById(R.id.recyclerView);


           }
           else if (currentTypeImage==Variables.FOTO_PROD_POSTCOSECHA){
               recyclerView= findViewById(R.id.recyclerViewPostcosecha);
               // at last set adapter to recycler view.

           }

           else if (currentTypeImage==Variables.FOTO_TRANSPORTISTA){
               recyclerView = findViewById(R.id.recyclerVieDatsTransport);

           }

           else if (currentTypeImage==Variables.FOTO_CONTENEDOR){
               recyclerView = findViewById(R.id.recyclerViewDatosContenedor);
           }


           else if (currentTypeImage==Variables.FOTO_SELLO_LLEGADA){
               recyclerView = findViewById(R.id.recyclerViewSellosLlegada);

           }





           RecyclerViewAdapter adapter=new RecyclerViewAdapter(listImagenReports,this);
           GridLayoutManager layoutManager=new GridLayoutManager(this,2);


           // at last set adapter to recycler view.
           recyclerView.setLayoutManager(layoutManager);
           recyclerView.setAdapter(adapter);
           eventoBtnclicklistenerDelete(adapter);


    }





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

              //  Log.i("sliexsa","existe"+product.cantidadOtro);


                         if(productxGlobal!=null){

                             Variables.currenProductPostCosecha=productxGlobal;
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

     TextInputEditText [] editextArray = {  ediPPC01,ediPPC02,ediPPC03,ediPPC04,ediPPC05,ediPPC06,ediPPC07,
             ediPPC08,ediPPC09, ediPPC010,ediPPC011,ediPPC012,ediPPC013,ediPPC014,ediPPC015,ediPPC016} ;

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



    // Variables.modoRecicler=Variables.SELEC_AND_TAKE_iMAGES;

 }







    void dowloadImagesDataReport(String reportUNIQUEidtoSEARCH){ //DESCRAGAMOS EL SEGUNDO

        RealtimeDB.initDatabasesReferenceImagesData(); //borrar

        // DatabaseReference midatabase=rootDatabaseReference.child("Informes").child("listInformes");
        Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("ImagesData").orderByChild("idReportePerteence").equalTo(reportUNIQUEidtoSEARCH);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

               // ArrayList<ImagenReport>listImagenData=new ArrayList<>();
                Variables.listImagenDataGlobalCurrentReport= new ArrayList<>();


                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    ImagenReport imagenReport=ds.getValue(ImagenReport.class);
                  //  listImagenData.add(imagenReport);

                    Variables.listImagenDataGlobalCurrentReport.add(imagenReport);

                    Log.i("cajhsd","key uninque id es "+imagenReport.getUniqueIdNamePic());
                    Log.i("cajhsd","el url es  "+imagenReport.getUrlStoragePic());



                    //  Log.i("ilaimgensss","se llamo a: la imegn uniqyue id "+keylocation);


                   // imagenReport.setHorientacionImage("vertical");
                  ///  imagenReport.setEstaENPdf(false);
                   /// imagenReport.setUrlStoragePic("");



                  //  Map<String, Object> mapValues = imagenReport.toMap();



                    /*
                    //SUBE MAPA
                    RealtimeDB.mibasedataPathImages.child(keylocation).setValue(mapValues).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(context, "HECHO", Toast.LENGTH_SHORT).show();

                            }else  {

                            }
                        }
                    });
*/


                    //guardamos ese objeto..


                }




                createlistsForReciclerviewsImages(Variables.listImagenDataGlobalCurrentReport);

              //  dowloadAllImages2AddCallRecicler(Variables.listImagenData);

                Utils.objsIdsDecripcionImgsMOreDescripc =new ArrayList<>();




                 //solo un archivo
                 //s


                if(currentIDcUDORmUESTREO!=null){

                    DowloadUniqeuRechazadosObjectCUADROMuestreoAndSetNumRechzados(CustomAdapter.idsFormsVinucladosCudorMuestreoString);

                }else{ //sino hay ningun cuadro muestreo;

                     btnGENERARpdf.setEnabled(true);

                }




              //  btnGENERARpdf.setEnabled(true);

                Log.i("mispiggi","se llamo a: addInfotomap");

                addInfotomap(Variables.listImagenDataGlobalCurrentReport);



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



    //nevcesitamos un metodo para eliminar un nodo en imagenes y borrarlo de storage..
    //por ahora solo con borrarlo de rtdabase
    private void geTidAndDelete( String idUniqueToDelete){ //busca el que tenga esa propieda y obtiene el id node child

               Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("ImagesData").orderByChild("uniqueIdNamePic").equalTo(idUniqueToDelete);

        DatabaseReference usersdRef= RealtimeDB.rootDatabaseReference.child("Informes").child("ImagesData");
      //  Query query = usersdRef.orderByChild("uniqueIdNamePic").equalTo(Variables.currentCuponObjectGlob.getUniqueIdCupòn());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot nodeShot = dataSnapshot.getChildren().iterator().next();
                String key = nodeShot.getKey();
                //   private void editaValue(String keyAtoUpdate,String titulo, String descripcion, String direccion, String ubicacionCordenaGoogleMap, String picNameofStorage, double cuponValor, String categoria,boolean switchActivate, boolean switchDestacado){


                usersdRef.child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //Toast.makeText(OfertsAdminActivity.this, "Se elimino correctamente", Toast.LENGTH_SHORT).show();

                        }



                    }
                });



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


   void addDataHashMapTypeImagesToPdf(ArrayList<ImagenReport>miLisAllImages){


        for(ImagenReport imageb:miLisAllImages ){




        }


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

                    //   HelperImage.imAGESpdfSetGlobal.add(imgsObect);

                       HelperImage.ImagesToPdfMap.put(uniqueId,imgsObect);


                        Log.i("hamiso","el size de la lista de Variables.listImagenData es "+Variables.listImagenData.size());
                       // Log.i("hamiso","el size del map es "+HelperImage.ImagesToPdfMap.size());
                        ///llamamos a este otro metodo .......
                             contadorIterador++;
                        Log.i("hamiso","el contador iteradopr "+ contadorIterador);




                        if(contadorIterador == miLisAllImages.size() ) {
                            createlistsForReciclerviewsImages(Variables.listImagenData);
                                Log.i("hamiso","se llamokkk");
                                Utils.objsIdsDecripcionImgsMOreDescripc =new ArrayList<>();
                                btnGENERARpdf.setEnabled(true);
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
    private boolean checkDatosHaciendaIsLleno(){
        LinearLayout layoutContainerSeccion8=findViewById(R.id.layoutContainerSeccion8);




        if(spFuenteAgua.getSelectedItem().toString().equals("Ninguna")){ //chekamos que no este vacia
            ediFuenteAgua.requestFocus();
            ediFuenteAgua.setError("Fuente de agua requerido");

            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



/*

        if(ediAguaCorrida.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediAguaCorrida.requestFocus();
            ediAguaCorrida.setError("Este espacio es obligatorio");

            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(ediLavadoRacimos.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediLavadoRacimos.requestFocus();
            ediLavadoRacimos.setError("Este espacio es obligatorio");

            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(ediFumigacionClin1.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediFumigacionClin1.requestFocus();
            ediFumigacionClin1.setError("Este espacio es obligatorio");

            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(ediTipoBoquilla.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediTipoBoquilla.requestFocus();
            ediTipoBoquilla.setError("Este espacio es obligatorio");

            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }
*/

        if(ediCajasProcDesp.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCajasProcDesp.requestFocus();
            ediCajasProcDesp.setError("Este espacio es obligatorio");
            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(ediRacimosCosech.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediRacimosCosech.requestFocus();
            ediRacimosCosech.setError("Este espacio es obligatorio");

            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediRacimosRecha.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediRacimosRecha.requestFocus();
            ediRacimosRecha.setError("Vincula Un Reporte C.muestro Rechazados");

            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;
        }

        if(ediRacimProces.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediRacimProces.requestFocus();
            ediRacimProces.setError("Este espacio es obligatorio");

            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }




        if(ediExtCalid.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediExtCalid.requestFocus();
            ediExtCalid.setError("Este espacio es obligatorio");
            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;
        }



        if(ediExtCalidCi.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediExtCalidCi.requestFocus();
            ediExtCalidCi.setError("Este espacio es obligatorio");
            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }




        ///LOS DEMAS DATOS OPCIONALES

        if(!ediExtRodillo.getText().toString().isEmpty() && ediExtRodilloCi.getText().toString().isEmpty() ){ //chekamos que no este vacia
            ediExtRodilloCi.requestFocus();
            ediExtRodilloCi.setError("Se requiere La C.I");
            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }




        if(ediExtRodillo.getText().toString().isEmpty() && !ediExtRodilloCi.getText().toString().isEmpty() ){ //chekamos que no este vacia
            ediExtRodillo.requestFocus();
            ediExtRodillo.setError("Se requiere el nombre");
            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(!ediExtGancho.getText().toString().isEmpty() && ediExtGanchoCi.getText().toString().isEmpty() ){ //chekamos que no este vacia
            ediExtGanchoCi.requestFocus();
            ediExtGanchoCi.setError("Se requiere La C.I");
            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }




        if(ediExtGancho.getText().toString().isEmpty() && !ediExtGanchoCi.getText().toString().isEmpty() ){ //chekamos que no este vacia
            ediExtGancho.requestFocus();
            ediExtGancho.setError("Se requiere el nombre");
            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        ///vamos con los datos de semananas y esoRe




        return true;

    }


    private boolean checkDataCalibFrutaCalEnfn(){
        //le decimos que esta todo bien y omitiremos estos datos....
        return true;
    }



    public void saveInfo(){

            Log.i("test001","toda la data esta completa HUrra ");

               pdialogff = new ProgressDialog(ActivityContenedoresPrev.this);
             pdialogff.setMessage("Actualizando data ");
             pdialogff.show();

            uploadImagesInStorageAndInfoPICS(); //subimos laS IMAGENES EN STORAGE Y LA  data de las imagenes EN R_TDBASE


            //  createObjcInformeAndUpload(); //CREAMOS LOS INFORMES Y LOS SUBIMOS...

            for(int i=0; i<listImagesToDelete.size() ; i++) {

                geTidAndDelete(listImagesToDelete.get(i));

            }

            //aliminamos cambios

            createObjcInformeAndUpload();

    }


    private boolean checkQueexistminim() {

        TextInputEditText ediColortSem14 = findViewById(R.id.ediColortSem14);
        TextInputEditText ediColortSem13 = findViewById(R.id.ediColortSem13);
        TextInputEditText ediColortSem12 = findViewById(R.id.ediColortSem12);
        TextInputEditText ediColortSem11 = findViewById(R.id.ediColortSem11);
        TextInputEditText ediColortSem10 = findViewById(R.id.ediColortSem10);
        TextInputEditText ediColortSem9 = findViewById(R.id.ediColortSem9);

        TextInputEditText ediNumRcim14 = findViewById(R.id.ediNumRcim14);
        TextInputEditText ediNumRcim13 = findViewById(R.id.ediNumRcim13);
        TextInputEditText ediNumRcim12 = findViewById(R.id.ediNumRcim12);
        TextInputEditText ediNumRcim11 = findViewById(R.id.ediNumRcim11);
        TextInputEditText ediNumRcim10 = findViewById(R.id.ediNumRcim10);
        TextInputEditText ediNumRac9 = findViewById(R.id.ediNumRac9);


        TextInputEditText ediPorc14=findViewById(R.id.ediPorc14);
        TextInputEditText ediPorc13=findViewById(R.id.ediPorc13);
        TextInputEditText ediPorc12=findViewById(R.id.ediPorc12);
        TextInputEditText ediPorc11=findViewById(R.id.ediPorc11);
        TextInputEditText ediPorc10=findViewById(R.id.ediPorc10);
        TextInputEditText ediPsgddsorc9 =findViewById(R.id.ediPorc9);


        TextInputEditText [] array = {ediColortSem14,ediColortSem13,ediColortSem12,ediColortSem11,ediColortSem10, ediColortSem9,
                ediNumRcim14,ediNumRcim13,ediNumRcim12,ediNumRcim11,ediNumRcim10,ediNumRac9,
                ediPorc14,ediPorc13,ediPorc12,ediPorc11,ediPorc10,ediPsgddsorc9};


          int indice=0;


        for(int i=0; i<array.length; i++){

            TextInputEditText current =array [i];

            if(!current.getText().toString().trim().isEmpty()){
                String value=current.getText().toString();
                indice++;

                switch(current.getId()){
                    case R.id.ediColortSem14:
                        break;

                    case R.id.ediColortSem13:
                        break;

                    case R.id.ediColortSem12:
                        break;


                    case R.id.ediColortSem11:
                        break;


                    case R.id.ediColortSem10:

                        break;


                    case R.id.ediColortSem9:

                        break;



                    case R.id.ediNumRcim14:
                        break;


                    case R.id.ediNumRcim13:

                        break;

                    case R.id.ediNumRcim12:
                        break;


                    case R.id.ediNumRcim11:
                        break;

                    case R.id.ediNumRcim10:
                        break;

                    case R.id.ediNumRac9:
                        break;

                    ////ediPorc14
                    case R.id.ediPorc14:
                        break;

                    case R.id.ediPorc13:
                        break;

                    case R.id.ediPorc12:
                        break;

                    case R.id.ediPorc11:
                        break;

                    case R.id.ediPorc10:
                        break;

                    case R.id.ediPorc9:
                        break;




                }

            }

        }

if(indice>2) {
   return true;

}else{
    ediPsgddsorc9.requestFocus();
    ediPsgddsorc9.setError("Inserte al menos un valor en este cuadro");

    return false;

}

    }


    private boolean updateCaledarioEnfunde(SetInformDatsHacienda informe) {

        TextInputEditText ediColortSem14 = findViewById(R.id.ediColortSem14);
        TextInputEditText ediColortSem13 = findViewById(R.id.ediColortSem13);
        TextInputEditText ediColortSem12 = findViewById(R.id.ediColortSem12);
        TextInputEditText ediColortSem11 = findViewById(R.id.ediColortSem11);
        TextInputEditText ediColortSem10 = findViewById(R.id.ediColortSem10);
        TextInputEditText ediColortSem9 = findViewById(R.id.ediColortSem9);

        TextInputEditText ediNumRcim14 = findViewById(R.id.ediNumRcim14);
        TextInputEditText ediNumRcim13 = findViewById(R.id.ediNumRcim13);
        TextInputEditText ediNumRcim12 = findViewById(R.id.ediNumRcim12);
        TextInputEditText ediNumRcim11 = findViewById(R.id.ediNumRcim11);
        TextInputEditText ediNumRcim10 = findViewById(R.id.ediNumRcim10);
        TextInputEditText ediNumRac9 = findViewById(R.id.ediNumRac9);


        TextInputEditText ediPorc14=findViewById(R.id.ediPorc14);
        TextInputEditText ediPorc13=findViewById(R.id.ediPorc13);
        TextInputEditText ediPorc12=findViewById(R.id.ediPorc12);
        TextInputEditText ediPorc11=findViewById(R.id.ediPorc11);
        TextInputEditText ediPorc10=findViewById(R.id.ediPorc10);
        TextInputEditText ediPsgddsorc9 =findViewById(R.id.ediPorc9);


        TextInputEditText [] array = {ediColortSem14,ediColortSem13,ediColortSem12,ediColortSem11,ediColortSem10, ediColortSem9,
                ediNumRcim14,ediNumRcim13,ediNumRcim12,ediNumRcim11,ediNumRcim10,ediNumRac9,
                ediPorc14,ediPorc13,ediPorc12,ediPorc11,ediPorc10,ediPsgddsorc9};


        int indice=0;


        for(int i=0; i<array.length; i++){

            TextInputEditText current =array [i];

            if(!current.getText().toString().trim().isEmpty()){
                String value=current.getText().toString();
                indice++;

                switch(current.getId()){
                    case R.id.ediColortSem14:
                        informe.setColortSem14(value);
                        break;

                    case R.id.ediColortSem13:
                        informe.setColortSem13(value);
                        break;

                    case R.id.ediColortSem12:
                        informe.setColortSem12(value);
                        break;


                    case R.id.ediColortSem11:
                        informe.setColortSem11(value);
                        break;


                    case R.id.ediColortSem10:
                        informe.setColortSem10(value);

                        break;


                    case R.id.ediColortSem9:
                        informe.setColortSem9(value);

                        break;



                    case R.id.ediNumRcim14:
                        informe.setNumRcim14(value);
                        break;


                    case R.id.ediNumRcim13:

                        informe.setNumRcim13(value);
                        break;

                    case R.id.ediNumRcim12:
                        informe.setNumRcim12(value);
                        break;


                    case R.id.ediNumRcim11:
                        informe.setNumRcim11(value);
                        break;

                    case R.id.ediNumRcim10:
                        informe.setNumRcim10(value);
                        break;

                    case R.id.ediNumRac9:
                        informe.setNumRcim9(value);
                        break;

                    ////ediPorc14
                    case R.id.ediPorc14:
                        informe.setPorc14(value);
                        break;

                    case R.id.ediPorc13:
                        informe.setPorc13(value);
                        break;

                    case R.id.ediPorc12:
                        informe.setPorc12(value);
                        break;

                    case R.id.ediPorc11:
                        informe.setPorc11(value);
                        break;

                    case R.id.ediPorc10:
                        informe.setPorc10(value);
                        break;

                    case R.id.ediPorc9:
                        informe.setPorc9(value);
                        break;




                }

            }

        }

        if(indice>2) {
            return true;

        }else{
            ediPsgddsorc9.requestFocus();
            ediPsgddsorc9.setError("Inserte al menos un valor en este cuadro");

            return false;

        }

    }


    private void setDataInMoreViews(SetInformDatsHacienda informe) {

        TextInputEditText ediColortSem14 = findViewById(R.id.ediColortSem14);
        TextInputEditText ediColortSem13 = findViewById(R.id.ediColortSem13);
        TextInputEditText ediColortSem12 = findViewById(R.id.ediColortSem12);
        TextInputEditText ediColortSem11 = findViewById(R.id.ediColortSem11);
        TextInputEditText ediColortSem10 = findViewById(R.id.ediColortSem10);
        TextInputEditText ediColortSem9 = findViewById(R.id.ediColortSem9);

        TextInputEditText ediNumRcim14 = findViewById(R.id.ediNumRcim14);
        TextInputEditText ediNumRcim13 = findViewById(R.id.ediNumRcim13);
        TextInputEditText ediNumRcim12 = findViewById(R.id.ediNumRcim12);
        TextInputEditText ediNumRcim11 = findViewById(R.id.ediNumRcim11);
        TextInputEditText ediNumRcim10 = findViewById(R.id.ediNumRcim10);
        TextInputEditText ediNumRac9 = findViewById(R.id.ediNumRac9);


        TextInputEditText ediPorc14=findViewById(R.id.ediPorc14);
        TextInputEditText ediPorc13=findViewById(R.id.ediPorc13);
        TextInputEditText ediPorc12=findViewById(R.id.ediPorc12);
        TextInputEditText ediPorc11=findViewById(R.id.ediPorc11);
        TextInputEditText ediPorc10=findViewById(R.id.ediPorc10);
        TextInputEditText ediPsgddsorc9 =findViewById(R.id.ediPorc9);



        ediColortSem14.setText(informe.getColortSem14());
        ediColortSem13.setText(informe.getColortSem13());
        ediColortSem12 .setText(informe.getColortSem11());
        ediColortSem11 .setText(informe.getColortSem11());
        ediColortSem10 .setText(informe.getColortSem11());
        ediColortSem9.setText(informe.getColortSem9());

        ediNumRcim14.setText(informe.getNumRcim14());
        ediNumRcim13.setText(informe.getNumRcim13());
        ediNumRcim12.setText(informe.getNumRcim12());
        ediNumRcim11.setText(informe.getNumRcim11());
        ediNumRcim10.setText(informe.getNumRcim10());
        ediNumRac9.setText(informe.getNumRcim9());


        ediPorc14.setText(informe.getPorc14());
        ediPorc13.setText(informe.getPorc13());
        ediPorc12.setText(informe.getPorc12());
        ediPorc11.setText(informe.getPorc11());
        ediPorc10.setText(informe.getPorc10());
        ediPsgddsorc9.setText(informe.getPorc9());



    }


    void dowloadSecondPART_Report(String reportUNIQUEidtoSEARCH, int modo){ //DESCRAGAMOS EL SEGUNDO REPORTE

        Log.i("secondInform","el curren report id es "+reportUNIQUEidtoSEARCH);
         RealtimeDB.initDatabasesRootOnly();

        Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("listInformes").orderByChild("uniqueIDinformePart2").equalTo(reportUNIQUEidtoSEARCH);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    SetInformEmbarque2 informEmbarque2= ds.getValue(SetInformEmbarque2.class);

                    if(informEmbarque2!=null){
                        Variables.CurrenReportPart2=informEmbarque2;
                        break;
                    }
                }
                Log.i("secondInform","el id del secon resport es "+Variables.CurrenReportPart2.getUniqueIDinformePart2());
                Log.i("secondInform","el CANDAO ES "+Variables.CurrenReportPart2.getCandadoQsercom());


                dowloadThirdReportAndCallSetData(reportUNIQUEidtoSEARCH,modo);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }

    void dowloadThirdReportAndCallSetData(String reportUNIQUEidtoSEARCH,int modo){ //DESCRAGAMOS EL SEGUNDO REPORTE

        Log.i("secondInform","el curren report id es "+reportUNIQUEidtoSEARCH);


        // DatabaseReference midatabase=rootDatabaseReference.child("Informes").child("listInformes");
        Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("listInformes").orderByChild("uniqueIDinformeDatsHda").equalTo(reportUNIQUEidtoSEARCH);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    SetInformDatsHacienda inform= ds.getValue(SetInformDatsHacienda.class);

                    if(inform!=null){
                        Variables.CurrenReportPart3=inform;
                        break;
                    }
                }



                //AGREGMOS LA DATA EN LOS FILEDS
                setDataInfields(Variables.CurrenReportPart1,Variables.CurrenReportPart2,Variables.CurrenReportPart3);
                setDataInMoreViews(Variables.CurrenReportPart3);
                setDtaInOthersViews(Variables.CurrenReportPart1,Variables.CurrenReportPart2,Variables.CurrenReportPart3);

                if(Variables.CurrenReportPart1.getAtachControCuadroMuestreo().trim().length()>1){
                    //descragamos cuadro muestreo adn se data..

                    DowloadUniqeuRechazadosObjectCUADROMuestreoAndSetNumRechzados(Variables.CurrenReportPart1.getAtachControCuadroMuestreo());



                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }


    @Override
    public void onBackPressed() {
       finish();
    }

    private void dowloadReportsVinucladosAndSetinRecycler(String reportidToSearch, ArrayList<String>listIdSvINCULADOS ) {
        Log.i("salero","bsucando este reporte con este id  "+reportidToSearch);

        RealtimeDB.initDatabasesRootOnly();


        //  Query query = mDatabase2.child("Clientes").orderByChild("userIdUnique").equalTo(userCurrent.getUserIdUnique());

        Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("listControCalidad").orderByChild("uniqueId").equalTo(reportidToSearch);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {
                    ControlCalidad  user=ds.getValue(ControlCalidad.class);
                    Log.i("salero","encontrado uno ");

                    listFormsControlCalidad.add(user);

                    mapControlCalidad.put(user.getUniqueId(),user);


                    checkedListForms.add(new CheckedAndAtatch(user.getSimpleDate(),user.getHacienda(),"Control calidad",true,String.valueOf(user.getUniqueId())));


                }

                //cuando las descrague todos
                Log.i("salero","el list forms size es "+ listFormsControlCalidad.size());

                Log.i("salero","el listIdSvINCULADOS size es "+listIdSvINCULADOS.size());




                if(checkedListForms.size() ==listIdSvINCULADOS.size()){




                    calltoDowloadReportsVinculadoCudroMuestreo();

                    //cargamos la info en el sheet cargado


                   // DowloadCalbAndRechazadosAndSetRecicler();


                    //  showReportsAndSelectOrDeleteVinuclados(ActivityContenedores.this,true);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private void DowloadCalbAndRechazadosAndSetRecicler (RecyclerView recicler,boolean esreportsVinculados, String uniqeuIDiNFORME,int numTOtalRportVINCULARaLLTYPES ){

        //to fetch all the users of firebase Auth app
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference usersdRef = rootRef.child("Informes").child("CuadrosMuestreo");

        Query query = usersdRef.orderByChild("uniqueIdObject").equalTo(uniqeuIDiNFORME);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {

                    CuadroMuestreo informe=ds.getValue(CuadroMuestreo.class);
                    Log.i("holerd","aqui se encontro un cuadro muestreo......");

                    if(informe!=null){


                       // listFormsControlCalidad.add(informe);

                        checkedListForms.add(new CheckedAndAtatch(informe.getSimpleDateFormat(),informe.getCodigo(),"Cuadro Muestreo",true,String.valueOf(informe.getUniqueIdObject())));



                    }


                }



                Log.i("hanoaia","el checkedListForms size es "+ checkedListForms.size());
                Log.i("hanoaia","el numTOtalRportVINCULARaLLTYPES es "+ numTOtalRportVINCULARaLLTYPES);


                if(checkedListForms.size()==numTOtalRportVINCULARaLLTYPES){

                    Log.i("hanoaia","es tru evamos sgiguienyte...");


                    setDataInRecyclerOfBottomSheet(recicler,checkedListForms,esreportsVinculados);


                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("misdata","el error es  "+ error.getMessage());

            }
        } );

    }




    private void setDataInRecyclerOfBottomSheet(RecyclerView reciclerView, ArrayList<CheckedAndAtatch>lista,boolean esReportsVinculadosMod) {
        Log.i("samerr", "se llamo setDataInRecyclerOfBottomSheet y esl zie es  " + lista.size());

        //Button  btnSaveCambiosxxx=bottomSheetDialog.findViewById(R.id.btnSaveCambiosxxx);

        if (esReportsVinculadosMod && lista.size() == 0) {
            txtAdviseer.setText("No hay Reportes Vinculados");

        }


        if (lista.size() == 0) {

            txtAdviseer.setVisibility(TextView.VISIBLE);
            txtAdviseer.setText("No hay Reportes en este periodo, selecione otro");

            txtAdviserDesvicunlar.setVisibility(TextView.GONE);
            //  btnSaveCambiosxxx.setVisibility(TextView.GONE);

            Log.i("samerr", "se ejeduto el if ");

        }

        else

        {
            Log.i("samerr", "se ejeduto el else ");
            txtAdviserDesvicunlar.setVisibility(TextView.VISIBLE);
            // btnSaveCambiosxxx.setVisibility(TextView.VISIBLE);

            txtAdviseer.setVisibility(TextView.GONE);
        }



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ActivityContenedoresPrev.this);


      int sizeList= generateLISTbyStringVinculados(CustomAdapter.idsFormsVinucladosControlCalidadString,CustomAdapter.idsFormsVinucladosCudorMuestreoString).size();
      Log.i("elsized","misizelistes "+sizeList);


        Log.i("elsized","e; size de lista  2es  "+lista.size());

        CustomAdapter adapter = new CustomAdapter(ActivityContenedoresPrev.this, lista, generateLISTbyStringVinculados(CustomAdapter.idsFormsVinucladosControlCalidadString,CustomAdapter.idsFormsVinucladosCudorMuestreoString));


        //  this.adapter.setPlayPauseClickListener(this);

        reciclerView.setLayoutManager(layoutManager);
        reciclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new CustomAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {

                //mejor ponemos el key en un


                // seria un mapa


                if( v.getTag(R.id.tagImgCategory).toString().equals("Cuadro Muestreo")){


                   // String tipoInforme = (String) holder.checkBx.getTag(R.id.tipoInforme);


                    ///    Variables.currentcuadroMuestreo=listCuadrosMuestreos.get(position)

                    Variables.currentcuadroMuestreo=mapCudroMuestreo.get(String. valueOf(v.getTag(R.id.tagImgUniqueIdItem)));


                    Log.i("dbuhehjr","el id selecte  es "+v.getTag(R.id.tagImgUniqueIdItem));

                    showPRogressAndStartActivity(new Intent(ActivityContenedoresPrev.this, CuadMuestreoCalibAndRechazPrev.class));


                }else{

                    Log.i("dbuhehjr","el id selecte es "+v.getTag(R.id.tagImgUniqueIdItem));



                  //  Variables.currenControlCalReport= listFormsControlCalidad.get(position);

                    Variables.currenControlCalReport=mapControlCalidad.get(String.valueOf(v.getTag(R.id.tagImgUniqueIdItem)));


                    showPRogressAndStartActivity(new Intent(ActivityContenedoresPrev.this, FormularioControlCalidadPreview.class));


                }




                bottomSheetDialog.dismiss();

            }
        });

        bottomSheetDialog.show();


    }

    private void showPRogressAndStartActivity(Intent i) {
        progress =new ProgressDialog(ActivityContenedoresPrev.this);

        progress.setMessage("Cargando Formulario....");
        // progress.setProgressStyle(ProgressDialog.THEME_HOLO_LIGHT);
        progress.setIndeterminate(true);
        progress.show();


        new Thread ( new Runnable()
        {
            public void run()
            {

                startActivity(i);
                // finish();
               // progress.dismiss();


            }
        }).start();



        @SuppressLint("HandlerLeak") Handler progressHandler = new Handler()
        {

            public void handleMessage(Message msg1)
            {

                progress.dismiss();
            }
        };


    }



    private void dowloadReportsVinucLADSAndGOcREATEpdf(String reportidToSearch, int contador,int sizeListIterate ) {

        Variables.listReprsVinculads=new ArrayList<>();

        Log.i("salero","bsucando este reporte con este id  "+reportidToSearch);



        RealtimeDB.initDatabasesRootOnly();

        Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("listControCalidad").orderByChild("uniqueId").equalTo(reportidToSearch);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {
                    ControlCalidad  user=ds.getValue(ControlCalidad.class);

                    if(user != null) {

                        Variables.listReprsVinculads.add(user);

                    }
                }

                if( sizeListIterate == contador){


                    String nameFilePdf="TTNU-"+Variables.CurrenReportPart1.getUniqueIDinforme()+" "+ Variables.CurrenReportPart1.getProductor();


                    Log.i("comnadaer","bien vamos a activity pdf maker");

                    //vamos a  activity
// createObjWhitCurrentDataFieldsAndCALLdOWLOAD();
                    Intent intent = new Intent(ActivityContenedoresPrev.this, PdfMaker2_0.class);
                    intent.putExtra(Variables.KEY_PDF_MAKER,Variables.FormPreviewContenedores);
                    intent.putExtra(Variables.KEY_PDF_MAKER_PDF_NAME ,nameFilePdf);



                    startActivity(intent);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }



    private void DowloadControlcalidadVinculadosandDecideIRpdfMAKER(String valueVinculds){

      ArrayList<String> listIdSvINCULADOS= generateLISTbyStringVinculados(valueVinculds);

        if(listIdSvINCULADOS.size()>0 ){  //si existen vinuclados DESCRAGAMOS los informes viculados usando los ids uniqe id

          //  showReportsAndSelectOrDeleteVinuclados(ActivityContenedoresPrev.this,true);
               int contadorx=0;

            for(String value: listIdSvINCULADOS){
                contadorx++;

                Log.i("comnadaer","se ejecuto esto veces, buscamos este "+value);

                dowloadReportsVinucLADSAndGOcREATEpdf (value,contadorx,listIdSvINCULADOS.size());

            }
        }


        else {

            Toast.makeText(ActivityContenedoresPrev.this, "No Hay reportes vinculados ", Toast.LENGTH_SHORT).show();

        }

    }





    private void DowloadUniqeuRechazadosObjectCUADROMuestreoAndSetNumRechzados(String currentIDoBJECTvinuc ){

        DatabaseReference usersdRef = RealtimeDB.rootDatabaseReference.child("Informes").child("CuadrosMuestreo");

        Query query = usersdRef.orderByChild("uniqueIdObject").equalTo(currentIDoBJECTvinuc);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {

                    CuadroMuestreo informe=ds.getValue(CuadroMuestreo.class);
                    Log.i("holerd","aqui se encontro un cuadro muestreo......");

                    if(informe!=null){

                        Variables.CurrenReportPart3.setEdiRacimosRecha(  String.valueOf(informe.getTotalRechazadosAll()));

                        ediRacimosRecha.setText(String.valueOf(informe.getTotalRechazadosAll()));
                            btnGENERARpdf.setEnabled(true);

                        break;
                    }


                }





            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("misdata","el error es  "+ error.getMessage());

            }
        } );

    }





}
