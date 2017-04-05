package com.example.admin.bookstore.Adapters;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.bookstore.Entity.Sach;
import com.example.admin.bookstore.Fragments.SachNBFragments;
import com.example.admin.bookstore.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.grantland.widget.AutofitTextView;

/**
 * Created by Admin on 3/4/2017.
 */

public class AdapterSach extends ArrayAdapter<Sach>{

    Activity context;
    int resource;
    List<Sach> objects;
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
        AutofitTextView tg = (AutofitTextView) v.findViewById(R.id.txtTenTG);
        AutofitTextView nxb = (AutofitTextView) v.findViewById(R.id.txtNhaXB);
        AutofitTextView g = (AutofitTextView) v.findViewById(R.id.txtGiaSach);
        ImageView img = (ImageView) v.findViewById(R.id.imageView2);
        Button btnCT = (Button) v.findViewById(R.id.btnChiTiet);
        Button btnDH = (Button) v.findViewById(R.id.btnDatHang);

        Sach s = this.objects.get(position);
        tg.setText("Tác giả: "+s.getTenTG());
        nxb.setText("Nhà xuất bản: "+s.getNhaXB());
        g.setText("Giá : "+s.getGia());
        ten.setText(s.getTenSach());
        Picasso.with(this.context).load(s.getHinh()).into(img);

        final ArrayList<Sach> arrayList = new ArrayList<>();
        arrayList.addAll(SachNBFragments.sachArrayList);
        btnCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sach s = new Sach();
                s = arrayList.get(position);
                Toast.makeText(context, String.valueOf(s.getTenSach()), Toast.LENGTH_LONG).show();
            }
        });
        return v;
    }
}
