package gui;

import domain.CategoryController;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class CategorieenPaneel extends GridPane {

	private final HoofdPaneel hoofdPaneel;
	private final CategoryController categoryController;

	public CategorieenPaneel(HoofdPaneel hoofdPaneel , CategoryController categoryController ) {
		this.hoofdPaneel = hoofdPaneel;
		 this.categoryController=categoryController;

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


		
		/*for (List<String> lijstCategorieen: categoryController.getCategorieeen()) {
			MenuItem verwijderCategorieMenuItem = new MenuItem("Verwijderen");
			MenuItem wijzigCategorieMenuItem = new MenuItem("Wijzigen");
			verwijderCategorieMenuItem.setOnAction(this::verwijderCategorie);
			wijzigCategorieMenuItem.setOnAction(this::wijzigCategorie);
			
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

//
//		MenuItem verwijderCategorieMenuItem = new MenuItem("Verwijderen");
//		MenuItem wijzigCategorieMenuItem = new MenuItem("Wijzigen");
//		verwijderCategorieMenuItem.setOnAction(this::verwijderCategorie);
//		wijzigCategorieMenuItem.setOnAction(this::wijzigCategorie);
//		
//		MenuItem verwijderCategorieMenuItem2 = new MenuItem("Verwijderen");
//		MenuItem wijzigCategorieMenuItem2 = new MenuItem("Wijzigen");
//		verwijderCategorieMenuItem2.setOnAction(this::verwijderCategorie);
//		wijzigCategorieMenuItem2.setOnAction(this::wijzigCategorie);
//
//		
//		MenuButton categorieMenuButton = new MenuButton("testje",null,verwijderCategorieMenuItem,wijzigCategorieMenuItem);
//
//		MenuButton categorieMenuButton2 = new MenuButton("testje2",null,verwijderCategorieMenuItem2,wijzigCategorieMenuItem2);
//		categorieenBox.getChildren().add(categorieMenuButton2);
//
//		categorieenBox.getChildren().add(categorieMenuButton);
//
//		VBox vboxtest = new VBox();
//		Label testlabel=new Label("test");
//		Button testbutton = new Button("click");
//		testbutton.setOnAction((event)->{
//			System.out.println( ((Node) event.getSource()).getParent().getParent().getChildrenUnmodifiable().indexOf(((Node) event.getSource()).getParent()) );
//		});
//		vboxtest.getChildren().addAll(testlabel,testbutton);
//		categorieenBox.getChildren().add(vboxtest);
//		
//		
//
//		
		HBox categorieenHBox = new CategorieenHBox(categoryController, hoofdPaneel);
		
		
		add(categorieenHBox,0,2,1,1);
		
		
		
	}

	public void wijzigCategorie(ActionEvent event) {
		System.out.println(this.getChildren().indexOf(event.getSource()));
		System.out.println(event.getSource());
		MenuItem item = (MenuItem) event.getSource();
		System.out.println(item);
		
		
	}

	public void verwijderCategorie(ActionEvent event) {
		System.out.println(this.getChildren().indexOf(event.getSource()));
		System.out.println(event.getSource());

	}

}
