package domain;

import java.util.List;

import persistence.GenericMapperJpa;

public class SdgController {
	
	SdgManager sdgManager = new SdgManager(); 
	
	public SdgController() {
		sdgManager = new SdgManager(); 
	}
	
	public void addSdg(int id, String name, List<SdgChild> sdgs, Category category) {
		
		SdgComp sdgComp = new SdgComp(id, name, sdgs, category); 
		sdgManager.addSdgJpa(sdgComp); 
	}
	
	public void updateSdg(int sdgId) {
		
		SdgComp sdgCompToUpdate = getSdg(sdgId); 
		
		sdgManager.updateSdg(sdgCompToUpdate);
	}
	
	
	public SdgComp getSdg(int categoryId) {
		
		return sdgManager.getSdgJpa(categoryId);
	}
	
	
	public void deleteSdg(SdgComp sdgComp) {
		 sdgManager.deleteSdg(sdgComp); 
	}
	
	

}
