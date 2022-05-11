
	package gui;

	import java.util.ArrayList;
	import java.util.List;

	import javafx.geometry.Insets;
	import javafx.geometry.Pos;
	import javafx.scene.Scene;
	import javafx.scene.control.Button;
	import javafx.scene.control.Label;
	import javafx.scene.control.TextField;
	import javafx.scene.layout.HBox;
	import javafx.scene.layout.VBox;
	import javafx.stage.Modality;
	import javafx.stage.Stage;

	public class SdgAanmakenPopup {
		
		static List<Object> sdg;
		
		public static List<Object> display(){
			sdg=new ArrayList<>();
			
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Sdg maken");
			window.setMinWidth(350);
			window.setMinHeight(300);
			Label labeltitel = new Label("Nieuwe Sdg maken");
			labeltitel.setPadding(new Insets(0, 0, 25, 0));
			Label labelNaam = new Label("Naam:");
			TextField textFieldNaam = new TextField();
			textFieldNaam.setPromptText("Sdg naam");
			
			Label labelDescription= new Label("Description:");
			TextField textFieldDescription= new TextField();
			textFieldDescription.setPromptText("Sdg description");
			
			Button aanmakenButton = new Button("Aanmaken");
			aanmakenButton.setOnAction(e->{
				sdg.add(textFieldNaam.getText());
				sdg.add(textFieldDescription.getText());
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
			layout.getChildren().addAll(labeltitel,labelNaam,textFieldNaam,labelDescription,textFieldDescription,hboxButtons);
			layout.setAlignment(Pos.CENTER);
			Scene scene = new Scene(layout);
			window.setScene(scene);
			window.showAndWait();
			
			
			
			return sdg;
			
			
		}

	}




