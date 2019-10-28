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

public class MessageAdapter extends ArrayAdapter<String> {
    public MessageAdapter(@NonNull Context context,  @NonNull List<String> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.messageslistviewlayout,parent,false);
        TextView text1 = convertView.findViewById(R.id.MessageIndex);
        TextView text2 = convertView.findViewById(R.id.MessageText);
        int index = position+1;
        text1.setText("Message "+index);
        text2.setText(getItem(position));
        return convertView;
    }
}
