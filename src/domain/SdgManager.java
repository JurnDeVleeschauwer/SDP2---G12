package domain;

import java.util.ArrayList;
import java.util.List;

import persistence.GenericMapperJpa;

public class SdgManager {
	
	private GenericMapperJpa<SdgAbstract> sdgMapper; 
	private List<SdgAbstract> sdgs;
	
	public SdgManager() {
		
		sdgMapper = new GenericMapperJpa<>(SdgAbstract.class);
		sdgs = new ArrayList<SdgAbstract>();
		populateList(); 
	}
	
	
		//Mock Constructor voor testen//
	public SdgManager(GenericMapperJpa<SdgAbstract> sdgMapper) {
		this.sdgMapper = sdgMapper; 
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
	public int getIndexFromId(int sdgId) {
		for(SdgAbstract sdg : sdgs) {
			if(sdg.getId()==sdgId) {
				return sdgs.indexOf(sdg);
			}
		}
		
		return 0;
	}
	public SdgAbstract getSdgById(int id) {
		return sdgs.stream().filter(category->category.getId()==id).findAny().get();

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
