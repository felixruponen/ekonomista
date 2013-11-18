package com.example.uppgift_1;

import java.util.ArrayList;

import android.R.color;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TransactionListAdapter extends BaseAdapter {

	Context context;
	ArrayList<TransactionListItem> data;
	LayoutInflater inflater;
	
	public TransactionListAdapter(Context context, ArrayList<TransactionListItem> data){
		this.context = context;
		this.data = data;
		
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	
	@Override
	public int getCount() {

		return data.size();
	}

	@Override
	public Object getItem(int position) {

		return data.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		if(convertView == null)
			convertView = inflater.inflate(R.layout.transaction_list_item, null);
		
		TextView text = (TextView) convertView.findViewById(R.id.transaction_item_title);
		TextView amount = (TextView) convertView.findViewById(R.id.transaction_item_amount);
		
		String type = data.get(position).getType();
		
		String prefix = "";
		
		if(type.equals(DBTools.TRANSACTION_TYPE_INCOME)){
			Log.i("List", "BLUE");
			prefix = "+";
			text.setTextColor(context.getResources().getColor(R.color.income_color));
			amount.setTextColor(context.getResources().getColor(R.color.income_color));
		}
		else if(type.equals(DBTools.TRANSACTION_TYPE_EXPENSE)){
			prefix = "-";
			text.setTextColor(context.getResources().getColor(R.color.expense_color));
			amount.setTextColor(context.getResources().getColor(R.color.expense_color));
		}
		
		text.setText(data.get(position).getTitle());
		amount.setText(prefix + " " + data.get(position).getAmount());
		
		return convertView;
	}

}
