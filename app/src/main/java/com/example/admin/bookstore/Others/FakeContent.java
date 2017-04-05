package com.example.admin.bookstore.Others;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.admin.bookstore.R;

/**
 * Created by Admin on 4/4/2017.
 */

public class FakeContent implements TabHost.TabContentFactory{

    Context context;

    public FakeContent(Context mContext) {
        context = mContext;
    }

    @Override
    public View createTabContent(String tag) {
        View fakeView = new View(context);
        fakeView.setMinimumHeight(0);
        fakeView.setMinimumWidth(0);
        return fakeView;
    }

}
