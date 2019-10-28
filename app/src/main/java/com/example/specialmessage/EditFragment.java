package com.example.specialmessage;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditFragment extends Fragment {


    public EditFragment() {
        // Required empty public constructor
    }

    String id;
    List<String>list;
    ListView listView;
    MessageAdapter adapter;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v= inflater.inflate(R.layout.fragment_edit, container, false);

        return v;
    }

    @Override
    public void onStart() {
        MainActivity activity = (MainActivity) getActivity();
        id = activity.getId();

        list = new ArrayList<>();
        listView = v.findViewById(R.id.messagesListView);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Messages").child(id);
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                adapter.notifyDataSetChanged();
                for (DataSnapshot data:dataSnapshot.getChildren()
                ) {

                    list.add(data.getKey());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        adapter = new MessageAdapter(getContext(),list);
        listView.setAdapter(adapter);
        super.onStart();
    }
}
