package com.myfirstprogramm.sokol.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class UsersAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    List<User> mUserList;



    public UsersAdapter(Context context, List<User> users) {
        layoutInflater = LayoutInflater.from(context);
        mUserList = users;
    }

    @Override
    public int getCount() {
        return mUserList.size();
    }

    @Override
    public Object getItem(int position) {
        return mUserList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView =  layoutInflater.inflate(R.layout.item, parent, false);

        TextView nameTo = (TextView) convertView.findViewById(R.id.textView);
        TextView  lastNameTo = (TextView) convertView.findViewById(R.id.textView2);
        TextView  ageTo = (TextView) convertView.findViewById(R.id.textView3);

        User user = mUserList.get(position);

        nameTo.setText(user.getName());

        lastNameTo.setText(user.getLastName());

        ageTo.setText(String.valueOf(user.getAge()));

        return convertView;
    }
}
