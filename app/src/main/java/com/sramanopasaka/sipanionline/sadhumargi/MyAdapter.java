package com.sramanopasaka.sipanionline.sadhumargi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.sramanopasaka.sipanionline.sadhumargi.model.*;
import com.sramanopasaka.sipanionline.sadhumargi.model.Donations;
import java.util.ArrayList;

/**
 * Created by apple on 01/02/18.
 */

public class MyAdapter extends ArrayAdapter {

    Context context;
    ImageLoader imageLoader;

    ArrayList<Donations> nameList = new ArrayList<com.sramanopasaka.sipanionline.sadhumargi.model.Donations>();
    int[] ImageID;

    public MyAdapter(Context context, int grid_item_layout, ArrayList<Donations> arrayList, int[] imageArray) {
        super(context, grid_item_layout, arrayList);

        this.context = context;
        this.nameList = arrayList;
        this.ImageID = imageArray;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

//        View v = convertView;
//        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        v = inflater.inflate(R.layout.activity_donation_adapter, null);

        Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/KrutiDev010 .TTF");

//        TextView textView = (TextView) convertView.findViewById(R.id.txt_donate_name);
//        textView.setText(nameList.get(position).getTypes_donations());
//        textView.setTypeface(type);
//        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
//        imageView.setImageResource((Integer) daanImages.get(position));

         /*private view holder class*/

        ViewHolder holder = new ViewHolder();

        LayoutInflater mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.activity_donation_adapter, null);
            holder = new ViewHolder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.txt_donate_name);
            holder.imageView = (ImageView) convertView.findViewById(R.id.donate_image);
            convertView.setTag(holder);
        } else

            holder = (ViewHolder) convertView.getTag();
            holder.txtTitle.setText(nameList.get(position).getTypes_donations());
            holder.txtTitle.setTypeface(type);
            holder.imageView.setImageResource(ImageID[position]);

        Button btnDonate = convertView.findViewById(R.id.btndonate);

        btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int a1 = Integer.parseInt(nameList.get(position).getDonate_id());
                Intent i1 = new Intent(getContext(), DonationsDetails.class);
                Bundle b = new Bundle();
                b.putInt("donate_id",a1);
                b.putString("dhan_name1",nameList.get(position).getTypes_donations());
                i1.putExtras(b);
                getContext().startActivity(i1);
            }
        });
        return convertView;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

}
