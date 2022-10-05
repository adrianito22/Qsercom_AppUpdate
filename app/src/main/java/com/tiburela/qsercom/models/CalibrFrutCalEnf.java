package com.tiburela.qsercom.models;

public class CalibrFrutCalEnf {

    private String colorSemana14;
    private String colorSemana13;
    private String colorSemana12;
    private String colorSemana11;
    private String colorSemana10;
    private String colorSemana9;

    private int numeracionRacimosSem14;
    private int numeracionRacimosSem13;
    private int numeracionRacimosSem12;
    private int numeracionRacimosSem11;
    private int numeracionRacimosSem10;
    private int numeracionRacimosSem9;


    private String idPertenece;

    public String getKeyFirebase() {
        return keyFirebase;
    }

    private String keyFirebase;



    public String getColorSemana13() {
        return colorSemana13;
    }

    public void setColorSemana13(String colorSemana13) {
        this.colorSemana13 = colorSemana13;
    }

    public String getColorSemana12() {
        return colorSemana12;
    }

    public void setColorSemana12(String colorSemana12) {
        this.colorSemana12 = colorSemana12;
    }

    public String getColorSemana11() {
        return colorSemana11;
    }

    public void setColorSemana11(String colorSemana11) {
        this.colorSemana11 = colorSemana11;
    }

    public String getColorSemana10() {
        return colorSemana10;
    }

    public void setColorSemana10(String colorSemana10) {
        this.colorSemana10 = colorSemana10;
    }

    public String getColorSemana9() {
        return colorSemana9;
    }

    public void setColorSemana9(String colorSemana9) {
        this.colorSemana9 = colorSemana9;
    }

    public int getNumeracionRacimosSem14() {
        return numeracionRacimosSem14;
    }

    public void setNumeracionRacimosSem14(int numeracionRacimosSem14) {
        this.numeracionRacimosSem14 = numeracionRacimosSem14;
    }

    public int getNumeracionRacimosSem13() {
        return numeracionRacimosSem13;
    }

    public void setNumeracionRacimosSem13(int numeracionRacimosSem13) {
        this.numeracionRacimosSem13 = numeracionRacimosSem13;
    }

    public int getNumeracionRacimosSem12() {
        return numeracionRacimosSem12;
    }

    public void setNumeracionRacimosSem12(int numeracionRacimosSem12) {
        this.numeracionRacimosSem12 = numeracionRacimosSem12;
    }

    public int getNumeracionRacimosSem11() {
        return numeracionRacimosSem11;
    }

    public void setNumeracionRacimosSem11(int numeracionRacimosSem11) {
        this.numeracionRacimosSem11 = numeracionRacimosSem11;
    }

    public int getNumeracionRacimosSem10() {
        return numeracionRacimosSem10;
    }

    public void setNumeracionRacimosSem10(int numeracionRacimosSem10) {
        this.numeracionRacimosSem10 = numeracionRacimosSem10;
    }

    public int getNumeracionRacimosSem9() {
        return numeracionRacimosSem9;
    }

    public void setNumeracionRacimosSem9(int numeracionRacimosSem9) {
        this.numeracionRacimosSem9 = numeracionRacimosSem9;
    }

    public String getIdPertenece() {
        return idPertenece;
    }

    public void setIdPertenece(String idPertenece) {
        this.idPertenece = idPertenece;
    }


    public void setKeyFirebase(String keyFirebase) {
        this.keyFirebase = keyFirebase;
    }


    public CalibrFrutCalEnf(){


    }

    public CalibrFrutCalEnf(String idPertenece){
        this.idPertenece=idPertenece;
        colorSemana14="";
        colorSemana13="";
        colorSemana12="";
        colorSemana11="";
        colorSemana10="";
        colorSemana9="";

        numeracionRacimosSem14=0;
        numeracionRacimosSem13=0;
        numeracionRacimosSem12=0;
        numeracionRacimosSem11=0;
        numeracionRacimosSem10=0;
        numeracionRacimosSem9=0;
         keyFirebase="";



    }



    public String getColorSemana14() {
        return colorSemana14;
    }

    public void setColorSemana14(String colorSemana14) {
        this.colorSemana14 = colorSemana14;
    }




}
