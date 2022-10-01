package com.tiburela.qsercom.models;

public class DatosDeProceso {

    //pertenecienta la actividad y clase FormDatosContersEnAcopio
    private int numProd;

    public int getNumProd() {
        return numProd;
    }

    public void setNumProd(int numProd) {
        this.numProd = numProd;
    }

    public String getTipoEmpaque() {
        return tipoEmpaque;
    }

    public void setTipoEmpaque(String tipoEmpaque) {
        this.tipoEmpaque = tipoEmpaque;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public int getNumeroCajas() {
        return numeroCajas;
    }

    public void setNumeroCajas(int numeroCajas) {
        this.numeroCajas = numeroCajas;
    }

    public String getNombreProd() {
        return nombreProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    public String getInformePertenece() {
        return InformePertenece;
    }

    public void setInformePertenece(String informePertenece) {
        InformePertenece = informePertenece;
    }

    private String tipoEmpaque;
    private String cod;
    private int numeroCajas;
    private String nombreProd;
    private String InformePertenece;
    private String KeyFirebase;


    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public String getKey2() {
        return key2;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    private String key1;
    private String key2;



    public String getKeyFirebase() {
        return KeyFirebase;
    }

    public void setKeyFirebase(String keyFirebase) {
        KeyFirebase = keyFirebase;
    }

    public DatosDeProceso(String nombreProd, int numProd, String tipoEmpaque, String cod, int numeroCajas, String InformePertenece,String key1) {
        this.nombreProd = nombreProd;
        this.numProd = numProd;
        this.tipoEmpaque = tipoEmpaque;
        this.cod = cod;
        this.numeroCajas = numeroCajas;
        this.InformePertenece = InformePertenece;
        this.key1=key1;
        KeyFirebase="";
    }


    public DatosDeProceso(){


    }

}

    ///ahora creamos un mapa..usando el id de cada uno

    /***RECORDAR QUE CREAMOS UN MAPA USANDO LOS TAG DE LOS TEXIMPUTEDITEXT VIEWS */


