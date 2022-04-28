package domain;

import java.util.List;

import javax.security.sasl.AuthenticationException;

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
	
	
	private MvoCoordinator getMvoCoordinator(String username, String password) throws AuthenticationException {
		
		return mvoCoord.getMvoCoordinator(username, password);
		
	}
	
	public MvoCoordinator login(String username, String password) throws AuthenticationException /*throws exception*/ { 
	
			MvoCoordinator mvoCoordDB = mvoCoord.getMvoCoordinator(username, password);
			
		
			
			return mvoCoordDB; 
			
			
	
	
	}
		
	public void insertMvoCoordinator(String username, String password) {
		
		MvoCoordinator mvoCoordinator = new MvoCoordinator(username, password);
		
		System.out.println(mvoCoordinator.getUsername() + " " + mvoCoordinator.getPassword()); 
		
		mvoCoord.createMvoCoordinator(mvoCoordinator);
		
	}
	
	public List<MvoCoordinator> getAll() {
		return mvoCoord.getAll(); 
	}

}
