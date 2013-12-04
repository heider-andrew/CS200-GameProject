import java.util.Random;


public class Enemy extends GameObject{

	private int lvl;
	private Boolean movedRight;
	private Random shootGen = new Random(4191991);	
	private Boolean moved;
	
	//checks for enemey level and updates the outstring
	private void checklvl(){
		if (lvl == 1){
			setOutString(">-<");
		} else
			setOutString("^-^");
	}
	
	//demotes enemy level
	private void demote(){
		lvl = lvl - 1;
	}

	//moved getter
	public Boolean getMoved(){
		return moved;
	}
	
	//moved setter
	public void setMoved(Boolean move){
		moved = move;
	}
	
	//flip moved right for tracking purposes
	public void flip(){
		if (movedRight){
			movedRight = false;
		} else{
			movedRight = true;
		}
	}
	
	//demotes an enemy if hit
	public int hit(){
		if (lvl > 0){
			demote();
			return 1;
		} else {
			return 0;
		}
	}
	
	//outstring getter, checks for appropriate level out first
	public String getOut(){
		checklvl();
		return outString;
	}
	
	//outstring setter
	public void setOutString(String newOut){
		outString = newOut;
	}
	
	//lvl getter
	public int getLvl(){
		return lvl;
	}
	
	public Boolean getRight(){
		return movedRight;
	}
	
	//determines if enemy shoots or not
	public Boolean shooter(){
		if (lvl == 1){
			double p = shootGen.nextFloat();
			if (p > 0.2)
				return true;
			else
				return false;
		}else {
			return false;
		}
	}
	
	//processes enemy movement and updates GameObject Grid
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
	public Enemy(int lvl, Boolean startLeft){
		outString = "^-^";
		this.lvl = lvl;
		objType = 5;
		movedRight = startLeft;
		moved = false;
	}
}
