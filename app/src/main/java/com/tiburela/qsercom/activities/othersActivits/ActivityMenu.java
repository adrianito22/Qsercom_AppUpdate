package com.tiburela.qsercom.activities.othersActivits;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import static com.tiburela.qsercom.utils.Variables.currentFormSelect;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.SharePref.SharePref;
import com.tiburela.qsercom.activities.formularios.ActivityCamionesyCarretas;
import com.tiburela.qsercom.activities.formularios.ActivityContenedores;
import com.tiburela.qsercom.activities.formularios.ActivityContersEnAcopio;
import com.tiburela.qsercom.activities.formularios.ActivityCuadMuestCalibAndRechaz;
import com.tiburela.qsercom.activities.formularios.ActivityControlCalidad;
import com.tiburela.qsercom.activities.formularios.ActivityPackingList;
import com.tiburela.qsercom.adapters.RecyclerViewAdapLinkage;
import com.tiburela.qsercom.callbacks.CallbackDialogConfirmCreation;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.dialog_fragment.DialogConfirmCreateNewForm;
import com.tiburela.qsercom.models.EstateFieldView;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.InformRegister;
import com.tiburela.qsercom.models.UsuarioQsercom;
import com.tiburela.qsercom.utils.HelperImage;
import com.tiburela.qsercom.utils.PerecentHelp;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//package com.tiburela.qsercom.activities.formularios;
public class ActivityMenu extends AppCompatActivity implements CallbackDialogConfirmCreation {

    boolean userIniciosSesion=false;
    private FirebaseAuth mAuth;
      GoogleSignInClient mGoogleSignInClient;
   // private CallbackManager mCallbackManager;

     String correoUsuario="";
    String nombreYapllidoUser="";


    Button btnInforsGuardados;

    ImageView imGProfile;

    TextView txtHeader;
    TextView txtSubHeader;

    LinearLayout ly_contenedores;
    LinearLayout ly_conte_en_acopio;
    LinearLayout ly_camy_carretas;
    LinearLayout ly_packing_list;
    LinearLayout ly_cuadro_Muestreo_caly_rechaz;
    LinearLayout ly_controlCalidad;
    Intent currentIntent;
    Context contetext;
    private ProgressDialog progress;

    private final int RC_SIGN_IN=500;



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

        SharePref.init(ActivityMenu.this);



        //  Variables.actividad =ActivityMenu;
        Variables.activity=this;

        imGProfile=findViewById(R.id.imageView2);


        RealtimeDB.initDatabasesRootOnly();




        inigoogleSigni();//iniciamos google account autentificacion

        String uniqueId =String.valueOf(Utils.generateNumRadom6Digits());
        Log.i("elnumber","el numero generado es ss "+uniqueId);


      //  testCreateRegisters();



        Utils.dataFieldsPreferencias=new HashMap<String,String>();
        Utils.listImagesToSaVE=new ArrayList<>();




        Variables.contexto=this;


        EstateFieldView.adddataListsStateFields();
        txtAdviser=findViewById(R.id.txtAdviser);
        txtAdviser2=findViewById(R.id.txtAdviser2);

        btnInInformes =findViewById(R.id.btnIformesRevisar);

        btnInforsGuardados=findViewById(R.id.btnInforsGuardados);

        ly_contenedores=findViewById(R.id.ly_contenedores);
        ly_conte_en_acopio=findViewById(R.id.ly_conte_en_acopio);
        ly_camy_carretas=findViewById(R.id.ly_camy_carretas);
        ly_packing_list=findViewById(R.id.ly_packing_list);
        ly_cuadro_Muestreo_caly_rechaz=findViewById(R.id.ly_cuadro_Muestreo_caly_rechaz);

        ly_controlCalidad=findViewById(R.id.ly_controlCalidad);


        btnInforsGuardados.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                SharePref.init(ActivityMenu.this);
                     Utils.isOfflineReport=true;

