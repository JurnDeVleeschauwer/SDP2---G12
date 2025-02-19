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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListMvoGoalPaneel extends HBox {
	private final MvoGoalController mvoGoalController;
	private final HoofdPaneel hoofdPaneel;
	private DatasourceController datasourceController;
	private TableView<MvoGoalAbstract> tableView;

	public ListMvoGoalPaneel(HoofdPaneel hoofdPaneel, MvoGoalController mvoGoalController,
			DatasourceController datasourceController) {
		this.hoofdPaneel = hoofdPaneel;
		this.mvoGoalController = mvoGoalController;
		this.datasourceController = datasourceController;

		configureerGrid();

	}

	private void configureerGrid() {
		this.setSpacing(40);
		voegComponentenToe();

	}

	public void voegComponentenToe() {
		maakGrid();
	}

	private void maakGrid() {
		this.getChildren().clear();
		
		VBox vbox = new VBox();
		vbox.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		vbox.setPadding(new Insets(0, 0, 20, 0));
		vbox.setMaxHeight(Double.MAX_VALUE);
		vbox.setId("mvolistVbox_id");
		
		Button createButtonAction = new Button("Nieuwe MVO maken");
		createButtonAction.setOnAction(this::createButtonAction);
		createButtonAction.setId("createmvobtn_id");
		
		Button deleteButtonAction = new Button("Verwijder MVO");
		deleteButtonAction.setOnAction(this::deleteButtonAction);
		deleteButtonAction.setId("deletemvobtn_id");
		
		vbox.getChildren().addAll(createButtonAction, deleteButtonAction);
		
		VBox newVbox = new VBox();
		newVbox.setPadding(new Insets(20,0,20,0));
		
		tableView = new TableView<MvoGoalAbstract>();
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableView.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		TableColumn<MvoGoalAbstract, Number> column1 = new TableColumn<>("Id");
		column1.setCellValueFactory(new PropertyValueFactory<MvoGoalAbstract, Number>("id"));
		column1.setMinWidth(30);
		column1.setMaxWidth(30);

		TableColumn<MvoGoalAbstract, String> column2 = new TableColumn<>("Naam");
		column2.setCellValueFactory(new PropertyValueFactory<MvoGoalAbstract, String>("name"));

		TableColumn<MvoGoalAbstract, String> column3 = new TableColumn<>("Mvoname");
		column3.setCellValueFactory(new PropertyValueFactory<MvoGoalAbstract, String>("MvoName"));

		TableColumn<MvoGoalAbstract, Number> column4 = new TableColumn<>("Waarde");
		column4.setCellValueFactory(new PropertyValueFactory<MvoGoalAbstract, Number>("value"));

		tableView.getColumns().add(column1);
		tableView.getColumns().add(column2);
		tableView.getColumns().add(column3);
		tableView.getColumns().add(column4);

		for (MvoGoalAbstract mvoGoalChild : mvoGoalController.getAll()) {
			// System.out.println(mvoGoalChild);
			tableView.getItems().add((MvoGoalAbstract) mvoGoalChild);
		}
		newVbox.getChildren().addAll(new Label("MVO's"), tableView);

		tableView.setRowFactory(tv -> {
			TableRow<MvoGoalAbstract> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					MvoGoalAbstract rowData = row.getItem();
					hoofdPaneel.toonMvoGoalPaneel(rowData.getId() - 1);
				}
			});
			return row;
		});
		
		this.getChildren().addAll(vbox, newVbox);
	}

	private void deleteButtonAction(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmeer verwijdering");
		alert.setHeaderText("Bent u zeker dat u deze MVO doelstelling wilt verwijderen?");
		alert.setGraphic(null);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			mvoGoalController.deleteMvoGoal(
					mvoGoalController.getMvoGoal(tableView.getSelectionModel().getSelectedItem().getId() - 1));

			tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItem());

		}
		maakGrid();

	}

	private void createButtonAction(ActionEvent event) {

		List<Object> resultaat = MvoGoalAanmakenPopup.display(datasourceController.getDatasources(), false);
		if (!resultaat.isEmpty()) {
			mvoGoalController.addMvoGoalChild(Integer.valueOf((String) resultaat.get(0)), (Datasource) resultaat.get(1),
					(String) resultaat.get(2), (String) resultaat.get(3));

		}
		;
		maakGrid();

	}
}
