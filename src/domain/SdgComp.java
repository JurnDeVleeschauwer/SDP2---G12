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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import exceptions.SdgException;
import persistence.GenericMapperJpa;


@Entity
@Table(name = "sdg")
public class SdgComp extends SdgAbstract implements Serializable  {
	@Transient
	private GenericMapperJpa<SdgAbstract> sdgMapper = new GenericMapperJpa<>(SdgAbstract.class); 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
	@OneToMany(mappedBy = "sdgchild")
	private List<SdgChild> sdgs = new ArrayList<>(); 
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Category category;  

	
	public SdgComp(int id, String name, List<SdgChild> sdgs, Category cat) {
		setId(id);
		setName(name);
		setSdgs(sdgs);
		setCategory(cat);
		
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

	
	public void addSdgJpa(SdgComp comp) {
		sdgMapper.insert(comp);
		
	}
	
	public void deleteSdg(SdgComp sdgComp) {
		sdgMapper.delete(sdgComp);
		
	}
	
	public SdgComp getSdgJpa(int sdgId) {
		return (SdgComp) sdgMapper.get(sdgId); 
		
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category cat) {
		this.category = cat;
	}
	
}
