package domain;

import java.util.List;

public class SdgController {
	
	
	private SdgAbstract sdg;
	
	public SdgController() {
		sdg = new SdgComp(); 
	}
	
	public void addSdg(int id, String name, List<SdgChild> sdgs, Category category) {
		
		SdgComp sdgComp = new SdgComp(id, name, sdgs, category); 
		sdg.addSdgJpa(sdgComp); 
	}
	
	public void updateSdg(int sdgId) {
		
		SdgComp sdgCompToUpdate = getSdg(sdgId); 
		
		sdg.updateSdg(sdgCompToUpdate);
	}
	
	
	public SdgComp getSdg(int categoryId) {
		
		return sdg.getSdgJpa(categoryId);
	}
	
	
	public void deleteSdg(SdgComp sdgComp) {
		((SdgComp) sdg).deleteSdg(sdgComp); 
	}
	
	

}