                startActivity(new Intent(ActivityMenu.this,ActivitySeeReportsOffline.class));


            }
        });




        ly_controlCalidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intencion=new Intent(ActivityMenu.this,ActivityControlCalidad.class);
                showPRogressAndStartActivity(intencion);


                /*
                if(checkIfExisteFormIcompleto(SharePref.KEY_CONTROL_CALIDAD)){

                   currentFormSelect=Variables.FormCantrolCalidad;

                    muestraDialog2Opciones(SharePref.KEY_CONTROL_CALIDAD);

                }
                else{

                  //  startActivity(new Intent(ActivityMenu.this, ActivityControlCalidad.class));

                    Intent intencion=new Intent(ActivityMenu.this,ActivityControlCalidad.class);
                    showPRogressAndStartActivity(intencion);

                }

*/
            }
        });

        ly_camy_carretas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intencion=new Intent(ActivityMenu.this, ActivityCamionesyCarretas.class);
                showPRogressAndStartActivity(intencion);


            }
        });

        ly_packing_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intencion=new Intent(ActivityMenu.this,ActivityPackingList.class);
                showPRogressAndStartActivity(intencion);


            }
        });


        ly_conte_en_acopio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intencion=new Intent(ActivityMenu.this, ActivityContersEnAcopio.class);
                showPRogressAndStartActivity(intencion);


            }
        });


        ly_contenedores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intencion=new Intent(ActivityMenu.this,ActivityContenedores.class);
                showPRogressAndStartActivity(intencion);


            }
        });


        ly_cuadro_Muestreo_caly_rechaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intencion=new Intent(ActivityMenu.this,ActivityCuadMuestCalibAndRechaz.class);
                showPRogressAndStartActivity(intencion);



            }
        });






        btnInInformes.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                Utils.isOfflineReport=false;


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

       // Log.i("ciclelife","onrestar call");

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

        RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString= "";
        RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado= "";
        RecyclerViewAdapLinkage.mapWhitIDScontrolCaldVinclds=new HashMap<>();
        RecyclerViewAdapLinkage. mapWhitIdsCuadroMuestreo=new HashMap<>();
        Utils.numReportsVinculadsAll=0;
        Variables.listPromedioLibriado= new ArrayList<>();

           Variables.isClickable = true;



        librearMemor();


       // estableceHeaderTextAndListerner();


         Variables.userGoogle = FirebaseAuth.getInstance().getCurrentUser();

        if(Variables.userGoogle!=null) {

            mAuth = FirebaseAuth.getInstance(); //verificamos que si esta autentificado..

            mAuth.getAccessToken(false).addOnSuccessListener(new OnSuccessListener<GetTokenResult>() {
                @Override
                public void onSuccess(GetTokenResult getTokenResult) {
                    Log.i("solodataaqui", "el user esta autentificado");
                    userIniciosSesion=true;
                    descragCurrentUsuario(Variables.userGoogle.getEmail());



                }
            });
        }



            try {
            progress.dismiss();

        } catch (Exception e) {
            e.printStackTrace();
        }




   ///   PdfMaker. generaPdFtEST(ActivityMenu.this);



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

                DialogConfirmCreateNewForm OBJEC= new DialogConfirmCreateNewForm();


                if(currentFormSelect ==Variables.FormatDatsContAcopi) {

                    OBJEC.showBottomSheetDialogConfirmMenu(ActivityMenu.this, ActivityContersEnAcopio.class,keyShareDelete);


                }
                else if(currentFormSelect ==Variables.FormPackingList){
                    OBJEC.showBottomSheetDialogConfirmMenu(ActivityMenu.this,ActivityPackingList.class,keyShareDelete);

                }
                else if(currentFormSelect ==Variables.FormCamionesyCarretasActivity){
                    OBJEC.showBottomSheetDialogConfirmMenu(ActivityMenu.this, ActivityCamionesyCarretas.class,keyShareDelete);

                }
                else if(currentFormSelect ==Variables.FormMuestreoRechaz){

                    OBJEC.showBottomSheetDialogConfirmMenu(ActivityMenu.this,ActivityCuadMuestCalibAndRechaz.class,keyShareDelete);
                }
                else if(currentFormSelect ==Variables.FormCantrolCalidad){
                    OBJEC.showBottomSheetDialogConfirmMenu(ActivityMenu.this,ActivityControlCalidad.class,keyShareDelete);

                }
                else if(currentFormSelect ==Variables.FormPreviewContenedores
                ){
                    OBJEC.showBottomSheetDialogConfirmMenu(ActivityMenu.this,ActivityContenedores.class,keyShareDelete);

                }

               //DialogConfirmCreateNewForm.gotofragment(ActivityMenu.this);


            }
        });

         alertDialog=alertDialogBuilder.create();
        alertDialog.show();



    }

    @Override
    public void confirmNuevoFormulario(boolean selecionoCrearNuevoForm) {

        if(selecionoCrearNuevoForm){
            Variables.esUnFormularioOfflienSharePref =false;

         //   DialogConfirmCreateNewForm.bottomSheetDialog.dismiss();

            createAndGoActivity(currentFormSelect);


        }

    }




    private void createAndGoActivity(int tipoFormulario){

        if(tipoFormulario==Variables.FormCantrolCalidad){

        //    ActivityMenu.this.startActivity(new Intent(ActivityMenu.this, ActivityControlCalidad.class)) ;

              Intent intencion=  new Intent(ActivityMenu.this, ActivityControlCalidad.class);
            showPRogressAndStartActivity(intencion);


        }


        else  if(tipoFormulario==Variables.FormPreviewContenedores){

           // ActivityMenu.this.startActivity(new Intent(ActivityMenu.this, ControlCalidad.class)) ;

            Intent myintent = new Intent(ActivityMenu.this,ActivityContenedores.class);
            showPRogressAndStartActivity(myintent);


          //  finish();
        }


        else  if(tipoFormulario==Variables.FormatDatsContAcopi){
          //  startActivity(new Intent(ActivityMenu.this, ActivityContersEnAcopio.class)) ;

            Intent intencion=new Intent(ActivityMenu.this, ActivityContersEnAcopio.class);

            showPRogressAndStartActivity(intencion);


        }


        else  if(tipoFormulario==Variables.FormCamionesyCarretasActivity){//
          //  startActivity(new Intent(ActivityMenu.this, ActivityCamionesyCarretas.class)) ;

            Intent intencion=new Intent(ActivityMenu.this, ActivityCamionesyCarretas.class);

            showPRogressAndStartActivity(intencion);

        }


        else  if(tipoFormulario==Variables.FormPackingList){
          //  startActivity(new Intent(ActivityMenu.this, ActivityPackingList.class)) ;
            Intent intencion=new Intent(ActivityMenu.this, ActivityPackingList.class);

            showPRogressAndStartActivity(intencion);

        }


        else  if(tipoFormulario==Variables.FormMuestreoRechaz){
           // startActivity(new Intent(ActivityMenu.this, ActivityCuadMuestCalibAndRechaz.class)) ;
            Intent intencion=new Intent(ActivityMenu.this, ActivityCuadMuestCalibAndRechaz.class);

            showPRogressAndStartActivity(intencion);


        }
    }



    private boolean checkIfExisteFormIcompleto( String keyFormulario){
        SharePref.init(getApplicationContext());

        Map<String,String>mimap= SharePref.loadMap(keyFormulario);

        if(mimap!=null && mimap.size()>0){ //si no es nulo
            Log.i("chekenadoPREFE"," NO ES NULO y hay contenido  HURRA");
            Log.i("chekenadoPREFE"," EL SIZE ES "+mimap.size());

           Variables.currentMapPreferences= (HashMap<String, String>) mimap;
            Variables.esUnFormularioOfflienSharePref =true;
            return true;
        }

        else{ //si es nulo
            Log.i("chekenadoPREFE","  ES NULO O  NO HAY UN MAPA EN PREFRENCIAS");

            return false;
        }

    }



    private boolean checkIfExisteMapTOrecycler( String keyFormulario){

        Map<String,String>mimap= SharePref.loadMap(keyFormulario);

        if(mimap!=null && mimap.size()>0){ //si no es nulo

            SharePref.mihashMapFieldsToRecycler= mimap;
            Variables.hayDataPreferences =true;
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


    private void showPRogressAndStartActivity(Intent i) {
        progress =new ProgressDialog(ActivityMenu.this);

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




    private void librearMemor(){


        if(HelperImage.imAGESpdfSetGlobal!=null){
            HelperImage.imAGESpdfSetGlobal.clear();
            HelperImage.imAGESpdfSetGlobal=new ArrayList<>();

        }


        if(HelperImage.imAGESpdfSetGlobal!=null){
            HelperImage.imAGESpdfSetGlobal= new ArrayList<>();

        }


        if( Variables.CurrenReportPart1!=null){
            Variables.CurrenReportPart1=null;

        }
        if(Variables.CurrenReportPart2!=null){

            Variables.CurrenReportPart2=null;

        }

        if(Variables.CurrenReportPart3!=null){
            Variables.CurrenReportPart3=null;

        }

        if(Variables.listReprsVinculads!=null){
            Variables.listReprsVinculads= new ArrayList<>();
        }


        if(ImagenReport.hashMapImagesData!=null){
            ImagenReport.hashMapImagesData= null;
        }


        if(Variables.listImagenDataGlobalCurrentReport!=null){
            Variables.listImagenDataGlobalCurrentReport= null;
        }
        if(Variables.hashMapImagesStart!=null){
            Variables.hashMapImagesStart= null;
        }
    }




    void estableceHeaderTextAndListerner(){
        txtHeader=findViewById(R.id.txtHeader);
        txtSubHeader=findViewById(R.id.txtSubHeader);


        if(Variables.userGoogle!=null)
        {
            Log.i("solodataaqui", "el aqui tampoco es nulo");



            // When firebase user is not equal to null
            // Set image on image view
            Glide.with(ActivityMenu.this)
                    .load(Variables.userGoogle.getPhotoUrl())

                    .apply(RequestOptions.circleCropTransform())
                    //.circleCrop()
                    .into(imGProfile);
            // set name on text view


            txtHeader.setText(Variables.userGoogle.getDisplayName());
            txtSubHeader.setText(Variables.userGoogle.getEmail());

        }else{


            Log.i("solodataaqui", "aqui si es nulo");


        }


        if(userIniciosSesion){ ///mostramos el nombre y el cargo que tiene
            txtSubHeader.setOnClickListener(null);



        }else{
           // txtSubHeader.setEnabled(true);

            txtHeader.setText("!No has iniciado Sesion !");
            txtSubHeader.setText("Inicia sesion Aqui");

            ///LE CAMBISMO DE COLOR A UN


            txtSubHeader.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

        signInGoogle();
                }
            });



        }



    }



    private void signInGoogle() {



        Intent signInIntent =  mGoogleSignInClient.getSignInIntent();


        startActivityForResult(signInIntent, RC_SIGN_IN);
        Log.i("logingoogle","se puslo singin metodo2");



    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

      //  mCallbackManager.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);

                Log.i("defugero", "firebaseAuthWithGoogle:" + account.getId());
                Log.i("defugero", "firebaseAuthWithGoogle:" + account.getDisplayName());


                if(task.isSuccessful()){  ///vamohaber tareaspendientes

                }


                firebaseAuthWithGoogle(account.getIdToken());
                Log.i("defugero","se jecuito el try");

            } catch (ApiException e) {

                Log.i("defugero","se produjo un error ");
                Log.i("defugero","se produjo un error es "+e);
                // Google Sign In failed, update UI appropriately
                Log.w("defugero", "Google sign in failed", e);
            }
        }
    }
    // [END onactivityresult]



    private void firebaseAuthWithGoogle(String idToken) {  //se logea correctamente
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                          //  Log.d(TAG, "signInWithCredential:success");

                            Log.i("dataLogin","se ejecuto firebaseAuthWithGoogle()  succes");

                            Variables.  userGoogle = mAuth.getCurrentUser();


                            chelkeasIEXTSEuser(Variables.userGoogle.getEmail());
                             nombreYapllidoUser=Variables.userGoogle.getDisplayName();


                             userIniciosSesion=true;

                             estableceHeaderTextAndListerner();

                           // estableceHeaderTextAndListerner


                            /**verificar si por aqui creamos el nuevo nodo
                             * */

                            // creaNuevoUser(ediNomnre.getText().toString(),ediApellido.getText().toString(),numeroTelefonico.getText().toString(),email,1,0,contrasena_string,"");

                            //verificamos si existe un



                            // updateUI(user);
                        } else {
                            Log.i("dataLogin","se ejecuto firebaseAuthWithGoogle() else failure ");

                            Log.i("logingoogle","ocurrio un errro "+task.getException());

                            // If sign in fails, display a message to the user.
                         //   Log.w(TAG, "signInWithCredential:failure", task.getException());
                            // updateUI(null);
                        }
                    }
                });
    }
    // [END auth_with_google]




    private void inigoogleSigni(){


        mAuth = FirebaseAuth.getInstance(); //verificamos que si esta autentificado..
        // FirebaseDatabase.getInstance().setPersistenceEnabled(true); //anterior



        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("672391309758-af9tn52aj1icbel33c729ms9qemcpbdm.apps.googleusercontent.com")

                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        if(Variables.userGoogle==null){
            estableceHeaderTextAndListerner();

        }else{

            descragCurrentUsuario(Variables.userGoogle.getEmail());


        }

    }





    void chelkeasIEXTSEuser(String idMailGoogle){


        Query query = RealtimeDB.rootDatabaseReference.child("Usuarios").child("Colaboradores").orderByChild("mailGooglaUser").equalTo(idMailGoogle);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UsuarioQsercom user=null;

                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                     user=ds.getValue(UsuarioQsercom.class);


                }


                if(user !=null){


                    //existe

                }else{  //no existe los registramos...




                    Log.i("skjsf","el correo google es : "+idMailGoogle);


                    RealtimeDB.addNewUser(ActivityMenu.this,  new UsuarioQsercom("Colaborador", UUID.randomUUID().toString(),idMailGoogle,Variables.userGoogle.getDisplayName()));






                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }



    ///descragmos current usuario();



    void descragCurrentUsuario(String mailUserThisUser){


        Query query = RealtimeDB.rootDatabaseReference.child("Usuarios").child("ColaboradoresQsercom").orderByChild("mailGooglaUser").equalTo(mailUserThisUser);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    UsuarioQsercom usuarioQsercom=ds.getValue(UsuarioQsercom.class);

                    if(usuarioQsercom!=null){
                        Variables.usuarioQsercomGlobal=usuarioQsercom;

                        Log.i("hahsger","no es nulo");

                        checkIFuserIsActivatexx(Variables.usuarioQsercomGlobal.getMailGooglaUser());

                    }

                    estableceHeaderTextAndListerner();



                    Log.i("hahsger","esta por aqui esta data ");


                    break;


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }





    private void testCreateRegisters(){

     //   RealtimeDB.addNewRegistroInforme(ActivityMenu.this, new InformRegister("163349", Constants.CONTENEDORES,"Adriano Vicente","idaqui","Contenedores"));
       // RealtimeDB.addNewRegistroInforme(ActivityMenu.this, new InformRegister("", Constants.CUADRO_MUESTRO_CAL_RECHZDS,"Adriano Vicente","idaqui","Cudro Muestreo"));
      //  RealtimeDB.addNewRegistroInforme(ActivityMenu.this, new InformRegister("", Constants.CONTROL_CALIDAD,"Adriano Vicente","idaqui","Control CALIDAD"));
       // RealtimeDB.addNewRegistroInforme(ActivityMenu.this, new InformRegister("", Constants.CONTROL_CALIDAD,"Adriano Vicente","idaqui","Control CALIDAD"));
      //  RealtimeDB.addNewRegistroInforme(ActivityMenu.this, new InformRegister("", Constants.CONTROL_CALIDAD,"Adriano Vicente","idaqui","Control CALIDAD"));


    }






    public  void checkIFuserIsActivatexx(String mailGoogleUser){

        Log.i("holerr", "se llamo checkIFuserIsActivatexx ");


        DatabaseReference usersdRef = RealtimeDB.rootDatabaseReference.child("Usuarios").child("ColaboradoresQsercom");

        Query query = usersdRef.orderByChild("mailGooglaUser").equalTo(mailGoogleUser);


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Log.i("hahsger","el value de snpatshot es "+snapshot.getValue());
                UsuarioQsercom currentObect = null;
                if(snapshot.exists()) {

                    for (DataSnapshot ds : snapshot.getChildren()) {
                        currentObect = ds.getValue(UsuarioQsercom.class);
                    }


                    //   isUserAptobadoAccount[0] = currentObect.isUserISaprobadp() ;
                    //  System.out.println("worked");
                  //  currentObect=snapshot.getValue(UsuarioQsercom.class);



                    Log.i("holerr", "el user id es  "+currentObect.getUniqueIDuser());

                    //
                    Log.i("holerr", "el user se aprobo  "+currentObect.isUserISaprobadp());


                    if(currentObect.isUserISaprobadp()){

                        checkIRealTimeUserSiFaltaReportPorRevisa();
                    }

                }



                Log.i("hahsger", "se a llmado ondata change "+currentObect.isUserISaprobadp());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("isclkiel","el error es  "+ error.getMessage());


            }
        } );




    }



    private void checkIRealTimeUserSiFaltaReportPorRevisa () {

        final int[] informesPorRevfisarContador = {0};

        DatabaseReference usersdRef = RealtimeDB.rootDatabaseReference.child("Registros").child("InformesRegistros");

        // [START post_value_event_listener]
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    InformRegister informe=ds.getValue(InformRegister.class);


                    if(informe!=null){
                      if(!informe.isSeRevisoForm()){
                          informesPorRevfisarContador[0]++;
                      }

                    }


                }


                setTextAdviser(informesPorRevfisarContador[0]);







            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message



              //  Log.w(TAG, "loadPost:onCancelled", databaseError.toException());


            }
        };
        usersdRef.addValueEventListener(postListener);
        // [END post_value_event_listener]
    }



    private void setTextAdviser(int numsInformsPorRevisar){


        Log.i("misdtata","el numcontador es "+numsInformsPorRevisar);

        if(numsInformsPorRevisar >0){

            //SACTUALIZAMOS VIEWS
            btnInInformes.setText("Revisar Informes");
            txtAdviser.setText("Hay "+numsInformsPorRevisar+ " informes por revisar");
            txtAdviser2.setText("Dale!");
            //mostramos data...Hay un formulario a medias....


        }


    else



    {

        btnInInformes.setText("Revisar Informes");
        txtAdviser.setText("No hay tarea por ahora");
        txtAdviser2.setText("Hurra!");
        hayUnformulariAmediasPorSubir=false;

    }


    }


}