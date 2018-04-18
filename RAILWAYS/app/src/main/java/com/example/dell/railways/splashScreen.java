package com.example.dell.railways;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/* Splash screen , first screen to be displayed for 3 seconds when application runs. */

public class splashScreen  extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Thread myThread = new Thread(){
            public void run()

            {
                try {
                    sleep(3000);
                    //After displaying for 3 seconds, starts MainActivity java class
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {


                }
            }


        };

        myThread.start();
    }
}
