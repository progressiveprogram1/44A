package com.example.rkuandpeer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        getSupportActionBar().setTitle("Events up to date");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button sports = (Button) findViewById(R.id.btnsports);
        Button calendar = (Button) findViewById(R.id.btncalendar);
        Button special = (Button) findViewById(R.id.btnspecial);
        Button academic = (Button) findViewById(R.id.btnacademic);

        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sports = new Intent(EventActivity.this,SportsActivity.class);
                startActivity(sports);
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calendar = new Intent(EventActivity.this,CalendarActivity.class);
                startActivity(calendar);
            }
        });

        academic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent academic = new Intent(EventActivity.this,AcademicEventActivity.class);
                startActivity(academic);
            }
        });


        special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent special = new Intent(EventActivity.this,SpecialEventActivity.class);
                startActivity(special);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.event_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.home)
        {
            startActivity(new Intent(EventActivity.this,MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}



