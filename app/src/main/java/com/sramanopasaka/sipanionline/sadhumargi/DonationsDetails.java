package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DonationsDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;

import java.util.ArrayList;

public class DonationsDetails extends BaseActivity implements GUICallback {

    /*private static final String TAG_DONATE_ID = "data";
    JSONParser jParser1 = new JSONParser();
    JSONArray cast1 = null;*/
    Button submit;
 //  String url3= "http://shriabsjainsangh.sipanionline.com/sramanopasaka/phpfiles/donatedetails.php";

    Context context;

    ArrayList<com.sramanopasaka.sipanionline.sadhumargi.model.DonationsDetails> arraylist;

    TextView txt_name,txt_type_name,txt_details;
    EditText txt_amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donations_details);

        Toolbar toolbar = findViewById(R.id.donationdetails_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionbar = this.getSupportActionBar();
        actionbar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Uphaar</font>"));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow_patasala);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.statusbarcolor));
        }
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        submit=(Button)findViewById(R.id.btn_don) ;

       // txt_type_name=(EditText)findViewById(R.id.dhan_name1);
        txt_name=(TextView)findViewById(R.id.txt_don_name);
        txt_details=(TextView)findViewById(R.id.txt_don_details);
        txt_amount=(EditText) findViewById(R.id.txt_don_amount);

        //Font style has taken from directory assets.
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/KrutiDev010 .TTF");

        //Setting font size and style
        txt_details.setTypeface(type,Typeface.BOLD);
        txt_name.setTypeface(type,Typeface.BOLD);

        Bundle is=getIntent().getExtras();
        int a1= is.getInt("donate_id");
        final String s1=is.getString("dhan_name1");

      // new Remote().execute();
        RequestProcessor processor=new RequestProcessor(this);
        processor.getDonationsDetails(a1);
        showLoadingDialog();
    }

    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
        hideLoadingDialog();

        if(guiResponse!=null){

            if(requestStatus.equals(RequestStatus.SUCCESS)){

                DonationsDetailsResponse response= (DonationsDetailsResponse) guiResponse;
                if(response!=null){

                    if(response.getData()!=null && response.getData().size()>0){

                        arraylist=response.getData();

                        //   txt_type_name.setText(s1);
                        txt_name.setText(arraylist.get(0).getDonate_name());
                        txt_details.setText(arraylist.get(0).getDonate_details());
                        
//                        submit.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//
//                                Toast.makeText(context, "Please wait we are updating soon", Toast.LENGTH_SHORT).show();
////                                int s1= Integer.parseInt(txt_amount.getText().toString());
////                                Intent pay=new Intent(DonationsDetails.this,Payment.class);
////                                startActivity(pay);
//
//                            }
//                        });
                        makeTextViewResizable(txt_details, 3, "और देखो", true);

                    }else{
                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(context, "Network error", Toast.LENGTH_SHORT).show();
        }
    }

   /* final class Remote extends AsyncTask<Void, Void, Void> {
        ProgressDialog pg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pg = new ProgressDialog(DonationsDetails.this);
            pg.setIndeterminate(true);
            pg.setMessage("Please Wait While Loading....");
            //  deleteCache(FlipActivity.this);
            pg.show();
            pg.setCancelable(false);

        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Bundle is=getIntent().getExtras();
                int a1= is.getInt("donate_id");
                final String s1=is.getString("dhan_name1");

                Log.e("Donations Id","//////"+a1);
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("donate_id",String.valueOf(a1) ));

                final List<String> allNames1 = new ArrayList<String>();
                JSONObject json1 = new JSONObject(String.valueOf(jParser1.makeHttpRequest(url3, "GET", nameValuePairs)));
                if (json1 != null) {
                    try {

                        cast1 = json1.getJSONArray(TAG_DONATE_ID);
                        for (int i = 0; i < cast1.length(); i++) {

                            json1=cast1.getJSONObject(i);

                            final String id = json1.getString("donate_id");
                            final String donatename=json1.getString("donate_name");
                            final String donatedetails = json1.getString("donate_details");
                            final int donateamount=json1.getInt("donate_amount");

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                 //   txt_type_name.setText(s1);
                                    txt_name.setText(donatename);
                                    txt_details.setText(donatedetails);
                                    submit.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            int s1= Integer.parseInt(txt_amount.getText().toString());
                                            Intent pay=new Intent(DonationsDetails.this,Payment.class);
                                            startActivity(pay);

                                        }
                                    });
                                    makeTextViewResizable(txt_details, 3, "और देखो", true);

                                }
                            });
                        }

                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            pg.dismiss();
        }

    }*/

//To show min lines of info and hideing rest os lines by giving a dropdown option to read more lines.
    public void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean viewMore) {

        if (tv.getTag() == null) {
            tv.setTag(tv.getText());
        }
        ViewTreeObserver vto = tv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {

                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (maxLine == 0) {
                    int lineEndIndex = tv.getLayout().getLineEnd(0);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                    int lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else {
                    int lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, lineEndIndex, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                }
            }
        });

    }

    private SpannableStringBuilder addClickablePartTextViewResizable(final Spanned strSpanned, final TextView tv,
                                                                     final int maxLine, final String spanableText, final boolean viewMore) {
        String str = strSpanned.toString();
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (str.contains(spanableText)) {
            ssb.setSpan(new ClickableSpan() {

                @Override
                public void onClick(View widget) {

                    if (viewMore) {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, -1, "देखें कम", false);
                    } else {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, 100, "और देखो", true);
                    }

                }
            }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length(), 0);

        }
        return ssb;

    }
}
