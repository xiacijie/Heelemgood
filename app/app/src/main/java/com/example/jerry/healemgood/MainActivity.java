package com.example.jerry.healemgood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jerry.healemgood.controller.UserCreationController;
import com.example.jerry.healemgood.model.user.User;
import com.example.jerry.healemgood.view.UserViews.AccountCreationActivity;
import com.example.jerry.healemgood.view.UserViews.PatientHomeActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.onCreateAccount();
    }
    public void onCreateAccount(){
        TextView createTextView = findViewById(R.id.createAccountTextView);
        Button signInButton = findViewById(R.id.signInButton);
        createTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AccountCreationActivity.class));
            }
        });
        signInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String userId = ((EditText)findViewById(R.id.userIdEditText)).getText().toString();

                Intent intent = new Intent(MainActivity.this,PatientHomeActivity.class);
                startActivity(intent);


            }
        });

    }
}
