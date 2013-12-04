import java.io.Serializable;


public abstract class GameObject implements Serializable{
	
	protected String outString;
	protected int objType;
	private Boolean moved;
	
	//moved getter
	public Boolean getMoved(){
		return moved;
	}
	
	//moved setter
	public void setMoved(Boolean move){
		moved = move;
	}
	
	//abstract get out
	abstract String getOut();
	
	//abstract mover for objects
	abstract void mover(GameObject[][] level, int xPos, int yPos, int direction);
	
	//returns object type
	public int getObjType(){
		return objType;
	}
}
