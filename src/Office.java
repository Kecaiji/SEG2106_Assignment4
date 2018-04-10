
public interface Office {
	public Desk arrive();    
	 
	public void leave(Desk desk);  
	 
	public Desk service(); 
	 
	public void cleaned(Desk desk); 

}
