package org.example;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import javax.swing.SwingUtilities;
import java.util.Scanner;

//Timer
import java.util.Timer;
import java.util.TimerTask;
import java.awt.AWTEvent;
import java.awt.AWTException;
import java.awt.BufferCapabilities.FlipContents;
import java.awt.Button;
import java.awt.Component;
import java.awt.DefaultKeyboardFocusManager;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.FocusTraversalPolicy;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.Window;
//Mouse
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
//GUI
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.file.Paths;
import java.security.PublicKey;
import java.io.File;
import java.io.FileReader;

import javax.swing.JToggleButton;
import javax.swing.SortingFocusTraversalPolicy;
import javax.swing.JPasswordField;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JOptionPane;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent; //Message Box
import javax.swing.JFileChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.MouseAdapter; //Save File Dialog Box



public class MainFrame extends JFrame implements NativeKeyListener{

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    //components
    private JLabel lblCoordinates;
    private JButton btnWriteFile;
    private JRadioButton rdbtnLEFTCLICK;
    private JLabel lbWelcome;
    private JPasswordField passwordField;
    private JTextField XCordinateTextBox;
    private JTextField YCordinateTextBox;
    private JTextField txtboxTypeCommand;
    private JTextField textboxWaitTime;



    /**
     * Launch the application.
     */
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }//end of main


    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
       //These lines are for testing
        //System.out.println("key released code:" + e.getKeyCode());
        //System.out.println("key released char:" + e.getKeyChar());

        if(e.getKeyCode() == 3666) {
        String yPOS = new String(); //for performance use static or use stringbuilder
        String xPOS = new String();
        xPOS = String.valueOf(MouseInfo.getPointerInfo().getLocation().x);
        yPOS = String.valueOf(MouseInfo.getPointerInfo().getLocation().y);
        XCordinateTextBox.setText(xPOS);
        YCordinateTextBox.setText(yPOS);
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        System.out.println("Key code" + e.getKeyCode());
        }//End If
    }//End of nativeKeyPressed


    /**
     * Create the frame.
     */
    public MainFrame() {
        //------------------------------Window Setup-------------------------
        //this.setLayout(null);
        this.setVisible(true); //FOR SOME REASON - if this is gone, keyListener will not work

        getContentPane().setLayout(null);
        setTitle("Auto-Clicker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 423, 495);
        contentPane = new JPanel();
        //contentPane.setToolTipText("ass\r\ndick");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        //declaration for components is moved up
        lblCoordinates = new JLabel("Current Mouse: ");
        lblCoordinates.setBounds(10, 11, 91, 36);
        contentPane.add(lblCoordinates);

        JTextArea CMD = new JTextArea();
        CMD.setEditable(false);
        CMD.setBounds(10, 71, 254, 188);
        contentPane.add(CMD);

        JLabel XLabel = new JLabel("X:");
        XLabel.setBounds(10, 324, 15, 20);
        contentPane.add(XLabel);

        JLabel YLabel = new JLabel("Y:");
        YLabel.setBounds(10, 355, 15, 20);
        contentPane.add(YLabel);

        XCordinateTextBox = new JTextField();
        XCordinateTextBox.setText("777");
        XCordinateTextBox.setColumns(10);
        XCordinateTextBox.setBounds(35, 324, 86, 20);
        contentPane.add(XCordinateTextBox);

        YCordinateTextBox = new JTextField();
        YCordinateTextBox.setText("777");
        YCordinateTextBox.setColumns(10);
        YCordinateTextBox.setBounds(35, 355, 86, 20);
        contentPane.add(YCordinateTextBox);

        JLabel XcoordinateLabel = new JLabel("0");
        XcoordinateLabel.setBounds(184, 22, 46, 14);
        contentPane.add(XcoordinateLabel);

        JLabel YcoordinateLabel = new JLabel("0");
        YcoordinateLabel.setBounds(111, 22, 46, 14);
        contentPane.add(YcoordinateLabel);

        lbWelcome = new JLabel("Press Insert to copy location");
        lbWelcome.setBounds(10, 46, 180, 14);
        contentPane.add(lbWelcome);

        rdbtnLEFTCLICK = new JRadioButton("Left Click");
        rdbtnLEFTCLICK.setSelected(true);
        rdbtnLEFTCLICK.setBounds(2, 266, 69, 23);
        contentPane.add(rdbtnLEFTCLICK);

        JRadioButton rdbtnRIGHTCLICK = new JRadioButton("Right Click");
        rdbtnRIGHTCLICK.setBounds(91, 266, 80, 23);
        contentPane.add(rdbtnRIGHTCLICK);

        JRadioButton rdbtnMOVE = new JRadioButton("Move Mouse");
        rdbtnMOVE.setBounds(39, 292, 91, 23);
        contentPane.add(rdbtnMOVE);
        rdbtnMOVE.setVisible(true);

        //Group radio Buttons using ButtonGroup Container
        ButtonGroup radioButtonContainer = new ButtonGroup();
        radioButtonContainer.add(rdbtnLEFTCLICK);
        radioButtonContainer.add(rdbtnRIGHTCLICK);
        radioButtonContainer.add(rdbtnMOVE);

        JButton btnAddClick = new JButton("Add Click Cmd");
        btnAddClick.setBounds(10, 386, 143, 23);
        contentPane.add(btnAddClick);

        btnWriteFile = new JButton("Save Script");
        btnWriteFile.setBounds(274, 162, 120, 23);
        contentPane.add(btnWriteFile);

        JButton btnLoadButton = new JButton("Load Script");
        btnLoadButton.setBounds(274, 196, 120, 20);
        contentPane.add(btnLoadButton);

        //-------------------THIS ELEMENT IS FOR TESTING
        JButton btnTestButton = new JButton("Test");
        btnTestButton.setBounds(274, 227, 89, 23);
        contentPane.add(btnTestButton);

        JButton btnClearAllButton = new JButton("DELETE EVERY COMMAND");
        btnClearAllButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnClearAllButton.setForeground(new Color(255, 0, 0));
        btnClearAllButton.setBounds(10, 420, 318, 36);
        contentPane.add(btnClearAllButton);

        JButton btnDeleteCMDButton = new JButton("Delete \r\nCommand");
        btnDeleteCMDButton.setBounds(274, 115, 120, 36);
        contentPane.add(btnDeleteCMDButton);

        // Create the new button
        JButton btnRun = new JButton("Run");
// Set bounds: same x, width, height as btnDeleteCMDButton, but y-position 36 pixels higher
        btnRun.setBounds(274, 79, 120, 28);
        contentPane.add(btnRun);



        JLabel lblKeys = new JLabel("Type Keys");
        lblKeys.setBounds(184, 270, 80, 14);
        contentPane.add(lblKeys);

        txtboxTypeCommand = new JTextField();
        txtboxTypeCommand.setText("Type Here");
        txtboxTypeCommand.setToolTipText("");
        txtboxTypeCommand.setBounds(184, 293, 120, 20);
        contentPane.add(txtboxTypeCommand);
        txtboxTypeCommand.setColumns(10);

        JButton btnAddKeyBoardButton = new JButton("Add Type Cmd");
        btnAddKeyBoardButton.setBounds(184, 323, 120, 23);
        contentPane.add(btnAddKeyBoardButton);

        JLabel lblWait = new JLabel("Pause in milliseconds: ");
        lblWait.setBounds(184, 358, 180, 14);
        contentPane.add(lblWait);

        textboxWaitTime = new JTextField();
        textboxWaitTime.setText("1000");
        textboxWaitTime.setBounds(178, 387, 39, 20);
        contentPane.add(textboxWaitTime);
        textboxWaitTime.setColumns(10);

        JButton btnPause = new JButton("Add Pause");
        btnPause.setBounds(227, 386, 120, 23);
        contentPane.add(btnPause);
        //-------------------THIS ELEMENT IS FOR TESTING
        //------------------------------End of Window Setup-------------------------

        //-------------------------------EVENT HANDLERS--------------------------------------------

        //Run Button
        btnRun.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)  { //- throws AWTEXCEPTION - From StackOverflow "How to simulate a real mouse click using java?

                String allCommands = CMD.getText();
                String[] commands = allCommands.split("\n");

                for(String command : commands) {
                    char commandType = command.charAt(0);
                    String commandValue = command.substring(1).trim();

                    try {
                        switch (commandType) {
                            case 'L': // Left Click
                                String[] coordsL = commandValue.split("s");
                                int xL = Integer.parseInt(coordsL[0]);
                                int yL = Integer.parseInt(coordsL[1]);
                                LeftClick(xL, yL);
                                break;
                            case 'R': // Right Click
                                String[] coordsR = commandValue.split("s");
                                int xR = Integer.parseInt(coordsR[0]);
                                int yR = Integer.parseInt(coordsR[1]);
                                RightClick(xR, yR);
                                break;
                            case 'M': // Move Mouse
                                String[] coordsM = commandValue.split("s");
                                int xM = Integer.parseInt(coordsM[0]);
                                int yM = Integer.parseInt(coordsM[1]);
                                mouseMove(xM, yM);
                                break;
                            case 'T': // Type
                                for (char ch : commandValue.toCharArray()) {
                                    int keyCode = KeyEvent.getExtendedKeyCodeForChar(ch);
                                    if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                                        throw new RuntimeException(
                                                "Key code not found for character '" + ch + "'");
                                    }
                                    keyPress(keyCode);
                                    sleep(50); // Small delay between keystrokes
                                }
                                break;
                            case 'W': // Wait
                                int waitTime = Integer.parseInt(commandValue);
                                sleep(waitTime);
                                break;
                            default:
                                System.out.println("Unknown command: " + commandType);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });

        //DELETE SINGLE COMMAND - MUST BE FIXED
        btnDeleteCMDButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                StringBuilder s = new StringBuilder(CMD.getText());

                //CMD.setRows(4);
                //System.out.println(CMD.getSelectedText() + "Start Index: " + CMD.getSelectionStart() + "End index:" + CMD.getSelectionEnd());
                s.replace(CMD.getSelectionStart(), CMD.getSelectionEnd(), "");

                //CMD.setText("ASSHOLE"); //this works and can be used as an exploit. Validate & surround Crown with Try-Catch
                CMD.setText(s.toString());
            }
        });

        //DELETE EVERY COMMAND
        btnClearAllButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            int dialogButton = JOptionPane.YES_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null,"Do you want to delete everything?","Delete", dialogButton);

            if(dialogResult == JOptionPane.YES_OPTION)
                CMD.setText("");
        } });

        //Write File
        btnWriteFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String FwriteF = CMD.getText();
                JFrame parentFrame = new JFrame();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save File");



                int userSelection = fileChooser.showSaveDialog(parentFrame);

                if(userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSaveFile = fileChooser.getSelectedFile(); //need a file write stream
                    try {
                        FileWriter filewriter = new FileWriter(fileToSaveFile);
                        filewriter.write(FwriteF);
                        filewriter.close();
                        System.out.println("Save as file: " + fileToSaveFile.getAbsolutePath());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        System.out.println("ERROR fileWrite");
                    }//end try

                } //end If

            } //End Action performed
        }); //End Event Listener

        //Load File
        btnLoadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                char [] fileContent = new char[100];
                JFrame parentFrame = new JFrame();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Load File");



                int userSelection = fileChooser.showOpenDialog(parentFrame);
                if(userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToLoadFile = fileChooser.getSelectedFile(); //need a file write stream
                    try(Scanner input = new Scanner(Paths.get(fileToLoadFile.toString()))) {

                        while(input.hasNext()) {
                            //System.out.println(input.next());
                            CMD.append(input.next() + "\n"); //Loads file into CMD box

                        }

                    } catch (IOException e1) {
                        e1.printStackTrace();
                        System.out.println("ERROR fileWrite");
                    }//end try

                } //end If

            }
        });


        //Add Click Command
        btnAddClick.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    Integer.parseInt(XCordinateTextBox.getText());
                    Integer.parseInt(YCordinateTextBox.getText());

                }
                catch (NumberFormatException errorFromCatch) {
                    JOptionPane.showMessageDialog(null,"Value(s) must be numeric","Error Code X1",1);
                    return;
                }

                if(rdbtnLEFTCLICK.isSelected())	//LEFT
                    CMD.setText(CMD.getText() + "L"+XCordinateTextBox.getText() +"s" + YCordinateTextBox.getText() + "\n");

                if(rdbtnRIGHTCLICK.isSelected()) //RIGHT
                    CMD.setText(CMD.getText() + "R"+XCordinateTextBox.getText()  +"s" + YCordinateTextBox.getText() + "\n");

                if(rdbtnMOVE.isSelected()) //MOVE
                    CMD.setText(CMD.getText() + "M"+XCordinateTextBox.getText() + YCordinateTextBox.getText() + "\n");

            }
        }); //End Add Click Command

        //Add Type Command
        btnAddKeyBoardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CMD.setText(CMD.getText() + "T" + txtboxTypeCommand.getText() + "\n");
            }
        });
        //Add Wait Command
        btnPause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CMD.setText(CMD.getText() + "W" + textboxWaitTime.getText() + "\n");
            }
        });


        //Timer
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                //X,Y Coordinates for Labels
                String yPOS = new String(); //for performance use static or use stringbuilder
                String xPOS = new String();
                xPOS = String.valueOf(MouseInfo.getPointerInfo().getLocation().x);
                yPOS = String.valueOf(MouseInfo.getPointerInfo().getLocation().y);
                XcoordinateLabel.setText("X: " + xPOS);
                YcoordinateLabel.setText("Y: " + yPOS);
                //COPY X,Y Coordinates into Textbox



            }
        };



        //timer.schedule(task, 0); // can use a date from Calender class.
        timer.scheduleAtFixedRate(task, 0, 1);

        //Test Button
        btnTestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)  { //- throws AWTEXCEPTION - From StackOverflow "How to simulate a real mouse click using java?

                //----------------Mouse Simulation Test--------------
                //mouseMove(100, 750);
                //LeftClick(21, 743);rrr
                //RightClick(21, 743);

                //----------------Keyboard Simulation Test---------------

                try {
                    LeftClick(Integer.parseInt(XCordinateTextBox.getText()), Integer.parseInt(YCordinateTextBox.getText()));
                    //sleep(100); // Basic Sleep
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                //keyPress(KeyEvent.VK_R);


            }
        });



        //Fixes Focus Problem. Clicking the buttons would take Focus away from key Listner


        txtboxTypeCommand.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {

                MainFrame.super.requestFocus();
                //Important NOTE: This is all the work and research below. Do not forget
                //Learned about EventQueue and KeyBoardFocusManager
                //btnDeleteCMDButton.setText("6969696");
                //txtboxTypeCommand.transferFocus();
                //KeyboardFocusManager.setCurrentKeyboardFocusManager(JFrame);
                //cancel.requestFocusInWindow();
                //SortingFocusTraversalPolicy sortingFocusTraversalPolicy = new SortingFocusTraversalPolicy(null);
                //sortingFocusTraversalPolicy.getDefaultComponent(getParent());
                //DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager(this);
                //Window.class.notify();
                //JFrame.getOwnerlessWindows().notify();
            }
        });
        textboxWaitTime.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                MainFrame.super.requestFocus();
            }
        });



        // Inside MainFrame constructor, after UI setup:
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            ex.printStackTrace();
        }
        GlobalScreen.addNativeKeyListener(this);
        //----------------------------------------------------------------------------------------------------------------
    } //---------------------------------------- End of MainFrame Constructor-----------------------------------------
    //----------------------------------------------------------------------------------------------------------------



    //--------------------------------The Five Commands-------------------------------------------

    public static void mouseMove(int x, int y) {
        try {
            Robot bRobot = new Robot();
            bRobot.mouseMove(x, y);
        } catch (AWTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//end try
    }//end mouseMove
    public static void LeftClick(int x, int y) {
        try {
            Robot bRobot = new Robot();
            int mask = InputEvent.BUTTON1_DOWN_MASK;
            bRobot.mouseMove(x, y);
            bRobot.mousePress(mask);
            bRobot.mouseRelease(mask);

        } catch (AWTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//end try
    }//end LeftClick
    public static void RightClick(int x, int y) {
        try {
            Robot bRobot = new Robot();
            int mask = InputEvent.BUTTON3_DOWN_MASK; //Button 2 is wheel-click. 3 is for Right Clicks.
            bRobot.mouseMove(x, y);
            bRobot.mousePress(mask);
            bRobot.mouseRelease(mask);
        } catch (AWTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//end try
    }//end LeftClick

    //KeyBoard
    public static void keyPress(int i) {
        try {
            Robot bRobot = new Robot();
            bRobot.keyPress(i);
            bRobot.keyRelease(i);
        } catch (AWTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void sleep(int i) throws InterruptedException {
        Thread.sleep(i);
    }



} //End of Class

//USER ERROR CODES:
// 103 - Error File Write
// 603 - Error File Read - First char is an invalid command.










//-------------SCRAP CODE-----------------
//			MainFrame secondary = new MainFrame(); //used to create another window
//	secondary.setVisible(true);
//			secondary.rdbtnGAY.setEnabled(true); //doesn't work

/* COMPONENTS:
 * 		JToggleButton tglbtnNewToggleButton = new JToggleButton("New toggle button");
		tglbtnNewToggleButton.setBounds(476, 77, 121, 23);
		contentPane.add(tglbtnNewToggleButton);

		passwordField = new JPasswordField();
		passwordField.setToolTipText("pass");
		passwordField.setBounds(337, 169, 109, 20);
		contentPane.add(passwordField);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("add\r\nsyb\r\nmker");
		menuBar.setBounds(265, 314, 101, 22);
		contentPane.add(menuBar);

 *
 *
 */
