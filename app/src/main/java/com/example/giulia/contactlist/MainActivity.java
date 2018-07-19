package com.example.giulia.contactlist;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    AdapterActivity adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        DataAccessUtils.initDataSource(this);
        adapter = new AdapterActivity(this, DataAccessUtils.getDataSourceItemList(getApplicationContext()));

        ListView listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(adapter);
        setTitle("Codici");


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                //final TextView t = (TextView) view.findViewById(R.id.label);

                AlertDialog.Builder adb1 = new AlertDialog.Builder(MainActivity.this);
                adb1.setTitle("Remove");
                adb1.setMessage("Sei sciuro di rimuovere l'elemento");

                adb1.setPositiveButton(R.string.alert_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //cose
                        DataAccessUtils.removeItem(Singleton.getInstance().getItemList().get(position), getApplicationContext());
                        adapter.setValues();
                    }
                });
                adb1.setNegativeButton(R.string.alert_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                adb1.show();
                return true;

            }
        });

    }

    //Metodo per refreshare la view
    @Override
    protected void onResume() {
        super.onResume();
        adapter.setValues();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add: {

                Intent i = new Intent(MainActivity.this, SecretActivity.class); //Far partire un'altra applicazione
                startActivityForResult(i, 2);


            }
            default: {
                return super.onOptionsItemSelected(item);
            }

        }

    }


    //Apparizione del toast (label con quello che ho schiacciato)


    /*Toast.makeText(getApplicationContext(), t.getText(), Toast.LENGTH_SHORT).show();*/


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {

            String nameResult = (String) data.getExtras().getString("name");
            String numberResult = (String) data.getExtras().getString("number");
            String passwordResult = (String) data.getExtras().getString("password");

            Contatto contactResult = new Contatto(nameResult, numberResult, passwordResult );

            DataAccessUtils.addItem(contactResult, getApplicationContext());
            adapter.setValues();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

}
