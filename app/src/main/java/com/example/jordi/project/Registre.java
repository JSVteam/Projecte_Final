package com.example.jordi.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Registre extends AppCompatActivity {

    Button registrarse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);
        registrarse = findViewById(R.id.boto_registrarse);

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_registrarse = new Intent(Registre.this , Entrar_Usuari.class);
                startActivity(intent_registrarse);
            }
        });
    }


}
