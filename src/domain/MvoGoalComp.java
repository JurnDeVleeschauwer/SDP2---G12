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
	
	@ManyToOne(targetEntity = Category.class, cascade = CascadeType.PERSIST)
	private int categoryID;  

	
	public MvoGoalComp(int id, String name, List<MvoGoalChild> sdgs, Category cat) {
		setId(id);
		setName(name);
		setSdgs(sdgs);
		setCategoryID(cat);
		
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

	
	public void addSdgJpa(MvoGoalComp comp) {
		mvoGoalMapper.insert(comp);
		
	}
	
	public void deleteSdg(MvoGoalComp sdgComp) {
		mvoGoalMapper.delete(sdgComp);
		
	}
	
	public MvoGoalComp getSdgJpa(int sdgId) {
		return (MvoGoalComp) mvoGoalMapper.get(sdgId); 
		
	}
	
	public int getCategoryID() {
		return categoryID;
	}
	
	public void setCategoryID(Category cat) {
		this.categoryID = cat.getId(); 
	}
	
}
