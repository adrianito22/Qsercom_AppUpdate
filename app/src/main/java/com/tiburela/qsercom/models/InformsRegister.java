package com.tiburela.qsercom.models;

import java.util.UUID;

public class InformsRegister {


    public String getIdInforme() {
        return idInforme;
    }

    public void setIdInforme(String idInforme) {
        this.idInforme = idInforme;
    }

    public String getUniqueIdInformLargo() {
        return uniqueIdInformLargo;
    }

    public void setUniqueIdInformLargo(String uniqueIdInformLargo) {
        this.uniqueIdInformLargo = uniqueIdInformLargo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String uniqueIdInformLargo ;
    private String date ;
    private String idInforme ;

    public int getTipoInform() {
        return tipoInform;
    }

    public void setTipoInform(int tipoInform) {
        this.tipoInform = tipoInform;
    }

    private int tipoInform ;



    public InformsRegister(String idInforme,int tipoInform) {
       uniqueIdInformLargo = UUID.randomUUID().toString();
       date = "";
        this.idInforme = idInforme;
    }



}
