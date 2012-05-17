/**
 * 
 */
package fi.dy.esav.lines;

import android.graphics.Point;
import android.graphics.PointF;

/**
 * @author Oppilas
 *
 */
public class Line {
	PointF start;
	PointF end;
	
	float length;
	double angle;
	double angleIncrement;
	
	public Line(PointF center, float length, float angle, double d) {
		this.start = center;
		this.length = length;
		this.angle = angle;
		this.angleIncrement = d;
		end = new PointF();
		
		getPoints(); // Run to initialize the variable 'end' with correct values
	}
	
	float[] getPoints() {
		end.x = (float) (start.x + length * Math.cos(angle));
		end.y = (float) (start.y + length * Math.sin(angle));
		float points[] = {start.x, start.y, end.x, end.y};
		return points;
	}
	
	void incrementAngle() {
		angle += angleIncrement;
	}
}
