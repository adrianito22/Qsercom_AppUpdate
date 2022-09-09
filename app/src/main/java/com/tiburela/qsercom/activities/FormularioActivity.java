package com.tiburela.qsercom.activities;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
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

import com.google.android.material.textfield.TextInputEditText;
import com.tiburela.qsercom.BuildConfig;
import com.tiburela.qsercom.PdfMaker.PdfMaker;
import com.tiburela.qsercom.adapters.RecyclerViewAdapter;
import com.tiburela.qsercom.auth.Auth;
import com.tiburela.qsercom.databaseHelper.RealtimeDB;
import com.tiburela.qsercom.models.EstateFieldView;
import com.tiburela.qsercom.models.ImagenX;
import com.tiburela.qsercom.storageHelper.StorageData;
import com.tiburela.qsercom.utils.Permisionx;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.tiburela.qsercom.R;


public class FormularioActivity extends AppCompatActivity implements View.OnClickListener , View.OnTouchListener {
    private static final int PERMISSION_REQUEST_CODE=100;

    private int currentTypeImage=0;

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

    TextInputEditText ediFotosLlegada;

    TextInputEditText ediContenedor;
    TextInputEditText  ediPPC01;
    TextInputEditText        ediPPC02;
    TextInputEditText        ediPPC03;
    TextInputEditText        ediPPC04;
    TextInputEditText        ediPPC05;
    TextInputEditText        ediPPC06;
    TextInputEditText        ediPPC07;
    TextInputEditText        ediPPC08;
    TextInputEditText        ediPPC09;
    TextInputEditText        ediPPC010;
    TextInputEditText        ediPPC011;
    TextInputEditText       ediPPC012;
    TextInputEditText     ediPPC013;
    TextInputEditText     ediPPC014;
    TextInputEditText      ediPPC015;
    TextInputEditText      ediPPC016;

    TextInputEditText  ediDestino;
    TextInputEditText  ediNViaje;
    TextInputEditText  ediTipoContenedor;
    TextInputEditText  ediVapor;
    TextInputEditText  ediHOraLllegada;
    TextInputEditText  ediHoraSalida;
    TextInputEditText  ediFotosHerex;
    TextInputEditText  editPhotos;
   TextInputEditText ediFotoContenedor;





    ProgressBar progressBarFormulario;



    LinearLayout linLayoutHeader1;
    LinearLayout linLayoutHeader2;
    LinearLayout linLayoutHeader3;
    LinearLayout linLayoutHeader4;
    LinearLayout linLayoutHeader5;
    LinearLayout linLayoutHeader6;
    LinearLayout linLayoutHeader7;



    Spinner spinnerSelectZona;

    Switch switchContenedor;

    ArrayList<View> listViewsClickedUser;

    ImageView imBatach;
    ActivityResultLauncher activityResultLauncher;
    Uri cam_uri;

    ImageView imBtakePic;

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

        Auth.initAuth(FormularioActivity.this);
        Auth.signInAnonymously(FormularioActivity.this);


        // Check if user is signed in (non-null) and update UI accordingly.
       // FirebaseUser currentUser = Auth.mAuth.getCurrentUser();
      //  updateUI(currentUs bver)

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

      // FirebaseApp.initializeApp(this);
      //  DatabaseReference rootDatabaseReference = FirebaseDatabase.getInstance().getReference(); //anterior

        Auth.initAuth(this);

        StorageData. initStorageReference();


        findViewsIds();
        configCertainSomeViewsAliniciar();
        listViewsClickedUser=new ArrayList<>();

        addClickListeners();
        resultatachImages();
        listennerSpinner();

