package com.example.rkuandpeer;


import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.Map;


public class fillform<database> extends AppCompatActivity {

    private Button nexting;
    private EditText title;
    private EditText price;
    private EditText email;
    private EditText desc;
    private Button btnUpload;
    private ImageView imageView;
    private Button publish;
    private FirebaseDatabase database; //Real-time database
    private FirebaseStorage storage;
    DatabaseReference databaseReference;//Firebase storage

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //for the drop down list we add the list
        String[] item={"Stationary", "Books","Clothes","Sport"};
        AutoCompleteTextView compy; //the def for the items
        ArrayAdapter<String> adapterItems;
        compy=findViewById(R.id.comp);
        //accessing the layout for it
        adapterItems=new ArrayAdapter<String>(this,R.layout.list_item,item);
        compy.setAdapter(adapterItems);
        compy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item=adapterView.getItemAtPosition(i).toString();
                Toast.makeText(fillform.this,"Category: " +item,Toast.LENGTH_SHORT).show();

            }
        });




        setContentView(R.layout.activity_fillform);






        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        //Gphoto = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        // @Override
        //public void onActivityResult(Uri result) {

//                firebaserecyclerOptions<MainModel> options=
//                imageView.setImageURI(result); //call image uri from gallery
//                final StorageReference reference = storage.getInstance().getReference().push().setValue(map).addOnSuccessListener()
//                values.put("title", titlein);
//                values.put("price",moneyin );
//                values.put("email", emailin);
//                HttpCookie ref;
//                reference.setValue(values); //calling storage referance to create a node(child) called image to store all images in it
//                reference.putFile(result).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() { //adding image to storage in image node using uri (address)
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        // Saving copy of same image in real-time database
//                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                            @Override
//                            public void onSuccess(Uri uri) { //creating image node in database
//                                database.getReference().child("User")
//                                        .setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                            @Override
//                                            public void onSuccess(Void unused) {
//                                                Toast.makeText(getApplicationContext(), "Image Uploaded", Toast.LENGTH_SHORT).show();
//                                                startActivity(new Intent(fillform.this,selectcat.class));
//                                                finish();
//                                            }
//                                        });
//                            }
//                        });
//                    }
//                });
//            }
//        });
//        btnUpload = findViewById(R.id.btnUpload);
//        imageView = findViewById(R.id.imageView);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Gphoto.launch("image/*");
//
//            }
//        });
//        btnUpload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//    }




        title=(EditText)findViewById(R.id.title);
        price=(EditText)findViewById(R.id.price);
        email=(EditText)findViewById(R.id.email);
        desc=(EditText)findViewById(R.id.desc);
//
//        back=(Button)findViewById(R.id.add_back);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(),MainActivity.class));
//                finish();
//            }
//        });

        publish=(Button)findViewById(R.id.publish);
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsert();
            }
        });
    }

    private void processinsert()
    {
        Map<String,Object> map=new HashMap<>();
        map.put("name",title.getText().toString());
        map.put("course",price.getText().toString());
        map.put("email",email.getText().toString());
        map.put("Description",desc.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("students").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        title.setText("");
                        price.setText("");
                        email.setText("");
                        desc.setText("");
                        Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Could not insert",Toast.LENGTH_LONG).show();
                    }
                });
    } }