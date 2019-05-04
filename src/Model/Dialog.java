package Model;

public interface Dialog {
	void talk();
	boolean isTalking();
	String getCurrentSentence();
	void nextSentence();
}
