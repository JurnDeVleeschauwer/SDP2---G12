package domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class DatasourceController {

	private DatasourceManager datasourceManager;
	
	public DatasourceController() {
		datasourceManager = new DatasourceManager();
	}

	public void addDatasource(String name, String yAxis, String xAxis, int year, int expectedGoal, File f) throws FileNotFoundException {

		DataPerSource dataPerSource = new DataPerSource(f);  
		
		Datasource data = new Datasource(name, yAxis, xAxis, year, expectedGoal, dataPerSource );
		datasourceManager.addDatasource(data);
	}

	public void updateDatasource(int DatasourceId) {

		Datasource datasourceToUpdate = getDatasource(DatasourceId);

		datasourceManager.updateDatasource(datasourceToUpdate);
	}

	public Datasource getDatasource(int DatasourceId) {

		return datasourceManager.getDatasource(DatasourceId);
	}

	public void deleteDatasource(Datasource Datasource) {
		datasourceManager.deleteDatasource(Datasource);
	}
	
	public List<Datasource> getDatasources() {
		return datasourceManager.getAllDatasources(); 
	}

}
