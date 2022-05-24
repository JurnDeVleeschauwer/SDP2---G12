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

public class ListSdgPaneel extends HBox {
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
		vbox.setId("sdglistVbox_id");
		
		Button createButtonAction = new Button("Nieuwe SDG aanmaken");
		createButtonAction.setOnAction(this::createButtonAction);
		createButtonAction.setId("createsdgbtn_id");
		
		Button deleteButtonAction = new Button("Verwijder SDG");
		deleteButtonAction.setOnAction(this::deleteButtonAction);
		deleteButtonAction.setId("deletesdgbtn_id");
		
		vbox.getChildren().addAll(createButtonAction, deleteButtonAction);
		
		VBox newVbox = new VBox();
		newVbox.setPadding(new Insets(20,0,20,0));
		
		tableView = new TableView<SdgAbstract>();
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableView.setPrefWidth(400);
		tableView.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		
		TableColumn<SdgAbstract, String> column1 = new TableColumn<>("id");
		column1.setCellValueFactory(new PropertyValueFactory<>("id"));
		column1.setMinWidth(30);
		column1.setMaxWidth(30);
		
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
			// System.out.println(sdgChild);
			tableView.getItems().add((SdgAbstract) sdgChild);
		}
		newVbox.getChildren().addAll(new Label("SDG's"), tableView);
		
		this.getChildren().addAll(vbox, newVbox);

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

		List<Object> resultaat = SdgAanmakenPopup.display(mvoGoalController.getAll(), false);
		if (!resultaat.isEmpty()) {
			sdgController.addSdg((String) resultaat.get(0), (String) resultaat.get(1));

		}
		maakGrid();

	}
}
