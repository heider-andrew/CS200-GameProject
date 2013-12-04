
public class Snake extends GameObject{

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
	
	//flips moved right for tracking purposes
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
	
	//movedRight Getter
	public Boolean getRight(){
		return movedRight;
	}
	
	//processes Snake movement and updates GameObject grid accordingly
	public void mover(GameObject[][] level, int xPos, int yPos, int dir){
		int i = xPos;
		int j = yPos;
		
		if (level[i][j].getMoved()){
			return;
		}
		//moves enemy left or right based on getRight method along with down
		//no need to check for edge or walls, enemys hard coded not to conflict with these
		if (getRight()){
			if (level[i-1][j-1].getObjType() != 1){
			level[i][j] = Game.space;
			level[i-1][j-1] = this;
			setMoved(true);
			flip();
			return;
			} else {
				level[i][j] = Game.space;
				Game.you.damage(2);
			}
		} else {
			if (level[i-1][j+1].getObjType() != 1){
				level[i][j] = Game.space;
				level[i-1][j+1] = this;
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
	public Snake(Boolean startLeft){
		outString = "*w*";
		objType = 7;
		movedRight = startLeft;
		moved = false;
	}
}
