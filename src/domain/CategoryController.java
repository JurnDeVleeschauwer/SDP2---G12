package domain;

import java.util.ArrayList;
import java.util.List;

public class CategoryController {
	
	
	private CategoryManager cm; 
	
	public CategoryController() {
		cm = new CategoryManager(); 
	}
	
	public void addCategory(String name, String icon,  boolean showCategory) {
		
		Category cat = new Category(name, icon,  showCategory); 
		cm.addCategory(cat);
	}
	
	public void updateCategory(int index,List<String> categorie) {
		
		Category categoryToUpdate = getCategory(index); 
		categoryToUpdate.setName(categorie.get(0));
		categoryToUpdate.setIcon(categorie.get(1));
		cm.updateCategory(categoryToUpdate);
	}
	
	
	public Category getCategory(int index) {
		
		return cm.getCategoryByIndex(index);
	}
	
	
	public void deleteCategory(int index) {
		cm.deleteCategory(index); 
	}
	
	public List<Category> getAll() {
		return cm.getAllCategories(); 
	}

	public List<List<String>> getCategorieen() { //om GUI te testen


		return cm.getCategoriesAsStringList();

	}

}
