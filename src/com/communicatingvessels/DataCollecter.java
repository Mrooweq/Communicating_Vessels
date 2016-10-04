package com.communicatingvessels;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class DataCollecter extends Activity{
	
	private EditText ETc1;
	private EditText ETc2;
	private EditText ETr1;
	private EditText ETr2;
	private EditText ETu;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);  

		ETc1 = (EditText)findViewById(R.id.ETc1);  
		ETc2 = (EditText)findViewById(R.id.ETc2); 
		ETr1 = (EditText)findViewById(R.id.ETr1); 
		ETr2 = (EditText)findViewById(R.id.ETr2); 
		ETu = (EditText)findViewById(R.id.ETu);   

		Button btn = (Button)findViewById(R.id.button1);

		btn.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				try
				{
					Steering.c1 = Float.parseFloat(ETc1.getText().toString());
					Steering.c2 = Float.parseFloat(ETc2.getText().toString());
					Steering.r1 = Float.parseFloat(ETr1.getText().toString());
					Steering.r2 = Float.parseFloat(ETr2.getText().toString());
					Steering.u = Float.parseFloat(ETu.getText().toString());
				} catch(Exception e){}

				Intent intent = new Intent(DataCollecter.this, Steering.class);
				startActivity(intent);
			}
		});


	}

}
