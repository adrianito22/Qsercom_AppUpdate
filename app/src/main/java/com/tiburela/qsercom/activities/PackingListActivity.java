package com.tiburela.qsercom.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.models.EstateFieldView;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.PackingModel;

import java.util.HashMap;

public class PackingListActivity extends AppCompatActivity {

    TextInputEditText edinumbox1;
    TextInputEditText edinumbox2;
    TextInputEditText edinumbox3;
    TextInputEditText edinumbox4;
    TextInputEditText edinumbox5;
    TextInputEditText edinumbox6;
    TextInputEditText edinumbox7;
    TextInputEditText edinumbox8;
    TextInputEditText edinumbox9;
    TextInputEditText edinumbox10;

    TextInputEditText edinumbox11;
    TextInputEditText edinumbox12;
    TextInputEditText edinumbox13;

    TextInputEditText edinumbox14;
    TextInputEditText edinumbox15;
    TextInputEditText edinumbox16;
    TextInputEditText edinumbox17;
    TextInputEditText edinumbox18;
    TextInputEditText edinumbox19;
    TextInputEditText edinumbox20;


    TextInputEditText ediDescripcion1;
    TextInputEditText ediDescripcion2;
    TextInputEditText ediDescripcion3;
    TextInputEditText ediDescripcion4;
    TextInputEditText ediDescripcion5;
    TextInputEditText ediDescripcion6;
    TextInputEditText ediDescripcion7;
    TextInputEditText ediDescripcion8;
    TextInputEditText ediDescripcion9;
    TextInputEditText ediDescripcion10;

    TextInputEditText ediDescripcion11;
    TextInputEditText ediDescripcion12;
    TextInputEditText ediDescripcion13;
    TextInputEditText ediDescripcion14;
    TextInputEditText ediDescripcion15;
    TextInputEditText ediDescripcion16;
    TextInputEditText ediDescripcion17;
    TextInputEditText ediDescripcion18;
    TextInputEditText ediDescripcion19;
    TextInputEditText ediDescripcion20;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packing_list2);
    }



    void findViewsIds( ) {

        edinumbox1  =findViewById(R.id.edinumbox1 );
        edinumbox2 =findViewById(R.id.edinumbox2 );
        edinumbox3 =findViewById(R.id.edinumbox3 );
        edinumbox4 =findViewById(R.id.edinumbox4 );
        edinumbox5 =findViewById(R.id.edinumbox5 );
        edinumbox6 =findViewById(R.id.edinumbox6 );
        edinumbox7 =findViewById(R.id.edinumbox7 );
        edinumbox8 =findViewById(R.id.edinumbox8 );
        edinumbox9 =findViewById(R.id.edinumbox9 );
        edinumbox10 =findViewById(R.id.edinumbox10 );
         edinumbox11=findViewById(R.id.edinumbox11);
         edinumbox12=findViewById(R.id.edinumbox12);
         edinumbox13=findViewById(R.id.edinumbox13);
         edinumbox14=findViewById(R.id.edinumbox14);
         edinumbox15=findViewById(R.id.edinumbox15);
         edinumbox16=findViewById(R.id.edinumbox16);
         edinumbox17=findViewById(R.id.edinumbox17);
         edinumbox18=findViewById(R.id.edinumbox18);
         edinumbox19=findViewById(R.id.edinumbox19);
         edinumbox20=findViewById(R.id.edinumbox20);



        ediDescripcion1 =findViewById(R.id.ediDescripcion1 );
        ediDescripcion2 =findViewById(R.id.ediDescripcion2 );
        ediDescripcion3 =findViewById(R.id.ediDescripcion3 );
        ediDescripcion4 =findViewById(R.id.ediDescripcion4 );
        ediDescripcion5 =findViewById(R.id.ediDescripcion5 );
        ediDescripcion6 =findViewById(R.id.ediDescripcion6 );
        ediDescripcion7 =findViewById(R.id.ediDescripcion7 );
        ediDescripcion8 =findViewById(R.id.ediDescripcion8 );
        ediDescripcion9 =findViewById(R.id.ediDescripcion9 );
        ediDescripcion10 =findViewById(R.id.ediDescripcion10 );



         ediDescripcion11=findViewById(R.id.ediDescripcion11);
         ediDescripcion12=findViewById(R.id.ediDescripcion12);
         ediDescripcion13=findViewById(R.id.ediDescripcion13);
         ediDescripcion14=findViewById(R.id.ediDescripcion14);
         ediDescripcion15=findViewById(R.id.ediDescripcion15);
         ediDescripcion16=findViewById(R.id.ediDescripcion16);
         ediDescripcion17=findViewById(R.id.ediDescripcion17);
         ediDescripcion18=findViewById(R.id.ediDescripcion18);
    }

    ///vasmos a crear un mapa usando un tag o el id.....



    private void Addpackings(String uniqueIDinforme) { //si el views esta lleno usamos ese valor del id ...

        TextInputEditText   [] miArrayTexImputEditexts=createArrayOfEditext();

        HashMap<String, PackingModel>miMapaPacking=new HashMap<>();

        for(int i = 0; i< miArrayTexImputEditexts.length/2; i++) { //solo recorremos la mitad


            if(!miArrayTexImputEditexts[i].getText().toString().isEmpty()  &&  !miArrayTexImputEditexts[i+10].getText().toString().isEmpty()) { //si el par no estan vacios

                String key=String.valueOf(miArrayTexImputEditexts[i].getId());
                String keyOfValorEditext=String.valueOf(miArrayTexImputEditexts[i+10].getId());

                miMapaPacking.put(key, new PackingModel(miArrayTexImputEditexts [i].getText().toString(),miArrayTexImputEditexts [i+10].getText().toString(),uniqueIDinforme,keyOfValorEditext ));

            }

        }



        //AHORA SUBIMOS ESTA MAPA..SI SE NOS COMPLICA SUBIR EL MAPA CON VARIOS VALORES,  SOLO SUBIMOS UN VALOR DEL MAPA UNO POR UNO,,.


    }




    private TextInputEditText  [] createArrayOfEditext() { //si el views esta lleno usamos ese valor del id ...

       TextInputEditText   [] miArray = {

               edinumbox1, //0
               edinumbox2,
               edinumbox3,
               edinumbox4,
               edinumbox5,
               edinumbox6,
               edinumbox7,
               edinumbox8,
               edinumbox9,
               edinumbox11,
               edinumbox12,
               edinumbox13,
               edinumbox14,
               edinumbox15,
               edinumbox16,
               edinumbox18,
               edinumbox19,
               edinumbox20,





               ediDescripcion1,
               ediDescripcion2,
               ediDescripcion3,
               ediDescripcion4,
               ediDescripcion5,
               ediDescripcion6,
               ediDescripcion7,
               ediDescripcion8,
               ediDescripcion9,
               ediDescripcion10,
               ediDescripcion11,
               ediDescripcion12,
               ediDescripcion13,
               ediDescripcion14,
               ediDescripcion15,
               ediDescripcion16,
               ediDescripcion17,
               ediDescripcion18,
               ediDescripcion19,
               ediDescripcion20,



       } ;

        //entonces creamos un array y despues lo recorremos ,suando un for



        return miArray;
    }
}