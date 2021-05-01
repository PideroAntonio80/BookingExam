package com.svalero.bookingexam.feature.list_hotels.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.svalero.bookingexam.R;
import com.svalero.bookingexam.data.Hotel;
import com.svalero.bookingexam.feature.MainActivity;
import com.svalero.bookingexam.feature.list_hotels.ListHotelsContract;
import com.svalero.bookingexam.feature.list_hotels.ListHotelsPresenter;

import java.util.ArrayList;

public class ListHotelsActivity extends AppCompatActivity implements ListHotelsContract.View {

    private ListHotelsPresenter listHotelsPresenter;
    private FrameLayout fragmentContainer;
    private BottomNavigationView bottomNavigation;

    private ProgressBar loading;
    private LinearLayout layoutError;
    private Button retry;

    private ArrayList<Hotel> hotels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hotels);

        initComponents();

        initBottomNavigation();

        listHotelsPresenter = new ListHotelsPresenter(this);
        listHotelsPresenter.getHotels();

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setVisibility(View.VISIBLE);
                fragmentContainer.setVisibility(View.GONE);
                bottomNavigation.setVisibility(View.GONE);
                layoutError.setVisibility(View.GONE);
                listHotelsPresenter.getHotels();
            }
        });

    }

    public void initComponents() {
        fragmentContainer = findViewById(R.id.fragmentContainer);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        loading = findViewById(R.id.pbLoading);
        layoutError = findViewById(R.id.llLayoutError);
        retry = findViewById(R.id.bRetry);
    }

    public void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
        if(fragment instanceof ListHotelsFragment) {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        } else {
            showFragment(ListHotelsFragment.newInstance(hotels));
        }
    }

    public void initBottomNavigation(){
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_nav_hotels:
                        showFragment(CategoryHotelsFragment.newInstance(new ArrayList<Hotel>(hotels)));
                        break;
                    case R.id.menu_nav_precio_asc:
                        showFragment(PrizeAscHotelsFragment.newInstance(new ArrayList<Hotel>(hotels)));
                        break;
                    case R.id.menu_nav_precio_desc:
                        showFragment(PrizeDescHotelsFragment.newInstance(new ArrayList<Hotel>(hotels)));
                        break;
                    case R.id.menu_nav_favoritos:
                        showFragment(FavouriteHotelsFragment.newInstance(new ArrayList<Hotel>(hotels)));
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void success(ArrayList<Hotel> hotels) {
        fragmentContainer.setVisibility(View.VISIBLE);
        bottomNavigation.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
        layoutError.setVisibility(View.GONE);

        this.hotels = hotels;

        showFragment(ListHotelsFragment.newInstance(hotels));
    }

    @Override

    public void error(String message) {
        layoutError.setVisibility(View.VISIBLE);
        fragmentContainer.setVisibility(View.GONE);
        bottomNavigation.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
    }
}