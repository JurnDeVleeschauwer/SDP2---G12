package domain;

import java.io.Serializable;


import javax.annotation.processing.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import exceptions.SdgException;


@Entity
public class SdgChild extends SdgAbstract implements Serializable{

	
	
	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;
	private String description;
	private String icon;
	

	

	private int target;

	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private MvoGoalAbstract mvoGoal; 

	
	public SdgChild(String name, String icon, MvoGoalAbstract mvoGoal, SdgComp sdgComp, int target) {
		setName(name);
		setDescription(description);
		setTarget(target); 
		setMvoGoalAbstract(mvoGoal);
	}


	protected SdgChild() {
		
	}

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}








	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getIcon() {
		return icon;
	}



	public void setIcon(String icon) {
		this.icon = icon;
	}




	public int getTarget() {
		return target;
	}



	public void setTarget(int target) {
		this.target = target;
	}
	
	
	public void setMvoGoalAbstract(MvoGoalAbstract mvoGoal) {
		
		
		if(mvoGoal instanceof MvoGoalChild) {
			this.mvoGoal = (MvoGoalChild) mvoGoal;

		} else {
			this.mvoGoal = (MvoGoalComp) mvoGoal; 
		}
		
	}
	
	
	@Override
	public String toString() {
		return String.format("id: %s, icon: %s, description: %s", getId(), getIcon(), getDescription());
	}
	
	public void get() {
		throw new UnsupportedOperationException(); 

		
	}
	
	public void getChild() {
		throw new UnsupportedOperationException(); 

	}
	
	public void add(SdgAbstract sdg) throws SdgException {
		throw new UnsupportedOperationException(); 

		
	}
	public void remove(SdgAbstract sdg) throws SdgException {
		throw new UnsupportedOperationException(); 

	}

	
	public SdgChild getChild(SdgAbstract sdg) throws SdgException {
		throw new UnsupportedOperationException(); 
	}
	
	public void addSdgJpa(SdgAbstract comp) {
		throw new UnsupportedOperationException(); 

	}
	
	public void removeSdgJpa(SdgComp comp) {
		throw new UnsupportedOperationException(); 

	}
	
	public SdgComp getSdgJpa(int id) {
		throw new UnsupportedOperationException(); 
		
	}

	public void updateSdg(SdgComp sdgCompToUpdate) {
		throw new UnsupportedOperationException(); 
	}

}
