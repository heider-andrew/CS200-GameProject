
public class Fairy extends GameObject{

	private Boolean moved;
	private Boolean movedUp;

	//moved getter
	public Boolean getMoved(){
		return moved;
	}

	//movedUp getter
	public Boolean getMoveUp(){
		return movedUp;
	}

	//moved setter
	public void setMoved(Boolean move){
		moved = move;
	}

	//toggles movedUp for tracking purposes
	public void flip(){
		if (movedUp){
			movedUp = false;
		} else{
			movedUp = true;
		}
	}

	//outString setter
	public String getOut() {
		return outString;
	}

	//processes Fairy movement and updates GameObject Grid Accordingly
	public void mover(GameObject[][] level, int xPos, int yPos, int dir){
		int i = xPos;
		int j = yPos;
		//System.out.println(i);
		//System.out.println(j);
		//moves up or down based on getMoveUp method
		//no need to check for edge or walls, hard coded not to conflict with these
		if (getMoveUp()){
			if (level[i-1][j].getObjType() != 1){
				level[i-1][j] = Game.sparkle;
				level[i][j] = Game.space;
				setMoved(true);
				flip();
				return;
			}
		} else {
			if (level[i+1][j].getObjType() != 1){
				level[i+1][j] = Game.sparkle;
				level[i][j] = Game.space;
				setMoved(true);
				flip();
				return;
			}
		}
	}

	//constructor
	public Fairy(Boolean startDown){
		objType = 6;
		outString = " * ";
		movedUp = startDown;
		moved = false;
	}

}
