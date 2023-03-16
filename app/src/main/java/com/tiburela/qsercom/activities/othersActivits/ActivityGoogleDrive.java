package com.tiburela.qsercom.activities.othersActivits;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.gson.Gson;
import com.tiburela.qsercom.drive.DriveServiceHelper;
import com.tiburela.qsercom.drive.GoogleDriveFileHolder;

import java.util.Collections;

import com.tiburela.qsercom.R;


public class ActivityGoogleDrive extends AppCompatActivity {
    Drive  googleDriveService;
    private static final int REQUEST_CODE_SIGN_IN = 100;
    private GoogleSignInClient mGoogleSignInClient;
    private DriveServiceHelper mDriveServiceHelper;
    private static final String TAG = "MainActivity";
    private Button login;
    private LinearLayout gDriveAction;
    private Button searchFile;
    private Button searchFolder;
    private Button createTextFile;
    private Button createFolder;
    private Button uploadFile;
    private Button downloadFile;
    private Button deleteFileFolder;
    private TextView email;
    private Button viewFileFolder;
    private  Scope ACCESS_DRIVE_SCOPE;
    private   Scope SCOPE_EMAIL;
 private SignInButton btnSigningGoogle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();

         ACCESS_DRIVE_SCOPE = new Scope(Scopes.DRIVE_FILE);
         SCOPE_EMAIL = new Scope(Scopes.EMAIL);


        btnSigningGoogle=findViewById(R.id.btnSigningGoogle);
        btnSigningGoogle.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                signInGoogle();

            }
        });

        searchFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDriveServiceHelper == null) {
                    return;
                }


                /*
                mDriveServiceHelper.searchFile("textfilename.txt", "text/plain")
                        .addOnSuccessListener(new OnSuccessListener<List<GoogleDriveFileHolder>>() {
                            @Override
                            public void onSuccess(List<GoogleDriveFileHolder> googleDriveFileHolders) {

                                Gson gson = new Gson();
                                Log.d(TAG, "onSuccess: " + gson.toJson(googleDriveFileHolders));
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "onFailure: " + e.getMessage());
                            }
                        });


                 */

            }
        });

        searchFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDriveServiceHelper == null) {
                    return;
                }



                /*
                mDriveServiceHelper.searchFolder("testDummy")
                        .addOnSuccessListener(new OnSuccessListener<List<GoogleDriveFileHolder>>() {
                            @Override
                            public void onSuccess(List<GoogleDriveFileHolder> googleDriveFileHolders) {
                                Gson gson = new Gson();
                                Log.d(TAG, "onSuccess: " + gson.toJson(googleDriveFileHolders));
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "onFailure: " + e.getMessage());
                            }
                        });
                */


            }
        });

        createTextFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDriveServiceHelper == null) {
                    return;
                }
                // you can provide  folder id in case you want to save this file inside some folder.
                // if folder id is null, it will save file to the root

               /*
                mDriveServiceHelper.createTextFile("textfilename.txt", "some text", null)
                        .addOnSuccessListener(new OnSuccessListener<GoogleDriveFileHolder>() {
                            @Override
                            public void onSuccess(GoogleDriveFileHolder googleDriveFileHolder) {
                                Gson gson = new Gson();
                                Log.d(TAG, "onSuccess: " + gson.toJson(googleDriveFileHolder));
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "onFailure: " + e.getMessage());
                            }
                        });



                */
            }
        });

        createFolder.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                if (mDriveServiceHelper == null) {
                    Log.i("sailo", " mDriveServiceHelper es nulo");

                    return;
                }
                // you can provide  folder id in case you want to save this file inside some folder.
                // if folder id is null, it will save file to the root
                //foldername= nombre carpeta que crearemos //folder id es la caperpta donde crearemos la carpe

                mDriveServiceHelper.createFolder("02/02/adrianito", null)
                        .addOnSuccessListener(new OnSuccessListener<GoogleDriveFileHolder>() {
                            @Override
                            public void onSuccess(GoogleDriveFileHolder googleDriveFileHolder) {
                                Gson gson = new Gson();
                                Log.i("sailo", "onSuccess: " + gson.toJson(googleDriveFileHolder));
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("sailo", "onFailure: " + e.getMessage());



                            }
                        });
            }
        });

        uploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mDriveServiceHelper == null) {
                    return;
                }


                mDriveServiceHelper.uploadFile(new java.io.File(getApplicationContext().getFilesDir(), "dummy.txt"), "text/plain", "1SYslex9vNhIbIw_wZzjUig2HKefxLp0u")
                        .addOnSuccessListener(new OnSuccessListener<GoogleDriveFileHolder>() {
                            @Override
                            public void onSuccess(GoogleDriveFileHolder googleDriveFileHolder) {
                                Gson gson = new Gson();
                                Log.d(TAG, "onSuccess: " + gson.toJson(googleDriveFileHolder));
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "onFailure: " + e.getMessage());
                            }
                        });




            }
        });

        downloadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDriveServiceHelper == null) {
                    return;
                }
                java.io.File file = new java.io.File(getExternalFilesDir(null), "appstore.png");
                mDriveServiceHelper.downloadFile(file, "1kDzr50tPJBk9Gb56Vdms4kkjnN4wsy8b")

                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Log.i("miOdata","es succe4sw data");

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("miOdata","es failure ,error this: "+e.getMessage());

                            }
                        });
            }
        });

        viewFileFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (mDriveServiceHelper == null) {
//                    return;
//                }
//
//                mDriveServiceHelper.queryFiles()
//                        .addOnSuccessListener(new OnSuccessListener<List<GoogleDriveFileHolder>>() {
//                            @Override
//                            public void onSuccess(List<GoogleDriveFileHolder> googleDriveFileHolders) {
//                                Gson gson = new Gson();
//                                Log.d(TAG, "onSuccess: " + gson.toJson(googleDriveFileHolders));
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//
//                            }
//                        });

              /*
                Intent openActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(openActivity);

*/

            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());

        if (account == null) {

            signIn();

        } else {


            email.setText(account.getEmail());

           // mDriveServiceHelper = new DriveServiceHelper(getGoogleDriveService(getApplicationContext(), account, "appName"));

            driveSetUp();

        }
    }

    private void signIn() {

       // mGoogleSignInClient = buildGoogleSignInClient();
    //    startActivityForResult(mGoogleSignInClient.getSignInIntent(), REQUEST_CODE_SIGN_IN);
    }



