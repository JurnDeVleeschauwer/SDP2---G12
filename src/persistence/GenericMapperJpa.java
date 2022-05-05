package persistence;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenericMapperJpa<T> implements GenericMapper<T> {
	private static final String PU_NAME = "fluvius";
	protected static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU_NAME);
	protected static EntityManager em = emf.createEntityManager();
	private final Class<T> type;

	public GenericMapperJpa(Class<T> type) {
		this.type = type;
	}

	public static void closePersistency() {
		em.close();
		emf.close();
	}

	public static void startTransaction() {
		em = emf.createEntityManager();
		em.getTransaction().begin();
	}

	public static void commitTransactionAndClose() {

		em.getTransaction().commit();
		em.close();

	}

	public static void rollbackTransaction() {
		em.getTransaction().rollback();
	}

	@Override
	public List<T> findAll() {

		startTransaction();
		List<T> result = new ArrayList<>();
		result = em.createQuery("select entity from " + type.getName() + " entity", type).getResultList();
		commitTransactionAndClose();

		return result;
	}

	@Override
	public <U> T get(U id) {
		startTransaction();
		T entity = em.find(type, id);
		commitTransactionAndClose();
		return entity;

	}

	@Override
	public T update(T object) {
		T updatedtT;
		startTransaction();
		updatedtT = em.merge(object);

		commitTransactionAndClose();
		return updatedtT;
	}

	@Override
	public void delete(T object) {
		startTransaction();
		em.remove(em.merge(object));
		commitTransactionAndClose();
	}

	@Override
	public void insert(T object) {

		startTransaction();
		em.persist(object);
		commitTransactionAndClose();
	}

	@Override
	public <U> boolean exists(U id) {
		startTransaction();
		T entity = em.find(type, id);
		commitTransactionAndClose();

		return entity != null;
	}

}
