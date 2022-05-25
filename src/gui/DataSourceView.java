package gui;

import domain.Category;
import domain.DataPerSource;
import domain.Datasource;
import domain.DatasourceController;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ListView.EditEvent;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;

public class DataSourceView extends HBox {

	private final HoofdPaneel hoofdPaneel;
	private final DatasourceController datasourceController;
	private TableView<Datasource> tableView;
	private int mvoId;
	
	ListView<String> listViewX;
	ListView<String> listViewY;
	Label labelTable;
	Label labelX ;
	Label labelY;
	Button buttonMvo;
	
	public DataSourceView(HoofdPaneel hoofdPaneel, DatasourceController datasourceController,Datasource datasource, int mvoId) {
		this.hoofdPaneel = hoofdPaneel;
		this.datasourceController = datasourceController;
		this.mvoId=mvoId;
		maakTableView(datasource);
		
		this.setSpacing(20);
	}

	public void maakTableView(Datasource datasource) {
		
		VBox vbox= new VBox();
		vbox.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		vbox.setId("datasourcevbox_id");
		buttonMvo=new Button("Terug naar MvoGoal");
		buttonMvo.setId("terugmvobtn_id");
		buttonMvo.setOnAction(e->{
			hoofdPaneel.toonMvoGoalPaneel(mvoId);
		});
		
		vbox.getChildren().add(buttonMvo);

		tableView = new TableView<Datasource>();
		tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		tableView.setEditable(true);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableView.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		listViewX=new ListView<>();
		listViewY=new ListView<>();
		listViewX.setPrefWidth(100);
		listViewY.setPrefWidth(100);
		listViewX.setEditable(true);
		listViewX.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		listViewY.setEditable(true);
		listViewY.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		listViewX.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		listViewY.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		listViewX.setCellFactory(TextFieldListCell.forListView());
		listViewX.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {

				listViewY.getSelectionModel().select(listViewX.getSelectionModel().getSelectedIndex());
			}
		} );
	
		listViewX.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>() {
	
			@Override
			public void handle(EditEvent<String> event) {


				datasource.getData().getDataSet().put(listViewY.getSelectionModel().getSelectedItem(), Integer.parseInt( event.getNewValue()));
				datasourceController.updateDatasource(datasource);
				listViewX.getItems().set(event.getIndex(), event.getNewValue());
}
		});
	
		
		listViewY.setCellFactory(TextFieldListCell.forListView());
		listViewY.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {

				listViewX.getSelectionModel().select(listViewY.getSelectionModel().getSelectedIndex());
			}
		} );
	
		listViewY.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>() {
	
			@Override
			public void handle(EditEvent<String> event) {


				datasource.getData().getDataSet().remove(listViewY.getSelectionModel().getSelectedItem());
				datasource.getData().getDataSet().put(event.getNewValue(),Integer.parseInt( listViewX.getSelectionModel().getSelectedItem()));
				
				datasourceController.updateDatasource(datasource);
				listViewY.getItems().set(event.getIndex(), event.getNewValue());
}
		});
	
		
		
		labelTable = new Label("Datasource kenmerken");
		labelX = new Label("X-as waarden");
		labelY = new Label("Y-as waarden");
		
		
		tableView.getItems().add(datasource);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		
		
		TableColumn<Datasource, Integer> idColumn = new TableColumn<>("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		idColumn.setMinWidth(30);
		idColumn.setMaxWidth(30);
		
		
		TableColumn<Datasource, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		nameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Datasource,String>>() {
			@Override
			public void handle(CellEditEvent<Datasource, String> event) {
				Datasource datasource = event.getRowValue();
				datasource.setName(event.getNewValue());
				datasourceController.updateDatasource( datasource);
			}
		});
		
		TableColumn<Datasource, Integer> expectedGoalColumn = new TableColumn<>("Expected goal");
		expectedGoalColumn.setCellValueFactory(new PropertyValueFactory<>("expectedGoal"));
		expectedGoalColumn.setCellFactory(TextFieldTableCell.<Datasource,Integer>forTableColumn(new IntegerStringConverter()));
		expectedGoalColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Datasource,Integer>>() {
			
			@Override
			public void handle(CellEditEvent<Datasource, Integer> event) {
				Datasource datasource = event.getRowValue();
				datasource.setExpectedGoal(event.getNewValue());
				datasourceController.updateDatasource( datasource);
			}
		});
		
		TableColumn<Datasource, Integer> yearColumn = new TableColumn<>("Year");
		yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
		yearColumn.setCellFactory(TextFieldTableCell.<Datasource,Integer>forTableColumn(new IntegerStringConverter()));

yearColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Datasource,Integer>>() {
			
			@Override
			public void handle(CellEditEvent<Datasource, Integer> event) {
				Datasource datasource = event.getRowValue();
				datasource.setYear(event.getNewValue());
				datasourceController.updateDatasource( datasource);
			}
		});
		

		
		TableColumn<Datasource, String> xAxisColumn = new TableColumn<>("X axis");
		xAxisColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getxAxis()));
		xAxisColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		xAxisColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Datasource,String>>() {
			@Override
			public void handle(CellEditEvent<Datasource, String> event) {
				Datasource datasource = event.getRowValue();
				datasource.setxAxis(event.getNewValue());
				datasourceController.updateDatasource( datasource);
			}
		});



		TableColumn<Datasource, String> yAxisColumn = new TableColumn<>("Y axis");
		yAxisColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getyAxis()));
		yAxisColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		yAxisColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Datasource,String>>() {
			@Override
			public void handle(CellEditEvent<Datasource, String> event) {
				Datasource datasource = event.getRowValue();
				datasource.setyAxis(event.getNewValue());
				datasourceController.updateDatasource( datasource);
			}
		});

		
		datasource.getData().getDataSet().forEach((month,data)-> {
			listViewY.getItems().add(month);
			listViewX.getItems().add(Integer.toString(data) );
		});
		



		tableView.getColumns().add(idColumn);
		tableView.getColumns().add(nameColumn);
		tableView.getColumns().add(expectedGoalColumn);
		tableView.getColumns().add(yearColumn);
		tableView.getColumns().add(xAxisColumn);
		tableView.getColumns().add(yAxisColumn);
		
		Insets ins = new Insets(20,0,0,0);
		
		VBox vboxtable = new VBox();
		vboxtable.setPadding(ins);
		vboxtable.getChildren().addAll(labelTable, tableView);
		
		VBox vboxtabley = new VBox();
		vboxtabley.setPadding(ins);
		vboxtabley.getChildren().addAll(labelY, listViewY);
		
		VBox vboxtablex = new VBox();
		vboxtablex.setPadding(ins);
		vboxtablex.getChildren().addAll(labelX, listViewX);
		
		
		this.getChildren().addAll(vbox, vboxtable,vboxtabley, vboxtablex);

	}

}
