/**
 * 
  * @author Yixin Wang and Freddy Yang
 */

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Utility {
	
	public static JLabel imageToJLabel(Image image)
	{
		JLabel jLabel = new JLabel(new ImageIcon (image));
		return jLabel;
	}
	
	
	/*getColor, which has 2 int parameters, iterationCount and iterationLimi and which returns the Color associated
	 *  with that iteration count. It returns black, if the iteration count equals the iteration limit; otherwise,
 	 *   it returns a Color object whose red, green, and blue values are, respectively:
 	 *		fraction
 	 *		0
 	 *		1 - fraction
 	 */
	public static Color getColor( int iterationCount, int iterationLimit )
    {
		if (iterationCount == iterationLimit)
		    {
			return Color.black;
		    }
		else
		    {
	    	float fraction = ((float)iterationCount / (float)iterationLimit);
			Color color= new Color ((fraction),0,(1-fraction));
			return color;
		    }
    }
}
