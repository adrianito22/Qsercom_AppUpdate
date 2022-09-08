package com.tiburela.qsercom.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.tiburela.qsercom.models.EstateFieldView;

import java.util.ArrayList;

import com.tiburela.qsercom.R;


public class MainActivity2 extends AppCompatActivity implements View.OnTouchListener {
    int typeUser;
    View root;
    String contrasena_string;
    private EditText mEditText;
    String stringNumeroTelefonico;
    boolean estamostrandoPasword=false;
    Button ocultaYmuestrbtn2;
    EditText ediNomnre;
    EditText ediPasword;
    EditText ediApellido;
    EditText ediNumeroTelefonico;
    EditText ediCorreo;
    Button btnSubir;
    String email="";
    String userIDCurrentUser;
    EditText nombreComercio;

    View vistaAnterior;

    Switch switchComerciox;
    LinearLayout laynombre,layoutapellido,layoutcorreo,layoutpassword,layoutnumerotelefonico;



    ArrayList  <EstateFieldView>listCamposAllenar;
    ArrayList  <View> listViewsClickedUser;


    // TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainActivity2() {
// Required empty public constructor
    }

    public MainActivity2(EditText editText) {
        mEditText = editText;
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        findViewsId();


        agregatouchs();

        iinicilizaObjeEstadoField();

        listViewsClickedUser =new ArrayList<>();


        eventoBtns();





    }




    @SuppressLint("ClickableViewAccessibility")
    private void agregatouchs(){

        ediNomnre.setOnTouchListener(this);
        ediApellido.setOnTouchListener(this);
        ediCorreo.setOnTouchListener(this);

        ediPasword.setOnTouchListener(this);
        ediNumeroTelefonico.setOnTouchListener(this);




        //contrasena_Editxt.setOnTouchListener(this);
        //  numeroTelefonico.setOnTouchListener(this);
    }







    public void obtieneTexto(){

        //vamos a validar un numero telefonico.. 09 o 902588885 con 8 numeros....
        //si no no es


        stringNumeroTelefonico= ediNumeroTelefonico.getText().toString();
        email = ediCorreo.getText().toString();
        contrasena_string= ediPasword.getText().toString();


        if(! ediNomnre.getText().toString().matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$")) {
            ediNomnre.setError("introduce un nombre valido");
            ediNomnre.requestFocus();
            return;



        }
        if(! ediApellido.getText().toString().matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$")) {
            ediApellido.setError("introduce un apellido valido");
            ediApellido.requestFocus();
            return;

        }


        if (email.isEmpty()) {
            ediCorreo.setError("Correo es requerido");
            ediCorreo.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            ediCorreo.setError("Por favor ingrese un correo valido");
            ediCorreo.requestFocus();

            return;
        }

        //private void creaNuevoUser(String nombre,String apellido,String correoElectronico,int userIdCategory,int nivelVerificacion,String password){





        if(stringNumeroTelefonico.isEmpty()){
            ediNumeroTelefonico.setError("Numero telefonico es requerido");
            ediNumeroTelefonico.requestFocus();
            return;

        }


        else { //aqui validamos el formato del numero


            if( stringNumeroTelefonico.length()<4 || stringNumeroTelefonico.length()>15){ //el rpimero substring tiene que ser cero y el 2segundo 9
                //   Toast.makeText(this, "escribe un numero en este formato 09xxxxxxxx", Toast.LENGTH_SHORT).show();
                ediNumeroTelefonico.setError("Escribe un número valido");
                ediNumeroTelefonico.requestFocus();
                return;

            }
            //tienq que tener.....//inserte el numero en este formato..



        }






        if (contrasena_string.isEmpty()) {
            ediPasword.setError("contrasena es requerida");
            ediPasword.requestFocus();
            return;
        }

        if (contrasena_string.length() < 6) {
            ediPasword.setError("el tamano minimo de contrasena es 6");
            ediPasword.requestFocus();
            return;
        }




        if(switchComerciox.isChecked()){


            if(nombreComercio.getText().toString().isEmpty()){ //obtenemos el texto y lo gaudamos en el string
                nombreComercio.setError("Este campo no pued estar vacio");
                nombreComercio.requestFocus();
                return;

            }
            //ultima



            if(nombreComercio.getText().toString().length()<3){ //obtenemos el texto y lo gaudamos en el string
                nombreComercio.setError("Nombre muy Corto");
                nombreComercio.requestFocus();
                return;

            }


        }









    }



    private void findViewsId(){


        ediNomnre=findViewById(R.id.edinombre);
        ediApellido=findViewById(R.id.ediapellido);
        ediNumeroTelefonico =findViewById(R.id.ediNumeroTelefonico);
        ediCorreo =findViewById(R.id.ediCorreo);
        btnSubir =findViewById(R.id.btnSubir);
        ediPasword =findViewById(R.id.ediPasword);
        ocultaYmuestrbtn2=findViewById(R.id.ocultaYmuestrbtn2);


        laynombre=findViewById(R.id.laynombre);
        layoutapellido=findViewById(R.id.layoutapellido);
        layoutcorreo=findViewById(R.id.layoutcorreo);
        layoutpassword=findViewById(R.id.layoutpassword);
        layoutnumerotelefonico=findViewById(R.id.layoutnumerotelefonico);




    }

    private void eventoBtns(){
        btnSubir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                obtieneTexto();


            }
        });


    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if(motionEvent.getAction()==MotionEvent.ACTION_DOWN ){


            //esta comprobacion de instancia la cxhekeamos en el metodo  getVistaAnteriorClick();
            if( view instanceof EditText ) { //asi obtenemos la vista...y obtenemos el texto..
                //  EditText textView = (EditText) view;
                //BUCSAMOS ESTE TAG PROPIEDAD...EN LA LISTA DE OBJETOS....
                //agregamos esta vista clickada a la lista

                Log.i("casnasd","se llamo on touch ");

                listViewsClickedUser.add(view);

                Log.i("casnasd","el size de la lista es "+listViewsClickedUser.size());

                if(listViewsClickedUser.size()>1){
                    getVistaAnteriorClick();
                }


            }

        }



        return false;
    }




    private void buscaSiEsteFieldEstaCompletado() {
        //obtenemos el click anterior....


       for(int i=0; i<listCamposAllenar.size() ; i++) {



       }



    }



    private void iinicilizaObjeEstadoField() { //el estado puede ser lleno o vacio isEstaLleno
        /*
        listCamposAllenar= new ArrayList<>();
        listCamposAllenar.add(new EstateFieldView("edinombre"));
        listCamposAllenar.add(new EstateFieldView("ediapellido"));
        listCamposAllenar.add(new EstateFieldView("ediCorreo"));
        listCamposAllenar.add(new EstateFieldView("ediPasword"));
        listCamposAllenar.add(new EstateFieldView("ediNumeroTelefonico"));
*/


    }

    private View getVistaAnteriorClick() { //el estado puede ser lleno o vacio isEstaLleno


        if(listViewsClickedUser.size() ==3) { //SOLO GUARDAMOS DOS NUMEROS para ahorra memoria
                    listViewsClickedUser.remove(0);   //ya no queremoes el primer objeto de la lista siempre y cuando la lista contnega 3 objetos

                }
        Log.i("casnasd","el size aqui en metodo es "+listViewsClickedUser.size());




       View vistAnterior = listViewsClickedUser.get(0);
        Log.i("soeobjetc","el objeto anterioR TAG ES "+vistAnterior.getTag().toString());

        //una foprma de comprobar el tipo de vista y en base a eso comprobar si la tarea de esta vista esta completa...

        //if es un editexdt comprobar si hay texcto----si hay texto entonces devulve true..

        //si es un image.. chekear que halla imagenes el minimo requerido

        //si la vista es de tipo redio button .. chekear que halla selecionado una opcion...
        //si hay una opcion selecionada devulve true ....si no es falso y asi hacemos...

        //si el user a completado el campo de dicha vista o la tarea como ejemplo fotos..selecionar un spinner..




        return   vistAnterior;

    }

/**https://ammar.lanui.online/integrate-google-drive-rest-api-on-android-app-bc4ddbd90820*/
//example uso de api rest drive
}