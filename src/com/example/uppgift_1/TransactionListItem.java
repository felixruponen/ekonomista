package com.example.uppgift_1;

public class TransactionListItem {
    
	private int id;
    private String title;
    private String amount;
    private String type;

    public TransactionListItem( int id , String title , String amount, String type) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.type = type;
    }
    
    public String getAmount(){
    	return amount;
    }
    
    public String getType(){
    	return type;
    }

    public int getId () {
        return id;
    }

    public String getTitle () {
        return title;
    }

    @Override
    public String toString () {
        return title;
    }
}
