package com.tiburela.qsercom.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.models.PackingListMod;
import com.tiburela.qsercom.models.PackingModel;
import com.tiburela.qsercom.utils.Variables;

import java.util.HashMap;
import java.util.Map;

public class PackingListPreviewActivity extends AppCompatActivity {

    HashMap<String, String> packingListMap;
    Button btnSavePacking;

    private TextInputEditText mEdinumbox1;
    private TextInputEditText mEdiDescripcion1;
    private TextInputEditText mEdinumbox3;
    private TextInputEditText mEdiDescripcion3;
    private TextInputEditText mEdinumbox5;
    private TextInputEditText mEdiDescripcion5;
    private TextInputEditText mEdinumbox7;
    private TextInputEditText mEdiDescripcion7;
    private TextInputEditText mEdinumbox9;
    private TextInputEditText mEdiDescripcion9;
    private TextInputEditText mEdinumbox11;
    private TextInputEditText mEdiDescripcion11;
    private TextInputEditText mEdinumbox13;
    private TextInputEditText mEdiDescripcion13;
    private TextInputEditText mEdinumbox15;
    private TextInputEditText mEdiDescripcion15;
    private TextInputEditText mEdinumbox17;
    private TextInputEditText mEdiDescripcion17;
    private TextInputEditText mEdinumbox19;
    private TextInputEditText mEdiDescripcion18;
    private TextInputEditText mEdiDescripcion19;

    private TextInputEditText mEdinumbox2;
    private TextInputEditText mEdiDescripcion2;
    private TextInputEditText mEdinumbox4;
    private TextInputEditText mEdiDescripcion4;
    private TextInputEditText mEdinumbox6;
    private TextInputEditText mEdiDescripcion6;
    private TextInputEditText mEdinumbox8;
    private TextInputEditText mEdiDescripcion8;
    private TextInputEditText mEdinumbox10;
    private TextInputEditText mEdiDescripcion10;
    private TextInputEditText mEdinumbox12;
    private TextInputEditText mEdiDescripcion12;
    private TextInputEditText mEdinumbox14;
    private TextInputEditText mEdiDescripcion14;
    private TextInputEditText mEdinumbox16;
    private TextInputEditText mEdiDescripcion16;
    private TextInputEditText mEdinumbox18;
    private TextInputEditText mEdinumbox20;
    private TextInputEditText mEdiDescripcion20;
    private TextInputEditText mEdiProductor1;
    private TextInputEditText mEdiProductor2;
    private TextInputEditText mEdiProductor3;
    private TextInputEditText mEdiProductor4;
    private TextInputEditText mEdiProductor5;
    private TextInputEditText mEdiProductor6;
    private TextInputEditText mEdiProductor7;
    private TextInputEditText mEdiProductor8;
    private TextInputEditText mEdiProductor9;
    private TextInputEditText mEdiProductor10;

    private TextInputEditText mEdiCajas1;
    private TextInputEditText mEdiCajas2;
    private TextInputEditText mEdiCajas3;
    private TextInputEditText mEdiCajas4;
    private TextInputEditText mEdiCajas5;
    private TextInputEditText mEdiCajas6;
    private TextInputEditText mEdiCajas7;
    private TextInputEditText mEdiCajas8;
    private TextInputEditText mEdiCajas9;
    private TextInputEditText mEdiCajas10;

    private TextInputEditText mEdiCodigoN1;
    private TextInputEditText mEdiCodigoN3;
    private TextInputEditText mEdiCodigoN2;
    private TextInputEditText mEdiCodigoN4;

    private TextInputEditText mEdiCodigoN5;
    private TextInputEditText mEdiCodigoN6;
    private TextInputEditText mEdiCodigoN7;
    private TextInputEditText mEdiCodigoN8;
    private TextInputEditText mEdiCodigoN9;
    private TextInputEditText mEdiCodigoN10;


