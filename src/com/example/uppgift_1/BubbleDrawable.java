package com.example.uppgift_1;

import android.graphics.Canvas;
import android.graphics.Paint;

public class BubbleDrawable implements IDrawable {
	
	private float x, y, radius;
	private Paint paint;
	
	public BubbleDrawable(float x, float y, float radius, Paint paint, String text){
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.paint = paint;
		
		init();
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;		
	}
	
	private void init(){
		x = x - radius * 2;
		y = y - radius * 2;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawCircle(x, y, radius, paint);
	}

	@Override
	public void isClicked(float x, float y) {
		
	}

	@Override
	public int getIdentifier() {
		return -1;
	}
	
	
	
	
	
	
}
