package com.example.giulia.contactlist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giulia on 12/02/2018.
 */

public class Singleton {

    //base di appoggio per i nostri dati
    private static Singleton ourInstance = new Singleton();
    private List notes = new ArrayList<Nota>();

    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {

    }

    public void setItemList(List<Nota> notes) {
        this.notes = notes;
    }

    public List<Nota> getItemList() {
        return this.notes;
    }


}

