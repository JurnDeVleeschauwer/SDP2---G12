package domain;

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
		
	}
	
	public void removeDatasource(Datasource d) {
		
		datasourceMapper.delete(d);
	}
	
	public Datasource getDatasource(int datasourceId) {
		return datasourceMapper.get(datasourceId);
	}
	
	public void updateDatasource(Datasource category) {
		datasourceMapper.update(category); 
	}

	public void deleteDatasource(Datasource category) {
		datasourceMapper.delete(category);
		
	}
		
	public List<Datasource> getAllDatasources() {
		return datasources; 
	}
	
	
	public void populateList() {
		datasources.addAll(datasourceMapper.findAll()); 

	}
	

	public List<Datasource> getDatasources() {
		return datasources; 
	}
	
	

}
