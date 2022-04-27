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


		HBox categorieenHBox = new CategorieenHBox(categoryController, hoofdPaneel);
		
		
		add(categorieenHBox,0,2,1,1);
		
		
		
	}




}
