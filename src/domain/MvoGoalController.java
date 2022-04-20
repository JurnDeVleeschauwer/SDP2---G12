package domain;

public class MvoGoalController {
	
	
	private MvoGoal goal;
	
	public MvoGoalController() {
		goal = new MvoGoal(); 
	}
	
	public void addMvoGoal(int id, int value, SdgComp sdgComp, Datasource datasource, String icon, String mvoName) {
		
		MvoGoal goal = new MvoGoal( id,  value,  sdgComp,  datasource,  icon,  mvoName); 
		goal.addMvoGoal(goal);
	}
	
	public void updateMvoGoal(int MvoGoalId) {
		
		MvoGoal MvoGoalToUpdate = getMvoGoal(MvoGoalId); 
		
		goal.updateMvoGoal(MvoGoalToUpdate);
	}
	
	
	public MvoGoal getMvoGoal(int MvoGoalId) {
		
		return goal.getMvoGoal(MvoGoalId);
	}
	
	
	public void deleteMvoGoal(MvoGoal MvoGoal) {
		goal.deleteMvoGoal(MvoGoal); 
	}
	
	

}
