package persistence;

import domain.MvoCoordinator;

public class MvoCoordinatorMapper<T> extends GenericMapperJpa<T>
{

	private Class<T> type; 
	
	public MvoCoordinatorMapper(Class<T> type) {
		super(type);
		this.type = type; 
	}

	public MvoCoordinator get(String username, String password) /*throws MvoException*/ {
		
        return (MvoCoordinator) em.createQuery("select mvo from " + type.getSimpleName() + " mvo " + "where " + "mvo.username like " + ":username" + " AND mvo.password like " + ":password" )
        		.setParameter("password", password)
        		.setParameter("username", username).getSingleResult(); 

		
		
	}

}
