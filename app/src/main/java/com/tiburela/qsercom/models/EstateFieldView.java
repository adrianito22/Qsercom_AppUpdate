package com.tiburela.qsercom.models;

import java.util.ArrayList;

public class EstateFieldView {
    public  String idOfView;
   public static    ArrayList<EstateFieldView>listEstateViewField;

    boolean estaLleno;
    boolean IsComplete;



    public String getIdOfView() {
        return idOfView;
    }

    public void setIdOfView(String idOfView) {
        this.idOfView = idOfView;
    }

    public boolean isEstaLleno() {
        return estaLleno;
    }

    public void setEstaLleno(boolean estaLleno) {
        this.estaLleno = estaLleno;
    }


    public EstateFieldView(String idOfVIEW, boolean IsComplete) { //asi iniciara todo en vacio//

       // listEstateViewField.add(idOfVIEW,IsComplete)
        this.idOfView=idOfVIEW;
        this.IsComplete=IsComplete;
        // this.idOfView = idOfView;
       // estaLleno = false;

    }


    public static void adddataList() { //asi iniciara todo en vacio//
        listEstateViewField=new ArrayList<>();
        listEstateViewField.add(new EstateFieldView("ediNhojaEvaluacion",false));
        listEstateViewField.add(new EstateFieldView("ediFecha",false));
        listEstateViewField.add(new EstateFieldView("spinnerZona",false));
        listEstateViewField.add(new EstateFieldView("ediProductor",false));
        listEstateViewField.add(new EstateFieldView("ediCodigo",false));
        listEstateViewField.add(new EstateFieldView("ediPemarque",false));
        listEstateViewField.add(new EstateFieldView("ediNguiaRemision",false));
        listEstateViewField.add(new EstateFieldView("ediHacienda",false));
        listEstateViewField.add(new EstateFieldView("edi_nguia_transporte",false));
        listEstateViewField.add(new EstateFieldView("ediNtargetaEmbarque",false));
        listEstateViewField.add(new EstateFieldView("ediInscirpMagap",false));
        listEstateViewField.add(new EstateFieldView("ediHoraInicio",false));
        listEstateViewField.add(new EstateFieldView("ediHoraTermino",false));
        listEstateViewField.add(new EstateFieldView("ediSemana",false));
        listEstateViewField.add(new EstateFieldView("ediEmpacadora",false));
        listEstateViewField.add(new EstateFieldView("ediContenedor",false));
        listEstateViewField.add(new EstateFieldView("ediObservacion",false));
        listEstateViewField.add(new EstateFieldView("imbAtach/imbTakePic",false));
        listEstateViewField.add(new EstateFieldView("ediPPC/someProductPostCosecha",false));

        // this.idOfView = idOfView;
        // estaLleno = false;


    }


}
