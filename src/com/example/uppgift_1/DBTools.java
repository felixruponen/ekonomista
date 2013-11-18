package com.example.uppgift_1;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBTools extends SQLiteOpenHelper {
		
	// Column names!
	public static final String TRANSACTION_ID = "transaction_id";
	public static final String TRANSACTION_NAME = "transaction_name";
	public static final String TRANSACTION_AMOUNT = "transaction_amount";
	public static final String TRANSACTION_DATE = "transaction_date";
	public static final String TRANSACTION_TYPE = "transaction_type";
	public static final String TRANSACTION_CATEGORY = "transaction_category";
	
	public static final String CATEGORY_ID = "category_id";
	public static final String CATEGORY_TITLE = "title";
	
	// Type constants. INCOME or EXPENSE ?
	public static final String TRANSACTION_TYPE_INCOME = "income";
	public static final String TRANSACTION_TYPE_EXPENSE = "expense";
	public static final String TRANSACTION_IMAGE = "image";
	
	
	
	
	
	public DBTools(Context context){
		super(context, "Uppgift1.db", null, 1);
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String query_cat ="CREATE TABLE category (" 
				+ CATEGORY_ID + " INTEGER PRIMARY KEY, "
				+ CATEGORY_TITLE + " TEXT, "
				+ TRANSACTION_TYPE + " TEXT )";
		
		String query_trans = "CREATE TABLE transaction_entry (" 
				+ TRANSACTION_ID + " INTEGER PRIMARY KEY, "
				+ TRANSACTION_NAME + " TEXT, "
				+ TRANSACTION_DATE + " DATETIME, "
				+ TRANSACTION_AMOUNT + " REAL, "
				+ TRANSACTION_TYPE + " TEXT, "
				+ TRANSACTION_IMAGE + " TEXT, "
				+ TRANSACTION_CATEGORY + " INTEGER)";
		

		
		db.execSQL(query_cat); // Executes as long as query isn't SELECT ! OBS BARA EN QUERY I DENNA METOD PER GÅNG
		db.execSQL(query_trans);
		
		seed(db);
	}


	private void seed(SQLiteDatabase db) {
			
				
		ContentValues values = new ContentValues();
		
		// Kategorier
		values.put(CATEGORY_TITLE, "Lön");
		values.put(TRANSACTION_TYPE, TRANSACTION_TYPE_INCOME);
		
		db.insert("category", null, values);
		
		values = new ContentValues();
		values.put(CATEGORY_TITLE, "Livsmedel");
		values.put(TRANSACTION_TYPE, TRANSACTION_TYPE_EXPENSE);
		
		db.insert("category", null, values);
		
		values = new ContentValues();
		values.put(CATEGORY_TITLE, "Övrigt");
		values.put(TRANSACTION_TYPE, TRANSACTION_TYPE_INCOME);
		
		db.insert("category", null, values);
		
		
		// Transaktioner...
		values = new ContentValues();
		values.put(TRANSACTION_NAME, "Min saftiga lön");
		values.put(TRANSACTION_AMOUNT, "500");
		values.put(TRANSACTION_CATEGORY, "1");
		values.put(TRANSACTION_TYPE, TRANSACTION_TYPE_INCOME);
		values.put(TRANSACTION_DATE, "2013-02-01 10:00:00");
		
		db.insert("transaction_entry", null, values);
		
		values = new ContentValues();
		values.put(TRANSACTION_NAME, "Fredags handel");
		values.put(TRANSACTION_AMOUNT, "500");
		values.put(TRANSACTION_CATEGORY, "2");
		values.put(TRANSACTION_TYPE, TRANSACTION_TYPE_INCOME);
		values.put(TRANSACTION_DATE, "2013-03-01 10:00:00");
		
		db.insert("transaction_entry", null, values);
		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String query = "DROP TABLE IF EXISTS transaction_entry";
		db.execSQL(query);
		
		query = "DROP TABLE IF EXISTS category";
		db.execSQL(query);
		
		onCreate(db);
	}

	public void insertTransaction(HashMap<String,String> queryValues){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(TRANSACTION_NAME, queryValues.get(TRANSACTION_NAME));
		values.put(TRANSACTION_AMOUNT, queryValues.get(TRANSACTION_AMOUNT));
		values.put(TRANSACTION_DATE, queryValues.get(TRANSACTION_DATE));
		values.put(TRANSACTION_TYPE, queryValues.get(TRANSACTION_TYPE));
		
		if(queryValues.containsKey(TRANSACTION_IMAGE))
			values.put(TRANSACTION_IMAGE, queryValues.get(TRANSACTION_IMAGE));
			
		values.put(TRANSACTION_CATEGORY, queryValues.get(TRANSACTION_CATEGORY));
		
		db.insert("transaction_entry", null, values);
		db.close();		
	}
	
	public ArrayList<HashMap<String,String>> getTransactions(){
		SQLiteDatabase db = this.getReadableDatabase();
		
		ArrayList<HashMap<String, String>> transactionArrayList = new ArrayList<HashMap<String,String>>();
		
		String query = "SELECT * FROM transaction_entry;";
		
		Cursor cursor = db.rawQuery(query, null);
		
		if(cursor.moveToFirst()){
			do{
				HashMap<String,String> transactionMap = new HashMap<String, String>();
				
				transactionMap.put(TRANSACTION_ID, cursor.getString(0));
				transactionMap.put(TRANSACTION_NAME, cursor.getString(1));
				transactionMap.put(TRANSACTION_DATE, cursor.getString(2));
				transactionMap.put(TRANSACTION_AMOUNT, cursor.getString(3));
				transactionMap.put(TRANSACTION_TYPE, cursor.getString(4));
				transactionMap.put(TRANSACTION_CATEGORY, cursor.getString(5));
				
				
				
				transactionArrayList.add(transactionMap);
			}while(cursor.moveToNext());
			
		}
		
		db.close();
		
		return transactionArrayList;
		
	}
	
	public ArrayList<String> getAllTransactionImages(){
		SQLiteDatabase db = this.getReadableDatabase();
		
		ArrayList<String> images = new ArrayList<String>();
		
		String query = "SELECT transaction_entry." + TRANSACTION_IMAGE + " FROM transaction_entry";
		
		Cursor cursor = db.rawQuery(query, null);
		
		if(cursor.moveToFirst()){
			
			do{
				if(cursor.getString(0) != null)
				{
					Log.d("Image: ", cursor.getString(0));
					images.add(cursor.getString(0));	
				}
				
			}while(cursor.moveToNext());
			
			
		}
		
		return images;
	}
	
	public ArrayList<HashMap<String,String>> getAllTransactionsWithCategory(){
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<HashMap<String, String>> transactionArray = new ArrayList<HashMap<String,String>>();
		
		
		String query = "SELECT transaction_entry." + TRANSACTION_NAME + ", transaction_entry." + TRANSACTION_AMOUNT + ", transaction_entry." + TRANSACTION_TYPE + ", transaction_entry." + TRANSACTION_DATE + ", category." + CATEGORY_TITLE + ", transaction_entry." + TRANSACTION_IMAGE + " FROM transaction_entry INNER JOIN category on transaction_entry." + TRANSACTION_CATEGORY + "= category." + CATEGORY_ID;
		
		Log.d("EXEC query:", query);
		
		Cursor cursor = db.rawQuery(query, null);
		
		if(cursor.moveToFirst()){
			
			do{
				HashMap<String, String> transactionMap = new HashMap<String, String>();
				transactionMap.put(TRANSACTION_NAME, cursor.getString(0));
				transactionMap.put(TRANSACTION_AMOUNT, cursor.getString(1));				
				transactionMap.put(TRANSACTION_TYPE, cursor.getString(2));
				transactionMap.put(TRANSACTION_DATE, cursor.getString(3));
				transactionMap.put(TRANSACTION_CATEGORY, cursor.getString(4));
				
				transactionMap.put(TRANSACTION_IMAGE, cursor.getString(5));
				
				
				transactionArray.add(transactionMap);
			}while(cursor.moveToNext());
		}
		
		db.close();
		
		return transactionArray;
		
	}
	
	public int getTransactionCount(){
		SQLiteDatabase db = this.getReadableDatabase();
		
		String query = "SELECT count(" + TRANSACTION_ID + ") from transaction_entry";
		
		Cursor cursor = db.rawQuery(query, null);
		
		int count = -1;
		
		if(cursor.moveToFirst()){
			
			count = cursor.getInt(0);			
		}
		
		db.close();
		
		return count;
	}
	
	public ArrayList<HashMap<String,String>> getIncomeCategories(){
		SQLiteDatabase db = this.getReadableDatabase();
		
		ArrayList<HashMap<String, String>> categoryArrayList = new ArrayList<HashMap<String,String>>();
		
		String query = "SELECT * FROM category WHERE " + TRANSACTION_TYPE + " = '" + TRANSACTION_TYPE_INCOME + "'";
		
		Cursor cursor = db.rawQuery(query, null);
		
		if(cursor.moveToFirst()){
			do{
				HashMap<String,String> categoryMap = new HashMap<String, String>();
				
				categoryMap.put(CATEGORY_ID, String.valueOf(cursor.getInt(0)));
				categoryMap.put(CATEGORY_TITLE, cursor.getString(1));
				
				categoryArrayList.add(categoryMap);
			}while(cursor.moveToNext());
			
		}
		
		db.close();
		
		return categoryArrayList;
	}
	
	public ArrayList<HashMap<String,String>> getExpenseCategories(){
		SQLiteDatabase db = this.getReadableDatabase();
		
		ArrayList<HashMap<String, String>> categoryArrayList = new ArrayList<HashMap<String,String>>();
		
		String query = "SELECT * FROM category WHERE " + TRANSACTION_TYPE + " = '" + TRANSACTION_TYPE_EXPENSE + "'";
		
		Cursor cursor = db.rawQuery(query, null);
		
		if(cursor.moveToFirst()){
			do{
				HashMap<String,String> categoryMap = new HashMap<String, String>();
				
				categoryMap.put(CATEGORY_ID, String.valueOf(cursor.getInt(0)));
				categoryMap.put(CATEGORY_TITLE, cursor.getString(1));
				
				categoryArrayList.add(categoryMap);
			}while(cursor.moveToNext());
			
		}
		
		db.close();
		
		return categoryArrayList;
	}	
		
	public HashMap<String, String> getTransactionInfo(String id){
		SQLiteDatabase db = this.getReadableDatabase();
		HashMap<String, String> transactionMap = new HashMap<String, String>();
		
		String query = "SELECT transaction_entry." + TRANSACTION_NAME + ", transaction_entry." + TRANSACTION_AMOUNT + ", transaction_entry." + TRANSACTION_TYPE + ", transaction_entry." + TRANSACTION_DATE + ", category." + CATEGORY_TITLE + ", transaction_entry." + TRANSACTION_IMAGE + " FROM transaction_entry INNER JOIN category on transaction_entry." + TRANSACTION_CATEGORY + "= category." + CATEGORY_ID + " WHERE transaction_id='" + id + "'";
		
		Log.d("EXEC query:", query);
		
		Cursor cursor = db.rawQuery(query, null);
		
		if(cursor.moveToFirst()){
			
			do{
				transactionMap.put(TRANSACTION_NAME, cursor.getString(0));
				transactionMap.put(TRANSACTION_AMOUNT, cursor.getString(1));				
				transactionMap.put(TRANSACTION_TYPE, cursor.getString(2));
				transactionMap.put(TRANSACTION_DATE, cursor.getString(3));
				transactionMap.put(TRANSACTION_CATEGORY, cursor.getString(4));
				transactionMap.put(TRANSACTION_IMAGE, cursor.getString(5));
			}while(cursor.moveToNext());
		}
		
		db.close();
		
		return transactionMap;
	}


	public String getTransactionTitle(int position) {
		SQLiteDatabase db = this.getReadableDatabase();
		String title = "";
		
		String query = "SELECT transaction_entry." + TRANSACTION_NAME + " FROM transaction_entry WHERE " + TRANSACTION_ID + "='" + String.valueOf(position) + "'";
		
		Log.d("EXEC query:", query);
		
		Cursor cursor = db.rawQuery(query, null);
		
		if(cursor.moveToFirst()){
			title = cursor.getString(0);			
		}
		
		db.close();
		
		return title;
	}


	public String getIncomeTotal() {
		SQLiteDatabase db = this.getReadableDatabase();
		
		String result = "";
		
		String query = "SELECT sum(" + TRANSACTION_AMOUNT + ") FROM transaction_entry WHERE " + TRANSACTION_TYPE + "= '" + TRANSACTION_TYPE_INCOME + "'";
		
		Cursor cursor = db.rawQuery(query, null);
		
		if(cursor.moveToFirst()){
			result = cursor.getString(0);			
		}
		
		return result;
	}
	
	public String getExpenseTotal(){
		SQLiteDatabase db = this.getReadableDatabase();
		
		String result = "";
		
		String query = "SELECT sum(" + TRANSACTION_AMOUNT + ") FROM transaction_entry WHERE " + TRANSACTION_TYPE + "= '" + TRANSACTION_TYPE_EXPENSE + "'";
		
		Cursor cursor = db.rawQuery(query, null);
		
		if(cursor.moveToFirst()){
			result = cursor.getString(0);			
		}
		
		return result;
	}
		
}


