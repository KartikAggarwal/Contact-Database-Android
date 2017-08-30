package com.databasetask.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.databasetask.beans.Contact;

import java.util.ArrayList;

/**
 * Created by appinventiv on 26/8/17.
 */

public class DbHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;
    ContentValues contentValues;

    public DbHelper(Context context) {
        super(context, DatabaseConstants.DATABASE_NAME,null, DatabaseConstants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseConstants.LOGIN_TABLE_CREATE);
        db.execSQL(DatabaseConstants.CONTACT_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DatabaseConstants.LOGIN_TABLE_DROP);
        onCreate(db);
    }

    public boolean insert (String name,String user, String pass) {
        db = this.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(DatabaseConstants.NAME,name);
        contentValues.put(DatabaseConstants.USER_NAME, user);
        contentValues.put(DatabaseConstants.PASSWORD, pass);
        long i=db.insert(DatabaseConstants.LOGIN_TABLE, null, contentValues);
        return (i>0)?true:false;
    }

    public boolean check(String user,String pass)
    {
        db=this.getReadableDatabase();
        Cursor cs=db.query(DatabaseConstants.LOGIN_TABLE,null,DatabaseConstants.USER_NAME+"=?", new String[]{user},null,null,null);
        if(cs.getCount()<1)
        {
            cs.close();
            return false;
        }
        cs.moveToFirst();
        String ps=cs.getString(cs.getColumnIndex(DatabaseConstants.PASSWORD));
        return (ps.equals(pass))?true:false;
    }

    public boolean insertContact(String user,String name,String number)
    {
        db=this.getWritableDatabase();
        contentValues=new ContentValues();
        contentValues.put(DatabaseConstants.CONTACT_USER,user);
        contentValues.put(DatabaseConstants.CONTACT_NAME,name);
        contentValues.put(DatabaseConstants.CONTACT_NUMBER, number);
        long i=db.insert(DatabaseConstants.CONTACT_TABLE,null,contentValues);
        return (i>0)?true:false;
    }

    public int getContactCount()
    {
        db=this.getReadableDatabase();
        Cursor cs=db.rawQuery(DatabaseConstants.GET_CONTACTS,null);
        return cs.getCount();
    }

    public ArrayList<Contact> getContacts(String user)
    {
        ArrayList<Contact> allContacts=new ArrayList<>();
        db=this.getReadableDatabase();
        Contact contact;
        Cursor cs=db.query(DatabaseConstants.CONTACT_TABLE,null,DatabaseConstants.CONTACT_USER+"=?",new String[]{user},null,null,null);
        if(cs!=null)
        {
            cs.moveToFirst();
            while (!cs.isAfterLast())
            {
                contact=new Contact();
                contact.setName(cs.getString(1));
                contact.setNumber(cs.getString(2));
                allContacts.add(contact);
                cs.moveToNext();
            }
        }
        return allContacts;
    }

    public String getName(String userName)
    {
        String s="";
        db=getReadableDatabase();
        Cursor cs=db.query(DatabaseConstants.LOGIN_TABLE,null,DatabaseConstants.USER_NAME+"=?",new  String[]{userName},null,null,null);
        if (cs!=null)
        {
            cs.moveToFirst();
            s=cs.getString(0);
        }
        return s;
    }
}
