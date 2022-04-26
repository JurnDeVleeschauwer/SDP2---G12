package domain;

import java.io.Serializable;


import javax.annotation.processing.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class SdgChild extends SdgAbstract implements Serializable{

	
	
	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;
	private String name;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String description;
	private String icon;
	
	@ManyToOne(cascade = CascadeType.PERSIST )
	private Category category;
	private int target;
	@ManyToOne(targetEntity = SdgComp.class, cascade = CascadeType.MERGE)
	private SdgComp sdgComp; 
	
	
	public SdgChild(String name, String id, String description, String icon, Category category, SdgComp sdgComp, int target) {
		setName(name);
		setId(id);
		setDescription(description);
		setCategoryID(category);
		setTarget(target); 
		setSdgComp(sdgComp); 
		
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
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



	public Category getCategoryID() {
		return category;
	}



	public void setCategoryID(Category cat) {
		this.category = cat; 
	
	}



	public int getTarget() {
		return target;
	}



	public void setTarget(int target) {
		this.target = target;
	}
	
	public SdgComp getSdgComp() {
		return sdgComp;
	}
	
	public void setSdgComp(SdgComp sdgComp) {
		this.sdgComp = sdgComp;
	}

}
