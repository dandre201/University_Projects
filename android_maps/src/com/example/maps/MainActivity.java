package com.example.maps;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.app.Dialog;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.Marker;
import com.maps.AlertDialogRadio.AlertPositiveListener;




public class MainActivity extends Activity implements OnClickListener, OnMarkerClickListener, AlertPositiveListener {

	private GoogleMap map;
	int position = 0;
	final CharSequence[] cities={"White"};
	private int mtype;
	private final LatLng loc_glasgow = new LatLng(55.8651500, -4.2576300);
	private final LatLng loc_scots = new LatLng(55.899839 , -4.395019);
	private final LatLng loc_ibrox = new LatLng(55.853136 , -4.309143);
	private final LatLng loc_secc = new LatLng(55.860914 , -4.288986);
	private final LatLng loc_kelv = new LatLng(55.867324 , -4.288245);
	private final LatLng loc_hamp = new LatLng(55.825780 , -4.251741);
	private final LatLng loc_hockey = new LatLng(55.844960 , -4.236709);
	private final LatLng loc_toll = new LatLng(55.845048 , -4.176075);
	private final LatLng loc_velo = new LatLng(55.846847 , -4.207008);
	
	private final LatLng loc_dehli = new LatLng(28.704059 , 77.10249);
	private final LatLng loc_melb = new LatLng(-37.814107 , 144.96328);
	private final LatLng loc_man = new LatLng(53.479324 , -2.248485);
	private final LatLng loc_Kuala = new LatLng(3.139003 , 101.686855);
	private final LatLng loc_Vict = new LatLng(48.428421 , -123.365644);
	private final LatLng loc_Auk = new LatLng(-36.848460 , 174.763332);
	private final LatLng loc_Edin = new LatLng(55.953252 , -3.188267);
	private final LatLng loc_Bri = new LatLng(-27.471011 , 153.023449);
	private final LatLng loc_Edm = new LatLng(53.544389 , -113.490927);
	private final LatLng loc_Chri = new LatLng(-43.532054 , 172.636225);
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		map  = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(loc_glasgow,10);
		map.animateCamera(update);
		map.setOnMarkerClickListener(this);
		map.setMyLocationEnabled(true);	
		 AlertDialog.Builder builder;
		 AlertDialog alert;
		
	
	
		
		map.addMarker(new MarkerOptions().position(loc_scots).title("Scotstoun").icon(BitmapDescriptorFactory.fromResource(R.drawable.tab)));
		map.addMarker(new MarkerOptions().position(loc_ibrox).title("Ibrox").icon(BitmapDescriptorFactory.fromResource(R.drawable.rug)));
		map.addMarker(new MarkerOptions().position(loc_secc).title("SECC").icon(BitmapDescriptorFactory.fromResource(R.drawable.box)));
		map.addMarker(new MarkerOptions().position(loc_kelv).title("Kelvingroves").icon(BitmapDescriptorFactory.fromResource(R.drawable.bow)));
		map.addMarker(new MarkerOptions().position(loc_hamp).title("Hampden").icon(BitmapDescriptorFactory.fromResource(R.drawable.athl)));
		map.addMarker(new MarkerOptions().position(loc_hockey).title("National Hockey Centre").icon(BitmapDescriptorFactory.fromResource(R.drawable.hoc)));
		map.addMarker(new MarkerOptions().position(loc_toll).title("Tollcross").icon(BitmapDescriptorFactory.fromResource(R.drawable.swimming)));
		map.addMarker(new MarkerOptions().position(loc_velo).title("Velodrome").icon(BitmapDescriptorFactory.fromResource(R.drawable.cyc)));
	
		map.addMarker(new MarkerOptions().position(loc_dehli).title("Dehli"));
		map.addMarker(new MarkerOptions().position(loc_melb).title("Melbourne"));
		
