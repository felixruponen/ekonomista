package com.example.uppgift_1;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class BarDrawable implements IDrawable {

	private Rect rect;
	private Paint paint;
	private int identifier;
	private OnDrawableTouchedListener listener;
	
	public BarDrawable(Rect rect, Paint paint, int identifier){
		this.rect = rect;
		this.paint = paint;
		this.identifier = identifier;
	}
	
	public void setOnDrawableTouchedListener(OnDrawableTouchedListener listener){
		this.listener = listener;
	}
	
	public void draw(Canvas canvas){
		canvas.drawRect(rect, paint);	
	}

	@Override
	public void isClicked(float x, float y) {

		if(x >= rect.left && x <= rect.right && y <= rect.bottom && y >= rect.top){

			if(listener != null)
				listener.onTouch(this, x, y);
		}
	}

	@Override
	public int getIdentifier() {

		return identifier;
	}
	
	
}
