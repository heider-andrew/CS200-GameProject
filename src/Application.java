//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.*;


public class Application extends JFrame implements Serializable{

	public Application(){
		//creates new game and gameframe objects for use
		final Game realLife = new Game();
		GameFrame soulWindow = new GameFrame(realLife);
		//sets up the first level of the game
		realLife.setup(1);
		//creates a menubar for user controls
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu mainMenu = new JMenu("Menu");
		menuBar.add(mainMenu);
		//new game menu item
		JMenuItem newItem = new JMenuItem("New Game");
		newItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				newGame(realLife);
			}
		});
		mainMenu.add(newItem);
		//high scores menu item
		JMenuItem highItem = new JMenuItem("High Scores");
		highItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null, "    High Scores \n 1)Crimzon - 999999\n 2)CP Jones - 987635\n 3)LolCatZ - 963554\n 4)Freddy`.=. - 887654\n 5)Juddas - 867234\n 6)TheGMan - 767783\n 7)Dipski - 653380\n 8)DukeNuke - 583290\n 9)MintyX - 533057\n 10)Knexer - 428920\n", "High Scores",1);
				//highScores();
			}
		});
		mainMenu.add(highItem);
		//save game menu item
		JMenuItem saveItem = new JMenuItem("Save");
		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				saveGame(realLife);
			}
		});
		mainMenu.add(saveItem);
		//load game menu item
		JMenuItem loadItem = new JMenuItem("Load");
		loadItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				loadGame(realLife);
			}
		});
		mainMenu.add(loadItem);
		//help dialogue menu item
		JMenuItem helpItem = new JMenuItem("Help");
		helpItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null, "Commands:\nw key - moves character up\nd key - moves character right\na key - moves character left\nspace - character shoots\n\n\nCharacters:\n:|: = You! (The player)\n X = Walls (Dont hit!)\n>=< = Enemy (Kill or avoid)\n(P) = Powerup (collect these!)\n * = Fairy (collect for health)", "Help", 2);
				//helpMenu(realLife);
			}
		});
		mainMenu.add(helpItem);
		//exit game menu item
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				exitGame(realLife);
			}
		});
		//adds menu to frame and sets visable
		mainMenu.add(exitItem);
		menuBar.setVisible(true);

		//sets up border layout and adds GameFrame the the main JFrame
		setLayout(new BorderLayout());
		add(soulWindow, BorderLayout.CENTER);

	}

	public void saveGame(Game realLife){
		//System.out.println("please enter filename to save:");
		//Scanner input = new Scanner(System.in);
		String savename = JOptionPane.showInputDialog("Please enter filename to save:", "");
		try {
			//open a new stream, save Game object to stream, and close stream
			ObjectOutputStream gameOut = new ObjectOutputStream(new FileOutputStream(savename + ".txt"));
			gameOut.writeObject(realLife);
			gameOut.close();
			//alert user to errors
		} catch (FileNotFoundException e) {
			System.out.println("Error in finding save file");
		} catch (IOException e) {
			System.out.println("Error in writing to file");
			e.printStackTrace();
		}
	}

	public void loadGame(Game realLife){
		//System.out.println("please enter filename to load:");
		//Scanner input = new Scanner(System.in);
		String loadname = JOptionPane.showInputDialog("Please enter filename to load:", "");
		try {
			//create new input stream, open file, and restore Game object, then close stream
			ObjectInputStream gameIn = new ObjectInputStream(new FileInputStream(loadname + ".txt"));
			try {
				realLife = (Game)(gameIn.readObject());
			} catch (ClassNotFoundException e) {
				System.out.println("Error in class read");
			}
			gameIn.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error in finding load file");
		} catch (IOException e) {
			System.out.println("Error in file input");
		}
		repaint();
	}

	public void newGame(Game realLife){
		//realLife.readCmd("newgame");
		//sets up a new game and refreshes the screen
		realLife.setup(1);
		repaint();
	}

	public void exitGame(Game realLife){
		ObjectOutputStream gameExit;
		try {
			//save Game object to default save file
			gameExit = new ObjectOutputStream(new FileOutputStream("default_save.txt"));
			gameExit.writeObject(realLife);
			gameExit.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error in finding save file");
		} catch (IOException e) {
			System.out.println("Error in writing to file");
		}
		//System.out.println("Tryin to exit");
		dispose();
		return;
	}

	public static void main(String[] args) {
		//sets up the Application and sets JFrame options
		Application app = new Application();
		app.setSize(490, 650);
		app.setVisible(true);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//sets up initial state of realLife and returns an error and quits if something went wrong
		//welcome player
		//realLife.welcomeOut();

		//realLife.mapOut();
		//prompt user and intake initial command
		//System.out.println("Enter Command:");
		//usrCmd = input.next();
		//loop till exit condition
		//while (true){
		//check user command with Game class
		//realLife.readCmd(usrCmd);

		//Enters Main GameFrame Loop
		//soulWindow.setupFrame();
		//soulWindow.gameLoop();


		//save game option
		//System.out.println("Quitin time boss");
		//prompt user for next command
		//System.out.println("Enter Command:");
		//usrCmd = input.next();
	}
}

