package gui;

import java.util.ArrayList;
import java.util.List;

import domain.MvoGoalAbstract;
import domain.MvoGoalChild;
import domain.MvoGoalController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListMvoGoalPaneel extends GridPane {
	private final MvoGoalController mvoGoalController;
	private final HoofdPaneel hoofdPaneel;
	private int id;
	private TableView<MvoGoalChild> tableView;

	public ListMvoGoalPaneel(HoofdPaneel hoofdPaneel, MvoGoalController mvoGoalController) {
		this.hoofdPaneel = hoofdPaneel;
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
		getChildren().clear();
		tableView = new TableView<MvoGoalChild>();

		TableColumn<MvoGoalChild, String> column1 = new TableColumn<>("id");
		column1.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<MvoGoalChild, String> column2 = new TableColumn<>("value");
		column2.setCellValueFactory(new PropertyValueFactory<>("value"));

		TableColumn<MvoGoalChild, String> column3 = new TableColumn<>("mvoName");
		column2.setCellValueFactory(new PropertyValueFactory<>("mvoName"));

		tableView.getColumns().add(column1);
		tableView.getColumns().add(column2);
		tableView.getColumns().add(column3);

		for (MvoGoalAbstract mvoGoalChild : mvoGoalController.getAll()) {
			tableView.getItems().add((MvoGoalChild) mvoGoalChild);
		}

		add(tableView, 2, 4);

		/*
		 * Button aanmakenButton = new Button("Toevoegen");
		 * aanmakenButton.setOnAction(e->{ mvo.add(); window.close(); });
		 * aanmakenButton.setDefaultButton(true);
		 * 
		 * Button annulerenButton = new Button("Annuleren");
		 * annulerenButton.setOnAction(e->{
		 * 
		 * });
		 */

	}
}
