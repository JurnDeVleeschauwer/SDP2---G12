package domain;

import java.util.ArrayList;
import java.util.List;

import persistence.GenericMapperJpa;

public class MvoCoordinatorManager {
	
	private List<MvoCoordinator> mvoCoordinator; 
	private GenericMapperJpa<MvoCoordinator> mvoCoordinatorMapper = new GenericMapperJpa<>(MvoCoordinator.class); 
	
	
	public MvoCoordinatorManager() {
		mvoCoordinator = new ArrayList<>(); 
	}
		
	public void updateMvoCoordinator(MvoCoordinator mvoCoordinatorToUpdate) {
		mvoCoordinatorMapper.update(mvoCoordinatorToUpdate); 

	}

	public void deleteMvoCoordinator(MvoCoordinator mvoCoordinator) {
		mvoCoordinatorMapper.insert(mvoCoordinator);
		
	}

	public MvoCoordinator getMvoCoordinator(int mvoCoordinatorId) {
		return mvoCoordinatorMapper.get(mvoCoordinatorMapper);
	}

}
