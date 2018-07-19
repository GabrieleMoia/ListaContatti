package com.example.giulia.contactlist;

public class Nota {

    private String descrizione, username, code;

    public Nota(String descrizione, String username, String code) {
        this.descrizione = descrizione;
        this.username = username;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
