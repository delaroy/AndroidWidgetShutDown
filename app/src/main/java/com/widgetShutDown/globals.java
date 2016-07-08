package com.widgetShutDown;

import java.io.DataOutputStream;
import java.io.IOException;

import android.os.Handler;

public class globals {
	protected static Handler hand = new Handler();
	public static void reboot(String str) {
		try
   	    {
   	      Process localProcess = Runtime.getRuntime().exec("su");
   	      DataOutputStream localDataOutputStream = new DataOutputStream(localProcess.getOutputStream());
   	      localDataOutputStream.writeBytes(str);
   	      localDataOutputStream.flush();
   	      localProcess.waitFor();
   	      return;
   	    }
   	    catch (IOException localIOException)
   	    {
   	      while (true)
   	        localIOException.printStackTrace();
   	    }
   	    catch (InterruptedException localInterruptedException)
   	    {
   	      while (true)
   	        localInterruptedException.printStackTrace();
   	    }
	}
}
