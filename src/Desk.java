
public class Desk {
	public boolean condition_clean = true;
	 
	 public Desk() {
	 }
	 
	 public boolean get() {
	  return condition_clean;
	 }
	 public void setDeskClean() {
	  condition_clean = true;
	 }
	 
	 public void setDeskDirty() {
	  condition_clean = false;
	 }

}
