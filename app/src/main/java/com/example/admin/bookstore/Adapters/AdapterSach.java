package com.example.admin.bookstore.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
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
import android.widget.Toast;

import com.example.admin.bookstore.Activity.ChiTietSachActivity;
import com.example.admin.bookstore.Entity.DonHang;
import com.example.admin.bookstore.Entity.DonHangSach;
import com.example.admin.bookstore.Entity.HoaDon;
import com.example.admin.bookstore.Entity.Sach;
import com.example.admin.bookstore.Fragments.SachNBFragments;
import com.example.admin.bookstore.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.grantland.widget.AutofitTextView;

/**
 * Created by Admin on 3/4/2017.
 */

public class AdapterSach extends ArrayAdapter<Sach>{

    Activity context;
    int resource;
    List<Sach> objects;
    int i = 1;
    int tong,sum =0 ;
    Sach s = new Sach();
    DonHang donHang = new DonHang();
    HoaDon hoaDon = new HoaDon();
    DonHangSach donHangSach = new DonHangSach();
    public AdapterSach(@NonNull Activity context, @LayoutRes int resource, @NonNull List<Sach> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        final View v = inflater.inflate(this.resource,null);

        final AutofitTextView ten = (AutofitTextView) v.findViewById(R.id.txtTenSach);
        AutofitTextView theloai = (AutofitTextView) v.findViewById(R.id.txtTheLoai);
        AutofitTextView tg = (AutofitTextView) v.findViewById(R.id.txtTenTG);
        AutofitTextView nxb = (AutofitTextView) v.findViewById(R.id.txtNhaXB);
        AutofitTextView g = (AutofitTextView) v.findViewById(R.id.txtGiaSach);
        ImageView img = (ImageView) v.findViewById(R.id.imageView2);
        Button btnCT = (Button) v.findViewById(R.id.btnChiTiet);
        Button btnDH = (Button) v.findViewById(R.id.btnDatHang);

        s = this.objects.get(position);
        tg.setText("Tác giả: "+s.getTenTG());
        theloai.setText("Thể loại: "+s.getTheLoai());
        nxb.setText("Nhà xuất bản: "+s.getNhaXB());
        g.setText("Giá : "+s.getGia());
        ten.setText(s.getTenSach());
        Picasso.with(this.context).load(s.getHinh()).into(img);

        final ArrayList<Sach> arrayList = new ArrayList<>();
        arrayList.addAll(SachNBFragments.sachArrayList);
        btnCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = new Sach();
                s = arrayList.get(position);
                Intent i = new Intent(context, ChiTietSachActivity.class);
                i.putExtra("SACH",String.valueOf(s.getTenSach()));
                context.startActivity(i);
            }
        });
        btnDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = arrayList.get(position);
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_muahang);

                ImageView img = (ImageView)dialog.findViewById(R.id.imghienthi);
                Picasso .with(context)
                        .load(s.getHinh())
                        .into(img);

                Button btnDat = (Button)dialog.findViewById(R.id.btn_dat);
                btnDat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseReference mData;
                        mData = FirebaseDatabase.getInstance().getReference();

                        hoaDon.setMaHD(gethd());
                        hoaDon.setTenKH("laytulucdangnhap");
                        hoaDon.setTenSach(String.valueOf(s.getTenSach()));
                        hoaDon.setSoLuong(String.valueOf(i));
                        hoaDon.setTongGiaTien(String.valueOf(sum));
                        hoaDon.setTinhTrang("dang cho");

                        mData.child("HoaDon").push().setValue(hoaDon);
                        dialog.dismiss();
                        Toast.makeText(context,"Dat hang thanh cong xin cho chung toi lien lac",Toast.LENGTH_LONG).show();


//                        String key = mData.child("DonHang").push().getKey();
//                        String namebook = String.valueOf(s.getTenSach());
//
//                        donHangSach.setGia(String.valueOf(String.valueOf(s.getGia())));
//                        donHangSach.setGia(String.valueOf(String.valueOf(sum)));
//                        donHang.setTenKH("user");
//                        donHang.setTinhTrang("dang cho");
//                        donHang.setTongGiaTien(String.valueOf(sum));
//                        mData.child("DonHang").push().setValue(donHang);
//                        dialog.dismiss();
                        //Toast.makeText(context,key+"Dat hang thanh cong xin cho chung toi lien lac",Toast.LENGTH_LONG).show();
                    }
                });
                Button btnHuy = (Button)dialog.findViewById(R.id.btn_huy);
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                TextView txtvTen = (TextView)dialog.findViewById(R.id.txtv_ten);
                txtvTen.setText(String.valueOf(s.getTenSach()));
                final TextView txtvTong = (TextView)dialog.findViewById(R.id.txtv_tong);
                txtvTong.setText("Tong tien :"+String.valueOf(s.getGia())+"dong");
                tong = Integer.parseInt(s.getGia());
                sum = tong;
                final TextView txtvSl = (TextView)dialog.findViewById(R.id.txt_number);
                ImageView left = (ImageView)dialog.findViewById(R.id.btn_left);
                left.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (i >1){
                            i--;
                            sum -= tong;
                            txtvSl.setText(""+i);
                            txtvTong.setText("Tong tien :"+String.valueOf(sum)+"dong");
                        }else {
                            i = 1;
                            sum = tong;
                            txtvSl.setText("1");
                            txtvTong.setText("Tong tien :"+String.valueOf(sum)+"dong");
                        }
                    }
                });
                ImageView right = (ImageView)dialog.findViewById(R.id.btn_right);
                right.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        i++;
                        sum += tong;
                        txtvSl.setText(""+i);
                        txtvTong.setText("Tong tien :"+String.valueOf(sum)+"dong");

                    }
                });
                dialog.show();


            }
        });
        return v;
    }

    public String gethd(){
        String charsCaps= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String nums = "0123456789";
        String passSymbols = charsCaps+nums;
        Random rnd = new Random();
        char[] password = new char[6];
        int index = 0;
        for(int i =0;i < 6;i++){
            password[i]= passSymbols.charAt(rnd.nextInt(passSymbols.length()));
        }
        String spw = String.valueOf(password);
        return spw;
    }
}
