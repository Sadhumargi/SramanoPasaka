package com.sramanopasaka.sipanionline.sadhumargi.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.model.Family;
import com.sramanopasaka.sipanionline.sadhumargi.model.Relations;

import java.util.ArrayList;

/**
 * Name    :   pranavjdev
 * Date   : 8/30/17
 * Email : pranavjaydev@gmail.com
 */

public class RelationAdapter  extends ArrayAdapter<Relations> {

    private static class ViewHolder {
        private TextView itemView;
    }

    public RelationAdapter(Context context, int textViewResourceId, ArrayList<Relations> items) {
        super(context, textViewResourceId, items);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Relations item = getItem(position);
        TextView label = new TextView(this.getContext());
        label.setTextColor(Color.BLACK);

        if (item!= null) {
            label.setText(String.format("%s", item.getRelation()));
        }


        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.list_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.itemView = (TextView) convertView.findViewById(R.id.textView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Relations item = getItem(position);
        if (item!= null) {
            // My layout has only one TextView
            // do whatever you want with your string and long
            viewHolder.itemView.setText(String.format("%s", item.getRelation()));
        }

        return convertView;
    }
}
