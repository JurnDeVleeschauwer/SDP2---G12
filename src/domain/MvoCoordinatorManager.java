package domain;

import java.util.ArrayList;
import java.util.List;

import persistence.GenericMapperJpa;
import persistence.MvoCoordinatorMapper;

public class MvoCoordinatorManager {
	
	private List<MvoCoordinator> mvoCoordinator; 
	private MvoCoordinatorMapper<MvoCoordinator> mvoCoordinatorMapper = new MvoCoordinatorMapper<MvoCoordinator>(MvoCoordinator.class); 
	
	
	public MvoCoordinatorManager() {
		mvoCoordinator = new ArrayList<>(); 
	}
		
	public void updateMvoCoordinator(MvoCoordinator mvoCoordinatorToUpdate) {
		mvoCoordinatorMapper.update(mvoCoordinatorToUpdate); 

	}

	public void deleteMvoCoordinator(MvoCoordinator mvoCoordinator) {
		mvoCoordinatorMapper.delete(mvoCoordinator);
		
	}

	public MvoCoordinator getMvoCoordinator(int mvoCoordinatorId) {
		return (MvoCoordinator) mvoCoordinatorMapper.get(mvoCoordinatorId);
	}

	public MvoCoordinator getMvoCoordinator(String username, String password) {
		return mvoCoordinatorMapper.get(username, password ); 
	}
	
	public void createMvoCoordinator(MvoCoordinator mvoCoordinator) {
	
		mvoCoordinatorMapper.insert(mvoCoordinator); 

	}
	
	
	public List<MvoCoordinator> getAll() {
		return mvoCoordinator; 
	}
	

}
