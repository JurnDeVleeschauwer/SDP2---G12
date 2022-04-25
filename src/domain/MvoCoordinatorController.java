package domain;

public class MvoCoordinatorController {
	
	
	private MvoCoordinatorManager mvoCoord;
	
	public MvoCoordinatorController() {
		mvoCoord = new MvoCoordinatorManager(); 
	}
	
	
	public void updateMvoCoordinator(int MvoCoordinatorId) {
		
		MvoCoordinator MvoCoordinatorToUpdate = getMvoCoordinator(MvoCoordinatorId); 
		
		mvoCoord.updateMvoCoordinator(MvoCoordinatorToUpdate);
	}
	
	
	public MvoCoordinator getMvoCoordinator(int MvoCoordinatorId) {
		
		return mvoCoord.getMvoCoordinator(MvoCoordinatorId);
	}
	
	
	public void deleteMvoCoordinator(MvoCoordinator MvoCoordinator) {
		mvoCoord.deleteMvoCoordinator(MvoCoordinator); 
	}
	
	

}
