package domain;

import java.util.List;

import persistence.GenericMapperJpa;

public class SdgController {
	
	SdgManager sdgManager; 
	
	public SdgController() {
		sdgManager = new SdgManager(); 
		
	}
	
	public void addSdg(String name, String description) {
		
		SdgComp sdgComp = new SdgComp.Builder().name(name).description(description).build();

		sdgManager.addSdgJpa(sdgComp); 
	}
	
	public void addSdg(String name, String icon, MvoGoalAbstract mvoGoal, SdgComp sdgComp, int target) {
		SdgChild sdgChild = new SdgChild.Builder().name(name).icon(icon).mvoGoal(mvoGoal).sdgComp(sdgComp).target(target).build();

		sdgManager.addSdgJpa(sdgChild);
	}
	
	
	public void updateSdg(SdgAbstract sdgCompToUpdate) {
		
			
		sdgManager.updateSdg(sdgCompToUpdate);
	}
	
	
	public SdgAbstract getSdg(int sdgCompId) {
		
		return sdgManager.getSdg(sdgCompId);
	}
	public int getIndexFromId(int sdgId) {
		return sdgManager.getIndexFromId(sdgId);
	}
	
	
	public SdgManager getSdgManager() {
		return sdgManager;
	}

	public void addSubSdg(String name, String icon, MvoGoalAbstract mvoGoal, SdgComp sdgComp, int target) {
		SdgChild sdgChild = new SdgChild.Builder().name(name).icon(icon).mvoGoal(mvoGoal).sdgComp(sdgComp).target(target).build();

		sdgManager.addSubSdg(sdgChild, sdgComp.getId());
		sdgManager.updateSdg(sdgComp);
	}
	
	public void deleteSdg(SdgAbstract sdgAbstract) {
		 sdgManager.deleteSdg(sdgAbstract); 
	}
	
	public List<SdgAbstract> getAll() {
		return sdgManager.getAll();
	}
	

}
