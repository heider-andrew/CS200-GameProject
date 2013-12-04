import java.util.Random;


public class Shooter extends GameObject{

	private Boolean movedRight;
	private Random shootGen = new Random(4191991);	
	private Boolean moved;

	//moved getter
	public Boolean getMoved(){
		return moved;
	}

	//moved setter
	public void setMoved(Boolean move){
		moved = move;
	}

	//toggles moved right for tracking
	public void flip(){
		if (movedRight){
			movedRight = false;
		} else{
			movedRight = true;
		}
	}

	//outString getter
	public String getOut(){
		return outString;
	}

	//movedRight getter
	public Boolean getRight(){
		return movedRight;
	}

	//determines if it will shoot that move
	public Boolean shooter(){
		double p = shootGen.nextFloat();
		if (p > 0.2)
			return true;
		else
			return false;
	}

	//processes movement for this turn, then updates GameObject Grid accordingly
	public void mover(GameObject[][] level, int xPos, int yPos, int dir){
		int i = xPos;
		int j = yPos;

		if (level[i][j].getMoved()){
			return;
		}
		//moves enemy left or right based on getRight method
		//no need to check for edge or walls, enemys hard coded not to conflict with these
		if (getRight()){
			if (level[i][j-1].getObjType() != 1){
				level[i][j] = Game.space;
				level[i][j-1] = this;
				setMoved(true);
				flip();
				return;
			} else {
				level[i][j] = Game.space;
				Game.you.damage(2);
			}
		} else {
			if (level[i][j+1].getObjType() != 1){
				level[i][j] = Game.space;
				level[i][j+1] = this;
				setMoved(true);
				flip();
				return;
			} else {
				level[i][j] = Game.space;
				Game.you.damage(2);
			}
		}
	}

	//constructor
	public Shooter(int lvl, Boolean startLeft){
		outString = "'|'";
		objType = 8;
		movedRight = startLeft;
		moved = false;
	}
}
