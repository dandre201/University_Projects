package com.honours;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.DialogInterface;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.Marker;
import android.view.View.OnClickListener;
import android.app.FragmentManager;
import android.location.Location;
import android.location.LocationListener;
import com.honours.AlertDialogRadio.AlertPositiveListener;
import com.honours.AlertDialogRadio;
import android.app.AlertDialog.Builder;
import com.google.android.gms.maps.model.Polyline;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.SupportMapFragment;
import android.widget.Toast;



public class Main extends FragmentActivity implements OnClickListener, OnMarkerClickListener, AlertPositiveListener {
	
	//sensor values

    // layout values   
    private Button startButton;
    private Button location;
    private TextView textTime;   
    
 // timer values
    private long startTime = 0L;
	private Handler customHandler = new Handler();
	long timeInMilliseconds = 0L;
	long timeSwapBuff = 0L;
	long updatedTime = 0L;
    
	// sesnor+misc values
	int latx1=0;
	int latx2=0;

	
    double time=0;
    double ndist=0;
    double en_bu=515;
    
    Double latcur;
	Double loncur;
	Double latnxt;
	Double lonnxt;
	double perlat;
	double perlon;
    double movex=0;
    
    //google maps 
    private GoogleMap map;
    int position = 0;
    LatLng current;
    LatLng nxtcurrent;
    int no_stat;
    String title;
    String nxttitle;
    double statlat;
    double statlon;
    double entime=50;
    Marker ker;
    
   int m1=0;
    
    private final LatLng loc_glasgow = new LatLng(55.86515, -4.28);
 
    private final LatLng loc_kelvin = new LatLng(55.8742, -4.2795);
    private final LatLng loc_geo = new LatLng(55.8714, -4.2687);  
    private final LatLng loc_cow = new LatLng(55.8683, -4.2595); 
    private final LatLng loc_buch = new LatLng(55.8625, -4.2534); 
    private final LatLng loc_enoch = new LatLng(55.8572, -4.2552);
    private final LatLng loc_bridge = new LatLng(55.8519, -4.258);
    private final LatLng loc_west = new LatLng(55.8495, -4.2659);
    private final LatLng loc_sheild = new LatLng(55.8499, -4.2753);
    private final LatLng loc_kin = new LatLng(55.8504, -4.2876);
    private final LatLng loc_cess = new LatLng(55.8524, -4.2949);
    private final LatLng loc_ibrox = new LatLng(55.8546, -4.3046); 
    private final LatLng loc_govan = new LatLng(55.8623, -4.3105);
    private final LatLng loc_part = new LatLng(55.8698, -4.3086);
    private final LatLng loc_hall = new LatLng(55.8709, -4.3);
    private final LatLng loc_hill = new LatLng(55.8752, -4.2934);
    
	 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //sensor initialisation

           
        //asign buttons
        textTime = (TextView) findViewById(R.id.textTime);
        startButton = (Button) findViewById(R.id.b_start);
		location = (Button) findViewById(R.id.b_location);
		
		//set up the map fragment
		map  = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(loc_glasgow,12);
		map.animateCamera(update);
		map.setOnMarkerClickListener(this);
		map.setMyLocationEnabled(false);
		
		//set up the polyline for the whole map
		Polyline line = map.addPolyline(new PolylineOptions()
		.add(loc_kelvin, loc_geo, loc_cow, loc_buch, loc_enoch, loc_bridge, loc_west, loc_sheild, loc_kin, loc_cess, loc_ibrox, loc_govan, loc_part, loc_hall, loc_hill, loc_kelvin)
	    .width(5)
	    .color(Color.BLUE));
		
		//set up custom dialog box
		AlertDialog.Builder builder;
		AlertDialog alert;
		 
