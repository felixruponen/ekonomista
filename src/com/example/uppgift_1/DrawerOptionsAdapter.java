package com.example.uppgift_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerOptionsAdapter extends BaseAdapter {

	private Context context;
	private DrawerOptionsItem[] data;
	
	public static LayoutInflater inflater = null;
	
	public DrawerOptionsAdapter(Context context, DrawerOptionsItem[] data){
		this.context = context;		
		this.data = data;
		
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
				
		if(convertView == null)
			convertView = inflater.inflate(R.layout.drawer_option_item, null);
		
		TextView text = (TextView) convertView.findViewById(R.id.drawer_options_item_text);
		ImageView img = (ImageView) convertView.findViewById(R.id.drawer_options_item_image);
		
		
		
		
		img.setImageDrawable(context.getResources().getDrawable(data[position].getImageId()));
		text.setText(data[position].getText());
		
		return convertView;
	}

	
}
