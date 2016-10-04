package com.communicatingvessels;


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class Steering extends Activity {

	public Algorithm algo;
	public float x1 = 0;
	public float x2 = 0;
	public float [][] A;
	public float [] B;
	public int max1;
	public int max2;
	public float height1 = 0;
	public float height2 = 0;
	
	public static float r1 = 0;
	public static float r2 = 0;
	public static float c1 = 0;
	public static float c2 = 0;
	public static float u = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		algo = new Algorithm();

		float [][] A = { {(float)-1/(r1*c1),(float)1/(r1*c1)},  {(float)1/(r1*c2),((float)-1/(r1*c2)-(float)1/(r2*c2))}	};
		float [] B = {(float)1/c1,0};
		
		this.A = A;
		this.B = B;

		float [][] invA = algo.inv(A);
		float [] wynik = algo.multiplication(invA, B);
		
		max1 = Math.round(-u*wynik[0]);
		max2 = Math.round(-u*wynik[1]);
		
		setContentView(new CustomView(this));  
	}
	
	public void setX1(float x1)
	{
		this.x1 = x1;
	}
	
	public void setX2(float x2)
	{
		this.x2 = x2;
	}
}
