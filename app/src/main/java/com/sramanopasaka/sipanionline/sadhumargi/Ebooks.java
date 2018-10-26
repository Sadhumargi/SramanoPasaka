package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.cms.response.EbookResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.Ebook;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Ebooks extends BaseActivity implements ConnectivityReceiver.ConnectivityReceiverListener ,GUICallback {
    EditText ts;
   // ImageView cal_pc;
    int day;
    int month;
    int year;
    Context context;
    public static String url="http://shriabsjainsangh.sipanionline.com/sramanopasaka/phpfiles/Ebooks.php";
    JSONArray jsonarray = null;
    private ArrayList<Ebook> arraylist;
    ListView listview;
    JSONObject jsonobject;
    private RecyclerView recyclerView;
    EbookCustomGrid adapter;
    TextView days;
    Button reset;
    GridView gridView;
    View v;
    List<Ebook> list;

    /*  static final String[] Months = new String[] { "Jan", "Feb",
            "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep",
            "Oct", "Nov", "Dec" };
    static int monthno;
    static String yearno;
   static String totaldate;*/

    TextView emptyView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebooks);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolebook);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // cal_pc = (ImageView) findViewById(R.id.cal_pic);
        ts = (EditText) findViewById(R.id.tv_ebook_date);
        days=(TextView) findViewById(R.id.txt_days);
        emptyView=(TextView)findViewById(R.id.emptyElement);
        Calendar cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        final int mnt=month+1;
        ts.setText(day+"-"+mnt+"-"+year);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.statusbarcolor));
        }
        //  checkConnection();
       // new Remote().execute();

        gridView = findViewById(R.id.ebookGrid);

