
package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import domain.Category;
import domain.Datasource;
import domain.DatasourceController;
import domain.MvoGoalAbstract;
import domain.MvoGoalChild;
import domain.MvoGoalComp;
import domain.MvoGoalController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
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
public class MvoGoalPaneel extends GridPane {
	private final MvoGoalController mvoGoalController;
	private final HoofdPaneel hoofdPaneel;
	private DatasourceController datasourceController;
	private int id;
	private TableView<MvoGoalChild> tableView;

	public MvoGoalPaneel(HoofdPaneel hoofdPaneel, MvoGoalController mvoGoalController,
			DatasourceController datasourceController) {
		this.hoofdPaneel = hoofdPaneel;
		this.mvoGoalController = mvoGoalController;
		this.datasourceController = datasourceController;

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

	private void deleteButtonAction(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmeer verwijdering");
		alert.setHeaderText("Bent u zeker dat u deze categorie wilt verwijderen?");
		alert.setGraphic(null);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			mvoGoalController.deleteMvoGoal(
					mvoGoalController.getMvoGoal(tableView.getSelectionModel().getSelectedItem().getId()));

			tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItem());

		}

	}

	private void editButton(ActionEvent event) {

		List<Object> resultaat = MvoGoalWijzigenPopup.display(mvoGoalController.getMvoGoal(this.id));
		if (resultaat != null) {
			MvoGoalAbstract mvoGoal = mvoGoalController.getMvoGoal(this.id);
			if (mvoGoal.getClass() == MvoGoalChild.class) {
				/*mvoGoalController.updateMvoGoal(new MvoGoalChild(Integer.valueOf((String) resultaat.get(0)),
						((MvoGoalChild)mvoGoalController.getMvoGoal(this.id)).getDatasource(), (String) resultaat.get(1),
						(String) resultaat.get(2)));*/
			}else {
				//mvoGoalController.updateMvoGoal(new MvoGoalComp((String) resultaat.get(0)));
			}
		}

	}

	private void createButton(ActionEvent event) {
		List<Object> resultaat = MvoGoalAanmakenPopup.display(datasourceController.getDatasources());
		if (!resultaat.isEmpty()) {
			mvoGoalController.addMvoGoalChild(Integer.valueOf((String) resultaat.get(0)), (Datasource) resultaat.get(1),
					(String) resultaat.get(2), (String) resultaat.get(3));

		}
		;
	}

	private void maakGrid() {
		getChildren().clear();

		Label title = new Label("MVO Doelstelling");
		title.setFont(new Font("Arial", 30));
		add(title, 5, 0);

		MvoGoalComp mvoGoal = (MvoGoalComp) mvoGoalController.getMvoGoal(this.id);

		Label id = new Label("ID:");
		id.setFont(new Font("Arial", 15));
		add(id, 1, 1);
		add(new Label(Integer.toString(mvoGoal.getId())), 2, 1);
//			Label category = new Label("Category:");
//			category.setFont(new Font("Arial", 15));
//			add( category, 1, 2);
//			add( new Label(mvoGoal.getCategory().getName()), 2, 2);
		Label name = new Label("Name:");
		name.setFont(new Font("Arial", 15));
		add(name, 1, 3);
		add(new Label(mvoGoal.getName()), 2, 3);
		;
		Label SDGs = new Label("SubMvo Doelstelligen:");
		SDGs.setFont(new Font("Arial", 15));
		add(SDGs, 1, 4);
		maakTableView(mvoGoal);

		Button deleteButtonAction = new Button("Verwijderen");
		deleteButtonAction.setOnAction(this::deleteButtonAction);
		add(deleteButtonAction, 1, 11);

		Button editButton = new Button("Wijzigen");
		editButton.setOnAction(this::editButton);
		add(editButton, 7, 0);

		Button createButton = new Button("Aanmaken");
		createButton.setOnAction(this::createButton);
		add(createButton, 1, 12);

	}

	private void maakTableView(MvoGoalComp mvoGoal) {
		// getChildren().clear();
		tableView = new TableView<MvoGoalChild>();
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TableColumn<MvoGoalChild, String> column1 = new TableColumn<>("id");
		column1.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<MvoGoalChild, String> column2 = new TableColumn<>("value");
		column2.setCellValueFactory(new PropertyValueFactory<>("value"));

		TableColumn<MvoGoalChild, String> column3 = new TableColumn<>("mvoName");
		column3.setCellValueFactory(new PropertyValueFactory<>("mvoName"));

		tableView.getColumns().add(column1);
		tableView.getColumns().add(column2);
		tableView.getColumns().add(column3);

		for (MvoGoalChild mvoGoalChild : mvoGoal.getSdgs()) {
			tableView.getItems().add(mvoGoalChild);
		}

		add(tableView, 1, 5);

	}

}
