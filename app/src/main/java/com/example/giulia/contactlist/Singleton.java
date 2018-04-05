package com.example.giulia.contactlist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giulia on 12/02/2018.
 */

public class Singleton {

    //base di appoggio per i nostri dati
    private static Singleton ourInstance = new Singleton();
    private List contactList = new ArrayList<Contatto>();

    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {

    }

    public void setItemList(List<Contatto> contactList) {
        this.contactList = contactList;
    }

    public List<Contatto> getItemList() {
        return this.contactList;
    }


}

