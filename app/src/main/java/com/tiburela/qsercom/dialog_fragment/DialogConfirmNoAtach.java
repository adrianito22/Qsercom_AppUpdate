package com.tiburela.qsercom.dialog_fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.tiburela.qsercom.Constants.Constants;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.activities.formularios.ActivityCamionesyCarretas;
import com.tiburela.qsercom.activities.formularios.ActivityContenedores;
import com.tiburela.qsercom.activities.formulariosPrev.ActivityContenedoresPrev;
import com.tiburela.qsercom.activities.formulariosPrev.PreviewCalidadCamionesyCarretas;
import com.tiburela.qsercom.utils.Utils;

public class DialogConfirmNoAtach extends BottomSheetDialogFragment implements View.OnClickListener {
        public static final String TAG = "ActionBottomDialog";

         boolean userDecicidoVinucularSomeReport =false;

       static int formullarioSelect=0;
        private View vista;
        Button atachAhora;
        Button atachDespues;

        TextView txtiText;

    Context context;

        public static DialogConfirmNoAtach newInstance(int tipoFormulario) {
            formullarioSelect=tipoFormulario;
            return new DialogConfirmNoAtach();
        }
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            vista= inflater.inflate(R.layout.bottom_sheet_confirm_atch, container, false);

            atachAhora =vista.findViewById(R.id.atachAhora);
            atachDespues =vista.findViewById(R.id.atachDespues);
            txtiText=vista.findViewById(R.id.txtiText);

            context = getActivity();


            atachAhora.setOnClickListener(this);
            atachDespues.setOnClickListener(this);
            Log.i("ontatch","se ejecuto onViewCreated");
            Log.i("ontatch","se ejecuto onCreateView");

            return  vista;


        }
        @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            if(! Utils.userDecidioNoVincularAhora){
                txtiText.setText(Utils.textoShow);

            }

            else if(! Utils.userDecidioNoVincularCuadroMuestreo){
                txtiText.setText(Utils.textoShow);

            }



            //  view.findViewById(R.id.textView4).setOnClickListener(this);
        }


        @Override
        public void onAttach(Context context) {
            super.onAttach(context);

        }

        @Override
        public void onDetach() {
            super.onDetach();


        }




        @Override public void onClick(View view) {
          // Button tvSelected = (Button) view;

            if(view.getId()==R.id.atachAhora){ //
                userDecicidoVinucularSomeReport =true;
                callmethodOfActivity(formullarioSelect);

            }

            if(view.getId()==R.id.atachDespues){
                userDecicidoVinucularSomeReport =false;
                Utils.userDecidioNoVincularAhora=true;

            }



            dismiss();
        }



        public interface ItemClickListener {
            void onItemClick(boolean esNEW);
        }


    public interface CallBtoActityContenedor2 {
        void uploadConfirm(boolean esNEW);
    }


    @Override
    public void onStart() {
        super.onStart();

        Log.i("ontatch","se ejecuto onStart");

    }

    public  void callmethodOfActivity(int tipoFormulario) {
      //  openBottomSheetConfirmCreateNew  saveinfo


        if(tipoFormulario== Constants.CONTENEDORES){

            if(userDecicidoVinucularSomeReport){

                ((ActivityContenedores)getActivity()).decideaAtachReport(true);//  ///

            }else{

                ((ActivityContenedores)getActivity()).decideaAtachReport(false);//  ///

            }

        }



          else  if(tipoFormulario== Constants.PREV_CONTENEDORES){

            if(userDecicidoVinucularSomeReport){

                ((ActivityContenedoresPrev)getActivity()).decideaAtachReport(true);//  ///

            }else{

                ((ActivityContenedoresPrev)getActivity()).decideaAtachReport(false);//  ///

            }





        }


        else  if(tipoFormulario== Constants.PREV_CAMIONES_Y_CARRETAS){

            if(userDecicidoVinucularSomeReport){

                ((PreviewCalidadCamionesyCarretas)getActivity()).decideaAtachReport(true);//  ///

            }else{

                ((PreviewCalidadCamionesyCarretas)getActivity()).decideaAtachReport(false);//  ///

            }





        }


        else  if(tipoFormulario== Constants.CAMIONES_Y_CARRETAS){

            if(userDecicidoVinucularSomeReport){

                ((ActivityCamionesyCarretas)getActivity()).decideaAtachReport(true);//  ///

            }else{

                ((ActivityCamionesyCarretas)getActivity()).decideaAtachReport(false);//  ///

            }





        }




             //m,aselse if por aqui


          dismiss();


    }


}

