package com.example.specialmessage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SearchAdapter extends ArrayAdapter<User> {
    public SearchAdapter(@NonNull Context context,  @NonNull List<User> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.usersearchlayout,parent,false);
        User user = getItem(position);
        TextView text1 = convertView.findViewById(R.id.userSearchUsername);
        TextView text2 = convertView.findViewById(R.id.userSearchEmail);
        text1.setText(user.getUsername());
        text2.setText(user.getEmail());
        return convertView;
    }
}
