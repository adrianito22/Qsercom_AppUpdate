package com.tiburela.qsercom.activities.formulariosPrev;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tiburela.qsercom.PdfMaker.PdfMaker2_0;
import com.tiburela.qsercom.PdfMaker.PdfMakerContenresAcopio;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.activities.formularios.ActivityContersEnAcopio;
import com.tiburela.qsercom.adapters.RecyclerViewAdapLinkage;
import com.tiburela.qsercom.adapters.RecyclerViewAdapter;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.dialog_fragment.DialogConfirmChanges;
import com.tiburela.qsercom.models.ContenedoresEnAcopio;
import com.tiburela.qsercom.models.DatosDeProceso;
import com.tiburela.qsercom.models.EstateFieldView;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.SetInformDatsHacienda;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;
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


public class PreviewsFormDatSContersEnAc extends AppCompatActivity implements View.OnClickListener
{

    TextInputEditText ediNombreRevisa;
    TextInputEditText ediCodigoRevisa;




    TextInputEditText ediSemana;
    ImageView imgVAtachProcesoFrutaFinca;
    ImageView imbTakePicProcesoFrutaFinca;
    ImageView imgVAtachLlegadaContenedor;
    ImageView imbTakePicLllegadaContenedor;
    ImageView imgVAtachSellosLlegada;
    ImageView imbTakePicSellosLlegada;
    ImageView imgVAtachPuertaAbiertaContenedor;
    ImageView imbTakePicPuertaAbiertaContenedor;
    ImageView imgVAtachFotosPallet;
    ImageView imbTakePicPallet;
    ImageView imgVAtachCierreContenedor;
    ImageView imbTakePicCierreContenedor;
    ImageView imgVAtachDocumentacionss;
    ImageView imbTakePicDocuementacionxx;
    Button btnGenerarPdf;
    private static final int PERMISSION_REQUEST_CODE=100;
    private String UNIQUE_ID_iNFORME;
    boolean hayUnformularioIcompleto ;
    boolean isModEdicionFields;
    FloatingActionButton fab ;
    int contadorIterador=0;
    boolean copiamosDatax;
    private int currentTypeImage=0;
    private final int CODE_TWO_PERMISIONS = 12;

    ProgressBar progressBarFormulario;
    String keyNodeActualizar="";
    TextInputEditText ediFechaInicio;
    TextInputEditText fechDetermino;
    TextInputEditText ediExpSolicitante;
    TextInputEditText ediExpProcesada;
    TextInputEditText ediMarca;
    TextInputEditText ediPuerto;
    TextInputEditText ediAgenciaNav;
    TextInputEditText ediInspectorAcopio;
    TextInputEditText ediCedulaI;
    TextInputEditText ediCjasProcesDespacha;

    TextInputEditText ediZona;
    TextInputEditText ediHoraInicio;
    TextInputEditText ediHoraTermino;
    TextInputEditText ediHoraLLegadaContenedor;
    TextInputEditText ediHoraSalidaContenedor;
    TextInputEditText ediNguiaRemision;
    TextInputEditText ediNtargetaEmbarque;
    TextInputEditText ediDestino;
    TextInputEditText ediVapor;
    TextInputEditText ediFotoContenedor;
    TextInputEditText ediNumContenedor;

    TextInputEditText ediCompaniaTransporte;
    TextInputEditText ediNombreChofer;
    TextInputEditText ediCedula;
    TextInputEditText ediCelular;
    TextInputEditText ediPLaca;
    TextInputEditText ediMarcaCabezal;
    TextInputEditText ediColorCabezal;

    TextInputEditText ediTare;
    TextInputEditText ediBooking;
    TextInputEditText ediMaxGross;
    TextInputEditText ediNumSerieFunda;
    TextInputEditText stikVentolerExterna;
    TextInputEditText ediCableRastreoLlegada;
    TextInputEditText ediSelloPlasticoNaviera;
    TextInputEditText ediOtroSellosLlegada;

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

    Spinner spinnerSelectZona;
    Spinner spFumigaCorL1 ;
    Spinner spTipoBoquilla ;



    LinearLayout linLayoutHeader1;
    LinearLayout linLayoutHeader3;
    LinearLayout linLayoutHeader4;
    LinearLayout linLayoutHeader5;
    LinearLayout linLayoutHeader6;
    LinearLayout linLayoutHeader7;


    ArrayList<View> listViewsClickedUser;

    ActivityResultLauncher activityResultLauncher;
    Uri cam_uri;



    public static Context context;


    @Override
    protected void onStart() {
        super.onStart();

        //Auth.initAuth(PreviewsFormDatSContersEnAc.this);
       // Auth.signInAnonymously(PreviewsFormDatSContersEnAc.this);




        // Check if user is signed in (non-null) and update UI accordingly.
       // FirebaseUser currentUser = Auth.mAuth.getCurrentUser();
      //  updateUI(currentUs bver)

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_datos_contene_acopio_prev);

        hideSomeElemtosAnexos();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            hayUnformularioIcompleto = extras.getBoolean("ActivitymenuKey");

