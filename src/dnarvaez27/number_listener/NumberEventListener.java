package dnarvaez27.number_listener;

/**
 * Interface enabling to be notified when a NumberEvent occurs on {@link NumberListener}
 *
 * @author d.narvaez11
 */
public interface NumberEventListener
{
	/**
	 * Invoked when a NumberEvent from {@link NumberListener} occurs
	 */
	public void numberTyped( NumberEvent n );
}