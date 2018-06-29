package com.example.android.filemanagementsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String url = "http://192.168.43.252:8888/login.php";
    private EditText editTextusername,editTextpassword;
    private Button buttonlogin,buttonsignup;
    private ProgressDialog progressDialog;
  //  private String message;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedpreferenceseditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextusername = (EditText)findViewById(R.id.logineditTextusername);
        editTextpassword = (EditText)findViewById(R.id.logineditTextpassword);
        buttonlogin = (Button)findViewById(R.id.loginbuttonlogin);
        buttonsignup = (Button)findViewById(R.id.loginbuttonsignup);

        progressDialog = new ProgressDialog(this);

        buttonlogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        authenticateuser();
                    }
                }
        );
    }

    public void signupPage(View v) {
        Intent i = new Intent(this, signup.class);
        startActivity(i);
    }

    public void authenticateuser(){
        final String username = editTextusername.getText().toString().trim();
        final String password = editTextpassword.getText().toString().trim();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedpreferenceseditor = sharedPreferences.edit();

        sharedpreferenceseditor.putString("user",username);
        sharedpreferenceseditor.commit();

        progressDialog.setMessage("authenticating...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),jsonObject.getString("Message"),Toast.LENGTH_SHORT).show();
                            String message = jsonObject.getString("Message").toString();
                         //   Log.i("Message",response);
                            if(message.equals("login Successfull")){
                                Intent i = new Intent(MainActivity.this,mainMenu.class);
                                startActivity(i);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Sorry For Inconvenience",Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
