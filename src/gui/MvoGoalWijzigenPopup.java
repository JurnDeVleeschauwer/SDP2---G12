package gui;

import java.util.ArrayList;
import java.util.List;

import domain.Datasource;
import domain.MvoGoalAbstract;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MvoGoalWijzigenPopup {

	static List<Object> mvo;
	static MvoGoalAbstract mvoGoal;

	public static List<Object> display(MvoGoalAbstract mvoGoalToUpdate) {
		mvoGoal = mvoGoalToUpdate;
		mvo = new ArrayList<>();

		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("MvoGoal wijzigen");
		window.setMinWidth(350);
		window.setMinHeight(300);

		Label labelNaam = new Label("Naam:");
		TextField textFieldNaam = new TextField();
		// textFieldNaam.setText(mvoGoal.getMvoName());

		Label labelMvoNaam = new Label("Foto:");
		TextField textFieldMvoNaam = new TextField();
		// textFieldMvoNaam.setPromptText(mvoGoal.getIcon());

		Label labelValue = new Label("Value:");
		TextField textFieldValue = new TextField();
		// textFieldValue.setPromptText(mvoGoal.getValue());

		ChoiceBox<Datasource> cbDatasources = new ChoiceBox<>();
		ObservableList<Datasource> datasourcesList = FXCollections.observableArrayList();

		// mvoGoal.getDatasource().stream().forEach(a -> datasourcesList.add(a));

		cbDatasources.setItems(datasourcesList);

		Button wijzigenButton = new Button("Wijzigen");
		wijzigenButton.setOnAction(e -> {
			mvo.add(textFieldValue.getText());
			mvo.add(cbDatasources.getSelectionModel().getSelectedItem());
			mvo.add(textFieldMvoNaam.getText());
			mvo.add(textFieldNaam.getText());
			window.close();
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
		layout.getChildren().addAll(labelNaam, textFieldNaam, labelNaam, textFieldNaam, labelMvoNaam, textFieldMvoNaam,
				labelValue, textFieldValue, cbDatasources, hboxButtons);
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
