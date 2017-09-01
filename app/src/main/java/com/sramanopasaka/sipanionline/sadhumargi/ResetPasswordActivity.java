package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.vision.text.Text;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;

public class ResetPasswordActivity extends BaseActivity implements GUICallback {

    Toolbar toolbar;

    EditText edtFirstname;
    EditText edtLastname;
    EditText edtMobileno;
    EditText edtEmailid;
    Button btnClose;
    Button btnRecover;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        toolbar= (Toolbar) findViewById(R.id.tb_resetpass);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        android.support.v7.app.ActionBar actionBar=this.getSupportActionBar();
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow_patasala);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>Recover Login Details</font>"));

        edtFirstname = (EditText) findViewById(R.id.edt_Firstname);
        edtLastname = (EditText) findViewById(R.id.edt_Lastname);
        edtMobileno = (EditText) findViewById(R.id.edt_mobileno);
        edtEmailid = (EditText) findViewById(R.id.edt_email);
        btnClose = (Button) findViewById(R.id.btn_Close);
        btnRecover = (Button) findViewById(R.id.btn_Recover);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in=new Intent(ResetPasswordActivity.this,SigninActivity.class);
                startActivity(in);
            }
        });

       btnRecover.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Boolean callAPi = true;

               if(TextUtils.isEmpty(edtLastname.getText().toString())){

                   edtLastname.setError("Last Name is required");
                   edtLastname.requestFocus();
                   callAPi = false;
               }

               if(TextUtils.isEmpty(edtFirstname.getText().toString())){

                   edtFirstname.setError("First Name is required");
                   edtFirstname.requestFocus();
                   callAPi = false;
               }

               if((!TextUtils.isEmpty(edtLastname.getText().toString()))&&(!TextUtils.isEmpty(edtFirstname.getText().toString()))){

                   if(!TextUtils.isEmpty(edtMobileno.getText().toString())||(!TextUtils.isEmpty(edtEmailid.getText().toString()))){
                       callAPi = true;
                   }else {

                       Toast.makeText(ResetPasswordActivity.this, "\"Please enter either email address or mobile phone number to retrieve login details!\"",
                               Toast.LENGTH_SHORT).show();
                   }

               }


               if(callAPi){
                   ResetPassword();
               }
           }
       });

    }
    private void ResetPassword() {

        showLoadingDialog();
            RequestProcessor requestProcessor = new RequestProcessor(ResetPasswordActivity.this);
            requestProcessor.passwordRecovery(edtEmailid.getText().toString(),edtMobileno.getText().toString(),
                    edtFirstname.getText().toString(), edtLastname.getText().toString());

    }


    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
hideLoadingDialog();
        if (guiResponse != null) {
            if (requestStatus.equals(RequestStatus.SUCCESS)) {
                if (guiResponse instanceof PasswordRecoverResponse) {
                    PasswordRecoverResponse response = (PasswordRecoverResponse) guiResponse;
                    if (response != null)
                        if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {
                            

                            if (response.getEmail()) {

                                Toast.makeText(this,"Account information sent to your registered email id!", Toast.LENGTH_SHORT).show();
                            }else if(response.getMobile()){

                                Toast.makeText(this, "\"Account information sent to your registered mobile via SMS!\"", Toast.LENGTH_SHORT).show();

                            }


                        }
                }
            }
        }

    }
}
