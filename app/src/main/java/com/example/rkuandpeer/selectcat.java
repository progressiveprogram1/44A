package com.example.rkuandpeer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class selectcat extends AppCompatActivity {
    private ImageButton addpost;
    private ImageButton  viewcart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectcat);
        addpost=findViewById(R.id.addpost);
        viewcart=findViewById(R.id.viewcart);
        addpost.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(selectcat.this,fillform.class);
                startActivity(intent);
            }
        });
        viewcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(selectcat.this,viewcart.class);
                startActivity(intent);
            }
        });
    }
}