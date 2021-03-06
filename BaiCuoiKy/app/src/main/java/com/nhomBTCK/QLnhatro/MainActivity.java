package com.nhomBTCK.QLnhatro;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    String DB_NAME = "PhongTro.db";
    String DB_PATH= "/databases/";
    SQLiteDatabase db=null;

    EditText txtTenDn, txtMatKhau;
    String TenDNKT="",MatKhauKT="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        LoadDatabase();
    }

    private void addControls() {
        txtTenDn= findViewById(R.id.txtTenDN);
        txtMatKhau= findViewById(R.id.txtMatKhau);
    }

    public void Thoat(View view) {
        AlertDialog.Builder b=new AlertDialog.Builder(MainActivity.this);
        b.setTitle("Thoát");
        b.setMessage("Bạn có chắc muốn thoát không?");
        b.setPositiveButton("Có", new DialogInterface. OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
            }});
        b.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });
        b.create().show();
    }

    private void LoadDatabase() {
        File dbFile = getDatabasePath(DB_NAME);
        if (!dbFile.exists())
        {
            try{
                CopyDataBaseFromAsset();
            }
            catch (Exception e)
            {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void CopyDataBaseFromAsset() {
        try {
            InputStream myInput;
            myInput = getAssets().open(DB_NAME);
            String outFileName = GetPATH();
            File f = new File(getApplicationInfo().dataDir + DB_PATH);
            if (!f.exists())
                f.mkdir();
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0)
            {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private String GetPATH(){
        return getApplicationInfo().dataDir+DB_PATH+DB_NAME;
    }

    public void DangNhap(View view) {
        KTDN();
    }
    private void KTDN() {
        db= openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
        Cursor c = db.rawQuery("SELECT * FROM TAIKHOAN",null);
        while (c.moveToNext())
        {
            TenDNKT=c.getString(1);
            MatKhauKT=c.getString(2);
        }
        c.close();

        String TenDN= txtTenDn.getText().toString();
        String MatKhau=txtMatKhau.getText().toString();

        if(TenDN.equals(TenDNKT) && MatKhau.equals(MatKhauKT))
        {
            Intent i= new Intent(MainActivity.this,Main.class);
            startActivity(i);
        }
        else
        {
            Toast.makeText(MainActivity.this,"Tên đăng nhập hoặc mật khẩu chưa chính xác!",Toast.LENGTH_LONG).show();
        }
    }

    public void DoiMatKhau(View view) {
        Intent i= new Intent(MainActivity.this,DoiMatKhau.class);
        startActivity(i);
    }
}
