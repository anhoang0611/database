package com.example.databasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText name,phone;
    Button insert;
    FirebaseDatabase database;
    DatabaseReference ref;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        insert = (Button) findViewById(R.id.btnInsert);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("User");

        user = new User();
//        insert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ref.child("User").setValue(user);
//            }
//        });


    }
    private void getValues(){
        user.setName(name.getText().toString());
        user.setPhone(phone.getText().toString());
        // tạo child

        ref.child(user.getName()).setValue(user);
    }

    public void btnInsert(View view){
        Intent intent = new Intent(MainActivity.this,Retrieve.class);
        startActivity(intent);
        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                getValues();
                Toast.makeText(MainActivity.this, "Data Insert..", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}