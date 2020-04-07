import java.io.*;
import java.util.*;

import java.awt.*; // import the AWT graphic classes
import javax.swing.*; // import the Swing classes
/**
 * This class creates a frame and add GUI
 *
 * @author odilo M. Rugamba
 *
 * Andrew ID: omutuyin
 *
 * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */
public class PlayGame
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        String str;
        final LayoutGUI gui;
        if(args.length == 0)
            gui = new HangmanGUI();
        else
            gui = new HangmanGUI(args[0]);

        // Note: this is an anonymous inner-class that extends the Runnable class
        // and overrides the run() method.

        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowGUI(gui);
            }
        });
    }

    private static void createAndShowGUI(LayoutGUI gui)
    {
        // Create a JFrame and get its content pane

        JFrame theFrame = new JFrame(gui.getClass().getName() + " Application");
        Container c = theFrame.getContentPane();

        // Instantiate the GUI, which is a JPanel

        gui.addComponents(theFrame);

        // Set the size of the frame and exit behavior

        theFrame.setPreferredSize(new Dimension(1000, 900));
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the GUI to the frame

        c.add(gui);

        // show the frame

        theFrame.pack();
        theFrame.setVisible(true);
    }
}
