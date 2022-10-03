package com.tiburela.qsercom.models;

public class PackingModel {
    private String nombre;
    private String valor;
    private String uniqIdReportPertenece;

    public String getKeyOfValor() {
        return keyOfValor;
    }

    public void setKeyOfValor(String keyOfValor) {
        this.keyOfValor = keyOfValor;
    }

    private String keyOfValor;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getUniqIdReportPertenece() {
        return uniqIdReportPertenece;
    }

    public void setUniqIdReportPertenece(String uniqIdReportPertenece) {
        this.uniqIdReportPertenece = uniqIdReportPertenece;
    }


    public PackingModel(String nombre, String valor, String uniqIdReportPertenece,String keyOfValor) {
        this.nombre = nombre;
        this.valor = valor;
        this.uniqIdReportPertenece = uniqIdReportPertenece;
        this.keyOfValor = keyOfValor;



    }



    //entonces vamos a haber....guardamos solo

}
