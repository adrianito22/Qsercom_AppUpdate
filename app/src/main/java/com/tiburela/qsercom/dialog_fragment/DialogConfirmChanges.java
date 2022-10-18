package com.tiburela.qsercom.dialog_fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.activities.ActivityContenedoresPrev;
import com.tiburela.qsercom.activities.CuadMuestreoCalibAndRechazPrev;
import com.tiburela.qsercom.activities.FormularioControlCalidadPreview;
import com.tiburela.qsercom.activities.PackingListPreviewActivity;
import com.tiburela.qsercom.activities.PreviewCalidadCamionesyCarretas;
import com.tiburela.qsercom.activities.PreviewsFormDatSContersEnAc;
import com.tiburela.qsercom.utils.Variables;

public class DialogConfirmChanges extends BottomSheetDialogFragment implements View.OnClickListener {
        public static final String TAG = "ActionBottomDialog";


       static int formullarioSelect=0;
        private View vista;
        Button btnSi;
        Button btnNo;

    Context context;

        public static DialogConfirmChanges newInstance(int tipoFormulario) {
            formullarioSelect=tipoFormulario;
            return new DialogConfirmChanges();
        }
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            vista= inflater.inflate(R.layout.bottom_sheet_confirm_changes, container, false);

             btnSi=vista.findViewById(R.id.btnSi);
             btnNo=vista.findViewById(R.id.btnNo);

            context = getActivity();
            Log.i("ontatch","se ejecuto onCreateView");

            return  vista;


        }
        @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            btnSi.setOnClickListener(this);
            btnNo.setOnClickListener(this);
            Log.i("ontatch","se ejecuto onViewCreated");

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

            if(view.getId()==R.id.btnSi){ //crea un formulario nuevo
                callmethod(formullarioSelect);

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

    public  void callmethod(int tipoFormulario) {
      //  openBottomSheetConfirmCreateNew  saveinfo


        if(tipoFormulario== Variables.FormCantrolCalidad){
            Log.i("ontatch","se ejecuto onStart");
            ((FormularioControlCalidadPreview)getActivity()).saveInfo();

        }

        else  if(tipoFormulario==Variables.FormPreviewContenedores){
            ((ActivityContenedoresPrev)getActivity()).saveInfo();
        }


        else  if(tipoFormulario==Variables.FormatDatsContAcopi){
            ((PreviewsFormDatSContersEnAc)getActivity()).saveInfo();

        }

        else  if(tipoFormulario==Variables.FormCamionesyCarretasActivity){//
            ((PreviewCalidadCamionesyCarretas)getActivity()).saveInfo();

        }

        else  if(tipoFormulario==Variables.FormPackingList){
            ((PackingListPreviewActivity)getActivity()).saveInfo();


        }

        else  if(tipoFormulario==Variables.FormMuestreoRechaz){
            ((CuadMuestreoCalibAndRechazPrev)getActivity()).saveInfo();

        }


        ///
    }


}

