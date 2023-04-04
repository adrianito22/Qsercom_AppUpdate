package com.tiburela.qsercom.models;

import java.util.UUID;

public class Exportadora {

    public String getNameExportadora() {
        return nameExportadora;
    }

    public void setNameExportadora(String nameExportadora) {
        this.nameExportadora = nameExportadora;
    }

    public String getIdExportadora() {
        return idExportadora;
    }



    private String nameExportadora;
    private String idExportadora;



    public Exportadora(String nameExportadora){
     this.nameExportadora=nameExportadora;
     idExportadora= UUID.randomUUID().toString();



    }

    public Exportadora(){



    }
}
