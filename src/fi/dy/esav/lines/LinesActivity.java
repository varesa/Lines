package fi.dy.esav.lines;

import android.app.Activity;
import android.os.Bundle;

public class LinesActivity extends Activity {
	
	LinesRenderer renderer;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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