package com.databasetask;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.databasetask.Database.DbHelper;
import com.databasetask.adapters.ContactPagerAdapter;
import com.databasetask.adapters.ContactRecyclerAdapter;
import com.databasetask.sharedPrefrences.MySharedPref;

/**
 * Created by appinventiv on 26/8/17.
 */

public class Home extends AppCompatActivity {
    TextView username;
    Button logout;
    ViewPager pager;
    TabLayout tabs;
    ContactPagerAdapter pagerAdapter;
    DbHelper db;
    static RecyclerView recyclerView;
    static ContactRecyclerAdapter adapter;
    String user;
    static SharedPreferences pref;
    Intent in;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        logout=(Button)findViewById(R.id.b_logout);
        username=(TextView)findViewById(R.id.tv_user_logged_in);
        pager=(ViewPager)findViewById(R.id.vp_slide);
        tabs=(TabLayout)findViewById(R.id.tl_tabs);

        db=new DbHelper(getApplicationContext());

        user=MySharedPref.getUser();
        pref=MySharedPref.getPreference();

        username.setText(db.getName(user));

        pagerAdapter=new ContactPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if(position==1)
                {
                    adapter=new ContactRecyclerAdapter(getApplicationContext(),db.getContacts(user));
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        tabs.setupWithViewPager(pager);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MySharedPref.clearPreferences();
                in=new Intent(getApplicationContext(),Login.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);
                finish();
            }
        });
    }

    public void getRecyclerView(RecyclerView recyclerView)
    {
        this.recyclerView=recyclerView;
    }

    @Override
    public void onBackPressed() {
        if(MySharedPref.checkLogin())
            Toast.makeText(getApplicationContext(),"You Cannot Go Back...First Logout",Toast.LENGTH_SHORT).show();
        else
            super.onBackPressed();
    }
}
