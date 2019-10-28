package com.example.specialmessage;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
public class SearchFragment extends Fragment {


    public SearchFragment() {
        // Required empty public constructor
    }
    EditText search;
    ListView listView;
    List<User>list;
    SearchAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_search, container, false);
        search = v.findViewById(R.id.searchUserEditText);
        list = new ArrayList<>();
        listView = v.findViewById(R.id.UsersListView);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Users");
       search.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }
           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               myRef.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       list = new ArrayList<>();

                       for (DataSnapshot datasnap:dataSnapshot.getChildren()
                       ) {
                           String username=   datasnap.child("username").getValue().toString();
                           if (username.contains(search.getText().toString())) {
                               User user = new User(dataSnapshot.getKey(), datasnap.child("username").getValue().toString(), datasnap.child("email").getValue().toString());
                               list.add(user);
                           }
                       }
                       adapter = new SearchAdapter(getContext(),list);
                       listView.setAdapter(adapter);
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });
           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });

        return v;
    }

}
