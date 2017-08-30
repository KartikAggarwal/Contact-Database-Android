package com.databasetask.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.databasetask.Database.DbHelper;
import com.databasetask.Home;
import com.databasetask.R;
import com.databasetask.adapters.ContactRecyclerAdapter;
import com.databasetask.sharedPrefrences.MySharedPref;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactListFragment extends Fragment {
    static RecyclerView recyclerView;
    static ContactRecyclerAdapter adapter;
    static DbHelper db;
    MySharedPref pref;
    Home home;
    String user;

    public ContactListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.contact_list,container,false);
        recyclerView=view.findViewById(R.id.rv_list);
        db=new DbHelper(getContext());
        //pref=new MySharedPref(getContext());
        user=MySharedPref.getUser();
        adapter=new ContactRecyclerAdapter(getContext(),db.getContacts(user));
        home=new Home();
        home.getRecyclerView(recyclerView);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
