
public class Player extends GameObject {

	private static final int LEFT = 0;
	private static final int RIGHT = 1;
	private static final int UP = 2;
	private static final int SHOOT = 3;

	private Boolean powered;
	private int health;
	private int score;
	private Boolean moved;

	//moved getter
	public Boolean getMoved(){
		return moved;
	}

	//moved setter
	public void setMoved(Boolean move){
		moved = move;
	}

	//increases player score
	public void upScore(int value){
		score += value;
	}

	//sets player score
	public void setScore(int value){
		score = value;
	}

	//powers up a player
	public void powerUp(){
		powered = true;
	}

	//processes damage to player
	public void damage(int value){
		health -= value;
		powered = false;
	}

	//sets player health
	public void setHealth(int value){
		health = value;
	}

	//player score getter
	public int getScore(){
		return score;
	}
	
	//powered setter
	public void setPower(Boolean pow){
		powered = pow;
	}

	//powered getter
	public Boolean getPower(){
		return powered;
	}

	//health getter
	public int getHealth(){
		return health;
	}

	//outString getter
	public String getOut(){
		return outString;
	}
	//helps determine player movement based on user input from keyboard
	public void mover(GameObject[][] level, int xPos, int yPos, int direction){
		int move = direction;
		int i = xPos;
		int j = yPos;
		int checkObj;
		
		if (health < 1){
			level[i][j] = Game.space;
			return;
		}

		switch (move){
		case LEFT:
			if (j == 0 || getMoved()){
				break;
			} else {
				checkObj = level[i][j-1].getObjType();
				if (checkObj == 3){
					powerUp();
				} else if (checkObj == 2){
					setHealth(0);
					level[i][j] = Game.space;
					break;
				} else if (checkObj == 6){
					damage(-2);
				} else if (checkObj == 5){
					damage(2);
				}
				level[i][j-1] = this;
				level[i][j] = Game.space;
			}
			break;
		case RIGHT:
			if (j == 5 || getMoved()){
				break;
			} else {
				checkObj = level[i][j+1].getObjType();
				if (checkObj == 3){
					powerUp();
				} else if (checkObj == 2){
					setHealth(0);
					level[i][j] = Game.space;
					break;
				} else if (checkObj == 6){
					damage(-2);
				} else if (checkObj == 5){
					damage(2);
				}
				level[i][j+1] = this;
				level[i][j] = Game.space;
				setMoved(true);
				break;
			}
		case UP:
			if (getMoved()){
				break;
			}
			checkObj = level[i+1][j].getObjType();
			if (checkObj == 3){
				powerUp();
			} else if (checkObj == 2){
				setHealth(0);
				level[i][j] = Game.space;
				break;
			} else if (checkObj == 6){
				damage(-2);
			} else if (checkObj == 5){
				damage(2);
			}
			level[i+1][j] = this;
			level[i][j] = Game.space;
			Game.incPlayer();
			setMoved(true);
			break;
		case SHOOT:
			//shoot
			if (!powered){
				checkObj = level[i+1][j].getObjType();
				if (checkObj == 5 || checkObj == 6){
					level[i+1][j] = Game.space;
				} else if (checkObj == 2 || checkObj == 3){
					//level[i][j] = Game.space;
				} else {
					level[i+1][j] = Game.bf1;
					Game.bf1.setMoved(true);
				}
			} else {
				checkObj = level[i+2][j].getObjType();
				if (checkObj == 5 || checkObj == 6){
					level[i+2][j] = Game.space;
				} else if (checkObj == 2 || checkObj == 3){
					//do nothing
				} else {
					level[i+2][j] = Game.bf2;
					Game.bf2.setMoved(true);
				}
			}
		}
	}

	//constructor
	public Player(){
		objType = 1;
		outString = ":!:";
		moved = false;
		powered = false;
		health = 5;
	}
}
