
package persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmfInstance {

	private static EntityManagerFactory entityManagerFactory; 

	
	public static EntityManagerFactory getEntityManagerFactory() {
		
		
		if(entityManagerFactory == null) {
			 entityManagerFactory = Persistence.createEntityManagerFactory("fluvius");
		} 
		return entityManagerFactory;
	}
}