    private TextInputEditText mEdiTotalCajas;
    private TextInputEditText mEdiContenedorxzz;
    private TextInputEditText mEdiFechaHere;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packing_list2);

     findviewsIDS();

     RealtimeDB.initDatabasesRootOnly();

        getPakinkListMap(Variables.currenReportPackinList.getKeyOrNodeContainsMapPli());


        btnSavePacking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(CheckData()){ //si todo esta en orden....

                 if( checkAndCreateAllPackinListMap()){

                     if(packingListMap.size()>0){//si el packing list es mayor 0

                         showBottomSheetDialogConfirmAndCallUpdate();

                         //debe haber al menos un datao en el paking list

                     }else{
                         Toast.makeText(PackingListPreviewActivity.this, "No tienes datosen el packing list ", Toast.LENGTH_SHORT).show();


                         //probablemente mostrar un sheet here..
                     }


                   }


                }

            }
        });


    }



private boolean CheckData(){

        if(mEdiTotalCajas.getText().toString().isEmpty()){
            mEdiTotalCajas.requestFocus();
            mEdiTotalCajas.setError("Agrege el total de cajas");
            return false;
        }
        else if(mEdiContenedorxzz.getText().toString().isEmpty()){
            mEdiContenedorxzz.requestFocus();
            mEdiContenedorxzz.setError("Este datos es rquerido");
            return false;


        }

        else if(mEdiFechaHere.getText().toString().isEmpty()){
            mEdiFechaHere.requestFocus();
            mEdiFechaHere.setError("Selecione una fecha ");
            return false;

        }

       return  true;

}



   private void findviewsIDS(){
       btnSavePacking=findViewById(R.id.btnSavePacking);

         mEdiTotalCajas=findViewById(R.id.ediTotalCajas);
         mEdiContenedorxzz=findViewById(R.id.ediContenedorxzz);
         mEdiFechaHere=findViewById(R.id.ediFechaHere);





       mEdiDescripcion1 = findViewById(R.id.ediDescripcion1);
       mEdiDescripcion3 = findViewById(R.id.ediDescripcion3);
       mEdiDescripcion5 = findViewById(R.id.ediDescripcion5);
       mEdiDescripcion7 = findViewById(R.id.ediDescripcion7);
       mEdiDescripcion9 = findViewById(R.id.ediDescripcion9);


       mEdinumbox9 = findViewById(R.id.edinumbox9);
       mEdinumbox7 = findViewById(R.id.edinumbox7);
       mEdinumbox5 = findViewById(R.id.edinumbox5);
       mEdinumbox3 = findViewById(R.id.edinumbox3);
       mEdinumbox1 = findViewById(R.id.edinumbox1);
       mEdinumbox11 = findViewById(R.id.edinumbox11);
       mEdinumbox13 = findViewById(R.id.edinumbox13);
       mEdinumbox15 = findViewById(R.id.edinumbox15);
       mEdinumbox17 = findViewById(R.id.edinumbox17);
       mEdinumbox19 = findViewById(R.id.edinumbox19);
       mEdinumbox10 = findViewById(R.id.edinumbox10);
       mEdinumbox12 = findViewById(R.id.edinumbox12);
       mEdinumbox14 = findViewById(R.id.edinumbox14);
       mEdinumbox16 = findViewById(R.id.edinumbox16);
       mEdinumbox18 = findViewById(R.id.edinumbox18);
       mEdinumbox2 = findViewById(R.id.edinumbox2);
       mEdinumbox4 = findViewById(R.id.edinumbox4);
       mEdinumbox6 = findViewById(R.id.edinumbox6);
       mEdinumbox8 = findViewById(R.id.edinumbox8);
       mEdinumbox20 = findViewById(R.id.edinumbox20);



       mEdiDescripcion11 = findViewById(R.id.ediDescripcion11);
       mEdiDescripcion13 = findViewById(R.id.ediDescripcion13);
       mEdiDescripcion15 = findViewById(R.id.ediDescripcion15);
       mEdiDescripcion17 = findViewById(R.id.ediDescripcion17);

       mEdiDescripcion18 = findViewById(R.id.ediDescripcion18);
       mEdiDescripcion19 = findViewById(R.id.ediDescripcion19);

       mEdiDescripcion2 = findViewById(R.id.ediDescripcion2);
       mEdiDescripcion4 = findViewById(R.id.ediDescripcion4);
       mEdiDescripcion6 = findViewById(R.id.ediDescripcion6);
       mEdiDescripcion8 = findViewById(R.id.ediDescripcion8);
       mEdiDescripcion10 = findViewById(R.id.ediDescripcion10);
       mEdiDescripcion12 = findViewById(R.id.ediDescripcion12);
       mEdiDescripcion14 = findViewById(R.id.ediDescripcion14);
       mEdiDescripcion16 = findViewById(R.id.ediDescripcion16);
       mEdiDescripcion20 = findViewById(R.id.ediDescripcion20);

       mEdiProductor1 = findViewById(R.id.ediProductor1);
       mEdiProductor2 = findViewById(R.id.ediProductor2);
       mEdiProductor3 = findViewById(R.id.ediProductor3);
       mEdiProductor4=findViewById(R.id.ediProductor4 );
               mEdiProductor5 = findViewById(R.id.ediProductor5);
       mEdiProductor6 = findViewById(R.id.ediProductor6);
       mEdiProductor7 = findViewById(R.id.ediProductor7);
       mEdiProductor8 = findViewById(R.id.ediProductor8);
       mEdiProductor9 = findViewById(R.id.ediProductor9);
       mEdiProductor10 = findViewById(R.id.ediProductor10);



       mEdiCajas1 = findViewById(R.id.ediCajas1);
       mEdiCajas2 = findViewById(R.id.ediCajas2);
       mEdiCajas3 = findViewById(R.id.ediCajas3);
       mEdiCajas4 = findViewById(R.id.ediCajas4);
       mEdiCajas5 = findViewById(R.id.ediCajas5);
       mEdiCajas6 = findViewById(R.id.ediCajas6);
       mEdiCajas7 = findViewById(R.id.ediCajas7);
       mEdiCajas8 = findViewById(R.id.ediCajas8);
       mEdiCajas9 = findViewById(R.id.ediCajas9);
       mEdiCajas10 = findViewById(R.id.ediCajas10);

       mEdiCodigoN9=findViewById(R.id.ediCodigoN9);
       mEdiCodigoN8 = findViewById(R.id.ediCodigoN8);
       mEdiCodigoN1 = findViewById(R.id.ediCodigIGoN1);
       mEdiCodigoN2 = findViewById(R.id.ediCodigoN2);
       mEdiCodigoN4 = findViewById(R.id.ediCodigoN4);

       mEdiCodigoN3 = findViewById(R.id.ediCodigoN3);
       mEdiCodigoN5 = findViewById(R.id.ediCodigoN5);
       mEdiCodigoN6 = findViewById(R.id.ediCodigoN6);
       mEdiCodigoN7 = findViewById(R.id.ediCodigoN7);
       mEdiCodigoN10 = findViewById(R.id.ediCodigoN10);
       mEdiTotalCajas = findViewById(R.id.ediTotalCajas);
       mEdiContenedorxzz = findViewById(R.id.ediContenedorxzz);
       mEdiFechaHere = findViewById(R.id.ediFechaHere);


   }

    ///vasmos a crear un mapa usando un tag o el id.....


    private void Addpackings(String uniqueIDinforme) { //si el views esta lleno usamos ese valor del id ...

        TextInputEditText[] miArrayTexImputEditexts = createArrayOfEditext();

        HashMap<String, PackingModel> miMapaPacking = new HashMap<>();

        for (int i = 0; i < miArrayTexImputEditexts.length / 2; i++) { //solo recorremos la mitad


            if (!miArrayTexImputEditexts[i].getText().toString().isEmpty() && !miArrayTexImputEditexts[i + 10].getText().toString().isEmpty()) { //si el par no estan vacios

                String key = String.valueOf(miArrayTexImputEditexts[i].getId());
                String keyOfValorEditext = String.valueOf(miArrayTexImputEditexts[i + 10].getId());

                miMapaPacking.put(key, new PackingModel(miArrayTexImputEditexts[i].getText().toString(), miArrayTexImputEditexts[i + 10].getText().toString(), uniqueIDinforme, keyOfValorEditext));

            }

        }


        //AHORA SUBIMOS ESTA MAPA..SI SE NOS COMPLICA SUBIR EL MAPA CON VARIOS VALORES,  SOLO SUBIMOS UN VALOR DEL MAPA UNO POR UNO,,.


    }


    private TextInputEditText[] createArrayOfEditext() { //si el views esta lleno usamos ese valor del id ...

        TextInputEditText[] miArray = {


        };

        //entonces creamos un array y despues lo recorremos ,suando un for


        return miArray;
    }





