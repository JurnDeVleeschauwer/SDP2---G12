package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import exceptions.MvoGoalException;
import exceptions.SdgException;
import persistence.GenericMapperJpa;


@Entity
@Table(name = "sdg")
public class MvoGoalComp extends MvoGoalAbstract implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Transient
	private GenericMapperJpa<MvoGoalAbstract> mvoGoalMapper = new GenericMapperJpa<>(MvoGoalAbstract.class); 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private List<MvoGoalChild> mvoGoals = new ArrayList<>(); 
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Category category;   

	
	public MvoGoalComp(int id, String name, List<MvoGoalChild> sdgs, Category cat) {
		setId(id);
		setName(name);
		setSdgs(sdgs);
		setCategory(cat);
		
	}
	
	public MvoGoalComp() {
		
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
	public MvoGoalChild getChild(MvoGoalAbstract sdg) throws MvoGoalException {
		
	 return mvoGoals.stream().filter((currentSdg) -> currentSdg == sdg).collect(Collectors.toList()).get(0);
		
	}
	
	
	public int getId() {
		return id; 
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

	public void setSdgs(List<MvoGoalChild> mvoGoals) {
		this.mvoGoals = mvoGoals;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Category getCategory() {
		return category;
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

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		 String res = ""; 
	
		 
		 res += String.format("Id: %d%n, Category: %s%n, Name: %s%n", getId(), getName(), getCategory().toString());
		 
		 for(MvoGoalChild child :mvoGoals) {
			 res += mvoGoals.toString(); 
			 
		 }
		 
		 return res; 
		 
	}
	
}
