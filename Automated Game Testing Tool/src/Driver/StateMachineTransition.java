package Driver;

public class StateMachineTransition {

	private String funcName;
	private String sourceState, targetState;
	private Integer priorityScore;
	private String parentStateName;

	public StateMachineTransition(String funcName, String sourceState,
			String targetState, String parentStateName) {
		this.funcName = funcName;
		this.sourceState = sourceState;
		this.targetState = targetState;
		this.parentStateName=parentStateName;
	}

	public void setPriorityScore(Integer score) {
		this.priorityScore=score;
	}
	
	public Integer getPriorityScore() {
		return this.priorityScore;
	}
	
	public String getParentStateName()
	{
		return this.parentStateName;
	}
	
	public String getTransitionName()
	{
		return this.funcName;
	}
	
	public String getSourceState() {
		return this.sourceState;
	}
	
	public String getTargetState() {
		return this.targetState;
	}
	public void print() {
		System.out.println("ParentState: "+ parentStateName+" Name: " + funcName + "  Source_State: "
				+ sourceState + "  TargetState: " + targetState);
	}
}
