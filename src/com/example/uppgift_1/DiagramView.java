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
	
	private int mDiagramSize;
	
	private double mIncome, mExpense;
	private double mIncomeProcent, mExpenseProcent;
	private int mIncomeHeight, mExpenseHeight;
	
	DBTools db;	
	
	public DiagramView(Context context) {
		super(context);
		
	}
	
	public DiagramView(Context context, AttributeSet attr) {
		super(context, attr);
		
	
		
	}
	
	public DiagramView(Context context, AttributeSet attr, int defStyleAttr) {
		super(context, attr, defStyleAttr);
		
	}
	
	private void init(){		
		mDiagramSize = (int)mIncome + (int)mExpense;
		mIncomeProcent = mIncome / mDiagramSize;
		mExpenseProcent = mExpense / mDiagramSize;
		mIncomeHeight = (int) (mIncomeProcent * DIAGRAM_HEIGHT);
		mExpenseHeight = (int) (mExpenseProcent * DIAGRAM_HEIGHT);
		
		
		
	}
	
	public void setDiagram(double income, double expense){
		mIncome = income;
		mExpense = expense;
		
		init();	
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(DIAGRAM_WIDTH + START_X + getPaddingLeft() + getPaddingRight(), DIAGRAM_HEIGHT + START_Y + getPaddingBottom() + getPaddingTop()  + 50);
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
		rect.set(START_X + 20, START_Y + (DIAGRAM_HEIGHT - mIncomeHeight), START_X + 100, START_Y + DIAGRAM_HEIGHT);
		canvas.drawRect(rect, mPaint);
		
		mPaint.setColor(Color.RED);
		rect.set(START_X + 120, START_Y + (DIAGRAM_HEIGHT - mExpenseHeight), START_X + 200, START_Y + DIAGRAM_HEIGHT);
		canvas.drawRect(rect, mPaint);
	}

}
