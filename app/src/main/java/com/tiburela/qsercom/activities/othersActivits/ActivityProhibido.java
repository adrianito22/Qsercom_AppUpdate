package com.tiburela.qsercom.activities.othersActivits;

import static com.tiburela.qsercom.dialog_fragment.DialogConfirmChanges.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.models.UsuarioQsercon;
import com.tiburela.qsercom.utils.Variables;

public class ActivityProhibido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prohibido);

        if(Variables.usuarioQserconGlobal!=null){
            seeUserActivate(Variables.usuarioQserconGlobal);

        }

    }



    private void seeUserActivate(UsuarioQsercon objec) {

        DatabaseReference databaseReference = RealtimeDB.rootDatabaseReference.child("Usuarios").child("Colaboradores").child(objec.getKeyLocaliceUser());

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                UsuarioQsercon user = dataSnapshot.getValue(UsuarioQsercon.class);

                if(user!=null){

                    Variables.usuarioQserconGlobal=user;

                    if(Variables.usuarioQserconGlobal.isUserISaprobadp()){

                        Intent intent = new Intent(getApplicationContext(), ActivityMenu.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);


                    }

                }


                // ..
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        databaseReference.addValueEventListener(postListener);
        ///si el usuario esta navegando
        //si cambio el nodo actual.....verifica si esta baneado...

    }

}