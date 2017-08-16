package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddAddressResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.BasicDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.UpdateBasicDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.BasicDetailsFragment;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.Address;
import com.sramanopasaka.sipanionline.sadhumargi.model.BasicDetailsData;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostalAddressActivity extends BaseActivity implements GUICallback {

    @Bind(R.id.postaladdresstool)
    Toolbar postaladdresstool;
    @Bind(R.id.address1)
    EditText address1;
    @Bind(R.id.address2)
    EditText address2;
    @Bind(R.id.post)
    EditText post;
    @Bind(R.id.district)
    EditText district;
    @Bind(R.id.city)
    EditText city;
    @Bind(R.id.pin)
    EditText pin;
    @Bind(R.id.state)
    EditText state;
    @Bind(R.id.country)
    EditText country;
    @Bind(R.id.addresstype)
    EditText addresstype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        ButterKnife.bind(this);

        setSupportActionBar(postaladdresstool);

        postaladdresstool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow_patasala);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>१८ वर्ष से कम उम्र के बच्चों का विवरण</font>"));


    }

    @OnClick(R.id.addAddress)
    public void addAddressListner() {
        //TODO validations

        showLoadingDialog();
        Address address = new Address(address1.getText().toString(), address2.getText().toString(), post.getText().toString(),
                district.getText().toString(), city.getText().toString(), pin.getText().toString(), state.getText().toString(),
                country.getText().toString(), addresstype.getText().toString());
        LoginModel loginResponse = OfflineData.getLoginData();
        if (loginResponse != null) {


            RequestProcessor requestProcessor = new RequestProcessor(PostalAddressActivity.this);
            requestProcessor.addAddress(loginResponse.getId(), loginResponse.getAppToken(), address);
        }
    }



    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
        hideLoadingDialog();
        if (guiResponse != null) {
            if (requestStatus.equals(RequestStatus.SUCCESS)) {
                    AddAddressResponse response = (AddAddressResponse) guiResponse;
                    if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {
                        if (!TextUtils.isEmpty(response.getMessage())) {
                            Toast.makeText(PostalAddressActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(PostalAddressActivity.this, "Address added successfully!", Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    }else{
                        if (!TextUtils.isEmpty(response.getMessage())) {
                            Toast.makeText(PostalAddressActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(PostalAddressActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }
            }
        }
    }
}
