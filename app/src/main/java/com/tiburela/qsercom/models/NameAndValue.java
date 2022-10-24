package com.tiburela.qsercom.models;

public class NameAndValue {

    public NameAndValue(String nameFields, String valueContent) {
        this.nameFields = nameFields;
        ValueContent = valueContent;
    }

    private String nameFields ;
    private String ValueContent ;

    public String getNameFields() {
        return nameFields;
    }

    public void setNameFields(String nameFields) {
        this.nameFields = nameFields;
    }

    public String getValueContent() {
        return ValueContent;
    }

    public void setValueContent(String valueContent) {
        ValueContent = valueContent;
    }


}
