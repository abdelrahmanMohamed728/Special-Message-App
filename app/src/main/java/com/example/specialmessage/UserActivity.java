package com.example.specialmessage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    ListView listView;
    List<String> list;
    SendMessageAdapter adapter;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
         user = new User(getIntent().getStringExtra("id"),getIntent().getStringExtra("username"),getIntent().getStringExtra("email"));
        listView = findViewById(R.id.sendMessagesListView);
        list = new ArrayList<>();
         final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Messages").child(getIntent().getStringExtra("curid"));
        final DatabaseReference myRef2 = database.getReference("Get").child(user.getId());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren()
                     ) {
                   list.add(data.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        adapter = new SendMessageAdapter(UserActivity.this,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               myRef2.child(list.get(position)).setValue(true);
               Toast.makeText(UserActivity.this,"Message Sent",Toast.LENGTH_LONG).show();
            }
        });
    }
}
