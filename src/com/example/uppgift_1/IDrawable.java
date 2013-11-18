package com.example.uppgift_1;

import android.graphics.Canvas;

public interface IDrawable {
	void draw(Canvas canvas);
	void isClicked(float x, float y);
	int getIdentifier();
}
