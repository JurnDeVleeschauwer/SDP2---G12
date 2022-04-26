package domain;

import java.util.ArrayList;
import java.util.List;

import persistence.GenericMapperJpa;

public class CategoryManager {
	
	private List<Category> categories;
	private GenericMapperJpa<Category> categoryMapper = new GenericMapperJpa<>(Category.class); 
	
	
	public CategoryManager() {
		categories = new ArrayList<>();
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
		
	}

}
