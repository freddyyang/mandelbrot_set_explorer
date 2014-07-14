import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

// imports go here

/**
 * This class provides a view of a MandelBrot set Model object and responds to
 * mouse clicks, which indicate what subregion the user wishes to select.
 * 
 * @author Freddy Yang & Yixin Wang
 */

public class ImageJPanel extends JPanel implements MouseListener
{
    private final Model model;
    private ControlJPanel controlJPanel;
    private int imageSize;
    private BufferedImage bufferedImage;
    
    // constructor
    ImageJPanel( Model model ) 
    { 
        this.model = model;
        initialize(); 
    }
    
    
    @Override
    public void paintComponent( Graphics graphics )
    {
        // paint bufferedImage
        // have the controlJPanel display current attribute values of model that produced the image.
        super.paintComponent( graphics );
        graphics.drawImage( model.getImage(), 0, 0, imageSize, imageSize, this);
        controlJPanel.view();
    }
        

    void setImageSize(int imageSize){        this.imageSize = imageSize;}
    
	
    //  Mouselistener implementation goes here

    //  Mouselistener implementation
    @Override
    public void mousePressed( MouseEvent mouseEvent ) {}

    @Override
    public void mouseReleased( MouseEvent mouseEvent ) {}

    @Override
    public void mouseEntered( MouseEvent mouseEvent ) {}

    @Override
    public void mouseExited( MouseEvent mouseEvent ) {}

    @Override
    public void mouseClicked( MouseEvent mouseEvent )
    {
        controlJPanel.zoom( mouseEvent.getX(), mouseEvent.getY() );
        repaint();
    }    
    
    void setImage( BufferedImage bufferedImage )
    {
        // set bufferedImage attribute & repaint; this is a "publish" method.
    	this.bufferedImage = bufferedImage;
    	repaint();
    }
    // your implementation of setControlJPanel goes here. Its a simple "set" method.
    void setControlJPanel(ControlJPanel controlJPanel){this.controlJPanel= controlJPanel;}
    
    // perform initialization, when the application launches.    
    private void initialize()
    { 
        // add this as a MouseListener
        addMouseListener( this ); // add this to the set of objects listening for MouseEvent objects.
        // set imageSize (get from Model)
        imageSize = model.getNumPixels();
        // initialize bufferedImage (get from model reference)
        bufferedImage = model.bufferedImage; //silknotsure
        repaint();
    }    
}
