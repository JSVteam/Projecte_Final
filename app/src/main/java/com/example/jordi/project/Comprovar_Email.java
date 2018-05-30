package com.example.jordi.project;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Comprovar_Email extends StringRequest {

    //En aquest metode el fem servir per comprovar que l'email que introduim al Login exisiteixi a la Base de dades
    private static final String Comprovar_email = "http://80.211.40.68/ProjecteFinal/comprovar_email.php";
    private Map<String, String> params;

    public Comprovar_Email(String email, Response.Listener<String> listener) {
        super(Request.Method.POST, Comprovar_email, listener, null);
        params = new HashMap<>();
        params.put("email", email);
    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }
}
