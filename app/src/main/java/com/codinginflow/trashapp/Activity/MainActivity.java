package com.codinginflow.trashapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.codinginflow.trashapp.Fragment.ChatFragment;
import com.codinginflow.trashapp.Fragment.HomeFragment;
import com.codinginflow.trashapp.Fragment.PesananFragment;
import com.codinginflow.trashapp.Fragment.ProfileFragment;
import com.codinginflow.trashapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationMenu;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationMenu = findViewById(R.id.bn_main_bottomnavigation);
//        toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle("TrashApp");
//        setSupportActionBar(toolbar);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_main_main,new HomeFragment()).commit();

        bottomNavigationMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                switch (menuItem.getItemId()){
                    case R.id.menu_main_home :
                        ft.replace(R.id.fl_main_main,new HomeFragment()).commit();
                        break;
                    case R.id.menu_main_pesanan:
                        ft.replace(R.id.fl_main_main,new PesananFragment()).commit();
                        break;
                    case R.id.menu_main_chat:
                        ft.replace(R.id.fl_main_main,new ChatFragment()).commit();
                        break;
                    case R.id.menu_main_profile:
                        ft.replace(R.id.fl_main_main,new ProfileFragment()).commit();
                        break;
                }
                return false;
            }
        });

    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}

