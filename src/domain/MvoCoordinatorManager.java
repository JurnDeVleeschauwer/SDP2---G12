package domain;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.InvalidRoleValueException;
import javax.security.sasl.AuthenticationException;

import persistence.GenericMapperJpa;
import persistence.MvoCoordinatorMapper;

public class MvoCoordinatorManager {
	
	private MvoCoordinator mvoCoordinator; 
	private MvoCoordinatorMapper<MvoCoordinator> mvoCoordinatorMapper = new MvoCoordinatorMapper<MvoCoordinator>(MvoCoordinator.class); 
	
	
	public MvoCoordinatorManager() {

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

	public MvoCoordinator getMvoCoordinator(String username, String password) throws AuthenticationException, InvalidRoleValueException {
		mvoCoordinator=mvoCoordinatorMapper.get(username, password);
		if(!mvoCoordinator.getRoles().contains("MVO co�rdinator")) {
			mvoCoordinator=null;
			throw new InvalidRoleValueException();
		}
		return mvoCoordinator; 
	}
	
	public void createMvoCoordinator(MvoCoordinator mvoCoordinator) {
	
		mvoCoordinatorMapper.insert(mvoCoordinator); 
		
	}
	
	public void afmelden() {
		mvoCoordinator=null;
	}
	public MvoCoordinator getMvoCoordinator() {
		return mvoCoordinator; 
	}
	

}
