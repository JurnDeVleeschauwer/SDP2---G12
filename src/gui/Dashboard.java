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

		this.hoofdPaneel = hoofdPaneel;
		this.mcc=mcc;
		this.gridLinesVisibleProperty().set(true);
		this.getStylesheets().add(getClass().getResource("css.css").toExternalForm());

		configureerGrid();
		voegComponentenToe();
	}

	private void voegComponentenToe() {
		Button buttonCategoryPaneel = new Button("Categories");
		buttonCategoryPaneel.setOnAction(e -> {
			hoofdPaneel.toonCategoriePaneell();

		});
		buttonCategoryPaneel.setId("categorybtn_id");
		add(buttonCategoryPaneel, 0, 0);

		Button buttonSdg = new Button("Sdg 1");
		buttonSdg.setOnAction(e -> {
			hoofdPaneel.toonSdgPaneel(1);
		});
		buttonSdg.setId("sdgbtn_id");
		add(buttonSdg, 1, 0);

		Button buttonMvogoal = new Button("MvoGoal 1");
		buttonMvogoal.setOnAction(e -> {
			hoofdPaneel.toonMvoGoalPaneel(1);
		});
		buttonMvogoal.setId("mvogoalbtn_id");
		add(buttonMvogoal, 2, 0);

		Button buttonListMVO = new Button("List Mvo");
		buttonListMVO.setOnAction(e -> {
			hoofdPaneel.toonListMvoGoalPaneel();
		});
		buttonListMVO.setId("listmvobtn_id");
		add(buttonListMVO, 3, 0);
		
		Button buttonAanmelden = new Button("Afmelden");
		buttonAanmelden.setOnAction(e -> {
			mcc.afmelden();
			hoofdPaneel.toonNewAanmeldPaneel();
		});
		buttonAanmelden.setId("btnaanmelden_id");
		add(buttonAanmelden, 4, 0);

		

	}

	private void configureerGrid() {
		setPadding(new Insets(10));
		//setHgap(10);
		setVgap(10);

		ColumnConstraints col1 = new ColumnConstraints();
		col1.setHalignment(HPos.RIGHT);

		ColumnConstraints col2 = new ColumnConstraints();
		col2.setHgrow(Priority.ALWAYS);

		getColumnConstraints().addAll(col1, col2);
	}

}
