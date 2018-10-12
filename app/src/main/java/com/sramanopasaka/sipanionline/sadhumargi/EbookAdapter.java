package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sramanopasaka.sipanionline.sadhumargi.model.Ebook;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class EbookAdapter extends RecyclerView.Adapter<EbookAdapter.ViewHolder>  {

   /* Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<EbookGetSetter> data;
    ImageLoader imageLoader;
    private List<EbookGetSetter> androidlist;

    Activity a;


    public EbookAdapter(Context context,
                        ArrayList<EbookGetSetter> arraylist) {
        this.context = context;
        androidlist = arraylist;
        imageLoader = new ImageLoader(context);
        this.data = new ArrayList<EbookGetSetter>();
        this.data.addAll(androidlist);

    }*/


    private ArrayList<Ebook> arraylist;
    Context context;

    ImageLoader imageLoader;

    public EbookAdapter(Context context, ArrayList<Ebook> arraylist) {

        this.context=context;
        this.arraylist=arraylist;
//        imageLoader = new ImageLoader(context);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_ebook_adapter, parent, false);
        ViewHolder view=new ViewHolder(view1);
        return view;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

       // Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/KrutiDev010 .TTF");
        final Ebook model = arraylist.get(position);

        holder.tv_date.setText(model.getDate());
        final String id = String.valueOf(model.getBook_id());

        String url2=model.getImg_link();
//        imageLoader.DisplayImage(url2, holder.image);

        if ((!url2.isEmpty())){
            Glide.with(context).load(url2).into(holder.image);
        }else {
            Glide.with(context).load(R.drawable.temp_img).into(holder.image);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final boolean isConnected=ConnectivityReceiver.isConnected();
                if(isConnected)
                {
                    String a1=holder.tv_date.getText().toString();
                    Intent i1 = new Intent(context, WebviewEbook.class);
                    i1.putExtra("Edition",id);
                    context.startActivity(i1);
                }
                else
                {
                    Snackbar snackbar1 = Snackbar.make(v, "Sorry! Not connected to internet", Snackbar.LENGTH_SHORT);
                    View sbView = snackbar1.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.RED);
                    snackbar1.show();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return arraylist == null ? 0 : arraylist.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    /*@Override
    public Filter getFilter() {
        return null;
    }*/


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_date;
        CircleImageView image;


     //   public ProgressBar progressBar;

        public ViewHolder(View view) {
            super(view);

            tv_date = (TextView)view.findViewById(R.id.ebook_txt_date);
            image=(CircleImageView)view.findViewById(R.id.ebook_image);
         //   progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);

            }


    }

    // Filter Class
   /* public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        list.clear();
        if (charText.length() == 0) {
            list.addAll(data);
        }
        else
        {
            for (EbookGetSetter wp : data)
            {
                if (wp.getDate().contains(charText))
                {
                    androidlist.add(wp);
                }
                else
                {
                    Toast.makeText(context,"Item's Not Found",Toast.LENGTH_SHORT).show();
                }
            }
        }
        notifyDataSetChanged();
    }*/

    }

