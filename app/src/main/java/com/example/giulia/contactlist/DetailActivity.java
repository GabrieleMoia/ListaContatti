package com.example.giulia.contactlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Giulia on 19/02/2018.
 */

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        setTitle("Dettagli Contatto");
        final Button back = findViewById(R.id.save_pref);

        Intent det = getIntent();

        //Detail
        TextView name_rec = findViewById(R.id.text_name);
        TextView num_rec = findViewById(R.id.text_number);

        String name = det.getExtras().getString("nome");
        final String number = det.getExtras().getString("numero");


        name_rec.setText(name);
        num_rec.setText(number);

        back.setOnClickListener(new View.OnClickListener() { //si fa il setvaluepreferences
            @Override
            public void onClick(View v) {

                DataAccessUtils.writeOnSharedPreferences(number, DetailActivity.this);
                DataAccessUtils.getOnSharedPreferences(DetailActivity.this);
                finish();
            }
        });


    }
}
