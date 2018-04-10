import java.util.concurrent.ThreadLocalRandom;

public class Clerk extends Thread{
 Office office;
 int ID;
 
 public Clerk(int ID, Office office){
  this.ID = ID;
  this.office= office;
 }
 
 public void run() {
  
  int work_time = ThreadLocalRandom.current().nextInt(5,11);
  work_time = work_time * 1000;
  
  while(true) {
   Desk desk = office.arrive();

   try {
	System.out.println(this.ID+"is working");
    Thread.sleep(work_time);
   } catch (InterruptedException e) {
    e.printStackTrace();
   }
   System.out.println(this.ID+"finished working");
   office.leave(desk);
  }
 }
}