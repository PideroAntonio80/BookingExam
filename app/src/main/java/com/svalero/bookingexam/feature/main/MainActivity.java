package com.svalero.bookingexam.feature.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.svalero.bookingexam.R;
import com.svalero.bookingexam.feature.list_booked.BookActivity;
import com.svalero.bookingexam.feature.list_hotels.view.ListHotelsActivity;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;
    public static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

        showFragment(MainFragment.newInstance());

        initBottomNavigation();
    }

    public void initComponents() {
        bottomNavigation = findViewById(R.id.main_bottom_navigation);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_overflow, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.oiTodosHoteles) {
            Intent navegarTodosHoteles = new Intent(getBaseContext(), ListHotelsActivity.class);
            startActivity(navegarTodosHoteles);
        } else if(id == R.id.oiMasReservados) {
            Intent navegarHotelesMasReservados = new Intent(getBaseContext(), BookActivity.class);
            startActivity(navegarHotelesMasReservados);
        }
        return super.onOptionsItemSelected(item);
    }

    public void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.flmainFragmentContainer, fragment).commit();
    }

    public void initBottomNavigation(){
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.main_menu_nav_home:
                        showFragment(MainFragment.newInstance());
                        break;
                    case R.id.main_menu_nav_trips:
                        showFragment(BookingsFragment.newInstance());
                        break;
                    case R.id.main_menu_nav_login:
                        showFragment(LoginFragment.newInstance());
                        break;
                }
                return false;
            }
        });
    }
}