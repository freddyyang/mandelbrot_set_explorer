
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import javax.imageio.ImageIO;
import javax.swing.*;



/**
 *
 * @author Freddy Yang & Yixin Wang
 */
public class ControlJPanel extends JPanel 
{
    private Model model;
    private ImageJPanel imageJPanel;
    private JLabel centerRealLabel = new JLabel(" Real coordinate ");
    private JTextField centerRealTextField = new JTextField(15);
    private JLabel blankLabel1 = new JLabel(" ");
    private JLabel centerImagLabel = new JLabel(" Imag coordinate ");
    private JTextField centerImagTextField = new JTextField(15);
    private JLabel blankLabel2 = new JLabel(" ");
    private JLabel edgeLengthLabel = new JLabel(" Edge length ");
    private JTextField edgeLengthTextField = new JTextField(15);
    private JLabel blankLabel3 = new JLabel(" ");
    private JLabel iterationLimitLabel = new JLabel(" Iteration limit ");
    private JTextField iterationLimitTextField = new JTextField(15);
    private JLabel blankLabel4 = new JLabel(" ");
    private JButton forwardButton = new JButton( "Forward" );
    private JButton backButton = new JButton( "Back" );
    private JButton deleteButton = new JButton( "Delete" );
    private JButton homeButton = new JButton( "Reset" );
    private JButton saveButton = new JButton( "Save" );
    private JButton recordButton = new JButton( "Record" );
    private JLabel blankLabel5 = new JLabel(" ");
    private JButton playButton = new JButton( "Play" );
    private JButton stopButton = new JButton( "Stop" );
    private JTextField frameRateTextField = new JTextField("20", 2);
    private JTextField recordTimeTextField = new JTextField("1", 2);
    private JTextField numFramesTextField = new JTextField(10);
    private JLabel frameRateTextFieldLabel = new JLabel("   Frames/Second");
    private JLabel recordTimeTextFieldLabel = new JLabel("X       Seconds");
    private JLabel numFramesTextFieldLabel = new JLabel("=       Frames");
    
    private ModelList modelList = new ModelList();
    private File imageFolder;
    private int imageNum;
    private int frameRate = 20;
    private int recordTime = 1;
    private int numFrames = frameRate * recordTime;
    private int numPlayFrames;
    
    private final int numProcessors = Runtime.getRuntime().availableProcessors();
    private ExecutorService executorService = Executors.newFixedThreadPool(numProcessors);
    private AtomicInteger numUnfinishedFiles;
    
  //  private Timer playTimer = new Timer(20, new TimerHandler());

    private double centerReal;
    private double centerImag;
    private double edgeLength;
    private int  iterationLimit;
    
    ControlJPanel(Model model, ImageJPanel imageJPanel) 
    {
        this.model = model;
        this.imageJPanel = imageJPanel;
        
        // initialize modelList (silk)
				
        // use a GridLayout to display the JTextFields, JButtons, and JLabels
        // as shown in Fig. 1.
        setLayout (new GridLayout(9,3,7,7));
        
        // add the Jlabel, JTextField, and JButton objects to the layout of this JPanal
        add (centerRealLabel);
        add (centerRealTextField);
        add (blankLabel1);
        add (centerImagLabel);
        add (centerImagTextField);
        add (blankLabel2);
        add (edgeLengthLabel);
        add (edgeLengthTextField);
        add (blankLabel3);
        add (iterationLimitLabel);
        add (iterationLimitTextField );
        add (blankLabel4);
        add (forwardButton);
        add (backButton );
        add (deleteButton );
        add (homeButton); //reset
        add (saveButton);
        add (recordButton );
        add (blankLabel5 );
        add (playButton );
        add (stopButton );
        add (frameRateTextField );
        add (recordTimeTextField );
        add (numFramesTextField );
        add (frameRateTextFieldLabel );
        add (recordTimeTextFieldLabel );
        add (numFramesTextFieldLabel );

        
        initialize(); // see below
    }

