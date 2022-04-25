package domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import exceptions.CategoryException;
import persistence.GenericMapper;
import persistence.GenericMapperJpa;

@Entity
@Table
public class Category implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	//Attributes
	private String name;
	private String icon; 
	private boolean showCategory;
	
	@Transient
	private GenericMapperJpa<Category> categoryMapper = new GenericMapperJpa<Category>(Category.class); 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//Constructors
	
	public Category(String name, String icon, int id,  boolean showCategory)  {
		setName(name);
		setIcon(icon);
		setId(id);
		setShowCategory(showCategory);
	}
	
	protected Category() {
		
	}
	
	//Getters, Setters, HashEqual
	public String getName() {
		return name;
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


	public boolean isShowCategory() {
		return showCategory;
	}


	public void setShowCategory(boolean showCategory) {
		this.showCategory = showCategory;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(icon, name, showCategory);
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
		return Objects.equals(icon, other.icon) && Objects.equals(name, other.name)
				&& showCategory == other.showCategory;
	}

	

	

	

}
