package com.pan.panoramicview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.Parse;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;


public class MainActivity extends Activity {
	   static final LatLng India = new LatLng(22 , 77);
	   static final LatLng USA = new LatLng(38 , 97);
	   static final LatLng Canada = new LatLng(60 , 95);
	   
	   
	   private GoogleMap googleMap;
	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      Parse.enableLocalDatastore(this);
	      Parse.initialize(this, "RnsDdfZemEz0bGQoqMQE4JyyfSLcdJmqTreY5QD9", "0mipbWl5aniumL4XNu1W9pQ4c7ovIM6EdyU8PLrU");
	      setContentView(R.layout.activity_main);
	      try { 
	            if (googleMap == null) {
	               googleMap = ((MapFragment) getFragmentManager().
	               findFragmentById(R.id.map)).getMap();
	            }
	         googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
	         
	         
	         googleMap.addMarker(new MarkerOptions().
	         position(India).title("FW1"));
	         
	         googleMap.addMarker(new MarkerOptions().
	    	         position(USA).title("FW2"));
	         
	         /*
	           googleMap.addMarker(new MarkerOptions().
	          		 position(Canada).title("FW3"));
	         
	         */
	         
	         googleMap.setOnMarkerClickListener(new OnMarkerClickListener()
             {
	        	 @Override
                 public boolean onMarkerClick(Marker arg0) {
                     
                     Toast.makeText(MainActivity.this, arg0.getTitle(),1000).show();// display toast
                     Intent i=new Intent(
                             MainActivity.this,
                             Deviceinfopage.class);
                     i.putExtra("fw_name", arg0.getTitle());
                     startActivity(i);
                     return true;
                 }

             });  
	         

	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	   }

	}