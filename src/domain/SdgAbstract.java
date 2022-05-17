package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;


import exceptions.SdgException;

@Entity
@Inheritance(strategy =InheritanceType.SINGLE_TABLE)
@MappedSuperclass
public abstract class SdgAbstract implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id; 
	protected String name;

	
	protected SdgAbstract() {
		
	}
	
	
	//GEmeenschappelijke methodes 
	public void get() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	public void add(SdgAbstract sdg) throws SdgException {

		
	}
	public void remove(SdgAbstract sdg) throws SdgException {
		
	}
	
	
	public SdgChild getChild(int id) throws SdgException {
		return null;
	}
	
	public void addSdgJpa(SdgAbstract comp) {
		
	}
	
	public void removeSdgJpa(SdgComp comp) {
		
	}
	
	public SdgComp getSdgJpa(int id) {
		return null;
		
	}

	public void updateSdg(SdgComp sdgCompToUpdate) {
		
	}


	public void setId(int id) {
		this.id = id; 
		
	}
	
	public int getId() {
		return id; 
	}
	
	

}
