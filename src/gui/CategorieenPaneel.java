package gui;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class CategorieenPaneel extends GridPane {

	private final HoofdPaneel hoofdPaneel;
//	private final DomeinController domeinController;

	public CategorieenPaneel(HoofdPaneel hoofdPaneel/* , DomeinController domeinController */) {
		this.hoofdPaneel = hoofdPaneel;
		// this.domeinController=domeinController

		configureerGrid();
		update();
	}

	private void configureerGrid() {
		setPadding(new Insets(10));
		setHgap(10);
		setVgap(10);

		ColumnConstraints col1 = new ColumnConstraints();
		col1.setHalignment(HPos.RIGHT);

		ColumnConstraints col2 = new ColumnConstraints();
		col2.setHgrow(Priority.ALWAYS);

		getColumnConstraints().addAll(col1, col2);
	}

	private void update() {
		this.getChildren().clear();


		HBox categorieenBox = new HBox();
		categorieenBox.setSpacing(30);
		
		/*for (List<String> lijstCategorieen: domeinController.getCategorieeen()) {
			MenuItem verwijderCategorieMenuItem = new MenuItem("Verwijderen");
			MenuItem wijzigCategorieMenuItem = new MenuItem("Wijzigen");
			verwijderCategorieMenuItem.setOnAction(this::wijzigCategorie);
			wijzigCategorieMenuItem.setOnAction(this::verwijderCategorie);
			
			MenuButton categorieMenuButton = new MenuButton(lijstCategorieen.get(0),null,verwijderCategorieMenuItem,wijzigCategorieMenuItem);
			
			
		
			categorieenBox.getChildren().add(categorieMenuButton);
			
		}*/
		
		


		
//		deletecat.setOnAction(new EventHandler<ActionEvent>() {
//			
//			@Override
//			public void handle(ActionEvent arg0) {
//				System.out.println("deleted");
//		
//			}
//		});


		
		add(categorieenBox,0,2,1,1);
		
		
		
	}

	public void wijzigCategorie(ActionEvent event) {

	}

	public void verwijderCategorie(ActionEvent event) {

	}

}
