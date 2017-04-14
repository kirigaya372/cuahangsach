package com.example.admin.bookstore.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.bookstore.Activity.Giohang;
import com.example.admin.bookstore.Entity.HoaDon;
import com.example.admin.bookstore.Entity.Sach;
import com.example.admin.bookstore.Fragments.SachNBFragments;
import com.example.admin.bookstore.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.grantland.widget.AutofitTextView;

/**
 * Created by nuruha on 06/04/2017.
 */

public class AdapterHoaDon extends ArrayAdapter<HoaDon> {
    Activity context;
    int resource;
    List<HoaDon> objects;

    int tongGia=0, sum=0, j=0;

    public AdapterHoaDon(@NonNull Activity context, @LayoutRes int resource, @NonNull List<HoaDon> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = this.context.getLayoutInflater();
        View v = inflater.inflate(this.resource,null);
        HoaDon hoaDon = this.objects.get(position);
        AutofitTextView txtmahd = (AutofitTextView)v.findViewById(R.id.txthd);
        AutofitTextView txts = (AutofitTextView)v.findViewById(R.id.txttens);
        final AutofitTextView txtsl = (AutofitTextView)v.findViewById(R.id.txtsl);
        final AutofitTextView txttong = (AutofitTextView)v.findViewById(R.id.txttong);
        AutofitTextView txttrang = (AutofitTextView)v.findViewById(R.id.txttrang);
        ImageView imgView = (ImageView) v.findViewById(R.id.settings_hoadon);

        txtmahd.setText("MaHD :"+hoaDon.getMaHD());
        txts.setText("Tên sách :"+hoaDon.getTenSach());
        txtsl.setText("Số lượng :"+hoaDon.getSoLuong());
        txttong.setText("Tổng tiền :"+hoaDon.getTongGiaTien());
        txttrang.setText("Tình Trạng :"+hoaDon.getTinhTrang());

        // lấy position image khi click vào items trong listview và set tags position của imageview
        imgView.setTag(new Integer(position));
        final ArrayList<HoaDon> arrayHD = new ArrayList<>();
        arrayHD.addAll(Giohang.hoaDons);

        final ArrayList<Sach> arraySach = new ArrayList<>();
        arraySach.addAll(SachNBFragments.sachArrayList);
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
                String vt = v.getTag().toString();
                HoaDon hoa = arrayHD.get(Integer.parseInt(vt));
                //Toast.makeText(getContext(), "Dòng thứ: " + hoa.getTenSach(), Toast.LENGTH_LONG).show();

                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_muahang);

                ImageView img = (ImageView)dialog.findViewById(R.id.imghienthi);
                Button btnSua = (Button)dialog.findViewById(R.id.btn_dat);
                btnSua.setText("Sửa hàng");
                Button btnHuy = (Button)dialog.findViewById(R.id.btn_huy);
                btnHuy.setText("Hủy");
                TextView txtvTen = (TextView)dialog.findViewById(R.id.txtv_ten);
                final TextView txtvTong = (TextView)dialog.findViewById(R.id.txtv_tong);
                ImageView left = (ImageView)dialog.findViewById(R.id.btn_left);
                final TextView txtvSl = (TextView)dialog.findViewById(R.id.txt_number);
                ImageView right = (ImageView)dialog.findViewById(R.id.btn_right);

                txtvTen.setText(hoa.getTenSach());
                txtvTong.setText("Tổng tiền: "+hoa.getTongGiaTien());
                txtvSl.setText(hoa.getSoLuong());

                //load hình
                for(int i=0; i < arraySach.size(); i++){
                    Sach sa = arraySach.get(i);
                    String tenSachHD = hoa.getTenSach();
                    String tensach = sa.getTenSach();
                    if(tensach.equals(tenSachHD)) {
                        Picasso.with(context).load(sa.getHinh()).into(img);
                        tongGia = Integer.parseInt(sa.getGia().toString());
                    }
                }

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                String tienTong = txtvTong.getText().toString();
                String gia = tienTong.substring(11, tienTong.length());
                sum = Integer.parseInt(gia);
                left.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (j >1){
                            j--;
                            sum -= tongGia;
                            txtvSl.setText(""+j);
                            txtvTong.setText("Tổng tiền: :"+String.valueOf(sum)+"đồng");
                        }else {
                            j = 1;
                            sum = tongGia;
                            txtvSl.setText("1");
                            txtvTong.setText("Tổng tiền :"+String.valueOf(sum)+"đồng");
                        }
                    }
                });

                right.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        j++;
                        sum += tongGia;
                        txtvSl.setText(""+j);
                        txtvTong.setText("Tổng tiền :"+String.valueOf(sum)+"đồng");
                    }
                });

                dialog.show();

            }
        });

        return v;
    }

}
