import java.io.Serializable;
//import java.util.Arrays;
//import java.util.LinkedList;
import java.util.Scanner;


public class Game implements Serializable{

	//used for nextStep commands
	private static final int LEFT = 0;
	private static final int RIGHT = 1;
	private static final int UP = 2;
	private static final int SHOOT = 3;

	protected GameObject[][] level = new GameObject[50][6];
	private transient Scanner fileNamer = new Scanner(System.in);
	private static int playerPosition;
	private String currentCmd;
	private int currentLvl;

	//basic GameObject elements
	protected static Player you = new Player();
	protected static Terrain block = new Terrain();
	protected static Powerup yay = new Powerup();
	protected static Empty space = new Empty();
	protected Enemy e1L = new Enemy(1,true);
	protected Enemy e1R = new Enemy(1,false);
	protected static Snake righty = new Snake(true);
	protected static Snake lefty = new Snake(false);
	protected static Bouncer boing = new Bouncer(false);
	protected static Fairy sparkle = new Fairy(false);
	protected static Slider spikes = new Slider(false);
	protected static Bullet bf1 = new Bullet(true, 1);
	protected static Bullet bf2 = new Bullet(true, 2);
	protected static Bullet be1 = new Bullet(false, 1);
	protected static Bullet be2 = new Bullet(false, 2);

	//increments playerPosition as they move up the lvl
	public static void incPlayer(){
		playerPosition += 1;
	}

	private void nextStep(int move){
		int thisObj;

		//reset moved state of all GameObjects
		you.setMoved(false);
		e1L.setMoved(false);
		e1R.setMoved(false);
		bf1.setMoved(false);
		bf2.setMoved(false);
		be1.setMoved(false);
		be2.setMoved(false);
		lefty.setMoved(false);
		boing.setMoved(false);
		sparkle.setMoved(false);
		spikes.setMoved(false);

		//check if at end of level, and if not proceed using player position
		if (playerPosition < 44){
			for (int i = playerPosition; i < playerPosition + 6; i++){
				for (int j = 0; j < 6; j++){
					thisObj = level[i][j].getObjType();
					switch (thisObj){
					//space operations
					case 0:
						break;
						//player operations
					case 1:
						if (level[i][j].getMoved()){
							break;
						}
						you.mover(level, i, j, move);
						break;
					case 2:
						//terrain case
						break;
					case 3:
						//powerup case
						break;
					case 4:
						if (level[i][j].getMoved()){
							break;
						}
						Bullet tempObj;
						tempObj = (Bullet)level[i][j];
						if (tempObj.getFriend() && tempObj.getSpeed() == 1){
							bf1.mover(level, i, j, 0);
						} else if (tempObj.getFriend() && tempObj.getSpeed() == 2){
							bf2.mover(level, i, j, 0);
						} else if (!tempObj.getFriend() && tempObj.getSpeed() == 1){
							be1.mover(level, i, j, 0);
						} else {
							be2.mover(level, i, j, 0);
						}
						break;
					case 5:
						//dont let moved enemy move again
						if (level[i][j].getMoved()){
							break;
						}
						//moves enemy left or right based on getRight method
						//no need to check for edge or walls, enemys hard coded not to conflict with these
						e1R.mover(level, i, j, 0);
						break;
					case 6:
						//System.out.println(i);
						//ystem.out.println(j);
						if (level[i][j].getMoved()){
							break;
						}
						sparkle.mover(level, i, j, 0);
						break;
					case 7:
						if (level[i][j].getMoved()){
							break;
						}
						lefty.mover(level, i, j, 0);
						break;
					case 8:
						if (level[i][j].getMoved()){
							break;
						}
						//TODO:.mover(level, i, j, 0);
						break;
					case 9:
						if (level[i][j].getMoved()){
							break;
						}
						boing.mover(level, i, j, 0);
						break;
					case 10:
						if (level[i][j].getMoved()){
							break;
						}
						spikes.mover(level, i, j, 0);
						break;
					default:
						break;
					}
				}
			}
		} else {
			//same as above, but in case of end game screen, updates just the last portion regardless of playerPosition
			for (int i = 44; i < 50; i++){
				for (int j = 0; j < 6; j++){
					thisObj = level[i][j].getObjType();
					switch (thisObj){
					case 0:
						break;
					case 1:
						if (level[i][j].getMoved()){
							break;
						}
						you.mover(level, i, j, move);
						break;
					case 2:
						break;
					case 3:
						break;
					case 4:
						if (level[i][j].getMoved()){
							break;
						}
						Bullet tempObj;
						tempObj = (Bullet)level[i][j];
						if (tempObj.getFriend() && tempObj.getSpeed() == 1){
							bf1.mover(level, i, j, 0);
						} else if (tempObj.getFriend() && tempObj.getSpeed() == 2){
							bf2.mover(level, i, j, 0);
						} else if (!tempObj.getFriend() && tempObj.getSpeed() == 1){
							be1.mover(level, i, j, 0);
						} else {
							be2.mover(level, i, j, 0);
						}
						break;
					case 5:
						if (level[i][j].getMoved()){
							break;
						}
						e1R.mover(level, i, j, 0);
						break;
					case 6:
						if (level[i][j].getMoved()){
							break;
						}
						sparkle.mover(level, i, j, 0);
						break;
					case 7:
						if (level[i][j].getMoved()){
							break;
						}
						lefty.mover(level, i, j, 0);
						break;
					case 8:
						if (level[i][j].getMoved()){
							break;
						}
						//TODO:.mover(level, i, j, 0);
						break;
					case 9:
						if (level[i][j].getMoved()){
							break;
						}
						boing.mover(level, i, j, 0);
						break;
					case 10:
						if (level[i][j].getMoved()){
							break;
						}
						spikes.mover(level, i, j, 0);
						break;
					default:
						break;
					}
				}
			}

			if (playerPosition == 48){
				setup(currentLvl + 1);
			}
		}
	}


