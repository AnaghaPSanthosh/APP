package com.example.proj;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SIGNUP extends AppCompatActivity {
    private EditText mEmail, mPass, mPhone , mName;
    private Button mRegister;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mFirebaseAuthStateListnener;
    Button btn2_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Intent intent=getIntent();
        String message=intent.getStringExtra(LOGIN.EXTRA_MESSAGE);
        TextView textView=findViewById(R.id.name);
        mEmail = (EditText) findViewById(R.id.etemail);
        mPass = (EditText) findViewById(R.id.pass);
        mRegister = (Button) findViewById(R.id.reg);
        mName = (EditText) findViewById(R.id.name);
        mPhone = (EditText) findViewById(R.id.etmob);
        String email = mEmail.getText().toString().trim();
        String password= mPass.getText().toString().trim();
        if(email.isEmpty())
        {
            mEmail.setError("Email is empty");
            mPass.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            mEmail.setError("Enter the valid email address");
            mEmail.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            mPass.setError("Enter the password");
            mPass.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            mPass.setError("Length of the password should be more than 6");
            mPass.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(SIGNUP.this,"You are successfully Registered", Toast.LENGTH_SHORT).show
                            ();
                }
                else
                {
                    Toast.makeText(SIGNUP.this,"You are not Registered! Try again",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}
