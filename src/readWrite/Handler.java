package readWrite;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;




public class Handler {
	
	static BufferedWriter writeToFile;
    static private BufferedReader readFromFile;
    static int index = -1;
    static int readConfirm = 0;

    protected static void openToWriteToFile() {



        //check if the file exist to write the data if yes then set the screen to visible
        if (GUI.dataFile.exists() == true) {
        	GUI.setArea(true);
            Handler.createFile("needFileToWrite");

        } else {
            JOptionPane.showMessageDialog(null, "FILE DOES NOT EXIST !\n New File is created.");
            Handler.createFile("needFileToWrite");
            GUI.setArea(true);
        }
    }

    protected static void saveToFile() throws IOException {

        if (GUI.firstNameText.getText().trim().length() > 1 && GUI.firstNameText.getText().trim().length() > 1) {

            GUI.userDataLine = GUI.firstNameText.getText()
                    + "|" + GUI.lastNameText.getText()
                    + "|" + (String) GUI.professionCombo.getSelectedItem()
                    + "|" + (String) GUI.nationalityCombo.getSelectedItem();

            try {
                Handler.createFile("needFileToWrite");
                writeToFile.write(GUI.userDataLine);
                writeToFile.newLine();
                writeToFile.close();

                JOptionPane.showMessageDialog(null, "Writing complete");
                GUI.userDataLine = "";


            } catch (IOException e) {

                JOptionPane.showMessageDialog(null, e.getMessage() + "\nERROR !");
            }
            GUI.firstNameText.setText(null);
            GUI.lastNameText.setText(null);
        } else {

            JOptionPane.showMessageDialog(null, "Incomplete Data ! Try again");


        }
    }

    protected static void readFromFile() {

        if (GUI.dataFile.exists() != true) {

            JOptionPane.showMessageDialog(null, "File error ! File doesn't exist.");

        } else {
            Handler.createFile("needFileToRead");
            try {
                if (Handler.readFromFile.read() < 2) {
                    JOptionPane.showMessageDialog(null, "File is empty !");
                } else {
                    String line;

                    while ((line = readFromFile.readLine()) != null) {


                        String[] tokens = line.split("\\|");
                        String firstName = tokens[0];
                        String lastName = tokens[1];
                        String profession  = tokens[2];
                        String nationality = tokens[3];
                        
                        GUI.firstNameRead.setText(firstName);
                        GUI.lastNameRead.setText(lastName);
                        
                        GUI.professionRead.setText(profession);
                        GUI.nationalityRead.setText(nationality);
                        readFromFile.close();
                        GUI.indexCounter = 3;

                        break;
                    }
                }
            } catch (IOException ex) {

                JOptionPane.showMessageDialog(null, ex.getMessage());

            }
        }
    }

    protected static void createFile(String getTaskMessage) {
        if (getTaskMessage.equalsIgnoreCase("needFileToWrite") == true) {
            try {
                Handler.writeToFile = new BufferedWriter(new FileWriter(GUI.dataFile, true));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error :" + ex.getMessage());
            }
        } else if (getTaskMessage.equalsIgnoreCase("needFileToRead") == true) {
            try {
                Handler.readFromFile = new BufferedReader(new InputStreamReader(new FileInputStream(GUI.dataFile)));
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }




        }

    }

    public static int searchString(String[] arrayData, String name) {
        for (int n = 0; n < arrayData.length; n++) {
            if (arrayData[n].equals(name)) {
                System.out.println(arrayData);
                return n;
            }
        }
        return -1;

    }

    protected static void readNext() {



        if (GUI.indexCounter < GUI.tokens.size()) {

            GUI.indexCounter++;
            String firstName = GUI.tokens.get(GUI.indexCounter);
            GUI.indexCounter++;
            String lastName = GUI.tokens.get(GUI.indexCounter);
            GUI.indexCounter++;
            String profession  = GUI.tokens.get(GUI.indexCounter);
            GUI.indexCounter++;
            String nationality = GUI.tokens.get(GUI.indexCounter);
            
            GUI.firstNameRead.setText(firstName);
            GUI.lastNameRead.setText(lastName);
            
            GUI.professionRead.setText(profession);
            GUI.nationalityRead.setText(nationality);

        } else {

            JOptionPane.showMessageDialog(null, "No record found !");
        }
    }

    protected static int fillArray() {

        if (readConfirm == 0) {

            if (GUI.dataFile.exists() != true) {

                JOptionPane.showMessageDialog(null, "File error ! File doesn't exist.");
                return 0;

            } else {

                Handler.createFile("needFileToRead");
                try {
                    if (Handler.readFromFile.read() < 2) {
                        JOptionPane.showMessageDialog(null, "File is empty !");
                    } else {
                        String line;

                        try {
                            while ((line = readFromFile.readLine()) != null) {

                                String token[] = line.split("\\|");
                                for (int i = 0; i < 4; i++) {
                                    GUI.tokens.add(getIndex(), token[i]);
                                    Handler.readConfirm = 1;

                                }
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    readFromFile.close();
                } catch (IOException ex) {
                    Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                }


            }
        }
        if (GUI.tokens.size() == 4 || GUI.tokens.size() == GUI.indexCounter + 1) {

            JOptionPane.showMessageDialog(null, "No more record");
            return 0;
        } else {
            return 1;
        }

    }

    protected static int getIndex() {

        index++;
        return index;
    }



}
