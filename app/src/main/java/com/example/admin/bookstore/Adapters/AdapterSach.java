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

import com.example.admin.bookstore.Activity.ChiTietSachActivity;
import com.example.admin.bookstore.Entity.DonHang;
import com.example.admin.bookstore.Entity.DonHangSach;
import com.example.admin.bookstore.Entity.HoaDon;
import com.example.admin.bookstore.Entity.Sach;
import com.example.admin.bookstore.Entity.ThongTinKhachHang;
import com.example.admin.bookstore.Fragments.SachNBFragments;
import com.example.admin.bookstore.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
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
    HoaDon hoaDon;
    DatabaseReference mData;

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

        hoaDon = new HoaDon();

        s = this.objects.get(position);
        tg.setText("Tác giả: "+s.getTenTG());
        theloai.setText("Thể loại: "+s.getTheLoai());
        nxb.setText("Nhà xuất bản: "+s.getNhaXB());
        g.setText("Giá : "+s.getGia());
        ten.setText(s.getTenSach());
        Picasso.with(this.context).load(s.getHinh()).into(img);

        // lấy thông tin sách
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
                        mData = FirebaseDatabase.getInstance().getReference();

//                        mData.child("DonHang").child("User").addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                // lấy thông tin hóa đơn
////                                for(DataSnapshot dsp:dataSnapshot.getChildren())
////                                {
////                                    DonHang donHang = dsp.getValue(DonHang.class);
////                                    Toast.makeText(context, donHang.getTinhTrang().toString(), Toast.LENGTH_LONG).show();
////                                }
//
//                                // truy cập đến đối tượng chi tiết hóa đơn con
//                                for(DataSnapshot dsp:dataSnapshot.getChildren()) {
//                                    for (DataSnapshot dsp2 : dsp.getChildren()) {
//                                        for(DataSnapshot dsp3: dsp2.getChildren()) {
//                                            DonHangSach donHangSach = dsp3.getValue(DonHangSach.class);
//                                            Toast.makeText(context, donHangSach.getGia().toString()+dsp3.getKey().toString(), Toast.LENGTH_LONG).show();
//                                        }
//                                    }
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//
//                            }
//                        });


                            // phần thông tin khách hàng
//                        ThongTinKhachHang tt = new ThongTinKhachHang("User",012222222,"aaaawffwa",false);
//                        mData.child("ThongTinKhachHang").child("User").setValue(tt);

                        mData.child("ThongTinKhachHang").child("User").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                final ThongTinKhachHang tt = dataSnapshot.getValue(ThongTinKhachHang.class);
                                if (tt.isActive() == false) {
                                    ThemMoiHoaDon("User",i, sum, s.getTenSach());
                                    tt.setActive(true);
                                    mData.child("ThongTinKhachHang").child("User").setValue(tt);
                                }else{

                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        mData.child("DonHang").child("User").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot dsp: dataSnapshot.getChildren()) {
                                    DonHang dh = dsp.getValue(DonHang.class);
                                    if(dh.getTinhTrang().equals("Đang chờ")) {
                                        // lấy mã hóa đơn
                                        ThemHangDat("User",dsp.getKey().toString(), i, sum, s.getTenSach());
                                    }
                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        dialog.dismiss();
//
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

    public void ThemHangDat(final String tenKH, final String ma, final int sl, final int tong, final String tensach){
        mData = FirebaseDatabase.getInstance().getReference();
        mData.child("DonHang").child(tenKH).child(ma).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DonHangSach donHangSach = new DonHangSach();
                donHangSach.setSoLuong(String.valueOf(sl));
                donHangSach.setGia(String.valueOf(tong));
                donHangSach.setTinhTrangCT("Xác nhận mua");
                mData.child("DonHang").child(tenKH).child(ma).child("HangDat").child(tensach).setValue(donHangSach);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void ThemMoiHoaDon(String tenKH,int sl, int tong, String tenSach){
        mData = FirebaseDatabase.getInstance().getReference();
        String maHD = "HD: "+gethd();
        //lấy ngày tháng năm hiện tại
        Calendar calendar = Calendar.getInstance();
        final String ngay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))+
                "/"+String.valueOf(calendar.get(Calendar.MONTH)+1)+"/"+String.valueOf(calendar.get(Calendar.YEAR));

        // tạo 1 hóa đơn mua hàng
        DonHang donHang = new DonHang();
        donHang.setNgàyDH(ngay);
        donHang.setTenKH("User");
        donHang.setTinhTrang("Đang chờ");
        donHang.setTongGiaTien("Đang tính");

        // lấy thông tin đặt hàng sách
        DonHangSach donHangSach = new DonHangSach();
        donHangSach.setSoLuong(String.valueOf(sl));
        donHangSach.setGia(String.valueOf(tong));
        donHangSach.setTinhTrangCT("Xác nhận mua");

        mData.child("DonHang").child(tenKH).child(maHD).setValue(donHang);
        mData.child("DonHang").child(tenKH).child(maHD).child("HangDat").child(tenSach).setValue(donHangSach);

    }

    public String gethd(){
        //String charsCaps= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String nums = "0123456789";
        //String passSymbols = charsCaps+nums;
        Random rnd = new Random();
        char[] password = new char[6];
        int index = 0;
        for(int i =0;i < 6;i++){
            //password[i]= passSymbols.charAt(rnd.nextInt(passSymbols.length()));
            password[i]= nums.charAt(rnd.nextInt(nums.length()));
        }
        String spw = String.valueOf(password);
        return spw;
    }


}