        EstateFieldView.adddataList();
        addOnTouchaMayoriaDeViews();
        eventCheckdata();
        //creaFotos();


    }





    void showingTimePicker( View vista){


        final Calendar cldr = Calendar.getInstance();
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        // time picker dialog
        TimePickerDialog  picker = new TimePickerDialog(FormularioActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int sHour, int sMinute) {

                        if(vista.getId()==R.id.ediHoraInicio) {
                            ediHoraInicio.setText(sHour + ":" + sMinute);


                        }else if (vista.getId()== R.id.ediHoraTermino) {
                            ediHoraTermino.setText(sHour + ":" + sMinute);


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
        DatePickerDialog  picker = new DatePickerDialog(FormularioActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        ediFecha.setText(daySemana+"/"+mes+"/"+year);

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
        disableEditText(ediContenedor);
        disableEditText(ediFotosLlegada);

        disableEditText(ediZona);


    }

    private void findViewsIds( ) { //configuraremos algos views al iniciar
        ediEmpacadora=findViewById(R.id.ediEmpacadora);

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

        ediFotosLlegada=findViewById(R.id.ediFotosLlegada);


        linLayoutHeader1 =findViewById(R.id.linLayoutHeader1);
        linLayoutHeader2 =findViewById(R.id.linLayoutHeader2);
        linLayoutHeader3 =findViewById(R.id.linLayoutHeader3);
        linLayoutHeader4 =findViewById(R.id.linLayoutHeader4);
        linLayoutHeader5 =findViewById(R.id.linLayoutHeader5);
        linLayoutHeader6 =findViewById(R.id.linLayoutHeader6);
        linLayoutHeader7 =findViewById(R.id.linLayoutHeader7);





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
          ediHOraLllegada=findViewById(R.id.ediHOraLllegada);
          ediHoraSalida=findViewById(R.id.ediHoraSalida);

        ediTipoContenedor=findViewById(R.id.ediTipoContenedor);

        ediFotoContenedor=findViewById(R.id.ediFotoContenedor);

        progressBarFormulario=findViewById(R.id.progressBarFormulario);



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

    }


    private void addClickListeners( ) {

        /**todos add a todos clicklistener de la implemntacion*/


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





        linLayoutHeader2.setOnClickListener(this);
        linLayoutHeader1.setOnClickListener(this);
        linLayoutHeader3.setOnClickListener(this);
        linLayoutHeader4.setOnClickListener(this);
        linLayoutHeader5.setOnClickListener(this);
        linLayoutHeader6.setOnClickListener(this);
        linLayoutHeader7.setOnClickListener(this);

        ediFecha.setOnClickListener(this);
        ediHoraInicio.setOnClickListener(this);
        ediHoraTermino.setOnClickListener(this);





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




       switch (view.getId()) {

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


           case R.id.ediFecha:
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






       }

        //aqui o


    }

    private void takepickNow() {

        Permisionx.checkPermission(Manifest.permission.CAMERA,1,this, FormularioActivity.this);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "AppQsercom");
            values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");

             cam_uri = FormularioActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
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

                            //creamos un nuevo objet de tipo ImagenX
                            ImagenX obcjImagenX=new ImagenX("",cam_uri,currentTypeImage);

                            //agregamos este objeto a la lista
                            ImagenX.hashMapImagesData.put(obcjImagenX.getUniqueId(),obcjImagenX);


                            showImagesPicShotOrSelectUpdateView(false);

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
        ediCodigo.setOnTouchListener(this);
        ediInscirpMagap.setOnTouchListener(this);
        ediPemarque.setOnTouchListener(this);
       // ediZona.setOnTouchListener(this);
        ediHoraInicio.setOnTouchListener(this);
        ediHoraTermino.setOnTouchListener(this);
        ediNguiaRemision.setOnTouchListener(this);
        edi_nguia_transporte.setOnTouchListener(this);
        ediNtargetaEmbarque.setOnTouchListener(this);
        ediNhojaEvaluacion.setOnTouchListener(this);
        spinnerSelectZona.setOnTouchListener(this);
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

            if(ImagenX.listImagesData.size()> 0 ) {
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

                        for(int indice=0; indice<result.size(); indice++){

                            ImagenX imagenXObjc=new ImagenX("",result.get(indice),currentTypeImage);



                            ImagenX.hashMapImagesData.put(imagenXObjc.getUniqueId(),imagenXObjc);


                        }



                        showImagesPicShotOrSelectUpdateView(false);



                       // creaFotos(result);


                        //Do What you Want Here ................
                        //Do What you Want Here ................

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


private void listennerSpinner() {
        spinnerSelectZona .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String zonaEelejida= spinnerSelectZona.getSelectedItem().toString();

                ediZona.setText("Zona "+zonaEelejida+" ");


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

        currentTypeImage=Variables.typeoFdeleteImg;
    }


     ArrayList<ImagenX> filterListImagesData=new ArrayList<ImagenX>(); //LISTA FILTRADA QUE REPRESENTARA EL RECICLERVIEW

    RecyclerView recyclerView= findViewById(R.id.recyclerView);


    for (Map.Entry<String, ImagenX> set : ImagenX.hashMapImagesData.entrySet()) {

        String key = set.getKey();

        ImagenX value = set.getValue();

        if(value.getTipoImagenCategory()==currentTypeImage){

            filterListImagesData.add(ImagenX.hashMapImagesData.get(key));

        }


    }


    //buscamos este



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





    RecyclerViewAdapter adapter=new RecyclerViewAdapter(filterListImagesData,this);
    GridLayoutManager layoutManager=new GridLayoutManager(this,2);


    // at last set adapter to recycler view.
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

           // storageTest();



        }
    });





}

