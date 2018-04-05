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
        List<Contatto> contattoList = new ArrayList<Contatto>();

        contattoList.add(new Contatto("Gabriele", "3931943822"));
        contattoList.add(new Contatto("Mamma", "3383713708"));
        contattoList.add(new Contatto("Papà", "3383764074"));
        Singleton.getInstance().setItemList(contattoList);
    }

    public static List<Contatto> getDataSourceItemList(Context context) {
        return Singleton.getInstance().getItemList();
    }

    public static void addItem(Contatto contact, Context context) {
        Singleton.getInstance().getItemList().add(contact);
    }

    public static void removeItem(Contatto contact, Context context) {
        Singleton.getInstance().getItemList().remove(contact);
    }

    public static Contatto getItemByPosition(Context context, int positon) {
        Contatto contByPos = Singleton.getInstance().getItemList().get(positon);
        return contByPos;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static int getColorForPosition(Context context, int position) {
        if (position % 2 == 0) {
            return context.getResources().getColor(R.color.light_blue);
        } else {

        }
        return context.getResources().getColor(R.color.light_grey);
    }


    //Metodi per scrivere nella shared preferences
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
    //getFavouriteValueInPreferences (context)
}
