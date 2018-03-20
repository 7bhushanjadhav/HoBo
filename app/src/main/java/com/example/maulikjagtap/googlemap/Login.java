package com.example.maulikjagtap.googlemap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText email,pass;
    Button login,singup;
    ProgressDialog pd;
    String useremail,userpass;
    private FirebaseAuth firebaseAuth;
    private  FirebaseAuth.AuthStateListener authStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.loginemail);
        pass = (EditText) findViewById(R.id.loginpass);
        login = (Button) findViewById(R.id.login);
        singup = (Button) findViewById(R.id.singup);
        pd = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getBaseContext(),MapsActivity.class));
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userLogin();
            }
        });
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),Register.class));
                finish();
            }
        });

    }




    private void userLogin()
    {
         useremail=email.getText().toString().trim();
        userpass=pass.getText().toString().trim();
        if(useremail.isEmpty())
        {
            email.setError("enter email");
            email.requestFocus();
            return;


        }
        if(userpass.isEmpty())
        {
            pass.setError("enter email");
            email.requestFocus();
            return;
        }
        pd.setMessage("Login");
        pd.show();
        firebaseAuth.signInWithEmailAndPassword(useremail,userpass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                pd.dismiss();
                if(task.isSuccessful())
                {

                    startActivity(new Intent(getBaseContext(),MapsActivity.class));
                    finish();
                }
                else
                {
                    startActivity(new Intent(getBaseContext(),Login.class));
                    finish();

                }

            }
        });





    }
}

