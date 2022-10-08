package com.example.rkuandpeer;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewAnimationUtils;
import android.widget.LinearLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity //implements secondActivity1
{
    private CardView shopping, helping, guiding, maping, timing,phrasing;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Home");



        //defining cards
        shopping = (CardView) findViewById(R.id.buy);
        helping=(CardView) findViewById(R.id.help);
        guiding=(CardView) findViewById(R.id.guideline);
        maping=(CardView) findViewById(R.id.map);
        timing=(CardView) findViewById(R.id.date);
        phrasing=(CardView) findViewById(R.id.phrasebook);

        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Buy.class));
            }
        });
        helping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,seekhelp.class));
            }
        });
        guiding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,guidelines.class));
            }
        });

        maping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Map.class));
            }
        });

        timing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,EventActivity.class));
            }
        });

        phrasing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,phrasebook.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.settings)
        {
            startActivity(new Intent(MainActivity.this,CreateProfile.class));
        }

        if (id==R.id.logout)
        {
            startActivity(new Intent(MainActivity.this,login.class));
        }


//        if (id==R.id.profile)
//        {
//            startActivity(new Intent(MainActivity.this,profile.class));
//        }
        return super.onOptionsItemSelected(item);
    }
}



