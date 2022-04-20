package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import exceptions.SdgException;


@Entity
@Table(name = "sdg")
public class SdgComp extends SdgAbstract implements Serializable  {
	
	
	public SdgComp(int id, String name, List<SdgChild> sdgs) {
		setId(id);
		setName(name);
		setSdgs(sdgs);
		
	}
	
	public SdgComp() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private List<SdgChild> sdgs = new ArrayList<>(); 
	
	
	@Override
	public void add(SdgAbstract sdg) throws SdgException {
		sdgs.add((SdgChild) sdg);

		
	}
	
	@Override
	public void remove(SdgAbstract sdg) throws SdgException {
		
		sdgs.remove(sdg);

		
	}
	
	
	@Override
	public SdgChild getChild(SdgAbstract sdg) throws SdgException {
		
	 return sdgs.stream().filter((currentSdg) -> currentSdg == sdg).collect(Collectors.toList()).get(0);
		
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

	public List<SdgChild> getSdgs() {
		return sdgs;
	}

	public void setSdgs(List<SdgChild> sdgs) {
		this.sdgs = sdgs;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public void addSdgJpa() {
		
	}
	
	public void deleteSdg(SdgComp sdgComp) {
		
	}
	
	public void getSdgJpa() {
		
	}
	
}
