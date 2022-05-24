package domain;

import java.util.ArrayList;
import java.util.List;

public class CategoryController {

	private CategoryManager cm;
	private SdgManager sdgManager;

	public CategoryController() {
		cm = new CategoryManager();

	}

	public CategoryController(SdgManager sdgManager) {
		cm = new CategoryManager();
		this.sdgManager=sdgManager;
	}

	public Category addCategory(String name, String icon) {

		Category cat;
		cat = new Category(name, icon);
		
		return cm.addCategory(cat);
	}

	public void updateCategory(int id,String naam,String icon) {

		Category categoryToUpdate = getCategory(id);
		categoryToUpdate.setName(naam);
		categoryToUpdate.setIcon(icon);
		cm.updateCategory(categoryToUpdate);
	}

	public Category getCategory(int id) {

		return cm.getCategory(id);
	}

	public void deleteCategory(int id) {
		cm.deleteCategory(id);
	}

	public void addSdgToCategory(int categoryId, int sdgId) {


		SdgAbstract sdg = sdgManager.getSdg(sdgId-1);

		cm.addSdgToCategory(categoryId, sdg);
		
	}

	public List<Category> getAll() {
		return cm.getAllCategories();
	}

	public List<List<String>> getCategorieen() { // om GUI te testen

		return cm.getCategoriesAsStringList();

	}

	public void updateCategoryName(Category category, String newValue) {
		cm.updateCategoryName(category,newValue);
		
	}

	public void updateCategoryIcoon(Category category, String newValue) {
		cm.updateCategoryIcoon(category,newValue);

		
	}

	public boolean heeftSdgs(int id) {

		return cm.heeftSdgs(id);
	}

}
