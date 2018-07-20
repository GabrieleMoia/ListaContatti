package com.example.giulia.contactlist;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class AccessActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.access_activity);
        final Button button = findViewById(R.id.confirm);
        final EditText editCode = findViewById(R.id.editCode);
        setTitle("Accedi");
        getSupportActionBar().hide();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String access_code = editCode.getText().toString();
                CodeDatabaseManager codeDatabaseManager = new CodeDatabaseManager(getApplicationContext());
                codeDatabaseManager.open();
                if (!DataAccessUtils.getOnSharedPreferences(getApplicationContext())) {
                    if (access_code.length() < 4) {
                        editCode.setHint("Errato");
                    } else {
                        Long cursor = codeDatabaseManager.createItem(access_code);
                        Log.d("cursor", cursor.toString());
                        codeDatabaseManager.close();
                        DataAccessUtils.writeOnSharedPreferences(true, getApplicationContext());
                        Intent intent = new Intent(AccessActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                } else {
                    Cursor cursor = codeDatabaseManager.fetchAllItems();
                    cursor.moveToFirst();
                    int index = cursor.getCount();

                    if (index > 0) {
                        int i = 0;
                        do {
                            AccessCode accessCode = new AccessCode(
                                    cursor.getString(cursor.getColumnIndex(CodeDatabaseManager.KEY_ACCESS_CODE)));

                            if (accessCode.getAccessCode().equals(access_code)) {
                                Intent intent = new Intent(AccessActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                editCode.setHint("Errato");
                            }
                            i++;
                            Log.d("KEY_ACCESS_CODE", accessCode.getAccessCode());
                            cursor.moveToNext();
                        } while (i < index);
                    }
                    codeDatabaseManager.close();
                }
            }
        });
    }
}
