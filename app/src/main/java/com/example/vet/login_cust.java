package com.example.vet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class login_cust extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_cust);

        Button loginbtn = findViewById(R.id.btnlogin);
        TextView btn = findViewById(R.id.signUp);

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(login_cust.this, register_cust_page.class));
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(login_cust.this, homepage_cust.class));
            }
        });
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
}