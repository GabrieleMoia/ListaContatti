package com.example.giulia.contactlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Giulia on 07/02/2018.
 */

public class SecretActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.secretactivity);
        setTitle("Aggiungi Contatto");
        final Button button = findViewById(R.id.confirm);

        final EditText description = findViewById(R.id.editDescription);
        final EditText username = findViewById(R.id.editUsername);
        final EditText code = findViewById(R.id.editCode);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent resultIntent = new Intent(SecretActivity.this, MainActivity.class);

                String descriptionResult = description.getText().toString();
                String usernameResult = username.getText().toString();
                String codeResult = code.getText().toString();


                resultIntent.putExtra("description", descriptionResult);
                resultIntent.putExtra("username", usernameResult);
                resultIntent.putExtra("code", codeResult);


                setResult(2, resultIntent);

                finish();

            }
        });
    }
}
