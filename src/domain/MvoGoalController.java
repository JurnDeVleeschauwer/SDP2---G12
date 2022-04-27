package domain;

import java.util.List;

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
		
		MvoGoalAbstract MvoGoalToUpdate = getMvoGoal(MvoGoalId); 
		
		mvoGoalManager.updateMvoGoal(MvoGoalToUpdate);
	}
	
	
	public MvoGoalAbstract getMvoGoal(int MvoGoalId) {
		
		return mvoGoalManager.getMvoGoal(MvoGoalId);
	}
	
	
	public void deleteMvoGoal(MvoGoalChild MvoGoal) {
		mvoGoalManager.deleteMvoGoal(MvoGoal); 
	}
	
	public List<MvoGoalAbstract> getAll() {
		return mvoGoalManager.getAll(); 
	}
	

}