	//outputs current map to console
	public void mapOut(){
		//scrolls along leaving player at the bottom if not at end of lvl
		if (you.getPower()){
			System.out.println("You are Powered Up");
		} else {
			System.out.println("You are not Powered Up");
		}
		System.out.println("You have " + you.getHealth() + " health remaining");
		if (playerPosition < 44){
			for (int i = playerPosition + 5; i >= playerPosition; i--){
				//barrier for left edge
				System.out.print("|");
				for (int j = 0; j < 6; j++){
					//prints out for each object type
					System.out.print(level[i][j].getOut());
				}
				//barrier for right edge
				System.out.print("|");
				System.out.print('\n');
			}
		} else {
			//at end of lvl, screen ceases to scroll and stays fixed while player moved
			for (int i = 49; i >= 44; i--){
				System.out.print("|");
				for (int j = 0; j < 6; j++){
					System.out.print(level[i][j].getOut());
				}
				System.out.print("|");
				System.out.print('\n');
			}
		}
	}

	//prints out list of commands and objects to assist user
	public void helpOut(){
		System.out.print('\n');
		System.out.println("Help Menu:");
		System.out.println("'command' : action");
		System.out.print('\n');
		System.out.println("w key : moves character up");
		System.out.println("d key : moves character right");
		System.out.println("a key : moves character left");
		System.out.println("space : character shoots (Bullets are non-functoinal at this time)");
		System.out.println("'save' : prompts user for file name, then saves game");
		System.out.println("'load' : prompts user for file name, then loads game");
		System.out.println("'newgame' : starts a new game at level 1");
		System.out.println("'exit' : quits the game (auto saves game as 'default_save.txt')");
		System.out.print("\n \n");
		System.out.println("Characters:");
		System.out.println(":|: = You! (The player)");
		System.out.println(" X = Walls (Dont hit!)");
		System.out.println(">=< = Enemy-lvl 1 (Kill or avoid)");
		System.out.println("^-^ = Enemy-lvl 2 (Kill or avoid)");
		System.out.println("(P) = Powerup (collect these!)");
		System.out.print("\n \n");
	}

	//prints out welcome message and inform user of help command
	public void welcomeOut(){
		System.out.println("Welcome to TankWorld!");
		System.out.print('\n');
		System.out.println("Use the 'help' command for control instructions");
		System.out.print('\n');
		System.out.print('\n');
	}

