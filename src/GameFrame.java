import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;
import javax.swing.JPanel;

public class GameFrame extends JPanel implements KeyListener{

	//Rectangle2D.Double backgrounder;
	Game lifeControled;
	String usrCmd;
	Scanner input = new Scanner(System.in);
	Boolean gameOn = true;



	/*public void gameLoop(){
		//System.out.println("in da loop");
		while (gameOn){
			//loops waiting for user to enter a command outside of the GUI
			//System.out.println("Enter Command:");
			//usrCmd = input.next();
			//if player wishes to save
			/*if (usrCmd.equalsIgnoreCase("save")){
				//prompt user and intake save game naem

			}
			//load game option
			else if (usrCmd.equalsIgnoreCase("load")){
				//prompt user and intake file name to load

				//lifeControled.mapOut();

			}
			//exit option
			else if (usrCmd.equalsIgnoreCase("exit")){

			//help option
			} else if (usrCmd.equalsIgnoreCase("help")){

			//newgame option
			} else if (usrCmd.equalsIgnoreCase("newgame")){

			} else {
				System.out.println("Invalid Command");
				//lifeControled.mapOut();
			}
		}
	}*/
	//trigers user keyboard input for main game control
	public void keyPressed(KeyEvent e) {
		//System.out.println("keyy!");
		switch (e.getKeyCode()){
		case KeyEvent.VK_W:
			//System.out.println("UPPPP");
			lifeControled.readCmd("up");
			repaint();
			//System.out.println("Enter Command:");
			break;
		case KeyEvent.VK_A:
			//System.out.println("AAAAAA");
			lifeControled.readCmd("left");
			repaint();
			//System.out.println("Enter Command:");
			break;
		case KeyEvent.VK_D:
			//System.out.println("GRRRRR");
			lifeControled.readCmd("right");
			repaint();
			//System.out.println("Enter Command:");
			break;
		case KeyEvent.VK_SPACE:
			//System.out.println("OOOOO");
			lifeControled.readCmd("shoot");
			repaint();
			//System.out.println("Enter Command:");
			break;
		}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {
	}
	//constructor with keylistener
	GameFrame(Game lifeControled){
		this.lifeControled = lifeControled;
		//backgrounder = new Rectangle2D.Double(0, 0, 600, 600);
		addKeyListener(this);
	}

	//sets Game object associated with Panel
	public void setSoul(Game g){
		lifeControled = g;
	}

	//clears gameboard then paints new set
	public void paint(Graphics g){
		requestFocusInWindow();
		Graphics2D g2 = (Graphics2D)g;
		g2.clearRect(0, 0, 600, 600);
		//g2.setColor( Color.YELLOW );
		//g2.fill( backgrounder );
		//sets fonts for use
		Font outFont = new Font ("Arial", Font.BOLD, 50);
		Font smallFont = new Font ("Arial", Font.PLAIN, 20);
		g2.setFont(outFont);
		int pPos = lifeControled.getPPos();
		//scrolls with player if not at end of map
		if (pPos < 44){
			int x = 0;
			for (int i = pPos + 5; i >= pPos; i--){
				x += 1;
				for (int j = 0; j < 6; j++){
					g2.drawString(lifeControled.level[i][j].getOut(), (j * 75) + 20, (x * 75) + 20);
				}
				//barrier for right edge
			}
		} else {
			//at end of lvl, screen ceases to scroll and stays fixed while player moved
			int x = 0;
			for (int i = 49; i >= 44; i--){
				x += 1;
				for (int j = 0; j < 6; j++){
					g2.drawString(lifeControled.level[i][j].getOut(), (j * 75) + 20, (x * 75) + 20);
				}
			}
		}
		//outputs message if player has died
		g2.setFont(smallFont);
		if (Game.you.getHealth() < 1){
			g2.drawString("You are dead, game over",100, 570);
		}
		//tracks player health and powerup and level
		g2.drawString("You have " + Game.you.getHealth() + " health remaining", 100,500);
		g2.drawString("Power up recieved: " + Game.you.getPower(), 100,520);
		g2.drawString("You are on level " + lifeControled.getThisLvl(), 100, 550);

	}

}
