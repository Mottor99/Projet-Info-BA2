package Model;

public interface LevelSwitch {
	void attachLevelSwitch(LevelSwitchObserver o);
	void notifyLevelSwitchObservers();
}
