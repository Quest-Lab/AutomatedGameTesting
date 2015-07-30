package Driver;

import java.util.ArrayList;

public class TestPath {

	ArrayList<StateMachineTransition> transitions=new ArrayList<StateMachineTransition>();
	Integer score;
	
	public TestPath(){
		this.score=0;
	}
	
	public void addNextStateMachineTransition(StateMachineTransition stateMachineTransition) {
		transitions.add(stateMachineTransition);
	}
	
	public ArrayList<StateMachineTransition> getStateMachineTransitions() {
		return this.transitions;
	}
	
	public Integer getScore() {
		Integer sum=0;
		boolean addScore=true;
		
		ArrayList<StateMachineTransition> transitions1=this.copyTransitions();
		for (StateMachineTransition stateMachineTransition : transitions) {
			
			transitions1.remove(0); // removing transition to avoid self match
			for (StateMachineTransition stateMachineTransition1 : transitions1) {
				if(stateMachineTransition.getTransitionName().equalsIgnoreCase(stateMachineTransition1.getTransitionName()))
				{
					addScore=false;
				}
			}
			if(addScore)
				sum=sum+stateMachineTransition.getPriorityScore();
			
			addScore=true;
		}
		this.score=sum;
		return this.score;
	}
	
	public ArrayList<StateMachineTransition> copyTransitions() {
		ArrayList<StateMachineTransition> transitions1=new ArrayList<StateMachineTransition>();
		for (StateMachineTransition stateMachineTransition : transitions) {
			transitions1.add(stateMachineTransition);
		}
		return transitions1;
	}
	
	public String print() {
		String path = "";
		path+=transitions.get(0).getSourceState() + ",";
		for (StateMachineTransition stateMachineTransition : transitions) {
			path+=stateMachineTransition.getTransitionName() + "," + stateMachineTransition.getTargetState() + ",";
		}//System.out.println(" ___SCORE: "+ this.getScore());
	return path;
	}
}
