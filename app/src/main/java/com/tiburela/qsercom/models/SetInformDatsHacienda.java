package com.tiburela.qsercom.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class SetInformDatsHacienda {

    private String extensionistCalid;
    private String extensionistDeRodillo;
    private String extensionistEnGancho;

    public String getExtensionistCalid() {
        return extensionistCalid;
    }

    public void setExtensionistCalid(String extensionistCalid) {
        this.extensionistCalid = extensionistCalid;
    }

    public String getExtensionistDeRodillo() {
        return extensionistDeRodillo;
    }

    public void setExtensionistDeRodillo(String extensionistDeRodillo) {
        this.extensionistDeRodillo = extensionistDeRodillo;
    }

    public String getExtensionistEnGancho() {
        return extensionistEnGancho;
    }

    public void setExtensionistEnGancho(String extensionistEnGancho) {
        this.extensionistEnGancho = extensionistEnGancho;
    }

    public String getCI_extensionistCalid() {
        return CI_extensionistCalid;
    }

    public void setCI_extensionistCalid(String CI_extensionistCalid) {
        this.CI_extensionistCalid = CI_extensionistCalid;
    }

    public String getCI_extensionistDeRodillo() {
        return CI_extensionistDeRodillo;
    }

    public void setCI_extensionistDeRodillo(String CI_extensionistDeRodillo) {
        this.CI_extensionistDeRodillo = CI_extensionistDeRodillo;
    }

    public String getCI_extensionistEnGancho() {
        return CI_extensionistEnGancho;
    }

    public void setCI_extensionistEnGancho(String CI_extensionistEnGancho) {
        this.CI_extensionistEnGancho = CI_extensionistEnGancho;
    }

    public void setUniqueIDinformeDatsHda(String uniqueIDinformeDatsHda) {
        this.uniqueIDinformeDatsHda = uniqueIDinformeDatsHda;
    }

    private String CI_extensionistCalid;
    private String CI_extensionistDeRodillo;
    private String CI_extensionistEnGancho;


    private String fuenteAgua;
    private boolean hayAguaCorrida;
    private boolean hayLavadoRacimos;
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

    public String getColortSem9() {
        return colortSem9;
    }

    public void setColortSem9(String colortSem9) {
        this.colortSem9 = colortSem9;
    }

    public String getNumRcim9() {
        return numRcim9;
    }

    public void setNumRcim9(String numRcim9) {
        this.numRcim9 = numRcim9;
    }

    public String getPorc9() {
        return porc9;
    }

    public void setPorc9(String porc9) {
        this.porc9 = porc9;
    }

    private String colortSem9;



    //digamos q son las ultimas 5 semanas...

    private String numRcim14;  //ultima semana
    private String numRcim13;  //penultima
    private String numRcim12;  //antepenultimo
    private String numRcim11;  //trasantepenúltimo
    private String numRcim10;  //
    private String numRcim9;

    private String porc14;
    private String porc13;
    private String porc12;
    private String porc11;
    private String porc10;
    private String porc9;


    public String getKeyFirebase() {
        return keyFirebase;
    }

    public String getUniqueIDinformeDatsHda() {
        return uniqueIDinformeDatsHda;
    }

    private String keyFirebase;
    private String uniqueIDinformeDatsHda;



    public String getFuenteAgua() {
        return fuenteAgua;
    }

    public void setFuenteAgua(String fuenteAgua) {
        this.fuenteAgua = fuenteAgua;
    }

    public boolean isHayAguaCorrida() {
        return hayAguaCorrida;
    }

    public void setHayAguaCorrida(boolean hayAguaCorrida) {
        this.hayAguaCorrida = hayAguaCorrida;
    }

    public boolean isHayLavadoRacimos() {
        return hayLavadoRacimos;
    }

    public void setHayLavadoRacimos(boolean hayLavadoRacimos) {
        this.hayLavadoRacimos = hayLavadoRacimos;
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


    public SetInformDatsHacienda() {


    }


    public void setKeyFirebase(String keyFirebase) {
        this.keyFirebase = keyFirebase;
    }

    public SetInformDatsHacienda(String fuenteAgua, boolean hayAguaCorrida, boolean lavadoRacimos, String fumigacionClin1,
                                 String ediTipoBoquilla, String ediCajasProcDesp, String ediRacimosCosech,
                                 String ediRacimosRecha, String ediRacimProces, String uniqueIDinformeDatsHda,

                                  String extensionistCalid, String CI_extensionistCalid


                                 ){

   this. fuenteAgua=fuenteAgua;
    this.hayAguaCorrida = hayAguaCorrida;
    this.hayLavadoRacimos =lavadoRacimos;
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
    colortSem9="";

    numRcim14  ="";
    numRcim13  ="";
    numRcim12  ="";
    numRcim11  ="";
    numRcim10  ="";
        numRcim9  ="";


        porc14="";
    porc13="";
    porc12="";
    porc11="";
    porc10="";
    porc9="";

   this.uniqueIDinformeDatsHda = uniqueIDinformeDatsHda;


        this. extensionistCalid=extensionistCalid;
        this.CI_extensionistCalid =CI_extensionistCalid;


        extensionistDeRodillo="";
      extensionistEnGancho="";
        CI_extensionistDeRodillo="";
      CI_extensionistEnGancho="";

}


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("fuenteAgua", fuenteAgua);
        result.put("aguaCorrida", hayAguaCorrida);
        result.put("lavadoRacimos", hayLavadoRacimos);
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
        result.put("uniqueIDinformeDatsHda", uniqueIDinformeDatsHda);

        result.put("extensionistCalid", extensionistCalid);
        result.put("extensionistDeRodillo", extensionistDeRodillo);
        result.put("extensionistEnGancho", extensionistEnGancho);

        result.put("CI_extensionistCalid", CI_extensionistCalid);
        result.put("CI_extensionistDeRodillo", CI_extensionistDeRodillo);
        result.put("CI_extensionistEnGancho", CI_extensionistEnGancho);

        return result;


    }


}
