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

public class SendMessageAdapter extends ArrayAdapter<String> {
    public SendMessageAdapter(@NonNull Context context,  @NonNull List<String> objects) {
        super(context, 0,objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.sendmessagelayout,parent,false);
        String s = getItem(position);
        TextView textView = convertView.findViewById(R.id.messageText);
        textView.setText(s);
        return convertView;
    }
}