            //The key argument here must match that used in the other activity
        }


        ImagenReport.hashMapImagesData=new HashMap<>();

        context = getApplicationContext();


        UNIQUE_ID_iNFORME=Variables.CurrenReportContensEnACp.getUniqueIDinforme();

      // FirebaseApp.initializeApp(this);
      //  DatabaseReference rootDatabaseReference = FirebaseDatabase.getInstance().getReference(); //anterior


        Variables.activityCurrent=Variables.FormatDatsContAcopiPREVIEW;
      //  Auth.initAuth(this);

        StorageData. initStorageReference();


        findViewsIds();
        configCertainSomeViewsAliniciar();
      //  ocultaCertainViews();
        listViewsClickedUser=new ArrayList<>();

        addClickListeners();
        resultatachImages();
        listennersSpinners();

        eventCheckdata();




        checkModeVisualitY(); //despues lo llamaremos solo una vez
        addDataEnFields(Variables.CurrenReportContensEnACp);
        RealtimeDB.initDatabasesRootOnly();
        dowloadImagesDataReport(Variables.CurrenReportContensEnACp.getUniqueIDinforme());

        Log.i("samamf","el value es  "+ Variables.CurrenReportContensEnACp.getDatosProcesoContenAcopioKEYFather());
        dowLOADAndCallSETdatosAcopioprocesO(Variables.CurrenReportContensEnACp.getDatosProcesoContenAcopioKEYFather());

        //creaFotos();


    }





    void showingTimePicker( View vista){


        final Calendar cldr = Calendar.getInstance();
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        // time picker dialog
        TimePickerDialog  picker = new TimePickerDialog(PreviewsFormDatSContersEnAc.this,
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

                        if(minutes.length()==1){

                            minutes="0"+minutes;
                        }



                        if(vista.getId()==R.id.ediHoraInicio) {
                            ediHoraInicio.setText(sHour + ":" + minutes+" "+AM_PM);
                        }



                        else if (vista.getId()== R.id.ediHoraTermino) {
                            ediHoraTermino.setText(sHour + ":" + minutes+" "+AM_PM);
                        }



                        else if (vista.getId()== R.id.ediHoraLLegadaContenedor) {
                            ediHoraLLegadaContenedor.setText(sHour + ":" + minutes+" "+AM_PM);
                        }



                        else if (vista.getId()== R.id.ediHoraSalidaContenedor) {
                            ediHoraSalidaContenedor.setText(sHour + ":" + minutes+" "+AM_PM);

                        }


                        else if (vista.getId()== R.id.ediHoraEncendido1) {
                            ediHoraEncendido1.setText(sHour + ":" + minutes+" "+AM_PM);


                        }



                        else if (vista.getId()== R.id.ediHoraEncendido2) {
                            ediHoraEncendido2.setText(sHour + ":" + minutes+" "+AM_PM);


                        }



                    }
                }, hour, minutes, true);

        picker.setButton(DialogInterface.BUTTON_POSITIVE, "OK", picker);
        picker.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", picker);

        picker.show();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    void selecionaFecha(int idView){


        final Calendar cldr = Calendar.getInstance();
        int year = cldr.get(Calendar.YEAR);
        int daySemana = cldr.get(Calendar.DAY_OF_WEEK);
        int mes = cldr.get(Calendar.MONTH);

        // time picker dialog
        DatePickerDialog  picker = new DatePickerDialog(PreviewsFormDatSContersEnAc.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        String dateSelec=i2+"/"+(i1+1)+"/"+i;

                        if(i2<10  && i1+1<10){
                            dateSelec="0"+i2+"/0"+(i1+1)+"/"+i;
                        }

                        else if (i2<10){
                            dateSelec="0"+i2+"/"+(i1+1)+"/"+i;
                        }

                        else  if(i1+1<10){
                            dateSelec=i2+"/0"+(i1+1)+"/"+i;
                        }

                        if(idView==R.id.ediFechaInicio){
                            ediFechaInicio.setText(dateSelec);

                        }else{

                            fechDetermino.setText(dateSelec);

                        }


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

        disableEditText(ediFechaInicio);
        disableEditText(fechDetermino);

        disableEditText(ediHoraInicio);
        disableEditText(ediHoraTermino);

        disableEditText(ediHoraLLegadaContenedor);//here
        disableEditText(ediHoraSalidaContenedor);


        //disableEditText(ediFotosLlegada);

        disableEditText(ediZona);

        disableEditText(ediHoraEncendido1);
        disableEditText(ediHoraEncendido2);


    }

    private void findViewsIds( ) { //configuraremos algos views al iniciar


        ediNombreRevisa=findViewById(R.id.ediNombreRevisa);
        ediCodigoRevisa=findViewById(R.id.ediCodigoRevisa);


        btnGenerarPdf=findViewById(R.id.btnGenerarPdf);
        imgVAtachProcesoFrutaFinca=findViewById(R.id.imgVAtachProcesoFrutaFinca);
        imbTakePicProcesoFrutaFinca=findViewById(R.id.imbTakePicProcesoFrutaFinca);
        imgVAtachLlegadaContenedor = findViewById(R.id.imgVAtachLlegadaContenedor);
        imbTakePicLllegadaContenedor= findViewById(R.id.imbTakePicLllegadaContenedor);
        imgVAtachSellosLlegada= findViewById(R.id.imgVAtachSellosLlegada);
        imbTakePicSellosLlegada= findViewById(R.id.imbTakePicSellosLlegada);
        imgVAtachPuertaAbiertaContenedor= findViewById(R.id.imgVAtachPuertaAbiertaContenedor);
        imbTakePicPuertaAbiertaContenedor= findViewById(R.id.imbTakePicPuertaAbiertaContenedor);
        imgVAtachFotosPallet= findViewById(R.id.imgVAtachFotosPallet);
        imbTakePicPallet= findViewById(R.id.imbTakePicPallet);
        imgVAtachCierreContenedor= findViewById(R.id.imgVAtachCierreContenedor);
        imgVAtachDocumentacionss = findViewById(R.id.imgVAtachDocumentacionss);
        imbTakePicCierreContenedor= findViewById(R.id.imbTakePicCierreContenedor);
        imbTakePicDocuementacionxx = findViewById(R.id.imbTakePicDocuementacionxx);







        fab = (FloatingActionButton) findViewById(R.id.fab);

        ediNtargetaEmbarque=findViewById(R.id.ediNtargetaEmbarque);
         ediZona=findViewById(R.id.ediZona);

        ediSemana=findViewById(R.id.ediSemana);
         ediHoraInicio=findViewById(R.id.ediHoraInicio);
         ediHoraTermino=findViewById(R.id.ediHoraTermino);
         ediNguiaRemision=findViewById(R.id.ediNguiaRemision);
        spinnerSelectZona = findViewById(R.id.spinnerZona);
        ediCjasProcesDespacha=findViewById(R.id.ediCjasProcesDespacha);


         fechDetermino=findViewById(R.id.fechDetermino);
         ediExpSolicitante=findViewById(R.id.ediExpSolicitante);
         ediExpProcesada =findViewById(R.id.ediExpProcesada);
         ediMarca =findViewById(R.id.ediMarca);
         ediPuerto =findViewById(R.id.ediPuerto);
        ediAgenciaNav=findViewById(R.id.ediAgenciaNav);
        ediInspectorAcopio=findViewById(R.id.ediInspectorAcopio);
        ediCedulaI=findViewById(R.id.ediCedulaI);


        ediFechaInicio=findViewById(R.id.ediFechaInicio);

        ediTare=findViewById(R.id.ediTare);
        ediBooking=findViewById(R.id.ediBooking);
        ediMaxGross=findViewById(R.id.ediMaxGross);
        ediNumSerieFunda=findViewById(R.id.ediNumSerieFunda);
        stikVentolerExterna=findViewById(R.id.stikVentolerExterna);
        ediCableRastreoLlegada=findViewById(R.id.ediCableRastreoLlegada);
        ediSelloPlasticoNaviera=findViewById(R.id.ediSelloPlasticoNaviera);
        ediOtroSellosLlegada=findViewById(R.id.ediOtroSellosLlegada);


        ediNumContenedor=findViewById(R.id.ediNumContenedor);


        linLayoutHeader1 =findViewById(R.id.linLayoutHeader1);
        linLayoutHeader3 =findViewById(R.id.linLayoutHeader3);
        linLayoutHeader4 =findViewById(R.id.linLayoutHeader4);
        linLayoutHeader5 =findViewById(R.id.linLayoutHeader5);
        linLayoutHeader6 =findViewById(R.id.linLayoutHeader6);
        linLayoutHeader7 =findViewById(R.id.linLayoutHeader7);


         spFumigaCorL1=findViewById(R.id.spFumigaCorL1) ;
         spTipoBoquilla=findViewById(R.id.spTipoBoquilla) ;

          ediDestino=findViewById(R.id.ediDestino);
          ediVapor=findViewById(R.id.ediVapor);

         // ediHOraLllegada=findViewById(R.id.ediHoraLLegadaContenedor);
          //ediHoraSalida=findViewById(R.id.ediHoraSalida);

        ediHoraLLegadaContenedor=findViewById(R.id.ediHoraLLegadaContenedor);
        ediHoraSalidaContenedor=findViewById(R.id.ediHoraSalidaContenedor);

        progressBarFormulario=findViewById(R.id.progressBarFormulario);

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








    }

    public void scroollElementoFaltante(View vistFocus){

        // View targetView = findViewById(R.id.DESIRED_VIEW_ID);
        vistFocus.getParent().requestChildFocus(vistFocus,vistFocus);



    }
    private void  showToast(){

        Toast.makeText(PreviewsFormDatSContersEnAc.this, "Falta Imagen", Toast.LENGTH_SHORT).show();

    }

    boolean cehckFaltanImagenes() {


        if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_LLEGADA_CONTENEDOR)){

            TextView ediFotoProcesoEnFruta=findViewById(R.id.ediFotoLLegadaContenedor);
            ediFotoProcesoEnFruta.requestFocus();
            scroollElementoFaltante(ediFotoProcesoEnFruta);
            showToast();

            return false;
        }else{
            TextView ediFotosSellosInstalados=findViewById(R.id.ediFotoLLegadaContenedor);
            ediFotosSellosInstalados.clearFocus();
            ediFotosSellosInstalados.setError(null);
        }



        if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_SELLO_LLEGADA)){

            TextView ediFotoProcesoEnFruta=findViewById(R.id.ediFotoSellosLLegada);
            ediFotoProcesoEnFruta.requestFocus();
            scroollElementoFaltante(ediFotoProcesoEnFruta);
            showToast();

            return false;
        }else{

            TextView ediFotosSellosInstalados=findViewById(R.id.ediFotoSellosLLegada);
            ediFotosSellosInstalados.clearFocus();
            ediFotosSellosInstalados.setError(null);

        }



        if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_PUERTA_ABIERTA_DEL_CONTENENEDOR)){
            TextView ediFotoProcesoEnFruta=findViewById(R.id.txtFotoPuertacontenedor);
            ediFotoProcesoEnFruta.requestFocus();
            scroollElementoFaltante(ediFotoProcesoEnFruta);
            showToast();

            return false;
        }else{
            TextView ediFotosSellosInstalados=findViewById(R.id.txtFotoPuertacontenedor);
            ediFotosSellosInstalados.clearFocus();
            ediFotosSellosInstalados.setError(null);
        }





        if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_PALLETS)){

            TextView ediFotoProcesoEnFruta=findViewById(R.id.txtFotosPallets);
            ediFotoProcesoEnFruta.requestFocus();
            scroollElementoFaltante(ediFotoProcesoEnFruta);
            showToast();

            return false;
        }else{

            TextView ediFotosSellosInstalados=findViewById(R.id.txtFotosPallets);
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

    private void addClickListeners( ) {

        /**todos add a todos clicklistener de la implemntacion*/

        imgVAtachDocumentacionss.setOnClickListener(this);//ultimo
        imgVAtachProcesoFrutaFinca.setOnClickListener(this);
        imbTakePicProcesoFrutaFinca.setOnClickListener(this);
        imgVAtachLlegadaContenedor.setOnClickListener(this);
        imbTakePicLllegadaContenedor.setOnClickListener(this);
        imgVAtachSellosLlegada.setOnClickListener(this);
        imbTakePicSellosLlegada.setOnClickListener(this);
        imgVAtachPuertaAbiertaContenedor.setOnClickListener(this);
        imbTakePicPuertaAbiertaContenedor.setOnClickListener(this);
        imgVAtachFotosPallet.setOnClickListener(this);
        imbTakePicPallet.setOnClickListener(this);
        imgVAtachCierreContenedor.setOnClickListener(this);
        imbTakePicCierreContenedor.setOnClickListener(this);
        imbTakePicDocuementacionxx.setOnClickListener(this);



        ///  imBtakePic.setOnClickListener(this);
       // imBatach.setOnClickListener(this);

        fab.setOnClickListener(this);


        ediHoraEncendido1.setOnClickListener(this);
        ediHoraEncendido2.setOnClickListener(this);



        linLayoutHeader1.setOnClickListener(this);
        linLayoutHeader3.setOnClickListener(this);
        linLayoutHeader4.setOnClickListener(this);
        linLayoutHeader5.setOnClickListener(this);
        linLayoutHeader6.setOnClickListener(this);
        linLayoutHeader7.setOnClickListener(this);
     //   linLayoutHeader8.setOnClickListener(this);

        ediFechaInicio.setOnClickListener(this);
        ediHoraInicio.setOnClickListener(this);
        ediHoraTermino.setOnClickListener(this);
        fechDetermino.setOnClickListener(this);
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

        String data[]={"image/*"};
        Log.i("miclickimg","hemos hecho click");

        int idCurrent= view.getId();

        if(idCurrent==R.id.imgVAtachProcesoFrutaFinca || idCurrent==R.id.imgVAtachLlegadaContenedor || idCurrent==R.id.imgVAtachSellosLlegada ||
                idCurrent==R.id.imgVAtachPuertaAbiertaContenedor
                || idCurrent==R.id.imgVAtachFotosPallet || idCurrent==R.id.imgVAtachCierreContenedor ||
                idCurrent == R.id.imgVAtachDocumentacionss){  ///si es atach


            currentTypeImage=Integer.parseInt(view.getTag().toString());

            activityResultLauncher.launch("image/*");


            Log.i("miclickimg","es foto es type selected es "+currentTypeImage);

        }

        else if(idCurrent==R.id.imbTakePicProcesoFrutaFinca || idCurrent==R.id.imbTakePicLllegadaContenedor
                || idCurrent==R.id.imbTakePicSellosLlegada ||
                idCurrent==R.id.imbTakePicPuertaAbiertaContenedor || idCurrent==R.id.imbTakePicPallet
                || idCurrent==R.id.imbTakePicCierreContenedor || idCurrent==R.id.imbTakePicDocuementacionxx ){ //si es tajke pic con camara

            currentTypeImage=Integer.parseInt(view.getTag().toString());

            takepickNow();

            Log.i("miclickimg","es foto es type selected es "+currentTypeImage);

        }


          else{

            switch (view.getId()) {
                case R.id.fab: //si pulas en btn chekear en que modo esta ...si el modo cambia...

                    TextView txtModeAdviser=findViewById(R.id.txtModeAdviser2);

                    if(isModEdicionFields){ //si es modo edicion..
                        fab.setImageResource(R.drawable.ic_baseline_edit_24aa);

                        txtModeAdviser.setText("Modo Visualizacion ");



                        //cambiamos al modo visualizacion
                        isModEdicionFields=false;
                        activateModePreview();
                        activateModePreviewMoreViews();

                    }else{ //SI NO ES MODO VISUZALIZACION
                        fab.setImageResource(R.drawable.ic_baseline_preview_24jhj);
                        txtModeAdviser.setText("Modo Edicion ");

                        isModEdicionFields=true;
                        activateModeEdit();
                        activateModeEditMoreViews();


                        //CAMABIAMOS EL MODO

                    }



                    break; //









                case R.id.linLayoutHeader1:
                    LinearLayout layoutContainerSeccion=findViewById(R.id.layoutContainerSeccion7);

                    if(layoutContainerSeccion.getVisibility() == View.GONE) {
                        muestraLinearLayout(layoutContainerSeccion);
                    }
                    else{

                        oucultaLinearLayout(layoutContainerSeccion);
                    }
                    break; //



                case R.id.linLayoutHeader2:
                    LinearLayout layoutContainerSeccion3=findViewById(R.id.layoutContainerSeccion3);

                    if(layoutContainerSeccion3.getVisibility() == View.GONE) {
                        muestraLinearLayout(layoutContainerSeccion3);
                    }
                    else{

                        oucultaLinearLayout(layoutContainerSeccion3);
                    }
                    break; //


                case R.id.linLayoutHeader3:
                    LinearLayout layoutContainerSeccion4=findViewById(R.id.layoutContainerSeccion4);

                    if(layoutContainerSeccion4.getVisibility() == View.GONE) {
                        muestraLinearLayout(layoutContainerSeccion4);
                    }
                    else{

                        oucultaLinearLayout(layoutContainerSeccion4);
                    }
                    break; //

                case R.id.linLayoutHeader7:
                    LinearLayout layoutContainerSeccion5=findViewById(R.id.layoutContainerSeccion5);

                    if(layoutContainerSeccion5.getVisibility() == View.GONE) {
                        muestraLinearLayout(layoutContainerSeccion5);
                    }
                    else{

                        oucultaLinearLayout(layoutContainerSeccion5);
                    }
                    break; //
                //linLayoutHeader7

                case R.id.linLayoutHeader4:
                    LinearLayout layoutContainerSeccion3a=findViewById(R.id.layoutContainerSeccion3);

                    if(layoutContainerSeccion3a.getVisibility() == View.GONE) {
                        muestraLinearLayout(layoutContainerSeccion3a);
                    }
                    else{

                        oucultaLinearLayout(layoutContainerSeccion3a);
                    }
                    break; //


                case R.id.linLayoutHeader5:
                    LinearLayout layoutContainerSeccion6=findViewById(R.id.layoutContainerSeccion6);

                    if(layoutContainerSeccion6.getVisibility() == View.GONE) {
                        muestraLinearLayout(layoutContainerSeccion6);
                    }
                    else{

                        oucultaLinearLayout(layoutContainerSeccion6);
                    }
                    break; //


                case R.id.linLayoutHeader6:
                    LinearLayout layoutContainerSeccion9=findViewById(R.id.layoutContainerSeccion9);

                    if(layoutContainerSeccion9.getVisibility() == View.GONE) {
                        muestraLinearLayout(layoutContainerSeccion9);
                    }
                    else{

                        oucultaLinearLayout(layoutContainerSeccion9);
                    }
                    break; //


                case R.id.ediFechaInicio:
                    // Utils.closeKeyboard(FormularioActivity.this);
                    selecionaFecha(R.id.ediFechaInicio);

                    break; //


                case R.id.fechDetermino:
                    // Utils.closeKeyboard(FormularioActivity.this);

                    selecionaFecha(R.id.fechDetermino);

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

                    break; //




            }

        }

        //aqui o


    }

    private void takepickNow() {

        // Permisionx.checkPermission(Manifest.permission.CAMERA,1,this, ActivityContenedores.this)

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






        }




    }

        ActivityResultLauncher<Intent> startCamera = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {



                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(),cam_uri);


                               // Bitmap bitmap= Glide.with(context).asBitmap().load(cam_uri).submit().get();
                                String horientacionImg=HelperImage.devuelveHorientacionImg(bitmap);


                                //creamos un nuevo objet de tipo ImagenReport
                                ImagenReport obcjImagenReport =new ImagenReport("",cam_uri.toString(),currentTypeImage, Utils.getFileNameByUri(PreviewsFormDatSContersEnAc.this,cam_uri),horientacionImg);
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


                            ///  ImagenReport.hashMapImagesData.put(obcjImagenReport.getUniqueIdNamePic(), obcjImagenReport);

                            //Utils.saveMapImagesDataPreferences(ImagenReport.hashMapImagesData, ActivityContersEnAcopio.this);


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


                          //  actualizaListStateView("ediPPC/someProductPostCosecha",true) ;
                           //  Utils.addDataMapPreferences(String.valueOf(view.getId()),editText.getText().toString() ,"iduniquehere", ActivityContersEnAcopio.this);


                        }
                    }

                }

                else if(editText.getText().toString().isEmpty()) {


                    Log.i("idCheck","la data del editext anterior : "+view.getResources().getResourceName(view.getId() )+" esta vacio");


                   // actualizaListStateView(view.getResources().getResourceName(view.getId()),false) ;

                }




                ////si existe lo cambiamos a tru



                else if(! editText.getText().toString().isEmpty()) { //si esta lleno

                    Log.i("idCheck","la data del editext anterior : "+view.getResources().getResourceName(view.getId() )+" esta lleno");

                 //   actualizaListStateView(view.getResources().getResourceName(view.getId()),true) ;

                  //  Utils.addDataMapPreferences(String.valueOf(view.getId()),editText.getText().toString() ,"iduniquehere", ActivityContersEnAcopio.this);



                }


        }




        else if (view.getResources().getResourceName(view.getId()).contains("imbAtach")  ||  view.getResources().getResourceName(view.getId()).contains("imbTakePic")){ //imBtakePic

             //COMPORBAQMOS SI EXISTE AL ME4NOS UN IMAGEN URI LIST..

            if(ImagenReport.hashMapImagesData.size()> 0 ) {
              //  actualizaListStateView("imbAtach/imbTakePic",true) ;

               Log.i("miodata","el slecionado anteruior es imbAtach/imbTakePic y contiene al menos una foto");


            }else {

              //  actualizaListStateView("imbAtach/imbTakePic",false) ;
                Log.i("miodata","el slecionado anteruior es imbAtach/imbTakePic y no contiene fotos");



            }


        }






        //seran mas comprobacion para verificar si imagenes por ejemplo fiueron completadas..
        //otra para radiobutton y otr para otro tipo de view..tec


       // actualizaProgressBar();

            }



    private void actualizaListStateView(String idSearch,boolean isEstaLleno){
///


        final String  idview = idSearch.replace(Variables.paqueteName+":id/","");


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

                        for(int indice=0; indice<result.size(); indice++){


                            try {

                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(),result.get(indice));


                            //    Bitmap bitmap=Glide.with(context).asBitmap().load(cam_uri).submit().get();
                                String horientacionImg=HelperImage.devuelveHorientacionImg(bitmap);

                                //creamos un nuevo objet de tipo ImagenReport
                                ImagenReport obcjImagenReport =new ImagenReport("",result.get(indice).toString(),currentTypeImage, UUID.randomUUID().toString()+Utils.getFormate2(Utils.getFileNameByUri(PreviewsFormDatSContersEnAc.this,result.get(indice))),horientacionImg);
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


                            ///     ImagenReport imagenReportObjc =new ImagenReport("",result.get(indice).toString(),currentTypeImage,UNIQUE_ID_iNFORME,Utils.getFileNameByUri(ActivityContenedoresPrev.this,result.get(indice)));

                            //   ImagenReport.hashMapImagesData.put(imagenReportObjc.getUniqueIdNamePic(), imagenReportObjc);
                            //  Log.i("mispiggi","el size de la  lists  el key del value es "+imagenReportObjc.getUniqueIdNamePic());


                        }



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


    spinnerSelectZona .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String zonaEelejida= spinnerSelectZona.getSelectedItem().toString();
                ediZona.setText(zonaEelejida);
                if(zonaEelejida.equals("Ninguna")){
                    //actualizamos
                    Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                    ediZona.setText("");
                   // actualizaListStateView("spinnerZona",false) ;
                }else {
                   // actualizaListStateView("spinnerZona",true) ;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



}


private void showImagesPicShotOrSelectUpdateView(boolean isDeleteImg){

        //si es eliminar comprobar aqui
    if(isDeleteImg){

        currentTypeImage=Variables.typeoFdeleteImg;

        Log.i("isdeletyin","is deleting ");


    }


     ArrayList<ImagenReport> filterListImagesData=new ArrayList<ImagenReport>(); //LISTA FILTRADA QUE REPRESENTARA EL RECICLERVIEW

    RecyclerView recyclerView=null;


    for (Map.Entry<String, ImagenReport> set : ImagenReport.hashMapImagesData.entrySet()) {

        String key = set.getKey();

        ImagenReport value = set.getValue();

        if(value.getTipoImagenCategory()==currentTypeImage){

            filterListImagesData.add(ImagenReport.hashMapImagesData.get(key));

        }


    }


    //buscamos este





    switch(currentTypeImage){

        case Variables.FOTO_LLEGADA_CONTENEDOR:
            recyclerView= findViewById(R.id.recyclerFotollegadaContenedor);
            break;

        case Variables.FOTO_SELLO_LLEGADA:
            recyclerView= findViewById(R.id.recyclerFotoSellosLlegada);
            break;

        case Variables.FOTO_PUERTA_ABIERTA_DEL_CONTENENEDOR:
            recyclerView= findViewById(R.id.recyclerFotoPuertaAbrContedor);
            break;

        case Variables.FOTO_PALLETS:
            recyclerView= findViewById(R.id.recyclerFotoPallets);
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

            if( checkDataFieldsToUploadAndGeneratePdf(false)){ //ACTUALIZAMOS FORMULARIO

                    openBottomSheetConfirmCreateNew(Variables.FormatDatsContAcopi); //esti podemos hacerlo en caso que de true

            }else{

                Log.i("misgfa","falta data aqui");

            }



           /***vamos hacer el chekeo **********/


        }
    });

    btnGenerarPdf.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(  checkDataFieldsToUploadAndGeneratePdf(true)){


                updateInformeWhitCurrentDataOfViews(); //actualizamos el reporte actual

                Intent intent = new Intent(PreviewsFormDatSContersEnAc.this,    PdfMakerContenresAcopio.class);
                intent.putExtra(Variables.KEY_PDF_MAKER, Variables.FormatDatsContAcopiPREVIEW);


                startActivity(intent);
            }else{

                Toast.makeText(PreviewsFormDatSContersEnAc.this, "Falta data", Toast.LENGTH_SHORT).show();
            }







        }
    });



}

