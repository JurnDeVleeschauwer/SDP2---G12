package domain;

import java.util.ArrayList;
import java.util.List;

import persistence.GenericMapperJpa;

public class MvoGoalManager {
	
	private List<MvoGoalAbstract> mvoGoals;
	private GenericMapperJpa<MvoGoalAbstract> mvoGoalMapper = new GenericMapperJpa<MvoGoalAbstract>(MvoGoalAbstract.class); 

	
	public MvoGoalManager() {
		mvoGoals = new ArrayList<MvoGoalAbstract>(); 
	}
	
	
	public void populateList() {
		mvoGoals.addAll(mvoGoalMapper.findAll()); 
	}
	
	
	public void updateMvoGoal(MvoGoalAbstract comp) {
		mvoGoalMapper.update(comp); 
	}
	
	public void addMvoGoal(MvoGoalAbstract comp) {
		mvoGoalMapper.insert(comp);
		
	}
	
	public void deleteMvoGoal(MvoGoalAbstract comp) {
		mvoGoalMapper.delete(comp);
		
	}
	
	public MvoGoalComp getMvoGoal(int mvoGoalId) {
		return (MvoGoalComp) mvoGoalMapper.get(mvoGoalId); 
		
	}


	public List<MvoGoalAbstract> getAll() {
		
		return mvoGoals;
	}
	

}
