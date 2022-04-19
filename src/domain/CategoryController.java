package domain;

public class CategoryController {
	
	
	private Category c;
	
	public CategoryController() {
		c = new Category(); 
	}
	
	public void addCategory(String name, String icon, int id,  boolean showCategory) {
		
		Category cat = new Category(name, icon, id, showCategory); 
		c.addCategory(cat);
	}
	
	public void updateCategory(int categoryId) {
		
		Category categoryToUpdate = getCategory(categoryId); 
		
		c.updateCategory(categoryToUpdate);
	}
	
	
	public Category getCategory(int categoryId) {
		
		return c.getCategory(categoryId);
	}
	
	
	public void deleteCategory(Category category) {
		c.deleteCategory(category); 
	}
	
	

}
