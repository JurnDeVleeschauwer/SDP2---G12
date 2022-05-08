package domain;

import java.util.List;

public class MvoGoalController {
	
	
	private MvoGoalManager mvoGoalManager;
	
	public MvoGoalController() {
		mvoGoalManager = new MvoGoalManager(); 
	}
	
	public void addMvoGoalChild( int value, Datasource datasource, String icon, String mvoName) {
		MvoGoalChild goal = new MvoGoalChild(value, datasource,  icon,  mvoName); 
		System.out.println(goal.toString());
		mvoGoalManager.addMvoGoal(goal);
	}
	
	public void addMvoGoalComp(String name) {
		
		MvoGoalAbstract goal = new MvoGoalComp(name); 
		//System.out.println(goal.getId());
		mvoGoalManager.addMvoGoal(goal);
	}
	
	public void updateMvoGoal(int mvoGoalId) {
		
		MvoGoalAbstract MvoGoalToUpdate = getMvoGoal(mvoGoalId); 
		
		mvoGoalManager.updateMvoGoal(MvoGoalToUpdate);
	}
	
	
	public MvoGoalAbstract getMvoGoal(int mvoGoalId) {
		
		return mvoGoalManager.getMvoGoal(mvoGoalId);
	}
	
	
	public void deleteMvoGoal(MvoGoalAbstract mvoGoal) {
		mvoGoalManager.deleteMvoGoal(mvoGoal); 
	}
	
	public List<MvoGoalAbstract> getAll() {
		return mvoGoalManager.getAll(); 
	}
	
	
	public void addSubMvoGoal(int mvoGoalCompId, int value, Datasource datasource, String icon, String mvoName) {
		
		MvoGoalChild mvoGoalChild = new MvoGoalChild( value,  datasource,  icon,  mvoName);
		
		mvoGoalManager.addSubMvoGoal(mvoGoalChild, mvoGoalCompId);
	}
	

}
