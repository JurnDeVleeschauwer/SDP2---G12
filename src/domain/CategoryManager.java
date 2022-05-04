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
	public Category getCategoryByIndex(int index) {
		return categories.get(index);
	}
	public void updateCategory(Category category) {
		categoryMapper.update(category); 
		updateList();
	}

	public void deleteCategory(int index) {
		categoryMapper.delete(categories.get(index));
		updateList();
	}
	
	public void populateList() {
		categories.addAll(categoryMapper.findAll()); 

	}
	
	public void updateList() {
		categories.clear();
		categories.addAll(categoryMapper.findAll()); 

	}
	
	public List<Category> getAllCategories() {
		return categories; 
	}
	
	public List<List<String>> getCategoriesAsStringList() {
		List<List<String>> lijst = new ArrayList<>();
		
		for(Category category : categories) {
			lijst.add(category.getAsStringList());
		}
		
		return lijst; 
	}
	
	public void addCategory(Category c) {
		
		categoryMapper.insert(c);

		updateList();

	}
	
	public void addSdgToCategory(int id, SdgAbstract sdg) {
		
		categories.get(id).addSdg(sdg);
		populateList();
	}

}
