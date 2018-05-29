package com.example.jordi.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Entrar_Usuari extends AppCompatActivity {

    Button iniciar_sesion;
    EditText email;
    EditText contrasenya;
    Boolean sesion_iniciada = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar_usuari);

        //Si true, inicia el MainActivity
        if (shared_Preferences.getSharedPreferences(this)) {

            Intent intent = new Intent(getApplicationContext(), PaginaPrincipal.class);
            startActivity(intent);
            finish();
        } else {
            final ProgressDialog progressDialog = new ProgressDialog(this,
                    R.style.Theme_AppCompat_DayNight_Dialog);
            iniciar_sesion = findViewById(R.id.boto_entrar);
            email = findViewById(R.id.tv_email);
            contrasenya = findViewById(R.id.tv_password);

            final Response.Listener<String> respoListern = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if (success) {
                            sesion_iniciada = true;
                            shared_Preferences.setSharedPreferences(getApplicationContext(),sesion_iniciada);
                            Intent intent = new Intent(getApplicationContext(), Contingut_PaginaPrincipal.class);
                            startActivity(intent);
                            progressDialog.dismiss();
                            progressDialog.hide();
                            finish();

                        } else {
                            progressDialog.dismiss();
                            progressDialog.hide();
                            Toast.makeText(getApplicationContext(), "No se ha podido iniciar sesión", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            iniciar_sesion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Crear un objecto de tipo inicar sesion con los datos introducido.
                    Login iniciar_sesion = new Login(email.getText().toString(), contrasenya.getText().toString(), respoListern);
                    RequestQueue requestQueue = Volley.newRequestQueue(Entrar_Usuari.this);
                    requestQueue.add(iniciar_sesion);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Iniciando sesión...");
                    progressDialog.show();
                }
            });
        }
    }


}
