package gui;

import java.util.List;
import java.util.Optional;

import domain.DomeinController;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CategorieenHBox extends HBox{

	private final DomeinController domeinController;
	private final HoofdPaneel hoofdPaneel;
	
	public CategorieenHBox(DomeinController domeinController, HoofdPaneel hoofdPaneel) {
		
		this.domeinController=domeinController;
		this.hoofdPaneel=hoofdPaneel;
		update();
		
	}
	
	public void update() {
		this.getChildren().clear();

		for (List<String> lijstCategorieen: domeinController.getCategorieen()) {
		
			VBox categorieVBox = new VBox();
			Label categorieLabel = new Label(lijstCategorieen.get(0));// naam op 0e index van lijst
			Button verwijderCategorieButton = new Button("Verwijder categorie");
			Button wijzigCategorieButton = new Button("Wijzig categorie");
			
			verwijderCategorieButton.setOnAction(this::verwijderCategorie);
			wijzigCategorieButton.setOnAction(this::wijzigCategorie);
			
			categorieVBox.getChildren().addAll(categorieLabel,verwijderCategorieButton,wijzigCategorieButton);
			this.getChildren().add(categorieVBox);
			
		}
		
		
		
	}

		public void wijzigCategorie(ActionEvent event) {

			domeinController.wijzigCategorie(this.getChildren().indexOf(((Node) event.getSource()).getParent()));			// this.getChildren().indexOf(((Node) event.getSource()).getParent() geeft index van de categorie waarin de button geklikt werd

			
			
		}

		public void verwijderCategorie(ActionEvent event) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmeer verwijdering");
			alert.setHeaderText("Bent u zeker dat u deze categorie wilt verwijderen?");
			alert.setGraphic(null);
			
			Optional<ButtonType> result = alert.showAndWait();
			if(result.isPresent() && result.get()==ButtonType.OK) {
				domeinController.verwijderCategorie(this.getChildren().indexOf(((Node) event.getSource()).getParent()));			// this.getChildren().indexOf(((Node) event.getSource()).getParent() geeft index van de categorie waarin de button geklikt werd

			}


		}
	
	
}
