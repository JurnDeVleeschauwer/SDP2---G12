package gui;

import java.util.List;
import java.util.Optional;

import domain.Category;
import domain.CategoryController;
import domain.SdgAbstract;
import domain.SdgComp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.skin.TableHeaderRow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderImage;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CategorieenHBox extends HBox {

	private final CategoryController categoryController;
	private final HoofdPaneel hoofdPaneel;
	private TableView<Category> tableView;
	private ListView<String> listview;

	public CategorieenHBox(CategoryController categoryController, HoofdPaneel hoofdPaneel) {
		this.getStylesheets().add(getClass().getResource("css.css").toExternalForm());
		this.setFillHeight(true);
//		this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		this.categoryController = categoryController;
		this.hoofdPaneel = hoofdPaneel;
		this.setSpacing(40);
		update();

	}

	public void update() {
		this.getChildren().clear();

		VBox categorieVBox = new VBox();
		categorieVBox.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		categorieVBox.setPadding(new Insets(0, 0, 20, 0));
		categorieVBox.setMaxHeight(Double.MAX_VALUE);
		categorieVBox.setId("categorieVBox_id");
		Button verwijderCategorieButton = new Button("Verwijder categorie");
		verwijderCategorieButton.setId("verwijdercatbtn_id");

		verwijderCategorieButton.setOnAction(this::verwijderCategorie);
		Button categorieAanmakenButton = new Button("Nieuwe categorie maken");
		categorieAanmakenButton.setId("aanmakencatbtn_id");

		categorieVBox.getChildren().addAll(categorieAanmakenButton, verwijderCategorieButton);
		this.getChildren().add(categorieVBox);

		categorieAanmakenButton.setOnAction(this::categorieAanmaken);
		
		HBox hbox = new HBox();
		hbox.setSpacing(50);

		VBox vbox1 = new VBox();
		vbox1.getChildren().addAll(new Label("Categorieëen"),maakTableView(categoryController));
		
		
		VBox vbox2 = new VBox();
		vbox2.getChildren().addAll(new Label("Sdg's"),maakListView());
		
		
		hbox.getChildren().addAll(vbox1, vbox2);
		
		
		hbox.setPadding(new Insets(20,0,20,0));
		
		this.getChildren().add(hbox);  
	
		
	}

	public void verwijderCategorie(ActionEvent event) {
		System.out.println(categoryController.getCategory(tableView.getSelectionModel().getSelectedItem().getId()).getName());
		System.out.println(categoryController.heeftSdgs(tableView.getSelectionModel().getSelectedItem().getId()));

		if (categoryController.heeftSdgs(tableView.getSelectionModel().getSelectedItem().getId())) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Waarschuwing!");
			alert.setHeaderText("Deze categorie bevat nog Sdgs, gelieve deze eerst aan te passen.");
			
			alert.show();
		} else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmeer verwijdering");
			alert.setHeaderText("Bent u zeker dat u deze categorie wilt verwijderen?");
			alert.setGraphic(null);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				categoryController.deleteCategory(tableView.getSelectionModel().getSelectedItem().getId());

				tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItem());
			}

		}

	}

	public void categorieAanmaken(ActionEvent event) {
		List<String> resultaat = CategorieAanmakenPopup.display();
		if (!resultaat.isEmpty()) {

			tableView.getItems().add(categoryController.addCategory(resultaat.get(0), resultaat.get(1)));
		}

	}



	private TableView<Category> maakTableView(CategoryController categoryController) {
		
		tableView = new TableView<Category>();
		tableView.setId("tableviewcat_id");
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableView.setEditable(true);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableView.setPrefWidth(400);
		tableView.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		
		TableColumn<Category, String> columnId = new TableColumn<>("Id");
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnId.setEditable(false);
		columnId.setMinWidth(30);
		columnId.setMaxWidth(30);

		TableColumn<Category, String> columnNaam = new TableColumn<>("Naam");
		columnNaam.setCellValueFactory(new PropertyValueFactory<>("name"));
		columnNaam.setCellFactory(TextFieldTableCell.forTableColumn());
		columnNaam.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Category, String>>() {

			@Override
			public void handle(CellEditEvent<Category, String> event) {

				Category category = event.getRowValue();
				categoryController.updateCategoryName(category, event.getNewValue());
				

			}
		});

		TableColumn<Category, String> columnIcoon = new TableColumn<>("Icoon");
		columnIcoon.setCellValueFactory(new PropertyValueFactory<>("icon"));
		columnIcoon.setCellFactory(TextFieldTableCell.forTableColumn());
		columnIcoon.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Category, String>>() {

			@Override
			public void handle(CellEditEvent<Category, String> event) {
				Category category = event.getRowValue();
				categoryController.updateCategoryIcoon(category, event.getNewValue());

			}
		});

		tableView.getColumns().add(columnId);
		tableView.getColumns().add(columnNaam);
		tableView.getColumns().add(columnIcoon);
		tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {

				updateListView(newSelection.getSdgAbstracts());
			}
		});
		for (Category category : categoryController.getAll()) {
			tableView.getItems().add(category);
		}
		
		return tableView;

	}

	private void updateListView(List<SdgAbstract> sdgAbstract) {
		listview.getItems().clear();
		for (SdgAbstract sdg : sdgAbstract) {

				
			listview.getItems().add(sdg.getName());
		}

	}

	private ListView<String> maakListView() {

		listview = new ListView<String>();
		
		listview.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		return listview;
		

	}

}
