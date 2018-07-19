package com.example.giulia.contactlist;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Giulia on 15/02/2018.
 */

public class Contatto {
    private String descrizione;
    private String username;
    private String password;

    public Contatto(String descrizione, String username, String password) {
        this.descrizione = descrizione;
        this.username = username;
        this.password = password;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

