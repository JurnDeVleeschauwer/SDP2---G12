package domain;

import java.util.ArrayList;
import java.util.List;

public class CategoryController {
	
	
	private CategoryManager cm; 
	private SdgManager sdgManager; 

	public CategoryController() {
		cm = new CategoryManager(); 
		
		if(sdgManager == null) {
			sdgManager = new SdgManager(); 
		}
	}
	

	
	public void addCategory(String name, String icon) {
		
		Category cat; 
		cat = new Category(name, icon); 


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
	
	public void addSdgToCategory(int categoryId, int sdgId) {
		
		SdgAbstract sdg = sdgManager.getSdg(sdgId); 
		
		cm.addSdgToCategory(categoryId, sdg);
	}
	
	public List<Category> getAll() {
		return cm.getAllCategories(); 
	}

	public List<List<String>> getCategorieen() { //om GUI te testen


		return cm.getCategoriesAsStringList();

	}

}
