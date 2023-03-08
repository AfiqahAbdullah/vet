package com.example.vet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class rec_userregistration extends AppCompatActivity {

    ActionBar actionBar;
    RecyclerView recyclerView;
    RegisterAdapter registerAdapter;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_userregistration);

        this.setTitle("CUSTOMER");

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<RegisterModel> options =
                new FirebaseRecyclerOptions.Builder<RegisterModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("cust"), RegisterModel.class)
                        .build();

        registerAdapter = new RegisterAdapter(options);
        recyclerView.setAdapter(registerAdapter);

        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),add_cust.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        registerAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch (String str){
        FirebaseRecyclerOptions<RegisterModel> options =
                new FirebaseRecyclerOptions.Builder<RegisterModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("cust").orderByChild("username").startAt(str).endAt(str+"~"), RegisterModel.class)
                        .build();
        registerAdapter = new RegisterAdapter(options);
        registerAdapter.startListening();
        recyclerView.setAdapter(registerAdapter);
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