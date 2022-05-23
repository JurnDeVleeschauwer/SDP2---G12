package gui;

import java.util.ArrayList;
import java.util.List;

import domain.Datasource;
import domain.MvoGoalAbstract;
import domain.MvoGoalChild;
import domain.MvoGoalComp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MvoGoalWijzigenPopup {

	static List<Object> mvo;
	static MvoGoalAbstract mvoGoal;
	static Label foutbericht = new Label();

	public static List<Object> display(MvoGoalAbstract mvoGoalToUpdate) {
		mvoGoal = mvoGoalToUpdate;
		mvo = new ArrayList<>();

		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Mvo Doelstelling wijzigen");
		window.setMinWidth(350);
		window.setMinHeight(300);
		
		foutbericht.setStyle("-fx-font: normal 18px 'system'");
		foutbericht.setTextFill(Color.color(1, 0, 0));
		foutbericht.setMaxWidth(300.0);
		foutbericht.setWrapText(true);

		Label labelNaam = new Label("Naam:");
		TextField textFieldNaam = new TextField();

		Label labelMvoNaam = new Label("MvoNaam:");
		TextField textFieldMvoNaam = new TextField();

		Label labelFoto = new Label("Foto:");
		TextField textFieldFoto = new TextField();

		Label labelValue = new Label("Value:");
		TextField textFieldValue = new TextField();

		if (mvoGoalToUpdate.isBlad()) {
			textFieldValue.setPromptText(Integer.toString(((MvoGoalChild) mvoGoalToUpdate).getValue()));
			textFieldFoto.setPromptText(((MvoGoalChild) mvoGoalToUpdate).getIcon());
			textFieldMvoNaam.setPromptText(((MvoGoalChild) mvoGoalToUpdate).getMvoName());
		} else {
			textFieldNaam.setPromptText(((MvoGoalComp) mvoGoalToUpdate).getName());
		}

		Button wijzigenButton = new Button("Wijzigen");
		wijzigenButton.setOnAction(e -> {
			boolean finish = true;
			foutbericht.setText("");
			if (!(textFieldValue.getText().isEmpty())) {
				try {
					Integer.parseInt(textFieldValue.getText());
				} catch (NumberFormatException ex) {
					foutbericht.setText(foutbericht.getText() + "Value moet een nummer zijn\n");
					finish = false;
				}
			}

			if (finish) {
				if (mvoGoalToUpdate.isBlad()) {
					if (textFieldValue.getText().trim().isEmpty()) {
						mvo.add(String.valueOf(mvoGoalToUpdate.getValue()));
					} else {
						mvo.add(textFieldValue.getText());
					}
					if (textFieldFoto.getText().trim().isEmpty()) {
						mvo.add(((MvoGoalChild) mvoGoalToUpdate).getIcon());
					} else {
						mvo.add(textFieldFoto.getText());
					}
					if (textFieldMvoNaam.getText().trim().isEmpty()) {
						mvo.add(((MvoGoalChild) mvoGoalToUpdate).getMvoName());
					} else {
						mvo.add(textFieldMvoNaam.getText());
					}
				} else {
					if (textFieldNaam.getText().trim().isEmpty()) {
						mvo.add(((MvoGoalComp) mvoGoalToUpdate).getName());
					} else {
						mvo.add(textFieldNaam.getText());
					}
				}
				foutbericht.setText(null);
				window.close();
			}
		});
		wijzigenButton.setDefaultButton(true);

		Button annulerenButton = new Button("Annuleren");
		annulerenButton.setOnAction(e -> {

			window.close();
		});

		HBox hboxButtons = new HBox(50);

		hboxButtons.getChildren().addAll(wijzigenButton, annulerenButton);
		hboxButtons.setAlignment(Pos.CENTER);
		VBox layout = new VBox(10);
		if (mvoGoalToUpdate.isBlad()) {
			layout.getChildren().addAll(labelNaam, textFieldNaam, labelFoto, textFieldFoto, labelValue, textFieldValue,
					labelMvoNaam, textFieldMvoNaam, hboxButtons, foutbericht);
		} else {
			layout.getChildren().addAll(labelNaam, textFieldNaam, hboxButtons, foutbericht);
		}
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();

		if (mvo.isEmpty()) {
			System.out.println(mvo);
			return null;
		} else {

			return mvo;
		}

	}

}
