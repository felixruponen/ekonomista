package com.example.uppgift_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class InformationBubbleView extends View {
	




	BubbleDrawable drawable;
	
	float radius = 20f;
	
	public InformationBubbleView(Context context) {
		super(context);
		Log.i("InformationBubble", "CREATING");
	}
	
	public void setPosition(float x, float y, String text){
		Log.i("InformationBubble", "X : " + x);
		Log.i("InformationBubble", "Y : " + y);
		Log.i("InformationBubble", "Text : " + text);
		drawable = new BubbleDrawable(x, y, radius, new Paint(Color.CYAN), text);
	}
	
	public void removeBubble(){
		drawable = null;
	}
	
	public InformationBubbleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Log.i("InformationBubble", "CREATING");

	}
	
	public InformationBubbleView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);

	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		setMeasuredDimension((int)radius * 2, (int)radius * 2);
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		if(drawable != null)
			drawable.draw(canvas);
		
	}

}
