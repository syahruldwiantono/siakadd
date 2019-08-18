package com.example.login;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class buatnilai extends AppCompatActivity {
    protected Cursor cursor;
    SqliteHelper dbHelper;
    Button ton1, ton2;
    EditText text1, text2;
    private Spinner text3;
    int nuts, nuas, ntugas;
    String xnh;
    double hasil;
    EditText xUTS, xUAS, xNA, xNH, xT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buatnilai);

        dbHelper = new SqliteHelper(this);
        text1 = (EditText) findViewById(R.id.editText1);
        text2 = (EditText) findViewById(R.id.editText2);
        text3 = (Spinner) findViewById(R.id.editText3);
        ton1 = (Button) findViewById(R.id.button1);
        ton2 = (Button) findViewById(R.id.button2);

    }

    public void myClickHandler(View view) {
        switch (view.getId()) {
            case R.id.btnproses:
                xUTS = (EditText) findViewById(R.id.editText4);
                xUAS = (EditText) findViewById(R.id.editText5);
                xT = (EditText) findViewById(R.id.editText6);
                xNA = (EditText) findViewById(R.id.editText7);
                xNH = (EditText) findViewById(R.id.editText8);
                nuts = Integer.parseInt(xUTS.getText().toString());
                nuas = Integer.parseInt(xUAS.getText().toString());
                ntugas = Integer.parseInt(xT.getText().toString());
                hasil = 0.35 * nuts + 0.45 * nuas + 0.20 * ntugas;
                xNA.setText(hasil + "");
                if (hasil >= 80 && hasil <= 100) xnh = "A";
                else if (hasil >= 70 && hasil <= 79) xnh = "B";
                else if (hasil >= 60 && hasil <= 69) xnh = "C";
                else if (hasil >= 50 && hasil <= 59) xnh = "D";
                else if (hasil >= 0 && hasil <= 49) xnh = "E";
                xNH.setText(xnh);
                break;
            case R.id.btnhapus:
                xUTS.setText("");
                xUAS.setText("");
                xT.setText("");
                xNA.setText("");
                xNH.setText("");
                break;

        }

        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into NILAI(nis, nama, mapel, uts, uas, tugas, total, grade) values('" +
                        text1.getText().toString() + "','" +
                        text2.getText().toString() + "','" +
                        text3.getSelectedItem().toString() + "','" +
                        xUTS.getText().toString() + "','" +
                        xUAS.getText().toString() + "','" +
                        xT.getText().toString() + "','" +
                        xNA.getText().toString() + "','" +
                        xNH.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                nilai.ma.RefreshList();
                finish();
            }
        });

        ton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}
