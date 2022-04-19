package domain;

public class DomainController {
	
	private Category c = new Category() ; 
	
	
	public void addCategory(String name, String icon, int id,  boolean showCategory) {
		
		Category cat = new Category(name, icon, id, showCategory); 
		System.out.println("DC check");
		c.addCategory(cat);
		
		
		
	}
	
	

}
