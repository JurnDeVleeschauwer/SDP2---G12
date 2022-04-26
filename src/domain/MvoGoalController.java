package domain;

public class MvoGoalController {
	
	
	private MvoGoalManager mvoGoalManager;
	
	public MvoGoalController() {
		mvoGoalManager = new MvoGoalManager(); 
	}
	
	public void addMvoGoal(int id, int value, SdgComp sdgComp, Datasource datasource, String icon, String mvoName) {
		
		MvoGoalChild goal = new MvoGoalChild( id,  value,  sdgComp,  datasource,  icon,  mvoName); 
		goal.addMvoGoal(goal);
	}
	
	public void updateMvoGoal(int MvoGoalId) {
		
		MvoGoalChild MvoGoalToUpdate = getMvoGoal(MvoGoalId); 
		
		mvoGoalManager.updateMvoGoal(MvoGoalToUpdate);
	}
	
	
	public MvoGoalChild getMvoGoal(int MvoGoalId) {
		
		return mvoGoalManager.getMvoGoal(MvoGoalId);
	}
	
	
	public void deleteMvoGoal(MvoGoalChild MvoGoal) {
		mvoGoalManager.deleteMvoGoal(MvoGoal); 
	}
	
	

}
