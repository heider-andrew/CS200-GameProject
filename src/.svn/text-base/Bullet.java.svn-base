
public class Bullet extends GameObject{
	
	private Boolean friendly;
	private int speed;
	private Boolean moved;
	
	//moved getter
	public Boolean getMoved(){
		return moved;
	}

	//moved setter
	public void setMoved(Boolean move){
		moved = move;
	}

	//outstring getter
	public String getOut(){
		return outString;
	}
	
	//speed setter
	public int getSpeed(){
		return speed;
	}
	
	//friendly setter
	public Boolean getFriend(){
		return friendly;
	}
	
	//speed setter
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	//friendly setter
	public void setFriend(Boolean friend){
		friendly = friend;
	}
	
	//processes Bullet movement and updates GameObject grid
	public void mover(GameObject[][] level, int xPos, int yPos, int dir){
		int checkObj;
		int i = xPos;
		int j = yPos;

		if (friendly){
			checkObj = level[i+speed][j].getObjType();
			if (checkObj == 5 || checkObj == 6){
				level[i+speed][j] = Game.space;
				level[i][j] = Game.space;
			} else if (checkObj == 2 || checkObj == 3){
				level[i][j] = Game.space;
			} else {
				level[i+speed][j] = this;
				level[i][j] = Game.space;
			}
		} else {
			checkObj = level[i-speed][j].getObjType();
			if (checkObj == 5 || checkObj == 6){
				level[i-speed][j] = Game.space;
				level[i][j] = Game.space;
			} else if (checkObj == 2 || checkObj == 3){
				level[i][j] = Game.space;
			} else if (checkObj == 0){
				
			} else {
				level[i-speed][j] = this;
				level[i][j] = Game.space;
			}
		}
		moved = true;
	}

	public Bullet(Boolean friend, int speed){
		outString = " ' ";
		objType = 4;
		friendly = friend;
		this.speed = speed;
	}
}
