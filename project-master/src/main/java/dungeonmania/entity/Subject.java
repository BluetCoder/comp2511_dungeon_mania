package dungeonmania.entity;

public interface Subject {
	/**
	 * Attach an enemy that is an observer
	 * 
	 * @param observer
	 */

	public void attach(EnemyObserver observer);

	/**
	 * Detach an enemy that is an observer
	 * 
	 * @param observer
	 */

	public void detach(EnemyObserver observer);

	/**
	 * Notifies enemies to move when player moves
	 */
	public void notifyObservers();
}
