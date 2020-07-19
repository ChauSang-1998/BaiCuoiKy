package com.nhomBTCK.QLnhatro;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class AdapterTinhTien extends ArrayAdapter<SrcTinhTien> {
    Activity context;
    int resource;
    List<SrcTinhTien> objects;
    public AdapterTinhTien(Activity context, int resource, List<SrcTinhTien> objects) {
        super(context, resource, objects);
        this.context= context;
        this.resource= resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inf = this.context.getLayoutInflater();
        View row = inf.inflate(this.resource,null);
        TextView txtTenPHG = row.findViewById(R.id.txtTenPHG);
        TextView txtDien = row.findViewById(R.id.txtDien);
        TextView txtNuoc = row.findViewById(R.id.txtNuoc);
        TextView txtTienTro = row.findViewById(R.id.txtTienTro);
        TextView txtNgayHD = row.findViewById(R.id.txtNgayHD);
        TextView txtTrangThai = row.findViewById(R.id.txtTrangThai);
        TextView txtThanhTien = row.findViewById(R.id.txtThanhTien);
        EditText txtGach= row.findViewById(R.id.txtGach);

        SrcTinhTien db = objects.get(position);
        txtTenPHG.setText("Mã phòng: "+db.getTenPHG());
        txtDien.setText("Điện năng tiêu thụ: "+db.getDien()+" (kWh)");
        txtNuoc.setText("Lượng nước sử dụng: "+db.getNuoc()+" (m3)");
        txtTienTro.setText("Tiền trọ: "+db.getTienTro()+" VNĐ");
        txtNgayHD.setText("Ngày lập hoá đơn: "+db.getNgayHD());
        txtTrangThai.setText("Trạng thái thanh toán: "+db.getTrangThai());
        txtThanhTien.setText("Thành tiền: "+db.getThanhTien()+" VNĐ");
        return row;
    }
}
