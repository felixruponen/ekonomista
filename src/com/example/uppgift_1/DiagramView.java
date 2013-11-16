package com.example.uppgift_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class DiagramView extends View {

	Paint mPaint = new Paint();
	Rect rect = new Rect();
	
	private static final int DIAGRAM_HEIGHT = 300;
	private static final int DIAGRAM_WIDTH = 500;
	
	private static final int START_X = 40;
	private static final int START_Y = 20;

	
	public DiagramView(Context context) {
		super(context);
		
	}
	
	public DiagramView(Context context, AttributeSet attr) {
		super(context, attr);
		
	}
	
	public DiagramView(Context context, AttributeSet attr, int defStyleAttr) {
		super(context, attr, defStyleAttr);
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		
		
		
		// Rita Graf
		mPaint.setColor(Color.BLACK);
		
		// Y
		canvas.drawLine(START_X, START_Y, START_X, START_Y + DIAGRAM_HEIGHT, mPaint);
		

		
		
		// X
		canvas.drawLine(START_X, START_Y + DIAGRAM_HEIGHT, START_X + DIAGRAM_WIDTH, START_Y + DIAGRAM_HEIGHT, mPaint);
		
		
		
		// Rita staplar!
		mPaint.setColor(Color.BLUE);
		rect.set(START_X + 20, START_Y, START_X + 100, START_Y + DIAGRAM_HEIGHT);
		canvas.drawRect(rect, mPaint);
	}

}
