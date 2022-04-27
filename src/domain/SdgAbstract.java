package domain;

import exceptions.SdgException;

public abstract class SdgAbstract {
	
	
	
	//GEmeenschappelijke methodes 
	public void get() {
		
	}
	
	public void getChild() {
		
	}
	
	public void add(SdgAbstract sdg) throws SdgException {

		
	}
	public void remove(SdgAbstract sdg) throws SdgException {
		
	}

	
	public SdgChild getChild(SdgAbstract sdg) throws SdgException {
		return null;
	}
	
	public void addSdgJpa(SdgAbstract comp) {
		
	}
	
	public void removeSdgJpa(SdgComp comp) {
		
	}
	
	public SdgComp getSdgJpa(int id) {
		return null;
		
	}

	public void updateSdg(SdgComp sdgCompToUpdate) {
		
	}
	
	

}
