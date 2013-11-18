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
		TransactionListItem item = (TransactionListItem)l.getItemAtPosition(position);
		
		Log.d("TRANSACTION INDEX: ", String.valueOf(item.getId()));
		
		listener.onTransactionSelectionChanged(position);
			
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		DBTools db = new DBTools(getActivity());
		
		ArrayList<HashMap<String, String>> transactions = db.getTransactions();
		ArrayList<TransactionListItem> transactionList = new ArrayList<TransactionListItem>();
		
		for(HashMap<String, String> item : transactions){
				transactionList.add(new TransactionListItem(Integer.parseInt(item.get(DBTools.TRANSACTION_ID)), item.get(DBTools.TRANSACTION_NAME), item.get(DBTools.TRANSACTION_AMOUNT), item.get(DBTools.TRANSACTION_TYPE)));		
		}
		
		TransactionListAdapter adapter = new TransactionListAdapter(getActivity().getApplicationContext(), transactionList);	
		
		
		setListAdapter(adapter);
		
		
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		
		
	}
		
}
