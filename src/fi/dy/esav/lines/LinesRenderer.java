package fi.dy.esav.lines;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class LinesRenderer extends SurfaceView implements Runnable {

	Thread renderThread = null;
	SurfaceHolder holder;
	volatile boolean running;
	
	public LinesRenderer(Context context) {
		super(context);
		holder = this.getHolder();
	}
	
	public void onResume() {
		running = true;
		renderThread = new Thread(this);
		renderThread.start();
	}
	
	public void onPause() {
		running = false;
		while(true) {
			try {
				renderThread.join();
				return;
			} catch(InterruptedException e) {
				// retry
			}
		}
	}
	
	public void run() {
		while(running) {
			
		}
	}



}
