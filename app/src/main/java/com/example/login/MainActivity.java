package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_siswa;
    Button btn_gr;
    Button btn_nilai;
    Button btn_mapel;
    Button btn_about;
    Button btn_profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_gr = (Button) findViewById(R.id.btn_gr);
        btn_nilai = (Button) findViewById(R.id.btn_nilai);
        btn_siswa = (Button) findViewById(R.id.btn_siswa);
        btn_mapel = (Button) findViewById(R.id.btn_mapel);
        btn_about = (Button) findViewById(R.id.btn_about);
        btn_profil = (Button) findViewById(R.id.btn_profil);

        btn_gr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), guru.class);
                startActivity(i);
            }
        });

        btn_siswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), siswa.class);
                startActivity(i);
            }
        });

        btn_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), profil.class);
                startActivity(i);
            }
        });

        btn_mapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), mapel.class);
                startActivity(i);
            }
        });

        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), about.class);
                startActivity(i);
            }
        });
        btn_nilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), nilai.class);
                startActivity(i);
            }
        });
    }

}
