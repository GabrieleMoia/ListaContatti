package com.example.giulia.contactlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {


    AdapterActivity adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        DataAccessUtils.initDataSource(this);
        adapter = new AdapterActivity(getApplicationContext());

        ListView listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(adapter);
        setTitle("Note");


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                adb.setTitle("Remove");
                adb.setMessage("Sei sciuro di rimuovere l'elemento");

                adb.setPositiveButton(R.string.alert_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //cose
                        DataAccessUtils.removeItem(Singleton.getInstance().getItemList().get(position), getApplicationContext());
                        adapter.setValues();
                    }
                });
                adb.setNegativeButton(R.string.alert_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                adb.show();
                return true;

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent det = new Intent(MainActivity.this, DetailActivity.class); //Far partire un'altra applicazione

                Nota cont = DataAccessUtils.getItemByPosition(getApplicationContext(), position);

                String description = cont.getDescrizione();
                String username = cont.getUsername();
                String code = cont.getCode();


                det.putExtra("description", description);
                det.putExtra("username", username);
                det.putExtra("code", code);

                startActivity(det);
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
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

                Intent i = new Intent(MainActivity.this, AddActivity.class); //Far partire un'altra applicazione
                startActivityForResult(i, 2);


            }
            default: {
                return super.onOptionsItemSelected(item);
            }

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {

            String descriptionResult = (String) data.getExtras().getString("description");
            String usernameResult = (String) data.getExtras().getString("username");
            String codeResult = (String) data.getExtras().getString("code");

            Nota nota = new Nota(descriptionResult, usernameResult, codeResult);
            DataAccessUtils.addItem(nota, getApplicationContext());
            ItemDatabaseManager itemDatabaseManager = new ItemDatabaseManager(this);
            itemDatabaseManager.open();
            Long cursor = itemDatabaseManager.createItem(descriptionResult, usernameResult, codeResult);
            Log.d("cursor", cursor.toString());
            itemDatabaseManager.close();
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
