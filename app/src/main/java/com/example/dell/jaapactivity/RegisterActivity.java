package com.example.dell.jaapactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText username , email, password;
    Button register;
    MaterialEditText phoneNumber;
    FirebaseAuth  auth;
    DatabaseReference reference;
    boolean verified;
    TextView verify,verify_email;
    private String mVerificationId;
    private static final String TAG = "RegisterActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.emailid);
        register = findViewById(R.id.register);
        phoneNumber = findViewById(R.id.phoneNumber);
        verify = findViewById(R.id.verify);
        verify_email = findViewById(R.id.verify_email);
        LayoutInflater li = LayoutInflater.from(this);

        FirebaseApp.initializeApp(this);
        auth = FirebaseAuth.getInstance();

      //  verificationCode = promptsView.findViewById(R.id.verificationCode);


        // Phone Number verification

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txt_phoneNumber = phoneNumber.getText().toString().trim();
                Intent intent = new Intent(RegisterActivity.this,PhoneVerificationActivity.class);
                intent.putExtra("phoneNumber",txt_phoneNumber);
                startActivity(intent);

            }
        });

        // Verify email

       /* verify_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


             /*   if (checkIfEmailIsVerified()) {
                    Toast.makeText(RegisterActivity.this, "Email Id is  verified", Toast.LENGTH_SHORT).show();
                    FirebaseUser firebaseUser = auth.getCurrentUser();

                    final String userID = firebaseUser.getUid();

                    Toast.makeText(RegisterActivity.this, "Email verified ", Toast.LENGTH_SHORT);
                    reference = FirebaseDatabase.getInstance().getReference("Users").child(userID);
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("id", userID);
                    hashMap.put("username", txt_username);
                    hashMap.put("imageURL", "default");
                    hashMap.put("phoneNumber", txt_phoneNumber);
                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                //   Intent intent = new Intent(RegisterActivity.this,ScrollingActivity.class);
                                //   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                //   startActivity(intent);
                                //    finish();

                            }
                            else {
                                Toast.makeText(RegisterActivity.this, "Please verify your email first", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });

                }


                else*/


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_username = username.getText().toString();
                String txt_email =  email.getText().toString();
                String txt_password = password.getText().toString();
                String txt_phoneNumber = phoneNumber.getText().toString().trim();



                if(TextUtils.isEmpty(txt_username)|| TextUtils.isEmpty(txt_email)|| TextUtils.isEmpty(txt_password) || TextUtils.isEmpty(txt_phoneNumber)  ){
                    Toast.makeText(RegisterActivity.this,"All fields are required",Toast.LENGTH_SHORT).show();

                }
                else if(!isEmailValid(txt_email)){
                    email.setError("Incorrect email ID ");
                }
                else if(txt_password.length()<6){
                    Toast.makeText(RegisterActivity.this,"Password must be of at least 6 characters",Toast.LENGTH_SHORT).show();
                    password.setError("Password too short");
                }
                else if(txt_phoneNumber.length()<10){
                    phoneNumber.setError("Enter a valid mobile number");
                    phoneNumber.requestFocus();
                    return;
                }
                else {
                    register(txt_username,txt_email,txt_password,txt_phoneNumber);
                }

            }
        });

    }

    private boolean checkIfEmailIsVerified() {
        if (FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()) {

            verified = true;
        }
            return verified;

    }




    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    private void register(final String username, String email, final String password, final String phoneNumber){
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            final String userID   = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("Users").child(userID);
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", userID);
                            hashMap.put("username", username);
                            hashMap.put("imageURL", "default");
                            hashMap.put("phoneNumber", phoneNumber);
                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                          Intent intent = new Intent(RegisterActivity.this,ScrollingActivity.class);
                                          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                           startActivity(intent);
                                          finish();

                                    }
                                    else {
                                        Toast.makeText(RegisterActivity.this, "Please verify your email first", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            });

                            //Toast.makeText(RegisterActivity.this,"Check your email and click on the link",Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(RegisterActivity.this,"You can't register with this email or password ",Toast.LENGTH_SHORT).show();
                        }
                    }
                });




    }


}
