package com.tiburela.qsercom.PdfMaker;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.UsuarioQsercom;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

public class BackroundUserIsAproveed implements Runnable{
boolean userISaproveed=false;
 public static String  mailGoogleUser="adriapps10@gmail.com";


    @Override
    public void run() {
/*
        Log.i("hahsger","vamos a metodo check ");

        final TaskCompletionSource<UsuarioQsercom> tcs = new TaskCompletionSource<>();


        //  CountDownLatch done = new CountDownLatch(1);
        final boolean isUserAptobadoAccount[] = {false};

        DatabaseReference usersdRef = RealtimeDB.rootDatabaseReference.child("Usuarios").child("ColaboradoresQsercom");

        Query query = usersdRef.orderByChild("mailGooglaUser").equalTo(mailGoogleUser);


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Log.i("misdaterr","el value de snpatshot es "+snapshot.getValue());
                UsuarioQsercom currentObect = null;
                if(snapshot.exists()){


                    for (DataSnapshot ds : snapshot.getChildren()) {
                        currentObect=ds.getValue(UsuarioQsercom.class);
                    }



                    isUserAptobadoAccount[0] = currentObect.isUserISaprobadp() ;
                    //  System.out.println("worked");

                    Log.i("hahsger","el object es  "+ currentObect.isUserISaprobadp());

                    Log.i("hahsger","se a llmado ondata change ");

                    tcs.setResult(currentObect);

                    //  done.countDown();

                }else{


                    Log.i("hahsger","se ejecuto el else ");

                }







            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("isclkiel","el error es  "+ error.getMessage());


            }
        } );



        Task<UsuarioQsercom> t = tcs.getTask();

        try {
            Tasks.await(t);
        } catch (ExecutionException | InterruptedException e) {
            t = Tasks.forException(e);
        }

        if(t.isSuccessful()) {
            UsuarioQsercom result = t.getResult();

            Log.i("hahsger","devolvemos el valor que ahora es  "+result.isUserISaprobadp());


           // return isUserAptobadoAccount[0];

        }

    */
    }





/*

        try {
          //  done.await(); //it will wait till the response is received from firebase.
        } catch(InterruptedException e) {
            Log.i("hahsger","la excepcion es  "+e.getMessage());

            e.printStackTrace();
        }
*/

        //  return isUserAptobadoAccount[0];






    public boolean getVAaluIsAproveed() {
        return userISaproveed;
    }
}
