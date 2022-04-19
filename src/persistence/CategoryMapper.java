package persistence;

import java.io.Serializable;

import javax.persistence.EntityManager;

import domain.Category;

public class CategoryMapper implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager entityManager = EmfInstance.getEntityManagerFactory().createEntityManager();


	
	public void addCategory(Category c) {
		openTransaction();
		entityManager.persist(c);
		

		commitTransaction();
		closeTransaction();
		
		System.out.println("Category Added");
	}
	
	

	public void removeCategory(Category c) {

		
	}
	
	private void openTransaction() {
		entityManager.getTransaction().begin();
	}
	
	private void commitTransaction() {
		entityManager.getTransaction().commit();
	}
	
	private void closeTransaction() {
		entityManager.close();
	}


}
