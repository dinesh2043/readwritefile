package readWrite;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

public class GUI extends JFrame implements ActionListener  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JPanel panelForButton;
	private JPanel panelForNameInput; 
	private JPanel panelForSelection; 
	private JPanel infoPanel;
    protected static JTextField firstNameText;
    protected static JTextField lastNameText;
    private JButton openToRead;
    private JButton writeThis;
    private JButton openToWrite;
    private JButton readNext;
    private JLabel firstNameLabel;
    static JLabel firstNameRead;
    private JLabel lastNameLabel;
    static JLabel lastNameRead;
    private JLabel nationalityLabel;
    static JLabel nationalityRead;
    private JLabel professionLabel;
    static JLabel professionRead;
    private JLabel infoLabel;
    protected static JComboBox<?> nationalityCombo;
    protected static JComboBox<?> professionCombo;
    
    protected static String[] nationalityList = {"Finnish", "American", "Australian", "Austrian",
        "Bangladeshi", "Brazilian", "British", "Bulgarian", "Canadian", "Chinese",
        "Estonian", "Ethiopian", "Filipino", "Nepali", "French", "German", "Ghanaian",
        "Indian", "Iranian", "Iraqi", "Irish", "Italian", "Japanese", "Kenyan",
        "Lithuanian", "Malaysian", "Afghan", "Netherlander", "New Zealander",
        "Nigerien", "Pakistani", "Romanian", "Russian", "Somali", "South African",
        "South Korean", "Spanish", "Swedish", "Vietnamese"};
    
    
    
    protected static String[] professionList = {"Waiter", "Cook","Student", "Professor", "Graphic Designer", 
        "Bus Driver", "Seller", "Secretary", "Programmer", "Web Developer","3D Designer", 
        "Nurse", "Cleaner", "Psychologist", "Engineer"};
    
    
    public static String userDataLine;
    public static RandomAccessFile out;
    public static final String personalData = "PersonalData.txt";
    public static final File dataFile = new File(personalData);
    public static int indexCounter = 3;
    public static ArrayList<String> tokens = new ArrayList<String>();

    
	protected void createGUI() {

        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        window.setBackground(Color.BLACK);
        /**
         * 
         */
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(new GridLayout(5,1));
        
        panelForButton = new JPanel();
        panelForButton.setBackground(Color.white);
        panelForButton.setLayout(new GridLayout(1,4));

        panelForNameInput = new JPanel();
        panelForNameInput.setBackground(Color.white);
        panelForNameInput.setLayout(new GridLayout(2, 1));

        infoPanel = new JPanel();
        infoPanel.setBackground(Color.white);

        panelForSelection = new JPanel();
        panelForSelection.setBackground(Color.white);
       
        panelForSelection.setLayout(new GridLayout(2,1));


        openToWrite = new JButton("Open to Write");
        openToWrite.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	setReadArea(false);
                        firstNameText.setText("");
                        lastNameText.setText("");
                        nationalityCombo.setSelectedIndex(0);
                        professionCombo.setSelectedIndex(0);
                        Handler.openToWriteToFile();
                    }
                });

        
        writeThis = new JButton("Save");
        writeThis.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                        	Handler.openToWriteToFile();
                        	Handler.saveToFile();
                            setArea(true);
                            setReadArea(false);
                            GUI.nationalityCombo.setSelectedIndex(0);
                            GUI.professionCombo.setSelectedIndex(0);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    }
                });

        openToRead = new JButton("Open to Read");
        openToRead.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setArea(false);
                        setReadArea(true);
                        Handler.readFromFile();
                    }
                });




        readNext = new JButton("Read Next");
        readNext.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setArea(false);
                        setReadArea(true);
                        if (Handler.fillArray() == 1) {

                        	Handler.readNext();
                        }
                    }
                });



        firstNameLabel = new JLabel("First Name", JLabel.RIGHT);
        lastNameLabel = new JLabel("Last Name", JLabel.RIGHT);
        firstNameText = new JTextField(JTextField.LEFT);
        firstNameRead = new JLabel("",JLabel.LEFT);
        firstNameRead.setVisible(false);
        firstNameText.setEnabled(true);

        lastNameText = new JTextField(JTextField.LEFT);
        lastNameRead = new JLabel("",JLabel.LEFT);
        lastNameRead.setVisible(false);
        lastNameText.setEnabled(true);

        nationalityLabel = new JLabel("Nationality", JLabel.RIGHT);
        professionLabel = new JLabel("Profession", JLabel.RIGHT);

        infoLabel = new JLabel("Personal Information Form", JLabel.CENTER);

        nationalityCombo = new JComboBox<Object>(nationalityList);
        
        nationalityRead = new JLabel("",JLabel.LEFT);
        nationalityRead.setVisible(false);
        nationalityCombo.setEnabled(true);
        
        professionCombo = new JComboBox<Object>(professionList);
        professionRead = new JLabel("",JLabel.LEFT);
        professionRead.setVisible(false);
        professionCombo.setEnabled(true);

        window.add(mainPanel);
        mainPanel.add(infoPanel);
        mainPanel.add(panelForNameInput);
        mainPanel.add(panelForSelection);
        mainPanel.add(panelForButton);
        
        infoPanel.add(infoLabel);

        panelForButton.add(openToWrite);
        panelForButton.add(writeThis);
        panelForButton.add(openToRead);
        panelForButton.add(readNext);

        panelForNameInput.add(firstNameLabel);
        panelForNameInput.add(firstNameText);
        panelForNameInput.add(firstNameRead);

        panelForNameInput.add(lastNameLabel);
        panelForNameInput.add(lastNameText);
        panelForNameInput.add(lastNameRead);

        panelForSelection.add(professionLabel);
        panelForSelection.add(professionCombo);
        panelForSelection.add(professionRead);

        panelForSelection.add(nationalityLabel);
        panelForSelection.add(nationalityCombo);
        panelForSelection.add(nationalityRead);



    }

    @Override
    public void actionPerformed(ActionEvent ae) {
    }

    protected static void setArea(boolean setValue) {
        
    	firstNameText.setVisible(setValue);
    	lastNameText.setVisible(setValue);
    	nationalityCombo.setVisible(setValue);
    	professionCombo.setVisible(setValue);
    }
    protected static void setReadArea(boolean setValue) {
        
    	firstNameRead.setVisible(setValue);
    	lastNameRead.setVisible(setValue);
    	nationalityRead.setVisible(setValue);
    	professionRead.setVisible(setValue);
    }
    	
}
