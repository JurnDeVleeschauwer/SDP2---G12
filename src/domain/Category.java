package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class Category implements Serializable{
	
	/**
	 * interfaces Boven klassen 
	 * Code omdraaien -> Hoog -> laag 
	 * Unsupported operation exception toevoegen 
	 * Kijken voor Liberaries 
	 * SDGComp aanpassen 
	 * tableview implenteren 
	 */
	private static final long serialVersionUID = 1L;

	
	//Attributes
	private String name;
	private String icon; 
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<SdgAbstract> sdgAbstract; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//Constructors
	

	public Category(String name, String icon)  {
		setName(name);
		setIcon(icon);

	}
	
	protected Category() {
		
	}
	
	//Getters, Setters, HashEqual
	public String getName() {
		return name;
	}
	
	public List<String> getAsStringList(){
		List<String> lijst = new ArrayList<>();
		lijst.add(name);
		lijst.add(icon);
		lijst.add(Integer.toString(id));
		
		return lijst;
		
	}


	public void setMvoGoalAbstract(List<SdgAbstract> sdgAbstract) {
		this.sdgAbstract = sdgAbstract; 
		
	}
	
	
	public void setName(String name) /* throws CategoryException */ {
		this.name = name;
	}


	public String getIcon() {
		return icon;
	}


	public void setIcon(String icon) {
		this.icon = icon;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	
	public void addSdg(SdgAbstract sdg) {
		
		sdgAbstract.add(sdg); 
		
	}


	

	public List<SdgAbstract> getSdgAbstract() {
		return sdgAbstract;
	}

	public void setSdgAbstract(List<SdgAbstract> sdgAbstract) {
		this.sdgAbstract = sdgAbstract;
	}

	@Override
	public int hashCode() {
		return Objects.hash(icon, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(icon, other.icon) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return String.format("Name: %s, id: %d", getName(), getId());
	}


	

}
