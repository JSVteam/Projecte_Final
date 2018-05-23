package com.example.jordi.project;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registre extends AppCompatActivity {

    private EditText nom, cognoms, email, password, c_password;
    private Button boto_registrar;
    private ProgressBar loading;
    private static String URL_RESISTRAR = "http://80.211.40.68/ProjecteFinal/registrar.php";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);

        final ProgressDialog progressDialog = new ProgressDialog(this,
                R.style.Theme_AppCompat_DayNight_Dialog);
        nom = findViewById(R.id.edit_nom);
        cognoms = findViewById(R.id.edit_cognoms);
        email = findViewById(R.id.edit_email);
        password = findViewById(R.id.edit_password);
        c_password = findViewById(R.id.edit_c_password);
        boto_registrar = findViewById(R.id.boto_registrarse);

        final Response.Listener<String> respoListern = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) { // Para guardar los datos del usuario.
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        Intent intent = new Intent(Registre.this, PaginaPrincipal.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Registre.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        final Response.Listener<String> respoListernEmail = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) { // La comprobacion del correo
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {

                        email.setError("Este e-mail ya esta registrado");
                        email.requestFocus();
                    } else {

                        Regist();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        boto_registrar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if (nom.getText().toString().length() > 1) {
                    if (cognoms.getText().toString().length() > 1) {
                        if (email.getText().toString().length() > 1) {
                            if (password.getText().toString().length() > 5) {
                                if (c_password.getText().toString().length() > 5) {
                                    if (password.equals(c_password)) {

                                        // Crear un objecto de tipo comprobar correo para comprobar si el correo esta registrado o no.
                                        Comprovar_Email comprobar_email = new Comprovar_Email(email.getText().toString(), respoListernEmail);
                                        RequestQueue requestEmail = Volley.newRequestQueue(Registre.this);
                                        requestEmail.add(comprobar_email);


                                    } else { // las contraseñas no son iguales
                                        c_password.setError("Las contrasenyes no son iguals");
                                        c_password.requestFocus();
                                    }
                                } else {// repetir contraseña
                                    c_password.setError("Repeteix la contrasenya");
                                    c_password.requestFocus();

                                }
                            } else { // La contraseña tiene menos de 6 caracteres
                                password.setError("Introduir una constrasenya valida");
                                password.requestFocus();
                            }
                        } else {// Correo
                            email.setError("Introduir un Email");
                            email.requestFocus();
                        }
                    } else {//apellidos
                        cognoms.setError("Introduir els Cognoms");
                        cognoms.requestFocus();
                    }
                } else {// Nombre
                    nom.setError("Introduir el Nom");
                    nom.requestFocus();
                }
            }
        });
    }

    private void Regist() {
        //boto_registrar.setVisibility(View.GONE);

        final String nom = this.nom.getText().toString().trim();
        final String cognoms = this.cognoms.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String contrasenya = this.password.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_RESISTRAR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String succes = jsonObject.getString("success");

                            if (succes.equals("1")) {
                                Toast.makeText(Registre.this, "Register Success!", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Registre.this, "Register Error!" + e.toString(), Toast.LENGTH_SHORT).show();
                            //boto_registrar.setVisibility(View.VISIBLE);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Registre.this, "Register Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                        //boto_registrar.setVisibility(View.VISIBLE);
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("nom", nom);
                params.put("cognoms", cognoms);
                params.put("email", email);
                params.put("contrasenya", contrasenya);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
