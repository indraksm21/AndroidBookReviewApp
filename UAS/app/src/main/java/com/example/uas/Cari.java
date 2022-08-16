package com.example.uas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Cari extends AppCompatActivity {

    EditText form_cari;
    Button batal, reset;
    ListView listView;
    ArrayList<Objek> model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari);

        batal = findViewById(R.id.keluar);
        reset = findViewById(R.id.reset);
        form_cari = findViewById(R.id.form_cari);

        listView = findViewById(R.id.list_cari);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                form_cari.setText("");
            }
        });

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        form_cari.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                cari_data(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    void cari_data(String data){
        String url = "http://192.168.1.10/review_buku/cari.php?q=" +data;
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject;
                            jsonObject = new JSONObject(response);
                            JSONArray getHasil = jsonObject.getJSONArray("hasil");
                            model = new ArrayList<>();
                            for (int index = 0; index < getHasil.length(); index++) {
                                JSONObject getData = getHasil.getJSONObject(index);
                                String id = getData.getString("id");
                                String judul = getData.getString("judul");
                                String tahun = getData.getString("tahun");
                                model.add(new Objek(id, judul, "", "", tahun));
                            }

                            Adapter adapter = new Adapter(getApplicationContext(), model);
                            listView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Cari.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}