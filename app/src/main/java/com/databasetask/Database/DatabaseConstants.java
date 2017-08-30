package com.databasetask.Database;


/**
 * Created by appinventiv on 26/8/17.
 */

public class DatabaseConstants {
    static String DATABASE_NAME="test";
    static String LOGIN_TABLE="login";
    static String NAME="name";
    static String USER_NAME="user";
    static String PASSWORD="password";
    static int DB_VERSION=1;
    static String CONTACT_NAME="c_name";
    static String CONTACT_NUMBER="c_number";
    static String CONTACT_TABLE="contact";
    static String CONTACT_ID="c_id";
    static String CONTACT_USER="c_user";
    static String LOGIN_TABLE_CREATE="CREATE TABLE "+ LOGIN_TABLE +" ("+ NAME + " TEXT," + USER_NAME + " TEXT PRIMARY KEY,"+ PASSWORD + " TEXT )";
    static String LOGIN_TABLE_DROP="DROP TABLE IF EXISTS " + LOGIN_TABLE;
    static String CONTACT_TABLE_CREATE="CREATE TABLE "+ CONTACT_TABLE + " (" +CONTACT_USER + " TEXT," + CONTACT_NAME + " TEXT," + CONTACT_NUMBER +" TEXT )";
    static String GET_CONTACTS="SELECT * FROM " + CONTACT_TABLE;
}
