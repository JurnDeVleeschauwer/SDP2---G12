package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CategorieAanmakenPopup {
	
	static List<String> categorie;
	
	public static List<String> display(){
		categorie=new ArrayList<>();
		
		Stage window = new Stage(StageStyle.UTILITY);
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Categorie maken");
		window.setMinWidth(350);
		window.setMinHeight(300);
		Label labeltitel = new Label("Nieuwe Categorie maken");
		labeltitel.setStyle("-fx-text-fill: #B2D235; -fx-font: normal bold 25px 'system'");
		labeltitel.setPadding(new Insets(0, 0, 0, 0));
		Label labelNaam = new Label("Naam:");
		labelNaam.setStyle("-fx-font: normal 18px 'system'");
		TextField textFieldNaam = new TextField();
		textFieldNaam.setPromptText("Categorie naam");
		textFieldNaam.setStyle("-fx-font: normal 18px 'system'");
		
		Label labelFoto = new Label("Foto:");
		labelFoto.setStyle("-fx-font: normal 18px 'system'");
		TextField textFieldFoto = new TextField();
		textFieldFoto.setPromptText("Foto naam");
		textFieldFoto.setStyle("-fx-font: normal 18px 'system'");
		
		Button aanmakenButton = new Button("Aanmaken");
		aanmakenButton.setOnAction(e->{
			categorie.add(textFieldNaam.getText());
			categorie.add(textFieldFoto.getText());
			window.close();
		});
		aanmakenButton.setDefaultButton(true);
		String style = "-fx-background-color: #004C6A; -fx-text-fill: #B2D235; -fx-border-color: black; -fx-border-width: 1;-fx-font: normal 18px 'system'; -fx-pref-width: 200px;";
		String hoverstyle = "-fx-background-color: #B2D235; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 1;-fx-font: normal 18px 'system'; -fx-pref-width: 200px;";
		aanmakenButton.setStyle(style);
		aanmakenButton.setOnMouseEntered(e -> aanmakenButton.setStyle(hoverstyle));
		aanmakenButton.setOnMouseExited(e -> aanmakenButton.setStyle(style));
		
		Button annulerenButton = new Button("Annuleren");
		annulerenButton.setOnAction(e->{
			
			window.close();
		});
		annulerenButton.setStyle(style);
		annulerenButton.setOnMouseEntered(e -> annulerenButton.setStyle(hoverstyle));
		annulerenButton.setOnMouseExited(e -> annulerenButton.setStyle(style));
		
		HBox hboxButtons = new HBox(50);
		hboxButtons.getChildren().addAll(aanmakenButton,annulerenButton);
		hboxButtons.setAlignment(Pos.CENTER);
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.getChildren().addAll(labeltitel,labelNaam,textFieldNaam,labelFoto,textFieldFoto,hboxButtons);
		layout.setAlignment(Pos.TOP_LEFT);
		Scene scene = new Scene(layout, 450, 300);
		window.setScene(scene);
		window.showAndWait();
		
		
		
		return categorie;
		
		
	}

}
