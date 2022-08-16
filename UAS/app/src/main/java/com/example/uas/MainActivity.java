package com.example.uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton tambahBuku, logout;
    Button btnCari;
    ListView listView;
    ArrayList<Objek> model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tambahBuku = findViewById(R.id.tambahBuku);
        btnCari = findViewById(R.id.btnCari);
        logout = findViewById(R.id.logout);
        listView = findViewById(R.id.listview);

        tambahBuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TambahBuku.class);
                startActivity(intent);
            }
        });

        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cari.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        load();
    }

    void load() {
        String url = "http://192.168.1.10/review_buku/tampil_buku.php";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray getHasil = jsonObject.getJSONArray("hasil");
                            model = new ArrayList();
                            for (int i = 0; i <getHasil.length(); i++) {
                                JSONObject getData = getHasil.getJSONObject(i);
                                String id = getData.getString("id");
                                String judul = getData.getString("judul");
                                String pengarang = getData.getString("pengarang");
                                String deskripsi = getData.getString("deskripsi");
                                String tahun = getData.getString("tahun");
                                model.add(new Objek(id, judul, pengarang, deskripsi, tahun));
                            }
                            Adapter adapter = new Adapter(getApplicationContext(), model);
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int index, long l) {
                                    Intent intent = new Intent(getApplicationContext(),LihatBuku.class);
                                    intent.putExtra("id", model.get(index).getId());
                                    startActivity(intent);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    @Override
    protected void onResume() {
        load();
        super.onResume();
    }
}

