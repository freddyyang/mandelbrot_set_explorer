import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Pete Cappello
 */
public class App extends JFrame
{
    private static final int NUM_PIXELS = 400;
    private Model model;
    
    private ImageJPanel imageJPanel;
    private ControlJPanel controlJPanel;
                
    App() 
    {
        setTitle( "Mandelbrot Set Visualization" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        Model.NUM_PIXELS = NUM_PIXELS;
        model = new Model();
        imageJPanel = new ImageJPanel( model );
        model.setImageJPanel( imageJPanel) ;
        controlJPanel = new ControlJPanel( model, imageJPanel );
        imageJPanel.setControlJPanel( controlJPanel );
                
        add( imageJPanel, BorderLayout.CENTER );
        add( controlJPanel, BorderLayout.SOUTH );
        
        setSize( NUM_PIXELS, NUM_PIXELS + 300 );
        setPreferredSize( new Dimension(NUM_PIXELS, NUM_PIXELS + 300 ) );
        setVisible( true );
    }
    
    /**
     * Run the Mandelbrot set visualization application.
     * @param args unused 
     */
    public static void main( String[] args ) { App app = new App(); }
}