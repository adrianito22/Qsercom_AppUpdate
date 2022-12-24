package com.tiburela.qsercom.models;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CuadroMuestreo {



    //COLORES
    private String color14;
    private String color13;
    private String color12;

    public String getColor14() {
        return color14;
    }

    public void setColor14(String color14) {
        this.color14 = color14;
    }

    public String getColor13() {
        return color13;
    }

    public void setColor13(String color13) {
        this.color13 = color13;
    }

    public String getColor12() {
        return color12;
    }

    public void setColor12(String color12) {
        this.color12 = color12;
    }

    public String getColor11() {
        return color11;
    }

    public void setColor11(String color11) {
        this.color11 = color11;
    }

    public String getColor10() {
        return color10;
    }

    public void setColor10(String color10) {
        this.color10 = color10;
    }

    public String getColor9() {
        return color9;
    }

    public void setColor9(String color9) {
        this.color9 = color9;
    }

    public String getExportadora() {
        return exportadora;
    }

    public void setExportadora(String exportadora) {
        this.exportadora = exportadora;
    }

    private String color11;
    private String color10;
    private String color9;



    public CuadroMuestreo(){


    }



    public void setKeyFirebaseLocation(String keyFirebaseLocation) {
        this.keyFirebaseLocation = keyFirebaseLocation;
    }

    public String getKeyFirebaseLocation() {
        return keyFirebaseLocation;
    }

    private int totalRechazadosAll;


    private int mutantes;
    private int spekling;
    private int ptaAmarillaYb;
    private int cremaAlmendraFloja;

    private String keyFirebaseLocation;
   private String exportadora;

    public int getMutantes() {
        return mutantes;
    }

    public void setMutantes(int mutantes) {
        this.mutantes = mutantes;
    }

    public int getSpekling() {
        return spekling;
    }

    public void setSpekling(int spekling) {
        this.spekling = spekling;
    }

    public int getPtaAmarillaYb() {
        return ptaAmarillaYb;
    }

    public void setPtaAmarillaYb(int ptaAmarillaYb) {
        this.ptaAmarillaYb = ptaAmarillaYb;
    }

    public int getCremaAlmendraFloja() {
        return cremaAlmendraFloja;
    }

    public void setCremaAlmendraFloja(int cremaAlmendraFloja) {
        this.cremaAlmendraFloja = cremaAlmendraFloja;
    }

    public int getManchaRoja() {
        return manchaRoja;
    }

    public void setManchaRoja(int manchaRoja) {
        this.manchaRoja = manchaRoja;
    }

    public int getAlterados() {
        return alterados;
    }

    public void setAlterados(int alterados) {
        this.alterados = alterados;
    }

    public int getPobres() {
        return pobres;
    }

    public void setPobres(int pobres) {
        this.pobres = pobres;
    }

    public int getCaidos() {
        return caidos;
    }

    public void setCaidos(int caidos) {
        this.caidos = caidos;
    }

    public int getSobreGrado() {
        return sobreGrado;
    }

    public void setSobreGrado(int sobreGrado) {
        this.sobreGrado = sobreGrado;
    }

    public int getBajoGrado() {
        return bajoGrado;
    }

    public void setBajoGrado(int bajoGrado) {
        this.bajoGrado = bajoGrado;
    }

    public int getMosaico() {
        return mosaico;
    }

    public void setMosaico(int mosaico) {
        this.mosaico = mosaico;
    }

    public int getDanoAnimal() {
        return danoAnimal;
    }

    public void setDanoAnimal(int danoAnimal) {
        this.danoAnimal = danoAnimal;
    }

    public int getExplosivo() {
        return explosivo;
    }

    public void setExplosivo(int explosivo) {
        this.explosivo = explosivo;
    }

    public int getErwinea() {
        return erwinea;
    }

    public void setErwinea(int erwinea) {
        this.erwinea = erwinea;
    }

    public int getDedoCorto() {
        return dedoCorto;
    }

    public void setDedoCorto(int dedoCorto) {
        this.dedoCorto = dedoCorto;
    }

    public int getRacimosPasadosEdad() {
        return racimosPasadosEdad;
    }

    public void setRacimosPasadosEdad(int racimosPasadosEdad) {
        this.racimosPasadosEdad = racimosPasadosEdad;
    }

    public int getCochinillaEscamaFunagina() {
        return cochinillaEscamaFunagina;
    }

    public void setCochinillaEscamaFunagina(int cochinillaEscamaFunagina) {
        this.cochinillaEscamaFunagina = cochinillaEscamaFunagina;
    }

    public int getRacimosSinEdintificacion() {
        return racimosSinEdintificacion;
    }

    public void setRacimosSinEdintificacion(int racimosSinEdintificacion) {
        this.racimosSinEdintificacion = racimosSinEdintificacion;
    }

    public void setSimpleDateFormat(String simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
    }

    public void setDateInMillisecond(double dateInMillisecond) {
        this.dateInMillisecond = dateInMillisecond;
    }

    public String getUniqueIdObject() {
        return uniqueIdObject;
    }

    public void setUniqueIdObject(String uniqueIdObject) {
        this.uniqueIdObject = uniqueIdObject;
    }

    private int manchaRoja;
    private int alterados;
    private int pobres;
    private int caidos;
    private int sobreGrado;
    private int bajoGrado;
    private int mosaico;
    private int danoAnimal;
    private int explosivo;
    private int erwinea;
    private int dedoCorto;
    private int racimosPasadosEdad;
    private int cochinillaEscamaFunagina;
    private int racimosSinEdintificacion;


    // dtaos
    private String extensionistaCalidad ;

    public String getExtensionistaCalidad() {
        return extensionistaCalidad;
    }

    public void setExtensionistaCalidad(String extensionistaCalidad) {
        this.extensionistaCalidad = extensionistaCalidad;
    }

    public String getExtensionistaEnRodillo() {
        return extensionistaEnRodillo;
    }

    public void setExtensionistaEnRodillo(String extensionistaEnRodillo) {
        this.extensionistaEnRodillo = extensionistaEnRodillo;
    }

    public String getExtensionistaEnGancho() {
        return extensionistaEnGancho;
    }

    public void setExtensionistaEnGancho(String extensionistaEnGancho) {
        this.extensionistaEnGancho = extensionistaEnGancho;
    }

    private String extensionistaEnRodillo;
    private String extensionistaEnGancho ;

















    private int semanaNum;
    private String vapor;

    public int getSemanaNum() {
        return semanaNum;
    }

    public void setSemanaNum(int semanaNum) {
        this.semanaNum = semanaNum;
    }

    public String getVapor() {
        return vapor;
    }

    public void setVapor(String vapor) {
        this.vapor = vapor;
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEnfunde() {
        return enfunde;
    }

    public void setEnfunde(String enfunde) {
        this.enfunde = enfunde;
    }

    public String getSimpleDateFormat() {
        return simpleDateFormat;
    }



    public double getDateInMillisecond() {
        return dateInMillisecond;
    }



    public String getNodoKyDondeEstaHasmap() {
        return nodoKyDondeEstaHasmap;
    }

    public void setNodoKyDondeEstaHasmap(String nodoKyDondeEstaHasmap) {
        this.nodoKyDondeEstaHasmap = nodoKyDondeEstaHasmap;
    }

    private String productor;
    private String codigo;
    private String enfunde;
    private String nodoKyDondeEstaHasmap;
    private String simpleDateFormat;

    private String uniqueIdObject;
    private double dateInMillisecond;


    public int getTotalRechazadosAll() {
        return totalRechazadosAll;
    }

    public void setTotalRechazadosAll(int totalRechazadosAll) {
        this.totalRechazadosAll = totalRechazadosAll;
    }

    public CuadroMuestreo(int semanaNum, String exportadora, String vapor, String productor, String codigo, String enfunde, String nodoKyDondeEstaHasmap,
                          String extensionistaCalidad, String extensionistaEnRodillo, String extensionistaEnGancho,

                          String color14, String color13, String color12, String color11, String color10, String color9

                          ) {

        this.semanaNum = semanaNum;
        this.exportadora=exportadora;
        this.vapor = vapor;
        this.productor = productor;
        this.codigo = codigo;
        this.enfunde = enfunde;
        dateInMillisecond = new Date().getTime();
        this.nodoKyDondeEstaHasmap = nodoKyDondeEstaHasmap;
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        simpleDateFormat = formatter.format(dateInMillisecond);
        uniqueIdObject= UUID.randomUUID().toString();

        this.extensionistaCalidad=extensionistaCalidad;
        this.extensionistaEnRodillo=extensionistaEnRodillo;
        this.extensionistaEnGancho=extensionistaEnGancho;
        keyFirebaseLocation="";

          this. color14=color14;
          this.color13=color13;
          this.color12=color12;
          this.color11=color11;
          this.color10=color10;
          this.color9=color9;

          mutantes=0;
          spekling=0;
          ptaAmarillaYb=0;
          cremaAlmendraFloja=0;
          manchaRoja=0;
          alterados=0;
          pobres=0;
          caidos=0;
          sobreGrado=0;
          bajoGrado=0;
          mosaico=0;
          danoAnimal=0;
          explosivo=0;
          erwinea=0;
          dedoCorto=0;
          racimosPasadosEdad=0;
          cochinillaEscamaFunagina=0;
          racimosSinEdintificacion=0;

        totalRechazadosAll =0;

    }



}
