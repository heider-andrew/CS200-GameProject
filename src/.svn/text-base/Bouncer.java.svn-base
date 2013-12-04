
public class Bouncer extends GameObject{

	private Boolean movedRight;
	private Boolean moved;

	//moved getter
	public Boolean getMoved(){
		return moved;
	}

	//moved setter
	public void setMoved(Boolean move){
		moved = move;
	}

	//flips movedRight for tracking purposes
	public void flip(){
		if (movedRight){
			movedRight = false;
		} else{
			movedRight = true;
		}
	}

	//outstring getter
	public String getOut(){
		return outString;
	}

	//outstring setter
	public void setOutString(String newOut){
		outString = newOut;
	}


	//moved right getter
	public Boolean getRight(){
		return movedRight;
	}

	//processes Bouncer movement and updates GameObject Grid
	public void mover(GameObject[][] level, int xPos, int yPos, int dir){
		int i = xPos;
		int j = yPos;

		if (level[i][j].getMoved()){
			return;
		}
		//moves enemy left or right based on getRight method
		//no need to check for edge or walls, enemys hard coded not to conflict with these
			if (j < 6 && !movedRight){
				if (level[i][j+1].getObjType() != 1){
				level[i][j] = Game.space;
				level[i][j+1] = this;
				setMoved(true);
				if (j+1 == 5){
					flip();
				}
			} else {
				level[i][j] = Game.space;
				Game.you.damage(2);
			}
		} else {
			if (level[i][j-1].getObjType() != 1){
				level[i][j] = Game.space;
				level[i][j-1] = this;
				setMoved(true);
				if (j-1 == 0){
					flip();
				}
			} else {
				level[i][j] = Game.space;
				Game.you.damage(2);
			}
		}
	}

	//default constructor
	public Bouncer(Boolean startLeft){
		outString = "o-o";
		objType = 9;
		movedRight = startLeft;
		moved = false;
	}
}
