package fi.dy.esav.lines;

import android.content.Context;
import android.graphics.Canvas;
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
		while(running) {
			if(!holder.getSurface().isValid()) {
				continue;
			}
			
			Canvas canvas = holder.lockCanvas();
			// Do the drawing
			holder.unlockCanvasAndPost(canvas);
		}
	}



}
