package fi.dy.esav.lines;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
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
		
		Point center = new Point(this.getWidth()/2, this.getHeight()/2);
		
		double halfscreen_len = Math.min(this.getWidth(), this.getHeight())/2;
		
		double line1_len = halfscreen_len / 3 * 2;
		double line1_ang = 15;
		double line1_inc = 0.1;
		float line1_pts[] = {center.x, center.y, (float) (center.x + line1_len * Math.cos(line1_ang)), (float) (center.y + line1_len * Math.sin(line1_ang))};
		
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);

		while(running) {
			if(!holder.getSurface().isValid()) {
				continue;
			}
			
			Canvas canvas = holder.lockCanvas();
			
			canvas.drawLines(line1_pts, paint);
			
			endTime = System.currentTimeMillis();
		    dt = endTime - startTime;
		    
		    line1_ang += line1_inc;
		    line1_pts[2] = (float) (center.x + line1_len * Math.cos(line1_ang));
		    line1_pts[3] = (float) (center.y + line1_len * Math.sin(line1_ang));
		    
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
