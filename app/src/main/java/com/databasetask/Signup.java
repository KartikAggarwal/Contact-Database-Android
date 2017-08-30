package com.databasetask;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.databasetask.Database.DbHelper;
import com.databasetask.sharedPrefrences.MySharedPref;

/**
 * Created by appinventiv on 26/8/17.
 */

public class Signup extends AppCompatActivity {
    DbHelper db;
    EditText name,pass,user;
    String name_value,userName_value,pass_value;
    SharedPreferences pref;
    Intent in;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        db=new DbHelper(getApplicationContext());
        name=(EditText)findViewById(R.id.et_user);
        user=(EditText)findViewById(R.id.et_signup_userName);
        pass=(EditText)findViewById(R.id.et_signup_password);
        //sp=new MySharedPref(getApplicationContext());
        pref=MySharedPref.getPreference();
    }

    public void signup(View v)
    {
        name_value=name.getText().toString();
        userName_value=user.getText().toString();
        pass_value=pass.getText().toString();
        if(name_value.equals(""))
            Toast.makeText(this,"Enter Name",Toast.LENGTH_LONG).show();
        else if(userName_value.equals(""))
            Toast.makeText(this,"Enter User Name",Toast.LENGTH_LONG).show();
        else if(pass_value.equals(""))
            Toast.makeText(this,"Enter Password",Toast.LENGTH_LONG).show();
        else {
                if(db.insert(name_value,userName_value,pass_value))
                {
                    Toast.makeText(this,"Success",Toast.LENGTH_LONG).show();
                    in=new Intent(this,Home.class);
                    startActivity(in);
                    MySharedPref.editor(userName_value,pass_value);
                }
                else
                    Toast.makeText(this,"Not Registered",Toast.LENGTH_LONG).show();
        }

        name.setText("");
        user.setText("");
        pass.setText("");
    }

    public void login(View v)
    {
        in =new Intent(this,Login.class);
        startActivity(in);
    }
}
