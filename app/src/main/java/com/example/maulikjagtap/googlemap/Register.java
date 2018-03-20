package com.example.maulikjagtap.googlemap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    Button buton;
    private Firebase mref;
    FirebaseDatabase userdatabase;
    DatabaseReference databaseuser;
    EditText e1, e2, e3;
    TextView t1;
    private FirebaseAuth mAuth;
    ProgressDialog pd;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        buton = (Button) findViewById(R.id.button1);
        //e3= (EditText) findViewById(R.id.editname);
        e1 = (EditText) findViewById(R.id.editemail);
        e2 = (EditText) findViewById(R.id.editpass);
        t1=(TextView)findViewById(R.id.login);
        Firebase.setAndroidContext(this);
        mAuth = FirebaseAuth.getInstance();
        pd =new ProgressDialog(this);

        userdatabase = FirebaseDatabase.getInstance();
        // databaseuser = userdatabase.getReference("user");


        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  String name=e1.getText().toString();
                String email=e2.getText().toString().trim();
                String pass=e3.getText().toString().trim();
                Firebase mchild = mref.child("user");
                mchild.push().child("name").child("email").child("pass");
                mchild.setValue(name);
                mchild.setValue(email);
                mchild.setValue(pass);*/

                insert();

                Toast.makeText(getBaseContext(), "data is insert", Toast.LENGTH_LONG).show();

            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),Login.class));
                finish();
            }
        });
    }

    private void insert() {
        String  email= e1.getText().toString().trim();
        String pass = e2.getText().toString().trim();
        // String pass = e3.getText().toString().trim();
        if(email.isEmpty())
        {
            e1.setError("email is required");
            e1.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            e1.setError("please enter a valid email address");
            e1.requestFocus();
            return;
        }
        if(pass.isEmpty())
        {
            e2.setError("password is required");
            e2.requestFocus();
            return;
        }
        if(pass.length()<6)
        {
            e2.setError("Minimum length of passwrod  should be 6");
            e2.requestFocus();
            return;
        }
        pd.setMessage("Register");
        pd.show();
        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    pd.dismiss();
                    startActivity(new Intent(getBaseContext(),Login.class));
                    finish();
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                }

            }
        });


    /*   if (!TextUtils.isEmpty(email)) {
            String id = databaseuser.push().getKey();
            User user = new User("", email, pass);
            databaseuser.child(id).setValue(user);


        } else {
            Toast.makeText(getBaseContext(), "name is requried", Toast.LENGTH_LONG).show();
        } */


    }
}