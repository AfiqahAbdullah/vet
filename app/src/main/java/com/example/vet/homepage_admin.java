package com.example.vet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class homepage_admin extends AppCompatActivity {

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_admin);

        this.setTitle("HOME");

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button Registerbtn = findViewById(R.id.btnRegister);
        Button Servicebtn = findViewById(R.id.btnService);
        Button Apptbtn = findViewById(R.id.btnAppt);
        Button Treatbtn = findViewById(R.id.btnTreat);

        Registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(homepage_admin.this, registration_page.class));
            }
        });
        Servicebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(homepage_admin.this, rec_service.class));
            }
        });
        Apptbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(homepage_admin.this, rec_appointment.class));
            }
        });
        Treatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(homepage_admin.this, rec_treatment.class));
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate (R.menu.menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.aboutus:
                Intent intent = new Intent(this, about_us.class);
                startActivity(intent);
                break;
            case R.id.logout:
                intent = new Intent(this, app_page.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}