package com.tiburela.qsercom.dialog_fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


import com.tiburela.qsercom.R;

public class DialogFragmentMenu extends DialogFragment {
    TextView txt_muestra_respue,txt_muestra_pregunt;
    Button btn_siguiente_quiz;
    private View rootView;


    TextView txt_muestra_respue2;
    String muestra_respuesta,muestra_pregunta;

    boolean boolean_recibe_respuesta;
    boolean recibe_auto_close=false;
    ImageView ilayout;


    // Activity MainActivity2dos;
    int muestra_score;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_menu, container, false);

        //   txt_muestra_pregunt= rootView.findViewById(R.id.texto_uno);
        ImageView imgClose=rootView.findViewById(R.id.imageView8);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        LinearLayout contacAutor= rootView.findViewById(R.id.contacAutor);
        contacAutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickWhatsApp();

                //

            }
        });


        //   preCargar();
        //    eventos();

        return rootView;

    }

    private void onClickWhatsApp() {


        try {
            PackageManager pm=getActivity().getPackageManager();
            String phoneNumberWithCountryCode = "+593986037360";
            String message = "Hola :)";

            startActivity(
                    new Intent(Intent.ACTION_VIEW,
                            Uri.parse(
                                    String.format("https://api.whatsapp.com/send?phone=%s&text=%s", phoneNumberWithCountryCode, message)
                            )
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }





    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
        layoutParams.dimAmount = 0;
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        window.setGravity(Gravity.CENTER);
        getDialog().getWindow().setAttributes(layoutParams);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));




    }


    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.i("verde", "no cerrar");

        // Add you codition
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //   setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme); //posiblemente volver activar


        if (getArguments() != null ){
            Bundle bundle = getArguments();
        }

    }


    public void cerrar_fragmento(){

        dismiss();
    }






    @Override
    public int getTheme() {
        return R.style.DialogTheme;
    }




}