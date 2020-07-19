package com.nhomBTCK.QLnhatro;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class TinhTien extends AppCompatActivity {

    String DB_NAME = "PhongTro.db";
    String DB_PATH= "/databases/";
    SQLiteDatabase db=null;

    Spinner spPHG;
    ArrayList<String> arrPHG;
    ArrayAdapter<String> adapterPHG;

    Spinner spNgayHD;
    ArrayList<String> arrNgayHD;
    ArrayAdapter<String> adapterNgayHD;

    Button btnTinhTien;
    String MaHD="", MaHD2;

    ListView lvTinhTien;
    ArrayList<SrcTinhTien> arrTinhTien;
    AdapterTinhTien adapterTinhTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinh_tien);
        addControls();
        LoadNgayHD();
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

        adapterNgayHD= new ArrayAdapter<String>(TinhTien.this,android.R.layout.simple_spinner_item, arrNgayHD);
        adapterNgayHD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNgayHD.setAdapter(adapterNgayHD);
    }

    private void addControls() {
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

        adapterPHG= new ArrayAdapter<String>(TinhTien.this,android.R.layout.simple_spinner_item, arrPHG);
        adapterPHG.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPHG.setAdapter(adapterPHG);
    }

    private void showData() {
        lvTinhTien=findViewById(R.id.lvTienTro);
        arrTinhTien= new ArrayList<>();
        String PHG1= spPHG.getSelectedItem (). toString ();
        String NgayHD1= spNgayHD.getSelectedItem (). toString ();

        db= openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
        Cursor c = db.rawQuery("SELECT * FROM HOADON WHERE NgayHD='"+NgayHD1+"'",null);
        arrTinhTien.clear();
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
            arrTinhTien.add(new SrcTinhTien(TenPHG, Integer.valueOf(Dien), Integer.valueOf(Nuoc), tientro, NgayHD, TrangThai, ThanhTien));
        }

        c.close();

        adapterTinhTien= new AdapterTinhTien(TinhTien.this,R.layout.item_tinhtien,arrTinhTien);
        lvTinhTien.setAdapter(adapterTinhTien);
    }

    public void TinhTien(View view) {
        showData();
    }

    public void DaThanhToan(View view) {
        String PHG1= spPHG.getSelectedItem (). toString ();
        String NgayHD1= spNgayHD.getSelectedItem (). toString ();

        ContentValues row = new ContentValues();
        row.put("TrangThai","Đã thanh toán");
        int n=db.update("HOADON",row, "MaPHG=? AND NgayHD=?", new String[]{PHG1, NgayHD1});
        if(n==0)
        {
            Toast.makeText(TinhTien.this,"Cập nhật thất bại!",Toast.LENGTH_LONG).show();
            showData();
        }
        else
        {
            ContentValues row2 = new ContentValues();
            String[] a = new String[2];
            Cursor c = db.rawQuery("SELECT * FROM HOADON WHERE MaPHG='"+PHG1+"' AND NgayHD ='"+NgayHD1+"' AND TrangThai='Đã thanh toán'",null);
            while (c.moveToNext())
            {
                MaHD=c.getString(0);
                a=c.getString(3).split("/");
            }
            c.close();

            KTHD(MaHD);

            if(MaHD.equals(MaHD2))
            {
                Toast.makeText(TinhTien.this,"Cập nhật thành công!",Toast.LENGTH_LONG).show();
                showData();
            }
            else
            {
                row2.put("MaHD",MaHD);
                row2.put("Nam",Integer.valueOf(a[2]));
                db.insert("DOANHTHU",null, row2);
                Toast.makeText(TinhTien.this,"Cập nhật thành công!",Toast.LENGTH_LONG).show();
                showData();
            }
        }
    }

    private String KTHD(String MaHD) {
        Cursor c = db.rawQuery("SELECT * FROM DOANHTHU WHERE MaHD='"+MaHD+"'",null);
        while (c.moveToNext())
        {
            MaHD2=c.getString(1);
        }
        c.close();
        return MaHD2;
    }
}
