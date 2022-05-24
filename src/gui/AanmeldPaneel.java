package gui;

import javax.management.relation.InvalidRoleValueException;
import javax.security.sasl.AuthenticationException;

import domain.MvoCoordinatorController;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class AanmeldPaneel extends StackPane {
	private final HoofdPaneel hoofdPaneel;
	private GridPane grid;
	private final MvoCoordinatorController mvoCoordinatorController;
	private VBox vbox;
	private HBox hbox;

	public AanmeldPaneel(HoofdPaneel hoofdPaneel, MvoCoordinatorController mvoCoordinatorController) {

		this.hoofdPaneel = hoofdPaneel;
		this.mvoCoordinatorController = mvoCoordinatorController;

		// this.getStylesheets().add(getClass().getResource("css.css").toExternalForm());

		// this.setStyle("-fx-background-color: #004C6A");

		this.setId("aanmeldpanneel_id");

		this.setAlignment(Pos.CENTER);

		vbox = new VBox();
		hbox = new HBox();

		vbox.setAlignment(Pos.CENTER);
		hbox.setAlignment(Pos.CENTER);

		getChildren().add(vbox);
		vbox.getChildren().add(hbox);

		configureerGrid();
		voegComponentenToe();
	}

	private void configureerGrid() {
		grid = new GridPane();
		grid.getStylesheets().add(getClass().getResource("css.css").toExternalForm());
		// grid.gridLinesVisibleProperty().set(true);

		grid.setPadding(new Insets(10));
		grid.setHgap(10);
		grid.setVgap(10);
		hbox.getChildren().add(grid);
		StackPane.setAlignment(grid, Pos.CENTER);
	}

	private final TextField gebruikersnaam = new TextField();
	private final PasswordField wachtwoord = new PasswordField();
	private final Label foutbericht = new Label();

	private void voegComponentenToe() {
		// Label aanmelden
		Label header = new Label("Aanmelden");
		header.setId("header_id");
		// header.setStyle("-fx-text-fill: #B2D235; -fx-font: normal bold 47px
		// 'system'");
		grid.add(header, 0, 0, 1, 1);

		// label gebruikersnaam + inputbox
		Label gebruikersnaamLbl = new Label("Gebruikersnaam");
		gebruikersnaamLbl.setId("gebruikersnaamlbl_id");
		gebruikersnaam.setId("gebruikersnaambtn_id");
		// gebruikersnaamLbl.setStyle("-fx-text-fill: #B2D235; -fx-font: normal 18px
		// 'system'");
		// gebruikersnaam.setStyle("-fx-font: normal 18px 'system';");
		gebruikersnaam.setPrefWidth(300.0);
		gebruikersnaam.setMaxWidth(300.0);
		foutbericht.setStyle("-fx-font: normal 18px 'system'");
		foutbericht.setTextFill(Color.color(1, 0, 0));
		foutbericht.setMaxWidth(300.0);
		foutbericht.setWrapText(true);
		grid.add(gebruikersnaamLbl, 0, 1, 1, 1);
		grid.add(gebruikersnaam, 0, 2, 1, 1);

		// label wachtwoord + inputbox
		Label wachtwoordLbl = new Label("Wachtwoord");
		wachtwoordLbl.setId("wachtwoordlbl_id");
		wachtwoord.setId("wachtwoordbtn_id");
		// wachtwoordLbl.setStyle("-fx-text-fill: #B2D235; -fx-font: normal 18px
		// 'system'");
		// wachtwoord.setStyle("-fx-font: normal 18px 'system';");
		wachtwoord.setPrefWidth(300.0);
		wachtwoord.setMaxWidth(300.0);
		grid.add(wachtwoordLbl, 0, 3, 1, 1);
		grid.add(wachtwoord, 0, 4, 1, 1);

		Button aanmelden = new Button("Aanmelden");
		aanmelden.setId("aanmeldenbtn_id");
		aanmelden.setOnAction(this::aanmelden);
		aanmelden.setDefaultButton(true);
		aanmelden.setPrefWidth(300.0);
		// aanmelden.setStyle("-fx-background-color: #0A759D; -fx-text-fill: #B2D235;
		// -fx-font: normal 18px 'system'");
		grid.add(foutbericht, 0, 5, 1, 1);
		grid.add(aanmelden, 0, 6, 1, 1);

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
		} catch (InvalidRoleValueException e) {
			foutbericht.setText("U heeft niet de vereiste privileges om deze applicatie te gebruiken");
			return;
		}
		;
		foutbericht.setText(null);
		hoofdPaneel.enableDashboard();
		hoofdPaneel.toonCategoriePaneell();

	}

}
