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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SdgAanmakenPopup {

	static List<Object> SDG;

	public static List<Object> display(List<MvoGoalAbstract> mvoGoals, boolean child) {
		SDG = new ArrayList<>();
		
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPrefWidth(150);
		
		String foutstyle = "-fx-font: normal 18px 'system'; -fx-text-fill: #FF0000; -fx-max-width: 300px; -fx-wrap-text: true";

		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("SDG maken");
		
		Label labeltitel = new Label("Nieuwe SDG maken");
		labeltitel.setStyle("-fx-text-fill: #B2D235; -fx-font: normal bold 25px 'system'");
		
		Label labelNaam = new Label("SDG naam:");
		labelNaam.setStyle("-fx-font: normal 18px 'system'");
		Label lblNaamFout = new Label();
		lblNaamFout.setStyle(foutstyle);
		GridPane gridnaam = new GridPane();
		gridnaam.getColumnConstraints().add(col1);
		gridnaam.add(labelNaam, 0, 0);
		gridnaam.add(lblNaamFout, 1, 0);
		TextField textFieldNaam = new TextField();
		textFieldNaam.setMaxWidth(450);
		textFieldNaam.setPromptText("SDG naam");
		textFieldNaam.setStyle("-fx-font: normal 18px 'system'");

		Label labelDescription = new Label("Omschrijving:");
		labelDescription.setStyle("-fx-font: normal 18px 'system'");
		Label lblDescriptionFout = new Label();
		lblDescriptionFout.setStyle(foutstyle);
		GridPane griddescription = new GridPane();
		griddescription.getColumnConstraints().add(col1);
		griddescription.add(labelDescription, 0, 0);
		griddescription.add(lblDescriptionFout, 1, 0);
		TextField textFieldDescription = new TextField();
		textFieldDescription.setPromptText("SDG omschrijving");
		textFieldDescription.setMaxWidth(450);
		textFieldDescription.setStyle("-fx-font: normal 18px 'system'");

		Label labelIcon = new Label("Icon:");
		labelIcon.setStyle("-fx-font: normal 18px 'system'");
		Label lblIconFout = new Label();
		lblIconFout.setStyle(foutstyle);
		GridPane gridicon = new GridPane();
		gridicon.getColumnConstraints().add(col1);
		gridicon.add(labelIcon, 0, 0);
		gridicon.add(lblIconFout, 1, 0);
		TextField textFieldIcon = new TextField();
		textFieldIcon.setMaxWidth(450);
		textFieldIcon.setPromptText("SDG icon");
		textFieldIcon.setStyle("-fx-font: normal 18px 'system'");

		Label labelTarget = new Label("Target:");
		labelTarget.setStyle("-fx-font: normal 18px 'system'");
		Label lblTargetFout = new Label();
		lblIconFout.setStyle(foutstyle);
		GridPane gridtarget = new GridPane();
		gridtarget.getColumnConstraints().add(col1);
		gridtarget.add(labelTarget, 0, 0);
		gridtarget.add(lblTargetFout, 1, 0);
		TextField textFieldTarget = new TextField();
		textFieldTarget.setMaxWidth(450);
		textFieldTarget.setPromptText("SDG target");
		textFieldTarget.setStyle("-fx-font: normal 18px 'system'");

		Label labelMvoGoal = new Label("MVO goal:");
		labelMvoGoal.setStyle("-fx-font: normal 18px 'system'");
		Label lblMvoGoalFout = new Label();
		lblMvoGoalFout.setStyle(foutstyle);
		GridPane gridmvogoal = new GridPane();
		gridmvogoal.getColumnConstraints().add(col1);
		gridmvogoal.add(labelMvoGoal, 0, 0);
		gridmvogoal.add(lblMvoGoalFout, 1, 0);
		ChoiceBox<MvoGoalAbstract> cbMvoGoals = new ChoiceBox<>();
		cbMvoGoals.setStyle("-fx-font: normal 18px 'system'");
		cbMvoGoals.setMaxWidth(450);
		ObservableList<MvoGoalAbstract> mvoGoalList = FXCollections.observableArrayList();

		mvoGoals.stream().forEach(a -> mvoGoalList.add(a));

		cbMvoGoals.setItems(mvoGoalList);

		Button aanmakenButton = new Button("Aanmaken");
		aanmakenButton.setOnAction(e -> {
			boolean finish = true;
			if (textFieldDescription.getText().trim().isEmpty()) {
				lblDescriptionFout.setText("Naam moet ingvult zijn\n");
				finish = false;
			}
			if (textFieldNaam.getText().trim().isEmpty()) {
				lblNaamFout.setText("SDG naam moet ingvult zijn\n");
				finish = false;
			}
			if (child) {
				if (textFieldTarget.getText().trim().isEmpty()) {
					lblTargetFout.setText("Value moet ingvult zijn\n");
					finish = false;
				} else {
					try {
						Integer.parseInt(textFieldTarget.getText());
					} catch (NumberFormatException ex) {
						lblTargetFout.setText("Value moet een nummer zijn\n");
						finish = false;
					}
				}
				if (textFieldIcon.getText().trim().isEmpty()) {
					lblIconFout.setText("Foto moet ingvult zijn\n");
					finish = false;
				}

				if (cbMvoGoals.getSelectionModel().getSelectedItem() == null) {
					lblMvoGoalFout.setText("DataSource moet gekozen zijn\n");
					finish = false;
				}

			}
			if (finish) {
				SDG.add(textFieldNaam.getText());
				if (child) {
					SDG.add(textFieldIcon.getText());
					SDG.add(cbMvoGoals.getSelectionModel().getSelectedItem());
					SDG.add(textFieldTarget.getText());
					window.close();
				} else {
					SDG.add(textFieldDescription.getText());
				}
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
		layout.setPadding(new Insets(10, 10, 10, 10));
		if (child) {
			layout.getChildren().addAll(labeltitel, gridnaam, textFieldNaam, griddescription, textFieldDescription,
					gridicon, textFieldIcon, gridtarget, textFieldTarget,gridmvogoal, cbMvoGoals, hboxButtons);
		} else {
			layout.getChildren().addAll(labeltitel, gridnaam, textFieldNaam, griddescription, textFieldDescription,
					hboxButtons);
		}
		layout.setAlignment(Pos.TOP_LEFT);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();

		return SDG;

	}

}
