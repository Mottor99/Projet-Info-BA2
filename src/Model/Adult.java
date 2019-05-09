package Model;

public class Adult extends NPC implements Tire, Hunger, Hygiene, Bladder{
	
	private int energy = 100;
	private int hunger = 100;
	private int bladder = 100;
	private int hygiene = 100;


	public Adult(int x, int y, String s) {
		super (x, y, 1, 1);
		switch (s) {
		case "male" : sprite = Sprite.man; break;
		case "female" : sprite = Sprite.woman; break;
		}
		
		
	} 
	public void growTire(Game g) {

		if (energy > 10)
			energy -= 0.4;
		else {
			g.sendPlayerToObject("Bed");
			//g.sendPlayerToObject(Bed.class);
		}
	}
	public void growHunger(Game g) {
		if (hunger > 20) {
			hunger -= 0.2;
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
	public void growDirt(Game g) {
		if (hygiene > 20) {
			hygiene -= 0.1;
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
	public int getNeedState() {
		// TODO Auto-generated method stub
		return -1;
	}
}
	
	