    // used when in the PLAYING state (silk)
    void nextImage()
    {
        // if next imageNum equals the total number of images to be displayed (numPlayFrames),
        // reset imageNum to 0 (i.e., loop)
        
        // construct the file name from the folder name, "/image" , imageNum, and ".png"
        
        // read the file into a BufferedImage as was done in the solar system animation.
	/*        try 
        {
            // your code goes here
            
            // display the BufferedImage
        } 
        catch ( IOException e ) 
        {
            // restart animation
	    }*/
    }

    void view() 
    {
        // for each of the current model's  attribute values,
        //     set the JTextFields with that value
        
        // repaint
    	centerReal = model.getCenterReal();
    	centerImag = model.getCenterImag();
    	edgeLength = model.getEdgeLength();
    	iterationLimit = model.getIterationLimit();
    	
        centerRealTextField.setText(Double.toString(centerReal));
        centerImagTextField.setText(Double.toString(centerImag));
        edgeLengthTextField.setText(Double.toString(edgeLength));
        iterationLimitTextField.setText(Integer.toString(iterationLimit));
        
        //repaint (silk)
        //model.setImage(); 
	repaint();
}

    void zoom(int x, int y) //silk
    {
        // add a copy of the current model object to the list silk
	//        modelList.add();
	// recenter it to x and y.
    	model.recenter(x, y);
	modelList.add();
    	view();
        // display its attribute values and its image
    }

