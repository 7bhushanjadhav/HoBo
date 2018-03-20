package com.example.maulikjagtap.googlemap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Maulik Jagtap on 3/20/2018.
 */

public class SlpashScreen extends Activity {

        static  final  int time=3000;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_slpash_screen);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    startActivity(new Intent(getBaseContext(),Login.class));
                    finish();

                }
            },time);
        }

    }


