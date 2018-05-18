package com.example.jordi.project;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Login extends StringRequest{

    private static final  String Iniciar_sesion_URI = "http://80.211.40.68/ProjecteFinal/iniciar_sesion.php";
    private Map<String, String> params;
    public Login(String email, String contrasenya, Response.Listener<String> listener){
        super(Request.Method.POST, Iniciar_sesion_URI, listener, null);
        params = new HashMap<>();
        params.put("email", email);
        params.put("contrasenya", contrasenya);
    }
    @Override
    public Map<String, String> getParams() {

        return params;
    }
}
