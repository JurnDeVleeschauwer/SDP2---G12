package domain;

import java.util.ArrayList;
import java.util.List;

public class DomeinController {
	
	CategoryController categoryController;
	
	public DomeinController() {
		categoryController=new CategoryController();
	}

	public List<List<String>> getCategorieen(){
		List<List<String>>	lijstCategorieen = new ArrayList<>();
		List<String> categorie1=new ArrayList<>();
		categorie1.add("Eerste categorie");
		categorie1.add("foto1");
		
		List<String> categorie2=new ArrayList<>();
		
		categorie2.add("tweede categorie");
		categorie2.add("foto2");
		
		lijstCategorieen.add(categorie1);
		lijstCategorieen.add(categorie2);
		return lijstCategorieen;
		
	}

	public void verwijderCategorie(int indexOf) {
		categoryController.getCategory(indexOf);
		//categoryController.deleteCategory(categoryController.getCategory(indexOf));

	}

	public void wijzigCategorie(int indexOf) {
		// TODO Auto-generated method stub
		
	}
	
	

}
