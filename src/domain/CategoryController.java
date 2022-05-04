package domain;

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
	
	public void updateCategory(int categoryId) {
		
		Category categoryToUpdate = getCategory(categoryId); 
		
		cm.updateCategory(categoryToUpdate);
	}
	
	
	public Category getCategory(int categoryId) {
		
		return cm.getCategory(categoryId);
	}
	
	
	public void deleteCategory(Category category) {
		cm.deleteCategory(category); 
	}
	
	public void addSdgToCategory(int categoryId, int sdgId) {
		
		SdgAbstract sdg = sdgManager.getSdg(sdgId); 
		
		cm.addSdgToCategory(categoryId, sdg);
	}
	
	public List<Category> getAll() {
		return cm.getAllCategories(); 
	}

}
