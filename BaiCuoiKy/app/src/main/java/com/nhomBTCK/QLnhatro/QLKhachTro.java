package com.nhomBTCK.QLnhatro;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;

public class QLKhachTro extends AppCompatActivity {

    String DB_NAME = "PhongTro.db";
    String DB_PATH= "/databases/";
    SQLiteDatabase db=null;

    Spinner spPHG, spPHG2;
    ArrayList<String> arrPHG;
    ArrayAdapter<String> adapterPHG;

    ArrayList<String> arrDSKhachTro;
    ArrayAdapter<String> adapterDSKhachTro;
    ListView lvKhachTro;

    Button btnThemKhachTro;
    EditText txtHoTen, txtSDT, txtCMND;
    RadioButton chkNam, chkNu;
    String PHG1,PHG2;
    RadioGroup rdGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlkhach_tro);
        addControls();
        addEvents();
        DeleteData();
    }

    private void DeleteData() {
        lvKhachTro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                AlertDialog.Builder b=new AlertDialog.Builder(QLKhachTro.this);
                b.setTitle("Thoát");
                b.setMessage("Bạn có chắc muốn xoá không?");
                b.setPositiveButton("Có", new DialogInterface. OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        db.delete("KHACHTRO","TenKH=?", new String[]{arrDSKhachTro.get(i)});
                        showData();
                    }});
                b.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });
                b.create().show();
            }
        });
    }

    private void addEvents() {

    }

    private void addControls() {
        btnThemKhachTro=findViewById(R.id.btnThemKhachTro);
        txtHoTen=findViewById(R.id.txtTenKH);
        txtSDT=findViewById(R.id.txtSDT);
        txtCMND=findViewById(R.id.txtCMND);
        chkNam=findViewById(R.id.rbNam);
        chkNu=findViewById(R.id.rbNu);

        arrDSKhachTro= new ArrayList<>();
        adapterDSKhachTro=new ArrayAdapter<String>(QLKhachTro.this,android.R.layout.simple_list_item_1,arrDSKhachTro);
        lvKhachTro=findViewById(R.id.lvKhachTro);
        lvKhachTro.setAdapter(adapterDSKhachTro);

        spPHG= findViewById(R.id.spPHG);
        spPHG2= findViewById(R.id.spPHG2);
        arrPHG= new ArrayList<>();

        db= openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
        Cursor c = db.rawQuery("SELECT * FROM PHONG",null);
        //truy van de lay tat ca cac phong
        arrPHG.clear();
        while (c.moveToNext())
        {
            String MaPHG=c.getString(0);//
            arrPHG.add(MaPHG);
        }
        //duyet danh sach ket qua truy van
        c.close();

        adapterPHG= new ArrayAdapter<String>(QLKhachTro.this,android.R.layout.simple_spinner_item, arrPHG);
        adapterPHG.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPHG.setAdapter(adapterPHG);
        spPHG2.setAdapter(adapterPHG);
    }

    public void ThemKhachTro(View view) {
        PHG1= spPHG.getSelectedItem (). toString ();
        PHG2= spPHG2.getSelectedItem (). toString ();

        ContentValues row = new ContentValues();
        if(chkNam.isChecked())
        {
            row.put("TenKH",txtHoTen.getText().toString());
            row.put("CMND",txtCMND.getText().toString());
            row.put("SDT",txtSDT.getText().toString());
            row.put("GioiTinh",chkNam.getText().toString());
            row.put("MaPHG",PHG1);
            db.insert("KHACHTRO",null, row);
        }
        else if(chkNu.isChecked())
        {
            row.put("TenKH",txtHoTen.getText().toString());
            row.put("CMND",txtCMND.getText().toString());
            row.put("SDT",txtSDT.getText().toString());
            row.put("GioiTinh",chkNu.getText().toString());
            row.put("MaPHG",PHG1);
            db.insert("KHACHTRO",null, row);
        }
        showData();
    }


    private void showData() {
        db= openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
        Cursor c = db.rawQuery("SELECT * FROM KHACHTRO",null);
        arrDSKhachTro.clear();
        while (c.moveToNext())
        {
            String TenKH=c.getString(1);
            arrDSKhachTro.add(TenKH);
        }
        c.close();
        adapterDSKhachTro.notifyDataSetChanged();
    }

    public void TraCuuPHG(View view) {
        PHG1= spPHG.getSelectedItem (). toString (); //lay ds cac ptro de tra cuu,
        PHG2= spPHG2.getSelectedItem (). toString ();

        db= openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
        Cursor c = db.rawQuery("SELECT * FROM KHACHTRO WHERE MaPHG='"+PHG2+"'",null);
        arrDSKhachTro.clear();
        while (c.moveToNext())
        {
            String TenKH=c.getString(1);
            arrDSKhachTro.add(TenKH);
        }
        c.close();
        adapterDSKhachTro.notifyDataSetChanged();
    }
}