    private void initialize() 
    {
        //------------------------------------------
        // contoller TEMPLATE CODE for each action
        //------------------------------------------
        centerRealTextField.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent) 
            {
                centerRealTextFieldActionPerformed(actionEvent);
            }
        });
        
        // insert the template code as above,
        // for each JTextField and JButton declared above
        // (as you have done for other GUI assignments)

        
        // write analogous controller template code for the other JTextFields and JButtons
        centerImagTextField.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent actionEvent )
            {
            	centerImagTextFieldActionPerformed( actionEvent );
            }
        });
        
        edgeLengthTextField.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent actionEvent )
            {
            	edgeTextFieldActionPerformed( actionEvent );
            }
        });
        
        iterationLimitTextField.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent actionEvent )
            {
            	iterationTextFieldActionPerformed( actionEvent );
            }
        });
        
        //Button
        homeButton.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent actionEvent )
            {
                resetButtonActionPerformed( actionEvent );
            }
        });
        
        saveButton.addActionListener( new ActionListener()
        {
            @Override
          
            public void actionPerformed( ActionEvent actionEvent )
            {
            	//exception
            		saveMenuItemActionPerformed( actionEvent );
            			
            }
        });
        
        
        numFramesTextField.setText( String.valueOf( numFrames ) );
        
        // entering the INITIAL_EXPLORING state: disable record, play, & stop buttons silkmore?
        recordButton.setEnabled( false );
        playButton.setEnabled(   false );
        stopButton.setEnabled(   false );
        
        view();
    }

    // _____________________________
    //  controller for each action
    // _____________________________
    
    // I give an example controller for iterationLimitTextFieldActionPerformed
    // Write analogous code for all other JTextFields.    
    private void iterationLimitTextFieldActionPerformed(ActionEvent actionEvent) 
    {
        try
        {
            int value = Integer.parseInt( iterationLimitTextField.getText() );
            if ( value <= 0 )
            {
                JOptionPane.showMessageDialog( null, value + " is not greater than 0." );
                return;
            }
            //modelList.add();
            //model.setIterationLimit( value );
            view();
        }
        catch ( NumberFormatException unused )
        {
            JOptionPane.showMessageDialog( null, iterationLimitTextField.getText() + " is not an int." );
        }
    }

    private void centerRealTextFieldActionPerformed( ActionEvent actionEvent )
    {
        double value = Double.parseDouble( centerRealTextField.getText() );
        model.setCenterReal( value );
        imageJPanel.repaint();
    }
    
    // define analogous private controller methods for the other text fields
    private void centerImagTextFieldActionPerformed( ActionEvent actionEvent )
    {
        double value = Double.parseDouble( centerImagTextField.getText() );
        model.setCenterImag( value );
        imageJPanel.repaint();
    }
    
    private void edgeTextFieldActionPerformed( ActionEvent actionEvent )
    {
        double value = Double.parseDouble( edgeLengthTextField.getText() );
        model.setEdge( value );
        imageJPanel.repaint();
    }
    
    private void iterationTextFieldActionPerformed( ActionEvent actionEvent )
    {
        int value = Integer.parseInt( iterationLimitTextField.getText());
        model.setIteration( value );
        imageJPanel.repaint();
    }
    
    private void resetButtonActionPerformed( ActionEvent actionEvent )
    {
        // reset the model attribute values
    	model.reset();
        // make them viewable to the user
    	// update the image in the ImageJPanel to reflect these model attributes
    	imageJPanel.repaint();
    }
    
    private void saveMenuItemActionPerformed( ActionEvent actionEvent )
    {
        JFileChooser fileChooser = new JFileChooser( imageFolder );
        int returnValue = fileChooser.showDialog( this, "Save");
        if ( returnValue == JFileChooser.APPROVE_OPTION )
        {
            File imageFile = fileChooser.getSelectedFile();
            try
            {
                ImageIO.write( model.getImage(), "png", imageFile );
            }
            catch ( IOException ioException )
            {
                ioException.printStackTrace();
            }
        }
    }

    private void backButtonActionPerformed(ActionEvent actionEvent) 
    {
	modelList.back(); //silk
        view();
    }

    private void forwardButtonActionPerformed(ActionEvent actionEvent) 
    {
        // analogous to back
	modelList.forward();
	view();
}

    private void deleteButtonActionPerformed(ActionEvent actionEvent) 
    {
        // analogous to back
    }

    private void recordButtonActionPerformed(ActionEvent actionEvent) 
    {
        // entering the RECORDING state: disable all buttons & fields
        
        // set folder as has been done in previous assignments

        // create .png files and & put in folder
        //    compute numPlayFrames: the total number of images to be computed and written to file
       
        // numUnfinishedFiles is a thread-safe counter of Runnables that have to complete
        // before the record operation is considered complete
        numUnfinishedFiles = new AtomicInteger( numPlayFrames );
        
        // construct a new Animation and invoke its animate method
        
        // have all images files been made?
        if ( numUnfinishedFiles.get() > 0 )
        {
            // no: wait until all images files have been made
            try
            {
                synchronized ( this )
                {
                    wait();
                }
            }
            catch ( InterruptedException ignore ) {}
        } 

        // when flow of control reaches this point, all image files have been made: 
        // enable all operations but the stop button
        // view the modified control panel 
    }

    private void playButtonActionPerformed(ActionEvent actionEvent) 
    {
        // entering PLAYING state: 
        // disable all but stop button
        // enable stop button
        
        // starting with image0.png.,
        // start playing images
        
        repaint(); // control panel has been changed
    }
    
    private void stopButtonActionPerformed(ActionEvent actionEvent) 
    {
        // entering EXPLORE state: 
        // enable all but stop button
        // disable stop button
        
        // stop playing images
        
        // display list's current image
        
        repaint(); // control panel has changed
    }

    private void frameRateTextFieldActionPerformed(ActionEvent actionEvent) 
    {
        // get and validate
        
        // update playTimer delay
        // update numFrames & set its JTextField
        // display the updated fields
    }

    private void recordTimeTextFieldActionPerformed(ActionEvent actionEvent) 
    {
        // get and validate
        
        // update numFrames & set its JTextField
        // display the updated fields
    }
    
    // enable/disable all but stopButton
    private void enableAllButStop( boolean enable )
    {
        centerRealTextField.setEnabled( enable );
        // do for other JTextFields and JButtons, as appropriate
    }
    
    // enable/disable stopButton
    private void enableStop( boolean enable ) { stopButton.setEnabled( enable ); }
    
   private class ModelList 
    {
        private List list = new ArrayList<>();
        private int index;
        void init() { list.add(model); }
        void add() 
        {
            // clone current model object
	    Model cloneModel = model.clone();
            // place it in the appropriate point in the list,
	    list.add(index+1,cloneModel);
            // updating index, as needed
            model = cloneModel;
            // make cloned model the current controlJPanel model
            index ++;
            // enable the record button, if the list has at least 2 items
	    if (index >= 2)
		recordButton.setEnabled(true);
	}

        void back() 
        {
            // update the list and controlJPanel model as appropriate
            // display the new current model's image
	    if ( index > 0){
		index--;
		model = (Model)list.get(index);
	    }
	    //   imageJPanel.setImage(model.getImage());
	    //model.setImage();
	    view();
        }

        void forward() 
        {
            // update the list and controlJPanel model as appropriate
            // display the new current model's image
	    if (index < list.size()-1){
		index++;
		model = (Model)list.get(index);
	    }
	    //imageJPanel.setImage(model.getImage());
	    view();
	}

	   void delete() 
        {
            // if removing this list item would result in an empty list,
            // issue the following message, and ignore the request:
            JOptionPane.showMessageDialog( null, "Last image may not be deleted." );
            
            // if removing this list item would make the list have only 1 item
            // disable recording
            
            // remove item and update index, as appropriate
            // set and display the new current model
        }
	
        //Model first() {; /* return first item in the list */ }


	//	List list() { /* return list */ }

        //int size() { /* return the number of items in the list */ }
   }
    
