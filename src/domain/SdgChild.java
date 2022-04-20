package domain;

import java.io.Serializable;

import javax.persistence.Entity;


@Entity
public class SdgChild extends SdgAbstract implements Serializable{

	
	
	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;
	private String name;
	
	private String id;
	private String description;
	private String icon;
	private int categoryID;
	private int target;
	
	
	
	public SdgChild(String name, String id, String description, String icon, int categoryID, int target) {

		
		
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



	public int getCategoryID() {
		return categoryID;
	}



	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}



	public int getTarget() {
		return target;
	}



	public void setTarget(int target) {
		this.target = target;
	}
	
	

}
