package com.example.giulia.contactlist;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giulia on 12/02/2018.
 */

public class DataAccessUtils {

    public static void initDataSource(Context context) {
        List<Nota> notes = new ArrayList<>();
        Singleton.getInstance().setItemList(notes);
    }

    public static List<Nota> getDataSourceItemList(Context context) {
        return Singleton.getInstance().getItemList();
    }

    public static void addItem(Nota nota, Context context) {
        Singleton.getInstance().getItemList().add(nota);
    }

    public static void removeItem(Nota nota, Context context) {
        Singleton.getInstance().getItemList().remove(nota);
    }

    public static Nota getItemByPosition(Context context, int positon) {
        Nota contByPos = Singleton.getInstance().getItemList().get(positon);
        return contByPos;
    }

    /*//Metodi per scrivere nella shared preferences
    public static void writeOnSharedPreferences(String number, Context context) {
        //dichiarazione shared preferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("favourite", Context.MODE_PRIVATE);
        //dichiarazione edit dove scrivo la chiave e il valore corrispondente
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("preferito", number);
        editor.commit();
    }

    public static String getOnSharedPreferences(Context context) {

        //recupero dell chiave
        SharedPreferences sharedPreferences = context.getSharedPreferences("favourite", Context.MODE_PRIVATE);
        String favourite = sharedPreferences.getString("preferito", "DEFAULT");
        return favourite;
    }
    //setFavouriteValueInPreferences (context, String favourite value)
    //getFavouriteValueInPreferences (context)*/
}
