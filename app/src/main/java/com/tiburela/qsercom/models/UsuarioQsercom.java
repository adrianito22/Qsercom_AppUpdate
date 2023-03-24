package com.tiburela.qsercom.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class UsuarioQsercom
{

    public UsuarioQsercom(int tiposUSUARI, String uniqueIDuser, String mailGooglaUser, String nombreUsuario) {
        this.tiposUSUARI = tiposUSUARI;
        this.uniqueIDuser = uniqueIDuser;
        this.mailGooglaUser = mailGooglaUser;
        this.nombreUsuario=nombreUsuario;

    }

public UsuarioQsercom(){


}



    public int getTiposUSUARI() {
        return tiposUSUARI;
    }

    public void setTiposUSUARI(int tiposUSUARI) {
        this.tiposUSUARI = tiposUSUARI;
    }

    public String getUniqueIDuser() {
        return uniqueIDuser;
    }

    public void setUniqueIDuser(String uniqueIDuser) {
        this.uniqueIDuser = uniqueIDuser;
    }

    private int tiposUSUARI; //SI ES insepector de campo o de hacienda
    private String uniqueIDuser;

    private String mailGooglaUser;

    public boolean isUserISaprobadp() {
        return userISaprobadp;
    }

    public void setUserISaprobadp(boolean userISaprobadp) {
        this.userISaprobadp = userISaprobadp;
    }

    private boolean userISaprobadp;


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
        result.put("userISaprobadp", userISaprobadp);



        return  result;

}

}
