
public class Slider extends GameObject{

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

	//flips moved right for tracking
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

	//outString setter
	public void setOutString(String newOut){
		outString = newOut;
	}


	//moved right getter
	public Boolean getRight(){
		return movedRight;
	}

	//processes Slider movement and updates gameobject grid accordingly
	public void mover(GameObject[][] level, int xPos, int yPos, int dir){
		int i = xPos;
		int j = yPos;

		if (level[i][j].getMoved()){
			return;
		}
		//moves enemy left or right based on getRight method
		//no need to check for edge or walls, enemys hard coded not to conflict with these
		for (int z = 0; z<6; z++){
			if (level[i][z].getObjType() == 1){
				if(z > j){
					if (level[i][j+1].getObjType() != 1){
					level[i][j] = Game.space;
					level[i][j+1] = this;
					setMoved(true);
					} else {
						level[i][j] = Game.space;
						Game.you.damage(2);
					}
				} else {
					if (level[i][j-1].getObjType() != 1){
					level[i][j] = Game.space;
					level[i][j-1] = this;
					setMoved(true);
					} else {
						level[i][j] = Game.space;
						Game.you.damage(2);
					}
				}
			}
		}
	}

	//constructor
	public Slider(Boolean startLeft){
		outString = "<o>";
		objType = 10;
		movedRight = startLeft;
		moved = false;
	}
}
