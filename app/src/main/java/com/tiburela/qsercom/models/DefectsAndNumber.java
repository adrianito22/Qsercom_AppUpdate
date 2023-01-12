package com.tiburela.qsercom.models;

public class DefectsAndNumber {

    private int numDefects;
    private boolean ischekedDefecto;

    public String getTagDefect() {
        return tagDefect;
    }

    public void setTagDefect(String tagDefect) {
        this.tagDefect = tagDefect;
    }

    private String tagDefect;


    public String getDefectName() {
        return defectName;
    }

    public void setDefectName(String defectName) {
        this.defectName = defectName;
    }

    private String defectName;


    public DefectsAndNumber(boolean ischekedDefecto, int numDefects) {
        this.ischekedDefecto = ischekedDefecto;
        this.numDefects = numDefects;
        defectName="";
        tagDefect="";
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
