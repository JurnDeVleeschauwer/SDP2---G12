package domain;

public class MvoCoordinator {
	
	private String username;
	private String password;
	private MvoCoordinator co = null; 

	private MvoCoordinator(String username, String password) {
		
		setPassword(password);
		setUsername(username);
	}
	
	
	//Vraag lector: Is er mvo coordinator of zijn er meerdere? (Singleton) 
	
	public MvoCoordinator getMvoInstance(String username, String password) {
		if(co == null) {
			co = new MvoCoordinator(username, password); 
		} 
		
		return co; 
		
	}
	
	private void setUsername(String username) {
		this.username = username;
	}
	
	private void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
