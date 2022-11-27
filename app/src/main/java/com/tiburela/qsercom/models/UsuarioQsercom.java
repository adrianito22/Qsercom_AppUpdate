package com.tiburela.qsercom.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class UsuarioQsercom
{

    public UsuarioQsercom(String tiposUSUARI, String uniqueIDuser, String mailGooglaUser, String nombreUsuario) {
        this.tiposUSUARI = tiposUSUARI;
        this.uniqueIDuser = uniqueIDuser;
        this.mailGooglaUser = mailGooglaUser;
        this.nombreUsuario=nombreUsuario;

    }





    public String getTiposUSUARI() {
        return tiposUSUARI;
    }

    public void setTiposUSUARI(String tiposUSUARI) {
        this.tiposUSUARI = tiposUSUARI;
    }

    public String getUniqueIDuser() {
        return uniqueIDuser;
    }

    public void setUniqueIDuser(String uniqueIDuser) {
        this.uniqueIDuser = uniqueIDuser;
    }

    private String tiposUSUARI; //SI ES insepector de campo o de hacienda
    private String uniqueIDuser;

    private String mailGooglaUser;

    public String getMailGooglaUser() {
        return mailGooglaUser;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    private String nombreUsuario;





    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("tiposUSUARI", tiposUSUARI);
        result.put("uniqueIDuser", uniqueIDuser);
        result.put("mailGooglaUser", mailGooglaUser);

        result.put("nombreUsuario", nombreUsuario);


        return  result;

}

}
