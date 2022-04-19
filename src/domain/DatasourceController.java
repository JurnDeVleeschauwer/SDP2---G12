package domain;

public class DatasourceController {

	private Datasource datasource;
	
	public DatasourceController() {
		datasource = new Datasource();
	}

	public void addDatasource(String name, String yAxis, String xAxis, int year, int expectedGoal) {

		Datasource data = new Datasource(name, yAxis, xAxis, year, expectedGoal );
		datasource.addDatasource(data);
	}

	public void updateDatasource(int DatasourceId) {

		Datasource datasourceToUpdate = getDatasource(DatasourceId);

		datasource.updateDatasource(datasourceToUpdate);
	}

	public Datasource getDatasource(int DatasourceId) {

		return datasource.getDatasource(DatasourceId);
	}

	public void deleteDatasource(Datasource Datasource) {
		datasource.deleteDatasource(Datasource);
	}

}