boolean checkDataFieldsToUploadAndGeneratePdf(boolean isGeneratePdf){ //


        if(isGeneratePdf){
            if(ediNombreRevisa.getText().toString().equals("")){
                ediNombreRevisa.requestFocus();
                ediNombreRevisa.setError("Agrega un nombre del que revisó");
                return false;
            }



            if(ediCodigoRevisa.getText().toString().equals("")){
                ediCodigoRevisa.requestFocus();
                ediCodigoRevisa.setError("Agrega un codigo del que revisó");
                return false;
            }
        }




    if(! checkDatosGeneralesIsLleno()){

        Log.i("test001","no esta lleno  checkDatosGeneralesIsLleno");
        return false;
    }


    else{
        Log.i("test001","si esta lleno checkDatosGeneralesIsLleno ");

    }



    if(! checkDatosContenedorIsLleno()){
        Log.i("test001","no esta lleno  checkDatosContenedorIsLleno");

        return false;
    }else{

        Log.i("test001","si  esta lleno  checkDatosContenedorIsLleno");
    }


    if(! checkDataSellosLlegadaIsLleno()){
        Log.i("test001","no esta lleno  checkDataSellosLlegadaIsLleno");

        return false;
    }else{

        Log.i("test001","si  esta lleno  checkDataSellosLlegadaIsLleno");


    }




    if(! checkSellosInstaladosIsLleno()){
        Log.i("test001","no esta lleno  checkSellosInstaladosIsLleno");

        return false;

    }else{

        Log.i("test001","si  esta lleno  checkSellosInstaladosIsLleno");


    }


    if(! checkDatosTransportistaIsLleno()){
        Log.i("test001","no esta lleno  checkDatosTransportistaIsLleno");

        return false;

    }else{

        Log.i("test001","si  esta lleno  checkDatosTransportistaIsLleno");


    }

     keyNodeActualizar =Variables.CurrenReportContensEnACp.getDatosProcesoContenAcopioKEYFather(); //que que cotienen este nodo


    if(ediCjasProcesDespacha.getText().toString().trim().isEmpty()){
        ediCjasProcesDespacha.requestFocus();
        ediCjasProcesDespacha.setError("Este espacio es obligatorio");

        return false;

    }else{

        Log.i("caramba","si  esta lleno  todo en orden");


    }


    if(ediInspectorAcopio.getText().toString().trim().isEmpty()){
        ediInspectorAcopio.requestFocus();
        ediInspectorAcopio.setError("Este espacio es obligatorio");

        return false;

    }else{

        Log.i("caramba","si  esta lleno  todo en orden");

//
    }

    if(ediCedulaI.getText().toString().trim().isEmpty()){
        ediCedulaI.requestFocus();
        ediCedulaI.setError("Este espacio es obligatorio");

        return false;

    }else{

        Log.i("caramba","si  esta lleno  todo en orden");

//ediCedulaI
    }


    if(!cehckFaltanImagenes()){
        return false;

    }




    if(! creaAcMapDatosProcesoAndCheck(Variables.CurrenReportContensEnACp.getDatosProcesoContenAcopioKEYFather(),keyNodeActualizar)){
        Log.i("caramba","no esta en orden cc ");
        Toast.makeText(PreviewsFormDatSContersEnAc.this, "Falta cuadro Proceso ", Toast.LENGTH_LONG).show();

        return false;

    }else{

        Log.i("caramba","si  esta lleno  todo en orden ccc");

        Toast.makeText(PreviewsFormDatSContersEnAc.this, "Hay un dato imcompleto en datos de Proceso", Toast.LENGTH_LONG).show();

    }




   // DialogConfirmCreateNewForm.showBottomSheetDialogConfirmAndCallUpdate(PreviewsFormDatSContersEnAc.this,Variables.FormatDatsContAcopi);


return true;
}

    private void updateInformeWhitCurrentDataOfViews() {

        Variables.CurrenReportContensEnACp = new ContenedoresEnAcopio(UNIQUE_ID_iNFORME,ediFechaInicio.getText().toString(),fechDetermino.getText().toString()
                ,ediExpSolicitante.getText().toString(),
                ediExpProcesada.getText().toString(),ediPuerto.getText().toString(),ediZona.getText().toString(),ediMarca.getText().toString(),
                ediHoraInicio.getText().toString(),ediHoraTermino.getText().toString(),ediNguiaRemision.getText().toString(),ediNtargetaEmbarque.getText().toString()
                ,ediDestino.getText().toString(),ediVapor.getText().toString(),ediNumContenedor.getText().toString(),ediHoraLLegadaContenedor.getText().toString()
                ,ediHoraSalidaContenedor.getText().toString(),ediAgenciaNav.getText().toString(),ediSelloPlasticoNaviera.getText().toString()
                ,stikVentolerExterna.getText().toString(),ediNumSerieFunda.getText().toString(),ediCableRastreoLlegada.getText().toString(),ediBooking.getText().toString(),
                ediMaxGross.getText().toString(),ediTare.getText().toString(),ediOtherSellos.getText().toString(),ediTermofrafo1.getText().toString()
                ,ediTermofrafo2.getText().toString(),
                ediCandadoqsercon.getText().toString(),ediSelloNaviera.getText().toString(),ediCableNaviera.getText().toString(),ediSelloPlastico.getText().toString()
                ,ediCandadoBotella.getText().toString(),ediCableExportadora.getText().toString(),ediSelloAdesivoexpor.getText().toString(),esiSelloAdhNaviera.getText().toString()
                ,ediOtherSellos.getText().toString(),ediCompaniaTransporte.getText().toString(),ediNombreChofer.getText().toString(),ediCedula.getText().toString()
                ,ediCelular.getText().toString(),ediPLaca.getText().toString(),ediMarcaCabezal.getText().toString(),ediColorCabezal.getText().toString(),
                Integer.parseInt(ediCjasProcesDespacha.getText().toString()), ediInspectorAcopio.getText().toString(), Integer.parseInt(ediCedulaI.getText().toString()),"",
                Integer.parseInt(ediSemana.getText().toString()),ediUbicacion1.getText().toString(),
                ediUbicacion2.getText().toString(),ediHoraEncendido1.getText().toString(),ediHoraEncendido2.getText().toString());



        //agr5egamos la data finalemente

        Variables.CurrenReportContensEnACp.setDatosProcesoContenAcopioKEYFather(Variables.CurrenReportContensEnACp.getDatosProcesoContenAcopioKEYFather());
        Variables.CurrenReportContensEnACp.setKeyFirebase(Variables.CurrenReportContensEnACp.getKeyFirebase());
        Variables.CurrenReportContensEnACp.setSimpleDataFormat(Variables.CurrenReportContensEnACp.getSimpleDataFormat());
        Variables.CurrenReportContensEnACp.setFechaUploadMilliseconds(Variables.CurrenReportContensEnACp.getFechaUploadMilliseconds());



        Variables.CurrenReportContensEnACp.setNombreRevisa(ediNombreRevisa.getText().toString());
        Variables.CurrenReportContensEnACp.setCodigonRevisa(ediCodigoRevisa.getText().toString());





    }





    private void openBottomSheetConfirmCreateNew(int tipoFormulario){

        DialogConfirmChanges addPhotoBottomDialogFragment = DialogConfirmChanges.newInstance(tipoFormulario);
        addPhotoBottomDialogFragment.show(getSupportFragmentManager(), DialogConfirmChanges.TAG);
    }


    private void geTidAndDelete( String idUniqueToDelete){ //busca el que tenga esa propieda y obtiene el id node child

        Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("ImagesData").orderByChild("uniqueIdNamePic").equalTo(idUniqueToDelete);

        DatabaseReference usersdRef= RealtimeDB.rootDatabaseReference.child("Informes").child("ImagesData");
        //  Query query = usersdRef.orderByChild("uniqueIdNamePic").equalTo(Variables.currentCuponObjectGlob.getUniqueIdCupòn());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String key=null;

                try {
                    DataSnapshot nodeShot = dataSnapshot.getChildren().iterator().next();
                     key = nodeShot.getKey();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //   private void editaValue(String keyAtoUpdate,String titulo, String descripcion, String direccion, String ubicacionCordenaGoogleMap, String picNameofStorage, double cuponValor, String categoria,boolean switchActivate, boolean switchDestacado){


                if(key!=null){
                    usersdRef.child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                //Toast.makeText(OfertsAdminActivity.this, "Se elimino correctamente", Toast.LENGTH_SHORT).show();

                            }



                        }
                    });


                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