		map.addMarker(new MarkerOptions().position(loc_man).title("Manchester"));
		map.addMarker(new MarkerOptions().position(loc_Kuala).title("Kuala Lumpar"));
		map.addMarker(new MarkerOptions().position(loc_Vict).title("Victoria"));
		map.addMarker(new MarkerOptions().position(loc_Auk).title("Aukland"));
		map.addMarker(new MarkerOptions().position(loc_Edin).title("Edinburgh"));
		map.addMarker(new MarkerOptions().position(loc_Bri).title("Brisbane"));
		map.addMarker(new MarkerOptions().position(loc_Edm).title("Edmonton"));
		map.addMarker(new MarkerOptions().position(loc_Chri).title("Christchurch"));
		
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onclick_games(View v){
		  OnClickListener listener = new OnClickListener() {			
				@Override
				public void onClick(View v) {
					/** Getting the fragment manager */
					FragmentManager manager = getFragmentManager();
					
					/** Instantiating the DialogFragment class */
					AlertDialogRadio alert = new AlertDialogRadio();
					
					/** Creating a bundle object to store the selected item's index */
					Bundle b  = new Bundle();
					
					/** Storing the selected item's index in the bundle object */
					b.putInt("position", position);
					
					/** Setting the bundle object to the dialog fragment object */
					alert.setArguments(b);
					
					/** Creating the dialog fragment object, which will in turn open the alert dialog window */
					alert.show(manager, "alert_dialog_radio");			
					
				}
			};
			 /** Getting the reference of the button from the main layout */
	        Button btn = (Button) findViewById(R.id.butgames);
	        
	        /** Setting a button click listener for the choose button */
	        btn.setOnClickListener(listener);          
		
	}
		
	
	public void onclick_sat(View v)
	{  	
			map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);			
	}
	
	public void onclick_map(View v)
	{  	
			map.setMapType(GoogleMap.MAP_TYPE_NORMAL);			
	}


	public void onclick_home(View V)
	{
		CameraUpdate update = CameraUpdateFactory.newLatLng(loc_glasgow);
		map.animateCamera(update);	
	}
	
	
	@Override
	public boolean onMarkerClick(Marker marker) {
		
		String title = marker.getTitle();
		
		 if("Scotstoun".equals(title)) {	
			 new AlertDialog.Builder(this)
			    .setTitle("Scotstoun sports campus")
			    .setMessage("Address: 72 Danes Drive, Glasgow, G14 9HD "+
			    		" Events: Squash, Table tennis")			   
			    .setNegativeButton("close", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // do nothing
			        }
			     }).show(); 
		 }
		 else if("Ibrox".equals(title)) {	
			 new AlertDialog.Builder(this)
			    .setTitle("Ibrox stadium")		
			    .setMessage("Address: 150 Edmiston Dr, Glasgow, G51 2XD "+
			    		" Events: Rugby sevens")			   
			    .setNegativeButton("close", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // do nothing
			        }
			     }).show();
		
		 }
		 else if("SECC".equals(title)) {	
			 new AlertDialog.Builder(this)
			    .setTitle("SECC precinct")		
			    .setMessage("Address:  Exhibition Way, G3 8YW "+
			    		" Events: Boxing, Gynastics, Judo, Netball, Wrestling, Weightlifting")			   
			    .setNegativeButton("close", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // do nothing
			        }
			     }).show();
		
		 }
		 else if("Kelvingroves".equals(title)) {	
			 new AlertDialog.Builder(this)
			    .setTitle("Kelvingroves lawns bowls centre")		
			    .setMessage("Address:  Kelvin Way, G3 7TA "+
			    		" Events: Lawn Bowls")			   
			    .setNegativeButton("close", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // do nothing
			        }
			     }).show();
		
		 }
		 else if("Hampden".equals(title)) {	
			 new AlertDialog.Builder(this)
			    .setTitle("Hampden stadium")		
			    .setMessage("Address: Letherby Dr, G42 9BA "+
			    		" Events: Athletics")			   
			    .setNegativeButton("close", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // do nothing
			        }
			     }).show();
		
		 }
		 else if("National Hockey Centre".equals(title)) {	
			 new AlertDialog.Builder(this)
			    .setTitle("Glasgow National Hockey Centre")		
			    .setMessage("Address: Glasgow, G40 1HB "+
			    		" Events: Hockey")			   
			    .setNegativeButton("close", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // do nothing
			        }
			     }).show();
		
		 }
		 else if("Tollcross".equals(title)) {	
			 new AlertDialog.Builder(this)
			    .setTitle("Tollcross international Swimming centre")		
			    .setMessage("Address:  367 Wellshot Rd, G32 7QP "+
			    		" Events: Swimming")			   
			    .setNegativeButton("close", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // do nothing
			        }
			     }).show();
		 }
		 else if("Velodrome".equals(title)) {	
			 new AlertDialog.Builder(this)
			    .setTitle("Emeritates arena & velodrome")		
			    .setMessage("Address: 1000 London Road, G40 3HG "+
			    		" Events: Cycling, Badminton")			   
			    .setNegativeButton("close", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // do nothing
			        }
			     }).show();
		 }
		 else if("Dehli".equals(title)) {	
			 new AlertDialog.Builder(this)
			    .setTitle(" 2010-Dehli, India")		
			    .setMessage("Scotland got 26 medels: 9 Gold, 10 Silver, 7 Bronze ")			   
			    .setNegativeButton("close", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // do nothing
			        }
			     }).show();
		 }
		 else if("Melbourne".equals(title)) {	
			 new AlertDialog.Builder(this)
			    .setTitle(" 2006-Melbourne, Australia")		
			    .setMessage("Scotland got 29 medels: 11 Gold, 7 Silver, 11 Bronze ")			   
			    .setNegativeButton("close", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // do nothing
			        }
			     }).show();
		 }
		 else if("Manchester".equals(title)) {	
			 new AlertDialog.Builder(this)
			    .setTitle(" 2002-Manchester, England")		
			    .setMessage("Scotland got 30 medels: 6 Gold, 8 Silver, 16 Bronze ")			   
			    .setNegativeButton("close", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // do nothing
			        }
			     }).show();
		 }
		 else if("Kuala Lumpar".equals(title)) {	
			 new AlertDialog.Builder(this)
			    .setTitle(" 1998-Kuala Lumpar, Malaysia")		
			    .setMessage("Scotland got 12 medels: 3 Gold, 2 Silver, 7 Bronze ")			   
			    .setNegativeButton("close", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // do nothing
			        }
			     }).show();
		 }
		 else if("Victoria".equals(title)) {	
			 new AlertDialog.Builder(this)
			    .setTitle(" 1994-Victoria, Canada")		
			    .setMessage("Scotland got 20 medels: 6 Gold, 3 Silver, 11 Bronze ")			   
			    .setNegativeButton("close", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // do nothing
			        }
			     }).show();
		 }
		 else if("Aukland".equals(title)) {	
			 new AlertDialog.Builder(this)
			    .setTitle(" 1990-Aukland, New Zealand")		
			    .setMessage("Scotland got 22 medels: 5 Gold, 7 Silver, 10 Bronze ")			   
			    .setNegativeButton("close", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // do nothing
			        }
			     }).show();
		 }
		 else if("Edinburgh".equals(title)) {	
			 new AlertDialog.Builder(this)
			    .setTitle(" 1986-Edinburgh, Scotland")		
			    .setMessage("Scotland got 33 medels: 3 Gold, 12 Silver, 18 Bronze ")			   
			    .setNegativeButton("close", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // do nothing
			        }
			     }).show();
		 }
		 else if("Brisbane".equals(title)) {	
			 new AlertDialog.Builder(this)
			    .setTitle(" 1982-Brisbane, Australia")		
			    .setMessage("Scotland got 26 medels: 8 Gold, 6 Silver, 12 Bronze ")			   
			    .setNegativeButton("close", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // do nothing
			        }
			     }).show();
		 }
		 else if("Edmionton".equals(title)) {	
			 new AlertDialog.Builder(this)
			    .setTitle(" 1978-Edmonton-Canada")		
			    .setMessage("Scotland got 14 medels: 3 Gold, 6 Silver, 5 Bronze ")			   
			    .setNegativeButton("close", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // do nothing
			        }
			     }).show();
		 }
		 else if("Christchurch".equals(title)) {	
			 new AlertDialog.Builder(this)
			    .setTitle(" 1974-Christchurch-New Zealand")		
			    .setMessage("Scotland got 19 medels: 3 Gold, 5 Silver, 11 Bronze ")			   
			    .setNegativeButton("close", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // do nothing
			        }
			     }).show();
		 }
		
		return false;
	}

	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPositiveClick(int position) {
		this.position = position;
  
		switch (position) {
	    case 0:
	    	CameraUpdate update = CameraUpdateFactory.newLatLng(loc_dehli);	
	    	map.animateCamera(update);
	        break;
	     case 1:
		   CameraUpdate up = CameraUpdateFactory.newLatLng(loc_melb);
		   map.animateCamera(up);
		   break;
	     case 2:
		    CameraUpdate update1 = CameraUpdateFactory.newLatLng(loc_man);	
		    map.animateCamera(update1);
		       break;
		  case 3:
			 CameraUpdate up1 = CameraUpdateFactory.newLatLng(loc_Kuala);
			 map.animateCamera(up1);
			  break;
		  case 4:
		CameraUpdate update2 = CameraUpdateFactory.newLatLng(loc_Vict);	
			  map.animateCamera(update2);
			   break;
	case 5:
		 CameraUpdate up2 = CameraUpdateFactory.newLatLng(loc_Auk);
		 map.animateCamera(up2);
			  break;
	 case 6:
		CameraUpdate update3 = CameraUpdateFactory.newLatLng(loc_Edin);	
		map.animateCamera(update3);
				 break;
	 case 7:
			CameraUpdate up3 = CameraUpdateFactory.newLatLng(loc_Bri);
	          map.animateCamera(up3);
					   break;
	 case 8:
			CameraUpdate up4 = CameraUpdateFactory.newLatLng(loc_Edm);
			 map.animateCamera(up4);
				 break;			 
	 case 9:
			CameraUpdate up5 = CameraUpdateFactory.newLatLng(loc_Chri);
			 map.animateCamera(up5);
				 break;	    	        
	    }	
		
	}

	
	}
