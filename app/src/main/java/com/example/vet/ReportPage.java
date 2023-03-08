package com.example.vet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class ReportPage extends AppCompatActivity {
    ActionBar actionBar;
    RecyclerView recyclerView;
    CustReportAdapter custReportAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_page);

        this.setTitle("Report");

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ReportModel> options =
                new FirebaseRecyclerOptions.Builder<ReportModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("treat"), ReportModel.class)
                        .build();

        custReportAdapter = new CustReportAdapter(options);
        recyclerView.setAdapter(custReportAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        custReportAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        custReportAdapter.stopListening();
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