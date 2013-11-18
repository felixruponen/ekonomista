package com.example.uppgift_1;

import android.graphics.Canvas;
import android.graphics.Paint;

public class TextDrawable implements IDrawable {

	String text;
	int x, y;
	Paint paint;
	
	
	public TextDrawable(String text, int x, int y, Paint paint){
		this.text = text;
		this.x = x;
		this.y = y;
		this.paint = paint;
	}
	
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawText(text, x, y, paint);		
	}


	@Override
	public void isClicked(float x, float y) {

		
	}


	@Override
	public int getIdentifier() {
		// TODO Auto-generated method stub
		return 0;
	}



}
