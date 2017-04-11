package com.example.admin.bookstore.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.bookstore.Entity.HoaDon;
import com.example.admin.bookstore.Entity.Sach;
import com.example.admin.bookstore.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

import me.grantland.widget.AutofitTextView;

public class ChiTietSachActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    AutofitTextView ten, theloai, tg, nxb, g, tt;
    ImageView img;
    Button btnDH;
    DatabaseReference mData;
    int i = 1;
    int tong,sum =0 ;
    HoaDon hoaDon = new HoaDon();
    ArrayList<Sach> sachArrayList = new ArrayList<>();
    Sach s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_sach);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        addControls();
        addEvent();
    }

    private void addControls(){
        ten = (AutofitTextView) findViewById(R.id.txtTenSachChiTiet);
        theloai = (AutofitTextView) findViewById(R.id.txtTheLoaiChiTiet);
        tg = (AutofitTextView) findViewById(R.id.txtTenTGChiTiet);
        nxb = (AutofitTextView) findViewById(R.id.txtNhaXBChiTiet);
        g = (AutofitTextView) findViewById(R.id.txtGiaSachChiTiet);
        tt = (AutofitTextView) findViewById(R.id.txtTomTat);
        img = (ImageView) findViewById(R.id.imgChiTiet);
        btnDH = (Button) findViewById(R.id.btnDatHangChiTiet);

        mData = FirebaseDatabase.getInstance().getReference();
    }

    private void addEvent(){
        final String tenSach = getIntent().getExtras().getString("SACH").toString();
        mData.child("Sach").child(tenSach).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Sach sach = dataSnapshot.getValue(Sach.class);
                ten.setText(tenSach);
                theloai.setText("Thể loại: "+sach.getTheLoai());
                tg.setText("Tác giả: "+sach.getTenTG());
                nxb.setText("Nhà xuất bản: "+sach.getNhaXB());
                g.setText("Giá: "+sach.getGia());
                tt.setText(sach.getTomTat());
                Picasso.with(getApplicationContext()).load(String.valueOf(sach.getHinh())).into(img);

                sachArrayList.add(new Sach(sach.getHinh(), tenSach, sach.getTheLoai(), sach.getGia(), sach.getTenTG(),sach.getNhaXB(),
                        sach.getTinhTrang(), sach.getTomTat()));

                dathang(sachArrayList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    public  void dathang(ArrayList<Sach> arrayList){
        s = new Sach();
        s = arrayList.get(0);
        btnDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //               Toast.makeText(getApplicationContext(), s.getTenSach().toString(),Toast.LENGTH_SHORT).show();
                final Dialog dialog = new Dialog(ChiTietSachActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_muahang);

                ImageView img = (ImageView)dialog.findViewById(R.id.imghienthi);
                Picasso .with(getApplicationContext())
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
                        hoaDon.setTinhTrang("Đang chờ");

                        mData.child("HoaDon").push().setValue(hoaDon);
                        dialog.dismiss();
                        Toast.makeText(ChiTietSachActivity.this,"Dat hang thanh cong xin cho chung toi lien lac",Toast.LENGTH_LONG).show();

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
                txtvTong.setText("Tổng tiền :"+String.valueOf(s.getGia())+"đồng");
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
                            txtvTong.setText("Tổng tiền :"+String.valueOf(sum)+"đồng");
                        }else {
                            i = 1;
                            sum = tong;
                            txtvSl.setText("1");
                            txtvTong.setText("Tổng tiền :"+String.valueOf(sum)+"đồng");
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
                        txtvTong.setText("Tổng tiền :"+String.valueOf(sum)+"đồng");

                    }
                });
                dialog.show();
            }
        });
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.chi_tiet_sach, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home2) {
            Intent i = new Intent(ChiTietSachActivity.this,MainActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_infoTK2) {
            Toast.makeText(getApplicationContext(),"Thông tin tài khoản",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_shopping2) {
            Intent i = new Intent(ChiTietSachActivity.this,Giohang.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(),"Giỏ hàng",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_info2) {
            Toast.makeText(getApplicationContext(),"Infomation App",Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
