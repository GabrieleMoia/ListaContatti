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

import static com.example.giulia.contactlist.DataAccessUtils.getColorForPosition;

/**
 * Created by Giulia on 01/02/2018.
 */

public class AdapterActivity extends ArrayAdapter<Contatto> {

    private final Context context;
    private List<Contatto> contactlist = new ArrayList<>();
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
            viewHolder.nameHolder = (TextView) convertView.findViewById(R.id.nome);
            viewHolder.numberHolder = (TextView) convertView.findViewById(R.id.numero);
            viewHolder.imageHolder = (ImageView) convertView.findViewById(R.id.logo);
            viewHolder.starHolder = (ImageView) convertView.findViewById(R.id.star);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.nameHolder.setText(contactlist.get(position).getNome());
        viewHolder.numberHolder.setText(contactlist.get(position).getNumero());
        viewHolder.imageHolder.setBackgroundColor(getColorForPosition(this.context, position));
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
                Contatto contatto = new Contatto(cursor.getString(cursor.getColumnIndex(itemDatabaseManager.KEY_ID)),
                        cursor.getString(cursor.getColumnIndex(itemDatabaseManager.KEY_NAME)),
                        cursor.getString(cursor.getColumnIndex(itemDatabaseManager.KEY_NUMBER)));
                i++;
                cursor.moveToNext();
                this.contactlist.add(contatto);
            } while (i < index);
        }
    }

    public class ViewHolder {
        private TextView nameHolder;
        private TextView numberHolder;
        private ImageView imageHolder;
        private ImageView starHolder;
    }


    public void setValues() {
        //values=Singleton.getInstance().getItemList();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return contactlist.size();

    }

}

