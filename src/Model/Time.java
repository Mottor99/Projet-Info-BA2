package Model;

public class Time {
	private static int day;
	private static int min;
	private static int hour;
	private static int speed;
	private static int counter = 0;

	public Time(int day, int min, int hour, int speed) {
		Time.day = day;
		Time.min = min;
		Time.hour = hour;
		Time.speed = speed;
	}
	
	public void update() {
		counter += speed;
		if (counter > 1000) {
			min ++;
			counter = 0;
		}
		if (min == 60) {
			min = 0;
			hour++;
		}
		if (hour == 24) {
			hour = 0;
			day++;
		}
		
		
	}

	public static int getDay() {
		return day;
	}

	public static int getMin() {
		return min;
	}

	public static int getHour() {
		return hour;
	}
	
	
}
