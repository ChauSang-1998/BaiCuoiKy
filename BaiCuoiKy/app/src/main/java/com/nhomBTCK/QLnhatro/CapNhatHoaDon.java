package com.nhomBTCK.QLnhatro;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class CapNhatHoaDon extends AppCompatActivity {

    String DB_NAME = "PhongTro.db";
    String DB_PATH= "/databases/";
    SQLiteDatabase db=null;

    Spinner spPHG;
    ArrayList<String> arrPHG;
    ArrayAdapter<String> adapterPHG;

    Button btnCapNhatHoaDon;
    EditText txtDien, txtNuoc, txtNgayHD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_hoa_don);
        addControls();
        addEvents();
    }

    private void addEvents() {

    }

    private void addControls() {
        btnCapNhatHoaDon=findViewById(R.id.btnCapNhatHoaDon);
        txtDien=findViewById(R.id.txtDien);
        txtNuoc=findViewById(R.id.txtNuoc);
        txtNgayHD=findViewById(R.id.txtNgayHD);

        spPHG= findViewById(R.id.spPHG);
        arrPHG= new ArrayList<>();

        db= openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
        Cursor c = db.rawQuery("SELECT * FROM PHONG",null);
        arrPHG.clear();
        while (c.moveToNext())
        {
            String MaPHG=c.getString(0);
            arrPHG.add(MaPHG);
        }
        c.close();

        adapterPHG= new ArrayAdapter<String>(CapNhatHoaDon.this,android.R.layout.simple_spinner_item, arrPHG);
        adapterPHG.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPHG.setAdapter(adapterPHG);
    }

    public void CapNhatHoaDon(View view) {
        String PHG= spPHG.getSelectedItem (). toString ();
        ContentValues row = new ContentValues();
        row.put("DienTieuThu",Integer.parseInt(txtDien.getText().toString()));
        row.put("NuocTieuThu",Integer.parseInt(txtNuoc.getText().toString()));
        row.put("NgayHD",txtNgayHD.getText().toString());
        row.put("MaPHG",PHG);
        long n=db.insert("HOADON",null, row);
        if(n==0)
        {
            Toast.makeText(CapNhatHoaDon.this,"Cập nhật thất bại!",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(CapNhatHoaDon.this,"Cập nhật thành công!",Toast.LENGTH_LONG).show();
        }
    }
}
