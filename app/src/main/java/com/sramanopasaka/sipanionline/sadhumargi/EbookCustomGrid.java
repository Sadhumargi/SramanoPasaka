package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sramanopasaka.sipanionline.sadhumargi.model.Ebook;

import java.util.ArrayList;

/**
 * Created by apple on 13/02/18.
 */

class EbookCustomGrid  extends BaseAdapter{
     ArrayList<Ebook> ebookArrayList;
    Context context;
    ImageLoader imageLoader;

    public EbookCustomGrid(Ebooks ebooks, ArrayList<Ebook> arraylist) {

        this.ebookArrayList = arraylist;
        this.context = ebooks;
        imageLoader = new ImageLoader(context);
    }

    @Override
    public int getCount() {
        return ebookArrayList.size();
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
    public View getView(int i, View view, final ViewGroup viewGroup) {

        View grid;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final Ebook model = ebookArrayList.get(i);

//        final ViewHolder holder =null;

        if (view == null) {

            grid = new View(context);

            grid = inflater.inflate(R.layout.activity_ebook_adapter, null);

            TextView txtDate = (TextView) grid.findViewById(R.id.ebook_txt_date);
            ImageView imageView = (ImageView)grid.findViewById(R.id.ebook_image);
            txtDate.setText(ebookArrayList.get(i).getDate());
            final String id = model.getBook_id();
            String url2=model.getImg_link();
            imageLoader.DisplayImage(url2, imageView );

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
//                  String a1= viewGroup.txtDate.getText().toString();
                    Intent i1 = new Intent(context, WebviewEbook.class);
                    i1.putExtra("Edition",model.getBook_id());
                    context.startActivity(i1);
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

    private class ViewHolder {
        ImageView imageView;
        TextView txtDate;
    }

}
