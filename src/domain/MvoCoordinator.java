package domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import persistence.GenericMapperJpa;

@Entity
@Table(name = "MvoAccount")
public class MvoCoordinator implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	private String username;
	private String password;

	@Transient
	private GenericMapperJpa<MvoCoordinator> mvoCoordinatorMapper = new GenericMapperJpa<MvoCoordinator>(MvoCoordinator.class); 
	
	
	public MvoCoordinator(String username, String password) {
		
		setPassword(password);
		setUsername(username);
	}
	
	protected MvoCoordinator() {
		
	}
	
	private void setUsername(String username) {
		this.username = username;
	}
	
	private void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MvoCoordinator other = (MvoCoordinator) obj;
		return Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}
	
	
	
	

	
	
}
