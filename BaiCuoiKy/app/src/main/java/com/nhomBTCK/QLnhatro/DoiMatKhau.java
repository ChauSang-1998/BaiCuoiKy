package com.nhomBTCK.QLnhatro;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DoiMatKhau extends AppCompatActivity {
    String DB_NAME = "PhongTro.db";
    String DB_PATH= "/databases/";
    SQLiteDatabase db=null;

    EditText txtTenDN2, txtMatKhauCu, txtMatKhauMoi, txtNhapLaiMK;
    String TenDNKT="",MatKhauKT="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        addControls();
    }

    private void addControls() {
        txtTenDN2=findViewById(R.id.txtTenDN2);
        txtMatKhauCu=findViewById(R.id.txtMatKhauCu);
        txtMatKhauMoi=findViewById(R.id.txtMatKhauMoi);
        txtNhapLaiMK=findViewById(R.id.txtMatKhauMoiLai);
    }

    public void DoiMatKhau2(View view) {
        db= openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
        Cursor c = db.rawQuery("SELECT * FROM TAIKHOAN",null);
        while (c.moveToNext())
        {
            TenDNKT=c.getString(1);
            MatKhauKT=c.getString(2);
        }
        c.close();

        String TenDN= txtTenDN2.getText().toString();
        String MatKhau=txtMatKhauCu.getText().toString();
        String MKMoi=txtMatKhauMoi.getText().toString();
        String NMKMoi=txtNhapLaiMK.getText().toString();

        if(TenDN.equals(TenDNKT) && MatKhau.equals(MatKhauKT))
        {
            if(MKMoi.equals(NMKMoi))
            {
                ContentValues row = new ContentValues();
                row.put("MatKhau",MKMoi);
                int n=db.update("TAIKHOAN",row, "TenDN=? AND MatKhau=?", new String[]{TenDN, MatKhau});
                if(n==0)
                {
                    Toast.makeText(DoiMatKhau.this,"Đổi mật khẩu thất bại!",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(DoiMatKhau.this,"Đổi mật khẩu thành công!",Toast.LENGTH_LONG).show();
                    Intent i= new Intent(DoiMatKhau.this,MainActivity.class);
                    startActivity(i);
                }
            }
            else
            {
                Toast.makeText(DoiMatKhau.this,"Mật khẩu mới nhập lại không khớp!",Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(DoiMatKhau.this,"Mật khẩu cũ không đúng!",Toast.LENGTH_LONG).show();
        }
    }

    public void QuayLai(View view) {
        Intent i= new Intent(DoiMatKhau.this,MainActivity.class);
        startActivity(i);
    }
}
