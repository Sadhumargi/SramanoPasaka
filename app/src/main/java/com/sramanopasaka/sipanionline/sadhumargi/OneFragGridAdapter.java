package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sramanopasaka.sipanionline.sadhumargi.model.SahityaFragmentOne;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by apple on 15/02/18.
 */

class OneFragGridAdapter extends BaseAdapter {

    ArrayList<SahityaFragmentOne> sahityArrayList;
    Context context;
    ImageLoader imageLoader;

    public OneFragGridAdapter(FragmentActivity activity, ArrayList<SahityaFragmentOne> arrayList) {

        this.context= activity;
        this.sahityArrayList = arrayList;
        imageLoader = new ImageLoader(context);
    }

    @Override
    public int getCount() {
        return sahityArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/KrutiDev010 .TTF");

        View grid;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final SahityaFragmentOne model = sahityArrayList.get(i);

//        final ViewHolder holder =null;

        if (view == null) {

            grid = new View(context);

            grid = inflater.inflate(R.layout.onefrag_adapter_layout, null);

            TextView txtTitle = (TextView) grid.findViewById(R.id.onefrag_txt_date);
            txtTitle.setText(sahityArrayList.get(i).getTitle());
            txtTitle.setTypeface(type);

            ImageView imageView = (ImageView)grid.findViewById(R.id.onefrag_image);
            Glide.with(context).load(model.getImg_link()).into(imageView);

            final String id = model.getSang_book_id();
//            imageLoader.DisplayImage(url2, imageView );

        } else {
            grid = (View) view;
        }


        final View finalGrid = grid;

        grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final boolean isConnected=ConnectivityReceiver.isConnected();

                if(isConnected)
                {
                    String encode=sahityArrayList.get(i).getSang_book_id();
                    try {
                        String query = URLEncoder.encode(encode, "utf-8");
                        Log.e("Encode values","Success///"+query);
                        String a1=sahityArrayList.get(i).getDate();
                        Intent i1 = new Intent(context, WebviewSahitya.class);
                        i1.putExtra("BookTitle",encode);
                        i1.putExtra("BookType","S");
                        context.startActivity(i1);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Snackbar snackbar1 = Snackbar.make(view, "Sorry! Not connected to internet", Snackbar.LENGTH_SHORT);
                    View sbView = snackbar1.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.RED);
                    snackbar1.show();
                }
            }
        });
        return grid;
    }
}
