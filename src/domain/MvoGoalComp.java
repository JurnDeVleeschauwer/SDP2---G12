package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import exceptions.MvoGoalException;
import exceptions.SdgException;
import persistence.GenericMapperJpa;

@Entity
public class MvoGoalComp extends MvoGoalAbstract implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Transient
	private GenericMapperJpa<MvoGoalAbstract> mvoGoalMapper = new GenericMapperJpa<>(MvoGoalAbstract.class); 
	
	
	private String name;
	
	@OneToMany(cascade = CascadeType.PERSIST,orphanRemoval = true)
	private List<MvoGoalChild> mvoGoals = new ArrayList<>(); 
	
	

	
	public MvoGoalComp(String name) {
		setName(name);		
	}
	
	protected MvoGoalComp() {
		
	}


	
	public MvoGoalComp(String string, ArrayList<MvoGoalChild> listMVoChild) {
		setName(name);		
		setMvoGoals(listMVoChild);
	}

	@Override
	public void add(MvoGoalAbstract mvoGoal) throws MvoGoalException {
		mvoGoals.add((MvoGoalChild) mvoGoal);

		
	}
	
	@Override
	public void remove(MvoGoalAbstract mvoGoal) throws MvoGoalException {
		
		mvoGoals.remove(mvoGoal);

		
	}
	
	
	@Override
	public MvoGoalChild getChild(int mvoGoalChildId) throws MvoGoalException {
		
	 return mvoGoals.stream().filter((currentMvo) -> currentMvo.getCounter() == mvoGoalChildId).collect(Collectors.toList()).get(0);
		
	}	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MvoGoalChild> getSdgs() {
		return mvoGoals;
	}





	
	public GenericMapperJpa<MvoGoalAbstract> getMvoGoalMapper() {
		return mvoGoalMapper;
	}

	public void setMvoGoalMapper(GenericMapperJpa<MvoGoalAbstract> mvoGoalMapper) {
		this.mvoGoalMapper = mvoGoalMapper;
	}

	public List<MvoGoalChild> getMvoGoals() {
		return mvoGoals;
	}





	public void setMvoGoals(List<MvoGoalChild> mvoGoals) {
		this.mvoGoals = mvoGoals;
	}

	@Override
	public int hashCode() {
		return Objects.hash(mvoGoals, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MvoGoalComp other = (MvoGoalComp) obj;
		return Objects.equals(mvoGoals, other.mvoGoals) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		 String res = ""; 
	
		 
		 res += String.format(" Name: %s%n", getName());
		 
		 for(MvoGoalChild child : mvoGoals) {
			 res += child.toString(); 
			 
		 }
		 
		 return res; 
		 
	}
	
}
