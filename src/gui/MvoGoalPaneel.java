
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
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Jurn
 *
 */
public class MvoGoalPaneel extends HBox {
	private final MvoGoalController mvoGoalController;
	private final HoofdPaneel hoofdPaneel;
	private DatasourceController datasourceController;
	private int id;
	private TableView<MvoGoalAbstract> tableView;

	public MvoGoalPaneel(HoofdPaneel hoofdPaneel, MvoGoalController mvoGoalController,
			DatasourceController datasourceController) {
		this.hoofdPaneel = hoofdPaneel;
		this.mvoGoalController = mvoGoalController;
		this.datasourceController = datasourceController;

		configureerGrid();
	}

	private void configureerGrid() {
		this.setSpacing(40);

	}

	public void voegComponentenToe(int id) {
		this.id = id;
		maakGrid();
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
		
		VBox vbox = new VBox();
		vbox.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		vbox.setPadding(new Insets(0, 0, 20, 0));
		vbox.setMaxHeight(Double.MAX_VALUE);
		vbox.setId("MvoGoalVbox_id");
		
		MvoGoalAbstract mvoGoal;
		if (mvoGoalController.getMvoGoal(this.id).isBlad()) {
			mvoGoal = (MvoGoalChild) mvoGoalController.getMvoGoal(this.id);
		} else {
			mvoGoal = (MvoGoalComp) mvoGoalController.getMvoGoal(this.id);
		}
		
		if (!(mvoGoal.isBlad())) {
			Button createButton = new Button("Nieuwe Sub-MVO maken");
			createButton.setOnAction(this::createButton);
			vbox.getChildren().add(createButton);
			createButton.setId("createMVOGoalbtn_id");
		}
		
		Button editButton = new Button("MVO wijzigen");
		editButton.setOnAction(this::editButton);
		vbox.getChildren().add(editButton);
		editButton.setId("editMVOGoalbtn_id");

		if (!(mvoGoal.isBlad())) {
			Button deleteButtonAction = new Button("Sub-MVO verwijderen");
			deleteButtonAction.setOnAction(this::deleteButtonAction);
			vbox.getChildren().add(deleteButtonAction);
			deleteButtonAction.setId("deleteMVOGoalbtn_id");
		}

		Button listMvoGoalButton = new Button("MVO doelstelling lijst");
		listMvoGoalButton.setOnAction(this::listMvoGoalButton);
		vbox.getChildren().add(listMvoGoalButton);
		listMvoGoalButton.setId("listMVOGoalbtn_id");

		
		Button datasourceButton = new Button("Bekijk datasource");
		datasourceButton.setOnAction( this::datasourceButtonAction );
		vbox.getChildren().add(datasourceButton);
		datasourceButton.setId("datasourceMVOGoalbtn_id");
		
		VBox newVbox = new VBox();
		newVbox.setAlignment(Pos.TOP_CENTER);
		newVbox.setPadding(new Insets(0,0,20,0));

		Label title = new Label("MVO Doelstelling");
		title.setFont(new Font("Arial", 30));
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
		grid.add(new Label(Integer.toString(mvoGoal.getId())), 3, 1);

		if (mvoGoal.isBlad()) {
			Label name = new Label("Mvo name:");
			name.setFont(font);
			grid.add(name, 1, 2);
			grid.add(new Label(((MvoGoalChild) mvoGoal).getMvoName()), 3, 2);
		} else {
			Label name = new Label("Name:");
			name.setFont(font);
			grid.add(name, 1, 2);
			grid.add(new Label(((MvoGoalComp) mvoGoal).getName()), 3, 2);
		}

		Label SDGs = new Label("SubMvo Doelstelligen:");
		SDGs.setFont(font);
		grid.add(SDGs, 1, 4, 3, 1);
		grid.add(maakTableView(mvoGoal), 1, 5, 3, 1); 
		
		newVbox.getChildren().add(grid);
		
		this.getChildren().addAll(vbox, newVbox);
	}
	private void datasourceButtonAction(ActionEvent event) {
		try {
			
			MvoGoalChild mvogoal = (MvoGoalChild) mvoGoalController.getMvoGoal(id);
			
			hoofdPaneel.toonDatasource(mvogoal,mvogoal.getDatasource(), id);
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			
			alert.setTitle("Waarschuwing!");
			alert.setHeaderText("Deze MvoGoal bevat geen datasource");
			
			alert.show();
		}
		
	}

	private TableView<MvoGoalAbstract> maakTableView(MvoGoalAbstract mvoGoal) {
		// getChildren().clear();
		tableView = new TableView<MvoGoalAbstract>();
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableView.setPrefWidth(400);
		tableView.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		TableColumn<MvoGoalAbstract, String> column1 = new TableColumn<>("id");
		column1.setCellValueFactory(new PropertyValueFactory<>("id"));
		column1.setMinWidth(30);
		column1.setMaxWidth(30);

		TableColumn<MvoGoalAbstract, String> column2 = new TableColumn<>("value");
		column2.setCellValueFactory(new PropertyValueFactory<>("value"));

		TableColumn<MvoGoalAbstract, String> column3 = new TableColumn<>("mvoName");
		column3.setCellValueFactory(new PropertyValueFactory<>("mvoName"));

		tableView.getColumns().add(column1);
		tableView.getColumns().add(column2);
		tableView.getColumns().add(column3);

		if (!(mvoGoal.isBlad())) {
			for (MvoGoalAbstract mvoGoalChild : ((MvoGoalComp) mvoGoal).getSdgs()) {
				tableView.getItems().add(mvoGoalChild);
			}
		}
		
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

		return tableView;
//		add(tableView, 1, 5);

	}

}
