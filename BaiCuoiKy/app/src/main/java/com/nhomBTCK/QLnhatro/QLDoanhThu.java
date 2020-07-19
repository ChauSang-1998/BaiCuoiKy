package com.nhomBTCK.QLnhatro;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class QLDoanhThu extends AppCompatActivity {

    String DB_NAME = "PhongTro.db";
    String DB_PATH= "/databases/";
    SQLiteDatabase db=null;

    Spinner spNgayHD;
    ArrayList<String> arrNgayHD;
    ArrayAdapter<String> adapterNgayHD;

    Spinner spNam;
    ArrayList<String> arrNam;
    ArrayAdapter<String> adapterNam;

    TextView txtTongThang, txtTongNam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qldoanh_thu);
        addControls();
        LoadNgayHD();
        LoadNam();
    }

    private void addControls() {
        txtTongThang=findViewById(R.id.txtTongThang);
        txtTongNam=findViewById(R.id.txtTongNam);
    }

    private void LoadNgayHD() {
        spNgayHD= findViewById(R.id.spNgayHD);
        arrNgayHD= new ArrayList<>();

        db= openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
        Cursor c = db.rawQuery("SELECT NgayHD FROM HOADON GROUP BY NgayHD ORDER BY MaHD DESC",null);
        arrNgayHD.clear();
        while (c.moveToNext())
        {
            String NgayHD=c.getString(0);
            arrNgayHD.add(NgayHD);
        }
        c.close();

        adapterNgayHD= new ArrayAdapter<String>(QLDoanhThu.this,android.R.layout.simple_spinner_item, arrNgayHD);
        adapterNgayHD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNgayHD.setAdapter(adapterNgayHD);
    }

    private void LoadNam() {
        spNam= findViewById(R.id.spNam);
        arrNam= new ArrayList<>();

        db= openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
        Cursor c = db.rawQuery("SELECT Nam FROM DOANHTHU GROUP BY Nam ORDER BY MaDT DESC",null);
        arrNam.clear();
        while (c.moveToNext())
        {
            String Nam=c.getString(0);
            arrNam.add(Nam);
        }
        c.close();

        adapterNam= new ArrayAdapter<String>(QLDoanhThu.this,android.R.layout.simple_spinner_item, arrNam);
        adapterNam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNam.setAdapter(adapterNam);
    }

    private void showDTThang() {
        String NgayHD1= spNgayHD.getSelectedItem (). toString ();
        String Nam= spNam.getSelectedItem (). toString ();
        int TongThang = 0;

        db= openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
        Cursor c = db.rawQuery("SELECT * FROM HOADON WHERE NgayHD='"+NgayHD1+"' AND TrangThai='Đã thanh toán'",null);

        while (c.moveToNext()) {
            String Dien = c.getString(1);
            String Nuoc = c.getString(2);
            String NgayHD = c.getString(3);
            String TenPHG = c.getString(4);
            String TrangThai = c.getString(5);

            int x = Integer.valueOf(Dien);
            int n = 0;
            int tiendien = 0;
            for (int i = 0; i <= 50; i++)
            {
                if (n == x) {
                    tiendien=x*1549;
                }
                n++;
            }
            for (int i = 51; i <= 100; i++)
            {
                n = i;
                if (n == x) {
                    tiendien=x*1600;
                }
                n++;
            }
            for (int i = 101; i <= 200; i++)
            {
                n = i;
                if (n == x) {
                    tiendien=x*1858;
                }
                n++;
            }
            for (int i = 201; i <= 300; i++)
            {
                n = i;
                if (n == x) {
                    tiendien=x*2340;
                }
                n++;
            }
            for (int i = 301; i <= 400; i++)
            {
                n = i;
                if (n == x) {
                    tiendien=x*2615;
                }
                n++;
            }

            int tiennuoc= Integer.valueOf(Nuoc)*2000;
            int tientro=800000;
            int ThanhTien=tiendien+tiennuoc+tientro;
            TongThang += ThanhTien;
        }

        c.close();
        txtTongThang.setText(TongThang+" VNĐ");
    }

    private void showDTNam() {
        String Nam= spNam.getSelectedItem (). toString ();
        int TongNam = 0;

        db= openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
        Cursor c = db.rawQuery("SELECT * FROM HOADON HD, DOANHTHU DT WHERE HD.MaHD=DT.MaHD AND Nam='"+Nam+"' AND HD.TrangThai='Đã thanh toán'",null);

        while (c.moveToNext()) {
            String Dien = c.getString(1);
            String Nuoc = c.getString(2);
            String NgayHD = c.getString(3);
            String TenPHG = c.getString(4);
            String TrangThai = c.getString(5);

            int x = Integer.valueOf(Dien);
            int n = 0;
            int tiendien = 0;
            for (int i = 0; i <= 50; i++)
            {
                if (n == x) {
                    tiendien=x*1549;
                }
                n++;
            }
            for (int i = 51; i <= 100; i++)
            {
                n = i;
                if (n == x) {
                    tiendien=x*1600;
                }
                n++;
            }
            for (int i = 101; i <= 200; i++)
            {
                n = i;
                if (n == x) {
                    tiendien=x*1858;
                }
                n++;
            }
            for (int i = 201; i <= 300; i++)
            {
                n = i;
                if (n == x) {
                    tiendien=x*2340;
                }
                n++;
            }
            for (int i = 301; i <= 400; i++)
            {
                n = i;
                if (n == x) {
                    tiendien=x*2615;
                }
                n++;
            }

            int tiennuoc= Integer.valueOf(Nuoc)*2000;
            int tientro=800000;
            int ThanhTien=tiendien+tiennuoc+tientro;
            TongNam += ThanhTien;
        }

        c.close();
        txtTongNam.setText(TongNam+" VNĐ");
    }

    public void DoanhThuThang(View view) {
        showDTThang();
    }


    public void DoanhThuNam(View view) {
        showDTNam();
    }
}
