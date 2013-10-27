package main001;

/**
Program: Naughts and Crosses
Filename: cross_game.java
@author: © Bob Roberts 
@version: 0.1.0
Last update: 04/08/13
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Random;

public class cross_game extends JFrame implements ActionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel, panelRight, panelRight_1, panelRight_2, panelRight_2_1, panelRight_2_2, panelRight_2_3, panelRight_2_4, panelRight_2_5, panelRight_2_6;
	private JButton buttonRestart, buttonExit;
	JMenuBar topMenuBar;       
	JMenu fileMenu, editMenu, searchMenu, helpMenu, scenario;  //declare sub-Menus
	JMenuItem exitItem, fontItem, foreColor, backColor, helpItem, aboutItem, scenario1, scenario2, scenario3, mainEdit, partyMode, controls, controlsEdit, controlsInfo;
	private JLabel JLScore, JLHuman, JLPC, JLBlank1, JLBlank2;
	private JTextField gapField_human, gapField_PC;

    ImageIcon x	       = new ImageIcon("src/Resources/x.png");
    ImageIcon o        = new ImageIcon("src/Resources/o.png");
    ImageIcon nothing  = new ImageIcon("src/Resources/nothing.png");
    static ImageIcon icon = new ImageIcon("src/Resources/icon.png");
    
    int scoreHuman = 0;
    int scoreComputer = 0;
    int p = 0; //used for pc to check if square used up
    
    boolean BooleanA   = false;
    boolean[] BooleanB = new boolean[9]; //boolean to check if the the number has already been used up
    boolean BooleanC   = false;
    JButton buttonArray[]=new JButton[9]; 
    boolean override = false;
    int count = 0;
    //0=pc won  // 1=draw // 2=human won
    int whoWon = 1;

    // 1 == x  0 == 0 2 == nothing
    // 0 == human x == pc
    static int[] allArray = 
    { 
	    2, 2, 2,
	    2, 2, 2,
	    2, 2, 2
    };
    static int[] allArray1Copy = (int[])allArray.clone(); 
    static int[] allArray1CopyZ = (int[])allArray.clone(); 
    
    int[] bestMoves = {0, 2, 8, 4, 6, 1, 5, 3, 7};
    boolean[] bestMovesB = new boolean[9];
    
    int[] arrayWinA = { 0, 1, 2 };  //0,1,2
    int[] arrayWinB = { 3, 4, 5 };  //3,4,5
    int[] arrayWinC = { 6, 7, 8 };  //6,7,8
    int[] arrayWinD = { 0, 3, 6 };  //9,10,11
    int[] arrayWinE = { 1, 4, 7 };  //12,13,14
    int[] arrayWinF = { 2, 5, 8 };  //15,16,17
    int[] arrayWinG = { 0, 4, 8 };  //18,19,20
    int[] arrayWinH = { 2, 4, 6 };  //21,22,23
    
    boolean[] arrayWinA_A = new boolean[24];
	
    public static void main(String[] args)
    {	
    	cross_game frame = new cross_game(); 
    	frame.setSize(620, 500); 
        frame.createGUI();
        frame.setVisible(true); 
        frame.setTitle("cross game"); 
        frame.setIconImage(icon.getImage()); 
        frame.setResizable(false);
        }
    
    private void createGUI()
    {
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout() );
        
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 400));
        panel.setBackground(Color.white); 
        panel.setLayout(new GridLayout(3,3) );
        window.add(panel);
        panel.setFocusable(true);
        panel.addKeyListener(this);
        
        panelRight = new JPanel();
        panelRight.setPreferredSize(new Dimension(200, 400)); 
        //panelRight.setBackground(Color.green); 
        window.add(panelRight);
       
        panelRight_1 = new JPanel(); //contains restart and exit button
        panelRight_1.setPreferredSize(new Dimension(200, 200)); 
        //panelRight_1.setBackground(Color.blue); 
        panelRight.add(panelRight_1);
        
        panelRight_2 = new JPanel(); //contains score
        panelRight_2.setPreferredSize(new Dimension(200, 200)); 
        //panelRight_2.setBackground(Color.yellow); 
        panelRight.add(panelRight_2);
        
        //*******************************
        
        panelRight_2_1 = new JPanel(); //contains score
        panelRight_2_1.setPreferredSize(new Dimension(95, 30)); 
        //panelRight_2_1.setBackground(Color.yellow); 
        panelRight_2.add(panelRight_2_1);
        
        panelRight_2_2 = new JPanel(); //contains score
        panelRight_2_2.setPreferredSize(new Dimension(95, 30)); 
        //panelRight_2_2.setBackground(Color.blue); 
        panelRight_2.add(panelRight_2_2);
        
        panelRight_2_3 = new JPanel(); //contains score
        panelRight_2_3.setPreferredSize(new Dimension(95, 30)); 
        //panelRight_2_1.setBackground(Color.yellow); 
        panelRight_2.add(panelRight_2_3);
        
        panelRight_2_4 = new JPanel(); //contains score
        panelRight_2_4.setPreferredSize(new Dimension(95, 30)); 
        //panelRight_2_1.setBackground(Color.yellow); 
        panelRight_2.add(panelRight_2_4);
        
        panelRight_2_5 = new JPanel(); //contains score
        panelRight_2_5.setPreferredSize(new Dimension(95, 30)); 
        //panelRight_2_1.setBackground(Color.yellow); 
        panelRight_2.add(panelRight_2_5);
        
        panelRight_2_6 = new JPanel(); //contains score
        panelRight_2_6.setPreferredSize(new Dimension(95, 30)); 
        //panelRight_2_1.setBackground(Color.yellow); 
        panelRight_2.add(panelRight_2_6);

        
              
        buttonRestart = new JButton("Restart"); 
        buttonRestart.setPreferredSize(new Dimension(80, 35));
        buttonRestart.setMargin(new Insets(0,0,0,0));
        //buttonRestart.setIcon(restart);
        panelRight_1.add(buttonRestart);
        buttonRestart.addActionListener(this);
        buttonRestart.addKeyListener(this);
        
        buttonExit = new JButton("Exit");  
        buttonExit.setPreferredSize(new Dimension(80, 35));
        buttonExit.setMargin(new Insets(0,0,0,0));
        panelRight_1.add(buttonExit);
        buttonExit.addActionListener(this);
        
        gapField_human = new JTextField(4);
        gapField_PC = new JTextField(4);
        
        JLScore = new JLabel("Score:");
        JLHuman = new JLabel("Human:");
        JLPC    = new JLabel("Computer:");
        //JLBlank1    = new JLabel("               ");
        //JLBlank2    = new JLabel("           ");
        
        panelRight_2_1.add(JLScore);
        panelRight_2_3.add(JLHuman);
        //panelRight_2_4.add(gapField_human);
        //panelRight_2.add(JLBlank1);
        panelRight_2_5.add(JLPC);
        //panelRight_2_6.add(gapField_PC);
        //panelRight_2.add(JLBlank2);
        
        makeScore();

		

		setLocationRelativeTo(null); 
		menuSetup(); 
		makeEachButton();
    }
    
    private void makeScore() {
		String stringScoreHuman = Integer.toString(scoreHuman); 
		gapField_human.setText(stringScoreHuman);
		panelRight_2_4.add(gapField_human); 
		
		String stringScoreComputer = Integer.toString(scoreComputer); 
		gapField_PC.setText(stringScoreComputer);
		panelRight_2_6.add(gapField_PC); 
		
	}

	private void makeEachButton() 
    {
    	// 0 == x  1 == o  
        //JButton buttonPaper[]=new JButton[9]; 
		panel.removeAll(); 
		panel.updateUI(); 
        for(int i=0;i<allArray1CopyZ.length;i++) 
        {
	        if (allArray1CopyZ[i] == 2) 
	        {
	        	buttonArray[i] = new JButton(nothing);
	        	panel.add(buttonArray[i]); 
	        	buttonArray[i].addActionListener(this);
	        }
	        if (allArray1CopyZ[i] == 1) 
	        {
	        	buttonArray[i] = new JButton(x);
	        	panel.add(buttonArray[i]); 
	        	buttonArray[i].addActionListener(this);
	        }
	        if (allArray1CopyZ[i] == 0)
	        {
	        	buttonArray[i] = new JButton(o);
	            panel.add(buttonArray[i]);
	            buttonArray[i].addActionListener(this);
	        }
    	}
    }

    public void menuSetup()
    {
        topMenuBar = new JMenuBar();      //create a menu bar
        setJMenuBar(topMenuBar);          //set the menu bar to the JFrame
        
        scenario = new JMenu("File");     // File menu,
        scenario1 = new JMenuItem("scenario 1"); //one of the drop down options 
        scenario.add(scenario1);           
        scenario1.addActionListener(this); 
        scenario2 = new JMenuItem("scenario 2"); 
        scenario.add(scenario2);          
        scenario2.addActionListener(this); 
        scenario3 = new JMenuItem("scenario 3"); 
        scenario.add(scenario3);         
        scenario3.addActionListener(this); 
        topMenuBar.add(scenario);         

        editMenu = new JMenu("Edit");     
        mainEdit = new JMenuItem("Edit"); 
        editMenu.add(mainEdit);           
        mainEdit.addActionListener(this);
        partyMode = new JMenuItem("Party Mode!"); 
        editMenu.add(partyMode);           
        partyMode.addActionListener(this); 
        topMenuBar.add(editMenu );

        controls = new JMenu("Controls");
        controlsInfo = new JMenuItem("controls");
        controls.add(controlsInfo);
        controlsInfo.addActionListener(this);
        controlsEdit = new JMenuItem("Edit Controls");
        controls.add(controlsEdit);
        controlsEdit.addActionListener(this);
        topMenuBar.add(controls);

        helpMenu = new JMenu("Help");   // help menu, with  help topics, about application
        helpItem = new JMenuItem("Help Topics");
        helpMenu.add(helpItem);
        helpItem.addActionListener(this);
        aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);
        aboutItem.addActionListener(this);
        topMenuBar.add(helpMenu);
    }

	public void actionPerformed(ActionEvent event) //every time a button is pressed this method is played out
	{
        Object source = event.getSource(); 
        if(source == buttonRestart){
        	BooleanA = false;
        	restartButton();
        }
        if(source == buttonExit){
        	System.exit(0);
        }
        check();
        if(BooleanA != true){
            for(int i = 0; i < buttonArray.length; i++){
            	//check();
                if(source == buttonArray[i] && BooleanB[i] != true && count != 9){
                	buttonArray[i].setIcon(x);
                	allArray1CopyZ[i] = 1;
                	BooleanB[i] = true;
                	bestMovesB[i] = true;
                	BooleanA = true;
                	count++;
                	if(count == 9){
                		BooleanA = true;
                	}
                }
                else{
                	//does nothing
                }
            }
        }
        if(BooleanA){
        	//computerTurn();
        	computerTurnWin();
        }
        
    }
    // 0 = PC won // 1 == draw // 2 == human won
    // 0 == human x == pc

	private void check() {
		//score();
		checkIfWon();
		if(count == 9 || whoWon == 0 || whoWon == 2){
			//checkIfWon();
			//System.out.println(whoWon);
			if(whoWon == 0){
				JOptionPane.showMessageDialog(null, "You lost");
				//System.out.println("pc won");
				//whoWon = 1;
			}
			else if(whoWon == 1){
				JOptionPane.showMessageDialog(null, "No one won");
				//whoWon = 1;
			}
			else if(whoWon == 2){
				JOptionPane.showMessageDialog(null, "You won");
				//System.out.println("human won");
				score();
				//System.out.println("this" + whoWon);
				//whoWon = 1;
			}
			else{
				//System.out.println("test");
			}
            
            restart();
		}
		
	}

	private void restart() {
		System.arraycopy( allArray1Copy, 0, allArray1CopyZ, 0, allArray.length );
		count = 0;
	    //BooleanA = false;
		
	    BooleanB = new boolean[9];
	    bestMovesB = new boolean[9];
	    p = 0;
	    //whoWon = 1;
	    makeEachButton();
	}
	
	private void restartButton() {
		System.arraycopy( allArray1Copy, 0, allArray1CopyZ, 0, allArray.length );
		count = 0;
	    //BooleanA = false;
		
	    BooleanB = new boolean[9];
	    bestMovesB = new boolean[9];
	    //whoWon = 1;
	    p = 0;
	    makeEachButton();
	    
		scoreComputer = 0;
		scoreHuman = 0;
		makeScore();
	}
	/*
	private void computerTurn() {
		check();
		Random rand = new Random();
		int randInt = rand.nextInt(9);
		if(BooleanB[randInt] != true){
			BooleanB[randInt] = true;
			buttonArray[randInt].setIcon(o);
			allArray1CopyZ[randInt] = 0;
			count++;
			check();
			score();
        	if(count == 9){
        		BooleanA = false;
        		whoWon = 1;
        	}
		}
		else{
			computerTurn();
		}
    	BooleanA = false;
	}
	*/
	
	private void computerTurnWin() {
		check();
		if(BooleanB[bestMoves[p]] != true){
				BooleanB[bestMoves[p]] = true;
				bestMovesB[bestMoves[p]] = true; 
				buttonArray[bestMoves[p]].setIcon(o);
				allArray1CopyZ[bestMoves[p]] = 0;
				count++;
				check();
				score();
	        	if(count == 9){
	        		BooleanA = false;
	        		whoWon = 1;
	        	}
	        	p++;
		}
		else{
			p++;
			computerTurnWin();
		}
		BooleanA = false;
	}


	/*
    int[] arrayWinA = { 0, 1, 2 };  //0,1,2
    int[] arrayWinB = { 3, 4, 5 };  //3,4,5
    int[] arrayWinC = { 6, 7, 8 };  //6,7,8
    int[] arrayWinD = { 0, 3, 6 };  //9,10,11
    int[] arrayWinE = { 1, 4, 7 };  //12,13,14
    int[] arrayWinF = { 2, 5, 8 };  //15,16,17
    int[] arrayWinG = { 0, 4, 8 };  //18,19,20
    int[] arrayWinH = { 2, 4, 6 };  //21,22,23
    
    boolean[] arrayWinA_A = new boolean[24];
	*/
	
	
    // 1 == x    0 == 0      2 == nothing
    //  x == pc  0 == human 
	
    // 0 = PC won // 1 == draw // 2 == human won
    //int whoWon = 1;
    
	private void score(){
		if(whoWon == 2){
			scoreHuman++;
			makeScore();
		}
		else if(whoWon == 0){
			scoreComputer++;
			makeScore();
		}
		else if(whoWon == 1){
			//System.out.println("what");
		}
		else{
			//System.out.println("no one won (check) ");
		}
		

	}
	
	
	private void checkIfWon(){
		//human check
		if((allArray1CopyZ[0]==1 && allArray1CopyZ[1]==1 && allArray1CopyZ[2]==1) || (allArray1CopyZ[3]==1 && allArray1CopyZ[4]==1 && allArray1CopyZ[5]==1) || (allArray1CopyZ[6]==1 && allArray1CopyZ[7]==1 && allArray1CopyZ[8]==1)){
			whoWon = 2;
			
		}
		else if((allArray1CopyZ[0]==1 && allArray1CopyZ[3]==1 && allArray1CopyZ[6]==1) || (allArray1CopyZ[1]==1 && allArray1CopyZ[4]==1 && allArray1CopyZ[7]==1) || (allArray1CopyZ[2]==1 && allArray1CopyZ[5]==1 && allArray1CopyZ[8]==1)){
			whoWon = 2;

		}
		else if((allArray1CopyZ[0]==1 && allArray1CopyZ[4]==1 && allArray1CopyZ[8]==1) || (allArray1CopyZ[2]==1 && allArray1CopyZ[4]==1 && allArray1CopyZ[6]==1)){
			whoWon = 2;

		}
		//pc check
		else if((allArray1CopyZ[0]==0 && allArray1CopyZ[1]==0 && allArray1CopyZ[2]==0) || (allArray1CopyZ[3]==0 && allArray1CopyZ[4]==0 && allArray1CopyZ[5]==0) || (allArray1CopyZ[6]==0 && allArray1CopyZ[7]==0 && allArray1CopyZ[8]==0)){
			whoWon = 0;

		}
		else if((allArray1CopyZ[0]==0 && allArray1CopyZ[3]==0 && allArray1CopyZ[6]==0) || (allArray1CopyZ[1]==0 && allArray1CopyZ[4]==0 && allArray1CopyZ[7]==0) || (allArray1CopyZ[2]==0 && allArray1CopyZ[5]==0 && allArray1CopyZ[8]==0)){
			whoWon = 0;

		}
		else if((allArray1CopyZ[0]==0 && allArray1CopyZ[4]==0 && allArray1CopyZ[8]==0) || (allArray1CopyZ[2]==0 && allArray1CopyZ[4]==0 && allArray1CopyZ[6]==0)){
			whoWon = 0;

		}
		else{
			whoWon = 1;

		}
		//check();

	}


	
	
	public void keyPressed(KeyEvent e)  //if the up, down, right, or left arrow keys on the keyboard are pressed then the robot will move
	{ //key controls
	    if (e.getKeyCode() == KeyEvent.VK_UP ) 
	    {
	    } 
	    if (e.getKeyCode() == KeyEvent.VK_RIGHT ) 
	    {

	    } 
	    if (e.getKeyCode() == KeyEvent.VK_DOWN ) 
	    {
		
	    }
	    if (e.getKeyCode() == KeyEvent.VK_LEFT ) 
	    {

	    } 
	}
	public void keyReleased(KeyEvent e) 
	{
		//does nothing
	}

	public void keyTyped(KeyEvent e) 
	{
		//does nothing
	}
	
}



