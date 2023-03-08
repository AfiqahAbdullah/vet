package com.example.vet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class homepage_cust extends AppCompatActivity implements View.OnClickListener{
    private CardView scan,gps,service,mpet,appointment,report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_cust);

        this.setTitle("Home");

        scan = (CardView)findViewById(R.id.dScan);
        gps = (CardView)findViewById(R.id.dGps);
        service = (CardView)findViewById(R.id.dServices);
        mpet = (CardView)findViewById(R.id.dPets);
        appointment = (CardView)findViewById(R.id.dAppointment);
        report = (CardView)findViewById(R.id.dReport);

        scan.setOnClickListener((View.OnClickListener) this);
        gps.setOnClickListener((View.OnClickListener) this);
        service.setOnClickListener((View.OnClickListener) this);
        mpet.setOnClickListener((View.OnClickListener) this);
        appointment.setOnClickListener((View.OnClickListener) this);
        report.setOnClickListener((View.OnClickListener) this);
    }

    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int item_id= item.getItemId();

        if(item_id == R.id.aboutus){
            Toast.makeText(this, "About Harmony Clinic", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, about_us.class);
            startActivity(intent);
        }
        else if(item_id == R.id.logout){
            Toast.makeText(this, "Log Out App", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, app_page.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.dScan: i = new Intent(this, ScanMePage.class); startActivity(i); break;
            case R.id.dGps: i = new Intent(this, GpsPage.class); startActivity(i); break;
            case R.id.dServices: i = new Intent(this, ServicesPage.class); startActivity(i); break;
            case R.id.dPets: i = new Intent(this, MyPetsPage.class); startActivity(i); break;
            case R.id.dAppointment: i = new Intent(this, AppointmentPage.class); startActivity(i); break;
            case R.id.dReport: i = new Intent(this, ReportPage.class); startActivity(i); break;
        }
    }
}