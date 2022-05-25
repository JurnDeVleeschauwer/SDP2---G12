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

		hbox.setAlignment(Pos.CENTER);
		vbox.setAlignment(Pos.CENTER_LEFT);
		

		getChildren().add(hbox);
		hbox.getChildren().add(vbox);

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
		vbox.getChildren().add(grid);
		StackPane.setAlignment(grid, Pos.CENTER);
	}

	private final TextField gebruikersnaam = new TextField();
	private final PasswordField wachtwoord = new PasswordField();
	private Label lblGebruikersnaamFout = new Label();
	private Label lblWachtwoordFout = new Label();
	private Label foutbericht = new Label();

	private void voegComponentenToe() {
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPrefWidth(150);
		
		String foutstyle = "-fx-font: normal 18px 'system'; -fx-text-fill: #FF0000; -fx-wrap-text: true";

		
		// Label aanmelden
		Label header = new Label("Aanmelden");
		header.setId("header_id");
		HBox headerhbox = new HBox();
		headerhbox.setAlignment(Pos.CENTER);
		headerhbox.getChildren().add(header);
		// header.setStyle("-fx-text-fill: #B2D235; -fx-font: normal bold 47px
		// 'system'");
		vbox.getChildren().add(headerhbox);

		// label gebruikersnaam + inputbox
		Label gebruikersnaamLbl = new Label("Gebruikersnaam:");
		gebruikersnaamLbl.setId("gebruikersnaamlbl_id");
		lblGebruikersnaamFout.setStyle(foutstyle);
		GridPane gridgebruikersnaam = new GridPane();
		gridgebruikersnaam.getColumnConstraints().add(col1);
		gridgebruikersnaam.add(gebruikersnaamLbl, 0, 0);
		gridgebruikersnaam.add(lblGebruikersnaamFout, 1, 0);
		gebruikersnaam.setId("gebruikersnaambtn_id");
		// gebruikersnaamLbl.setStyle("-fx-text-fill: #B2D235; -fx-font: normal 18px
		// 'system'");
		// gebruikersnaam.setStyle("-fx-font: normal 18px 'system';");
		gebruikersnaam.setPrefWidth(525.0);
//		gebruikersnaam.setMaxWidth(300.0);
		vbox.getChildren().add(gridgebruikersnaam);
		vbox.getChildren().add(gebruikersnaam);

		// label wachtwoord + inputbox
		Label wachtwoordLbl = new Label("Wachtwoord");
		wachtwoordLbl.setId("wachtwoordlbl_id");
		lblWachtwoordFout.setStyle(foutstyle);
		GridPane gridwachtwoord = new GridPane();
		gridwachtwoord.getColumnConstraints().add(col1);
		gridwachtwoord.add(wachtwoordLbl, 0, 0);
		gridwachtwoord.add(lblWachtwoordFout, 1, 0);
		wachtwoord.setId("wachtwoordbtn_id");
		// wachtwoordLbl.setStyle("-fx-text-fill: #B2D235; -fx-font: normal 18px
		// 'system'");
		// wachtwoord.setStyle("-fx-font: normal 18px 'system';");
		wachtwoord.setPrefWidth(525.0);
//		wachtwoord.setMaxWidth(300.0);
		vbox.getChildren().add(gridwachtwoord);
		vbox.getChildren().add(wachtwoord);

		Button aanmelden = new Button("Aanmelden");
		aanmelden.setId("aanmeldenbtn_id");
		aanmelden.setOnAction(this::aanmelden);
		aanmelden.setDefaultButton(true);
		aanmelden.setPrefWidth(525.0);
		// aanmelden.setStyle("-fx-background-color: #0A759D; -fx-text-fill: #B2D235;
		// -fx-font: normal 18px 'system'");
		foutbericht.setStyle(foutstyle);
		vbox.getChildren().add(foutbericht);
		vbox.getChildren().add(aanmelden);

	}

	private void aanmelden(ActionEvent event) {
		boolean finish = true;
		lblGebruikersnaamFout.setText("");
		lblWachtwoordFout.setText("");
		foutbericht.setText("");

		if (gebruikersnaam.getText().trim().isEmpty()) {
			lblGebruikersnaamFout.setText("Gelieve uw gebruikersnaam op te geven");
			finish = false;
		} 
		if (wachtwoord.getText().trim().isEmpty()) {
			lblWachtwoordFout.setText("Gelieve uw wachtwoord op te geven");
			finish = false;
			
		}
		if(finish) {
			try {
				mvoCoordinatorController.login(gebruikersnaam.getText().trim(), wachtwoord.getText().trim());
			} catch (AuthenticationException e) {
				foutbericht.setText("Ongeldige combinatie, gelieve opnieuw te proberen.");
				return;
			} catch (InvalidRoleValueException e) {
				foutbericht.setText("U heeft niet de vereiste privileges om deze applicatie te gebruiken");
				return;
			}

			foutbericht.setText(null);
			hoofdPaneel.enableDashboard();
			hoofdPaneel.toonCategoriePaneell();
		}
		

	}

}