/*
    private GoogleSignInClient buildGoogleSignInClient() {
        GoogleSignInOptions signInOptions =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestScopes(Drive.DEFAULT_SERVICE_PATH)
                        .requestEmail()
                        .build();
        return GoogleSignIn.getClient(getApplicationContext(), signInOptions);
    }

*/


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        switch (requestCode) {
            case REQUEST_CODE_SIGN_IN:
                if (resultCode == Activity.RESULT_OK && resultData != null) {
                    handleSignInResult(resultData);
                }
                break;


        }

        super.onActivityResult(requestCode, resultCode, resultData);
    }

    public void test() {
        System.out.println("test");
    }

    private void handleSignInResult(Intent result) {
        GoogleSignIn.getSignedInAccountFromIntent(result)
                .addOnSuccessListener(new OnSuccessListener<GoogleSignInAccount>() {
                    @Override
                    public void onSuccess(GoogleSignInAccount googleSignInAccount) {
                        Log.i("sailo", "Signed in as " + googleSignInAccount.getEmail());
                        email.setText(googleSignInAccount.getEmail());

                      //  mDriveServiceHelper = new DriveServiceHelper(getGoogleDriveService(getApplicationContext(), googleSignInAccount, "appName"));
                        driveSetUp();
                        Log.i("sailo", "handleSignInResult: " + mDriveServiceHelper);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("sailo", "Unable to sign in.", e);
                    }
                });
    }

    private void initView() {

        email = findViewById(R.id.email);
        gDriveAction = findViewById(R.id.g_drive_action);
        searchFile = findViewById(R.id.search_file);
        searchFolder = findViewById(R.id.search_folder);
        createTextFile = findViewById(R.id.create_text_file);
        createFolder = findViewById(R.id.create_folder);
        uploadFile = findViewById(R.id.upload_file);
        downloadFile = findViewById(R.id.download_file);
        deleteFileFolder = findViewById(R.id.delete_file_folder);
        viewFileFolder = findViewById(R.id.view_file_folder);
    }

    private void driveSetUp() {

        GoogleSignInAccount mAccount = GoogleSignIn.getLastSignedInAccount(ActivityGoogleDrive.this);

        GoogleAccountCredential credential =
                GoogleAccountCredential.usingOAuth2(
                        getApplicationContext(), Collections.singleton(Scopes.DRIVE_FILE));
        credential.setSelectedAccount(mAccount.getAccount());
        googleDriveService = new com.google.api.services.drive.Drive.Builder(
                        AndroidHttp.newCompatibleTransport(),
                        new GsonFactory(),
                        credential)
                        .setApplicationName("GoogleDriveIntegration 3")
                        .build();
        mDriveServiceHelper = new DriveServiceHelper(googleDriveService);


        checkForGooglePermissions();
    }






    private void checkForGooglePermissions() {

        if (!GoogleSignIn.hasPermissions(
                GoogleSignIn.getLastSignedInAccount(getApplicationContext()),
                ACCESS_DRIVE_SCOPE,
                SCOPE_EMAIL)) {
            GoogleSignIn.requestPermissions(
                    ActivityGoogleDrive.this, REQUEST_CODE_SIGN_IN,
                    GoogleSignIn.getLastSignedInAccount(getApplicationContext()),
                    ACCESS_DRIVE_SCOPE,
                    SCOPE_EMAIL);
        } else {
            //Toast.makeText(this, "Permission to access Drive and Email has been granted", Toast.LENGTH_SHORT).show();
          //  driveSetUp();

        }

    }


    private void signInGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        GoogleSignInClient mGoogleSignInClient;
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();

        startActivityForResult(signInIntent, REQUEST_CODE_SIGN_IN);
        Log.i("logingoogle","se puslo singin metodo2");



    }

}