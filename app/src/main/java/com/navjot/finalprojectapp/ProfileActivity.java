package com.navjot.finalprojectapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity  {

   // private static final int CHOOSE_IMAGE = 101;
   // private FirebaseAuth mAuth;
    EditText name,email,mobileNo,weight,age;
    //RadioGroup gender;
    private Button updateProfile,retriveData;
    //ImageView imageView;
   // Uri uriProfileImage;
   // ProgressBar progressBar;
   // String profileImageUrl;
DatabaseReference databaseReference;
FirebaseFirestore firebaseFirestore;

    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        Intent rcv = getIntent();

        firebaseFirestore = FirebaseFirestore.getInstance();


        findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();

                Intent intent=new Intent(ProfileActivity.this,AuthenticationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  |  Intent.FLAG_ACTIVITY_CLEAR_TASK); //will clear everything from the stack and start new activity  ie. On clicking back button , Activity will not go back to the login screen
               startActivity(intent);

            }
      });

        databaseReference= FirebaseDatabase.getInstance().getReference("profiledata");

       // mAuth= FirebaseAuth.getInstance();
        name =findViewById(R.id.editTextName);
        email = findViewById(R.id.editTextEmail);
        mobileNo = findViewById(R.id.editTextMobileNo);
        weight= findViewById(R.id.editTextWeight);
        age = findViewById(R.id.editTextAge);

        updateProfile=findViewById(R.id.updateProfile);
        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    //addProfileData();
                addProfileData();
              //  fetchProfileDataFromFirebase();
                Toast.makeText(ProfileActivity.this,"Profile updated in firebase",Toast.LENGTH_LONG).show();
            }
        });

        retriveData=findViewById(R.id.retriveData);
        retriveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDataFromFirebase();
            }
        });
    }


    public void showDataFromFirebase(){

        firebaseFirestore.collection("user").document("one").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if (task.isSuccessful()){

                            DocumentSnapshot documentSnapshot= task.getResult();


                            String userName = documentSnapshot.getString("userName");
                            String userEmail = documentSnapshot.getString("userEmail");
                            String userMobileNo = documentSnapshot.getString("userMobileNo");
                            String userWeight = documentSnapshot.getString("userWeight");
                            String userAge = documentSnapshot.getString("userAge");


                            name.setText(userName);
                            email.setText(userEmail);
                            mobileNo.setText(userMobileNo);
                            weight.setText(userWeight);
                            age.setText(userAge);


                        }else{
                            Toast.makeText(ProfileActivity.this,"Error occured...",Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }




    public void addProfileData(){

        Toast.makeText(ProfileActivity.this,"addProfiledata() called",Toast.LENGTH_LONG).show();
        String userName = name.getText().toString();
        String userEmail = email.getText().toString();
        String userMobileNo = mobileNo.getText().toString();
        String userWeight = weight.getText().toString();
        String userAge = age.getText().toString();

        if(!TextUtils.isEmpty(userName)   && !TextUtils.isEmpty(userEmail)  &&  !TextUtils.isEmpty(userMobileNo)  &&  !TextUtils.isEmpty(userWeight)  &&  !TextUtils.isEmpty(userAge) ){

            String id =databaseReference.push().getKey();

            ProfileData profileData= new ProfileData(id,userName,userEmail,userMobileNo,userWeight,userAge);

            //
            // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
         //   String uid = user.getUid();

            firebaseFirestore.collection("user").document("one").set(profileData)
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isComplete()){
                                Toast.makeText(ProfileActivity.this, "func called  ", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(ProfileActivity.this, "error.. func not called      ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

            name.setText("");
            email.setText("");
            mobileNo.setText("");
            weight.setText("");
            age.setText("");
         //   Toast.makeText(ProfileActivity.this,"addProfiledata() COMPLETED ",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(ProfileActivity.this,"Please Enter full data",Toast.LENGTH_LONG).show();
        }
    }






    }







//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_profile);
//        getSupportActionBar().hide();
//        Intent rcv= getIntent();
//
//        progressBar =findViewById(R.id.progressBar2);
//        mAuth= FirebaseAuth.getInstance();
//        name =findViewById(R.id.editTextName);
//        email = findViewById(R.id.editTextEmail);
//        mobileNo = findViewById(R.id.editTextMobile);
//        gender = findViewById(R.id.editTextGender);
//        weight= findViewById(R.id.editTextWeight);
//        age = findViewById(R.id.editTextAge);
//        updateProfile=findViewById(R.id.buttonUpdateProfile);
//
//        updateProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                updateUserProfile();
//
//            }
//        });
//
//
//
//       imageView=findViewById(R.id.image);
//
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                showImageChooser();
//
//            }
//        });
//
//
//
//
//
//        findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                FirebaseAuth.getInstance().signOut();
//
//                Intent intent=new Intent(ProfileActivity.this,AuthenticationActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  |  Intent.FLAG_ACTIVITY_CLEAR_TASK); //will clear everything from the stack and start new activity  ie. On clicking back button , Activity will not go back to the login screen
//                startActivity(intent);
//
//            }
//        });
//    }
//
//    private void updateUserProfile() {
//
//        String username= name.getText().toString();
//        String useremail= email.getText().toString();
//        String usermobileno= mobileNo.getText().toString();
//        String usergender= String.valueOf(gender.getCheckedRadioButtonId());
//        String userweight= name.getText().toString();
//        String userage= name.getText().toString();
//        if(username.isEmpty()){
//            name.setError("Name required");
//            name.requestFocus();
//            return;
//        }
//
//        if(useremail.isEmpty()){
//            email.setError("Email required");
//            email.requestFocus();
//            return;
//        }
//
//        if(usermobileno.isEmpty()){
//            mobileNo.setError("Mobile no. required");
//            mobileNo.requestFocus();
//            return;
//        }
//
//        if(userweight.isEmpty()){
//            weight.setError("Weight required");
//            weight.requestFocus();
//            return;
//        }
//
//        if(userage.isEmpty()){
//            age.setError("Weight required");
//            age.requestFocus();
//            return;
//        }
//
//        FirebaseUser user =mAuth.getCurrentUser();
//        if(user != null  &&  profileImageUrl != null){
//            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
//
//                 .setDisplayName(username)
//                    .setPhotoUri(Uri.parse(profileImageUrl))
//                    .build()
//                    ;
//
//            user.updateProfile(profile)
//                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if(task.isSuccessful()){
//                                Toast.makeText(ProfileActivity.this,"Profile Updated",Toast.LENGTH_LONG).show();
//                            }
//                        }
//                    });
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//
//        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK  && data!= null && data.getData() != null){
//
//          uriProfileImage = data.getData();
//
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uriProfileImage);
//                imageView.setImageBitmap(bitmap);
//
//                uploadImageToFirebasStorage();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
//
//    private void uploadImageToFirebasStorage() {
//
//        StorageReference profileImageRef =
//                FirebaseStorage.getInstance().getReference("profilepics/"+System.currentTimeMillis() + ".jpg");
//
//        if (uriProfileImage != null){
//            progressBar.setVisibility(View.VISIBLE);
//            profileImageRef.putFile(uriProfileImage)
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        progressBar.setVisibility(View.GONE);
//                        profileImageUrl = taskSnapshot.getDownloadUrl().toString();
//
//                    }
//                })
//            .addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    progressBar.setVisibility(View.GONE);
//                    Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//
//    }
//
//    private void showImageChooser(){
//        Intent intent= new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Profile Image"), CHOOSE_IMAGE);
//    }
//
//}
