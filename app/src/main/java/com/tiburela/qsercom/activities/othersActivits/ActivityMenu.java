package com.tiburela.qsercom.activities.othersActivits;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import static com.tiburela.qsercom.utils.Variables.currentFormSelect;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.SharePref.SharePref;
import com.tiburela.qsercom.activities.formularios.ActivityContenedores;
import com.tiburela.qsercom.activities.formularios.ActivityCuadMuestCalibAndRechaz;
import com.tiburela.qsercom.activities.formularios.ActivityDatosContersEnAcopio;
import com.tiburela.qsercom.activities.formularios.ActivityControlCalidad;
import com.tiburela.qsercom.activities.formularios.ActivityPackingList;
import com.tiburela.qsercom.activities.formularios.ActivityReporteCalidadCamionesyCarretas;
import com.tiburela.qsercom.callbacks.CallbackDialogConfirmCreation;
import com.tiburela.qsercom.dialog_fragment.DialogConfirmCreateNewForm;
import com.tiburela.qsercom.models.EstateFieldView;
import com.tiburela.qsercom.utils.PerecentHelp;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//package com.tiburela.qsercom.activities.formularios;
public class ActivityMenu extends AppCompatActivity implements CallbackDialogConfirmCreation {
    LinearLayout ly_contenedores;
    LinearLayout ly_conte_en_acopio;
    LinearLayout ly_camy_carretas;
    LinearLayout ly_packing_list;
    LinearLayout ly_cuadro_Muestreo_caly_rechaz;
    LinearLayout ly_controlCalidad;
    Intent currentIntent;
    Context contetext;

  private  AlertDialog alertDialog=null;

    @Override
    protected void onStop() {
        super.onStop();

        Log.i("ciclelife","onStop call");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i("ciclelife","onDestroy call");

    }


   public  ActivityMenu(){
       contetext=this;

    }

    Button btnInInformes;
    TextView txtAdviser,txtAdviser2;
    private static final int PERMISSION_REQUEST_CODE=100;
    private boolean hayUnformulariAmediasPorSubir =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

      //  Variables.actividad =ActivityMenu;
        Variables.activity=this;

        if(Variables.activity!=null ){

            Log.i("miactivity","no es nulo") ;

        }



        Utils.dataFieldsPreferencias=new HashMap<String,String>();
        Utils.listImagesToSaVE=new ArrayList<>();
      Variables.contexto=this;


        EstateFieldView.adddataListsStateFields();
        txtAdviser=findViewById(R.id.txtAdviser);
        txtAdviser2=findViewById(R.id.txtAdviser2);

        btnInInformes =findViewById(R.id.btnIformesRevisar);



        ly_contenedores=findViewById(R.id.ly_contenedores);
        ly_conte_en_acopio=findViewById(R.id.ly_conte_en_acopio);
        ly_camy_carretas=findViewById(R.id.ly_camy_carretas);
        ly_packing_list=findViewById(R.id.ly_packing_list);
        ly_cuadro_Muestreo_caly_rechaz=findViewById(R.id.ly_cuadro_Muestreo_caly_rechaz);

