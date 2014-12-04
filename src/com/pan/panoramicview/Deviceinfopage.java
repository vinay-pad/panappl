package com.pan.panoramicview;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Deviceinfopage extends Activity{
	
	TextView fw_name;
	TextView connected;
	String passedArg;
	Button refresh_but;
	Button cpu_but;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.device_info_page);
		refresh_but = (Button) findViewById(R.id.refresh_but);
		cpu_but = (Button) findViewById(R.id.cpu_button);
		fw_name = (TextView) findViewById(R.id.textView1);
		connected = (TextView) findViewById(R.id.textView4);
		passedArg = getIntent().getExtras().getString("fw_name");
		fw_name.setText(passedArg);
		
		update_data();
		refresh_but.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				update_data();
			}
		});	
		cpu_but.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParseQuery<ParseObject> query = ParseQuery.getQuery(passedArg);
				query.orderByAscending("updatedAt");
				query.findInBackground(new FindCallback<ParseObject>() {
				    public void done(List<ParseObject> scoreList, ParseException e) {
				    	ArrayList<String> value_y = new ArrayList<String>();
				    	ArrayList<Integer> value_x = new ArrayList<Integer>();
				    	Integer first_val = 0;
				    	if (e == null) {
				    		for(int i =0; i< scoreList.size(); i++) {
				    			ParseObject myObj = scoreList.get(i);
				    			String b_s_conn = (String) myObj.get("Connected");
				    			java.util.Date b_s_updated = myObj.getUpdatedAt();
				    			Long date_l = b_s_updated.getTime();
				    			if (i != 0){
				    				value_x.add(date_l.intValue()/60000 - first_val);
				    			} else {
				    				first_val = date_l.intValue()/60000;
				    				value_x.add(1);
				    			}
				    			value_y.add(b_s_conn);
				    		}
				    		Toast.makeText(Deviceinfopage.this, "CPU", 1000).show();// display toast
			                 Intent i=new Intent(
			                		 Deviceinfopage.this,
			                         PanGraphView.class);
			                 i.putExtra("fw_name", passedArg.toString());
			                 i.putStringArrayListExtra("y_val", value_y);
			                 i.putIntegerArrayListExtra("x_val", value_x);
			                 startActivity(i);
				    	}
				    }
				});
				 
			}
		});
		
	}
	
	
	private void update_data() {
		// TODO Auto-generated method stub
		ParseQuery<ParseObject> query = ParseQuery.getQuery(passedArg);
		//query.whereEqualTo("test", "test");
		query.orderByDescending("updatedAt");
		query.setLimit(1);
		query.findInBackground(new FindCallback<ParseObject>() {
		    public void done(List<ParseObject> scoreList, ParseException e) {
		    	if (e == null) {
		    		//Toast.makeText(Deviceinfopage.this, "fetched data",1000).show();// display toast
		    		for(int i =0; i< scoreList.size(); i++) {
						ParseObject myObj = scoreList.get(i);
						String b_s_conn = (String) myObj.get("Connected");
						if( b_s_conn.equals("1")) {
							connected.setText("Connected");
						} else {
							connected.setText("Dis-Connected");
						}
						
					}
		    		
		    		
		    		//Log.d("score", "Retrieved " + scoreList.size() + " scores");
		        } else {
		            //Log.d("score", "Error: " + e.getMessage());
		            Toast.makeText(Deviceinfopage.this, "Could not fetch data",1000).show();// display toast
		        }
		    }
		});
		
	}
	

}
