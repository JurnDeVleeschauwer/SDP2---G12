package domain;

import java.util.ArrayList;
import java.util.List;

import persistence.GenericMapperJpa;

public class MvoGoalManager {
	
	private List<MvoGoalComp> mvoGoals;
	private GenericMapperJpa<MvoGoalComp> mvoGoalMapper = new GenericMapperJpa<MvoGoalComp>(MvoGoalComp.class); 

	
	public MvoGoalManager() {
		mvoGoals = new ArrayList<MvoGoalComp>(); 
	}
	
	
	public void populateList() {
		mvoGoals.addAll(mvoGoalMapper.findAll()); 
	}
	
	
	public void addSdgJpa(MvoGoalComp comp) {
		mvoGoalMapper.insert(comp);
		
	}
	
	public void deleteSdg(MvoGoalComp sdgComp) {
		mvoGoalMapper.delete(sdgComp);
		
	}
	
	public MvoGoalComp getSdgJpa(int sdgId) {
		return (MvoGoalComp) mvoGoalMapper.get(sdgId); 
		
	}
	
	public void closePersistency() {
		GenericMapperJpa.closePersistency();
	}
}
