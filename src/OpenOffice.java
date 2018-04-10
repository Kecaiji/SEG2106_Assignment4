import java.util.Queue;
import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OpenOffice implements Office {

	Queue<Desk> cleanDesks = new LinkedList<Desk>();
	Queue<Desk> dirtyDesks = new LinkedList<Desk>();
	private final Lock lock = new ReentrantLock(true);
	Condition clean = lock.newCondition();
	Condition dirty= lock.newCondition();
	
	OpenOffice(){
		Desk[] arr_d = new Desk[100];
		
		
		for (int i =0; i<100;i++){
			arr_d[i] = new Desk();
			arr_d[i].setDeskClean();
			cleanDesks.add(arr_d[i]);
		}
		
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Office of= new OpenOffice(); 
		Clerk[] arr_clerk = new Clerk[120];
		Cleaner[] arr_cleaner = new Cleaner[20];
		for (int i =0; i<120;i++){
			arr_clerk[i] = new Clerk(i,of);
			//arr_clerk[i].start();
			//System.out.println(arr_clerk[i].isAlive());
			//System.out.println(arr_clerk[i].getState());
			arr_clerk[i].run();
		}
		
		for (int i =0; i<20;i++){
			arr_cleaner[i] = new Cleaner(i);
			arr_cleaner[i].run();
		}
		
	}

	@Override
	public Desk arrive() {
		while(cleanDesks.size()==0){
			try {
			    clean.wait();
			   } catch (InterruptedException e) {
			    e.printStackTrace();
			   }
			cleanDesks.poll();
		}
		
		return cleanDesks.poll();
	}

	@Override
	public void leave(Desk desk){
		lock.lock(); // Lock to ensure mutually exclusive access
		try{
			dirtyDesks.add(desk); 
			dirty.signalAll();
			}
		finally{
		lock.unlock();// Whenever you lock, you must unlock
		}
		
	}

	@Override
	public Desk service() {
		
		while(dirtyDesks.size() == 0) { 
			   try {
				   dirty.wait();
			   } catch (InterruptedException e) {
			    e.printStackTrace();
			   }
		}
		return dirtyDesks.poll();
	}

	@Override
	public void cleaned(Desk desk) {
		lock.lock(); // Lock to ensure mutually exclusive access
		try{
			cleanDesks.add(desk); 
			clean.signalAll();
			}
		finally{
		lock.unlock();// Whenever you lock, you must unlock
		}
		}

	}


