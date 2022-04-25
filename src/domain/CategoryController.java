package domain;

public class CategoryController {
	
	
	private CategoryManager cm; 
	
	public CategoryController() {
		cm = new CategoryManager(); 
	}
	
	public void addCategory(String name, String icon, int id,  boolean showCategory) {
		
		Category cat = new Category(name, icon, id, showCategory); 
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
	
	

}
