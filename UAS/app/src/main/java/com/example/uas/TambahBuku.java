package com.example.uas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
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

public class TambahBuku extends AppCompatActivity {

    Button keluar, simpanData;
    EditText judul, pengarang, deskripsi, tahun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_buku);

        keluar = findViewById(R.id.keluar);
        judul = findViewById(R.id.judul);
        pengarang = findViewById(R.id.pengarang);
        deskripsi = findViewById(R.id.deskripsi);
        tahun = findViewById(R.id.tahun);
        simpanData = findViewById(R.id.simpanData);

        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        simpanData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpan_data();
            }
        });
    }
    void simpan_data(){
        String url = "http://192.168.1.10/review_buku/simpan_buku.php";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status_kirim = jsonObject.getString("status");
                            if(status_kirim.equals("berhasil")) {
                                Toast.makeText(TambahBuku.this, "Buku Berhasil Disimpan!", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(TambahBuku.this, "Buku Gagal Disimpan!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TambahBuku.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> input_form = new HashMap<String, String>();
                input_form.put("judul", judul.getText().toString());
                input_form.put("pengarang", pengarang.getText().toString());
                input_form.put("deskripsi", deskripsi.getText().toString());
                input_form.put("tahun", tahun.getText().toString());
                return input_form;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }
}