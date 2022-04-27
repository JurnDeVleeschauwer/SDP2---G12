package gui;

import java.util.List;
import java.util.Optional;

import domain.CategoryController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CategorieenHBox extends HBox{

	private final CategoryController categoryController;
	private final HoofdPaneel hoofdPaneel;
	
	public CategorieenHBox(CategoryController categoryController, HoofdPaneel hoofdPaneel) {
		
		this.categoryController=categoryController;
		this.hoofdPaneel=hoofdPaneel;
		this.setSpacing(40);
		update();
		
	}
	
	public void update() {
		this.getChildren().clear();

		for (List<String> lijstCategorieen: categoryController.getCategorieen()) {
			
			VBox categorieVBox = new VBox();
			categorieVBox.setBorder( new Border(new BorderStroke(Color.BLACK, 
		            BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)) );
		    categorieVBox.setPadding(new Insets(5, 10, 20, 10));
			Label categorieLabel = new Label(lijstCategorieen.get(0));// naam op 0e index van lijst
			Button verwijderCategorieButton = new Button("Verwijder categorie");
			Button wijzigCategorieButton = new Button("Wijzig categorie");
			
			verwijderCategorieButton.setOnAction(this::verwijderCategorie);
			wijzigCategorieButton.setOnAction(e->{
				List<String> resultaat=CategorieWijzigenPopup.display(lijstCategorieen.get(0),lijstCategorieen.get(1));
				if(resultaat!=null) {
					
					categoryController.updateCategory( this.getChildren().indexOf(((Node) e.getSource()).getParent()) , resultaat);
					update();
				}
				
			});
			
			categorieVBox.getChildren().addAll(categorieLabel,verwijderCategorieButton,wijzigCategorieButton);
			this.getChildren().add(categorieVBox);
			
		}
		
		Button categorieAanmakenButton = new Button("Nieuwe categorie maken");
		
		categorieAanmakenButton.setOnAction(e->{
			List<String> resultaat=CategorieAanmakenPopup.display();
			if(!resultaat.isEmpty()) {
				categoryController.addCategory(resultaat.get(0),resultaat.get(1),true);
				update();
			}
		});
		
		this.getChildren().add(categorieAanmakenButton);
	}



		public void verwijderCategorie(ActionEvent event) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmeer verwijdering");
			alert.setHeaderText("Bent u zeker dat u deze categorie wilt verwijderen?");
			alert.setGraphic(null);
			
			Optional<ButtonType> result = alert.showAndWait();
			if(result.isPresent() && result.get()==ButtonType.OK) {
				categoryController.deleteCategory(this.getChildren().indexOf(((Node) event.getSource()).getParent()));			// this.getChildren().indexOf(((Node) event.getSource()).getParent() geeft index van de categorie waarin de button geklikt werd
				update();
			}


		}
	
	
}