/*
    private boolean checkDatosProcesoIsLleno(){

        if(ediCondicionBalanza.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCondicionBalanza.requestFocus();
            ediCondicionBalanza.setError("Este espacio es obligatorio");
            scroollElementoFaltante(ediCondicionBalanza);

            layoutContainerDatsProceso.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediTipodeCaja.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediTipodeCaja.requestFocus();
            ediTipodeCaja.setError("Este espacio es obligatorio");
            scroollElementoFaltante(ediTipodeCaja);

            layoutContainerDatsProceso.setVisibility(LinearLayout.VISIBLE);
            return false;

        }




        if(ediTipoPlastico.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediTipoPlastico.requestFocus();
            ediTipoPlastico.setError("Este espacio es obligatorio");
            scroollElementoFaltante(ediTipoPlastico);

            layoutContainerDatsProceso.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(ediTipoBalanza.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediTipoBalanza.requestFocus();
            ediTipoBalanza.setError("Este espacio es obligatorio");
            scroollElementoFaltante(ediTipoBalanza);

            layoutContainerDatsProceso.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(switchBalanzaRep.isChecked()  && editipbalanzaRepeso.getText().toString().trim().isEmpty() ){

            editipbalanzaRepeso.requestFocus();
            editipbalanzaRepeso.setError("Selecione el tipo de balanza");
            scroollElementoFaltante(editipbalanzaRepeso);

            layoutContainerDatsProceso.setVisibility(LinearLayout.VISIBLE);
            return false;
        }else{
            editipbalanzaRepeso.setText("");

        }


/*
        if(editipbalanzaRepeso.getText().toString().isEmpty()){ //chekamos que no este vacia
           // editipbalanzaRepeso.requestFocus();
            //editipbalanzaRepeso.setError("Este espacio es obligatorio");
            //scroollElementoFaltante(editipbalanzaRepeso);

            //layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
           // return false;

        }


        if(ediUbicacionBalanza.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediUbicacionBalanza.requestFocus();
            ediUbicacionBalanza.setError("Este espacio es obligatorio");
            scroollElementoFaltante(ediUbicacionBalanza);

            layoutContainerDatsProceso.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        return true;

    }

*/



