package downtoearth.gameUtil;

import downtoearth.world.worldGen.NoiseGen;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JPanel;

@SuppressWarnings( "serial" )
public class DrawPanel extends JPanel implements MouseListener, ComponentListener
{
	private transient Map<Point, Image> map;
	private NoiseGen generator;
	
	public DrawPanel( NoiseGen generator )
	{
		super();
		setBackground( Color.black );
		addMouseListener( this );
		addComponentListener( this );
		
		this.generator = generator;
		map = generator.getMap();
		
		new Thread( () -> refresh() ).start();
	}
	
	public void refresh()
	{
		for( ;; )
			try
			{
				Thread.sleep( 16 );
				repaint();
			}
			catch( InterruptedException e )
			{
				//care
			}
	}
	
	@Override
	public void paintComponents( Graphics g )
	{
		super.paintComponents( g );
		
		if( generator.isRunning() )
		{
			Point point;
			Image image;
			
			for( Entry<Point, Image> entry: map.entrySet() )
			{
				point = entry.getKey();
				image = entry.getValue();
				
				g.drawImage( image, point.x, point.y, null );
			}
		}
		else
		{
			Graphics2D gfx = (Graphics2D) g;
			gfx.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
			
			gfx.setColor( Color.white );
			gfx.setFont( new Font( "Calibri", Font.PLAIN, 30 ) );
			gfx.drawString( "Click anywhere to start the generator", 10, 40 );
		}
	}
	
	@Override
	public void mouseClicked( MouseEvent e )
	{

		generator.start();
		generator.resized(960, 960);
		//generator.reset();
	}
	
	@Override
	public void componentResized( ComponentEvent e )
	{
		//generator.resized( getWidth(), getHeight() );
	}
	
	@Override
	public void componentMoved( ComponentEvent e )
	{}
	
	@Override
	public void componentShown( ComponentEvent e )
	{}
	
	@Override
	public void componentHidden( ComponentEvent e )
	{}
	
	@Override
	public void mousePressed( MouseEvent e )
	{}
	
	@Override
	public void mouseReleased( MouseEvent e )
	{}
	
	@Override
	public void mouseEntered( MouseEvent e )
	{}
	
	@Override
	public void mouseExited( MouseEvent e )
	{}
}
