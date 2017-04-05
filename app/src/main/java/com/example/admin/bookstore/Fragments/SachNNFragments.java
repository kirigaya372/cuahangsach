package com.example.admin.bookstore.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.admin.bookstore.Adapters.AdapterSach;
import com.example.admin.bookstore.Entity.Sach;
import com.example.admin.bookstore.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Admin on 4/4/2017.
 */

public class SachNNFragments extends Fragment{

    public AdapterSach adapterSach;
    public static ArrayList<Sach> sachArrayList = new ArrayList<>();
    public DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    public SachNNFragments() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v  =inflater.inflate(R.layout.activity_sach_nn,null);

        ListView listView = (ListView) v.findViewById(R.id.lvListNN);
        //mData = FirebaseDatabase.getInstance().getReference();

        adapterSach = new AdapterSach(getActivity(), R.layout.hienthsach, sachArrayList );
        listView.setAdapter(adapterSach);

        mData.child("Sach").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dsp: dataSnapshot.getChildren()) {
                    Sach sach = dsp.getValue(Sach.class);
                    sachArrayList.add(new Sach(sach.getHinh(), dsp.getKey().toString(),sach.getGia(), sach.getNhaXB(),
                            sach.getTenTG(), sach.getTinhTrang(), sach.getTomTat()));
                }
                Collections.shuffle(sachArrayList);
                adapterSach.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return v;
    }
}
