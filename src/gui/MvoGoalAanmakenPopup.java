
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
	static Label foutbericht = new Label();

	public static List<Object> display(List<Datasource> datasourceList, boolean child) {
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

		Label labelFoto = new Label("Foto:");
		TextField textFieldFoto = new TextField();

		Label labelValue = new Label("Value:");
		TextField textFieldValue = new TextField();

		Label labelMvoNaam = new Label("MvoName:");
		TextField textFieldMvoNaam = new TextField();

		ChoiceBox<Datasource> cbDatasources = new ChoiceBox<>();
		ObservableList<Datasource> datasourcesList = FXCollections.observableArrayList();

		datasourceList.stream().forEach(a -> datasourcesList.add(a));

		cbDatasources.setItems(datasourcesList);

		Button aanmakenButton = new Button("Aanmaken");
		aanmakenButton.setOnAction(e -> {
			boolean finish = true;
			foutbericht.setText("");
			if (child) {
				if (textFieldValue.getText().trim().isEmpty()) {
					foutbericht.setText(foutbericht.getText() + "Value moet ingvult zijn\n");
					finish = false;
				} else {
					try {
						Integer.parseInt(textFieldValue.getText());
					} catch (NumberFormatException ex) {
						foutbericht.setText(foutbericht.getText() + "Value moet een nummer zijn\n");
						finish = false;
					}
				}
				if (textFieldFoto.getText().trim().isEmpty()) {
					foutbericht.setText(foutbericht.getText() + "Foto moet ingvult zijn\n");
					finish = false;
				}
				if (textFieldMvoNaam.getText().trim().isEmpty()) {
					foutbericht.setText(foutbericht.getText() + "MvoNaam moet ingvult zijn\n");
					finish = false;
				}
				if(cbDatasources.getSelectionModel().getSelectedItem() == null) {
					foutbericht.setText(foutbericht.getText() + "DataSource moet gekozen zijn\n");
					finish = false;
				}
			} else {
				if (textFieldNaam.getText().trim().isEmpty()) {
					foutbericht.setText(foutbericht.getText() + "Naam moet ingvult zijn\n");
					finish = false;
				}
			}
			if (finish) {
				if (child) {
					mvo.add(textFieldValue.getText());
					mvo.add(cbDatasources.getSelectionModel().getSelectedItem());
					mvo.add(textFieldFoto.getText());
					mvo.add(textFieldMvoNaam.getText());
				} else {
					mvo.add(textFieldNaam.getText());
				}
				foutbericht.setText(null);
				window.close();
			}
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
		if (child) {
			layout.getChildren().addAll(labeltitel, labelFoto, textFieldFoto, labelMvoNaam,
					textFieldMvoNaam, labelValue, textFieldValue, cbDatasources, hboxButtons, foutbericht);
		} else {
			layout.getChildren().addAll(labeltitel, labelNaam, textFieldNaam, hboxButtons, foutbericht);
		}
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();

		return mvo;

	}

}
