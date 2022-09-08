package com.tiburela.qsercom.auth;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Auth {
    public static FirebaseAuth mAuth;
    public static FirebaseUser firebaseUser;


    public static void autUser()  {


    }


    public static void initAuth(Context context)  {

     //   FirebaseApp.initializeApp(context);

        if(mAuth == null) {
            mAuth =FirebaseAuth.getInstance();

        }

// Initialize Firebase Auth




    }

    public static  void signInAnonymously(Activity activity) {
        // [START signin_anonymously]
        mAuth.signInAnonymously()
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInAnonymously:success");
                             firebaseUser = mAuth.getCurrentUser();
                          //  updateUI(user);  ///si la autentificacion sali bien....
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInAnonymously:failure", task.getException());
                         //   Toast.makeText(AnonymousAuthActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                          //  updateUI(null);
                        }
                    }
                });
        // [END signin_anonymously]
    }







}
