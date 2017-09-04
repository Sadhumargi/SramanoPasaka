package com.sramanopasaka.sipanionline.sadhumargi.helpers;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.R;

public class CustomToast {
 
	// Custom Toast Method
    public void showErrorToast(Context context, View view, String error) {
 
		// Layout Inflater for inflating custom view
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		// inflate the layout over view
		View layout = inflater.inflate(R.layout.custom_toast,
				(ViewGroup) view.findViewById(R.id.toast_root));


 
		// Get TextView id and set error
		TextView text = (TextView) layout.findViewById(R.id.toast_error);
		text.setText(error);
		text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.toast_error, 0, 0, 0);
 
		Toast toast = new Toast(context);// Get Toast Context
		toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);// Set
																		// Toast
																		// gravity
																		// and
																		// Fill
																		// Horizoontal
 
		toast.setDuration(Toast.LENGTH_SHORT);// Set Duration
		toast.setView(layout); // Set Custom View over toast
 
		toast.show();// Finally show toast
	}

	public void showInformationToast(Context context, View view, String info) {

		// Layout Inflater for inflating custom view
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// inflate the layout over view
		View layout = inflater.inflate(R.layout.custom_toast,
				(ViewGroup) view.findViewById(R.id.toast_root));

		// Get TextView id and set error
		TextView text = (TextView) layout.findViewById(R.id.toast_error);
		text.setText(info);
		text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.toast_error, 0, 0, 0);

		Toast toast = new Toast(context);// Get Toast Context
		toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);// Set
		// Toast
		// gravity
		// and
		// Fill
		// Horizoontal

		toast.setDuration(Toast.LENGTH_SHORT);// Set Duration
		toast.setView(layout); // Set Custom View over toast

		toast.show();// Finally show toast
	}
 
}