package com.tiburela.qsercom.models;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ControlCalidad {

    private String uniqueId;
    private String simpleDate;
    private double timeDateMillis;

    public String getKeyWhereLocateasHmapFieldsRecha() {
        return keyWhereLocateasHmapFieldsRecha;
    }

    public void setKeyWhereLocateasHmapFieldsRecha(String keyWhereLocateasHmapFieldsRecha) {
        this.keyWhereLocateasHmapFieldsRecha = keyWhereLocateasHmapFieldsRecha;
    }

    private String keyWhereLocateasHmapFieldsRecha;


    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getSimpleDate() {
        return simpleDate;
    }

    public void setSimpleDate(String simpleDate) {
        this.simpleDate = simpleDate;
    }

    public double getTimeDateMillis() {
        return timeDateMillis;
    }

    public void setTimeDateMillis(double timeDateMillis) {
        this.timeDateMillis = timeDateMillis;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getNodekeyLocation() {
        return nodekeyLocation;
    }

    public void setNodekeyLocation(String nodekeyLocation) {
        this.nodekeyLocation = nodekeyLocation;
    }

    private String observaciones;
    private String nodekeyLocation;





    public ControlCalidad( String observaciones, String nodekeyLocation,String keyWhereLocateasHmapFieldsRecha) {
        uniqueId = UUID.randomUUID().toString();
        timeDateMillis = new Date().getTime();
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        simpleDate = formatter.format(timeDateMillis);

        this.observaciones = observaciones;
        this.nodekeyLocation = nodekeyLocation;
        this.keyWhereLocateasHmapFieldsRecha=keyWhereLocateasHmapFieldsRecha;
    }




}
