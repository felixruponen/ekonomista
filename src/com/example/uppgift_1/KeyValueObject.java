package com.example.uppgift_1;

public class KeyValueObject {

    private int id;
    private String value;

    public KeyValueObject ( int id , String value ) {
        this.id = id;
        this.value = value;
    }

    public int getId () {
        return id;
    }

    public String getValue () {
        return value;
    }

    @Override
    public String toString () {
        return value;
    }
	
}
