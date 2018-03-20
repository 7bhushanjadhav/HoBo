package com.example.maulikjagtap.googlemap;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ChatSystem extends AppCompatActivity {

    private  static  int SIGN_IN_REQUEST_CODE=1;
    private Toolbar toolbar ;
    private FirebaseListAdapter<ChatMessage> adapter;
    RelativeLayout relativeLayout;
    FloatingActionButton floatingActionButton;
    EditText inputtext;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SIGN_IN_REQUEST_CODE)
        {
            if(requestCode==RESULT_OK)
            {
                Snackbar.make(relativeLayout,"Sucessfully signed in welocme",Snackbar.LENGTH_LONG).show();
                displaycharmessage();
            }
            else
            {
                Snackbar.make(relativeLayout," try again",Snackbar.LENGTH_LONG).show();
                finish();


            }


        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_system);
       toolbar = (Toolbar) findViewById(R.id.toolar1);
       setSupportActionBar(toolbar);
        relativeLayout = (RelativeLayout) findViewById(R.id.messageid);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(), SIGN_IN_REQUEST_CODE);

        } else {
            Snackbar.make(relativeLayout, "welocme" + FirebaseAuth.getInstance().getCurrentUser().getEmail(), Snackbar.LENGTH_SHORT).show();
            displaycharmessage();
        }
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputtext = (EditText) findViewById(R.id.input);

                FirebaseDatabase.getInstance().getReference().push().setValue(new ChatMessage(inputtext.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getDisplayName()));
                inputtext.setText("");
                displaycharmessage();
            }
        });
    }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            super.onCreateOptionsMenu(menu);
            getMenuInflater().inflate(R.menu.menu,menu);
            return  true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            super.onOptionsItemSelected(item);
            int id=item.getItemId();
            switch (id)
            {
                case R.id.logout:

                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getBaseContext(),Login.class));

            }

            return true;
        }


    private void displaycharmessage() {
        ListView listofmessage=(ListView)findViewById(R.id.messagelist);
        adapter=new FirebaseListAdapter<ChatMessage>(this,ChatMessage.class,R.layout.chatmessage,FirebaseDatabase.getInstance().getReference())
        {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                TextView messagetext,messageuser,messagetime;
                messagetext=(TextView)v.findViewById(R.id.message_text);
                messageuser=(TextView)v.findViewById(R.id.message_user);
                messagetime=(TextView)v.findViewById(R.id.message_time);
                messagetext.setText(model.getMessageText());
                messageuser.setText(model.getMessageUser());
                messagetime.setText(DateFormat.format("dd-MM-YYYY (HH:mm:ss)",model.getMessageTime()));


            }
        };
        listofmessage.setAdapter(adapter);
    }


    }




