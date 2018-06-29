package com.example.android.filemanagementsystem;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {

    private String url = "http://192.168.43.252:8888/signup.php";
    private EditText editTextfirstname, editTextpassword, editTextusername, editTextlastname, editTextemail, editTextdepartment;
    private Button buttonsignup;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextfirstname = (EditText) findViewById(R.id.editTextfirstname);
        editTextlastname = (EditText) findViewById(R.id.editTextlastname);
        editTextusername = (EditText) findViewById(R.id.editTextusername);
        editTextpassword = (EditText) findViewById(R.id.editTextpassword);
        editTextemail = (EditText) findViewById(R.id.editTextemail);
        editTextdepartment = (EditText) findViewById(R.id.editTextdepartment);

        buttonsignup = (Button) findViewById(R.id.buttonSignup);
        progressDialog = new ProgressDialog(this);

        buttonsignup.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        registerUser();

                    }
                }
        );
    }

    public void registerUser() {
        final String firstname = editTextfirstname.getText().toString().trim();
        final String lastname = editTextlastname.getText().toString().trim();
        final String username = editTextusername.getText().toString().trim();
        final String password = editTextpassword.getText().toString().trim();
        final String email = editTextemail.getText().toString().trim();
        final String department = editTextdepartment.getText().toString().trim();

        progressDialog.setMessage("registration in process....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();


                        try {
                           // System.out.println("response " + response);
                           // Log.i("response : " ,response);
                            JSONObject jsonObject = new JSONObject(response);


                            Toast.makeText(getApplicationContext(),jsonObject.getString("Message"), Toast.LENGTH_SHORT).show();
                            String message = jsonObject.getString("Message").toString();
                        //    Log.i("Message",message);
                            if(message.equals("registered successfully")){finish();}
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                      //

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Sorry For Inconvenience", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("firstname", firstname);
                params.put("lastname", lastname);
                params.put("password", password);
                params.put("email", email);
                params.put("department", department);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
