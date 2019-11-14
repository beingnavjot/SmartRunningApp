//package com.navjot.finalprojectapp;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.NotificationCompat;
//
//import android.app.Notification;
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.app.ProgressDialog;
//import android.content.ContentResolver;
//import android.content.Intent;
//import android.os.Build;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;
//
////public class ProfileNewUserActivity extends AppCompatActivity {
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_profile_new_user);
////    }
////}
//
//public class ProfileNewUserActivity extends AppCompatActivity implements View.OnClickListener {
//
//    EditText name,email,mobileNo,weight,age;
//    //RadioGroup gender;
//    private Button updateProfile;
//
//    //User user;
//    ProfileData profileData;
//
//    ContentResolver resolver;
//
//    boolean updateMode;
//
//    ProgressDialog progressDialog;
//
//    void initViews(){
//        name =findViewById(R.id.editTextName);
//        email = findViewById(R.id.editTextEmail);
//        mobileNo = findViewById(R.id.editTextMobileNo);
//        weight= findViewById(R.id.editTextWeight);
//        age = findViewById(R.id.editTextAge);
//        updateProfile=findViewById(R.id.updateProfile);
//        updateProfile.setOnClickListener(this);
//
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Please Wait..");
//        progressDialog.setCancelable(false);
//
//        profileData = new profileData();
//        resolver = getContentResolver();
//
//        Intent rcv = getIntent();
//        updateMode = rcv.hasExtra("keyCustomer");
//
//        if(updateMode){
//
//            user = (User) rcv.getSerializableExtra("keyCustomer");
//            eTxtName.setText(customer.name);
//            eTxtPhone.setText(customer.phone);
//            eTxtEmail.setText(customer.email);
//            btnAdd.setText("Update Customer");
//        }
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_customer);
//        initViews();
//    }
//
//    @Override
//    public void onClick(View view) {
//        if(view.getId() == R.id.buttonAdd){
//
//            customer.name = eTxtName.getText().toString();
//            customer.phone = eTxtPhone.getText().toString();
//            customer.email = eTxtEmail.getText().toString();
//
//            /*
//            //Toast.makeText(this, customer.toString(), Toast.LENGTH_LONG).show();
//
//            String tabName = "Customer";
//            // URI is 3 parts :  1. content:// | 2. authorities | 3. Table Name
//            Uri uri = Uri.parse("content://com.auribises.gw2019android1.mycp/"+tabName);
//
//            ContentValues values = new ContentValues();
//            // LHS name is column name of our table and RHS is value which we wish to insert in table
//            values.put("name", customer.name);
//            values.put("phone", customer.phone);
//            values.put("email",customer.email);
//
//            if(!updateMode) {
//
//                Uri dummyUri = resolver.insert(uri, values);
//                Toast.makeText(this, customer.name + " Added at " + dummyUri.getLastPathSegment(), Toast.LENGTH_LONG).show();
//
//                eTxtName.setText("");
//                eTxtPhone.setText("");
//                eTxtEmail.setText("");
//            }else{
//
//                String where = "_ID = "+customer.id;
//                int i = resolver.update(uri, values, where, null);
//                if(i>0){
//                    Toast.makeText(this, customer.name+" Updated", Toast.LENGTH_LONG).show();
//                    finish();
//                }else{
//                    Toast.makeText(this, customer.name+" Not Updated", Toast.LENGTH_LONG).show();
//                }
//
//            }
//            */
//
//            progressDialog.show();
//            addCustomerInFirebase();
//
//        }
//    }
//
//    void addCustomerInFirebase(){
//
//        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = firebaseUser.getUid();
//
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//        db.collection("users")
//                .document(uid)
//                .collection("customers")
//                .add(customer)
//                .addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentReference> task) {
//                        if(task.isComplete()){
//                            Toast.makeText(AddCustomerActivity.this, customer.name + " Added in DataBase", Toast.LENGTH_LONG).show();
//
//                            eTxtName.setText("");
//                            eTxtPhone.setText("");
//                            eTxtEmail.setText("");
//                            progressDialog.dismiss();
//                            showNotification();
//                        }
//                    }
//                });
//
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        if(!updateMode) {
//            menu.add(1, 101, 1, "Customers").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
//
//        }
//
//        menu.add(1, 201, 1, "LogOut").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        if(item.getItemId() == 101){
//            //Toast.makeText(this, "You Selected Customers", Toast.LENGTH_SHORT).show();
//
//            Intent intent = new Intent(AddCustomerActivity.this, AllCustomersActivity.class);
//            startActivity(intent);
//
//        }
//
//        if(item.getItemId() == 201){
//
//            FirebaseAuth auth = FirebaseAuth.getInstance();
//            auth.signOut();
//
//            Intent intent = new Intent(AddCustomerActivity.this, SplashActivity.class);
//            startActivity(intent);
//            finish();
//
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    void showNotification(){
//
//        // To show the Notification
//        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            // Associate a NotificationChannel to NotificationManager
//            NotificationChannel notificationChannel = new NotificationChannel("myId", "myChannel", NotificationManager.IMPORTANCE_HIGH);
//            notificationManager.createNotificationChannel(notificationChannel);
//        }
//
//        Intent intent = new Intent(AddCustomerActivity.this, TechCrunchNewsActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 111, intent, 0);
//
//        // Create Notification
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "myId");
//        builder.setContentTitle("This is Title");
//        builder.setContentText("Customer Added "+customer.name);
//        builder.setContentIntent(pendingIntent);
//        builder.setSmallIcon(R.drawable.ic_menu_send);
//
//        Notification notification = builder.build();
//
//        notificationManager.notify(101, notification);
//
//    }
//
//}
