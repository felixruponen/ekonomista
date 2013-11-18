package com.example.uppgift_1;

import android.graphics.Canvas;
import android.graphics.Paint;

public class LineDrawable implements IDrawable{

	int startX, startY, stopX, stopY;
	Paint paint;
	
	public LineDrawable(int startX, int startY, int stopX, int stopY, Paint paint){
		this.startX = startX;
		this.startY = startY;
		this.stopX = stopX;
		this.stopY = stopY;
		this.paint = paint;
	}
	
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawLine(startX, startY, stopX, stopY, paint);		
	}


	@Override
	public void isClicked(float x, float y) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int getIdentifier() {
		// TODO Auto-generated method stub
		return 0;
	}





}
