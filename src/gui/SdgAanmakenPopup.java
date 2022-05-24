package gui;

import java.util.ArrayList;
import java.util.List;

import domain.Datasource;
import domain.MvoGoalAbstract;
import domain.SdgComp;
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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SdgAanmakenPopup {

	static List<Object> sdg;
	static Label foutbericht = new Label();

	public static List<Object> display(List<MvoGoalAbstract> mvoGoals, boolean child) {
		sdg = new ArrayList<>();

		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Sdg maken");
		window.setMinWidth(350);
		window.setMinHeight(300);
		
		foutbericht.setStyle("-fx-font: normal 18px 'system'");
		foutbericht.setTextFill(Color.color(1, 0, 0));
		foutbericht.setMaxWidth(300.0);
		foutbericht.setWrapText(true);
		
		Label labeltitel = new Label("Nieuwe Sdg maken");
		labeltitel.setStyle("-fx-text-fill: #B2D235; -fx-font: normal bold 25px 'system'");
		labeltitel.setPadding(new Insets(0, 0, 25, 0));
		Label labelNaam = new Label("Naam:");
		labelNaam.setStyle("-fx-font: normal 18px 'system'");
		TextField textFieldNaam = new TextField();
		textFieldNaam.setPromptText("Sdg naam");
		textFieldNaam.setStyle("-fx-font: normal 18px 'system'");

		Label labelDescription = new Label("Description:");
		labelDescription.setStyle("-fx-font: normal 18px 'system'");
		TextField textFieldDescription = new TextField();
		textFieldDescription.setPromptText("Sdg description");
		textFieldDescription.setStyle("-fx-font: normal 18px 'system'");

		Label labelIcon = new Label("Icon:");
		labelIcon.setStyle("-fx-font: normal 18px 'system'");
		TextField textFieldIcon = new TextField();
		textFieldIcon.setPromptText("Sdg icon");
		textFieldIcon.setStyle("-fx-font: normal 18px 'system'");

		Label labelTarget = new Label("Target:");
		labelTarget.setStyle("-fx-font: normal 18px 'system'");
		TextField textFieldTarget = new TextField();
		textFieldTarget.setPromptText("Sdg target");
		textFieldTarget.setStyle("-fx-font: normal 18px 'system'");

		ChoiceBox<MvoGoalAbstract> cbMvoGoals = new ChoiceBox<>();
		ObservableList<MvoGoalAbstract> mvoGoalList = FXCollections.observableArrayList();

		mvoGoals.stream().forEach(a -> mvoGoalList.add(a));

		cbMvoGoals.setItems(mvoGoalList);

		Button aanmakenButton = new Button("Aanmaken");
		aanmakenButton.setOnAction(e -> {
			boolean finish = true;
			foutbericht.setText("");
			if (textFieldDescription.getText().trim().isEmpty()) {
				foutbericht.setText(foutbericht.getText() + "Naam moet ingvult zijn\n");
				finish = false;
			}
			if (textFieldNaam.getText().trim().isEmpty()) {
				foutbericht.setText(foutbericht.getText() + "MvoNaam moet ingvult zijn\n");
				finish = false;
			}
			if (child) {
				if (textFieldTarget.getText().trim().isEmpty()) {
					foutbericht.setText(foutbericht.getText() + "Value moet ingvult zijn\n");
					finish = false;
				} else {
					try {
						Integer.parseInt(textFieldTarget.getText());
					} catch (NumberFormatException ex) {
						foutbericht.setText(foutbericht.getText() + "Value moet een nummer zijn\n");
						finish = false;
					}
				}
				if (textFieldIcon.getText().trim().isEmpty()) {
					foutbericht.setText(foutbericht.getText() + "Foto moet ingvult zijn\n");
					finish = false;
				}

				if (cbMvoGoals.getSelectionModel().getSelectedItem() == null) {
					foutbericht.setText(foutbericht.getText() + "DataSource moet gekozen zijn\n");
					finish = false;
				}

			}
			if (finish) {
				sdg.add(textFieldNaam.getText());
				if (child) {
					sdg.add(textFieldIcon.getText());
					sdg.add(cbMvoGoals.getSelectionModel().getSelectedItem());
					sdg.add(textFieldTarget.getText());
					window.close();
				} else {
					sdg.add(textFieldDescription.getText());
				}
				foutbericht.setText(null);
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
		annulerenButton.setOnAction(e -> {

			window.close();
		});
		annulerenButton.setStyle(style);
		annulerenButton.setOnMouseEntered(e -> annulerenButton.setStyle(hoverstyle));
		annulerenButton.setOnMouseExited(e -> annulerenButton.setStyle(style));

		HBox hboxButtons = new HBox(50);

		hboxButtons.getChildren().addAll(aanmakenButton, annulerenButton);
		hboxButtons.setAlignment(Pos.CENTER);
		VBox layout = new VBox(10);
		if (child) {
			layout.getChildren().addAll(labeltitel, labelNaam, textFieldNaam, labelDescription, textFieldDescription,
					labelIcon, textFieldIcon, labelTarget, textFieldTarget, cbMvoGoals, hboxButtons, foutbericht);
		} else {
			layout.getChildren().addAll(labeltitel, labelNaam, textFieldNaam, labelDescription, textFieldDescription,
					hboxButtons, foutbericht);
		}
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();

		return sdg;

	}

}
