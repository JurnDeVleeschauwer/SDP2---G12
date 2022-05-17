package domain;

import java.util.ArrayList;
import java.util.List;

import persistence.GenericMapperJpa;

public class SdgManager {
	
	private GenericMapperJpa<SdgAbstract> sdgMapper = new GenericMapperJpa<>(SdgAbstract.class); 
	private List<SdgAbstract> sdgs;
	
	public SdgManager() {
		sdgs = new ArrayList<SdgAbstract>();
		populateList(); 
	}
	
	public void populateList() {
		
		sdgs.addAll(sdgMapper.findAll()); 
	}
	
	
	
	public void addSdgJpa(SdgAbstract sdg) {
		sdgMapper.insert(sdg);
		populateList();
		
	}
	
	public void deleteSdg(SdgAbstract sdg) {
		sdgMapper.delete(sdg);
		
	}
	
	public SdgComp getSdg(int sdgId) {
	
		return (SdgComp) sdgs.stream().filter(category->category.getId()==sdgId).findAny().get(); 
		
	}
	
	public void updateSdg(SdgAbstract sdg) {
		sdgMapper.update(sdg); 
		
	}
	
	public List<SdgAbstract> getAll() {
		return sdgs; 
	}

	public void addSubSdg(SdgChild sdgChild, int sdgCompId) {

		SdgComp comp = (SdgComp)  sdgs.stream().filter(category->category.getId()==sdgCompId).findAny().get();

		comp.add(sdgChild);
	}
	
}