	//sets player position and resets gameboard based on level passed in
	public void setup(int lvl){
		switch (lvl){
		case 1:
			currentLvl = 1;
			playerPosition = 0;
			you.setHealth(5);
			you.setPower(false);

			for (int i = 0; i < 50; i++){
				for (int j = 0; j<6; j++){
					level[i][j] = space;
				}
			}

			level[0][0] = you;
			level[5][0] = boing;
			level[7][0] = spikes;
			level[10][2] = boing;
			level[15][4] = boing;
			level[20][3] = boing;
			level[23][5] = spikes;
			level[25][1] = boing;
			level[30][0] = boing;
			level[35][2] = boing;
			level[33][1] = block;
			level[34][2] = block;
			level[36][5] = block;
			level[37][1] = yay;
			level[40][4] = boing;
			level[45][3] = boing;
			level[49][0] = block;
			level[49][1] = block;
			level[49][2] = block;
			level[49][3] = block;
			level[49][4] = block;
			level[49][5] = block;
			break;
			
			
					case 2:
			currentLvl = 2;
			playerPosition = 0;

			for (int i = 0; i < 50; i++){
				for (int j = 0; j<6; j++){
					level[i][j] = space;
				}
			}

			level[0][0] = you;
			level[2][1] = block;
			level[4][4] = block;
			level[6][2] = e1R;
			level[8][0] = block;
			level[8][1] = block;
			level[9][3] = sparkle;
			level[10][5] = yay;
			level[11][2] = e1R;
			level[11][4] = block;
			level[11][5] = block;
			level[14][1] = block;
			level[14][3] = e1R;
			level[16][1] = sparkle;
			level[17][2] = block;
			level[17][3] = block;
			level[19][5] = yay;
			level[20][1] = e1R;
			level[20][2] = block;
			level[22][0] = block;
			level[22][1] = block;
			level[23][2] = e1R;
			level[24][2] = block;
			level[26][1] = e1R;
			level[28][2] = block;
			level[28][3] = block;
			level[29][2] = e1R;
			level[29][5] = sparkle;
			level[30][1] = block;
			level[30][3] = block;
			level[31][1] = block;
			level[31][3] = block;
			level[32][4] = e1R;
			level[33][0] = block;
			level[33][1] = block;
			level[34][4] = block;
			level[35][5] = block;
			level[37][1] = yay;
			level[38][4] = block;
			level[39][1] = e1R;
			level[40][0] = block;
			level[41][3] = block;
			level[43][1] = e1R;
			level[44][0] = block;
			level[44][1] = block;
			level[44][2] = block;
			level[44][4] = yay;
			level[46][2] = block;
			level[46][3] = block;
			level[47][3] = e1R;
			level[48][0] = block;
			level[48][5] = block;
			level[49][0] = block;
			level[49][1] = block;
			level[49][2] = block;
			level[49][3] = block;
			level[49][4] = block;
			level[49][5] = block;
			break;

			
		case 3:
			currentLvl = 3;
			playerPosition = 0;

			for (int i = 0; i < 50; i++){
				for (int j = 0; j<6; j++){
					level[i][j] = space;
				}
			}
			
			level[0][0] = you;
			level[5][3] = lefty;
			level[13][3] = lefty;
			level[20][3] = lefty;
			level[28][3] = lefty;
			level[30][3] = lefty;
			level[37][3] = lefty;
			level[40][3] = lefty;
			level[45][3] = lefty;
			level[49][0] = block;
			level[49][1] = block;
			level[49][2] = block;
			level[49][3] = block;
			level[49][4] = block;
			level[49][5] = block;
			break;
		}

		//fills remaining spaces with blanks to avoid null references


	}

	//returns current level for output purposes
	public int getThisLvl(){
		return currentLvl;
	}

	//takes in command passed from Application and calls appropriate method
	public void readCmd(String cmd){
		currentCmd = cmd;
		//System.out.println("Current comdn: " + cmd);
		if (currentCmd.equalsIgnoreCase("left")){
			nextStep(LEFT);
		} else if (currentCmd.equalsIgnoreCase("right")){
			nextStep(RIGHT);
		} else if (currentCmd.equalsIgnoreCase("up")){
			nextStep(UP);
		} else if (currentCmd.equalsIgnoreCase("shoot")){
			nextStep(SHOOT);
		} else if (currentCmd.equalsIgnoreCase("load")){
			//loadHelper();
		}else if (currentCmd.equalsIgnoreCase("save")){ 
			//saveHelper();
		} else if (currentCmd.equalsIgnoreCase("help")){
			helpOut();
		}else if (currentCmd.equalsIgnoreCase("newgame")){
			setup(1);
		} else if (currentCmd.equalsIgnoreCase("exit")){
			//save("default_save.txt");
		}else {
			System.out.println("Invalid command");
		}
		//mapOut();
	}

	//player position getter
	public int getPPos(){
		return playerPosition;
	}

	//game constructor
	public Game(){
		playerPosition = 0;
	}
}
