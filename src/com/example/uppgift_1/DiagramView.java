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
import android.view.View.MeasureSpec;

public class DiagramView extends View implements OnDrawableTouchedListener {


	Paint mPaint = new Paint();
	Rect rect = new Rect();
	
	private static final int DIAGRAM_HEIGHT = 300;
	private static final int DIAGRAM_WIDTH = 500;
	
	private static final int START_X = 40;
	private static final int START_Y = 20;

	private static final int BAR_EXPENSE_IDENTIFIER = 1000;

	private static final int BAR_INCOME_IDENTIFIER = 1001;
	
	private int mDiagramSize;
	
	private double mIncome, mExpense;
	private double mIncomeProcent, mExpenseProcent;
	private int mIncomeHeight, mExpenseHeight;
	
	ArrayList<IDrawable> drawables;
	
	DBTools db;	
	
	public DiagramView(Context context) {
		super(context);
		init();
	}
	
	public DiagramView(Context context, AttributeSet attr) {
		super(context, attr);
		
		init();
		
	}
	
	public DiagramView(Context context, AttributeSet attr, int defStyleAttr) {
		super(context, attr, defStyleAttr);
		init();
	}
	
	private void init(){		
		mDiagramSize = (int)mIncome + (int)mExpense;
		mIncomeProcent = mIncome / mDiagramSize;
		mExpenseProcent = mExpense / mDiagramSize;
		mIncomeHeight = (int) (mIncomeProcent * DIAGRAM_HEIGHT);
		mExpenseHeight = (int) (mExpenseProcent * DIAGRAM_HEIGHT);
		
		drawables = new ArrayList<IDrawable>();
		
		BarDrawable incomeBar = new BarDrawable(new Rect(START_X + 20, START_Y + (DIAGRAM_HEIGHT - mIncomeHeight), START_X + 100, START_Y + DIAGRAM_HEIGHT), new Paint(Color.BLUE), BAR_INCOME_IDENTIFIER);
		incomeBar.setOnDrawableTouchedListener(this);
		drawables.add(incomeBar);
		
		BarDrawable expenseBar = new BarDrawable(new Rect(START_X + 120, START_Y + (DIAGRAM_HEIGHT - mExpenseHeight), START_X + 200, START_Y + DIAGRAM_HEIGHT), new Paint(Color.RED), BAR_EXPENSE_IDENTIFIER);
		expenseBar.setOnDrawableTouchedListener(this);
		drawables.add(expenseBar);
		
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
			
			for(IDrawable drawable : drawables){
				drawable.isClicked(x, y);
			}
			
			
			break;
		
		
		}
		
		
		
		return super.onTouchEvent(event);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//setMeasuredDimension(DIAGRAM_WIDTH + START_X + getPaddingLeft() + getPaddingRight(), DIAGRAM_HEIGHT + START_Y + getPaddingBottom() + getPaddingTop()  + 50);
	
		int measuredWidth = getWidthMeasure(widthMeasureSpec);
		int measureHeight = getHeightMeasure(heightMeasureSpec);
		
		setMeasuredDimension(measuredWidth, measureHeight);
	}
	
	private int getHeightMeasure(int heightMeasureSpec) {
		int desired = DIAGRAM_HEIGHT + START_Y + 60;
		
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

	@Override
	public void onTouch(IDrawable drawable, float x, float y) {

		switch(drawable.getIdentifier()){
		case BAR_EXPENSE_IDENTIFIER:
				Log.i("DiagramView", "Expensebar clicked!");
			break;
			
		case BAR_INCOME_IDENTIFIER:
				Log.i("DiagramView", "Incomebar clicked!");
			break;
		}
		
	}

}
