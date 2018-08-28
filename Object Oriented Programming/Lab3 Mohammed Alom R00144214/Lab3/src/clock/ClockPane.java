package clock;

/**
 * Name - Mohammed Jahanigr Alom
 * Student No - R00144214
 * Lab No 3
 */

import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class ClockPane extends Pane {

	private int hour;
	private int minute;
	private int second;

	private double W = 350;
	private double H = 350;

	// 1st Constructor
	public ClockPane() {
		setCurrentTime();
	}

	/**
	 * This method will set the current time 
	 */
	private void setCurrentTime() {
		Calendar calendar = new GregorianCalendar();

		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);

		printClock();
	}
	
	/**
	 * This method will display clock on the screen 
	 */

	protected void printClock() {

		double clockRadius = Math.min(W, H) * 0.8 * 0.5;
		double centerX = W / 2;
		double centerY = H / 2;

		Circle circle = new Circle(centerX, centerY, clockRadius);
		circle.setFill(Color.WHITE);
		circle.setStroke(Color.BLACK);

		Text t1 = new Text(centerX + clockRadius / 2 - 8, centerY - clockRadius / 2 * Math.sqrt(3) + 12, "1");
		Text t2 = new Text(centerX + clockRadius / 2 * Math.sqrt(3) - 10, centerY - clockRadius / 2 + 8, "2");
		Text t3 = new Text(centerX + clockRadius - 10, centerY + 5, "3");
		Text t4 = new Text(centerX + clockRadius / 2 * Math.sqrt(3) - 10, centerY + clockRadius / 2 + 2, "4");
		Text t5 = new Text(centerX + clockRadius / 2 - 8, centerY + clockRadius / 2 * Math.sqrt(3) - 2, "5");
		Text t6 = new Text(centerX - 3, centerY + clockRadius - 5, "6");
		Text t7 = new Text(centerX - clockRadius / 2 + 3, centerY + clockRadius / 2 * Math.sqrt(3) - 2, "7");
		Text t8 = new Text(centerX - clockRadius / 2 * Math.sqrt(3) + 7, centerY + clockRadius / 2 + 2, "8");
		Text t9 = new Text(centerX - clockRadius + 5, centerY + 5, "9");
		Text t10 = new Text(centerX - clockRadius / 2 * Math.sqrt(3) + 2, centerY - clockRadius / 2 + 8, "10");
		Text t11 = new Text(centerX - clockRadius / 2 - 3, centerY - clockRadius / 2 * Math.sqrt(3) + 14, "11");
		Text t12 = new Text(centerX - 7, centerY - clockRadius + 15, "12");

		// Second Line
		double sLength = clockRadius * 0.9;
		double xSecond = centerX + sLength * Math.sin(second * (2 * Math.PI / 60));
		double ySecond = centerY - sLength * Math.cos(second * (2 * Math.PI / 60));
		Line sLine = new Line(centerX, centerY, xSecond, ySecond);
		sLine.setStroke(Color.RED);

		// Minute Line
		double mLength = clockRadius * 0.75;
		double xMinute = centerX + mLength * Math.sin(minute * (2 * Math.PI / 60));
		double yMinute = centerY - mLength * Math.cos(minute * (2 * Math.PI / 60));
		Line mLine = new Line(centerX, centerY, xMinute, yMinute);
		mLine.setStroke(Color.BLACK);
		mLine.setStrokeWidth(2);

		// Hour Line
		double hLength = clockRadius * 0.5;
		double xHour = centerX + hLength * Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
		double yHour = centerY - hLength * Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
		Line hLine = new Line(centerX, centerY, xHour, yHour);
		hLine.setStroke(Color.BLACK);
		hLine.setStrokeWidth(3);

		getChildren().clear();
		getChildren().addAll(circle, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, sLine, mLine, hLine);

	}//end of printClock() method

	// 2nd Constructor
	public ClockPane(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;

		printClock();
	}

	//Getter and Setter
	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
		printClock();
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
		printClock();
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
		printClock();
	}

	public double getW() {
		return W;
	}

	public void setW(double W) {
		this.W = W;
		printClock();
	}

	public double getH() {
		return H;
	}

	public void setH(double H) {
		this.H = H;
		printClock();
	}

}
