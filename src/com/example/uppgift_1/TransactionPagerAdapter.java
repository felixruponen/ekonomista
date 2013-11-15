package com.example.uppgift_1;

import java.util.ArrayList;
import java.util.HashMap;



import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.util.Log;
import android.view.View;

public class TransactionPagerAdapter extends FragmentPagerAdapter {

	ArrayList<HashMap<String, String>> mTransactions;
	
	public TransactionPagerAdapter(FragmentManager fm, Context context) {
		super(fm);
		
		DBTools db = new DBTools(context);
		
		mTransactions = db.getAllTransactionsWithCategory();		
	}

	@Override
	public int getCount() {
		
		int count = mTransactions.size();
		
		if(count == -1){
			try {
				throw new Exception("SQLite Error");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return count;
	}

	@Override
	public Fragment getItem(int position) {
		
		HashMap<String, String> courseMap = mTransactions.get(position);
		
		Bundle arguments = new Bundle();
		arguments.putString(BudgetChildDetailsFragment.TRANSACTION_TITLE, courseMap.get(DBTools.TRANSACTION_NAME));
		arguments.putString(BudgetChildDetailsFragment.TRANSACTION_DATE, courseMap.get(DBTools.TRANSACTION_DATE));
		arguments.putString(BudgetChildDetailsFragment.TRANSACTION_TYPE, courseMap.get(DBTools.TRANSACTION_TYPE));
		arguments.putString(BudgetChildDetailsFragment.TRANSACTION_CATEGORY, courseMap.get(DBTools.TRANSACTION_CATEGORY));
		
		String image = courseMap.get(DBTools.TRANSACTION_IMAGE);
		
		if(image != null)
			arguments.putString(BudgetChildDetailsFragment.TRANSACTION_IMAGE, image);
		
		arguments.putString(BudgetChildDetailsFragment.TRANSACTION_AMOUNT, courseMap.get(DBTools.TRANSACTION_AMOUNT));

		
		
		BudgetChildDetailsFragment fragment = new BudgetChildDetailsFragment();
		fragment.setArguments(arguments);
		
		return fragment;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		
		String title = mTransactions.get(position).get(DBTools.TRANSACTION_NAME);
		
		return title;
	}
	
}