//        recyclerView = (RecyclerView)findViewById(R.id.ebooks_recycler_view);
//        recyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        reset=(Button)findViewById(R.id.reset);

        ActionBar actionbar = this.getSupportActionBar();
        actionbar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Sramanopasaka</font>"));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow_patasala);

        RequestProcessor processor=new RequestProcessor(Ebooks.this);
        processor.getEbookList();
        showLoadingDialog();

        //String dayOfWeek = getDayOfWeek(cal.get(Calendar.DAY_OF_WEEK));

      //  days.setText(dayOfWeek);

       /* ts.setFocusable(false);
        ts.setClickable(true);

        ts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datedialog();
            }
        });

        // Set months
        final Spinner spinMonths = (Spinner)findViewById(R.id.edt_spin_month);
        final Spinner spinYear = (Spinner)findViewById(R.id.spin_years);
        ArrayAdapter<String> adapterMonths = new ArrayAdapter<String>(this,
                R.layout.spinner_item, Months);
        spinMonths.setAdapter(adapterMonths);

        ArrayList<String> years = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1900; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }

        ArrayAdapter<String> adapteryear = new ArrayAdapter<String>(this,
                R.layout.spinner_item, years);
        spinYear.setAdapter(adapteryear);

        spinMonths.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 monthno=spinMonths.getSelectedItemPosition()+1;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                yearno=spinYear.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(monthno<=9)
                    {
                        totaldate= String.valueOf("0"+monthno)+"-"+String.valueOf(yearno);
                        Log.e("Success((()))",""+totaldate);
                    }
                    else
                    {
                        totaldate= String.valueOf(monthno)+"-"+String.valueOf(yearno);
                        Log.e("Success((()))",""+totaldate);
                    }

               // String text = ts.getText().toString();

               // query = query.toString().toLowerCase();

                final ArrayList<EbookGetSetter> filteredList = new ArrayList<EbookGetSetter>();

                for (int i = 0; i < arraylist.size(); i++) {

                    final String text1 = arraylist.get(i).getDate().toString();
                    final String text2 = arraylist.get(i).getDate().toString();
                    if (text1.contains(totaldate)) {

                        filteredList.add(arraylist.get(i));
                    }
                }

               recyclerView.setLayoutManager(new LinearLayoutManager(Ebooks.this));
                adapter = new EbookAdapter(Ebooks.this,filteredList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();  // data set changed
              //  emptyView.setVisibility(View.GONE);

            }
        });*/
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);

    }
    // Showing the status in Snackbar
    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = "Good! Connected to internet";
            color = Color.WHITE;
        } else {
            message = "Sorry! Not connected to internet";
            color = Color.RED;
        }

        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.txt_msg), message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }
   /* @Override
    protected void onStart() {
        super.onStart();

    }
    @Override
    protected void onResume() {
        super.onResume();
        // register connection status listener
        MyApplication.getInstance().setConnectivityListener(this);
    }*/

   /* @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }*/

    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
        hideLoadingDialog();

        try{

            if(guiResponse!=null){

                if(requestStatus.equals(RequestStatus.SUCCESS)){

                    //guiResponse has the response

                    EbookResponse ebookResponse = (EbookResponse)guiResponse;
                    if(ebookResponse!=null){

                        if(ebookResponse.getData()!=null && ebookResponse.getData().size()>0)
                        {
                            arraylist = ebookResponse.getData();
                            adapter = new EbookCustomGrid(Ebooks.this, arraylist);
                            gridView.setAdapter(adapter);
//                         recyclerView.setAdapter(adapter);
                        }else{
                            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();}

                    }else{
                        Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();}
                }else{
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();}
            }

        }catch (RuntimeException e){

            Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
        }

    }
   /* private String getDayOfWeek(int value) {
        String day = "";
        switch (value) {
            case 1:
                day = "Sunday";
                break;
            case 2:
                day = "Monday";
                break;
            case 3:
                day = "Tuesday";
                break;
            case 4:
                day = "Wednesday";
                break;
            case 5:
                day = "Thursday";
                break;
            case 6:
                day = "Friday";
                break;
            case 7:
                day = "Saturday";
                break;
        }
        return day;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    void datedialog()
    {
        final DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {

                ts.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
            }};

        final DatePickerDialog dpDialog=new DatePickerDialog(Ebooks.this, listener, year, month, day);

        dpDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        dpDialog.getDatePicker().clearFocus();
                        dpDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                        DatePicker datePicker = dpDialog
                                .getDatePicker();
                        listener.onDateSet(datePicker,
                                datePicker.getYear(),
                                datePicker.getMonth(),
                                datePicker.getDayOfMonth());

                        int mnt=datePicker.getMonth()+1;
                        int day=datePicker.getDayOfMonth();
                        int year=datePicker.getYear();

                        Log.e("day.....","day of month"+day);
                        Log.e("month.....","month of year"+mnt);
                        Log.e("year.....","year of year"+year);

                        String a1=String.format("%02d-%02d-%d", day,mnt,year );

                        Log.e("year.....","sample values****"+String.format("%02d-%02d-%d", day,mnt,year ));


                        ts.setText(a1);

                    }
                });

        dpDialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       // getMenuInflater().inflate(R.menu.menu_pathrika,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        boolean isConnected = ConnectivityReceiver.isConnected();
        if(item.getItemId()==R.id.refresh)
        {
            if(isConnected)
            {
                new Remote().execute();
            }
            else
            {
                showSnack(true);
            }

        }
        return super.onOptionsItemSelected(item);
    }*/




   /* class Remote extends AsyncTask<Void,Void,Void>
    {
        ProgressDialog pg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pg=new ProgressDialog(Ebooks.this);
            pg.setIndeterminate(true);
            pg.setMessage("Please Wait While Loading....");
            pg.show();
            pg.setCancelable(false);
        }

        @Override
        protected Void doInBackground(Void... pasrams) {

            // Create an array
            arraylist = new ArrayList<>();
            // Retrieve JSON Objects from the given URL address
            jsonobject = JSONfunctions
                    .getJSONfromURL(url);

            try {
                // Locate the array name in JSON
                jsonarray = jsonobject.getJSONArray("data");


                for (int i = 0; i < jsonarray.length(); i++) {

                    jsonobject = jsonarray.getJSONObject(i);

                    EbookGetSetter imgs=new EbookGetSetter();
                    imgs.setId(jsonobject.getString("book_id"));
                    imgs.setImglink(jsonobject.getString("img_link"));
                    imgs.setDate(jsonobject.getString("date"));

                  *//*  map.put("book_id", jsonobject.getString("book_id"));
                    map.put("img_link", jsonobject.getString("img_link"));
                    map.put("date", jsonobject.getString("date"));*//*

                    arraylist.add(imgs);
                }

            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Collections.sort(arraylist, new Comparator<EbookGetSetter>() {

                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                public int compare(EbookGetSetter lhs, EbookGetSetter rhs) {

                    try {
                        return df.parse(rhs.getDate()).compareTo(
                                df.parse(lhs.getDate()));

                    } catch (ParseException e) {
                        throw new IllegalArgumentException(e);
                    }
                }
            });

            adapter = new EbookAdapter(Ebooks.this, arraylist);
            // Set the adapter to the ListView
            recyclerView.setAdapter(adapter);
            pg.dismiss();

        }
    }
*/
}
