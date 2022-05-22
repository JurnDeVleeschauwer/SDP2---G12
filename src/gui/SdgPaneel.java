package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import domain.Category;
import domain.Datasource;
import domain.MvoGoalAbstract;
import domain.MvoGoalChild;
import domain.MvoGoalComp;
import domain.MvoGoalController;
import domain.SdgAbstract;
import domain.SdgChild;
import domain.SdgComp;
import domain.SdgController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 * 
 * @author Jurn
 *
 */
public class SdgPaneel extends GridPane {
	private final SdgController sdgController;
	private final HoofdPaneel hoofdPaneel;
	private int id;
	private TableView<SdgAbstract> tableView;
	private MvoGoalController mvoGoalController;

	public SdgPaneel(HoofdPaneel hoofdPaneel, SdgController sdgController, MvoGoalController mvoGoalController) {
		this.hoofdPaneel = hoofdPaneel;
		this.sdgController = sdgController;
		this.mvoGoalController = mvoGoalController;

		configureerGrid();
	}

	private void configureerGrid() {
		setPadding(new Insets(10));
		setHgap(2);
		setVgap(2);

	}

	public void voegComponentenToe(int id) {
		this.id = id;
		maakGrid();
	}

	private void maakGrid() {
		this.gridLinesVisibleProperty().set(true);

		getChildren().clear();

		Label title = new Label("SDG");
		title.setFont(new Font("Arial", 30));
		add(title, 5, 0);
		SdgAbstract sdg;
		if (sdgController.getSdg(this.id).isBlad()) {
			sdg = (SdgChild) sdgController.getSdg(this.id);
		} else {
			sdg = (SdgComp) sdgController.getSdg(this.id);
		}

		Label id = new Label("ID:");
		id.setFont(new Font("Arial", 15));
		add(id, 1, 1);
		add(new Label(Integer.toString(sdg.getId())), 2, 1);
		Label description = new Label("Description:");
		description.setFont(new Font("Arial", 15));
		add(description, 1, 2);
		add(new Label(sdg.getDescription()), 2, 2);
		Label name = new Label("Name:");
		name.setFont(new Font("Arial", 15));
		add(name, 1, 3);
		add(new Label(sdg.getName()), 2, 3);

		Label SDGs = new Label("Sub Sdgs:");
		SDGs.setFont(new Font("Arial", 15));
		add(SDGs, 1, 4);
		maakTableView(sdg);

		Button editButton = new Button("Wijzigen");
		editButton.setOnAction(this::editButton);
		add(editButton, 7, 0);

		if (!(sdg.isBlad())) {

			Button deleteButtonAction = new Button("Verwijderen");
			deleteButtonAction.setOnAction(this::deleteButtonAction);
			add(deleteButtonAction, 1, 11);

			Button createButton = new Button("Aanmaken");
			createButton.setOnAction(this::createButton);
			add(createButton, 1, 12);
		}
		Button listSdgButton = new Button("Back naar Sdg lijst");
		listSdgButton.setOnAction(this::listSdgButton);
		add(listSdgButton, 1, 13);

	}

	private void editButton(ActionEvent event) {

		List<Object> resultaat = SdgWijzigenPopup.display(sdgController.getSdg(this.id));
		if (resultaat != null) {
			SdgAbstract sdg = sdgController.getSdg(this.id);
			if (sdg.isBlad()) {
				SdgChild sdgChild = new SdgChild.Builder().description((String) resultaat.get(0))
						.name((String) resultaat.get(1)).icon((String) resultaat.get(2))
						.target(Integer.valueOf((String) resultaat.get(3))).mvoGoal(((SdgChild) sdg).getMvoGoal())
						.build();
				sdgController.updateSdg(sdgChild);
			} else {
				SdgComp sdgComp = new SdgComp.Builder().description((String) resultaat.get(0))
						.name((String) resultaat.get(1)).build();
				sdgController.updateSdg(sdgComp);
			}
		}
		maakGrid();
	}

	private void createButton(ActionEvent event) {
		List<Object> resultaat = SdgAanmakenPopup.display(mvoGoalController.getAll(), true);
		if (!resultaat.isEmpty()) {
			sdgController.addSubSdg((String) resultaat.get(0), (String) resultaat.get(1),
					(MvoGoalAbstract) resultaat.get(2), (SdgComp) sdgController.getSdg(id),
					Integer.valueOf((String) resultaat.get(3)));

		}
		maakGrid();
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

	private void listSdgButton(ActionEvent event) {
		hoofdPaneel.toonListSdgPaneel();
	}

	private void maakTableView(SdgAbstract sdg) {
		// getChildren().clear();
		tableView = new TableView<SdgAbstract>();
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TableColumn<SdgAbstract, Integer> column1 = new TableColumn<>("id");
		column1.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<SdgAbstract, String> column2 = new TableColumn<>("target");
		column1.setCellValueFactory(new PropertyValueFactory<>("target"));

		TableColumn<SdgAbstract, String> column3 = new TableColumn<>("name");
		column2.setCellValueFactory(new PropertyValueFactory<>("name"));

		tableView.getColumns().add(column1);
		tableView.getColumns().add(column2);
		tableView.getColumns().add(column3);
		if (!(sdg.isBlad())) {
			for (SdgAbstract mvoGoalChild : ((SdgComp) sdg).getSdgs()) {
				tableView.getItems().add(mvoGoalChild);
			}
		}
		add(tableView, 1, 5);

	}

}
