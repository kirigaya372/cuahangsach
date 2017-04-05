package com.example.admin.bookstore.Activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.admin.bookstore.Adapters.ViewPagerAdapters;
import com.example.admin.bookstore.Fragments.SachBCFragments;
import com.example.admin.bookstore.Fragments.SachNBFragments;
import com.example.admin.bookstore.Fragments.SachNNFragments;
import com.example.admin.bookstore.Others.FakeContent;
import com.example.admin.bookstore.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener {

    TabHost tabHost;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuTab();
        addControls();
        initTabhost();
    }

    private void addControls(){

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new SachNBFragments());
        fragmentList.add(new SachBCFragments());
        fragmentList.add(new SachNNFragments());

        ViewPagerAdapters pagerAdapters = new ViewPagerAdapters(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(pagerAdapters);
        viewPager.setOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(3);//số lượng page lưu vào bộ nhớ cache để tránh tình trạng load lại

    }

    private void initTabhost(){
        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();

        String[] tabName = {"Sách nổi bật","Sách bán chạy", "Sách ngẫu nhiên"};

        for (int i=0;i<tabName.length;i++){
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabName[i]);
            tabSpec.setIndicator(tabName[i]);
            tabSpec.setContent(new FakeContent(getApplicationContext()));
            tabHost.addTab(tabSpec);

        }

        tabHost.setOnTabChangedListener(this);
    }


    //#Menu
    public void menuTab(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_home) {
            Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_infoTK) {
            Toast.makeText(getApplicationContext(),"Thông tin tài khoản",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_shopping) {
            Toast.makeText(getApplicationContext(),"Giỏ hàng",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_info) {
            Toast.makeText(getApplicationContext(),"Infomation App",Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //#end Menu

    //#Tabhost and ViewPager

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    @Override
    public void onPageSelected(int position) {
        tabHost.setCurrentTab(position);
    }


    //ViewPager listener
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //on tabhost listener
    @Override
    public void onTabChanged(String tabId) {
        int selectedPage = tabHost.getCurrentTab();
        viewPager.setCurrentItem(selectedPage);
    }
}
