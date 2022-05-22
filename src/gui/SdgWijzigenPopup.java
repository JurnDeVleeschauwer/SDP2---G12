package gui;

import java.util.ArrayList;
import java.util.List;

import domain.SdgAbstract;
import domain.SdgChild;
import domain.SdgComp;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SdgWijzigenPopup {

	static List<Object> sdg;
	static SdgAbstract sdgUpdate;
	static Label foutbericht = new Label();

	public static List<Object> display(SdgAbstract sdgToUpdate) {
		sdgUpdate = sdgToUpdate;
		sdg = new ArrayList<>();

		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Sdg wijzigen");
		window.setMinWidth(350);
		window.setMinHeight(300);

		Label labelNaam = new Label("Naam:");
		TextField textFieldNaam = new TextField();

		Label labelDiscription = new Label("Discription:");
		TextField textFieldDiscription = new TextField();

		Label labelIcon = new Label("Icon:");
		TextField textFieldIcon = new TextField();

		Label labelTarget = new Label("Target:");
		TextField textFieldTarget = new TextField();

		Button wijzigenButton = new Button("Wijzigen");
		wijzigenButton.setOnAction(e -> {
			boolean finish = true;
			foutbericht.setText("");
			if (!(textFieldTarget.getText().isEmpty())) {
				try {
					Integer.parseInt(textFieldTarget.getText());
				} catch (NumberFormatException ex) {
					foutbericht.setText(foutbericht.getText() + "Value moet een nummer zijn\n");
					finish = false;
				}
			}

			if (finish) {
				if (textFieldDiscription.getText().trim().isEmpty()) {
					sdg.add(String.valueOf(sdgToUpdate.getDescription()));
				} else {
					sdg.add(textFieldDiscription.getText());
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
		if (sdgToUpdate.isBlad()) {
			layout.getChildren().addAll(labelNaam, textFieldNaam, labelDiscription, textFieldDiscription, labelIcon,
					textFieldIcon, labelTarget, textFieldTarget, hboxButtons);
		} else {
			layout.getChildren().addAll(labelNaam, textFieldNaam, labelDiscription, textFieldDiscription, hboxButtons);
		}
		layout.setAlignment(Pos.CENTER);
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
