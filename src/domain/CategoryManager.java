package domain;

import java.util.ArrayList;
import java.util.List;

import persistence.GenericMapperJpa;

public class CategoryManager {
	
	private List<Category> categories;
	private GenericMapperJpa<Category> categoryMapper;
	
	public CategoryManager() {
		categories = new ArrayList<>();
		categoryMapper = new GenericMapperJpa<Category>(Category.class); 
	
		
		populateList();
	}
	
	public void removeCategory(Category c) {
		
		categoryMapper.delete(c);
	}
	
	public Category getCategory(int categoryId) {
		return categoryMapper.get(categoryId);
	}
	
	public void updateCategory(Category category) {
		categoryMapper.update(category); 
	}

	public void deleteCategory(Category category) {
		categoryMapper.delete(category);
		
	}
	
	public void populateList() {
		categories.addAll(categoryMapper.findAll()); 

	}
	
	public List<Category> getAllCategories() {
		return categories; 
	}
	
	public void addCategory(Category c) {
		
		categoryMapper.insert(c);
		populateList();
		
	}
	
	public void addSdgToCategory(int id, SdgAbstract sdg) {
		
		categories.get(id).addSdg(sdg);
		populateList();
	}

}
