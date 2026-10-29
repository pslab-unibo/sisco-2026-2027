package sisco.deadlock_obs;

public class AgentOne extends Thread {
 	MyObservedEntity obj;
	
 	public AgentOne(MyObservedEntity obj){
 		this.obj = obj;
 	}
 	
	public void run(){
		while (true){
			obj.changeState1();
			obj.changeState2();
		}
	}
}
