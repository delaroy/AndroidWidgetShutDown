package com.widgetShutDown;

import java.io.IOException;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.widget.RemoteViews;

public class WidgetShutDown extends AppWidgetProvider{
	PendingIntent pIntent;
	RemoteViews remote;
	PowerManager.WakeLock wl;
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		remote = new RemoteViews(context.getPackageName(), R.layout.main);
		
		for(int i=0;i<appWidgetIds.length;i++) {
			int widgetId = appWidgetIds[i];
			// If we want to start an action on click we do the next lines
		    Intent intent = new Intent(context, WidgetShutDown.class);
		    
		    // If we want to start an activity on click we do the following
		    intent = new Intent("com.shutdown");
	        pIntent = PendingIntent.getActivity(context, 0, intent, 0);
	        remote.setOnClickPendingIntent(R.id.btnShutDown, pIntent);
	        
//	        Set the general action to be performed.
	        intent = new Intent(context, WidgetShutDown.class);
	        intent.setAction("Restart");
//	        Retrieve a PendingIntent that will perform a broadcast in this widget
	        pIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
	        remote.setOnClickPendingIntent(R.id.btnRestart, pIntent);
	        
//	        intent = new Intent("com.restart");
//	        pIntent = PendingIntent.getActivity(context, 0, intent, 0);
//	        remote.setOnClickPendingIntent(R.id.btnRestart, pIntent);
	        
	        intent = new Intent("com.screenOff");
	        pIntent = PendingIntent.getActivity(context, 0, intent, 0);
	        remote.setOnClickPendingIntent(R.id.btnScreenOff, pIntent);
	        // Tell the AppWidgetManager to perform an update on the current App  
	        appWidgetManager.updateAppWidget(widgetId, remote); 
		}
	}
	
	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		try {
			Process localProcess = Runtime.getRuntime().exec("su");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		// Here we receive the broadcast.
		if (intent.getAction().equals("Restart")) {
			globals.reboot("reboot");
		}
	}

}