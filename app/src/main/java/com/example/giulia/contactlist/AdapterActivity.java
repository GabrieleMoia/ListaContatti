package com.example.giulia.contactlist;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static com.example.giulia.contactlist.DataAccessUtils.getColorForPosition;

/**
 * Created by Giulia on 01/02/2018.
 */

public class AdapterActivity extends ArrayAdapter<Contatto> {

    private final Context context;
    private List<Contatto> contactlist;

    //costruttore
    public AdapterActivity(Context context, List<Contatto> contactlist) {

        super(context, R.layout.linear_item, contactlist);
        this.contactlist = contactlist;
        this.context = context;
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

        Contatto contatto = DataAccessUtils.getItemByPosition(this.context, position);
        viewHolder.nameHolder.setText(contatto.getNome());
        viewHolder.numberHolder.setText(contatto.getNumero());
        viewHolder.imageHolder.setBackgroundColor(getColorForPosition(this.context, position));
        //Recupero il valore della mia preference e setto la stella visibile se corrispondono i numeri di cellulare
        String favourite = DataAccessUtils.getOnSharedPreferences(context);
        if (!favourite.equals(null) && favourite.equals(contatto.getNumero())) {
            viewHolder.starHolder.setVisibility(View.VISIBLE);
        } else {
            viewHolder.starHolder.setVisibility(View.INVISIBLE);
        }

        return convertView;
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


    //getfavourites delle shall preferences if(!=null && =numero di telefono)
    //setVisible sulla stellina visible
    //starImage.setVisibility(View.VISIBLE)
    //GONE


        /*View rowView = inflater.inflate(R.layout.linear_item, parent,false); //convertire un xml in una view

        //set label
        TextView nameView = (TextView) rowView.findViewById(R.id.nome);
        String itemName = this.contactlist.get(position).getNome();
        nameView.setText(itemName);

        TextView numberView = (TextView) rowView.findViewById(R.id.number);
        String itemNumber = this.contactlist.get(position).getNumero();
        numberView.setText(itemNumber);

        // Set icon di defualt
        ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
        imageView.setImageResource(R.drawable.ic_fruit);

        /*String url="ic_"+itemName.toLowerCase();
        //per prendere le immagini corrispondenti ad ogni nome del frutto
        if(!TextUtils.isEmpty(url))
        {
            //uso il try perchè non sono sicruo di avere tutte le immagini
            try {
                int imageResource = context.getResources().getIdentifier(url, "drawable", getContext().getPackageName());

                Drawable image = context.getResources().getDrawable(imageResource,null);
                imageView.setImageDrawable(image);
            }
            catch(Exception e){}

        }

        return rowView;
    }*/


}

