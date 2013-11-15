package com.example.uppgift_1;

import java.io.File;
import java.net.URI;
import java.util.HashMap;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Thumbnails;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class BudgetChildDetailsFragment extends Fragment {


	public static final String TRANSACTION_TITLE = "title";
	public static final String TRANSACTION_DATE = "date";
	public static final String TRANSACTION_AMOUNT = "amount";
	public static final String TRANSACTION_TYPE = "type";
	public static final String TRANSACTION_CATEGORY = "category";
	public static final String TRANSACTION_IMAGE = "image";
	private static final int THUMBNAIL_SIZE = 96;
	
	TextView txtTitle, txtDate, txtType, txtAmount, txtCategory;
	HashMap<String, String> values;
	
	ImageView imgContainer;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View theView = inflater.inflate(R.layout.budget_details_fragment, container, false);
		
		txtTitle = (TextView) theView.findViewById(R.id.txtTitle);
		txtDate = (TextView) theView.findViewById(R.id.txtDate);
		txtType = (TextView) theView.findViewById(R.id.txtType);
		txtAmount = (TextView) theView.findViewById(R.id.txtAmount);
		txtCategory = (TextView) theView.findViewById(R.id.txtCategory);
		imgContainer = (ImageView) theView.findViewById(R.id.imgContainer);
		
		Bundle arguments = getArguments();
		
		if(arguments != null){
			
			Log.d("lnawnl", "wafawf");
			
			values = new HashMap<String, String>();
			
			values.put(TRANSACTION_TITLE, arguments.getString(TRANSACTION_TITLE));
			values.put(TRANSACTION_DATE, arguments.getString(TRANSACTION_DATE));
			values.put(TRANSACTION_CATEGORY, arguments.getString(TRANSACTION_CATEGORY));
			values.put(TRANSACTION_TYPE, arguments.getString(TRANSACTION_TYPE));
			values.put(TRANSACTION_AMOUNT, arguments.getString(TRANSACTION_AMOUNT));
						
			if(arguments.containsKey(TRANSACTION_IMAGE))
				values.put(TRANSACTION_IMAGE, arguments.getString(TRANSACTION_IMAGE));
			
			
			displayValues();
			
		}
		
		
		
		
		
		return theView;
	}
	
	
	private void displayValues() {
				
		txtTitle.setText(getActivity().getResources().getString(R.string.details_title) + values.get(TRANSACTION_TITLE));
		txtDate.setText(getActivity().getResources().getString(R.string.details_date) + values.get(TRANSACTION_DATE));
		txtType.setText(getActivity().getResources().getString(R.string.details_type) + values.get(TRANSACTION_TYPE));
		txtAmount.setText(getActivity().getResources().getString(R.string.details_amount) + values.get(TRANSACTION_AMOUNT));
		txtCategory.setText(getActivity().getResources().getString(R.string.details_category) + values.get(TRANSACTION_CATEGORY));

		if(values.containsKey(TRANSACTION_IMAGE)){
			String[] temp = values.get(TRANSACTION_IMAGE).split("\\.");
			
			String thumb = temp[0] + "_thumb" + "." + temp[1];
			
			imgContainer.setImageDrawable(Drawable.createFromPath(thumb));
		}
		
	}

	public void setTransaction(int transactionId){
		
		DBTools db = new DBTools(getActivity());
		
		HashMap<String,String> transactionInfo = db.getTransactionInfo(String.valueOf(transactionId));
		
		txtTitle.setText(getActivity().getResources().getString(R.string.details_title) + transactionInfo.get(DBTools.TRANSACTION_NAME));
		txtDate.setText(getActivity().getResources().getString(R.string.details_date) + transactionInfo.get(DBTools.TRANSACTION_DATE));
		txtType.setText(getActivity().getResources().getString(R.string.details_type) + transactionInfo.get(DBTools.TRANSACTION_TYPE));
		txtAmount.setText(getActivity().getResources().getString(R.string.details_amount) + transactionInfo.get(DBTools.TRANSACTION_AMOUNT));
		txtCategory.setText(getActivity().getResources().getString(R.string.details_category) + transactionInfo.get(DBTools.TRANSACTION_CATEGORY));
		
	}
	

	


}
