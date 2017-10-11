package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static com.sramanopasaka.sipanionline.sadhumargi.ViharActivity.a1;

/**
 * Created by sipani001 on 30/6/17.
 */

public class PthsalaDetailsFragment extends Fragment {




    TextView empty_element;
    //RecyclerView recyclerview;
    ProgressDialog mProgressDialog;

    // private int ival = 0;
    private static int loadLimit = 99;
    JSONParser jParser1 = new JSONParser();
    JSONArray cast1 = null;
    JSONObject jsonobject;
    JSONArray jsonarray;
    public  int current_pages = 1;
    String FEED_URL="http://pathshala.sadhumargi.com/api/schools/list";
    ArrayList<PathsalaGetSetter> arraylist=new ArrayList<PathsalaGetSetter>();

    TextView tv_code;
    TextView tv_address;
    TextView tv_place;
    TextView tv_city;
    TextView tv_timing;
    TextView tv_totalstudent;
    TextView tv_totalTeacher;
    TextView tv_Name;
    TextView tv_incharge;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.activity_pthsala_details_fragment, container, false);
        tv_Name= (TextView) view.findViewById(R.id.txt_Name);
        tv_code= (TextView) view.findViewById(R.id.txt_patasla_code);
        tv_address= (TextView) view.findViewById(R.id.txt_patasla_address);
        tv_place= (TextView) view.findViewById(R.id.txt_patasla_place);
        tv_city= (TextView) view.findViewById(R.id.txt_patasla_City);
        tv_timing= (TextView) view.findViewById(R.id.txt_patasla_timing);
        tv_totalstudent= (TextView) view.findViewById(R.id.studentcount);
        tv_incharge= (TextView) view.findViewById(R.id.txt_patasla_incharge);
        tv_totalTeacher=(TextView) view.findViewById(R.id.txt__studentteachers);
        //tv_timing.setText("Monday to Friday open");

      new Remote().execute();
        return view;

    }




class Remote extends AsyncTask<Void, Void, Void> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if(mProgressDialog!=null)
        {
            mProgressDialog.dismiss();
            mProgressDialog=null;
        }

        if ( mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setTitle("Please wait...");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

       /* // Create a progressdialog
        // Set progressdialog title
        mProgressDialog.setTitle("Please wait");
        // Set progressdialog message
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        // Show progressdialog
        mProgressDialog.show();*/
    }

    @Override
    protected Void doInBackground(Void... voids) {

      /* Bundle is=getActivity().getIntent().getExtras();
        int a1= is.getInt("id");*/
       // final String s1=is.getString("dhan_name1");
try{



        /*Bundle is=getActivity().getIntent().getExtras();
        int a1= is.getInt("id");
        final String s1=is.getString("code");*/

        final String sign2=MD5(current_pages+""+""+""+"1a2ea8119addab5f196b6c8cf5baeaa9");


        Log.e("Signature","????????????"+sign2);

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("page", String.valueOf(current_pages)));
        nameValuePairs.add(new BasicNameValuePair("city", ""));
        nameValuePairs.add(new BasicNameValuePair("prof_query", ""));
        nameValuePairs.add(new BasicNameValuePair("edu_query", ""));
        nameValuePairs.add(new BasicNameValuePair("signature", sign2));
       nameValuePairs.add(new BasicNameValuePair("id",String.valueOf(a1)));


        Log.e("Signature","????????????"+sign2);
    //E/Signature: ????????????65e2dc9815be3ccf6bd3f942b3571bc9

        try {


            Bundle is=getActivity().getIntent().getExtras();
            int a1= is.getInt("id");

            Log.e("Patasala Id","//////"+a1);

            nameValuePairs.add(new BasicNameValuePair("id",String.valueOf(a1)));

            JSONObject json1 = new JSONObject(String.valueOf(jParser1.makeHttpRequest(FEED_URL, "POST", nameValuePairs)));



            if(json1!=null) {

                cast1 = json1.optJSONArray("data");
                for (int i = 0; i < cast1.length(); i++) {

                    json1 = cast1.getJSONObject(i);


                    final String id = json1.getString("id");

                    if (id.equals(String.valueOf(a1))) {
                        final String code = json1.getString("code");
                        final String address = json1.getString("address");
                        final String place = json1.getString("place");
                        final String city = json1.getString("city");
                        final String timings = json1.getString("timing");
                        final String students = json1.getString("strength");
                        final String incharge = json1.getString("in_charge_name");


                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                tv_code.setText(code);
                                tv_address.setText(address);
                                //tv_place.setText(place);
                                tv_city.setText(city);
                                tv_timing.setText(timings);
                                tv_totalstudent.setText(students);
                                tv_incharge.setText(incharge);

                            }
                        });




                    /*PathsalaGetSetter imgs = new PathsalaGetSetter();
                    imgs.setId(json1.getString("id"));
                    imgs.setCode(json1.getString("code"));
                    imgs.setCity(json1.getString("city"));
                    imgs.setPlace(json1.getString("place"));
                    imgs.setType(json1.getString("type"));
                    imgs.setTimings(json1.getString("timing"));
                    imgs.setStrength(json1.getString("strength"));
                    imgs.setAddress(json1.getString("address"));
                    imgs.setSangh(json1.getString("local_sangh_name"));
                    imgs.setIn_charge_name(json1.getString("in_charge_name"));
                    imgs.setIn_charge_number(json1.getString("in_charge_number"));
                    imgs.setIn_charge_email(json1.getString("in_charge_email"));
                    imgs.setGrade(json1.getString("grade"));

                    arraylist.add(imgs);*/

                    }
                }
            }
            else
            {

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

}catch (Exception e) {
    e.printStackTrace();
}
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        //Toast.makeText(Pathsalalist.this, "data received", Toast.LENGTH_SHORT).show();

        mProgressDialog.dismiss();
        //loadmore();
    }}


    public String MD5(String md5)
    {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

}

