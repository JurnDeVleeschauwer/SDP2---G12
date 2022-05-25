package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
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
		
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPrefWidth(150);
		
		String foutstyle = "-fx-font: normal 18px 'system'; -fx-text-fill: #FF0000; -fx-max-width: 300px; -fx-wrap-text: true";
		
		Stage window = new Stage(StageStyle.UTILITY);
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Categorie maken");
		
		Label labeltitel = new Label("Nieuwe Categorie maken");
		labeltitel.setStyle("-fx-text-fill: #B2D235; -fx-font: normal bold 25px 'system'");
		
		Label labelNaam = new Label("Naam:");
		labelNaam.setStyle("-fx-font: normal 18px 'system'");
		Label lblNaamFout = new Label();
		lblNaamFout.setStyle(foutstyle);
		GridPane gridnaam = new GridPane();
		gridnaam.getColumnConstraints().add(col1);
		gridnaam.add(labelNaam, 0, 0);
		gridnaam.add(lblNaamFout, 1, 0);
		TextField textFieldNaam = new TextField();
		textFieldNaam.setPromptText("Categorie naam");
		textFieldNaam.setStyle("-fx-font: normal 18px 'system'");
		
		Label labelFoto = new Label("Foto:");
		labelFoto.setStyle("-fx-font: normal 18px 'system'");
		Label lblFotoFout = new Label();
		lblFotoFout.setStyle(foutstyle);
		GridPane gridfoto = new GridPane();
		gridfoto.getColumnConstraints().add(col1);
		gridfoto.add(labelFoto, 0, 0);
		gridfoto.add(lblFotoFout, 1, 0);
		TextField textFieldFoto = new TextField();
		textFieldFoto.setPromptText("Naam van de foto");
		textFieldFoto.setStyle("-fx-font: normal 18px 'system'");
		
		Button aanmakenButton = new Button("Aanmaken");
		aanmakenButton.setOnAction(e->{
			boolean finish = true;
			if (textFieldFoto.getText().trim().isEmpty()) {
				lblFotoFout.setText("Foto moet ingvult zijn\n");
				finish = false;
			}
			if (textFieldNaam.getText().trim().isEmpty()) {
				lblNaamFout.setText("Naam moet ingevuld zijn");
			}

			if (finish) {
				categorie.add(textFieldNaam.getText());
				categorie.add(textFieldFoto.getText());
				window.close();
			}
			
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
		layout.getChildren().addAll(labeltitel,gridnaam,textFieldNaam,gridfoto,textFieldFoto,hboxButtons);
		layout.setAlignment(Pos.TOP_LEFT);
		Scene scene = new Scene(layout, 450, 300);
		window.setScene(scene);
		window.showAndWait();
		
		
		
		return categorie;
		
		
	}

}
