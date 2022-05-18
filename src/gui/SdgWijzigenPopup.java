package gui;

import java.util.ArrayList;
import java.util.List;

import domain.SdgAbstract;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SdgWijzigenPopup {
	
	static List<Object> sdg;
	static SdgAbstract sdgUpdate;
	
	
	public static List<Object> display(SdgAbstract sdgToUpdate){
		sdgUpdate = sdgToUpdate;
		sdg=new ArrayList<>();
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Sdg wijzigen");
		window.setMinWidth(350);
		window.setMinHeight(300);
		
		Label labelNaam = new Label("Naam:");
		TextField textFieldNaam = new TextField();
		//textFieldNaam.setText(sdgUpdate.getName());
		
		Label labelCategorie = new Label("Discription:");
		TextField textFieldCategorie = new TextField();
		//textFieldCategorie.setText(sdgUpdate.get);
		
		Button wijzigenButton = new Button("Wijzigen");
		wijzigenButton.setOnAction(e->{
			sdg.add(textFieldNaam.getText());
			sdg.add(textFieldCategorie.getText());
			

			window.close();
		});
		wijzigenButton.setDefaultButton(true);
		
		Button annulerenButton = new Button("Annuleren");
		annulerenButton.setOnAction(e->{
			
			window.close();
		});
		
		HBox hboxButtons = new HBox(50);
	
		hboxButtons.getChildren().addAll(wijzigenButton,annulerenButton);
		hboxButtons.setAlignment(Pos.CENTER);
		VBox layout = new VBox(10);
		layout.getChildren().addAll(labelNaam,textFieldNaam,labelCategorie,textFieldCategorie,hboxButtons);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
		
		if(sdg.isEmpty()) {
			System.out.println(sdg);
			return null;
		}else {
			
			return sdg;
		}
		
		
	}

}
