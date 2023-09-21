package com.tiburela.qsercom.dialog_fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.models.CalibrFrutCalEnf;
import com.tiburela.qsercom.models.ContenedoresEnAcopio;
import com.tiburela.qsercom.models.ControlCalidad;
import com.tiburela.qsercom.models.DatosDeProceso;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.InformRegister;
import com.tiburela.qsercom.models.ProductPostCosecha;
import com.tiburela.qsercom.models.ReportCamionesyCarretas;
import com.tiburela.qsercom.models.SetInformDatsHacienda;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;
import com.tiburela.qsercom.storage.StorageDataAndRdB;
import com.tiburela.qsercom.utils.SharePrefHelper;
import com.tiburela.qsercom.utils.Variables;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class BottonSheetCallUploading2 extends BottomSheetDialogFragment {
    
    static final StorageReference ImageFolderReferenceImagesAll = FirebaseStorage.getInstance().getReference().child("imagenes_all_reports");
    public static final String TAG = "ActionBottomDialog";
    static int activityIdx;
    static Bitmap bitmapOriginal;
    static Button btnOkButton;
    static CalibrFrutCalEnf calendariox;
    static ReportCamionesyCarretas camionesyCarretasx;
    static ContenedoresEnAcopio contenedoresAcx;
    static Context context;
    static ControlCalidad controlCalidadX;
    static ImagenReport currenImageReport;
    static byte[] data;
    static Handler handler1 = new Handler();
    static HashMap<String, String> hasHmapOtherFieldsEditxsx;
    static HashMap<String, String> hasMapitemsSelecPosicRechazToUploadx;
    static ArrayList<String> idDeleteIMgesList;
    static StorageReference imagename;
    static ImageView imgIcon;
    static InformRegister informRegister;
    static SetInformEmbarque1 informe1;
    static SetInformEmbarque2 informe2;
    static SetInformDatsHacienda informe3;
    static InputStream inputStream;
    /* access modifiers changed from: private */
    public static String keyPrefrencesIfUserSaveReportLocale = "";
    static ArrayList<ImagenReport> listImagesx;
    static HashMap<String, DatosDeProceso> miMaProcesoX;
    static HashMap<String, Float> miMapLbriado;
    static ProductPostCosecha productosPoscosecha;
    static ProgressBar progressBar;
    static Query query;
    public static StorageReference rootStorageReference;
    static ByteArrayOutputStream stream;
    static Thread thread;
    static TextView txtImagenesfAIL;
    static TextView txtSubTitle;
    static TextView txtTitle;
    static UploadTask uploadTask;
    static DatabaseReference usersdRef;
    String textoImagenesPorSubir = "";
    TrheadUploadImages thread1;
    private View vista;


    ///
    public static BottonSheetCallUploading2 newInstance(Context context2, ControlCalidad controlCalidad, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, InformRegister informRegister2, int i) {
        context = context2;
        controlCalidadX = controlCalidad;
        hasMapitemsSelecPosicRechazToUploadx = hashMap2;
        hasHmapOtherFieldsEditxsx = hashMap;
        informRegister = informRegister2;
        activityIdx = i;
        return new BottonSheetCallUploading2();
    }

    /**Contenedores*/
    public static BottonSheetCallUploading2 newInstance(Context context2, SetInformEmbarque1 setInformEmbarque1, SetInformEmbarque2 setInformEmbarque2, SetInformDatsHacienda setInformDatsHacienda, InformRegister informRegister2, ProductPostCosecha productPostCosecha, ArrayList<ImagenReport> arrayList, HashMap<String, Float> hashMap, int i) {
        context = context2;
        informe1 = setInformEmbarque1;
        informe2 = setInformEmbarque2;
        informe3 = setInformDatsHacienda;
        informRegister = informRegister2;
        productosPoscosecha = productPostCosecha;
        listImagesx = arrayList;
        activityIdx = i;
        miMapLbriado = hashMap;
        return new BottonSheetCallUploading2();
    }

    public static BottonSheetCallUploading2 newInstance(Context context2, ContenedoresEnAcopio contenedoresEnAcopio, InformRegister informRegister2, HashMap<String, DatosDeProceso> hashMap, ArrayList<ImagenReport> arrayList, int i) {
        contenedoresAcx = contenedoresEnAcopio;
        context = context2;
        miMaProcesoX = hashMap;
        informRegister = informRegister2;
        listImagesx = arrayList;
        activityIdx = i;
        return new BottonSheetCallUploading2();
    }

    public static BottonSheetCallUploading2 newInstance(Context context2, ReportCamionesyCarretas reportCamionesyCarretas, CalibrFrutCalEnf calibrFrutCalEnf, InformRegister informRegister2, ProductPostCosecha productPostCosecha, ArrayList<ImagenReport> arrayList, int i) {
        calendariox = calibrFrutCalEnf;
        camionesyCarretasx = reportCamionesyCarretas;
        context = context2;
        informRegister = informRegister2;
        productosPoscosecha = productPostCosecha;
        listImagesx = arrayList;
        activityIdx = i;
        return new BottonSheetCallUploading2();
    }

    public static BottonSheetCallUploading2 newInstance(Context context2, ReportCamionesyCarretas reportCamionesyCarretas, CalibrFrutCalEnf calibrFrutCalEnf, ProductPostCosecha productPostCosecha, ArrayList<ImagenReport> arrayList, int i) {
        calendariox = calibrFrutCalEnf;
        camionesyCarretasx = reportCamionesyCarretas;
        context = context2;
        productosPoscosecha = productPostCosecha;
        listImagesx = arrayList;
        activityIdx = i;
        return new BottonSheetCallUploading2();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        keyPrefrencesIfUserSaveReportLocale = getArguments().getString("keyPrefrencesReportCurrent", "");
        View inflate = layoutInflater.inflate(R.layout.layout_botton_sheetcd, viewGroup, false);
        this.vista = inflate;
        progressBar = (ProgressBar) inflate.findViewById(R.id.progressBar);
        txtTitle = (TextView) this.vista.findViewById(R.id.txtAdviser);
        txtSubTitle = (TextView) this.vista.findViewById(R.id.txtSubheader);
        imgIcon = (ImageView) this.vista.findViewById(R.id.imgIcon);
        btnOkButton = (Button) this.vista.findViewById(R.id.btnOkButton);
        txtImagenesfAIL = (TextView) this.vista.findViewById(R.id.txtImagenesfAIL);
        btnOkButton.setEnabled(false);
        Variables.contador = 0;
        StorageDataAndRdB.indiceCurrentOFlistIamges = 0;
        btnOkButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FragmentActivity activity = getActivity();
                if (activity != null) {
                    activity.finish();
                }
                try {
                    dismiss();
                } catch (Exception e) {
                    Log.i("misfafa", "la expecion es " + e.getMessage());
                }
            }
        });


        decideMethodCallUpdate(activityIdx);


        return this.vista;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Log.i("ontatch", "se ejecuto onViewCreated");
    }

    public void onAttach(Context context2) {
        super.onAttach(context2);
    }

    public void onStart() {
        super.onStart();
        Log.i("ontatch", "se ejecuto onStart");
    }

    public static void uploadConteendoresForm(int i) {
        if (i == Variables.OBJECT_SetInformEmbarque1) {

            RealtimeDB.addNewDSetinformEmarque1(informe1);

        } else if (i == Variables.OBJECT_SetInformEmbarque2) {

            RealtimeDB.addNewInformeEmbarque2(context, informe2);
            Log.i("finalizando", "SECOND");

        } else if (i == Variables.OBJECT_SetInformDatsHacienda) {
            RealtimeDB.addNewDSetinformEmarque1(informe3);
        } else if (i == Variables.LIBRIADO_IF_EXIST) {
            RealtimeDB.addNewhasmapPesoBrutoClosters2y3L(miMapLbriado, informe1.getKeyOrNodeLibriadoSiEs());
        } else if (i == Variables.PRODUCTS_POST_COSECHA) {
            RealtimeDB.UploadProductosPostCosecha(productosPoscosecha);
        } else if (i == Variables.INFORM_REGISTER) {
            RealtimeDB.addNewRegistroInforme(context, informRegister);
        } else if (i == Variables.IMAGENES_SET_DE_REPORTE) {
            new BottonSheetCallUploading2().callThreadImagenesUpload();
            if (!keyPrefrencesIfUserSaveReportLocale.equals("")) {
                SharePrefHelper.UpdateRegisterLOCALEMarcaSubido(true, keyPrefrencesIfUserSaveReportLocale);
            }
        }
    }

    public static void uploadCamionesYcarretas(int i) {
        if (i == Variables.OBJECT_CAMIONESYCARRETAS) {
            RealtimeDB.addNewReportCalidaCamionCarrretas(camionesyCarretasx);
        } else if (i == Variables.PRODUCTS_POST_COSECHA) {
            RealtimeDB.UploadProductosPostCosecha(productosPoscosecha);
        } else if (i == 8555174) {
            RealtimeDB.UploadCalibracionFrutCal(calendariox);


        } else if (i == Variables.INFORM_REGISTER) {
            RealtimeDB.addNewRegistroInforme(context, informRegister);
        } else if (i == Variables.IMAGENES_SET_DE_REPORTE) {
            new BottonSheetCallUploading2().callThreadImagenesUpload();
            if (!keyPrefrencesIfUserSaveReportLocale.equals("")) {
                SharePrefHelper.UpdateRegisterLOCALEMarcaSubido(true, keyPrefrencesIfUserSaveReportLocale);
            }
        }
    }

    public static void updateCamionesYcarretas(int i) {
        Log.i("samisas", "update camiones y c");
        if (i == Variables.OBJECT_CAMIONESYCARRETAS) {
            Log.i("samisas", "update camiones y c  1");
            RealtimeDB.updateCalidaCamionCarrretas(camionesyCarretasx);
        } else if (i == Variables.PRODUCTS_POST_COSECHA) {
            Log.i("samisas", "update camiones y c  2");
            RealtimeDB.UpdateProductosPostCosecha(productosPoscosecha);
        } else if (i == 8555174) {
            Log.i("samisas", "update camiones y c  3");
            RealtimeDB.UpdateCalibracionFrutCal(calendariox);
        } else if (i == 96389) {
            geTidAndDelete(0);
        } else if (i == Variables.IMAGENES_SET_DE_REPORTE) {
            Log.i("samisas", "update camiones y c  4");
            new BottonSheetCallUploading2().callThreadImagenesUpload();
        }
    }

    /* access modifiers changed from: private */
    public static void geTidAndDelete(int i) {
        final int[] iArr = {i};
        if (i < Variables.listImagesToDelete.size()) {
            query = RealtimeDB.rootDatabaseReference.child("Informes").child("ImagesData").orderByChild("uniqueIdNamePic").equalTo(Variables.listImagesToDelete.get(i));
            usersdRef = RealtimeDB.rootDatabaseReference.child("Informes").child("ImagesData");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                public void onCancelled(DatabaseError databaseError) {
                }

                public void onDataChange(DataSnapshot dataSnapshot) {
                    try {
                        usersdRef.child(dataSnapshot.getChildren().iterator().next().getKey()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(Task<Void> task) {
                                if (task.isSuccessful()) {
                                    iArr[0] = iArr[0] + 1;
                                    BottonSheetCallUploading2.geTidAndDelete(iArr[0]);
                                    Log.i("eliminamos", "aqui se elimino esto");
                                }
                            }
                        });
                    } catch (Exception e) {
                        Log.i("eliminamos", "aqui hay una expecion y es " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            });
        } else if (Variables.activityCurrent == 10071) {
            updateCamionesYcarretas(Variables.IMAGENES_SET_DE_REPORTE);
        } else if (Variables.activityCurrent == 10060) {
            UpdateConteendores(Variables.IMAGENES_SET_DE_REPORTE);
        } else if (Variables.activityCurrent == 10069) {
            updateContenedresEnAcopio(Variables.IMAGENES_SET_DE_REPORTE);
        }
    }

    public static void uploadConteendoresEnAcopio(int i) {
        if (i == 10067) {
            RealtimeDB.addNewInformContenresAcopio(contenedoresAcx);
        } else if (i == 963) {
            RealtimeDB.addDatosProceso(miMaProcesoX, contenedoresAcx.getDatosProcesoContenAcopioKEYFather());
        } else if (i == Variables.INFORM_REGISTER) {
            RealtimeDB.addNewRegistroInforme(context, informRegister);
        } else if (i == Variables.IMAGENES_SET_DE_REPORTE) {
            new BottonSheetCallUploading2().callThreadImagenesUpload();
            if (!keyPrefrencesIfUserSaveReportLocale.equals("")) {
                SharePrefHelper.UpdateRegisterLOCALEMarcaSubido(true, keyPrefrencesIfUserSaveReportLocale);
            }
        }
    }

    public static void updateContenedresEnAcopio(int i) {
        if (i == 10069) {
            RealtimeDB.updateInformContenresAcopio(contenedoresAcx);
        } else if (i == 963) {
            RealtimeDB.UpadateDatosProceso(miMaProcesoX, contenedoresAcx.getDatosProcesoContenAcopioKEYFather());
        } else if (i == 96389) {
            geTidAndDelete(0);
        } else if (i == Variables.IMAGENES_SET_DE_REPORTE) {
            new BottonSheetCallUploading2().callThreadImagenesUpload();
            if (!keyPrefrencesIfUserSaveReportLocale.equals("")) {
                SharePrefHelper.UpdateRegisterLOCALEMarcaSubido(true, keyPrefrencesIfUserSaveReportLocale);
            }
        }
    }

    public void callThreadImagenesUpload() {
        Variables.numImagenesSubirTotal = listImagesx.size();
        Variables.contadorImagenesSubidasSumaAll = 0;
        Variables.contadorImagenesFall = 0;
        StorageDataAndRdB.initContexta(context);
        Log.i("IMAGESTASKEdit", "la cantidad de imagenes a subir es: " + Variables.numImagenesSubirTotal);
        String str = "" + Variables.contadorImagenesSubidasSumaAll + " imagenes subidas de " + Variables.numImagenesSubirTotal;
        this.textoImagenesPorSubir = str;
        txtTitle.setText(str);
        TrheadUploadImages trheadUploadImages = new TrheadUploadImages(listImagesx, 1);
        this.thread1 = trheadUploadImages;
        trheadUploadImages.start();
    }

    public static void UpdateConteendores(int i) {
        if (i == Variables.SEVERAL_INFORMS_UPDATE) {
            RealtimeDB.updateSetinformEmbarq1(informe1);
        } else if (i == 1001) {
            RealtimeDB.actualizaInformePart2(informe2);
        } else if (i == 1002) {
            RealtimeDB.actualizaInformePart3(informe3);
        } else if (i == Variables.LIBRIADO_IF_EXIST) {
            RealtimeDB.UpdateHasmapPesoBrutoClosters2y3L(miMapLbriado, informe1.getKeyOrNodeLibriadoSiEs());
        } else if (i == Variables.PRODUCTS_POST_COSECHA) {
            RealtimeDB.UpdateProductosPostCosecha(productosPoscosecha);
        } else if (i == 96389) {
            geTidAndDelete(0);
        } else if (i == Variables.IMAGENES_SET_DE_REPORTE) {
            new BottonSheetCallUploading2().callThreadImagenesUpload();
        }
    }

    public static void UploadControlCalidad(final int i) {
        final int[] iArr = {0};
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                if (i == Variables.CONTROL_CALIDAD_OBJECT) {
                    RealtimeDB.UploadControlcalidadInform(controlCalidadX);
                    iArr[0] = 20;
                } else {
                    if (i == 1003) {


                        if(controlCalidadX==null){
                            Log.i("midfadfffd","es nulo");

                        }else{
                            Log.i("midfadfffd","no es nulo");

                        }



                        if(controlCalidadX.getKeyWhereLocateasHmapFieldsRecha()==null){
                            Log.i("midfadfffd","propiedad es nula");

                        }else {
                            Log.i("midfadfffd","propiedad no es nula");

                        }


                        RealtimeDB.addNewHashMapControlCalidad(hasHmapOtherFieldsEditxsx, controlCalidadX.getKeyWhereLocateasHmapFieldsRecha());
                        iArr[0] = 30;
                    } else if (i == 1004) {
                        RealtimeDB.uploadHasmapDefectSelec(hasMapitemsSelecPosicRechazToUploadx, controlCalidadX.getKeyDondeEstaraHasmapDefecSelec());
                        iArr[0] = 40;
                       // Log.i("elformasd", "FINISH_ALL_UPLOAD  Y EL KEY ES " + BottonSheetCallUploading2.keyPrefrencesIfUserSaveReportLocale);
                    } else if (i == Variables.INFORM_REGISTER) {
                        RealtimeDB.addNewRegistroInforme(context, informRegister);
                        iArr[0] = 50;
                       // Log.i("elformasd", "FINISH_ALL_UPLOAD  Y EL KEY ES " + BottonSheetCallUploading2.keyPrefrencesIfUserSaveReportLocale);
                    } else if (i == Variables.FINISH_ALL_UPLOAD) {
                      //  Log.i("elformasd", "FINISH_ALL_UPLOAD  Y EL KEY ES " + BottonSheetCallUploading2.keyPrefrencesIfUserSaveReportLocale);
                       // Log.i("elformasd", "FINISH_ALL_UPLOAD  Y EL KEY ES " + BottonSheetCallUploading2.keyPrefrencesIfUserSaveReportLocale);
                        SharePrefHelper.UpdateRegisterLOCALEMarcaSubido(true, keyPrefrencesIfUserSaveReportLocale);
                        iArr[0] = 100;
                    } else if (i == Variables.ERROR_SUBIDA) {
                        iArr[0] = 1000;
                        Log.i("updatexxxx", "FINISH_ALL_UPLOAD");
                    }
                }
                handler1.post(new Runnable() {
                    public void run() {
                        if (progressBar == null) {
                            return;
                        }
                        if (iArr[0] == 20) {
                            progressBar.setProgress(20);
                            Log.i("finalizando", "value percent es igual a 100");
                        } else if (i == Variables.ERROR_SUBIDA || iArr[0] == 1000) {

                            progressBar.setProgress(0);
                            Log.i("updatexxxx", ":( error ");
                            txtSubTitle.setText("Se produjo un error :(");
                            txtTitle.setText("0% COMPLETADO ");
                            btnOkButton.setVisibility(View.VISIBLE);
                            imgIcon.setImageResource(R.drawable.baseline_check_circle_24);
                            btnOkButton.setEnabled(true);

                        } else if (iArr[0] == 100) {

                            progressBar.setProgress(100);
                            Log.i("updatexxxx", "update todos hurra");
                            txtSubTitle.setText("Hurra, se subio");
                            txtTitle.setText("100% COMPLETADO");
                            btnOkButton.setVisibility(View.VISIBLE);
                            imgIcon.setImageResource(R.drawable.baseline_check_circle_24);
                            btnOkButton.setEnabled(true);

                        }
                    }
                });
            }
        });
        thread = thread2;
        thread2.start();
    }

    public static void updatControlCalidad(final int indice2) {
        final int[] iArr = {0};
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                if (indice2 == Variables.CONTROL_CALIDAD_OBJECT) {
                    iArr[0] = 20;
                    RealtimeDB.UpdateControlcalidadInform(controlCalidadX,controlCalidadX.getKeyDondeEstarThisInform());
                
                
                } else {
                    int i = indice2;
                    if (i == 1003) {
                        iArr[0] = 30;
                        Log.i("elformasd", "FINISH_ALL_UPLOAD  Y EL KEY ES " + keyPrefrencesIfUserSaveReportLocale);
                        RealtimeDB.updateHashMapControlCalidad(hasHmapOtherFieldsEditxsx, controlCalidadX.getKeyWhereLocateasHmapFieldsRecha());
                    } else if (i == 1004) {
                        iArr[0] = 40;
                        Log.i("elformasd", "FINISH_ALL_UPLOAD  Y EL KEY ES " + keyPrefrencesIfUserSaveReportLocale);
                        RealtimeDB.updateHasmapDefectSelec(hasMapitemsSelecPosicRechazToUploadx, controlCalidadX.getKeyDondeEstaraHasmapDefecSelec());
                    } else if (i == Variables.FINISH_ALL_UPLOAD) {
                        Log.i("elformasd", "FINISH_ALL_UPLOAD  Y EL KEY ES " + keyPrefrencesIfUserSaveReportLocale);
                        iArr[0] = 100;
                    } else if (i == Variables.ERROR_SUBIDA) {
                        iArr[0] = 1000;
                        Log.i("updatexxxx", "FINISH_ALL_UPLOAD");
                    }
                }
                handler1.post(new Runnable() {
                    public void run() {
                        if (progressBar == null) {
                            return;
                        }
                        if (indice2 == Variables.ERROR_SUBIDA || iArr[0] == 1000) {
                            progressBar.setProgress(0);
                            Log.i("updatexxxx", ":( error ");
                            txtSubTitle.setText("Se produjo un error :(");
                            txtTitle.setText("0% COMPLETADO ");
                            btnOkButton.setVisibility(View.VISIBLE);
                            imgIcon.setImageResource(R.drawable.baseline_check_circle_24);
                            btnOkButton.setEnabled(true);
                        } else if (iArr[0] == 100) {
                            progressBar.setProgress(100);
                            Log.i("updatexxxx", "update todos hurra");
                            txtSubTitle.setText("Hurra, se subio");
                            txtTitle.setText("100% COMPLETADO");
                            btnOkButton.setVisibility(View.VISIBLE);
                            imgIcon.setImageResource(R.drawable.baseline_check_circle_24);
                            btnOkButton.setEnabled(true);
                        }
                    }
                });
            }
        });
        thread = thread2;
        thread2.start();
    }

    public class TrheadUploadImages extends Thread {
        ArrayList<ImagenReport> arrayListx;
        public int contadorImagenesSubidasThisObject = 0;
        public int indiceCurrentObjectx = 0;
        boolean seSubioAlLImagenesSet;
        public int threadNUm;

        public TrheadUploadImages(ArrayList<ImagenReport> arrayList, int i) {
            this.arrayListx = arrayList;
            this.threadNUm = i;
            this.seSubioAlLImagenesSet = false;
        }

        public void run() {
            Log.i("subprocesa", "el rhread num num " + this.threadNUm);
            int i = (int) ((((float) Variables.contadorImagenesSubidasSumaAll) / ((float) Variables.numImagenesSubirTotal)) * 100.0f);
            textoImagenesPorSubir = "" + Variables.contadorImagenesSubidasSumaAll + " imagenes subidas de " + Variables.numImagenesSubirTotal;
            txtTitle.setText(textoImagenesPorSubir);
            if (Variables.contadorImagenesSubidasSumaAll == thread1.arrayListx.size() || thread1.indiceCurrentObjectx >= thread1.arrayListx.size() || Variables.contadorImagenesSubidasSumaAll == Variables.numImagenesSubirTotal) {
                thread1.seSubioAlLImagenesSet = true;
                Log.i("IMAGESTASKEdit", "la lista 1 READY OK BIEN ");
                i = 100;
            } else {
                try {
                    uploaddImagesAndDataImages1(thread1.arrayListx.get(thread1.indiceCurrentObjectx));
                } catch (IOException e) {
                    Log.i("ege", "excpecion es " + e.getMessage());
                }
            }
            final int[] iArr = {i};
            handler1.post(new Runnable() {
                public void run() {
                    progressBar.setProgress(iArr[0]);
                    txtImagenesfAIL.setText("Error imagenes subidas: " + Variables.contadorImagenesFall);
                    if (iArr[0] == 0) {
                        txtSubTitle.setText("Espere");
                        txtSubTitle.setText("Subiendo imagenes...");
                        progressBar.setProgress(20);
                    }
                    if (iArr[0] == 100) {
                        Log.i("finalizando", "value percent es igual a 100");
                        txtSubTitle.setText("Hurra, se subió exitosamente");
                        txtTitle.setText("100% COMPLETADO");
                        btnOkButton.setVisibility(View.VISIBLE);
                        imgIcon.setImageResource(R.drawable.baseline_check_circle_24);
                        btnOkButton.setEnabled(true);
                    }
                }
            });
        }

        /* JADX WARNING: Removed duplicated region for block: B:10:0x0087  */
        /* JADX WARNING: Removed duplicated region for block: B:9:0x0039  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public   void uploaddImagesAndDataImages1( ImagenReport currenImageReport) throws IOException {

            /**SI HAY PROBELASM DE URI PERMISOS ASEGURARSE QUE EL URI CONTENGA UNA PROPIEDAD QUE HACER QUE LE DE PERMISOS DE
             * LECTURA ALGO AS..ESO EN INTENT AL SELECIONAR IMAGENES*/
            Log.i("IMAGESTASKEdit","vamos a subir para el hilo "+1);

            Uri uriImage  = Uri.parse(currenImageReport.geturiImage());
            imagename = ImageFolderReferenceImagesAll.child(currenImageReport.getUniqueIdNamePic());


            boolean existValue=false;

            if(null != uriImage) {
                try {
                    inputStream = context.getContentResolver().openInputStream(uriImage);
                    inputStream.close();
                    existValue = true;
                } catch (Exception e) {
                    Log.i("IMAGESTASKEdit","exepcion aqui y exist value es");
                }
            }


            if(existValue){

                Log.i("IMAGESTASKEdit", "bitmap original here ");

                bitmapOriginal = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uriImage);
                stream = new ByteArrayOutputStream();
                bitmapOriginal.compress(Bitmap.CompressFormat.WEBP,95,stream);//0=lowe


                data = stream.toByteArray();
                uploadTask = imagename.putBytes(data);

                Log.i("IMAGESTASKEdit", "empezandoupload task");

                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {

                        Log.i("IMAGESTASKEdit","es falilure");

                        /***BIEN NOTE : EL METODO DE ABAJO DEBERIA LLEVAR UN PAREMTRO QUE INDENTIFCQUE EL OBJETO GLOBAL
                         * DE BOOTOMSHEETCALLUPLOADING..
                         * AHORA INVOCAMOS EL METODO DE BOTTOM SHEET OTRA VEZ Y LE PASAMOS EL NUEVO INDICE
                         * */


                        Variables.contadorImagenesSubidasSumaAll++;
                        Variables.contadorImagenesFall++;
                        updateObjectGCurrentIndiceAndContadorUpload1();

                        thread1.run();


                        Log.i("imagestorage", "existe una exepecion y es "+exception.getMessage());

                        // Handle unsuccessful uploads
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                        //  Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        imagename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Log.i("IMAGESTASKEdit","es succes");


                                String iconPathFirebase = uri.toString();
                                currenImageReport.setUrlStoragePic(iconPathFirebase);
                                // value.setIdReportePerteence(uniqueIDImagesSetAndUInforme);
                                Log.i("superstorage","se subio imagen y el url esd  al informe "+currenImageReport.getUrlStoragePic());

                                /**aumnetamos el valor del indice en ek on succes dek siguiente metodo*/
                                addNewSetPicsInforme1(currenImageReport);

                            }
                        });
                    }
                });

            } else {

                Variables.contadorImagenesSubidasSumaAll++;
                Log.i("IMAGESTASKEdit","el contador imagenes subidas es "+ Variables.contadorImagenesSubidasSumaAll);

                Variables.contadorImagenesFall++;

                updateObjectGCurrentIndiceAndContadorUpload1();

                thread1.run();

                Log.i("exepciopmx","no existe valores");

            }



        }

        public void addNewSetPicsInforme1(final ImagenReport imagenReport) {
            if (RealtimeDB.mibasedataPathImages == null) {
                RealtimeDB.initDatabasesReferenceImagesData();
            }
            RealtimeDB.mibasedataPathImages.push().setValue(imagenReport.toMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
                public void onComplete(Task<Void> task) {
                    if (task.isSuccessful()) {
                        Log.i("IMAGESTASKEdit", "se subio imagen report " + imagenReport.getUrlStoragePic());
                        Variables.contadorImagenesSubidasSumaAll++;
                        Log.i("IMAGESTASKEdit", "el contador imagenes subidas es " + Variables.contadorImagenesSubidasSumaAll);
                        Log.i("IMAGESTASKEdit", "llamamos tread otravez ");
                        TrheadUploadImages.this.updateObjectGCurrentIndiceAndContadorUpload1();
                        thread1.run();
                        return;
                    }
                    Variables.contadorImagenesFall++;
                    Variables.ErrorSubirImage = true;
                    TrheadUploadImages.this.updateObjectGCurrentIndiceAndContadorUpload1();
                    thread1.run();
                }
            });
        }

        public void updateObjectGCurrentIndiceAndContadorUpload1() {
            thread1.contadorImagenesSubidasThisObject++;
            thread1.indiceCurrentObjectx++;
        }
    }

    static void decideMethodCallUpdate(int i) {
        Log.i("idcurrent", "el id current es " + i);
        if (i == 10060) {
            /**Usar clases enum**/


            UpdateConteendores(Variables.SEVERAL_INFORMS_UPDATE);
        } else if (i == 1065072) {
            updatControlCalidad(Variables.CONTROL_CALIDAD_OBJECT);
        } else if (i == 10066) {
            uploadConteendoresForm(Variables.OBJECT_SetInformEmbarque1);
        } else if (i != 10067) {
            switch (i) {
                case Variables.FormatDatsContAcopiPREVIEW /*10069*/:
                    updateContenedresEnAcopio(Variables.FormatDatsContAcopiPREVIEW);
                    return;
                case Variables.FormCamionesyCarretasActivity /*10070*/:
                    uploadCamionesYcarretas(Variables.OBJECT_CAMIONESYCARRETAS);
                    return;
                case Variables.FormCamionesyCarretasActivityPreview /*10071*/:
                    updateCamionesYcarretas(Variables.OBJECT_CAMIONESYCARRETAS);
                    return;
                case Variables.FormCantrolCalidad /*10072*/:
                    UploadControlCalidad(Variables.CONTROL_CALIDAD_OBJECT);
                    return;
                default:
                    return;
            }
        } else {
            uploadConteendoresEnAcopio(Variables.FormatDatsContAcopi);
        }
    }
}
