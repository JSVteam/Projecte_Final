package com.example.jordi.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Entrar_Usuari extends AppCompatActivity {

    Button boto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar__usuari);
        boto = findViewById(R.id.boto_entrar);



        boto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Entrar_Usuari.this , PaginaPrincipal.class);
                startActivity(intent);
            }
        });

    }


}
