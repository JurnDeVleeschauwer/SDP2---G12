
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
					mvoGoalController.getMvoGoal(tableView.getSelectionModel().getSelectedItem().getId() - 1));

			tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItem());

		}

	}

	private void editButton(ActionEvent event) {

		List<Object> resultaat = MvoGoalWijzigenPopup.display(mvoGoalController.getMvoGoal(this.id));
		if (resultaat != null) {
			MvoGoalAbstract mvoGoal = mvoGoalController.getMvoGoal(this.id);
			if (mvoGoal.isBlad()) {

				MvoGoalChild mvoGoalChild = new MvoGoalChild.Builder().value(Integer.valueOf((String) resultaat.get(0)))
						.datasource(((MvoGoalChild) mvoGoalController.getMvoGoal(this.id)).getDatasource())
						.icon((String) resultaat.get(1)).mvoName((String) resultaat.get(2))
						.counter(((MvoGoalChild) mvoGoalController.getMvoGoal(this.id)).getCounter()).build();

				mvoGoalChild.setId(this.id+1);
				mvoGoalController.updateMvoGoal(mvoGoalChild);
			} else {
				MvoGoalComp mvoGoalComp = new MvoGoalComp.Builder().name((String) resultaat.get(0))
						.mvoGoals(((MvoGoalComp) mvoGoalController.getMvoGoal(this.id)).getMvoGoals())
						.mvoGoalMapper(((MvoGoalComp) mvoGoalController.getMvoGoal(this.id)).getMvoGoalMapper())
						.build();
				;
				mvoGoalComp.setId(this.id+1);
				mvoGoalController.updateMvoGoal(mvoGoalComp);
			}
		}
		maakGrid();

	}

	private void createButton(ActionEvent event) {
		List<Object> resultaat = MvoGoalAanmakenPopup.display(datasourceController.getDatasources(), true);
		if (!resultaat.isEmpty()) {
			mvoGoalController.addSubMvoGoal(id, Integer.valueOf((String) resultaat.get(0)),
					(Datasource) resultaat.get(1), (String) resultaat.get(2), (String) resultaat.get(3));

		}
		maakGrid();
	}

	private void listMvoGoalButton(ActionEvent event) {
		hoofdPaneel.toonListMvoGoalPaneel();
	}

	private void maakGrid() {
		this.getChildren().clear();

		Label title = new Label("MVO Doelstelling");
		title.setFont(new Font("Arial", 30));
		add(title, 5, 0);
		MvoGoalAbstract mvoGoal;
		if (mvoGoalController.getMvoGoal(this.id).isBlad()) {
			mvoGoal = (MvoGoalChild) mvoGoalController.getMvoGoal(this.id);
		} else {
			mvoGoal = (MvoGoalComp) mvoGoalController.getMvoGoal(this.id);
		}

		Label id = new Label("ID:");
		id.setFont(new Font("Arial", 15));
		add(id, 1, 1);
		add(new Label(Integer.toString(mvoGoal.getId())), 2, 1);

		if (mvoGoal.isBlad()) {
			Label name = new Label("Mvo name:");
			name.setFont(new Font("Arial", 15));
			add(name, 1, 3);
			add(new Label(((MvoGoalChild) mvoGoal).getMvoName()), 2, 3);
		} else {
			Label name = new Label("Name:");
			name.setFont(new Font("Arial", 15));
			add(name, 1, 3);
			add(new Label(((MvoGoalComp) mvoGoal).getName()), 2, 3);
		}

		Label SDGs = new Label("SubMvo Doelstelligen:");
		SDGs.setFont(new Font("Arial", 15));
		add(SDGs, 1, 4);
		maakTableView(mvoGoal);

		Button editButton = new Button("Wijzigen");
		editButton.setOnAction(this::editButton);
		add(editButton, 7, 0);

		if (!(mvoGoal.isBlad())) {
			Button createButton = new Button("Aanmaken");
			createButton.setOnAction(this::createButton);
			add(createButton, 1, 12);

			Button deleteButtonAction = new Button("Verwijderen");
			deleteButtonAction.setOnAction(this::deleteButtonAction);
			add(deleteButtonAction, 1, 11);
		}

		Button listMvoGoalButton = new Button("Back naar Mvo Doesstellingen lijst");
		listMvoGoalButton.setOnAction(this::listMvoGoalButton);
		add(listMvoGoalButton, 1, 13);

	}

	private void maakTableView(MvoGoalAbstract mvoGoal) {
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

		if (!(mvoGoal.isBlad())) {
			for (MvoGoalChild mvoGoalChild : ((MvoGoalComp) mvoGoal).getSdgs()) {
				tableView.getItems().add(mvoGoalChild);
			}
		}

		add(tableView, 1, 5);

	}

}
