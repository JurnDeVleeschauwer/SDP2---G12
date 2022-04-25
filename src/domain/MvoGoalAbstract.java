package domain;

import exceptions.MvoGoalException;
import exceptions.SdgException;

public abstract class MvoGoalAbstract {
	
	
	
	public void get() {
		
	}
	
	public void getChild() {
		
	}
	
	public void add(MvoGoalAbstract sdg) throws MvoGoalException {

		
	}
	public void remove(MvoGoalAbstract sdg) throws MvoGoalException {
		
	}

	
	public MvoGoalChild getChild(MvoGoalAbstract sdg) throws MvoGoalException {
		return null;
	}
	
	public void addMvoGoalJpa(MvoGoalAbstract comp) {
		
	}
	
	public void removeMvoGoalJpa(MvoGoalComp comp) {
		
	}
	
	public MvoGoalAbstract getMvoGoalJpa(int id) {
		return null;
		
	}

	public void updateMvoGoal(MvoGoalComp mvoGoalToUpdate) {
		
	}
	
	

}
