package com.example.giulia.contactlist;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Giulia on 15/02/2018.
 */

public class Contatto {
    String id;
    String nome;
    String numero;

    public Contatto(String id, String nome, String numero) {
        this.nome = nome;
        this.numero = numero;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public String getNome() {

        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

}