        ly_controlCalidad=findViewById(R.id.ly_controlCalidad);
        ly_controlCalidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkIfExisteFormIcompleto(SharePref.KEY_CONTROL_CALIDAD)){

                   currentFormSelect=Variables.FormCantrolCalidad;
                    muestraDialog2Opciones(SharePref.KEY_CONTROL_CALIDAD);

                }
                else{

                    startActivity(new Intent(ActivityMenu.this, ActivityControlCalidad.class));

                }


            }
        });

        ly_camy_carretas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkIfExisteFormIcompleto(SharePref.KEY_CALIDAD_CAMIONESY_CARRETAS)){

                    currentFormSelect=Variables.FormCamionesyCarretasActivity;

                    muestraDialog2Opciones(SharePref.KEY_CALIDAD_CAMIONESY_CARRETAS);

                }
                else{

                    startActivity(new Intent(ActivityMenu.this, ActivityReporteCalidadCamionesyCarretas.class));

                }


            }
        });

        ly_packing_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkIfExisteFormIcompleto(SharePref.KEY_PACKING_LIST)){

                    currentFormSelect=Variables.FormPackingList;

                    muestraDialog2Opciones(SharePref.KEY_PACKING_LIST);

                }
                else{

                    startActivity(new Intent(ActivityMenu.this, ActivityPackingList.class));

                }



            }
        });


        ly_conte_en_acopio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(checkIfExisteFormIcompleto(SharePref.KEY_CONTENEDORES_EN_ACOPIO)){
                    currentFormSelect=Variables.FormatDatsContAcopi;

                    muestraDialog2Opciones(SharePref.KEY_CONTENEDORES_EN_ACOPIO);

                }
                else{
                    startActivity(new Intent(ActivityMenu.this, ActivityDatosContersEnAcopio.class));

                }



            }
        });


        ly_contenedores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(checkIfExisteFormIcompleto(SharePref.KEY_CONTENEDORES)){

                    currentFormSelect=Variables.FormPreviewContenedores;

                    muestraDialog2Opciones(SharePref.KEY_CONTENEDORES);

                }
                else{

                    startActivity(new Intent(ActivityMenu.this, ActivityContenedores.class));

                }


            }
        });


        ly_cuadro_Muestreo_caly_rechaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkIfExisteMapTOrecycler(SharePref.KEY_CUADRO_MUESTRA_CALIB_RECHAZDS)){
                    Log.i("midatashare","existe data hey");

                }


                if(checkIfExisteFormIcompleto(SharePref.KEY_MUESTRO_RECHAZDOS) ){
                    currentFormSelect=Variables.FormMuestreoRechaz;
                    muestraDialog2Opciones(SharePref.KEY_MUESTRO_RECHAZDOS);


                }





                else{

                    startActivity(new Intent(ActivityMenu.this, ActivityCuadMuestCalibAndRechaz.class ));

                }



            }
        });






        btnInInformes.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {


                if(Variables.tipoDeUser ==Variables.CALIFICADOR_OFICINA) {

                    startActivity(new Intent(ActivityMenu.this, ActivitySeeReports.class));



                }else {  //si es inspector de campo

                    Intent intencion = new Intent(ActivityMenu.this,ActivityContenedores.class);

                    intencion.putExtra("ActivitymenuKey",hayUnformulariAmediasPorSubir);
                    startActivity(intencion);

                }



            }
        });
    }



    ///


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void callgeneratePdf(){
        //ad data to object
        generateObjc();


        // PdfMaker.generatePdfReport1(ActivityMenu.this,informeObjct);

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void generateObjc(){
        //    public SetInformEmbarque1(String codeInforme, int ediNhojaEvaluacion, String zona, String productor, String codigo, String pemarque, String nguiaRemision, String hacienda, String _nguia_transporte, String ntargetaEmbarque, String inscirpMagap, String horaInicio, String horaTermino, String semana, String empacadora, String contenedor, String cbservacion) {
        // informeObjct = new SetInformEmbarque1("aaad01","testxz",12,"Sur","Horlando Mendez","01dssd","Adrtinañ","021121","Florestilla","45654","5454","ADER INCRIPCION","8:00","16:23","12","La Florencia","Contenedor 01","falto mas cola y pan");

        if(!checkPermission()){

            requestPermission();
            //   Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            // checkPermission2();

            /****por aqui pedir permisos antes **/

        }


    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.i("ciclelife","onrestar call");

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


    private void showDataByMode() {

        if(Variables.tipoDeUser == Variables.CALIFICADOR_CAMPO) {  //chekeamos si tiene algun informe incloncluso...
            Utils.dataFieldsPreferencias= (HashMap<String, String>) Utils.loadMap(ActivityMenu.this);

            //*CHEKEAMOS SI TIENE DATA
            if( Utils.dataFieldsPreferencias!=null) {

                //*CHEKEAMOS SI TIENE DATA
                if( Utils.dataFieldsPreferencias.size()>0) {
                    //verificamos si ya lo lleno, si no lo lleno ,quiere decir que introduciremos data en los fileds usando el mapa y un array de editexts...

                    if(Utils.dataFieldsPreferencias.get("estaSubido").equals("no")) {
                        hayUnformulariAmediasPorSubir=true;

                        //SACTUALIZAMOS VIEWS
                        btnInInformes.setText("Completa el Informe");
                        txtAdviser.setText("Tienes 1 formulario Incompleto");
                        txtAdviser2.setText("Tienes Tarea");
                        //mostramos data...Hay un formulario a medias....
                    }else{
                        btnInInformes.setText("Nuevo informe");
                        txtAdviser.setText("Agrega un nuevo informe");
                        txtAdviser2.setText("Hola");
                        hayUnformulariAmediasPorSubir=false;

                    }

                }else {
                    btnInInformes.setText("Nuevo informe");
                    txtAdviser.setText("Agrega un nuevo Informe");
                    txtAdviser2.setText("Hola");

                    hayUnformulariAmediasPorSubir=false;

                }
            }else{
                //SACTUALIZAMOS VIEWS
                btnInInformes.setText("Nuevo informe");
                txtAdviser.setText("Agrega un nuevo Informe");
                txtAdviser2.setText("Hola");
                hayUnformulariAmediasPorSubir=false;


            }

            //le decimos que tiene

        }

        else if(Variables.tipoDeUser ==Variables.CALIFICADOR_OFICINA) {

            btnInInformes.setText("Revisar Informes");
            txtAdviser.setText("Informes por Revisar : 1");
            txtAdviser2.setText("Tienes Tarea");

            //chekjemos sio tiene informes que revisar


        }
        //obtenemos laspreferencias


    }

    @Override
    protected void onStart() {

        super.onStart();

        Log.i("ciclelife","onstart call");
        Variables.currentMapPreferences  =new HashMap<>();
        PerecentHelp.estateForm= new HashMap<>();
        PerecentHelp.listViewsClickedUser =new ArrayList<>();
        showDataByMode();

    }

    @Override
    protected void onResume() {

        super.onResume();

        Log.i("ciclelife","onresume call");

        //  Variables.currentMapPreferences  =new HashMap<>();
      //  showDataByMode();

    }


    private void muestraDialog2Opciones(String keyShareDelete){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ActivityMenu.this);
        alertDialogBuilder.setMessage("Hay un formulario Incompleto deseas continuarlo");


        alertDialogBuilder.setPositiveButton(" Si Continuar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                createAndGoActivity(currentFormSelect);


            }
        });



        alertDialogBuilder.setNegativeButton("Crear uno Nuevo ",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //le mostramos un sheet diciendole si esta seguro de esto...


              DialogConfirmCreateNewForm OBJEC= new DialogConfirmCreateNewForm();


               //DialogConfirmCreateNewForm.gotofragment(ActivityMenu.this);



               OBJEC.showBottomSheetDialogConfirmMenu(ActivityMenu.this,ActivityContenedores.class,keyShareDelete);




            }
        });

         alertDialog=alertDialogBuilder.create();
        alertDialog.show();



    }

    @Override
    public void confirmNuevoFormulario(boolean selecionoCrearNuevoForm) {

        if(selecionoCrearNuevoForm){
            Variables.hayUnFormIncompleto =false;

         //   DialogConfirmCreateNewForm.bottomSheetDialog.dismiss();

            createAndGoActivity(currentFormSelect);


        }

    }




    private void createAndGoActivity(int tipoFormulario){

        if(tipoFormulario==Variables.FormCantrolCalidad){

            ActivityMenu.this.startActivity(new Intent(ActivityMenu.this, ActivityControlCalidad.class)) ;
        }


        else  if(tipoFormulario==Variables.FormPreviewContenedores){

           // ActivityMenu.this.startActivity(new Intent(ActivityMenu.this, ControlCalidad.class)) ;

            Intent myintent = new Intent(ActivityMenu.this,ActivityContenedores.class);
            ActivityMenu.this.startActivity(myintent);
            finish();
        }


        else  if(tipoFormulario==Variables.FormatDatsContAcopi){
            startActivity(new Intent(ActivityMenu.this, ActivityDatosContersEnAcopio.class)) ;

        }


        else  if(tipoFormulario==Variables.FormCamionesyCarretasActivity){//
            startActivity(new Intent(ActivityMenu.this, ActivityReporteCalidadCamionesyCarretas.class)) ;

        }


        else  if(tipoFormulario==Variables.FormPackingList){
            startActivity(new Intent(ActivityMenu.this, ActivityPackingList.class)) ;


        }


        else  if(tipoFormulario==Variables.FormMuestreoRechaz){
            startActivity(new Intent(ActivityMenu.this, ActivityCuadMuestCalibAndRechaz.class)) ;
        }


    }




    private boolean checkIfExisteFormIcompleto( String keyFormulario){
        SharePref.init(getApplicationContext());

        Map<String,String>mimap= SharePref.loadMap(ActivityMenu.this,keyFormulario);

        if(mimap!=null && mimap.size()>0){ //si no es nulo
            Log.i("chekenadoPREFE"," NO ES NULO y hay contenido  HURRA");
            Log.i("chekenadoPREFE"," EL SIZE ES "+mimap.size());

           Variables.currentMapPreferences= (HashMap<String, String>) mimap;
            Variables.hayUnFormIncompleto =true;
            return true;
        }

        else{ //si es nulo
            Log.i("chekenadoPREFE","  ES NULO O  NO HAY UN MAPA EN PREFRENCIAS");

            return false;
        }

    }



    private boolean checkIfExisteMapTOrecycler( String keyFormulario){

        Map<String,String>mimap= SharePref.loadMap(ActivityMenu.this,keyFormulario);

        if(mimap!=null && mimap.size()>0){ //si no es nulo

            SharePref.mihashMapFieldsToRecycler= mimap;
            Variables.hayUnFormIcompletoTOrEYCLER =true;
            return true;
        }



        else{ //si es nulo
            Log.i("chekenadoPREFE","  ES NULO O  NO HAY UN MAPA EN PREFRENCIAS");

            return false;
        }

    }




    public  void showBottomSheetDialogConfirmMenu(Context context) {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);

        bottomSheetDialog.setContentView(R.layout.bottom_sheet_confirm_changesxml);

        Button btnSi=bottomSheetDialog.findViewById(R.id.btnSix);
        Button btnNo=bottomSheetDialog.findViewById(R.id.btnNox);


        btnSi.setOnClickListener(new View.OnClickListener() { //revisar

            @Override
            public void onClick(View v) {

               // CallbackDialogConfirmCreation callbackDialogConfirmCreation= new ActivityMenu();
              //  callbackDialogConfirmCreation.confirmNuevoFormulario(true);

                Log.i("comprobacionzz","onclick en si y call form dialogcoonfirm class ");

                // bottomSheetDialog.dismiss();-

                Intent intent = new Intent(ActivityMenu.this, ActivityContenedores.class);
                startActivity(intent);//


                // finish(); //lamaos el calback aqui

            }
        });



        btnNo.setOnClickListener(new View.OnClickListener() {  //activar switch
            @Override
            public void onClick(View v) {
             //   CallbackDialogConfirmCreation callbackDialogConfirmCreation= new ActivityMenu();
              //  callbackDialogConfirmCreation.confirmNuevoFormulario(false);
                Log.i("comprobacionzz","onclick en no y call form dialogcoonfirm class ");


                bottomSheetDialog.dismiss();
            }
        });


        bottomSheetDialog.show();
    }


}