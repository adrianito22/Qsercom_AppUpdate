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


    public static void adddataListsStateFields() { //asi iniciara todo en vacio//
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


        listEstateViewField.add(new EstateFieldView("ediHoraLLegadaContenedor",false));
        listEstateViewField.add(new EstateFieldView("ediHoraSalidaContenedor",false));
        listEstateViewField.add(new EstateFieldView("ediDestino",false));
        listEstateViewField.add(new EstateFieldView("ediNViaje",false));

        listEstateViewField.add(new EstateFieldView("ediVapor",false));
        listEstateViewField.add(new EstateFieldView("ediTipoContenedor",false));

        //HAST AQUI
        listEstateViewField.add(new EstateFieldView("ediTare",false));
        listEstateViewField.add(new EstateFieldView("ediBooking",false));
        listEstateViewField.add(new EstateFieldView("ediMaxGross",false));

        listEstateViewField.add(new EstateFieldView("ediNumSerieFunda",false));
        listEstateViewField.add(new EstateFieldView("stikVentolerExterna",false));
        listEstateViewField.add(new EstateFieldView("ediCableRastreoLlegada",false));

        listEstateViewField.add(new EstateFieldView("ediSelloPlasticoNaviera",false));
        listEstateViewField.add(new EstateFieldView("ediOtroSellosLlegada",false));

        listEstateViewField.add(new EstateFieldView("ediTermofrafo1",false));

        listEstateViewField.add(new EstateFieldView("ediHoraEncendido1",false));
        listEstateViewField.add(new EstateFieldView("ediUbicacion1",false));
        listEstateViewField.add(new EstateFieldView("ediRuma1",false));
        listEstateViewField.add(new EstateFieldView("ediTermofrafo2",false));
        listEstateViewField.add(new EstateFieldView("ediHoraEncendido2",false));
        listEstateViewField.add(new EstateFieldView("ediUbicacion2",false));
        listEstateViewField.add(new EstateFieldView("ediRuma2",false));


        listEstateViewField.add(new EstateFieldView("ediCandadoqsercon",false));
        listEstateViewField.add(new EstateFieldView("ediSelloNaviera",false));
        listEstateViewField.add(new EstateFieldView("ediCableNaviera",false));

        listEstateViewField.add(new EstateFieldView("ediSelloPlastico",false));
        listEstateViewField.add(new EstateFieldView("ediCandadoBotella",false));
        listEstateViewField.add(new EstateFieldView("ediCableExportadora",false));
        listEstateViewField.add(new EstateFieldView("ediSelloAdesivoexpor",false));
        listEstateViewField.add(new EstateFieldView("esiSelloAdhNaviera",false));



        listEstateViewField.add(new EstateFieldView("ediCompaniaTransporte",false));
        listEstateViewField.add(new EstateFieldView("ediNombreChofer",false));
        listEstateViewField.add(new EstateFieldView("ediCedula",false));
        listEstateViewField.add(new EstateFieldView("ediCelular",false));

        listEstateViewField.add(new EstateFieldView("ediPLaca",false));
        listEstateViewField.add(new EstateFieldView("ediMarcaCabezal",false));
        listEstateViewField.add(new EstateFieldView("ediColorCabezal",false));

        listEstateViewField.add(new EstateFieldView("ediUbicacionBalanza",false));


        listEstateViewField.add(new EstateFieldView("ediCondicionBalanza",false));
        listEstateViewField.add(new EstateFieldView("ediTipodeCaja",false));
        listEstateViewField.add(new EstateFieldView("ediBalanza",false));
        listEstateViewField.add(new EstateFieldView("ediEnsunchado",false));


        listEstateViewField.add(new EstateFieldView("ediTipoPlastico",false));
        listEstateViewField.add(new EstateFieldView("ediTipoBalanza",false));

        listEstateViewField.add(new EstateFieldView("ediFuenteAgua",false));
        listEstateViewField.add(new EstateFieldView("ediAguaCorrida",false));
        listEstateViewField.add(new EstateFieldView("ediLavadoRacimos",false));
        listEstateViewField.add(new EstateFieldView("ediFumigacionClin1",false));

        listEstateViewField.add(new EstateFieldView("ediTipoBoquilla",false));
        listEstateViewField.add(new EstateFieldView("ediCajasProcDesp",false));
        listEstateViewField.add(new EstateFieldView("ediRacimosCosech",false));


        listEstateViewField.add(new EstateFieldView("ediRacimosRecha",false));
        listEstateViewField.add(new EstateFieldView("ediRacimProces",false));












    }


}
