package domain;

public class MvoGoalController {
	
	
	private MvoGoalChild goal;
	
	public MvoGoalController() {
		goal = new MvoGoalChild(); 
	}
	
	public void addMvoGoal(int id, int value, SdgComp sdgComp, Datasource datasource, String icon, String mvoName) {
		
		MvoGoalChild goal = new MvoGoalChild( id,  value,  sdgComp,  datasource,  icon,  mvoName); 
		goal.addMvoGoal(goal);
	}
	
	public void updateMvoGoal(int MvoGoalId) {
		
		MvoGoalChild MvoGoalToUpdate = getMvoGoal(MvoGoalId); 
		
		goal.updateMvoGoal(MvoGoalToUpdate);
	}
	
	
	public MvoGoalChild getMvoGoal(int MvoGoalId) {
		
		return goal.getMvoGoal(MvoGoalId);
	}
	
	
	public void deleteMvoGoal(MvoGoalChild MvoGoal) {
		goal.deleteMvoGoal(MvoGoal); 
	}
	
	

}
