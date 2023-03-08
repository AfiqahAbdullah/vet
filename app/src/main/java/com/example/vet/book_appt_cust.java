package com.example.vet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class book_appt_cust extends AppCompatActivity {

    ActionBar actionBar;

    TextView tvDate, tvTime;
    EditText petname, custname, custic, contact, etDate, etTime;
    Button Cancelbtn,Addbtn;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appt_cust);

        this.setTitle("Book Appointment");

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvDate = findViewById(R.id.tv_date);
        tvTime = findViewById(R.id.tv_time);

        petname = (EditText)findViewById(R.id.petname);
        custname = (EditText)findViewById(R.id.custname);
        custic = (EditText)findViewById(R.id.custic);
        contact = (EditText)findViewById(R.id.contact);

        etTime = (EditText)findViewById(R.id.et_time);
        etDate = (EditText)findViewById(R.id.et_date);


        Addbtn = findViewById(R.id.btnadd);
        Cancelbtn = findViewById(R.id.btncancel);

        Addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        Cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //startActivity(new Intent(book_appt_cust.this, homepage_cust.class));
            }
        });

        // on below line we are adding click
        // listener for our pick date button
        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting the
                // instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting our hour, minute.
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // on below line we are initializing our Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(book_appt_cust.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                // on below line we are setting selected time
                                // in our text view.
                                etTime.setText(hourOfDay + ":" + minute);
                            }
                        }, hour, minute, false);
                // at last we are calling show to
                // display our time picker dialog.
                timePickerDialog.show();
            }
        });

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        book_appt_cust.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });

        setListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                tvDate.setText(date);
            }
        };

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        book_appt_cust.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        etDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });
    }

    private void insertData()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("petname", petname.getText().toString());
        map.put("custname", custname.getText().toString());
        map.put("custIC", custic.getText().toString());
        map.put("contact", contact.getText().toString());
        map.put("date", etDate.getText().toString());
        map.put("time", etTime.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("appt").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(book_appt_cust.this, "Data inserted successfully.", Toast.LENGTH_LONG).show();
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(book_appt_cust.this, "failed to save the data.", Toast.LENGTH_LONG).show();
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
}