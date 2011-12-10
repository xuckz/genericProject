package de.genericproject.game;
/**
 * to build this project you need to download the JOGL libraries and add them to your build path.
 * you can find the latest release at:
 * http://jogamp.org/deployment/jogamp-current/archive/
 */

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.UIManager;


public class Engine 
{
	public static void main(String[] args) 
	{		
		EventQueue.invokeLater(new Runnable() {
            public void run() {

                // switch to system l&f for native font rendering etc.
                try{
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }catch(Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.INFO, "can not enable system look and feel", ex);
                }

                Frame frame = new Frame();
                frame.setVisible(true);
            }
        });
	}
}