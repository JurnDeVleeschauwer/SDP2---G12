package gui;

import domain.Category;
import domain.DataPerSource;
import domain.Datasource;
import domain.DatasourceController;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class DataSourceView extends GridPane {

	private final HoofdPaneel hoofdPaneel;
	private final DatasourceController datasourceController;
	private TableView<Datasource> tableView;
	ListView<String> listViewX;
	ListView<String> listViewY;

	public DataSourceView(HoofdPaneel hoofdPaneel, DatasourceController datasourceController) {
		this.hoofdPaneel = hoofdPaneel;
		this.datasourceController = datasourceController;
		maakTableView(datasourceController.getDatasource(1));
		add(tableView, 1, 1);
		add(listViewY,2,1);
		add(listViewX,3,1);

	}

	public void maakTableView(Datasource datasource) {

		tableView = new TableView<Datasource>();
		listViewX=new ListView<>();
		listViewY=new ListView<>();
		listViewX.setPrefWidth(70);

		listViewY.setPrefWidth(70);
		
		tableView.getItems().add(datasource);
		tableView.setEditable(true);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TableColumn<Datasource, Integer> idColumn = new TableColumn<>("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<Datasource, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Datasource, Integer> expectedGoalColumn = new TableColumn<>("Expected goal");
		expectedGoalColumn.setCellValueFactory(new PropertyValueFactory<>("expectedGoal"));

		TableColumn<Datasource, Integer> yearColumn = new TableColumn<>("Year");
		yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

		TableColumn<Datasource, String> xAxisColumn = new TableColumn<>("X axis");
		xAxisColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getxAxis()));


		TableColumn<Datasource, String> xAxisDataColumn = new TableColumn<>("X axis Data");



		
		datasource.getData().getDataSet().forEach((month,data)-> {

			listViewY.getItems().add(month);
			listViewX.getItems().add(Integer.toString(data) );
		});

		


		TableColumn<Datasource, String> yAxisDataColumn = new TableColumn<>("Y axis Data");

		TableColumn<Datasource, String> yAxisColumn = new TableColumn<>("Y axis");
		yAxisColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getyAxis()));

		tableView.getColumns().add(idColumn);
		tableView.getColumns().add(nameColumn);
		tableView.getColumns().add(expectedGoalColumn);
		tableView.getColumns().add(yearColumn);
		tableView.getColumns().add(xAxisColumn);
//		tableView.getColumns().add(xAxisDataColumn);
//		tableView.getColumns().add(yAxisDataColumn);
		tableView.getColumns().add(yAxisColumn);

	}

}
