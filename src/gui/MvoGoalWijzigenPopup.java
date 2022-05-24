package gui;

import java.util.ArrayList;
import java.util.List;

import domain.Datasource;
import domain.MvoGoalAbstract;
import domain.MvoGoalChild;
import domain.MvoGoalComp;
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

public class MvoGoalWijzigenPopup {

	static List<Object> mvo;
	static MvoGoalAbstract mvoGoal;

	public static List<Object> display(MvoGoalAbstract mvoGoalToUpdate) {
		mvoGoal = mvoGoalToUpdate;
		mvo = new ArrayList<>();
		
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPrefWidth(150);
		
		String foutstyle = "-fx-font: normal 18px 'system'; -fx-text-fill: #FF0000; -fx-max-width: 300px; -fx-wrap-text: true";

		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("MVO Doelstelling wijzigen");

		Label labeltitel = new Label("MVO doelstelling wijzigen");
		labeltitel.setStyle("-fx-text-fill: #B2D235; -fx-font: normal bold 25px 'system'");
		
		Label labelNaam = new Label("MVO naam:");
		labelNaam.setStyle("-fx-font: normal 18px 'system'");
		Label lblNaamFout = new Label();
		lblNaamFout.setStyle(foutstyle);
		GridPane gridnaam = new GridPane();
		gridnaam.getColumnConstraints().add(col1);
		gridnaam.add(labelNaam, 0, 0);
		gridnaam.add(lblNaamFout, 1, 0);
		TextField textFieldNaam = new TextField();
		textFieldNaam.setText("Nog in te vullen");
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
		textFieldFoto.setStyle("-fx-font: normal 18px 'system'");
		textFieldFoto.setPromptText("foto");
		

		Label labelValue = new Label("Value:");
		labelValue.setStyle("-fx-font: normal 18px 'system'");
		Label lblValueFout = new Label();
		lblValueFout.setStyle(foutstyle);
		GridPane gridvalue = new GridPane();
		gridvalue.getColumnConstraints().add(col1);
		gridvalue.add(labelValue, 0, 0);
		gridvalue.add(lblValueFout, 1, 0);
		TextField textFieldValue = new TextField();
		textFieldValue.setStyle("-fx-font: normal 18px 'system'");
		textFieldValue.setPromptText("Value van de MVO");

		Label labelMvoNaam = new Label("MVO name:");
		labelMvoNaam.setStyle("-fx-font: normal 18px 'system'");
		Label lblMvoNaamFout = new Label();
		lblMvoNaamFout.setStyle(foutstyle);
		GridPane gridmvonaam = new GridPane();
		gridmvonaam.getColumnConstraints().add(col1);
		gridmvonaam.add(labelMvoNaam, 0, 0);
		gridmvonaam.add(lblMvoNaamFout, 1, 0);
		TextField textFieldMvoNaam = new TextField();
		textFieldMvoNaam.setStyle("-fx-font: normal 18px 'system'");
		textFieldMvoNaam.setPromptText("MVO naam");

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
			if (mvoGoalToUpdate.isBlad()) {
				if (textFieldValue.getText().trim().isEmpty()) {
					lblValueFout.setText("Value moet ingvult zijn\n");
					finish = false;
				} else {
					try {
						Integer.parseInt(textFieldValue.getText());
					} catch (NumberFormatException ex) {
						lblValueFout.setText("Value moet een nummer zijn\n");
						finish = false;
					}
				}
				if (textFieldFoto.getText().trim().isEmpty()) {
					lblFotoFout.setText("Foto moet ingvult zijn\n");
					finish = false;
				}
				if (textFieldMvoNaam.getText().trim().isEmpty()) {
					lblMvoNaamFout.setText("MvoNaam moet ingvult zijn\n");
					finish = false;
				}
			} else {
				if (textFieldNaam.getText().trim().isEmpty()) {
					lblNaamFout.setText("Naam moet ingvult zijn\n");
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
				window.close();
			}
		});
		wijzigenButton.setDefaultButton(true);
		String style = "-fx-background-color: #004C6A; -fx-text-fill: #B2D235; -fx-border-color: black; -fx-border-width: 1;-fx-font: normal 18px 'system'; -fx-pref-width: 200px;";
		String hoverstyle = "-fx-background-color: #B2D235; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 1;-fx-font: normal 18px 'system'; -fx-pref-width: 200px;";
		wijzigenButton.setStyle(style);
		wijzigenButton.setOnMouseEntered(e -> wijzigenButton.setStyle(hoverstyle));
		wijzigenButton.setOnMouseExited(e -> wijzigenButton.setStyle(style));
		
		Button annulerenButton = new Button("Annuleren");
		annulerenButton.setOnAction(e -> {

			window.close();
		});
		annulerenButton.setStyle(style);
		annulerenButton.setOnMouseEntered(e -> annulerenButton.setStyle(hoverstyle));
		annulerenButton.setOnMouseExited(e -> annulerenButton.setStyle(style));
		
		HBox hboxButtons = new HBox(50);

		hboxButtons.getChildren().addAll(wijzigenButton, annulerenButton);
		hboxButtons.setAlignment(Pos.CENTER);
		VBox layout = new VBox(10);
		if (mvoGoalToUpdate.isBlad()) {
			layout.getChildren().addAll(labeltitel, gridnaam, textFieldNaam, gridfoto, textFieldFoto, gridvalue, textFieldValue,
					gridmvonaam, textFieldMvoNaam, hboxButtons);
		} else {
			layout.getChildren().addAll(labeltitel, gridnaam, textFieldNaam, hboxButtons);
		}
		layout.setAlignment(Pos.TOP_LEFT);
		layout.setPadding(new Insets(10,10,10,10));
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
