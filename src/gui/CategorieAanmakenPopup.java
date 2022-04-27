package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CategorieAanmakenPopup {
	
	static List<String> categorie;
	
	public static List<String> display(){
		categorie=new ArrayList<>();
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Categorie maken");
		window.setMinWidth(350);
		window.setMinHeight(300);
		
		Label labelNaam = new Label("Naam:");
		TextField textFieldNaam = new TextField();
		textFieldNaam.setPromptText("Categorie naam");
		
		Label labelFoto = new Label("Foto:");
		TextField textFieldFoto = new TextField();
		textFieldFoto.setPromptText("Foto naam");
		
		Button aanmakenButton = new Button("Aanmaken");
		aanmakenButton.setOnAction(e->{
			categorie.add(textFieldNaam.getText());
			categorie.add(textFieldFoto.getText());
			window.close();
		});
		aanmakenButton.setDefaultButton(true);
		
		Button annulerenButton = new Button("Annuleren");
		annulerenButton.setOnAction(e->{
			
			window.close();
		});
		
		HBox hboxButtons = new HBox(50);
	
		hboxButtons.getChildren().addAll(aanmakenButton,annulerenButton);
		hboxButtons.setAlignment(Pos.CENTER);
		VBox layout = new VBox(10);
		layout.getChildren().addAll(labelNaam,textFieldNaam,labelFoto,textFieldFoto,hboxButtons);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
		
		
		return categorie;
		
		
	}

}
