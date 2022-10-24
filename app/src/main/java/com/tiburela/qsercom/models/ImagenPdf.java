package com.tiburela.qsercom.models;

import android.net.Uri;

public class ImagenPdf extends Celda {
    public Uri getUriImagenUbicacion() {
        return UriImagenUbicacion;
    }

    public void setUriImagenUbicacion(Uri uriImagenUbicacion) {
        UriImagenUbicacion = uriImagenUbicacion;
    }

    private  Uri UriImagenUbicacion;


    public ImagenPdf(int startX, int endX, int startY, int endY,Uri UriImagenUbicacion ) {
        super(startX, endX, startY, endY);



        this.UriImagenUbicacion=UriImagenUbicacion;
    }


}
