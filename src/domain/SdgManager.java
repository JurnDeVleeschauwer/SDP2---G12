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
		sdgs.clear();
		sdgs.addAll(sdgMapper.findAll()); 
	}
	
	
	
	public void addSdgJpa(SdgAbstract sdg) {
		sdgMapper.insert(sdg);
		populateList();
		
	}
	
	public void deleteSdg(SdgAbstract sdg) {
		sdgMapper.delete(sdg);
		populateList();
	}
	
	public SdgAbstract getSdg(int sdgId) {
	
		return sdgs.get(sdgId); 
		
	}
	
	public void updateSdg(SdgAbstract sdg) {
		sdgMapper.update(sdg); 
		populateList();
	}
	
	public List<SdgAbstract> getAll() {
		return sdgs; 
	}

	public void addSubSdg(SdgChild sdgChild, int sdgCompId) {

		SdgComp comp = (SdgComp)  sdgs.stream().filter(category->category.getId()==sdgCompId).findAny().get();

		comp.add(sdgChild);
		//populateList();
	}
	
}
