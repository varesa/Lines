package fi.dy.esav.lines;

import android.app.Activity;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

public class LinesActivity extends Activity {
	
	LinesRenderer renderer;
	WakeLock wakelock;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        PowerManager pm = (PowerManager) this.getSystemService(POWER_SERVICE);
        wakelock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "Lines");
        
        renderer = new LinesRenderer(this);
        setContentView(renderer);
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	wakelock.acquire();
    	renderer.resume();
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    	renderer.pause();
    	wakelock.release();
    }
}