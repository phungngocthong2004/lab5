package com.example.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import Fregmen.Fragmnet_test;
import Fregmen.fragment_CaiDat;
import Fregmen.fragment_Danhba;
import Fregmen.fragment_Home;
import Fregmen.fragment_MayTinh;

import Fregmen.fragment_thoithiet;
import Fregmen.fregmnt_tintuc;

public class MainActivity extends AppCompatActivity {
        DrawerLayout mDrawerLayout;
        Toolbar toolbar;
        BottomNavigationView mBottomNavigationView;
        NavigationView mNavigationView;

        fragment_CaiDat fgcaiDat;
        fragment_Danhba fgdanhba;
          fragment_Home fghome;
    fragment_MayTinh fgmattinhl;
    fragment_thoithiet fgthoitiet;
    fregmnt_tintuc fgtintuc;
  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout=findViewById(R.id.myDrawer);
        mBottomNavigationView=findViewById(R.id.bottom_navi);
        mNavigationView=findViewById(R.id.navication);
        toolbar=findViewById(R.id.toobar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");

        fghome=new fragment_Home();
        fgcaiDat=new fragment_CaiDat();
        fgdanhba=new fragment_Danhba();
        fgmattinhl=new fragment_MayTinh();
        fgtintuc=new fregmnt_tintuc();
        fgthoitiet=new fragment_thoithiet();


        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().add(R.id.fream_container,fghome).commit();
        
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if (id==R.id.nav_home){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fream_container,fghome).commit();
                    getSupportActionBar().setTitle("Home");
                } else if (id==R.id.nav_db) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fream_container,fgdanhba).commit();
                    getSupportActionBar().setTitle("DanhBa");
                } else if (id==R.id.nav_mt) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fream_container,fgmattinhl).commit();
                    getSupportActionBar().setTitle("May Tinh");
                } else if (id==R.id.nav_seting) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fream_container,fgcaiDat).commit();
                    getSupportActionBar().setTitle("Setting");
                }else {
                    startActivity(new Intent(MainActivity.this,breakup.class));
                }

        mDrawerLayout.close();
                return true;
            }
        });
        mBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if (id==R.id.nav_thoitiey){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fream_container,fgthoitiet).commit();
                    getSupportActionBar().setTitle("Thoi tiet");
                } else if (id==R.id.nav_TinTuc) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fream_container,fgtintuc).commit();
                    getSupportActionBar().setTitle("Tin tuc");
                }
                mDrawerLayout.close();
                return true;
            }
        });
//        boolean isNavigationBarVisible = !ViewConfiguration.get(MainActivity.this).hasPermanentMenuKey();
//
//// Ẩn hiện bottom navigation khi navigation bar xuất hiện
//
//        if (isNavigationBarVisible) {
//            mBottomNavigationView.setVisibility(View.GONE);
//        } else {
//            mBottomNavigationView.setVisibility(View.VISIBLE);
//        }


    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }
}
// if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//         getWindow().getInsetsController().addWindowInsetsAnimationCallback(new WindowInsetsAnimationCallback() {
//@Override
//public WindowInsets onProgress(WindowInsets insets, List<WindowInsetsAnimation> runningAnimations) {
//        boolean isNavigationBarVisible = insets.isVisible(WindowInsets.Type.navigationBars());
//        if (isNavigationBarVisible) {
//        mBottomNavigationView.setVisibility(View.GONE);
//        } else {
//        mBottomNavigationView.setVisibility(View.VISIBLE);
//        }
//        return insets;
//        }
//        });
//        } else {
//        getWindow().setFlags(
//        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        );
//        }