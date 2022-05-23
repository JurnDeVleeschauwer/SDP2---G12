package domain;

import java.util.ArrayList;
import java.util.List;

import persistence.GenericMapperJpa;

public class MvoGoalManager {
	
	
	private GenericMapperJpa<MvoGoalAbstract> mvoGoalMapper = new GenericMapperJpa<MvoGoalAbstract>(MvoGoalAbstract.class); 
	private List<MvoGoalAbstract> mvoGoals;
	
	public MvoGoalManager() {
		mvoGoals = new ArrayList<MvoGoalAbstract>(); 
		populateList();
	}
	
	
	public void populateList() {
		mvoGoals.clear();
		mvoGoals.addAll(mvoGoalMapper.findAll()); 
	}
	
	
	public void updateMvoGoal(MvoGoalAbstract comp) {
		System.out.println( comp); 
		System.out.println( mvoGoalMapper.update(comp)); 
		populateList();
	}
	
	public void addMvoGoal(MvoGoalAbstract comp) {

		mvoGoalMapper.insert(comp);
		populateList();

	}
	
	public void deleteMvoGoal(MvoGoalAbstract comp) {
		mvoGoalMapper.delete(comp);
		populateList();
		
	}
	
	public MvoGoalAbstract getMvoGoal(int mvoGoalId) {
		return mvoGoals.get(mvoGoalId); 
		
	}


	public List<MvoGoalAbstract> getAll() {
		
		return mvoGoals;
	}
	
	
	public void addSubMvoGoal(MvoGoalChild mvoGoalChild, int mvoCompIndex) {
	
		MvoGoalComp comp = (MvoGoalComp) mvoGoals.get(mvoCompIndex);

		comp.add(mvoGoalChild);
		//populateList();
		
	}

}
