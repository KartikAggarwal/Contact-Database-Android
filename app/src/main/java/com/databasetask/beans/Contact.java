package com.databasetask.beans;

import android.database.StaleDataException;

/**
 * Created by appinventiv on 28/8/17.
 */

public class Contact {
    String name;
    String number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}
