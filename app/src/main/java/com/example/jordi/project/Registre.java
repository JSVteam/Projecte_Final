package com.example.jordi.project;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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
    private CheckBox acceptar_condicions;
    private Dialog popup;
    private TextView textView_politica;

    //Declarem aquest String al qual aquesta url que esta en el nostre servidor , ens permet poder registrar-se.
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
        textView_politica = findViewById(R.id.textview_condicions);
        popup = new Dialog(this);

        subrayar_Texto(textView_politica);

        textView_politica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarPopup();
            }
        });

        final Response.Listener<String> respoListern = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) { // Para guardar les dades del usuari.
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
            public void onResponse(String response) { // Comprovem que el correo.
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

                                    if (password.getText().toString().equals(c_password.getText().toString())) {

                                        // Crear un objecto de tipo comprobar correo para comprobar si el correo esta registrado o no.
                                        Comprovar_Email comprobar_email = new Comprovar_Email(email.getText().toString(), respoListernEmail);
                                        RequestQueue requestEmail = Volley.newRequestQueue(Registre.this);
                                        requestEmail.add(comprobar_email);


                                    } else { // les contrasenyes no son iguals.
                                        c_password.setError("Las contrasenyes no son iguals");
                                        c_password.requestFocus();
                                    }
                                } else {// reptir contrasenya.
                                    c_password.setError("Repeteix la contrasenya");
                                    c_password.requestFocus();

                                }
                            } else { // La contrasenya té menys de 5 caracters.
                                password.setError("Introduir una constrasenya valida");
                                password.requestFocus();
                            }
                        } else {// Correu
                            email.setError("Introduir un Email");
                            email.requestFocus();
                        }
                    } else {//Cognoms
                        cognoms.setError("Introduir els Cognoms");
                        cognoms.requestFocus();
                    }
                } else {// Nom
                    nom.setError("Introduir el Nom");
                    nom.requestFocus();
                }
            }
        });
    }

    // Aquest és el metode que farà servir per registrar-se
    private void Regist() {

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
                                Intent accedir_usuari = new Intent(getApplicationContext(), Entrar_Usuari.class);
                                startActivity(accedir_usuari);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Registre.this, "Register Error!" + e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Registre.this, "Register Error!" + error.toString(), Toast.LENGTH_SHORT).show();
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


    public void MostrarPopup() {
        TextView cerrarpopup, titulopopup;
        popup.setContentView(R.layout.layout_ventana_popup);
        cerrarpopup = popup.findViewById(R.id.cerrar_popup);
        titulopopup = popup.findViewById(R.id.titulo_popup);
        subrayar_Texto(titulopopup);


        cerrarpopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });

        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popup.show();
    }

    public void subrayar_Texto(TextView texto) {
        SpannableString subrayado = new SpannableString(texto.getText());
        subrayado.setSpan(new UnderlineSpan(), 0, subrayado.length(), 0);
        texto.setText(subrayado);

    }

}
