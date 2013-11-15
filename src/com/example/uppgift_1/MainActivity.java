package com.example.uppgift_1;



import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends FragmentActivity implements ListView.OnItemClickListener {

	NavigationDrawerHelper mNavigationDrawer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
		mNavigationDrawer = new NavigationDrawerHelper();
		mNavigationDrawer.init(this, this);
		
		AddTransactionFragment fragment = new AddTransactionFragment();
		
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.fragment_content, fragment);
		ft.commit();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState); 
		mNavigationDrawer.syncState();
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		mNavigationDrawer.handleOnPrepareOptionsMenu(menu);
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		mNavigationDrawer.handleOnOptionsItemSelected(item);
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		mNavigationDrawer.syncState();
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int i, long l) {
		
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		Fragment fragment;
		
		if(i == 0){
			fragment = new AddTransactionFragment();
			ft.replace(R.id.fragment_content, fragment);
			ft.commit();
			
		}else if(i == 1){
			fragment = new BudgetFragment();
			ft.replace(R.id.fragment_content, fragment);
			ft.commit();
		}else if(i == 2){
			fragment = new DetailsFragment();
			ft.replace(R.id.fragment_content, fragment);
			ft.commit();
		}else if(i == 3){
			fragment = new UserSettingsFragment();
			ft.replace(R.id.fragment_content, fragment);
			ft.commit();
		}
		
		mNavigationDrawer.handleSelect(i);
		
	}

}
