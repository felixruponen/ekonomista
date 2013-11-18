package com.example.uppgift_1;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BudgetFragment extends Fragment implements OnTransactionChanged {

	
	TransactionPagerAdapter mPagerAdapter;
	ViewPager mViewPager;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View theView = inflater.inflate(R.layout.budget_fragment, container, false);
		
		Fragment mListFragment = Fragment.instantiate(getActivity(), "com.example.uppgift_1.BudgetChildListFragment");
		Fragment mDetailsFragment = Fragment.instantiate(getActivity(), "com.example.uppgift_1.BudgetChildDetailsFragment");
		
		FragmentManager fm = getChildFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		
		ft.replace(R.id.list_fragment_container, mListFragment);
		//ft.replace(R.id.details_fragment_container, mDetailsFragment);
		ft.commit();
		
		DBTools db = new DBTools(getActivity());
		
		TextView budgetBalance = (TextView) theView.findViewById(R.id.budget_balance);
		
		double incomeTotal = Double.parseDouble(db.getIncomeTotal());
		double expenseTotal = Double.parseDouble(db.getExpenseTotal());
		
		budgetBalance.setText("Ditt konto har just nu: " + String.valueOf(incomeTotal - expenseTotal) + " kr");
		
		
		
		ArrayList<String> images = db.getAllTransactionImages();
		
		
		
		LoadThumbnailsAsync task = new LoadThumbnailsAsync(getActivity());
		task.execute(images);
		
		mPagerAdapter = new TransactionPagerAdapter(getChildFragmentManager(), getActivity());
		
		
		
		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) theView.findViewById(R.id.pager);
		mViewPager.setAdapter(mPagerAdapter);
		
		return theView;
	}

	@Override
	public void onTransactionSelectionChanged(int transactionId) {
		/*
		FragmentManager fm = getChildFragmentManager();
		BudgetChildDetailsFragment fragment = (BudgetChildDetailsFragment) fm.findFragmentById(R.id.details_fragment_container);
		
		//Toast t = Toast.makeText(getActivity(), transactionId, Toast.LENGTH_SHORT);
		//t.show();
		
		fragment.setTransaction(transactionId);
		*/
		
		mViewPager.setCurrentItem(transactionId);
	}

	


	

}
