package com.example.android.filemanagementsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import static android.content.SharedPreferences.*;

public class addfiledetails extends AppCompatActivity {

    private EditText editTextfileid,editTextfilename,editTextfiledepartment;
    private Button buttonaddfile;
    private ProgressDialog progressDialog;
    private String url = "http://192.168.43.252:8888/addFile.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfiledetails);

        buttonaddfile = (Button)findViewById(R.id.buttonaddfile);
        editTextfileid = (EditText)findViewById(R.id.editTextfileid);
        editTextfilename = (EditText)findViewById(R.id.editTextfilename);
        editTextfiledepartment = (EditText)findViewById(R.id.editTextfiledepartment);

        progressDialog = new ProgressDialog(this);

        buttonaddfile.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addnewfile();
                    }
                }
        );
    }

    public void addnewfile(){
        final String fileid = editTextfileid.getText().toString();
        final String filename = editTextfilename.getText().toString();
        final String department = editTextfiledepartment.getText().toString();

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor sharedpreferenceseditor = sharedPreferences.edit();

        final String username = sharedPreferences.getString("user","Not Available");

        sharedpreferenceseditor.putString("addfileid",fileid);
        sharedpreferenceseditor.putString("addfilename",filename);
        sharedpreferenceseditor.putString("addfiledepartment",department);
        sharedpreferenceseditor.commit();

        progressDialog.setMessage("Adding file details...");
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
                            if(message.equals("File Added Successfully")){
                                Intent i = new Intent(addfiledetails.this,addresult.class);
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
                params.put("fileholder", username);
                params.put("fileid",fileid);
                params.put("filename", filename);
                params.put("department", department);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
