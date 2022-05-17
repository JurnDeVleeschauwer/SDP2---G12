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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import exceptions.SdgException;
import persistence.GenericMapperJpa;


@Entity
@Table(name = "sdg")
public class SdgComp extends SdgAbstract implements Serializable  {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String description; 
	
	@OneToMany(cascade = CascadeType.PERSIST,orphanRemoval = true)
	private List<SdgChild> sdgs = new ArrayList<>(); 
	

	
	public SdgComp(String name, String description) {
		setName(name); 
		setDescription(description); 
	}
	
	public SdgComp(String name, String description, List<SdgChild> sdgs) {
		setName(name);
		setSdgs(sdgs);
		setDescription(description); 
		
	}
	
	public void setDescription(String description) {
		this.description = description; 
		
	}
	
	public String getDescription() {
		return description; 
	}

	public SdgComp() {
		
	}


	
	@Override
	public void add(SdgAbstract sdg) throws SdgException {
		sdgs.add((SdgChild) sdg);

		
	}
	
	@Override
	public void remove(SdgAbstract sdg) throws SdgException {
		
		sdgs.remove(sdg);

		
	}
	
	
	@Override
	public SdgChild getChild(int sdgId) throws SdgException {
		
	 return sdgs.stream().filter((currentSdg) -> currentSdg.getId() == sdgId).collect(Collectors.toList()).get(0);
		
	}
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SdgChild> getSdgs() {
		return sdgs;
	}

	public void setSdgs(List<SdgChild> sdgs) {
		this.sdgs = sdgs;
	}
	

	@Override
	public String toString() {
		String res = "";
		
		res += String.format("Id: %d, name: %s%n, Category: %s%n", getName());
		
		
		for(SdgChild child : sdgs) {
			res += child.toString(); 
			res += System.lineSeparator(); 
			
		}
		return res; 
	}
	
}
