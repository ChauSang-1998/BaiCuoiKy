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

import java.util.ArrayList;

public class QLPhongTro extends AppCompatActivity {

    String DB_NAME = "PhongTro.db";
    String DB_PATH= "/databases/";
    SQLiteDatabase db=null;

    EditText txtMaPHG, txtTenPHG;
    Button btnThemPhongTro;

    ArrayList<String> arrDanhSach;
    ArrayAdapter<String> adapterDanhSach;
    ListView lvPhongTro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlphong_tro);
        addControl();
        showData();
        Delete();
    }

    private void Delete() {
        lvPhongTro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                AlertDialog.Builder b=new AlertDialog.Builder(QLPhongTro.this);
                b.setTitle("Thoát");
                b.setMessage("Bạn có chắc muốn xoá không?");
                b.setPositiveButton("Có", new DialogInterface. OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        db.delete("PHONG","TenPHG=?", new String[]{arrDanhSach.get(i)});
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

    private void showData() {
        db= openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
        Cursor c = db.rawQuery("SELECT * FROM PHONG",null);
        arrDanhSach.clear();
        while (c.moveToNext())
        {
            String TenPHG=c.getString(1);
            arrDanhSach.add(TenPHG);
        }
        c.close();
        adapterDanhSach.notifyDataSetChanged();
    }



    private void addControl() {
        arrDanhSach= new ArrayList<String>();
        adapterDanhSach= new ArrayAdapter<String>(QLPhongTro.this, android.R.layout.simple_list_item_1,arrDanhSach);
        lvPhongTro=(ListView)findViewById(R.id.lvPhongTro);
        lvPhongTro.setAdapter(adapterDanhSach);

        txtMaPHG= (EditText)findViewById(R.id.txtMaPHG);
        txtTenPHG= (EditText)findViewById(R.id.txtTenPHG);
        btnThemPhongTro=(Button)findViewById(R.id.btnThemPhongTro);
    }

    public void ThemPhongTro(View view) {
        ContentValues row = new ContentValues();
        row.put("MaPHG",txtMaPHG.getText().toString());
        row.put("TenPHG",txtTenPHG.getText().toString());
        db.insert("PHONG",null, row);
        showData();
    }
}
