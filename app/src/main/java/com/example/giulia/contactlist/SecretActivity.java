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

        final EditText name = findViewById(R.id.editName);
        final EditText number = findViewById(R.id.editNumber);

        name.setText("");
        number.setText("");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent resultIntent = new Intent(SecretActivity.this, MainActivity.class);

                String nameResult = name.getText().toString();
                String numberResult = number.getText().toString();


                resultIntent.putExtra("name", nameResult);
                resultIntent.putExtra("number", numberResult);


                setResult(2, resultIntent);

                finish();

            }
        });

            /*Intent i=getIntent();
            String txt=i.getStringExtra("text");

            TextView txv=(TextView) findViewById(R.id.textName);
            txv.setText(txt);*/
    }
}
