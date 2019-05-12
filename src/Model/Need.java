package Model;

public interface Need {
	final int NOTHING = -1;
	final int SLEEPING = 0;
	final int WASHING = 1;
	final int EATING = 2;
	final int PEEING = 3;
	final int PLAYING = 4;
	int getNeedState();
}
