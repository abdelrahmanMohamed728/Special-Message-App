package com.example.specialmessage;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {
   EditText message;
   Button add;

    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_add, container, false);
        add = v.findViewById(R.id.AddMessageButton);
        message = v.findViewById(R.id.newMessageEditText);
        MainActivity activity = (MainActivity) getActivity();
        final String id = activity.getId();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference myRef = database.getReference("Messages").child(id).child(message.getText().toString());
                myRef.setValue(true);
                 myRef = database.getReference("Messages").child(message.getText().toString());
                myRef.setValue(true);
                message.setText("");
                Toast.makeText(getActivity(),"Message Added",Toast.LENGTH_LONG).show();
            }
        });
        return v;
    }

}
