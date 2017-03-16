package com.anncode.aplicacioncontactos.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/**
 * Created by anahisalgado on 19/04/16.
 */
public class Contacto implements Parcelable {
    private String id;
    private String nombreCompleto;
    private String urlFoto;
    private int likes = 0;

    public Contacto(String foto, String nombre, int likes) {
        this.urlFoto = foto;
        this.nombreCompleto = nombre;
        this.likes = likes;
    }

    public Contacto() {

    }

    public Contacto(Parcel in) {
        this.id = in.readString();
        this.nombreCompleto = in.readString();
        this.urlFoto = in.readString();
        this.likes = in.readInt();
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    public String getId() {
         return id;
    }

    public void setId(String id) {
       this.id = id;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.nombreCompleto);
        dest.writeString(this.urlFoto);
        dest.writeInt(this.likes);
    }

    public static final Parcelable.Creator<Contacto> CREATOR
            = new Parcelable.Creator<Contacto>() {
        public Contacto createFromParcel(Parcel in) {
            return new Contacto(in);
        }

        public Contacto[] newArray(int size) {
            return new Contacto[size];
        }
    };

}