void checkDataFields(){ //

    LinearLayout layoutContainerSeccion1=findViewById(R.id.layoutContainerSeccion1);
    LinearLayout layoutContainerSeccion2=findViewById(R.id.layoutContainerSeccion2);
    LinearLayout layoutContainerSeccion3=findViewById(R.id.layoutContainerSeccion3);
    LinearLayout layoutContainerSeccion4=findViewById(R.id.layoutContainerSeccion4);
    LinearLayout layoutContainerSeccion5=findViewById(R.id.layoutContainerSeccion5);

    int minimoFotos=1;


    if(ediSemana.getText().toString().isEmpty()){ //chekamos que no este vacia
        ediSemana.requestFocus();
        ediSemana.setError("Este espacio es obligatorio");
        layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
        return;
        //obtiene el padre del padre

    }

    if(ediFecha.getText().toString().isEmpty()){ //chekamos que no este vacia
        ediFecha.requestFocus();
        ediFecha.setError("Debe selecionar una fecha");

        layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
        return;

    }







    if(ediProductor.getText().toString().isEmpty()){ //chekamos que no este vacia
        ediProductor.requestFocus();
        ediProductor.setError("Este espacio es obligatorio");

        layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
        return;

    }




    if(ediHacienda.getText().toString().isEmpty()){ //chekamos que no este vacia
        ediHacienda.requestFocus();
        ediHacienda.setError("Este espacio es obligatorio");

        layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
        return;

    }






    if(ediCodigo.getText().toString().isEmpty()){ //chekamos que no este vacia
        ediCodigo.requestFocus();
        ediCodigo.setError("Este espacio es obligatorio");

        layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
        return;

    }


    if(ediInscirpMagap.getText().toString().isEmpty()){ //chekamos que no este vacia
        ediInscirpMagap.requestFocus();
        ediInscirpMagap.setError("Este espacio es obligatorio");

        layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
        return;

    }

    if(ediPemarque.getText().toString().isEmpty()){ //chekamos que no este vacia
        ediPemarque.requestFocus();
        ediPemarque.setError("Este espacio es obligatorio");

        layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
        return;

    }


    if(ediZona.getText().toString().isEmpty()){ //chekamos que no este vacia
        ediZona.requestFocus();
        ediZona.setError("Debe selecionar una zona");
        layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
        return;

    }


    if(ediHoraInicio.getText().toString().isEmpty()){ //chekamos que no este vacia
        ediHoraInicio.requestFocus();
        ediHoraInicio.setError("Este espacio es obligatorio");

        layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
        return;

    }


    if(ediHoraTermino.getText().toString().isEmpty()){ //chekamos que no este vacia
        ediHoraTermino.requestFocus();
        ediHoraTermino.setError("Este espacio es obligatorio");

        layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
        return;

    }



    if(ediNguiaRemision.getText().toString().isEmpty()){ //chekamos que no este vacia
        ediNguiaRemision.requestFocus();
        ediNguiaRemision.setError("Este espacio es obligatorio");

        layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
        return;

    }

    if(edi_nguia_transporte.getText().toString().isEmpty()){ //chekamos que no este vacia
        edi_nguia_transporte.requestFocus();
        edi_nguia_transporte.setError("Este espacio es obligatorio");

        layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
        return;

    }


    if(ediNtargetaEmbarque.getText().toString().isEmpty()){ //chekamos que no este vacia
        ediNtargetaEmbarque.requestFocus();
        ediNtargetaEmbarque.setError("Este espacio es obligatorio");

        layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
        return;

    }




    if(ediNhojaEvaluacion.getText().toString().isEmpty()){ //chekamos que no este vacia
        ediNhojaEvaluacion.requestFocus();
        ediNhojaEvaluacion.setError("Este espacio es obligatorio");
        layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
         return;
        //obtiene el padre del padre

    }



    if(ediEmpacadora.getText().toString().isEmpty()){ //chekamos que no este vacia
        ediEmpacadora.requestFocus();
        ediEmpacadora.setError("Este espacio es obligatorio");

        layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
        return;

    }





    if( ! existminiumImage(minimoFotos,Variables.FOTO_LLEGADA)){
        ediFotosLlegada.requestFocus();

        layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
        ediFotosLlegada.setError("Agregue al menos "+minimoFotos+" foto");
        return;
    }else{

        ediFotosLlegada.clearFocus();
        ediFotosLlegada.setError(null);

    }

    //



    //falta chekear al menos un producto de postcosehca y si utilizo otros neceistamos que especifique
//PROXIMO

    //chekeando al menos un producto postcosecha
     //si todos estan vacios...

    //si no estan vacios todos...

    if(ediPPC01.getText().toString().isEmpty() && ediPPC02.getText().toString().isEmpty()&& ediPPC03.getText().toString().isEmpty()
            && ediPPC04.getText().toString().isEmpty()&& ediPPC05.getText().toString().isEmpty()&& ediPPC06.getText().toString().isEmpty()
            && ediPPC07.getText().toString().isEmpty()&& ediPPC08.getText().toString().isEmpty() && ediPPC09.getText().toString().isEmpty()
            && ediPPC010.getText().toString().isEmpty() && ediPPC011.getText().toString().isEmpty() && ediPPC012.getText().toString().isEmpty()
            && ediPPC013.getText().toString().isEmpty() && ediPPC014.getText().toString().isEmpty() && ediPPC015.getText().toString().isEmpty()
            && ediPPC016.getText().toString().isEmpty()
    ){ //chekamos que no este vacia
        ediPPC01.requestFocus();
        ediPPC07.setError("Inserte al menos un producto");
        layoutContainerSeccion2.setVisibility(LinearLayout.VISIBLE);

        return;

    }



    if(! ediPPC015.getText().toString().isEmpty() && ediPPC016.getText().toString().isEmpty() ){ //chekamos que no este vacia
        ediPPC016.requestFocus();
        ediPPC016.setError("Inserte cantidad");

        layoutContainerSeccion2.setVisibility(LinearLayout.VISIBLE);
        return;

    }


    if(! ediPPC016.getText().toString().isEmpty() && ediPPC015.getText().toString().isEmpty() ){ //chekamos que no este vacia
        ediPPC015.requestFocus();
        ediPPC015.setError("inserte nombre producto");

        layoutContainerSeccion2.setVisibility(LinearLayout.VISIBLE);
        return;

    }


    ///CHEKEAMOS DATA seccion CONTENEDOR

    if(ediDestino.getText().toString().isEmpty()){ //chekamos que no este vacia
        ediDestino.requestFocus();
        ediDestino.setError("Este espacio es obligatorio");

        layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
        return;

    }


    if(ediNViaje.getText().toString().isEmpty()){ //chekamos que no este vacia
        ediNViaje.requestFocus();
        ediNViaje.setError("Este espacio es obligatorio");

        layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
        return;

    }


    if(ediVapor.getText().toString().isEmpty()){ //chekamos que no este vacia
        ediVapor.requestFocus();
        ediVapor.setError("Este espacio es obligatorio");

        layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
        return;

    }



    if(ediTipoContenedor.getText().toString().isEmpty()){ //chekamos que no este vacia
        ediTipoContenedor.requestFocus();
        ediTipoContenedor.setError("Este espacio es obligatorio");

        layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
        return;

    }


    if(ediHOraLllegada.getText().toString().isEmpty()){ //chekamos que no este vacia
        ediHOraLllegada.requestFocus();
        ediHOraLllegada.setError("Este espacio es obligatorio");

        layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
        return;

    }


    if(ediHoraSalida.getText().toString().isEmpty()){ //chekamos que no este vacia
        ediHoraSalida.requestFocus();
        ediHoraSalida.setError("Este espacio es obligatorio");

        layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
        return;

    }


    //chekamos que al menos exista una imagen...


    if( ! existminiumImage(minimoFotos,Variables.FOTO_CONTENEDOR)){
        ediFotoContenedor.requestFocus();

        layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
        ediFotoContenedor.setError("Agregue al menos "+minimoFotos+" foto");
        return;
    }else{

        ediFotoContenedor.clearFocus();
        ediFotoContenedor.setError(null);

    }








    //si toda la data esta lista

    //LA SUBIMOS...

    RealtimeDB.initDatabaseReference(); //inicilizamos la base de datos

    //(Context context, String codeInforme, int ediNhojaEvaluacion, String zona, String productor, String codigo,
    // String pemarque, String nguiaRemision, String hacienda, String _nguia_transporte, String ntargetaEmbarque,
    // String inscirpMagap, String horaInicio, String horaTermino, String semana, String empacadora, String contenedor, String cbservacion) {

        RealtimeDB.addNewInforme(FormularioActivity.this,ediCodigo.getText().toString(),Integer.parseInt(ediNhojaEvaluacion.getText().toString()),
                ediZona.getText().toString(),ediProductor.getText().toString(),ediCodigo.getText().toString()
                ,ediPemarque.getText().toString(),ediNguiaRemision.getText().toString(),ediHacienda.getText().toString()
                ,edi_nguia_transporte.getText().toString(),ediNtargetaEmbarque.getText().toString(),
                ediInscirpMagap.getText().toString(),ediHoraInicio.getText().toString(),ediHoraTermino.getText().toString()
                ,ediSemana.getText().toString(),ediEmpacadora.getText().toString(),ediContenedor.getText().toString(),ediObservacion.getText().toString()

                ); //agregamos


//chekemoa s que tenga al m,noe sun producto....y y sselciono otro


}

    private void eventoBtnclicklistenerDelete(RecyclerViewAdapter adapter) {

        adapter.setOnItemClickListener(new RecyclerViewAdapter.ClickListener() {


            @Override
            public void onItemClick(int position, View v) {  //este para eminar
                //  Variables.currentCuponObjectGlob =listGiftCards.get(position);

                Log.i("midaclick","el click es here, posicion es "+position);

               ///elimnar el hasmap
               //vamos a ver el tipo del objeto removivo
               Variables.typeoFdeleteImg=  ImagenX.hashMapImagesData.get(v.getTag()).getTipoImagenCategory();

                Log.i("camisax","el size antes de eliminar es "+ ImagenX.hashMapImagesData.size());


                ImagenX.hashMapImagesData.remove(v.getTag().toString());

                Log.i("camisax","el size despues de eliminar es "+ ImagenX.hashMapImagesData.size());

                showImagesPicShotOrSelectUpdateView(true);





                //   Log.i("dtaas","switch a" + "ctivate is "+Variables.currentCuponObjectGlob.isEsActivateCupon());
                //  Log.i("dtaas","switch destacado  is "+Variables.currentCuponObjectGlob.isEsDestacadoCupon());


            }

        });
    }



    void storageTest() {
   //una lista de Uris


        if(ImagenX.listImagesData.size() ==0 ){

            Toast.makeText(this, "esta vacia ", Toast.LENGTH_SHORT).show();
             return;
        }

        //    public static void uploadImage(Context context, ArrayList<ImagenX> listImagesData) {

        //aqui subimos
       // StorageData.uploadImage(FormularioActivity.this, ImagenX.listImagesData);

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
            Uri uri = Uri.parse("package:" + BuildConfig.APPLICATION_ID);
           startActivity(new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, uri));
        }





        PdfMaker. generatePdfReport1(FormularioActivity.this);


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
            int result = ContextCompat.checkSelfPermission(FormularioActivity.this, READ_EXTERNAL_STORAGE);
            int result1 = ContextCompat.checkSelfPermission(FormularioActivity.this, WRITE_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
        }
    }



    private void findSpinnersAndAddData() {

        Spinner spinnerCondicionBalanza=  findViewById(R.id.spinnerCondicionBalanza);
        Spinner spinnertipoCaja =  findViewById(R.id.spinnertipoCaja);
        Spinner spinnertipodePlastico = findViewById(R.id.spinnertipodePlastico);
        Spinner spinnertipodeBlanza =  findViewById(R.id.spinnertipodeBlanza);
        Spinner spinnertipodeBlanzaRepeso =  findViewById(R.id.spinnertipodeBlanzaRepeso);
        Spinner spinnerubicacionBalanza =  findViewById(R.id.spinnerubicacionBalanza);




        //setlisttoview spinner


    }


    private boolean chekIsLLenoDataContenedor() {

        TextInputEditText  ediDestino=findViewById(R.id.ediDestino);
        TextInputEditText  ediNViaje=findViewById(R.id.ediNViaje);
        TextInputEditText  ediVapor=findViewById(R.id.ediVapor);
        TextInputEditText  ediHOraLllegada=findViewById(R.id.ediHOraLllegada);
        TextInputEditText  ediHoraSalida=findViewById(R.id.ediHoraSalida);




        return true;

    }



    ///vamos a cehekar que exista al menos una imagen en cada categoria...
    //comprbar que exista un objeto imagen.....

    //primero chekeamos el el uri exista...


    private boolean existminiumImage(int numImagenNMinimo, int categoriaImagenToSearch){

        int numImagesEcontradas=0;


        for (Map.Entry<String, ImagenX> set : ImagenX.hashMapImagesData.entrySet()) { //revismao en todo el map

         //   String key = set.getKey();

            ImagenX value = set.getValue();

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

}
