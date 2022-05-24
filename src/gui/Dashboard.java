package gui;

import domain.DomeinController;
import domain.MvoCoordinatorController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;


public class Dashboard extends GridPane {

	private final HoofdPaneel hoofdPaneel;
	private MvoCoordinatorController mcc;
	
	public Dashboard(HoofdPaneel hoofdPaneel, MvoCoordinatorController mcc) {
		this.setId("dashboard_id");

		this.hoofdPaneel = hoofdPaneel;
		this.mcc=mcc;
		this.gridLinesVisibleProperty().set(true);
		this.getStylesheets().add(getClass().getResource("css.css").toExternalForm());

		configureerGrid();
		voegComponentenToe();
	}

	private void voegComponentenToe() {
		String normalStyle = "";
		String clickedStyle = "-fx-background-color: -fx-fbluelight; -fx-text-fill: black;";
		
		Button buttonCategoryPaneel = new Button("Categorieën");
		Button buttonListSdg = new Button("SDG's");
		Button buttonListMVO = new Button("MVO doelstellingen");
		
		
		buttonCategoryPaneel.setOnAction(e -> {
			hoofdPaneel.toonCategoriePaneell();
			
			buttonCategoryPaneel.setStyle(clickedStyle);
			buttonListSdg.setStyle(normalStyle);
			buttonListMVO.setStyle(normalStyle);

		});
		buttonCategoryPaneel.setId("categorybtn_id");
		add(buttonCategoryPaneel, 0, 0);
		buttonCategoryPaneel.setStyle(clickedStyle);
		
		buttonListSdg.setOnAction(e -> {
			hoofdPaneel.toonListSdgPaneel();
			
			buttonCategoryPaneel.setStyle(normalStyle);
			buttonListSdg.setStyle(clickedStyle);
			buttonListMVO.setStyle(normalStyle);
		});
		buttonListSdg.setId("listsdgbtn_id");
		add(buttonListSdg, 1, 0);
		
		buttonListMVO.setOnAction(e -> {
			hoofdPaneel.toonListMvoGoalPaneel();
			
			buttonCategoryPaneel.setStyle(normalStyle);
			buttonListSdg.setStyle(normalStyle);
			buttonListMVO.setStyle(clickedStyle);
		});
		buttonListMVO.setId("listmvobtn_id");
		add(buttonListMVO, 2, 0);
		
		
		
		Button buttonAanmelden = new Button("Afmelden");
		buttonAanmelden.setOnAction(e -> {
			mcc.afmelden();
			hoofdPaneel.toonNewAanmeldPaneel();
		});
		buttonAanmelden.setId("btnaanmelden_id");
		add(buttonAanmelden, 4, 0);

		

	}

	private void configureerGrid() {
//		setPadding(new Insets(10));
//		//setHgap(10);
//		setVgap(10);

		ColumnConstraints col1 = new ColumnConstraints();
		col1.setHalignment(HPos.LEFT);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setHalignment(HPos.LEFT);
		ColumnConstraints col3 = new ColumnConstraints();
		col3.setHalignment(HPos.LEFT);
		ColumnConstraints col4 = new ColumnConstraints();
		col4.setHalignment(HPos.LEFT);
		ColumnConstraints col5 = new ColumnConstraints();
		col5.setHalignment(HPos.RIGHT);
		col5.setHgrow(Priority.ALWAYS);

		getColumnConstraints().addAll(col1, col2, col3, col4, col5);
	}

}
