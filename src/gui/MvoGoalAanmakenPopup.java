
package gui;

import java.util.ArrayList;
import java.util.List;

import domain.Datasource;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MvoGoalAanmakenPopup {

	static List<Object> mvo;

	public static List<Object> display(List<Datasource> datasourceList) {
		mvo = new ArrayList<>();

		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Mvo Doelstelling maken");
		window.setMinWidth(350);
		window.setMinHeight(300);
		Label labeltitel = new Label("Nieuwe Mvo maken");
		labeltitel.setPadding(new Insets(0, 0, 25, 0));
		Label labelNaam = new Label("Naam:");
		TextField textFieldNaam = new TextField();
		textFieldNaam.setPromptText("Mvo naam");

		Label labelMvoNaam = new Label("Foto:");
		TextField textFieldMvoNaam = new TextField();
		textFieldMvoNaam.setPromptText("Foto naam");

		Label labelValue = new Label("Value:");
		TextField textFieldValue = new TextField();
		textFieldValue.setPromptText("Mvo waarde");

		ChoiceBox<Datasource> cbDatasources = new ChoiceBox<>();
		ObservableList<Datasource> datasourcesList = FXCollections.observableArrayList();

		datasourceList.stream().forEach(a -> datasourcesList.add(a));
		
		cbDatasources.setItems(datasourcesList);


		Button aanmakenButton = new Button("Aanmaken");
		aanmakenButton.setOnAction(e -> {
			mvo.add(textFieldValue.getText());
			mvo.add(cbDatasources.getSelectionModel().getSelectedItem());
			mvo.add(textFieldMvoNaam.getText());
			mvo.add(textFieldNaam.getText());
			window.close();
		});
		aanmakenButton.setDefaultButton(true);

		Button annulerenButton = new Button("Annuleren");
		annulerenButton.setOnAction(e -> {

			window.close();
		});

		HBox hboxButtons = new HBox(50);

		hboxButtons.getChildren().addAll(aanmakenButton, annulerenButton);
		hboxButtons.setAlignment(Pos.CENTER);
		VBox layout = new VBox(10);
		layout.getChildren().addAll(labeltitel, labelNaam, textFieldNaam, labelMvoNaam, textFieldMvoNaam, labelValue,
				textFieldValue, cbDatasources, hboxButtons);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();

		return mvo;

	}

}
