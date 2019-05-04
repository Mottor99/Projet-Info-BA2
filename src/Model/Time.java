package Model;

public class Time {
	private static int day;
	private static int min;
	private static int hour;
	private static int speed;
	private static int counter = 0;
	private Game game;


	public Time(Game game, int day, int min, int hour, int speed) {
		Time.day = day;
		Time.min = min;
		Time.hour = hour;
		Time.speed = speed;
		this.game = game;
		
	}
	
	public void update() {
		counter += speed;
		if (counter > 1000) {
			min ++;
			if (min%10==0) {
				//game.tirePlayer();
			}
			if (min%4==0) {
				//game.growHunger();
			}
			if (min%3==0) {
				//game.growBladder();
			}
			counter = 0;
			
		}
		
		if (min >= 60) {
			min = 0;
			hour++;
			
		}
		if (hour >= 24) {
			hour = 0;
			day++;
			
		}
		
		
	}

	public static String getDay() {
		return String.valueOf(day);
	}

	public static String getMin() {
		String res = "";
		if (min<10) {
			res+="0";
		}
		res+=String.valueOf(min);
		return res;
	}

	public static String getHour() {
		String res = "";
		if (hour<10) {
			res+="0";
		}
		res+=String.valueOf(hour);
		return res;
	}
	
	
}
