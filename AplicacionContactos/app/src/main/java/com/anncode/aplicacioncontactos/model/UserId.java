package com.anncode.aplicacioncontactos.model;

/**
 * Created by rodomualdo on 15/03/2017.
 */

public class UserId {


    private String id;
    private String nombreCompleto;

    public UserId(String id, String nombreCompleto){
        this.id = id;
        this.nombreCompleto = nombreCompleto;
    }
    public UserId(){

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

}
