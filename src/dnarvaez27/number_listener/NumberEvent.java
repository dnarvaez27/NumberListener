package dnarvaez27.number_listener;

import java.awt.event.KeyEvent;

public class NumberEvent
{
	private char ch;
	
	private boolean menos;
	
	private Number number;
	
	private boolean punto;
	
	public NumberEvent( boolean isDouble, String c )
	{
		ch = c.charAt( 0 );
		punto = ch == KeyEvent.VK_PERIOD;
		menos = ch == KeyEvent.VK_MINUS;
		if( !punto && !menos )
		{
			number = isDouble ? Double.parseDouble( c ) : Integer.parseInt( c );
		}
		else
		{
			number = 0;
		}
	}
	
	public Number getNumber( )
	{
		return number;
	}
	
	public boolean isMenos( )
	{
		return menos;
	}
	
	public boolean isPunto( )
	{
		return punto;
	}
	
	@Override
	public String toString( )
	{
		if( punto || menos )
		{
			return String.valueOf( ch );
		}
		else
		{
			return number.toString( );
		}
	}
}