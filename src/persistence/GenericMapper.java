package persistence;

import java.util.List;

public interface GenericMapper<T> {
 
	    public List<T> findAll();  
	    public <U> T get(U id);
	    public T update(T object);
	    public void delete(T object);
	    public void insert(T object);
	    public <U> boolean exists(U id);

}
