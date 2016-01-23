package readWrite;

import javax.swing.JFrame;

public class Main {
	public static void main(String [] args ){
		GUI graphicalUserInterface = new GUI();
		

        graphicalUserInterface.setSize(650, 300);
        graphicalUserInterface.createGUI();
        graphicalUserInterface.setVisible(true);
        graphicalUserInterface.setTitle("Welcome to Person Form");
        graphicalUserInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	

}
