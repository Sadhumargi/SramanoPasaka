package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.listener.TabselectionListner;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.Inflater;

import static android.R.attr.password;
import static com.sramanopasaka.sipanionline.sadhumargi.R.string.Password;

public class SignInFragment extends Fragment {


    EditText edttxtEmail, edttxtPassword;
    TextView txtFrgtpass;
    Button btnLogin, btnSignup;
    private TabselectionListner tabselectionListner = null;
    ProgressDialog pg;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_sign_in_fragment, container, false);

        edttxtEmail = (EditText) view.findViewById(R.id.editTex_email);
        edttxtPassword = (EditText) view.findViewById(R.id.editText_password);

        txtFrgtpass = (TextView) view.findViewById(R.id.tv_frgtpass);

        btnLogin = (Button) view.findViewById(R.id.button_login);
        btnSignup = (Button) view.findViewById(R.id.button_create_profile);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = edttxtEmail.getText().toString();
                String password = edttxtPassword.getText().toString();



                if (username.isEmpty()||password.isEmpty()) {

                    Toast.makeText(getActivity(), "Email id / Password shouldn't be blank", Toast.LENGTH_LONG).show();

                }else if  ((username.length() >= 8 && username.length() <= 100)) {

                     if ((password.length() >= 5 && password.length() <= 12)) {

                        if ((isValidMail(username)) || ((isValidMobile(username)) && username.length() == 10)) {

                            new LoginAsyntask().execute(username,password);


                        } else {

                            Toast.makeText(getActivity(), "Not a valid email id / mobile number", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getActivity(), "Password should contain 5 - 12 characters ", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getActivity(), "Not a valid email id / mobile number", Toast.LENGTH_SHORT).show();
                }



            }
boolean callNxt =true;

        });

        txtFrgtpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Reset password", Toast.LENGTH_SHORT).show();
            }
        });


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tabselectionListner!=null)
                    tabselectionListner.onSelectTab(1);



            }
        });



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tabselectionListner = (TabselectionListner) getActivity();
    }

    public boolean isValidMail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    //Validates
    public boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }


    private class LoginAsyntask extends AsyncTask<String, Void, Void>


    {
        @Override
        protected Void doInBackground(String... params) {


            /*try {

               int success;

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("password", password));
                Log.d("request!", "starting");
                JSONObject json = JSONfunctions.getJSONfromURL( LOGIN_URL, "POST", params);
                // checking log for json response
                // Log.d("Login attempt", json.toString());
                // success tag for json
                success = json.getInt(TAG_SUCCESS);
                if (success == 1)
                { Log.d("Successfully Login!", json.toString());
                 Intent ii = new Intent(getActivity(),Profile.class);
                // this finish() method is used to tell android os that we are done with current //activity now! Moving to other activity
                startActivity(ii);
                    getActivity().finish();
                    return json.getString(TAG_MESSAGE);
                }
                else{ return json.getString(TAG_MESSAGE);
                }
            } catch (JSONException e) {

                e.printStackTrace();

            }*/


            Intent i = new Intent(getActivity(), Profile.class);
            startActivity(i);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            String username = edttxtEmail.getText().toString();
            String password = edttxtPassword.getText().toString();

            pg = new ProgressDialog(getActivity());
            pg.setTitle("Please wait");
            pg.setMessage("Loging in");
            pg.setCancelable(false);
            pg.setIndeterminate(false);
            pg.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            pg.dismiss();
        }


    }


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_fragment)    }*/
}
