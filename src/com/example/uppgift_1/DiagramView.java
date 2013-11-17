package com.example.uppgift_1;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class DiagramView extends View {



	boolean clicked;

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
	
	ArrayList<IDrawable> drawables;
	
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
		
		drawables = new ArrayList<IDrawable>();
		
		drawables.add(new BarDrawable(new Rect(START_X + 20, START_Y + (DIAGRAM_HEIGHT - mIncomeHeight), START_X + 100, START_Y + DIAGRAM_HEIGHT), new Paint(Color.BLUE)));
		drawables.add(new BarDrawable(new Rect(START_X + 120, START_Y + (DIAGRAM_HEIGHT - mExpenseHeight), START_X + 200, START_Y + DIAGRAM_HEIGHT), new Paint(Color.RED)));
		drawables.add(new TextDrawable("Your income: " + String.valueOf(mIncome), START_X + 50, START_Y + DIAGRAM_HEIGHT + 30, new Paint(Color.BLACK)));
		drawables.add(new TextDrawable("Your expense: " + String.valueOf(mExpense), START_X + 50, START_Y + DIAGRAM_HEIGHT + 50, new Paint(Color.BLACK)));
		drawables.add(new LineDrawable(START_X, START_Y, START_X, START_Y + DIAGRAM_HEIGHT, new Paint(Color.BLACK)));
		drawables.add(new LineDrawable(START_X, START_Y + DIAGRAM_HEIGHT, START_X + DIAGRAM_WIDTH, START_Y + DIAGRAM_HEIGHT, new Paint(Color.BLACK)));
	}
	
	public void setDiagram(double income, double expense){
		mIncome = income;
		mExpense = expense;
		
		init();	
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		switch(event.getAction()){
		
		case MotionEvent.ACTION_DOWN:
			
			float x = event.getX();			
			float y = event.getY();
			
			if(x >= START_X + 20 && x <= START_X + 100 && y >= START_Y + (DIAGRAM_HEIGHT - mIncomeHeight) && y <= START_Y + DIAGRAM_HEIGHT){
				clicked = true;
				Log.i("DrawerView", "Clicked!");
			}
			
			
			break;
		
		
		}
		
		
		
		return super.onTouchEvent(event);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(DIAGRAM_WIDTH + START_X + getPaddingLeft() + getPaddingRight(), DIAGRAM_HEIGHT + START_Y + getPaddingBottom() + getPaddingTop()  + 50);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
								
		for(IDrawable drawable : drawables){
			drawable.draw(canvas);
		}		
	}

}
