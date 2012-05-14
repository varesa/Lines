package fi.dy.esav.lines;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class LinesActivity extends Activity {
	
	LinesRenderer renderer;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        renderer = new LinesRenderer(this);
        setContentView(renderer);
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	renderer.resume();
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    	renderer.pause();
    }
}