package com.example.vet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ServicesPage extends AppCompatActivity {
    ActionBar actionBar;
    RecyclerView recyclerView;
    CustServiceAdapter custServiceAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_page);

        this.setTitle("Services");

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ServiceModel> options =
                new FirebaseRecyclerOptions.Builder<ServiceModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("service"), ServiceModel.class)
                        .build();

        custServiceAdapter = new CustServiceAdapter(options);
        recyclerView.setAdapter(custServiceAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        custServiceAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        custServiceAdapter.stopListening();
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