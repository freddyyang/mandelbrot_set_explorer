
import java.awt.*;
import java.awt.image.BufferedImage;
// imports go here

/**
 * A model for visualizing an approximation of the Mandelbrot set.
 * 
 * @author Freddy Yang & Yixin Wang
 */
final class Model implements Cloneable
{
    // class view attribute
    public static int NUM_PIXELS;
    
    // default attribute values
    private static final double CENTER_X = -0.7440975859375;
    private static final double CENTER_Y = 0.1385680625;
    private static final double EDGE_LENGTH = 0.01611;
    private static final int    ITERATION_LIMIT = 512;
    
    // constants used in getIterationCount method
    private static final double ONE_FOURTH = 1.0 / 4.0;
    private static final double ONE_SIXTEENTH = ONE_FOURTH * ONE_FOURTH;

    // model attributes initialized to default values
    private double centerReal = CENTER_X;
    private double centerImag = CENTER_Y;
    private double edgeLength = EDGE_LENGTH;
    private int    iterationLimit = ITERATION_LIMIT;
    
    // publish BufferedImage objects to imageJPanel
    public ImageJPanel imageJPanel;
    
    // computed attribute
    public BufferedImage bufferedImage;
    
    //what is this? silk why another Model()
   /* Model( int numPixels )
    {
        this.numPixels = numPixels;
        
        // initialize the BufferedImage
        bufferedImage = new BufferedImage( numPixels, numPixels, BufferedImage.TYPE_INT_ARGB );
        setImage();
    }
*/
    Model(){};
    Model( double centerReal, double centerImag, double edgeLength, int iterationLimit )
    {
        this.centerReal = centerReal;
        this.centerImag = centerImag;
        this.edgeLength = edgeLength;
        this.iterationLimit = iterationLimit;
    }
        
    /**
     * Clone this object. silk
     * @return the cloned Model object.
    */
    @Override
    protected Model clone()
    {
        Model model = null;
        try
        {
            model = (Model) super.clone();
        }
        catch ( CloneNotSupportedException cannotHappen ) {}
        return model;
    }

    // declare getters for basic attributes
    public double getCenterReal(){
        return centerReal;
    }

    public double getCenterImag(){
        return centerImag;
    }

    public double getEdgeLength(){
        return edgeLength;
    }

    public int getIterationLimit(){
        return iterationLimit;
    }


	public int getNumPixels() {
		// TODO Auto-generated method stub
		return NUM_PIXELS;
	}
	
	
	
	
    // declare setters for basic attributes, 
    //         for each, after setting, invoke setImage() 
    //         to update image and publish to ImageJPanel

	public void setCenterReal(double value) {
		// TODO Auto-generated method stub
		this.centerReal = value;
		setImage();
	}

	public void setCenterImag(double value) {
		// TODO Auto-generated method stub
		centerImag = value;
		setImage();
	}

	public void setEdge(double value) {
		// TODO Auto-generated method stub
		edgeLength = value;
		setImage();
	}

	public void setIteration(int value) {
		// TODO Auto-generated method stub
		iterationLimit = value;
		setImage();
	}
	
	
	
	
	
	

	
    // declare a simple set method, setImageJPanel silknotsure
    //         again, also invoke setImage, to update/publish image to ImageJPanel.
	public void setImageJPanel(ImageJPanel imageJPanel){
		this.imageJPanel =  imageJPanel;
		setImage();
	};
	
	// if the code below is different from the code you produced on the previous Mandelbrot assignment, 

    // use whichever you prefer.
    void recenter( int X, int Y )
    {
        Y = NUM_PIXELS - Y;
        double dPixels = NUM_PIXELS;
        centerImag += edgeLength * ( Y / dPixels - 0.5 );
        centerReal += edgeLength * ( X / dPixels - 0.5 );   
        edgeLength /= 2.0;
        setImage();
    }

    BufferedImage getImage() { return bufferedImage; }
    
    // make the bufferedImage
    void makeImage()
    {
        long startTime = System.currentTimeMillis();
        bufferedImage = new BufferedImage( NUM_PIXELS, NUM_PIXELS, BufferedImage.TYPE_INT_RGB );
        Graphics graphics = bufferedImage.getGraphics();
        double delta = edgeLength / NUM_PIXELS;
        double x = centerReal - edgeLength / 2.0;
        for ( int row = 0; row < NUM_PIXELS; row++, x += delta )
        {
            double y = centerImag - edgeLength / 2.0;
            for ( int col = 0; col < NUM_PIXELS; col++, y += delta )
            {
                graphics.setColor( getColor( getIterationCount( x, y ) ) );
                graphics.fillRect( row, NUM_PIXELS - 1 - col, 1, 1);  
            }
        }
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println( "Model.makeImage: " + elapsedTime + " ms" );
    }
    
    void setImage()
    {
        makeImage();
        
        // publish image to imageJPanel
        imageJPanel.setImage(bufferedImage);
        //silk
    }

    private int getIterationCount( double x0, double y0 )
    {
        // use code that you used before
        //    which, I believe, I gave you
    	double x = x0;
        double y = y0;

        /* The 2 quick checks below (cardoid and period-2 bulb) see if the point is in
         * a known region of the Mandelbrot set, which may speed up the overall computation.
         */
         
        // in cardoid ?
        double xMinusOneFourth = x - ONE_FOURTH;
        double ySquared = y * y;
        double q = xMinusOneFourth * xMinusOneFourth + ySquared;
        if ( q * ( q + xMinusOneFourth ) < ONE_FOURTH * ySquared )
        {
            return iterationLimit; 
        }

        // in period-2 bulb ?
        double xPlusOne = x + 1.0;
        if ( xPlusOne * xPlusOne + ySquared < ONE_SIXTEENTH )
        {
            return iterationLimit; 
        }

        // perform typical iterationCount computation
        int iterationCount = 0;
        for ( double xtemp ; x*x + y*y <= 4.0 && iterationCount < iterationLimit; iterationCount++ )
        {
            xtemp = x*x - y*y + x0;
            y = 2*x*y + y0;
            x = xtemp;
        }
        return iterationCount;
    }

    private Color getColor( int iterationCount )
    {
        // use Utility.getColor
    	return Utility.getColor(iterationCount, iterationLimit);
    }
    
    void reset()	
    {
        // reset to default values
        // make and publish image
       	centerReal = CENTER_X;
        centerImag = CENTER_Y;
        edgeLength = EDGE_LENGTH;
        iterationLimit = ITERATION_LIMIT;
        setImage();
    }

    @Override
    public String toString()
    {
        StringBuilder string = new StringBuilder();
        string.append( "Model:" );
        string.append( " centerReal: ").append( centerReal );
        string.append( " centerImag: ").append( centerImag );
        string.append( " edgeLength: ").append( edgeLength );
        string.append( " iterationLimit: ").append( iterationLimit );
        return new String( string );
    }
}
