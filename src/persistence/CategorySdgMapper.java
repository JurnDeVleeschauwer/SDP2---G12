package persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.security.sasl.AuthenticationException;

import domain.MvoCoordinator;
import domain.SdgAbstract;


public class CategorySdgMapper<T> extends GenericMapperJpa<T>{

	private Class<T> type;

	public CategorySdgMapper(Class<T> type) {
		super(type);
		this.type = type;
	}


	public void persistSdgToCategory(int catId,int sdgId) {
		em = emf.createEntityManager();
		em.getTransaction().begin();

	
			em
					.createNativeQuery("insert into category_sdgabstract  (Category_ID,sdgAbstract_ID) values (#catId ,#sdgId)" )
					.setParameter("catId", catId).setParameter("sdgId", sdgId).executeUpdate();
		
			
		

		em.getTransaction().commit();
		em.close();

	}

	public void deleteFromSdgToCategory(int catId) {
		em = emf.createEntityManager();
		em.getTransaction().begin();

	
			em
					.createNativeQuery("DELETE FROM category_sdgabstract WHERE Category_ID = #catId" )
					.setParameter("catId", catId).executeUpdate();
		
			
		

		em.getTransaction().commit();
		em.close();

	}

	
	@SuppressWarnings("unchecked")
	public List<SdgAbstract> getSdgsFromCategory(int catId) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<SdgAbstract> sdgs = new ArrayList<>();
		
	
		sdgs	=  em
					.createNamedQuery("getSdgsFromCategory")
					.setParameter("catId", catId).getResultList();
	
		em.getTransaction().commit();
		em.close();

		return sdgs;

	}
	
	
}
