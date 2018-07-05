package com.example.android.filemanagementsystem;

import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchFileList extends AppCompatActivity {

    private List<FileItems> fileItems;
    private RecyclerView recyclerView;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_file_list);

        recyclerView = findViewById(R.id.recyclerView);

        fileItems = new ArrayList<FileItems>();

        extractfiledata();
    }

    private void extractfiledata(){

        String url = "http://192.168.43.252:8888/searchFile.php";
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("fetching data.... ");
        progressDialog.show();

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor sharedpreferenceseditor = sharedPreferences.edit();

        final  String fileid = sharedPreferences.getString("searchfileid","");
        final  String filename = sharedPreferences.getString("searchfilename","");
        final  String filedepartment = sharedPreferences.getString("searchfiledepartment","");
        url = url + "?fileid=" + fileid + "&filename=" + filename + "&filedepartment=" + filedepartment;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                fileItems.add(new FileItems(
                                        jsonObject.getString("fileid"),
                                        jsonObject.getString("filename"),
                                        jsonObject.getString("fileholder"),
                                        jsonObject.getString("filedepartment")
                                ));
                            }
                            adapter = new Adapter(SearchFileList.this, fileItems);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            recyclerView.setAdapter(adapter);
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
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
