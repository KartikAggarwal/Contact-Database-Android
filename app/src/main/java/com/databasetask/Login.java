package com.databasetask;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.databasetask.Database.DbHelper;
import com.databasetask.sharedPrefrences.MySharedPref;

/**
 * Created by appinventiv on 26/8/17.
 */

public class Login extends AppCompatActivity {
    EditText user,pass;
    String user_val,pass_val;
    DbHelper db;
    TextView register;
    Intent in;
    /*SharedPreferences pref;
    MySharedPref sp;*/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        user=(EditText)findViewById(R.id.et_login);
        pass=(EditText)findViewById(R.id.et_password);
        db=new DbHelper(getApplicationContext());
        register=(TextView)findViewById(R.id.tv_register);

        /*sp=new MySharedPref(getApplicationContext());

        pref=MySharedPref.getPreference();
        if(pref.contains("user_id"))
        {
            Intent in=new Intent(this,Home.class);
            startActivity(in);
        }*/

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                in=new Intent(getApplicationContext(),Signup.class);
                startActivity(in);
            }
        });
    }

    public void authenticate(View v)
    {
        user_val=user.getText().toString();
        pass_val=pass.getText().toString();

        if(db.check(user_val,pass_val))
        {
            in=new Intent(this,Home.class);
            startActivity(in);
            MySharedPref.editor(user_val,pass_val);
        }
        else
            Toast.makeText(getApplicationContext(),"User Name or Password is Incorrect",Toast.LENGTH_LONG).show();
    }
}
