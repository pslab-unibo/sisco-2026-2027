package sisco.deadlock_obs;

public class AgentTwo extends Thread {
 	MyObserver obj;
	
 	public AgentTwo(MyObserver obj){
 		this.obj = obj;
 	}
 	
	public void run(){
		while (true){
			log("overall state: "+obj.getOverallState());
		}
	}

	private void log(String msg){
		synchronized(System.out){
			System.out.println("["+this+"] "+msg);
		}
	}
}

