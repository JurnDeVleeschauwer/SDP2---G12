package gui;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class CategorieenPaneel extends GridPane {

	private final HoofdPaneel hoofdPaneel;

	public CategorieenPaneel(HoofdPaneel hoofdPaneel) {
		this.hoofdPaneel = hoofdPaneel;
		// Domeincontroller TODO

		configureerGrid();
		voegComponentenToe();
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

	Button categorieWijzigenButton = new Button("Categorie wijzigen");
	Button categorieVerwijderenButton = new Button("Categorie verwijderen");

	private void voegComponentenToe() {

		categorieWijzigenButton.setOnAction(this::wijzigCategorie);
		categorieVerwijderenButton.setOnAction(this::verwijderCategorie);
		add(categorieWijzigenButton, 0, 2, 1, 1);
		add(categorieVerwijderenButton, 1, 2, 1, 1);
	}

	public void wijzigCategorie(ActionEvent event) {

	}

	public void verwijderCategorie(ActionEvent event) {

	}
	
	
}
