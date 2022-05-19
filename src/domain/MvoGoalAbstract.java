package domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import exceptions.MvoGoalException;
import exceptions.SdgException;

@Entity
@Inheritance(strategy =InheritanceType.SINGLE_TABLE)
@Table(name= "MvoGoal")
public abstract class MvoGoalAbstract implements Serializable {
	
	protected MvoGoalAbstract() {
		
	}
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MvoGoalAbstract other = (MvoGoalAbstract) obj;
		return id == other.id;
	}

	public void get() {
		
	}
	
	public void getChild() {
		
	}
	
	public void add(MvoGoalAbstract sdg) throws MvoGoalException {

		
	}
	public void remove(MvoGoalAbstract sdg) throws MvoGoalException {
		
	}

	
	public MvoGoalChild getChild(int mvoGoalId) throws MvoGoalException {
		return null;
	}
	
	public void addMvoGoalJpa(MvoGoalAbstract comp) {
		
	}
	
	public void removeMvoGoalJpa(MvoGoalComp comp) {
		
	}
	
	public MvoGoalAbstract getMvoGoalJpa(int id) {
		return null;
		
	}

	public void updateMvoGoal(MvoGoalComp mvoGoalToUpdate) {
		
	}
	
	public boolean isBlad() {
		return false;
	}
	
	

}
