package com.tiburela.qsercom.activities;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiburela.qsercom.R;
import com.tiburela.qsercom.models.EstateFieldView;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import java.util.ArrayList;
import java.util.HashMap;


public class ActivityMenu extends AppCompatActivity {
ImageView imgContenedores;
SetInformEmbarque1 informeObjct;
Button btnInInformes;
TextView txtAdviser,txtAdviser2;
    private static final int PERMISSION_REQUEST_CODE=100;
     private boolean hayUnformulariAmediasPorSubir =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Utils.dataFieldsPreferencias=new HashMap<String,String>();
        Utils.listImagesToSaVE=new ArrayList<>();


        EstateFieldView.adddataListsStateFields();
        txtAdviser=findViewById(R.id.txtAdviser);
        txtAdviser2=findViewById(R.id.txtAdviser2);

        btnInInformes =findViewById(R.id.btnIformesRevisar);
        imgContenedores=findViewById(R.id.imgContenedores);

         imgContenedores.setOnClickListener(new View.OnClickListener() {


             @Override
             public void onClick(View view) {



                 startActivity(new Intent(ActivityMenu.this,FormularioActivity.class ));


             }
         });


         btnInInformes.setOnClickListener(new View.OnClickListener() {
             @RequiresApi(api = Build.VERSION_CODES.M)
             @Override
             public void onClick(View view) {


                 if(Variables.tipOuSER ==Variables.CALIFICADOR_OFICINA) {

                      startActivity(new Intent(ActivityMenu.this, ActivitySeeReports.class));



                 }else {  //si es inspector de campo

                     Intent intencion = new Intent(ActivityMenu.this,FormularioActivity.class);

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

        if(Variables.tipOuSER == Variables.CALIFICADOR_CAMPO) {  //chekeamos si tiene algun informe incloncluso...
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

        else if(Variables.tipOuSER==Variables.CALIFICADOR_OFICINA) {

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

        showDataByMode();

    }
}