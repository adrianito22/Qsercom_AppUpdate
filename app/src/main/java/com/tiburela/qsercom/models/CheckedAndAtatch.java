package com.tiburela.qsercom.models;

public class CheckedAndAtatch {

    public String getDataFirst() {
        return dataFirst;
    }

    public void setDataFirst(String dataFirst) {
        this.dataFirst = dataFirst;
    }

    public String getDataSecond() {
        return dataSecond;
    }

    public void setDataSecond(String dataSecond) {
        this.dataSecond = dataSecond;
    }

    public String getDataChecboxTxt() {
        return dataChecboxTxt;
    }

    public void setDataChecboxTxt(String dataChecboxTxt) {
        this.dataChecboxTxt = dataChecboxTxt;
    }

    public boolean isItemChek() {
        return itemChek;
    }

    public void setItemChek(boolean itemChek) {
        this.itemChek = itemChek;
    }

    private String dataFirst;
    private String dataSecond;
    private String dataChecboxTxt;

    public String getUniqueID() {
        return uniqueID;
    }

    private String uniqueID;

    private boolean itemChek;

    public CheckedAndAtatch(String dataFirst, String dataSecond, String dataChecboxTxt, boolean itemChek, String uniqueID) {
        this.dataFirst = dataFirst;
        this.dataSecond = dataSecond;
        this.dataChecboxTxt = dataChecboxTxt;
        this.itemChek = itemChek;
        this.uniqueID=uniqueID;
    }



}
