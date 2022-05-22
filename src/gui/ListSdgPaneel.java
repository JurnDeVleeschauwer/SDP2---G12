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
import domain.SdgAbstract;
import domain.SdgComp;
import domain.SdgController;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListSdgPaneel extends GridPane {
	private final SdgController sdgController;
	private final HoofdPaneel hoofdPaneel;
	private TableView<SdgAbstract> tableView;
	private MvoGoalController mvoGoalController;

	public ListSdgPaneel(HoofdPaneel hoofdPaneel, SdgController sdgController, MvoGoalController mvoGoalController) {
		this.hoofdPaneel = hoofdPaneel;
		this.sdgController = sdgController;
		this.mvoGoalController = mvoGoalController;

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
		this.getChildren().clear();
		tableView = new TableView<SdgAbstract>();
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TableColumn<SdgAbstract, String> column1 = new TableColumn<>("id");
		column1.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<SdgAbstract, String> column2 = new TableColumn<>("target");
		column2.setCellValueFactory(new PropertyValueFactory<>("target"));

		TableColumn<SdgAbstract, String> column3 = new TableColumn<>("name");
		column3.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<SdgAbstract, String> column4 = new TableColumn<>("description");
		column4.setCellValueFactory(new PropertyValueFactory<>("description"));

		tableView.getColumns().add(column1);
		tableView.getColumns().add(column2);
		tableView.getColumns().add(column3);
		tableView.getColumns().add(column4);

		for (SdgAbstract sdgChild : sdgController.getAll()) {
			System.out.println(sdgChild);
			tableView.getItems().add((SdgAbstract) sdgChild);
		}
		add(tableView, 2, 4);

		Button deleteButtonAction = new Button("Verwijderen");
		deleteButtonAction.setOnAction(this::deleteButtonAction);
		add(deleteButtonAction, 12, 11);

		Button createButtonAction = new Button("Aanmaken");
		createButtonAction.setOnAction(this::createButtonAction);
		add(createButtonAction, 13, 11);

		tableView.setRowFactory(tv -> {
			TableRow<SdgAbstract> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					SdgAbstract rowData = row.getItem();
					hoofdPaneel.toonSdgPaneel(rowData.getId() - 1);
				}
			});
			return row;
		});

	}

	private void deleteButtonAction(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmeer verwijdering");
		alert.setHeaderText("Bent u zeker dat u deze categorie wilt verwijderen?");
		alert.setGraphic(null);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			sdgController.deleteSdg(sdgController.getSdg(tableView.getSelectionModel().getSelectedItem().getId()));

			tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItem());

		}
		maakGrid();

	}

	private void createButtonAction(ActionEvent event) {

		List<Object> resultaat = SdgAanmakenPopup.display(mvoGoalController.getAll() ,false);
		if (!resultaat.isEmpty()) {
			sdgController.addSdg((String) resultaat.get(0), (String) resultaat.get(1));

		}
		maakGrid();

	}
}