/*    private class Animation 
    {   
    	void animate() 
        {
            // the first image to be recorded is named image0.png
            // for each pair of adjacent models (i.e., index pairs (0, 1), (1, 2), ... )
                makeModelRunnables( firstModel, nextModel );
        }

        void makeModelRunnables(Model bgnModel, Model endModel) 
        {
            // make a ModelToImageRunnable object from the bgnModel 
            //      and execute it via the executorService
            // update imageNum

            // generate intermediate models & corresponding runnables
            double x1 = bgnModel.getCenterReal();
            double x2 = endModel.getCenterReal();
            double y1 = bgnModel.getCenterImag();
            double y2 = endModel.getCenterImag();
            double z1 = bgnModel.getEdgeLength();
            double z2 = endModel.getEdgeLength();
            int    i1 = bgnModel.getIterationLimit();
            int    i2 = endModel.getIterationLimit();

            double dX = ( x2 - x1 ) / numFrames;
            double dY = ( y2 - y1 ) / numFrames;
            double dZ = Math.pow( ( z2 / z1 ), 1.0 / numFrames);
            double dI = ( i2 - i1 ) / numFrames;

            for (int num = 1; num < numFrames; num++) 
            {
                x1 += dX;
                y1 += dY;
                z1 *= dZ;
                i1 += dI;
                
                // using these values, 
                //     1. construct a Model object
                //     2. make a ModelToImageRunnable object and increment imageNum
                //     3. execute it via the executorService
                //
            }
        }
    }

    private class ModelToImageRunnable implements Runnable 
    {
        private Model model;
        private int fileNum;

        ModelToImageRunnable( Model model, int fileNum ) 
        {
            // set attributes from parameters
        }

        @Override
        public void run() 
        {
            // have the model make its ImageBuffer
            
            // write the ImageBuffer to file, as has been done in previous assignments
            
            // The code below does the following:
            // decrement the number of unfinished files
            // if we are all done, notify the ControlJPanel to stop waiting 
            // (in recordButtonActionPerformed method)
            if ( numUnfinishedFiles.decrementAndGet() == 0 )
            {
                synchronized ( this )
                {
                    synchronized( ControlJPanel.this )
                    {
                        ControlJPanel.this.notify();
                    }
                }
            }
        }
    }

    private class TimerHandler implements ActionListener 
    {
        // override actionPerformed to display the next image file
        @Override
        public void actionPerformed(ActionEvent unused) 
        {
            nextImage();
        }
    }
*/
}