private boolean checkAndCreateAllPackinListMap() {


//1 array edinumbox


    TextInputEditText[][] arrayEdisNumBoxAndediDescripcion = {{

            mEdinumbox1,  mEdinumbox2, mEdinumbox3, mEdinumbox4, mEdinumbox5, mEdinumbox6, mEdinumbox7, mEdinumbox8, mEdinumbox9, mEdinumbox10, mEdinumbox11,
            mEdinumbox12, mEdinumbox13, mEdinumbox14, mEdinumbox15, mEdinumbox16, mEdinumbox17, mEdinumbox18, mEdinumbox19, mEdinumbox20},


            //19

            {mEdiDescripcion1, mEdiDescripcion2, mEdiDescripcion3, mEdiDescripcion4, mEdiDescripcion5, mEdiDescripcion6, mEdiDescripcion7, mEdiDescripcion8,
                    mEdiDescripcion9, mEdiDescripcion10, mEdiDescripcion11, mEdiDescripcion12, mEdiDescripcion13, mEdiDescripcion14,
                    mEdiDescripcion15, mEdiDescripcion16,
                    mEdiDescripcion17, mEdiDescripcion18, mEdiDescripcion19, mEdiDescripcion20}};


//el par es edisNumBox/ediDescripcion

    //ediProductor1/ediCajas1
    TextInputEditText[][] arrayEdiProductoresAndDescripc = {{
            mEdiProductor1, mEdiProductor2, mEdiProductor3, mEdiProductor4, mEdiProductor5, mEdiProductor6, mEdiProductor7, mEdiProductor8, mEdiProductor9, mEdiProductor10
    }

            , {
            mEdiCajas1, mEdiCajas2, mEdiCajas3, mEdiCajas4, mEdiCajas5, mEdiCajas6, mEdiCajas7, mEdiCajas8, mEdiCajas9, mEdiCajas10

    }};


    TextInputEditText[] arrayCodigos =

            {
                    mEdiCodigoN1, mEdiCodigoN2, mEdiCodigoN3, mEdiCodigoN4, mEdiCodigoN5, mEdiCodigoN6, mEdiCodigoN7, mEdiCodigoN8,
                    mEdiCodigoN9, mEdiCodigoN10,

            };


    //ahora recorremos con 3 fors
     //primero for

    //iniciamos el mapa
    packingListMap= new HashMap<>();



        //chekeamos que haya data

    for(int indice2=0; indice2<arrayEdisNumBoxAndediDescripcion[0].length; indice2++){
        Log.i("misdatas","se eejctuo esto "+indice2+" veces");


        //chekeamos que haya dat

        //el primero tiene data y el otro no
        if(!arrayEdisNumBoxAndediDescripcion[0][indice2].getText(). toString().trim().isEmpty() && arrayEdisNumBoxAndediDescripcion[1][indice2].getText(). toString().trim().isEmpty() ){  ///
            arrayEdisNumBoxAndediDescripcion[1][indice2].requestFocus();
            arrayEdisNumBoxAndediDescripcion[1][indice2].setError("Falta este dato");
            Log.i("misdatas","se eejctuo primer  if");

            return false;

        }


        //el primero no tiene data y el segundo si
        if(arrayEdisNumBoxAndediDescripcion[0][indice2].getText(). toString().trim().isEmpty() && ! arrayEdisNumBoxAndediDescripcion[1][indice2].getText(). toString().trim().isEmpty() ){  ///
            arrayEdisNumBoxAndediDescripcion[0][indice2].requestFocus();
            arrayEdisNumBoxAndediDescripcion[0][indice2].setError("Falta este dato");
            Log.i("misdatas","se eejctuo este segundo if");

            return false;

        }

        //si ambs tienen data
        if(!arrayEdisNumBoxAndediDescripcion[0][indice2].getText().toString().trim().isEmpty() && !arrayEdisNumBoxAndediDescripcion[1][indice2].getText(). toString().trim().isEmpty() ){  ///

            String key1=String .valueOf(arrayEdisNumBoxAndediDescripcion[0][indice2].getId());
            String key2=String .valueOf(arrayEdisNumBoxAndediDescripcion[1][indice2].getId());

            packingListMap.put(key1,arrayEdisNumBoxAndediDescripcion[0][indice2].getText().toString());
            packingListMap.put(key2,arrayEdisNumBoxAndediDescripcion[1][indice2].getText().toString());

            Log.i("misdatas","esta tiene data z");


        }


    }



    //segundo array
        //chekeamos que haya data

        for(int indice2=0; indice2<arrayEdiProductoresAndDescripc.length; indice2++){
            //chekeamos que haya dat

            //el primero tiene data y el otro no
            if(!arrayEdiProductoresAndDescripc[0][indice2].getText(). toString().isEmpty() && arrayEdiProductoresAndDescripc[1][indice2].getText(). toString().isEmpty() ){  ///
                arrayEdiProductoresAndDescripc[1][indice2].requestFocus();
                arrayEdiProductoresAndDescripc[1][indice2].setError("Falta este dato");

                return false;
            }

            //el primero no tiene data y el segundo si
            if(arrayEdiProductoresAndDescripc[0][indice2].getText(). toString().isEmpty() && ! arrayEdiProductoresAndDescripc[1][indice2].getText(). toString().isEmpty() ){  ///
                arrayEdiProductoresAndDescripc[0][indice2].requestFocus();
                arrayEdiProductoresAndDescripc[0][indice2].setError("Falta este dato");
                return false;
            }



            if(!arrayEdiProductoresAndDescripc[0][indice2].getText().toString().isEmpty() && !arrayEdiProductoresAndDescripc[1][indice2].getText(). toString().isEmpty() ){  ///

                String key1=String .valueOf(arrayEdiProductoresAndDescripc[0][indice2].getId());
                String key2=String .valueOf(arrayEdiProductoresAndDescripc[1][indice2].getId());

                packingListMap.put(key1,arrayEdiProductoresAndDescripc[0][indice2].getText().toString());
                packingListMap.put(key2,arrayEdiProductoresAndDescripc[1][indice2].getText().toString());

            }

        }



    for(int indice2=0; indice2<arrayCodigos.length; indice2++){
        //chekeamos que haya dat

        String key=String.valueOf(arrayCodigos[indice2].getId());

        if(!arrayCodigos[indice2].getText().toString().trim().isEmpty()){

            packingListMap.put(key,arrayCodigos[indice2].getText().toString());

        }



    }

    return true;


    }


    private void setDaataOfViews(HashMap <String,String>miMapa, PackingListMod packingListModObj){
  ///agregamos algunos datos el objeto packing
        mEdiFechaHere.setText(packingListModObj.getSimpledatFormt());
        mEdiContenedorxzz.setText(packingListModObj.getContenedor());
        mEdiTotalCajas.setText(String.valueOf(packingListModObj.getTotalCajas()));
        btnSavePacking.setText("Guardar Cambios");


        TextInputEditText[] allArrayViewsTextIMPUTe =

                {mEdinumbox1, mEdinumbox2, mEdinumbox3, mEdinumbox4, mEdinumbox5, mEdinumbox6, mEdinumbox7, mEdinumbox8, mEdinumbox9, mEdinumbox10, mEdinumbox11, mEdinumbox12, mEdinumbox13
                        , mEdinumbox14, mEdinumbox15, mEdinumbox16, mEdinumbox17, mEdinumbox18, mEdinumbox19, mEdinumbox20,

                        mEdiDescripcion1, mEdiDescripcion2, mEdiDescripcion3, mEdiDescripcion4, mEdiDescripcion5, mEdiDescripcion6, mEdiDescripcion7, mEdiDescripcion8, mEdiDescripcion9, mEdiDescripcion10,
                        mEdiDescripcion11, mEdiDescripcion12, mEdiDescripcion13, mEdiDescripcion14, mEdiDescripcion15, mEdiDescripcion16,
                        mEdiDescripcion17, mEdiDescripcion18, mEdiDescripcion19, mEdiDescripcion20,

                        mEdiProductor1, mEdiProductor2, mEdiProductor3, mEdiProductor4, mEdiProductor5, mEdiProductor6, mEdiProductor7,
                        mEdiProductor8, mEdiProductor9, mEdiProductor10,

                        mEdiCajas1, mEdiCajas2, mEdiCajas3, mEdiCajas4, mEdiCajas5, mEdiCajas6, mEdiCajas7, mEdiCajas8, mEdiCajas9, mEdiCajas10,

                        mEdiCodigoN1, mEdiCodigoN2, mEdiCodigoN3, mEdiCodigoN4, mEdiCodigoN5, mEdiCodigoN6, mEdiCodigoN7, mEdiCodigoN8,
                        mEdiCodigoN9, mEdiCodigoN10,

                };


        for (Map.Entry<String, String > entry : miMapa.entrySet()) {
            String keyAndIdOfView = entry.getKey();
            String valueOfItem = entry.getValue();

            TextInputEditText currenTextImput=getTexImputEditextByidORkey(allArrayViewsTextIMPUTe,Integer.parseInt(keyAndIdOfView));

            if(currenTextImput==null){ //si es nulo

                Log.i("midata","este teximputeditext es nulo" +keyAndIdOfView);

                return;


            }else{

                currenTextImput.setText(valueOfItem);


            }


           //Agregamos este valor en este edi text

        }

        ///
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



//getPakinkListMap

    private void getPakinkListMap (String nodeKePackinGList){
        Log.i("hameha","el NODEKey es : "+nodeKePackinGList);

        ValueEventListener seenListener;



//        /
        //
       // DatabaseReference usersdRef = rootRef.child("Informes").child("PackingListMaps");

       /// Query query = usersdRef.orderByChild("uniqueIDinforme").equalTo(uniqeuIDiNFORME);


        seenListener = RealtimeDB.rootDatabaseReference.child("Informes").child("PackingListMaps").child(nodeKePackinGList).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               packingListMap=new HashMap<>();


                for (DataSnapshot dss : dataSnapshot.getChildren()) {
                    String key = dss.getKey();

                    String  fieldData =dss.getValue(String.class);

                 //   HashMap packinKey = dss.getValue( String.class);

                 //   Log.i("misadhd","el size del mapa es "+ packingListMap.size());
                    Log.i("hameha","el key es "+key);


                    if (fieldData!=null) {///

                        packingListMap.put(key,fieldData);


                        //    Map<String, Object> hashMap = new HashMap<>();
                        //    hashMap.put("isseen", new DatosDeProceso("",1,"","",2,""));
                        //   dss.getRef().updateChildren(hashMap);



                      //  setDatosProcesODataInViews(Variables.mimapaDatosProcesMapCurrent);



                    }
                }

                setDaataOfViews(packingListMap,Variables.currenReportPackinList);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("misadhd","el error es "+ databaseError.getMessage());



            }
        });




    }


    private  void showBottomSheetDialogConfirmAndCallUpdate( ) {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(PackingListPreviewActivity.this);

        bottomSheetDialog.setContentView(R.layout.bottom_sheet_confirm_changes);

        Button btnSi=bottomSheetDialog.findViewById(R.id.btnSi);
        Button btnNo=bottomSheetDialog.findViewById(R.id.btnNo);


        btnSi.setOnClickListener(new View.OnClickListener() { //revisar

            @Override
            public void onClick(View v) {


                PackingListMod objePackingList=new PackingListMod(Integer.parseInt(mEdiTotalCajas.getText().toString()),mEdiContenedorxzz.getText().toString());
                objePackingList.setTimeCurrenMillisecds(Variables.currenReportPackinList.getTimeCurrenMillisecds());

                //actualizamos data
                RealtimeDB.updateNewPackingListHasMap(packingListMap,Variables.currenReportPackinList);
                RealtimeDB.updatePackingListObject(objePackingList,Variables.currenReportPackinList);
                //guardamos data...


                Toast.makeText(PackingListPreviewActivity.this, "Hecho", Toast.LENGTH_SHORT).show();
                 finish();
                bottomSheetDialog.dismiss();


            }
        });



        btnNo.setOnClickListener(new View.OnClickListener() {  //activar switch
            @Override
            public void onClick(View v) {

                bottomSheetDialog.dismiss();
            }
        });


        bottomSheetDialog.show();
    }


}




