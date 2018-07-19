package com.example.giulia.contactlist;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giulia on 01/02/2018.
 */

public class AdapterActivity extends ArrayAdapter<Nota> {

    private final Context context;
    ItemDatabaseManager itemDatabaseManager;

    //costruttore
    public AdapterActivity(Context context) {

        super(context, R.layout.linear_item);
        this.context = context;
        this.updateList(context);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getViewOptimize(position, convertView, parent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public View getViewOptimize(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;


        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.linear_item, null);
            viewHolder = new ViewHolder();
            viewHolder.descriptionHolder = (TextView) convertView.findViewById(R.id.descrizione);
            viewHolder.usernameHolder = (TextView) convertView.findViewById(R.id.username);
            viewHolder.codeHolder = (TextView) convertView.findViewById(R.id.code);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.descriptionHolder.setText(DataAccessUtils.getItemByPosition(this.context, position).getDescrizione());
        viewHolder.usernameHolder.setText(DataAccessUtils.getItemByPosition(this.context, position).getUsername());
        viewHolder.codeHolder.setText(DataAccessUtils.getItemByPosition(this.context, position).getCode());
        return convertView;
    }


    public void updateList(Context context) {

        itemDatabaseManager = new ItemDatabaseManager(context);
        itemDatabaseManager.open();
        Cursor cursor = itemDatabaseManager.fetchAllItems();
        cursor.moveToFirst();
        int index = cursor.getCount();

        if (index > 0) {
            int i = 0;
            do {
                Nota nota = new Nota(
                        cursor.getString(cursor.getColumnIndex(itemDatabaseManager.KEY_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(itemDatabaseManager.KEY_USERNAME)),
                        cursor.getString(cursor.getColumnIndex(itemDatabaseManager.KEY_CODE)));
                i++;
                DataAccessUtils.addItem(nota,context);
                cursor.moveToNext();
            } while (i < index);
        }
        itemDatabaseManager.close();
    }

    public class ViewHolder {
        private TextView descriptionHolder;
        private TextView usernameHolder;
        private TextView codeHolder;
    }


    public void setValues() {
        //values=Singleton.getInstance().getItemList();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return Singleton.getInstance().getItemList().size();

    }

}

