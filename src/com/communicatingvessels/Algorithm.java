package com.communicatingvessels;

public class Algorithm 
{
	public float [] multiplication(float [][] tab1, float [] tab2)
	{
		float [] result = {  tab1[0][0]*tab2[0] +  tab1[0][1]*tab2[1], tab1[1][0]*tab2[0] + tab1[1][1]*tab2[1]  };
		
		return result;
	}
	
	public float [] multiplication(float [] tab1, float u)
	{
		float [] result = {  tab1[0] * u, tab1[1] * u  };
		
		return result;
	}
	
	public float multiplication(float [] tab1, float [] tab2)
	{
		float result = tab1[0] * tab2[0] + tab1[1] * tab2[1];
		
		return result;
	}
	
	public float [] add(float [] tab1, float [] tab2)
	{
		float [] result = {  tab1[0] + tab2[0], tab1[1] + tab2[1]  };
		
		return result;
	}
	
	public float [][] inv(float [][] tab1)
	{
		float determinant = tab1[0][0]*tab1[1][1]-tab1[0][1]*tab1[1][0];
		
		float [][] result = { {(float)tab1[1][1]/determinant, - (float)tab1[0][1]/determinant},{-(float)tab1[1][0]/determinant, (float)tab1[0][0]/determinant} };
		
		return result;
	}
}
