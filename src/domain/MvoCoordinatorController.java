package domain;

public class MvoCoordinatorController {
	
	
	private MvoCoordinatorManager mvoCoord;
	
	public MvoCoordinatorController() {
		mvoCoord = new MvoCoordinatorManager(); 
	}
	
	
	public void updateMvoCoordinator(int mvoCoordinatorId) {
		
		MvoCoordinator MvoCoordinatorToUpdate = getMvoCoordinator(mvoCoordinatorId); 
		
		mvoCoord.updateMvoCoordinator(MvoCoordinatorToUpdate);
	}
	
	
	public MvoCoordinator getMvoCoordinator(int mvoCoordinatorId) {
		
		return mvoCoord.getMvoCoordinator(mvoCoordinatorId);
	}
	

	public void deleteMvoCoordinator(MvoCoordinator mvoCoordinator) {
		mvoCoord.deleteMvoCoordinator(mvoCoordinator); 
	}
	
	
	private MvoCoordinator getMvoCoordinator(String username, String password) {
		
		return mvoCoord.getMvoCoordinator(username, password);
		
	}
	
	public MvoCoordinator login(String username, String password) /*throws exception*/ { 
	
			MvoCoordinator mvoCoordDB = mvoCoord.getMvoCoordinator(username, password);
			
		
			
			return mvoCoordDB; 
			
			
	
	
	}
		
	public void insertMvoCoordinator(String username, String password) {
		
		MvoCoordinator mvoCoordinator = new MvoCoordinator(username, password);
		
		System.out.println(mvoCoordinator.getUsername() + " " + mvoCoordinator.getPassword()); 
		
		mvoCoord.createMvoCoordinator(mvoCoordinator);
		
	}
	

}
