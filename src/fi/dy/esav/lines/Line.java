/**
 * 
 */
package fi.dy.esav.lines;

import android.graphics.Point;

/**
 * @author Oppilas
 *
 */
public class Line {
	Point start;
	
	float length;
	double angle;
	double angleIncrement;
	
	public Line(Point start, float length, float angle, double d) {
		this.start = start;
		this.length = length;
		this.angle = angle;
		this.angleIncrement = d;
	}
	
	float[] getPoints() {
		float points[] = {start.x, start.y, (float) (start.x + length * Math.cos(angle)), (float) (start.y + length * Math.sin(angle))};
		return points;
	}
	
	void incrementAngle() {
		angle += angleIncrement;
	}
}
