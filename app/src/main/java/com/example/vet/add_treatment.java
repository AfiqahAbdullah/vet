package com.example.vet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class add_treatment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    ActionBar actionBar;

    TextView  tv_starttime,tv_endtime;
    EditText date, petname, vetname, custIC, start_time, end_time, medicine, payment;
    Spinner treatment;
    Button btnAdd, btnCancel;
    ArrayAdapter<CharSequence> adapter;

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_treatment);

        this.setTitle("TREATMENT");

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        treatment = (Spinner) findViewById(R.id.treatment);
        adapter = ArrayAdapter.createFromResource(this, R.array.treatment, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        treatment.setAdapter(adapter);

        treatment.setOnItemSelectedListener(this);

        date = (EditText) findViewById(R.id.date);
        petname = (EditText) findViewById(R.id.petname);
        vetname = (EditText) findViewById(R.id.vetname);
        custIC = (EditText) findViewById(R.id.custIC);
        start_time = (EditText) findViewById(R.id.start_time);
        end_time = (EditText) findViewById(R.id.end_time);
        medicine = (EditText) findViewById(R.id.medicine);
        payment = (EditText) findViewById(R.id.payment);

        tv_starttime = (TextView) findViewById(R.id.tv_startime);
        tv_endtime = (TextView) findViewById(R.id.tv_endtime);

        btnAdd = (Button) findViewById(R.id.btnadd);
        btnCancel = (Button) findViewById(R.id.btncancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // on below line we are adding click
        // listener for our pick date button
        start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting the
                // instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting our hour, minute.
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // on below line we are initializing our Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(add_treatment.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                // on below line we are setting selected time
                                // in our text view.
                                start_time.setText(hourOfDay + ":" + minute);
                            }
                        }, hour, minute, false);
                // at last we are calling show to
                // display our time picker dialog.
                timePickerDialog.show();
            }
        });

        end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting the
                // instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting our hour, minute.
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // on below line we are initializing our Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(add_treatment.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                // on below line we are setting selected time
                                // in our text view.
                                end_time.setText(hourOfDay + ":" + minute);
                            }
                        }, hour, minute, false);
                // at last we are calling show to
                // display our time picker dialog.
                timePickerDialog.show();
            }
        });
    }

    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    private void insertData(){
        Map<String,Object> map = new HashMap<>();
        map.put("date",date.getText().toString());
        map.put("petname",petname.getText().toString());
        map.put("vetname",vetname.getText().toString());
        map.put("custIC",custIC.getText().toString());
        map.put("start_time",start_time.getText().toString());
        map.put("end_time",end_time.getText().toString());
        map.put("treatment",treatment.getSelectedItem().toString());
        map.put("medicine",medicine.getText().toString());
        map.put("payment",payment.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("treat").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(add_treatment.this, "Data Inserted Successfully.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(add_treatment.this, "Error While Insertation.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