private boolean creaAcMapDatosProcesoAndCheck(String informePertenece,String PuskEY){
 boolean isReady=true;

    TextInputEditText ediNombProd1;
    TextInputEditText ediNombProd2;
    TextInputEditText ediNombProd3;
    TextInputEditText ediNombProd4;
    TextInputEditText ediNombProd5;
    TextInputEditText ediNombProd6;
    TextInputEditText ediNombProd7;
    TextInputEditText ediNombProd8;

    TextInputEditText  ediTipoEmp1;
    TextInputEditText  ediTipoEmp2;
    TextInputEditText  ediTipoEmp3;
    TextInputEditText  ediTipoEmp4;
    TextInputEditText  ediTipoEmp5;
    TextInputEditText  ediTipoEmp6;
    TextInputEditText  ediTipoEmp7;
    TextInputEditText  ediTipoEmp8;

    TextInputEditText  ediCod1;
    TextInputEditText  ediCod2;
    TextInputEditText  ediCod3;
    TextInputEditText  ediCod4;
    TextInputEditText  ediCod5;
    TextInputEditText  ediCod6;
    TextInputEditText  ediCod7;
    TextInputEditText  ediCod8;

    TextInputEditText  edinCajas1;
    TextInputEditText  edinCajas2;
    TextInputEditText  edinCajas3;
    TextInputEditText  edinCajas4;
    TextInputEditText  edinCajas5;
    TextInputEditText  edinCajas6;
    TextInputEditText  edinCajas7;
    TextInputEditText  edinCajas8;

 ///vamos con findview id
     ediNombProd1=findViewById(R.id.ediNombProd1);
     ediNombProd2=findViewById(R.id.ediNombProd2);
     ediNombProd3=findViewById(R.id.ediNombProd3);
     ediNombProd4=findViewById(R.id.ediNombProd4);
     ediNombProd5=findViewById(R.id.ediNombProd5);
     ediNombProd6=findViewById(R.id.ediNombProd6);
     ediNombProd7=findViewById(R.id.ediNombProd7);
     ediNombProd8=findViewById(R.id.ediNombProd8);

      ediTipoEmp1=findViewById(R.id.ediTipoEmp1);
      ediTipoEmp2=findViewById(R.id.ediTipoEmp2);
      ediTipoEmp3=findViewById(R.id.ediTipoEmp3);
      ediTipoEmp4=findViewById(R.id.ediTipoEmp4);
      ediTipoEmp5=findViewById(R.id.ediTipoEmp5);
      ediTipoEmp6=findViewById(R.id.ediTipoEmp6);
      ediTipoEmp7=findViewById(R.id.ediTipoEmp7);
      ediTipoEmp8=findViewById(R.id.ediTipoEmp8);

      ediCod1=findViewById(R.id.ediCod1);
      ediCod2=findViewById(R.id.ediCod2);
      ediCod3=findViewById(R.id.ediCod3);
      ediCod4=findViewById(R.id.ediCod4);
      ediCod5=findViewById(R.id.ediCod5);
      ediCod6=findViewById(R.id.ediCod6);
      ediCod7=findViewById(R.id.ediCod7);
      ediCod8=findViewById(R.id.ediCod8);


      edinCajas1=findViewById(R.id.edinCajas1);
      edinCajas2=findViewById(R.id.edinCajas2);
      edinCajas3=findViewById(R.id.edinCajas3);
      edinCajas4=findViewById(R.id.edinCajas4);
      edinCajas5=findViewById(R.id.edinCajas5);
      edinCajas6=findViewById(R.id.edinCajas6);
      edinCajas7=findViewById(R.id.edinCajas7);
      edinCajas8=findViewById(R.id.edinCajas8);


  TextInputEditText [] arrayNmbresProd= {ediNombProd1, ediNombProd2, ediNombProd3, ediNombProd4, ediNombProd5, ediNombProd6, ediNombProd7, ediNombProd8

  };



    TextInputEditText [] arrayTiposEmpaque= {ediTipoEmp1, ediTipoEmp2, ediTipoEmp3, ediTipoEmp4, ediTipoEmp5, ediTipoEmp6, ediTipoEmp7, ediTipoEmp8

    };


    TextInputEditText [] arrayCodigos= {ediCod1, ediCod2, ediCod3, ediCod4, ediCod5, ediCod6, ediCod7, ediCod8

    };



    TextInputEditText [] arraynCajas= {edinCajas1, edinCajas2, edinCajas3, edinCajas4, edinCajas5, edinCajas6, edinCajas7, edinCajas8

    };


    Variables.mimapaDatosProcesMapCurrent= new HashMap<>();


    if(! Utils.checkIFaltaunDatoLlenoAndFocus(arrayNmbresProd,arrayTiposEmpaque,arrayCodigos,arraynCajas)){ //si ha llenado un  value de los 3 y el siguiente esta vacio...
        Log.i("caramba", "aqui es return");

        return false;
    }


    int numeroCajas=0;

    for(int indice=0; indice<arraynCajas.length; indice++){

        String KeyDataIdOfView=String.valueOf(arrayNmbresProd[indice].getId()) ;
        String tipoEmpaque=arrayTiposEmpaque[indice].getText().toString();
        String cod=arrayCodigos[indice].getText().toString();
        String nombreProd=arrayNmbresProd[indice].getText().toString();

        if(!arraynCajas[indice].getText().toString().trim().isEmpty()){

            numeroCajas=Integer.parseInt(arraynCajas[indice].getText().toString());

        }

/*
        if(indice==0 && tipoEmpaque.trim().isEmpty()  && cod.trim().isEmpty()  && nombreProd.trim().isEmpty()
                && arraynCajas[indice].getText().toString().trim().isEmpty()){

            tipoEmpaque="";
            cod="";
            numeroCajas=0;
            nombreProd="";

            //String InformePertenece;  //subimos el primero al menos..
            DatosDeProceso midatosProceso= new DatosDeProceso(nombreProd,tipoEmpaque,cod,numeroCajas,informePertenece,KeyDataIdOfView);
            midatosProceso.setKeyFirebase(PuskEY);
            Variables.mimapaDatosProcesMapCurrent.put(KeyDataIdOfView,midatosProceso);

        }

*/

         if(! tipoEmpaque.trim().isEmpty()  & !  cod.trim().isEmpty()  &  ! nombreProd.trim().isEmpty()
                & ! arraynCajas[indice].getText().toString().trim().isEmpty()  ) {  //si es diferente de 0

            //entonces subimos la data.....

            //String InformePertenece;
            DatosDeProceso midatosProceso= new DatosDeProceso(nombreProd,tipoEmpaque,cod,numeroCajas,informePertenece,KeyDataIdOfView);
            midatosProceso.setKeyFirebase(PuskEY);

            Variables.mimapaDatosProcesMapCurrent.put(KeyDataIdOfView,midatosProceso);

            Log.i("saer","hay un data");

        }

        if( Variables.mimapaDatosProcesMapCurrent.size()==0){

            return false;

        }


    }



    return isReady;
  }







