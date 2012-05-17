package fi.dy.esav.lines;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
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
	
	public void resume() {
		running = true;
		renderThread = new Thread(this);
		renderThread.start();
	}
	
	public void pause() {
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
		
		long startTime = System.currentTimeMillis();
		long endTime;
		long dt;
		
		float r = 0;
		
		while(running) {
			if(!holder.getSurface().isValid()) {
				continue;
			}
			
			Canvas canvas = holder.lockCanvas();
			// Do the drawing
			canvas.drawRGB( (int)r, 0, 0);
			r += 0.05;
			
			
			endTime = System.currentTimeMillis();
		    dt = endTime - startTime;
		    Log.d("Lines", String.valueOf(dt));
		    if (dt < 20) {
				try {
					Thread.sleep(20 - dt);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    startTime = System.currentTimeMillis();
			
			holder.unlockCanvasAndPost(canvas);
		}
	}



}
