package com.databasetask.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.databasetask.fragments.AddContactFragment;
import com.databasetask.fragments.ContactListFragment;

/**
 * Created by appinventiv on 28/8/17.
 */

public class ContactPagerAdapter extends FragmentPagerAdapter {
    int tabs=2;

    public ContactPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                AddContactFragment addContactFragment=new AddContactFragment();
                return addContactFragment;
            case 1:
                ContactListFragment contactListFragment=new ContactListFragment();
                return contactListFragment;
        }
        return new AddContactFragment();
    }

    @Override
    public int getCount() {
        return tabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "ADD";
            case 1:
                return "CONTACTS";
        }
        return "ADD";
    }
}
