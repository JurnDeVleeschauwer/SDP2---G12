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
public class SdgComp implements Serializable, SdgInterface {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int name;
	private List<SdgChild> sdgs = new ArrayList<>(); 
	
	
	public void add(SdgChild sdg) throws SdgException {
		sdgs.add(sdg);

		
	}
	
	public void remove(SdgChild sdg) throws SdgException {

		sdgs.remove(sdg);

		
	}
	
	public SdgChild getChild(SdgChild sdg) throws SdgException {
		
	 return sdgs.stream().filter((currentSdg) -> currentSdg == sdg).collect(Collectors.toList()).get(0);
		
	}
	
	
	
	
}
