package com.example.vet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class MyPetsPage extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    ActionBar actionBar;

    RecyclerView recyclerView;
    CustPetAdapter custPetAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pets_page);

        this.setTitle("My Pets");

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=(RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<PetModel> options =
                new FirebaseRecyclerOptions.Builder<PetModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("pet"), PetModel.class)
                        .build();

        custPetAdapter = new CustPetAdapter(options);
        recyclerView.setAdapter(custPetAdapter);

        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyPetsPage.this, new_pet.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        custPetAdapter.startListening();
    }

    protected void onStop(){
        super.onStop();
        custPetAdapter.stopListening();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}