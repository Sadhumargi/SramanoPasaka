package com.sramanopasaka.sipanionline.sadhumargi;

import android.support.v7.app.AppCompatActivity;

public class BooksPages extends AppCompatActivity {

   /* private static final float MIN_SCALE = 0.75f;
    private static final float MIN_ALPHA = 0.75f;
    public static String url3="http://shriabsjainsangh.sipanionline.com/sramanopasaka/phpfiles/sample.php";
    private static final String TAG_BOOK_PAGE="pagejson";
    private static final String TAG_JSON1 = "data1";
    private static final String TAG_PAGE_NAME="pagejson0";
    private static final String TAG_PAGE_NAME1="pagejson1";
    static JSONParser jParser1 = new JSONParser();

    private FlipperAdapter adapter;
    static private ArrayList<String> stringArrayList;
    static  JSONArray cast1=null;
    static  String a1="";

    SectionsPagerAdapter mSectionsPagerAdapter;
    // The ViewPager that will host the section contents.
    ViewPager mViewPager;

    String text;
    JSONArray jArray;
    ArrayList<String> Questionarray = new ArrayList<String>();
    ArrayList<String> Answerarray = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.style.activity_books_pages);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent in=getIntent();
         a1=in.getStringExtra("book_name");
        Log.e("Success***","//////"+a1);

        VerticalViewPager verticalViewPager = (VerticalViewPager) findViewById(R.id.verticalviewpager);

        verticalViewPager.setAdapter(new DummyAdapter(getSupportFragmentManager()));
        verticalViewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.pagemargin));
        verticalViewPager.setPageMarginDrawable(new ColorDrawable(getResources().getColor(android.R.color.holo_green_dark)));

        verticalViewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View view, float position) {
                int pageWidth = view.getWidth();
                int pageHeight = view.getHeight();

                if (position < -1) { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    view.setAlpha(0);

                } else if (position <= 1) { // [-1,1]
                    // Modify the default slide transition to shrink the page as well
                    float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                    float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                    float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                    if (position < 0) {
                        view.setTranslationY(vertMargin - horzMargin / 2);
                    } else {
                        view.setTranslationY(-vertMargin + horzMargin / 2);
                    }

                    // Scale the page down (between MIN_SCALE and 1)
                    view.setScaleX(scaleFactor);
                    view.setScaleY(scaleFactor);

                    // Fade the page relative to its size.
                    view.setAlpha(MIN_ALPHA +
                            (scaleFactor - MIN_SCALE) /
                                    (1 - MIN_SCALE) * (1 - MIN_ALPHA));

                } else { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    view.setAlpha(0);
                }
            }
        });

        parseJason(text);
        // ===============page viewer ==========================
        mSectionsPagerAdapter = new SectionsPagerAdapter(this,
                getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    *//*public class DummyAdapter extends FragmentPagerAdapter {

        public DummyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return "PAGE 1";
                case 1:
                    return "PAGE 2";
                case 2:
                    return "PAGE 3";
            }
            return null;
        }

    }*//*

    // ============== parse jason ======================
    private void parseJason(String text) {
        String parser = text;
        try {
            jArray = new JSONArray(text);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                Questionarray.add(json_data.getString("ques"));
                Answerarray.add(json_data.getString("ans"));
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    // ===================== adapter =======================

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        Context c;
        public SectionsPagerAdapter(Context c, FragmentManager fm) {
            super(fm);
            this.c = c;
        }
        @Override
        public Fragment getItem(int i) {

            Fragment fragment = null;
            String ques = Questionarray.get(i);
            String ans = Answerarray.get(i);

            fragment = new Page1(c, ques, ans);//pass value to be displayed in inflated view
            return fragment;
        }
        @Override
        public int getCount() {
            return Questionarray.size();//get number of pages to be displayed
        }
        @Override
        public CharSequence getPageTitle(int position) {
            String s = "Page  "+position;//setting the title
            return s;
        }
    }




    *//**
     * A placeholder fragment containing a simple view.
     *//*
    public static  class PlaceholderFragment extends Fragment {
        *//**
         * The fragment argument representing the section number for this
         * fragment.
         *//*
        private static final String ARG_SECTION_NUMBER = "section_number";

        *//**
         * Returns a new instance of this fragment for the given section
         * number.
         *//*
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.style.fragment_layout, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.textview);
            textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            TextView txt=(TextView) rootView.findViewById(R.id.values);

            try
            {
                Log.e("Success***","oncreateview(((())))"+a1);
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("book_name", a1));

                final List<String> allNames1 = new ArrayList<String>();
                JSONObject json1 =new JSONObject(String.valueOf(jParser1.makeHttpRequest(url3,"GET",nameValuePairs))) ;
                if (json1 != null) {
                    try {
                        cast1= json1.getJSONArray(TAG_PAGE_NAME);
                        for (int i = 0; i < cast1.length(); i++) {
                            JSONObject js=cast1.getJSONObject(0);
                            final String bookname = js.getString("pagejson0");
                            TextUtils.htmlEncode(bookname);
                            Log.e("Hindi Conversion****","Hindi"+TextUtils.htmlEncode(bookname));
                            allNames1.add(TextUtils.htmlEncode(bookname));
                            txt.setText(TextUtils.htmlEncode(String.valueOf(allNames1)));

                        }

                        stringArrayList = new ArrayList<>();
                        stringArrayList.add(String.valueOf(allNames1));
                        adapter = new FlipperAdapter(BooksPages.this, stringArrayList);
                        flipViewController.setAdapter(adapter);

                    } catch (JSONException e1) {
                    }
                }
            }
            catch (Exception ed)
            {

            }

            return rootView;
        }
    }

    final class Remote extends AsyncTask<Void,Void,Void>
    {
        ProgressDialog pg=null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pg=new ProgressDialog(BooksPages.this);
            pg.setIndeterminate(true);
            pg.setMessage("Please Wait While Loading....");
            pg.show();
            pg.setCancelable(false);

        }

        @Override
        protected Void doInBackground(Void... params) {

            try
            {
                Intent in=getIntent();
                String a1=in.getStringExtra("book_name");
                Log.e("Success***","//////"+a1);
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("book_name", a1));

                final List<String> allNames1 = new ArrayList<String>();
                JSONObject json1 =new JSONObject(String.valueOf(jParser1.makeHttpRequest(url3,"GET",nameValuePairs))) ;
                if (json1 != null) {
                    try {
                        cast1= json1.getJSONArray(TAG_PAGE_NAME);
                        for (int i = 0; i < cast1.length(); i++) {
                            final String bookname = cast1.getString(i);
                            TextUtils.htmlEncode(bookname);
                            Log.e("Hindi Conversion****","Hindi"+TextUtils.htmlEncode(bookname));
                            allNames1.add(TextUtils.htmlEncode(bookname));

                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                stringArrayList = new ArrayList<>();
                                stringArrayList.add(String.valueOf(allNames1));
                               *//* adapter = new FlipperAdapter(BooksPages.this, stringArrayList);
                                flipViewController.setAdapter(adapter);*//*



                            }
                        });

                    } catch (JSONException e1) {
                    }
                }
            }
            catch (Exception e)
            {
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

}
