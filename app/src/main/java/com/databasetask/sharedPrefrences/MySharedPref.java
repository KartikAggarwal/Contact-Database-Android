package com.databasetask.sharedPrefrences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by appinventiv on 29/8/17.
 */

public class MySharedPref {
    private static SharedPreferences pref;
    private static String user;
    private static Context context;
    private static SharedPreferences.Editor edit;
    private static String USER_ID="user_id";
    private static String PREFERENCE_FILE_NAME="MyDatabaseTask";


    public MySharedPref(Context context)
    {
        this.context=context;
    }

    public static String getUser()
    {
        user= pref.getString("user_id","GUEST");
        return user;
    }

    public static SharedPreferences getPreference()
    {
        if(pref==null)
            pref=context.getSharedPreferences(PREFERENCE_FILE_NAME,Context.MODE_PRIVATE);
        return pref;
    }

    public static void editor(String user,String pass)
    {
        edit=pref.edit();
        edit.putString("user_id",user);
        edit.putString("pass",pass);
        edit.commit();
    }

    public static void clearPreferences()
    {
        edit=pref.edit();
        edit.clear().commit();
    }

    public static boolean checkLogin()
    {
        return (pref.contains(USER_ID))?true:false;
    }
}
