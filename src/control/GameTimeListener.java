package control;

public interface GameTimeListener {

	public void timeOut();

	public void onChange(long sec);
}
