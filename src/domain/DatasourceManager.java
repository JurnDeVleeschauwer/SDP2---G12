package domain;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import persistence.GenericMapperJpa;

public class DatasourceManager {
	
	private List<Datasource> datasources;
	private GenericMapperJpa<Datasource> datasourceMapper = new GenericMapperJpa<>(Datasource.class);	
	
	public DatasourceManager() {
		datasources = new ArrayList<>();
	}
	
	
	public void addDatasource(Datasource d) {
		datasourceMapper.insert(d);
		populateList();
	} 
	
	public void addDataFromFile(File f) {
		
		
		
	}
	
	public void removeDatasource(Datasource d) {
		
		datasourceMapper.delete(d);
	}
	
	public Datasource getDatasource(int index) {
		return datasources.get(index);
	}
	
	public void updateDatasource(Datasource datasource) {
		datasourceMapper.update(datasource); 
	}

	public void deleteDatasource(Datasource datasource) {
		datasourceMapper.delete(datasource);
		
	}
		
	public List<Datasource> getAllDatasources() {
		return datasources; 
	}
	
	
	public void populateList() {
		datasources.clear();
		datasources.addAll(datasourceMapper.findAll()); 

	}
	

	public List<Datasource> getDatasources() {
		return datasources; 
	}
	
	

}
