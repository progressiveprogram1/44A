package com.example.rkuandpeer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class CreateProfile extends AppCompatActivity {
    EditText etusername,etemail,etcountry,etbranch;
    Button button;
    ImageView imageView;
    ProgressBar progressBar;
    Uri imageUri;
    UploadTask uploadTask;
    StorageReference storageReference;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    private static final int PICK_IMAGE=1;
    All_UserMember member;
    String currentUserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        imageView=findViewById(R.id.iv_cp);
        etusername=findViewById(R.id.et_username_cp);
        etemail=findViewById(R.id.et_email_cp);
        etcountry=findViewById(R.id.et_branch_cp);
        etbranch=findViewById(R.id.et_branch_cp);
        button=findViewById(R.id.btn_cp);
        progressBar=findViewById(R.id.progressbar_cp);

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        currentUserId=user.getUid();


        documentReference=db.collection("user").document(currentUserId);
        storageReference= FirebaseStorage.getInstance().getReference("Profile images");
        databaseReference=database.getReference("All Users");
        
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadData();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,PICK_IMAGE);

            }
        });
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        try {

            if (requestCode == PICK_IMAGE || resultCode == RESULT_OK || data != null || data.getData() != null) {
                imageUri = data.getData();

                Picasso.get().load(imageUri).into(imageView);
            }
        }
            catch(Exception e){
                Toast.makeText(this, "Error"+e, Toast.LENGTH_SHORT).show();
            }

            }
            private String getFileExt(Uri uri){
                ContentResolver contentResolver=getContentResolver();
                MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
                return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

            }





    private void uploadData() {

        String username=etusername.getText().toString();
        String email=etemail.getText().toString();
        String country=etcountry.getText().toString();
        String branch=etbranch.getText().toString();

        if( ! TextUtils.isEmpty(username) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(country) || !TextUtils.isEmpty(branch)
        || imageUri != null){

            progressBar.setVisibility(View.VISIBLE);
            final StorageReference reference=storageReference.child(System.currentTimeMillis()+"."+getFileExt(imageUri));
            uploadTask = reference.putFile(imageUri);

            Task<Uri>urlTask=uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                   return reference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {

                    if(task.isSuccessful()){
                        Uri downloadUri=task.getResult();

                        Map<String, String> profile =new HashMap<>();
                        profile.put("username",username);
                        profile.put("country",country);
                        profile.put("uid",currentUserId);
                        profile.put("branch",branch);
                        profile.put("email",email);
                        profile.put("email",email);

                        member.setName(username);
                        member.setUid(currentUserId);
                        member.setUrl(downloadUri.toString());

                        databaseReference.child(currentUserId).setValue(member);

                        documentReference.set(profile)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        progressBar.setVisibility(View.INVISIBLE);
                                        Toast.makeText(CreateProfile.this, "Profile Created", Toast.LENGTH_SHORT).show();

                                        Handler handler= new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent=new Intent(CreateProfile.this, fragment1.class);

                                            }
                                        },2000);

                                    }
                                });




                    }

                }
            });
            
        }
        else
        {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }

    }
}