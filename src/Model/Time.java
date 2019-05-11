package Model;

import java.io.Serializable;

public class Time implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int day;
	private int min;
	private int hour;
	private int speed;
	private int counter = 0;
	private Game game;



	public Time(Game game, int day, int min, int hour, int speed) {
		this.day = day;
		this.min = min;
		this.hour = hour;
		this.speed = speed;
		this.game = game;
		
	}
	public Time() {
		
	}
	
	public void update() {
		counter += speed;
		if (counter > 1000) {
			min ++;
			game.tic();
			/*if (min%10==0) {
				//game.tirePlayer();
			}
			if (min%4==0) {
				//game.growHunger();
			}
			if (min%3==0) {
				//game.growBladder();
				//game.moreMoney();
			}
			if (min%10==0) {
				//game.growHygiene();
				
			}
			*/
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

	public String getDay() {
		return String.valueOf(day);
	}

	public String getMin() {
		String res = "";
		if (min<10) {
			res+="0";
		}
		res+=String.valueOf(min);
		return res;
	}

	public String getHour() {
		String res = "";
		if (hour<10) {
			res+="0";
		}
		res+=String.valueOf(hour);
		return res;
	}
	
	public void accelerate() {
		speed = 1000;
		
	}

	public void decelerates() {
		speed = 100;
	}
	
	
}