private void createObjcInformeAndUpload(){



//aplicamos la logica PARA CREAR UN NUEVO INFORME
//SI LA DATA ES OPCIONAL EN EL FIELD LE AGREGAMOS UN "";en editex comprobacion le agragmos para que el texto no sea nulo

    ContenedoresEnAcopio informe = new ContenedoresEnAcopio(UNIQUE_ID_iNFORME,ediFechaInicio.getText().toString(),fechDetermino.getText().toString()
            ,ediExpSolicitante.getText().toString(),
            ediExpProcesada.getText().toString(),ediPuerto.getText().toString(),ediZona.getText().toString(),ediMarca.getText().toString(),
    ediHoraInicio.getText().toString(),ediHoraTermino.getText().toString(),ediNguiaRemision.getText().toString(),ediNtargetaEmbarque.getText().toString()
            ,ediDestino.getText().toString(),ediVapor.getText().toString(),ediNumContenedor.getText().toString(),ediHoraLLegadaContenedor.getText().toString()
            ,ediHoraSalidaContenedor.getText().toString(),ediAgenciaNav.getText().toString(),ediSelloPlasticoNaviera.getText().toString()
    ,stikVentolerExterna.getText().toString(),ediNumSerieFunda.getText().toString(),ediCableRastreoLlegada.getText().toString(),ediBooking.getText().toString(),
            ediMaxGross.getText().toString(),ediTare.getText().toString(),ediOtherSellos.getText().toString(),ediTermofrafo1.getText().toString()
            ,ediTermofrafo2.getText().toString(),
            ediCandadoqsercon.getText().toString(),ediSelloNaviera.getText().toString(),ediCableNaviera.getText().toString(),ediSelloPlastico.getText().toString()
            ,ediCandadoBotella.getText().toString(),ediCableExportadora.getText().toString(),ediSelloAdesivoexpor.getText().toString(),esiSelloAdhNaviera.getText().toString()
            ,ediOtherSellos.getText().toString(),ediCompaniaTransporte.getText().toString(),ediNombreChofer.getText().toString(),ediCedula.getText().toString()
            ,ediCelular.getText().toString(),ediPLaca.getText().toString(),ediMarcaCabezal.getText().toString(),ediColorCabezal.getText().toString(),
            Integer.parseInt(ediCjasProcesDespacha.getText().toString()), ediInspectorAcopio.getText().toString(), Integer.parseInt(ediCedulaI.getText().toString()),
            "",Integer.parseInt(ediSemana.getText().toString()),ediUbicacion1.getText().toString(),
            ediUbicacion2.getText().toString(),ediHoraEncendido1.getText().toString(),ediHoraEncendido2.getText().toString());





    //agr5egamos la data finalemente

    informe.setDatosProcesoContenAcopioKEYFather(Variables.CurrenReportContensEnACp.getDatosProcesoContenAcopioKEYFather());
    informe.setKeyFirebase(Variables.CurrenReportContensEnACp.getKeyFirebase());
    informe.setSimpleDataFormat(Variables.CurrenReportContensEnACp.getSimpleDataFormat());
    informe.setFechaUploadMilliseconds(Variables.CurrenReportContensEnACp.getFechaUploadMilliseconds());


    informe.setNombreRevisa(ediNombreRevisa.getText().toString());
    informe.setCodigonRevisa(ediCodigoRevisa.getText().toString());


    RealtimeDB.initDatabasesReferenceImagesData(); //inicilizamos la base de datos

    uploadImagesInStorageAndInfoPICS(); //subimos laS IMAGENES EN STORAGE Y LA  data de las imagenes EN R_TDBASE


    RealtimeDB.updateInformContenresAcopio(informe,PreviewsFormDatSContersEnAc.this);


 ///   Toast.makeText(context, "Informe Actualizado", Toast.LENGTH_SHORT).show();

   // finish();

}

    private void eventoBtnclicklistenerDelete(RecyclerViewAdapter adapter) {

        adapter.setOnItemClickListener(new RecyclerViewAdapter.ClickListener() {


            @Override
            public void onItemClick(int position, View v) {  //este para eminar
                //  Variables.currentCuponObjectGlob =listGiftCards.get(position);

                Log.i("elfile","elsize de mapa es "+ImagenReport.hashMapImagesData.size());

               ///elimnar el hasmap
               //vamos a ver el tipo del objeto removivo
                if( ImagenReport.hashMapImagesData.containsKey(v.getTag().toString())){

                    Variables.typeoFdeleteImg=  ImagenReport.hashMapImagesData.get(v.getTag()).getTipoImagenCategory();

                    Log.i("camisax","el size antes de eliminar es "+ ImagenReport.hashMapImagesData.size());
                    Variables.listImagesToDelete.add(v.getTag().toString());//agregamos ea imagen para borrarla


                    ImagenReport.hashMapImagesData.remove(v.getTag().toString());
                    Utils.saveMapImagesDataPreferences(ImagenReport.hashMapImagesData, PreviewsFormDatSContersEnAc.this);


                    Log.i("camisax","el size despues de eliminar es "+ ImagenReport.hashMapImagesData.size());

                    showImagesPicShotOrSelectUpdateView(true);




                }



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


        if(  !Variables.hashMapImagesStart.keySet().equals(ImagenReport.hashMapImagesData.keySet())){ //si no son iguales

            Log.i("imagheddd", "alguno o toos son diferentes images llamaos metodo filtra");

            StorageData.counTbucle = 0; //resetemoa esta variable que sera indice en la reflexion
            ArrayList<ImagenReport> list2 = Utils.mapToArrayList(Utils.creaHahmapNoDuplicado());

            StorageData.uploaddata(list2);

          ///  HashMap<String , ImagenReport>mihasmap= Utils.creaHahmapNoDuplicado();


        }

        else{
            Log.i("elfile","el size de hashMapImagesStart es  "+ Variables.hashMapImagesStart.size()+" y el size de hashMapImagesData es" +ImagenReport.hashMapImagesData.size());


            Log.i("elfile","son iguales las imagenes");

        }

        if (Utils.objsIdsDecripcionImgsMOreDescripc.size() > 0) {

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
           // Uri uri = Uri.parse("package:" + BuildConfig.APPLICATION_ID);
          // startActivity(new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, uri));
        }









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
        if (requestCode == CODE_TWO_PERMISIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Log.i("codereisterxcc","permisos concedidos") ;
                takePickCamera();

            }

            else{
                Log.i("codereisterxcc","no se concedieron") ;

            }


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

        LinearLayout layoutContainerSeccion1=findViewById(R.id.layoutContainerSeccion7);
        if(ediFechaInicio.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediFechaInicio.requestFocus();
            ediFechaInicio.setError("Este espacio es obligatorio");
            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;
            //obtiene el padre del padre

        }

         if (fechDetermino.getText().toString().isEmpty()){ //chekamos que no este vacia
             fechDetermino.requestFocus();
             fechDetermino.setError("Debe selecionar una fecha");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



         if(ediExpSolicitante.getText().toString().isEmpty()){ //chekamos que no este vacia
             ediExpSolicitante.requestFocus();
             ediExpSolicitante.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }






         if(ediExpProcesada.getText().toString().isEmpty()){ //chekamos que no este vacia
             ediExpProcesada.requestFocus();
             ediExpProcesada.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }






         if(ediMarca.getText().toString().isEmpty()){ //chekamos que no este vacia
             ediMarca.requestFocus();
             ediMarca.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediPuerto.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediPuerto.requestFocus();
            ediPuerto.setError("Este espacio es obligatorio");
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






         if(ediNtargetaEmbarque.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediNtargetaEmbarque.requestFocus();
            ediNtargetaEmbarque.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }








        return true;
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


        if(ediNumContenedor.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediNumContenedor.requestFocus();
            ediNumContenedor.setError("Este espacio es obligatorio");

            layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediVapor.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediVapor.requestFocus();
            ediVapor.setError("Este espacio es obligatorio");

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



        if(ediAgenciaNav.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediAgenciaNav.requestFocus();
            ediAgenciaNav.setError("Este espacio es obligatorio");

            layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        //chekamos que al menos exista una imagen...



return  true;

    }




    private boolean checkDataSellosLlegadaIsLleno(){
        LinearLayout layoutContainerSeccion4=findViewById(R.id.layoutContainerSeccion4);



        if(ediSemana.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediSemana.requestFocus();
            ediSemana.setError("Este espacio es obligatorio");

            layoutContainerSeccion4.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

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









        return true;


    }


    private boolean checkSellosInstaladosIsLleno(){

        LinearLayout layoutContainerSeccion5=findViewById(R.id.layoutContainerSeccion5);

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








      return true;

    }





    private boolean checkDataCalibFrutaCalEnfn(){

        //le decimos que esta todo bien y omitiremos estos datos....
        return true;
    }





//upload data...

//descragamos el ultimo
//Si hay un formulario ... que no se envio aun.....estado subido..
//si hay un formulario obtenerlo..
    //una propiedad que diga si ya lo subio...
    ///el primer valor del map conttendra esa propiedad...
private TextInputEditText[] creaArryOfTextInputEditText() {

    TextInputEditText [] arrayEditex = {
            ediZona,
            ediHoraInicio,
            ediHoraTermino,
            ediHoraLLegadaContenedor,
            ediHoraSalidaContenedor,
            ediNguiaRemision,
            ediNtargetaEmbarque,
            ediDestino,
            ediVapor,
            ediFotoContenedor,
            ediCompaniaTransporte,
            ediNombreChofer,
            ediCedula,
            ediCelular,
            ediPLaca,
            ediMarcaCabezal,
            ediColorCabezal,

            ediTare,
            ediBooking,
            ediMaxGross,
            ediNumSerieFunda,
            stikVentolerExterna,
            ediCableRastreoLlegada,
            ediSelloPlasticoNaviera,
            ediOtroSellosLlegada,

            ediTermofrafo1,
            ediHoraEncendido1,
            ediUbicacion1,
            ediRuma1,
            ediTermofrafo2,
            ediHoraEncendido2,
            ediUbicacion2,
            ediRuma2,
            ediCandadoqsercon,
            ediSelloNaviera,
            ediCableNaviera,
            ediSelloPlastico,
            ediCandadoBotella,
            ediCableExportadora,
            ediSelloAdesivoexpor,
            esiSelloAdhNaviera,
            ediOtherSellos,




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
        if(!copiamosDatax) {
            // Variables.hashMapImagesStart =ImagenReport.hashMapImagesData;

            //CREAMOS UNA COPIA USANDO UN BUCLE

            Variables.hashMapImagesStart=new HashMap<String, ImagenReport>();


            for (Map.Entry<String, ImagenReport> entry : ImagenReport.hashMapImagesData.entrySet()) {
                String key = entry.getKey();
                ImagenReport value = entry.getValue();

                Variables.hashMapImagesStart.put(key,value);
                // ...
            }

            copiamosDatax =true;




        }



    }

    void addImagesInRecyclerviews(ArrayList<ImagenReport>listImagenReports){

        //agregamos data al map


        RecyclerView recyclerView=null;


        switch(currentTypeImage){

            case Variables.FOTO_LLEGADA_CONTENEDOR:
                recyclerView= findViewById(R.id.recyclerFotollegadaContenedor);
                break;

            case Variables.FOTO_SELLO_LLEGADA:
                recyclerView= findViewById(R.id.recyclerFotoSellosLlegada);
                break;

            case Variables.FOTO_PUERTA_ABIERTA_DEL_CONTENENEDOR:
                recyclerView= findViewById(R.id.recyclerFotoPuertaAbrContedor);
                break;

            case Variables.FOTO_PALLETS:
                recyclerView= findViewById(R.id.recyclerFotoPallets);
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


    }




    private void calibracionFutaCalendarioEnfunde(){

        TextInputEditText ediCandadoqsercon;


    }





    private void dowLOADAndCallSETdatosAcopioprocesO(String fathherNode){

        Log.i("COMENMZAR","el FATHER NODE  es "+ fathherNode);

        ValueEventListener seenListener;

        seenListener = RealtimeDB.rootDatabaseReference.child("Informes").child("datosProcesoContenAcopio").child(fathherNode).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Variables.mimapaDatosProcesMapCurrent=new HashMap<>();


                for (DataSnapshot dss : dataSnapshot.getChildren()) {
                    DatosDeProceso datosProceso = dss.getValue(DatosDeProceso.class);

                    if (datosProceso!=null) {///

                        Variables.mimapaDatosProcesMapCurrent.put(datosProceso.getKey1(),datosProceso);

                        Log.i("COMENMZAR","el size del mapa es "+ Variables.mimapaDatosProcesMapCurrent.size());

                    //    Map<String, Object> hashMap = new HashMap<>();
                    //    hashMap.put("isseen", new DatosDeProceso("",1,"","",2,""));
                     //   dss.getRef().updateChildren(hashMap);




                    }
                }

                setDatosProcesODataInViews(Variables.mimapaDatosProcesMapCurrent);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("misadhd","el error es "+ databaseError.getMessage());



            }
        });




    }

    private void setDatosProcesODataInViews(HashMap<String, DatosDeProceso> mimapaDatosProcesMapCurrent) {


        TextInputEditText ediNombProd1;
        TextInputEditText ediNombProd2;
        TextInputEditText ediNombProd3;
        TextInputEditText ediNombProd4;
        TextInputEditText ediNombProd5;
        TextInputEditText ediNombProd6;
        TextInputEditText ediNombProd7;
        TextInputEditText ediNombProd8;

        TextInputEditText  ediTipoEmp1;
        TextInputEditText  ediTipoEmp2;
        TextInputEditText  ediTipoEmp3;
        TextInputEditText  ediTipoEmp4;
        TextInputEditText  ediTipoEmp5;
        TextInputEditText  ediTipoEmp6;
        TextInputEditText  ediTipoEmp7;
        TextInputEditText  ediTipoEmp8;

        TextInputEditText  ediCod1;
        TextInputEditText  ediCod2;
        TextInputEditText  ediCod3;
        TextInputEditText  ediCod4;
        TextInputEditText  ediCod5;
        TextInputEditText  ediCod6;
        TextInputEditText  ediCod7;
        TextInputEditText  ediCod8;


        TextInputEditText  edinCajas1;
        TextInputEditText  edinCajas2;
        TextInputEditText  edinCajas3;
        TextInputEditText  edinCajas4;
        TextInputEditText  edinCajas5;
        TextInputEditText  edinCajas6;
        TextInputEditText  edinCajas7;
        TextInputEditText  edinCajas8;




        ///vamos con findview id
        ediNombProd1=findViewById(R.id.ediNombProd1);
        ediNombProd2=findViewById(R.id.ediNombProd2);
        ediNombProd3=findViewById(R.id.ediNombProd3);
        ediNombProd4=findViewById(R.id.ediNombProd4);
        ediNombProd5=findViewById(R.id.ediNombProd5);
        ediNombProd6=findViewById(R.id.ediNombProd6);
        ediNombProd7=findViewById(R.id.ediNombProd7);
        ediNombProd8=findViewById(R.id.ediNombProd8);

        ediTipoEmp1=findViewById(R.id.ediTipoEmp1);
        ediTipoEmp2=findViewById(R.id.ediTipoEmp2);
        ediTipoEmp3=findViewById(R.id.ediTipoEmp3);
        ediTipoEmp4=findViewById(R.id.ediTipoEmp4);
        ediTipoEmp5=findViewById(R.id.ediTipoEmp5);
        ediTipoEmp6=findViewById(R.id.ediTipoEmp6);
        ediTipoEmp7=findViewById(R.id.ediTipoEmp7);
        ediTipoEmp8=findViewById(R.id.ediTipoEmp8);

        ediCod1=findViewById(R.id.ediCod1);
        ediCod2=findViewById(R.id.ediCod2);
        ediCod3=findViewById(R.id.ediCod3);
        ediCod4=findViewById(R.id.ediCod4);
        ediCod5=findViewById(R.id.ediCod5);
        ediCod6=findViewById(R.id.ediCod6);
        ediCod7=findViewById(R.id.ediCod7);
        ediCod8=findViewById(R.id.ediCod8);


        edinCajas1=findViewById(R.id.edinCajas1);
        edinCajas2=findViewById(R.id.edinCajas2);
        edinCajas3=findViewById(R.id.edinCajas3);
        edinCajas4=findViewById(R.id.edinCajas4);
        edinCajas5=findViewById(R.id.edinCajas5);
        edinCajas6=findViewById(R.id.edinCajas6);
        edinCajas7=findViewById(R.id.edinCajas7);
        edinCajas8=findViewById(R.id.edinCajas8);


        TextInputEditText [] arrayNmbresProd= {ediNombProd1, ediNombProd2, ediNombProd3, ediNombProd4, ediNombProd5, ediNombProd6, ediNombProd7, ediNombProd8

        };



        TextInputEditText [] arrayTiposEmpaque= {ediTipoEmp1, ediTipoEmp2, ediTipoEmp3, ediTipoEmp4, ediTipoEmp5, ediTipoEmp6, ediTipoEmp7, ediTipoEmp8

        };


        TextInputEditText [] arrayCodigos= {ediCod1, ediCod2, ediCod3, ediCod4, ediCod5, ediCod6, ediCod7, ediCod8

        };



        TextInputEditText [] arraynCajas= {edinCajas1, edinCajas2, edinCajas3, edinCajas4, edinCajas5, edinCajas6, edinCajas7, edinCajas8

        };







        for(int indice=0; indice<arrayNmbresProd.length; indice++){
             //recoreemos el array arrayNmbresProd que contien los keys como ids..
            String keySearch=String.valueOf(arrayNmbresProd[indice].getId());

            if(mimapaDatosProcesMapCurrent.containsKey(keySearch)){//si contiene esta key


                        DatosDeProceso currenObjDaProc= mimapaDatosProcesMapCurrent.get(keySearch);

                      Log.i("saminamas","valuexxxc"+currenObjDaProc.getNumeroCajas());
                Log.i("saminamas","valuexxxc"+currenObjDaProc.getCod());
                Log.i("saminamas","valuexxxc "+currenObjDaProc.getTipoEmpaque());
                Log.i("saminamas","valuexxxc NOMBRE PRODUCTO"+currenObjDaProc.getNombreProd());

                arrayNmbresProd [indice].setText(currenObjDaProc.getNombreProd());
                        arrayTiposEmpaque [indice].setText(currenObjDaProc.getTipoEmpaque());
                        arraynCajas [indice].setText(String.valueOf(currenObjDaProc.getNumeroCajas()));
                           arrayCodigos[indice].setText(currenObjDaProc.getCod());

            }


        }


        if(ediNombProd1.getText().toString().trim().isEmpty() || ediNombProd1.getText().toString().isEmpty() ) {//si el primer valor es empty noo lo pongAS
            ediNombProd1.getText().clear();
            ediTipoEmp1.getText().clear();
            ediCod1.getText().clear();
            edinCajas1.getText().clear();

        }



    }





    private void checkModeVisualitY(){

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            isModEdicionFields = extras.getBoolean(Variables.KEYEXTRA_CONTEN_EN_ACP);

            Log.i("extra","el modo es "+isModEdicionFields);
            //The key argument here must match that used in the other activity
        }


        if(isModEdicionFields){
            TextView txtModeAdviser=findViewById(R.id.txtModeAdviser2);
            activateModeEdit();
            activateModeEditMoreViews();
            txtModeAdviser.setText("Modo Edicion ");

            Log.i("moder","es modoedicion, de");

        }


        else{
            activateModePreview();
            activateModePreviewMoreViews();
           fab.setImageResource(R.drawable.ic_baseline_edit_24aa);
            Log.i("moder","es modo preview");

        }


        Variables.modoRecicler=Variables.DOWLOAD_IMAGES;
        //AGREGMOS LA DATA EN LOS FILEDS
      //  addDataEnFields(Variables.CurrenReportPart1,Variables.CurrenReportPart2);

       // addDataENfiledsoTHERviews(Variables.CurrenReportPart1,Variables.CurrenReportPart2);




        //inicializamos STORAGE..
        StorageData.initStorageReference();
     //   dowloadImagesDataReport(Variables.CurrenReportPart1.getUniqueIDinformePart2());

      //  dowLoadProducsPostC(Variables.CurrenReportPart1.getUniqueIDinformePart2());



    }
    private void activateModeEdit() {
        Variables.isClickable=true;
        //Creamos un array de todos los objetos..

        //vamos a probar ocn varios

        View [] misViewsArray={ediCjasProcesDespacha,ediFechaInicio,    fechDetermino,    ediExpSolicitante,    ediExpProcesada,    ediMarca,    ediPuerto,    ediAgenciaNav,
                ediInspectorAcopio,    ediCedulaI,    ediZona,    ediHoraInicio,    ediHoraTermino,    ediHoraLLegadaContenedor,
                ediHoraSalidaContenedor,    ediNguiaRemision,    ediNtargetaEmbarque,        ediDestino,    ediVapor,
                ediFotoContenedor,    ediNumContenedor,    ediCompaniaTransporte,    ediNombreChofer,    ediCedula,
                ediCelular,    ediPLaca,    ediMarcaCabezal,    ediColorCabezal,        ediTare,    ediBooking,
                ediMaxGross,    ediNumSerieFunda,    stikVentolerExterna,    ediCableRastreoLlegada,    ediSelloPlasticoNaviera,    ediOtroSellosLlegada,
                       ediTermofrafo1,    ediHoraEncendido1,    ediUbicacion1,    ediRuma1,    ediTermofrafo2,
                ediHoraEncendido2,    ediUbicacion2,    ediRuma2,    ediCandadoqsercon,    ediSelloNaviera,    ediCableNaviera,    ediSelloPlastico,
                ediCandadoBotella,    ediCableExportadora,    ediSelloAdesivoexpor,    esiSelloAdhNaviera,    ediOtherSellos,
                spinnerSelectZona,     spFumigaCorL1 ,     spTipoBoquilla
                ,ediSemana

    };




        HelperEditAndPreviewmode.activateViewsByTypeView(misViewsArray);


        //Buttons
      //  Button  btnCheck=findViewById(R.id.btnCheck);
      ///  activateViewsByTypeView( btnCheck);

    }



    private void activateModeEditMoreViews() {
        TextInputEditText     ediNombProd1=findViewById(R.id.ediNombProd1);
        TextInputEditText     ediNombProd2=findViewById(R.id.ediNombProd2);
        TextInputEditText     ediNombProd3=findViewById(R.id.ediNombProd3);
        TextInputEditText     ediNombProd4=findViewById(R.id.ediNombProd4);
        TextInputEditText     ediNombProd5=findViewById(R.id.ediNombProd5);
        TextInputEditText     ediNombProd6=findViewById(R.id.ediNombProd6);
        TextInputEditText     ediNombProd7=findViewById(R.id.ediNombProd7);
        TextInputEditText     ediNombProd8=findViewById(R.id.ediNombProd8);
        TextInputEditText      ediTipoEmp1=findViewById(R.id.ediTipoEmp1);
        TextInputEditText      ediTipoEmp2=findViewById(R.id.ediTipoEmp2);
        TextInputEditText      ediTipoEmp3=findViewById(R.id.ediTipoEmp3);
        TextInputEditText      ediTipoEmp4=findViewById(R.id.ediTipoEmp4);
        TextInputEditText      ediTipoEmp5=findViewById(R.id.ediTipoEmp5);
        TextInputEditText      ediTipoEmp6=findViewById(R.id.ediTipoEmp6);
        TextInputEditText      ediTipoEmp7=findViewById(R.id.ediTipoEmp7);
        TextInputEditText      ediTipoEmp8=findViewById(R.id.ediTipoEmp8);
        TextInputEditText      ediCod1=findViewById(R.id.ediCod1);
        TextInputEditText      ediCod2=findViewById(R.id.ediCod2);
        TextInputEditText      ediCod3=findViewById(R.id.ediCod3);
        TextInputEditText      ediCod4=findViewById(R.id.ediCod4);
        TextInputEditText      ediCod5=findViewById(R.id.ediCod5);
        TextInputEditText      ediCod6=findViewById(R.id.ediCod6);
        TextInputEditText      ediCod7=findViewById(R.id.ediCod7);
        TextInputEditText      ediCod8=findViewById(R.id.ediCod8);
        TextInputEditText      edinCajas1=findViewById(R.id.edinCajas1);
        TextInputEditText      edinCajas2=findViewById(R.id.edinCajas2);
        TextInputEditText      edinCajas3=findViewById(R.id.edinCajas3);
        TextInputEditText      edinCajas4=findViewById(R.id.edinCajas4);
        TextInputEditText      edinCajas5=findViewById(R.id.edinCajas5);
        TextInputEditText      edinCajas6=findViewById(R.id.edinCajas6);
        TextInputEditText      edinCajas7=findViewById(R.id.edinCajas7);
        TextInputEditText      edinCajas8=findViewById(R.id.edinCajas8);


        Variables.isClickable=true;
        //Creamos un array de todos los objetos..

        //vamos a probar ocn varios

        View [] misViewsArray={
                ediNombProd1,
                ediNombProd2,
                ediNombProd3,
                ediNombProd4,
                ediNombProd5,
                ediNombProd6,
                ediNombProd7,
                ediNombProd8,

                ediTipoEmp1,
                ediTipoEmp2,
                ediTipoEmp3,
                ediTipoEmp4,
                ediTipoEmp5,
                ediTipoEmp6,
                ediTipoEmp7,
                ediTipoEmp8,

                ediCod1,
                ediCod2,
                ediCod3,
                ediCod4,
                ediCod5,
                ediCod6,
                ediCod7,
                ediCod8,
                edinCajas1,
                edinCajas2,
                edinCajas3,
                edinCajas4,
                edinCajas5,
                edinCajas6,
                edinCajas7,
                edinCajas8,




        };




        HelperEditAndPreviewmode.activateViewsByTypeView(misViewsArray);


        //Buttons
        //  Button  btnCheck=findViewById(R.id.btnCheck);
        ///  activateViewsByTypeView( btnCheck);

    }

    private void activateModePreviewMoreViews() {
        TextInputEditText     ediNombProd1=findViewById(R.id.ediNombProd1);
        TextInputEditText     ediNombProd2=findViewById(R.id.ediNombProd2);
        TextInputEditText     ediNombProd3=findViewById(R.id.ediNombProd3);
        TextInputEditText     ediNombProd4=findViewById(R.id.ediNombProd4);
        TextInputEditText     ediNombProd5=findViewById(R.id.ediNombProd5);
        TextInputEditText     ediNombProd6=findViewById(R.id.ediNombProd6);
        TextInputEditText     ediNombProd7=findViewById(R.id.ediNombProd7);
        TextInputEditText     ediNombProd8=findViewById(R.id.ediNombProd8);
        TextInputEditText      ediTipoEmp1=findViewById(R.id.ediTipoEmp1);
        TextInputEditText      ediTipoEmp2=findViewById(R.id.ediTipoEmp2);
        TextInputEditText      ediTipoEmp3=findViewById(R.id.ediTipoEmp3);
        TextInputEditText      ediTipoEmp4=findViewById(R.id.ediTipoEmp4);
        TextInputEditText      ediTipoEmp5=findViewById(R.id.ediTipoEmp5);
        TextInputEditText      ediTipoEmp6=findViewById(R.id.ediTipoEmp6);
        TextInputEditText      ediTipoEmp7=findViewById(R.id.ediTipoEmp7);
        TextInputEditText      ediTipoEmp8=findViewById(R.id.ediTipoEmp8);
        TextInputEditText      ediCod1=findViewById(R.id.ediCod1);
        TextInputEditText      ediCod2=findViewById(R.id.ediCod2);
        TextInputEditText      ediCod3=findViewById(R.id.ediCod3);
        TextInputEditText      ediCod4=findViewById(R.id.ediCod4);
        TextInputEditText      ediCod5=findViewById(R.id.ediCod5);
        TextInputEditText      ediCod6=findViewById(R.id.ediCod6);
        TextInputEditText      ediCod7=findViewById(R.id.ediCod7);
        TextInputEditText      ediCod8=findViewById(R.id.ediCod8);
        TextInputEditText      edinCajas1=findViewById(R.id.edinCajas1);
        TextInputEditText      edinCajas2=findViewById(R.id.edinCajas2);
        TextInputEditText      edinCajas3=findViewById(R.id.edinCajas3);
        TextInputEditText      edinCajas4=findViewById(R.id.edinCajas4);
        TextInputEditText      edinCajas5=findViewById(R.id.edinCajas5);
        TextInputEditText      edinCajas6=findViewById(R.id.edinCajas6);
        TextInputEditText      edinCajas7=findViewById(R.id.edinCajas7);
        TextInputEditText      edinCajas8=findViewById(R.id.edinCajas8);


        Variables.isClickable=false;
        //Creamos un array de todos los objetos..

        //vamos a probar ocn varios

        View [] misViewsArray={
                ediNombProd1,
                ediNombProd2, ediNombProd3, ediNombProd4, ediNombProd5, ediNombProd6, ediNombProd7, ediNombProd8,
                ediTipoEmp1, ediTipoEmp2, ediTipoEmp3, ediTipoEmp4, ediTipoEmp5, ediTipoEmp6, ediTipoEmp7,
                ediTipoEmp8, ediCod1, ediCod2, ediCod3, ediCod4, ediCod5, ediCod6, ediCod7, ediCod8, edinCajas1,
                edinCajas2, edinCajas3, edinCajas4, edinCajas5, edinCajas6, edinCajas7, edinCajas8,




        };




        HelperEditAndPreviewmode.diseableViewsByTipe(misViewsArray);


        //Buttons
        //  Button  btnCheck=findViewById(R.id.btnCheck);
        ///  activateViewsByTypeView( btnCheck);

    }




    private void activateModePreview() {
        Variables.isClickable=false;
        //Creamos un array de todos los objetos..

        //vamos a probar ocn varios

        View [] misViewsArray={ediCjasProcesDespacha,ediFechaInicio,    fechDetermino,    ediExpSolicitante,    ediExpProcesada,    ediMarca,    ediPuerto,    ediAgenciaNav,
                ediInspectorAcopio,    ediCedulaI,    ediZona,    ediHoraInicio,    ediHoraTermino,    ediHoraLLegadaContenedor,
                ediHoraSalidaContenedor,    ediNguiaRemision,    ediNtargetaEmbarque,        ediDestino,    ediVapor,
                ediFotoContenedor,       ediNumContenedor,    ediCompaniaTransporte,    ediNombreChofer,    ediCedula,
                ediCelular,    ediPLaca,    ediMarcaCabezal,    ediColorCabezal,        ediTare,    ediBooking,
                ediMaxGross,    ediNumSerieFunda,    stikVentolerExterna,    ediCableRastreoLlegada,    ediSelloPlasticoNaviera,    ediOtroSellosLlegada,
                        ediTermofrafo1,    ediHoraEncendido1,    ediUbicacion1,    ediRuma1,    ediTermofrafo2,
                ediHoraEncendido2,    ediUbicacion2,    ediRuma2,    ediCandadoqsercon,    ediSelloNaviera,    ediCableNaviera,    ediSelloPlastico,
                ediCandadoBotella,    ediCableExportadora,    ediSelloAdesivoexpor,    esiSelloAdhNaviera,    ediOtherSellos,
                spinnerSelectZona,     spFumigaCorL1 ,     spTipoBoquilla ,ediSemana};


        HelperEditAndPreviewmode.diseableViewsByTipe(misViewsArray);


    }

    //




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
    private  void addDataEnFields(ContenedoresEnAcopio currentInform)  {


        ediNombreRevisa.setText(currentInform.getNombreRevisa());
        ediCodigoRevisa.setText(currentInform.getCodigonRevisa());

        //usamos los 2 objetos para establecer esta data..

     //   Log.i("jamisama","la semana es "+currentInform.getSemana());
        ediExpSolicitante.setText(currentInform.getExportSolicitante());
        ediExpProcesada.setText(currentInform.getExportProcesada());
                ediMarca.setText(currentInform.getMarca());
        ediPuerto.setText(currentInform.getPuerto());
                ediNumContenedor.setText(currentInform.getNumContenedor());
        ediAgenciaNav.setText(currentInform.getAgenciaNaviera());
        ediFechaInicio.setText(currentInform.getFechaInicio());
       // Format formatter = new SimpleDateFormat("dd-MM-yyyy");
       // String fechaString = formatter.format(currentInform.get);
        fechDetermino.setText(currentInform.getFechadeTermino());
        ediZona.setText(currentInform.getZona());
        ediHoraInicio.setText(currentInform.getHoraInicio());

        ediHoraTermino.setText(currentInform.getHoraDetermino());
        ediHoraLLegadaContenedor.setText(currentInform.getHoraDeLlegada());

        ediHoraSalidaContenedor.setText(currentInform.getHoraDeSalida());
        ediNguiaRemision.setText(currentInform.getGuiaDeRemision());
        ediNtargetaEmbarque.setText(currentInform.getTarjaDeEmbarque());

        /*faltan los prouctos postcosecha agregamos usando un for**/


        ediDestino.setText(currentInform.getDestino());
        ediVapor.setText(currentInform.getVapor());

        ediCompaniaTransporte.setText(currentInform.getCompaniaTranportista());
        ediNombreChofer.setText(currentInform.getNombredeChofer());
        ediCedula.setText(String.valueOf(currentInform.getCedula()));
        ediCelular.setText(String.valueOf(currentInform.getCelular()));
        ediPLaca.setText(currentInform.getPlaca());
        ediMarcaCabezal.setText(currentInform.getMarcaCabezal());
        ediColorCabezal.setText(currentInform.getColorCabezal());
        ediTare.setText(currentInform.getTare());
        ediSemana.setText(String.valueOf(currentInform.getSemanaNum()));
        ediBooking.setText(currentInform.getBooking());
        ediMaxGross.setText(currentInform.getMaxGross());
        ediNumSerieFunda.setText(currentInform.getNumSerieFunda());
        stikVentolerExterna.setText(currentInform.getStickerDeVentolExternn1());
        ediCableRastreoLlegada.setText(currentInform.getCableRastreoLlegada());
        ediSelloPlasticoNaviera.setText(currentInform.getSellosPlasticoNaviera());

        Log.i("termografo","el termografo 1 es "+currentInform.getTermografoN1());

        ediTermofrafo1.setText(currentInform.getTermografoN1());
       ediHoraEncendido1.setText(currentInform.getTermografo1HoraEncendido());

        ediUbicacion1.setText(currentInform.getUbicacionTermografo1());
        ediUbicacion2.setText(currentInform.getUbicacionTermografo2());

        ediTermofrafo2.setText(currentInform.getTermogragoN2());
       ediHoraEncendido2.setText(currentInform.getTermografo2HoraEncendido());
       // ediRuma2.setText(currentInform.getRuma);
        ediCandadoqsercon.setText(currentInform.getCandadoDeQsercon());
        ediSelloNaviera.setText(currentInform.getSelloDeNaviera());
        ediCableNaviera.setText(currentInform.getCableDeNaviera());
        ediSelloPlastico.setText(currentInform.getSelloPlastico());
        ediCandadoBotella.setText(currentInform.getCandadodeBotella());
        ediCableExportadora.setText(currentInform.getCableExportadora());
        ediSelloAdesivoexpor.setText(currentInform.getSelloAdhesivoExportadora());
        esiSelloAdhNaviera.setText(currentInform.getSelloAdhesivoNaviera()); //ESTE PARECE OTRO
        ediOtherSellos.setText(currentInform.getOtrosSellos());

        selectValue(spinnerSelectZona,Variables.CurrenReportContensEnACp.getZona()) ;


        ediCjasProcesDespacha.setText(String.valueOf(currentInform.getCajasProcesadasDespachadas()));
        ediInspectorAcopio.setText(currentInform.getInspectorAcopio());
        ediCedulaI.setText(String.valueOf(currentInform.getCedulaIdenti()));




        // selectValue(spFumigaCorL1,Variables.CurrenReportContensEnACp.fum) ;
        //   selectValue(spTipoBoquilla,Variables.CurrenReportContensEnACp.tipo) ;

    }



    void dowloadImagesDataReport(String reportUNIQUEidtoSEARCH){ //DESCRAGAMOS EL SEGUNDO

        Log.i("mispiggi","el reportunique id es "+reportUNIQUEidtoSEARCH);


        // DatabaseReference midatabase=rootDatabaseReference.child("Informes").child("listInformes");
        Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("ImagesData").orderByChild("idReportePerteence").equalTo(reportUNIQUEidtoSEARCH);
        Log.i("mispiggi","se llamo dowload images cc");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<ImagenReport>listImagenData=new ArrayList<>();



                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    ImagenReport imagenReport=ds.getValue(ImagenReport.class);
                    listImagenData.add(imagenReport);

                }

                Log.i("mispiggi","el size es "+listImagenData.size());

                Variables.listImagenDataGlobalCurrentReport =listImagenData;

                //dowloadAllImages2AddCallRecicler(Variables.listImagenData);

                Log.i("mispiggi","se llamo a: addInfotomap");


                createlistsForReciclerviewsImages(Variables.listImagenDataGlobalCurrentReport);

                addInfotomap(Variables.listImagenDataGlobalCurrentReport);



                //este metodo lo llamaremos ahora
                //al objeto imagen report le agregaremos una propiedad llamada bitmap...o crearemos un map de bitmaps que usraemos para cargarlos desde el
                //el adpater del recicler view y asiu no alteramos el objeto imagereport...solo que ya no descragremos la imagen nuevamente...


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("mispiggi","el error es "+error.getMessage());

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
                        Log.i("mispiggi","el contador iteradopr "+ contadorIterador);




                        if(contadorIterador == miLisAllImages.size() ) {

                            createlistsForReciclerviewsImages(Variables.listImagenData);
                            Log.i("mispiggi","se llamokkk");
                            Utils.objsIdsDecripcionImgsMOreDescripc =new ArrayList<>();
                           // btnGENERARpdf.setEnabled(true);

                        }



                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("mispiggi","se produjo un error y es "+e.getMessage());

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

    public void saveInfo() {
        RealtimeDB.UpadateDatosProceso(Variables.mimapaDatosProcesMapCurrent,keyNodeActualizar);
        createObjcInformeAndUpload(); //CREAMOS LOS INFORMES Y LOS SUBIMOS...


        for(int i=0; i<Variables.listImagesToDelete.size() ; i++) {

            geTidAndDelete(Variables.listImagesToDelete.get(i));

    }


    }

    void takePickCamera() {


        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "AppQsercom");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");

        cam_uri = PreviewsFormDatSContersEnAc.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cam_uri);

        //startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE); // OLD WAY
        startCamera.launch(cameraIntent);                // VERY NEW WAY

    }



    private void ocultaCertainViews(){
        ediRuma1.setVisibility(View.GONE);
        ediRuma2.setVisibility(View.GONE);
        ediHoraEncendido1.setVisibility(View.GONE);
        ediHoraEncendido2.setVisibility(View.GONE);
        ediUbicacion1.setVisibility(View.GONE);
        ediUbicacion2.setVisibility(View.GONE);





    }

    void hideSomeElemtosAnexos(){

        LinearLayout  lay1x=findViewById(R.id.lay1x);
        RecyclerView recyclerFotoProcesoFrEnFinca=findViewById(R.id.recyclerFotoProcesoFrEnFinca);

        lay1x.setVisibility(View.GONE);
        recyclerFotoProcesoFrEnFinca.setVisibility(View.GONE);

    }





}
