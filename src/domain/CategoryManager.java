package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import persistence.CategorySdgMapper;
import persistence.GenericMapperJpa;

public class CategoryManager {
	
	private List<Category> categories;
	private GenericMapperJpa<Category> categoryMapper;
	private CategorySdgMapper<String> catSdgMapper;
	public CategoryManager() {
		categories = new ArrayList<>();
		categoryMapper = new GenericMapperJpa<Category>(Category.class); 
	
		
		populateList();
	}
	
	public void removeCategory(Category c) {
		
		categoryMapper.delete(c);
	}
	
	public Category getCategory(int id) {
		return categories.stream().filter(category->category.getId()==id).findAny().get();
	}
	public Category getCategoryByIndex(int index) {
		return categories.get(index);
	}
	public void updateCategory(Category category) {
		categoryMapper.update(category); 
		updateList();
	}

	public void deleteCategory(int id) {
		Category categorySelected= categories.stream().filter(category->category.getId()==id).findAny().get();
		categoryMapper.delete(categorySelected );
		categories.remove(categorySelected);
	}
	
	public void populateList() {
		categories.clear();
		categories.addAll(categoryMapper.findAll()); 
		for (Category cat :categories) {
			cat.setMvoGoalAbstract(catSdgMapper.getSdgsFromCategory(cat.getId()) );

		}

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
	
	public Category addCategory(Category c) {
		
		categoryMapper.insert(c);

		categories.add(c);
		return c;
	}
	
	public void addSdgToCategory(int id, SdgAbstract sdg) {
		 catSdgMapper = new CategorySdgMapper<>(null);
	
		catSdgMapper.persistSdgToCategory(categories.get(id).getId(), sdg.getId());
		categories.get(id).addSdg(sdg);
		populateList();
	}

	public  void updateCategoryName(Category category, String newValue) {
		Category categorySelected= categories.stream().filter(categoryFromList->categoryFromList.getId()==category.getId()).findAny().get();
		categorySelected.setName(newValue);
		categoryMapper.update(categorySelected);
	}

	public void updateCategoryIcoon(Category category, String newValue) {
		Category categorySelected= categories.stream().filter(categoryFromList->categoryFromList.getId()==category.getId()).findAny().get();
		categorySelected.setIcon(newValue);
		categoryMapper.update(categorySelected);		
	}

	public boolean heeftSdgs(int id) {
		return categories.stream().filter(category->category.getId()==id).findAny().get().hasSdgs();
	}

}
