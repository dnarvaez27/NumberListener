package dnarvaez27.number_listener;

import javax.swing.text.JTextComponent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Listener class used for number events on JTextComponent<br>
 * This class must be instanciated to be used.
 * Cannot be extended or implemented
 *
 * @author d.narvaez11
 */
public final class NumberListener implements KeyListener
{
	/**
	 * Verifies that the number is either a Double or an Integer number
	 */
	private boolean isDouble;
	
	/**
	 * Verifies that the number is either positive or negative
	 */
	private boolean negative;
	
	/**
	 * Verifies that the class implementing the interface {@link NumberEventListener} should be notified
	 */
	private boolean notifies;
	
	/**
	 * The interface to be notified if required
	 */
	private NumberEventListener numberEventListener;
	
	/**
	 * BETA. Text containing what the textComponent should have
	 */
	private String text;
	
	/**
	 * TextComponent implementing the NumberListener
	 */
	private JTextComponent textComponent;
	
	public NumberListener( )
	{
		this( false );
	}
	
	/**
	 * Basic constructor for the NumberListener
	 *
	 * @param isDouble Tells whether or not the number is a Double
	 */
	public NumberListener( boolean isDouble )
	{
		text = new String( );
		this.isDouble = isDouble;
	}
	
	/**
	 * Constructor for the NumberListener
	 *
	 * @param isDouble Tells whether or not the number is a Double
	 * @param textComponent TextComponent to add the NumberListener
	 */
	public NumberListener( boolean isDouble, JTextComponent textComponent )
	{
		text = new String( );
		this.isDouble = isDouble;
		this.textComponent = textComponent;
	}
	
	// public static void main( String[ ] args )
	// {
	// // JFT
	// JFrame j = new JFrame( "Hola" );
	// JTextField f = new JTextField( );
	// NumberListener nl = new NumberListener( true );
	// nl.allowNegativeValues( false );
	// nl.getNotified( n -> System.out.println( n ) );
	// f.addKeyListener( nl );
	//
	// j.add( f );
	//
	// j.setSize( 300, 300 );
	// j.setLocationRelativeTo( null );
	// j.setVisible( true );
	// j.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	// }
	
	/**
	 * Allows the field to be negative or not
	 *
	 * @param allowNegatives True if negative numbers are allowed, False otherwise
	 */
	public void allowNegativeValues( boolean allowNegatives )
	{
		negative = allowNegatives;
	}
	
	/**
	 * Enable the listener to notify a {@link NumberEventListener} in case of number typing
	 *
	 * @param numberEventListener The class implementing the interface
	 */
	public void getNotified( NumberEventListener numberEventListener )
	{
		notifies = numberEventListener != null ? true : false;
		this.numberEventListener = numberEventListener;
	}
	
	@Override
	public void keyPressed( KeyEvent e )
	{
		
	}
	
	@Override
	public void keyReleased( KeyEvent e )
	{
		
	}
	
	@Override
	public void keyTyped( KeyEvent e )
	{
		boolean isNumber = true;
		
		char c = e.getKeyChar( );
		
		boolean range = ( c >= '0' ) && ( c <= '9' );
		boolean space = ( c == KeyEvent.VK_BACK_SPACE );
		boolean delete = ( c == KeyEvent.VK_DELETE );
		boolean punto = ( c == KeyEvent.VK_PERIOD );
		boolean neg = ( c == KeyEvent.VK_MINUS );
		
		if( ( !isDouble && punto ) || ( !negative && neg ) )
		{
			isNumber = false;
			e.consume( );
		}
		
		boolean notNull = textComponent != null;
		boolean isEmpty = notNull && textComponent.getText( ).isEmpty( );
		boolean isPunto = notNull && textComponent.getText( ).contains( "." );
		boolean isMinus = notNull && textComponent.getText( ).contains( "-" );
		boolean primer = ( isEmpty || text.isEmpty( ) ) && punto;
		boolean segundo = ( isPunto || text.contains( "." ) ) && punto;
		boolean tercero = ( isMinus || text.contains( "-" ) ) && neg;
		
		if( primer || segundo || tercero )
		{
			isNumber = false;
			e.consume( );
		}
		
		if( delete || space )
		{
			int len = text.length( ) - 1;
			text = text.substring( 0, len > 0 ? len : 0 );
		}
		else if( isNumber )
		{
			text += c;
		}
		
		if( !( range || space || delete || punto || neg ) )
		{
			isNumber = false;
			e.consume( );
		}
		
		if( isNumber && ( range || ( isDouble && punto ) || ( negative && neg ) ) )
		{
			notifyListener( String.valueOf( c ) );
		}
	}
	
	/**
	 * Sets the component for which the listener is added
	 *
	 * @param textComponent component for which the listener is added
	 * @return this instance
	 */
	public NumberListener setComponent( JTextComponent textComponent )
	{
		this.textComponent = textComponent;
		
		return this;
	}
	
	private void notifyListener( String n )
	{
		if( notifies )
		{
			numberEventListener.numberTyped( new NumberEvent( isDouble, n ) );
		}
	}
}