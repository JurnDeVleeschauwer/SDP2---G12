package gui;

import javax.security.sasl.AuthenticationException;

import domain.MvoCoordinatorController;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

public class AanmeldPaneel extends GridPane {
	private final HoofdPaneel hoofdPaneel;
	private final MvoCoordinatorController mvoCoordinatorController;
	

	public AanmeldPaneel(HoofdPaneel hoofdPaneel, MvoCoordinatorController mvoCoordinatorController) {

		this.hoofdPaneel = hoofdPaneel;
		this.mvoCoordinatorController = mvoCoordinatorController;
		this.setStyle("-fx-background-color: #004C6A");

		configureerGrid();
		voegComponentenToe();
	}
	
	private void configureerGrid() {
		//this.gridLinesVisibleProperty().set(true);
		
		setPadding(new Insets(10));
		setHgap(10);
		setVgap(10);
	}

	private final TextField gebruikersnaam = new TextField();
	private final PasswordField wachtwoord = new PasswordField();
	private final Label foutbericht = new Label();

	private void voegComponentenToe() {
		//Label aanmelden
		Label header = new Label("Aanmelden");
		header.setStyle("-fx-text-fill: #B2D235; -fx-font: normal bold 47px 'system'");
		add(header, 0, 0, 1, 1);
		
		
		//label gebruikersnaam + inputbox
		Label gebruikersnaamLbl = new Label("Gebruikersnaam");
		gebruikersnaamLbl.setStyle("-fx-text-fill: #B2D235; -fx-font: normal 18px 'system'");
		gebruikersnaam.setStyle("-fx-font: normal 18px 'system';");
		gebruikersnaam.setPrefWidth(300.0);
		gebruikersnaam.setMaxWidth(300.0);
		foutbericht.setStyle("-fx-font: normal 18px 'system'");
		foutbericht.setTextFill(Color.color(1, 0, 0));
		add(gebruikersnaamLbl, 0, 1, 1, 1);
		add(gebruikersnaam, 0, 2, 1, 1);

		//label wachtwoord + inputbox
		Label wachtwoordLbl = new Label("Wachtwoord");
		wachtwoordLbl.setStyle("-fx-text-fill: #B2D235; -fx-font: normal 18px 'system'");
		wachtwoord.setStyle("-fx-font: normal 18px 'system';");
		wachtwoord.setPrefWidth(300.0);
		wachtwoord.setMaxWidth(300.0);
		add(wachtwoordLbl, 0, 3, 1, 1);
		add(wachtwoord, 0, 4, 1, 1);

		Button aanmelden = new Button("Aanmelden");
		aanmelden.setOnAction(this::aanmelden);
		aanmelden.setDefaultButton(true);
		aanmelden.setStyle("-fx-background-color: #0A759D; -fx-text-fill: #B2D235; -fx-font: normal 18px 'system'");
		add(foutbericht, 0, 5, 1, 1);
		add(aanmelden, 0, 6, 1, 1);
		

	}

	private void aanmelden(ActionEvent event) {

		if (gebruikersnaam.getText().trim().isEmpty()) {

			foutbericht.setText("Gelieve uw gebruikersnaam op te geven");
			if (wachtwoord.getText().trim().isEmpty()) {
				foutbericht.setText("Gelieve uw gebruikersnaam en wachtwoord op te geven");
				return;
			}
			return;
		} else if (wachtwoord.getText().trim().isEmpty()) {
			foutbericht.setText("Gelieve uw wachtwoord op te geven");
			return;
		}
		try {
			mvoCoordinatorController.login(gebruikersnaam.getText().trim(), wachtwoord.getText().trim());
		} catch (AuthenticationException e) {
			foutbericht.setText("Ongeldige combinatie, gelieve opnieuw te proberen.");
			return;

		}
		;
		foutbericht.setText(null);
		hoofdPaneel.toonCategoriePaneell();

	}

}
