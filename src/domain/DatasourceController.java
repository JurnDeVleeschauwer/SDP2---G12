package domain;

import java.util.List;

public class DatasourceController {

	private DatasourceManager datasourceManager;
	
	public DatasourceController() {
		datasourceManager = new DatasourceManager();
	}

	public void addDatasource(String name, String yAxis, String xAxis, int year, int expectedGoal) {

		Datasource data = new Datasource(name, yAxis, xAxis, year, expectedGoal );
		System.out.println(data.getId());
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
