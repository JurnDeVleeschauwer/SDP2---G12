package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import domain.Datasource;
import domain.DatasourceController;
import domain.MvoGoalAbstract;
import domain.MvoGoalChild;
import domain.MvoGoalComp;
import domain.MvoGoalController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListMvoGoalPaneel extends GridPane {
	private final MvoGoalController mvoGoalController;
	private final HoofdPaneel hoofdPaneel;
	private DatasourceController datasourceController;
	private TableView<MvoGoalAbstract> tableView;

	public ListMvoGoalPaneel(HoofdPaneel hoofdPaneel, MvoGoalController mvoGoalController,DatasourceController datasourceController) {
		this.hoofdPaneel = hoofdPaneel;
		this.mvoGoalController = mvoGoalController;
		this.datasourceController = datasourceController;

		configureerGrid();


	}

	private void configureerGrid() {
		setPadding(new Insets(10));
		setHgap(2);
		setVgap(2);
		voegComponentenToe();

	}

	public void voegComponentenToe() {
		maakGrid();
	}

	private void maakGrid() {
		getChildren().clear();
		tableView = new TableView<MvoGoalAbstract>();
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TableColumn<MvoGoalAbstract, String> column1 = new TableColumn<>("id");
		column1.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<MvoGoalAbstract, String> column2 = new TableColumn<>("name");
		column2.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<MvoGoalAbstract, String> column3 = new TableColumn<>("mvoName");
		column2.setCellValueFactory(new PropertyValueFactory<>("mvoName"));
		
		TableColumn<MvoGoalAbstract, String> column4 = new TableColumn<>("value");
		column2.setCellValueFactory(new PropertyValueFactory<>("value"));


		tableView.getColumns().add(column1);
		tableView.getColumns().add(column2);
		tableView.getColumns().add(column3);
		tableView.getColumns().add(column4);

		for (MvoGoalAbstract mvoGoalChild : mvoGoalController.getAll()) {
			tableView.getItems().add((MvoGoalAbstract) mvoGoalChild);
		}
		add(tableView, 2, 4);

		
		Button deleteButtonAction = new Button("Verwijderen");
		deleteButtonAction.setOnAction(this::deleteButtonAction);
		add(deleteButtonAction, 12, 11);
		
		Button createButtonAction = new Button("Aanmaken");
		createButtonAction.setOnAction(this::createButtonAction);
		add(createButtonAction, 13, 11);
		 

		
	}
	private void deleteButtonAction(ActionEvent event) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmeer verwijdering");
		alert.setHeaderText("Bent u zeker dat u deze categorie wilt verwijderen?");
		alert.setGraphic(null);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			mvoGoalController.deleteMvoGoal(mvoGoalController.getMvoGoal(tableView.getSelectionModel().getSelectedItem().getId()-1)); 
																									
			tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItem());

		}
		
	}
	
	private void createButtonAction(ActionEvent event) {
		
		List<Object> resultaat = MvoGoalAanmakenPopup.display(datasourceController.getDatasources());
		if (!resultaat.isEmpty()) {
			mvoGoalController.addMvoGoalChild(Integer.valueOf((String) resultaat.get(0)), (Datasource) resultaat.get(1),
					(String) resultaat.get(2), (String) resultaat.get(3));

		};
		
	}
}
