
public class Powerup extends GameObject{

	//outstring getter
	public String getOut(){
		return outString;
	}
	
	//no movement
	public void mover(GameObject[][] level, int xPos, int yPos, int dir){
		
	}
	
	//constructor
	public Powerup(){
		outString = "(P)";
		objType = 3;
	}
}
