package com.tiburela.qsercom.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class SetInformDatsHacienda {
    private String fuenteAgua;
    private String aguaCorrida;
    private String lavadoRacimos;
    private String fumigacionClin1;
    private String ediTipoBoquilla;

    private String ediCajasProcDesp;
    private String ediRacimosCosech;

    private String ediRacimosRecha;
    private String ediRacimProces;


    private String colortSem14;
    private String colortSem13;
    private String colortSem12;
    private String colortSem11;
    private String colortSem10;



    //digamos q son las ultimas 5 semanas...

    private String numRcim14;  //ultima semana
    private String numRcim13;  //penultima
    private String numRcim12;  //antepenultimo
    private String numRcim11;  //trasantepenúltimo
    private String numRcim10;  //

    private String porc14;
    private String porc13;
    private String porc12;
    private String porc11;
    private String porc10;

    public String getKeyFirebase() {
        return keyFirebase;
    }

    public String getCodeInformePertenence() {
        return codeInformePertenence;
    }

    private String keyFirebase;
    private String codeInformePertenence;



    public String getFuenteAgua() {
        return fuenteAgua;
    }

    public void setFuenteAgua(String fuenteAgua) {
        this.fuenteAgua = fuenteAgua;
    }

    public String getAguaCorrida() {
        return aguaCorrida;
    }

    public void setAguaCorrida(String aguaCorrida) {
        this.aguaCorrida = aguaCorrida;
    }

    public String getLavadoRacimos() {
        return lavadoRacimos;
    }

    public void setLavadoRacimos(String lavadoRacimos) {
        this.lavadoRacimos = lavadoRacimos;
    }

    public String getFumigacionClin1() {
        return fumigacionClin1;
    }

    public void setFumigacionClin1(String fumigacionClin1) {
        this.fumigacionClin1 = fumigacionClin1;
    }

    public String getEdiTipoBoquilla() {
        return ediTipoBoquilla;
    }

    public void setEdiTipoBoquilla(String ediTipoBoquilla) {
        this.ediTipoBoquilla = ediTipoBoquilla;
    }

    public String getEdiCajasProcDesp() {
        return ediCajasProcDesp;
    }

    public void setEdiCajasProcDesp(String ediCajasProcDesp) {
        this.ediCajasProcDesp = ediCajasProcDesp;
    }

    public String getEdiRacimosCosech() {
        return ediRacimosCosech;
    }

    public void setEdiRacimosCosech(String ediRacimosCosech) {
        this.ediRacimosCosech = ediRacimosCosech;
    }

    public String getEdiRacimosRecha() {
        return ediRacimosRecha;
    }

    public void setEdiRacimosRecha(String ediRacimosRecha) {
        this.ediRacimosRecha = ediRacimosRecha;
    }

    public String getEdiRacimProces() {
        return ediRacimProces;
    }

    public void setEdiRacimProces(String ediRacimProces) {
        this.ediRacimProces = ediRacimProces;
    }

    public String getColortSem14() {
        return colortSem14;
    }

    public void setColortSem14(String colortSem14) {
        this.colortSem14 = colortSem14;
    }

    public String getColortSem13() {
        return colortSem13;
    }

    public void setColortSem13(String colortSem13) {
        this.colortSem13 = colortSem13;
    }

    public String getColortSem12() {
        return colortSem12;
    }

    public void setColortSem12(String colortSem12) {
        this.colortSem12 = colortSem12;
    }

    public String getColortSem11() {
        return colortSem11;
    }

    public void setColortSem11(String colortSem11) {
        this.colortSem11 = colortSem11;
    }

    public String getColortSem10() {
        return colortSem10;
    }

    public void setColortSem10(String colortSem10) {
        this.colortSem10 = colortSem10;
    }

    public String getNumRcim14() {
        return numRcim14;
    }

    public void setNumRcim14(String numRcim14) {
        this.numRcim14 = numRcim14;
    }

    public String getNumRcim13() {
        return numRcim13;
    }

    public void setNumRcim13(String numRcim13) {
        this.numRcim13 = numRcim13;
    }

    public String getNumRcim12() {
        return numRcim12;
    }

    public void setNumRcim12(String numRcim12) {
        this.numRcim12 = numRcim12;
    }

    public String getNumRcim11() {
        return numRcim11;
    }

    public void setNumRcim11(String numRcim11) {
        this.numRcim11 = numRcim11;
    }

    public String getNumRcim10() {
        return numRcim10;
    }

    public void setNumRcim10(String numRcim10) {
        this.numRcim10 = numRcim10;
    }

    public String getPorc14() {
        return porc14;
    }

    public void setPorc14(String porc14) {
        this.porc14 = porc14;
    }

    public String getPorc13() {
        return porc13;
    }

    public void setPorc13(String porc13) {
        this.porc13 = porc13;
    }

    public String getPorc12() {
        return porc12;
    }

    public void setPorc12(String porc12) {
        this.porc12 = porc12;
    }

    public String getPorc11() {
        return porc11;
    }

    public void setPorc11(String porc11) {
        this.porc11 = porc11;
    }

    public String getPorc10() {
        return porc10;
    }

    public void setPorc10(String porc10) {
        this.porc10 = porc10;
    }


    public void setKeyFirebase(String keyFirebase) {
        this.keyFirebase = keyFirebase;
    }

    public SetInformDatsHacienda(String fuenteAgua, String aguaCorrida, String lavadoRacimos, String fumigacionClin1,
                                 String ediTipoBoquilla, String ediCajasProcDesp, String ediRacimosCosech,
                                 String ediRacimosRecha, String ediRacimProces,String codeInformePertenence ){

   this. fuenteAgua=fuenteAgua;
    this.aguaCorrida=aguaCorrida;
    this.lavadoRacimos=lavadoRacimos;
  this. fumigacionClin1=fumigacionClin1;
  this. ediTipoBoquilla=ediTipoBoquilla;
   this. ediCajasProcDesp=ediCajasProcDesp;
   this.ediRacimosCosech=ediRacimosCosech;
 this. ediRacimosRecha=ediRacimosRecha;
this.ediRacimProces=ediRacimProces;

    keyFirebase="";

    colortSem14="";
    colortSem13="";
    colortSem12="";
    colortSem11="";
    colortSem10="";
    numRcim14  ="";
    numRcim13  ="";
    numRcim12  ="";
    numRcim11  ="";
    numRcim10  ="";
    porc14="";
    porc13="";
    porc12="";
    porc11="";
    porc10="";

   this.codeInformePertenence=codeInformePertenence;

}


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("fuenteAgua", fuenteAgua);
        result.put("aguaCorrida", aguaCorrida);
        result.put("lavadoRacimos", lavadoRacimos);
        result.put("fumigacionClin1", fumigacionClin1);
        result.put("ediTipoBoquilla", ediTipoBoquilla);
        result.put("ediCajasProcDesp", ediCajasProcDesp);
        result.put("ediRacimosCosech", ediRacimosCosech);
        result.put("ediRacimosRecha", ediRacimosRecha);
        result.put("ediRacimProces", ediRacimProces);

        result.put("colortSem14", colortSem14);
        result.put("colortSem13", colortSem13);
        result.put("colortSem12", colortSem12);
        result.put("colortSem11", colortSem11);
        result.put("colortSem10", colortSem10);
        result.put("numRcim14", numRcim14);
        result.put("numRcim13", numRcim13);
        result.put("numRcim12", numRcim12);
        result.put("numRcim11", numRcim11);
        result.put("numRcim10", numRcim10);
        result.put("porc14", porc14);

        result.put("porc13", porc13);
        result.put("porc12", porc12);
        result.put("porc11", porc11);
        result.put("porc10", porc10);
        result.put("keyFirebase", keyFirebase);
        result.put("codeInformePertenence", codeInformePertenence);



        return result;


    }


}
