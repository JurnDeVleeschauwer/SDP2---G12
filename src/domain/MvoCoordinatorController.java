package domain;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.InvalidRoleValueException;
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
	
	
	private MvoCoordinator getMvoCoordinator(String username, String password) throws AuthenticationException, InvalidRoleValueException {
		
		return mvoCoord.getMvoCoordinator(username, password);
		
	}
	
	public MvoCoordinator login(String username, String password) throws AuthenticationException /*throws exception*/, InvalidRoleValueException { 
	
			MvoCoordinator mvoCoordDB = mvoCoord.getMvoCoordinator(username, password);
			
		
			
			return mvoCoordDB; 
			
			
	
	
	}
	
		
	public void insertMvoCoordinator(String username, String password,ArrayList<String> roles) {
		
		MvoCoordinator mvoCoordinator = new MvoCoordinator(username, password,roles);
		
		System.out.println(mvoCoordinator.getUsername() + " " + mvoCoordinator.getPassword()); 
		
		mvoCoord.createMvoCoordinator(mvoCoordinator);
		
	}
	
	public MvoCoordinator getMvoCoordinator() {
		return mvoCoord.getMvoCoordinator(); 
	}


	public void afmelden() {

		mvoCoord.afmelden();
	}

}
