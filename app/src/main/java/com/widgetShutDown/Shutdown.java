package com.widgetShutDown;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Shutdown extends Activity{
	
	public void onCreate(Bundle save) {
		super.onCreate(save);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity);
		lockScreen();
	}
	
	public void onStop() {
		super.onStop();
		finish();
	}
	
	public void lockScreen(){
    	Window mywindow = getWindow();
        WindowManager.LayoutParams lp = mywindow.getAttributes();
        lp.screenBrightness = 0.0f;
        mywindow.setAttributes(lp);
        if(lp.screenBrightness == 0.0f) {
        	Runnable runner = new Runnable() {
        		public void run() {
        			globals.reboot("reboot -p \n exit \n");
        		}
        	};
        	globals.hand.postDelayed(runner, 1000);
        }
    }
}