		// add markers to the map
		map.addMarker(new MarkerOptions().position(loc_kelvin).title("Kelvinbridge"));
		map.addMarker(new MarkerOptions().position(loc_geo).title("St Georges Cross"));
		map.addMarker(new MarkerOptions().position(loc_cow).title("Cowcaddens"));
		map.addMarker(new MarkerOptions().position(loc_buch).title("Buchanan street"));
		map.addMarker(new MarkerOptions().position(loc_enoch).title("St Enochs"));
		map.addMarker(new MarkerOptions().position(loc_bridge).title("Bridge street"));
		map.addMarker(new MarkerOptions().position(loc_west).title("West street"));
		map.addMarker(new MarkerOptions().position(loc_sheild).title("Sheilds road"));
		map.addMarker(new MarkerOptions().position(loc_kin).title("kinning park"));
		map.addMarker(new MarkerOptions().position(loc_cess).title("Cessnock"));
		map.addMarker(new MarkerOptions().position(loc_ibrox).title("Ibrox"));
		map.addMarker(new MarkerOptions().position(loc_govan).title("Govan"));
		map.addMarker(new MarkerOptions().position(loc_part).title("Partick"));
		map.addMarker(new MarkerOptions().position(loc_hall).title("Kelvinhall"));
		map.addMarker(new MarkerOptions().position(loc_hill).title("Hillhead"));
		
		
		
