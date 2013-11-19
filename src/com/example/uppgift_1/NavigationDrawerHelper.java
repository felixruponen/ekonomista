package com.example.uppgift_1;


import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;


public class NavigationDrawerHelper {
	DrawerLayout mDrawerLayout;
	ListView mDrawerListView;
	FrameLayout mDrawerContainer;
	private ActionBarDrawerToggle mDrawerToggle;
	
	public void init(Activity theActivity, ListView.OnItemClickListener listener){
		mDrawerLayout = (DrawerLayout) theActivity.findViewById(R.id.drawer_layout);
		mDrawerListView = (ListView) theActivity.findViewById(R.id.drawer_options_list);
			
		DrawerOptionsItem[] data = { 
				new DrawerOptionsItem(android.R.drawable.ic_menu_add, "Add transaction"), 
				new DrawerOptionsItem(android.R.drawable.ic_menu_agenda, "Budget Overview"), 
				new DrawerOptionsItem(android.R.drawable.ic_menu_info_details, "Budget Details"),
				new DrawerOptionsItem(android.R.drawable.ic_menu_manage, "User Settings")
		};
		
		DrawerOptionsAdapter navigationDrawerAdapter = new DrawerOptionsAdapter(theActivity, data);
		
		
		mDrawerListView.setAdapter(navigationDrawerAdapter);
		mDrawerListView.setOnItemClickListener(listener);
		
		
		mDrawerListView.setItemChecked(0, true);
		
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		
		setupActionBar(theActivity);
		
				
		
	}
	
	private void setupActionBar(Activity theActivity){
			
		final Activity activity = theActivity;
		ActionBar actionBar = theActivity.getActionBar();
			
		actionBar.setDisplayHomeAsUpEnabled(true);
		
			mDrawerToggle = new ActionBarDrawerToggle(theActivity, mDrawerLayout, R.drawable.ic_navigation_drawer, R.string.open_drawer_message, R.string.closed_drawer_message)
			{
				@Override
				public void onDrawerOpened(android.view.View drawerView) {
					activity.invalidateOptionsMenu();
					super.onDrawerOpened(drawerView);
					
				};
				
				@Override
				public void onDrawerClosed(android.view.View drawerView) {
					activity.invalidateOptionsMenu();
					super.onDrawerClosed(drawerView);
				};
				
			};
			
			
	} 
	
	public void handleSelect(int option){
			mDrawerListView.setItemChecked(option, true);
			mDrawerLayout.closeDrawer(mDrawerListView);
		
	}
	
	public void handleOnPrepareOptionsMenu(Menu menu){
		/*boolean itemVisible = ! mDrawerLayout.isDrawerOpen(mDrawerLayout);
		
		for(int index = 0; index < menu.size(); index++){
			MenuItem item = menu.getItem(index);
			item.setEnabled(itemVisible);
		}
		*/
	}
	
	public void handleOnOptionsItemSelected(MenuItem item){
		mDrawerToggle.onOptionsItemSelected(item);		
	}
	
	public void syncState(){
		mDrawerToggle.syncState();		
	}
	
	public void setSelection(int option){
		mDrawerListView.setItemChecked(option, true);		
	}
	

	
	
	
	
	
	
	
}

