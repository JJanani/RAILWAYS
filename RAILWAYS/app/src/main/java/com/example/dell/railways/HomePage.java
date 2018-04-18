package com.example.dell.railways;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class HomePage extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //set toolbar icon and title
        toolbar.setNavigationIcon(R.drawable.ic_directions_transit_black_24dp);
        toolbar.setSubtitle(getString(R.string.sub_title));
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        displaySelectedScreen(R.id.navigation_home);
    }

    //AlertDialog Box to confirm exiting of application
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(getString(R.string.close_activity))
                .setMessage(getString(R.string.Closing_message))
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }


    //Called when any navigation item is selected.
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        displaySelectedScreen(id);
        return true;
    }

    //On Selecting any navigation item , respective fragments are created.
    private void displaySelectedScreen(int itemID) {

        Fragment fragment = null;
        Fragment fragment1 = null;
        FragmentTransaction ft, ft2;
        switch (itemID){
            //Creating multiple fragments in home page
            case R.id.navigation_home:
                fragment = new HomePageContent();
                fragment1= new TopFragment();
                ft2 = getSupportFragmentManager().beginTransaction();
                ft2.replace(R.id.content,fragment);
                ft2.replace(R.id.layout4, fragment1);
                ft2.commit();
                break;
            //Creating single fragment
            case R.id.navigation_dashboard:
                fragment = new RegisterComplaint();
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content, fragment);
                ft.commit();
                break;

            //Creating single fragment
            case  R.id.navigation_notifications:
                fragment = new ViewComplaints();
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content, fragment);
                ft.commit();
                break;
            }
    }
}
