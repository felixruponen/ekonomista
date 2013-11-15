package com.example.uppgift_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BudgetChildListFragment extends ListFragment{
	
	OnTransactionChanged listener;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		
	}

	@Override
	public void onListItemClick(ListView l, View view, int position, long id) {
		listener = (OnTransactionChanged) getParentFragment();
		KeyValueObject item = (KeyValueObject)l.getItemAtPosition(position);
		
		Log.d("TRANSACTION INDEX: ", String.valueOf(item.getId()));
		
		listener.onTransactionSelectionChanged(position);
			
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		DBTools db = new DBTools(getActivity());
		
		ArrayList<HashMap<String, String>> transactions = db.getTransactions();
		List<KeyValueObject> transactionList = new ArrayList<KeyValueObject>();
		
		for(HashMap<String, String> item : transactions){
				transactionList.add(new KeyValueObject(Integer.parseInt(item.get(DBTools.TRANSACTION_ID)), item.get(DBTools.TRANSACTION_NAME)));		
		}
		
				
		ArrayAdapter<KeyValueObject> titleAdapter = new ArrayAdapter<KeyValueObject>(getActivity(), 
				android.R.layout.simple_list_item_1, transactionList);
		
		setListAdapter(titleAdapter);
		
		
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		
		
	}
		
}
