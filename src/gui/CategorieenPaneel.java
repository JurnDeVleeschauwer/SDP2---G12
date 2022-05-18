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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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

		ColumnConstraints col1 = new ColumnConstraints();
		col1.setHalignment(HPos.RIGHT);
		col1.setHgrow(Priority.ALWAYS);
		
		RowConstraints row1 = new RowConstraints();
	    row1.setPercentHeight(100);

		getColumnConstraints().addAll(col1);
		getRowConstraints().addAll(row1);
	}

	private void update() {
		this.getChildren().clear();


		HBox categorieenHBox = new CategorieenHBox(categoryController, hoofdPaneel);
		
		categorieenHBox.setFillHeight(true);
		add(categorieenHBox,0,0);
		
		
		
	}




}
