package com.tiburela.qsercom.models;

public class DefectsAndNumber {

    private int numDefects;
    private boolean ischekedDefecto;


    public DefectsAndNumber(boolean ischekedDefecto, int numDefects) {
        this.ischekedDefecto = ischekedDefecto;
        this.numDefects = numDefects;
    }


    public boolean isIschekedDefecto() {
        return ischekedDefecto;
    }


    public void setIschekedDefecto(boolean ischekedDefecto) {
        this.ischekedDefecto = ischekedDefecto;
    }


    public int getNumDefects() {
        return numDefects;
    }



    public void setNumDefects(int numDefects) {
        this.numDefects = numDefects;
    }



}
