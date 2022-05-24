package gui;

import java.util.ArrayList;
import java.util.List;

import domain.SdgAbstract;
import domain.SdgChild;
import domain.SdgComp;
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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SdgWijzigenPopup {

	static List<Object> sdg;
	static SdgAbstract sdgUpdate;

	public static List<Object> display(SdgAbstract sdgToUpdate) {
		sdgUpdate = sdgToUpdate;
		sdg = new ArrayList<>();
		
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPrefWidth(150);
		
		String foutstyle = "-fx-font: normal 18px 'system'; -fx-text-fill: #FF0000; -fx-max-width: 300px; -fx-wrap-text: true";


		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Sdg wijzigen");
		window.setMinWidth(350);
		window.setMinHeight(300);
		
		Label labeltitel = new Label("SDG wijzigen");
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
		textFieldNaam.setText(sdgToUpdate.getName());
		textFieldNaam.setStyle("-fx-font: normal 18px 'system'");

		Label labelDescription = new Label("Description:");
		labelDescription.setStyle("-fx-font: normal 18px 'system'");
		Label lblDescriptionFout = new Label();
		lblDescriptionFout.setStyle(foutstyle);
		GridPane griddescription = new GridPane();
		griddescription.getColumnConstraints().add(col1);
		griddescription.add(labelDescription, 0, 0);
		griddescription.add(lblDescriptionFout, 1, 0);
		TextField textFieldDescription = new TextField();
		textFieldDescription.setText(sdgToUpdate.getDescription());
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
		textFieldIcon.setPromptText("Sdg icon");
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
		textFieldTarget.setPromptText("Sdg target");
		textFieldTarget.setStyle("-fx-font: normal 18px 'system'");

		Button wijzigenButton = new Button("Wijzigen");
		wijzigenButton.setOnAction(e -> {
			boolean finish = true;
			if (textFieldDescription.getText().trim().isEmpty()) {
				lblDescriptionFout.setText("Naam moet ingvult zijn\n");
				finish = false;
			}
			if (textFieldNaam.getText().trim().isEmpty()) {
				lblNaamFout.setText("SDG naam moet ingvult zijn\n");
				finish = false;
			}
			if (sdgToUpdate.isBlad()) {
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
			}

			if (finish) {
				if (textFieldDescription.getText().trim().isEmpty()) {
					sdg.add(String.valueOf(sdgToUpdate.getDescription()));
				} else {
					sdg.add(textFieldDescription.getText());
				}
				if (textFieldNaam.getText().trim().isEmpty()) {
					sdg.add(String.valueOf(sdgToUpdate.getName()));
				} else {
					sdg.add(textFieldNaam.getText());
				}
				if (sdgToUpdate.isBlad()) {

					if (textFieldIcon.getText().trim().isEmpty()) {
						sdg.add(String.valueOf(((SdgChild) sdgToUpdate).getIcon()));
					} else {
						sdg.add(textFieldIcon.getText());
					}
					if (textFieldTarget.getText().trim().isEmpty()) {
						sdg.add(String.valueOf(((SdgChild) sdgToUpdate).getTarget()));
					} else {
						sdg.add(textFieldTarget.getText());
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
		layout.setPadding(new Insets(10,10,10,10));
		if (sdgToUpdate.isBlad()) {
			layout.getChildren().addAll(labeltitel, gridnaam, textFieldNaam, griddescription, textFieldDescription, gridicon,
					textFieldIcon, gridtarget, textFieldTarget, hboxButtons);
		} else {
			layout.getChildren().addAll(labeltitel, gridnaam, textFieldNaam, griddescription, textFieldDescription, hboxButtons);
		}
		layout.setAlignment(Pos.TOP_LEFT);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();

		if (sdg.isEmpty()) {
			System.out.println(sdg);
			return null;
		} else {

			return sdg;
		}

	}

}
