package com.example.uppgift_1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


public class AddTransactionFragment extends Fragment implements Button.OnClickListener, RadioGroup.OnCheckedChangeListener{

	

	private static final int PICTURE_RESULT = 100;
	Spinner spnCategories;
	Button btnAdd;
	RadioGroup transaction_type_rgroup;
	EditText txtTitle, txtAmount;
	LinearLayout layout;
	String imageAbsoulutePath;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View theView = inflater.inflate(R.layout.new_expense_fragment, container, false);
		
		layout = (LinearLayout) theView.findViewById(R.id.layout_new_expense);
		
		spnCategories = (Spinner) theView.findViewById(R.id.spnCategory);
		
		//ArrayAdapter<SpinnerKeyValueObject> adapter = getSpinnerAdapter();
		
		//spnCategories.setAdapter(adapter);
		setSpinnerAdapter(DBTools.TRANSACTION_TYPE_INCOME);
		
		imageAbsoulutePath = "";
		
		
		btnAdd = (Button) theView.findViewById(R.id.btnAdd);
		btnAdd.setOnClickListener(this);
		
		txtTitle = (EditText) theView.findViewById(R.id.txtNamn);
		txtAmount = (EditText) theView.findViewById(R.id.txtAmount);
		
		transaction_type_rgroup = (RadioGroup) theView.findViewById(R.id.rbtn_transaction_type);
		transaction_type_rgroup.setOnCheckedChangeListener(this);
		
		return theView;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	
		
		if(requestCode == PICTURE_RESULT)
		{
			if(resultCode == Activity.RESULT_OK)
			{
			KeyValueObject item = (KeyValueObject)spnCategories.getSelectedItem();
		
			int id = item.getId();
			
			DBTools db = new DBTools(getActivity());
			
			String title = txtTitle.getText().toString();
			String amount = txtAmount.getText().toString();
			Date d = new Date();
			String s  = (String) DateFormat.format("yyyy-MM-dd HH:mm:ss", d.getTime());
			
			HashMap<String,String> queryValues = new HashMap<String, String>();
			
			queryValues.put(DBTools.TRANSACTION_NAME, title);
			queryValues.put(DBTools.TRANSACTION_AMOUNT, amount);
			queryValues.put(DBTools.TRANSACTION_DATE, s);
			queryValues.put(DBTools.TRANSACTION_IMAGE, imageAbsoulutePath);
			queryValues.put(DBTools.TRANSACTION_CATEGORY, String.valueOf(id));
			
			int radioBtnId = transaction_type_rgroup.getCheckedRadioButtonId();
			
			if(radioBtnId == R.id.rbtn_expense)
				queryValues.put(DBTools.TRANSACTION_TYPE, DBTools.TRANSACTION_TYPE_EXPENSE);
			else
				queryValues.put(DBTools.TRANSACTION_TYPE, DBTools.TRANSACTION_TYPE_INCOME);
			
			db.insertTransaction(queryValues);
			
			clearInputs();
			
			
			Toast t = Toast.makeText(getActivity(), "New transaction inserted!", Toast.LENGTH_SHORT);
			t.show();
			}
		}
		
		
	}
	
	private void setSpinnerAdapter(String type){
		
		DBTools db = new DBTools(getActivity());
		ArrayList<HashMap<String, String>> dbItems;
		
		if(type == DBTools.TRANSACTION_TYPE_EXPENSE)
		{
			dbItems = db.getExpenseCategories();
		}
		else
		{
			dbItems = db.getIncomeCategories();			
		}
			
		List<KeyValueObject> spinnerArray = new ArrayList<KeyValueObject>();
		
		
		for(HashMap<String, String> map : dbItems){
			spinnerArray.add(new KeyValueObject((Integer.parseInt((map.get(DBTools.CATEGORY_ID)))), map.get(DBTools.CATEGORY_TITLE)));
		}
		
		ArrayAdapter<KeyValueObject> adapter = new ArrayAdapter<KeyValueObject>(getActivity(), android.R.layout.simple_spinner_item, spinnerArray);
		
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		
		
	
		spnCategories.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {

		
		if(validateInput()){
			
			String title = txtTitle.getText().toString();			
			
			Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			
			File path = new File(Environment.getExternalStorageDirectory() + "/ekonomista_images");
			
			if(!path.exists()){
				path.mkdir();
			}
			
			imageAbsoulutePath = path.getAbsolutePath() + "/" + title + "_" + Calendar.getInstance().getTimeInMillis() + ".jpg";
			
			File image = new File(path.getAbsolutePath(),title + "_" + Calendar.getInstance().getTimeInMillis() + ".jpg");	
			
			camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(image));
			startActivityForResult(camera, PICTURE_RESULT);
			
			

		}else{
			
			
		}
	}
	
	private void clearInputs(){
		txtAmount.setText("");
		txtTitle.setText("");
		
		layout.requestFocus();
		
	}

	private boolean validateInput() {
		String title = txtTitle.getText().toString();
		String amount = txtAmount.getText().toString();
		
		if(!title.equals("") && !amount.equals("")){
			
			try{
				double attempt = Double.parseDouble(amount);
							
				return true;
			}catch(Exception e){
				return false;
			}
		}else{
			return false;
			
		}
				
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		
		switch(checkedId){
			case R.id.rbtn_expense:
					setSpinnerAdapter(DBTools.TRANSACTION_TYPE_EXPENSE);
				break;
			case R.id.rbtn_income:
					setSpinnerAdapter(DBTools.TRANSACTION_TYPE_INCOME);
				break;
		}
		
	}
	
	

	
}

