package com.tiburela.qsercom.database;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.ProductPostCosecha;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RealtimeDB {

 static  public DatabaseReference rootDatabaseReference ;

static  public  DatabaseReference mibasedataPathImages;





    //ESTA VCLASED VA A ENCRAGARSE DE CREAR MODIFICAR ,BORRAR DATOS DE LA BASE DE DATOS REALTIME


    public static  void initDatabasesReference(){

        rootDatabaseReference = FirebaseDatabase.getInstance().getReference(); //anterior

        mibasedataPathImages = rootDatabaseReference.child("Informes").child("ImagesData");


    }



    private void editInform(){


    }




    public static void addNewInforme(Context context, SetInformEmbarque1 informeObjct) {
        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listInformes");

           //agregamos la propiedad keyFirebase a al objeto
        String PuskEY = mibasedata.push().getKey();

        informeObjct.setKeyFirebase(PuskEY);
            Map<String, Object> mapValues = informeObjct.toMap();



        mibasedata.child(PuskEY).setValue(mapValues).addOnCompleteListener(new OnCompleteListener<Void>() {



            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                   // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }

    public static void actualizaInformePart1( SetInformEmbarque1 informeObjct) {

        ///"-NCHQnVUUyMat8l_SSwh"
        Log.i("elides","el key o child al que perteence este objeto es "+informeObjct.getKeyFirebase());

        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listInformes").child("-NCHQnVUUyMat8l_SSwh");

        Map<String, Object> mapValues = informeObjct.toMap(); //lo convertimos en maP

        mibasedata.updateChildren(mapValues).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }

    public static void actualizaInformePart2( SetInformEmbarque2 informeObjc2) {
        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listInformes").child(informeObjc2.getKeyFirebase());

        Map<String, Object> mapValues = informeObjc2.toMap(); //lo convertimos en maP

        mibasedata.updateChildren(mapValues).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }




    public static void addNewInforme(Context context, SetInformEmbarque2 informeObjct) {
        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listInformes");

        //agregamos la propiedad keyFirebase a al objeto
        String PuskEY = mibasedata.push().getKey();

        informeObjct.setKeyFirebase(PuskEY);

        Map<String, Object> mapValues = informeObjct.toMap();

        //SUBE MAPA
        mibasedata.child(PuskEY).setValue(mapValues).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    //lo borramos...

                    try {
                       Utils.deleteMap(context);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }


    public static void addNewSetPicsInforme(Context context, ImagenReport objecImageReport ) {

       if(mibasedataPathImages==null ) {
           initDatabasesReference();

       }


        Map<String, Object> mapValues = objecImageReport.toMap();

        //SUBE MAPA
        mibasedataPathImages.push().setValue(mapValues).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                    Toast.makeText(context, "HECHO", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }



    public static void UploadProductosPostCosecha( ProductPostCosecha producto) {

        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listProductosPostCosecha");
       // Map<String, Object> mapValues = informeObjct.toMap();
        String PuskEY = mibasedata.push().getKey();

        producto.keyFirebase=PuskEY;
        //SUBE MAPA
        mibasedata.child(PuskEY).setValue(producto).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }
    public static void UpdateProductosPostCosecha( ProductPostCosecha productosObject) {

        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listProductosPostCosecha").child(productosObject.keyFirebase);
        // Map<String, Object> mapValues = informeObjct.toMap();


        //SUBE MAPA
        mibasedata.setValue(productosObject).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }



    private static void getKeyOfImagenDataObjecRTD(String NameImageunique,String nuevaDescripcion){
//        mibasedataPathImages = rootDatabaseReference.child("Informes").child("ImagesData");
        mibasedataPathImages .child(NameImageunique)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String KeyNodeFatheroFObject = snapshot.getKey(); // will be efg

                         if(KeyNodeFatheroFObject !=null){

                             ///editamos el objeto

                             editValue(KeyNodeFatheroFObject,nuevaDescripcion);



                         }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }


                });
}





//un bucle que contendra una lista que recorremos

    public static void actualizaDescripcionIms(ArrayList<String>listKeyTosearch){

      try {

          for(int indice=0; indice<listKeyTosearch.size(); indice++){

              String[] array = listKeyTosearch.get(indice).split("@");


              Log.i("samasu","el texto es "+listKeyTosearch.get(indice));


              String keyCurrentToEdit=array[0]; //key primero /depsues el content
              String contentDescripcion=array[1];

            //  getKeyOfImagenDataObjecRTD();

               //si esta key esta esta en la lista copia start

              if(Variables.hashMapImagesStart.containsKey(keyCurrentToEdit)){ ///si esta en esta lista esta en la db

                  getkeyActualizaDescripcion(keyCurrentToEdit,contentDescripcion);

              }



          }

      } catch (Exception e) {
          Log.i("samasu","el excepcion es "+e.getMessage().toString());


          e.printStackTrace();
      }




    }


    private static void editValue(String keyAtoUpdate, String descripcion ){

        try{

            HashMap<String, Object> update = new HashMap<>(); //CAMBIAMOS LA PROPIEDA TITULO  Y LE GRAGAMOS EL NUEVO TITULO..

            update.put("descripcionImagen", descripcion);//


            mibasedataPathImages.child(keyAtoUpdate).updateChildren(update);//

        } catch (Exception e) {
            e.printStackTrace();
        }




    }



    private static void getkeyActualizaDescripcion(String NameImageunique, String nuevaDescripcion){

        Query query = mibasedataPathImages.orderByChild("uniqueIdNamePic").equalTo(NameImageunique);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                DataSnapshot nodeShot = dataSnapshot.getChildren().iterator().next();
                String key = nodeShot.getKey();
                //   private void editaValue(String keyAtoUpdate,String titulo, String descripcion, String direccion, String ubicacionCordenaGoogleMap, String picNameofStorage, double cuponValor, String categoria,boolean switchActivate, boolean switchDestacado){

               // String KeyNodeFatheroFObject = snapshot.getKey(); // will be efg

                if(key !=null){

                    ///editamos el objeto

                    editValue(key,nuevaDescripcion);



                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



}
