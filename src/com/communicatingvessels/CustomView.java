package com.communicatingvessels;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Display;
import android.view.View;

public class CustomView extends View{

	private final int FACTOR1 = 10;
	private final float FACTOR2 = 0.1f;
	private final int SPLIT_WIDTH = 30;
	private final int BLUE = Color.rgb(0, 0, 255);
	private final int WHITE = Color.rgb(255, 255, 255);
	
	private Steering steering;
	private int WIDTH;
	private int HEIGHT;
	private float multiplicator;


	public CustomView(Context context) 
	{
		super(context);                     
		this.steering = (Steering) context;

		Display display = steering.getWindowManager().getDefaultDisplay(); 
		WIDTH = display.getWidth();
		HEIGHT = display.getHeight();
		multiplicator = (float)(HEIGHT-2*FACTOR1)/Math.max(steering.max1, steering.max2);
	}


	@Override 
	protected void onDraw(final Canvas canvas) 
	{
		Paint paint = new Paint();
		paint.setColor(WHITE);
		
		drawVessels(canvas);
		drawWater(canvas);	
		drawStrings(canvas, paint);	

		invalidate();	
	}

	private void drawStrings(final Canvas canvas, Paint paint)
	{
		canvas.drawText(String.valueOf(steering.max1) + "m", 2*FACTOR1, HEIGHT-FACTOR1 - steering.max1 * multiplicator, paint);	
		canvas.drawText(String.valueOf(steering.max2) + "m", 2*FACTOR1 + (WIDTH-2*FACTOR1)/2+SPLIT_WIDTH, HEIGHT - FACTOR1 - steering.max2 * multiplicator, paint);		
	}

	private void drawWater(final Canvas canvas)
	{
		final Paint paint = new Paint();
		paint.setColor(BLUE);

		float y = (float)1/steering.r2*steering.x2;
		float stan[] = {steering.x1, steering.x2};

		float [] stanNowy = steering.algo.add(steering.algo.multiplication(steering.A, stan), steering.algo.multiplication(steering.B, steering.u));

		steering.setX1((float) (steering.x1 + FACTOR2*stanNowy[0]));
		steering.setX2((float) (steering.x2 + FACTOR2*stanNowy[1]));

		canvas.drawRect(FACTOR1 + 1, HEIGHT-FACTOR1 - steering.x1*multiplicator, FACTOR1 + (WIDTH-2*FACTOR1)/2-SPLIT_WIDTH, HEIGHT-FACTOR1, paint); // left vessel
		canvas.drawRect(FACTOR1 + (WIDTH-2*FACTOR1)/2+SPLIT_WIDTH+1, HEIGHT-FACTOR1 - steering.x2*multiplicator, WIDTH - FACTOR1, HEIGHT-FACTOR1, paint); // right vessel

		if(HEIGHT - 2*SPLIT_WIDTH < HEIGHT-FACTOR1 - steering.x2*multiplicator)
			canvas.drawRect(FACTOR1 + (WIDTH-2*FACTOR1)/2-SPLIT_WIDTH, HEIGHT-FACTOR1 - steering.x2*multiplicator, FACTOR1 + (WIDTH-2*FACTOR1)/2+SPLIT_WIDTH+1, HEIGHT-FACTOR1, paint); 
		else
			canvas.drawRect(FACTOR1 + (WIDTH-2*FACTOR1)/2-SPLIT_WIDTH, HEIGHT - 2*SPLIT_WIDTH, FACTOR1 + (WIDTH-2*FACTOR1)/2+SPLIT_WIDTH+1, HEIGHT-FACTOR1, paint);
	}


	private void drawVessels(Canvas canvas) 
	{
		Paint paint = new Paint();
		paint.setColor(WHITE);

		canvas.drawLine(FACTOR1, FACTOR1, FACTOR1, HEIGHT-FACTOR1, paint);     
		canvas.drawLine(FACTOR1, HEIGHT-FACTOR1, WIDTH - FACTOR1, HEIGHT-FACTOR1, paint);  

		canvas.drawLine(WIDTH-FACTOR1, FACTOR1, WIDTH-FACTOR1, HEIGHT - SPLIT_WIDTH, paint); 
		canvas.drawLine(WIDTH-FACTOR1, HEIGHT - 2*SPLIT_WIDTH, WIDTH-FACTOR1, HEIGHT-FACTOR1, paint); 

		canvas.drawLine(FACTOR1 + (WIDTH-2*FACTOR1)/2-SPLIT_WIDTH, FACTOR1, FACTOR1 + (WIDTH-2*FACTOR1)/2-SPLIT_WIDTH, HEIGHT - 2*SPLIT_WIDTH, paint);
		canvas.drawLine(FACTOR1 + (WIDTH-2*FACTOR1)/2+SPLIT_WIDTH, FACTOR1, FACTOR1 + (WIDTH-2*FACTOR1)/2+SPLIT_WIDTH, HEIGHT - 2*SPLIT_WIDTH, paint);

		canvas.drawLine(FACTOR1 + (WIDTH-2*FACTOR1)/2-SPLIT_WIDTH, HEIGHT - 2*SPLIT_WIDTH, FACTOR1 + (WIDTH-2*FACTOR1)/2+SPLIT_WIDTH, HEIGHT - 2*SPLIT_WIDTH, paint);
	}
}