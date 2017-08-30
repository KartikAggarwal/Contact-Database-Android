package com.databasetask.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.databasetask.Database.DbHelper;
import com.databasetask.R;
import com.databasetask.beans.Contact;
import java.util.ArrayList;

/**
 * Created by appinventiv on 28/8/17.
 */

public class ContactRecyclerAdapter extends RecyclerView.Adapter<ContactRecyclerAdapter.ContactViewHolder> {
    int count;
    DbHelper db;
    ArrayList<Contact> allContacts;
    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_view,parent,false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.profile_pic.setImageResource(R.drawable.android);
        holder.name.setText(allContacts.get(position).getName());
        holder.number.setText(allContacts.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return allContacts.size(); //db.getContactCount();
    }

    public ContactRecyclerAdapter(Context context,ArrayList<Contact> allContacts)
    {
        db=new DbHelper(context);
        count= db.getContactCount();
        this.allContacts=allContacts; //db.getContacts();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        ImageView profile_pic;
        TextView name,number;
        public ContactViewHolder(View itemView) {
            super(itemView);
            profile_pic=itemView.findViewById(R.id.iv_profile_pic);
            name=itemView.findViewById(R.id.tv_contactName);
            number=itemView.findViewById(R.id.tv_contactNumber);

            /*itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    return false;
                }
            });*/
        }

    }
}
