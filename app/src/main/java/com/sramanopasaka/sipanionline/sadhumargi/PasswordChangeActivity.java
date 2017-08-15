package com.sramanopasaka.sipanionline.sadhumargi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordChangeActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button btnReset;
    EditText curPassword;
    EditText newPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        curPassword= (EditText) findViewById(R.id.cur_password);
        newPassword= (EditText) findViewById(R.id.new_password);
        btnReset= (Button) findViewById(R.id.button_reset);

        toolbar= (Toolbar) findViewById(R.id.tool_password);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow_patasala);
        getSupportActionBar().setTitle("पासवर्ड");

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cPassword = curPassword.getText().toString();
                String nPassword = newPassword.getText().toString();

                Boolean callAPi = true;

                if (curPassword.getText().toString().equals(newPassword.getText().toString())) {
                    newPassword.setError("Current and New Passwords sholuldn't be same");
                    newPassword.requestFocus();
                    callAPi = false;
                }

                if ((newPassword.getText().toString().length()) < 8) {
                    newPassword.setError("New Password should be atleast of 8 charactors");
                    newPassword.requestFocus();
                    callAPi = false;
                }

                if (newPassword.getText().toString().length() == 0) {
                    newPassword.setError("New password is required");
                    newPassword.requestFocus();
                    callAPi = false;
                }

                if ((curPassword.getText().toString().length()) < 8) {
                    curPassword.setError("Current Password should be atleast of 8 charactors");
                    curPassword.requestFocus();
                    callAPi = false;
                }

                if (curPassword.getText().toString().length() == 0) {
                    curPassword.setError("Current Password is required");
                    curPassword.requestFocus();
                    callAPi = false;

                }
                    if(callAPi){

                        Toast.makeText(PasswordChangeActivity.this, "Password changed", Toast.LENGTH_SHORT).show();
                    }


                }


            });

        }
    }

