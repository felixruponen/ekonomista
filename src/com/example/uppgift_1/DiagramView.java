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
	
	private int mDiagramStep;
	
	private double mIncome, mExpense;
	
	DBTools db;	
	
	public DiagramView(Context context) {
		super(context);
		

		init(context);
		
	}
	
	public DiagramView(Context context, AttributeSet attr) {
		super(context, attr);
		
		init(context);
		
	}
	
	public DiagramView(Context context, AttributeSet attr, int defStyleAttr) {
		super(context, attr, defStyleAttr);
		
		init(context);
	}
	
	private void init(Context context){
		db = new DBTools(context);
		
		mIncome = db.getTotalIncome();
		mExpense = db.getTotalExpense();
		
		mDiagramStep = (mIncome > mExpense) ? (int)mIncome / DIAGRAM_HEIGHT : (int)mExpense / DIAGRAM_HEIGHT;
		
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
		
		
		canvas.drawText("Your income: " + String.valueOf(mIncome), START_X + 50, START_Y + DIAGRAM_HEIGHT + 30, mPaint);
		canvas.drawText("Your expense: " + String.valueOf(mExpense), START_X + 50, START_Y + DIAGRAM_HEIGHT + 50, mPaint);
		
		// Rita staplar!
		mPaint.setColor(Color.BLUE);
		rect.set(START_X + 20, START_Y, START_X + 100, START_Y + DIAGRAM_HEIGHT);
		canvas.drawRect(rect, mPaint);
	}

}
