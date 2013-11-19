package com.example.uppgift_1;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DateSliderView extends View {

	
	ArrayList<IDrawable> drawables;
	
	public DateSliderView(Context context) {
		super(context);
		
		init(context);
	}
	
	public DateSliderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public DateSliderView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	
	public void init(Context context){
		
		drawables = new ArrayList<IDrawable>();
		
		Paint p = new Paint(Color.BLACK);
		p.setStrokeWidth((float) 1.5);
		
		LineDrawable line = new LineDrawable(0, 10, 200, 10, p);
		
		
		drawables.add(line);
		
		
		
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//setMeasuredDimension(DIAGRAM_WIDTH + START_X + getPaddingLeft() + getPaddingRight(), DIAGRAM_HEIGHT + START_Y + getPaddingBottom() + getPaddingTop()  + 50);
	
		int measuredWidth = getWidthMeasure(widthMeasureSpec);
		int measureHeight = getHeightMeasure(heightMeasureSpec);
		
		setMeasuredDimension(measuredWidth, measureHeight);
	}
	
	private int getHeightMeasure(int heightMeasureSpec) {
		int desired = 500;
		
		int mode = MeasureSpec.getMode(heightMeasureSpec);
		int size = MeasureSpec.getSize(heightMeasureSpec);
		
		switch( mode ){
		case MeasureSpec.AT_MOST:
			return Math.min(size, desired);
		case MeasureSpec.EXACTLY:
			return size;
		case MeasureSpec.UNSPECIFIED:
			return desired;
		default:
			return desired;
		}
	}

	private int getWidthMeasure(int widthMeasureSpec) {
		int desired = 500;
		
		int mode = MeasureSpec.getMode(widthMeasureSpec);
		int size = MeasureSpec.getSize(widthMeasureSpec);
		
		switch( mode ){
		case MeasureSpec.AT_MOST:
			return Math.min(size, desired);
		case MeasureSpec.EXACTLY:
			return size;
		case MeasureSpec.UNSPECIFIED:
			return desired;
		default:
			return desired;
		}
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
								
		for(IDrawable drawable : drawables){
			drawable.draw(canvas);
		}		
	}

}
