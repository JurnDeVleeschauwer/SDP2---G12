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
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * 
 * @author Jurn
 *
 */
public class SdgPaneel extends HBox {
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
		this.setSpacing(20);
	}

	public void voegComponentenToe(int id) {
		this.id = id;
		maakGrid();
	}

	private void maakGrid() {
		getChildren().clear();
		SdgAbstract sdg;
		if (sdgController.getSdg(this.id).isBlad()) {
			sdg = (SdgChild) sdgController.getSdg(this.id);
		} else {
			sdg = (SdgComp) sdgController.getSdg(this.id);
		}
		
		VBox vbox = new VBox();
		vbox.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		vbox.setId("sdgpaneelvbox_id");
		
		if (!(sdg.isBlad())) {
			Button createButton = new Button("Nieuwe sub-SDG maken");
			createButton.setOnAction(this::createButton);
			createButton.setId("createsdgpaneelbtn_id");
			vbox.getChildren().add(createButton);
		}
		
		Button editButton = new Button("SDG wijzigen");
		editButton.setOnAction(this::editButton);
		editButton.setId("wijzigsdgpaneelbtn_id");
		vbox.getChildren().add(editButton);

		if (!(sdg.isBlad())) {
			Button deleteButtonAction = new Button("Sub-SDG Verwijderen");
			deleteButtonAction.setOnAction(this::deleteButtonAction);
			deleteButtonAction.setId("deletesdgpaneelbtn_id");
			vbox.getChildren().add(deleteButtonAction);
		}
		
		Button listSdgButton = new Button("Back naar Sdg lijst");
		listSdgButton.setOnAction(this::listSdgButton);
		listSdgButton.setId("retoursdgpaneelbtn_id");
		vbox.getChildren().add(listSdgButton);

		VBox newVbox = new VBox();
		newVbox.setAlignment(Pos.TOP_CENTER);
		newVbox.setPadding(new Insets(0,0,20,0));
		
		Label title = new Label("SDG");
		title.setId("titleSDG_id");
		newVbox.getChildren().add(title);
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.BOTTOM_LEFT);
		
		for(int i = 0; i < 3; i++) {
			ColumnConstraints col = new ColumnConstraints();
			col.setMinWidth(20);
			grid.getColumnConstraints().add(col);
		}
		
		for(int i = 0; i<5; i++) {
			RowConstraints row = new RowConstraints();
			row.setMinHeight(20);
			grid.getRowConstraints().add(row);
		}
		
		Font font = Font.font("Arial", FontWeight.BOLD, 15);
		
		Label id = new Label("ID:");
		id.setFont(font);
		grid.add(id, 1, 1);
		grid.add(new Label(Integer.toString(sdg.getId())), 3, 1);
		Label description = new Label("Description:");
		description.setFont(font);
		grid.add(description, 1, 2);
		grid.add(new Label(sdg.getDescription()), 3, 2);
		Label name = new Label("Name:");
		name.setFont(font);
		grid.add(name, 1, 3);
		grid.add(new Label(sdg.getName()), 3, 3);

		Label SDGs = new Label("Sub Sdgs:");
		SDGs.setFont(font);
		grid.add(SDGs, 1, 5, 3,1);
		

		grid.add(maakTableView(sdg), 1, 6, 3, 1);
		newVbox.getChildren().add(grid);
		
		this.getChildren().addAll(vbox, newVbox);
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

				sdgChild.setId(this.id + 1);
				sdgController.updateSdg(sdgChild);
			} else {
				SdgComp sdgComp = new SdgComp.Builder().description((String) resultaat.get(0))
						.name((String) resultaat.get(1)).sdgs(((SdgComp) sdg).getSdgs()).build();

				sdgComp.setId(this.id + 1);
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
		alert.setHeaderText("Bent u zeker dat u deze SDG wilt verwijderen?");
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

	private TableView<SdgAbstract> maakTableView(SdgAbstract sdg) {
		// getChildren().clear();
		tableView = new TableView<SdgAbstract>();
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableView.setPrefWidth(400);
		tableView.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		TableColumn<SdgAbstract, Integer> column1 = new TableColumn<>("id");
		column1.setCellValueFactory(new PropertyValueFactory<>("id"));
		column1.setMinWidth(30);
		column1.setMaxWidth(30);

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
		
		return tableView;
//		add(tableView, 1, 5);

	}

}
