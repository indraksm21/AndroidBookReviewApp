package com.example.uas;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uas.databinding.ActivityLihatBukuBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class LihatBuku extends AppCompatActivity {

    private ActivityLihatBukuBinding binding;

    TextView judul, pengarang, deskripsi, tahun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLihatBukuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;

        judul = findViewById(R.id.judul);
        pengarang = findViewById(R.id.pengarang);
        deskripsi = findViewById(R.id.deskripsi);
        tahun = findViewById(R.id.tahun);

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hapus_data();
            }
        });
        tampil_data();
    }

    void tampil_data(){
        String url = "http://192.168.1.10/review_buku/tampil_data.php?id=" +getIntent().getStringExtra("id");
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            judul.setText(jsonObject.getString("judul"));
                            pengarang.setText(jsonObject.getString("pengarang"));
                            deskripsi.setText(jsonObject.getString("deskripsi"));
                            tahun.setText(jsonObject.getString("tahun"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    void hapus_data(){
        String url = "http://192.168.1.10/review_buku/hapus_data.php?id=" +getIntent().getStringExtra("id");
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("status");
                            if(status.equals("Data Terhapus")) {
                                Toast.makeText(LihatBuku.this, "Data Terhapus!", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(LihatBuku.this, "Data Gagal Terhapus!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}