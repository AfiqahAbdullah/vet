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

public class AppointmentPage extends AppCompatActivity {

    ActionBar actionBar;
    RecyclerView recyclerView;
    CustAppointmentAdapter custAppointmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_page);

        this.setTitle("Appointment");

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton floatingActionButton = findViewById(R.id. floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AppointmentPage.this, book_appt_cust.class));
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<AppointmentModel> options =
                new FirebaseRecyclerOptions.Builder<AppointmentModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("appt"), AppointmentModel.class)
                        .build();

        custAppointmentAdapter = new CustAppointmentAdapter(options);
        recyclerView.setAdapter(custAppointmentAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        custAppointmentAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        custAppointmentAdapter.stopListening();
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