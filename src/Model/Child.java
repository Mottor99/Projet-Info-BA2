package Model;

public class Child extends NPC implements Tire, Hunger, Hygiene, Bladder{
	
	private int energy = 100;
	private int hunger = 100;
	private int bladder = 100;
	private int hygiene = 100;
	


	public Child(int x, int y, String s) {
		super (x, y, 1, 1);
		switch (s) {
		case "male" : sprite = Sprite.boy; break;
		case "female" : sprite = Sprite.girl; break;
		}
		
		
		
	}
	public void tire(Game g) {

		if (energy > 10)
			energy -= 0.2;
		else {
			g.sendPlayerToObject("Bed");
			//g.sendPlayerToObject(Bed.class);
		}
	}
	public void growHunger(Game g) {
		if (hunger > 20) {
			hunger -= 0.1;
		}
		else {
			g.sendPlayerToObject("Fridge");
			//g.sendPlayerToObject(Fridge.class);
		}
	}
	public void growBladder(Game g) {
		if (bladder > 20) {
			bladder -= 0.1;
		}
		else {
			g.sendPlayerToObject("Toilet");
			//g.sendPlayerToObject(Toilet.class);
		}
	}
	public void growHygiene(Game g) {
		if (hygiene > 20) {
			hygiene -= 0.2;
		}
		else {
			g.sendPlayerToObject("Shower");
			//g.sendPlayerToObject(Shower.class);
		}
	}
	

	public double getHygiene() {
		return hygiene/100.0;
	}

	public void setHygiene(int hygiene) {
		this.hygiene = hygiene;
	}


	public double getBladder() {
		return bladder/100.0;
	}

	public void setBladder(int bladder) {
		this.bladder = bladder;
	}





	public double getHunger() {
		return hunger/100.0;
	}





	public void setHunger(int hunger) {
		this.hunger = hunger;
	}





	public double getEnergy() {
    	return energy/100.0;
    }
    

	public void setEnergy(int energy) {
		this.energy = energy;
	}
	@Override
	public void animate() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}