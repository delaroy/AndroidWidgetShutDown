package com.widgetShutDown;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

public class ScreenOff extends Activity {
	Handler hand = new Handler();
	public boolean isPowerOff;
	private PowerManager pm;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity);
		isPowerOff = powerOff();
	}
	
	// this set the brightness, this will work only in activity
	public void lockScreen(float flo){
    	Window mywindow = getWindow();
        WindowManager.LayoutParams lp = mywindow.getAttributes();
        lp.screenBrightness = flo;
        mywindow.setAttributes(lp);
    }
	
	public void unloockScreen(int millis) {
		Runnable runner = new Runnable() {
			public void run() {
				lockScreen(100.0f);
			}
		};
		hand.postDelayed(runner, millis);
	}
	
	public boolean powerOff() {
		PowerManager pm = (PowerManager)getSystemService(POWER_SERVICE);
		PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "My_App");
		wl.acquire();
		return true;
	}
}
