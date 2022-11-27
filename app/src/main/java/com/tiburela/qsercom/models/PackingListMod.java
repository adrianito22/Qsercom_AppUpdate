package com.tiburela.qsercom.models;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class PackingListMod {


    public void setTimeCurrenMillisecds(long timeCurrenMillisecds) {
        this.timeCurrenMillisecds = timeCurrenMillisecds;
    }

    public PackingListMod(int totalCajas, String contenedor) {
        this.totalCajas = totalCajas;
        keyOrNodeContainsMapPli = "";
        this.contenedor = contenedor;
        timeCurrenMillisecds = new Date().getTime();
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        simpledatFormt = formatter.format(timeCurrenMillisecds);
       nodoPadreThisObect="";
       uniqueIDinforme = UUID.randomUUID().toString();

    }


    public PackingListMod(){



    }


    private int  totalCajas;

    public int getTotalCajas() {
        return totalCajas;
    }

    public void setTotalCajas(int totalCajas) {
        this.totalCajas = totalCajas;
    }

    public String getKeyOrNodeContainsMapPli() {
        return keyOrNodeContainsMapPli;
    }

    public void setKeyOrNodeContaineHashMap(String keyOrNodeContainsMapPli) {
        this.keyOrNodeContainsMapPli = keyOrNodeContainsMapPli;
    }

    public String getContenedor() {
        return contenedor;
    }

    public void setContenedor(String contenedor) {
        this.contenedor = contenedor;
    }

    public String getSimpledatFormt() {
        return simpledatFormt;
    }

    public void setSimpledatFormt(String simpledatFormt) {
        this.simpledatFormt = simpledatFormt;
    }

    public long getTimeCurrenMillisecds() {
        return timeCurrenMillisecds;
    }


    private String keyOrNodeContainsMapPli;
    private String contenedor;
    private String simpledatFormt;
    private long timeCurrenMillisecds;

    public String getUniqueIDinforme() {
        return uniqueIDinforme;
    }

    public void setUniqueIDinforme(String uniqueIDinforme) {
        this.uniqueIDinforme = uniqueIDinforme;
    }

    private String uniqueIDinforme;


    public String getNodoPadreThisObect() {
        return nodoPadreThisObect;
    }

    public void setNodoPadreThisObect(String nodoPadreThisObect) {
        this.nodoPadreThisObect = nodoPadreThisObect;
    }

    private String nodoPadreThisObect;

    //new Date().getTime();



}
