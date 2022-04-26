package domain;

import java.util.ArrayList;
import java.util.List;

import persistence.GenericMapperJpa;

public class SdgManager {
	
	private GenericMapperJpa<SdgAbstract> sdgMapper = new GenericMapperJpa<>(SdgAbstract.class); 
	private List<SdgAbstract> sdgs;
	
	public SdgManager() {
		sdgs = new ArrayList<SdgAbstract>();
	}
	
	
	
	public void addSdgJpa(SdgAbstract sdg) {
		sdgMapper.insert(sdg);
		
	}
	
	public void deleteSdg(SdgAbstract sdg) {
		sdgMapper.delete(sdg);
		
	}
	
	public SdgComp getSdgJpa(int sdgId) {
		return (SdgComp) sdgMapper.get(sdgId); 
		
	}
	
	public void updateSdg(SdgAbstract sdg) {
		sdgMapper.update(sdg); 
		
	}

}
