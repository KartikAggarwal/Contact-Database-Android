package com.databasetask.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.databasetask.Database.DbHelper;
import com.databasetask.R;
import com.databasetask.sharedPrefrences.MySharedPref;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddContactFragment extends Fragment {

    EditText c_name,c_number;
    DbHelper db;
    Button save;
    MySharedPref pref;
    String user,contactName,contactNumber;

    public AddContactFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.new_contact,container,false);
        c_name=view.findViewById(R.id.et_new_contact_name);
        c_number=view.findViewById(R.id.et_new_contact_number);
        save=view.findViewById(R.id.b_save);
        db=new DbHelper(getContext());
        pref=new MySharedPref(getContext());

        user=MySharedPref.getUser();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactName=c_name.getText().toString();
                contactNumber=c_number.getText().toString();
                if(contactName.equals(""))
                    Toast.makeText(getContext(),"Enter Name",Toast.LENGTH_SHORT).show();
                else if (contactNumber.equals(""))
                    Toast.makeText(getContext(),"Enter Number",Toast.LENGTH_SHORT).show();
                else
                {
                    if(db.insertContact(user,contactName,contactNumber))
                        Toast.makeText(getContext(),"Saved",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();

                    c_name.setText("");
                    c_number.setText("");
                }
            }
        });
        return view;
    }

}
