package com.example.admin.bookstore.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.bookstore.Adapters.AdapterHoaDon;
import com.example.admin.bookstore.Entity.HoaDon;
import com.example.admin.bookstore.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Giohang extends AppCompatActivity {

    private ArrayList<HoaDon> hoaDons = new ArrayList<>();
    private ListView listView ;
    private AdapterHoaDon adapterHoaDon;
    private DatabaseReference mData;
    HoaDon hoaDon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);

        mData = FirebaseDatabase.getInstance().getReference();
        listView = (ListView)findViewById(R.id.lsvHoadon);
        adapterHoaDon = new AdapterHoaDon(Giohang.this,R.layout.hoadon_layout,hoaDons);

        String key = mData.child("HoaDon").push().getKey();

        TextView user = (TextView)findViewById(R.id.use);
        user.setText("laytulucdangnhap");

        listView.setAdapter(adapterHoaDon);


        mData.child("HoaDon").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp: dataSnapshot.getChildren()){
                    hoaDon = dsp.getValue(HoaDon.class);
                    hoaDons.add(new HoaDon(hoaDon.getMaHD(),hoaDon.getTenKH(),hoaDon.getTenSach(),hoaDon.getSoLuong(),hoaDon.getTongGiaTien(),hoaDon.getTinhTrang()));
                }
                adapterHoaDon.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Giohang.this,"Chua lam xong ma click cai gi ?~~",Toast.LENGTH_LONG).show();
            }
        });

    }
}
