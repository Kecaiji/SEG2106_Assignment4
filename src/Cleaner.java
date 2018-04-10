import java.util.concurrent.ThreadLocalRandom;

public class Cleaner extends Thread{
 Office office;
 int ID;
 
 public Cleaner(int ID){
  this.ID = ID;
 }
 
 public void run() {
  int clean_time = ThreadLocalRandom.current().nextInt(1,6);
  clean_time = clean_time * 1000;
  while(true) {
   Desk desk = office.service();

   try {
	System.out.println(this.ID+"is cleaning");
    Thread.sleep(clean_time);
   } catch (InterruptedException e) {
 
    e.printStackTrace();
   }
   System.out.println(this.ID+"finished cleaning");
   office.cleaned(desk);
  }
 }
}