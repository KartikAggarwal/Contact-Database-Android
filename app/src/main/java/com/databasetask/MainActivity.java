package com.databasetask;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.databasetask.sharedPrefrences.MySharedPref;

public class MainActivity extends AppCompatActivity {

    MySharedPref mySharedPref;
    SharedPreferences pref;
    Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySharedPref=new MySharedPref(getApplicationContext());
        pref=MySharedPref.getPreference();
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(MySharedPref.checkLogin())
                {
                    in=new Intent(MainActivity.this,Home.class);
                    startActivity(in);
                    finish();
                }
                else
                {
                    in=new Intent(MainActivity.this,Login.class);
                    startActivity(in);
                    finish();
                }
            }
        }, 2000);
    }
}
