package com.mide.adolf.socialmide;

public class PostedMideObject {

    private String nombreRuta, descripcion, urlImage;

    public PostedMideObject() {
    }

    public PostedMideObject(String nombreRuta, String descripcion, String urlImage) {
        this.nombreRuta = nombreRuta;
        this.descripcion = descripcion;
        this.urlImage = urlImage;
    }

    public String getNombreRuta() {
        return nombreRuta;
    }

    public void setNombreRuta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
