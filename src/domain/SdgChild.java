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
	
	

}