        startButton.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View view) {
				if(statlat==0)
					
					Toast.makeText(getApplicationContext(), 
			                ( "Please select your locations first" ), Toast.LENGTH_LONG).show();
				else
				startTime = SystemClock.uptimeMillis();
				customHandler.postDelayed(updateTimerThread, 0);
			}	});
	    
    }
    
    private Runnable updateTimerThread = new Runnable() {
		public void run() {		
			timeInMilliseconds = SystemClock.uptimeMillis() - startTime;	
			updatedTime = timeSwapBuff + timeInMilliseconds;
			int secs = (int) (updatedTime / 1000);
			int mins = secs / 60;
			secs = secs % 60;			
			textTime.setText("" + mins + ":"
					+ String.format("%02d", secs));
					customHandler.postDelayed(this, 500);
					
			movemarker(updatedTime);				
		}};
		
		
	public void movemarker(long time)
		{	
			if (time>400 && time<50700) ker.setVisible(false);
			
			if(time<50900)
			{			
				perlat=statlat/100;
				perlon=statlon/100;
				
				/*						  				
				 down 
				 latcur=latcur-perlat;

				right
				
				loncur=loncur-perlon;			  
				  
				  left
				 
				loncur=loncur+perlon;			  
				  */
				
				if(latx1==1)	{loncur=loncur-perlon;}
				else		{	latcur=latcur+perlat;}
				
				if(latx2==1)
				{loncur=loncur-perlon;}
				else
					{loncur=loncur+perlon;}
				
			ker=map.addMarker(new MarkerOptions().position(new LatLng(latcur, -loncur)).visible(true)
						.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));	
			movex++;			
			}			
		}
		

    protected void onResume() {
        super.onResume();       
    }

    protected void onPause() {
        super.onPause();     
    }
	
    
    @Override
    protected void onStop() {
        super.onStop();
       //write your code here to start your service
    }

	public void newLine(LatLng current, LatLng nxtcurrent)
	{
		m1=0;
		//add black polyline between stations
		Polyline line = map.addPolyline(new PolylineOptions()
		.add(current,nxtcurrent)
		.width(10)
	    .color(Color.BLACK));
		
		latcur=current.latitude;
		loncur=current.longitude;
		latnxt=nxtcurrent.latitude;
		lonnxt=nxtcurrent.longitude;
		
		 if( latcur<0) latcur=-latcur;
		 if( loncur<0) loncur=-loncur;
		 if( latnxt<0) latnxt=-latnxt;
		 if( lonnxt<0) lonnxt =-lonnxt;
		
		if(latcur >latnxt){ statlat=latcur-latnxt;
		latx1=1;
		}
		else statlat=latnxt - latcur;
		
		if(loncur >lonnxt){ statlon=loncur-lonnxt;
		latx2=1;}
		
		else statlon=lonnxt- loncur;
				
		String test= Double.toString(statlat);
		String test2= Double.toString(statlon);
		
		Toast.makeText(getApplicationContext(), 
                (test + ":-----:" + test2), Toast.LENGTH_LONG).show();
		 	
		//en_bu=515
		//entime = 50
		
		//map.addMarker(new MarkerOptions().position(new LatLng(55.86, -4.25427)).visible(true)
			//	.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
	}
	
	@Override
	public boolean onMarkerClick(final Marker marker) {
		
		//when marker is clicked call this. set up values and alertbox.
		
		title = marker.getTitle();
		
		if (m1==0)
		{
		current=marker.getPosition();
		}
		if (m1==1)
		{
			nxtcurrent=marker.getPosition();
		}
		   	
			 new AlertDialog.Builder(this)
			    .setTitle(title)
			    .setMessage("Station picked")			   
			    .setNegativeButton("ok", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) {			        			        
			        	 		    			        	 		    	
			        	if (m1==1)
			        	{
			        	newLine(current,nxtcurrent);
			        	}
			        	m1++;		        	 		   
			        	 	   }		        		       	        
			     }).show();	 
		
		return true;
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	public void onclick_loc(View v){
		  OnClickListener listener = new OnClickListener() {			
				@Override
				public void onClick(View v) {
					// Getting the fragment manager 
					FragmentManager manager = getFragmentManager();
					
					// Instantiating the DialogFragment class 
					AlertDialogRadio alert = new AlertDialogRadio();
					
					// Creating a bundle object to store the selected item's index 
					Bundle b  = new Bundle();
					
					// Storing the selected item's index in the bundle object 
					b.putInt("position", position);
					
					// Setting the bundle object to the dialog fragment object 
					alert.setArguments(b);
					
					// Creating the dialog fragment object, which will in turn open the alert dialog window 
					alert.show(manager, "alert_dialog_radio");			
					
				}
			};
			 // Getting the reference of the button from the main layout 
	        Button btn = (Button) findViewById(R.id.b_location);
	        
	        //Setting a button click listener for the choose button 
	        btn.setOnClickListener(listener);          
		
	}
	
	@Override
	public void onPositiveClick(int position) {
		this.position = position;
  
		//move camera and zoom on specific location. 
		
		switch (position) {
	    case 0:	    	
	    	CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(loc_kelvin, 14);
	    	map.animateCamera(yourLocation); 
	    	
	        break;
	     case 1:
		   CameraUpdate yourLocation1 = CameraUpdateFactory.newLatLngZoom(loc_geo, 14);
	    	map.animateCamera(yourLocation1); 
		   break;
	     case 2:	   
		    CameraUpdate yourLocation2 = CameraUpdateFactory.newLatLngZoom(loc_cow, 14);
	    	map.animateCamera(yourLocation2); 
		       break;
		  case 3:
			  CameraUpdate yourLocation3 = CameraUpdateFactory.newLatLngZoom(loc_buch, 14);
		    	map.animateCamera(yourLocation3);
			  break;
		  case 4:
			  CameraUpdate yourLocation4 = CameraUpdateFactory.newLatLngZoom(loc_enoch, 14);
		    	map.animateCamera(yourLocation4);
			   break;
	case 5:
	
		 CameraUpdate yourLocation5 = CameraUpdateFactory.newLatLngZoom(loc_bridge, 14);
	    	map.animateCamera(yourLocation5);
			  break;
	 case 6:
		 CameraUpdate yourLocation6 = CameraUpdateFactory.newLatLngZoom(loc_west, 14);
	    	map.animateCamera(yourLocation6);
				 break;
	 case 7:
		 CameraUpdate yourLocation7 = CameraUpdateFactory.newLatLngZoom(loc_sheild, 14);
	    	map.animateCamera(yourLocation7);
					   break;
	 case 8:
		 CameraUpdate yourLocation8 = CameraUpdateFactory.newLatLngZoom(loc_kin, 14);
	    	map.animateCamera(yourLocation8);
				 break;			 
	 case 9:
		 CameraUpdate yourLocation9 = CameraUpdateFactory.newLatLngZoom(loc_cess, 14);
	    	map.animateCamera(yourLocation9);
				 break;	 
	 case 10:
		 CameraUpdate yourLocation10 = CameraUpdateFactory.newLatLngZoom(loc_ibrox, 14);
	    	map.animateCamera(yourLocation10);
				 break;
	 case 11:
		 CameraUpdate yourLocation11 = CameraUpdateFactory.newLatLngZoom(loc_govan, 14);
	    	map.animateCamera(yourLocation11);
				 break;
	case 12:
		 CameraUpdate yourLocation12 = CameraUpdateFactory.newLatLngZoom(loc_part, 14);
	    	map.animateCamera(yourLocation12);
				 break;
	 case 13:
		 CameraUpdate yourLocation13 = CameraUpdateFactory.newLatLngZoom(loc_hall, 14);
	    	map.animateCamera(yourLocation13);
				 break;
	 case 14:
		 CameraUpdate yourLocation14 = CameraUpdateFactory.newLatLngZoom(loc_hill, 14);
	    	map.animateCamera(yourLocation14);
				 break ;  	        
	    }	
		
	}
}