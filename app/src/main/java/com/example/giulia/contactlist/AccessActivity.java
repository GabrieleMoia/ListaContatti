package com.example.giulia.contactlist;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AccessActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.access_activity);
        final TextView textCode = findViewById(R.id.textCode);
        setTitle("Accedi");
        getSupportActionBar().hide();
        textCode.setText("");
        if (DataAccessUtils.getOnSharedPreferences(getApplicationContext())) {
            final TextView firstTutorial = findViewById(R.id.first_tutorial);
            final TextView secondTutorial = findViewById(R.id.second_tutorial);

            firstTutorial.setVisibility(View.INVISIBLE);
            secondTutorial.setVisibility(View.INVISIBLE);
        }


    }

    public void access(View v) {
        CodeDatabaseManager codeDatabaseManager = new CodeDatabaseManager(getApplicationContext());
        codeDatabaseManager.open();
        final TextView textCode = findViewById(R.id.textCode);
        Button button = (Button) v;
        String buttonText = button.getText().toString();
        textCode.append(buttonText);
        if (!DataAccessUtils.getOnSharedPreferences(getApplicationContext())) {
            if (textCode.getText().toString().length() == 4) {
                Long cursor = codeDatabaseManager.createItem(textCode.getText().toString());
                Log.d("cursor", cursor.toString());
                codeDatabaseManager.close();
                DataAccessUtils.writeOnSharedPreferences(true, getApplicationContext());
                Intent intent = new Intent(AccessActivity.this, MainActivity.class);
                startActivity(intent);
            }
        } else {
            if (textCode.getText().toString().length() == 4) {
                Cursor cursor = codeDatabaseManager.fetchAllItems();
                cursor.moveToFirst();
                int index = cursor.getCount();

                if (index > 0) {
                    int i = 0;
                    do {
                        AccessCode accessCode = new AccessCode(
                                cursor.getString(cursor.getColumnIndex(CodeDatabaseManager.KEY_ACCESS_CODE)));

                        if (accessCode.getAccessCode().equals(textCode.getText().toString())) {
                            Intent intent = new Intent(AccessActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            textCode.setText("");
                            textCode.setHint("****");
                        }
                        i++;
                        Log.d("KEY_ACCESS_CODE", accessCode.getAccessCode());
                        cursor.moveToNext();
                    } while (i < index);
                }
                codeDatabaseManager.close();
            }
        }
    }

    public void back(View v) {
        final TextView textCode = findViewById(R.id.textCode);
        String code = textCode.getText().toString();
        if (code != null && code.length() > 0) {
            textCode.setText(code.substring(0, code.length() - 1));
        }
    }

    public void view(View v) {
        final TextView textCode = findViewById(R.id.textCode);
        if (textCode.getInputType() == InputType.TYPE_CLASS_TEXT) {
            textCode.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        } else {
            textCode.setInputType(InputType.TYPE_CLASS_TEXT);
        }
    }

}
