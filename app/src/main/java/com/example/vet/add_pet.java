package com.example.vet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class add_pet extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    EditText petName, petBreed, petSex, petDOB, petSurl, petOwnerIC;
    TextView tvDate;
    Spinner petType;

    Button btnAdd, btnCancel;
    ActionBar actionBar;
    ArrayAdapter<CharSequence> adapter;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        this.setTitle("PET");

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        petName = (EditText) findViewById(R.id. petName);

        petType = (Spinner) findViewById(R.id.petType);
        adapter = ArrayAdapter.createFromResource(this, R.array.type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        petType.setAdapter(adapter);

        petType.setOnItemSelectedListener(this);

        petSex = (EditText) findViewById(R.id.petSex);
        petBreed = (EditText) findViewById(R.id.petBreed);
        petDOB = (EditText) findViewById(R.id.petDOB);
        petSurl = (EditText) findViewById(R.id.petSurl);
        petOwnerIC = (EditText) findViewById(R.id.petOwnerIC);

        tvDate = (TextView) findViewById(R.id.tv_date);

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

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        petDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        add_pet.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
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
                petDOB.setText(date);
            }
        };

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        add_pet.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        tvDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();

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
        map.put("petName",petName.getText().toString());
        map.put("petType",petType.getSelectedItem().toString());
        map.put("petSex",petBreed.getText().toString());
        map.put("petBreed",petBreed.getText().toString());
        map.put("petDOB",petDOB.getText().toString());
        map.put("petSurl",petSurl.getText().toString());
        map.put("petOwnerIC",petOwnerIC.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("pet").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(add_pet.this, "Data Inserted Successfully.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(add_pet.this, "Error While Insertation.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
